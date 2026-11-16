package client.util;

import client.setting.OptionManager;
import java.util.ArrayList;

public class GeometryPipeline extends OptionManager {
   public GeometryCounter[] geometryCounterArray = null;
   public OptionManager[] optionManagerArray = null;
   public static final String labelText8 = "<profile>";
   public static final String labelText9 = "<custom>";

   private static String resolveText(GeometryCounter[] geometryCounterArray2, OptionManager[] optionManagerArray2) {
      return resolveText(geometryCounterArray2, optionManagerArray2, false);
   }

   @Override
   public String resolveText(String text) {
      return text.equals("<custom>") ? "§c" : "§a";
   }

   @Override
   public String resolveTextByText(String text) {
      return text.equals("<custom>") ? GeneratorDefaultManager.resolveText("of.general.custom", "<custom>") : PreEndHandManager.get("profile." + text, text);
   }

   public GeometryPipeline(GeometryCounter[] geometryCounterArray2, OptionManager[] optionManagerArray2) {
      super("<profile>", "", resolveText(geometryCounterArray2, optionManagerArray2), resolveTextArray(geometryCounterArray2), resolveText(geometryCounterArray2, optionManagerArray2, true), (String)null);
      this.geometryCounterArray = geometryCounterArray2;
      this.optionManagerArray = optionManagerArray2;
   }

   @Override
   public String getText() {
      String text = PreEndHandManager.get("profile.comment", (String)null);
      if (text != null) {
         return text;
      } else {
         StringBuffer builder = new StringBuffer();

         for (int index = 0; index < this.geometryCounterArray.length; index++) {
            String labelText = this.geometryCounterArray[index].getLabelText();
            if (labelText != null) {
               String text2 = PreEndHandManager.get("profile." + labelText + ".comment", (String)null);
               if (text2 != null) {
                  builder.append(text2);
                  if (!text2.endsWith(". ")) {
                     builder.append(". ");
                  }
               }
            }
         }

         return builder.toString();
      }
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.getLabelText2().equals("<custom>")) {
         super.updateState();
      }

      this.updateState5();
   }

   public void updateState4() {
      GeometryCounter geometryCounter = this.resolveGeometryCounter(this.getLabelText2());
      if (geometryCounter == null || !InterpolationPredictor.matchesGeometryCounter(geometryCounter, this.optionManagerArray, false)) {
         String text = resolveText(this.geometryCounterArray, this.optionManagerArray);
         this.matchesText3(text);
      }
   }

   @Override
   public String getTextAsText() {
      return GeneratorDefaultManager.resolveText("of.shaders.profile");
   }

   private static String[] resolveTextArray(GeometryCounter[] geometryCounterArray2) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < geometryCounterArray2.length; index++) {
         GeometryCounter geometryCounter = geometryCounterArray2[index];
         list.add(geometryCounter.getLabelText());
      }

      list.add("<custom>");
      return ((String[])list.toArray(new String[list.size()]));
   }

   private OptionManager resolveOptionManager(String text) {
      for (int index = 0; index < this.optionManagerArray.length; index++) {
         OptionManager optionManager = this.optionManagerArray[index];
         if (optionManager.getLabelText().equals(text)) {
            return optionManager;
         }
      }

      return null;
   }

   private void updateState5() {
      GeometryCounter geometryCounter = this.resolveGeometryCounter(this.getLabelText2());
      if (geometryCounter != null) {
         String[] textArray = geometryCounter.getTextArray();

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            OptionManager optionManager = this.resolveOptionManager(text);
            if (optionManager != null) {
               String text2 = geometryCounter.get(text);
               optionManager.matchesText3(text2);
            }
         }
      }
   }

   private GeometryCounter resolveGeometryCounter(String text) {
      for (int index = 0; index < this.geometryCounterArray.length; index++) {
         GeometryCounter geometryCounter = this.geometryCounterArray[index];
         if (geometryCounter.getLabelText().equals(text)) {
            return geometryCounter;
         }
      }

      return null;
   }

   private static String resolveText(GeometryCounter[] geometryCounterArray2, OptionManager[] optionManagerArray2, boolean flag) {
      GeometryCounter geometryCounter = InterpolationPredictor.resolveGeometryCounter(geometryCounterArray2, optionManagerArray2, flag);
      return geometryCounter == null ? "<custom>" : geometryCounter.getLabelText();
   }
}
