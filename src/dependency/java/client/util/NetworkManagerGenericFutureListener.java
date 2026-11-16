package client.util;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;

public class NetworkManagerGenericFutureListener implements GenericFutureListener {
   public final NetworkManager networkManager;
   public final ChatComponentText chatComponentText;
   public final CustomLoggerEntry customLoggerEntry;

   public void operationComplete(Future future) throws Exception {
      this.networkManager.applyIChatComponent(this.chatComponentText);
   }

   public NetworkManagerGenericFutureListener(CustomLoggerEntry customLoggerEntry2, NetworkManager networkManager2, ChatComponentText chatComponentText2) {
      this.customLoggerEntry = customLoggerEntry2;
      this.networkManager = networkManager2;
      this.chatComponentText = chatComponentText2;
   }
}
