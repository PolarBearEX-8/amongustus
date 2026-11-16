package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovQueue extends CustomItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      EnumFacingController enumFacingController = this.enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()];
      EnumFacingController enumFacingController2 = this.enumFacingController;
      EnumFacingController enumFacingController3 = enumFacingController2.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()];
      EnumFacingController enumFacingController4 = enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()];
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 8, 0, enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
         this.applyWorld(world, distanceAggregator, 0, 0, enumFacingController2.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (enumFacingController3.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 8, 1, 7, 8, 6, iBlockState4);
      }

      if (enumFacingController4.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 8, 8, 1, 14, 8, 6, iBlockState4);
      }

      for (int index = 1; index <= 7; index++) {
         IBlockState blockState = iBlockState;
         if (index == 2 || index == 6) {
            blockState = iBlockState4;
         }

         this.applyWorld(world, distanceAggregator, 0, index, 0, 0, index, 7, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 15, index, 0, 15, index, 7, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 1, index, 0, 15, index, 0, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 1, index, 7, 14, index, 7, blockState, blockState, false);
      }

      this.applyWorld(world, distanceAggregator, 2, 1, 3, 2, 7, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 1, 2, 4, 7, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 1, 5, 4, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 13, 1, 3, 13, 7, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 11, 1, 2, 12, 7, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 11, 1, 5, 12, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 3, 5, 3, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 1, 3, 10, 3, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 7, 2, 10, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 5, 2, 5, 7, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 5, 2, 10, 7, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 5, 5, 5, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 5, 5, 10, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, iBlockState, 6, 6, 2, distanceAggregator);
      this.applyWorld(world, iBlockState, 9, 6, 2, distanceAggregator);
      this.applyWorld(world, iBlockState, 6, 6, 5, distanceAggregator);
      this.applyWorld(world, iBlockState, 9, 6, 5, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 5, 4, 3, 6, 4, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 4, 3, 10, 4, 4, iBlockState, iBlockState, false);
      this.applyWorld(world, iBlockState3, 5, 4, 2, distanceAggregator);
      this.applyWorld(world, iBlockState3, 5, 4, 5, distanceAggregator);
      this.applyWorld(world, iBlockState3, 10, 4, 2, distanceAggregator);
      this.applyWorld(world, iBlockState3, 10, 4, 5, distanceAggregator);
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

      if (enumFacingController3.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 5, 0, 4, 6, 0, false);
      }

      if (enumFacingController3.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 5, 7, 4, 6, 7, false);
      }

      if (enumFacingController3.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 5, 3, 0, 6, 4, false);
      }

      if (enumFacingController4.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 11, 5, 0, 12, 6, 0, false);
      }

      if (enumFacingController4.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 11, 5, 7, 12, 6, 7, false);
      }

      if (enumFacingController4.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 15, 5, 3, 15, 6, 4, false);
      }

      return true;
   }

   public FovQueue(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 2, 2, 1);
   }

   public FovQueue() {
   }
}
