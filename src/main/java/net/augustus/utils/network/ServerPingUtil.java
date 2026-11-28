package net.augustus.utils.network;

import net.augustus.Augustus;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;

public class ServerPingUtil implements MC {
   private static long lastCheckTime;
   private static int cachedPing = 0;

   public static int getPing() {
      if (System.currentTimeMillis() - lastCheckTime > 15000L) {
         CompletableFuture.runAsync(
            () -> {
               lastCheckTime = System.currentTimeMillis();

               try {
                  int n = 0;
                  if (Augustus.getInstance().getProxy() != null) {
                     Socket socket = new Socket();
                     long currentTimeMs = System.currentTimeMillis();
                     socket.connect(Augustus.getInstance().getProxy().getSocketAddress(), 4000);
                     n = (int)(System.currentTimeMillis() - currentTimeMs);
                  }

                  String str;
                  if (mc.getCurrentServerData() == null) {
                     str = "www.youtube.com";
                  } else if (mc.getCurrentServerData().serverIP.contains("gommehd.net")) {
                     short s = 19048;
                     str = "gomme.sexy";
                  } else {
                     str = mc.getCurrentServerData().serverIP;
                  }

                  InetAddress byName = InetAddress.getByName(str);
                  long l2 = System.currentTimeMillis();
                  if (byName.isReachable(4000)) {
                     setCachedPing((int)(System.currentTimeMillis() - l2) + n);
                  }
               } catch (Exception e) {
                  setCachedPing(
                     mc.getNetHandler() != null && mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null
                        ? mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()
                        : 0
                  );
               }
            }
         );
      }

      return cachedPing;
   }

   private static synchronized void setCachedPing(int n) {
      cachedPing = n;
   }

}
