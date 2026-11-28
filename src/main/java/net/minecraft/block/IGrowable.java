package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IGrowable {
   boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag);

   void grow(World world, Random rand, BlockPos pos, IBlockState state);

   boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state);
}
