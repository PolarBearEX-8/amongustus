package client.util;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class ExtraPacketHandler implements Packet {
   public GameProfile gameProfile;

   public void applyInternalINetHandlerExtensi(InternalINetHandlerExtension internalINetHandlerExtension) {
      internalINetHandlerExtension.applyExtraPacketHandler(this);
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.gameProfile = new GameProfile((UUID)null, packet.resolveTextByIndex(16));
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyInternalINetHandlerExtensi((InternalINetHandlerExtension)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.gameProfile.getName());
   }

   public ExtraPacketHandler() {
   }

   public ExtraPacketHandler(GameProfile gameProfile2) {
      this.gameProfile = gameProfile2;
   }
}
