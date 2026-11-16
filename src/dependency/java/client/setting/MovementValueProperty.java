package client.setting;

public enum MovementValueProperty {
    MICROSOFT_DEVICE_CODE("Device code"),
    CRACKED("Cracked"),
    THE_ALTENING("TheAltening"),
    MICROSOFT_EXTERNAL_BROWSER("External browser"),
    MICROSOFT_CREDENTIALS("Credentials");

   public final String labelText;
   public static final MovementValueProperty[] ENUM_VALUES = new MovementValueProperty[]{
      MovementValueProperty.MICROSOFT_CREDENTIALS,
      MovementValueProperty.MICROSOFT_EXTERNAL_BROWSER,
      MICROSOFT_DEVICE_CODE,
      MovementValueProperty.THE_ALTENING,
      CRACKED
   };

   public String getLabelText() {
      return this.labelText;
   }

   private MovementValueProperty(String text) {
      this.labelText = text;
   }


}
