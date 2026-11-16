package client.setting;

public enum MovementLimitProperty {
    NEGATIVE(-1, "Towards negative"),
    POSITIVE(1, "Towards positive");

   public final String labelText;
   public final int count;
   public static final MovementLimitProperty[] ENUM_VALUES = new MovementLimitProperty[]{POSITIVE, NEGATIVE};

   public int getCount() {
      return this.count;
   }

   private MovementLimitProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
