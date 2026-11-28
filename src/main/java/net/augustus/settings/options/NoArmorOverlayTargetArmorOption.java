package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NoArmorOverlayTargetArmorOption implements SelectableOption {
   HELMET("Helmet", true),
   CHEST_PLATE("ChestPlate", true),
   TROUSERS("Trousers", true),
   BOOTS("Boots", true);

private String name;
   private boolean selected;
private NoArmorOverlayTargetArmorOption(String str, boolean flag) {
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
