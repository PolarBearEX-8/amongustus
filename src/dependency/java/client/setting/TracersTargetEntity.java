package client.setting;

public enum TracersTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    ARMOR_STAND("ArmorStand", false),
    INVISIBLE("Invisible", false),
    TEAMMATES("Teammates", false),
    ALERTED("Alerted", true);

   public final String labelText;
   public boolean enabled;
   public static final TracersTargetEntity[] ENUM_VALUES = new TracersTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE, TEAMMATES, ALERTED};

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

   private TracersTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
