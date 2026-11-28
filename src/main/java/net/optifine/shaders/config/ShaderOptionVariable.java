package net.optifine.shaders.config;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.util.StrUtils;

public class ShaderOptionVariable extends ShaderOption {
   private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+(\\w+)\\s+(-?[0-9\\.Ff]+|\\w+)\\s*(//.*)?$");

   @Override
   public String getValueColor(String str) {
      String str2 = str.toLowerCase();
      return !str2.equals("false") && !str2.equals("off") ? "§a" : "§c";
   }

   @Override
   public String getSourceLine() {
      return "#define " + this.getName() + " " + this.getValue() + " // Shader option " + this.getValue();
   }

   public static ShaderOption parseOption(String str, String str2) {
      Matcher matcher = PATTERN_VARIABLE.matcher(str);
      if (!matcher.matches()) {
         return null;
      } else {
         String str3 = matcher.group(1);
         String str4 = matcher.group(2);
         String str5 = matcher.group(3);
         String segment = StrUtils.getSegment(str5, "[", "]");
         if (segment != null && !segment.isEmpty()) {
            str5 = str5.replace(segment, "").trim();
         }

         String[] strings = parseValues(str4, segment);
         if (str3 != null && !str3.isEmpty()) {
            str2 = StrUtils.removePrefix(str2, "/shaders/");
            return new ShaderOptionVariable(str3, str5, str4, strings, str2);
         } else {
            return null;
         }
      }
   }

   @Override
   public String getValueText(String str) {
      String str2 = Shaders.translate("prefix." + this.getName(), "");
      String valueText = super.getValueText(str);
      String str3 = Shaders.translate("suffix." + this.getName(), "");
      return str2 + valueText + str3;
   }

   public static String[] parseValues(String str, String str2) {
      String[] strings = new String[]{str};
      if (str2 == null) {
         return strings;
      } else {
         str2 = str2.trim();
         str2 = StrUtils.removePrefix(str2, "[");
         str2 = StrUtils.removeSuffix2(str2, "]");
         str2 = str2.trim();
         if (str2.isEmpty()) {
            return strings;
         } else {
            String[] strings2 = Config.tokenize(str2, " ");
            if (strings2.length == 0) {
               return strings;
            } else {
               if (!Arrays.asList(strings2).contains(str)) {
                  strings2 = (String[])Config.addObjectToArray(strings2, str, 0);
               }

               return strings2;
            }
         }
      }
   }

   @Override
   public boolean matchesLine(String str) {
      Matcher matcher = PATTERN_VARIABLE.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         return matcher.group(1).matches(this.getName());
      }
   }

   public ShaderOptionVariable(String str, String str2, String str3, String[] strings, String str4) {
      super(str, str2, str3, strings, str3, str4);
      this.setVisible(this.getValues().length > 1);
   }
}
