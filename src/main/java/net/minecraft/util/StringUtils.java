package net.minecraft.util;

import java.util.regex.Pattern;

public class StringUtils {
   private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

   public static boolean isNullOrEmpty(String str) {
      return org.apache.commons.lang3.StringUtils.isEmpty(str);
   }

   public static String stripControlCodes(String str) {
      return patternControlCode.matcher(str).replaceAll("");
   }

   public static String ticksToElapsedTime(int n) {
      int n2 = n / 20;
      int n3 = n2 / 60;
      n2 %= 60;
      return n2 < 10 ? n3 + ":0" + n2 : n3 + ":" + n2;
   }
}
