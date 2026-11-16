package client.setting;

public enum AutoRodNewTargetEntity implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    INVISIBLE("Invisible", true),
    DEAD("Dead", true);

   public final String labelText;
   public boolean enabled;
   public static final AutoRodNewTargetEntity[] ENUM_VALUES = new AutoRodNewTargetEntity[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, DEAD};

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

   private AutoRodNewTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
