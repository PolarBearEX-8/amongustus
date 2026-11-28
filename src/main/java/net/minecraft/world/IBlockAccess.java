package net.minecraft.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccess {
   IBlockState getBlockState(BlockPos pos);

   boolean isAirBlock(int n, int n2, int n3);

   boolean extendedLevelsInChunkCache();

   WorldType getWorldType();

   boolean isAirBlock(BlockPos pos);

   BiomeGenBase getBiomeGenForCoords(BlockPos pos);

   IBlockState getBlockState(double d, double d2, double d3);

   int getCombinedLight(int n, int n2, int n3, int n4);

   int getStrongPower(BlockPos pos, EnumFacing facing);

   IBlockState getBlockState2(int n, int n2, int n3);

   TileEntity getTileEntity(BlockPos pos);
}
