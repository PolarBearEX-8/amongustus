package net.minecraft.util;

public class IntegerCache {
   private static final Integer[] CACHE = new Integer[65535];

   static {
      int n = 0;

      for (int i = CACHE.length; n < i; n++) {
         CACHE[n] = n;
      }
   }

   public static Integer getInteger(int n) {
      return n >= 0 && n < CACHE.length ? CACHE[n] : new Integer(n);
   }
}
