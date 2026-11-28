package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S24PacketBlockAction implements Packet {
   private BlockPos blockPosition;
   private int instrument;
   private int pitch;
   private Block block;

   public int getData1() {
      return this.instrument;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.blockPosition);
      buf.writeByte(this.instrument);
      buf.writeByte(this.pitch);
      buf.writeVarIntToBuffer(Block.getIdFromBlock(this.block) & 4095);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleBlockAction(this);
   }

   public Block getBlockType() {
      return this.block;
   }

   public int getData2() {
      return this.pitch;
   }

   public BlockPos getBlockPosition() {
      return this.blockPosition;
   }

   public S24PacketBlockAction(BlockPos pos, Block block2, int n, int n2) {
      this.blockPosition = pos;
      this.instrument = n;
      this.pitch = n2;
      this.block = block2;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.blockPosition = buf.readBlockPos();
      this.instrument = buf.readUnsignedByte();
      this.pitch = buf.readUnsignedByte();
      this.block = Block.getBlockById(buf.readVarIntFromBuffer() & 4095);
   }

   public S24PacketBlockAction() {
   }
}
