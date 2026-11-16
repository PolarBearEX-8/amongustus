package client.util;

import client.setting.OptionManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RayTraceConverter extends RayTraceChecker {
   public String labelText8 = null;
   public static final Pattern pattern2 = Pattern.compile("^\\s*const\\s*(float|int)\\s*([A-Za-z0-9_]+)\\s*=\\s*(-?[0-9\\.]+f?F?)\\s*;\\s*(//.*)?$");

   @Override
   public boolean matchesText(String text) {
      Matcher matcher = pattern2.matcher(text);
      return !matcher.matches() ? false : matcher.group(2).matches(this.getLabelText());
   }

   public static OptionManager resolveOptionManagerByText(String text, String text2) {
      Matcher matcher = pattern2.matcher(text);
      if (!matcher.matches()) {
         return null;
      } else {
         String text3 = matcher.group(1);
         String text4 = matcher.group(2);
         String text5 = matcher.group(3);
         String text6 = matcher.group(4);
         String text7 = SplineCollector.resolveText2(text6, "[", "]");
         if (text7 != null && text7.length() > 0) {
            text6 = text6.replace(text7, "").trim();
         }

         String[] textArray = resolveTextArray(text5, text7);
         if (text4 != null && text4.length() > 0) {
            String text8 = SplineCollector.resolveText2(text2, "/shaders/");
            return new RayTraceConverter(text4, text3, text6, text5, textArray, text8);
         } else {
            return null;
         }
      }
   }

   @Override
   public String getText2() {
      return "const " + this.labelText8 + " " + this.getLabelText() + " = " + this.getLabelText2() + "; // Shader option " + this.getLabelText2();
   }

   public RayTraceConverter(String text, String text2, String text3, String text4, String[] textArray, String text5) {
      super(text, text3, text4, textArray, text5);
      this.labelText8 = text2;
   }
}
