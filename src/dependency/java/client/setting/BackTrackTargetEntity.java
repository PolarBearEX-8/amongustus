package client.setting;

public enum BackTrackTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand");

   public String labelText;
   public boolean enabled;
   public static final BackTrackTargetEntity[] ENUM_VALUES = new BackTrackTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND};

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

   private BackTrackTargetEntity(String text) {
      this.labelText = text;
   }


}
