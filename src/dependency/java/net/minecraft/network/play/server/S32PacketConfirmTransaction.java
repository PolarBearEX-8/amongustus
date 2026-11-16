package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;

public class S32PacketConfirmTransaction implements Packet {
   public int entityId;
   public short shortIndex;
   public boolean onGround;

   public short processValue() {
      return this.shortIndex;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         this.entityId = packet.readInt();
      } else {
         this.entityId = packet.readUnsignedByte();
         this.shortIndex = packet.readShort();
         this.onGround = packet.readBoolean();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public boolean canExecute() {
      return this.onGround;
   }

   public int getIndex() {
      return this.entityId;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.entityId);
      packet.writeShort(this.shortIndex);
      packet.writeBoolean(this.onGround);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.processAction(this);
   }

   public void setShortIndex(short shortIndex2) {
      this.shortIndex = shortIndex2;
   }

   public S32PacketConfirmTransaction() {
   }

   public S32PacketConfirmTransaction(int count, short shortValue, boolean flag) {
      this.entityId = count;
      this.shortIndex = shortValue;
      this.onGround = flag;
   }
}
