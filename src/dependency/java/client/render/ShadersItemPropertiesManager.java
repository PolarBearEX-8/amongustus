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

public class ShadersItemPropertiesManager {
   public static int[] countArray = null;
   public static boolean enabled;
   public static final int count = Integer.MIN_VALUE;

   private static void applyList(List list, int count2, int count3) {
      while (list.size() <= count2) {
         list.add(Integer.MIN_VALUE);
      }

      list.set(count2, count3);
   }

   public static void renderElement() {
      countArray = null;
   }

   private static int[] resolveIndexArray(List list) {
      int[] countArray2 = new int[list.size()];

      for (int index = 0; index < countArray2.length; index++) {
         countArray2[index] = (Integer)list.get(index);
      }

      return countArray2;
   }

   public static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter) {
      renderElement();
      if (preUpdateFilter != null) {
         ArrayList list = new ArrayList();
         String text = "/shaders/item.properties";
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

   private static void applyInputStream(InputStream inputStream, String text, List list) {
      InputStream inputStream2 = inputStream;
      if (inputStream != null) {
         try {
            inputStream2 = BezierCounter.resolveInputStream(inputStream2, text);
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream2);
            inputStream2.close();
            BuildManager.applyText4("[Shaders] Parsing item mappings: " + text);
            NetherManager netherManager = new NetherManager("Shaders");

            for (Object element : propertiesAdapter.keySet()) {
               String text2 = (String)element;
               String property = propertiesAdapter.getProperty(text2);
               String text3 = "item.";
               if (!text2.startsWith(text3)) {
                  BuildManager.applyText3("[Shaders] Invalid item ID: " + text2);
               } else {
                  int count2 = BuildManager.computeCount(SplineCollector.resolveText2(text2, text3), -1);
                  if (count2 < 0) {
                     BuildManager.applyText3("[Shaders] Invalid item alias ID: " + count2);
                  } else {
                     int[] countArray2 = netherManager.resolveIndexArray2(property);
                     if (countArray2 != null && countArray2.length >= 1) {
                        for (int index = 0; index < countArray2.length; index++) {
                           int count3 = countArray2[index];
                           applyList(list, count3, count2);
                        }
                     } else {
                        BuildManager.applyText3("[Shaders] Invalid item ID mapping: " + text2 + "=" + property);
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

   public static int computeCount(int count2) {
      if (countArray == null) {
         return count2;
      } else if (count2 >= 0 && count2 < countArray.length) {
         int count3 = countArray[count2];
         return count3 == Integer.MIN_VALUE ? count2 : count3;
      } else {
         return count2;
      }
   }

   private static void applyList(List list) {
   }
}
