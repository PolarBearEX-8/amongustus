package client.util;

import java.util.Random;
import net.minecraft.item.CustomItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxCalculator extends CustomItemEntryAdapter {
   public HitBoxCalculator() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 2, -1, 2, 11, -1, 11, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 0, -1, 0, 1, -1, 11, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 12, -1, 0, 13, -1, 11, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 2, -1, 0, 11, -1, 1, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 2, -1, 12, 11, -1, 13, iBlockState4, iBlockState4, false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 0, 0, 13, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 13, 0, 0, 13, 0, 13, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 12, 0, 0, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 1, 0, 13, 12, 0, 13, iBlockState, iBlockState, false);

      for (int byteValue = 2; byteValue <= 11; byteValue += 3) {
         this.applyWorld(world, iBlockState3, 0, 0, byteValue, distanceAggregator);
         this.applyWorld(world, iBlockState3, 13, 0, byteValue, distanceAggregator);
         this.applyWorld(world, iBlockState3, byteValue, 0, 0, distanceAggregator);
      }

      this.applyWorld(world, distanceAggregator, 2, 0, 3, 4, 0, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 9, 0, 3, 11, 0, 9, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 4, 0, 9, 9, 0, 11, iBlockState, iBlockState, false);
      this.applyWorld(world, iBlockState, 5, 0, 8, distanceAggregator);
      this.applyWorld(world, iBlockState, 8, 0, 8, distanceAggregator);
      this.applyWorld(world, iBlockState, 10, 0, 10, distanceAggregator);
      this.applyWorld(world, iBlockState, 3, 0, 10, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 3, 0, 3, 3, 0, 7, iBlockState2, iBlockState2, false);
      this.applyWorld(world, distanceAggregator, 10, 0, 3, 10, 0, 7, iBlockState2, iBlockState2, false);
      this.applyWorld(world, distanceAggregator, 6, 0, 10, 7, 0, 10, iBlockState2, iBlockState2, false);
      byte byteValue2 = 3;

      for (int index = 0; index < 2; index++) {
         for (int byteValue3 = 2; byteValue3 <= 8; byteValue3 += 3) {
            this.applyWorld(world, distanceAggregator, byteValue2, 0, byteValue3, byteValue2, 2, byteValue3, iBlockState, iBlockState, false);
         }

         byteValue2 = 10;
      }

      this.applyWorld(world, distanceAggregator, 5, 0, 10, 5, 2, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 8, 0, 10, 8, 2, 10, iBlockState, iBlockState, false);
      this.applyWorld(world, distanceAggregator, 6, -1, 7, 7, -1, 8, iBlockState2, iBlockState2, false);
      this.applyWorld(world, distanceAggregator, 6, -1, 3, 7, -1, 4, false);
      this.matchesWorld(world, distanceAggregator, 6, 1, 6);
      return true;
   }

   public HitBoxCalculator(EnumFacing enumFacing, DistanceAggregator distanceAggregator) {
      super(enumFacing, distanceAggregator);
   }
}
