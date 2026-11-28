package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum ModelRendererTargetOption implements SelectableOption {
   ME("Me"),
   PLAYERS("Players");

private final String name;
   private boolean selected = true;
private ModelRendererTargetOption(String str) {
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
