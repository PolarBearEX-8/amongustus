package cc.baritone.utils;

import client.util.IBlockState;

public interface DirectListener {
   int getCount3();

   default DirectListener resolveDirectListenerByDirectListener(DirectListener directListener) {
      return new DepthFirstSelector(this, directListener, SharedCustom.OR);
   }

   default DirectListener resolveDirectListener(DirectListener directListener) {
      return new DepthFirstSelector(this, directListener, SharedCustom.XOR);
   }

   int getCount();

   int getCount2();

   default DirectListener resolveDirectListener2(DirectListener directListener) {
      return new DepthFirstSelector(this, directListener, SharedCustom.AND);
   }

   default DirectListener getDirectListener() {
      return new DepthFirstValidator(this);
   }

   boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState);
}
