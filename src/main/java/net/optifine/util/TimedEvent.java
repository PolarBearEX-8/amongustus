package net.optifine.util;

import java.util.HashMap;
import java.util.Map;

public class TimedEvent {
   private static Map mapEventTimes = new HashMap();

   public static boolean isActive(String str, long l) {
      synchronized (mapEventTimes) {
         long l2 = System.currentTimeMillis();
         Long val = (Long)mapEventTimes.get(str);
         if (val == null) {
            val = new Long(l2);
            mapEventTimes.put(str, val);
         }

         long l3 = val;
         if (l2 < l3 + l) {
            return false;
         } else {
            mapEventTimes.put(str, new Long(l2));
            return true;
         }
      }
   }
}
