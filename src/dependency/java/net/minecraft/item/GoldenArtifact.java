package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenArtifact extends CustomItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 1, 8, 0, 14, 8, 14, iBlockState4);
      IBlockState blockState = iBlockState;
      this.applyWorld(world, distanceAggregator, 0, 7, 0, 0, 7, 15, blockState, blockState, false);
      this.applyWorld(world, distanceAggregator, 15, 7, 0, 15, 7, 15, blockState, blockState, false);
      this.applyWorld(world, distanceAggregator, 1, 7, 0, 15, 7, 0, blockState, blockState, false);
      this.applyWorld(world, distanceAggregator, 1, 7, 15, 14, 7, 15, blockState, blockState, false);

      for (int index = 1; index <= 6; index++) {
         IBlockState blockState2 = iBlockState;
         if (index == 2 || index == 6) {
            blockState2 = iBlockState4;
         }

         for (int byteValue = 0; byteValue <= 15; byteValue += 15) {
            this.applyWorld(world, distanceAggregator, byteValue, index, 0, byteValue, index, 1, blockState2, blockState2, false);
            this.applyWorld(world, distanceAggregator, byteValue, index, 6, byteValue, index, 9, blockState2, blockState2, false);
            this.applyWorld(world, distanceAggregator, byteValue, index, 14, byteValue, index, 15, blockState2, blockState2, false);
         }

         this.applyWorld(world, distanceAggregator, 1, index, 0, 1, index, 0, blockState2, blockState2, false);
         this.applyWorld(world, distanceAggregator, 6, index, 0, 9, index, 0, blockState2, blockState2, false);
         this.applyWorld(world, distanceAggregator, 14, index, 0, 14, index, 0, blockState2, blockState2, false);
         this.applyWorld(world, distanceAggregator, 1, index, 15, 14, index, 15, blockState2, blockState2, false);
      }

      this.applyWorld(world, distanceAggregator, 6, 3, 6, 9, 6, 9, iBlockState2, iBlockState2, false);
      this.applyWorld(world, distanceAggregator, 7, 4, 7, 8, 5, 8, Blocks.block35.getDefaultState(), Blocks.block35.getDefaultState(), false);

      for (int byteValue2 = 3; byteValue2 <= 6; byteValue2 += 3) {
         for (int byteValue3 = 6; byteValue3 <= 9; byteValue3 += 3) {
            this.applyWorld(world, iBlockState3, byteValue3, byteValue2, 6, distanceAggregator);
            this.applyWorld(world, iBlockState3, byteValue3, byteValue2, 9, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 5, 1, 6, 5, 2, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 1, 9, 5, 2, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 1, 6, 10, 2, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 1, 9, 10, 2, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 1, 5, 6, 2, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 1, 5, 9, 2, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, 1, 10, 6, 2, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 1, 10, 9, 2, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 2, 5, 5, 6, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 2, 10, 5, 6, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 2, 5, 10, 6, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 2, 10, 10, 6, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 7, 1, 5, 7, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 7, 1, 10, 7, 6, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 5, 7, 9, 5, 7, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 10, 7, 9, 10, 7, 14, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 7, 5, 6, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 7, 10, 6, 7, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 7, 5, 14, 7, 5, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 7, 10, 14, 7, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 1, 2, 2, 1, 3, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 1, 2, 3, 1, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 13, 1, 2, 13, 1, 3, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 12, 1, 2, 12, 1, 2, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 2, 1, 12, 2, 1, 13, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 3, 1, 13, 3, 1, 13, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 13, 1, 12, 13, 1, 13, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 12, 1, 13, 12, 1, 13, iBlockState, iBlockState, false);
      return true;
   }

   public GoldenArtifact() {
   }

   public GoldenArtifact(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      super(1, enumFacing, enumFacingController, 2, 2, 2);
   }
}
