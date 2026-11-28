package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S05PacketSpawnPosition implements Packet {
   private BlockPos spawnBlockPos;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.spawnBlockPos = buf.readBlockPos();
   }

   public S05PacketSpawnPosition(BlockPos pos) {
      this.spawnBlockPos = pos;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnPosition(this);
   }

   public BlockPos getSpawnPos() {
      return this.spawnBlockPos;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.spawnBlockPos);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S05PacketSpawnPosition() {
   }
}
