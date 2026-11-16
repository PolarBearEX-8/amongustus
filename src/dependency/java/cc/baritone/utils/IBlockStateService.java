package cc.baritone.utils;

import client.util.IBlockState;
import net.minecraft.util.BlockPos;

public interface IBlockStateService {
   IBlockState resolveIBlockState(int count, int count2, int count3);

   default IBlockState resolveIBlockState(BlockPos blockPos) {
      return this.resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }
}
