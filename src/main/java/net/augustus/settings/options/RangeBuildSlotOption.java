package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.modules.legit.Range;
import net.augustus.utils.interfaces.MC;

public enum RangeBuildSlotOption implements SelectableOption {
   SLOT1("Slot1"),
   SLOT2("Slot2"),
   SLOT3("Slot3"),
   SLOT4("Slot4"),
   SLOT5("Slot5"),
   SLOT6("Slot6"),
   SLOT7("Slot7"),
   SLOT8("Slot8"),
   SLOT9("Slot9");

private final String name;
   private boolean selected;

   private RangeBuildSlotOption(String str) {
      this.name = str;
      this.selected = true;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   private static boolean isHeldSlotEnabled() {
      int n = MC.mc.thePlayer.inventory.currentItem + 1;
      return (n != 1 || SLOT1.isSelected())
         && (n != 2 || SLOT2.isSelected())
         && (n != 3 || SLOT3.isSelected())
         && (n != 4 || SLOT4.isSelected())
         && (n != 5 || SLOT5.isSelected())
         && (n != 6 || SLOT6.isSelected())
         && (n != 7 || SLOT7.isSelected())
         && (n != 8 || SLOT8.isSelected())
         && (n != 9 || SLOT9.isSelected());
   }

   public static boolean isCurrentSlotAllowed() {
      return isHeldSlotEnabled();
   }
}
