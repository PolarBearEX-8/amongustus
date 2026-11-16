package client.util;

import client.Client;
import client.config.ClientNetworkConfig;
import client.setting.CombatKeybindProperty;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import net.minecraft.network.NetworkManager;

public class ReachProxyHelper {
   public static final String[] stringArray = new String[]{"gommehd.net", "gomme.sexy", "hypixel.net", "mineblaze.ru", "top.pika.host", "blocksmc.com"};

   private static Boolean resolveBoolean(String text) {
      double timestamp = (double)System.currentTimeMillis();
      boolean[] flagArray = new boolean[]{false};
      JavaNamingFactoryInitialManager javaNamingFactoryInitialManager = JavaNamingFactoryInitialManager.resolveJavaNamingFactoryInitialMana(text);

      NetworkManager networkManager;
      try {
         networkManager = NetworkManager.resolveNetworkManager(InetAddress.getByName(javaNamingFactoryInitialManager.toASCII()), javaNamingFactoryInitialManager.getCount(), false);
         networkManager.applyINetHandler(new FinishedSmoothCalculator(flagArray, networkManager));

         try {
            networkManager.handlePacket6(new BasePacketAdapter(47, javaNamingFactoryInitialManager.toASCII(), javaNamingFactoryInitialManager.getCount(), CombatKeybindProperty.STATUS));
            networkManager.handlePacket6(new CommonPacketAdapter());
         } catch (Exception exception) {
            return false;
         }
      } catch (Exception exception2) {
         return false;
      }

      if (networkManager == null) {
         return false;
      } else {
         while ((double)System.currentTimeMillis() - timestamp < 5000.0) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException interruptedException) {
            }

            if (flagArray[0]) {
               return true;
            }
         }

         return false;
      }
   }

   public static Pair resolvePairByClientNetworkCon(ClientNetworkConfig clientNetworkConfig) {
      Pair pair = resolvePair(clientNetworkConfig);
      if ((Integer)pair.getFirst() >= 5000) {
         return pair;
      } else {
         CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

         for (String text : stringArray) {
            copyOnWriteArrayList.add(CompletableFuture.supplyAsync(() -> ReachProxyHelper.resolveBoolean(text)));
         }

         while (!copyOnWriteArrayList.isEmpty()) {
            for (CompletableFuture completableFuture : (Iterable<CompletableFuture>)(Iterable)copyOnWriteArrayList) {
               if (completableFuture.isDone()) {
                  try {
                     if ((Boolean)completableFuture.get()) {
                        return pair;
                     }
                  } catch (ExecutionException | InterruptedException executionException) {
                     executionException.printStackTrace();
                  }

                  copyOnWriteArrayList.remove(completableFuture);
               }
            }
         }

         return new Pair("Failed to reach proxy", 5000);
      }
   }

   public static Pair resolvePair(ClientNetworkConfig clientNetworkConfig, ClientNetworkConfig clientNetworkConfig2, boolean flag) {
      Pair pair = flag ? resolvePairByClientNetworkCon(clientNetworkConfig) : resolvePair(clientNetworkConfig);
      if ((Integer)pair.getFirst() >= 5000) {
         Client.getClient().applyClientNetworkConfig(clientNetworkConfig2);
         ClientManager.applyRunnable(MsnManager::updateState4);
         return pair;
      } else {
         ClientManager.applyRunnable(MsnManager::updateState3);
         ClientNetworkConfig clientNetworkConfig3 = new ClientNetworkConfig(clientNetworkConfig);

         for (ClientNetworkConfig clientNetworkConfig4 : (Iterable<ClientNetworkConfig>)(Iterable)ColorEntry.copyOnWriteArrayList) {
            if (clientNetworkConfig4.matchesClientNetworkConfig(clientNetworkConfig3)) {
               int count = clientNetworkConfig3.getCount();
               clientNetworkConfig4.applyIndex(count);
               break;
            }
         }

         if (!ColorEntry.copyOnWriteArrayList.contains(clientNetworkConfig3)) {
            ColorEntry.copyOnWriteArrayList.add(clientNetworkConfig3);
            ColorEntry.concurrentHashMap.put(clientNetworkConfig3, new InterpolationEngine(clientNetworkConfig3));
         }

         return pair;
      }
   }

   public static Pair resolvePair(ClientNetworkConfig clientNetworkConfig) {
      Socket socket = new Socket();

      Pair pair;
      try {
         long timestamp = System.currentTimeMillis();
         socket.connect(clientNetworkConfig.getInetSocketAddress(), 5000);
         int count = (int)(System.currentTimeMillis() - timestamp);
         clientNetworkConfig.applyIndex(count);
         Client.getClient().applyClientNetworkConfig(clientNetworkConfig);
         ColorEntry.setClientNetworkConfig3(clientNetworkConfig);
         return new Pair("Reached proxy in " + count + "ms §8[§7" + clientNetworkConfig.getString3() + ":" + clientNetworkConfig.getInt() + "§8]", count);
      } catch (Exception exception) {
         if (!(exception instanceof SocketTimeoutException)) {
            if (exception instanceof IOException) {
               return new Pair("Failed to reach proxy", 5000);
            }

            if (exception instanceof IllegalBlockingModeException) {
               return new Pair("Illegal blocking method", 5000);
            }

            if (!(exception instanceof IllegalArgumentException)) {
               return new Pair("Unknown error", 5000);
            }

            return new Pair("Invalid IP or port", 5000);
         }

         pair = new Pair("Timeout reached, unreachable", 5000);
      } finally {
         try {
            socket.close();
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }

      return pair;
   }
}
