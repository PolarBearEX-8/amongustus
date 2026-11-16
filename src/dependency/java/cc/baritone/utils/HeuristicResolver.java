package cc.baritone.utils;

import client.util.IBlockState;
import java.util.List;

public abstract class HeuristicResolver extends AbstractInternalListener {
   public final InternalListener internalListener;

   public static HeuristicResolver resolveHeuristicResolver(InternalListener internalListener2, DirectListener directListener) {
      return new RuntimeComponent2(internalListener2, directListener);
   }

   public HeuristicResolver(InternalListener internalListener2) {
      super(internalListener2.getCount(), internalListener2.getCount2(), internalListener2.getCount3());
      this.internalListener = internalListener2;
   }

   @Override
   public boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.internalListener.matchesIBlockState(count, count2, count3, blockState) && this.matchesIBlockStateByIBlockState(count, count2, count3, blockState);
   }

   public abstract boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState);

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list) {
      return this.internalListener.resolveIBlockState(count, count2, count3, blockState, list);
   }
}
