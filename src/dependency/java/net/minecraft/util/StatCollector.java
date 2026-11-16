package net.minecraft.util;

import client.util.LangSLangManager;
import java.util.Map;

public class StatCollector {
   public static LangSLangManager langSLangManager;

   public static Map getMap() {
      return langSLangManager.map;
   }

   public static void setLangSLangManager(LangSLangManager langSLangManager2) {
      langSLangManager = langSLangManager2;
   }

   public static String getText(String text, Object[] entryArray) {
      return langSLangManager.resolveText(text, entryArray);
   }
}
