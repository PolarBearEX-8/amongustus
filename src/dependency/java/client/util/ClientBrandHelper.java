package client.util;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class ClientBrandHelper {
   public static final Pattern pattern = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

   public static boolean isEmpty(String text) {
      return StringUtils.isEmpty(text);
   }

   public static String getString(String text) {
      return pattern.matcher(text).replaceAll("");
   }

   public static String resolveText(int count) {
      int count2 = count / 20;
      int count3 = count2 / 60;
      int count4 = count2 % 60;
      return count4 < 10 ? count3 + ":0" + count4 : count3 + ":" + count4;
   }
}
