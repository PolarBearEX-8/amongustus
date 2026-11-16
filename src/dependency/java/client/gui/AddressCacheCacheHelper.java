package client.gui;

import client.util.DampenerProvider;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.Map;

public class AddressCacheCacheHelper {
   public static void renderElement() {
      renderElement3();
      renderElement2();
   }

   private static void renderElement2() {
      try {
         Field declaredField = InetAddress.class.getDeclaredField("addressCache");
         declaredField.setAccessible(true);
         Object entry = declaredField.get(null);
         synchronized (entry) {
            Class classItem = entry.getClass();
            String text = DampenerProvider.c(classItem, "cache");
            Field declaredField2 = classItem.getDeclaredField(text);
            declaredField2.setAccessible(true);
            ((Map)declaredField2.get(entry)).clear();
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static void renderElement3() {
      try {
         Runtime.getRuntime().exec("cmd ipconfig /flushdns");
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }
}
