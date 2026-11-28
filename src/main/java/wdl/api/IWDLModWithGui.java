package wdl.api;

import net.minecraft.client.gui.GuiScreen;

public interface IWDLModWithGui extends IWDLMod {
   void openGui(GuiScreen screen);

   String getButtonName();
}
