package net.minecraft.world;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.IBlockState;
import client.util.InfoManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public interface IBlockAccess {
   IBlockState getBlockState(BlockPos blockPos);

   TileEntity resolveTileEntity(BlockPos blockPos);

   int computeCount(BlockPos blockPos, EnumFacing enumFacing);

   int computeCount(int count, int count2, int count3, int count4);

   boolean isEnabled();

   BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos);

   InfoManager getInfoManager();

   IBlockState getIBlockState(int count, int count2, int count3);

   boolean checkCondition(int count, int count2, int count3);

   IBlockState getIBlockState2(double posX, double posY, double posZ);

   boolean isAirBlock(BlockPos blockPos);
}
