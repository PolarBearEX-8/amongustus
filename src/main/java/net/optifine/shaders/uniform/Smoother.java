package net.optifine.shaders.uniform;

import java.util.HashMap;
import java.util.Map;
import net.optifine.util.SmoothFloat;
import net.optifine.util.CounterInt;

public class Smoother {
   private static Map mapSmoothValues = new HashMap();
   private static CounterInt counterIds = new CounterInt(1);

   public static void resetValues() {
      synchronized (mapSmoothValues) {
         mapSmoothValues.clear();
      }
   }

   public static int getNextId() {
      synchronized (counterIds) {
         return counterIds.nextValue();
      }
   }

   public static float getSmoothValue(int n, float f, float f2, float f3) {
      synchronized (mapSmoothValues) {
         Integer num = n;
         SmoothFloat smoothFloat = (SmoothFloat)mapSmoothValues.get(num);
         if (smoothFloat == null) {
            smoothFloat = new SmoothFloat(f, f2, f3);
            mapSmoothValues.put(num, smoothFloat);
         }

         return smoothFloat.getSmoothValue(f, f2, f3);
      }
   }
}
