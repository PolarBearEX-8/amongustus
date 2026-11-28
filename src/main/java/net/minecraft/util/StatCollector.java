package net.minecraft.util;

public class StatCollector {
   private static StringTranslate localizedName = StringTranslate.getInstance();
   private static StringTranslate fallbackTranslator = new StringTranslate();

   public static long getLastTranslationUpdateTimeInMilliseconds() {
      return localizedName.getLastUpdateTimeInMilliseconds();
   }

   public static String translateToLocalFormatted(String str, Object[] array) {
      return localizedName.translateKeyFormat(str, array);
   }

   public static String translateToFallback(String str) {
      return fallbackTranslator.translateKey(str);
   }

   public static boolean canTranslate(String str) {
      return localizedName.isKeyTranslated(str);
   }

   public static String translateToLocal(String str) {
      return localizedName.translateKey(str);
   }
}
