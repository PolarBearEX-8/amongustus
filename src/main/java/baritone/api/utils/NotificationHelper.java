package baritone.api.utils;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import org.apache.commons.lang3.SystemUtils;

public class NotificationHelper {
   private static TrayIcon trayIcon;

   private static void mac(String str) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("osascript", "-e", "display notification \"" + str + "\" with title \"Baritone\"");

      try {
         processBuilder.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void windows(String str, boolean flag) {
      if (SystemTray.isSupported()) {
         try {
            if (trayIcon == null) {
               SystemTray systemTray = SystemTray.getSystemTray();
               Image image = Toolkit.getDefaultToolkit().createImage("");
               trayIcon = new TrayIcon(image, "Baritone");
               trayIcon.setImageAutoSize(true);
               trayIcon.setToolTip("Baritone");
               systemTray.add(trayIcon);
            }

            trayIcon.displayMessage("Baritone", str, flag ? MessageType.ERROR : MessageType.INFO);
         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         System.out.println("SystemTray is not supported");
      }
   }

   private static void linux(String str) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("notify-send", "-a", "Baritone", str);

      try {
         processBuilder.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void notify(String str, boolean flag) {
      if (SystemUtils.IS_OS_WINDOWS) {
         windows(str, flag);
      } else if (SystemUtils.IS_OS_MAC_OSX) {
         mac(str);
      } else if (SystemUtils.IS_OS_LINUX) {
         linux(str);
      }
   }
}
