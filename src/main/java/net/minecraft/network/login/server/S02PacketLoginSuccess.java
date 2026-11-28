package net.minecraft.network.login.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S02PacketLoginSuccess implements Packet {
   private GameProfile profile;

   public GameProfile getProfile() {
      return this.profile;
   }

   public S02PacketLoginSuccess(GameProfile profile2) {
      this.profile = profile2;
   }

   public S02PacketLoginSuccess() {
   }

   public void processPacket(INetHandlerLoginClient handler) {
      handler.handleLoginSuccess(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerLoginClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      UUID id = this.profile.getId();
      buf.writeString(id == null ? "" : id.toString());
      buf.writeString(this.profile.getName());
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      String str = buf.readStringFromBuffer(36);
      String str2 = buf.readStringFromBuffer(16);
      UUID uuid = UUID.fromString(str);
      this.profile = new GameProfile(uuid, str2);
   }
}
