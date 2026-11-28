package net.optifine.gui;

import java.awt.Rectangle;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public interface TooltipProvider {
   Rectangle getTooltipBounds(GuiScreen screen, int n, int n2);

   boolean isRenderBorder();

   String[] getTooltipLines(GuiButton button, int n);
}
