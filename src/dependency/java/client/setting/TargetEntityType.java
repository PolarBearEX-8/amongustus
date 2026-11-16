package client.setting;

public enum TargetEntityType implements NamedOption {
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
   public static final TargetEntityType[] ENUM_VALUES = new TargetEntityType[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, DEAD, TEAMMATES};

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

   private TargetEntityType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   private TargetEntityType(String text) {
      this.labelText = text;
   }


}
