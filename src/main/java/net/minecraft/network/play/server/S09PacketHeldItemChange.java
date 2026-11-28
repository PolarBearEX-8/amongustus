package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S09PacketHeldItemChange implements Packet {
   private int heldItemHotbarIndex;

   public S09PacketHeldItemChange(int n) {
      this.heldItemHotbarIndex = n;
   }

   public int getHeldItemHotbarIndex() {
      return this.heldItemHotbarIndex;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.heldItemHotbarIndex);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.heldItemHotbarIndex = buf.readByte();
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleHeldItemChange(this);
   }

   public S09PacketHeldItemChange() {
   }
}
