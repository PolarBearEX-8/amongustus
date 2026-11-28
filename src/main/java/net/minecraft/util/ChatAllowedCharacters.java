package net.minecraft.util;

public class ChatAllowedCharacters {
   public static final char[] allowedCharactersArray = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};

   public static boolean isAllowedCharacter(char ch) {
      return ch != 167 && ch >= ' ' && ch != 127;
   }

   public static String filterAllowedCharacters(String str) {
      StringBuilder sb = new StringBuilder();

      for (char ch : str.toCharArray()) {
         if (isAllowedCharacter(ch)) {
            sb.append(ch);
         }
      }

      return sb.toString();
   }
}
