package client.setting;

public enum NoArmorOverlayHelmetType implements NamedOption {
    HELMET("Helmet", true),
    CHEST_PLATE("ChestPlate", true),
    TROUSERS("Trousers", true),
    BOOTS("Boots", true);

   public String labelText;
   public boolean enabled;
   public static final NoArmorOverlayHelmetType[] ENUM_VALUES = new NoArmorOverlayHelmetType[]{HELMET, CHEST_PLATE, TROUSERS, BOOTS};

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

   private NoArmorOverlayHelmetType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
