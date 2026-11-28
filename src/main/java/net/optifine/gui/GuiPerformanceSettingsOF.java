package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiPerformanceSettingsOF extends GuiScreen {
   private GuiScreen prevScreen;
   public String title;
   private GameSettings settings;
   private static GameSettings.Options[] enumOptions = new GameSettings.Options[]{
      GameSettings.Options.SMOOTH_FPS,
      GameSettings.Options.SMOOTH_WORLD,
      GameSettings.Options.FAST_RENDER,
      GameSettings.Options.FAST_MATH,
      GameSettings.Options.CHUNK_UPDATES,
      GameSettings.Options.CHUNK_UPDATES_DYNAMIC,
      GameSettings.Options.RENDER_REGIONS,
      GameSettings.Options.LAZY_CHUNK_LOADING,
      GameSettings.Options.SMART_ANIMATIONS
   };
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.performanceTitle", new Object[0]);
      this.buttonList.clear();

      for (int i = 0; i < enumOptions.length; i++) {
         GameSettings.Options options = enumOptions[i];
         int n = this.width / 2 - 155 + i % 2 * 160;
         int n2 = this.height / 6 + 21 * (i / 2) - 12;
         if (!options.getEnumFloat()) {
            this.buttonList.add(new GuiOptionButtonOF(options.returnEnumOrdinal(), n, n2, options, this.settings.getKeyBinding(options)));
         } else {
            this.buttonList.add(new GuiOptionSliderOF(options.returnEnumOrdinal(), n, n2, options));
         }
      }

      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id < 200 && button instanceof GuiOptionButton) {
            this.settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
            button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
         }

         if (button.id == 200) {
            this.mc.gameSettings.saveOptions();
            GuiScreen prevScreen2 = this.prevScreen;
            this.mc.displayGuiScreen(prevScreen2);
         }
      }
   }

   public GuiPerformanceSettingsOF(GuiScreen screen, GameSettings gameSettings) {
      this.prevScreen = screen;
      this.settings = gameSettings;
   }

}
