package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum BlockMarkerRenderOption implements SelectableOption {
   SAME_BLOCK("Same block"),
   NOT_SAME_NOT_AIR("Different block (not air)"),
   NOT_SAME_AIR("Different block (air)");

private final String name;
   private boolean selected = true;
private BlockMarkerRenderOption(String str) {
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
