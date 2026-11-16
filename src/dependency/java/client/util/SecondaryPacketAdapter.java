package client.util;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class SecondaryPacketAdapter implements Packet {
   public GameProfile gameProfile;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      String text = packet.resolveTextByIndex(36);
      String text2 = packet.resolveTextByIndex(16);
      UUID uuid = UUID.fromString(text);
      this.gameProfile = new GameProfile(uuid, text2);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyINetHandlerExtension((INetHandlerExtension)iNetHandler);
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      UUID id = this.gameProfile.getId();
      packet.getPacketBuffer(id == null ? "" : id.toString());
      packet.getPacketBuffer(this.gameProfile.getName());
   }

   public void applyINetHandlerExtension(INetHandlerExtension iNetHandlerExtension) {
      iNetHandlerExtension.applySecondaryPacketAdapter(this);
   }

   public SecondaryPacketAdapter() {
   }

   public SecondaryPacketAdapter(GameProfile gameProfile2) {
      this.gameProfile = gameProfile2;
   }
}
