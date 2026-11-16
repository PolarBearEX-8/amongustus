package cc.baritone.utils;

import client.util.IBlockState;

public class HeuristicCalibrator extends HeuristicResolver {
   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return count == 0 || count2 == 0 || count3 == 0 || count == this.getCount() - 1 || count2 == this.getCount2() - 1 || count3 == this.getCount3() - 1;
   }

   public HeuristicCalibrator(InternalListener internalListener) {
      super(internalListener);
   }
}
