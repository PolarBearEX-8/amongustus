package net.augustus.font;

import java.util.ArrayList;
import java.util.List;
import net.augustus.utils.TimeHelper;

public class TextWrapper {

   public static List wrapText(String str, int n, CustomFontUtil customFontUtil) {
      ArrayList list = new ArrayList();
      if (str != null && !str.isEmpty()) {

         for (String str2 : str.split("\n")) {
            if (str2.trim().isEmpty()) {
               list.add("");
            } else {
               if (customFontUtil.getStringWidth(str2) <= (float)n) {
                  list.add(str2);
               } else {
                  wrapParagraph(str2, n, customFontUtil, list);
               }
            }
         }

         return list;
      } else {
         return list;
      }
   }

   private static void wrapLongWord(String str, int n, CustomFontUtil customFontUtil, List list) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (customFontUtil.getStringWidth(sb.toString() + ch) <= (float)n) {
            sb.append(ch);
         } else {
            if (sb.length() > 0) {
               list.add(sb.toString());
            }

            sb = new StringBuilder();
            sb.append(ch);
         }
      }

      if (sb.length() > 0) {
         list.add(sb.toString());
      }
   }

   private static void wrapParagraph(String str, int n, CustomFontUtil customFontUtil, List list) {
      String[] parts = str.split(" ");
      StringBuilder sb = new StringBuilder();

      for (String str2 : parts) {
         if (customFontUtil.getStringWidth(sb.length() == 0 ? str2 : sb + " " + str2) <= (float)n) {
            if (sb.length() > 0) {
               sb.append(" ");
            }

            sb.append(str2);
         } else {
            if (sb.length() > 0) {
               list.add(sb.toString());
               sb = new StringBuilder();
            }

            if (customFontUtil.getStringWidth(str2) > (float)n) {
               wrapLongWord(str2, n, customFontUtil, list);
            } else {
               sb.append(str2);
            }
         }
      }

      if (sb.length() > 0) {
         list.add(sb.toString());
      }
   }
}
