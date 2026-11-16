package client.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondaryCustom {
   public static Pattern pattern = Pattern.compile("\\s*uniform\\s+\\w+\\s+(\\w+).*");
   public static Pattern pattern2 = Pattern.compile("\\s*attribute\\s+\\w+\\s+(\\w+).*");
   public static Pattern pattern3 = Pattern.compile("\\s*const\\s+int\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
   public static Pattern pattern4 = Pattern.compile("\\s*const\\s+float\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
   public static Pattern pattern5 = Pattern.compile("\\s*const\\s+vec4\\s+(\\w+)\\s*=\\s*(.+)\\s*;.*");
   public static Pattern pattern6 = Pattern.compile("\\s*const\\s+bool\\s+(\\w+)\\s*=\\s*(\\w+)\\s*;.*");
   public static Pattern pattern7 = Pattern.compile("\\s*(/\\*|//)?\\s*([A-Z]+):\\s*(\\w+)\\s*(\\*/.*|\\s*)");
   public static Pattern pattern8 = Pattern.compile("\\s*#\\s*extension\\s+(\\w+)\\s*:\\s*(\\w+).*");
   public static Pattern pattern9 = Pattern.compile(".*deferred[0-9]*\\.fsh");
   public static Pattern pattern10 = Pattern.compile(".*composite[0-9]*\\.fsh");
   public static Pattern pattern11 = Pattern.compile(".*final\\.fsh");
   public static Pattern pattern12 = Pattern.compile("[0-7N]*");

   public static int computeCount2(String text) {
      return text.equals("shadow") ? 0 : (text.equals("watershadow") ? 1 : computeCount(text, "shadowtex", 0, 1));
   }

   public static boolean matchesText2(String text) {
      return pattern12.matcher(text).matches();
   }

   public static int computeCount3(String text) {
      int count = computeCount(text, "gaux", 1, 4);
      return count > 0 ? count + 3 : computeCount(text, "colortex", 4, 7);
   }

   public static boolean matchesText3(String text) {
      return pattern9.matcher(text).matches();
   }

   public static int computeCountByText(String text) {
      return text.equals("shadowcolor") ? 0 : computeCount(text, "shadowcolor", 0, 1);
   }

   public static int computeCount(String text, String text2, int count, int count2) {
      if (text.length() != text2.length() + 1) {
         return -1;
      } else if (!text.startsWith(text2)) {
         return -1;
      } else {
         int count3 = text.charAt(text2.length()) - '0';
         return count3 >= count && count3 <= count2 ? count3 : -1;
      }
   }

   public static boolean matchesTextByText(String text) {
      return pattern11.matcher(text).matches();
   }

   public static boolean matchesText(String text) {
      return pattern10.matcher(text).matches();
   }

   public static AngleFactory resolveAngleFactory(String text) {
      Matcher matcher = pattern.matcher(text);
      if (matcher.matches()) {
         return new AngleFactory(1, matcher.group(1), "", text);
      } else {
         Matcher matcher2 = pattern2.matcher(text);
         if (matcher2.matches()) {
            return new AngleFactory(2, matcher2.group(1), "", text);
         } else {
            Matcher matcher3 = pattern7.matcher(text);
            if (matcher3.matches()) {
               return new AngleFactory(6, matcher3.group(2), matcher3.group(3), text);
            } else {
               Matcher matcher4 = pattern3.matcher(text);
               if (matcher4.matches()) {
                  return new AngleFactory(3, matcher4.group(1), matcher4.group(2), text);
               } else {
                  Matcher matcher5 = pattern4.matcher(text);
                  if (matcher5.matches()) {
                     return new AngleFactory(4, matcher5.group(1), matcher5.group(2), text);
                  } else {
                     Matcher matcher6 = pattern6.matcher(text);
                     if (matcher6.matches()) {
                        return new AngleFactory(5, matcher6.group(1), matcher6.group(2), text);
                     } else {
                        Matcher matcher7 = pattern8.matcher(text);
                        if (matcher7.matches()) {
                           return new AngleFactory(7, matcher7.group(1), matcher7.group(2), text);
                        } else {
                           Matcher matcher8 = pattern5.matcher(text);
                           return matcher8.matches() ? new AngleFactory(8, matcher8.group(1), matcher8.group(2), text) : null;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static int computeCount(String text) {
      return computeCount(text, "depthtex", 0, 2);
   }
}
