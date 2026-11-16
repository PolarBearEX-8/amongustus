package client.util;

import client.setting.OptionManager;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RayTraceChecker extends OptionManager {
   public static final Pattern pattern = Pattern.compile("^\\s*#define\\s+(\\w+)\\s+(-?[0-9\\.Ff]+|\\w+)\\s*(//.*)?$");

   @Override
   public String getText2() {
      return "#define " + this.getLabelText() + " " + this.getLabelText2() + " // Shader option " + this.getLabelText2();
   }

   @Override
   public String resolveTextByText(String text) {
      String text2 = PreEndHandManager.get("prefix." + this.getLabelText(), "");
      String text3 = super.resolveTextByText(text);
      String text4 = PreEndHandManager.get("suffix." + this.getLabelText(), "");
      return text2 + text3 + text4;
   }

   public static String[] resolveTextArray(String text, String text2) {
      String[] textArray = new String[]{text};
      if (text2 == null) {
         return textArray;
      } else {
         String text3 = text2.trim();
         text3 = SplineCollector.resolveText2(text3, "[");
         text3 = SplineCollector.resolveText3(text3, "]");
         text3 = text3.trim();
         if (text3.isEmpty()) {
            return textArray;
         } else {
            String[] textArray2 = BuildManager.resolveTextArray(text3, " ");
            if (textArray2.length == 0) {
               return textArray;
            } else {
               if (!Arrays.asList(textArray2).contains(text)) {
                  textArray2 = (String[])BuildManager.resolveTargetArray(textArray2, text, 0);
               }

               return textArray2;
            }
         }
      }
   }

   @Override
   public String resolveText(String text) {
      String text2 = text.toLowerCase();
      return !text2.equals("false") && !text2.equals("off") ? "§a" : "§c";
   }

   public RayTraceChecker(String text, String text2, String text3, String[] textArray, String text4) {
      super(text, text2, text3, textArray, text3, text4);
      this.setEnabled2(this.getTextArray().length > 1);
   }

   @Override
   public boolean matchesText(String text) {
      Matcher matcher = pattern.matcher(text);
      return !matcher.matches() ? false : matcher.group(1).matches(this.getLabelText());
   }

   public static OptionManager resolveOptionManager(String text, String text2) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return null;
      } else {
         String text3 = matcher.group(1);
         String text4 = matcher.group(2);
         String text5 = matcher.group(3);
         String text6 = SplineCollector.resolveText2(text5, "[", "]");
         if (text6 != null && !text6.isEmpty()) {
            text5 = text5.replace(text6, "").trim();
         }

         String[] textArray = resolveTextArray(text4, text6);
         if (text3 != null && !text3.isEmpty()) {
            String text7 = SplineCollector.resolveText2(text2, "/shaders/");
            return new RayTraceChecker(text3, text5, text4, textArray, text7);
         } else {
            return null;
         }
      }
   }
}
