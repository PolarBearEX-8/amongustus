package client.setting;

public enum Custom2DItemsBronzeType implements NamedOption {
    BRONZE("Bronze"),
    IRON("Iron"),
    GOLD("Gold"),
    DIAMOND("Diamond"),
    EMERALD("Emerald"),
    ALL("Other");

   public final String labelText;
   public boolean enabled = true;
   public static final Custom2DItemsBronzeType[] ENUM_VALUES = new Custom2DItemsBronzeType[]{BRONZE, IRON, GOLD, DIAMOND, EMERALD, ALL};

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

   private Custom2DItemsBronzeType(String text) {
      this.labelText = text;
   }


}
