package net.optifine.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.optifine.config.Config;

public class HttpPipeline {
   private static Map mapConnections = new HashMap();
   public static final String HEADER_USER_AGENT = null;
   public static final String HEADER_HOST = null;
   public static final String HEADER_ACCEPT = null;
   public static final String HEADER_LOCATION = null;
   public static final String HEADER_KEEP_ALIVE = null;
   public static final String HEADER_CONNECTION = null;
   public static final String HEADER_VALUE_KEEP_ALIVE = null;
   public static final String HEADER_TRANSFER_ENCODING = null;
   public static final String HEADER_VALUE_CHUNKED = null;

   public static byte[] get(String str, Proxy proxy) throws IOException {
      if (str.startsWith("file:")) {
         return Config.readAll(new URL(str).openStream());
      } else {
         HttpResponse httpResponse = executeRequest(makeRequest(str, proxy));
         if (httpResponse.getStatus() / 100 != 2) {
            throw new IOException("HTTP response: " + httpResponse.getStatus());
         } else {
            return httpResponse.getBody();
         }
      }
   }

   public static boolean hasActiveRequests() {
      for (Object obj : mapConnections.values()) {
         if (((HttpPipelineConnection)obj).hasActiveRequests()) {
            return true;
         }
      }

      return false;
   }

   public static HttpResponse executeRequest(HttpRequest httpRequest) throws IOException {
      HashMap map = new HashMap();
      String str = "Response";
      String str2 = "Exception";
      HttpPipeline.DefaultHttpListener obj = new HttpPipeline.DefaultHttpListener(map);
      synchronized (map) {
         addRequest(new HttpPipelineRequest(httpRequest, obj));

         try {
            map.wait();
         } catch (InterruptedException e) {
            throw new InterruptedIOException("Interrupted");
         }

         Exception e2 = (Exception)map.get("Exception");
         if (e2 != null) {
            if (e2 instanceof IOException) {
               throw (IOException)e2;
            } else if (e2 instanceof RuntimeException) {
               throw (RuntimeException)e2;
            } else {
               throw new RuntimeException(e2.getMessage(), e2);
            }
         } else {
            HttpResponse httpResponse = (HttpResponse)map.get("Response");
            if (httpResponse == null) {
               throw new IOException("Response is null");
            } else {
               return httpResponse;
            }
         }
      }
   }

   private static synchronized HttpPipelineConnection getConnection(String str, int n, Proxy proxy) {
      String str2 = makeConnectionKey(str, n, proxy);
      HttpPipelineConnection httpPipelineConnection = (HttpPipelineConnection)mapConnections.get(str2);
      if (httpPipelineConnection == null) {
         httpPipelineConnection = new HttpPipelineConnection(str, n, proxy);
         mapConnections.put(str2, httpPipelineConnection);
      }

      return httpPipelineConnection;
   }

   private static String makeConnectionKey(String str, int n, Proxy proxy) {
      return str + ":" + n + "-" + proxy;
   }

   public static void addRequest(String str, HttpListener listener, Proxy proxy) throws IOException {
      HttpRequest httpRequest = makeRequest(str, proxy);
      addRequest(new HttpPipelineRequest(httpRequest, listener));
   }

   public static void addRequest(String str, HttpListener listener) throws IOException {
      addRequest(str, listener, Proxy.NO_PROXY);
   }

   public static HttpRequest makeRequest(String str, Proxy proxy) throws IOException {
      URL url = new URL(str);
      if (!url.getProtocol().equals("http")) {
         throw new IOException("Only protocol http is supported: " + url);
      } else {
         String file = url.getFile();
         String host = url.getHost();
         int port = url.getPort();
         if (port <= 0) {
            port = 80;
         }

         String str2 = "GET";
         String str3 = "HTTP/1.1";
         LinkedHashMap map = new LinkedHashMap();
         map.put("User-Agent", "Java/" + System.getProperty("java.version"));
         map.put("Host", host);
         map.put("Accept", "text/html, image/gif, image/png");
         map.put("Connection", "keep-alive");
         byte[] bytes = new byte[0];
         return new HttpRequest(host, port, proxy, str2, file, str3, map, bytes);
      }
   }

   private static synchronized void removeConnection(String str, int n, Proxy proxy, HttpPipelineConnection httpPipelineConnection) {
      String str2 = makeConnectionKey(str, n, proxy);
      if (((HttpPipelineConnection)mapConnections.get(str2)) == httpPipelineConnection) {
         mapConnections.remove(str2);
      }
   }

   public static void addRequest(HttpPipelineRequest httpPipelineRequest) {
      HttpRequest httpRequest = httpPipelineRequest.getHttpRequest();

      for (HttpPipelineConnection i = getConnection(httpRequest.getHost(), httpRequest.getPort(), httpRequest.getProxy());
         !i.addRequest(httpPipelineRequest);
         i = getConnection(httpRequest.getHost(), httpRequest.getPort(), httpRequest.getProxy())
      ) {
         removeConnection(httpRequest.getHost(), httpRequest.getPort(), httpRequest.getProxy(), i);
      }
   }

   public static byte[] get(String str) throws IOException {
      return get(str, Proxy.NO_PROXY);
   }


   public static final class DefaultHttpListener implements HttpListener {
      public final Map map;

      @Override
      public void failed(HttpRequest httpRequest, Exception e) {
         synchronized (this.map) {
            this.map.put("Exception", e);
            this.map.notifyAll();
         }
      }

      public DefaultHttpListener(Map map2) {
         this.map = map2;
      }

      @Override
      public void finished(HttpRequest httpRequest, HttpResponse httpResponse) {
         synchronized (this.map) {
            this.map.put("Response", httpResponse);
            this.map.notifyAll();
         }
      }
   }
}
