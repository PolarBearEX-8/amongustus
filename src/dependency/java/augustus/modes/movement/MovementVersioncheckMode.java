package augustus.modes.movement;

import client.util.BuildManager;
import client.util.VanillaManager;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovementVersioncheckMode extends Thread {
   public MovementVersioncheckMode() {
      super("VersionCheck");
   }

   @Override
   public void run() {
      try {
         BuildManager.applyText4("Checking for new version");
         HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http://optifine.net/version/1.8.9/HD_U.txt").openConnection();
         if (BuildManager.getGameSettings().state115) {
            httpURLConnection.setRequestProperty("OF-MC-Version", "1.8.9");
            httpURLConnection.setRequestProperty("OF-MC-Brand", "" + VanillaManager.getText());
            httpURLConnection.setRequestProperty("OF-Edition", "HD_U");
            httpURLConnection.setRequestProperty("OF-Release", "M5");
            httpURLConnection.setRequestProperty("OF-Java-Version", "" + System.getProperty("java.version"));
            httpURLConnection.setRequestProperty("OF-CpuCount", "" + BuildManager.getCount5());
            httpURLConnection.setRequestProperty("OF-OpenGL-Version", "" + BuildManager.labelText5);
            httpURLConnection.setRequestProperty("OF-OpenGL-Vendor", "" + BuildManager.labelText6);
         }

         httpURLConnection.setDoInput(true);
         httpURLConnection.setDoOutput(false);
         httpURLConnection.connect();

         try {
            InputStream inputStream = httpURLConnection.getInputStream();
            String text = BuildManager.resolveText(inputStream);
            inputStream.close();
            String[] textArray = BuildManager.resolveTextArray(text, "\n\r");
            if (textArray.length < 1) {
               return;
            }

            String text2 = textArray[0].trim();
            BuildManager.applyText4("Version found: " + text2);
            if (BuildManager.computeCount(text2, "M5") > 0) {
               BuildManager.setLabelText11(text2);
               return;
            }
         } finally {
            if (httpURLConnection != null) {
               httpURLConnection.disconnect();
            }
         }
      } catch (Exception exception) {
         BuildManager.applyText4(exception.getClass().getName() + ": " + exception.getMessage());
      }
   }
}
