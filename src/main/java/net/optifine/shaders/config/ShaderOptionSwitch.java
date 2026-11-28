package net.optifine.shaders.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.util.StrUtils;

public class ShaderOptionSwitch extends ShaderOption {
   private static final Pattern PATTERN_DEFINE = Pattern.compile("^\\s*(//)?\\s*#define\\s+([A-Za-z0-9_]+)\\s*(//.*)?$");
   private static final Pattern PATTERN_IFDEF = Pattern.compile("^\\s*#if(n)?def\\s+([A-Za-z0-9_]+)(\\s*)?$");

   @Override
   public boolean matchesLine(String str) {
      Matcher matcher = PATTERN_DEFINE.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         return matcher.group(2).matches(this.getName());
      }
   }

   @Override
   public String getValueText(String str) {
      String valueText = super.getValueText(str);
      return valueText != str ? valueText : (isTrue(str) ? Lang.getOn() : Lang.getOff());
   }

   @Override
   public String getSourceLine() {
      return isTrue(this.getValue()) ? "#define " + this.getName() + " // Shader option ON" : "//#define " + this.getName() + " // Shader option OFF";
   }

   @Override
   public String getValueColor(String str) {
      return isTrue(str) ? "§a" : "§c";
   }

   public static boolean isTrue(String str) {
      return Boolean.parseBoolean(str);
   }

   public ShaderOptionSwitch(String str, String str2, String str3, String str4) {
      super(str, str2, str3, new String[]{"false", "true"}, str3, str4);
   }

   @Override
   public boolean checkUsed() {
      return true;
   }

   @Override
   public boolean isUsedInLine(String str) {
      Matcher matcher = PATTERN_IFDEF.matcher(str);
      if (matcher.matches()) {
         if (matcher.group(2).equals(this.getName())) {
            return true;
         }
      }

      return false;
   }

   public static ShaderOption parseOption(String str, String str2) {
      Matcher matcher = PATTERN_DEFINE.matcher(str);
      if (!matcher.matches()) {
         return null;
      } else {
         String str3 = matcher.group(1);
         String str4 = matcher.group(2);
         String str5 = matcher.group(3);
         if (str4 != null && !str4.isEmpty()) {
            boolean flag2 = !Config.equals(str3, "//");
            str2 = StrUtils.removePrefix(str2, "/shaders/");
            return new ShaderOptionSwitch(str4, str5, String.valueOf(flag2), str2);
         } else {
            return null;
         }
      }
   }
}
