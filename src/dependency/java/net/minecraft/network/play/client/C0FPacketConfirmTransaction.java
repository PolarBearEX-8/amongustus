package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;

public class C0FPacketConfirmTransaction implements Packet {
   public int entityId;
   public short shortIndex;
   public boolean onGround;

   public short getShortIndex() {
      return this.shortIndex;
   }

   public void setEntityId(int entityId2) {
      this.entityId = entityId2;
   }

   public void setShortIndex(short shortIndex2) {
      this.shortIndex = shortIndex2;
   }

   public void setOnGround(boolean onGround2) {
      this.onGround = onGround2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.entityId = packet.readByte();
      this.shortIndex = packet.readShort();
      this.onGround = packet.readByte() != 0;
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         packet.writeInt(this.entityId);
      } else {
         packet.writeByte(this.entityId);
         packet.writeShort(this.shortIndex);
         packet.writeByte(((this.onGround) ? 1 : 0));
      }
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0FPacketConfirmTransactio(this);
   }

   public C0FPacketConfirmTransaction(int count, short shortValue, boolean flag) {
      this.entityId = count;
      this.shortIndex = shortValue;
      this.onGround = flag;
   }

   public C0FPacketConfirmTransaction(int count) {
      this.entityId = count;
   }

   public C0FPacketConfirmTransaction() {
   }
}
