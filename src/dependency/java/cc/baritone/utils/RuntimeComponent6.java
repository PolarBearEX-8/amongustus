package cc.baritone.utils;

import net.minecraft.client.gui.GuiScreen;

public class RuntimeComponent6 extends GuiScreen {
   public final RandomSeedMapGeneratorGuiScreen parentScreen;

   public RuntimeComponent6(byte modeFlag, int seed, RandomSeedMapGeneratorGuiScreen parentScreen, int state) {
      this.parentScreen = parentScreen;
   }
}
