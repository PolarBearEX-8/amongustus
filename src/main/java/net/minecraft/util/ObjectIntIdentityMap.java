package net.minecraft.util;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class ObjectIntIdentityMap implements IObjectIntIterable {
   private final IdentityHashMap identityMap = new IdentityHashMap(512);
   private final List objectList = Lists.newArrayList();

   @Override
   public Iterator iterator() {
      return Iterators.filter(this.objectList.iterator(), Predicates.notNull());
   }

   public void put(Object obj, int n) {
      this.identityMap.put(obj, n);

      while (this.objectList.size() <= n) {
         this.objectList.add(null);
      }

      this.objectList.set(n, obj);
   }

   public int size() {
      return this.identityMap.size();
   }

   public final Object getByValue(int n) {
      return n >= 0 && n < this.objectList.size() ? this.objectList.get(n) : null;
   }

   public int get(Object obj) {
      Integer num = (Integer)this.identityMap.get(obj);
      return num == null ? -1 : num;
   }
}
