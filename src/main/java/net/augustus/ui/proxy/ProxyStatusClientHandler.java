package net.augustus.ui.proxy;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public final class ProxyStatusClientHandler implements INetHandlerStatusClient {
   public final boolean[] reachableFlag;
   public final NetworkManager networkManager;

   @Override
   public void onDisconnect(IChatComponent component) {
   }

   public ProxyStatusClientHandler(boolean[] flags, NetworkManager networkManager2) {
      this.reachableFlag = flags;
      this.networkManager = networkManager2;
   }

   @Override
   public void handlePong(S01PacketPong packet) {
   }

   @Override
   public void handleServerInfo(S00PacketServerInfo packet) {
      this.reachableFlag[0] = true;
      this.networkManager.closeChannel(new ChatComponentText("Finished"));
   }
}
