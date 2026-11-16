package client.setting;

public enum ProjectileAimBotTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled;
   public static final ProjectileAimBotTargetEntity[] ENUM_VALUES = new ProjectileAimBotTargetEntity[]{
      PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, DEAD
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity projectileAimBotTargetEntity) {
      return projectileAimBotTargetEntity.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private ProjectileAimBotTargetEntity(String text) {
      this.labelText = text;
   }


}
