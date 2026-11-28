package net.augustus.utils;

import net.augustus.Augustus;
import net.augustus.ui.UiIconResources;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;

public class SystemTrayNotifier {
   private static TrayIcon trayIcon;

   public static void displayNotification(String str, String str2) {
      trayIcon.displayMessage(str, str2, MessageType.INFO);
   }

   public static void init() {

      try {
         trayIcon = new TrayIcon(
            ImageIO.read(
               (InputStream)Objects.requireNonNull(
                  UiIconResources.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logoWindows.png")
               )
            ),
            "Augustus Message"
         );
      } catch (IOException e) {
      }

      try {
         SystemTray systemTray = SystemTray.getSystemTray();
         trayIcon.setImageAutoSize(true);
         trayIcon.setToolTip("Message from " + Augustus.getInstance().getName() + " hack client");
         if (SystemTray.isSupported()) {
            systemTray.add(trayIcon);
         }
      } catch (AWTException e2) {
      }
   }
}
