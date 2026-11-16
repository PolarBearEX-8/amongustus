package client.setting;

public enum BlurTargetEntity implements NamedOption {
    MYSELF("Myself"),
    PLAYERS("Players"),
    MOBS("Mobs"),
    ANIMALS("Animals"),
    ARMOR_STANDS("ArmorStands"),
    VILLAGER("Villager"),
    INVISIBLE("Invisible");

   public final String labelText;
   public boolean enabled;
   public static final BlurTargetEntity[] ENUM_VALUES = new BlurTargetEntity[]{MYSELF, PLAYERS, MOBS, ANIMALS, ARMOR_STANDS, VILLAGER, INVISIBLE};

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

   private BlurTargetEntity(String text) {
      this.labelText = text;
   }


}
