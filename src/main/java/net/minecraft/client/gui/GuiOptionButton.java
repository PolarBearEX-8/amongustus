package net.minecraft.client.gui;

import net.minecraft.client.settings.GameSettings;

public class GuiOptionButton extends GuiButton {
   private final GameSettings.Options enumOptions;

   public GuiOptionButton(int n, int n2, int n3, GameSettings.Options options, String str) {
      super(n, n2, n3, 150, 20, str);
      this.enumOptions = options;
   }

   public GuiOptionButton(int n, int n2, int n3, int n4, int n5, String str) {
      super(n, n2, n3, n4, n5, str);
      this.enumOptions = null;
   }

   public GameSettings.Options returnEnumOptions() {
      return this.enumOptions;
   }

   public GuiOptionButton(int n, int n2, int n3, String str) {
      this(n, n2, n3, (GameSettings.Options)null, str);
   }
}
