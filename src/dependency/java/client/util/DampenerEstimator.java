package client.util;

import java.util.Stack;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DampenerEstimator {
   public static final Logger logger = LogManager.getLogger();
   public static final Pattern pattern = Pattern.compile("\\[[-+\\d|,\\s]+\\]");

   public static NBTTagCompound resolveNBTTagCompound(String text) throws SecondaryExceptionAdapter {
      String text2 = text.trim();
      if (!text2.startsWith("{")) {
         throw new SecondaryExceptionAdapter("Invalid tag encountered, expected '{' as first char.");
      } else if (computeCount(text2) != 1) {
         throw new SecondaryExceptionAdapter("Encountered multiple top tags, only one expected");
      } else {
         return (NBTTagCompound)resolveAbstractLISTManagerRecord("tag", text2).getLISTManager();
      }
   }

   private static AbstractLISTManagerRecord resolveAbstractLISTManagerRecord(String text, boolean flag) throws SecondaryExceptionAdapter {
      String text2 = resolveText2(text, flag);
      String text3 = resolveTextByText(text, flag);
      return resolveAbstractLISTManagerRecord(new String[]{text2, text3});
   }

   private static String resolveTextByText(String text, boolean flag) throws SecondaryExceptionAdapter {
      String text2 = text;
      if (flag) {
         text2 = text.trim();
         if (text2.startsWith("{") || text2.startsWith("[")) {
            return text2;
         }
      }

      int count = computeCount(text2, ':');
      if (count != -1) {
         return text2.substring(count + 1).trim();
      } else if (flag) {
         return text2;
      } else {
         throw new SecondaryExceptionAdapter("Unable to locate name/value separator for string: " + text2);
      }
   }

   public static AbstractLISTManagerRecord resolveAbstractLISTManagerRecord(String text, String text2) throws SecondaryExceptionAdapter {
      String text3 = text2.trim();
      if (text3.startsWith("{")) {
         text3 = text3.substring(1, text3.length() - 1);
         LISTManagerLISTManagerRecImpl lISTManagerLISTManagerRecImpl = new LISTManagerLISTManagerRecImpl(text);

         while (text3.length() > 0) {
            String text4 = resolveText(text3, true);
            if (text4.length() > 0) {
               lISTManagerLISTManagerRecImpl.list.add(resolveAbstractLISTManagerRecord(text4, false));
            }

            if (text3.length() < text4.length() + 1) {
               break;
            }

            char character = text3.charAt(text4.length());
            if (character != ',' && character != '{' && character != '}' && character != '[' && character != ']') {
               throw new SecondaryExceptionAdapter("Unexpected token '" + character + "' at: " + text3.substring(text4.length()));
            }

            text3 = text3.substring(text4.length() + 1);
         }

         return lISTManagerLISTManagerRecImpl;
      } else if (text3.startsWith("[") && !pattern.matcher(text3).matches()) {
         text3 = text3.substring(1, text3.length() - 1);
         LISTManagerLISTManagerRec lISTManagerLISTManagerRec = new LISTManagerLISTManagerRec(text);

         while (text3.length() > 0) {
            String text5 = resolveText(text3, false);
            if (text5.length() > 0) {
               lISTManagerLISTManagerRec.list.add(resolveAbstractLISTManagerRecord(text5, true));
            }

            if (text3.length() < text5.length() + 1) {
               break;
            }

            char character2 = text3.charAt(text5.length());
            if (character2 != ',' && character2 != '{' && character2 != '}' && character2 != '[' && character2 != ']') {
               throw new SecondaryExceptionAdapter("Unexpected token '" + character2 + "' at: " + text3.substring(text5.length()));
            }

            text3 = text3.substring(text5.length() + 1);
         }

         return lISTManagerLISTManagerRec;
      } else {
         return new RuntimeComponentContext(text, text3);
      }
   }

   private static int computeCount(String text, char character) {
      int count = 0;

      for (boolean flag = true; count < text.length(); count++) {
         char character2 = text.charAt(count);
         if (character2 == '"') {
            if (!matchesText(text, count)) {
               flag = !flag;
            }
         } else if (flag) {
            if (character2 == character) {
               return count;
            }

            if (character2 == '{' || character2 == '[') {
               return -1;
            }
         }
      }

      return -1;
   }

   private static String resolveText(String text, boolean flag) throws SecondaryExceptionAdapter {
      int count = computeCount(text, ':');
      int count2 = computeCount(text, ',');
      if (flag) {
         if (count == -1) {
            throw new SecondaryExceptionAdapter("Unable to locate name/value separator for string: " + text);
         }

         if (count2 != -1 && count2 < count) {
            throw new SecondaryExceptionAdapter("Name error at: " + text);
         }
      } else if (count == -1 || count > count2) {
         count = -1;
      }

      return resolveText(text, count);
   }

   public static int computeCount(String text) throws SecondaryExceptionAdapter {
      int count = 0;
      boolean flag = false;
      Stack stack = new Stack();

      for (int index = 0; index < text.length(); index++) {
         char character = text.charAt(index);
         if (character == '"') {
            if (matchesText(text, index)) {
               if (!flag) {
                  throw new SecondaryExceptionAdapter("Illegal use of \\\": " + text);
               }
            } else {
               flag = !flag;
            }
         } else if (!flag) {
            if (character != '{' && character != '[') {
               if (character == '}' && (stack.isEmpty() || (Character)stack.pop() != '{')) {
                  throw new SecondaryExceptionAdapter("Unbalanced curly brackets {}: " + text);
               }

               if (character == ']' && (stack.isEmpty() || (Character)stack.pop() != '[')) {
                  throw new SecondaryExceptionAdapter("Unbalanced square brackets []: " + text);
               }
            } else {
               if (stack.isEmpty()) {
                  count++;
               }

               stack.push(character);
            }
         }
      }

      if (flag) {
         throw new SecondaryExceptionAdapter("Unbalanced quotation: " + text);
      } else if (!stack.isEmpty()) {
         throw new SecondaryExceptionAdapter("Unbalanced brackets: " + text);
      } else {
         if (count == 0 && !text.isEmpty()) {
            count = 1;
         }

         return count;
      }
   }

   private static String resolveText2(String text, boolean flag) throws SecondaryExceptionAdapter {
      String text2 = text;
      if (flag) {
         text2 = text.trim();
         if (text2.startsWith("{") || text2.startsWith("[")) {
            return "";
         }
      }

      int count = computeCount(text2, ':');
      if (count != -1) {
         return text2.substring(0, count).trim();
      } else if (flag) {
         return "";
      } else {
         throw new SecondaryExceptionAdapter("Unable to locate name/value separator for string: " + text2);
      }
   }

   private static boolean matchesText(String text, int count) {
      return count > 0 && text.charAt(count - 1) == '\\' && !matchesText(text, count - 1);
   }

   private static String resolveText(String text, int count) throws SecondaryExceptionAdapter {
      Stack stack = new Stack();
      int count2 = count + 1;
      boolean flag = false;
      boolean flag2 = false;
      boolean flag3 = false;

      for (int index = 0; count2 < text.length(); count2++) {
         char character = text.charAt(count2);
         if (character == '"') {
            if (matchesText(text, count2)) {
               if (!flag) {
                  throw new SecondaryExceptionAdapter("Illegal use of \\\": " + text);
               }
            } else {
               flag = !flag;
               if (flag && !flag3) {
                  flag2 = true;
               }

               if (!flag) {
                  index = count2;
               }
            }
         } else if (!flag) {
            if (character != '{' && character != '[') {
               if (character == '}' && (stack.isEmpty() || (Character)stack.pop() != '{')) {
                  throw new SecondaryExceptionAdapter("Unbalanced curly brackets {}: " + text);
               }

               if (character == ']' && (stack.isEmpty() || (Character)stack.pop() != '[')) {
                  throw new SecondaryExceptionAdapter("Unbalanced square brackets []: " + text);
               }

               if (character == ',' && stack.isEmpty()) {
                  return text.substring(0, count2);
               }
            } else {
               stack.push(character);
            }
         }

         if (!Character.isWhitespace(character)) {
            if (!flag && flag2 && index != count2) {
               return text.substring(0, index + 1);
            }

            flag3 = true;
         }
      }

      return text.substring(0, count2);
   }

   public static AbstractLISTManagerRecord resolveAbstractLISTManagerRecord(String[] textArray) throws SecondaryExceptionAdapter {
      return resolveAbstractLISTManagerRecord(textArray[0], textArray[1]);
   }
}
