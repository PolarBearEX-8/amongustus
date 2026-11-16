package client.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class SplineCollector {
   public static String resolveText3(String text, String text2, String text3) {
      return resolveText(text, new String[]{text2}, new String[]{text3});
   }

   public static String resolveText5(String text, String text2) {
      if (text != null && text2 != null) {
         String text3 = resolveText4(text, text2);
         return resolveText7(text3, text2);
      } else {
         return text;
      }
   }

   public static String resolveTextByText(String text, String text2) {
      return text == null || text2 == null ? text : (text.endsWith(text2) ? text : text2 + text);
   }

   private static int computeCount(String text, String text2, int count, char character) {
      if (text != null && text2 != null) {
         if (count >= 0 && count <= text.length()) {
            if (text.length() < count + text2.length()) {
               return -1;
            } else {
               for (int index = count; index + text2.length() <= text.length(); index++) {
                  if (matchesText(text.substring(index, index + text2.length()), text2, character)) {
                     return index;
                  }
               }

               return -1;
            }
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public static int computeCount(String[] textArray, String text) {
      if (textArray != null && text != null) {
         for (int index = 0; index < textArray.length; index++) {
            if (textArray[index].startsWith(text)) {
               return index;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static String resolveText(String text, String[] textArray) {
      String text2 = text;
      if (text != null && textArray != null) {
         int length = text.length();

         for (int index = 0; index < textArray.length; index++) {
            String text3 = textArray[index];
            text2 = resolveText2(text2, text3);
            if (text2.length() != length) {
               break;
            }
         }

         return text2;
      } else {
         return text;
      }
   }

   private static boolean matchesTextByText(String text, String text2, char character) {
      if (text != null && text2 != null) {
         return text.length() < text2.length() ? false : matchesText(text.substring(text.length() - text2.length(), text.length()), text2, character);
      } else {
         return text == text2;
      }
   }

   public static String resolveText(String text, String text2) {
      String text3 = text;

      for (int index = 0; index < text2.length(); index++) {
         if (text3.indexOf(text2.charAt(index)) < 0) {
            text3 = text3 + text2.charAt(index);
         }
      }

      return text3;
   }

   public static String[] resolveTextArray(String text, String text2) {
      if (text != null && text.length() > 0) {
         if (text2 == null) {
            return new String[]{text};
         } else {
            ArrayList list = new ArrayList();
            int count = 0;

            for (int index = 0; index < text.length(); index++) {
               if (matchesText(text.charAt(index), text2)) {
                  list.add(text.substring(count, index));
                  count = index + 1;
               }
            }

            list.add(text.substring(count, text.length()));
            return (String[])list.toArray(new String[list.size()]);
         }
      } else {
         return new String[0];
      }
   }

   private static boolean matchesText(String text, String text2, char character) {
      if (text != null && text2 != null) {
         if (text.length() != text2.length()) {
            return false;
         } else {
            for (int index = 0; index < text2.length(); index++) {
               char character2 = text2.charAt(index);
               if (character2 != character && text.charAt(index) != character2) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return text == text2;
      }
   }

   public static String resolveText(String text, String text2, String text3) {
      String text4 = text3;
      if (text == null || text2 == null) {
         return text;
      } else if (!text.startsWith(text2)) {
         return text;
      } else {
         if (text3 == null) {
            text4 = "";
         }

         String text5 = text.substring(text2.length());
         return text4 + text5;
      }
   }

   public static boolean matchesText(String text, String[] textArray) {
      if (text == null) {
         return false;
      } else if (textArray == null) {
         return false;
      } else {
         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text.endsWith(text2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static String resolveTextByText(String text, String text2, String text3) {
      String text4 = text3;
      if (text == null || text2 == null) {
         return text;
      } else if (!text.endsWith(text2)) {
         return text;
      } else {
         if (text3 == null) {
            text4 = "";
         }

         String text5 = text.substring(0, text.length() - text2.length());
         return text5 + text4;
      }
   }

   public static String resolveText3(String text, String text2) {
      String text3 = text;
      if (text != null && text2 != null && text.endsWith(text2)) {
         text3 = text.substring(0, text.length() - text2.length());
      }

      return text3;
   }

   public static boolean matchesTarget(Object entry, Object entry2) {
      return entry == entry2 ? true : (entry != null && entry.equals(entry2) ? true : entry2 != null && entry2.equals(entry));
   }

   public static boolean matchesText(String text) {
      return text == null ? true : text.trim().length() <= 0;
   }

   public static int parseInt(String text, int count) {
      if (text == null) {
         return count;
      } else {
         try {
            return Integer.parseInt(text);
         } catch (NumberFormatException numberFormatException) {
            return count;
         }
      }
   }

   public static boolean matchesTextByText(String text) {
      return !matchesText(text);
   }

   public static String resolveText7(String text, String text2) {
      if (text != null && text2 != null) {
         int length = text.length();

         int count;
         for (count = length; count > 0; count--) {
            char character = text.charAt(count - 1);
            if (text2.indexOf(character) < 0) {
               break;
            }
         }

         return count == length ? text : text.substring(0, count);
      } else {
         return text;
      }
   }

   public static boolean matchesTextByText(String text, String[] textArray) {
      if (text == null) {
         return false;
      } else if (textArray == null) {
         return false;
      } else {
         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text.startsWith(text2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static int computeCountByTextArray(String[] textArray, String text) {
      if (textArray != null && text != null) {
         for (int index = 0; index < textArray.length; index++) {
            if (textArray[index].endsWith(text)) {
               return index;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static boolean matchesText(String text, String text2) {
      String text3 = text;
      String text4 = text2;
      if (text != null) {
         text3 = text.trim();
      }

      if (text2 != null) {
         text4 = text2.trim();
      }

      return matchesTarget(text3, text4);
   }

   public static String resolveText(String text, String[] textArray, String[] textArray2) {
      String text2 = resolveText(text, textArray);
      return resolveTextByText(text2, textArray2);
   }

   public static boolean matchesText2(String text, String text2, char character) {
      if (text2 != null && text != null) {
         if (text2.indexOf(character) < 0) {
            return text2.equals(text);
         } else {
            ArrayList list = new ArrayList();
            String text3 = "" + character;
            if (text2.startsWith(text3)) {
               list.add("");
            }

            StringTokenizer stringTokenizer = new StringTokenizer(text2, text3);

            while (stringTokenizer.hasMoreElements()) {
               list.add(stringTokenizer.nextToken());
            }

            if (text2.endsWith(text3)) {
               list.add("");
            }

            String text4 = (String)list.get(0);
            if (!text.startsWith(text4)) {
               return false;
            } else {
               String text5 = (String)list.get(list.size() - 1);
               if (!text.endsWith(text5)) {
                  return false;
               } else {
                  int count = 0;

                  for (int index = 0; index < list.size(); index++) {
                     String text6 = (String)list.get(index);
                     if (text6.length() > 0) {
                        int count2 = text.indexOf(text6, count);
                        if (count2 < 0) {
                           return false;
                        }

                        count = count2 + text6.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return text2 == text;
      }
   }

   public static String resolveText2(String text, String text2, String text3) {
      if (text != null && text2 != null && text3 != null) {
         int count = text.indexOf(text2);
         if (count < 0) {
            return null;
         } else {
            int count2 = text.indexOf(text3, count);
            return count2 < 0 ? null : text.substring(count, count2 + text3.length());
         }
      } else {
         return null;
      }
   }

   public static String resolveText4(String text, String text2) {
      if (text != null && text2 != null) {
         int length = text.length();

         for (int index = 0; index < length; index++) {
            char character = text.charAt(index);
            if (text2.indexOf(character) < 0) {
               return text.substring(index);
            }
         }

         return "";
      } else {
         return text;
      }
   }

   public static String resolveTextByText(String text, String[] textArray) {
      String text2 = text;
      if (text != null && textArray != null) {
         int length = text.length();

         for (int index = 0; index < textArray.length; index++) {
            String text3 = textArray[index];
            text2 = resolveText3(text2, text3);
            if (text2.length() != length) {
               break;
            }
         }

         return text2;
      } else {
         return text;
      }
   }

   public static String resolveTextByText(String text, int count, char character) {
      String text2 = text;
      if (text == null) {
         text2 = "";
      }

      if (text2.length() >= count) {
         return text2;
      } else {
         StringBuffer builder = new StringBuffer(text2);

         while (builder.length() < count) {
            builder.append(character);
         }

         return builder.toString();
      }
   }

   public static String resolveText6(String text, String text2) {
      return text == null || text2 == null ? text : (text.endsWith(text2) ? text : text + text2);
   }

   private static boolean matchesText3(String text, String text2, char character) {
      if (text != null && text2 != null) {
         return text.length() < text2.length() ? false : matchesText(text.substring(0, text2.length()), text2, character);
      } else {
         return text == text2;
      }
   }

   private static boolean matchesText(char character, String text) {
      for (int index = 0; index < text.length(); index++) {
         if (text.charAt(index) == character) {
            return true;
         }
      }

      return false;
   }

   public static String[] resolveTextArray(String[] textArray, int count, int count2) {
      if (textArray == null) {
         return textArray;
      } else if (count2 > 0 && count < textArray.length) {
         if (count >= count2) {
            return textArray;
         } else {
            ArrayList list = new ArrayList(textArray.length);

            for (int index = 0; index < textArray.length; index++) {
               String text = textArray[index];
               if (index < count || index >= count2) {
                  list.add(text);
               }
            }

            return (String[])list.toArray(new String[list.size()]);
         }
      } else {
         return textArray;
      }
   }

   public static boolean matchesText(String text, String[] textArray, char character) {
      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         if (matchesText2(text, text2, character)) {
            return true;
         }
      }

      return false;
   }

   public static String resolveText2(String text, String text2) {
      String text3 = text;
      if (text != null && text2 != null) {
         if (text.startsWith(text2)) {
            text3 = text.substring(text2.length());
         }

         return text3;
      } else {
         return text;
      }
   }

   public static String resolveText(String text) {
      int count = parseInt(text, -1);
      if (count == -1) {
         return "";
      } else {
         count++;
         return ("" + count).length() > text.length() ? "" : resolveText("" + count, text.length(), '0');
      }
   }

   public static boolean matchesText(String text, String text2, char character, char character2) {
      if (text2 != null && text != null) {
         if (text2.indexOf(character) < 0) {
            return text2.indexOf(character2) < 0 ? text2.equals(text) : matchesText(text, text2, character2);
         } else {
            ArrayList list = new ArrayList();
            String text3 = "" + character;
            if (text2.startsWith(text3)) {
               list.add("");
            }

            StringTokenizer stringTokenizer = new StringTokenizer(text2, text3);

            while (stringTokenizer.hasMoreElements()) {
               list.add(stringTokenizer.nextToken());
            }

            if (text2.endsWith(text3)) {
               list.add("");
            }

            String text4 = (String)list.get(0);
            if (!matchesText3(text, text4, character2)) {
               return false;
            } else {
               String text5 = (String)list.get(list.size() - 1);
               if (!matchesTextByText(text, text5, character2)) {
                  return false;
               } else {
                  int count = 0;

                  for (int index = 0; index < list.size(); index++) {
                     String text6 = (String)list.get(index);
                     if (text6.length() > 0) {
                        int count2 = computeCount(text, text6, count, character2);
                        if (count2 < 0) {
                           return false;
                        }

                        count = count2 + text6.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return text2 == text;
      }
   }

   public static String resolveText(String text, int count, char character) {
      String text2 = text;
      if (text == null) {
         text2 = "";
      }

      if (text2.length() >= count) {
         return text2;
      } else {
         StringBuffer builder = new StringBuffer();
         int count2 = count - text2.length();

         while (builder.length() < count2) {
            builder.append(character);
         }

         return builder.toString() + text2;
      }
   }
}
