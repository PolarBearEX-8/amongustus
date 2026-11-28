package net.minecraft.network.handshake.client;

import java.io.IOException;
import net.augustus.ui.BungeeHackScreen;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public class C00Handshake implements Packet {
   private int protocolVersion;
   private String ip;
   private int port;
   private EnumConnectionState requestedState;

   public void processPacket(INetHandlerHandshakeServer handler) {
      handler.processHandshake(this);
   }

   public C00Handshake(int n, String str, int n2, EnumConnectionState enumConnectionState) {
       super();
      this.protocolVersion = n;
      this.ip = str;
      this.port = n2;
      this.requestedState = enumConnectionState;
      this.ip = BungeeHackScreen.appendBungeeForwarding(this.ip);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.protocolVersion = buf.readVarIntFromBuffer();
      this.ip = buf.readStringFromBuffer(255);
      this.port = buf.readUnsignedShort();
      this.requestedState = EnumConnectionState.getById(buf.readVarIntFromBuffer());
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.protocolVersion);
      buf.writeString(this.ip);
      buf.writeShort(this.port);
      buf.writeVarIntToBuffer(this.requestedState.getId());
   }

   public C00Handshake() {
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerHandshakeServer)handler);
   }

   public EnumConnectionState getRequestedState() {
      return this.requestedState;
   }
}
