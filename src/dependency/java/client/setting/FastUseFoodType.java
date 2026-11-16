package client.setting;

public enum FastUseFoodType implements NamedOption {
    FOOD("Food"),
    BOW("Bow"),
    OTHER("Other");

   public String labelText;
   public boolean enabled = true;
   public static final FastUseFoodType[] ENUM_VALUES = new FastUseFoodType[]{FOOD, BOW, OTHER};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkFastUseFoodType(FastUseFoodType fastUseFoodType) {
      return fastUseFoodType.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private FastUseFoodType(String text) {
      this.labelText = text;
   }


}
