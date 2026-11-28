package net.minecraft.client.multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadLanServerPing extends Thread {
   private static final AtomicInteger UNIQUE_THREAD_ID = new AtomicInteger(0);
   private static final Logger LOGGER = LogManager.getLogger();
   private final String motd;
   private final DatagramSocket socket;
   private boolean isStopping = true;
   private final String address;

   public static String getPingResponse(String str, String str2) {
      return "[MOTD]" + str + "[/MOTD][AD]" + str2 + "[/AD]";
   }

   @Override
   public void run() {
      byte[] bytes = getPingResponse(this.motd, this.address).getBytes();

      while (!this.isInterrupted() && this.isStopping) {
         try {
            InetAddress byName = InetAddress.getByName("224.0.2.60");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, byName, 4445);
            this.socket.send(packet);
         } catch (IOException e) {
            LOGGER.warn("LanServerPinger: " + e.getMessage());
            break;
         }

         try {
            sleep(1500L);
         } catch (InterruptedException e2) {
         }
      }
   }

   @Override
   public void interrupt() {
      super.interrupt();
      this.isStopping = false;
   }

   public ThreadLanServerPing(String str, String str2) throws IOException {
      super("LanServerPinger #" + UNIQUE_THREAD_ID.incrementAndGet());
      this.motd = str;
      this.address = str2;
      this.setDaemon(true);
      this.socket = new DatagramSocket();
   }

   public static String getMotdFromPingResponse(String str) {
      int n = str.indexOf("[MOTD]");
      if (n < 0) {
         return "missing no";
      } else {
         int n2 = str.indexOf("[/MOTD]", n + "[MOTD]".length());
         return n2 < n ? "missing no" : str.substring(n + "[MOTD]".length(), n2);
      }
   }

   public static String getAdFromPingResponse(String str) {
      int n = str.indexOf("[/MOTD]");
      if (n < 0) {
         return null;
      } else {
         if (str.indexOf("[/MOTD]", n + "[/MOTD]".length()) >= 0) {
            return null;
         } else {
            int n3 = str.indexOf("[AD]", n + "[/MOTD]".length());
            if (n3 < 0) {
               return null;
            } else {
               int n4 = str.indexOf("[/AD]", n3 + "[AD]".length());
               return n4 < n3 ? null : str.substring(n3 + "[AD]".length(), n4);
            }
         }
      }
   }
}
