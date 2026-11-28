package net.augustus.utils.network;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class PacketFlushMarker implements Packet {
   public boolean immediate = false;
   public boolean tickBoundary = false;

   public PacketFlushMarker(boolean flag) {
      this.immediate = flag;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
   }

   public PacketFlushMarker() {
   }

   @Override
   public void processPacket(INetHandler handler) {
   }
}
