package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum InvManagerActionOption implements SelectableOption {
   SORT("Sort"),
   MERGE("Merge", false),
   PUT_ON_ARMOR("Armor"),
   CLEAN("Clean"),
   CRAFT("Craft", false),
   PUT_ON_ARMOR_HOT_BAR("HotbarArmor");

private final String name;
   private boolean selected = true;
private InvManagerActionOption(String str) {
      this.name = str;
   }

   private InvManagerActionOption(String str, boolean flag) {
      this.name = str;
      this.selected = flag;
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

   public static boolean isOptionSelected(InvManagerActionOption invManagerActionOption) {
      return invManagerActionOption.selected;
   }
}
