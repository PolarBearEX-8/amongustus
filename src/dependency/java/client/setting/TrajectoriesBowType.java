package client.setting;

public enum TrajectoriesBowType implements NamedOption {
    BOW("Bow"),
    ENDER_PEARL("EnderPearl"),
    EGG("Egg"),
    SNOW_BALL("SnowBall"),
    XP_BOTTLE("XPBottle"),
    POTION("Potion"),
    ROD("Rod"),
    FIREBALL("Fireball");

   public final String labelText;
   public boolean enabled = true;
   public static final TrajectoriesBowType[] ENUM_VALUES = new TrajectoriesBowType[]{BOW, ENDER_PEARL, EGG, SNOW_BALL, XP_BOTTLE, POTION, ROD, FIREBALL};

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

   private TrajectoriesBowType(String text) {
      this.labelText = text;
   }


}
