package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum StaffDetectorModeOption implements SelectableOption {
   NORMAL("Normal"),
   SIMPLE("Simple"),
   GOMME("OldGomme"),
   OTHER("Other"),
   LIST_BASED("ListBased");

private final String name;
   private boolean selected;
private StaffDetectorModeOption(String str) {
      this.name = str;
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
