package client.render;

import client.event.PreUpdateFilter;
import client.util.BezierCounter;
import client.util.BuildManager;
import client.util.NetherManager;
import client.util.PreEndHandManager;
import client.util.PropertiesAdapter;
import client.util.SplineCollector;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShadersEntityPropertiesManager {
   public static int[] countArray = null;
   public static boolean enabled;

   private static int[] resolveIndexArray(List list) {
      int[] countArray2 = new int[list.size()];

      for (int index = 0; index < countArray2.length; index++) {
         countArray2[index] = (Integer)list.get(index);
      }

      return countArray2;
   }

   public static void renderElement() {
      countArray = null;
   }

   public static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter) {
      renderElement();
      if (preUpdateFilter != null) {
         ArrayList list = new ArrayList();
         String text = "/shaders/entity.properties";
         InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text);
         if (resourceAsStream != null) {
            applyInputStream(resourceAsStream, text, list);
         }

         applyList(list);
         if (!list.isEmpty()) {
            countArray = resolveIndexArray(list);
         }
      }
   }

   private static void applyList(List list, int count, int count2) {
      while (list.size() <= count) {
         list.add(-1);
      }

      list.set(count, count2);
   }

   private static void applyList(List list) {
   }

   public static int computeCount(int count) {
      if (countArray == null) {
         return -1;
      } else {
         return count >= 0 && count < countArray.length ? countArray[count] : -1;
      }
   }

   private static void applyInputStream(InputStream inputStream, String text, List list) {
      InputStream inputStream2 = inputStream;
      if (inputStream != null) {
         try {
            inputStream2 = BezierCounter.resolveInputStream(inputStream2, text);
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream2);
            inputStream2.close();
            BuildManager.applyText4("[Shaders] Parsing entity mappings: " + text);
            NetherManager netherManager = new NetherManager("Shaders");

            for (Object element : propertiesAdapter.keySet()) {
               String text2 = (String)element;
               String property = propertiesAdapter.getProperty(text2);
               String text3 = "entity.";
               if (!text2.startsWith(text3)) {
                  BuildManager.applyText3("[Shaders] Invalid entity ID: " + text2);
               } else {
                  int count = BuildManager.computeCount(SplineCollector.resolveText2(text2, text3), -1);
                  if (count < 0) {
                     BuildManager.applyText3("[Shaders] Invalid entity alias ID: " + count);
                  } else {
                     int[] countArray2 = netherManager.resolveIndexArray(property);
                     if (countArray2 != null && countArray2.length >= 1) {
                        for (int index = 0; index < countArray2.length; index++) {
                           int count2 = countArray2[index];
                           applyList(list, count2, count);
                        }
                     } else {
                        BuildManager.applyText3("[Shaders] Invalid entity ID mapping: " + text2 + "=" + property);
                     }
                  }
               }
            }
         } catch (IOException ioException) {
            BuildManager.applyText3("[Shaders] Error reading: " + text);
         }
      }
   }

   public static void renderElement2() {
      if (enabled) {
         enabled = false;
         applyPreUpdateFilter(PreEndHandManager.getPreUpdateFilter());
      }
   }
}
