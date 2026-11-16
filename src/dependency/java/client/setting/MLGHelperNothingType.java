package client.setting;

public enum MLGHelperNothingType {
    NOTHING(null),
    IMPOSSIBLE("Impossible"),
    WALK("Walk"),
    JUMP("Jump"),
    BOTH("Both");

   public final String labelText;
   public static final MLGHelperNothingType[] ENUM_VALUES = new MLGHelperNothingType[]{NOTHING, IMPOSSIBLE, WALK, JUMP, BOTH};

   private MLGHelperNothingType(String text) {
      this.labelText = text;
   }

   public String getLabelText() {
      return this.labelText;
   }


}
