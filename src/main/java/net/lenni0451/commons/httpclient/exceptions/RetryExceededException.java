package net.lenni0451.commons.httpclient.exceptions;

import net.lenni0451.commons.httpclient.HttpResponse;

public class RetryExceededException extends HttpRequestException {
   public RetryExceededException(HttpResponse response) {
      super(response, "Maximum retry count exceeded");
   }

   public RetryExceededException(HttpResponse response, String message) {
      super(response, message);
   }
}
