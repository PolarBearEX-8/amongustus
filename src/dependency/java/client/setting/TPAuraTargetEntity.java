package client.setting;

public enum TPAuraTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled;
   public static final TPAuraTargetEntity[] ENUM_VALUES = new TPAuraTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, DEAD};

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

   private TPAuraTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   private TPAuraTargetEntity(String text) {
      this.labelText = text;
   }


}
