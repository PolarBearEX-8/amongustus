package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class ResourceLocation {
   public final String resourceDomain;
   public final String resourcePath;

   public ResourceLocation(String str, String str2) {
      this(0, new String[]{str, str2});
   }

   public ResourceLocation(int n, String[] strings) {
      this.resourceDomain = org.apache.commons.lang3.StringUtils.isEmpty(strings[0]) ? "minecraft" : strings[0].toLowerCase();
      this.resourcePath = strings[1];
      Validate.notNull(this.resourcePath);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ResourceLocation)) {
         return false;
      } else {
         ResourceLocation location = (ResourceLocation)obj;
         return this.resourceDomain.equals(location.resourceDomain) && this.resourcePath.equals(location.resourcePath);
      }
   }

   @Override
   public String toString() {
      return this.resourceDomain + ':' + this.resourcePath;
   }

   @Override
   public int hashCode() {
      return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
   }

   public ResourceLocation(String str) {
      this(0, splitObjectName(str));
   }

   public static String[] splitObjectName(String str) {
      String[] strings = new String[]{null, str};
      int n = str.indexOf(58);
      if (n >= 0) {
         strings[1] = str.substring(n + 1);
         if (n > 1) {
            strings[0] = str.substring(0, n);
         }
      }

      return strings;
   }

   public String getResourcePath() {
      return this.resourcePath;
   }

   public String getResourceDomain() {
      return this.resourceDomain;
   }
}
