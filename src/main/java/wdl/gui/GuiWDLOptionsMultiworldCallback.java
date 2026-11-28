package wdl.gui;

import wdl.WDL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLOptionsMultiworldCallback implements MultiworldCallback {
   public final GuiWDL parentGui;

   public void onSelect(boolean flag) {
      WDL.isMultiworld = flag;
      if (WDL.isMultiworld) {
         GuiWDL.getMinecraftForMultiworldSelect(this.parentGui).displayGuiScreen((GuiScreen)new GuiWDLMultiworldSelect(I18n.format("wdl.gui.multiworldSelect.title.changeOptions", new Object[0]), new GuiWDLOptionsSetupMultiworldSelectCallback(this)));
      } else {
         WDL.baseProps.setProperty("LinkedWorlds", "");
         WDL.saveProps();
         WDL.propsFound = true;
         GuiWDL.getMinecraftForReturnToOptions(this.parentGui).displayGuiScreen(this.parentGui);
      }
   }

   public void onCancel() {
      GuiWDL.getMinecraftForMultiworldCancel(this.parentGui).displayGuiScreen((GuiScreen)null);
   }

   public GuiWDLOptionsMultiworldCallback(GuiWDL guiWDL) {
      this.parentGui = guiWDL;
   }


}
