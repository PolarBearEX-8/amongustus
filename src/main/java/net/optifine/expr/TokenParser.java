package net.optifine.expr;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;

public class TokenParser {

   private static Token readToken(char ch, TokenType tokenType, PushbackReader pushbackReader) throws IOException {
      StringBuffer sb = new StringBuffer();
      sb.append(ch);

      while (true) {
         int n = pushbackReader.read();
         if (n < 0) {
            break;
         }

         char ch2 = (char)n;
         if (!tokenType.hasCharNext(ch2)) {
            pushbackReader.unread(ch2);
            break;
         }

         sb.append(ch2);
      }

      return new Token(tokenType, sb.toString());
   }

   public static Token[] parse(String str) throws IOException, ParseException {
      StringReader stringReader = new StringReader(str);
      PushbackReader pushbackReader = new PushbackReader(stringReader);
      ArrayList list = new ArrayList();

      while (true) {
         int n = pushbackReader.read();
         if (n < 0) {
            return (Token[])list.toArray(new Token[list.size()]);
         }

         char ch = (char)n;
         if (!Character.isWhitespace(ch)) {
            TokenType typeByFirstChar = TokenType.getTypeByFirstChar(ch);
            if (typeByFirstChar == null) {
               throw new ParseException("Invalid character: '" + ch + "', in: " + str);
            }

            list.add(readToken(ch, typeByFirstChar, pushbackReader));
         }
      }
   }
}
