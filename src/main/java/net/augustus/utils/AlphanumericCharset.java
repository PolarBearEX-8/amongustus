package net.augustus.utils;

public class AlphanumericCharset {
   public static final String DIGITS = null;
   public static final String LETTERS = null;

   private AlphanumericCharset() {
   }

   static {
      String[] strings = new String[]{"0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"};
   }
}
