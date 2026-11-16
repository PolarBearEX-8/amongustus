package client.gui;

import client.setting.DisablerSuccessfullyLoadedType;
import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class DefaultGuiScreenAdapFutureCallback implements FutureCallback {
   public final DefaultGuiScreenAdapter defaultGuiScreenAdapter;

   public void onSuccess(Object entry) {
      this.defaultGuiScreenAdapter
         .networkManager
         .handlePacket6(new C19PacketResourcePackStatus(this.defaultGuiScreenAdapter.labelText2, DisablerSuccessfullyLoadedType.SUCCESSFULLY_LOADED));
   }

   public DefaultGuiScreenAdapFutureCallback(DefaultGuiScreenAdapter defaultGuiScreenAdapter2) {
      this.defaultGuiScreenAdapter = defaultGuiScreenAdapter2;
   }

   public void onFailure(Throwable throwable) {
      this.defaultGuiScreenAdapter
         .networkManager
         .handlePacket6(new C19PacketResourcePackStatus(this.defaultGuiScreenAdapter.labelText2, DisablerSuccessfullyLoadedType.FAILED_DOWNLOAD));
   }
}
