package client.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class GeometryDeserializer {
   public static Map resolveMap(Iterable collection, Iterable collection2) {
      return resolveMap(collection, collection2, Maps.newLinkedHashMap());
   }

   public static Map resolveMap(Iterable collection, Iterable collection2, Map map) {
      Iterator iterator = collection2.iterator();

      for (Object element : collection) {
         map.put(element, iterator.next());
      }

      if (iterator.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return map;
      }
   }
}
