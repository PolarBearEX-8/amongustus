package wdl.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class MultiworldSelectButton extends GuiButton {
   public final GuiWDLMultiworldSelect parentGui;

   public MultiworldSelectButton(GuiWDLMultiworldSelect guiWDLMultiworldSelect, int n, int n2, int n3, String str, String str2) {
      super(n, n2, n3, "");
      this.parentGui = guiWDLMultiworldSelect;
   }

   public MultiworldInfo getWorldInfo() {
      int n = GuiWDLMultiworldSelect.getIndex(this.parentGui) + this.id;
      if (n < 0) {
         return null;
      } else {
         return n >= GuiWDLMultiworldSelect.getLinkedWorldsFiltered(this.parentGui).size() ? null : (MultiworldInfo)GuiWDLMultiworldSelect.getLinkedWorldsFiltered(this.parentGui).get(n);
      }
   }

   public MultiworldSelectButton(GuiWDLMultiworldSelect guiWDLMultiworldSelect, int n, int n2, int n3, int n4, int n5) {
      super(n, n2, n3, n4, n5, "");
      this.parentGui = guiWDLMultiworldSelect;
   }

   @Override
   public void drawButton(Minecraft mc, int n, int n2) {
      MultiworldInfo worldInfo = this.getWorldInfo();
      if (worldInfo == null) {
         this.displayString = "";
         this.enabled = false;
      } else {
         this.displayString = worldInfo.displayName;
         this.enabled = true;
      }

      if (worldInfo != null && worldInfo == GuiWDLMultiworldSelect.getSelectedMultiWorld(this.parentGui)) {
         drawRect(this.xPosition - 2, this.yPosition - 2, this.xPosition + this.width + 2, this.yPosition + this.height + 2, -16744704);
      }

      super.drawButton(mc, n, n2);
   }
}
