package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S13PacketDestroyEntities implements Packet {
   private int[] entityIDs;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityIDs = new int[buf.readVarIntFromBuffer()];

      for (int i = 0; i < this.entityIDs.length; i++) {
         this.entityIDs[i] = buf.readVarIntFromBuffer();
      }
   }

   public S13PacketDestroyEntities() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleDestroyEntities(this);
   }

   public S13PacketDestroyEntities(int[] ints) {
      this.entityIDs = ints;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int[] getEntityIDs() {
      return this.entityIDs;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityIDs.length);

      for (int i = 0; i < this.entityIDs.length; i++) {
         buf.writeVarIntToBuffer(this.entityIDs[i]);
      }
   }
}
