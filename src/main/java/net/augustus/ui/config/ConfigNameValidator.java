package net.augustus.ui.config;

import java.util.HashSet;
import java.util.Set;

public class ConfigNameValidator {
   private static final Set ALLOWED_CHARACTERS = new HashSet();

   static {
      for (char i = 'a'; i <= 'z'; i++) {
         ALLOWED_CHARACTERS.add(i);
      }

      for (char j = 'A'; j <= 'Z'; j++) {
         ALLOWED_CHARACTERS.add(j);
      }

      for (char k = '0'; k <= '9'; k++) {
         ALLOWED_CHARACTERS.add(k);
      }


      for (char ch : new char[]{'ä', 'ö', 'ü', 'Ä', 'Ö', 'Ü'}) {
         ALLOWED_CHARACTERS.add(ch);
      }


      for (char ch2 : new char[]{'-', '_', ' '}) {
         ALLOWED_CHARACTERS.add(ch2);
      }
   }

   public static boolean isValidConfigName(String str) {
      boolean flag = false;

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (!ALLOWED_CHARACTERS.contains(ch)) {
            return false;
         }
      }

      return true;
   }
}
