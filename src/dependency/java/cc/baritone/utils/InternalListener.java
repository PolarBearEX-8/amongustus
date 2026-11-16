package cc.baritone.utils;

import client.setting.EnumFacingXType;
import client.util.IBlockState;
import java.util.List;

public interface InternalListener {
   int getCount();

   IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list);

   default void updateState() {
   }

   default int computeCount(EnumFacingXType enumFacingXType) {
      switch (DepthFirstScheduler.countArray[enumFacingXType.ordinal()]) {
         case 1:
            return this.getCount();
         case 2:
            return this.getCount2();
         case 3:
            return this.getCount3();
         default:
            throw new UnsupportedOperationException(enumFacingXType + "");
      }
   }

   default boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return count >= 0 && count < this.getCount() && count2 >= 0 && count2 < this.getCount2() && count3 >= 0 && count3 < this.getCount3();
   }

   int getCount3();

   int getCount2();
}
