package client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class WeightManager {
   public static DistanceEngine distanceEngine = null;
   public static final Random random = new Random();

   public static DistanceEngine getDistanceEngine() {
      return distanceEngine;
   }

   private static int computeCount(int[] countArray) {
      int count = AltFloatEntry.computeCountByIndexArray(countArray);
      int count2 = random.nextInt(count);
      int count3 = 0;

      for (int index = 0; index < countArray.length; index++) {
         count3 += countArray[index];
         if (count3 > count2) {
            return index;
         }
      }

      return countArray.length - 1;
   }

   private static int[] resolveIndexArray(Properties[] propertiesArray) {
      int[] countArray = new int[propertiesArray.length];

      for (int index = 0; index < countArray.length; index++) {
         Properties properties = propertiesArray[index];
         if (properties == null) {
            properties = propertiesArray[0];
         }

         if (properties == null) {
            countArray[index] = 1;
         } else {
            String property = properties.getProperty("weight", (String)null);
            countArray[index] = BuildManager.computeCount(property, 1);
         }
      }

      return countArray;
   }

   private static String[] getTextArray() {
      ArrayList list = new ArrayList();
      list.add("textures/gui/title/background");

      for (int index = 0; index < 100; index++) {
         String text = "optifine/gui/background" + index;
         String text2 = text + "/panorama_0.png";
         if (BuildManager.matchesResourceLocation3(new ResourceLocation(text2))) {
            list.add(text);
         }
      }

      return ((String[])list.toArray(new String[list.size()]));
   }

   public static void updateState() {
      distanceEngine = null;
      String[] textArray = getTextArray();
      if (textArray.length > 1) {
         Properties[] propertiesArray = resolvePropertiesArray(textArray);
         int count = computeCount(resolveIndexArray(propertiesArray));
         String text = textArray[count];
         Object entry = propertiesArray[count];
         if (entry == null) {
            entry = propertiesArray[0];
         }

         if (entry == null) {
            entry = new PropertiesAdapter();
         }

         distanceEngine = new DistanceEngine(text, (Properties)entry);
      }
   }

   private static Properties[] resolvePropertiesArray(String[] textArray) {
      Properties[] propertiesArray = new Properties[textArray.length];

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         if (index == 0) {
            text = "optifine/gui";
         } else {
            BuildManager.applyText4("CustomPanorama: " + text);
         }

         ResourceLocation location = new ResourceLocation(text + "/background.properties");

         try {
            InputStream inputStream = BuildManager.resolveInputStream(location);
            if (inputStream != null) {
               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               BuildManager.applyText4("CustomPanorama: " + location.getText1());
               propertiesArray[index] = propertiesAdapter;
               inputStream.close();
            }
         } catch (IOException ioException) {
         }
      }

      return propertiesArray;
   }
}
