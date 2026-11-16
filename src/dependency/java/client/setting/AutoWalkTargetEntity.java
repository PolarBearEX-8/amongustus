package client.setting;

public enum AutoWalkTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    ARMOR_STAND("ArmorStand", false),
    INVISIBLE("Invisible", false);

   public final String labelText;
   public boolean enabled;
   public static final AutoWalkTargetEntity[] ENUM_VALUES = new AutoWalkTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE};

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

   private AutoWalkTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
