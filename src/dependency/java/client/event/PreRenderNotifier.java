package client.event;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.awt.Rectangle;
import net.minecraft.client.gui.GuiScreen;

public interface PreRenderNotifier {
   String[] resolveTextArray(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count);

   Rectangle resolveRectangle(GuiScreen screen, int count, int count2);

   boolean isActive();
}
