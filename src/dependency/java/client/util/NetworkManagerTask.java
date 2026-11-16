package client.util;

import java.util.concurrent.Callable;
import net.minecraft.network.NetworkManager;

public class NetworkManagerTask implements Callable {
   public final NetworkManager networkManager;
   public final CustomLoggerEntry customLoggerEntry;

   public NetworkManagerTask(CustomLoggerEntry customLoggerEntry2, NetworkManager networkManager2) {
      this.customLoggerEntry = customLoggerEntry2;
      this.networkManager = networkManager2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.networkManager.toString();
   }
}
