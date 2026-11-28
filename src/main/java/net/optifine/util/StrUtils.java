package net.optifine.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StrUtils {
   public static String replaceSuffix(String str, String str2, String str3) {
      if (str == null || str2 == null) {
         return str;
      } else if (!str.endsWith(str2)) {
         return str;
      } else {
         if (str3 == null) {
            str3 = "";
         }

         str = str.substring(0, str.length() - str2.length());
         return str + str3;
      }
   }

   public static String addSuffixCheck(String str, String str2) {
      return str == null || str2 == null ? str : (str.endsWith(str2) ? str : str + str2);
   }

   private static boolean startsWithMaskSingle(String str, String str2, char ch) {
      if (str != null && str2 != null) {
         if (str.length() < str2.length()) {
            return false;
         } else {
            return equalsMaskSingle(str.substring(0, str2.length()), str2, ch);
         }
      } else {
         return str == str2;
      }
   }

   public static String fillRight(String str, int n, char ch) {
      if (str == null) {
         str = "";
      }

      if (str.length() >= n) {
         return str;
      } else {
         StringBuffer sb = new StringBuffer(str);

         while (sb.length() < n) {
            sb.append(ch);
         }

         return sb.toString();
      }
   }

   public static String getSegment(String str, String str2, String str3) {
      if (str != null && str2 != null && str3 != null) {
         int n = str.indexOf(str2);
         if (n < 0) {
            return null;
         } else {
            int n2 = str.indexOf(str3, n);
            return n2 < 0 ? null : str.substring(n, n2 + str3.length());
         }
      } else {
         return null;
      }
   }

   public static boolean equalsMask(String str, String str2, char ch, char ch2) {
      if (str2 != null && str != null) {
         if (str2.indexOf(ch) < 0) {
            return str2.indexOf(ch2) < 0 ? str2.equals(str) : equalsMaskSingle(str, str2, ch2);
         } else {
            ArrayList list = new ArrayList();
            String str3 = "" + ch;
            if (str2.startsWith(str3)) {
               list.add("");
            }

            StringTokenizer stringTokenizer = new StringTokenizer(str2, str3);

            while (stringTokenizer.hasMoreElements()) {
               list.add(stringTokenizer.nextToken());
            }

            if (str2.endsWith(str3)) {
               list.add("");
            }

            if (!startsWithMaskSingle(str, (String)list.get(0), ch2)) {
               return false;
            } else {
               if (!endsWithMaskSingle(str, (String)list.get(list.size() - 1), ch2)) {
                  return false;
               } else {
                  int n = 0;

                  for (int i = 0; i < list.size(); i++) {
                     String str6 = (String)list.get(i);
                     if (str6.length() > 0) {
                        int n2 = indexOfMaskSingle(str, str6, n, ch2);
                        if (n2 < 0) {
                           return false;
                        }

                        n = n2 + str6.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return str2 == str;
      }
   }

   public static String replacePrefix(String str, String str2, String str3) {
      if (str == null || str2 == null) {
         return str;
      } else if (!str.startsWith(str2)) {
         return str;
      } else {
         if (str3 == null) {
            str3 = "";
         }

         str = str.substring(str2.length());
         return str3 + str;
      }
   }

   public static String trimTrailing(String str, String str2) {
      if (str != null && str2 != null) {
         int length = str.length();

         int n;
         for (n = length; n > 0; n--) {
            if (str2.indexOf(str.charAt(n - 1)) < 0) {
               break;
            }
         }

         return n == length ? str : str.substring(0, n);
      } else {
         return str;
      }
   }

   public static String addPrefixCheck(String str, String str2) {
      return str == null || str2 == null ? str : (str.endsWith(str2) ? str : str2 + str);
   }

   public static String removeSuffix2(String str, String str2) {
      if (str != null && str2 != null && str.endsWith(str2)) {
         str = str.substring(0, str.length() - str2.length());
      }

      return str;
   }

   public static String stringInc(String str) {
      int n = parseInt(str, -1);
      if (n == -1) {
         return "";
      } else {
         return ("" + ++n).length() > str.length() ? "" : fillLeft("" + n, str.length(), '0');
      }
   }

   public static String removeSuffix(String str, String[] strings) {
      if (str != null && strings != null) {
         int length = str.length();

         for (int i = 0; i < strings.length; i++) {
            str = removeSuffix2(str, strings[i]);
            if (str.length() != length) {
               break;
            }
         }

         return str;
      } else {
         return str;
      }
   }

   public static boolean equals2(Object obj, Object obj2) {
      return obj == obj2 ? true : (obj != null && obj.equals(obj2) ? true : obj2 != null && obj2.equals(obj));
   }

   private static boolean endsWithMaskSingle(String str, String str2, char ch) {
      if (str != null && str2 != null) {
         if (str.length() < str2.length()) {
            return false;
         } else {
            return equalsMaskSingle(str.substring(str.length() - str2.length(), str.length()), str2, ch);
         }
      } else {
         return str == str2;
      }
   }

   private static boolean equals(char ch, String str) {
      for (int i = 0; i < str.length(); i++) {
         if (str.charAt(i) == ch) {
            return true;
         }
      }

      return false;
   }

   public static String removePrefixSuffix(String str, String str2, String str3) {
      return removePrefixSuffix2(str, new String[]{str2}, new String[]{str3});
   }

   public static String[] split(String str, String str2) {
      if (str != null && str.length() > 0) {
         if (str2 == null) {
            return new String[]{str};
         } else {
            ArrayList list = new ArrayList();
            int n = 0;

            for (int i = 0; i < str.length(); i++) {
               if (equals(str.charAt(i), str2)) {
                  list.add(str.substring(n, i));
                  n = i + 1;
               }
            }

            list.add(str.substring(n, str.length()));
            return (String[])list.toArray(new String[list.size()]);
         }
      } else {
         return new String[0];
      }
   }

   public static boolean equalsMask(String str, String[] strings, char ch) {
      for (int i = 0; i < strings.length; i++) {
         if (equalsMask2(str, strings[i], ch)) {
            return true;
         }
      }

      return false;
   }

   public static int findPrefix(String[] strings, String str) {
      if (strings != null && str != null) {
         for (int i = 0; i < strings.length; i++) {
            if (strings[i].startsWith(str)) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static String fillLeft(String str, int n, char ch) {
      if (str == null) {
         str = "";
      }

      if (str.length() >= n) {
         return str;
      } else {
         StringBuffer sb = new StringBuffer();
         int n2 = n - str.length();

         while (sb.length() < n2) {
            sb.append(ch);
         }

         return sb.toString() + str;
      }
   }

   public static boolean isEmpty(String str) {
      return str == null ? true : str.trim().length() <= 0;
   }

   public static String[] remove(String[] strings, int n, int n2) {
      if (strings == null) {
         return strings;
      } else if (n2 > 0 && n < strings.length) {
         if (n >= n2) {
            return strings;
         } else {
            ArrayList list = new ArrayList(strings.length);

            for (int i = 0; i < strings.length; i++) {
               String str = strings[i];
               if (i < n || i >= n2) {
                  list.add(str);
               }
            }

            return (String[])list.toArray(new String[list.size()]);
         }
      } else {
         return strings;
      }
   }

   public static String removePrefix2(String str, String[] strings) {
      if (str != null && strings != null) {
         int length = str.length();

         for (int i = 0; i < strings.length; i++) {
            str = removePrefix(str, strings[i]);
            if (str.length() != length) {
               break;
            }
         }

         return str;
      } else {
         return str;
      }
   }

   public static int parseInt(String str, int n) {
      if (str == null) {
         return n;
      } else {
         try {
            return Integer.parseInt(str);
         } catch (NumberFormatException e) {
            return n;
         }
      }
   }

   public static boolean isFilled(String str) {
      return !isEmpty(str);
   }

   public static boolean endsWith(String str, String[] strings) {
      if (str == null) {
         return false;
      } else if (strings == null) {
         return false;
      } else {
         for (int i = 0; i < strings.length; i++) {
            if (str.endsWith(strings[i])) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean equalsMask2(String str, String str2, char ch) {
      if (str2 != null && str != null) {
         if (str2.indexOf(ch) < 0) {
            return str2.equals(str);
         } else {
            ArrayList list = new ArrayList();
            String str3 = "" + ch;
            if (str2.startsWith(str3)) {
               list.add("");
            }

            StringTokenizer stringTokenizer = new StringTokenizer(str2, str3);

            while (stringTokenizer.hasMoreElements()) {
               list.add(stringTokenizer.nextToken());
            }

            if (str2.endsWith(str3)) {
               list.add("");
            }

            if (!str.startsWith((String)list.get(0))) {
               return false;
            } else {
               if (!str.endsWith((String)list.get(list.size() - 1))) {
                  return false;
               } else {
                  int n = 0;

                  for (int i = 0; i < list.size(); i++) {
                     String str6 = (String)list.get(i);
                     if (str6.length() > 0) {
                        int n2 = str.indexOf(str6, n);
                        if (n2 < 0) {
                           return false;
                        }

                        n = n2 + str6.length();
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return str2 == str;
      }
   }

   public static String addIfNotContains(String str, String str2) {
      for (int i = 0; i < str2.length(); i++) {
         if (str.indexOf(str2.charAt(i)) < 0) {
            str = str + str2.charAt(i);
         }
      }

      return str;
   }

   public static boolean startsWith(String str, String[] strings) {
      if (str == null) {
         return false;
      } else if (strings == null) {
         return false;
      } else {
         for (int i = 0; i < strings.length; i++) {
            if (str.startsWith(strings[i])) {
               return true;
            }
         }

         return false;
      }
   }

   public static String removePrefixSuffix2(String str, String[] strings, String[] strings2) {
      str = removePrefix2(str, strings);
      return removeSuffix(str, strings2);
   }

   public static String removePrefix(String str, String str2) {
      if (str != null && str2 != null) {
         if (str.startsWith(str2)) {
            str = str.substring(str2.length());
         }

         return str;
      } else {
         return str;
      }
   }

   public static int findSuffix(String[] strings, String str) {
      if (strings != null && str != null) {
         for (int i = 0; i < strings.length; i++) {
            if (strings[i].endsWith(str)) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static boolean equalsTrim(String str, String str2) {
      if (str != null) {
         str = str.trim();
      }

      if (str2 != null) {
         str2 = str2.trim();
      }

      return equals2((Object)str, (Object)str2);
   }

   private static int indexOfMaskSingle(String str, String str2, int n, char ch) {
      if (str != null && str2 != null) {
         if (n >= 0 && n <= str.length()) {
            if (str.length() < n + str2.length()) {
               return -1;
            } else {
               for (int i = n; i + str2.length() <= str.length(); i++) {
                  if (equalsMaskSingle(str.substring(i, i + str2.length()), str2, ch)) {
                     return i;
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

   private static boolean equalsMaskSingle(String str, String str2, char ch) {
      if (str != null && str2 != null) {
         if (str.length() != str2.length()) {
            return false;
         } else {
            for (int i = 0; i < str2.length(); i++) {
               char ch2 = str2.charAt(i);
               if (ch2 != ch && str.charAt(i) != ch2) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return str == str2;
      }
   }

   public static String trimLeading(String str, String str2) {
      if (str != null && str2 != null) {
         int length = str.length();

         for (int i = 0; i < length; i++) {
            if (str2.indexOf(str.charAt(i)) < 0) {
               return str.substring(i);
            }
         }

         return "";
      } else {
         return str;
      }
   }

   public static String trim(String str, String str2) {
      if (str != null && str2 != null) {
         str = trimLeading(str, str2);
         return trimTrailing(str, str2);
      } else {
         return str;
      }
   }
}
