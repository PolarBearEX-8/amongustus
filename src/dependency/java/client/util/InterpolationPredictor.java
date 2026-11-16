package client.util;

import client.setting.OptionManager;

public class InterpolationPredictor {
   public static GeometryCounter resolveGeometryCounter(GeometryCounter[] geometryCounterArray, OptionManager[] optionManagerArray, boolean flag) {
      if (geometryCounterArray == null) {
         return null;
      } else {
         for (int index = 0; index < geometryCounterArray.length; index++) {
            GeometryCounter geometryCounter = geometryCounterArray[index];
            if (matchesGeometryCounter(geometryCounter, optionManagerArray, flag)) {
               return geometryCounter;
            }
         }

         return null;
      }
   }

   public static OptionManager resolveOptionManager(String text, OptionManager[] optionManagerArray) {
      if (optionManagerArray == null) {
         return null;
      } else {
         for (int index = 0; index < optionManagerArray.length; index++) {
            OptionManager optionManager = optionManagerArray[index];
            if (optionManager.getLabelText().equals(text)) {
               return optionManager;
            }
         }

         return null;
      }
   }

   public static boolean matchesGeometryCounter(GeometryCounter geometryCounter, OptionManager[] optionManagerArray, boolean flag) {
      if (geometryCounter == null) {
         return false;
      } else if (optionManagerArray == null) {
         return false;
      } else {
         String[] textArray = geometryCounter.getTextArray();

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            OptionManager optionManager = resolveOptionManager(text, optionManagerArray);
            if (optionManager != null) {
               String text2 = flag ? optionManager.getLabelText7() : optionManager.getLabelText2();
               String text3 = geometryCounter.get(text);
               if (!BuildManager.matchesTarget(text2, text3)) {
                  return false;
               }
            }
         }

         return true;
      }
   }
}
