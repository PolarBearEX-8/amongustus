package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S25PacketBlockBreakAnim implements Packet {
   private int breakerId;
   private BlockPos position;
   private int progress;

   public S25PacketBlockBreakAnim() {
   }

   public S25PacketBlockBreakAnim(int n, BlockPos pos, int n2) {
      this.breakerId = n;
      this.position = pos;
      this.progress = n2;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleBlockBreakAnim(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.breakerId = buf.readVarIntFromBuffer();
      this.position = buf.readBlockPos();
      this.progress = buf.readUnsignedByte();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.breakerId);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.progress);
   }

   public int getProgress() {
      return this.progress;
   }

   public int getBreakerId() {
      return this.breakerId;
   }
}
