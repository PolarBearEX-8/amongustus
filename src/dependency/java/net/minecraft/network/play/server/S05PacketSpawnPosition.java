package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;

public class S05PacketSpawnPosition implements Packet {
   public BlockPos blockPos;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS05PacketSpawnPosition(this);
   }

   public S05PacketSpawnPosition() {
   }

   public S05PacketSpawnPosition(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }
}
