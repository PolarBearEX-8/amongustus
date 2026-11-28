package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem implements Packet {
   private int collectedItemEntityId;
   private int entityId;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.collectedItemEntityId = buf.readVarIntFromBuffer();
      this.entityId = buf.readVarIntFromBuffer();
   }

   public S0DPacketCollectItem() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleCollectItem(this);
   }

   public int getCollectedItemEntityID() {
      return this.collectedItemEntityId;
   }

   public int getEntityID() {
      return this.entityId;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.collectedItemEntityId);
      buf.writeVarIntToBuffer(this.entityId);
   }

   public S0DPacketCollectItem(int n, int n2) {
      this.collectedItemEntityId = n;
      this.entityId = n2;
   }
}
