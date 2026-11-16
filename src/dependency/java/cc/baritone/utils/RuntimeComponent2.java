package cc.baritone.utils;

import client.util.IBlockState;

public class RuntimeComponent2 extends HeuristicResolver {
   public final DirectListener directListener;

   public RuntimeComponent2(InternalListener internalListener, DirectListener directListener) {
      super(internalListener);
      this.directListener = directListener;
   }

   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.directListener.matchesIBlockState(count, count2, count3, blockState);
   }
}
