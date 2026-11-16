package com.viaversion.viaversion.libs.gson;

import client.util.Controller10;
import client.util.Controller2;
import client.util.Controller3;
import client.util.ControllerEntry;
import client.util.OnlineConfigsManager;
import client.util.TimerTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;

public class ClientRedirectController {
   public static String labelText = "Keine Informationen verfügbar";
   public static String labelText2;
   public static File file = new File(OnlineConfigsManager.text + File.separator + "spotify");
   public static String labelText3;
   public static String labelText4;
   public static ExecutorService executorService = Executors.newSingleThreadExecutor();
   public static String labelText5;
   public static Timer timer = new Timer();
   public String labelText6;
   public static ResourceLocation resourceLocation = null;
   public static String labelText7 = null;

   public static String getText() throws Exception {
      Map map = getMap();
      String orDefault = (String) map.getOrDefault("client_id", "default_client_id");
      String orDefault2 = (String) map.getOrDefault("redirect_uri", "http://localhost:8080/auth");
      String text = "https://accounts.spotify.com/authorize";
      String text2 = "user-read-playback-state user-read-currently-playing";
      String text3 = "code";
      String text4 = text
         + "?response_type="
         + text3
         + "&client_id="
         + URLEncoder.encode(orDefault, StandardCharsets.UTF_8.toString())
         + "&scope="
         + URLEncoder.encode(text2, StandardCharsets.UTF_8.toString())
         + "&redirect_uri="
         + URLEncoder.encode(orDefault2, StandardCharsets.UTF_8.toString());
      System.out.println("Spotify Auth URL: " + text4);
      applyText2(text4);
      return text4;
   }

   public static void updateState3() throws Exception {
      String text = "https://api.spotify.com/v1/me/player/pause";
      String text2 = labelText4;
      applyText(text, text2);
   }

   private void currentTimeMillis(int count) {
      System.currentTimeMillis();
   }

