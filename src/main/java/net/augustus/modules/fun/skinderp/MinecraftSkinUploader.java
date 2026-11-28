package net.augustus.modules.fun.skinderp;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;

public class MinecraftSkinUploader implements MC {
   private static final int MAX_UPLOADS_PER_MINUTE = 20;
   private static long rateLimitWindowStartMs = System.currentTimeMillis();
   private static final String API_ENDPOINT_OVERRIDE = null;
   private static int uploadsInCurrentWindow = 0;

   private static String readErrorResponse(HttpURLConnection conn) {

      try (InputStream errorStream = conn.getErrorStream()) {
         if (errorStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            String str;
            while ((str = reader.readLine()) != null) {
               sb.append(str);
            }

            return sb.toString();
         }
      } catch (IOException e) {
      }

      return "Unknown error";
   }

   private static void recordSuccessfulUpload() {
      uploadsInCurrentWindow++;
   }

   public static int getRemainingUploads() {
      return System.currentTimeMillis() - rateLimitWindowStartMs >= 60000L ? 20 : 20 - uploadsInCurrentWindow;
   }

   private static boolean checkRateLimit() {
      long currentTimeMs = System.currentTimeMillis();
      if (currentTimeMs - rateLimitWindowStartMs >= 60000L) {
         uploadsInCurrentWindow = 0;
         rateLimitWindowStartMs = currentTimeMs;
      }

      return uploadsInCurrentWindow < 20;
   }

   public static boolean uploadSkin(File file, String str) {
      if (!checkRateLimit()) {
         System.err.println("[SkinUpload] Rate limit exceeded! Maximum 20 uploads per minute.");
         return false;
      } else {
         try {
            String token = mc.getSession().getToken();
            if (token != null && !token.isEmpty()) {
               if (file.exists() && file.getName().toLowerCase().endsWith(".png")) {
                  BufferedImage image = ImageIO.read(file);
                  if (image != null && image.getWidth() == 64 && (image.getHeight() == 64 || image.getHeight() == 32)) {
                     String str2;
                     if (str != null) {
if (str.equals("slim")) {
str2 = "slim";
} else {
str2 = "classic";
}
} else {
str2 = "classic";
}

                     String str3 = str2;
                     String str4 = "----WebKitFormBoundary" + System.currentTimeMillis();
                     HttpURLConnection conn = (HttpURLConnection)new URL("https://api.minecraftservices.com/minecraft/profile/skins").openConnection();
                     conn.setRequestMethod("POST");
                     conn.setDoOutput(true);
                     conn.setRequestProperty("Authorization", "Bearer " + token);
                     conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str4);
                     conn.setConnectTimeout(10000);
                     conn.setReadTimeout(10000);

                     try (
                        OutputStream outputStream = conn.getOutputStream();
                        DataOutputStream out = new DataOutputStream(outputStream);
                     ) {
                        out.writeBytes("--" + str4 + "\r\n");
                        out.writeBytes("Content-Disposition: form-data; name=\"variant\"\r\n\r\n");
                        out.writeBytes(str3 + "\r\n");
                        out.writeBytes("--" + str4 + "\r\n");
                        out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
                        out.writeBytes("Content-Type: image/png\r\n\r\n");

                        try (FileInputStream fileInputStream = new FileInputStream(file)) {
                           byte[] bytes = new byte[4096];

                           int n;
                           while ((n = fileInputStream.read(bytes)) != -1) {
                              out.write(bytes, 0, n);
                           }
                        }

                        out.writeBytes("\r\n");
                        out.writeBytes("--" + str4 + "--\r\n");
                        out.flush();
                     }

                     int responseCode = conn.getResponseCode();
                     if (responseCode == 200) {
                        System.out.println("[SkinUpload] Skin uploaded successfully!");
                        recordSuccessfulUpload();
                        return true;
                     } else if (responseCode == 429) {
                        System.err.println("[SkinUpload] Rate limit exceeded by server!");
                        return false;
                     } else if (responseCode == 401) {
                        System.err.println("[SkinUpload] Authentication failed! Invalid access token.");
                        return false;
                     } else {
                        String str5 = readErrorResponse(conn);
                        System.err.println("[SkinUpload] Upload failed with code " + responseCode + ": " + str5);
                        return false;
                     }
                  } else {
                     System.err.println("[SkinUpload] Invalid skin dimensions. Must be 64x64 or 64x32.");
                     return false;
                  }
               } else {
                  System.err.println("[SkinUpload] Invalid skin file: " + file.getPath());
                  return false;
               }
            } else {
               System.err.println("[SkinUpload] No access token available!");
               return false;
            }
         } catch (IOException e) {
            System.err.println("[SkinUpload] Error uploading skin: " + e.getMessage());
            e.printStackTrace();
            return false;
         }
      }
   }

   public static long getRateLimitResetMs() {
      long l2 = System.currentTimeMillis() - rateLimitWindowStartMs;
      return l2 >= 60000L ? 0L : 60000L - l2;
   }

}
