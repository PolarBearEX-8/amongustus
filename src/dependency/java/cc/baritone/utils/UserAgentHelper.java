package cc.baritone.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import net.minecraft.client.Minecraft;

public class UserAgentHelper {
   public static final String labelText;
   public static final JsonParser jsonParser = new JsonParser();
   public static final String labelText2 = "https://api.github.com/repos/Pokechu22/WorldDownloader/releases?per_page=100";
   public static final File file = new File(Minecraft.getMinecraft().mcDataDir, "WorldDownloader_Update_Cache.json");

   public static JsonElement resolveJsonElement(String text) throws Exception {
      InputStream entry = null;

      JsonElement jsonElement;
      try {
         HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL(text).openConnection();
         httpsURLConnection.setRequestProperty("User-Agent", labelText);
         httpsURLConnection.setRequestProperty("Accept", "application/vnd.github.v3.full+json");
         if (Pokechu22WorldDownloaderManager.properties3.getProperty("UpdateETag") != null) {
            String property = Pokechu22WorldDownloaderManager.properties3.getProperty("UpdateETag");
            if (!property.isEmpty()) {
               httpsURLConnection.setRequestProperty("If-None-Match", property);
            }
         }

         httpsURLConnection.connect();
         if (httpsURLConnection.getResponseCode() == 304) {
            LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.usingCachedUpdates", new Object[0]);
            entry = new FileInputStream(file);
         } else {
            if (httpsURLConnection.getResponseCode() != 200) {
               throw new Exception("Unexpected response while getting " + text + ": " + httpsURLConnection.getResponseCode() + " " + httpsURLConnection.getResponseMessage());
            }

            LoggerEntry.applyAltListener(OptionType.UPDATE_DEBUG, "wdl.messages.updates.grabingUpdatesFromGithub", new Object[0]);
            entry = httpsURLConnection.getInputStream();
         }

         InputStreamReader inputStreamReader = null;
         boolean flag = false ;

         try {
            flag = true;
            inputStreamReader = new InputStreamReader((InputStream)entry);
            jsonElement = jsonParser.parse(inputStreamReader);
            PrintStream printStream = null;
            boolean flag2 = false ;

            String text2;
            try {
               flag2 = true;
               printStream = new PrintStream(file);
               printStream.println(jsonElement.toString());
               text2 = httpsURLConnection.getHeaderField("ETag");
               flag2 = false;
            } catch (Exception exception) {
               throw exception;
            } finally {
               if (flag2) {
                  if (printStream != null) {
                     printStream.close();
                  }

                  Pokechu22WorldDownloaderManager.properties3.remove("UpdateETag");
                  Pokechu22WorldDownloaderManager.updateState8();
               }
            }

            printStream.close();
            if (text2 != null) {
               Pokechu22WorldDownloaderManager.properties3.setProperty("UpdateETag", text2);
            } else {
               Pokechu22WorldDownloaderManager.properties3.remove("UpdateETag");
            }

            Pokechu22WorldDownloaderManager.updateState8();
            flag = false;
         } finally {
            if (flag) {
               if (inputStreamReader != null) {
                  inputStreamReader.close();
               }
            }
         }

         inputStreamReader.close();
      } finally {
         if (entry != null) {
            entry.close();
         }
      }

      return jsonElement;
   }

   static {
      String text = Pokechu22WorldDownloaderManager.getText3();
      String text2 = "1.8.9a-beta2";
      labelText = String.format("WorldDownloader mod by Pokechu22 (Minecraft %s; WDL %s) ", text, text2);
   }

   public static List getList() throws Exception {
      JsonArray asJsonArray = resolveJsonElement("https://api.github.com/repos/Pokechu22/WorldDownloader/releases?per_page=100").getAsJsonArray();
      java.util.ArrayList list = new java.util.ArrayList();

      for (JsonElement jsonElement : asJsonArray) {
         list.add(new ReleaseURLController(jsonElement.getAsJsonObject()));
      }

      return list;
   }
}
