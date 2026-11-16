package client.setting;

public enum PLAYERAnimalEnum implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled;
   public static final PLAYERAnimalEnum[] ENUM_VALUES = new PLAYERAnimalEnum[]{PLAYER, MOB, ANIMAL, VILLAGER, DEAD};

   private PLAYERAnimalEnum(String text) {
      this.labelText = text;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }


}
