package client.setting;

public enum ANIMALMobEnum implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    ARMOR_STAND("ArmorStand");

   public String labelText;
   public boolean enabled;
   public static final ANIMALMobEnum[] ENUM_VALUES = new ANIMALMobEnum[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND};

   private ANIMALMobEnum(String text) {
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
