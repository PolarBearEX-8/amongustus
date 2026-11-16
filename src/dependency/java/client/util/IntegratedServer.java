package client.util;

import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import com.google.common.util.concurrent.Futures;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.FutureTask;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.GoldenOre;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagCompoundEntry;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends VanillaRunnable {
   public static final Logger logger2 = LogManager.getLogger();
   public final Minecraft mc;
   public final AdvancedLongEntry advancedLongEntry;
   public World world = null;
   public BlockPos blockPos = null;
   public GoldenOre goldenOre = null;
   public boolean enabled15;
   public boolean enabled14;
   public CustomValueHandler customValueHandler;
   public long timestamp4 = 0L;

   @Override
   public GameSettingsPeacefulType getGameSettingsPeacefulType() {
      return this.mc.theWorld == null ? this.mc.gameSettings.gameSettingsPeacefulType : this.mc.theWorld.getRandomSeedManager().getGameSettingsPeacefulType2();
   }

   @Override
   public void updateState5() {
      super.updateState5();
      if (this.customValueHandler != null) {
         this.customValueHandler.interrupt();
         this.customValueHandler = null;
      }
   }

   private void applyWorldServer2(WorldServer worldServer) {
      RandomSeedManager randomSeedManager = worldServer.getRandomSeedManager();
      if (randomSeedManager.isEnabled4() || randomSeedManager.isEnabled6()) {
         randomSeedManager.setCount6(0);
         randomSeedManager.setEnabled4(false);
         worldServer.updateFloat(0.0F);
         randomSeedManager.setCount10(0);
         randomSeedManager.setEnabled6(false);
         worldServer.updateFloat2(0.0F);
         this.getMovingManager().applyPacket(new S2BPacketChangeGameState(2, 0.0F));
         this.getMovingManager().applyPacket(new S2BPacketChangeGameState(7, 0.0F));
         this.getMovingManager().applyPacket(new S2BPacketChangeGameState(8, 0.0F));
      }
   }

   @Override
   public void updateState() {
      Futures.getUnchecked(this.resolveListenableFuture(new RotationAccumulator(this)));
      super.updateState();
      if (this.customValueHandler != null) {
         this.customValueHandler.interrupt();
         this.customValueHandler = null;
      }
   }

   @Override
   public boolean isActive2() {
      return this.advancedLongEntry.isEnabled();
   }

   private void applyWorldServerByWorldServer(WorldServer worldServer) {
      if (!BuildManager.isActive37()) {
         this.applyWorldServer(worldServer);
      }

      if (!BuildManager.isActive51()) {
         this.applyWorldServer2(worldServer);
      }

      if (BuildManager.enabled) {
         BuildManager.enabled = false;
         AimFactory.applyGameSettingsByGameSettings(BuildManager.getGameSettings(), worldServer);
      }

      if (this.world == worldServer && this.blockPos != null) {
         this.goldenOre = worldServer.resolveGoldenOre(this.blockPos);
         this.world = null;
         this.blockPos = null;
      }
   }

   private void applyWorldServer(WorldServer worldServer) {
      if (worldServer.getRandomSeedManager().getWorldSettingsGameType().getIndex() == 1) {
         long timestampAsTime = worldServer.getTimestampAsTime();
         long value = timestampAsTime % 24000L;
         if (BuildManager.isActive65()) {
            if (value <= 1000L) {
               worldServer.applyTime(timestampAsTime - value + 1001L);
            }

            if (value >= 11000L) {
               worldServer.applyTime(timestampAsTime - value + 24001L);
            }
         }

         if (BuildManager.isActive4()) {
            if (value <= 14000L) {
               worldServer.applyTime(timestampAsTime - value + 14001L);
            }

            if (value >= 22000L) {
               worldServer.applyTime(timestampAsTime - value + 24000L + 14001L);
            }
         }
      }
   }

   @Override
   public String resolveText(WorldSettingsGameType worldSettingsGameType, boolean flag) {
      try {
         int count = -1;

         try {
            count = GETManager.getCount();
         } catch (IOException ioException) {
         }

         if (count <= 0) {
            count = 25564;
         }

         this.getCustomLoggerEntry().applyInetAddress(null, count);
         logger2.info("Started on " + count);
         this.enabled14 = true;
         this.customValueHandler = new CustomValueHandler(this.getLabelText5(), count + "");
         this.customValueHandler.start();
         this.getMovingManager().setWorldSettingsGameType(worldSettingsGameType);
         this.getMovingManager().setEnabled2(flag);
         return count + "";
      } catch (IOException ioException2) {
         return null;
      }
   }

   @Override
   public void applyPlayerUsageSnooper(PlayerUsageSnooper playerUsageSnooper) {
      super.applyPlayerUsageSnooper(playerUsageSnooper);
      playerUsageSnooper.applyTextByText("snooper_partner", this.mc.getPlayerUsageSnooper().getLabelText());
   }

   @Override
   public ListenerAdapter29 getListenerAdapter29() {
      return new ListenerAdapter30();
   }

   @Override
   public void applyGameSettingsPeacefulType(GameSettingsPeacefulType gameSettingsPeacefulType) {
      super.applyGameSettingsPeacefulType(gameSettingsPeacefulType);
      if (this.mc.theWorld != null) {
         this.mc.theWorld.getRandomSeedManager().setGameSettingsPeacefulType2(gameSettingsPeacefulType);
      }
   }

   @Override
   public boolean isActive9() {
      return false;
   }

   public GoldenOre resolveGoldenOreByWorld(World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
      return this.goldenOre;
   }

   @Override
   public void applyText(String text, String text2, long value, InfoManager infoManager, String text3) {
      this.applyText(text);
      this.worldServerArray = new WorldServer[3];
      this.timestampArray = new long[this.worldServerArray.length][100];
      ISaveHandler iSaveHandler = this.getISaveFormat().resolveISaveHandler(text, true);
      this.applyText(this.getLabelText2(), iSaveHandler);
      RandomSeedManager randomSeedManager = iSaveHandler.getRandomSeedManager();
      if (randomSeedManager == null) {
         randomSeedManager = new RandomSeedManager(this.advancedLongEntry, text2);
      } else {
         randomSeedManager.setLabelText(text2);
      }

      for (int index = 0; index < this.worldServerArray.length; index++) {
         byte byteValue = 0;
         if (index == 1) {
            byteValue = -1;
         }

         if (index == 2) {
            byteValue = 1;
         }

         if (index == 0) {
            if (this.isEnabled11()) {
               this.worldServerArray[0] = (WorldServer)new BezierWriter(this, iSaveHandler, randomSeedManager, byteValue, this.uNKNOWNManager).getWorld();
            } else {
               this.worldServerArray[0] = (WorldServer)new WorldServer(this, iSaveHandler, randomSeedManager, byteValue, this.uNKNOWNManager).getWorld();
            }

            this.worldServerArray[0].applyAdvancedLongEntry(this.advancedLongEntry);
         } else {
            this.worldServerArray[index] = (WorldServer)new WorldServerAdapter(this, iSaveHandler, byteValue, this.worldServerArray[0], this.uNKNOWNManager).getWorld();
         }

         this.worldServerArray[index].add(new ListenerAdapter16(this, this.worldServerArray[index]));
      }

      this.getMovingManager().applyWorldServerArray(this.worldServerArray);
      if (this.worldServerArray[0].getRandomSeedManager().getGameSettingsPeacefulType2() == null) {
         this.applyGameSettingsPeacefulType(this.mc.gameSettings.gameSettingsPeacefulType);
      }

      this.updateState2();
   }

   @Override
   public boolean isActiveAsFlag() {
      return Minecraft.getMinecraft().isActiveAsFlag();
   }

   @Override
   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType) {
      this.getMovingManager().setWorldSettingsGameType(worldSettingsGameType);
   }

   @Override
   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.advancedLongEntry.getWorldSettingsGameType();
   }

   @Override
   public void applyCrashReport(CrashReport crashReport) {
      this.mc.applyCrashReport(crashReport);
   }

   @Override
   public File getFile() {
      return this.mc.mcDataDir;
   }

   @Override
   public int getCount() {
      return 4;
   }

   @Override
   public void setEnabled(boolean enabled) {
      if (enabled) {
         int countAsIndex = this.getCountAsIndex();
         int indexOffset = this.mc.gameSettings.indexOffset132;
         if ((long)countAsIndex < this.timestamp4 + (long)indexOffset) {
            return;
         }

         this.timestamp4 = (long)countAsIndex;
      }

      super.setEnabled(enabled);
   }

   @Override
   public CrashReport resolveCrashReport(CrashReport crashReport) {
      crashReport = super.resolveCrashReport(crashReport);
      crashReport.getCrashReportCategory().applyText("Type", new SmoothDeserializer(this));
      crashReport.getCrashReportCategory().applyText("Is Modded", new AimService(this));
      return crashReport;
   }

   @Override
   public boolean isActive3() {
      return true;
   }

   @Override
   public void updateState3() {
      this.updateState12();
      boolean enabled = this.enabled15;
      this.enabled15 = Minecraft.getMinecraft().getNetHandlerPlayClient() != null && Minecraft.getMinecraft().isState();
      if (!enabled && this.enabled15) {
         logger2.info("Saving and pausing game...");
         this.getMovingManager().updateState4();
         this.setEnabled(false);
      }

      if (this.enabled15) {
         synchronized (this.queue) {
            while (!this.queue.isEmpty()) {
               OsNameManager.resolveTarget((FutureTask)this.queue.poll(), logger2);
            }
         }
      } else {
         super.updateState3();
         if (this.mc.gameSettings.indexOffset16 != this.getMovingManager().getCount3()) {
            logger2.info("Changing view distance to {}, from {}", new Object[]{this.mc.gameSettings.indexOffset16, this.getMovingManager().getCount3()});
            this.getMovingManager().applyIndex(this.mc.gameSettings.indexOffset16);
         }

         if (this.mc.theWorld != null) {
            RandomSeedManager randomSeedManager = this.worldServerArray[0].getRandomSeedManager();
            RandomSeedManager randomSeedManager2 = this.mc.theWorld.getRandomSeedManager();
            if (!randomSeedManager.isEnabled() && randomSeedManager2.getGameSettingsPeacefulType2() != randomSeedManager.getGameSettingsPeacefulType2()) {
               logger2.info("Changing difficulty to {}, from {}", new Object[]{randomSeedManager2.getGameSettingsPeacefulType2(), randomSeedManager.getGameSettingsPeacefulType2()});
               this.applyGameSettingsPeacefulType(randomSeedManager2.getGameSettingsPeacefulType2());
            } else if (randomSeedManager2.isEnabled() && !randomSeedManager.isEnabled()) {
               logger2.info("Locking difficulty to {}", new Object[]{randomSeedManager2.getGameSettingsPeacefulType2()});

               for (WorldServer worldServer : this.worldServerArray) {
                  if (worldServer != null) {
                     worldServer.getRandomSeedManager().setEnabled(true);
                  }
               }
            }
         }
      }
   }

   public void updateState13() {
      this.updateState4();
   }

   @Override
   public boolean isActive8() throws IOException {
      logger2.info("Starting integrated minecraft server version 1.9");
      this.setEnabled2(true);
      this.setEnabled12(true);
      this.setEnabled8(true);
      this.setEnabled13(true);
      this.setEnabled4(true);
      logger2.info("Generating keypair");
      this.setKeyPair(SharedLoggerEntry.getKeyPair());
      this.applyText(
         this.getLabelText2(),
         this.getLabelText4(),
         this.advancedLongEntry.getTimestamp(),
         this.advancedLongEntry.getInfoManager(),
         this.advancedLongEntry.getLabelText()
      );
      this.setLabelText5(this.getLabelText3() + " - " + this.worldServerArray[0].getRandomSeedManager().getLabelText());
      return true;
   }

   public boolean isEnabled14() {
      return this.enabled14;
   }

   @Override
   public boolean isActive5() {
      return false;
   }

   @Override
   public boolean isActive6() {
      return true;
   }

   private void updateState12() {
      for (WorldServer worldServer : this.worldServerArray) {
         this.applyWorldServerByWorldServer(worldServer);
      }
   }

   @Override
   public boolean isActive4() {
      return false;
   }

   @Override
   public boolean isActive7() {
      return true;
   }

   public IntegratedServer(Minecraft mc2) {
      super(mc2.getProxy(), new File(mc2.mcDataDir, file2.getName()));
      this.mc = mc2;
      this.advancedLongEntry = null;
   }

   public IntegratedServer(Minecraft mc2, String text, String text2, AdvancedLongEntry advancedLongEntry2) {
      super(new File(mc2.mcDataDir, "saves"), mc2.getProxy(), new File(mc2.mcDataDir, file2.getName()));
      this.setLabelText3(mc2.getSession().getText1());
      this.setLabelText2(text);
      this.setLabelText4(text2);
      this.setEnabled11(mc2.isEnabled14());
      this.setEnabled5(advancedLongEntry2.isEnabled4());
      this.setCount3(256);
      this.setMovingManager(new NBTTagCompoundEntry(this));
      this.mc = mc2;
      this.advancedLongEntry = this.isEnabled11() ? BezierWriter.advancedLongEntry : advancedLongEntry2;
      RandomSeedManager randomSeedManager = this.getISaveFormat().resolveISaveHandler(text, false).getRandomSeedManager();
      if (randomSeedManager != null) {
         NBTTagCompound nBTTagCompound = randomSeedManager.getNBTTagCompound();
         if (nBTTagCompound != null && nBTTagCompound.containsKey("Dimension")) {
            ClientManager.count = nBTTagCompound.computeCount("Dimension");
            this.mc.loadingScreenRenderer.setCount2(-1);
         }
      }
   }
}
