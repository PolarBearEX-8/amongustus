package client.util;

import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import net.minecraft.client.Minecraft;

public class BaseVersionProviderAdapter extends BaseVersionProvider {
   public ProtocolVersion getClosestServerProtocol(UserConnection userConnection) throws Exception {
      return userConnection.isClientSide() && !Minecraft.getMinecraft().isFullScreen() ? ViaLoadingBase.protocolVersion : super.getClosestServerProtocol(userConnection);
   }
}
