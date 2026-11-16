package client.setting;

public enum BlinkTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public String labelText;
   public boolean enabled = true;
   public static final BlinkTargetEntity[] ENUM_VALUES = new BlinkTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, DEAD};

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

   private BlinkTargetEntity(String text) {
      this.labelText = text;
   }


}
