package client.setting;

public enum ESPTargetEntity implements NamedOption {
    PLAYERS("Players", true),
    MOBS("Mobs", true),
    VILLAGER("Villager", true),
    ANIMALS("Animals", false),
    INVISIBLE("Invisible", true),
    TEAMMATES("Teammates", true);

   public final String labelText;
   public boolean enabled;
   public static final ESPTargetEntity[] ENUM_VALUES = new ESPTargetEntity[]{PLAYERS, MOBS, VILLAGER, ANIMALS, INVISIBLE, TEAMMATES};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkESPTargetEntity(ESPTargetEntity eSPTargetEntity) {
      return eSPTargetEntity.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private ESPTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
