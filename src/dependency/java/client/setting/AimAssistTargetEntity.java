package client.setting;

public enum AimAssistTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand"),
    INVISIBLE("Invisible"),
    DEAD("Dead"),
    TEAMMATES("Teammates");

   public final String labelText;
   public boolean enabled;
   public static final AimAssistTargetEntity[] ENUM_VALUES = new AimAssistTargetEntity[]{
      PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, DEAD, TEAMMATES
   };

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

   private AimAssistTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   private AimAssistTargetEntity(String text) {
      this.labelText = text;
   }


}
