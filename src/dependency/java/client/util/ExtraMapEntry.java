package client.util;

import java.util.HashMap;
import java.util.Map;

public class ExtraMapEntry {
   public static Map map = new HashMap();
   public static RayTraceEvaluator rayTraceEvaluator = new RayTraceEvaluator(1);

   public static float computeFactor(int count, float posX, float posY, float posZ) {
      synchronized (map) {
         Integer count2 = count;
         SplineSelector splineSelector = (SplineSelector)map.get(count2);
         if (splineSelector == null) {
            splineSelector = new SplineSelector(posX, posY, posZ);
            map.put(count2, splineSelector);
         }

         return splineSelector.computeFactor(posX, posY, posZ);
      }
   }

   public static void clear() {
      synchronized (map) {
         map.clear();
      }
   }

   public static int getCount() {
      synchronized (rayTraceEvaluator) {
         return rayTraceEvaluator.getCount();
      }
   }
}
