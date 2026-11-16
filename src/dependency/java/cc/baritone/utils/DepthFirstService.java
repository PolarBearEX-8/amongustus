package cc.baritone.utils;

import java.lang.reflect.Field;

public class DepthFirstService {
   public static Object resolveTarget(Object entry, Class clazz, Class clazz2) {
      try {
         Field field = resolveField(clazz, clazz2);
         return clazz2.cast(field.get(entry));
      } catch (Exception exception) {
         throw new RuntimeException("WorldDownloader: Couldn't get Field of type \"" + clazz2 + "\" from object \"" + entry + "\" !", exception);
      }
   }

   public static void applyTarget(Object entry, Class clazz, Class clazz2, Object entry2) {
      try {
         resolveField(clazz, clazz2).set(entry, entry2);
      } catch (Exception exception) {
         throw new RuntimeException("WorldDownloader: Couldn't set Field of type \"" + clazz2 + "\" from object \"" + entry + "\" to " + entry2 + "!", exception);
      }
   }

   public static void applyTarget(Object entry, Class clazz, Object entry2) {
      Object entry3 = entry;
      Class clazz2;
      if (entry instanceof Class) {
         clazz2 = (Class)entry;
         entry3 = null;
      } else {
         clazz2 = entry.getClass();
      }

      try {
         resolveField(clazz2, clazz).set(entry3, entry2);
      } catch (Exception exception) {
         throw new RuntimeException("WorldDownloader: Couldn't set Field of type \"" + clazz + "\" from object \"" + entry3 + "\" to " + entry2 + "!", exception);
      }
   }

   public static Field resolveField(Class clazz, Class clazz2) {
      for (Field field : clazz.getDeclaredFields()) {
         if (field.getType().equals(clazz2)) {
            try {
               field.setAccessible(true);
               return field;
            } catch (Exception exception) {
               throw new RuntimeException("WorldDownloader: Couldn't steal Field of type \"" + clazz2 + "\" from class \"" + clazz + "\" !", exception);
            }
         }
      }

      throw new RuntimeException("WorldDownloader: Couldn't steal Field of type \"" + clazz2 + "\" from class \"" + clazz + "\" !");
   }

   public static Object resolveTarget(Object entry, Class clazz) {
      Object entry2 = entry;
      Class clazz2;
      if (entry instanceof Class) {
         clazz2 = (Class)entry;
         entry2 = null;
      } else {
         clazz2 = entry.getClass();
      }

      try {
         Field field = resolveField(clazz2, clazz);
         return clazz.cast(field.get(entry2));
      } catch (Exception exception) {
         throw new RuntimeException("WorldDownloader: Couldn't get Field of type \"" + clazz + "\" from object \"" + entry2 + "\" !", exception);
      }
   }
}
