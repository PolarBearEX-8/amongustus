package client.setting;

public enum PathfinderTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    INVISIBLE("Invisible", false),
    SLEEPING("Sleeping", false);

   public final String labelText;
   public boolean enabled;
   public static final PathfinderTargetEntity[] ENUM_VALUES = new PathfinderTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, SLEEPING};

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

   private PathfinderTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
