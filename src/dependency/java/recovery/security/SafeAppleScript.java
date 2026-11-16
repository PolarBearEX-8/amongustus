package recovery.security;

public final class SafeAppleScript {
   private SafeAppleScript() {
   }

   public static String escape(String text) {
      if (text == null) {
         return "null";
      } else {
         StringBuilder builder = new StringBuilder(text.length());

         for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            switch (character) {
               case '\t':
                  builder.append("\\t");
                  break;
               case '\n':
                  builder.append("\\n");
                  break;
               case '\r':
                  builder.append("\\r");
                  break;
               case '"':
                  builder.append("\\\"");
                  break;
               case '\\':
                  builder.append("\\\\");
                  break;
               default:
                  if (character >= ' ' && character != 127) {
                     builder.append(character);
                  } else {
                     builder.append(' ');
                  }
            }
         }

         return builder.toString();
      }
   }
}
