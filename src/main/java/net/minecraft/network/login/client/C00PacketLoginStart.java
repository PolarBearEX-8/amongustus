package net.minecraft.network.login.client;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart implements Packet {
   private GameProfile profile;

   public C00PacketLoginStart() {
   }

   public C00PacketLoginStart(GameProfile profile2) {
      this.profile = profile2;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.profile = new GameProfile((UUID)null, buf.readStringFromBuffer(16));
   }

   public void processPacket(INetHandlerLoginServer handler) {
      handler.processLoginStart(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.profile.getName());
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerLoginServer)handler);
   }
}
