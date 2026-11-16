package client.setting;

public enum PulseBlinkTargetEntity implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled = true;
   public static final PulseBlinkTargetEntity[] ENUM_VALUES = new PulseBlinkTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, DEAD};

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

   private PulseBlinkTargetEntity(String text) {
      this.labelText = text;
   }


}
