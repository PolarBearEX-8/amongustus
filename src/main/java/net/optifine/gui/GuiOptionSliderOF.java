package net.optifine.gui;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.GuiOptionSlider;

public class GuiOptionSliderOF extends GuiOptionSlider implements IOptionControl {
   private GameSettings.Options option = null;

   public GuiOptionSliderOF(int n, int n2, int n3, GameSettings.Options options2) {
      super(n, n2, n3, options2);
      this.option = options2;
   }

   @Override
   public GameSettings.Options getOption() {
      return this.option;
   }
}
