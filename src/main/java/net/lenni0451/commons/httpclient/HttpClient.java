package net.lenni0451.commons.httpclient;

import java.io.IOException;
import java.net.CookieManager;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLException;
import net.lenni0451.commons.httpclient.exceptions.RetryExceededException;
import net.lenni0451.commons.httpclient.executor.ExecutorType;
import net.lenni0451.commons.httpclient.executor.RequestExecutor;
import net.lenni0451.commons.httpclient.handler.HttpResponseHandler;
import net.lenni0451.commons.httpclient.proxy.ProxyHandler;
import net.lenni0451.commons.httpclient.requests.HttpRequest;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;

public class HttpClient extends HeaderStore<HttpClient> implements HttpRequestBuilder {
   private RequestExecutor executor;
   @Nullable
   private CookieManager cookieManager = new CookieManager();
   private boolean followRedirects = true;
   private int connectTimeout = 10000;
   private int readTimeout = 10000;
   private RetryHandler retryHandler = new RetryHandler();
   private ProxyHandler proxyHandler = new ProxyHandler();
   private boolean ignoreInvalidSSL = false;

   public HttpClient() {
      this(ExecutorType.AUTO);
   }

   public HttpClient(@Nonnull ExecutorType executorType) {
      this(executorType::makeExecutor);
   }

   public HttpClient(@Nonnull Function<HttpClient, RequestExecutor> executorSupplier) {
      this.setExecutor(executorSupplier);
   }

   public HttpClient setExecutor(@Nonnull Function<HttpClient, RequestExecutor> executorSupplier) {
      this.executor = executorSupplier.apply(this);
      if (this.executor == null) {
         throw new IllegalArgumentException("Unsupported executor type");
      } else {
         return this;
      }
   }

   @Nullable
   public CookieManager getCookieManager() {
      return this.cookieManager;
   }

   public HttpClient setCookieManager(@Nullable CookieManager cookieManager) {
      this.cookieManager = cookieManager;
      return this;
   }

   public boolean isFollowRedirects() {
      return this.followRedirects;
   }

   public HttpClient setFollowRedirects(boolean followRedirects) {
      this.followRedirects = followRedirects;
      return this;
   }

   public int getConnectTimeout() {
      return this.connectTimeout;
   }

   public HttpClient setConnectTimeout(int connectTimeout) {
      this.connectTimeout = connectTimeout;
      return this;
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   public HttpClient setReadTimeout(int readTimeout) {
      this.readTimeout = readTimeout;
      return this;
   }

   @Nonnull
   public RetryHandler getRetryHandler() {
      return this.retryHandler;
   }

   public HttpClient setRetryHandler(@Nonnull RetryHandler retryHandler) {
      this.retryHandler = retryHandler;
      return this;
   }

   @Nonnull
   public ProxyHandler getProxyHandler() {
      return this.proxyHandler;
   }

   public HttpClient setProxyHandler(@Nonnull ProxyHandler proxyHandler) {
      this.proxyHandler = proxyHandler;
      return this;
   }

   public boolean isIgnoreInvalidSSL() {
      return this.ignoreInvalidSSL;
   }

   public HttpClient setIgnoreInvalidSSL(boolean ignoreInvalidSSL) {
      this.ignoreInvalidSSL = ignoreInvalidSSL;
      return this;
   }

   public <R> R execute(HttpRequest request, HttpResponseHandler<R> responseHandler) throws IOException {
      return responseHandler.handle(this.execute(request));
   }

   public HttpResponse execute(HttpRequest request) throws IOException {
      RetryHandler retryHandler = request.isRetryHandlerSet() ? request.getRetryHandler() : this.retryHandler;

      for (int connects = 0; connects <= retryHandler.getMaxConnectRetries(); connects++) {
         try {
            HttpResponse response = null;

            for (int headers = 0; headers <= retryHandler.getMaxHeaderRetries(); headers++) {
               response = this.executor.execute(request);
               Optional<String> retryAfter = response.getFirstHeader("Retry-After");
               if (!retryAfter.isPresent()) {
                  return response;
               }

               if (headers >= retryHandler.getMaxHeaderRetries()) {
                  break;
               }

               Long delay = HttpRequestUtils.parseSecondsOrHttpDate(retryAfter.get());
               if (delay == null) {
                  return response;
               }

               if (delay > 0L) {
                  Thread.sleep(delay);
               }
            }

            if (response == null) {
               throw new IllegalStateException("Response not received but no exception was thrown");
            }

            if (retryHandler.getMaxHeaderRetries() == 0) {
               return response;
            }

            throw new RetryExceededException(response);
         } catch (InterruptedException e) {
            throw new IOException(e);
         } catch (SSLException | ProtocolException | UnknownHostException e2) {
            throw e2;
         } catch (IOException e3) {
            if (connects >= retryHandler.getMaxConnectRetries()) {
               throw e3;
            }
         }
      }

      throw new IllegalStateException("Connect retry failed but no exception was thrown");
   }

   @Override
   public <T extends HttpRequest> T bind(T request) {
      request.bind(this);
      return request;
   }
}
