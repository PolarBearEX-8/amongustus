package client.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DonTKnowHowToSearchForAbstractSet extends AbstractSet {
   public static final Set set = Sets.newHashSet();
   public final Map map = Maps.newHashMap();
   public final Set set2 = Sets.newIdentityHashSet();
   public final Class memberRef;
   public final List list = Lists.newArrayList();

   public Iterable resolveIterable(Class clazz) {
      return new IterableAdapter(this, clazz);
   }

   public void applyClass(Class clazz) {
      set.add(clazz);

      for (Object element : this.list) {
         if (clazz.isAssignableFrom(element.getClass())) {
            this.applyTarget(element, clazz);
         }
      }

      this.set2.add(clazz);
   }

   public static Map resolveMap(DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet) {
      return donTKnowHowToSearchForAbstractSet.map;
   }

   public Class resolveClass(Class clazz) {
      if (this.memberRef.isAssignableFrom(clazz)) {
         if (!this.set2.contains(clazz)) {
            this.applyClass(clazz);
         }

         return clazz;
      } else {
         throw new IllegalArgumentException("Don't know how to search for " + clazz);
      }
   }

   private void applyTarget(Object entry, Class clazz) {
      List list2 = (List)this.map.get(clazz);
      if (list2 == null) {
         this.map.put(clazz, Lists.newArrayList(new Object[]{entry}));
      } else {
         list2.add(entry);
      }
   }

   public DonTKnowHowToSearchForAbstractSet(Class clazz) {
      this.memberRef = clazz;
      this.set2.add(clazz);
      this.map.put(clazz, this.list);

      for (Class clazz2 : (Iterable<Class>)(Iterable<?>)set) {
         this.applyClass(clazz2);
      }
   }

   @Override
   public boolean add(Object entry) {
      for (Class clazz : (Iterable<Class>)(Iterable<?>)this.set2) {
         if (clazz.isAssignableFrom(entry.getClass())) {
            this.applyTarget(entry, clazz);
         }
      }

      return true;
   }

   @Override
   public boolean remove(Object entry) {
      Object entry2 = entry;
      boolean flag = false;

      for (Class clazz : (Iterable<Class>)(Iterable<?>)this.set2) {
         if (clazz.isAssignableFrom(entry2.getClass())) {
            List list2 = (List)this.map.get(clazz);
            if (list2 != null && list2.remove(entry2)) {
               flag = true;
            }
         }
      }

      return flag;
   }

   @Override
   public boolean contains(Object entry) {
      return Iterators.contains(this.resolveIterable(entry.getClass()).iterator(), entry);
   }

   @Override
   public Iterator iterator() {
      return (Iterator)(this.list.isEmpty() ? Collections.emptyIterator() : Iterators.unmodifiableIterator(this.list.iterator()));
   }

   @Override
   public int size() {
      return this.list.size();
   }
}
