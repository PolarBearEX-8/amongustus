package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C16PacketClientStatus implements Packet {
   private C16PacketClientStatus.EnumState status;

   public C16PacketClientStatus.EnumState getStatus() {
      return this.status;
   }

   public C16PacketClientStatus(C16PacketClientStatus.EnumState enumState) {
      this.status = enumState;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processClientStatus(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.status);
   }

   public C16PacketClientStatus() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.status = (C16PacketClientStatus.EnumState)buf.readEnumValue(C16PacketClientStatus.EnumState.class);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }


   public enum EnumState {
      PERFORM_RESPAWN,
      REQUEST_STATS,
      OPEN_INVENTORY_ACHIEVEMENT;
   }
}
