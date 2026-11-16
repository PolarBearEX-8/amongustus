package client.util;

import java.util.HashMap;
import java.util.Map;

public class SupplementalMapEntry {
   public static Map map = new HashMap();

   public static boolean matchesText(String text, long value) {
      synchronized (map) {
         long timestamp = System.currentTimeMillis();
         Long value2 = (Long)map.get(text);
         if (value2 == null) {
            value2 = new Long(timestamp);
            map.put(text, value2);
         }

         long value3 = value2;
         if (timestamp < value3 + value) {
            return false;
         } else {
            map.put(text, new Long(timestamp));
            return true;
         }
      }
   }
}
