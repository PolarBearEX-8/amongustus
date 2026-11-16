package net.minecraft.network.play.server;

import client.util.IBlockState;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S23PacketBlockChange implements Packet {
   public BlockPos blockPos;
   public IBlockState iBlockState;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS23PacketBlockChange(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.iBlockState = (IBlockState)Block.identityHashMapEntry.resolveTarget(packet.getInt3());
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
      packet.updateInt(Block.identityHashMapEntry.computeCount(this.iBlockState));
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public S23PacketBlockChange(World world, BlockPos blockPos2) {
      this.blockPos = blockPos2;
      this.iBlockState = world.getBlockState(blockPos2);
   }

   public S23PacketBlockChange() {
   }
}
