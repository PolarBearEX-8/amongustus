package client.setting;

public enum MovementFilterProperty {
    TITLE,
    SUBTITLE,
    TIMES,
    CLEAR,
    RESET;

   public static final MovementFilterProperty[] ENUM_VALUES = new MovementFilterProperty[]{TITLE, SUBTITLE, TIMES, CLEAR, RESET};

   public static String[] getTextArray() {
      String[] textArray = new String[values().length];
      int count = 0;

      for (MovementFilterProperty movementFilterProperty : values()) {
         textArray[count++] = movementFilterProperty.name().toLowerCase();
      }

      return textArray;
   }

   public static MovementFilterProperty resolveMovementFilterProperty(String text) {
      for (MovementFilterProperty movementFilterProperty : values()) {
         if (movementFilterProperty.name().equalsIgnoreCase(text)) {
            return movementFilterProperty;
         }
      }

      return TITLE;
   }


}
