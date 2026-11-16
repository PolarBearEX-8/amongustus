package client.util;

import client.setting.DisablerSuccessfullyLoadedType;
import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class FutureCallbackAdapter implements FutureCallback {
   public final String labelText;
   public final NetHandlerPlayClient netHandlerPlayClient;

   public void onSuccess(Object entry) {
      NetHandlerPlayClient.resolveNetworkManager(this.netHandlerPlayClient)
         .handlePacket6(new C19PacketResourcePackStatus(this.labelText, DisablerSuccessfullyLoadedType.SUCCESSFULLY_LOADED));
   }

   public void onFailure(Throwable throwable) {
      NetHandlerPlayClient.resolveNetworkManager(this.netHandlerPlayClient)
         .handlePacket6(new C19PacketResourcePackStatus(this.labelText, DisablerSuccessfullyLoadedType.FAILED_DOWNLOAD));
   }

   public FutureCallbackAdapter(NetHandlerPlayClient netHandlerPlayClient2, String text) {
      this.netHandlerPlayClient = netHandlerPlayClient2;
      this.labelText = text;
   }
}
