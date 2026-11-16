package client.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApplicationContentHelper {
   public static final String labelText = "application/json";
   public static final Gson gson = new Gson();

   public static JsonObject resolveJsonObject(HttpURLConnection httpURLConnection) {
      return (JsonObject)gson.fromJson(resolveText(httpURLConnection), JsonObject.class);
   }

   public static JsonObject resolveJsonObject(String text, Map map) {
      try {
         HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text).openConnection();
         if (map != null) {
            for (String text2 : (Iterable<String>)(Object)(map.keySet())) {
               httpURLConnection.addRequestProperty(text2, (String)map.get(text2));
            }
         }

         InputStream inputStream = httpURLConnection.getResponseCode() != 200 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
         return (JsonObject)gson.fromJson(resolveText(reader), JsonObject.class);
      } catch (IOException ioException) {
         ioException.printStackTrace();
         return null;
      }
   }

   private static String resolveText(BufferedReader reader) {
      try {
         StringBuilder builder = new StringBuilder();

         String text;
         while ((text = reader.readLine()) != null) {
            builder.append(text);
         }

         return builder.toString();
      } catch (IOException ioException) {
         ioException.printStackTrace();
         return null;
      }
   }

   public static JsonObject resolveJsonObject(String text, Object entry) {
      HttpURLConnection httpURLConnection = resolveHttpURLConnection(text);
      httpURLConnection.setDoOutput(true);
      httpURLConnection.addRequestProperty("Content-Type", "application/json");
      httpURLConnection.addRequestProperty("Accept", "application/json");

      try {
         httpURLConnection.setRequestMethod("POST");
         httpURLConnection.getOutputStream().write(gson.toJson(entry).getBytes(StandardCharsets.UTF_8));
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }

      return resolveJsonObject(httpURLConnection);
   }

   public static HttpURLConnection resolveHttpURLConnection(String text) {
      HttpURLConnection httpURLConnection = null;

      try {
         httpURLConnection = (HttpURLConnection)new URL(text).openConnection();
         httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
         httpURLConnection.setRequestProperty("Accept-Language", "en-US");
         httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      return httpURLConnection;
   }

   public static String resolveText(HttpURLConnection httpURLConnection) {
      String headerField = httpURLConnection.getHeaderField("Location");
      if (headerField != null) {
         return resolveText(resolveHttpURLConnection(headerField));
      } else {
         StringBuilder builder = new StringBuilder();

         try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream()));
            Throwable throwable = null;

            while (true) {
               boolean flag = false ;

               try {
                  flag = true;
                  String text;
                  if ((text = reader.readLine()) == null) {
                     flag = false;
                     break;
                  }

                  builder.append(text).append('\n');
               } catch (Throwable throwable2) {
                  throwable = throwable2;
                  throw throwable2;
               } finally {
                  if (flag) {
                     if (throwable != null) {
                        try {
                           reader.close();
                        } catch (Throwable throwable3) {
                           throwable.addSuppressed(throwable3);
                        }
                     } else {
                        reader.close();
                     }
                  }
               }
            }

            reader.close();
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }

         return builder.toString();
      }
   }
}
