package net.minecraft.client.resources;

import java.util.Map;

public class I18n {
   private static Locale i18nLocale;

   public static Map getLocaleProperties() {
      return i18nLocale.properties;
   }

   public static void setLocale(Locale locale) {
      i18nLocale = locale;
   }

   public static String format(String str, Object[] array) {
      return i18nLocale.formatMessage(str, array);
   }
}
