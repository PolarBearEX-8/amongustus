package client.util;

public class SplineDeserializer {
   public final double value;
   public final String labelText4;
   public final String labelText5;
   public final String labelText6;
   public final String labelText;
   public final String labelText2;
   public final double value2;
   public final String labelText3;
   public final String labelText7;
   public final String labelText8;
   public final boolean enabled;
   public final String labelText9;

   public SplineDeserializer(
      String text,
      String text2,
      String text3,
      String text4,
      String text5,
      double offset,
      double offset2,
      String text6,
      String text7,
      String text8,
      String text9,
      boolean flag
   ) {
      this.labelText7 = text;
      this.labelText2 = text2;
      this.labelText4 = text3;
      this.labelText3 = text4;
      this.labelText = text5;
      this.value = offset;
      this.value2 = offset2;
      this.labelText8 = text6;
      this.labelText6 = text7;
      this.labelText9 = text8;
      this.labelText5 = text9;
      this.enabled = flag;
   }
}
