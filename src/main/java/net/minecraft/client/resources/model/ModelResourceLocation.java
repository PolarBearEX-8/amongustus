package net.minecraft.client.resources.model;

import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

public class ModelResourceLocation extends ResourceLocation {
   private final String variant;

   public ModelResourceLocation(ResourceLocation location, String str) {
      this(location.toString(), str);
   }

   public ModelResourceLocation(int n, String[] strings) {
      super(0, new String[]{strings[0], strings[1]});
      this.variant = StringUtils.isEmpty(strings[2]) ? "normal" : strings[2].toLowerCase();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof ModelResourceLocation && super.equals(obj)) {
         ModelResourceLocation modelResourceLocation = (ModelResourceLocation)obj;
         return this.variant.equals(modelResourceLocation.variant);
      } else {
         return false;
      }
   }

   public static String[] parsePathString(String str) {
      String[] strings = new String[]{null, str, null};
      int n = str.indexOf(35);
      String str2 = str;
      if (n >= 0) {
         strings[2] = str.substring(n + 1);
         if (n > 1) {
            str2 = str.substring(0, n);
         }
      }

      System.arraycopy(ResourceLocation.splitObjectName(str2), 0, strings, 0, 2);
      return strings;
   }

   @Override
   public String toString() {
      return super.toString() + '#' + this.variant;
   }

   @Override
   public int hashCode() {
      return 31 * super.hashCode() + this.variant.hashCode();
   }

   public ModelResourceLocation(String str, String str2) {
      this(0, parsePathString(str + '#' + (str2 == null ? "normal" : str2)));
   }

   public String getVariant() {
      return this.variant;
   }

   public ModelResourceLocation(String str) {
      this(0, parsePathString(str));
   }
}
