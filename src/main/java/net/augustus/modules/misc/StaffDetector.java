package net.augustus.modules.misc;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.ui.notifications.NotificationLevel;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.ui.notifications.NotificationManager;
import net.augustus.settings.options.StaffDetectorModeOption;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.options.NotificationCategoryOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class StaffDetector extends Module implements LoadWorldListener, RenderListener, PacketListener, TickListener {
   private static final String STAFF_LISTS_DIR = ClientPaths.CLIENT_DIR + File.separator + "staffLists";
   private final MultiOptionSetting modes;
   private final SettingGroup normalModeSettings;
   private final BooleanValue excludeRawUuids;
   private final BooleanValue showInSpectatorListNormal;
   private final BooleanValue chatMessageNormal;
   private final BooleanValue notificationNormal;
   private final BooleanValue acousticWarningNormal;
   private final SettingGroup simpleModeSettings;
   private final BooleanValue showInSpectatorListSimple;
   private final BooleanValue chatMessageSimple;
   private final BooleanValue notificationSimple;
   private final BooleanValue acousticWarningSimple;
   private final SettingGroup oldgommeModeSettings;
   private final RandomizedRangeSettings checkDelay;
   private final BooleanValue showInSpectatorListGomme;
   private final BooleanValue chatMessageGomme;
   private final BooleanValue notificationGomme;
   private final BooleanValue acousticWarningGomme;
   private final SettingGroup otherModeSettings;
   private final BooleanValue showInSpectatorListOther;
   private final BooleanValue chatMessageOther;
   private final BooleanValue notificationOther;
   private final BooleanValue acousticWarningOther;
   private final SettingGroup listbasedModeSettings;
   private final StringValue lists;
   private final BooleanValue showInSpectatorListListBased;
   private final BooleanValue chatMessageListBased;
   private final BooleanValue notificationListBased;
   private final BooleanValue acousticWarningListBased;
   private final DoubleValue acousticWaringVolume;
   private final DoubleValue scale;
   private final BlurShadowSettings blurShadowSettings;
   private final TimeHelper timeHelper;
   private final ArrayList gommeTabSpectators;
   private final HashSet gommeDetectedStaff;
   private final HashMap gommeSeenPlayersTimestamps;
   private boolean simpleStaffDetected;
   private final HashSet listDetectedStaff;
   private final HashSet configuredStaffNames;
   private long otherModeLastDetectedMs;
   private final HashSet normalDetectedStaff;
   private final HashMap spectatorOverlayEntries;
   private final ArrayList pendingLookupTasks;
   private CompletableFuture activeLookupFuture;

   public StaffDetector() {
       super("StaffDetector", "Moderatorerkenner", new Color(80, 162, 181), Categorys.MISC);
      this.modes = new MultiOptionSetting(this, "Modes", StaffDetectorModeOption.values());
      this.normalModeSettings = new SettingGroup(this, "Normal-Mode settings").visibleWhen(StaffDetectorModeOption.NORMAL::isSelected);
      BooleanValue booleanValue = new BooleanValue(this, "Exclude raw UUIDS", true, this.normalModeSettings);
      SettingGroup normalModeSettings2 = this.normalModeSettings;
      SettingGroup settingGroup = normalModeSettings2;
      this.excludeRawUuids = (BooleanValue)booleanValue.withVisibility(() -> settingGroup.isVisible());
      BooleanValue booleanValue3 = (BooleanValue)new BooleanValue(this, "Show in spectator list normal", true, this.normalModeSettings).withDisplayName("Show in spectator list");
      normalModeSettings2 = this.normalModeSettings;
      SettingGroup settingGroup2 = normalModeSettings2;
      this.showInSpectatorListNormal = (BooleanValue)booleanValue3.withVisibility(() -> settingGroup2.isVisible());
      BooleanValue booleanValue5 = (BooleanValue)new BooleanValue(this, "Chat message normal", true, this.normalModeSettings).withDisplayName("Send chat message");
      normalModeSettings2 = this.normalModeSettings;
      SettingGroup settingGroup3 = normalModeSettings2;
      this.chatMessageNormal = (BooleanValue)booleanValue5.withVisibility(() -> settingGroup3.isVisible());
      this.notificationNormal = (BooleanValue)((BooleanValue)new BooleanValue(this, "Notification normal", true, this.normalModeSettings).withDisplayName("Show notification")).withVisibility(() -> this.isNormalNotificationVisible());
      BooleanValue booleanValue9 = (BooleanValue)new BooleanValue(this, "Acoustic warning normal", true, this.normalModeSettings).withDisplayName("Acoustic warning");
      normalModeSettings2 = this.normalModeSettings;
      SettingGroup settingGroup4 = normalModeSettings2;
      this.acousticWarningNormal = (BooleanValue)booleanValue9.withVisibility(() -> settingGroup4.isVisible());
      this.simpleModeSettings = new SettingGroup(this, "Simple-Mode settings").visibleWhen(StaffDetectorModeOption.SIMPLE::isSelected);
      BooleanValue booleanValue11 = (BooleanValue)new BooleanValue(this, "Show in spectator list simple", true, this.simpleModeSettings).withDisplayName("Show in spectator list");
      normalModeSettings2 = this.simpleModeSettings;
      SettingGroup settingGroup5 = normalModeSettings2;
      this.showInSpectatorListSimple = (BooleanValue)booleanValue11.withVisibility(() -> settingGroup5.isVisible());
      BooleanValue booleanValue13 = (BooleanValue)new BooleanValue(this, "Chat message simple", true, this.simpleModeSettings).withDisplayName("Send chat message");
      normalModeSettings2 = this.simpleModeSettings;
      SettingGroup settingGroup6 = normalModeSettings2;
      this.chatMessageSimple = (BooleanValue)booleanValue13.withVisibility(() -> settingGroup6.isVisible());
      BooleanValue booleanValue15 = (BooleanValue)new BooleanValue(this, "Notification simple", true, this.simpleModeSettings).withDisplayName("Show notification");
      SettingVisibilityCondition condition10 = () -> {
         return this.simpleModeSettings.isVisible() && this.isVanishNotificationActive();
      };
      this.notificationSimple = (BooleanValue)booleanValue15.withVisibility(condition10);
      BooleanValue booleanValue17 = (BooleanValue)new BooleanValue(this, "Acoustic warning simple", true, this.simpleModeSettings).withDisplayName("Acoustic warning");
      normalModeSettings2 = this.simpleModeSettings;
      SettingGroup settingGroup7 = normalModeSettings2;
      this.acousticWarningSimple = (BooleanValue)booleanValue17.withVisibility(() -> settingGroup7.isVisible());
      this.oldgommeModeSettings = new SettingGroup(this, "OldGomme-Mode settings").visibleWhen(StaffDetectorModeOption.GOMME::isSelected);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "CheckDelay", 2000.0, 3000.0, 500.0, 5000.0, 0, this.oldgommeModeSettings)).withoutNotInARow();
      normalModeSettings2 = this.oldgommeModeSettings;
      SettingGroup settingGroup8 = normalModeSettings2;
      this.checkDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> settingGroup8.isVisible());
      BooleanValue booleanValue19 = (BooleanValue)new BooleanValue(this, "Show in spectator list gomme", true, this.oldgommeModeSettings).withDisplayName("Show in spectator list");
      normalModeSettings2 = this.oldgommeModeSettings;
      SettingGroup settingGroup9 = normalModeSettings2;
      this.showInSpectatorListGomme = (BooleanValue)booleanValue19.withVisibility(() -> settingGroup9.isVisible());
      BooleanValue booleanValue21 = (BooleanValue)new BooleanValue(this, "Chat message gomme", true, this.oldgommeModeSettings).withDisplayName("Send chat message");
      normalModeSettings2 = this.oldgommeModeSettings;
      SettingGroup settingGroup10 = normalModeSettings2;
      this.chatMessageGomme = (BooleanValue)booleanValue21.withVisibility(() -> settingGroup10.isVisible());
      this.notificationGomme = (BooleanValue)((BooleanValue)new BooleanValue(this, "Notification gomme", true, this.oldgommeModeSettings).withDisplayName("Show notification")).withVisibility(() -> this.isGommeNotificationVisible());
      BooleanValue booleanValue25 = (BooleanValue)new BooleanValue(this, "Acoustic warning gomme", true, this.oldgommeModeSettings).withDisplayName("Acoustic warning");
      normalModeSettings2 = this.oldgommeModeSettings;
      SettingGroup settingGroup11 = normalModeSettings2;
      this.acousticWarningGomme = (BooleanValue)booleanValue25.withVisibility(() -> settingGroup11.isVisible());
      this.otherModeSettings = new SettingGroup(this, "Other-Mode settings").visibleWhen(StaffDetectorModeOption.OTHER::isSelected);
      BooleanValue booleanValue27 = (BooleanValue)new BooleanValue(this, "Show in spectator list other", true, this.otherModeSettings).withDisplayName("Show in spectator list");
      normalModeSettings2 = this.otherModeSettings;
      SettingGroup settingGroup12 = normalModeSettings2;
      this.showInSpectatorListOther = (BooleanValue)booleanValue27.withVisibility(() -> settingGroup12.isVisible());
      BooleanValue booleanValue29 = (BooleanValue)new BooleanValue(this, "Chat message other", true, this.otherModeSettings).withDisplayName("Send chat message");
      normalModeSettings2 = this.otherModeSettings;
      SettingGroup settingGroup13 = normalModeSettings2;
      this.chatMessageOther = (BooleanValue)booleanValue29.withVisibility(() -> settingGroup13.isVisible());
      this.notificationOther = (BooleanValue)((BooleanValue)new BooleanValue(this, "Notification other", true, this.otherModeSettings).withDisplayName("Show notification")).withVisibility(() -> this.isOtherNotificationVisible());
      BooleanValue booleanValue33 = (BooleanValue)new BooleanValue(this, "Acoustic warning other", true, this.otherModeSettings).withDisplayName("Acoustic warning");
      normalModeSettings2 = this.otherModeSettings;
      SettingGroup settingGroup14 = normalModeSettings2;
      this.acousticWarningOther = (BooleanValue)booleanValue33.withVisibility(() -> settingGroup14.isVisible());
      this.listbasedModeSettings = new SettingGroup(this, "ListBased-Mode settings").visibleWhen(StaffDetectorModeOption.LIST_BASED::isSelected);
      StringValue setting = new StringValue(this, "Lists", "GommeHDDefault", new String[0], this.listbasedModeSettings);
      normalModeSettings2 = this.listbasedModeSettings;
      SettingGroup settingGroup15 = normalModeSettings2;
      this.lists = (StringValue)((StringValue)setting.withVisibility(() -> settingGroup15.isVisible())).withChangeListener(() -> this.loadSelectedStaffList());
      BooleanValue booleanValue35 = (BooleanValue)new BooleanValue(this, "Show in spectator list list based", true, this.listbasedModeSettings).withDisplayName("Show in spectator list");
      normalModeSettings2 = this.listbasedModeSettings;
      SettingGroup settingGroup16 = normalModeSettings2;
      this.showInSpectatorListListBased = (BooleanValue)booleanValue35.withVisibility(() -> settingGroup16.isVisible());
      BooleanValue booleanValue37 = (BooleanValue)new BooleanValue(this, "Chat message list based", true, this.listbasedModeSettings).withDisplayName("Send chat message");
      normalModeSettings2 = this.listbasedModeSettings;
      SettingGroup settingGroup17 = normalModeSettings2;
      this.chatMessageListBased = (BooleanValue)booleanValue37.withVisibility(() -> settingGroup17.isVisible());
      this.notificationListBased = (BooleanValue)((BooleanValue)new BooleanValue(this, "Notification list based", true, this.listbasedModeSettings).withDisplayName("Show notification")).withVisibility(() -> this.isListNotificationVisible());
      BooleanValue booleanValue41 = (BooleanValue)new BooleanValue(this, "Acoustic warning list based", true, this.listbasedModeSettings).withDisplayName("Acoustic warning");
      normalModeSettings2 = this.listbasedModeSettings;
      SettingGroup settingGroup18 = normalModeSettings2;
      this.acousticWarningListBased = (BooleanValue)booleanValue41.withVisibility(() -> settingGroup18.isVisible());
      DoubleValue acousticWaringVolumeSetting = new DoubleValue(this, "Acoustic waring volume", 93.0, 0.0, 100.0, 0);
      SettingVisibilityCondition condition29 = () -> {
         return this.acousticWarningNormal.getValue() && this.acousticWarningNormal.isVisible()
            || this.acousticWarningGomme.getValue() && this.acousticWarningGomme.isVisible()
            || this.acousticWarningOther.getValue() && this.acousticWarningOther.isVisible()
            || this.acousticWarningListBased.getValue() && this.acousticWarningListBased.isVisible();
      };
      this.acousticWaringVolume = acousticWaringVolumeSetting.visibleWhen(condition29);
      DoubleValue scaleSetting = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2);
      SettingVisibilityCondition condition30 = () -> {
         return this.showInSpectatorListNormal.getValue() && this.showInSpectatorListNormal.isVisible()
            || this.showInSpectatorListGomme.getValue() && this.showInSpectatorListGomme.isVisible()
            || this.showInSpectatorListOther.getValue() && this.showInSpectatorListOther.isVisible()
            || this.showInSpectatorListListBased.getValue() && this.showInSpectatorListListBased.isVisible();
      };
      this.scale = scaleSetting.visibleWhen(condition30);
      this.blurShadowSettings = (BlurShadowSettings)new BlurShadowSettings(this).withCustomShadowColor(Color.black).withVisibility(() -> this.isBlurShadowVisible());
      this.timeHelper = new TimeHelper();
      this.gommeTabSpectators = new ArrayList();
      this.gommeDetectedStaff = new HashSet();
      this.gommeSeenPlayersTimestamps = new HashMap();
      this.simpleStaffDetected = false;
      this.listDetectedStaff = new HashSet();
      this.configuredStaffNames = new HashSet();
      this.otherModeLastDetectedMs = 0L;
      this.normalDetectedStaff = new HashSet();
      this.spectatorOverlayEntries = new HashMap();
      this.pendingLookupTasks = new ArrayList();
      this.activeLookupFuture = null;
      this.extractDefaultStaffLists();
   }

   @Override
   public void onEnable() {
      this.refreshStaffListOptions();
      this.clearAllDetectedStaff();
      this.spectatorOverlayEntries.clear();
      this.gommeSeenPlayersTimestamps.clear();
      this.checkDelay.nextValue();
      this.simpleStaffDetected = false;
   }

   @Override
   public void onDisable() {
      this.clearAllDetectedStaff();
      this.spectatorOverlayEntries.clear();
      this.gommeSeenPlayersTimestamps.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (mc.currentScreen == null && !this.spectatorOverlayEntries.isEmpty() && !KeyboardInputUtil.isKeyDown(15)) {
         GL11.glPushMatrix();
         GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
         double value = this.scale.getValue();
         GL11.glScaled(value, value, 1.0);
         ScaledResolution sr2 = new ScaledResolution((double)mc.displayWidth * (1.0 / value), (double)mc.displayHeight * (1.0 / value), (int)value);
         FontRenderer fontRendererObj = mc.fontRendererObj;
         int n = sr2.getScaledWidth() / 2;
         String str = "Spectators:";
         int n2 = fontRendererObj.getStringWidth(str) + 4;
         int n3 = fontRendererObj.FONT_HEIGHT + 2;

         for (String str2 : (Iterable<String>)(this.spectatorOverlayEntries.keySet())) {
            n3 += 11;
            int n4 = fontRendererObj.getStringWidth(str2) + 4;
            if (n4 > n2) {
               n2 = n4;
            }
         }

         int n5 = n2;
         int n6 = n3;
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
            byte b = 1;
            if (shaderRenderPass == ShaderRenderPass.SHADOW) {
               RenderUtil.drawRect(n - n5 / 2, 0, n + n5 / 2, b + n6, this.blurShadowSettings.resolveShadowColor(Color.black.getRGB()));
            } else {
               RenderUtil.drawRect(n - n5 / 2, 0, n + n5 / 2, b + n6, 0, 0, 0, 150);
            }

            RenderUtil.drawRect(n - n5 / 2, 11, n + n5 / 2, 12, 0, 0, 0);
            if (shaderRenderPass == ShaderRenderPass.NONE) {
               fontRendererObj.drawString(str, n - fontRendererObj.getStringWidth(str) / 2, b, Augustus.getInstance().getClientColor());
               b = 13;

               for (String str3 : (Iterable<String>)(this.spectatorOverlayEntries.keySet())) {
                  Boolean bool = (Boolean)this.spectatorOverlayEntries.get(str3);
                  if (bool != null) {
                     Color color2 = bool ? Color.RED : Color.LIGHT_GRAY;
                     fontRendererObj.drawString(str3, n - fontRendererObj.getStringWidth(str3) / 2, b, color2.getRGB());
                     b += 11;
                  }
               }
            }
         });
         GL11.glPopMatrix();
         RenderUtil.resetGlColorState();
      }
   }

   @Override
   public void onTick() {
      this.rebuildSpectatorOverlayMap();
      if (StaffDetectorModeOption.GOMME.isSelected()) {
         if (this.timeHelper.reached((long)this.checkDelay.getLastIntValue()) && !(mc.currentScreen instanceof GuiChat)) {
            this.checkDelay.nextValue();
            this.timeHelper.reset();
            this.sendGommeTabCompleteProbe();

            for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
               String name2 = networkPlayerInfo.getGameProfile().getName();
               this.gommeSeenPlayersTimestamps.remove(name2);
               this.gommeSeenPlayersTimestamps.put(name2, System.currentTimeMillis());
            }

            LinkedList list = new LinkedList();

            for (String str : (Iterable<String>)(this.gommeSeenPlayersTimestamps.keySet())) {
               Long val = (Long)this.gommeSeenPlayersTimestamps.get(str);
               if (val == null || val + 15000L < System.currentTimeMillis() && !this.gommeTabSpectators.contains(str)) {
                  list.add(str);
               }
            }

            for (String str2 : (Iterable<String>)(list)) {
               this.gommeSeenPlayersTimestamps.remove(str2);
            }

            for (String str3 : (Iterable<String>)(this.gommeTabSpectators)) {
               if (this.gommeSeenPlayersTimestamps.get(str3) == null) {
                  if (this.acousticWarningGomme.getValue()) {
                     this.playWarningSound();
                  }

                  this.gommeDetectedStaff.add(str3);
                  this.gommeSeenPlayersTimestamps.put(str3, System.currentTimeMillis());
               }
            }
         }
      }

      if (StaffDetectorModeOption.LIST_BASED.isSelected() && !this.configuredStaffNames.isEmpty()) {
         for (NetworkPlayerInfo networkPlayerInfo2 : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            if (networkPlayerInfo2 != null && networkPlayerInfo2.getGameProfile() != null) {
               this.checkListBasedStaffName(networkPlayerInfo2.getGameProfile().getName());
            }
         }

         for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
            if (entity instanceof EntityPlayer) {
               this.checkListBasedStaffName(((EntityPlayer)entity).getGameProfile().getName());
            }
         }
      }

      if (StaffDetectorModeOption.NORMAL.isSelected() && !this.pendingLookupTasks.isEmpty() && (this.activeLookupFuture == null || !this.activeLookupFuture.isDone())) {
         this.activeLookupFuture = CompletableFuture.runAsync((Runnable)this.pendingLookupTasks.get(0));
         this.pendingLookupTasks.remove(0);
      }

      this.pruneDisabledModeState();
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.clearAllDetectedStaff();
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (mc.theWorld != null && mc.thePlayer != null) {
         if (StaffDetectorModeOption.NORMAL.isSelected() && packet instanceof S38PacketPlayerListItem) {
            S38PacketPlayerListItem packet2 = (S38PacketPlayerListItem)packet;
            if (Objects.requireNonNull(packet2.getAction()) == S38PacketPlayerListItem.Action.UPDATE_LATENCY) {
               this.pendingLookupTasks.add((Runnable)() -> {
                  LinkedList list = new LinkedList();

                  for (S38PacketPlayerListItem.AddPlayerData addPlayerData : (Iterable<S38PacketPlayerListItem.AddPlayerData>)(packet2.getPlayers())) {
                     if (!mc.getNetHandler().getPlayerInfoMap2().containsKey(addPlayerData.getProfile().getId())) {
                        UUID id = addPlayerData.getProfile().getId();

                        try {
                           HttpURLConnection conn = (HttpURLConnection)new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + id).openConnection();
                           conn.setRequestMethod("GET");
                           conn.setConnectTimeout(20000);
                           conn.connect();
                           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                           String str = "";
                           StringBuilder sb = new StringBuilder();

                           while ((str = reader.readLine()) != null) {
                              sb.append(str);
                           }

                           conn.disconnect();
                           IOUtils.closeQuietly(reader);
                           String[] parts = sb.toString().split("\"");
                           this.handleNormalStaffDetected(parts[7]);
                           list.add(parts[7]);
                        } catch (Exception e) {
                           if (!this.excludeRawUuids.getValue()) {
                              String str3 = id.toString();
                              this.handleNormalStaffDetected(str3);
                              list.add(str3);
                           }
                        }
                     }
                  }

                  this.normalDetectedStaff.removeIf(obj -> {
                     return !list.contains(obj);
                  });
               });
            }
         }

         if (StaffDetectorModeOption.SIMPLE.isSelected() && packet instanceof S38PacketPlayerListItem) {
            S38PacketPlayerListItem packet4 = (S38PacketPlayerListItem)packet;
            if (packet4.getAction() == S38PacketPlayerListItem.Action.UPDATE_LATENCY) {
               List players = packet4.getPlayers();
               int n = players == null ? 0 : players.size();
               NetHandlerPlayClient netHandler = mc.getNetHandler();
               if (n != (netHandler == null ? 0 : netHandler.getPlayerInfoMap().size())) {
                  if (!this.simpleStaffDetected) {
                     if (this.acousticWarningSimple.getValue()) {
                        this.playWarningSound();
                     }

                     if (this.chatMessageSimple.getValue()) {
                        String str4 = "unknown";
                        this.sendStaffChatAlert(StaffDetectorModeOption.SIMPLE, str4);
                     }

                     if (this.notificationSimple.getValue()) {
                        String str5 = "unknown";
                        this.sendStaffNotification(StaffDetectorModeOption.SIMPLE, str5);
                     }
                  }

                  this.simpleStaffDetected = true;
               } else {
                  this.simpleStaffDetected = false;
               }
            }
         }

         if (StaffDetectorModeOption.GOMME.isSelected() && packet instanceof S3APacketTabComplete && !(mc.currentScreen instanceof GuiChat)) {
            String[] matches = ((S3APacketTabComplete)packet).getMatches();
            ArrayList list2 = new ArrayList();

            for (String str6 : matches) {
               boolean flag = !str6.equals(mc.session.getUsername());

               for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
                  if (!networkPlayerInfo.getGameProfile().getName().equals(mc.thePlayer.getGameProfile().getName())) {
                     String str7 = networkPlayerInfo.getDisplayName() != null
                        ? networkPlayerInfo.getDisplayName().getUnformattedText()
                        : ScorePlayerTeam.formatPlayerName(networkPlayerInfo.getPlayerTeam(), networkPlayerInfo.getGameProfile().getName());
                     if (str7.contains(str6)) {
                        flag = false;
                        break;
                     }
                  }
               }

               if (flag) {
                  list2.add(str6);
               }

               if (this.chatMessageGomme.getValue() || this.notificationGomme.getValue()) {
                  for (String str8 : (Iterable<String>)(list2)) {
                     if (!this.gommeTabSpectators.contains(str8)) {
                        if (this.chatMessageGomme.getValue()) {
                           this.sendStaffChatAlert(StaffDetectorModeOption.GOMME, str8);
                        }

                        if (this.notificationGomme.getValue()) {
                           this.sendStaffNotification(StaffDetectorModeOption.GOMME, str8);
                        }
                     }
                  }
               }

               this.gommeTabSpectators.clear();
               this.gommeTabSpectators.addAll(list2);
            }
         }

         if (StaffDetectorModeOption.OTHER.isSelected()) {
            long currentTimeMs = System.currentTimeMillis();
            if (packet instanceof S1DPacketEntityEffect && mc.theWorld.getEntityByID(((S1DPacketEntityEffect)packet).getEntityId()) == null
               || packet instanceof S14PacketEntity && mc.theWorld.getEntityByID(((S14PacketEntity)packet).getEntityId()) == null
               || packet instanceof S18PacketEntityTeleport && mc.theWorld.getEntityByID(((S18PacketEntityTeleport)packet).getEntityId()) == null) {
               if (this.getOtherModeLastDetectedMs() == 0L) {
                  if (this.acousticWarningOther.getValue()) {
                     this.playWarningSound();
                  }

                  if (this.chatMessageOther.getValue()) {
                     String str9 = "unknown";
                     this.sendStaffChatAlert(StaffDetectorModeOption.OTHER, str9);
                  }

                  if (this.notificationOther.getValue()) {
                     String str10 = "unknown";
                     this.sendStaffNotification(StaffDetectorModeOption.OTHER, str10);
                  }
               }

               this.setOtherModeLastDetectedMs(currentTimeMs);
            }

            if (currentTimeMs - this.getOtherModeLastDetectedMs() > 30000L) {
               this.setOtherModeLastDetectedMs(0L);
            }
         }
      }
   }

   private void checkListBasedStaffName(String str) {
      if (this.configuredStaffNames.contains(str) && !this.listDetectedStaff.contains(str)) {
         if (this.acousticWarningListBased.getValue()) {
            this.playWarningSound();
         }

         if (this.chatMessageListBased.getValue()) {
            this.sendStaffChatAlert(StaffDetectorModeOption.LIST_BASED, str);
         }

         if (this.notificationListBased.getValue()) {
            this.sendStaffNotification(StaffDetectorModeOption.LIST_BASED, str);
         }

         this.listDetectedStaff.add(str);
      }
   }

   private void handleNormalStaffDetected(String str) {
      if (!this.normalDetectedStaff.contains(str)) {
         this.normalDetectedStaff.add(str);
         if (this.acousticWarningNormal.getValue()) {
            this.playWarningSound();
         }

         if (this.chatMessageNormal.getValue()) {
            this.sendStaffChatAlert(StaffDetectorModeOption.NORMAL, str);
         }

         if (this.notificationNormal.getValue()) {
            this.sendStaffNotification(StaffDetectorModeOption.NORMAL, str);
         }
      }
   }

   private void sendStaffChatAlert(StaffDetectorModeOption staffDetectorModeOption, String str) {
      this.sendChatMessage("§4§lStaff detected (" + staffDetectorModeOption.getName() + "):§6 " + str);
   }

   private void sendStaffNotification(StaffDetectorModeOption staffDetectorModeOption, String str) {
      if (Augustus.getInstance().getModuleManager().notifications.isToggled() && NotificationCategoryOption.VANISH_NOTIFICATION.isSelected()) {
         String str2 = "§4§lStaff detected (" + staffDetectorModeOption.getName() + "):§6 " + str;
         NotificationLevel info = NotificationLevel.INFO;
         NotificationManager.pushNotification("VanishNotify", str2, info);
      }
   }

   private void sendGommeTabCompleteProbe() {
      BlockPos pos = null;
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         pos = mc.objectMouseOver.getBlockPos();
      }

      mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete("/tp ", pos));
   }

   private void rebuildSpectatorOverlayMap() {
      this.spectatorOverlayEntries.clear();
      if (StaffDetectorModeOption.GOMME.isSelected() && this.showInSpectatorListGomme.getValue()) {
         for (String str : (Iterable<String>)(this.gommeTabSpectators)) {
            this.spectatorOverlayEntries.put(str, this.gommeDetectedStaff.contains(str));
         }
      }

      if (StaffDetectorModeOption.OTHER.isSelected() && this.showInSpectatorListOther.getValue() && this.getOtherModeLastDetectedMs() != 0L) {
         this.spectatorOverlayEntries.put("Unknown Staff", true);
      }

      if (StaffDetectorModeOption.SIMPLE.isSelected() && this.showInSpectatorListSimple.getValue() && this.simpleStaffDetected) {
         this.spectatorOverlayEntries.put("Unknown Staff", true);
      }

      if (StaffDetectorModeOption.LIST_BASED.isSelected() && this.showInSpectatorListListBased.getValue()) {
         for (String str2 : (Iterable<String>)(this.listDetectedStaff)) {
            this.spectatorOverlayEntries.put(str2, true);
         }
      }

      if (StaffDetectorModeOption.NORMAL.isSelected() && this.showInSpectatorListNormal.getValue()) {
         for (String str3 : (Iterable<String>)(this.normalDetectedStaff)) {
            this.spectatorOverlayEntries.put(str3, true);
         }
      }
   }

   private void pruneDisabledModeState() {
      if (!StaffDetectorModeOption.LIST_BASED.isSelected()) {
         this.listDetectedStaff.clear();
      }

      if (!StaffDetectorModeOption.GOMME.isSelected()) {
         this.gommeTabSpectators.clear();
         this.gommeDetectedStaff.clear();
      }

      if (!StaffDetectorModeOption.OTHER.isSelected()) {
         this.setOtherModeLastDetectedMs(0L);
      }

      if (!StaffDetectorModeOption.NORMAL.isSelected()) {
         this.normalDetectedStaff.clear();
      }
   }

   private void clearAllDetectedStaff() {
      this.gommeTabSpectators.clear();
      this.gommeDetectedStaff.clear();
      this.listDetectedStaff.clear();
      this.setOtherModeLastDetectedMs(0L);
      this.normalDetectedStaff.clear();
   }

   private void extractDefaultStaffLists() {
      String str = Augustus.getInstance().getName().toLowerCase() + "/stafflists";
      String str2 = str;
      ClientFileUtil.extractResource("assets/augustus/stafflists/BlocksMCDefault.txt", str2, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/stafflists";
      String str3 = str;
      ClientFileUtil.extractResource("assets/augustus/stafflists/HypixelDefault.txt", str3, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/stafflists";
      String str4 = str;
      ClientFileUtil.extractResource("assets/augustus/stafflists/GamsterDefault.txt", str4, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/stafflists";
      String str5 = str;
      ClientFileUtil.extractResource("assets/augustus/stafflists/GommeHDDefault.txt", str5, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/stafflists";
      String str6 = str;
      ClientFileUtil.extractResource("assets/augustus/stafflists/PikaDefault.txt", str6, true);
      this.refreshStaffListOptions();
   }

   private void refreshStaffListOptions() {
      File file = new File(STAFF_LISTS_DIR);
      ClientFileUtil.ensureDirectory(STAFF_LISTS_DIR);
      File[] files = file.listFiles();
      if (files != null) {
         List list = Arrays.stream(files).filter(file2 -> {
            return file2 != null && file2.getName().endsWith(".txt");
         }).map(file3 -> file3.getName().substring(0, Math.max(0, file3.getName().length() - 4))).collect(Collectors.toList());
         boolean flag = list.contains(this.lists.getSelectedValue());
         String[] strings = (String[])list.toArray(new String[0]);
         if (!flag) {
            String str = strings[0];
            this.lists.setSelectedValue(str);
         }

         this.lists.setOptions(strings);
         this.loadSelectedStaffList();
      }
   }

   private void loadSelectedStaffList() {
      File file = new File(STAFF_LISTS_DIR);
      ClientFileUtil.ensureDirectory(STAFF_LISTS_DIR);
      File[] files = file.listFiles();
      if (files != null) {
         Stream stream = Arrays.stream(files);
         File file2 = (File)stream.filter(obj -> {
            return ((File)obj).getName().equals(this.lists.getSelectedValue() + ".txt");
         }).findFirst().orElse(null);
         if (file2 != null) {
            ArrayList list = new ArrayList(ClientFileUtil.readAllLines(file2.getAbsolutePath()));
            list.replaceAll(obj2 -> {
               if (((String)obj2).contains(" ")) {
                  obj2 = StringUtils.replace((String)obj2, " ", "");
               }

               return obj2;
            });
            this.configuredStaffNames.clear();
            this.configuredStaffNames.addAll(list);
            this.sendChatMessage("Loaded " + list.size() + " staff names!");
         }
      }
   }

   private void playWarningSound() {
      mc.getSoundHandler().playSound(PositionedSoundRecord.createWithVolume(ClientSounds.AUGUSTUS_TEXTURE7, (float)(this.acousticWaringVolume.getValue() / 100.0)));
   }

   private synchronized long getOtherModeLastDetectedMs() {
      return this.otherModeLastDetectedMs;
   }

   private synchronized void setOtherModeLastDetectedMs(long l) {
      this.otherModeLastDetectedMs = l;
   }

   public boolean hasConfirmedStaff() {

      for (String str : (Iterable<String>)(this.spectatorOverlayEntries.keySet())) {
         Boolean bool = (Boolean)this.spectatorOverlayEntries.get(str);
         if (bool != null && bool) {
            return true;
         }
      }

      return false;
   }

   private boolean isVanishNotificationActive() {
      return ClientManagers.moduleManager.oldNotification.isToggled() && NotificationCategoryOption.VANISH_NOTIFICATION.isSelected();
   }

   private boolean isBlurShadowVisible() {
      return this.showInSpectatorListNormal.getValue() && this.showInSpectatorListNormal.isVisible()
         || this.showInSpectatorListGomme.getValue() && this.showInSpectatorListGomme.isVisible()
         || this.showInSpectatorListOther.getValue() && this.showInSpectatorListOther.isVisible()
         || this.showInSpectatorListListBased.getValue() && this.showInSpectatorListListBased.isVisible();
   }

   private boolean isListNotificationVisible() {
      return this.listbasedModeSettings.isVisible() && this.isVanishNotificationActive();
   }

   private boolean isOtherNotificationVisible() {
      return this.otherModeSettings.isVisible() && this.isVanishNotificationActive();
   }

   private boolean isGommeNotificationVisible() {
      return this.oldgommeModeSettings.isVisible() && this.isVanishNotificationActive();
   }

   private boolean isNormalNotificationVisible() {
      return this.normalModeSettings.isVisible() && this.isVanishNotificationActive();
   }

}
