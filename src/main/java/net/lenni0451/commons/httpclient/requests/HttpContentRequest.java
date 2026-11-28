package net.lenni0451.commons.httpclient.requests;

import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;
import net.lenni0451.commons.httpclient.content.HttpContent;

public class HttpContentRequest extends HttpRequest {
   @Nullable
   private HttpContent content;

   public HttpContentRequest(String method, String url) throws MalformedURLException {
      super(method, url);
   }

   public HttpContentRequest(String method, URL url) {
      super(method, url);
   }

   public boolean hasContent() {
      return this.content != null;
   }

   @Nullable
   public HttpContent getContent() {
      return this.content;
   }

   public HttpContentRequest setContent(@Nullable HttpContent content) {
      this.content = content;
      return this;
   }
}
