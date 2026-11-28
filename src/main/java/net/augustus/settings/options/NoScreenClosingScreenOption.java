package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NoScreenClosingScreenOption implements SelectableOption {
   CLICK_GUI("ClickGui", true),
   CHAT("Chat", true),
   INVENTORY("Inventory", false),
   CHEST("Chest", false),
   OTHERS("Others", false);

private final String name;
   private boolean selected;
private NoScreenClosingScreenOption(String str, boolean flag) {
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

   public static boolean isOptionSelected(NoScreenClosingScreenOption option) {
      return option.selected;
   }
}
