package augustus.modules.misc;

import augustus.modes.render.TargetFilterMode;
import client.Client;
import client.ClientGlobals;
import client.event.EventPacket;
import client.event.EventWorldLoad;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BeverageNotifyInfoType;
import client.setting.BeverageNotifyModuleTogglesType;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.setting.StaffDetectorAddPlayerType;
import client.util.BeverageItemNotification;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.ConsumerTask;
import client.util.KeyUtils;
import client.util.LatencyManager;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.OnlineConfigsManager;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.SoundPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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

public class StaffDetector extends Module implements WorldLoadListener, RenderListener, PacketListener, TickListener {
    public static String displayName2 = OnlineConfigsManager.text + File.separator + "staffLists";
    public MultiSelectSetting settingModes;
    public SettingGroup settingNormalModeSettings;
    public BooleanSetting settingExcludeRawUUIDS;
    public BooleanSetting settingShowInSpectatorListNormal;
    public BooleanSetting settingChatMessageNormal;
    public BooleanSetting settingNotificationNormal;
    public BooleanSetting settingAcousticWarningNormal;
    public SettingGroup settingSimpleModeSettings;
    public BooleanSetting settingShowInSpectatorListSimple;
    public BooleanSetting settingChatMessageSimple;
    public BooleanSetting settingNotificationSimple;
    public BooleanSetting settingAcousticWarningSimple;
    public SettingGroup settingOldGommeModeSettings;
    public RangeSetting settingCheckDelay;
    public BooleanSetting settingShowInSpectatorListGomme;
    public BooleanSetting settingChatMessageGomme;
    public BooleanSetting settingNotificationGomme;
    public BooleanSetting settingAcousticWarningGomme;
    public SettingGroup settingOtherModeSettings;
    public BooleanSetting settingShowInSpectatorListOther;
    public BooleanSetting settingChatMessageOther;
    public BooleanSetting settingNotificationOther;
    public BooleanSetting settingAcousticWarningOther;
    public SettingGroup settingListBasedModeSettings;
    public ModeSetting settingLists;
    public BooleanSetting settingShowInSpectatorListListBased;
    public BooleanSetting settingChatMessageListBased;
    public BooleanSetting settingNotificationListBased;
    public BooleanSetting settingAcousticWarningListBased;
    public NumberSetting settingAcousticWaringVolume;
    public NumberSetting settingScale;
    public ShaderOptions settingScale2;
    public TimeHelper timer;
    public ArrayList itemList3;
    public HashSet entrySet;
    public HashMap entryMap;
    public boolean expanded2;
    public HashSet entrySet2;
    public HashSet entrySet3;
    public long timestampMillis;
    public HashSet entrySet4;
    public HashMap entryMap2;
    public ArrayList pendingCheckTasks;
    public CompletableFuture completableFuture;

