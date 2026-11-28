package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum SpinBotStopConditionOption implements SelectableOption {
   CLICK("Click", true),
   CHEST("Chest", true),
   CRAFTER("CraftingTable", false),
   FURNACE("Furnace", false);

private final String name;
   private boolean selected;
private SpinBotStopConditionOption(String str, boolean flag) {
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
