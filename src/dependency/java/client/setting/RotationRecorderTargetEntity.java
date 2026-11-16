package client.setting;

public enum RotationRecorderTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled;
   public static final RotationRecorderTargetEntity[] ENUM_VALUES = new RotationRecorderTargetEntity[]{
      PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, DEAD
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

   private RotationRecorderTargetEntity(String text) {
      this.labelText = text;
   }


}
