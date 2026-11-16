package client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class PlayerModelsLocalManager {
   public static String labelText3 = null;
   public static final String labelText = "http://s.optifine.net";
   public static final String labelText2 = "http://optifine.net";

   public static synchronized String getText() {
      if (labelText3 == null) {
         try {
            if (BuildManager.matchesText(System.getProperty("player.models.local"), false)) {
               File mcDataDir = Minecraft.getMinecraft().mcDataDir;
               labelText3 = new File(mcDataDir, "playermodels").toURI().toURL().toExternalForm();
            }
         } catch (Exception exception) {
            BuildManager.applyText3("" + exception.getClass().getName() + ": " + exception.getMessage());
         }

         if (labelText3 == null) {
            labelText3 = "http://s.optifine.net";
         }
      }

      return labelText3;
   }

   public static String resolveText(String text, Map map, byte[] byteValueArray) throws IOException {
      HttpURLConnection httpURLConnection = null;

      String text2;
      try {
         httpURLConnection = (HttpURLConnection)new URL(text).openConnection(Minecraft.getMinecraft().getProxy());
         httpURLConnection.setRequestMethod("POST");
         if (map != null) {
            for (Object element : map.keySet()) {
               String text3 = "" + map.get(element);
               httpURLConnection.setRequestProperty((String)element, text3);
            }
         }

         httpURLConnection.setRequestProperty("Content-Type", "text/plain");
         httpURLConnection.setRequestProperty("Content-Length", "" + byteValueArray.length);
         httpURLConnection.setRequestProperty("Content-Language", "en-US");
         httpURLConnection.setUseCaches(false);
         httpURLConnection.setDoInput(true);
         httpURLConnection.setDoOutput(true);
         OutputStream outputStream = httpURLConnection.getOutputStream();
         outputStream.write(byteValueArray);
         outputStream.flush();
         outputStream.close();
         InputStream inputStream = httpURLConnection.getInputStream();
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ASCII");
         BufferedReader reader = new BufferedReader(inputStreamReader);
         StringBuffer builder = new StringBuffer();

         String text4;
         while ((text4 = reader.readLine()) != null) {
            builder.append(text4);
            builder.append('\r');
         }

         reader.close();
         text2 = builder.toString();
      } finally {
         if (httpURLConnection != null) {
            httpURLConnection.disconnect();
         }
      }

      return text2;
   }

   public static byte[] resolveByteArray(String text) throws IOException {
      HttpURLConnection httpURLConnection = null;

      byte[] byteValueArray;
      try {
         httpURLConnection = (HttpURLConnection)new URL(text).openConnection(Minecraft.getMinecraft().getProxy());
         httpURLConnection.setDoInput(true);
         httpURLConnection.setDoOutput(false);
         httpURLConnection.connect();
         if (httpURLConnection.getResponseCode() / 100 != 2) {
            if (httpURLConnection.getErrorStream() != null) {
               BuildManager.resolveByteArray(httpURLConnection.getErrorStream());
            }

            throw new IOException("HTTP response: " + httpURLConnection.getResponseCode());
         }

         InputStream inputStream = httpURLConnection.getInputStream();
         byteValueArray = new byte[httpURLConnection.getContentLength()];
         int count = 0;

         do {
            int count2 = inputStream.read(byteValueArray, count, byteValueArray.length - count);
            if (count2 < 0) {
               throw new IOException("Input stream closed: " + text);
            }

            count += count2;
         } while (count < byteValueArray.length);
      } finally {
         if (httpURLConnection != null) {
            httpURLConnection.disconnect();
         }
      }

      return byteValueArray;
   }
}
