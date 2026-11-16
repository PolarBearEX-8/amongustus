package cc.baritone.utils;

import client.util.IBlockState;

public interface AStarRegistry extends InternalListener {
   IBlockState resolveIBlockState(int count, int count2, int count3);

   default IBlockState[] resolveIBlockStateArray(int count, int count2) {
      IBlockState[] blockStateArray = new IBlockState[this.getCount2()];

      for (int index = 0; index < this.getCount2(); index++) {
         blockStateArray[index] = this.resolveIBlockState(count, index, count2);
      }

      return blockStateArray;
   }
}
