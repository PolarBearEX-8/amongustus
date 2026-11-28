package net.optifine.http;

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
import net.optifine.config.Config;

public class HttpUtils {
   private static String playerItemsUrl = null;
   public static final String SERVER_URL = null;
   public static final String POST_URL = null;

   public static String post(String str, Map map, byte[] bytes) throws IOException {
      HttpURLConnection conn = null;

      String str2;
      try {
         conn = (HttpURLConnection)new URL(str).openConnection(Minecraft.getMinecraft().getProxy());
         conn.setRequestMethod("POST");
         if (map != null) {
            for (Object obj : map.keySet()) {
               String str3 = "" + map.get(obj);
               conn.setRequestProperty((String)obj, str3);
            }
         }

         conn.setRequestProperty("Content-Type", "text/plain");
         conn.setRequestProperty("Content-Length", "" + bytes.length);
         conn.setRequestProperty("Content-Language", "en-US");
         conn.setUseCaches(false);
         conn.setDoInput(true);
         conn.setDoOutput(true);
         OutputStream outputStream = conn.getOutputStream();
         outputStream.write(bytes);
         outputStream.flush();
         outputStream.close();
         InputStream inputStream = conn.getInputStream();
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ASCII");
         BufferedReader reader = new BufferedReader(inputStreamReader);
         StringBuffer sb = new StringBuffer();

         String str4;
         while ((str4 = reader.readLine()) != null) {
            sb.append(str4);
            sb.append('\r');
         }

         reader.close();
         str2 = sb.toString();
      } finally {
         if (conn != null) {
            conn.disconnect();
         }
      }

      return str2;
   }

   public static byte[] get(String str) throws IOException {
      HttpURLConnection conn = null;

      byte[] bytes;
      try {
         conn = (HttpURLConnection)new URL(str).openConnection(Minecraft.getMinecraft().getProxy());
         conn.setDoInput(true);
         conn.setDoOutput(false);
         conn.connect();
         if (conn.getResponseCode() / 100 != 2) {
            if (conn.getErrorStream() != null) {
               Config.readAll(conn.getErrorStream());
            }

            throw new IOException("HTTP response: " + conn.getResponseCode());
         }

         InputStream inputStream = conn.getInputStream();
         byte[] bytes2 = new byte[conn.getContentLength()];
         int n = 0;

         do {
            int n2 = inputStream.read(bytes2, n, bytes2.length - n);
            if (n2 < 0) {
               throw new IOException("Input stream closed: " + str);
            }

            n += n2;
         } while (n < bytes2.length);

         bytes = bytes2;
      } finally {
         if (conn != null) {
            conn.disconnect();
         }
      }

      return bytes;
   }

   public static synchronized String getPlayerItemsUrl() {
      if (playerItemsUrl == null) {
         try {
            if (Config.parseBoolean2(System.getProperty("player.models.local"), false)) {
               File mcDataDir = Minecraft.getMinecraft().mcDataDir;
               playerItemsUrl = new File(mcDataDir, "playermodels").toURI().toURL().toExternalForm();
            }
         } catch (Exception e) {
            Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
         }

         if (playerItemsUrl == null) {
            playerItemsUrl = "http://s.optifine.net";
         }
      }

      return playerItemsUrl;
   }
}
