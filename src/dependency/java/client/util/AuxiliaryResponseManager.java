package client.util;

import client.event.PreRenderConsumer;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.item.DiamondRelic;

public class AuxiliaryResponseManager {
   public static Map map = new HashMap();
   public static final String labelText6 = "User-Agent";
   public static final String labelText5 = "Host";
   public static final String labelText3 = "Accept";
   public static final String labelText9 = "Location";
   public static final String labelText7 = "Keep-Alive";
   public static final String labelText4 = "Connection";
   public static final String labelText8 = "keep-alive";
   public static final String labelText = "Transfer-Encoding";
   public static final String labelText2 = "chunked";

   public static ComplexStringEntry resolveComplexStringEntry(HEADManager hEADManager) throws IOException {
      HashMap map2 = new HashMap();
      ResponseManager responseManager = new ResponseManager(map2);
      synchronized (map2) {
         applyDiamondRelic(new DiamondRelic(hEADManager, responseManager));

         try {
            map2.wait();
         } catch (InterruptedException interruptedException) {
            throw new InterruptedIOException("Interrupted");
         }

         Exception exception = (Exception)map2.get("Exception");
         if (exception != null) {
            if (exception instanceof IOException) {
               throw (IOException)exception;
            } else if (exception instanceof RuntimeException) {
               throw (RuntimeException)exception;
            } else {
               throw new RuntimeException(exception.getMessage(), exception);
            }
         } else {
            ComplexStringEntry complexStringEntry = (ComplexStringEntry)map2.get("Response");
            if (complexStringEntry == null) {
               throw new IOException("Response is null");
            } else {
               return complexStringEntry;
            }
         }
      }
   }

   private static synchronized AlreadyConnectedManager resolveAlreadyConnectedManager(String text, int count, Proxy proxy) {
      String text2 = resolveText(text, count, proxy);
      AlreadyConnectedManager alreadyConnectedManager = (AlreadyConnectedManager)map.get(text2);
      if (alreadyConnectedManager == null) {
         alreadyConnectedManager = new AlreadyConnectedManager(text, count, proxy);
         map.put(text2, alreadyConnectedManager);
      }

      return alreadyConnectedManager;
   }

   public static void applyText(String text, PreRenderConsumer preRenderConsumer) throws IOException {
      applyText(text, preRenderConsumer, Proxy.NO_PROXY);
   }

   public static boolean isActive() {
      Iterator iterator = map.values().iterator();

      while (iterator.hasNext()) {
         if (((AlreadyConnectedManager)iterator.next()).size()) {
            return true;
         }
      }

      return false;
   }

   private static synchronized void applyText(String text, int count, Proxy proxy, AlreadyConnectedManager alreadyConnectedManager) {
      String text2 = resolveText(text, count, proxy);
      if ((AlreadyConnectedManager)map.get(text2) == alreadyConnectedManager) {
         map.remove(text2);
      }
   }

   public static void applyDiamondRelic(DiamondRelic diamondRelic) {
      HEADManager hEADManager = diamondRelic.getHEADManager();

      for (AlreadyConnectedManager alreadyConnectedManager = resolveAlreadyConnectedManager(hEADManager.getLabelText9(), hEADManager.getCount2(), hEADManager.getProxy());
         !alreadyConnectedManager.matchesDiamondRelic(diamondRelic);
         alreadyConnectedManager = resolveAlreadyConnectedManager(hEADManager.getLabelText9(), hEADManager.getCount2(), hEADManager.getProxy())
      ) {
         applyText(hEADManager.getLabelText9(), hEADManager.getCount2(), hEADManager.getProxy(), alreadyConnectedManager);
      }
   }

   private static String resolveText(String text, int count, Proxy proxy) {
      return text + ":" + count + "-" + proxy;
   }

   public static byte[] resolveByteArray(String text) throws IOException {
      return resolveByteArray(text, Proxy.NO_PROXY);
   }

   public static HEADManager resolveHEADManager(String text, Proxy proxy) throws IOException {
      URL url = new URL(text);
      if (!url.getProtocol().equals("http")) {
         throw new IOException("Only protocol http is supported: " + url);
      } else {
         String file = url.getFile();
         String host = url.getHost();
         int port = url.getPort();
         if (port <= 0) {
            port = 80;
         }

         String text2 = "GET";
         String text3 = "HTTP/1.1";
         LinkedHashMap map2 = new LinkedHashMap();
         map2.put("User-Agent", "Java/" + System.getProperty("java.version"));
         map2.put("Host", host);
         map2.put("Accept", "text/html, image/gif, image/png");
         map2.put("Connection", "keep-alive");
         byte[] byteValueArray = new byte[0];
         return new HEADManager(host, port, proxy, text2, file, text3, map2, byteValueArray);
      }
   }

   public static void applyText(String text, PreRenderConsumer preRenderConsumer, Proxy proxy) throws IOException {
      HEADManager hEADManager = resolveHEADManager(text, proxy);
      applyDiamondRelic(new DiamondRelic(hEADManager, preRenderConsumer));
   }

   public static byte[] resolveByteArray(String text, Proxy proxy) throws IOException {
      if (text.startsWith("file:")) {
         return BuildManager.resolveByteArray(new URL(text).openStream());
      } else {
         ComplexStringEntry complexStringEntry = resolveComplexStringEntry(resolveHEADManager(text, proxy));
         if (complexStringEntry.getCount() / 100 != 2) {
            throw new IOException("HTTP response: " + complexStringEntry.getCount());
         } else {
            return complexStringEntry.getByteFlagArray();
         }
      }
   }
}
