package client.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomValueHandler extends Thread {
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger = LogManager.getLogger();
   public final String labelText;
   public final DatagramSocket datagramSocket;
   public boolean enabled = true;
   public final String labelText2;

   @Override
   public void interrupt() {
      super.interrupt();
      this.enabled = false;
   }

   public CustomValueHandler(String text, String text2) throws IOException {
      super("LanServerPinger #" + atomicInteger.incrementAndGet());
      this.labelText = text;
      this.labelText2 = text2;
      this.setDaemon(true);
      this.datagramSocket = new DatagramSocket();
   }

   public static String resolveText(String text) {
      int count = text.indexOf("[/MOTD]");
      if (count < 0) {
         return null;
      } else if (text.indexOf("[/MOTD]", count + "[/MOTD]".length()) >= 0) {
         return null;
      } else {
         int count2 = text.indexOf("[AD]", count + "[/MOTD]".length());
         if (count2 < 0) {
            return null;
         } else {
            int count3 = text.indexOf("[/AD]", count2 + "[AD]".length());
            return count3 < count2 ? null : text.substring(count2 + "[AD]".length(), count3);
         }
      }
   }

   @Override
   public void run() {
      byte[] bytes = resolveText(this.labelText, this.labelText2).getBytes();

      while (!this.isInterrupted() && this.enabled) {
         try {
            InetAddress byName = InetAddress.getByName("224.0.2.60");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, byName, 4445);
            this.datagramSocket.send(packet);
         } catch (IOException ioException) {
            logger.warn("LanServerPinger: " + ioException.getMessage());
            break;
         }

         try {
            sleep(1500L);
         } catch (InterruptedException interruptedException) {
         }
      }
   }

   public static String resolveText(String text, String text2) {
      return "[MOTD]" + text + "[/MOTD][AD]" + text2 + "[/AD]";
   }

   public static String resolveTextByText(String text) {
      int count = text.indexOf("[MOTD]");
      if (count < 0) {
         return "missing no";
      } else {
         int count2 = text.indexOf("[/MOTD]", count + "[MOTD]".length());
         return count2 < count ? "missing no" : text.substring(count + "[MOTD]".length(), count2);
      }
   }
}
