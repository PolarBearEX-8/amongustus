package cc.baritone.utils;

import net.minecraft.client.gui.GuiScreen;

public interface GuiScreenSpecialListener extends SpecialListener {
   String getText();

   void applyGuiScreen(GuiScreen screen);
}
