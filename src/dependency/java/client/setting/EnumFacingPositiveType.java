package client.setting;

public enum EnumFacingPositiveType {
    POSITIVE(1, "Towards positive"),
    NEGATIVE(-1, "Towards negative");

   public final int count;
   public final String labelText;
   public static final EnumFacingPositiveType[] ENUM_VALUES = new EnumFacingPositiveType[]{POSITIVE, NEGATIVE};

   public int getCount() {
      return this.count;
   }

   private EnumFacingPositiveType(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
