package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovPipeline extends CustomItemEntryAdapter {
   public FovPipeline(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 1, 2, 2);
   }

   public FovPipeline() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      EnumFacingController enumFacingController = this.enumFacingController.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()];
      EnumFacingController enumFacingController2 = this.enumFacingController;
      EnumFacingController enumFacingController3 = enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()];
      EnumFacingController enumFacingController4 = enumFacingController2.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()];
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 0, 8, enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
         this.applyWorld(world, distanceAggregator, 0, 0, enumFacingController2.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (enumFacingController4.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 8, 1, 6, 8, 7, iBlockState4);
      }

      if (enumFacingController3.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 8, 8, 6, 8, 14, iBlockState4);
      }

      for (int index = 1; index <= 7; index++) {
         IBlockState blockState = iBlockState;
         if (index == 2 || index == 6) {
            blockState = iBlockState4;
         }

         this.applyWorld(world, distanceAggregator, 0, index, 0, 0, index, 15, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 7, index, 0, 7, index, 15, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 1, index, 0, 6, index, 0, blockState, blockState, false);
         this.applyWorld(world, distanceAggregator, 1, index, 15, 6, index, 15, blockState, blockState, false);
      }

      for (int index2 = 1; index2 <= 7; index2++) {
         IBlockState blockState2 = iBlockState2;
         if (index2 == 2 || index2 == 6) {
            blockState2 = iBlockState3;
         }

         this.applyWorld(world, distanceAggregator, 3, index2, 7, 4, index2, 8, blockState2, blockState2, false);
      }

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

      if (enumFacingController4.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 5, 0, 4, 6, 0, false);
      }

      if (enumFacingController4.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 7, 5, 3, 7, 6, 4, false);
         this.applyWorld(world, distanceAggregator, 5, 4, 2, 6, 4, 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 2, 6, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 5, 6, 3, 5, iBlockState, iBlockState, false);
      }

      if (enumFacingController4.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 5, 3, 0, 6, 4, false);
         this.applyWorld(world, distanceAggregator, 1, 4, 2, 2, 4, 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 2, 1, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 5, 1, 3, 5, iBlockState, iBlockState, false);
      }

      if (enumFacingController3.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 3, 5, 15, 4, 6, 15, false);
      }

      if (enumFacingController3.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 0, 5, 11, 0, 6, 12, false);
         this.applyWorld(world, distanceAggregator, 1, 4, 10, 2, 4, 13, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 10, 1, 3, 10, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 13, 1, 3, 13, iBlockState, iBlockState, false);
      }

      if (enumFacingController3.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
         this.applyWorld(world, distanceAggregator, 7, 5, 11, 7, 6, 12, false);
         this.applyWorld(world, distanceAggregator, 5, 4, 10, 6, 4, 13, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 10, 6, 3, 10, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 13, 6, 3, 13, iBlockState, iBlockState, false);
      }

      return true;
   }
}
