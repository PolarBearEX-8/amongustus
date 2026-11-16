package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxChecker extends CustomItemEntryAdapter {
   public HitBoxChecker() {
   }

   public HitBoxChecker(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 1, 1, 2);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      EnumFacingController enumFacingController = this.enumFacingController.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()];
      EnumFacingController enumFacingController2 = this.enumFacingController;
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 0, 8, enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
         this.applyWorld(world, distanceAggregator, 0, 0, enumFacingController2.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (enumFacingController2.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 4, 1, 6, 4, 7, iBlockState4);
      }

      if (enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 4, 8, 6, 4, 14, iBlockState4);
      }

      this.applyWorld(world, distanceAggregator, 0, 3, 0, 0, 3, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 7, 3, 0, 7, 3, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 0, 7, 3, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 15, 6, 3, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 2, 15, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 7, 2, 0, 7, 2, 15, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 7, 2, 0, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 1, 2, 15, 6, 2, 15, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 1, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 7, 1, 0, 7, 1, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 7, 1, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 15, 6, 1, 15, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 1, 1, 1, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 1, 1, 6, 1, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 1, 1, 3, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 3, 1, 6, 3, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 13, 1, 1, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 1, 13, 6, 1, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 13, 1, 3, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 3, 13, 6, 3, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 1, 6, 2, 3, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 6, 5, 3, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 1, 9, 2, 3, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 9, 5, 3, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 2, 6, 4, 2, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 2, 9, 4, 2, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 2, 7, 2, 2, 8, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 2, 7, 5, 2, 8, iBlockState, iBlockState, false);
      this.applyWorld(world, iBlockState3, 2, 2, 5, distanceAggregator);
      this.applyWorld(world, iBlockState3, 5, 2, 5, distanceAggregator);
      this.applyWorld(world, iBlockState3, 2, 2, 10, distanceAggregator);
      this.applyWorld(world, iBlockState3, 5, 2, 10, distanceAggregator);
      this.applyWorld(world, iBlockState, 2, 3, 5, distanceAggregator);
      this.applyWorld(world, iBlockState, 5, 3, 5, distanceAggregator);
      this.applyWorld(world, iBlockState, 2, 3, 10, distanceAggregator);
      this.applyWorld(world, iBlockState, 5, 3, 10, distanceAggregator);
      if (enumFacingController2.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 1, 0, 4, 2, 0, false);
      }

      if (enumFacingController2.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 7, 1, 3, 7, 2, 4, false);
      }

      if (enumFacingController2.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 1, 3, 0, 2, 4, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 1, 15, 4, 2, 15, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 1, 11, 0, 2, 12, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 7, 1, 11, 7, 2, 12, false);
      }

      return true;
   }
}
