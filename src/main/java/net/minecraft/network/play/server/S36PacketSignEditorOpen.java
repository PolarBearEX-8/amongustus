package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S36PacketSignEditorOpen implements Packet {
   private BlockPos signPosition;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.signPosition);
   }

   public S36PacketSignEditorOpen() {
   }

   public S36PacketSignEditorOpen(BlockPos pos) {
      this.signPosition = pos;
   }

   public BlockPos getSignPosition() {
      return this.signPosition;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.signPosition = buf.readBlockPos();
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSignEditorOpen(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
