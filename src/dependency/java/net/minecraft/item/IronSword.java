package net.minecraft.item;

import client.util.DistanceAggregator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class IronSword extends CustomItemEntryAdapter {
   public int count6;

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 0, 0, this.enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (this.enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 4, 1, 6, 4, 6, iBlockState4);
      }

      boolean flag = this.count6 != 0
         && random.nextBoolean()
         && !this.enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]
         && !this.enumFacingController.enabledArray[EnumFacing.UP.getIndexOffset1()]
         && this.enumFacingController.getCount() > 1;
      if (this.count6 == 0) {
         this.applyWorld(world, distanceAggregator, 0, 1, 0, 2, 1, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 3, 0, 2, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 2, 2, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 1, 2, 0, 2, 2, 0, iBlockState4, iBlockState4, false);
         this.applyWorld(world, iBlockState3, 1, 2, 1, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 5, 1, 0, 7, 1, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 5, 3, 0, 7, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 2, 0, 7, 2, 2, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 5, 2, 0, 6, 2, 0, iBlockState4, iBlockState4, false);
         this.applyWorld(world, iBlockState3, 6, 2, 1, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 0, 1, 5, 2, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 3, 5, 2, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 2, 5, 0, 2, 7, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 1, 2, 7, 2, 2, 7, iBlockState4, iBlockState4, false);
         this.applyWorld(world, iBlockState3, 1, 2, 6, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 5, 1, 5, 7, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 5, 3, 5, 7, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 2, 5, 7, 2, 7, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 5, 2, 7, 6, 2, 7, iBlockState4, iBlockState4, false);
         this.applyWorld(world, iBlockState3, 6, 2, 6, distanceAggregator);
         if (this.enumFacingController.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 3, 3, 0, 4, 3, 0, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 3, 3, 0, 4, 3, 1, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 3, 2, 0, 4, 2, 0, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 3, 1, 0, 4, 1, 1, iBlockState, iBlockState, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 3, 3, 7, 4, 3, 7, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 3, 3, 6, 4, 3, 7, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 3, 2, 7, 4, 2, 7, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 3, 1, 6, 4, 1, 7, iBlockState, iBlockState, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 0, 3, 3, 0, 3, 4, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 0, 3, 3, 1, 3, 4, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 0, 2, 3, 0, 2, 4, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 0, 1, 3, 1, 1, 4, iBlockState, iBlockState, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 7, 3, 3, 7, 3, 4, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 6, 3, 3, 7, 3, 4, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 7, 2, 3, 7, 2, 4, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 6, 1, 3, 7, 1, 4, iBlockState, iBlockState, false);
         }
      } else if (this.count6 == 1) {
         this.applyWorld(world, distanceAggregator, 2, 1, 2, 2, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 2, 1, 5, 2, 3, 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 5, 1, 5, 5, 3, 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 5, 1, 2, 5, 3, 2, iBlockState, iBlockState, false);
         this.applyWorld(world, iBlockState3, 2, 2, 2, distanceAggregator);
         this.applyWorld(world, iBlockState3, 2, 2, 5, distanceAggregator);
         this.applyWorld(world, iBlockState3, 5, 2, 5, distanceAggregator);
         this.applyWorld(world, iBlockState3, 5, 2, 2, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 0, 1, 0, 1, 3, 0, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 1, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 1, 7, 1, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 1, 6, 0, 3, 6, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 7, 7, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 6, 7, 3, 6, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 1, 0, 7, 3, 0, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 1, 7, 3, 1, iBlockState, iBlockState, false);
         this.applyWorld(world, iBlockState4, 1, 2, 0, distanceAggregator);
         this.applyWorld(world, iBlockState4, 0, 2, 1, distanceAggregator);
         this.applyWorld(world, iBlockState4, 1, 2, 7, distanceAggregator);
         this.applyWorld(world, iBlockState4, 0, 2, 6, distanceAggregator);
         this.applyWorld(world, iBlockState4, 6, 2, 7, distanceAggregator);
         this.applyWorld(world, iBlockState4, 7, 2, 6, distanceAggregator);
         this.applyWorld(world, iBlockState4, 6, 2, 0, distanceAggregator);
         this.applyWorld(world, iBlockState4, 7, 2, 1, distanceAggregator);
         if (!this.enumFacingController.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 1, 3, 0, 6, 3, 0, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 1, 2, 0, 6, 2, 0, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 1, 1, 0, 6, 1, 0, iBlockState, iBlockState, false);
         }

         if (!this.enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 1, 3, 7, 6, 3, 7, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 1, 2, 7, 6, 2, 7, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 1, 1, 7, 6, 1, 7, iBlockState, iBlockState, false);
         }

         if (!this.enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 0, 3, 1, 0, 3, 6, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 0, 2, 1, 0, 2, 6, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 1, 6, iBlockState, iBlockState, false);
         }

         if (!this.enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 7, 3, 1, 7, 3, 6, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 7, 2, 1, 7, 2, 6, iBlockState4, iBlockState4, false);
            this.applyWorld(world, distanceAggregator, 7, 1, 1, 7, 1, 6, iBlockState, iBlockState, false);
         }
      } else if (this.count6 == 2) {
         this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 0, 7, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 0, 6, 1, 0, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 1, 7, 6, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 2, 7, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 7, 2, 0, 7, 2, 7, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 1, 2, 0, 6, 2, 0, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 1, 2, 7, 6, 2, 7, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 0, 3, 0, 0, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 3, 0, 7, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 3, 0, 6, 3, 0, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 1, 3, 7, 6, 3, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 0, 1, 3, 0, 2, 4, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 3, 7, 2, 4, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 3, 1, 0, 4, 2, 0, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 3, 1, 7, 4, 2, 7, iBlockState2, iBlockState2, false);
         if (this.enumFacingController.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 3, 1, 0, 4, 2, 0, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 3, 1, 7, 4, 2, 7, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 0, 1, 3, 0, 2, 4, false);
         }

         if (this.enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 7, 1, 3, 7, 2, 4, false);
         }
      }

      if (flag) {
         this.applyWorld(world, distanceAggregator, 3, 1, 3, 4, 1, 4, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 3, 2, 3, 4, 2, 4, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 3, 3, 3, 4, 3, 4, iBlockState, iBlockState, false);
      }

      return true;
   }

   public IronSword(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 1, 1, 1);
      this.count6 = random.nextInt(3);
   }

   public IronSword() {
   }
}
