package net.minecraft.client.network;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanServerDetector {
   private static final AtomicInteger ATOMIC_COUNTER = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();

   public static AtomicInteger accessATOMIC_COUNTER() {
      return ATOMIC_COUNTER;
   }

   public static Logger accessLogger() {
      return logger;
   }


   public static class LanServer {
      private String lanServerMotd;
      private String lanServerIpPort;
      private long timeLastSeen;

      public void updateLastSeen() {
         this.timeLastSeen = Minecraft.getSystemTime();
      }

      public LanServer(String str, String str2) {
         this.lanServerMotd = str;
         this.lanServerIpPort = str2;
         this.timeLastSeen = Minecraft.getSystemTime();
      }

      public String getServerIpPort() {
         return this.lanServerIpPort;
      }

      public String getServerMotd() {
         return this.lanServerMotd;
      }
   }

   public static class LanServerList {
      private List listOfLanServers = Lists.newArrayList();
      public boolean wasUpdated;

      public synchronized void setWasNotUpdated() {
         this.wasUpdated = false;
      }

      public synchronized List getLanServers() {
         return Collections.unmodifiableList(this.listOfLanServers);
      }

      public synchronized void addServer(String str, InetAddress inetAddress) {
         String motdFromPingResponse = ThreadLanServerPing.getMotdFromPingResponse(str);
         String adFromPingResponse = ThreadLanServerPing.getAdFromPingResponse(str);
         if (adFromPingResponse != null) {
            adFromPingResponse = inetAddress.getHostAddress() + ":" + adFromPingResponse;
            boolean flag = false;

            for (LanServerDetector.LanServer lanServer : (Iterable<LanServerDetector.LanServer>)(this.listOfLanServers)) {
               if (lanServer.getServerIpPort().equals(adFromPingResponse)) {
                  lanServer.updateLastSeen();
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               this.listOfLanServers.add(new LanServerDetector.LanServer(motdFromPingResponse, adFromPingResponse));
               this.wasUpdated = true;
            }
         }
      }

      public synchronized boolean getWasUpdated() {
         return this.wasUpdated;
      }
   }

   public static class ThreadLanServerFind extends Thread {
      private final LanServerDetector.LanServerList localServerList;
      private final InetAddress broadcastAddress;
      private final MulticastSocket socket;

      @Override
      public void run() {
         byte[] bytes = new byte[1024];

         while (!this.isInterrupted()) {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

            try {
               this.socket.receive(packet);
            } catch (SocketTimeoutException e) {
               continue;
            } catch (IOException e2) {
               LanServerDetector.accessLogger().error("Couldn't ping server", e2);
               break;
            }

            String str = new String(packet.getData(), packet.getOffset(), packet.getLength());
            LanServerDetector.accessLogger().debug(packet.getAddress() + ": " + str);
            this.localServerList.addServer(str, packet.getAddress());
         }

         try {
            this.socket.leaveGroup(this.broadcastAddress);
         } catch (IOException e3) {
         }

         this.socket.close();
      }

      public ThreadLanServerFind(LanServerDetector.LanServerList lanServerList) throws IOException {
         super("LanServerDetector #" + LanServerDetector.accessATOMIC_COUNTER().incrementAndGet());
         this.localServerList = lanServerList;
         this.setDaemon(true);
         this.socket = new MulticastSocket(4445);
         this.broadcastAddress = InetAddress.getByName("224.0.2.60");
         this.socket.setSoTimeout(5000);
         this.socket.joinGroup(this.broadcastAddress);
      }
   }
}
