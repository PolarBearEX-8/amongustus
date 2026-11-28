package net.augustus.utils.network;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.Map;

public class DnsCacheFlusher {

   private static void clearJvmAddressCache() {
      try {
         Field declaredField = InetAddress.class.getDeclaredField("addressCache");
         declaredField.setAccessible(true);
         Object obj = declaredField.get(null);
         synchronized (obj) {
            Class clazz = obj.getClass();
            Field declaredField2 = clazz.getDeclaredField("cache");
            declaredField2.setAccessible(true);
            ((Map)declaredField2.get(obj)).clear();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void flushOsDnsCache() {
      try {
         Runtime.getRuntime().exec("cmd ipconfig /flushdns");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void flushDns() {
      flushOsDnsCache();
      clearJvmAddressCache();
   }
}
