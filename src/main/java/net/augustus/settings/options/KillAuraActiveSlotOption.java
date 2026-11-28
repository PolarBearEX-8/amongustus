package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum KillAuraActiveSlotOption implements SelectableOption {
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
private KillAuraActiveSlotOption(String str) {
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
}
