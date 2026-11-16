package client.util;

import client.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class OnlineConfigsManager implements MinecraftAccessor {
   public static final String text = ConfigManager.labelText + Client.getClient().getString4().toLowerCase(Locale.ROOT);

   public static boolean isActive() {
      File file = new File(text + File.separator + "onlineConfigs");
      ConfigManager.applyString2(file.getAbsolutePath());
      File[] fileArray = file.listFiles();
      if (fileArray != null) {
         int length = fileArray.length;

         for (int index = 0; index < length; index++) {
            fileArray[index].delete();
         }
      }

      ArrayList list = new ArrayList();

      try {
         HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://raw.githubusercontent.com/AugustusClient/Online-Configs-4/main/Configs.txt")
            .openConnection();
         httpURLConnection.setRequestMethod("GET");
         httpURLConnection.setConnectTimeout(20000);
         httpURLConnection.connect();
         BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

         String text2;
         while ((text2 = reader.readLine()) != null) {
            list.add(text2);
         }

         reader.close();
         httpURLConnection.disconnect();

         for (String text3 : (Iterable<String>)(Iterable)(list)) {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection)new URL(
                  "https://raw.githubusercontent.com/AugustusClient/Online-Configs-4/main/Configs/" + text3 + ".json"
               )
               .openConnection();
            httpURLConnection2.setRequestMethod("GET");
            httpURLConnection2.setConnectTimeout(20000);
            httpURLConnection2.connect();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String text4;
            while ((text4 = reader2.readLine()) != null) {
               builder.append(text4);
            }

            reader2.close();
            httpURLConnection2.disconnect();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(text + File.separator + "onlineConfigs", text3 + ".json")));
            writer.write(builder.toString());
            writer.flush();
            writer.close();
         }

         return true;
      } catch (IOException ioException) {
         System.err.println("Failed to download new online configs");
         return false;
      }
   }
}
