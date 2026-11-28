package net.lenni0451.commons.httpclient.exceptions;

import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;

public class HttpRequestException extends IOException {
   private final HttpResponse response;

   public HttpRequestException(HttpResponse response) {
      this(response, "Request failed: " + response.getStatusCode() + " " + response.getStatusMessage());
   }

   public HttpRequestException(HttpResponse response, String message) {
      super(message);
      this.response = response;
   }

   public HttpResponse getResponse() {
      return this.response;
   }
}
