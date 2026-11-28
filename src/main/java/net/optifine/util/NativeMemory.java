package net.optifine.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import net.optifine.config.Config;

public class NativeMemory {
   private static LongSupplier bufferAllocatedSupplier = makeLongSupplier2(
      new String[][]{
         {"sun.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"},
         {"jdk.internal.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}
      }
   );
   private static LongSupplier bufferMaximumSupplier = makeLongSupplier2(new String[][]{{"sun.misc.VM", "maxDirectMemory"}, {"jdk.internal.misc.VM", "maxDirectMemory"}});

   public static long getBufferAllocated() {
      return bufferAllocatedSupplier == null ? -1L : bufferAllocatedSupplier.getAsLong();
   }

   private static LongSupplier makeLongSupplier(String[] strings) throws Exception {
      if (strings.length < 2) {
         return null;
      } else {
         Class clazz = Class.forName(strings[0]);
         String str = strings[1];
         Class[] clazzs = new Class[0];
         Method method = clazz.getMethod(str, clazzs);
         method.setAccessible(true);
         Object obj = null;

         for (int i = 2; i < strings.length; i++) {
            String str2 = strings[i];
            obj = method.invoke(obj);
            Class clazz2 = obj.getClass();
            clazzs = new Class[0];
            method = clazz2.getMethod(str2, clazzs);
            method.setAccessible(true);
         }

         return new NativeMemory.DefaultLongSupplier(method, obj);
      }
   }

   public static long getBufferMaximum() {
      return bufferMaximumSupplier == null ? -1L : bufferMaximumSupplier.getAsLong();
   }

   private static LongSupplier makeLongSupplier2(String[][] stringsArr) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < stringsArr.length; i++) {
         String[] strings = stringsArr[i];

         try {
            return makeLongSupplier(strings);
         } catch (Throwable e) {
            list.add(e);
         }
      }

      for (Throwable t : (Iterable<Throwable>)(list)) {
         Config.warn("" + t.getClass().getName() + ": " + t.getMessage());
      }

      return null;
   }



   public static final class DefaultLongSupplier implements LongSupplier {
      private boolean disabled;
      public final Method method;
      public final Object object;

      @Override
      public long getAsLong() {
         if (this.disabled) {
            return -1L;
         } else {
            try {
               return (Long)this.method.invoke(this.object);
            } catch (Throwable e) {
               Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
               this.disabled = true;
               return -1L;
            }
         }
      }

      public DefaultLongSupplier(Method method2, Object obj) {
         this.method = method2;
         this.object = obj;
         this.disabled = false;
      }
   }
}
