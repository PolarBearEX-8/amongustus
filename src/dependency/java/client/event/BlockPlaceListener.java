package client.event;

import client.util.IBlockState;

public interface BlockPlaceListener {
   static void applyIBlockState(int count, int count2, int count3, IBlockState blockState) {
      EventBusManager.getDietrichEvents2().post(55, new EventBlockPlace(count, count2, count3, blockState));
   }

   void onBlockPlace(EventBlockPlace event);
}
