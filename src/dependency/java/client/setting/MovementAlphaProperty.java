package client.setting;

public enum MovementAlphaProperty {
    FILE("file"),
    SOUND_EVENT("event");

   public final String labelText;
   public static final MovementAlphaProperty[] ENUM_VALUES = new MovementAlphaProperty[]{FILE, SOUND_EVENT};

   public static MovementAlphaProperty resolveMovementAlphaProperty(String text) {
      for (MovementAlphaProperty movementAlphaProperty : values()) {
         if (movementAlphaProperty.labelText.equals(text)) {
            return movementAlphaProperty;
         }
      }

      return null;
   }

   private MovementAlphaProperty(String text) {
      this.labelText = text;
   }


}
