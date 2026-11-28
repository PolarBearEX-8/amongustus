package net.lenni0451.commons.httpclient.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import javax.annotation.WillNotClose;
import lombok.Generated;

public final class HttpRequestUtils {
   @SafeVarargs
   public static Map<String, List<String>> mergeHeaders(Map<String, List<String>>... maps) {
      Map<String, List<String>> headers = new HashMap<>();

      for (Map<String, List<String>> map : maps) {
         for (Entry<String, List<String>> entry : map.entrySet()) {
            if (!entry.getValue().isEmpty()) {
               headers.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
            }
         }
      }

      return headers;
   }

   public static Map<String, List<String>> getCookieHeaders(@Nullable CookieManager cookieManager, URL url) throws IOException {
      try {
         return cookieManager == null ? Collections.emptyMap() : cookieManager.get(url.toURI(), Collections.emptyMap());
      } catch (URISyntaxException e) {
         throw new IOException("Failed to parse URL as URI", e);
      }
   }

   public static void updateCookies(@Nullable CookieManager cookieManager, URL url, Map<String, List<String>> headers) throws IOException {
      if (cookieManager != null) {
         try {
            cookieManager.put(url.toURI(), headers);
         } catch (URISyntaxException e) {
            throw new IOException("Failed to parse URL as URI", e);
         }
      }
   }

   public static void setHeaders(HttpURLConnection connection, Map<String, List<String>> headers) {
      setHeaders(headers, connection::setRequestProperty, connection::addRequestProperty);
   }

   public static void setHeaders(Map<String, List<String>> headers, BiConsumer<String, String> setConsumer, BiConsumer<String, String> addConsumer) {
      for (Entry<String, List<String>> entry : headers.entrySet()) {
         if ("Cookie".equalsIgnoreCase(entry.getKey())) {
            setConsumer.accept(entry.getKey(), String.join("; ", entry.getValue()));
         } else {
            boolean first = true;

            for (String val : entry.getValue()) {
               if (first) {
                  first = false;
                  setConsumer.accept(entry.getKey(), val);
               } else {
                  addConsumer.accept(entry.getKey(), val);
               }
            }
         }
      }
   }

   public static byte[] readBody(HttpURLConnection connection) throws IOException {
      return readFromStream(getInputStream(connection));
   }

   public static InputStream getInputStream(HttpURLConnection connection) throws IOException {
      InputStream is;
      if (connection.getResponseCode() >= 400) {
         is = connection.getErrorStream();
      } else {
         is = connection.getInputStream();
      }

      if (is == null) {
         is = new ByteArrayInputStream(new byte[0]);
      }

      return is;
   }

   public static byte[] readFromStream(@WillNotClose InputStream is) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];

      int len;
      while ((len = is.read(buf)) != -1) {
         baos.write(buf, 0, len);
      }

      return baos.toByteArray();
   }

   public static Instant parseHttpDate(String httpDate) throws DateTimeParseException {
      return Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(httpDate));
   }

   @Nullable
   public static Long parseSecondsOrHttpDate(String value) {
      try {
         return parseHttpDate(value).toEpochMilli() - Instant.now().toEpochMilli();
      } catch (DateTimeParseException e) {
         try {
            return (long)Integer.parseInt(value) * 1000L;
         } catch (NumberFormatException e2) {
            return null;
         }
      }
   }

   @Generated
   private HttpRequestUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
