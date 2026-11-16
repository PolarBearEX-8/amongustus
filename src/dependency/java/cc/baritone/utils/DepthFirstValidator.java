package cc.baritone.utils;

import client.util.IBlockState;

public final class DepthFirstValidator extends AbstractDirectListener {
   public final DirectListener directListener;

   public DepthFirstValidator(DirectListener directListener2) {
      super(directListener2.getCount(), directListener2.getCount2(), directListener2.getCount3());
      this.directListener = directListener2;
   }

   @Override
   public boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return !this.directListener.matchesIBlockState(count, count2, count3, blockState);
   }
}
