package net.lenni0451.commons.httpclient.executor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.content.StreamedHttpContent;
import net.lenni0451.commons.httpclient.proxy.SingleProxySelector;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;
import net.lenni0451.commons.httpclient.requests.HttpRequest;
import net.lenni0451.commons.httpclient.utils.HttpRequestUtils;
import net.lenni0451.commons.httpclient.utils.IgnoringTrustManager;

public class URLConnectionExecutor extends RequestExecutor {
   public URLConnectionExecutor(HttpClient client) {
      super(client);
   }

   @Nonnull
   @Override
   public HttpResponse execute(@Nonnull HttpRequest request) throws IOException {
      CookieManager cookieManager = this.getCookieManager(request);
      SingleProxySelector proxySelector = null;
      if (this.client.getProxyHandler().isProxySet()) {
         proxySelector = this.client.getProxyHandler().getProxySelector();
      }

      HttpResponse httpResponse;
      try {
         if (proxySelector != null) {
            proxySelector.set();
         }

         httpResponse = this.executeRequest(this.openConnection(request, cookieManager), cookieManager, request);
      } finally {
         if (proxySelector != null) {
            proxySelector.reset();
         }
      }

      return httpResponse;
   }

   private HttpURLConnection openConnection(HttpRequest request, CookieManager cookieManager) throws IOException {
      HttpURLConnection connection = (HttpURLConnection)request.getURL().openConnection();
      if (this.isIgnoreInvalidSSL(request) && connection instanceof HttpsURLConnection) {
         ((HttpsURLConnection)connection).setSSLSocketFactory(IgnoringTrustManager.makeIgnoringSSLContext().getSocketFactory());
      }

      this.setupConnection(connection, cookieManager, request);
      connection.connect();
      return connection;
   }

   private void setupConnection(HttpURLConnection connection, @Nullable CookieManager cookieManager, HttpRequest request) throws IOException {
      HttpRequestUtils.setHeaders(connection, this.getHeaders(request, cookieManager));
      HttpContentRequest contentRequest = request instanceof HttpContentRequest ? (HttpContentRequest)request : null;
      HttpContent content = contentRequest != null ? contentRequest.getContent() : null;
      connection.setConnectTimeout(this.client.getConnectTimeout());
      connection.setReadTimeout(this.client.getReadTimeout());
      connection.setRequestMethod(request.getMethod());
      connection.setDoInput(true);
      if (contentRequest != null && content != null) {
         connection.setDoOutput(true);
         if (content instanceof StreamedHttpContent) {
            connection.setFixedLengthStreamingMode(content.getContentLength());
         }
      } else {
         connection.setDoOutput(false);
      }

      switch (request.getFollowRedirects()) {
         case NOT_SET:
            connection.setInstanceFollowRedirects(this.client.isFollowRedirects());
            break;
         case FOLLOW:
            connection.setInstanceFollowRedirects(true);
            break;
         case IGNORE:
            connection.setInstanceFollowRedirects(false);
      }
   }

   private HttpResponse executeRequest(HttpURLConnection connection, @Nullable CookieManager cookieManager, HttpRequest request) throws IOException {
      boolean closeConnection = true;

      HttpResponse httpResponse;
      try {
         if (connection.getDoOutput()) {
            HttpContent content = ((HttpContentRequest)request).getContent();
            OutputStream os = connection.getOutputStream();
            if (content instanceof StreamedHttpContent) {
               StreamedHttpContent streamedContent = (StreamedHttpContent)content;
               InputStream is = streamedContent.getInputStream();
               byte[] buffer = new byte[streamedContent.getBufferSize()];

               int read;
               while ((read = is.read(buffer)) != -1) {
                  os.write(buffer, 0, read);
               }

               is.close();
            } else {
               os.write(content.getAsBytes());
            }

            os.flush();
         }

         Map<String, List<String>> headers = new HashMap<>(connection.getHeaderFields());
         headers.remove(null);
         HttpResponse response;
         if (request.isStreamedResponse()) {
            InputStream body = HttpRequestUtils.getInputStream(connection);
            response = new HttpResponse(request.getURL(), connection.getResponseCode(), body, headers);
            closeConnection = false;
         } else {
            byte[] body = HttpRequestUtils.readBody(connection);
            response = new HttpResponse(request.getURL(), connection.getResponseCode(), body, headers);
         }

         HttpRequestUtils.updateCookies(cookieManager, request.getURL(), connection.getHeaderFields());
         httpResponse = response;
      } finally {
         if (closeConnection) {
            connection.disconnect();
         }
      }

      return httpResponse;
   }
}
