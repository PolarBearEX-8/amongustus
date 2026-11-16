package cc.baritone.utils;

import client.util.IBlockState;

public final class DepthFirstSelector extends AbstractDirectListener {
   public final DirectListener directListener;
   public final DirectListener directListener2;
   public final BreadthFirstPredictor breadthFirstPredictor;

   @Override
   public boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.breadthFirstPredictor
         .checkFlag(matchesDirectListener(this.directListener, count, count2, count3, blockState), matchesDirectListener(this.directListener2, count, count2, count3, blockState));
   }

   public DepthFirstSelector(DirectListener directListener3, DirectListener directListener4, BreadthFirstPredictor breadthFirstPredictor2) {
      super(Math.max(directListener3.getCount(), directListener4.getCount()), Math.max(directListener3.getCount2(), directListener4.getCount2()), Math.max(directListener3.getCount3(), directListener4.getCount3()));
      this.directListener = directListener3;
      this.directListener2 = directListener4;
      this.breadthFirstPredictor = breadthFirstPredictor2;
   }

   private static boolean matchesDirectListener(DirectListener directListener3, int count, int count2, int count3, IBlockState blockState) {
      return count < directListener3.getCount() && count2 < directListener3.getCount2() && count3 < directListener3.getCount3() && directListener3.matchesIBlockState(count, count2, count3, blockState);
   }
}
