package net.minecraft.client.gui;

import java.io.IOException;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.augustus.events.listeners.GuiScaleListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.gui.GuiShaders;

public class GuiVideoSettings extends GuiScreen {
   public String screenTitle = "Video Settings";
   private GameSettings guiGameSettings;
   private static GameSettings.Options[] videoOptions = new GameSettings.Options[]{
      GameSettings.Options.GRAPHICS,
      GameSettings.Options.RENDER_DISTANCE,
      GameSettings.Options.AMBIENT_OCCLUSION,
      GameSettings.Options.FRAMERATE_LIMIT,
      GameSettings.Options.AO_LEVEL,
      GameSettings.Options.VIEW_BOBBING,
      GameSettings.Options.GUI_SCALE,
      GameSettings.Options.USE_VBO,
      GameSettings.Options.GAMMA,
      GameSettings.Options.BLOCK_ALTERNATIVES,
      GameSettings.Options.DYNAMIC_LIGHTS,
      GameSettings.Options.DYNAMIC_FOV
   };
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
      this.drawString(
         this.fontRendererObj,
         ((Module)Augustus.getInstance().getModuleManager().invisMode).isToggled() ? "OptiFine HD M5 Ultra" : "OptiFine HD M5 Ultra (Hard-forked by EnZaXD & DasNeueUpdate)",
         2,
         this.height - 10,
         8421504
      );
      String str = "Minecraft 1.8.9";
      int stringWidth = this.fontRendererObj.getStringWidth(str);
      this.drawString(this.fontRendererObj, str, this.width - stringWidth - 2, this.height - 10, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (keyCode == 1) {
         this.mc.gameSettings.saveOptions();
      }
   }

   public static int getButtonWidth(GuiButton button) {
      return button.width;
   }

   public GuiVideoSettings(GuiScreen screen, GameSettings gameSettings) {
      super(screen);
      this.guiGameSettings = gameSettings;
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         int guiScale = this.guiGameSettings.guiScale;
         if (button.id < 200 && button instanceof GuiOptionButton) {
            this.guiGameSettings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
            button.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
         }

         if (button.id == 200) {
            this.mc.gameSettings.saveOptions();
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         }

         if (this.guiGameSettings.guiScale != guiScale) {
            ScaledResolution sr = new ScaledResolution(this.mc);
            int scaledWidth = sr.getScaledWidth();
            int scaledHeight = sr.getScaledHeight();
            this.setWorldAndResolution(this.mc, scaledWidth, scaledHeight);
            GuiScaleListener.postGuiScale();
         }

         if (button.id == 201) {
            this.mc.gameSettings.saveOptions();
            GuiDetailSettingsOF guiDetailSettingsOF = new GuiDetailSettingsOF(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiDetailSettingsOF);
         }

         if (button.id == 202) {
            this.mc.gameSettings.saveOptions();
            GuiQualitySettingsOF guiQualitySettingsOF = new GuiQualitySettingsOF(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiQualitySettingsOF);
         }

         if (button.id == 211) {
            this.mc.gameSettings.saveOptions();
            GuiAnimationSettingsOF guiAnimationSettingsOF = new GuiAnimationSettingsOF(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiAnimationSettingsOF);
         }

         if (button.id == 212) {
            this.mc.gameSettings.saveOptions();
            GuiPerformanceSettingsOF guiPerformanceSettingsOF = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiPerformanceSettingsOF);
         }

         if (button.id == 222) {
            this.mc.gameSettings.saveOptions();
            GuiOtherSettingsOF guiOtherSettingsOF = new GuiOtherSettingsOF(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiOtherSettingsOF);
         }

         if (button.id == 231) {
            if (Config.isAntialiasing() || Config.isAntialiasingConfigured()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
               return;
            }

            if (Config.isAnisotropicFiltering()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
               return;
            }

            if (Config.isFastRender()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
               return;
            }

            if (Config.getGameSettings().anaglyph) {
               Config.showGuiMessage(Lang.get("of.message.shaders.an1"), Lang.get("of.message.shaders.an2"));
               return;
            }

            this.mc.gameSettings.saveOptions();
            GuiShaders guiShaders = new GuiShaders(this, this.guiGameSettings);
            this.mc.displayGuiScreen((GuiScreen)guiShaders);
         }
      }
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
      this.buttonList.clear();

      for (int i = 0; i < videoOptions.length; i++) {
         GameSettings.Options options = videoOptions[i];
         if (options != null) {
            int n = this.width / 2 - 155 + i % 2 * 160;
            int n2 = this.height / 6 + 21 * (i / 2) - 12;
            if (options.getEnumFloat()) {
               this.buttonList.add(new GuiOptionSliderOF(options.returnEnumOrdinal(), n, n2, options));
            } else {
               this.buttonList.add(new GuiOptionButtonOF(options.returnEnumOrdinal(), n, n2, options, this.guiGameSettings.getKeyBinding(options)));
            }
         }
      }

      int n3 = this.height / 6 + 21 * (videoOptions.length / 2) - 12;
      int n4 = 0;
      n4 = this.width / 2 - 155 + 0;
      this.buttonList.add(new GuiOptionButton(231, n4, n3, Lang.get("of.options.shaders")));
      n4 = this.width / 2 - 155 + 160;
      this.buttonList.add(new GuiOptionButton(202, n4, n3, Lang.get("of.options.quality")));
      n3 += 21;
      n4 = this.width / 2 - 155 + 0;
      this.buttonList.add(new GuiOptionButton(201, n4, n3, Lang.get("of.options.details")));
      n4 = this.width / 2 - 155 + 160;
      this.buttonList.add(new GuiOptionButton(212, n4, n3, Lang.get("of.options.performance")));
      n3 += 21;
      n4 = this.width / 2 - 155 + 0;
      this.buttonList.add(new GuiOptionButton(211, n4, n3, Lang.get("of.options.animations")));
      n4 = this.width / 2 - 155 + 160;
      this.buttonList.add(new GuiOptionButton(222, n4, n3, Lang.get("of.options.other")));
      n3 += 21;
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
   }

   public static int getButtonHeight(GuiButton button) {
      return button.height;
   }

   public static String getGuiChatText(GuiChat guiChat) {
      return guiChat.inputField.getText();
   }

   public static void drawGradientRect(GuiScreen screen, int n, int n2, int n3, int n4, int n5, int n6) {
      screen.drawGradientRect(n, n2, n3, n4, n5, n6);
   }

}
