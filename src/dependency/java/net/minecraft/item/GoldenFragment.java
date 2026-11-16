package net.minecraft.item;

import client.util.DistanceAggregator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenFragment extends CustomItemEntryAdapter {
   public GoldenFragment() {
   }

   public GoldenFragment(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 2, 1, 1);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      EnumFacingController enumFacingController = this.enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()];
      EnumFacingController enumFacingController2 = this.enumFacingController;
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 8, 0, enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
         this.applyWorld(world, distanceAggregator, 0, 0, enumFacingController2.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (enumFacingController2.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 4, 1, 7, 4, 6, iBlockState4);
      }

      if (enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 8, 4, 1, 14, 4, 6, iBlockState4);
      }

      this.applyWorld(world, distanceAggregator, 0, 3, 0, 0, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 15, 3, 0, 15, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 0, 15, 3, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 3, 7, 14, 3, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 2, 7, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 15, 2, 0, 15, 2, 7, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 15, 2, 0, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 1, 2, 7, 14, 2, 7, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 1, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 15, 1, 0, 15, 1, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 15, 1, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 1, 7, 14, 1, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 0, 10, 1, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 2, 0, 9, 2, 3, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 5, 3, 0, 10, 3, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, iBlockState3, 6, 2, 3, distanceAggregator);
      this.applyWorld(world, iBlockState3, 9, 2, 3, distanceAggregator);
      if (enumFacingController2.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 1, 0, 4, 2, 0, false);
      }

      if (enumFacingController2.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 1, 7, 4, 2, 7, false);
      }

      if (enumFacingController2.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 1, 3, 0, 2, 4, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 11, 1, 0, 12, 2, 0, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 11, 1, 7, 12, 2, 7, false);
      }

      if (enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 15, 1, 3, 15, 2, 4, false);
      }

      return true;
   }
}
