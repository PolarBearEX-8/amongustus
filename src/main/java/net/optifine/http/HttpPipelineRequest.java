package net.optifine.http;

public class HttpPipelineRequest {
   private HttpRequest httpRequest = null;
   private HttpListener httpListener = null;
   private boolean closed = false;

   public HttpPipelineRequest(HttpRequest httpRequest2, HttpListener listener) {
      this.httpRequest = httpRequest2;
      this.httpListener = listener;
   }

   public HttpListener getHttpListener() {
      return this.httpListener;
   }

   public HttpRequest getHttpRequest() {
      return this.httpRequest;
   }

   public void setClosed(boolean flag) {
      this.closed = flag;
   }

   public boolean isClosed() {
      return this.closed;
   }
}
