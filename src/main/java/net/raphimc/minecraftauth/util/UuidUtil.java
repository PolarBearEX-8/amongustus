package net.raphimc.minecraftauth.util;

import java.util.UUID;

public class UuidUtil {
   private static final String UNDASHED_UUID_REGEX = "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)";
   private static final String DASHED_UUID_REGEX = "(\\p{XDigit}{8})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}+)";

   public static UUID fromLenientString(String s) {
      return s == null
         ? null
         : UUID.fromString(s.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"));
   }

   public static boolean isUndashedUuid(String s) {
      return s.matches("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)");
   }

   public static boolean isDashedUuid(String s) {
      return s.matches("(\\p{XDigit}{8})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}+)");
   }
}
