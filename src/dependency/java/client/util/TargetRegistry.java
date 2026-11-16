package client.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TargetRegistry {
   public static Callback12 callback12 = resolveCallback12(
      new String[][]{
         {"sun.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"},
         {"jdk.internal.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}
      }
   );
   public static Callback12 callback2 = resolveCallback12(new String[][]{{"sun.misc.VM", "maxDirectMemory"}, {"jdk.internal.misc.VM", "maxDirectMemory"}});

   private static Callback12 resolveCallback12(String[][] textMatrix) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < textMatrix.length; index++) {
         String[] textArray = textMatrix[index];

         try {
            return resolveCallback12(textArray);
         } catch (Throwable throwable) {
            list.add(throwable);
         }
      }

      for (Throwable throwable2 : (Iterable<Throwable>)(Iterable<?>)list) {
         BuildManager.applyText3("" + throwable2.getClass().getName() + ": " + throwable2.getMessage());
      }

      return null;
   }

   public static long getTimestamp() {
      return callback12 == null ? -1L : callback12.getTimestamp();
   }

   public static long getTimestampAsTime() {
      return callback2 == null ? -1L : callback2.getTimestamp();
   }

   private static Callback12 resolveCallback12(String[] textArray) throws Exception {
      if (textArray.length < 2) {
         return null;
      } else {
         Class clazz = Class.forName(DampenerProvider.a(textArray[0]));
         String text = textArray[1];
         Class[] clazzArray = new Class[0];
         Method method = clazz.getMethod(DampenerProvider.b(text, clazz, clazzArray), clazzArray);
         method.setAccessible(true);
         Object entry = null;

         for (int index = 2; index < textArray.length; index++) {
            String text2 = textArray[index];
            entry = method.invoke(entry);
            clazz = entry.getClass();
            clazzArray = new Class[0];
            method = clazz.getMethod(DampenerProvider.b(text2, clazz, clazzArray), clazzArray);
            method.setAccessible(true);
         }

         return new MethodCallback12(method, entry);
      }
   }
}
