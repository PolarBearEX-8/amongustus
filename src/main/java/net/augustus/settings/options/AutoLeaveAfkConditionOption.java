package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum AutoLeaveAfkConditionOption implements SelectableOption {
   ROTATIONS("Rotations"),
   MOVEMENT("Movement"),
   OTHER_KEY_INPUTS("OtherKeyInputs");

private final String name;
   private boolean selected = true;
private AutoLeaveAfkConditionOption(String str) {
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

   public static boolean isOptionSelected(AutoLeaveAfkConditionOption option) {
      return option.selected;
   }
}
