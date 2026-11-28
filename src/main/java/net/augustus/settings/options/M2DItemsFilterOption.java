package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum M2DItemsFilterOption implements SelectableOption {
   BRONZE("Bronze"),
   IRON("Iron"),
   GOLD("Gold"),
   DIAMOND("Diamond"),
   EMERALD("Emerald"),
   ALL("Other");

private final String name;
   private boolean selected = true;
private M2DItemsFilterOption(String str) {
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
