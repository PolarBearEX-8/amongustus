package client.render;

import client.event.PreUpdateFilter;
import client.util.AngleObserver;
import client.util.BezierCounter;
import client.util.BuildManager;
import client.util.NetherManager;
import client.util.PreEndHandManager;
import client.util.PropertiesAdapter;
import client.util.SmoothMatcher;
import client.util.SplineCollector;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AuxiliaryShadersManager {
   public static AngleObserver[][] angleObserverArray = null;
   public static PropertiesAdapter propertiesAdapter = null;
   public static boolean enabled;

   public static int computeCount(int count, int count2) {
      if (angleObserverArray == null) {
         return count;
      } else if (count >= 0 && count < angleObserverArray.length) {
         AngleObserver[] angleObserverArray2 = angleObserverArray[count];
         if (angleObserverArray2 == null) {
            return count;
         } else {
            for (int index = 0; index < angleObserverArray2.length; index++) {
               AngleObserver angleObserver = angleObserverArray2[index];
               if (angleObserver.checkIndex(count, count2)) {
                  return angleObserver.getCount();
               }
            }

            return count;
         }
      } else {
         return count;
      }
   }

   public static void renderElement() {
      if (enabled) {
         enabled = false;
         applyPreUpdateFilter(PreEndHandManager.getPreUpdateFilter());
      }
   }

   private static void applyList(List list) {
   }

   private static AngleObserver[][] resolveAngleObserverArray(List list) {
      AngleObserver[][] angleObserverMatrix = new AngleObserver[list.size()][];

      for (int index = 0; index < angleObserverMatrix.length; index++) {
         List list2 = (List)list.get(index);
         if (list2 != null) {
            angleObserverMatrix[index] = ((AngleObserver[])list2.toArray(new AngleObserver[list2.size()]));
         }
      }

      return angleObserverMatrix;
   }

   public static void renderElement2() {
      angleObserverArray = (AngleObserver[][])null;
      propertiesAdapter = null;
   }

   private static void applyInputStream(InputStream inputStream, String text, List list) {
      InputStream inputStream2 = inputStream;
      if (inputStream != null) {
         try {
            inputStream2 = BezierCounter.resolveInputStream(inputStream2, text);
            PropertiesAdapter propertiesAdapter2 = new PropertiesAdapter();
            propertiesAdapter2.load(inputStream2);
            inputStream2.close();
            BuildManager.applyText4("[Shaders] Parsing block mappings: " + text);
            NetherManager netherManager = new NetherManager("Shaders");

            for (String text2 : (java.lang.Iterable<String>) (propertiesAdapter2.keySet())) {
               String property = propertiesAdapter2.getProperty(text2);
               if (text2.startsWith("layer.")) {
                  if (propertiesAdapter == null) {
                     propertiesAdapter = new PropertiesAdapter();
                  }

                  propertiesAdapter.put(text2, property);
               } else {
                  String text3 = "block.";
                  if (!text2.startsWith(text3)) {
                     BuildManager.applyText3("[Shaders] Invalid block ID: " + text2);
                  } else {
                     int count = BuildManager.computeCount(SplineCollector.resolveText2(text2, text3), -1);
                     if (count < 0) {
                        BuildManager.applyText3("[Shaders] Invalid block ID: " + text2);
                     } else {
                        SmoothMatcher[] smoothMatcherArray = netherManager.resolveSmoothMatcherArrayByText(property);
                        if (smoothMatcherArray != null && smoothMatcherArray.length >= 1) {
                           AngleObserver angleObserver = new AngleObserver(count, smoothMatcherArray);
                           applyList(list, angleObserver);
                        } else {
                           BuildManager.applyText3("[Shaders] Invalid block ID mapping: " + text2 + "=" + property);
                        }
                     }
                  }
               }
            }
         } catch (IOException ioException) {
            BuildManager.applyText3("[Shaders] Error reading: " + text);
         }
      }
   }

   private static void applyList(List list, AngleObserver angleObserver) {
      int[] indexArray = angleObserver.getIndexArray();

      for (int index = 0; index < indexArray.length; index++) {
         int count = indexArray[index];

         while (count >= list.size()) {
            list.add(null);
         }

         List entry = (List)list.get(count);
         if (entry == null) {
            entry = new ArrayList();
            list.set(count, entry);
         }

         AngleObserver angleObserver2 = new AngleObserver(angleObserver.getCount(), angleObserver.resolveSmoothMatcherArray(count));
         entry.add(angleObserver2);
      }
   }

   public static PropertiesAdapter getPropertiesAdapter() {
      return propertiesAdapter;
   }

   public static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter) {
      renderElement2();
      if (preUpdateFilter != null) {
         ArrayList list = new ArrayList();
         String text = "/shaders/block.properties";
         InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text);
         if (resourceAsStream != null) {
            applyInputStream(resourceAsStream, text, list);
         }

         applyList(list);
         if (list.size() > 0) {
            angleObserverArray = resolveAngleObserverArray(list);
         }
      }
   }
}
