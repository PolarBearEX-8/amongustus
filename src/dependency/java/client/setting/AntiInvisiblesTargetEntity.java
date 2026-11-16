package client.setting;

public enum AntiInvisiblesTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    ARMOR_STAND("ArmorStand", false);

   public final String labelText;
   public boolean enabled;
   public static final AntiInvisiblesTargetEntity[] ENUM_VALUES = new AntiInvisiblesTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND};

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

   private AntiInvisiblesTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
