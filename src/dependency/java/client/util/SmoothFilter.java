package client.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SmoothFilter {
   public static Object[] resolveTargetArray(Class clazz, int count) {
      return newInstance(clazz, count);
   }

   private static Object[] resolveTargetArray(Class clazz, Iterable collection) {
      ArrayList list = Lists.newArrayList();

      for (Object element : collection) {
         list.add(element);
      }

      return list.toArray(newInstance(clazz, list.size()));
   }

   public static Iterable resolveIterable(Class clazz, Iterable collection) {
      return new AuxiliaryIterableAdapter(clazz, (Iterable[])resolveTargetArray(Iterable.class, collection), null);
   }

   public static Iterable resolveIterable(Iterable collection) {
      return transform(resolveIterable(Object.class, collection));
   }

   private static Iterable transform(Iterable collection) {
      return Iterables.transform(collection, new BaseFunctionAdapter(null));
   }

   private static Object[] newInstance(Class clazz, int count) {
      return (Object[])Array.newInstance(clazz, count);
   }
}
