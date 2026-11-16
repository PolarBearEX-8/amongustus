package client.util;

import client.setting.CombatOffsetProperty;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import javax.crypto.SecretKey;
import net.minecraft.client.gui.GuiConnecting;

public class SmoothSorter implements GenericFutureListener {
   public final SecretKey secretKey;
   public final ConnectFailedManager connectFailedManager;

   public SmoothSorter(ConnectFailedManager connectFailedManager2, SecretKey secretKey2) {
      this.connectFailedManager = connectFailedManager2;
      this.secretKey = secretKey2;
   }

   public void operationComplete(Future future) throws Exception {
      GuiConnecting.add(CombatOffsetProperty.ENCRYPTING);
      ConnectFailedManager.resolveNetworkManager(this.connectFailedManager).applySecretKey(this.secretKey);
   }
}
