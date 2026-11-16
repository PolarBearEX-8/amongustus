package client.util;

import client.Client;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class SystemTrayNotifier {
   public static TrayIcon trayIcon;

   public static void updateState() {
      try {
         trayIcon = new TrayIcon(
            ImageIO.read(
               Objects.requireNonNull(PicturesConfigPngManager.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logoWindows.png"))
            ),
            "Augustus Message"
         );
      } catch (IOException ioException) {
      }

      try {
         SystemTray systemTray = SystemTray.getSystemTray();
         trayIcon.setImageAutoSize(true);
         trayIcon.setToolTip("Message from " + Client.getClient().getString4() + " hack client");
         if (SystemTray.isSupported()) {
            systemTray.add(trayIcon);
         }
      } catch (AWTException aWTException) {
      }
   }

   public static void applyString(String text, String text2) {
      trayIcon.displayMessage(text, text2, MessageType.INFO);
   }
}
