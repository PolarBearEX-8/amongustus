package net.minecraft.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class ResourceLocation {
   public final String text;
   public final String text1;

   public String getText() {
      return this.text;
   }

   public static String[] resolveTextArray(String text2) {
      String[] textArray = new String[]{null, text2};
      int count = text2.indexOf(58);
      if (count >= 0) {
         textArray[1] = text2.substring(count + 1);
         if (count > 1) {
            textArray[0] = text2.substring(0, count);
         }
      }

      return textArray;
   }

   public String getText1() {
      return this.text1;
   }

   public ResourceLocation(String text2, String text3) {
      this(0, new String[]{text2, text3});
   }

   public ResourceLocation(int count, String[] textArray) {
      this.text = StringUtils.isEmpty(textArray[0]) ? "minecraft" : textArray[0].toLowerCase();
      this.text1 = textArray[1];
      Validate.notNull(this.text1);
   }

   public ResourceLocation(String text2) {
      this(0, resolveTextArray(text2));
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ResourceLocation)) {
         return false;
      } else {
         ResourceLocation location = (ResourceLocation)other;
         return this.text.equals(location.text) && this.text1.equals(location.text1);
      }
   }

   @Override
   public String toString() {
      return this.text + ':' + this.text1;
   }

   @Override
   public int hashCode() {
      return 31 * this.text.hashCode() + this.text1.hashCode();
   }
}
