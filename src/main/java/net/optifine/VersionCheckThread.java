package net.optifine;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.minecraft.client.ClientBrandRetriever;
import net.optifine.config.Config;

public class VersionCheckThread extends Thread {

   @Override
   public void run() {
      HttpURLConnection conn = null;

      try {
         Config.dbg("Checking for new version");
         conn = (HttpURLConnection)new URL("http://optifine.net/version/1.8.9/HD_U.txt").openConnection();
         if (Config.getGameSettings().snooperEnabled) {
            conn.setRequestProperty("OF-MC-Version", "1.8.9");
            conn.setRequestProperty("OF-MC-Brand", "" + ClientBrandRetriever.getClientModName());
            conn.setRequestProperty("OF-Edition", "HD_U");
            conn.setRequestProperty("OF-Release", "M5");
            conn.setRequestProperty("OF-Java-Version", "" + System.getProperty("java.version"));
            conn.setRequestProperty("OF-CpuCount", "" + Config.getAvailableProcessors());
            conn.setRequestProperty("OF-OpenGL-Version", "" + Config.openGlVersion);
            conn.setRequestProperty("OF-OpenGL-Vendor", "" + Config.openGlVendor);
         }

         conn.setDoInput(true);
         conn.setDoOutput(false);
         conn.connect();

         try {
            InputStream inputStream = conn.getInputStream();
            String str = Config.readInputStream(inputStream);
            inputStream.close();
            String[] strings = Config.tokenize(str, "\n\r");
            if (strings.length < 1) {
               return;
            }

            String str2 = strings[0].trim();
            Config.dbg("Version found: " + str2);
            if (Config.compareRelease(str2, "M5") > 0) {
               Config.setNewRelease(str2);
               return;
            }
         } finally {
            if (conn != null) {
               conn.disconnect();
            }
         }
      } catch (Exception e) {
         Config.dbg(e.getClass().getName() + ": " + e.getMessage());
      }
   }

   public VersionCheckThread() {
      super("VersionCheck");
   }
}
