package wdl.gui;

import wdl.WDL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiWDLOptionsSetupMultiworldSelectCallback implements WorldSelectionCallback {
   public final GuiWDLOptionsMultiworldCallback parentCallback;

   public GuiWDLOptionsSetupMultiworldSelectCallback(GuiWDLOptionsMultiworldCallback callback) {
      this.parentCallback = callback;
   }

   @Override
   public void onCancel() {
      GuiWDL.getMinecraftForSetupCancel(this.parentCallback.parentGui).displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void onWorldSelected(String str) {
      WDL.worldName = str;
      WDL.isMultiworld = true;
      WDL.propsFound = true;
      WDL.worldProps = WDL.loadWorldProps(str);
      GuiWDL.getMinecraftForSetupSelected(this.parentCallback.parentGui).displayGuiScreen(this.parentCallback.parentGui);
   }
}
