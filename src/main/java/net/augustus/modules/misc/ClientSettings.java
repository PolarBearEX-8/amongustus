package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.EventManager;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.WindowResizeListener;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.StringValue;
import net.augustus.utils.ClientHoliday;
import net.augustus.utils.HolidayDetector;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.network.ProxyEntry;
import net.augustus.utils.render.BlurShadowManager;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class ClientSettings extends Module implements TickListener, PacketListener {
   private final SettingGroup guiButtons;
   public final BooleanValue roundedButtons;
   public final DoubleValue roundFactor;
   private final SettingGroup sounds;
   public final StringValue mode;
   public final BooleanValue moduleToggleSound;
   public final BooleanValue welcomeSound;
   private final SettingGroup commandStuff;
   public final StringValue commandPrefixes;
   private final SettingGroup configSettings;
   public final BooleanValue loadKeysFromConfigs;
   private final SettingGroup chatSettings;
   private final BooleanValue chatCopySymbol;
   public final BooleanValue fastServerPining1;
   public final StringValue banStatistics;
   public final BooleanValue removeFramerateLimitInScreens;
   public final BooleanValue limitFrameRateIfScreenNotFocused;
   private final SettingGroup backgroundSettings;
   public final BooleanValue customBackground;
   public final BooleanValue mouseInputsOnBackgrounds;
   public final BooleanValue useAntialiasedRoundRects;
   public final BooleanValue tryFixingRendermodulesWithOptifineShaders;
   public final BooleanValue tryFixingNametagsWithOptifineShaders;
   public final BooleanValue hideMainMenuAccountInfos;
   public final BooleanValue sideMainMenu;
   private final TimeHelper holidaySoundTimer;
   private long halloweenSoundDelay;
   private long aprilFoolsSoundDelay;
   private boolean lastBlurState;

   public ClientSettings() {
      super("ClientSettings", "KundenEinstellungen", true, new Color(158, 168, 50), Categorys.MISC);
      this.guiButtons = (SettingGroup)new SettingGroup(this, "GuiButtons", true).markVisualOnly();
      this.roundedButtons = (BooleanValue)new BooleanValue(this, "RoundedButtons", true, this.guiButtons).markVisualOnly();
      this.roundFactor = (DoubleValue)new DoubleValue(this, "RoundFactor", 2.0, 1.0, 20.0, 0, this.guiButtons).visibleWhen(this.roundedButtons::getValue).markVisualOnly();
      this.sounds = (SettingGroup)new SettingGroup(this, "Sounds", true).markVisualOnly();
      this.mode = (StringValue)new StringValue(this, "Mode", "Sigma", new String[]{"Vanilla", "Sigma", "Piano"}, this.sounds).markVisualOnly();
      this.moduleToggleSound = (BooleanValue)new BooleanValue(this, "ModuleToggleSound", true, this.sounds).markVisualOnly();
      this.welcomeSound = (BooleanValue)new BooleanValue(this, "WelcomeSound", true, this.sounds).markVisualOnly();
      this.commandStuff = (SettingGroup)new SettingGroup(this, "CommandStuff", true).markVisualOnly();
      this.commandPrefixes = (StringValue)new StringValue(
            this, "CommandPrefixes", ".", new String[]{".", "-", "_", ":", ",", "+", "!", "#", "?", "\\", ">", "<"}, this.commandStuff
         )
         .markVisualOnly();
      this.configSettings = (SettingGroup)new SettingGroup(this, "ConfigSettings", true).markVisualOnly();
      this.loadKeysFromConfigs = (BooleanValue)new BooleanValue(this, "LoadKeysFromConfigs", false, this.configSettings).markVisualOnly();
      this.chatSettings = (SettingGroup)new SettingGroup(this, "ChatSettings", true).markVisualOnly();
      this.chatCopySymbol = (BooleanValue)new BooleanValue(this, "ChatCopySymbol", false, this.chatSettings).markVisualOnly();
      this.fastServerPining1 = (BooleanValue)((BooleanValue)new BooleanValue(this, "FastServerPining1", false).withVisibility(() -> false)).markVisualOnly();
      this.banStatistics = (StringValue)((StringValue)new StringValue(this, "BanStatistics", "Icon", new String[0]).withVisibility(() -> false)).markVisualOnly();
      this.removeFramerateLimitInScreens = (BooleanValue)new BooleanValue(this, "RemoveFramerateLimitInScreens", true).markVisualOnly();
      this.limitFrameRateIfScreenNotFocused = (BooleanValue)new BooleanValue(this, "LimitFrameRateIfScreenNotFocused", true).markVisualOnly();
      this.backgroundSettings = (SettingGroup)new SettingGroup(this, "BackgroundSettings", true).markVisualOnly();
      this.customBackground = (BooleanValue)new BooleanValue(this, "CustomBackground", true, this.backgroundSettings).markVisualOnly();
      this.mouseInputsOnBackgrounds = (BooleanValue)new BooleanValue(this, "MouseInputsOnBackgrounds", false, this.backgroundSettings).markVisualOnly();
      this.useAntialiasedRoundRects = (BooleanValue)new BooleanValue(this, "UseAntialiasedRoundRects", true).markVisualOnly();
      this.tryFixingRendermodulesWithOptifineShaders = (BooleanValue)new BooleanValue(this, "Try fixing rendermodules with optifine shaders", true).markVisualOnly();
      this.tryFixingNametagsWithOptifineShaders = (BooleanValue)new BooleanValue(this, "Try fixing nametags with optifine shaders", true).markVisualOnly();
      this.hideMainMenuAccountInfos = (BooleanValue)new BooleanValue(this, "HideMainMenuAccountInfos", false).markVisualOnly();
      this.sideMainMenu = (BooleanValue)new BooleanValue(this, "SideMainMenu", true).markVisualOnly();
      this.holidaySoundTimer = new TimeHelper();
      this.halloweenSoundDelay = DefaultRandomGenerator.instance.nextLong(220000L, 400000L);
      this.aprilFoolsSoundDelay = DefaultRandomGenerator.instance.nextLong(800000L, 1000000L);
      this.setToggled(true);
      EventManager.getEventBus().subscribe(40, this);
      EventManager.getEventBus().subscribe(26, this);
      this.lastBlurState = BlurShadowManager.isShadersCompatible();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S02PacketChat && this.chatCopySymbol.getValue()) {
         S02PacketChat chatPacket = (S02PacketChat)packet;
         if (chatPacket.getType() == 2) {
            return;
         }

         String stripped = StringUtils.stripControlCodes(chatPacket.getChatComponent().getUnformattedText());
         if (!stripped.replace(" ", "").isEmpty()) {
            ChatComponentText copySymbol = new ChatComponentText(EnumChatFormatting.DARK_GRAY + "\u270D");
            ChatStyle style = new ChatStyle()
               .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.GRAY + "Copy message")))
               .setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, stripped));
            copySymbol.setChatStyle(style);
            chatPacket.getChatComponent().appendSibling(new ChatComponentText(EnumChatFormatting.RESET + " "));
            chatPacket.getChatComponent().appendSibling(copySymbol);
         }
      }
   }

   @Override
   public void onLateTick() {
      EntityRenderer.shaderProgramSwitchEnabled = this.tryFixingRendermodulesWithOptifineShaders.getValue();
      EntityRenderer.shaderNameTagFix = this.tryFixingNametagsWithOptifineShaders.getValue();
      if (HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN && mc.thePlayer != null) {
         if (this.holidaySoundTimer.reached(this.halloweenSoundDelay)) {
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE14);
            this.halloweenSoundDelay = DefaultRandomGenerator.instance.nextLong(220000L, 500000L);
            this.holidaySoundTimer.reset();
         }
      }

      if (HolidayDetector.getCurrentHoliday() == ClientHoliday.FIRST_APRIL && mc.thePlayer != null) {
         if (this.holidaySoundTimer.reached(this.aprilFoolsSoundDelay)) {
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE12);
            this.aprilFoolsSoundDelay = DefaultRandomGenerator.instance.nextLong(800000L, 1000000L);
            this.holidaySoundTimer.reset();
         }
      }
   }

   @Override
   public void onPreTick() {
      boolean shadersCompatible = BlurShadowManager.isShadersCompatible();
      if (this.lastBlurState != shadersCompatible) {
         WindowResizeListener.postWindowResize(mc.displayWidth, mc.displayHeight);
         this.lastBlurState = shadersCompatible;
      }
   }

   public static void renderMainMenuAccountInfo() {
      if (!ClientManagers.moduleManager.clientSettings.hideMainMenuAccountInfos.getValue()) {
         ProxyEntry proxy = Augustus.getInstance().getProxy();
         String statusColor = mc.session.getToken().length() < 5 ? "§c" : "§a";
         mc.fontRendererObj.drawStringWithShadow("Account: " + statusColor + mc.getSession().getUsername(), 2.0F, 2.0F, -1);
         mc.fontRendererObj.drawStringWithShadow("Proxy: " + (proxy == null ? "§cNot connected!" : "§7" + proxy.getHost() + ":" + proxy.getPort()), 2.0F, 12.0F, -1);
      }
   }

   public static void playButtonClickSound() {
      switch (Augustus.getInstance().getModuleManager().clientSettings.mode.getSelectedValue()) {
         case "Vanilla":
            mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            break;
         case "Piano":
         case "Sigma":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
      }
   }

   public static void playModuleEnableSound() {
      switch (Augustus.getInstance().getModuleManager().clientSettings.mode.getSelectedValue()) {
         case "Vanilla":
            mc.getSoundHandler().playSound(PositionedSoundRecord.create(ClientSounds.GUI_TEXTURE, 1.0F));
            break;
         case "Piano":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE5);
            break;
         case "Sigma":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE4);
      }
   }

   public static void playModuleDisableSound() {
      switch (Augustus.getInstance().getModuleManager().clientSettings.mode.getSelectedValue()) {
         case "Vanilla":
            mc.getSoundHandler().playSound(PositionedSoundRecord.create(ClientSounds.GUI_TEXTURE, 1.0F));
            break;
         case "Piano":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE13);
            break;
         case "Sigma":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE11);
      }
   }
}
