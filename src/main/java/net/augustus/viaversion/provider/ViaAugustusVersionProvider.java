package net.augustus.viaversion.provider;

import net.augustus.viaversion.ViaAugustus;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import net.minecraft.client.Minecraft;

public final class ViaAugustusVersionProvider extends BaseVersionProvider {
   public ProtocolVersion getClosestServerProtocol(UserConnection userConnection) throws Exception {
      return userConnection.isClientSide() && !Minecraft.getMinecraft().isSingleplayer() ? ViaAugustus.targetProtocolVersion : super.getClosestServerProtocol(userConnection);
   }
}
