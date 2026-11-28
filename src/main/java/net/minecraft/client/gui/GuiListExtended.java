package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;

public abstract class GuiListExtended extends GuiSlot {
   public GuiListExtended(Minecraft mc2, int n, int n2, int n3, int n4, int n5) {
      super(mc2, n, n2, n3, n4, n5);
   }

   public boolean mouseClicked(int mouseX2, int mouseY2, int mouseButton) {
      if (this.isMouseYWithinSlotBounds(mouseY2)) {
         int n = this.getSlotIndexFromScreenCoords(mouseX2, mouseY2);
         if (n >= 0) {
            int n2 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int n3 = this.top + 4 - this.getAmountScrolled() + n * this.slotHeight + this.headerPadding;
            int n4 = mouseX2 - n2;
            int n5 = mouseY2 - n3;
            if (this.getEntry(n).mousePressed(n, mouseX2, mouseY2, mouseButton, n4, n5)) {
               this.setEnabled(false);
               return true;
            }
         }
      }

      return false;
   }

   public abstract GuiListExtended.IGuiListEntry getEntry(int n);

   public boolean mouseReleased(int mouseX2, int mouseY2, int state) {
      for (int i = 0; i < this.getSize(); i++) {
         int n = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
         int n2 = this.top + 4 - this.getAmountScrolled() + i * this.slotHeight + this.headerPadding;
         int n3 = mouseX2 - n;
         int n4 = mouseY2 - n2;
         this.getEntry(i).mouseReleased(i, mouseX2, mouseY2, state, n3, n4);
      }

      this.setEnabled(true);
      return false;
   }

   @Override
   public void updateItemPos(int n, int n2, int n3) {
      this.getEntry(n).setSelected(n, n2, n3);
   }

   @Override
   public void drawBackground() {
   }

   @Override
   public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
      this.getEntry(n).drawEntry(n, n2, n3, this.getListWidth(), n4, n5, n6, this.getSlotIndexFromScreenCoords(n5, n6) == n);
   }

   @Override
   public boolean isSelected(int n) {
      return false;
   }

   @Override
   public void elementClicked(int n, boolean flag, int n2, int n3) {
   }


   public interface IGuiListEntry {
      void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6);

      void setSelected(int n, int n2, int n3);

      void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag);

      boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6);
   }
}
