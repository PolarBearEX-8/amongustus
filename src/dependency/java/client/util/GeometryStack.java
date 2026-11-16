package client.util;

import client.setting.OptionManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeometryStack extends RayTraceDeserializer {
   public static final Pattern pattern3 = Pattern.compile("^\\s*const\\s*bool\\s*([A-Za-z0-9_]+)\\s*=\\s*(true|false)\\s*;\\s*(//.*)?$");

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public boolean matchesText(String text) {
      Matcher matcher = pattern3.matcher(text);
      return !matcher.matches() ? false : matcher.group(1).matches(this.getLabelText());
   }

   public GeometryStack(String text, String text2, String text3, String text4) {
      super(text, text2, text3, text4);
   }

   @Override
   public String getText2() {
      return "const bool " + this.getLabelText() + " = " + this.getLabelText2() + "; // Shader option " + this.getLabelText2();
   }

   public static OptionManager resolveOptionManagerByText(String text, String text2) {
      Matcher matcher = pattern3.matcher(text);
      if (!matcher.matches()) {
         return null;
      } else {
         String text3 = matcher.group(1);
         String text4 = matcher.group(2);
         String text5 = matcher.group(3);
         if (text3 != null && text3.length() > 0) {
            String text6 = SplineCollector.resolveText2(text2, "/shaders/");
            GeometryStack geometryStack = new GeometryStack(text3, text5, text4, text6);
            geometryStack.setEnabled2(false);
            return geometryStack;
         } else {
            return null;
         }
      }
   }
}
