package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapPopulator {
   public static Map populateMap(Iterable items, Iterable items2, Map map) {
      Iterator it = items2.iterator();

      for (Object obj : items) {
         map.put(obj, it.next());
      }

      if (it.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return map;
      }
   }

   public static Map createMap(Iterable items, Iterable items2) {
      return populateMap(items, items2, Maps.newLinkedHashMap());
   }
}
