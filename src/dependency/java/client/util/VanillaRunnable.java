package client.util;

import client.event.CompositeListener;
import client.event.EnhancedListener;
import client.event.PostMotionNotifier;
import client.event.PreUpdateConsumer;
import client.setting.CombatNumberProperty;
import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
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
import java.awt.GraphicsEnvironment;
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
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class VanillaRunnable implements PostMotionNotifier, EnhancedListener, Runnable, ICommandSender {
   public static final Logger logger = LogManager.getLogger();
   public static final File file2 = new File("usercache.json");
   public static VanillaRunnable vanillaRunnable;
   public final ISaveFormat iSaveFormat;
   public final PlayerUsageSnooper playerUsageSnooper = new PlayerUsageSnooper("server", this, currentTimeMillis());
   public final File file;
   public final List list = Lists.newArrayList();
   public final ICommandManager iCommandManager;
   public final UNKNOWNManager uNKNOWNManager = new UNKNOWNManager();
   public final CustomLoggerEntry customLoggerEntry;
   public final IChatComponentEntry iChatComponentEntry = new IChatComponentEntry();
   public final Random random = new Random();
   public int count2 = -1;
   public WorldServer[] worldServerArray;
   public MovingManager movingManager;
   public boolean enabled = true;
   public boolean enabled9;
   public int count;
   public final Proxy proxy;
   public String labelText;
   public int count5;
   public boolean enabled2;
   public boolean enabled12;
   public boolean enabled8;
   public boolean enabled13;
   public boolean enabled4;
   public String labelText5;
   public int count3;
   public int count4 = 0;
   public final long[] timestampArray2 = new long[100];
   public long[][] timestampArray;
   public KeyPair keyPair;
   public String labelText3;
   public String labelText2;
   public String labelText4;
   public boolean enabled11;
   public boolean enabled5;
   public boolean enabled7;
   public String labelText6 = "";
   public String labelText7 = "";
   public boolean enabled6;
   public long timestamp2;
   public String labelText8;
   public boolean enabled3;
   public boolean enabled10;
   public final YggdrasilAuthenticationService yggdrasilAuthenticationService;
   public final MinecraftSessionService minecraftSessionService;
   public long timestamp = 0L;
   public final GameProfileRepository gameProfileRepository;
   public final YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager;
   public final Queue queue = Queues.newArrayDeque();
   public Thread thread;
   public long timestamp3 = currentTimeMillis();

   public abstract GameSettingsPeacefulType getGameSettingsPeacefulType();

   @Override
   public boolean matchesText(int count6, String text) {
      return true;
   }

   public String getTextAsText() {
      return "vanilla";
   }

   public Entity resolveEntityByUUID(UUID uuid) {
      for (WorldServer worldServer : this.worldServerArray) {
         if (worldServer != null) {
            Entity entity = worldServer.get(uuid);
            if (entity != null) {
               return entity;
            }
         }
      }

      return null;
   }

   public void applyText(String text, ISaveHandler iSaveHandler) {
      File file3 = new File(iSaveHandler.getFile(), "resources.zip");
      if (file3.isFile()) {
         this.applyText("level://" + text + "/" + file3.getName(), "");
      }
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(0.0, 0.0, 0.0);
   }

   public GameProfileRepository getGameProfileRepository() {
      return this.gameProfileRepository;
   }

   public static VanillaRunnable getVanillaRunnable() {
      return vanillaRunnable;
   }

   public boolean isEnabled12() {
      return this.enabled12;
   }

   public int getCount5() {
      return 29999984;
   }

   public void updateState5() {
      if (!this.enabled7) {
         logger.info("Stopping server");
         if (this.getCustomLoggerEntry() != null) {
            this.getCustomLoggerEntry().updateState();
         }

         if (this.movingManager != null) {
            logger.info("Saving players");
            this.movingManager.updateState4();
            this.movingManager.updateState3();
         }

         if (this.worldServerArray != null) {
            logger.info("Saving worlds");
            this.setEnabled(false);

            for (int index = 0; index < this.worldServerArray.length; index++) {
               this.worldServerArray[index].updateState17();
            }
         }

         if (this.playerUsageSnooper.isEnabled()) {
            this.playerUsageSnooper.cancel();
         }
      }
   }

   public void setEnabled4(boolean enabled14) {
      this.enabled4 = enabled14;
   }

   public void updateState2() {
      int count6 = 0;
      this.setLabelText8("menu.generatingTerrain");
      logger.info("Preparing start region for level " + 0);
      WorldServer worldServer = this.worldServerArray[0];
      BlockPos blockPos = worldServer.getBlockPos();
      long timestamp4 = currentTimeMillis();

      for (int shortValue = -192; shortValue <= 192 && this.isEnabled(); shortValue += 16) {
         for (int shortValue2 = -192; shortValue2 <= 192 && this.isEnabled(); shortValue2 += 16) {
            long timestamp5 = currentTimeMillis();
            if (timestamp5 - timestamp4 > 1000L) {
               this.applyText("Preparing spawn area", count6 * 100 / 625);
               timestamp4 = timestamp5;
            }

            count6++;
            worldServer.listenerAdapter21.resolveAlertNotificationSoundByIndex(blockPos.getX() + shortValue >> 4, blockPos.getZ() + shortValue2 >> 4);
         }
      }

      this.updateState7();
   }

   public int getCount2() {
      return this.movingManager.getCount2();
   }

   public void setMovingManager(MovingManager movingManager2) {
      this.movingManager = movingManager2;
   }

   public String[] getTextArray() {
      return this.movingManager.getTextArray2();
   }

   @Override
   public void applyPlayerUsageSnooperByPlayerUsageSnoop(PlayerUsageSnooper playerUsageSnooper2) {
      playerUsageSnooper2.applyText("singleplayer", this.isActive10());
      playerUsageSnooper2.applyText("server_brand", this.getTextAsText());
      playerUsageSnooper2.applyText("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
      playerUsageSnooper2.applyText("dedicated", this.isActive5());
   }

   public String getLabelText5() {
      return this.labelText5;
   }

   public File resolveFile(String text) {
      return new File(this.getFile(), text);
   }

   public GameProfile[] getGameProfileArray() {
      return this.movingManager.getGameProfileArray();
   }

   public boolean isEnabled10() {
      return this.enabled10;
   }

   public YyyyMMDdHHMmSsZManager getYyyyMMDdHHMmSsZManager() {
      return this.yyyyMMDdHHMmSsZManager;
   }

   public void setLabelText2(String labelText9) {
      this.labelText2 = labelText9;
   }

   public static long currentTimeMillis() {
      return System.currentTimeMillis();
   }

   public void setEnabled13(boolean enabled14) {
      this.enabled13 = enabled14;
   }

   public abstract boolean isActive2();

   public void setKeyPair(KeyPair keyPair2) {
      this.keyPair = keyPair2;
   }

   public void updateState() {
      this.enabled = false;
   }

   public void setEnabled2(boolean enabled14) {
      this.enabled2 = enabled14;
   }

   private void applyIChatComponentEntry(IChatComponentEntry iChatComponentEntry2) {
      File file3 = this.resolveFile("server-icon.png");
      if (file3.isFile()) {
         ByteBuf byteBuf = Unpooled.buffer();

         try {
            BufferedImage bufferedImage = ImageIO.read(file3);
            Validate.validState(bufferedImage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(bufferedImage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
            ImageIO.write(bufferedImage, "PNG", new ByteBufOutputStream(byteBuf));
            ByteBuf byteBuf2 = Base64.encode(byteBuf);
            iChatComponentEntry2.setLabelText("data:image/png;base64," + byteBuf2.toString(Charsets.UTF_8));
            byteBuf2.release();
         } catch (Exception exception) {
            logger.error("Couldn't load server icon", exception);
         } finally {
            byteBuf.release();
         }
      }
   }

   @Override
   public String getText() {
      return "Server";
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count6) {
   }

   public ListenerAdapter29 getListenerAdapter29() {
      return new ListenerAdapter29();
   }

   public ListenableFuture resolveListenableFuture(Callable callable) {
      Validate.notNull(callable);
      if (!this.isSingleplayer() && !this.isEnabled9()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.queue) {
            this.queue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception exception) {
            return Futures.immediateFailedFuture(exception);
         }
      }
   }

   public static MovingManager resolveMovingManager(VanillaRunnable vanillaRunnable2) {
      return vanillaRunnable2.movingManager;
   }

   public void start() {
      this.thread = new Thread(this, "Server thread");
      this.thread.start();
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public void setEnabled12(boolean enabled14) {
      this.enabled12 = enabled14;
   }

   public int getCount4() {
      return this.count4;
   }

   public abstract String resolveText(WorldSettingsGameType worldSettingsGameType, boolean flag);

   @Override
   public boolean isActive() {
      return getVanillaRunnable().worldServerArray[0].getDoFireTickManager().matchesText("sendCommandFeedback");
   }

   public PlayerUsageSnooper getPlayerUsageSnooper() {
      return this.playerUsageSnooper;
   }

   @Override
   public void applyPlayerUsageSnooper(PlayerUsageSnooper playerUsageSnooper2) {
      playerUsageSnooper2.applyTextByText("whitelist_enabled", Boolean.FALSE);
      playerUsageSnooper2.applyTextByText("whitelist_count", 0);
      if (this.movingManager != null) {
         playerUsageSnooper2.applyTextByText("players_current", this.getCount8());
         playerUsageSnooper2.applyTextByText("players_max", this.getCount2());
         playerUsageSnooper2.applyTextByText("players_seen", this.movingManager.getTextArrayAsTextArray().length);
      }

      playerUsageSnooper2.applyTextByText("uses_auth", this.enabled2);
      playerUsageSnooper2.applyTextByText("gui_state", this.isActive14() ? "enabled" : "disabled");
      playerUsageSnooper2.applyTextByText("run_time", (currentTimeMillis() - playerUsageSnooper2.getTimestamp()) / 60L * 1000L);
      playerUsageSnooper2.applyTextByText("avg_tick_ms", (int)(MathHelper.computeValue(this.timestampArray2) * 1.0E-6));
      int count6 = 0;
      if (this.worldServerArray != null) {
         for (int index = 0; index < this.worldServerArray.length; index++) {
            if (this.worldServerArray[index] != null) {
               WorldServer worldServer = this.worldServerArray[index];
               RandomSeedManager randomSeedManager = worldServer.getRandomSeedManager();
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][dimension]", worldServer.secondaryFloatArrayEntry.getCountAsIndex());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][mode]", randomSeedManager.getWorldSettingsGameType());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][difficulty]", worldServer.getGameSettingsPeacefulType());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][hardcore]", randomSeedManager.isEnabled2());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][generator_name]", randomSeedManager.getInfoManager().getText());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][generator_version]", randomSeedManager.getInfoManager().getCount());
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][height]", this.count3);
               playerUsageSnooper2.applyTextByText("world[" + count6 + "][chunks_loaded]", worldServer.getCommonListener().size());
               count6++;
            }
         }
      }

      playerUsageSnooper2.applyTextByText("worlds", count6);
   }

   @Override
   public IChatComponent getChatComponent() {
      return new ChatComponentText(this.getText());
   }

   public void setCount4(int count6) {
      this.count4 = count6;
   }

   public String getLabelText6() {
      return this.labelText6;
   }

   public KeyPair getKeyPair() {
      return this.keyPair;
   }

   public void setEnabled5(boolean enabled14) {
      this.enabled5 = enabled14;
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      logger.info(iChatComponent.getString());
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public synchronized void setLabelText8(String labelText9) {
      this.labelText8 = labelText9;
   }

   public void updateState4() {
      vanillaRunnable = this;
   }

   @Override
   public ListenableFuture resolveListenableFuture(Runnable task) {
      Validate.notNull(task);
      return this.resolveListenableFuture(Executors.callable(task));
   }

   public int getCount3() {
      return 256;
   }

   public void applyGameSettingsPeacefulType(GameSettingsPeacefulType gameSettingsPeacefulType) {
      for (int index = 0; index < this.worldServerArray.length; index++) {
         WorldServer worldServer = this.worldServerArray[index];
         if (worldServer != null) {
            if (worldServer.getRandomSeedManager().isEnabled2()) {
               worldServer.getRandomSeedManager().setGameSettingsPeacefulType2(GameSettingsPeacefulType.HARD);
               worldServer.applyFlag(true, true);
            } else if (this.isActive10()) {
               worldServer.getRandomSeedManager().setGameSettingsPeacefulType2(gameSettingsPeacefulType);
               worldServer.applyFlag(worldServer.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL, true);
            } else {
               worldServer.getRandomSeedManager().setGameSettingsPeacefulType2(gameSettingsPeacefulType);
               worldServer.applyFlag(this.isActive11(), this.enabled12);
            }
         }
      }
   }

   @Override
   public World getWorldObj() {
      return this.worldServerArray[0];
   }

   public int getCount7() {
      return this.count3;
   }

   public abstract boolean isActive9();

   public void setEnabled11(boolean enabled14) {
      this.enabled11 = enabled14;
   }

   public void setLabelText4(String labelText9) {
      this.labelText4 = labelText9;
   }

   public void applyText(String text, String text2, long value, InfoManager infoManager, String text3) {
      this.applyText(text);
      this.setLabelText8("menu.loadingLevel");
      this.worldServerArray = new WorldServer[3];
      this.timestampArray = new long[this.worldServerArray.length][100];
      ISaveHandler iSaveHandler = this.iSaveFormat.resolveISaveHandler(text, true);
      this.applyText(this.getLabelText2(), iSaveHandler);
      RandomSeedManager randomSeedManager = iSaveHandler.getRandomSeedManager();
      AdvancedLongEntry advancedLongEntry;
      if (randomSeedManager == null) {
         if (this.isEnabled11()) {
            advancedLongEntry = BezierWriter.advancedLongEntry;
         } else {
            advancedLongEntry = new AdvancedLongEntry(value, this.getWorldSettingsGameType(), this.isActive9(), this.isActive2(), infoManager);
            advancedLongEntry.withLabelText(text3);
            if (this.enabled5) {
               advancedLongEntry.getAdvancedLongEntryAsAdvancedLongEntr();
            }
         }

         randomSeedManager = new RandomSeedManager(advancedLongEntry, text2);
      } else {
         randomSeedManager.setLabelText(text2);
         advancedLongEntry = new AdvancedLongEntry(randomSeedManager);
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
               this.worldServerArray[index] = (WorldServer)new BezierWriter(this, iSaveHandler, randomSeedManager, byteValue, this.uNKNOWNManager).getWorld();
            } else {
               this.worldServerArray[index] = (WorldServer)new WorldServer(this, iSaveHandler, randomSeedManager, byteValue, this.uNKNOWNManager).getWorld();
            }

            this.worldServerArray[index].applyAdvancedLongEntry(advancedLongEntry);
         } else {
            this.worldServerArray[index] = (WorldServer)new WorldServerAdapter(this, iSaveHandler, byteValue, this.worldServerArray[0], this.uNKNOWNManager).getWorld();
         }

         this.worldServerArray[index].add(new ListenerAdapter16(this, this.worldServerArray[index]));
         if (!this.isActive10()) {
            this.worldServerArray[index].getRandomSeedManager().setWorldSettingsGameType(this.getWorldSettingsGameType());
         }
      }

      this.movingManager.applyWorldServerArray(this.worldServerArray);
      this.applyGameSettingsPeacefulType(this.getGameSettingsPeacefulType());
      this.updateState2();
   }

   public void applyText(String text, int count6) {
      this.labelText = text;
      this.count5 = count6;
      logger.info(text + ": " + count6 + "%");
   }

   public void updateState10() {
   }

   public MovingManager getMovingManager() {
      return this.movingManager;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void setCount3(int count6) {
      this.count3 = count6;
   }

   public void setEnabled8(boolean enabled14) {
      this.enabled8 = enabled14;
   }

   public void applyText(String text) {
      if (this.getISaveFormat().matchesText(text)) {
         logger.info("Converting map!");
         this.setLabelText8("menu.convertingLevel");
         this.getISaveFormat().matchesText(text, new ListenerRegistry(this));
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType) {
      for (int index = 0; index < this.worldServerArray.length; index++) {
         getVanillaRunnable().worldServerArray[index].getRandomSeedManager().setWorldSettingsGameType(worldSettingsGameType);
      }
   }

   public String getText2() {
      return "1.8.9";
   }

   public ListenableFuture resolveListenableFutureByCallable(Callable callable) {
      Validate.notNull(callable);
      if (!this.isSingleplayer() && !this.isEnabled9()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.queue) {
            this.queue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception exception) {
            return Futures.immediateFailedFuture(exception);
         }
      }
   }

   public int getCount8() {
      return this.movingManager.size();
   }

   public abstract WorldSettingsGameType getWorldSettingsGameType();

   public void updateState11() {
      this.timestamp = 0L;
   }

   public boolean isEnabled6() {
      return this.enabled6;
   }

   public CustomLoggerEntry getCustomLoggerEntry() {
      return this.customLoggerEntry;
   }

   public void warn(String text) {
      logger.warn(text);
   }

   public MinecraftSessionService getMinecraftSessionService() {
      return this.minecraftSessionService;
   }

   public void updateState8() {
      this.enabled3 = true;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   @Override
   public ListenableFuture resolveListenableFutureByRunnable(Runnable task) {
      Validate.notNull(task);
      return this.resolveListenableFuture(Executors.callable(task));
   }

   public static Logger getLogger() {
      return logger;
   }

   public String getLabelText7() {
      return this.labelText7;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public ICommandManager getICommandManager() {
      return this.iCommandManager;
   }

   public void updateState9() {
      this.enabled7 = true;
      this.getISaveFormat().updateState();

      for (int index = 0; index < this.worldServerArray.length; index++) {
         WorldServer worldServer = this.worldServerArray[index];
         if (worldServer != null) {
            worldServer.updateState17();
         }
      }

      this.getISaveFormat().matchesText2(this.worldServerArray[0].getManagedListener().getLabelText());
      this.updateState();
   }

   @Override
   public boolean isSingleplayer() {
      return Thread.currentThread() == this.thread;
   }

   public void setLabelText5(String labelText9) {
      this.labelText5 = labelText9;
   }

   @Override
   public BlockPos getBlockPos() {
      return BlockPos.origin;
   }

   public void applyText(String text, String text2) {
      this.labelText6 = text;
      this.labelText7 = text2;
   }

   public int getCount6() {
      return 16;
   }

   public ISaveFormat getISaveFormat() {
      return this.iSaveFormat;
   }

   public synchronized String getLabelText8() {
      return this.labelText8;
   }

   public void applyCrashReport(CrashReport crashReport) {
   }

   public CrashReport resolveCrashReport(CrashReport crashReport) {
      crashReport.getCrashReportCategory().applyText("Profiler Position", new SpecialCustom(this));
      if (this.movingManager != null) {
         crashReport.getCrashReportCategory().applyText("Player Count", new CurveValidator(this));
      }

      return crashReport;
   }

   public boolean matchesWorld(World world, BlockPos blockPos, EntityPlayer player) {
      return false;
   }

   public void updateState3() {
      long timestamp4 = System.nanoTime();
      this.count++;
      if (this.enabled3) {
         this.enabled3 = false;
         this.uNKNOWNManager.enabled = true;
         this.uNKNOWNManager.updateState2();
      }

      this.uNKNOWNManager.applyTextByText("root");
      this.updateState6();
      if (timestamp4 - this.timestamp >= 5000000000L) {
         this.timestamp = timestamp4;
         this.iChatComponentEntry.setGameProfileArrayHolder(new GameProfileArrayHolder(this.getCount2(), this.getCount8()));
         GameProfile[] gameProfileArray = new GameProfile[Math.min(this.getCount8(), 12)];
         int count6 = MathHelper.nextInt(this.random, 0, this.getCount8() - gameProfileArray.length);

         for (int index = 0; index < gameProfileArray.length; index++) {
            gameProfileArray[index] = ((EntityPlayerAdapter)this.movingManager.getList().get(count6 + index)).getGameProfile();
         }

         Collections.shuffle(Arrays.asList(gameProfileArray));
         this.iChatComponentEntry.getGameProfileArrayHolder().setGameProfileArray(gameProfileArray);
      }

      if (this.count % 900 == 0) {
         this.uNKNOWNManager.applyTextByText("save");
         this.movingManager.updateState4();
         this.setEnabled(true);
         this.uNKNOWNManager.updateState();
      }

      this.uNKNOWNManager.applyTextByText("tallying");
      this.timestampArray2[this.count % 100] = System.nanoTime() - timestamp4;
      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.applyTextByText("snooper");
      if (!this.playerUsageSnooper.isEnabled() && this.count > 100) {
         this.playerUsageSnooper.updateState2();
      }

      if (this.count % 6000 == 0) {
         this.playerUsageSnooper.updateState3();
      }

      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.updateState();
   }

   public WorldServer resolveWorldServer(int count6) {
      return count6 == -1 ? this.worldServerArray[1] : (count6 == 1 ? this.worldServerArray[2] : this.worldServerArray[0]);
   }

   public String getLabelText4() {
      return this.labelText4;
   }

   public IChatComponentEntry getIChatComponentEntry() {
      return this.iChatComponentEntry;
   }

   public void setLabelText3(String labelText9) {
      this.labelText3 = labelText9;
   }

   public abstract int getCount();

   public File getFile() {
      return new File(".");
   }

   @Override
   public Entity getEntity() {
      return null;
   }

   public List resolveList(ICommandSender iCommandSender, String text, BlockPos blockPos) {
      ArrayList list2 = Lists.newArrayList();
      if (text.startsWith("/")) {
         String text2 = text.substring(1);
         boolean flag = !text2.contains(" ");
         List list3 = this.iCommandManager.resolveList(iCommandSender, text2, blockPos);
         if (list3 != null) {
            for (String text3 : (java.lang.Iterable<String>) (list3)) {
               if (flag) {
                  list2.add("/" + text3);
               } else {
                  list2.add(text3);
               }
            }
         }

         return list2;
      } else {
         String[] textArray = text.split(" ", -1);
         String text4 = textArray[textArray.length - 1];

         for (String text5 : this.movingManager.getTextArray2()) {
            if (Listener87Entry.matchesText(text4, text5)) {
               list2.add(text5);
            }
         }

         return list2;
      }
   }

   public abstract boolean isActive3();

   public void setEnabled(boolean enabled14) {
      if (!this.enabled7) {
         for (WorldServer worldServer : this.worldServerArray) {
            if (worldServer != null) {
               if (!enabled14) {
                  logger.info("Saving chunks for level '" + worldServer.getRandomSeedManager().getLabelText() + "'/" + worldServer.secondaryFloatArrayEntry.getTextAsText());
               }

               try {
                  worldServer.applyCompositeListener(true, (CompositeListener)null);
               } catch (ExceptionAdapter exception) {
                  logger.warn(exception.getMessage());
               }
            }
         }
      }
   }

   public boolean onWorldTick() {
      return this.enabled13;
   }

   public void updateState7() {
      this.labelText = null;
      this.count5 = 0;
   }

   public void updateState6() {
      this.uNKNOWNManager.applyTextByText("jobs");
      synchronized (this.queue) {
         while (!this.queue.isEmpty()) {
            OsNameManager.resolveTarget((FutureTask)this.queue.poll(), logger);
         }
      }

      this.uNKNOWNManager.applyText("levels");

      for (int index = 0; index < this.worldServerArray.length; index++) {
         long timestamp4 = System.nanoTime();
         if (index == 0 || this.isActive12()) {
            WorldServer worldServer = this.worldServerArray[index];
            this.uNKNOWNManager.applyTextByText(worldServer.getRandomSeedManager().getLabelText());
            if (this.count % 20 == 0) {
               this.uNKNOWNManager.applyTextByText("timeSync");
               this.movingManager
                  .applyPacket(
                     new S03PacketTimeUpdate(worldServer.getTimestamp2(), worldServer.getTimestampAsTime(), worldServer.getDoFireTickManager().matchesText("doDaylightCycle")),
                     worldServer.secondaryFloatArrayEntry.getCountAsIndex()
                  );
               this.uNKNOWNManager.updateState();
            }

            this.uNKNOWNManager.applyTextByText("tick");

            try {
               worldServer.updateState2();
            } catch (Throwable throwable) {
               CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception ticking world");
               worldServer.resolveCrashReportCategory(crashReport);
               throw new SecondaryRuntimeExceptionAdapter(crashReport);
            }

            try {
               worldServer.updateState4();
            } catch (Throwable throwable2) {
               CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable2, "Exception ticking world entities");
               worldServer.resolveCrashReportCategory(crashReport2);
               throw new SecondaryRuntimeExceptionAdapter(crashReport2);
            }

            this.uNKNOWNManager.updateState();
            this.uNKNOWNManager.applyTextByText("tracker");
            worldServer.getSecondaryLoggerEntry().updateState();
            this.uNKNOWNManager.updateState();
            this.uNKNOWNManager.updateState();
         }

         this.timestampArray[index][this.count % 100] = System.nanoTime() - timestamp4;
      }

      this.uNKNOWNManager.applyText("connection");
      this.getCustomLoggerEntry().updateState2();
      this.uNKNOWNManager.applyText("players");
      this.movingManager.updateState2();
      this.uNKNOWNManager.applyText("tickables");

      for (int index2 = 0; index2 < this.list.size(); index2++) {
         ((PreUpdateConsumer)this.list.get(index2)).updateState();
      }

      this.uNKNOWNManager.updateState();
   }

   public boolean isActive13() {
      return true;
   }

   public abstract boolean isActive8() throws IOException;

   public boolean onUpdate() {
      return this.file != null;
   }

   public abstract boolean isActive5();

   public abstract boolean isActive6();

   public boolean isActive12() {
      return true;
   }

   public boolean isActive10() {
      return this.labelText3 != null;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public boolean isEnabled9() {
      return this.enabled9;
   }

   public abstract boolean isActive4();

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isActive14() {
      return false;
   }

   public boolean isEnabled11() {
      return this.enabled11;
   }

   public boolean isEnabled8() {
      return this.enabled8;
   }

   public abstract boolean isActive7();

   public boolean isActive11() {
      return true;
   }

   public VanillaRunnable(Proxy proxy2, File file3) {
      this.proxy = proxy2;
      vanillaRunnable = this;
      this.file = null;
      this.customLoggerEntry = null;
      this.yyyyMMDdHHMmSsZManager = new YyyyMMDdHHMmSsZManager(this, file3);
      this.iCommandManager = null;
      this.iSaveFormat = null;
      this.yggdrasilAuthenticationService = new YggdrasilAuthenticationService(proxy2, UUID.randomUUID().toString());
      this.minecraftSessionService = this.yggdrasilAuthenticationService.createMinecraftSessionService();
      this.gameProfileRepository = this.yggdrasilAuthenticationService.createProfileRepository();
   }

   public VanillaRunnable(File file3, Proxy proxy2, File file4) {
      this.proxy = proxy2;
      vanillaRunnable = this;
      this.file = file3;
      this.customLoggerEntry = new CustomLoggerEntry(this);
      this.yyyyMMDdHHMmSsZManager = new YyyyMMDdHHMmSsZManager(this, file4);
      this.iCommandManager = this.getListenerAdapter29();
      this.iSaveFormat = new LevelDatISaveFormatAdapter(file3);
      this.yggdrasilAuthenticationService = new YggdrasilAuthenticationService(proxy2, UUID.randomUUID().toString());
      this.minecraftSessionService = this.yggdrasilAuthenticationService.createMinecraftSessionService();
      this.gameProfileRepository = this.yggdrasilAuthenticationService.createProfileRepository();
   }

   @Override
   public void run() {
      try {
         if (this.isActive8()) {
            this.timestamp3 = currentTimeMillis();
            long value = 0L;
            this.iChatComponentEntry.setIChatComponent(new ChatComponentText(this.labelText5));
            this.iChatComponentEntry.setDampenerDeserializer(new DampenerDeserializer("1.8.9", 47));
            this.applyIChatComponentEntry(this.iChatComponentEntry);

            while (this.enabled) {
               long timestamp4 = currentTimeMillis();
               long value2 = timestamp4 - this.timestamp3;
               if (value2 > 2000L && this.timestamp3 - this.timestamp2 >= 15000L) {
                  logger.warn(
                     "Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)",
                     new Object[]{value2, value2 / 50L}
                  );
                  value2 = 2000L;
                  this.timestamp2 = this.timestamp3;
               }

               if (value2 < 0L) {
                  logger.warn("Time ran backwards! Did the system time change?");
                  value2 = 0L;
               }

               value += value2;
               this.timestamp3 = timestamp4;
               if (this.worldServerArray[0].isActive4()) {
                  this.updateState3();
                  value = 0L;
               } else {
                  while (value > 50L) {
                     value -= 50L;
                     this.updateState3();
                  }
               }

               Thread.sleep(Math.max(1L, 50L - value));
               this.enabled6 = true;
            }
         } else {
            this.applyCrashReport((CrashReport)null);
         }
      } catch (Throwable throwable) {
         logger.error("Encountered an unexpected exception", throwable);
         CrashReport crashReport;
         if (throwable instanceof SecondaryRuntimeExceptionAdapter) {
            crashReport = this.resolveCrashReport(((SecondaryRuntimeExceptionAdapter)throwable).getCrashReport());
         } else {
            crashReport = this.resolveCrashReport(new CrashReport("Exception in server tick loop", throwable));
         }

         File file3 = new File(
            new File(this.getFile(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt"
         );
         if (crashReport.matchesFile(file3)) {
            logger.error("This crash report has been saved to: " + file3.getAbsolutePath());
         } else {
            logger.error("We were unable to save this crash report to disk.");
         }

         this.applyCrashReport(crashReport);
      } finally {
         try {
            this.enabled9 = true;
            this.updateState5();
         } catch (Throwable throwable2) {
            logger.error("Exception stopping the server", throwable2);
         } finally {
            this.updateState10();
         }
      }
   }
}
