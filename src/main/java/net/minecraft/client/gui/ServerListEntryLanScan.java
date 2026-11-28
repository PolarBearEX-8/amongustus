package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanScan implements GuiListExtended.IGuiListEntry {
   private final Minecraft mc = Minecraft.getMinecraft();

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      int n8 = n3 + n5 / 2 - this.mc.fontRendererObj.FONT_HEIGHT / 2;
      this.mc
         .fontRendererObj
         .drawString(
            I18n.format("lanServer.scanning", new Object[0]),
            this.mc.currentScreen.width / 2 - this.mc.fontRendererObj.getStringWidth(I18n.format("lanServer.scanning", new Object[0])) / 2,
            n8,
            16777215
         );
      String str;
      switch ((int)(Minecraft.getSystemTime() / 300L % 4L)) {
         case 0:
         default:
            str = "O o o";
            break;
         case 1:
         case 3:
            str = "o O o";
            break;
         case 2:
            str = "o o O";
      }

      this.mc.fontRendererObj.drawString(str, this.mc.currentScreen.width / 2 - this.mc.fontRendererObj.getStringWidth(str) / 2, n8 + this.mc.fontRendererObj.FONT_HEIGHT, 8421504);
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      return false;
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }
}
