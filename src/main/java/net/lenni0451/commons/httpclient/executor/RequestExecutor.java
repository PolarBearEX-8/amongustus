package net.lenni0451.commons.httpclient.executor;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;
import net.lenni0451.commons.httpclient.requests.HttpRequest;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;

public abstract class RequestExecutor {
   @Nonnull
   protected final HttpClient client;

   public RequestExecutor(@Nonnull HttpClient client) {
      this.client = client;
   }

   @Nonnull
   public abstract HttpResponse execute(@Nonnull HttpRequest httpRequest) throws IOException, InterruptedException;

   protected final boolean isFollowRedirects(@Nonnull HttpRequest request) {
      switch (request.getFollowRedirects()) {
         case NOT_SET:
            return this.client.isFollowRedirects();
         case FOLLOW:
            return true;
         case IGNORE:
            return false;
         default:
            throw new IllegalStateException("Unexpected value: " + request.getFollowRedirects());
      }
   }

   @Nullable
   protected final CookieManager getCookieManager(@Nonnull HttpRequest request) {
      return request.isCookieManagerSet() ? request.getCookieManager() : this.client.getCookieManager();
   }

   protected final boolean isIgnoreInvalidSSL(@Nonnull HttpRequest request) {
      return request.isIgnoreInvalidSSLSet() ? request.getIgnoreInvalidSSL() : this.client.isIgnoreInvalidSSL();
   }

   protected final Map<String, List<String>> getHeaders(@Nonnull HttpRequest request, @Nullable CookieManager cookieManager) throws IOException {
      return this.getHeaders(request, cookieManager, true);
   }

   protected final Map<String, List<String>> getHeaders(@Nonnull HttpRequest request, @Nullable CookieManager cookieManager, boolean includeContentHeaders) throws IOException {
      Map<String, List<String>> headers = new HashMap<>();
      if (request instanceof HttpContentRequest && includeContentHeaders) {
         HttpContent content = ((HttpContentRequest)request).getContent();
         if (content != null) {
            headers.put("Content-Type", Collections.singletonList(content.getContentType().toString()));
            headers.put("Content-Length", Collections.singletonList(String.valueOf(content.getContentLength())));
         }
      }

      return HttpRequestUtils.mergeHeaders(
         HttpRequestUtils.getCookieHeaders(cookieManager, request.getURL()), headers, this.client.getHeaders(), request.getHeaders()
      );
   }
}
