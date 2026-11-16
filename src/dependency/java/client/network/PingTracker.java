package client.network;

import client.Client;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class PingTracker implements MinecraftAccessor {
   public static int count = 0;
   public static long timestamp;

   public static int getEstimatedPing() {
      if (System.currentTimeMillis() - timestamp > 15000L) {
         CompletableFuture.runAsync(PingTracker::updateState);
      }

      return count;
   }

   private static synchronized void setCount(int count2) {
      count = count2;
   }

   private static void updateState() {
      timestamp = System.currentTimeMillis();

      try {
         int count2 = 0;
         if (Client.getClient().getClientNetworkConfig() != null) {
            Socket socket = new Socket();
            long timestamp2 = System.currentTimeMillis();
            socket.connect(Client.getClient().getClientNetworkConfig().getInetSocketAddress(), 4000);
            count2 = (int)(System.currentTimeMillis() - timestamp2);
         }

         InetAddress byName = InetAddress.getByName(
            MinecraftAccessor.minecraft.getServerData() == null
               ? "www.youtube.com"
               : (MinecraftAccessor.minecraft.getServerData().text.contains("gommehd.net") ? "gomme.sexy" : MinecraftAccessor.minecraft.getServerData().text)
         );
         long timestamp3 = System.currentTimeMillis();
         if (byName.isReachable(4000)) {
            setCount((int)(System.currentTimeMillis() - timestamp3) + count2);
         }
      } catch (Exception exception) {
         setCount(
            MinecraftAccessor.minecraft.getNetHandlerPlayClient() != null
                  && MinecraftAccessor.minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(MinecraftAccessor.minecraft.thePlayer.getEntityUniqueID())
                     != null
               ? MinecraftAccessor.minecraft
                  .getNetHandlerPlayClient()
                  .getNetworkPlayerInfo(MinecraftAccessor.minecraft.thePlayer.getEntityUniqueID())
                  .getIndexOffset3()
               : 0
         );
      }
   }
}
