package augustus.anticheat.checks;

import client.setting.NamedOption;

public enum AntiBotCheck implements NamedOption {
    ADVANCED("Advanced"),
    HYPIXEL("Hypixel"),
    INTAVE("Intave"),
    NULL_TEAM("NullTeam"),
    ILLEGAL_PITCH("IllegalPitch"),
    TICKS_EXISTED("TicksExisted"),
    NEGATIVE_HEALTH("NegativeHealth"),
    ZERO_PING("ZeroPing"),
    TAB_LIST("IsInTabList"),
    INVALID_ENTITY_ID("InvalidEntityID"),
    INVALID_NAME("InvalidName"),
    NEVER_ON_GROUND("NeverOnGround"),
    SWING("Swinged"),
    ITEM("ItemInHand"),
    ARMOR("Armor"),
    SOUND("Sound");

   public String labelText;
   public boolean enabled;
   public static final AntiBotCheck[] ENUM_VALUES = new AntiBotCheck[]{
      ADVANCED,
      HYPIXEL,
      INTAVE,
      NULL_TEAM,
      ILLEGAL_PITCH,
      TICKS_EXISTED,
      NEGATIVE_HEALTH,
      ZERO_PING,
      TAB_LIST,
      INVALID_ENTITY_ID,
      INVALID_NAME,
      NEVER_ON_GROUND,
      SWING,
      ITEM,
      ARMOR,
      SOUND
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkAntiBotCheck(AntiBotCheck antiBotCheck) {
      return antiBotCheck.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private AntiBotCheck(String text) {
      this.labelText = text;
   }


}