    public StaffDetector() {
        super("StaffDetector", "Moderatorerkenner", new Color(80, 162, 181), Category.MISC);
        TargetFilterMode[] targetFilterModeArray = TargetFilterMode.values();
        this.settingModes = new MultiSelectSetting(this, "Modes", targetFilterModeArray);
        this.settingNormalModeSettings = new SettingGroup(this, "Normal-Mode settings").getSettingGroup(() -> TargetFilterMode.NORMAL.isSelected());
        this.settingExcludeRawUUIDS = (BooleanSetting)new BooleanSetting(this, "Exclude raw UUIDS", true, this.settingNormalModeSettings).getSetting(() -> this.settingNormalModeSettings.isVisible());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "Show in spectator list normal", true, this.settingNormalModeSettings);
        this.settingShowInSpectatorListNormal = (BooleanSetting)(((BooleanSetting)booleanSetting2.getSetting("Show in spectator list")).getSetting(() -> this.settingNormalModeSettings.isVisible()));
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "Chat message normal", true, this.settingNormalModeSettings);
        this.settingChatMessageNormal = (BooleanSetting)(((BooleanSetting)booleanSetting3.getSetting("Send chat message")).getSetting(() -> this.settingNormalModeSettings.isVisible()));
        Setting setting6 = new BooleanSetting(this, "Notification normal", true, this.settingNormalModeSettings).getSetting("Show notification");
        this.settingNotificationNormal = (BooleanSetting)(((BooleanSetting)setting6).getSetting(() -> this.isNotificationNormalVisible()));
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "Acoustic warning normal", true, this.settingNormalModeSettings);
        this.settingAcousticWarningNormal = (BooleanSetting)(((BooleanSetting)booleanSetting5.getSetting("Acoustic warning")).getSetting(() -> this.settingNormalModeSettings.isVisible()));
        this.settingSimpleModeSettings = new SettingGroup(this, "Simple-Mode settings").getSettingGroup(() -> TargetFilterMode.SIMPLE.isSelected());
        BooleanSetting booleanSetting6 = new BooleanSetting(this, "Show in spectator list simple", true, this.settingSimpleModeSettings);
        this.settingShowInSpectatorListSimple = (BooleanSetting)(((BooleanSetting)booleanSetting6.getSetting("Show in spectator list")).getSetting(() -> this.settingSimpleModeSettings.isVisible()));
        BooleanSetting booleanSetting7 = new BooleanSetting(this, "Chat message simple", true, this.settingSimpleModeSettings);
        this.settingChatMessageSimple = (BooleanSetting)(((BooleanSetting)booleanSetting7.getSetting("Send chat message")).getSetting(() -> this.settingSimpleModeSettings.isVisible()));
        Setting setting14 = new BooleanSetting(this, "Notification simple", true, this.settingSimpleModeSettings).getSetting("Show notification");
        this.settingNotificationSimple = (BooleanSetting)(((BooleanSetting)setting14).getSetting(() -> this.isNotificationSimpleVisible()));
        BooleanSetting booleanSetting9 = new BooleanSetting(this, "Acoustic warning simple", true, this.settingSimpleModeSettings);
        this.settingAcousticWarningSimple = (BooleanSetting)(((BooleanSetting)booleanSetting9.getSetting("Acoustic warning")).getSetting(() -> this.settingSimpleModeSettings.isVisible()));
        this.settingOldGommeModeSettings = new SettingGroup(this, "OldGomme-Mode settings").getSettingGroup(() -> TargetFilterMode.GOMME.isSelected());
        this.settingCheckDelay = new RangeSetting(this, "CheckDelay", 2000.0, 3000.0, 500.0, 5000.0, 0, this.settingOldGommeModeSettings)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingOldGommeModeSettings.isVisible());
        BooleanSetting booleanSetting10 = new BooleanSetting(this, "Show in spectator list gomme", true, this.settingOldGommeModeSettings);
        this.settingShowInSpectatorListGomme = (BooleanSetting)(((BooleanSetting)booleanSetting10.getSetting("Show in spectator list")).getSetting(() -> this.settingOldGommeModeSettings.isVisible()));
        BooleanSetting booleanSetting11 = new BooleanSetting(this, "Chat message gomme", true, this.settingOldGommeModeSettings);
        this.settingChatMessageGomme = (BooleanSetting)(((BooleanSetting)booleanSetting11.getSetting("Send chat message")).getSetting(() -> this.settingOldGommeModeSettings.isVisible()));
        Setting setting22 = new BooleanSetting(this, "Notification gomme", true, this.settingOldGommeModeSettings).getSetting("Show notification");
        this.settingNotificationGomme = (BooleanSetting)(((BooleanSetting)setting22).getSetting(() -> this.isNotificationGommeVisible()));
        BooleanSetting booleanSetting13 = new BooleanSetting(this, "Acoustic warning gomme", true, this.settingOldGommeModeSettings);
        this.settingAcousticWarningGomme = (BooleanSetting)(((BooleanSetting)booleanSetting13.getSetting("Acoustic warning")).getSetting(() -> this.settingOldGommeModeSettings.isVisible()));
        this.settingOtherModeSettings = new SettingGroup(this, "Other-Mode settings").getSettingGroup(() -> TargetFilterMode.OTHER.isSelected());
        BooleanSetting booleanSetting14 = new BooleanSetting(this, "Show in spectator list other", true, this.settingOtherModeSettings);
        this.settingShowInSpectatorListOther = (BooleanSetting)(((BooleanSetting)booleanSetting14.getSetting("Show in spectator list")).getSetting(() -> this.settingOtherModeSettings.isVisible()));
        BooleanSetting booleanSetting15 = new BooleanSetting(this, "Chat message other", true, this.settingOtherModeSettings);
        this.settingChatMessageOther = (BooleanSetting)(((BooleanSetting)booleanSetting15.getSetting("Send chat message")).getSetting(() -> this.settingOtherModeSettings.isVisible()));
        Setting setting30 = new BooleanSetting(this, "Notification other", true, this.settingOtherModeSettings).getSetting("Show notification");
        this.settingNotificationOther = (BooleanSetting)(((BooleanSetting)setting30).getSetting(() -> this.isNotificationOtherVisible()));
        BooleanSetting booleanSetting17 = new BooleanSetting(this, "Acoustic warning other", true, this.settingOtherModeSettings);
        this.settingAcousticWarningOther = (BooleanSetting)(((BooleanSetting)booleanSetting17.getSetting("Acoustic warning")).getSetting(() -> this.settingOtherModeSettings.isVisible()));
        this.settingListBasedModeSettings = new SettingGroup(this, "ListBased-Mode settings").getSettingGroup(() -> TargetFilterMode.LIST_BASED.isSelected());
        String[] textArray = new String[0];
        this.settingLists = new ModeSetting(this, "Lists", "GommeHDDefault", textArray, this.settingListBasedModeSettings).getModeSetting(() -> this.settingListBasedModeSettings.isVisible()).getModeSetting(() -> this.onListsAction());
        BooleanSetting booleanSetting18 = new BooleanSetting(
        this, "Show in spectator list list based", true, this.settingListBasedModeSettings
        );
        this.settingShowInSpectatorListListBased = (BooleanSetting)(((BooleanSetting)booleanSetting18.getSetting("Show in spectator list")).getSetting(() -> this.settingListBasedModeSettings.isVisible()));
        BooleanSetting booleanSetting19 = new BooleanSetting(this, "Chat message list based", true, this.settingListBasedModeSettings);
        this.settingChatMessageListBased = (BooleanSetting)(((BooleanSetting)booleanSetting19.getSetting("Send chat message")).getSetting(() -> this.settingListBasedModeSettings.isVisible()));
        Setting setting38 = new BooleanSetting(this, "Notification list based", true, this.settingListBasedModeSettings).getSetting("Show notification");
        this.settingNotificationListBased = (BooleanSetting)(((BooleanSetting)setting38).getSetting(() -> this.isNotificationListBasedVisible()));
        BooleanSetting booleanSetting21 = new BooleanSetting(this, "Acoustic warning list based", true, this.settingListBasedModeSettings);
        this.settingAcousticWarningListBased = (BooleanSetting)(((BooleanSetting)booleanSetting21.getSetting("Acoustic warning")).getSetting(() -> this.settingListBasedModeSettings.isVisible()));
        this.settingAcousticWaringVolume = new NumberSetting(this, "Acoustic waring volume", 93.0, 0.0, 100.0, 0).getNumberSetting(() -> this.isAcousticWaringVolumeVisible());
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2).getNumberSetting(() -> this.isScaleVisible2());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingScale2 = (ShaderOptions)shaderOptions.getShaderOptions(black).getSetting(() -> this.isScaleVisible());
        this.timer = new TimeHelper();
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
        this.entrySet = new HashSet();
        this.entryMap = new HashMap();
        this.expanded2 = false;
        this.entrySet2 = new HashSet();
        this.entrySet3 = new HashSet();
        this.timestampMillis = 0L;
        this.entrySet4 = new HashSet();
        this.entryMap2 = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        this.pendingCheckTasks = arrayList2;
        this.completableFuture = (CompletableFuture)null;
        this.updateState4();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.updateState();
        this.entryMap2.clear();
        this.entryMap.clear();
    }

    @Override
    public void onPreDisable() {
        this.processAction();
        this.updateState();
        this.entryMap2.clear();
        this.entryMap.clear();
        this.settingCheckDelay.recalculate();
        this.expanded2 = false;
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (minecraft.currentScreen == null) {
            if (this.entryMap2.isEmpty()) {
                return;
            }

            if (!KeyUtils.checkCondition(15)) {
                GL11.glPushMatrix();
                GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
                double value = this.settingScale.getValue();
                GL11.glScaled(value, value, 1.0);
                ScaledResolution scaledResolution2 = new ScaledResolution(
                (double)minecraft.displayWidth * (1.0 / value), (double)minecraft.displayHeight * (1.0 / value), (int)value
                );
                FontRenderer fontRendererObj = minecraft.fontRendererObj;
                int index = scaledResolution2.getScaledWidth() / 2;
                String text = "Spectators:";
                int index2 = fontRendererObj.getIndex(text) + 4;
                int index3 = fontRendererObj.indexOffset + 2;

                for (String text2 : ((Iterable<String>) this.entryMap2.keySet())) {
                    index3 += 11;
                    int index4 = fontRendererObj.getIndex(text2) + 4;
                    if (index4 > index2) {
                        index2 = index4;
                    }
                }

                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                MovementHandler.processBoolean(this.settingScale2.canExecute(), this.settingScale2.canExecute2());
                int index5 = index3;
                int index6 = index2;
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyFontRenderer(index, index6, index5, fontRendererObj, text, renderPass));
                GL11.glPopMatrix();
                return;
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.updateState();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (minecraft.theWorld != null && minecraft.thePlayer != null) {
            long time2 = 0L;
            boolean enabled = false;
            boolean completed4 = false;
            boolean completed3 = false;
            boolean enabled2 = false;
            boolean completed2 = false;
            ArrayList arrayList2 = null;
            String[] textArray = null;
            int index = 0;
            int index2 = 0;
            String text = null;
            boolean enabled3 = false;
            if (TargetFilterMode.NORMAL.isSelected()) {
                if (packet2 instanceof S38PacketPlayerListItem) {
                    S38PacketPlayerListItem s38PacketPlayerListItem = (S38PacketPlayerListItem)packet2;
                    if (Objects.requireNonNull(s38PacketPlayerListItem.getStaffDetectorAddPlayerType()) == StaffDetectorAddPlayerType.UPDATE_LATENCY) {
                        this.pendingCheckTasks.add(((Runnable)(() -> this.handleS38PacketPlayerListItem(s38PacketPlayerListItem))));
                    }
                }
                if (TargetFilterMode.SIMPLE.isSelected()) {
                    enabled2 = packet2 instanceof S38PacketPlayerListItem;
                    completed2 = true;
                }
                if (!completed2) {
                    if (!TargetFilterMode.GOMME.isSelected()
                    || !(packet2 instanceof S3APacketTabComplete)
                    || minecraft.currentScreen instanceof GuiChat) {
                        completed3 = true;
                    }
                    if (!completed3) {
                        String[] stringArray = ((S3APacketTabComplete)packet2).getStringArray();
                        arrayList2 = new ArrayList();
                        textArray = stringArray;
                        index = stringArray.length;
                        index2 = 0;
                        if (0 >= index) {
                            completed3 = true;
                        }
                        if (!completed3) {
                            text = stringArray[index2];
                            enabled3 = text.equals(minecraft.session.getText1());
                        }
                    }
                }
            } else {
                if (TargetFilterMode.SIMPLE.isSelected()) {
                    enabled2 = packet2 instanceof S38PacketPlayerListItem;
                    completed2 = true;
                }
                if (!completed2) {
                    if (!TargetFilterMode.GOMME.isSelected()
                    || !(packet2 instanceof S3APacketTabComplete)
                    || minecraft.currentScreen instanceof GuiChat) {
                        completed3 = true;
                    }
                    if (!completed3) {
                        String[] stringArray2 = ((S3APacketTabComplete)packet2).getStringArray();
                        arrayList2 = new ArrayList();
                        textArray = stringArray2;
                        index = stringArray2.length;
                        index2 = 0;
                        if (0 >= index) {
                            completed3 = true;
                        }
                        if (!completed3) {
                            text = stringArray2[index2];
                            enabled3 = text.equals(minecraft.session.getText1());
                        }
                    }
                }
            }
            if (!completed3) {
                if (!completed2) {
                    boolean enabled4 = !enabled3;
                    Iterator iterator = minecraft.getNetHandlerPlayClient().getCollection().iterator();
                    if (iterator.hasNext()) {
                        NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)iterator.next();
                        while (true) {
                            boolean completed = false;
                            String name = networkPlayerInfo.getGameProfile().getName();
                            if (!name.equals(minecraft.thePlayer.getGameProfile().getName())) {
                                String text2 = null;
                                if (networkPlayerInfo.getChatComponent() != null) {
                                    text2 = networkPlayerInfo.getChatComponent().getString();
                                } else {
                                    text2 = ScorePlayerTeam.getText(networkPlayerInfo.getScorePlayerTeam(), networkPlayerInfo.getGameProfile().getName());
                                }
                                if (text2.contains(text)) {
                                    enabled4 = false;
                                    while (true) {
                                        if (enabled4) {
                                            arrayList2.add(text);
                                        }
                                        if (this.settingChatMessageGomme.isEnabled() || this.settingNotificationGomme.isEnabled()) {
                                            for (String text3 : (Iterable<String>) arrayList2) {
                                                if (!this.itemList3.contains(text3)) {
                                                    if (this.settingChatMessageGomme.isEnabled()) {
                                                        TargetFilterMode targetFilterMode = TargetFilterMode.GOMME;
                                                        this.applyTargetFilterMode(targetFilterMode, text3);
                                                    }

                                                    if (this.settingNotificationGomme.isEnabled()) {
                                                        TargetFilterMode targetFilterMode2 = TargetFilterMode.GOMME;
                                                        this.applyTargetFilterMode2(targetFilterMode2, text3);
                                                    }
                                                }
                                            }
                                        }
                                        this.itemList3.clear();
                                        this.itemList3.addAll(arrayList2);
                                        if (++index2 >= index) {
                                            { completed4 = true; break; }
                                        }
                                        if (!completed4) {
                                            text = textArray[index2];
                                            enabled4 = !text.equals(minecraft.session.getText1());
                                            iterator = minecraft.getNetHandlerPlayClient().getCollection().iterator();
                                            if (iterator.hasNext()) {
                                                { completed = true; break; }
                                            }
                                        }
                                    }
                                    if (completed4) break;
                                }
                            }
                            if (!completed4) {
                                if (!completed) {
                                    while (!iterator.hasNext()) {
                                        if (enabled4) {
                                            arrayList2.add(text);
                                        }
                                        if (this.settingChatMessageGomme.isEnabled() || this.settingNotificationGomme.isEnabled()) {
                                            for (String text4 : (Iterable<String>) arrayList2) {
                                                if (!this.itemList3.contains(text4)) {
                                                    if (this.settingChatMessageGomme.isEnabled()) {
                                                        TargetFilterMode targetFilterMode3 = TargetFilterMode.GOMME;
                                                        this.applyTargetFilterMode(targetFilterMode3, text4);
                                                    }

                                                    if (this.settingNotificationGomme.isEnabled()) {
                                                        TargetFilterMode targetFilterMode4 = TargetFilterMode.GOMME;
                                                        this.applyTargetFilterMode2(targetFilterMode4, text4);
                                                    }
                                                }
                                            }
                                        }
                                        this.itemList3.clear();
                                        this.itemList3.addAll(arrayList2);
                                        if (++index2 >= index) {
                                            { completed4 = true; break; }
                                        }
                                        if (!completed4) {
                                            text = textArray[index2];
                                            enabled4 = !text.equals(minecraft.session.getText1());
                                            iterator = minecraft.getNetHandlerPlayClient().getCollection().iterator();
                                        }
                                    }
                                    if (completed4) break;
                                }
                                if (!completed4) {
                                    networkPlayerInfo = (NetworkPlayerInfo)iterator.next();
                                }
                            }
                        }
                    } else {
                        boolean enabled5 = enabled4;
                        while (true) {
                            if (enabled5) {
                                arrayList2.add(text);
                            }
                            if (this.settingChatMessageGomme.isEnabled() || this.settingNotificationGomme.isEnabled()) {
                                for (String text5 : (Iterable<String>) arrayList2) {
                                    if (!this.itemList3.contains(text5)) {
                                        if (this.settingChatMessageGomme.isEnabled()) {
                                            TargetFilterMode targetFilterMode5 = TargetFilterMode.GOMME;
                                            this.applyTargetFilterMode(targetFilterMode5, text5);
                                        }

                                        if (this.settingNotificationGomme.isEnabled()) {
                                            TargetFilterMode targetFilterMode6 = TargetFilterMode.GOMME;
                                            this.applyTargetFilterMode2(targetFilterMode6, text5);
                                        }
                                    }
                                }
                            }
                            this.itemList3.clear();
                            this.itemList3.addAll(arrayList2);
                            if (++index2 >= index) {
                                { completed4 = true; break; }
                            }
                            if (!completed4) {
                                text = textArray[index2];
                                boolean enabled6 = !text.equals(minecraft.session.getText1());
                                for (NetworkPlayerInfo networkPlayerInfo2 : ((Iterable<NetworkPlayerInfo>) minecraft.getNetHandlerPlayClient().getCollection())) {
                                    String name2 = networkPlayerInfo2.getGameProfile().getName();
                                    if (!name2.equals(minecraft.thePlayer.getGameProfile().getName())) {
                                        String text6;
                                        if (networkPlayerInfo2.getChatComponent() != null) {
                                            text6 = networkPlayerInfo2.getChatComponent().getString();
                                        } else {
                                            text6 = ScorePlayerTeam.getText(networkPlayerInfo2.getScorePlayerTeam(), networkPlayerInfo2.getGameProfile().getName());
                                        }

                                        if (text6.contains(text)) {
                                            enabled6 = false;
                                            break;
                                        }
                                    }
                                }
                                enabled5 = enabled6;
                            }
                        }
                    }
                }
                if (!completed4) {
                    if (enabled2) {
                        S38PacketPlayerListItem s38PacketPlayerListItem2 = (S38PacketPlayerListItem)packet2;
                        if (s38PacketPlayerListItem2.getStaffDetectorAddPlayerType() == StaffDetectorAddPlayerType.UPDATE_LATENCY) {
                            List itemList = s38PacketPlayerListItem2.getItemList();
                            int index3 = itemList == null ? 0 : itemList.size();
                            NetHandlerPlayClient netHandlerPlayClient = minecraft.getNetHandlerPlayClient();
                            int index4 = netHandlerPlayClient == null ? 0 : netHandlerPlayClient.getCollection().size();
                            if (index3 != index4) {
                                if (!this.expanded2) {
                                    if (this.settingAcousticWarningSimple.isEnabled()) {
                                        this.updateState6();
                                    }

                                    if (this.settingChatMessageSimple.isEnabled()) {
                                        TargetFilterMode targetFilterMode7 = TargetFilterMode.SIMPLE;
                                        this.applyTargetFilterMode(targetFilterMode7, "unknown");
                                    }

                                    if (this.settingNotificationSimple.isEnabled()) {
                                        TargetFilterMode targetFilterMode8 = TargetFilterMode.SIMPLE;
                                        this.applyTargetFilterMode2(targetFilterMode8, "unknown");
                                    }
                                }

                                this.expanded2 = true;
                            } else {
                                this.expanded2 = false;
                            }
                        }
                    }
                    if (TargetFilterMode.GOMME.isSelected()
                    && packet2 instanceof S3APacketTabComplete
                    && !(minecraft.currentScreen instanceof GuiChat)) {
                        String[] stringArray3 = ((S3APacketTabComplete)packet2).getStringArray();
                        ArrayList arrayList = new ArrayList();

                        for (String text7 : stringArray3) {
                            boolean enabled7 = !text7.equals(minecraft.session.getText1());

                            for (NetworkPlayerInfo networkPlayerInfo3 : ((Iterable<NetworkPlayerInfo>) minecraft.getNetHandlerPlayClient().getCollection())) {
                                String name3 = networkPlayerInfo3.getGameProfile().getName();
                                if (!name3.equals(minecraft.thePlayer.getGameProfile().getName())) {
                                    String text8;
                                    if (networkPlayerInfo3.getChatComponent() != null) {
                                        text8 = networkPlayerInfo3.getChatComponent().getString();
                                    } else {
                                        text8 = ScorePlayerTeam.getText(networkPlayerInfo3.getScorePlayerTeam(), networkPlayerInfo3.getGameProfile().getName());
                                    }

                                    if (text8.contains(text7)) {
                                        enabled7 = false;
                                        break;
                                    }
                                }
                            }

                            if (enabled7) {
                                arrayList.add(text7);
                            }

                            if (this.settingChatMessageGomme.isEnabled() || this.settingNotificationGomme.isEnabled()) {
                                for (String text9 : (Iterable<String>) arrayList) {
                                    if (!this.itemList3.contains(text9)) {
                                        if (this.settingChatMessageGomme.isEnabled()) {
                                            TargetFilterMode targetFilterMode9 = TargetFilterMode.GOMME;
                                            this.applyTargetFilterMode(targetFilterMode9, text9);
                                        }

                                        if (this.settingNotificationGomme.isEnabled()) {
                                            TargetFilterMode targetFilterMode10 = TargetFilterMode.GOMME;
                                            this.applyTargetFilterMode2(targetFilterMode10, text9);
                                        }
                                    }
                                }
                            }

                            this.itemList3.clear();
                            this.itemList3.addAll(arrayList);
                        }
                    }
                    completed4 = true;
                }
            }
            if (!completed4) {
                boolean enabled8 = TargetFilterMode.OTHER.isSelected();
                if (!enabled8) {
                    return;
                }
                time2 = System.currentTimeMillis();
                enabled = packet2 instanceof S1DPacketEntityEffect;
            } else {
                if (!TargetFilterMode.OTHER.isSelected()) {
                    return;
                }

                time2 = System.currentTimeMillis();
                enabled = packet2 instanceof S1DPacketEntityEffect;
            }

            if (enabled && minecraft.theWorld.getEntityByID(((S1DPacketEntityEffect)packet2).getIndex()) == null
            || packet2 instanceof S14PacketEntity && minecraft.theWorld.getEntityByID(((S14PacketEntity)packet2).getIndex()) == null
            || packet2 instanceof S18PacketEntityTeleport && minecraft.theWorld.getEntityByID(((S18PacketEntityTeleport)packet2).getIndex()) == null) {
                long time3;
                if (((time3 = this.getTimestamp()) == 0L ? 0 : (time3 < 0L ? -1 : 1)) == 0) {
                    if (this.settingAcousticWarningOther.isEnabled()) {
                        this.updateState6();
                    }

                    if (this.settingChatMessageOther.isEnabled()) {
                        TargetFilterMode targetFilterMode11 = TargetFilterMode.OTHER;
                        this.applyTargetFilterMode(targetFilterMode11, "unknown");
                    }

                    if (this.settingNotificationOther.isEnabled()) {
                        TargetFilterMode targetFilterMode12 = TargetFilterMode.OTHER;
                        this.applyTargetFilterMode2(targetFilterMode12, "unknown");
                    }
                }

                this.updateState2(time2);
            }

            long time4;
            if ((byte)((time4 = time2 - this.getTimestamp() - 30000L) == 0L ? 0 : (time4 < 0L ? -1 : 1)) > 0) {
                this.updateState2(0L);
            }
        }
    }

    @Override
    public void onRunTick() {
        this.updateState5();
        if (TargetFilterMode.GOMME.isSelected()) {
            TimeHelper timer2 = this.timer;
            long time = (long)this.settingCheckDelay.getIndexOffset2();
            if (timer2.hasReached(time) && !(minecraft.currentScreen instanceof GuiChat)) {
                this.settingCheckDelay.recalculate();
                this.timer.reset();
                this.updateState3();
                Iterator iterator = minecraft.getNetHandlerPlayClient().getCollection().iterator();

                while (iterator.hasNext()) {
                    String name = ((NetworkPlayerInfo)iterator.next()).getGameProfile().getName();
                    this.entryMap.remove(name);
                    HashMap entryMap3 = this.entryMap;
                    Long time2 = System.currentTimeMillis();
                    entryMap3.put(name, time2);
                }

                LinkedList linkedList = new LinkedList();
                Iterator iterator2 = this.entryMap.keySet().iterator();

                while (iterator2.hasNext()) {
                    Object object = iterator2.next();
                    String text = (String)object;
                    Long time3 = (Long)this.entryMap.get(text);
                    if (time3 != null) {
                        long time4 = time3 + 15000L;
                        long time5 = System.currentTimeMillis();
                        if ((time4 > time5 ? 1 : (time4 == time5 ? 0 : -1)) >= 0 || this.itemList3.contains(text)) {
                            continue;
                        }
                    }

                    linkedList.add(text);
                }

                Iterator iterator3 = linkedList.iterator();

                while (iterator3.hasNext()) {
                    Object object2 = iterator3.next();
                    String text2 = (String)object2;
                    this.entryMap.remove(text2);
                }

                Iterator iterator4 = this.itemList3.iterator();

                while (iterator4.hasNext()) {
                    String text3 = (String)iterator4.next();
                    if (this.entryMap.get(text3) == null) {
                        if (this.settingAcousticWarningGomme.isEnabled()) {
                            this.updateState6();
                        }

                        this.entrySet.add(text3);
                        HashMap entryMap4 = this.entryMap;
                        Long time6 = System.currentTimeMillis();
                        entryMap4.put(text3, time6);
                    }
                }
            }
        }

        if (TargetFilterMode.LIST_BASED.isSelected() && !this.entrySet3.isEmpty()) {
            Iterator iterator5 = minecraft.getNetHandlerPlayClient().getCollection().iterator();

            while (iterator5.hasNext()) {
                NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)iterator5.next();
                if (networkPlayerInfo != null && networkPlayerInfo.getGameProfile() != null) {
                    String name2 = networkPlayerInfo.getGameProfile().getName();
                    this.applyString6(name2);
                }
            }

            Iterator iterator6 = minecraft.theWorld.loadedEntityList.iterator();

            while (iterator6.hasNext()) {
                Entity entity = (Entity)iterator6.next();
                if (entity instanceof EntityPlayer) {
                    String name3 = ((EntityPlayer)entity).getGameProfile().getName();
                    this.applyString6(name3);
                }
            }
        }

        if (TargetFilterMode.NORMAL.isSelected() && !this.pendingCheckTasks.isEmpty() && (this.completableFuture == null || !this.completableFuture.isDone())) {
            this.completableFuture = CompletableFuture.runAsync((Runnable)this.pendingCheckTasks.get(0));
            this.pendingCheckTasks.remove(0);
        }

        this.processAction5();
    }

    private void applyTargetFilterMode(TargetFilterMode targetFilterMode, String text) {
        RayTraceUtil.applyString("§4§lStaff detected (" + targetFilterMode.getText() + "):§6 " + text);
    }

    private static String getString3(String text) {
        String text2 = text;
        if (text.contains(" ")) {
            text2 = StringUtils.replace(text, " ", "");
        }

        return text2;
    }

    private void updateState() {
        this.itemList3.clear();
        this.entrySet.clear();
        this.entrySet2.clear();
        this.updateState2(0L);
        this.entrySet4.clear();
    }

    private void onListsAction() {
        File file = new File(displayName2);
        ConfigManager.applyString2(displayName2);
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            File file2 = Arrays.stream(fileArray).filter(file3 -> this.checkFile(file3)).findFirst().orElse(null);
            if (file2 != null) {
                String absolutePath = file2.getAbsolutePath();
                ArrayList arrayList = new ArrayList(ConfigManager.getList(absolutePath));
                arrayList.replaceAll(object -> getString3((String)object));
                this.entrySet3.clear();
                this.entrySet3.addAll(arrayList);
                String text = "Loaded " + arrayList.size() + " staff names!";
                RayTraceUtil.applyString(text);
            }
        }
    }

    private void applyString(String text) {
        if (!this.entrySet4.contains(text)) {
            this.entrySet4.add(text);
            if (this.settingAcousticWarningNormal.isEnabled()) {
                this.updateState6();
            }

            if (this.settingChatMessageNormal.isEnabled()) {
                this.applyTargetFilterMode(TargetFilterMode.NORMAL, text);
            }

            if (this.settingNotificationNormal.isEnabled()) {
                this.applyTargetFilterMode2(TargetFilterMode.NORMAL, text);
            }
        }
    }

    private void applyFontRenderer(int x, int y, int z, FontRenderer fontRenderer, String text, RenderPass renderPass) {
        byte state = 1;
        if (renderPass == RenderPass.SHADOW) {
            int index = x - y / 2;
            int index2 = x + y / 2;
            int index3 = state + z;
            int rGB = Color.black.getRGB();
            RenderUtils.processAction(index, 0, index2, index3, this.settingScale2.getInt3(rGB));
        } else {
            RenderUtils.processAction(x - y / 2, 0, x + y / 2, state + z, 0, 0, 0, 150);
        }

        RenderUtils.processAction(x - y / 2, 11, x + y / 2, 12, 0, 0, 0);
        if (renderPass == RenderPass.NONE) {
            int index4 = x - fontRenderer.getIndex(text) / 2;
            Client client = Client.getClient();
            fontRenderer.getIndex(text, index4, state, client.getColor().getRGB());
            byte state2 = 13;

            for (String text2 : ((Iterable<String>) this.entryMap2.keySet())) {
                Boolean booleanVal = (Boolean)this.entryMap2.get(text2);
                if (booleanVal != null) {
                    Color color = booleanVal ? Color.RED : Color.LIGHT_GRAY;
                    fontRenderer.getIndex(text2, x - fontRenderer.getIndex(text2) / 2, state2, color.getRGB());
                    state2 = (byte)(state2 + 11);
                }
            }
        }
    }

    private synchronized void updateState2(long timestampMillis) {
        this.timestampMillis = timestampMillis;
    }

    private void processAction() {
        File file = new File(displayName2);
        ConfigManager.applyString2(displayName2);
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            List list = Arrays.stream(fileArray).filter(file2 -> checkFile2(file2)).map(file3 -> getText((File)file3)).collect(Collectors.toList());
            boolean enabled = list.contains(this.settingLists.getSelected());
            String[] textArray = ((String[])(list.toArray(new String[0])));
            if (!enabled) {
                String text = textArray[0];
                this.settingLists.setSelected(text);
            }

            this.settingLists.applyStringArray2(textArray);
            this.onListsAction();
        }
    }

    private boolean isNotificationNormalVisible() {
        return !this.settingNormalModeSettings.isVisible() ? false : this.checkCondition();
    }

    private void updateState3() {
        BlockPos blockPos = null;
        if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            blockPos = minecraft.objectMouseOver.getBlockPos();
        }

        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C14PacketTabComplete("/tp ", blockPos));
    }

    private synchronized long getTimestamp() {
        return this.timestampMillis;
    }

    private static boolean checkLinkedList(LinkedList linkedList, String text) {
        return !linkedList.contains(text);
    }

    private void applyTargetFilterMode2(TargetFilterMode targetFilterMode, String text) {
        if (Client.getClient().getModuleManager().notifications.isToggled()) {
            if (BeverageNotifyModuleTogglesType.VANISH_NOTIFICATION.isSelected()) {
                String text2 = "§4§lStaff detected (" + targetFilterMode.getText() + "):§6 " + text;
                BeverageNotifyInfoType beverageNotifyInfoTypeKind = BeverageNotifyInfoType.INFO;
                BeverageItemNotification.updateText("VanishNotify", text2, beverageNotifyInfoTypeKind);
            }
        }
    }

    private void updateState4() {
        ConfigManager.applyString(
        "assets/augustus/stafflists/BlocksMCDefault.txt",
        Client.getClient().getString4().toLowerCase() + "/stafflists",
        
        true
        );
        ConfigManager.applyString(
        "assets/augustus/stafflists/HypixelDefault.txt",
        Client.getClient().getString4().toLowerCase() + "/stafflists",
        
        true
        );
        ConfigManager.applyString(
        "assets/augustus/stafflists/GamsterDefault.txt",
        Client.getClient().getString4().toLowerCase() + "/stafflists",
        
        true
        );
        ConfigManager.applyString(
        "assets/augustus/stafflists/GommeHDDefault.txt",
        Client.getClient().getString4().toLowerCase() + "/stafflists",
        
        true
        );
        ConfigManager.applyString(
        "assets/augustus/stafflists/PikaDefault.txt",
        Client.getClient().getString4().toLowerCase() + "/stafflists",
        
        true
        );
        this.processAction();
    }

    private void applyString6(String text) {
        if (this.entrySet3.contains(text)) {
            if (!this.entrySet2.contains(text)) {
                if (this.settingAcousticWarningListBased.isEnabled()) {
                    this.updateState6();
                }

                if (this.settingChatMessageListBased.isEnabled()) {
                    this.applyTargetFilterMode(TargetFilterMode.LIST_BASED, text);
                }

                if (this.settingNotificationListBased.isEnabled()) {
                    this.applyTargetFilterMode2(TargetFilterMode.LIST_BASED, text);
                }

                this.entrySet2.add(text);
            }
        }
    }

    private void processAction5() {
        if (!TargetFilterMode.LIST_BASED.isSelected()) {
            this.entrySet2.clear();
        }

        if (!TargetFilterMode.GOMME.isSelected()) {
            this.itemList3.clear();
            this.entrySet.clear();
        }

        if (!TargetFilterMode.OTHER.isSelected()) {
            this.updateState2(0L);
        }

        if (!TargetFilterMode.NORMAL.isSelected()) {
            this.entrySet4.clear();
        }
    }

    private boolean checkFile(File file) {
        String name = file.getName();
        StringBuilder stringBuilder = new StringBuilder();
        return name.equals(stringBuilder.append(this.settingLists.getSelected()).append(".txt").toString());
    }

    private boolean isNotificationListBasedVisible() {
        return !this.settingListBasedModeSettings.isVisible() ? false : this.checkCondition();
    }

    private void updateState5() {
        this.entryMap2.clear();
        if (TargetFilterMode.GOMME.isSelected() && this.settingShowInSpectatorListGomme.isEnabled()) {
            for (String text : (Iterable<String>) this.itemList3) {
                this.entryMap2.put(text, this.entrySet.contains(text));
            }
        }

        long time2;
        if (TargetFilterMode.OTHER.isSelected()
        && this.settingShowInSpectatorListOther.isEnabled()
        && ((time2 = this.getTimestamp()) == 0L ? 0 : (time2 < 0L ? -1 : 1)) != 0) {
            this.entryMap2.put("Unknown Staff", true);
        }

        if (TargetFilterMode.SIMPLE.isSelected() && this.settingShowInSpectatorListSimple.isEnabled() && this.expanded2) {
            this.entryMap2.put("Unknown Staff", true);
        }

        if (TargetFilterMode.LIST_BASED.isSelected() && this.settingShowInSpectatorListListBased.isEnabled()) {
            for (String text2 : (Iterable<String>) this.entrySet2) {
                this.entryMap2.put(text2, true);
            }
        }

        if (TargetFilterMode.NORMAL.isSelected()) {
            if (this.settingShowInSpectatorListNormal.isEnabled()) {
                for (String text3 : (Iterable<String>) this.entrySet4) {
                    this.entryMap2.put(text3, true);
                }
            }
        }
    }

    private static String getText(File file) {
        return file.getName().substring(0, Math.max(0, file.getName().length() - 4));
    }

    private static boolean checkFile2(File file) {
        return file == null ? false : file.getName().endsWith(".txt");
    }

    private void updateState6() {
        minecraft.getMcSoundHandler()
        .applyISound2(
        PositionedSoundRecord.createPositionedSound(
        SoundPlayer.resourceLocation, (float)(this.settingAcousticWaringVolume.getValue() / 100.0)
        )
        );
    }

    private void handleS38PacketPlayerListItem(S38PacketPlayerListItem s38PacketPlayerListItem) {
        LinkedList linkedList = new LinkedList();

        for (LatencyManager latencyManager : ((Iterable<LatencyManager>) s38PacketPlayerListItem.getItemList())) {
            if (!minecraft.getNetHandlerPlayClient().getEntryMap().containsKey(latencyManager.getGameProfile().getId())) {
                UUID id = latencyManager.getGameProfile().getId();

                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(
                    stringBuilder.append("https://sessionserver.mojang.com/session/minecraft/profile/").append(id).toString()
                    )
                    .openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(20000);
                    httpURLConnection.connect();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder2 = new StringBuilder();

                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        stringBuilder2.append(text);
                    }

                    httpURLConnection.disconnect();
                    IOUtils.closeQuietly((Reader)bufferedReader);
                    String[] textArray = stringBuilder2.toString().split("\"");
                    String text2 = textArray[7];
                    this.applyString(text2);
                    linkedList.add(textArray[7]);
                } catch (Exception e) {
                    if (!this.settingExcludeRawUUIDS.isEnabled()) {
                        String text3 = id.toString();
                        this.applyString(text3);
                        linkedList.add(text3);
                    }
                }
            }
        }

        this.entrySet4.removeIf(object -> checkLinkedList(linkedList, (String)object));
    }

    private boolean isNotificationSimpleVisible() {
        return !this.settingSimpleModeSettings.isVisible() ? false : this.checkCondition();
    }

    private boolean checkCondition() {
        return !ClientGlobals.moduleManager.oldNotification.isToggled()
        ? false
        : BeverageNotifyModuleTogglesType.VANISH_NOTIFICATION.isSelected();
    }

    private boolean isNotificationGommeVisible() {
        return !this.settingOldGommeModeSettings.isVisible() ? false : this.checkCondition();
    }

    public boolean canExecute5() {
        for (String text : ((Iterable<String>) this.entryMap2.keySet())) {
            Boolean booleanVal = (Boolean)this.entryMap2.get(text);
            if (booleanVal != null && booleanVal) {
                return true;
            }
        }

        return false;
    }

    private boolean isScaleVisible() {
        if (this.settingShowInSpectatorListNormal.isEnabled() && this.settingShowInSpectatorListNormal.isVisible()) {
            return true;
        } else if (this.settingShowInSpectatorListGomme.isEnabled() && this.settingShowInSpectatorListGomme.isVisible()) {
            return true;
        } else if (this.settingShowInSpectatorListOther.isEnabled() && this.settingShowInSpectatorListOther.isVisible()) {
            return true;
        } else {
            return !this.settingShowInSpectatorListListBased.isEnabled() ? false : this.settingShowInSpectatorListListBased.isVisible();
        }
    }

    private boolean isNotificationOtherVisible() {
        return !this.settingOtherModeSettings.isVisible() ? false : this.checkCondition();
    }

    private boolean isScaleVisible2() {
        if (this.settingShowInSpectatorListNormal.isEnabled() && this.settingShowInSpectatorListNormal.isVisible()) {
            return true;
        } else if (this.settingShowInSpectatorListGomme.isEnabled() && this.settingShowInSpectatorListGomme.isVisible()) {
            return true;
        } else if (this.settingShowInSpectatorListOther.isEnabled() && this.settingShowInSpectatorListOther.isVisible()) {
            return true;
        } else {
            return !this.settingShowInSpectatorListListBased.isEnabled() ? false : this.settingShowInSpectatorListListBased.isVisible();
        }
    }

    private boolean isAcousticWaringVolumeVisible() {
        if (this.settingAcousticWarningNormal.isEnabled() && this.settingAcousticWarningNormal.isVisible()) {
            return true;
        } else if (this.settingAcousticWarningGomme.isEnabled() && this.settingAcousticWarningGomme.isVisible()) {
            return true;
        } else if (this.settingAcousticWarningOther.isEnabled() && this.settingAcousticWarningOther.isVisible()) {
            return true;
        } else {
            return !this.settingAcousticWarningListBased.isEnabled() ? false : this.settingAcousticWarningListBased.isVisible();
        }
    }
}
