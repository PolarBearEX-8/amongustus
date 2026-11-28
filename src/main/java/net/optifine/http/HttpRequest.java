package net.optifine.http;

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {
   private String host = null;
   private int port = 0;
   private Proxy proxy = Proxy.NO_PROXY;
   private String method = null;
   private String file = null;
   private String http = null;
   private Map headers = new LinkedHashMap();
   private byte[] body = null;
   private int redirects = 0;
   public static final String METHOD_GET = null;
   public static final String METHOD_HEAD = null;
   public static final String METHOD_POST = null;
   public static final String HTTP_1_0 = null;
   public static final String HTTP_1_1 = null;

   public int getPort() {
      return this.port;
   }

   public String getHttp() {
      return this.http;
   }

   public int getRedirects() {
      return this.redirects;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public String getFile() {
      return this.file;
   }

   public Map getHeaders() {
      return this.headers;
   }

   public void setRedirects(int n) {
      this.redirects = n;
   }

   public String getMethod() {
      return this.method;
   }

   public String getHost() {
      return this.host;
   }

   public byte[] getBody() {
      return this.body;
   }

   public HttpRequest(String str, int n, Proxy proxy2, String str2, String str3, String str4, Map map, byte[] bytes) {
      this.host = str;
      this.port = n;
      this.proxy = proxy2;
      this.method = str2;
      this.file = str3;
      this.http = str4;
      this.headers = map;
      this.body = bytes;
   }

   static {
      String[] strings = new String[]{"HTTP/1.1", "POST", "GET", "HTTP/1.0", "HEAD"};
   }
}
