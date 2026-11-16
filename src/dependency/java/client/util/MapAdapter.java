package client.util;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MapAdapter implements Map {
   public final Map map = Maps.newLinkedHashMap();

   public Object resolveTarget(String text, Object entry) {
      return this.map.put(text.toLowerCase(), entry);
   }

   @Override
   public Object remove(Object entry) {
      return this.map.remove(entry.toString().toLowerCase());
   }

   @Override
   public Object get(Object entry) {
      return this.map.get(entry.toString().toLowerCase());
   }

   @Override
   public Object put(Object entry, Object entry2) {
      return this.resolveTarget((String)entry, entry2);
   }

   @Override
   public Collection values() {
      return this.map.values();
   }

   @Override
   public void clear() {
      this.map.clear();
   }

   @Override
   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   @Override
   public int size() {
      return this.map.size();
   }

   @Override
   public Set entrySet() {
      return this.map.entrySet();
   }

   @Override
   public void putAll(Map map2) {
      for (Entry entry : (Iterable<Entry>)(Iterable<?>)map2.entrySet()) {
         this.resolveTarget((String)entry.getKey(), entry.getValue());
      }
   }

   @Override
   public Set keySet() {
      return this.map.keySet();
   }

   @Override
   public boolean containsKey(Object entry) {
      return this.map.containsKey(entry.toString().toLowerCase());
   }

   @Override
   public boolean containsValue(Object entry) {
      return this.map.containsKey(entry);
   }
}
