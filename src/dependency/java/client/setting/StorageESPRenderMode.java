package client.setting;

public enum StorageESPRenderMode implements NamedOption {
    BOX("Box", true),
    BLOCK_BOX("BlockBox", false),
    CORNER_3D("3DCorner", false),
    REAL_2D("Real2D", false),
    CORNER("Corner", false);

   public final String labelText;
   public boolean enabled;
   public static final StorageESPRenderMode[] ENUM_VALUES = new StorageESPRenderMode[]{BOX, BLOCK_BOX, CORNER_3D, REAL_2D, CORNER};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private StorageESPRenderMode(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
