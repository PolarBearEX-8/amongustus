package net.minecraft.dispenser;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public interface IBlockSource extends ILocatableSource {
   @Override
   double getY();

   TileEntity getBlockTileEntity();

   int getBlockMetadata();

   @Override
   double getX();

   @Override
   double getZ();

   BlockPos getBlockPos();
}
