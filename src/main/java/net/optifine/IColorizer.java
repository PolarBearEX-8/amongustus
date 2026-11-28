package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IColorizer {
   int getColor(IBlockState state, IBlockAccess world, BlockPos pos);

   boolean isColorConstant();
}
