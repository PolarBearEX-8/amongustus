package net.minecraft.network.play.client;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0FPacketConfirmTransaction implements Packet {
   private int windowId;
   private short uid;
   private boolean accepted;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         buf.writeInt(this.windowId);
      } else {
         buf.writeByte(this.windowId);
         buf.writeShort(this.uid);
         buf.writeByte(this.accepted ? 1 : 0);
      }
   }

   public C0FPacketConfirmTransaction(int n) {
      this.windowId = n;
   }

   public C0FPacketConfirmTransaction() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
      this.uid = buf.readShort();
      this.accepted = buf.readByte() != 0;
   }

   public void setUid(short s) {
      this.uid = s;
   }

   public int getWindowId() {
      return this.windowId;
   }

   public boolean isAccepted() {
      return this.accepted;
   }

   public void setWindowId(int n) {
      this.windowId = n;
   }

   public short getUid() {
      return this.uid;
   }

   public void setAccepted(boolean flag) {
      this.accepted = flag;
   }

   public C0FPacketConfirmTransaction(int n, short s, boolean flag) {
      this.windowId = n;
      this.uid = s;
      this.accepted = flag;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processConfirmTransaction(this);
   }
}
