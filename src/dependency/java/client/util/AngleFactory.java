package client.util;

import org.lwjgl.util.vector.Vector4f;

public class AngleFactory {
   public int count;
   public String labelText;
   public String labelText2;
   public String labelText3;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public static final int count4 = 3;
   public static final int count5 = 4;
   public static final int count6 = 5;
   public static final int count7 = 6;
   public static final int count8 = 7;
   public static final int count9 = 8;

   public boolean isActive7() {
      return this.count == 1;
   }

   public int getCountAsIndex() {
      try {
         return Integer.parseInt(this.labelText2);
      } catch (NumberFormatException numberFormatException) {
         throw new NumberFormatException("Invalid integer: " + this.labelText2 + ", line: " + this.labelText3);
      }
   }

   public boolean matchesText(String text, String text2, boolean flag) {
      return this.matchesText(text, text2) && this.isActive4() == flag;
   }

   public boolean isActive8() {
      return this.count == 7;
   }

   public boolean matchesTextByText(String text) {
      return this.isActive() && this.labelText.endsWith(text);
   }

   public boolean matchesText(String text, String text2, String text3) {
      return this.matchesText6(text) || this.matchesText6(text2) || this.matchesText6(text3);
   }

   public boolean isActive6() {
      return this.count == 4;
   }

   public AngleFactory(int count10, String text, String text2, String text3) {
      this.count = count10;
      this.labelText = text;
      this.labelText2 = text2;
      this.labelText3 = text3;
   }

   public boolean matchesText3(String text) {
      return this.isActive7() && text.equals(this.labelText);
   }

   public boolean matchesText(String text, String text2, String text3, boolean flag) {
      return this.matchesText(text, text2, text3) && this.isActive4() == flag;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isActive2() {
      return this.count == 6;
   }

   public boolean matchesText5(String text) {
      return this.isActiveAsFlag() && text.equals(this.labelText);
   }

   public Vector4f getVector4f() {
      if (this.labelText2 == null) {
         return null;
      } else {
         String text = this.labelText2.trim();
         text = SplineCollector.resolveText2(text, "vec4");
         text = SplineCollector.resolveText5(text, " ()");
         String[] textArray = BuildManager.resolveTextArray(text, ", ");
         if (textArray.length != 4) {
            return null;
         } else {
            float[] factorArray = new float[4];

            for (int index = 0; index < textArray.length; index++) {
               String text2 = textArray[index];
               text2 = SplineCollector.resolveTextByText(text2, new String[]{"F", "f"});
               float factor = BuildManager.computeFactor(text2, Float.MAX_VALUE);
               if (factor == Float.MAX_VALUE) {
                  return null;
               }

               factorArray[index] = factor;
            }

            return new Vector4f(factorArray[0], factorArray[1], factorArray[2], factorArray[3]);
         }
      }
   }

   public boolean matchesTextByText(String text, boolean flag) {
      return this.matchesText7(text) && this.isActive4() == flag;
   }

   public boolean isActive3() {
      return this.count == 5;
   }

   public boolean matchesText9(String text) {
      return this.isActive8() && text.equals(this.labelText);
   }

   public boolean matchesTextByText(String text, String text2) {
      return this.matchesText(text) && text2.equals(this.labelText2);
   }

   public int getCount() {
      return this.count;
   }

   public boolean matchesText6(String text) {
      return this.isActive3() && text.equals(this.labelText);
   }

   public boolean matchesText(String text, String text2) {
      return this.matchesText6(text) || this.matchesText6(text2);
   }

   public boolean matchesText2(String text) {
      return this.isActive5() && this.labelText.endsWith(text);
   }

   public boolean matchesText(String text) {
      return this.isActive2() && text.equals(this.labelText);
   }

   public boolean matchesText(String text, boolean flag) {
      return this.matchesText6(text) && this.isActive4() == flag;
   }

   public float getFactor() {
      try {
         return Float.parseFloat(this.labelText2);
      } catch (NumberFormatException numberFormatException) {
         throw new NumberFormatException("Invalid float: " + this.labelText2 + ", line: " + this.labelText3);
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public boolean matchesText8(String text) {
      return this.isActive6() && text.equals(this.labelText);
   }

   public boolean isActiveAsFlag() {
      return this.count == 2;
   }

   public boolean matchesText4(String text) {
      return this.isActive() && text.equals(this.labelText);
   }

   public boolean matchesText7(String text) {
      return this.isActive3() && this.labelText.endsWith(text);
   }

   public boolean isActive4() {
      String text = this.labelText2.toLowerCase();
      if (!text.equals("true") && !text.equals("false")) {
         throw new RuntimeException("Invalid boolean: " + this.labelText2 + ", line: " + this.labelText3);
      } else {
         return Boolean.valueOf(this.labelText2);
      }
   }

   public boolean isActive() {
      return this.count == 3;
   }

   public boolean isActive5() {
      return this.count == 8;
   }
}
