package wdl.gui;

import wdl.WDL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiWDLOptionsMultiworldSelectCallback implements WorldSelectionCallback {
   public final GuiWDL parentGui;

   @Override
   public void onCancel() {
      GuiWDL.getMinecraftForSelectCancel(this.parentGui).displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void onWorldSelected(String str) {
      WDL.worldName = str;
      WDL.isMultiworld = true;
      WDL.propsFound = true;
      WDL.worldProps = WDL.loadWorldProps(str);
      GuiWDL.getMinecraftForWorldSelected(this.parentGui).displayGuiScreen(this.parentGui);
   }

   public GuiWDLOptionsMultiworldSelectCallback(GuiWDL guiWDL) {
      this.parentGui = guiWDL;
   }
}
