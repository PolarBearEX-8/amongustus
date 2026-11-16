package client.util;

import client.setting.CommonOptionType;
import net.minecraft.block.BlockQuartzBlock;

public class DampenerTracker extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      CommonOptionType comparable = (CommonOptionType)blockState.getComparable(BlockQuartzBlock.immutableSetEntry);
      switch (SmoothEstimator.countArray[comparable.ordinal()]) {
         case 1:
         default:
            return new ResourceLocationAdapter("quartz_block", "normal");
         case 2:
            return new ResourceLocationAdapter("chiseled_quartz_block", "normal");
         case 3:
            return new ResourceLocationAdapter("quartz_column", "axis=y");
         case 4:
            return new ResourceLocationAdapter("quartz_column", "axis=x");
         case 5:
            return new ResourceLocationAdapter("quartz_column", "axis=z");
      }
   }

   public DampenerTracker(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
