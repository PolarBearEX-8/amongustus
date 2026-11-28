package net.lenni0451.commons.httpclient.handler;

import java.io.IOException;
import javax.annotation.Nonnull;
import net.lenni0451.commons.httpclient.HttpResponse;

@FunctionalInterface
public interface HttpResponseHandler<R> {
   static HttpResponseHandler<HttpResponse> identity() {
      return response -> response;
   }

   R handle(@Nonnull HttpResponse httpResponse) throws IOException;
}
