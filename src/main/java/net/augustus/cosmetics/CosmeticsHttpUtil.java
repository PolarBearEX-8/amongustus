package net.augustus.cosmetics;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.io.IOUtils;

public class CosmeticsHttpUtil {
   public static InputStream openUrlStream(String str, int n, Object[] array) throws IOException {
      return openUrlStream(str, n, (String[])null, array);
   }

   public static String translateColorCodes(String str) {
      if (str != null && str.contains("&")) {
         char[] chars = str.toCharArray();

         for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[i + 1]) != -1) {
               chars[i] = 167;
            }
         }

         return new String(chars);
      } else {
         return str;
      }
   }

   public static int packRgba(int n, int n2, int n3, int n4) {
      return (n4 & 0xFF) << 24 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | (n3 & 0xFF) << 0;
   }

   public static boolean endsWithAnyIgnoreCase(String str, String[] strings) {
      String str2 = str.toLowerCase();

      for (String str3 : strings) {
         if (str2.endsWith(str3)) {
            return true;
         }
      }

      return false;
   }

   public static boolean isClassPresent(String str) {
      try {
         Class.forName(str);
         return true;
      } catch (ClassNotFoundException e) {
         return false;
      }
   }

   public static SSLSocketFactory getTrustAllSocketFactory() {
      // Never bypass the platform's TLS certificate validation.
      return null;
   }

   public static InputStream openUrlStream(String str, int n, String[] strings, Object[] array) throws IOException {
      str = array == null ? str : String.format(str, array);
      URL url = new URL(str);
      if (!"https".equalsIgnoreCase(url.getProtocol())) {
         throw new IOException("Cosmetic downloads require HTTPS: " + str);
      }
      if (!TrustedHostWhitelist.isTrustedHost(str)) {
         throw new IOException("Cosmetic download host is not trusted: " + str);
      }

      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestProperty("User-Agent", "MoreCosmetics-1.2");
      conn.setConnectTimeout(n);
      conn.setReadTimeout(n);
      conn.connect();
      int responseCode = conn.getResponseCode();
      if (responseCode / 100 == 2) {
         if (strings != null) {
            strings[0] = conn.getContentType();
         }

         return conn.getInputStream();
      }

      InputStream errorStream = conn.getErrorStream();
      String error = errorStream != null ? " " + IOUtils.toString(errorStream) : "";
      throw new IOException("Response code: " + responseCode + error);
   }

   public static String getStackTraceString(Throwable t) {

      try (
         StringWriter stringWriter = new StringWriter();
         PrintWriter writer = new PrintWriter(stringWriter);
      ) {
         t.printStackTrace(writer);
         return stringWriter.toString();
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   public static int packRgbaFloat(float f, float f2, float f3, float f4) {
      return ((int)((double)(f4 * 255.0F) + 0.5) & 0xFF) << 24
         | ((int)((double)(f * 255.0F) + 0.5) & 0xFF) << 16
         | ((int)((double)(f2 * 255.0F) + 0.5) & 0xFF) << 8
         | ((int)((double)(f3 * 255.0F) + 0.5) & 0xFF) << 0;
   }


}
