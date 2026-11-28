package net.minecraft.network.play.server;

import net.minecraft.util.BlockPos;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

public class S22PacketMultiBlockChange implements Packet {
   private ChunkCoordIntPair chunkPosCoord;
   private S22PacketMultiBlockChange.BlockUpdateData[] changedBlocks;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.chunkPosCoord = new ChunkCoordIntPair(buf.readInt(), buf.readInt());
      this.changedBlocks = new S22PacketMultiBlockChange.BlockUpdateData[buf.readVarIntFromBuffer()];

      for (int i = 0; i < this.changedBlocks.length; i++) {
         this.changedBlocks[i] = new S22PacketMultiBlockChange.BlockUpdateData(
            this, buf.readShort(), (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer())
         );
      }
   }

   public S22PacketMultiBlockChange.BlockUpdateData[] getChangedBlocks() {
      return this.changedBlocks;
   }

   public S22PacketMultiBlockChange(int n, short[] sArr, Chunk chunk) {
      this.chunkPosCoord = new ChunkCoordIntPair(chunk.xPosition, chunk.zPosition);
      this.changedBlocks = new S22PacketMultiBlockChange.BlockUpdateData[n];

      for (int i = 0; i < this.changedBlocks.length; i++) {
         this.changedBlocks[i] = new S22PacketMultiBlockChange.BlockUpdateData(this, sArr[i], chunk);
      }
   }

   public static ChunkCoordIntPair accessChunkPosCoord(S22PacketMultiBlockChange packet) {
      return packet.chunkPosCoord;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleMultiBlockChange(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.chunkPosCoord.chunkXPos);
      buf.writeInt(this.chunkPosCoord.chunkZPos);
      buf.writeVarIntToBuffer(this.changedBlocks.length);

      for (S22PacketMultiBlockChange.BlockUpdateData blockUpdateData : this.changedBlocks) {
         buf.writeShort(blockUpdateData.getChunkPosCrammed());
         buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(blockUpdateData.getBlockState()));
      }
   }

   public S22PacketMultiBlockChange() {
   }


   public static class BlockUpdateData {
      private final short chunkPosCrammed;
      private final IBlockState blockState;
      public final S22PacketMultiBlockChange parentPacket;

      public BlockUpdateData(S22PacketMultiBlockChange packet, short s, Chunk chunk) {
         this.parentPacket = packet;
         this.chunkPosCrammed = s;
         this.blockState = chunk.getBlockState(this.getPos());
      }

      public IBlockState getBlockState() {
         return this.blockState;
      }

      public short getChunkPosCrammed() {
         return this.chunkPosCrammed;
      }

      public BlockUpdateData(S22PacketMultiBlockChange packet, short s, IBlockState state) {
         this.parentPacket = packet;
         this.chunkPosCrammed = s;
         this.blockState = state;
      }

      public BlockPos getPos() {
         return new BlockPos(
            S22PacketMultiBlockChange.accessChunkPosCoord(this.parentPacket).getBlock(this.chunkPosCrammed >> 12 & 15, this.chunkPosCrammed & 255, this.chunkPosCrammed >> 8 & 15)
         );
      }
   }
}
