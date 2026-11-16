package client.setting;

public enum KillAuraTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled;
   public static final KillAuraTargetEntity[] ENUM_VALUES = new KillAuraTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, DEAD};

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

   private KillAuraTargetEntity(String text) {
      this.labelText = text;
   }


}
