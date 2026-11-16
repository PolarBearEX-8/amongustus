package augustus.modules.misc;

import augustus.modules.combat.KillAura;
import augustus.modules.combat.MoreKB;
import augustus.modules.world.Scaffold;
import client.Client;
import client.ClientGlobals;
import client.config.ClientNetworkConfig;
import client.event.EventBusManager;
import client.event.EventCommand;
import client.event.EventCommandCallback;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.network.CosmeticsPayload;
import client.render.RenderUtils;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsChristmasType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.AutoReportCache;
import client.util.BooleanSupplier;
import client.util.ClickEventActionManager;
import client.util.ClientBrandHelper;
import client.util.HolidayHelper;
import client.util.HoverEventActionManager;
import client.util.Packet;
import client.util.ProfileConfigManager;
import client.util.SoundPlayer;
import client.util.StyleHasParentManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientSettings extends Module implements TickListener, RenderListener, PacketListener, EventCommandCallback {
    public SettingGroup settingGuiButtons;
    public BooleanSetting settingRoundedButtons;
    public NumberSetting settingRoundFactor;
    public SettingGroup settingSounds;
    public ModeSetting settingMode;
    public BooleanSetting settingModuleToggleSound;
    public BooleanSetting settingHideMainMenuAccountInfos;
    public SettingGroup settingConnectionInfo;
    public NumberSetting settingScale;
    public SettingGroup settingCommandStuff;
    public ModeSetting settingCommandPrefixes;
    public SettingGroup settingConfigSettings;
    public BooleanSetting settingLoadKeysFromConfigs;
    public SettingGroup settingChatSettings;
    public BooleanSetting settingChatCopySymbol;
    public BooleanSetting settingFastServerPining1;
    public ModeSetting settingBanStatistics;
    public BooleanSetting settingRemoveFramerateLimitInScreens;
    public SettingGroup settingBackgroundSettings;
    public BooleanSetting settingCustomBackground;
    public BooleanSetting settingMouseInputsOnBackgrounds;
    public BooleanSetting settingTryFixingRendermodulesWithOptifineShaders;
    public BooleanSetting settingTryFixingNametagsWithOptifineShaders;
    public BooleanSetting settingSideMainMenu;
    public TimeHelper timer;
    public static TimeHelper timer2 = new TimeHelper();
    public long timestampMillis;
    public long timestampMillis1;
    public static volatile int tickDelay = 2;
    public static int keyCode2;
    public static int[] intValueArray;

    public ClientSettings() {
        super("ClientSettings", "KundenEinstellungen", true, new Color(158, 168, 50), Category.MISC);
        this.settingGuiButtons = new SettingGroup(this, "GuiButtons", true);
        this.settingRoundedButtons = new BooleanSetting(this, "RoundedButtons", true, this.settingGuiButtons);
        this.settingRoundFactor = new NumberSetting(this, "RoundFactor", 2.0, 1.0, 20.0, 0, this.settingGuiButtons).getNumberSetting(() -> this.settingRoundedButtons.isEnabled());
        this.settingSounds = new SettingGroup(this, "Sounds", true);
        String[] textArray = new String[3];
        textArray[0] = "Vanilla";
        textArray[1] = "Sigma";
        textArray[2] = "Piano";
        this.settingMode = new ModeSetting(this, "Mode", "Sigma", textArray, this.settingSounds);
        this.settingModuleToggleSound = new BooleanSetting(this, "ModuleToggleSound", true, this.settingSounds);
        this.settingHideMainMenuAccountInfos = new BooleanSetting(this, "HideMainMenuAccountInfos", false);
        this.settingConnectionInfo = new SettingGroup(this, "ConnectionInfo", true);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2, this.settingConnectionInfo);
        this.settingCommandStuff = new SettingGroup(this, "CommandStuff", true);
        String[] textArray2 = new String[12];
        textArray2[0] = ".";
        textArray2[1] = "-";
        textArray2[2] = "_";
        textArray2[3] = ":";
        textArray2[4] = ",";
        textArray2[5] = "+";
        textArray2[6] = "!";
        textArray2[7] = "#";
        textArray2[8] = "?";
        textArray2[9] = "\\";
        textArray2[10] = ">";
        textArray2[11] = "<";
        this.settingCommandPrefixes = new ModeSetting(this, "CommandPrefixes", ".", textArray2, this.settingCommandStuff);
        this.settingConfigSettings = new SettingGroup(this, "ConfigSettings", true);
        this.settingLoadKeysFromConfigs = new BooleanSetting(this, "LoadKeysFromConfigs", false, this.settingConfigSettings);
        this.settingChatSettings = new SettingGroup(this, "ChatSettings", true);
        this.settingChatCopySymbol = new BooleanSetting(this, "ChatCopySymbol", false, this.settingChatSettings);
        this.settingFastServerPining1 = (BooleanSetting)new BooleanSetting(this, "FastServerPining1", false).getSetting(() -> canExecute6());
        String[] textArray3 = new String[0];
        this.settingBanStatistics = new ModeSetting(this, "BanStatistics", "Icon", textArray3).getModeSetting(() -> canExecute5());
        this.settingRemoveFramerateLimitInScreens = new BooleanSetting(this, "RemoveFramerateLimitInScreens", true);
        this.settingBackgroundSettings = new SettingGroup(this, "BackgroundSettings", true);
        this.settingCustomBackground = new BooleanSetting(this, "CustomBackground", true, this.settingBackgroundSettings);
        this.settingMouseInputsOnBackgrounds = new BooleanSetting(this, "MouseInputsOnBackgrounds", false, this.settingBackgroundSettings);
        this.settingTryFixingRendermodulesWithOptifineShaders = new BooleanSetting(this, "Try fixing rendermodules with optifine shaders", true);
        this.settingTryFixingNametagsWithOptifineShaders = new BooleanSetting(this, "Try fixing nametags with optifine shaders", true);
        this.settingSideMainMenu = new BooleanSetting(this, "SideMainMenu", true);
        this.timer = new TimeHelper();
        this.timestampMillis = SettingContainer.settingProvider.getLong(220000L, 400000L);
        this.timestampMillis1 = SettingContainer.settingProvider.getLong(800000L, 1000000L);
        this.setActive(true);
        EventBusManager.getDietrichEvents2().subscribe(40, this);
        EventBusManager.getDietrichEvents2().subscribe(32, this);
        EventBusManager.getDietrichEvents2().subscribe(26, this);
        EventBusManager.getDietrichEvents2().subscribe(5, this);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onCommand(EventCommand event) {
        String text = event.getText();
        String text2 = text.toLowerCase(Locale.ROOT);
        if (text2.startsWith("/report")) {
            String[] textArray = text.split(" ");
            if (textArray.length > 1) {
                if (AutoReportCache.entryMap2.containsKey(textArray[1])) {
                    ClientGlobals.moduleManager.shuffleMode.setActive(true);
                    event.cancel();
                }
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C01PacketChatMessage) {
            Client.getClient().getClass();
            String text = ((C01PacketChatMessage)packet2).getText();
            String text2 = text.toLowerCase();
            if (text2.contains("/intave record") || text2.contains("/sampler start")) {
                CosmeticsPayload.processAction(1);
            }

            if (text2.contains("/intave") || text2.contains("/sampler") || text2.contains("/polar") || text2.contains("/verbose") || text2.contains("/alerts")) {
                StringBuilder stringBuilder = new StringBuilder();
                Client client = Client.getClient();
                StringBuilder sb = stringBuilder.append(client.getString7()).append(" tE=");
                int index;
                if (minecraft.thePlayer == null) {
                    index = -1;
                } else {
                    index = minecraft.thePlayer.ticksExisted;
                }

                StringBuilder sb2 = sb.append(index).append(" aura=");
                KillAura killAura = ClientGlobals.moduleManager.killAura;
                StringBuilder sb3 = sb2.append(killAura.isToggled()).append(" scaffold=");
                Scaffold scaffold = ClientGlobals.moduleManager.scaffold;
                StringBuilder sb4 = sb3.append(scaffold.isToggled()).append(" auramode=");
                StringBuilder sb5 = sb4.append(ClientGlobals.moduleManager.killAura.settingOnlyRotation.getSelected()).append(" morekb=");
                MoreKB moreKB = ClientGlobals.moduleManager.moreKB;
                String text3 = sb5.append(moreKB.isToggled()).append(" Message=").append(text).toString();
                CompletableFuture.runAsync(() -> applyString(text3));
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        setActive4(false);
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S02PacketChat) {
            Client.getClient().getClass();
            IChatComponent chatComponent = ((S02PacketChat)packet2).getChatComponent();
            if (chatComponent == null) {
                return;
            }

            String text6 = chatComponent.getString();
            String text = text6.toLowerCase();
            String text7 = Client.getClient().getString7();
            if (text7 == null) {
                text7 = "";
            }

            boolean enabled = false;
            if (text.contains("sampling request sent") || text.contains("started recording for type: ") || text.contains("recording enabled for")) {
                enabled = true;
            }

            if (enabled
                || text.contains("polar")
                || text.contains("sampler")
                || text.contains("intave") && !text.contains("intave12")
                || text.contains("given resistance (id 11)")
                || text.contains("mitigated")
                || text.contains("clickpatterns")
                || text.contains("javadouble")
                || text.contains("receiving fine violation")
                || text.contains("combat modifier debug")
                || text.contains("transmitting and recording")
                || text.contains("remote status (sent from cloud)")
                || text.contains(" version: c-")
                || text.contains(" detection: ")
                || text.contains(" shard master is connected")
                || text.contains("recording disabled for")
                || text.contains("micro-lagging")
                || text.contains("component: ")
                || text.contains("heuristic") && (text.contains("check") || text.contains("vl") || text.contains("type"))
                || text.contains("likelihood")
                || text.contains("correlation")
                || text.contains("issued") && text.contains("on")
                || text.contains("exhibits")
                || text.contains("output of killaura")
                || text.contains("output of scaffold")
                || text.contains("history-based cloud detection")
                || text.contains("[ac]")
                || text.contains("/§agreen§7")
                || text.contains("/§6orange§7")
                || text.contains("suspicious")
                || text7.equals("Tom")
                || Client.getClient().getString7().equals("karuz")) {
                ModuleManager moduleManager = Client.getClient().getModuleManager();
                StringBuilder stringBuilder = new StringBuilder();
                Client client = Client.getClient();
                StringBuilder sb = stringBuilder.append(client.getString7()).append(" tE=");
                int index;
                if (minecraft.thePlayer == null) {
                    index = -1;
                } else {
                    index = minecraft.thePlayer.ticksExisted;
                }

                StringBuilder sb2 = sb.append(index).append(" aura=");
                KillAura killAura = moduleManager.killAura;
                StringBuilder sb3 = sb2.append(killAura.isToggled()).append(" scaffold=");
                Scaffold scaffold = moduleManager.scaffold;
                StringBuilder sb4 = sb3.append(scaffold.isToggled()).append(" auramode=");
                StringBuilder sb5 = sb4.append(moduleManager.killAura.settingOnlyRotation.getSelected()).append(" morekb=");
                MoreKB moreKB = moduleManager.moreKB;
                StringBuilder sb6 = sb5.append(moreKB.isToggled());
                String text2;
                if (moduleManager.killAura.isToggled() && moduleManager.killAura.settingOnlyRotation.getSelected().equals("Noise2")) {
                    StringBuilder sb7 = new StringBuilder().append(" speed=");
                    StringBuilder sb17 = sb7.append(moduleManager.killAura.settingAimSpeed.getValue()).append(" mindelay=").append(moduleManager.killAura.settingReactionTime.getDouble3()).append(" maxdelay=").append(moduleManager.killAura.settingReactionTime.getDouble6()).append(" attention=").append(moduleManager.killAura.settingAttention.getValue()).append(" supercorrectionprob=").append(moduleManager.killAura.settingSuperCorrectionProbability.getValue()).append(" supercorrectiondelaymin=").append(moduleManager.killAura.settingSuperCorrectionDelay.getDouble9()).append(" supercorrectiondelaymax=").append(moduleManager.killAura.settingSuperCorrectionDelay.getDouble2()).append(" additionalAccuracy=").append(moduleManager.killAura.settingAdditionalAccuracy.isEnabled()).append(" additionalInaccuracy=").append(moduleManager.killAura.settingAdditionalInaccuracy.getValue()).append(" decelerationFactorPre=").append(moduleManager.killAura.settingDecelerationfactorPre.getValue()).append(" decelerationFactorPost=");
                    text2 = sb17.append(moduleManager.killAura.settingDecelerationfactorPost.getValue()).toString();
                } else {
                    text2 = "";
                }

                StringBuilder sb18 = sb6.append(text2);
                String text3;
                if (!moduleManager.killAura.isToggled()) {
                    text3 = "";
                } else {
                    StringBuilder sb20 = new StringBuilder().append(" fixmode=").append(moduleManager.killAura.settingNone4.getSelected()).append(" target=");
                    String text4;
                    if (moduleManager.killAura.target == null) {
                        text4 = "null";
                    } else {
                        text4 = moduleManager.killAura.target.getText();
                    }

                    text3 = sb20.append(text4).toString();
                }

                String text5 = sb18.append(text3).append(" ").append(text6).toString();
                CompletableFuture.runAsync(() -> applyString6(text5));
            }
        }

        if (packet2 instanceof S02PacketChat) {
            if (this.settingChatCopySymbol.isEnabled()) {
                S02PacketChat s02PacketChat = (S02PacketChat)packet2;
                if (s02PacketChat.getByteIndex() == 2) {
                    return;
                }

                String text8 = ClientBrandHelper.getString(s02PacketChat.getChatComponent().getString());
                if (!text8.replace(" ", "").isEmpty()) {
                    ChatComponentText stringEntry = new ChatComponentText(ClientSettingsBlackType.DARK_GRAY + Character.toString((char)Integer.parseInt("270D", 16)));
                    StyleHasParentManager styleHasParentManager = new StyleHasParentManager()
                        .getStyleHasParentManager(
                            new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText(ClientSettingsBlackType.GRAY + "Copy message"))
                        )
                        .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.SUGGEST_COMMAND, text8));
                    stringEntry.getIChat(styleHasParentManager);
                    s02PacketChat.getChatComponent().appendChatComponent(new ChatComponentText(ClientSettingsBlackType.RESET + " "));
                    s02PacketChat.getChatComponent().appendChatComponent(stringEntry);
                }
            }
        }
    }

    @Override
    public void onClientTick() {
        keyCode2++;
    }

    @Override
    public void onTick() {
        EntityRenderer.state = this.settingTryFixingRendermodulesWithOptifineShaders.isEnabled();
        EntityRenderer.state1 = this.settingTryFixingNametagsWithOptifineShaders.isEnabled();
        ClientSettingsChristmasType clientSettingsChristmasTypeKind = HolidayHelper.getClientSettingsChristmasType();
        if (clientSettingsChristmasTypeKind == ClientSettingsChristmasType.HALLOWEEN && minecraft.thePlayer != null && this.timer.hasReached(this.timestampMillis)) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation9);
            this.timestampMillis = SettingContainer.settingProvider.getLong(220000L, 500000L);
            this.timer.reset();
        }

        ClientSettingsChristmasType clientSettingsChristmasTypeKind2 = HolidayHelper.getClientSettingsChristmasType();
        ClientSettingsChristmasType clientSettingsChristmasTypeKind3 = ClientSettingsChristmasType.FIRST_APRIL;
        if (clientSettingsChristmasTypeKind2 == clientSettingsChristmasTypeKind3 && minecraft.thePlayer != null) {
            if (!this.timer.hasReached(this.timestampMillis1)) {
                return;
            }

            SoundPlayer.processAction(SoundPlayer.resourceLocation4);
            this.timestampMillis1 = SettingContainer.settingProvider.getLong(800000L, 1000000L);
            this.timer.reset();
        }
    }

    public static int[] getIntValueArray() {
        return intValueArray;
    }

    private static void applyString(String text) {
        new ProfileConfigManager().applyString7(text);
    }

    public static void updateState() {
        String selected = Client.getClient().getModuleManager().clientSettings.settingMode.getSelected();
        switch (selected) {
            case "Vanilla":
                minecraft.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(SoundPlayer.resourceLocation3, 1.0F));
                break;
            case "Piano":
                ResourceLocation resourceLocation11 = SoundPlayer.resourceLocation11;
                SoundPlayer.processAction(resourceLocation11);
                break;
            case "Sigma":
                ResourceLocation resourceLocation15 = SoundPlayer.resourceLocation15;
                SoundPlayer.processAction(resourceLocation15);
        }
    }

    private static void applyString6(String text) {
        new ProfileConfigManager().applyString7(text);
    }

    public static void processAction(int index) {
        tickDelay = index;
    }

    private static boolean canExecute5() {
        return false;
    }

    public static void setActive4(boolean enabled) {
        GL11.glPushMatrix();
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        if (!enabled) {
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        }

        String text = tickDelay == 0
            ? "Lost connection to Augustus server (client closes in " + Math.max(0, 100 - keyCode2 / 20) + " seconds)"
            : (
                tickDelay == 1
                    ? "Reconnecting to Augustus server (client closes in " + Math.max(0, 100 - keyCode2 / 20) + " seconds)"
                    : "Connected successfully to Augustus server"
            );
        int index;
        if (tickDelay == 0) {
            index = Color.red.getRGB();
        } else if (tickDelay == 1) {
            index = Color.orange.getRGB();
        } else {
            index = Color.green.getRGB();
        }

        ScaledResolution scaledResolution2 = enabled
            ? new ScaledResolution(minecraft)
            : new ScaledResolution(
                (double)minecraft.displayWidth / ClientGlobals.moduleManager.clientSettings.settingScale.getValue(),
                (double)minecraft.displayHeight / ClientGlobals.moduleManager.clientSettings.settingScale.getValue(),
                (int)ClientGlobals.moduleManager.clientSettings.settingScale.getValue()
            );
        if (!enabled) {
            GL11.glScaled(
                ClientGlobals.moduleManager.clientSettings.settingScale.getValue(),
                ClientGlobals.moduleManager.clientSettings.settingScale.getValue(),
                1.0
            );
        }

        if (tickDelay != 2) {
            timer2.reset();
        } else {
            keyCode2 = 0;
        }

        if (tickDelay != 2 || timer2.hasReached(10000L)) {
            Client.getClient().getClass();
            minecraft.fontRendererObj.getIndex(text, scaledResolution2.getScaledWidth() / 2 - minecraft.fontRendererObj.getIndex(text) / 2 + (enabled ? 51 : 0), 1, index);
        }

        GL11.glPopMatrix();
        RenderUtils.processAction();
    }

    public static void processAction() {
        if (!ClientGlobals.moduleManager.clientSettings.settingHideMainMenuAccountInfos.isEnabled()) {
            ClientNetworkConfig clientNetworkConfig = Client.getClient().getClientNetworkConfig();
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            StringBuilder sb = new StringBuilder().append("Username: §e");
            Client client = Client.getClient();
            StringBuilder sb2 = sb.append(client.getString7()).append("§7 [§e");
            Client client2 = Client.getClient();
            StringBuilder sb3 = sb2.append(client2.getInt2()).append("§7]§r [");
            Client client3 = Client.getClient();
            StringBuilder sb4 = sb3.append(client3.getString3());
            Client client4 = Client.getClient();
            fontRendererObj.getIndex2(sb4.append(client4.getString6()).append("§r]").toString(), 2.0F, 2.0F, -1);
            FontRenderer fontRendererObj2 = minecraft.fontRendererObj;
            StringBuilder sb5 = new StringBuilder().append("Account: ");
            String text;
            if (minecraft.session.getText2().length() < 5) {
                text = "§c";
            } else {
                text = "Account: ";
            }

            StringBuilder sb6 = sb5.append(text);
            fontRendererObj2.getIndex2(sb6.append(minecraft.getSession().getText1()).toString(), 2.0F, 12.0F, -1);
            FontRenderer fontRendererObj3 = minecraft.fontRendererObj;
            StringBuilder sb7 = new StringBuilder().append("Proxy: ");
            String text2;
            if (clientNetworkConfig == null) {
                text2 = "§cNot connected!";
            } else {
                text2 = "§7" + clientNetworkConfig.getString3() + ":" + clientNetworkConfig.getInt();
            }

            fontRendererObj3.getIndex2(sb7.append(text2).toString(), 2.0F, 22.0F, -1);
        }
    }

    public static void setIntValueArray(int[] countArray) {
        intValueArray = countArray;
    }

    public static void processShort() {
        String selected = Client.getClient().getModuleManager().clientSettings.settingMode.getSelected();
switch (selected) {
            case "Vanilla":
                minecraft.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
                break;
            case "Piano":
            case "Sigma":
                ResourceLocation resourceLocation14 = SoundPlayer.resourceLocation14;
                SoundPlayer.processAction(resourceLocation14);
        }
    }

    private static boolean canExecute6() {
        return false;
    }

    public static void updateState2() {
        String selected = Client.getClient().getModuleManager().clientSettings.settingMode.getSelected();
        switch (selected) {
            case "Vanilla":
                minecraft.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(SoundPlayer.resourceLocation3, 1.0F));
                break;
            case "Piano":
                ResourceLocation resourceLocation13 = SoundPlayer.resourceLocation13;
                SoundPlayer.processAction(resourceLocation13);
                break;
            case "Sigma":
                ResourceLocation resourceLocation12 = SoundPlayer.resourceLocation12;
                SoundPlayer.processAction(resourceLocation12);
        }
    }
}
