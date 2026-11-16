package client.util;

import client.event.CompositeListener;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GETManager {
   public static final ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(
      Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Downloader %d").build())
   );
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger = LogManager.getLogger();

   public static String resolveText(URL url) throws IOException {
      HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
      httpURLConnection.setRequestMethod("GET");
      BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      StringBuilder builder = new StringBuilder();

      String text;
      while ((text = reader.readLine()) != null) {
         builder.append(text);
         builder.append('\r');
      }

      reader.close();
      return builder.toString();
   }

   public static Logger getLogger() {
      return logger;
   }

   public static String resolveText(URL url, Map map, boolean flag) {
      return resolveText(url, resolveText(map), flag);
   }

   public static ListenableFuture submit(File file, String text, Map map, int count, CompositeListener compositeListener, Proxy proxy) {
      return listeningExecutorService.submit(new GeometryWatcher(compositeListener, text, proxy, map, file, count));
   }

   private static String resolveText(URL url, String text, boolean flag) {
      try {
         Proxy proxy = VanillaRunnable.getVanillaRunnable() == null ? null : VanillaRunnable.getVanillaRunnable().getProxy();
         if (proxy == null) {
            proxy = Proxy.NO_PROXY;
         }

         HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection(proxy);
         httpURLConnection.setRequestMethod("POST");
         httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         httpURLConnection.setRequestProperty("Content-Length", "" + text.getBytes().length);
         httpURLConnection.setRequestProperty("Content-Language", "en-US");
         httpURLConnection.setUseCaches(false);
         httpURLConnection.setDoInput(true);
         httpURLConnection.setDoOutput(true);
         DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
         dataOutputStream.writeBytes(text);
         dataOutputStream.flush();
         dataOutputStream.close();
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
         StringBuffer builder = new StringBuffer();

         String text2;
         while ((text2 = reader.readLine()) != null) {
            builder.append(text2);
            builder.append('\r');
         }

         reader.close();
         return builder.toString();
      } catch (Exception exception) {
         if (!flag) {
            logger.error("Could not post to " + url, exception);
         }

         return "";
      }
   }

   public static String resolveText(Map map) {
      StringBuilder builder = new StringBuilder();

      for (Object entryObj : map.entrySet()) {
         Entry entry = (Entry)entryObj;
         if (builder.length() > 0) {
            builder.append('&');
         }

         try {
            builder.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
         } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
         }

         if (entry.getValue() != null) {
            builder.append('=');

            try {
               builder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException unsupportedEncodingException2) {
               unsupportedEncodingException2.printStackTrace();
            }
         }
      }

      return builder.toString();
   }

   public static int getCount() throws IOException {
      ServerSocket serverSocket = null;
      boolean flag = false ;

      int count;
      try {
         flag = true;
         serverSocket = new ServerSocket(0);
         count = serverSocket.getLocalPort();
         flag = false;
      } finally {
         if (flag) {
            try {
               if (serverSocket != null) {
                  serverSocket.close();
               }
            } catch (IOException ioException) {
            }
         }
      }

      try {
         serverSocket.close();
      } catch (IOException ioException2) {
      }

      return count;
   }
}
