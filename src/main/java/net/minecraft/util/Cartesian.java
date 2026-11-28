package net.minecraft.util;

import com.google.common.base.Function;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cartesian {
   private static Object[] toArray(Class clazz, Iterable items) {
      ArrayList list = Lists.newArrayList();

      for (Object obj : items) {
         list.add(obj);
      }

      return list.toArray(createArray(clazz, list.size()));
   }

   public static Iterable cartesianProduct(Class clazz, Iterable items) {
      return new Cartesian.Product(clazz, (Iterable[])toArray(Iterable.class, items), null);
   }

   public static Iterable cartesianProduct(Iterable items) {
      return arraysAsLists(cartesianProduct(Object.class, items));
   }

   public static Object[] accessCreateArray(Class clazz, int n) {
      return createArray(clazz, n);
   }

   private static Object[] createArray(Class clazz, int n) {
      return (Object[])Array.newInstance(clazz, n);
   }

   private static Iterable arraysAsLists(Iterable items) {
      return Iterables.transform(items, new Cartesian.GetList(null));
   }


   public static class InnerEntry {
   }

   public static class GetList implements Function {
      public List apply(Object[] array) {
         return Arrays.asList(array);
      }

      private GetList() {
      }

      public GetList(Cartesian.InnerEntry obj) {
         this();
      }

      public Object apply(Object obj) {
         return this.apply((Object[])obj);
      }
   }

   public static class Product implements Iterable {
      private final Class clazz;
      private final Iterable[] iterables;

      private Product(Class clazz2, Iterable[] itemsArr) {
         this.clazz = clazz2;
         this.iterables = itemsArr;
      }

      public Product(Class clazz2, Iterable[] itemsArr, Cartesian.InnerEntry obj) {
         this(clazz2, itemsArr);
      }

      @Override
      public Iterator iterator() {
         return (Iterator)(this.iterables.length <= 0
            ? Collections.singletonList(Cartesian.accessCreateArray(this.clazz, 0)).iterator()
            : new Cartesian.Product.ProductIterator(this.clazz, this.iterables, null));
      }


      public static class ProductIterator extends UnmodifiableIterator {
         private int index = -2;
         private final Iterable[] iterables;
         private final Iterator[] iterators;
         private final Object[] results;

         public Object[] nextTuple() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               while (this.index < this.iterators.length) {
                  this.results[this.index] = this.iterators[this.index].next();
                  this.index++;
               }

               return (Object[])this.results.clone();
            }
         }

         public boolean hasNext() {
            if (this.index == -2) {
               this.index = 0;

               for (Iterator it : this.iterators) {
                  if (!it.hasNext()) {
                     this.endOfData();
                     break;
                  }
               }

               return true;
            } else {
               if (this.index >= this.iterators.length) {
                  for (this.index = this.iterators.length - 1; this.index >= 0; this.index--) {
                     Iterator it2 = this.iterators[this.index];
                     if (it2.hasNext()) {
                        break;
                     }

                     if (this.index == 0) {
                        this.endOfData();
                        break;
                     }

                     it2 = this.iterables[this.index].iterator();
                     this.iterators[this.index] = it2;
                     if (!it2.hasNext()) {
                        this.endOfData();
                        break;
                     }
                  }
               }

               return this.index >= 0;
            }
         }

         public ProductIterator(Class clazz, Iterable[] itemsArr, Cartesian.InnerEntry obj) {
            this(clazz, itemsArr);
         }

         private void endOfData() {
            this.index = -1;
            Arrays.fill(this.iterators, null);
            Arrays.fill(this.results, null);
         }

         public Object next() {
            return this.nextTuple();
         }

         private ProductIterator(Class clazz, Iterable[] itemsArr) {
            this.iterables = itemsArr;
            this.iterators = (Iterator[])Cartesian.accessCreateArray(Iterator.class, this.iterables.length);

            for (int i = 0; i < this.iterables.length; i++) {
               this.iterators[i] = itemsArr[i].iterator();
            }

            this.results = Cartesian.accessCreateArray(clazz, this.iterators.length);
         }
      }
   }
}
