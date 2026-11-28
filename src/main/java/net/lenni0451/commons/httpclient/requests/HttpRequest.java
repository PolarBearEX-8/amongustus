package net.lenni0451.commons.httpclient.requests;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.lenni0451.commons.httpclient.HeaderStore;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.RetryHandler;
import net.lenni0451.commons.httpclient.handler.HttpResponseHandler;
import net.lenni0451.commons.httpclient.utils.ResettableStorage;

public class HttpRequest extends HeaderStore<HttpRequest> {
   private final String method;
   private final URL url;
   private boolean streamedResponse;
   private HttpRequest.FollowRedirects followRedirects = HttpRequest.FollowRedirects.NOT_SET;
   private final ResettableStorage<CookieManager> cookieManager = new ResettableStorage<>();
   private final ResettableStorage<RetryHandler> retryHandler = new ResettableStorage<>();
   private final ResettableStorage<Boolean> ignoreInvalidSSL = new ResettableStorage<>();
   private WeakReference<HttpClient> boundClient;

   public HttpRequest(String method, String url) throws MalformedURLException {
      this(method, new URL(url));
   }

   public HttpRequest(String method, URL url) {
      this.method = method;
      this.url = url;
   }

   public String getMethod() {
      return this.method;
   }

   public URL getURL() {
      return this.url;
   }

   public boolean isStreamedResponse() {
      return this.streamedResponse;
   }

   public HttpRequest setStreamedResponse(boolean streamedResponse) {
      this.streamedResponse = streamedResponse;
      return this;
   }

   public HttpRequest.FollowRedirects getFollowRedirects() {
      return this.followRedirects;
   }

   public HttpRequest setFollowRedirects(boolean followRedirects) {
      return this.setFollowRedirects(followRedirects ? HttpRequest.FollowRedirects.FOLLOW : HttpRequest.FollowRedirects.IGNORE);
   }

   public HttpRequest setFollowRedirects(@Nonnull HttpRequest.FollowRedirects followRedirects) {
      this.followRedirects = followRedirects;
      return this;
   }

   public boolean isCookieManagerSet() {
      return this.cookieManager.isSet();
   }

   public HttpRequest unsetCookieManager() {
      this.cookieManager.unset();
      return this;
   }

   @Nullable
   public CookieManager getCookieManager() {
      return this.cookieManager.get();
   }

   public HttpRequest setCookieManager(@Nullable CookieManager cookieManager) {
      this.cookieManager.set(cookieManager);
      return this;
   }

   public boolean isRetryHandlerSet() {
      return this.retryHandler.isSet();
   }

   public HttpRequest unsetRetryHandler() {
      this.retryHandler.unset();
      return this;
   }

   @Nonnull
   public RetryHandler getRetryHandler() {
      return this.retryHandler.get();
   }

   public HttpRequest setRetryHandler(@Nonnull RetryHandler retryHandler) {
      this.retryHandler.set(retryHandler);
      return this;
   }

   public boolean isIgnoreInvalidSSLSet() {
      return this.ignoreInvalidSSL.isSet();
   }

   public HttpRequest unsetIgnoreInvalidSSL() {
      this.ignoreInvalidSSL.unset();
      return this;
   }

   public boolean getIgnoreInvalidSSL() {
      return this.ignoreInvalidSSL.get();
   }

   public HttpRequest setIgnoreInvalidSSL(boolean ignoreInvalidSSL) {
      this.ignoreInvalidSSL.set(ignoreInvalidSSL);
      return this;
   }

   public HttpRequest bind(@Nullable HttpClient client) {
      if (client == null) {
         this.boundClient = null;
      } else {
         this.boundClient = new WeakReference<>(client);
      }

      return this;
   }

   public HttpResponse execute() throws IOException {
      HttpClient client = null;
      if (this.boundClient != null) {
         client = this.boundClient.get();
      }

      if (client == null) {
         client = new HttpClient();
      }

      return client.execute(this);
   }

   public <R> R execute(HttpResponseHandler<R> responseHandler) throws IOException {
      HttpClient client = null;
      if (this.boundClient != null) {
         client = this.boundClient.get();
      }

      if (client == null) {
         client = new HttpClient();
      }

      return client.execute(this, responseHandler);
   }

   public static enum FollowRedirects {
      NOT_SET,
      FOLLOW,
      IGNORE;
   }
}
