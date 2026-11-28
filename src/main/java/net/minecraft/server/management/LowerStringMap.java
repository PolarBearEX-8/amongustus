package net.minecraft.server.management;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LowerStringMap implements Map {
   private final Map internalMap = Maps.newLinkedHashMap();

   @Override
   public boolean containsKey(Object obj) {
      return this.internalMap.containsKey(obj.toString().toLowerCase());
   }

   @Override
   public void putAll(Map map) {
      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         this.put((String)entry.getKey(), entry.getValue());
      }
   }

   @Override
   public Object put(Object obj, Object obj2) {
      return this.put((String)obj, obj2);
   }

   @Override
   public int size() {
      return this.internalMap.size();
   }

   @Override
   public Object remove(Object obj) {
      return this.internalMap.remove(obj.toString().toLowerCase());
   }

   @Override
   public boolean containsValue(Object obj) {
      return this.internalMap.containsKey(obj);
   }

   @Override
   public Object get(Object obj) {
      return this.internalMap.get(obj.toString().toLowerCase());
   }

   @Override
   public void clear() {
      this.internalMap.clear();
   }

   @Override
   public Set keySet() {
      return this.internalMap.keySet();
   }

   public Object put(String str, Object obj) {
      return this.internalMap.put(str.toLowerCase(), obj);
   }

   @Override
   public Collection values() {
      return this.internalMap.values();
   }

   @Override
   public Set entrySet() {
      return this.internalMap.entrySet();
   }

   @Override
   public boolean isEmpty() {
      return this.internalMap.isEmpty();
   }
}
