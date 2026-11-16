package client.util;

import client.event.PreRenderListener;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationAggregator implements PreRenderListener {
   public final World world;
   public final BlockPos blockPos;

   @Override
   public TileEntity getTileEntity() {
      return this.world.resolveTileEntity(this.blockPos);
   }

   public RotationAggregator(World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
   }

   @Override
   public World getWorld() {
      return this.world;
   }

   @Override
   public double getValue() {
      return (double)this.blockPos.getX() + 0.5;
   }

   @Override
   public int getCount() {
      IBlockState blockState = this.world.getBlockState(this.blockPos);
      return blockState.getBlock().getIndex(blockState);
   }

   @Override
   public double getValue2() {
      return (double)this.blockPos.getY() + 0.5;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public double getValue3() {
      return (double)this.blockPos.getZ() + 0.5;
   }
}
