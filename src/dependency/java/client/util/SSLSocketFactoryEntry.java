package client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.commons.io.IOUtils;

public class SSLSocketFactoryEntry {
   public static SSLSocketFactory sSLSocketFactory;

   public static boolean matchesText(String text) {
      try {
         Class.forName(DampenerProvider.a(text));
         return true;
      } catch (ClassNotFoundException classNotFoundException) {
         return false;
      }
   }

   public static int computeCount(int count, int count2, int count3, int count4) {
      return (count4 & 0xFF) << 24 | (count & 0xFF) << 16 | (count2 & 0xFF) << 8 | (count3 & 0xFF) << 0;
   }

   public static boolean matchesText(String text, String[] textArray) {
      String text2 = text.toLowerCase();

      for (String text3 : textArray) {
         if (text2.endsWith(text3)) {
            return true;
         }
      }

      return false;
   }

   public static int computeCount(float posX, float posX2, float posY, float posZ) {
      return ((int)((double)(posZ * 255.0F) + 0.5) & 0xFF) << 24
         | ((int)((double)(posX * 255.0F) + 0.5) & 0xFF) << 16
         | ((int)((double)(posX2 * 255.0F) + 0.5) & 0xFF) << 8
         | ((int)((double)(posY * 255.0F) + 0.5) & 0xFF) << 0;
   }

   public static InputStream resolveInputStream(String text, int count, Object[] entryArray) throws IOException {
      String[] textArray = null;
      return resolveInputStream(text, count, textArray, entryArray);
   }

   public static String resolveText(String text) {
      if (text != null && text.contains("&")) {
         char[] characterArray = text.toCharArray();

         for (int index = 0; index < characterArray.length - 1; index++) {
            if (characterArray[index] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(characterArray[index + 1]) != -1) {
               characterArray[index] = 167;
            }
         }

         return new String(characterArray);
      } else {
         return text;
      }
   }

   public static SSLSocketFactory getSSLSocketFactory() {
      if (sSLSocketFactory != null) {
         return sSLSocketFactory;
      } else {
         try {
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init((KeyManager[])null, new TrustManager[]{new FovRanker()}, new SecureRandom());
            return sSLSocketFactory = instance.getSocketFactory();
         } catch (KeyManagementException | NoSuchAlgorithmException keyManagementException) {
            MoreCosmeticsManager.applyThrowable(keyManagementException);
            return null;
         }
      }
   }

   public static String resolveText(Throwable throwable) {
      try {
         StringWriter stringWriter = new StringWriter();
         Throwable throwable2 = null;
         boolean flag = false ;

         String text;
         try {
            flag = true;
            PrintWriter printWriter = new PrintWriter(stringWriter);
            Throwable throwable3 = null;
            boolean flag2 = false ;

            try {
               flag2 = true;
               throwable.printStackTrace(printWriter);
               text = stringWriter.toString();
               flag2 = false;
            } catch (Throwable throwable4) {
               throwable3 = throwable4;
               throw throwable4;
            } finally {
               if (flag2) {
                  if (throwable3 != null) {
                     try {
                        printWriter.close();
                     } catch (Throwable throwable5) {
                        throwable3.addSuppressed(throwable5);
                     }
                  } else {
                     printWriter.close();
                  }
               }
            }

            printWriter.close();
            flag = false;
         } catch (Throwable throwable6) {
            throwable2 = throwable6;
            throw throwable6;
         } finally {
            if (flag) {
               if (throwable2 != null) {
                  try {
                     stringWriter.close();
                  } catch (Throwable throwable7) {
                     throwable2.addSuppressed(throwable7);
                  }
               } else {
                  stringWriter.close();
               }
            }
         }

         stringWriter.close();
         return text;
      } catch (IOException ioException) {
         throw new IllegalStateException(ioException);
      }
   }

   public static InputStream resolveInputStream(String text, int count, String[] textArray, Object[] entryArray) throws IOException {
      String text2 = entryArray == null ? text : String.format(text, entryArray);
      HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text2).openConnection();
      if (text2.toLowerCase().startsWith("https") && httpURLConnection instanceof HttpsURLConnection) {
         ((HttpsURLConnection)httpURLConnection).setSSLSocketFactory(getSSLSocketFactory());
      }

      httpURLConnection.setRequestProperty("User-Agent", "MoreCosmetics-1.2");
      httpURLConnection.setConnectTimeout(count);
      httpURLConnection.setReadTimeout(count);
      httpURLConnection.connect();
      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode / 100 == 2) {
         if (textArray != null) {
            textArray[0] = httpURLConnection.getContentType();
         }

         return httpURLConnection.getInputStream();
      } else {
         InputStream errorStream = httpURLConnection.getErrorStream();
         String text3 = errorStream != null ? " " + IOUtils.toString(errorStream) : "";
         throw new IOException("Response code: " + responseCode + text3);
      }
   }
}
