package client.setting;

public enum ChamsTargetEntity implements NamedOption {
    PLAYERS("Players", true),
    MOBS("Mobs", true),
    ANIMALS("Animals", false),
    ARMOR_STANDS("ArmorStands", false),
    VILLAGER("Villager", false),
    INVISIBLE("Invisible", true);

   public final String labelText;
   public boolean enabled;
   public static final ChamsTargetEntity[] ENUM_VALUES = new ChamsTargetEntity[]{PLAYERS, MOBS, ANIMALS, ARMOR_STANDS, VILLAGER, INVISIBLE};

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

   private ChamsTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
