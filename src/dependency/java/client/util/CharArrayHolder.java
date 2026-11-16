package client.util;

public class CharArrayHolder {
   public static final char[] charCodeArray = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};

   public static String resolveText(String text) {
      StringBuilder builder = new StringBuilder();

      for (char character : text.toCharArray()) {
         if (checkChar(character)) {
            builder.append(character);
         }
      }

      return builder.toString();
   }

   public static boolean checkChar(char character) {
      return character != 167 && character >= ' ' && character != 127;
   }
}
