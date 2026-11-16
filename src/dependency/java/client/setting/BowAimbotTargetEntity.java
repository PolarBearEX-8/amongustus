package client.setting;

public enum BowAimbotTargetEntity implements NamedOption {
    PLAYERS("Players", true),
    MOBS("Mobs", false),
    ANIMALS("Animals", false),
    INVISIBLE("Invisible", false);

   public final String labelText;
   public boolean enabled;
   public static final BowAimbotTargetEntity[] ENUM_VALUES = new BowAimbotTargetEntity[]{PLAYERS, MOBS, ANIMALS, INVISIBLE};

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

   private BowAimbotTargetEntity(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
