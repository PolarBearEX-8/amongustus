package client.util;

import client.setting.NamedOption;

public enum ProjectileType implements NamedOption {
    ARROW("Arrow"),
    ENDER_PEARL("EnderPearl"),
    EGG("Egg"),
    SNOW_BALL("SnowBall"),
    XP_BOTTLE("XPBottle"),
    POTION("Potion"),
    FIRE_BALL("FireBall");

   public final String labelText;
   public boolean enabled = true;
   public static final ProjectileType[] ENUM_VALUES = new ProjectileType[]{ARROW, ENDER_PEARL, EGG, SNOW_BALL, XP_BOTTLE, POTION, FIRE_BALL};

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

   private ProjectileType(String text) {
      this.labelText = text;
   }


}
