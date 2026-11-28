package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum StorageEspRenderModeOption implements SelectableOption {
   BOX("Box", true),
   BLOCK_BOX("BlockBox", false),
   CORNER_3D("3DCorner", false),
   REAL_2D("Real2D", false),
   CORNER("Corner", false);

private final String name;
   private boolean selected;
private StorageEspRenderModeOption(String str, boolean flag) {
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
