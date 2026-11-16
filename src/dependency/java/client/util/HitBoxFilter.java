package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxFilter extends CustomItemEntryAdapter {
   public HitBoxFilter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 1, 2, 1);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 0, 0, this.enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      EnumFacingController enumFacingController = this.enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()];
      if (enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 8, 1, 6, 8, 6, iBlockState4);
      }

      this.applyWorld(world, distanceAggregator, 0, 4, 0, 0, 4, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 7, 4, 0, 7, 4, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 4, 0, 6, 4, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 4, 7, 6, 4, 7, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 4, 1, 2, 4, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 4, 2, 1, 4, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 4, 1, 5, 4, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 4, 2, 6, 4, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 4, 5, 2, 4, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 4, 5, 1, 4, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 4, 5, 5, 4, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 4, 5, 6, 4, 5, iBlockState, iBlockState, false);
      EnumFacingController enumFacingController2 = this.enumFacingController;

      for (int byteValue = 1; byteValue <= 5; byteValue += 4) {
         byte byteValue2 = 0;
         if (enumFacingController2.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 2, byteValue, byteValue2, 2, byteValue + 2, byteValue2, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 5, byteValue, byteValue2, 5, byteValue + 2, byteValue2, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 3, byteValue + 2, byteValue2, 4, byteValue + 2, byteValue2, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 0, byteValue, byteValue2, 7, byteValue + 2, byteValue2, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 0, byteValue + 1, byteValue2, 7, byteValue + 1, byteValue2, iBlockState4, iBlockState4, false);
         }

         byte byteValue3 = 7;
         if (enumFacingController2.enabledArray[EnumFacing.NORTH.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, 2, byteValue, byteValue3, 2, byteValue + 2, byteValue3, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 5, byteValue, byteValue3, 5, byteValue + 2, byteValue3, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 3, byteValue + 2, byteValue3, 4, byteValue + 2, byteValue3, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, 0, byteValue, byteValue3, 7, byteValue + 2, byteValue3, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 0, byteValue + 1, byteValue3, 7, byteValue + 1, byteValue3, iBlockState4, iBlockState4, false);
         }

         byte byteValue4 = 0;
         if (enumFacingController2.enabledArray[EnumFacing.WEST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, byteValue4, byteValue, 2, byteValue4, byteValue + 2, 2, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue4, byteValue, 5, byteValue4, byteValue + 2, 5, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue4, byteValue + 2, 3, byteValue4, byteValue + 2, 4, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, byteValue4, byteValue, 0, byteValue4, byteValue + 2, 7, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue4, byteValue + 1, 0, byteValue4, byteValue + 1, 7, iBlockState4, iBlockState4, false);
         }

         byte byteValue5 = 7;
         if (enumFacingController2.enabledArray[EnumFacing.EAST.getIndexOffset1()]) {
            this.applyWorld(world, distanceAggregator, byteValue5, byteValue, 2, byteValue5, byteValue + 2, 2, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue5, byteValue, 5, byteValue5, byteValue + 2, 5, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue5, byteValue + 2, 3, byteValue5, byteValue + 2, 4, iBlockState, iBlockState, false);
         } else {
            this.applyWorld(world, distanceAggregator, byteValue5, byteValue, 0, byteValue5, byteValue + 2, 7, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue5, byteValue + 1, 0, byteValue5, byteValue + 1, 7, iBlockState4, iBlockState4, false);
         }

         enumFacingController2 = enumFacingController;
      }

      return true;
   }

   public HitBoxFilter() {
   }
}
