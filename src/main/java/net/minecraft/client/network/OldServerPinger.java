package net.minecraft.client.network;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.ArrayUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
   private static final Splitter PING_RESPONSE_SPLITTER = Splitter.on('\u0000').limit(6);
   private static final Logger logger = LogManager.getLogger();
   private final List pingDestinations = Collections.synchronizedList(Lists.newArrayList());

   public void pingPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator it = this.pingDestinations.iterator();

         while (it.hasNext()) {
            NetworkManager networkManager = (NetworkManager)it.next();
            if (networkManager.isChannelOpen()) {
               networkManager.processReceivedPackets();
            } else {
               it.remove();
               networkManager.checkDisconnected();
            }
         }
      }
   }

   public void ping(ServerData serverData) throws UnknownHostException {
      ServerAddress serverAddress = ServerAddress.fromString(serverData.serverIP);
      InetAddress byName = InetAddress.getByName(serverAddress.getIP());
      NetworkManager networkManager = NetworkManager.createNetworkManagerAndConnect(byName, serverAddress.getPort(), false);
      this.pingDestinations.add(networkManager);
      serverData.serverMOTD = "Pinging...";
      serverData.pingToServer = -1L;
      serverData.playerList = null;
      networkManager.setNetHandler(new OldServerPinger.DefaultNetHandlerStatusClient(this, networkManager, serverData));

      try {
         if (ViaAugustus.targetProtocolVersion.newerThan(ProtocolVersion.v1_17)) {
            networkManager.sendPacket(new C00Handshake(47, byName.getHostName(), serverAddress.getPort(), EnumConnectionState.STATUS));
         } else {
            networkManager.sendPacket(new C00Handshake(47, serverAddress.getIP(), serverAddress.getPort(), EnumConnectionState.STATUS));
         }

         networkManager.sendPacket(new C00PacketServerQuery());
      } catch (Throwable e) {
         logger.error(e);
      }
   }

   public void clearPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator it = this.pingDestinations.iterator();

         while (it.hasNext()) {
            NetworkManager networkManager = (NetworkManager)it.next();
            if (networkManager.isChannelOpen()) {
               it.remove();
               networkManager.closeChannel(new ChatComponentText("Cancelled"));
            }
         }
      }
   }

   public static Logger accessLogger() {
      return logger;
   }


   public static class DefaultNetHandlerStatusClient implements INetHandlerStatusClient {
      private boolean successful;
      private boolean receivedStatus;
      private long pingStartTime;
      public final NetworkManager networkManager;
      public final ServerData serverData;
      public final OldServerPinger oldServerPinger;

      public DefaultNetHandlerStatusClient(OldServerPinger oldServerPinger2, NetworkManager networkManager2, ServerData serverData2) {
         this.oldServerPinger = oldServerPinger2;
         this.networkManager = networkManager2;
         this.serverData = serverData2;
         this.successful = false;
         this.receivedStatus = false;
         this.pingStartTime = 0L;
      }

      @Override
      public void onDisconnect(IChatComponent component) {
         if (!this.successful) {
            OldServerPinger.accessLogger().error("Can't ping " + this.serverData.serverIP + ": " + component.getUnformattedText());
            this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
            this.serverData.populationInfo = "";
         }
      }

      @Override
      public void handlePong(S01PacketPong packet) {
         long pingStartTime2 = this.pingStartTime;
         long systemTime = Minecraft.getSystemTime();
         this.serverData.pingToServer = systemTime - pingStartTime2;
         this.networkManager.closeChannel(new ChatComponentText("Finished"));
      }

      @Override
      public void handleServerInfo(S00PacketServerInfo packet) {
         if (this.receivedStatus) {
            this.networkManager.closeChannel(new ChatComponentText("Received unrequested status"));
         } else {
            this.receivedStatus = true;
            ServerStatusResponse response = packet.getResponse();
            if (response.getServerDescription() != null) {
               this.serverData.serverMOTD = response.getServerDescription().getFormattedText();
            } else {
               this.serverData.serverMOTD = "";
            }

            if (response.getProtocolVersionInfo() != null) {
               this.serverData.gameVersion = response.getProtocolVersionInfo().getName();
               this.serverData.version = response.getProtocolVersionInfo().getProtocol();
            } else {
               this.serverData.gameVersion = "Old";
               this.serverData.version = 0;
            }

            if (response.getPlayerCountData() == null) {
               this.serverData.populationInfo = EnumChatFormatting.DARK_GRAY + "???";
            } else {
               this.serverData.populationInfo = EnumChatFormatting.GRAY
                  + ""
                  + response.getPlayerCountData().getOnlinePlayerCount()
                  + EnumChatFormatting.DARK_GRAY
                  + "/"
                  + EnumChatFormatting.GRAY
                  + response.getPlayerCountData().getMaxPlayers();
               if (ArrayUtils.isNotEmpty(response.getPlayerCountData().getPlayers())) {
                  StringBuilder sb = new StringBuilder();

                  for (GameProfile profile : response.getPlayerCountData().getPlayers()) {
                     if (sb.length() > 0) {
                        sb.append("\n");
                     }

                     sb.append(profile.getName());
                  }

                  if (response.getPlayerCountData().getPlayers().length < response.getPlayerCountData().getOnlinePlayerCount()) {
                     if (sb.length() > 0) {
                        sb.append("\n");
                     }

                     sb.append("... and ")
                        .append(response.getPlayerCountData().getOnlinePlayerCount() - response.getPlayerCountData().getPlayers().length)
                        .append(" more ...");
                  }

                  this.serverData.playerList = sb.toString();
               }
            }

            if (response.getFavicon() != null) {
               String favicon = response.getFavicon();
               if (favicon.startsWith("data:image/png;base64,")) {
                  this.serverData.setBase64EncodedIconData(favicon.substring("data:image/png;base64,".length()));
               } else {
                  OldServerPinger.accessLogger().error("Invalid server icon (unknown format)");
               }
            } else {
               this.serverData.setBase64EncodedIconData(null);
            }

            this.pingStartTime = Minecraft.getSystemTime();
            this.networkManager.sendPacket(new C01PacketPing(this.pingStartTime));
            this.successful = true;
         }
      }
   }
}
