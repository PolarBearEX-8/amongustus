package client.util;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;

public class CharacterHelper {
   public static ABCDEFGHIJKLMNOPQREnController[] resolveABCDEFGHIJKLMNOPQREnControll(String text) throws IOException, AuxiliaryExceptionAdapter {
      StringReader stringReader = new StringReader(text);
      PushbackReader pushbackReader = new PushbackReader(stringReader);
      ArrayList list = new ArrayList();

      while (true) {
         int count = pushbackReader.read();
         if (count < 0) {
            return (ABCDEFGHIJKLMNOPQREnController[])list.toArray(new ABCDEFGHIJKLMNOPQREnController[list.size()]);
         }

         char character = (char)count;
         if (!Character.isWhitespace(character)) {
            ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum = ABCDEFGHIJKLMNOPQREnum.resolveABCDEFGHIJKLMNOPQREnum(character);
            if (aBCDEFGHIJKLMNOPQREnum == null) {
               throw new AuxiliaryExceptionAdapter("Invalid character: '" + character + "', in: " + text);
            }

            ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController = resolveABCDEFGHIJKLMNOPQREnControll(character, aBCDEFGHIJKLMNOPQREnum, pushbackReader);
            list.add(aBCDEFGHIJKLMNOPQREnController);
         }
      }
   }

   private static ABCDEFGHIJKLMNOPQREnController resolveABCDEFGHIJKLMNOPQREnControll(char character, ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum, PushbackReader pushbackReader) throws IOException {
      StringBuffer builder = new StringBuffer();
      builder.append(character);

      while (true) {
         int count = pushbackReader.read();
         if (count < 0) {
            break;
         }

         char character2 = (char)count;
         if (!aBCDEFGHIJKLMNOPQREnum.indexOf(character2)) {
            pushbackReader.unread(character2);
            break;
         }

         builder.append(character2);
      }

      return new ABCDEFGHIJKLMNOPQREnController(aBCDEFGHIJKLMNOPQREnum, builder.toString());
   }
}
