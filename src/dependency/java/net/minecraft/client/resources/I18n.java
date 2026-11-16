package net.minecraft.client.resources;

import client.util.FormatErrorManager;

public class I18n {
   public static FormatErrorManager formatErrorManager2 = FormatErrorManager.getFormatErrorManager();
   public static FormatErrorManager formatErrorManager = new FormatErrorManager();

   public static String resolveText(String text) {
      return formatErrorManager.resolveText(text);
   }

   public static String getString2(String text) {
      return formatErrorManager2.resolveText(text);
   }

   public static long getTimestamp() {
      return formatErrorManager2.getTimestamp();
   }

   public static String resolveText(String text, Object[] entryArray) {
      return formatErrorManager2.resolveText(text, entryArray);
   }

   public static boolean matchesText(String text) {
      return formatErrorManager2.containsKey(text);
   }
}
