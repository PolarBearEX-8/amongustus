package net.augustus.ui.proxy;

import net.augustus.Augustus;
import net.augustus.ui.account.AccountAuthenticator;
import net.augustus.utils.TuplePair;
import net.augustus.utils.network.ProxyEntry;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;

public class ProxyChecker {
   private static final String[] MINECRAFT_TEST_SERVERS = new String[]{"gommehd.net", "gomme.sexy", "hypixel.net", "mineblaze.ru", "top.pika.host", "blocksmc.com"};

   public static TuplePair checkProxyWithMinecraftServers(ProxyEntry proxyEntry) {
      TuplePair tuplePair = pingProxySocket(proxyEntry);
      if ((Integer)tuplePair.getSecond() >= 5000) {
         return tuplePair;
      } else {
         CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

         for (String str : MINECRAFT_TEST_SERVERS) {
            copyOnWriteArrayList.add(CompletableFuture.supplyAsync(() -> {
               double currentTimeMs = (double)System.currentTimeMillis();
               boolean[] flags = new boolean[]{false};
               ServerAddress serverAddress = ServerAddress.fromString(str);
               NetworkManager networkManager = null;

               try {
                  networkManager = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(serverAddress.getIP()), serverAddress.getPort(), false);
                  networkManager.setNetHandler(new ProxyStatusClientHandler(flags, networkManager));

                  try {
                     networkManager.sendPacket(new C00Handshake(47, serverAddress.getIP(), serverAddress.getPort(), EnumConnectionState.STATUS));
                     networkManager.sendPacket(new C00PacketServerQuery());
                  } catch (Exception e) {
                     return false;
                  }
               } catch (Exception e2) {
                  return false;
               }

               if (networkManager == null) {
                  return false;
               } else {
                  while ((double)System.currentTimeMillis() - currentTimeMs < 5000.0) {
                     try {
                        Thread.sleep(50L);
                     } catch (InterruptedException e3) {
                     }

                     if (flags[0]) {
                        return true;
                     }
                  }

                  return false;
               }
            }));
         }

         while (!copyOnWriteArrayList.isEmpty()) {
            for (CompletableFuture completableFuture : (Iterable<CompletableFuture>)(copyOnWriteArrayList)) {
               if (completableFuture.isDone()) {
                  try {
                     if ((Boolean)completableFuture.get()) {
                        return tuplePair;
                     }
                  } catch (ExecutionException | InterruptedException e4) {
                     e4.printStackTrace();
                  }

                  copyOnWriteArrayList.remove(completableFuture);
               }
            }
         }

         return new TuplePair("Failed to reach proxy", 5000);
      }
   }

   public static TuplePair pingProxySocket(ProxyEntry proxyEntry) {
      Socket socket = new Socket();

      TuplePair tuplePair;
      try {
         long currentTimeMs = System.currentTimeMillis();
         socket.connect(proxyEntry.getSocketAddress(), 5000);
         int n = (int)(System.currentTimeMillis() - currentTimeMs);
         proxyEntry.setPing(n);
         Augustus.getInstance().setProxy(proxyEntry);
         ProxyManagerScreen.setPendingProxy(proxyEntry);
         return new TuplePair("Reached proxy in " + n + "ms §8[§7" + proxyEntry.getHost() + ":" + proxyEntry.getPort() + "§8]", n);
      } catch (Exception e) {
         if (!(e instanceof SocketTimeoutException)) {
            if (e instanceof IOException) {
               return new TuplePair("Failed to reach proxy", 5000);
            }

            if (e instanceof IllegalBlockingModeException) {
               return new TuplePair("Illegal blocking method", 5000);
            }

            if (!(e instanceof IllegalArgumentException)) {
               return new TuplePair("Unknown error", 5000);
            }

            return new TuplePair("Invalid IP or port", 5000);
         }

         tuplePair = new TuplePair("Timeout reached, unreachable", 5000);
      } finally {
         try {
            socket.close();
         } catch (IOException e2) {
            e2.printStackTrace();
         }
      }

      return tuplePair;
   }

   public static TuplePair testAndApplyProxy(ProxyEntry proxyEntry, ProxyEntry proxyEntry2, boolean flag) {
      TuplePair tuplePair = flag ? checkProxyWithMinecraftServers(proxyEntry) : pingProxySocket(proxyEntry);
      if ((Integer)tuplePair.getSecond() >= 5000) {
         Augustus.getInstance().setProxy(proxyEntry2);
         PacketThreadUtil.runOnMainThread(() -> AccountAuthenticator.playFailureSound());
         return tuplePair;
      } else {
         PacketThreadUtil.runOnMainThread(() -> AccountAuthenticator.playSuccessSound());
         ProxyEntry proxyEntry3 = new ProxyEntry(proxyEntry);

         for (ProxyEntry proxyEntry4 : (Iterable<ProxyEntry>)(ProxyManagerScreen.proxies)) {
            if (proxyEntry4.matches(proxyEntry3)) {
               proxyEntry4.setPing(proxyEntry3.getPing());
               break;
            }
         }

         if (!ProxyManagerScreen.proxies.contains(proxyEntry3)) {
            ProxyManagerScreen.proxies.add(proxyEntry3);
            ProxyManagerScreen.proxyListEntries.put(proxyEntry3, new ProxyListEntry(proxyEntry3));
         }

         return tuplePair;
      }
   }

}
