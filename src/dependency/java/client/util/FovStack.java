package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.item.EnumFacingController;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovStack extends CustomItemEntryAdapter {
   public FovStack(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 1, 1, 1);
   }

   public FovStack() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.enumFacingController.count / 25 > 0) {
         this.applyWorld(world, distanceAggregator, 0, 0, this.enumFacingController.enabledArray[EnumFacing.DOWN.getIndexOffset1()]);
      }

      if (this.enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()] == null) {
         this.applyWorld(world, distanceAggregator, 1, 4, 1, 6, 4, 6, iBlockState4);
      }

      for (int index = 1; index <= 6; index++) {
         for (int index2 = 1; index2 <= 6; index2++) {
            if (random.nextInt(3) != 0) {
               int count = 2 + (random.nextInt(4) == 0 ? 0 : 1);
               this.applyWorld(world, distanceAggregator, index, count, index2, index, 3, index2, Blocks.block11.processAction(1), Blocks.block11.processAction(1), false);
            }
         }
      }

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

      return true;
   }
}
