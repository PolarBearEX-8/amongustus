package client.setting;

public enum AutoPotPotionType implements NamedOption {
    HEALTH("Health", true),
    SPEED("Speed", true),
    REGENERATION("Regeneration", true),
    ALL_POSITIVE("AllPositive", false);

   public final String labelText;
   public boolean enabled;
   public static final AutoPotPotionType[] ENUM_VALUES = new AutoPotPotionType[]{HEALTH, SPEED, REGENERATION, ALL_POSITIVE};

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

   private AutoPotPotionType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
