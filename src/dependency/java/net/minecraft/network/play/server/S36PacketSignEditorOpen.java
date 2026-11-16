package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;

public class S36PacketSignEditorOpen implements Packet {
   public BlockPos blockPos;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS36PacketSignEditorOpen(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public S36PacketSignEditorOpen(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public S36PacketSignEditorOpen() {
   }
}
