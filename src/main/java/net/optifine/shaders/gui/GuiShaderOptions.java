package net.optifine.shaders.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;

public class GuiShaderOptions extends GuiScreenOF {
   private GuiScreen prevScreen;
   public String title;
   private GameSettings settings;
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
   private String screenName = null;
   private String screenText = null;
   private boolean changed = false;
   public static final String OPTION_PROFILE = null;
   public static final String OPTION_EMPTY = null;
   public static final String OPTION_REST = "*";

   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.changed) {
         Shaders.saveShaderPackOptions();
         this.changed = false;
         Shaders.uninit();
      }
   }

   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id < 200 && button instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption guiButtonShaderOption = (GuiButtonShaderOption)button;
            ShaderOption shaderOption = guiButtonShaderOption.getShaderOption();
            if (shaderOption instanceof ShaderOptionScreen) {
               String name = shaderOption.getName();
               GuiShaderOptions guiShaderOptions = new GuiShaderOptions(this, this.settings, name);
               this.mc.displayGuiScreen((GuiScreen)guiShaderOptions);
               return;
            }

            if (isShiftKeyDown()) {
               shaderOption.resetValue();
            } else if (guiButtonShaderOption.isSwitchable()) {
               shaderOption.nextValue();
            }

            this.updateAllButtons();
            this.changed = true;
         }

         if (button.id == 201) {
            ShaderOption[] changedOptions = Shaders.getChangedOptions(Shaders.getShaderPackOptions());

            for (int i = 0; i < changedOptions.length; i++) {
               changedOptions[i].resetValue();
               this.changed = true;
            }

            this.updateAllButtons();
         }

         if (button.id == 200) {
            if (this.changed) {
               Shaders.saveShaderPackOptions();
               this.changed = false;
               Shaders.uninit();
            }

            GuiScreen prevScreen2 = this.prevScreen;
            this.mc.displayGuiScreen(prevScreen2);
         }
      }
   }

   public GuiShaderOptions(GuiScreen screen, GameSettings gameSettings) {
      this.title = "Shader Options";
      this.prevScreen = screen;
      this.settings = gameSettings;
   }

   public void actionPerformedRightClick(GuiButton button) {
      if (button instanceof GuiButtonShaderOption) {
         GuiButtonShaderOption guiButtonShaderOption = (GuiButtonShaderOption)button;
         ShaderOption shaderOption = guiButtonShaderOption.getShaderOption();
         if (isShiftKeyDown()) {
            shaderOption.resetValue();
         } else if (guiButtonShaderOption.isSwitchable()) {
            shaderOption.prevValue();
         }

         this.updateAllButtons();
         this.changed = true;
      }
   }

   public GuiShaderOptions(GuiScreen screen, GameSettings gameSettings, String str) {
      this(screen, gameSettings);
      this.screenName = str;
      if (str != null) {
         this.screenText = Shaders.translate("screen." + str, str);
      }
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      if (this.screenText != null) {
         drawCenteredString(this.fontRendererObj, this.screenText, this.width / 2, 15, 16777215);
      } else {
         drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   public void initGui() {
      this.title = I18n.format("of.options.shaderOptionsTitle", new Object[0]);
      byte b = 100;
      int n = 0;
      byte b4 = 120;
      byte b5 = 20;
      int shaderPackColumns = Shaders.getShaderPackColumns(this.screenName, 2);
      ShaderOption[] shaderPackOptions = Shaders.getShaderPackOptions(this.screenName);
      if (shaderPackOptions != null) {
         int n2 = MathHelper.ceiling_double_int((double)shaderPackOptions.length / 9.0);
         if (shaderPackColumns < n2) {
            shaderPackColumns = n2;
         }

         for (int i = 0; i < shaderPackOptions.length; i++) {
            ShaderOption shaderOption = shaderPackOptions[i];
            if (shaderOption != null && shaderOption.isVisible()) {
               int n3 = i % shaderPackColumns;
               int n4 = i / shaderPackColumns;
               int n5 = Math.min(this.width / shaderPackColumns, 200);
               n = (this.width - n5 * shaderPackColumns) / 2;
               int n6 = n3 * n5 + 5 + n;
               int n7 = ((byte)30) + n4 * ((byte)20);
               int n8 = n5 - 10;
               String buttonText = getButtonText(shaderOption, n8);
               Object obj;
               if (Shaders.isShaderPackOptionSlider(shaderOption.getName())) {
                  obj = new GuiSliderShaderOption(b + i, n6, n7, n8, b5, shaderOption, buttonText);
               } else {
                  obj = new GuiButtonShaderOption(b + i, n6, n7, n8, b5, shaderOption, buttonText);
               }

               ((GuiButtonShaderOption)obj).enabled = shaderOption.isEnabled();
               this.buttonList.add(obj);
            }
         }
      }

      this.buttonList.add(new GuiButton(201, this.width / 2 - b4 - 20, this.height / 6 + 168 + 11, b4, b5, I18n.format("controls.reset", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 + 20, this.height / 6 + 168 + 11, b4, b5, I18n.format("gui.done", new Object[0])));
   }

   private void updateAllButtons() {
      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         if (button instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption guiButtonShaderOption = (GuiButtonShaderOption)button;
            ShaderOption shaderOption = guiButtonShaderOption.getShaderOption();
            if (shaderOption instanceof ShaderOptionProfile) {
               ((ShaderOptionProfile)shaderOption).updateProfile();
            }

            guiButtonShaderOption.displayString = getButtonText(shaderOption, guiButtonShaderOption.getButtonWidth());
            guiButtonShaderOption.valueChanged();
         }
      }
   }

   public static String getButtonText(ShaderOption shaderOption, int n) {
      String nameText = shaderOption.getNameText();
      if (shaderOption instanceof ShaderOptionScreen) {
         ShaderOptionScreen shaderOptionScreen = (ShaderOptionScreen)shaderOption;
         return nameText + "...";
      } else {
         FontRenderer fontRendererObj2 = Config.getMc().fontRendererObj;
         int n2 = fontRendererObj2.getStringWidth(": " + Lang.getOff()) + 5;

         while (fontRendererObj2.getStringWidth(nameText) + n2 >= n && nameText.length() > 0) {
            nameText = nameText.substring(0, nameText.length() - 1);
         }

         String str = shaderOption.isChanged() ? shaderOption.getValueColor(shaderOption.getValue()) : "";
         String valueText = shaderOption.getValueText(shaderOption.getValue());
         return nameText + ": " + str + valueText;
      }
   }

}
