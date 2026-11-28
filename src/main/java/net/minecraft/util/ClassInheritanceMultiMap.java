package net.minecraft.util;

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

public class ClassInheritanceMultiMap extends AbstractSet {
   private static final Set ALL_KNOWN = Sets.newHashSet();
   private final Map map = Maps.newHashMap();
   private final Set knownKeys = Sets.newIdentityHashSet();
   private final Class baseClass;
   private final List values = Lists.newArrayList();

   private void addForClass(Object obj, Class clazz) {
      List list = (List)this.map.get(clazz);
      if (list == null) {
         this.map.put(clazz, Lists.newArrayList(new Object[]{obj}));
      } else {
         list.add(obj);
      }
   }

   public Iterable getByClass(Class clazz) {
      return new ClassInheritanceMultiMap.EntryIterable(this, clazz);
   }

   @Override
   public int size() {
      return this.values.size();
   }

   public static Map accessMap(ClassInheritanceMultiMap classInheritanceMultiMap) {
      return classInheritanceMultiMap.map;
   }

   public void createLookup(Class clazz) {
      ALL_KNOWN.add(clazz);

      for (Object obj : this.values) {
         if (clazz.isAssignableFrom(obj.getClass())) {
            this.addForClass(obj, clazz);
         }
      }

      this.knownKeys.add(clazz);
   }

   @Override
   public Iterator iterator() {
      return (Iterator)(this.values.isEmpty() ? Collections.emptyIterator() : Iterators.unmodifiableIterator(this.values.iterator()));
   }

   public ClassInheritanceMultiMap(Class clazz) {
      this.baseClass = clazz;
      this.knownKeys.add(clazz);
      this.map.put(clazz, this.values);

      for (Class clazz2 : (Iterable<Class>)(ALL_KNOWN)) {
         this.createLookup(clazz2);
      }
   }

   @Override
   public boolean contains(Object obj) {
      return Iterators.contains(this.getByClass(obj.getClass()).iterator(), obj);
   }

   @Override
   public boolean remove(Object obj) {
      Object obj2 = obj;
      boolean flag = false;

      for (Class clazz : (Iterable<Class>)(this.knownKeys)) {
         if (clazz.isAssignableFrom(obj2.getClass())) {
            List list = (List)this.map.get(clazz);
            if (list != null && list.remove(obj2)) {
               flag = true;
            }
         }
      }

      return flag;
   }

   @Override
   public boolean add(Object obj) {
      for (Class clazz : (Iterable<Class>)(this.knownKeys)) {
         if (clazz.isAssignableFrom(obj.getClass())) {
            this.addForClass(obj, clazz);
         }
      }

      return true;
   }

   public Object get(int n) {
      return this.values.get(n);
   }

   public Class initializeClassLookup(Class clazz) {
      if (this.baseClass.isAssignableFrom(clazz)) {
         if (!this.knownKeys.contains(clazz)) {
            this.createLookup(clazz);
         }

         return clazz;
      } else {
         throw new IllegalArgumentException("Don't know how to search for " + clazz);
      }
   }


   public static class EntryIterable implements Iterable {
      public final Class targetClass;
      public final ClassInheritanceMultiMap multiMap;

      public EntryIterable(ClassInheritanceMultiMap classInheritanceMultiMap, Class clazz) {
         this.multiMap = classInheritanceMultiMap;
         this.targetClass = clazz;
      }

      @Override
      public Iterator iterator() {
         List list = (List)ClassInheritanceMultiMap.accessMap(this.multiMap).get(this.multiMap.initializeClassLookup(this.targetClass));
         if (list == null) {
            return Collections.emptyIterator();
         } else {
            return Iterators.filter(list.iterator(), this.targetClass);
         }
      }
   }
}
