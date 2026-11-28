package wdl.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class LinkEntry extends TextEntry {
   private final String link;
   private final int textWidth;
   private final int linkWidth;

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (n5 >= 0 && n5 <= this.textWidth) {
         Utils.openLink(this.link);
         return true;
      } else {
         return false;
      }
   }

   public LinkEntry(Minecraft mc2, String str, String str2) {
      super(mc2, str, 5592575);
      this.link = str2;
      this.textWidth = mc2.fontRendererObj.getStringWidth(str);
      this.linkWidth = mc2.fontRendererObj.getStringWidth(str2);
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      if (n3 >= 0) {
         super.drawEntry(n, n2, n3, n4, n5, n6, n7, flag);
         int n8 = n6 - n2;
         int n9 = n7 - n3;
         if (n8 >= 0 && n8 <= this.textWidth && n9 >= 0 && n9 <= n5) {
            int n10 = n6 - 2;
            if (n10 + this.linkWidth + 4 > n4 + n2) {
               n10 = n4 + n2 - (4 + this.linkWidth);
            }

            Gui.drawRect(n10, n7 - 2, n10 + this.linkWidth + 4, n7 + this.mc.fontRendererObj.FONT_HEIGHT + 2, Integer.MIN_VALUE);
            Utils.drawStringWithShadow(this.link, n10 + 2, n7, 16777215);
         }
      }
   }
}
