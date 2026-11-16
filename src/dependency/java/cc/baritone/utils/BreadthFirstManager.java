package cc.baritone.utils;

import client.util.IBlockState;

public class BreadthFirstManager extends HeuristicResolver {
   public BreadthFirstManager(InternalListener internalListener) {
      super(internalListener);
   }

   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return count == 0 || count3 == 0 || count == this.getCount() - 1 || count3 == this.getCount3() - 1;
   }
}
