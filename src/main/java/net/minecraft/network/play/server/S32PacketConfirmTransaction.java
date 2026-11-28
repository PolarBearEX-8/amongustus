package net.minecraft.network.play.server;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S32PacketConfirmTransaction implements Packet {
   private int windowId;
   private short actionNumber;
   private boolean accepted;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S32PacketConfirmTransaction() {
   }

   public void setActionNumber(short s) {
      this.actionNumber = s;
   }

   public int getWindowId() {
      return this.windowId;
   }

   public S32PacketConfirmTransaction(int n, short s, boolean flag) {
      this.windowId = n;
      this.actionNumber = s;
      this.accepted = flag;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         this.windowId = buf.readInt();
      } else {
         this.windowId = buf.readUnsignedByte();
         this.actionNumber = buf.readShort();
         this.accepted = buf.readBoolean();
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.actionNumber);
      buf.writeBoolean(this.accepted);
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public boolean isAccepted() {
      return this.accepted;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleConfirmTransaction(this);
   }
}
