package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxMatcher extends CustomItemEntryAdapter {
   public HitBoxMatcher() {
   }

   public HitBoxMatcher(EnumFacing enumFacing, EnumFacingController enumFacingController) {
      super(1, enumFacing, enumFacingController, 1, 1, 1);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 3, 0, 2, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 3, 0, 7, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 1, 2, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 2, 0, 7, 2, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 1, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 7, 1, 0, 7, 1, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, 1, 7, 7, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 2, 3, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 0, 6, 3, 0, iBlockState, iBlockState, false);
      if (this.enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 1, 7, 4, 2, 7, false);
      }

      if (this.enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 1, 3, 1, 2, 4, false);
      }

      if (this.enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 6, 1, 3, 7, 2, 4, false);
      }

      return true;
   }
}
