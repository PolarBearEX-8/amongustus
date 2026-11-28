package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S23PacketBlockChange implements Packet {
   private BlockPos blockPosition;
   private IBlockState blockState;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public BlockPos getBlockPosition() {
      return this.blockPosition;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleBlockChange(this);
   }

   public S23PacketBlockChange() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.blockPosition = buf.readBlockPos();
      this.blockState = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer());
   }

   public S23PacketBlockChange(World world, BlockPos pos) {
      this.blockPosition = pos;
      this.blockState = world.getBlockState(pos);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.blockPosition);
      buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.blockState));
   }
}
