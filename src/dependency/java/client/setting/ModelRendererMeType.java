package client.setting;

public enum ModelRendererMeType implements NamedOption {
    ME("Me"),
    PLAYERS("Players");

   public final String labelText;
   public boolean enabled = true;
   public static final ModelRendererMeType[] ENUM_VALUES = new ModelRendererMeType[]{ME, PLAYERS};

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

   private ModelRendererMeType(String text) {
      this.labelText = text;
   }


}
