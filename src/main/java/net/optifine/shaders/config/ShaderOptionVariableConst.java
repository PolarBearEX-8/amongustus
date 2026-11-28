package net.optifine.shaders.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.util.StrUtils;

public class ShaderOptionVariableConst extends ShaderOptionVariable {
   private String type = null;
   private static final Pattern PATTERN_CONST = Pattern.compile("^\\s*const\\s*(float|int)\\s*([A-Za-z0-9_]+)\\s*=\\s*(-?[0-9\\.]+f?F?)\\s*;\\s*(//.*)?$");

   public static ShaderOption parseOption2(String str, String str2) {
      Matcher matcher = PATTERN_CONST.matcher(str);
      if (!matcher.matches()) {
         return null;
      } else {
         String str3 = matcher.group(1);
         String str4 = matcher.group(2);
         String str5 = matcher.group(3);
         String str6 = matcher.group(4);
         String segment = StrUtils.getSegment(str6, "[", "]");
         if (segment != null && segment.length() > 0) {
            str6 = str6.replace(segment, "").trim();
         }

         String[] strings = parseValues(str5, segment);
         if (str4 != null && str4.length() > 0) {
            str2 = StrUtils.removePrefix(str2, "/shaders/");
            return new ShaderOptionVariableConst(str4, str3, str6, str5, strings, str2);
         } else {
            return null;
         }
      }
   }

   @Override
   public String getSourceLine() {
      return "const " + this.type + " " + this.getName() + " = " + this.getValue() + "; // Shader option " + this.getValue();
   }

   public ShaderOptionVariableConst(String str, String str2, String str3, String str4, String[] strings, String str5) {
      super(str, str3, str4, strings, str5);
      this.type = str2;
   }

   @Override
   public boolean matchesLine(String str) {
      Matcher matcher = PATTERN_CONST.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         return matcher.group(2).matches(this.getName());
      }
   }
}
