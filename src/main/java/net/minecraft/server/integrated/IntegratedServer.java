package net.minecraft.server.integrated;

import com.google.common.collect.Lists;
import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.entity.player.EntityPlayerMP;
import com.google.common.util.concurrent.Futures;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.FutureTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.ClearWater;
import net.optifine.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends MinecraftServer {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Minecraft mc;
   private final WorldSettings theWorldSettings;
   public World difficultyUpdateWorld = null;
   public BlockPos difficultyUpdatePos = null;
   public DifficultyInstance difficultyLast = null;
   private boolean isGamePaused;
   private boolean isPublic;
   private ThreadLanServerPing lanServerPing;
   private long ticksSaveLast = 0L;

   private void fixWorldWeather(WorldServer world) {
      WorldInfo worldInfo = world.getWorldInfo();
      if (worldInfo.isRaining() || worldInfo.isThundering()) {
         worldInfo.setRainTime(0);
         worldInfo.setRaining(false);
         world.setRainStrength(0.0F);
         worldInfo.setThunderTime(0);
         worldInfo.setThundering(false);
         world.setThunderStrength(0.0F);
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(2, 0.0F));
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(7, 0.0F));
         this.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(8, 0.0F));
      }
   }

   @Override
   public boolean isSnooperEnabled() {
      return Minecraft.getMinecraft().isSnooperEnabled();
   }

   @Override
   public int getOpPermissionLevel() {
      return 4;
   }

   @Override
   public void tick() {
      this.onTick();
      boolean isGamePaused2 = this.isGamePaused;
      this.isGamePaused = Minecraft.getMinecraft().getNetHandler() != null && Minecraft.getMinecraft().isGamePaused();
      if (!isGamePaused2 && this.isGamePaused) {
         LOGGER.info("Saving and pausing game...");
         this.getConfigurationManager().saveAllPlayerData();
         this.saveAllWorlds(false);
      }

      if (this.isGamePaused) {
         synchronized (this.futureTaskQueue) {
            while (!this.futureTaskQueue.isEmpty()) {
               Util.runTask((FutureTask)this.futureTaskQueue.poll(), LOGGER);
            }
         }
      } else {
         super.tick();
         if (this.mc.gameSettings.renderDistanceChunks != this.getConfigurationManager().getViewDistance()) {
            LOGGER.info("Changing view distance to {}, from {}", new Object[]{this.mc.gameSettings.renderDistanceChunks, this.getConfigurationManager().getViewDistance()});
            this.getConfigurationManager().setViewDistance(this.mc.gameSettings.renderDistanceChunks);
         }

         if (this.mc.theWorld != null) {
            WorldInfo worldInfo = this.worldServers[0].getWorldInfo();
            WorldInfo worldInfo2 = this.mc.theWorld.getWorldInfo();
            if (!worldInfo.isDifficultyLocked() && worldInfo2.getDifficulty() != worldInfo.getDifficulty()) {
               LOGGER.info("Changing difficulty to {}, from {}", new Object[]{worldInfo2.getDifficulty(), worldInfo.getDifficulty()});
               this.setDifficultyForAllWorlds(worldInfo2.getDifficulty());
            } else if (worldInfo2.isDifficultyLocked() && !worldInfo.isDifficultyLocked()) {
               LOGGER.info("Locking difficulty to {}", new Object[]{worldInfo2.getDifficulty()});

               for (WorldServer world : this.worldServers) {
                  if (world != null) {
                     world.getWorldInfo().setDifficultyLocked(true);
                  }
               }
            }
         }
      }
   }

   @Override
   public void loadAllWorlds(String str, String str2, long l, WorldType worldType, String str3) {
      this.convertMapIfNeeded(str);
      this.worldServers = new WorldServer[3];
      this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
      ISaveHandler saveLoader = this.getActiveAnvilConverter().getSaveLoader(str, true);
      this.setResourcePackFromWorld(this.getFolderName(), saveLoader);
      WorldInfo worldInfo = saveLoader.loadWorldInfo();
      if (worldInfo == null) {
         worldInfo = new WorldInfo(this.theWorldSettings, str2);
      } else {
         worldInfo.setWorldName(str2);
      }

      for (int i = 0; i < this.worldServers.length; i++) {
         byte b = 0;
         if (i == 1) {
            b = -1;
         }

         if (i == 2) {
            b = 1;
         }

         if (i == 0) {
            if (this.isDemo()) {
               this.worldServers[0] = (WorldServer)new DemoWorldServer(this, saveLoader, worldInfo, b, this.theProfiler).init();
            } else {
               this.worldServers[0] = (WorldServer)new WorldServer(this, saveLoader, worldInfo, b, this.theProfiler).init();
            }

            this.worldServers[0].initialize(this.theWorldSettings);
         } else {
            this.worldServers[i] = (WorldServer)new WorldServerMulti(this, saveLoader, b, this.worldServers[0], this.theProfiler).init();
         }

         this.worldServers[i].addWorldAccess(new WorldManager(this, this.worldServers[i]));
      }

      this.getConfigurationManager().setPlayerManager(this.worldServers);
      if (this.worldServers[0].getWorldInfo().getDifficulty() == null) {
         this.setDifficultyForAllWorlds(this.mc.gameSettings.difficulty);
      }

      this.initialWorldChunkLoad();
   }

   @Override
   public void initiateShutdown() {
      Futures.getUnchecked(this.addScheduledTask(new IntegratedServer.WorkerTask(this)));
      super.initiateShutdown();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }
   }

   @Override
   public String shareToLAN(WorldSettings.GameType gameType, boolean flag) {
      try {
         int n = -1;

         try {
            n = HttpUtil.getSuitableLanPort();
         } catch (IOException e) {
         }

         if (n <= 0) {
            n = 25564;
         }

         this.getNetworkSystem().addLanEndpoint(null, n);
         LOGGER.info("Started on " + n);
         this.isPublic = true;
         this.lanServerPing = new ThreadLanServerPing(this.getMOTD(), n + "");
         this.lanServerPing.start();
         this.getConfigurationManager().setGameType(gameType);
         this.getConfigurationManager().setCommandsAllowedForAll(flag);
         return n + "";
      } catch (IOException e2) {
         return null;
      }
   }

   @Override
   public boolean isCommandBlockEnabled() {
      return true;
   }

   @Override
   public WorldSettings.GameType getGameType() {
      return this.theWorldSettings.getGameType();
   }

   @Override
   public boolean shouldUseNativeTransport() {
      return false;
   }

   @Override
   public void stopServer() {
      super.stopServer();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }
   }

   public void setStaticInstance() {
      this.setStaticInstance2();
   }

   @Override
   public boolean shouldBroadcastRconToOps() {
      return true;
   }

   @Override
   public boolean startServer() throws IOException {
      LOGGER.info("Starting integrated minecraft server version 1.9");
      this.setOnlineMode(true);
      this.setCanSpawnAnimals(true);
      this.setCanSpawnNPCs(true);
      this.setAllowPvp(true);
      this.setAllowFlight(true);
      LOGGER.info("Generating keypair");
      this.setKeyPair(CryptManager.generateKeyPair());
      this.loadAllWorlds(
         this.getFolderName(),
         this.getWorldName(),
         this.theWorldSettings.getSeed(),
         this.theWorldSettings.getTerrainType(),
         this.theWorldSettings.getWorldName()
      );
      this.setMOTD(this.getServerOwner() + " - " + this.worldServers[0].getWorldInfo().getWorldName());
      return true;
   }

   @Override
   public boolean isDedicatedServer() {
      return false;
   }

   private void onTick(WorldServer world) {
      if (!Config.isTimeDefault()) {
         this.fixWorldTime(world);
      }

      if (!Config.isWeatherEnabled()) {
         this.fixWorldWeather(world);
      }

      if (Config.waterOpacityChanged) {
         Config.waterOpacityChanged = false;
         ClearWater.updateWaterOpacity(Config.getGameSettings(), world);
      }

      if (this.difficultyUpdateWorld == world && this.difficultyUpdatePos != null) {
         this.difficultyLast = world.getDifficultyForLocation(this.difficultyUpdatePos);
         this.difficultyUpdateWorld = null;
         this.difficultyUpdatePos = null;
      }
   }

   @Override
   public void setGameType(WorldSettings.GameType gameType) {
      this.getConfigurationManager().setGameType(gameType);
   }

   @Override
   public boolean shouldBroadcastConsoleToOps() {
      return true;
   }

   @Override
   public boolean isHardcore() {
      return this.theWorldSettings.getHardcoreEnabled();
   }

   public DifficultyInstance getDifficultyAsync(World world, BlockPos pos) {
      this.difficultyUpdateWorld = world;
      this.difficultyUpdatePos = pos;
      return this.difficultyLast;
   }

   public IntegratedServer(Minecraft mc2) {
      super(mc2.getProxy(), new File(mc2.mcDataDir, USER_CACHE_FILE.getName()));
      this.mc = mc2;
      this.theWorldSettings = null;
   }

   @Override
   public File getDataDirectory() {
      return this.mc.mcDataDir;
   }

   private void onTick() {
      for (WorldServer world : this.worldServers) {
         this.onTick(world);
      }
   }

   @Override
   public ServerCommandManager createNewCommandManager() {
      return new IntegratedServerCommandManager();
   }

   @Override
   public CrashReport addServerInfoToCrashReport(CrashReport crashReport) {
      crashReport = super.addServerInfoToCrashReport(crashReport);
      crashReport.getCategory().addCrashSectionCallable("Type", new IntegratedServer.ValueCallable(this));
      crashReport.getCategory().addCrashSectionCallable("Is Modded", new IntegratedServer.ValueCallable2(this));
      return crashReport;
   }

   private void fixWorldTime(WorldServer world) {
      if (world.getWorldInfo().getGameType().getID() == 1) {
         long worldTime = world.getWorldTime();
         long l = worldTime % 24000L;
         if (Config.isTimeDayOnly()) {
            if (l <= 1000L) {
               world.setWorldTime(worldTime - l + 1001L);
            }

            if (l >= 11000L) {
               world.setWorldTime(worldTime - l + 24001L);
            }
         }

         if (Config.isTimeNightOnly()) {
            if (l <= 14000L) {
               world.setWorldTime(worldTime - l + 14001L);
            }

            if (l >= 22000L) {
               world.setWorldTime(worldTime - l + 24000L + 14001L);
            }
         }
      }
   }

   @Override
   public void setDifficultyForAllWorlds(EnumDifficulty enumDifficulty) {
      super.setDifficultyForAllWorlds(enumDifficulty);
      if (this.mc.theWorld != null) {
         this.mc.theWorld.getWorldInfo().setDifficulty(enumDifficulty);
      }
   }

   public boolean getPublic() {
      return this.isPublic;
   }

   public IntegratedServer(Minecraft mc2, String str, String str2, WorldSettings worldSettings) {
      super(new File(mc2.mcDataDir, "saves"), mc2.getProxy(), new File(mc2.mcDataDir, USER_CACHE_FILE.getName()));
      this.setServerOwner(mc2.getSession().getUsername());
      this.setFolderName(str);
      this.setWorldName(str2);
      this.setDemo(mc2.isDemo());
      this.canCreateBonusChest(worldSettings.isBonusChestEnabled());
      this.setBuildLimit(256);
      this.setConfigManager(new IntegratedPlayerList(this));
      this.mc = mc2;
      this.theWorldSettings = this.isDemo() ? DemoWorldServer.demoWorldSettings : worldSettings;
      WorldInfo worldInfo = this.getActiveAnvilConverter().getSaveLoader(str, false).loadWorldInfo();
      if (worldInfo != null) {
         NBTTagCompound playerNBTTagCompound = worldInfo.getPlayerNBTTagCompound();
         if (playerNBTTagCompound != null && playerNBTTagCompound.hasKey("Dimension")) {
            PacketThreadUtil.lastDimensionId = playerNBTTagCompound.getInteger("Dimension");
            this.mc.loadingScreen.setLoadingProgress(-1);
         }
      }
   }

   @Override
   public void saveAllWorlds(boolean flag) {
      if (flag) {
         int tickCounter2 = this.getTickCounter();
         int ofAutoSaveTicks = this.mc.gameSettings.ofAutoSaveTicks;
         if ((long)tickCounter2 < this.ticksSaveLast + (long)ofAutoSaveTicks) {
            return;
         }

         this.ticksSaveLast = (long)tickCounter2;
      }

      super.saveAllWorlds(flag);
   }

   @Override
   public EnumDifficulty getDifficulty() {
      return this.mc.theWorld == null ? this.mc.gameSettings.difficulty : this.mc.theWorld.getWorldInfo().getDifficulty();
   }

   @Override
   public boolean canStructuresSpawn() {
      return false;
   }

   @Override
   public void finalTick(CrashReport crashReport) {
      this.mc.crashed(crashReport);
   }


   public static class ValueCallable implements Callable {
      public final IntegratedServer integratedServer;

      public String callAsString() throws Exception {
         return "Integrated Server (map_client.txt)";
      }

      public ValueCallable(IntegratedServer integratedServer2) {
         this.integratedServer = integratedServer2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final IntegratedServer integratedServer;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(IntegratedServer integratedServer2) {
         this.integratedServer = integratedServer2;
      }

      public String callAsString() throws Exception {
         String clientModName = ClientBrandRetriever.getClientModName();
         if (!clientModName.equals("vanilla")) {
            return "Definitely; Client brand changed to '" + clientModName + "'";
         } else {
            clientModName = this.integratedServer.getServerModName();
            return !clientModName.equals("vanilla")
               ? "Definitely; Server brand changed to '" + clientModName + "'"
               : (
                  Minecraft.class.getSigners() == null
                     ? "Very likely; Jar signature invalidated"
                     : "Probably not. Jar signature remains and both client + server brands are untouched."
               );
         }
      }
   }

   public static class WorkerTask implements Runnable {
      public final IntegratedServer integratedServer;

      public WorkerTask(IntegratedServer integratedServer2) {
         this.integratedServer = integratedServer2;
      }

      @Override
      public void run() {
         for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(Lists.newArrayList(this.integratedServer.getConfigurationManager().getPlayerList()))) {
            this.integratedServer.getConfigurationManager().playerLoggedOut(entityPlayerMP);
         }
      }
   }
}
