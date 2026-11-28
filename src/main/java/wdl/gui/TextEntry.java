package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.Minecraft;

public class TextEntry implements GuiListExtended.IGuiListEntry {
   private final String text;
   private final int color;
   public final Minecraft mc;

   public TextEntry(Minecraft mc2, String str, int n) {
      this.mc = mc2;
      this.text = str;
      this.color = n;
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   public TextEntry(Minecraft mc2, String str) {
      this(mc2, str, 1048575);
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      if (n3 >= 0) {
         Utils.drawStringWithShadow(this.text, n2, n3 + 1, this.color);
      }
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      return false;
   }
}
