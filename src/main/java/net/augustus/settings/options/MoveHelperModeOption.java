package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum MoveHelperModeOption implements SelectableOption {
   COMBO_BREAKER("ComboBreaker"),
   HIT_SELECTION("HitSelection");

private final String name;
   private boolean selected;
private MoveHelperModeOption(String str) {
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

   public static boolean isOptionSelected(MoveHelperModeOption moveHelperModeOption) {
      return moveHelperModeOption.selected;
   }
}
