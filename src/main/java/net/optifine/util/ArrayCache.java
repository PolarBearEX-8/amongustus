package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class ArrayCache {
   private Class elementClass = null;
   private int maxCacheSize = 0;
   private ArrayDeque cache = new ArrayDeque();

   public synchronized Object allocate(int n) {
      Object obj = this.cache.pollLast();
      if (obj == null || Array.getLength(obj) < n) {
         obj = Array.newInstance(this.elementClass, n);
      }

      return obj;
   }

   public synchronized void free(Object obj) {
      if (obj != null) {
         Class clazz = obj.getClass();
         if (clazz.getComponentType() != this.elementClass) {
            throw new IllegalArgumentException("Wrong component type");
         }

         if (this.cache.size() < this.maxCacheSize) {
            this.cache.add(obj);
         }
      }
   }

   public ArrayCache(Class clazz, int n) {
      this.elementClass = clazz;
      this.maxCacheSize = n;
   }
}
