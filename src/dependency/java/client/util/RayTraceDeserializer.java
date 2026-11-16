package client.util;

import client.setting.OptionManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RayTraceDeserializer extends OptionManager {
   public static final Pattern pattern = Pattern.compile("^\\s*(//)?\\s*#define\\s+([A-Za-z0-9_]+)\\s*(//.*)?$");
   public static final Pattern pattern2 = Pattern.compile("^\\s*#if(n)?def\\s+([A-Za-z0-9_]+)(\\s*)?$");

   @Override
   public boolean isActive() {
      return true;
   }

   public static OptionManager resolveOptionManager(String text, String text2) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return null;
      } else {
         String text3 = matcher.group(1);
         String text4 = matcher.group(2);
         String text5 = matcher.group(3);
         if (text4 != null && !text4.isEmpty()) {
            boolean flag = !BuildManager.matchesTarget(text3, "//");
            String text6 = SplineCollector.resolveText2(text2, "/shaders/");
            return new RayTraceDeserializer(text4, text5, String.valueOf(flag), text6);
         } else {
            return null;
         }
      }
   }

   @Override
   public String resolveText(String text) {
      return parseBoolean(text) ? "§a" : "§c";
   }

   public static boolean parseBoolean(String text) {
      return Boolean.parseBoolean(text);
   }

   @Override
   public boolean matchesText(String text) {
      Matcher matcher = pattern.matcher(text);
      return !matcher.matches() ? false : matcher.group(2).matches(this.getLabelText());
   }

   @Override
   public boolean matchesTextByText(String text) {
      Matcher matcher = pattern2.matcher(text);
      return matcher.matches() && matcher.group(2).equals(this.getLabelText());
   }

   public RayTraceDeserializer(String text, String text2, String text3, String text4) {
      super(text, text2, text3, new String[]{"false", "true"}, text3, text4);
   }

   @Override
   public String resolveTextByText(String text) {
      String text2 = super.resolveTextByText(text);
      return text2 != text ? text2 : (parseBoolean(text) ? GeneratorDefaultManager.getTextAsText() : GeneratorDefaultManager.getText4());
   }

   @Override
   public String getText2() {
      return parseBoolean(this.getLabelText2())
         ? "#define " + this.getLabelText() + " // Shader option ON"
         : "//#define " + this.getLabelText() + " // Shader option OFF";
   }
}
