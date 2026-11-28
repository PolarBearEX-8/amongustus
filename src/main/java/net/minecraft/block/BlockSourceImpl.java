package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSourceImpl implements IBlockSource {
   private final World worldObj;
   private final BlockPos pos;

   @Override
   public int getBlockMetadata() {
      IBlockState blockState = this.worldObj.getBlockState(this.pos);
      return blockState.getBlock().getMetaFromState(blockState);
   }

   @Override
   public double getY() {
      return (double)this.pos.getY() + 0.5;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.pos;
   }

   @Override
   public double getZ() {
      return (double)this.pos.getZ() + 0.5;
   }

   @Override
   public double getX() {
      return (double)this.pos.getX() + 0.5;
   }

   @Override
   public World getWorld() {
      return this.worldObj;
   }

   public BlockSourceImpl(World world, BlockPos pos2) {
      this.worldObj = world;
      this.pos = pos2;
   }

   @Override
   public TileEntity getBlockTileEntity() {
      return this.worldObj.getTileEntity(this.pos);
   }
}
