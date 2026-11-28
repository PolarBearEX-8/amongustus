package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum ShadowItemOption implements SelectableOption {
   ITEMS_ON_GROUND("ItemsOnGround", false),
   ITEMS_IN_HAND("ItemsInHand", false),
   ITEMS_IN_HOTBAR("ItemsInHotbar", false);

private final String name;
   private boolean selected;
private ShadowItemOption(String str, boolean flag) {
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
}
