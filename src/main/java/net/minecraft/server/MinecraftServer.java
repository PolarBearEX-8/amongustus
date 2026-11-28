package net.minecraft.server;

import net.minecraft.command.CommandResultStats;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Util;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements IThreadListener, Runnable, ICommandSender {
   private static final Logger logger = LogManager.getLogger();
   public static final File USER_CACHE_FILE = new File("usercache.json");
   private static MinecraftServer mcServer;
   private final ISaveFormat anvilConverterForAnvilFile;
   private final File anvilFile;
   private final List playersOnline = Lists.newArrayList();
   public final ICommandManager commandManager;
   public final Profiler theProfiler = new Profiler();
   private final NetworkSystem networkSystem;
   private final ServerStatusResponse statusResponse = new ServerStatusResponse();
   private final Random random = new Random();
   private int serverPort = -1;
   public WorldServer[] worldServers;
   private ServerConfigurationManager serverConfigManager;
   private boolean serverRunning = true;
   private boolean serverStopped;
   private int tickCounter;
   public final Proxy serverProxy;
   public String currentTask;
   public int percentDone;
   private boolean onlineMode;
   private boolean canSpawnAnimals;
   private boolean canSpawnNPCs;
   private boolean pvpEnabled;
   private boolean allowFlight;
   private String motd;
   private int buildLimit;
   private int maxPlayerIdleMinutes = 0;
   public final long[] tickTimeArray = new long[100];
   public long[][] timeOfLastDimensionTick;
   private KeyPair serverKeyPair;
   private String serverOwner;
   private String folderName;
   private String worldName;
   private boolean isDemo;
   private boolean enableBonusChest;
   private boolean worldIsBeingDeleted;
   private String resourcePackUrl = "";
   private String resourcePackHash = "";
   private boolean serverIsRunning;
   private long timeOfLastWarning;
   private String userMessage;
   private boolean startProfiling;
   private boolean forceGamemode;
   private final YggdrasilAuthenticationService authService;
   private final MinecraftSessionService sessionService;
   private long nanoTimeSinceStatusRefresh = 0L;
   private final GameProfileRepository profileRepo;
   private final PlayerProfileCache profileCache;
   public final Queue futureTaskQueue = Queues.newArrayDeque();
   private Thread serverThread;
   private long currentTime = getCurrentTimeMillis();

   public String getMOTD() {
      return this.motd;
   }

   public synchronized String getUserMessage() {
      return this.userMessage;
   }

   public String getResourcePackHash() {
      return this.resourcePackHash;
   }

   public boolean isServerInOnlineMode() {
      return this.onlineMode;
   }

   public void saveAllWorlds(boolean flag) {
      if (!this.worldIsBeingDeleted) {
         for (WorldServer world : this.worldServers) {
            if (world != null) {
               if (!flag) {
                  logger.info("Saving chunks for level '" + world.getWorldInfo().getWorldName() + "'/" + world.provider.getDimensionName());
               }

               try {
                  world.saveAllChunks(true, (IProgressUpdate)null);
               } catch (MinecraftException e) {
                  logger.warn(e.getMessage());
               }
            }
         }
      }
   }

   public void stopServer() {
      if (!this.worldIsBeingDeleted) {
         logger.info("Stopping server");
         if (this.getNetworkSystem() != null) {
            this.getNetworkSystem().terminateEndpoints();
         }

         if (this.serverConfigManager != null) {
            logger.info("Saving players");
            this.serverConfigManager.saveAllPlayerData();
            this.serverConfigManager.removeAllPlayers();
         }

         if (this.worldServers != null) {
            logger.info("Saving worlds");
            this.saveAllWorlds(false);

            for (int i = 0; i < this.worldServers.length; i++) {
               this.worldServers[i].flush();
            }
         }
      }
   }

   public abstract boolean shouldBroadcastConsoleToOps();

   public boolean getForceGamemode() {
      return this.forceGamemode;
   }

   public boolean getGuiEnabled() {
      return false;
   }

   public MinecraftSessionService getMinecraftSessionService() {
      return this.sessionService;
   }

   public boolean isDemo() {
      return this.isDemo;
   }

   public ServerStatusResponse getServerStatusResponse() {
      return this.statusResponse;
   }

   public static MinecraftServer getServer() {
      return mcServer;
   }

   public String getMinecraftVersion() {
      return "1.8.9";
   }

   public boolean getCanSpawnNPCs() {
      return this.canSpawnNPCs;
   }

   public abstract WorldSettings.GameType getGameType();

   @Override
   public void addChatMessage(IChatComponent component) {
      logger.info(component.getUnformattedText());
   }

   public CrashReport addServerInfoToCrashReport(CrashReport crashReport) {
      crashReport.getCategory().addCrashSectionCallable("Profiler Position", new MinecraftServer.ValueCallable(this));
      if (this.serverConfigManager != null) {
         crashReport.getCategory().addCrashSectionCallable("Player Count", new MinecraftServer.ValueCallable2(this));
      }

      return crashReport;
   }

   public abstract boolean shouldBroadcastRconToOps();

   public void setPlayerIdleTimeout(int n) {
      this.maxPlayerIdleMinutes = n;
   }

   public String getFolderName() {
      return this.folderName;
   }

   public boolean isPVPEnabled() {
      return this.pvpEnabled;
   }

   @Override
   public void setCommandStat(CommandResultStats.Type type, int n) {
   }

   public boolean isAnnouncingPlayerAchievements() {
      return true;
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentText(this.getName());
   }

   private void addFaviconToStatusResponse(ServerStatusResponse serverStatusResponse) {
      File file = this.getFile("server-icon.png");
      if (file.isFile()) {
         ByteBuf buf = Unpooled.buffer();

         try {
            BufferedImage image = ImageIO.read(file);
            Validate.validState(image.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(image.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
            ImageIO.write(image, "PNG", new ByteBufOutputStream(buf));
            ByteBuf buf2 = Base64.encode(buf);
            serverStatusResponse.setFavicon("data:image/png;base64," + buf2.toString(Charsets.UTF_8));
            buf2.release();
         } catch (Exception e) {
            logger.error("Couldn't load server icon", e);
         } finally {
            buf.release();
         }
      }
   }

   @Override
   public void run() {
      try {
         if (this.startServer()) {
            this.currentTime = getCurrentTimeMillis();
            long l = 0L;
            this.statusResponse.setServerDescription(new ChatComponentText(this.motd));
            this.statusResponse.setProtocolVersionInfo(new ServerStatusResponse.MinecraftProtocolVersionIdentifier("1.8.9", 47));
            this.addFaviconToStatusResponse(this.statusResponse);

            while (this.serverRunning) {
               long currentTimeMillis = getCurrentTimeMillis();
               long l2 = currentTimeMillis - this.currentTime;
               if (l2 > 2000L && this.currentTime - this.timeOfLastWarning >= 15000L) {
                  logger.warn(
                     "Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)",
                     new Object[]{l2, l2 / 50L}
                  );
                  l2 = 2000L;
                  this.timeOfLastWarning = this.currentTime;
               }

               if (l2 < 0L) {
                  logger.warn("Time ran backwards! Did the system time change?");
                  l2 = 0L;
               }

               l += l2;
               this.currentTime = currentTimeMillis;
               if (this.worldServers[0].areAllPlayersAsleep()) {
                  this.tick();
                  l = 0L;
               } else {
                  while (l > 50L) {
                     l -= 50L;
                     this.tick();
                  }
               }

               Thread.sleep(Math.max(1L, 50L - l));
               this.serverIsRunning = true;
            }
         } else {
            this.finalTick((CrashReport)null);
         }
      } catch (Throwable e) {
         logger.error("Encountered an unexpected exception", e);
         Object obj = null;
         if (e instanceof ReportedException) {
            obj = this.addServerInfoToCrashReport(((ReportedException)e).getCrashReport());
         } else {
            obj = this.addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", e));
         }

         File file = new File(
            new File(this.getDataDirectory(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt"
         );
         if (((CrashReport)obj).saveToFile(file)) {
            logger.error("This crash report has been saved to: " + file.getAbsolutePath());
         } else {
            logger.error("We were unable to save this crash report to disk.");
         }

         this.finalTick((CrashReport)obj);
      } finally {
         try {
            this.serverStopped = true;
            this.stopServer();
         } catch (Throwable e2) {
            logger.error("Exception stopping the server", e2);
         } finally {
            this.systemExitNow();
         }
      }
   }

   public File getFile(String str) {
      return new File(this.getDataDirectory(), str);
   }

   public void setMOTD(String str) {
      this.motd = str;
   }

   public MinecraftServer(Proxy proxy, File file) {
      this.serverProxy = proxy;
      mcServer = this;
      this.anvilFile = null;
      this.networkSystem = null;
      this.profileCache = new PlayerProfileCache(this, file);
      this.commandManager = null;
      this.anvilConverterForAnvilFile = null;
      this.authService = new YggdrasilAuthenticationService(proxy, UUID.randomUUID().toString());
      this.sessionService = this.authService.createMinecraftSessionService();
      this.profileRepo = this.authService.createProfileRepository();
   }

   public abstract boolean isDedicatedServer();

   public NetworkSystem getNetworkSystem() {
      return this.networkSystem;
   }

   public int getMaxPlayers() {
      return this.serverConfigManager.getMaxPlayers();
   }

   public void setBuildLimit(int n) {
      this.buildLimit = n;
   }

   public abstract EnumDifficulty getDifficulty();

   public static long getCurrentTimeMillis() {
      return System.currentTimeMillis();
   }

   public void setStaticInstance2() {
      mcServer = this;
   }

   public void setGameType(WorldSettings.GameType gameType) {
      for (int i = 0; i < this.worldServers.length; i++) {
         getServer().worldServers[i].getWorldInfo().setGameType(gameType);
      }
   }

   public boolean isFlightAllowed() {
      return this.allowFlight;
   }

   public int getCurrentPlayerCount() {
      return this.serverConfigManager.getCurrentPlayerCount();
   }

   public String getResourcePackUrl() {
      return this.resourcePackUrl;
   }

   public void outputPercentRemaining(String str, int n) {
      this.currentTask = str;
      this.percentDone = n;
      logger.info(str + ": " + n + "%");
   }

   public int getBuildLimit() {
      return this.buildLimit;
   }

   public void enableProfiling() {
      this.startProfiling = true;
   }

   public void updateTimeLightAndEntities() {
      this.theProfiler.startSection("jobs");
      synchronized (this.futureTaskQueue) {
         while (!this.futureTaskQueue.isEmpty()) {
            Util.runTask((FutureTask)this.futureTaskQueue.poll(), logger);
         }
      }

      this.theProfiler.endStartSection("levels");

      for (int i = 0; i < this.worldServers.length; i++) {
         long l = System.nanoTime();
         if (i == 0 || this.getAllowNether()) {
            WorldServer world = this.worldServers[i];
            this.theProfiler.startSection(world.getWorldInfo().getWorldName());
            if (this.tickCounter % 20 == 0) {
               this.theProfiler.startSection("timeSync");
               this.serverConfigManager
                  .sendPacketToAllPlayersInDimension(
                     new S03PacketTimeUpdate(world.getTotalWorldTime(), world.getWorldTime(), world.getGameRules().getBoolean("doDaylightCycle")),
                     world.provider.getDimensionId()
                  );
               this.theProfiler.endSection();
            }

            this.theProfiler.startSection("tick");

            try {
               world.tick();
            } catch (Throwable e) {
               CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception ticking world");
               world.addWorldInfoToCrashReport(crashReport);
               throw new ReportedException(crashReport);
            }

            try {
               world.updateEntities();
            } catch (Throwable e2) {
               CrashReport crashReport2 = CrashReport.makeCrashReport(e2, "Exception ticking world entities");
               world.addWorldInfoToCrashReport(crashReport2);
               throw new ReportedException(crashReport2);
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("tracker");
            world.getEntityTracker().updateTrackedEntities();
            this.theProfiler.endSection();
            this.theProfiler.endSection();
         }

         this.timeOfLastDimensionTick[i][this.tickCounter % 100] = System.nanoTime() - l;
      }

      this.theProfiler.endStartSection("connection");
      this.getNetworkSystem().networkTick();
      this.theProfiler.endStartSection("players");
      this.serverConfigManager.onTick();
      this.theProfiler.endStartSection("tickables");

      for (int j = 0; j < this.playersOnline.size(); j++) {
         ((ITickable)this.playersOnline.get(j)).update();
      }

      this.theProfiler.endSection();
   }

   public boolean allowSpawnMonsters() {
      return true;
   }

   public abstract String shareToLAN(WorldSettings.GameType gameType, boolean flag);

   public abstract boolean canStructuresSpawn();

   public ISaveFormat getActiveAnvilConverter() {
      return this.anvilConverterForAnvilFile;
   }

   public void setDemo(boolean flag) {
      this.isDemo = flag;
   }

   public GameProfileRepository getGameProfileRepository() {
      return this.profileRepo;
   }

   public boolean isAnvilFileSet() {
      return this.anvilFile != null;
   }

   public void finalTick(CrashReport crashReport) {
   }

   public boolean getCanSpawnAnimals() {
      return this.canSpawnAnimals;
   }

   public static Logger accessLogger() {
      return logger;
   }

   public WorldServer worldServerForDimension(int n) {
      return n == -1 ? this.worldServers[1] : (n == 1 ? this.worldServers[2] : this.worldServers[0]);
   }

   @Override
   public boolean isCallingFromMinecraftThread() {
      return Thread.currentThread() == this.serverThread;
   }

   public MinecraftServer(File file, Proxy proxy, File file2) {
      this.serverProxy = proxy;
      mcServer = this;
      this.anvilFile = file;
      this.networkSystem = new NetworkSystem(this);
      this.profileCache = new PlayerProfileCache(this, file2);
      this.commandManager = this.createNewCommandManager();
      this.anvilConverterForAnvilFile = new AnvilSaveConverter(file);
      this.authService = new YggdrasilAuthenticationService(proxy, UUID.randomUUID().toString());
      this.sessionService = this.authService.createMinecraftSessionService();
      this.profileRepo = this.authService.createProfileRepository();
   }

   public int getTickCounter() {
      return this.tickCounter;
   }

   public void startServerThread() {
      this.serverThread = new Thread(this, "Server thread");
      this.serverThread.start();
   }

   public void initiateShutdown() {
      this.serverRunning = false;
   }

   @Override
   public BlockPos getPosition() {
      return BlockPos.ORIGIN;
   }

   public void setCanSpawnAnimals(boolean flag) {
      this.canSpawnAnimals = flag;
   }

   public void systemExitNow() {
   }

   public int getNetworkCompressionTreshold() {
      return 256;
   }

   public boolean isSinglePlayer() {
      return this.serverOwner != null;
   }

   @Override
   public World getEntityWorld() {
      return this.worldServers[0];
   }

   @Override
   public boolean sendCommandFeedback() {
      return getServer().worldServers[0].getGameRules().getBoolean("sendCommandFeedback");
   }

   public boolean serverIsInRunLoop() {
      return this.serverIsRunning;
   }

   public ICommandManager getCommandManager() {
      return this.commandManager;
   }

   public static ServerConfigurationManager accessServerConfigManager(MinecraftServer minecraftServer) {
      return minecraftServer.serverConfigManager;
   }

   public void setFolderName(String str) {
      this.folderName = str;
   }

   public int getMaxWorldSize() {
      return 29999984;
   }

   public Entity getEntityFromUuid(UUID uuid) {
      for (WorldServer world : this.worldServers) {
         if (world != null) {
            Entity entityFromUuid = world.getEntityFromUuid(uuid);
            if (entityFromUuid != null) {
               return entityFromUuid;
            }
         }
      }

      return null;
   }

   public void setAllowPvp(boolean flag) {
      this.pvpEnabled = flag;
   }

   public synchronized void setUserMessage(String str) {
      this.userMessage = str;
   }

   public void setWorldName(String str) {
      this.worldName = str;
   }

   @Override
   public Entity getCommandSenderEntity() {
      return null;
   }

   public abstract boolean shouldUseNativeTransport();

   public void setConfigManager(ServerConfigurationManager manager) {
      this.serverConfigManager = manager;
   }

   @Override
   public boolean canCommandSenderUseCommand(int n, String str) {
      return true;
   }

   public Proxy getServerProxy() {
      return this.serverProxy;
   }

   public void setResourcePackFromWorld(String str, ISaveHandler saveHandler) {
      File file = new File(saveHandler.getWorldDirectory(), "resources.zip");
      if (file.isFile()) {
         this.setResourcePack("level://" + str + "/" + file.getName(), "");
      }
   }

   public abstract int getOpPermissionLevel();

   public void convertMapIfNeeded(String str) {
      if (this.getActiveAnvilConverter().isOldMapFormat(str)) {
         logger.info("Converting map!");
         this.setUserMessage("menu.convertingLevel");
         this.getActiveAnvilConverter().convertMapFormat(str, new MinecraftServer.DefaultProgressUpdate(this));
      }
   }

   public void tick() {
      long l = System.nanoTime();
      this.tickCounter++;
      if (this.startProfiling) {
         this.startProfiling = false;
         this.theProfiler.profilingEnabled = true;
         this.theProfiler.clearProfiling();
      }

      this.theProfiler.startSection("root");
      this.updateTimeLightAndEntities();
      if (l - this.nanoTimeSinceStatusRefresh >= 5000000000L) {
         this.nanoTimeSinceStatusRefresh = l;
         this.statusResponse.setPlayerCountData(new ServerStatusResponse.PlayerCountData(this.getMaxPlayers(), this.getCurrentPlayerCount()));
         GameProfile[] profiles = new GameProfile[Math.min(this.getCurrentPlayerCount(), 12)];
         int randomIntegerInRange = MathHelper.getRandomIntegerInRange(this.random, 0, this.getCurrentPlayerCount() - profiles.length);

         for (int i = 0; i < profiles.length; i++) {
            profiles[i] = ((EntityPlayerMP)this.serverConfigManager.getPlayerList().get(randomIntegerInRange + i)).getGameProfile();
         }

         Collections.shuffle(Arrays.asList(profiles));
         this.statusResponse.getPlayerCountData().setPlayers(profiles);
      }

      if (this.tickCounter % 900 == 0) {
         this.theProfiler.startSection("save");
         this.serverConfigManager.saveAllPlayerData();
         this.saveAllWorlds(true);
         this.theProfiler.endSection();
      }

      this.theProfiler.startSection("tallying");
      this.tickTimeArray[this.tickCounter % 100] = System.nanoTime() - l;
      this.theProfiler.endSection();
      this.theProfiler.startSection("snooper");
      this.theProfiler.endSection();
      this.theProfiler.endSection();
   }

   public String[] getAllUsernames() {
      return this.serverConfigManager.getAllUsernames();
   }

   public ServerCommandManager createNewCommandManager() {
      return new ServerCommandManager();
   }

   @Override
   public Vec3 getPositionVector() {
      return new Vec3(0.0, 0.0, 0.0);
   }

   @Override
   public String getName() {
      return "Server";
   }

   public boolean getAllowNether() {
      return true;
   }

   @Override
   public ListenableFuture addScheduledTaskDirect(Runnable runnable) {
      Validate.notNull(runnable);
      return this.callFromMainThread(Executors.callable(runnable));
   }

   public ListenableFuture callFromMainThreadSafe(Callable callable) {
      Validate.notNull(callable);
      if (!this.isCallingFromMinecraftThread() && !this.isServerStopped()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.futureTaskQueue) {
            this.futureTaskQueue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception e) {
            return Futures.immediateFailedFuture(e);
         }
      }
   }

   public void clearCurrentTask() {
      this.currentTask = null;
      this.percentDone = 0;
   }

   public boolean isServerRunning() {
      return this.serverRunning;
   }

   public abstract boolean isHardcore();

   public ServerConfigurationManager getConfigurationManager() {
      return this.serverConfigManager;
   }

   public void setKeyPair(KeyPair keyPair) {
      this.serverKeyPair = keyPair;
   }

   public String getServerModName() {
      return "vanilla";
   }

   public void setResourcePack(String str, String str2) {
      this.resourcePackUrl = str;
      this.resourcePackHash = str2;
   }

   public void setOnlineMode(boolean flag) {
      this.onlineMode = flag;
   }

   public void setAllowFlight(boolean flag) {
      this.allowFlight = flag;
   }

   public ListenableFuture callFromMainThread(Callable callable) {
      Validate.notNull(callable);
      if (!this.isCallingFromMinecraftThread() && !this.isServerStopped()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.futureTaskQueue) {
            this.futureTaskQueue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception e) {
            return Futures.immediateFailedFuture(e);
         }
      }
   }

   public void setServerOwner(String str) {
      this.serverOwner = str;
   }

   public int getSpawnProtectionSize() {
      return 16;
   }

   public boolean isBlockProtected(World world, BlockPos pos, EntityPlayer player) {
      return false;
   }

   public void initialWorldChunkLoad() {
      int n = 0;
      this.setUserMessage("menu.generatingTerrain");
      byte b = 0;
      logger.info("Preparing start region for level " + b);
      WorldServer world = this.worldServers[b];
      BlockPos spawnPoint = world.getSpawnPoint();
      long currentTimeMillis = getCurrentTimeMillis();

      for (int i = -192; i <= 192 && this.isServerRunning(); i += 16) {
         for (int j = -192; j <= 192 && this.isServerRunning(); j += 16) {
            long currentTimeMillis2 = getCurrentTimeMillis();
            if (currentTimeMillis2 - currentTimeMillis > 1000L) {
               this.outputPercentRemaining("Preparing spawn area", n * 100 / 625);
               currentTimeMillis = currentTimeMillis2;
            }

            n++;
            world.theChunkProviderServer.loadChunk(spawnPoint.getX() + i >> 4, spawnPoint.getZ() + j >> 4);
         }
      }

      this.clearCurrentTask();
   }

   public void logWarning(String str) {
      logger.warn(str);
   }

   public abstract boolean isCommandBlockEnabled();

   public void refreshStatusNextTick() {
      this.nanoTimeSinceStatusRefresh = 0L;
   }

   public boolean isServerStopped() {
      return this.serverStopped;
   }

   public String getWorldName() {
      return this.worldName;
   }

   public String getServerOwner() {
      return this.serverOwner;
   }

   public File getDataDirectory() {
      return new File(".");
   }

   public void setDifficultyForAllWorlds(EnumDifficulty enumDifficulty) {
      for (int i = 0; i < this.worldServers.length; i++) {
         WorldServer world = this.worldServers[i];
         if (world != null) {
            if (world.getWorldInfo().isHardcoreModeEnabled()) {
               world.getWorldInfo().setDifficulty(EnumDifficulty.HARD);
               world.setAllowedSpawnTypes(true, true);
            } else if (this.isSinglePlayer()) {
               world.getWorldInfo().setDifficulty(enumDifficulty);
               world.setAllowedSpawnTypes(world.getDifficulty() != EnumDifficulty.PEACEFUL, true);
            } else {
               world.getWorldInfo().setDifficulty(enumDifficulty);
               world.setAllowedSpawnTypes(this.allowSpawnMonsters(), this.canSpawnAnimals);
            }
         }
      }
   }

   public boolean isSnooperEnabled() {
      return true;
   }

   public PlayerProfileCache getPlayerProfileCache() {
      return this.profileCache;
   }

   public void loadAllWorlds(String str, String str2, long l, WorldType worldType, String str3) {
      this.convertMapIfNeeded(str);
      this.setUserMessage("menu.loadingLevel");
      this.worldServers = new WorldServer[3];
      this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
      ISaveHandler saveLoader = this.anvilConverterForAnvilFile.getSaveLoader(str, true);
      this.setResourcePackFromWorld(this.getFolderName(), saveLoader);
      WorldInfo worldInfo = saveLoader.loadWorldInfo();
      WorldSettings worldSettings;
      if (worldInfo == null) {
         if (this.isDemo()) {
            worldSettings = DemoWorldServer.demoWorldSettings;
         } else {
            worldSettings = new WorldSettings(l, this.getGameType(), this.canStructuresSpawn(), this.isHardcore(), worldType);
            worldSettings.setWorldName(str3);
            if (this.enableBonusChest) {
               worldSettings.enableBonusChest();
            }
         }

         worldInfo = new WorldInfo(worldSettings, str2);
      } else {
         worldInfo.setWorldName(str2);
         worldSettings = new WorldSettings(worldInfo);
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
               this.worldServers[i] = (WorldServer)new DemoWorldServer(this, saveLoader, worldInfo, b, this.theProfiler).init();
            } else {
               this.worldServers[i] = (WorldServer)new WorldServer(this, saveLoader, worldInfo, b, this.theProfiler).init();
            }

            this.worldServers[i].initialize(worldSettings);
         } else {
            this.worldServers[i] = (WorldServer)new WorldServerMulti(this, saveLoader, b, this.worldServers[0], this.theProfiler).init();
         }

         this.worldServers[i].addWorldAccess(new WorldManager(this, this.worldServers[i]));
         if (!this.isSinglePlayer()) {
            this.worldServers[i].getWorldInfo().setGameType(this.getGameType());
         }
      }

      this.serverConfigManager.setPlayerManager(this.worldServers);
      this.setDifficultyForAllWorlds(this.getDifficulty());
      this.initialWorldChunkLoad();
   }

   public void canCreateBonusChest(boolean flag) {
      this.enableBonusChest = flag;
   }

   public List getTabCompletions(ICommandSender commandSender, String str, BlockPos pos) {
      ArrayList list = Lists.newArrayList();
      if (str.startsWith("/")) {
         str = str.substring(1);
         boolean flag = !str.contains(" ");
         List tabCompletionOptions = this.commandManager.getTabCompletionOptions(commandSender, str, pos);
         if (tabCompletionOptions != null) {
            for (String str2 : (Iterable<String>)(tabCompletionOptions)) {
               if (flag) {
                  list.add("/" + str2);
               } else {
                  list.add(str2);
               }
            }
         }

         return list;
      } else {
         String[] strings = str.split(" ", -1);
         String str3 = strings[strings.length - 1];

         for (String str4 : this.serverConfigManager.getAllUsernames()) {
            if (CommandBase.doesStringStartWith(str3, str4)) {
               list.add(str4);
            }
         }

         return list;
      }
   }

   public GameProfile[] getGameProfiles() {
      return this.serverConfigManager.getAllProfiles();
   }

   public void setCanSpawnNPCs(boolean flag) {
      this.canSpawnNPCs = flag;
   }

   public abstract boolean startServer() throws IOException;

   public KeyPair getKeyPair() {
      return this.serverKeyPair;
   }

   @Override
   public ListenableFuture addScheduledTask(Runnable runnable) {
      Validate.notNull(runnable);
      return this.callFromMainThread(Executors.callable(runnable));
   }

   public int getMaxPlayerIdleMinutes() {
      return this.maxPlayerIdleMinutes;
   }

   public void deleteWorldAndStopServer() {
      this.worldIsBeingDeleted = true;
      this.getActiveAnvilConverter().flushCache();

      for (int i = 0; i < this.worldServers.length; i++) {
         WorldServer world = this.worldServers[i];
         if (world != null) {
            world.flush();
         }
      }

      this.getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].getSaveHandler().getWorldDirectoryName());
      this.initiateShutdown();
   }


   public static class DefaultProgressUpdate implements IProgressUpdate {
      private long startTime;
      public final MinecraftServer minecraftServer;

      @Override
      public void displayLoadingString(String str) {
      }

      @Override
      public void setLoadingProgress(int n) {
         if (System.currentTimeMillis() - this.startTime >= 1000L) {
            this.startTime = System.currentTimeMillis();
            MinecraftServer.accessLogger().info("Converting... " + n + "%");
         }
      }

      @Override
      public void setDoneWorking() {
      }

      @Override
      public void displaySavingString(String str) {
      }

      public DefaultProgressUpdate(MinecraftServer minecraftServer2) {
         this.minecraftServer = minecraftServer2;
         this.startTime = System.currentTimeMillis();
      }

      @Override
      public void resetProgressAndMessage(String str) {
      }
   }

   public static class ValueCallable implements Callable {
      public final MinecraftServer minecraftServer;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return this.minecraftServer.theProfiler.profilingEnabled ? this.minecraftServer.theProfiler.getNameOfLastSection() : "N/A (disabled)";
      }

      public ValueCallable(MinecraftServer minecraftServer2) {
         this.minecraftServer = minecraftServer2;
      }
   }

   public static class ValueCallable2 implements Callable {
      public final MinecraftServer minecraftServer;

      public String callAsString() {
         return MinecraftServer.accessServerConfigManager(this.minecraftServer).getCurrentPlayerCount()
            + " / "
            + MinecraftServer.accessServerConfigManager(this.minecraftServer).getMaxPlayers()
            + "; "
            + MinecraftServer.accessServerConfigManager(this.minecraftServer).getPlayerList();
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(MinecraftServer minecraftServer2) {
         this.minecraftServer = minecraftServer2;
      }
   }
}
