package net.optifine.http;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
   private int status = 0;
   private String statusLine = null;
   private Map headers = new LinkedHashMap();
   private byte[] body = null;

   public Map getHeaders() {
      return this.headers;
   }

   public byte[] getBody() {
      return this.body;
   }

   public String getStatusLine() {
      return this.statusLine;
   }

   public int getStatus() {
      return this.status;
   }

   public String getHeader(String str) {
      return (String)this.headers.get(str);
   }

   public HttpResponse(int n, String str, Map map, byte[] bytes) {
      this.status = n;
      this.statusLine = str;
      this.headers = map;
      this.body = bytes;
   }
}
