package cc.baritone.utils;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import org.apache.commons.lang3.SystemUtils;
import recovery.security.SafeAppleScript;

public class OsascriptManager {
   public static TrayIcon trayIcon;

   public static void applyText(String text, boolean flag) {
      if (SystemUtils.IS_OS_WINDOWS) {
         applyTextByText(text, flag);
      } else if (SystemUtils.IS_OS_MAC_OSX) {
         applyText(text);
      } else if (SystemUtils.IS_OS_LINUX) {
         applyTextByText(text);
      }
   }

   private static void applyText(String text) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("osascript", "-e", "display notification \"" + SafeAppleScript.escape(text) + "\" with title \"Baritone\"");

      try {
         processBuilder.start();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   private static void applyTextByText(String text, boolean flag) {
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

            trayIcon.displayMessage("Baritone", text, flag ? MessageType.ERROR : MessageType.INFO);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      } else {
         System.out.println("SystemTray is not supported");
      }
   }

   private static void applyTextByText(String text) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("notify-send", "-a", "Baritone", text);

      try {
         processBuilder.start();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }
}
