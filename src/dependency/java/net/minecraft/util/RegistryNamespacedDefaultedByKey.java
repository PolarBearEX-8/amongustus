package net.minecraft.util;

import client.util.IterableExtension;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class RegistryNamespacedDefaultedByKey implements IterableExtension {
   public final IdentityHashMap identityHashMap = new IdentityHashMap(512);
   public final List list = Lists.newArrayList();

   public int computeCount(Object entry) {
      Integer count = (Integer)this.identityHashMap.get(entry);
      return count == null ? -1 : count;
   }

   public int size() {
      return this.identityHashMap.size();
   }

   public final Object resolveTarget(int count) {
      return count >= 0 && count < this.list.size() ? this.list.get(count) : null;
   }

   public void applyTarget(Object entry, int count) {
      this.identityHashMap.put(entry, count);

      while (this.list.size() <= count) {
         this.list.add(null);
      }

      this.list.set(count, entry);
   }

   @Override
   public Iterator iterator() {
      return Iterators.filter(this.list.iterator(), Predicates.notNull());
   }
}
