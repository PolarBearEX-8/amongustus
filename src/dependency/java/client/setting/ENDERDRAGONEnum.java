package client.setting;

public enum ENDERDRAGONEnum implements NamedOption {
    FLASH("Flash"),
    ENDER_DRAGON_DEATH_SOUND("DragonDeathSound"),
    FIRE("Fire");

   public final String labelText;
   public boolean enabled;
   public static final ENDERDRAGONEnum[] ENUM_VALUES = new ENDERDRAGONEnum[]{FLASH, ENDER_DRAGON_DEATH_SOUND, FIRE};

   private ENDERDRAGONEnum(String text) {
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
