package net.optifine.gui;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.GuiOptionButton;

public class GuiOptionButtonOF extends GuiOptionButton implements IOptionControl {
   private GameSettings.Options option = null;

   @Override
   public GameSettings.Options getOption() {
      return this.option;
   }

   public GuiOptionButtonOF(int n, int n2, int n3, GameSettings.Options options, String str) {
      super(n, n2, n3, options, str);
      this.option = options;
   }
}
