package client.event;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface PreMotionFilter {
   boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag);

   void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState);

   boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState);
}
