package net.optifine.shaders.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.util.StrUtils;

public class ShaderOptionSwitchConst extends ShaderOptionSwitch {
   private static final Pattern PATTERN_CONST = Pattern.compile("^\\s*const\\s*bool\\s*([A-Za-z0-9_]+)\\s*=\\s*(true|false)\\s*;\\s*(//.*)?$");

   @Override
   public boolean matchesLine(String str) {
      Matcher matcher = PATTERN_CONST.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         return matcher.group(1).matches(this.getName());
      }
   }

   public ShaderOptionSwitchConst(String str, String str2, String str3, String str4) {
      super(str, str2, str3, str4);
   }

   public static ShaderOption parseOption2(String str, String str2) {
      Matcher matcher = PATTERN_CONST.matcher(str);
      if (!matcher.matches()) {
         return null;
      } else {
         String str3 = matcher.group(1);
         String str4 = matcher.group(2);
         String str5 = matcher.group(3);
         if (str3 != null && str3.length() > 0) {
            str2 = StrUtils.removePrefix(str2, "/shaders/");
            ShaderOptionSwitchConst shaderOptionSwitchConst = new ShaderOptionSwitchConst(str3, str5, str4, str2);
            shaderOptionSwitchConst.setVisible(false);
            return shaderOptionSwitchConst;
         } else {
            return null;
         }
      }
   }

   @Override
   public String getSourceLine() {
      return "const bool " + this.getName() + " = " + this.getValue() + "; // Shader option " + this.getValue();
   }

   @Override
   public boolean checkUsed() {
      return false;
   }
}
