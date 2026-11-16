package cc.baritone.utils;

import client.util.IBlockState;
import java.util.List;

public class IBlockStateInternalListen extends AbstractInternalListener implements AStarRegistry {
   public IBlockState[][][] iBlockStateArray;

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3) {
      return this.iBlockStateArray[count][count3][count2];
   }

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list) {
      return this.iBlockStateArray[count][count3][count2];
   }

   @Override
   public IBlockState[] resolveIBlockStateArray(int count, int count2) {
      return this.iBlockStateArray[count][count2];
   }
}