   public String resolveTextByText(String text) throws Exception {
      String text2 = "https://accounts.spotify.com/api/token";
      URL url = new URL(text2);
      HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      String text3 = labelText3 + ":" + labelText7;
      String text4 = Base64.getEncoder().encodeToString(text3.getBytes());
      httpURLConnection.setRequestProperty("Authorization", "Basic " + text4);
      String text5 = "grant_type=authorization_code&code=" + text + "&redirect_uri=" + URLEncoder.encode(this.labelText6, "UTF-8");
      httpURLConnection.setDoOutput(true);
      boolean flag = false ;

      try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
         flag = true;
         byte[] bytes = text5.getBytes("utf-8");
         outputStream.write(bytes, 0, bytes.length);
         flag = false;
      }


      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != 200) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
         StringBuilder builder = new StringBuilder();

         String text6;
         while ((text6 = reader.readLine()) != null) {
            builder.append(text6);
         }

         reader.close();
         throw new RuntimeException("HTTP POST Request Failed with Error code : " + responseCode + "\nError Response: " + builder.toString());
      } else {
         BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
         StringBuilder builder2 = new StringBuilder();

         String text7;
         while ((text7 = reader2.readLine()) != null) {
            builder2.append(text7);
         }

         String text8 = builder2.toString();
         this.applyTextByText(text8);
         reader2.close();
         return resolveText2(builder2.toString());
      }
   }

   public static void schedule() {
      timer.schedule(new TimerTask(), 0L, 2000L);
   }

   public static void updateState6() throws Exception {
      String text = labelText4;
      applyTextByText("https://api.spotify.com/v1/me/player/previous", text);
   }

   public static void updateState5() throws Exception {
      String text = labelText4;
      applyTextByText("https://api.spotify.com/v1/me/player/next", text);
   }

   public static String resolveText(String text) throws Exception {
      String text2 = "https://api.spotify.com/v1/me/player/currently-playing";
      HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text2).openConnection();
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + text);
      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != 200) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
         StringBuilder builder = new StringBuilder();

         String text3;
         while ((text3 = reader.readLine()) != null) {
            builder.append(text3);
         }

         reader.close();
         throw new RuntimeException("HTTP GET Request Failed with Error code : " + responseCode + "\nError Response: " + builder.toString());
      } else {
         BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
         StringBuilder builder2 = new StringBuilder();

         String text4;
         while ((text4 = reader2.readLine()) != null) {
            builder2.append(text4);
         }

         reader2.close();
         Controller10 controller = new com.google.gson.GsonBuilder().create().fromJson(builder2.toString(), ControllerEntry.class).getController10();
         String labelText8 = controller.getLabelText();
         String labelText9 = ((Controller2)controller.getList().get(0)).getLabelText();
         String text5 = null;

         for (Controller3 controller2 : (Iterable<Controller3>)(Iterable)(controller.getController14().getList())) {
            if (controller2.getCount() == 300) {
               text5 = controller2.getLabelText();
               break;
            }
         }

         labelText5 = text5;
         labelText = "Track: " + labelText8 + " | Artist: " + labelText9;
         return labelText;
      }
   }

   public void applyTextByText(String text) throws Exception {
      JsonObject asJsonObject = JsonParser.parseString(text).getAsJsonObject();
      String asString = asJsonObject.get("access_token").getAsString();
      labelText4 = asString;
      if (asJsonObject.has("refresh_token")) {
         labelText2 = asJsonObject.get("refresh_token").getAsString();
      }

      int asInt = asJsonObject.get("expires_in").getAsInt();
      this.currentTimeMillis(asInt);
   }

   public static void updateState4() {
      String clipboard = Sys.getClipboard();
      if (!clipboard.isEmpty()) {
         System.out.println("Auth-Code aus der Zwischenablage: " + clipboard);
         applyText(clipboard);
      } else {
         System.out.println("Kein Auth-Code in der Zwischenablage gefunden.");
      }
   }

   private static void applyTextByText(String text, String text2) throws Exception {
      HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text).openConnection();
      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + text2);
      httpURLConnection.setRequestProperty("Content-Length", "0");
      httpURLConnection.setDoOutput(true);
      httpURLConnection.getOutputStream().write(new byte[0]);
      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != 204) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
         StringBuilder builder = new StringBuilder();

         String text3;
         while ((text3 = reader.readLine()) != null) {
            builder.append(text3);
         }

         reader.close();
         throw new RuntimeException("HTTP POST Request Failed with Error code : " + responseCode + "\nError Response: " + builder.toString());
      }
   }

   public static void updateState2() {
      timer.cancel();
      executorService.shutdown();
   }

   public static void applyText2(String text) {
      try {
         String text2 = System.getProperty("os.name").toLowerCase();
         if (text2.contains("win")) {
            Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", text});
         } else if (text2.contains("mac")) {
            Runtime.getRuntime().exec(new String[]{"open", text});
         } else {
            if (!text2.contains("nix") && !text2.contains("nux")) {
               throw new UnsupportedOperationException("Unbekanntes Betriebssystem: " + text2);
            }

            Runtime.getRuntime().exec(new String[]{"xdg-open", text});
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static void applyText(String text) {
      ClientRedirectController clientRedirectController = new ClientRedirectController();

      try {
         labelText4 = clientRedirectController.resolveTextByText(text);
         System.out.println("Access Token: " + labelText4);
         labelText = resolveText(labelText4);
         System.out.println("Current Track: " + labelText);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static void updateState() throws Exception {
      if (labelText2 != null && !labelText2.isEmpty()) {
         resourceLocation = null;
         String text = "https://accounts.spotify.com/api/token";
         HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text).openConnection();
         httpURLConnection.setRequestMethod("POST");
         httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         String text2 = labelText3 + ":" + labelText7;
         String text3 = Base64.getEncoder().encodeToString(text2.getBytes());
         httpURLConnection.setRequestProperty("Authorization", "Basic " + text3);
         String text4 = "grant_type=refresh_token&refresh_token=" + URLEncoder.encode(labelText2, "UTF-8");
         httpURLConnection.setDoOutput(true);
         boolean flag = false ;

         try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            flag = true;
            byte[] bytes = text4.getBytes("utf-8");
            outputStream.write(bytes, 0, bytes.length);
            flag = false;
         }


         int responseCode = httpURLConnection.getResponseCode();
         if (responseCode != 200) {
            throw new RuntimeException("HTTP POST Request Failed with Error code : " + responseCode);
         } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String text5;
            while ((text5 = reader.readLine()) != null) {
               builder.append(text5);
            }

            reader.close();
            resolveText2(builder.toString());
         }
      } else {
         throw new RuntimeException("Der Refresh Token ist null oder leer. Überprüfe, ob er korrekt gespeichert wurde.");
      }
   }

   public static String getLabelText4() {
      return labelText4;
   }

   public static Map getMap() {
      HashMap map = new HashMap();
      if (!file.exists()) {
         file.mkdirs();
      }

      File file2 = new File(file, "_auth.txt");
      if (!file2.exists()) {
         updateState7();
      }

      try {
         BufferedReader reader = new BufferedReader(new FileReader(file2));
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

               String[] textArray = text.split("=");
               if (textArray.length == 2) {
                  map.put(textArray[0].trim(), textArray[1].trim());
               }
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
         System.err.println("Fehler beim Lesen der Konfiguration: " + ioException.getMessage());
         ioException.printStackTrace();
      }

      return map;
   }

   public ClientRedirectController() {
      Map map = getMap();
      labelText3 = (String) map.getOrDefault("client_id", "default_client_id");
      labelText7 = (String) map.getOrDefault("client_secret", "default_client_secret");
      this.labelText6 = (String) map.getOrDefault("redirect_uri", "https://default.redirect.uri");
   }

   public static void updateState7() {
      HashMap map = new HashMap();
      map.put("client_id", "example_client_id");
      map.put("client_secret", "example_client_secret");
      map.put("redirect_uri", "https://example.com/redirect");
      File file2 = new File(file, "_auth.txt");
      if (file2.exists()) {
         System.out.println("Config file already exists. No changes made.");
      } else {
         file.mkdirs();

         try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
            Throwable throwable = null;
            boolean flag = false ;

            try {
               flag = true;

               for (Entry entry : (Iterable<Entry>)(Iterable)(map.entrySet())) {
                  writer.write((String)entry.getKey() + "=" + (String)entry.getValue());
                  writer.newLine();
               }

               System.out.println("Config file created successfully.");
               flag = false;
            } catch (Throwable throwable2) {
               throwable = throwable2;
               throw throwable2;
            } finally {
               if (flag) {
                  if (throwable != null) {
                     try {
                        writer.close();
                     } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                     }
                  } else {
                     writer.close();
                  }
               }
            }

            writer.close();
         } catch (IOException ioException) {
            System.err.println("Error writing the config file: " + ioException.getMessage());
            ioException.printStackTrace();
         }
      }
   }

   private static void applyText(String text, String text2) throws Exception {
      URL url = new URL(text);
      HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
      httpURLConnection.setRequestMethod("PUT");
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + text2);
      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != 204) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
         StringBuilder builder = new StringBuilder();

         String text3;
         while ((text3 = reader.readLine()) != null) {
            builder.append(text3);
         }

         reader.close();
         throw new RuntimeException("HTTP PUT Request Failed with Error code : " + responseCode + "\nError Response: " + builder.toString());
      }
   }

   public static ExecutorService getExecutorService() {
      return executorService;
   }

   private static String resolveText2(String text) {
      int count = text.indexOf("\"access_token\":\"");
      if (count == -1) {
         throw new RuntimeException("Access token not found in response");
      } else {
         int count2 = count + 16;
         int count3 = text.indexOf("\"", count2);
         return text.substring(count2, count3);
      }
   }
}
