package net.augustus.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TextFormatUtil {
   public static String ALPHANUMERIC_CHARS;
   private static final List LOWERCASE_CHARS;
   private static final Set USERNAME_CHAR_SET;
   private static final List DIGIT_CHARS;
   private static final List UPPERCASE_CHARS;

   public static String[] appendElement(String[] strings, String str) {
      return Stream.concat(Arrays.stream(strings), Stream.of(str)).toArray(String[]::new);
   }

   public static String stripFormattingCodes(String str, char ch) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < str.length(); i++) {
         char ch2 = str.charAt(i);
         if (ch2 == ch) {
            i++;
         } else {
            sb.append(ch2);
         }
      }

      return sb.toString();
   }

   public static boolean isValidMinecraftUsername(String str) {
      if (str != null && str.length() < 17 && str.length() >= 3) {
         if (containsOnlyChars(str, USERNAME_CHAR_SET)) {
            return true;
         }
      }

      return false;
   }

   public static synchronized boolean isValidIdentifier(String str, int n) {
      if (str != null && !str.isEmpty()) {
         Character[] chars = str.chars().mapToObj(n2 -> (char)n2).toArray(Character[]::new);
         LinkedList list = new LinkedList();
         int length = chars.length;

         for (int i = 0; i < length; i++) {
            char ch = chars[i];
            if (!DIGIT_CHARS.contains(ch) && !LOWERCASE_CHARS.contains(ch) && !UPPERCASE_CHARS.contains(ch) && ch != '_') {
               list.add(ch);
            }
         }

         return list.isEmpty() && str.length() >= n;
      } else {
         return false;
      }
   }

   static {
      ALPHANUMERIC_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      USERNAME_CHAR_SET = new HashSet();

      for (char ch : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_".toCharArray()) {
         USERNAME_CHAR_SET.add(ch);
      }

      DIGIT_CHARS = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
      LOWERCASE_CHARS = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
      UPPERCASE_CHARS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
   }

   public static boolean isCleanPlayerName(String str) {
      return !str.isEmpty() && !str.contains("§") && !str.contains("#") && !str.contains(" ") && str.length() < 17;
   }

   public static String[] removeElement(String[] strings, String str) {
      Stream stream = Arrays.stream(strings);
      return (String[])stream.filter(obj -> {
         return !obj.equals(str);
      }).toArray(String[]::new);
   }

   public static String findLongestString(String[] strings) {
      String str = "";

      for (String str2 : strings) {
         if (str2.length() > str.length()) {
            str = str2;
         }
      }

      return str;
   }

   public static String extractFormattingCodes(String str, char ch) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < str.length(); i++) {
         char ch2 = str.charAt(i);
         if (ch2 == ch || i > 0 && str.charAt(i - 1) == ch) {
            sb.append(ch2);
         }
      }

      return sb.toString();
   }

   public static String extractFormattingSequence(String str, char ch) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < str.length() - 1; i++) {
         if (str.charAt(i) == ch) {
            sb.append(str.charAt(i));
            sb.append(str.charAt(i + 1));
         }
      }

      return sb.toString();
   }

   public static boolean containsOnlyChars(String str, Set set) {
      char[] chars = str.toCharArray();
      int length = chars.length;

      for (int i = 0; i < length; i++) {
         if (!set.contains(chars[i])) {
            return false;
         }
      }

      return true;
   }

   public static String selectLongerString(String str, String str2) {
      return str.length() > str2.length() ? str : str2;
   }

}
