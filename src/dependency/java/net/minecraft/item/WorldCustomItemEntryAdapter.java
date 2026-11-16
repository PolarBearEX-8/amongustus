package net.minecraft.item;

import client.util.DistanceAggregator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldCustomItemEntryAdapter extends CustomItemEntryAdapter {
   public int count6;

   public WorldCustomItemEntryAdapter(EnumFacing enumFacing, DistanceAggregator distanceAggregator, int count) {
      super(enumFacing, distanceAggregator);
      this.count6 = count & 1;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count6 == 0) {
         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, 10 - index, 3 - index, 20 - index, 12 + index, 3 - index, 20, iBlockState, iBlockState, false);
         }

         this.applyWorld(world, distanceAggregator, 7, 0, 6, 15, 0, 16, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 6, 0, 6, 6, 3, 20, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 16, 0, 6, 16, 3, 20, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 7, 7, 1, 20, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 15, 1, 7, 15, 1, 20, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 6, 9, 3, 6, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 13, 1, 6, 15, 3, 6, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 8, 1, 7, 9, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 13, 1, 7, 14, 1, 7, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 9, 0, 5, 13, 0, 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 10, 0, 7, 12, 0, 7, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 8, 0, 10, 8, 0, 12, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 14, 0, 10, 14, 0, 12, iBlockState2, iBlockState2, false);

         for (int byteValue = 18; byteValue >= 7; byteValue -= 3) {
            this.applyWorld(world, iBlockState3, 6, 3, byteValue, distanceAggregator);
            this.applyWorld(world, iBlockState3, 16, 3, byteValue, distanceAggregator);
         }

         this.applyWorld(world, iBlockState3, 10, 0, 10, distanceAggregator);
         this.applyWorld(world, iBlockState3, 12, 0, 10, distanceAggregator);
         this.applyWorld(world, iBlockState3, 10, 0, 12, distanceAggregator);
         this.applyWorld(world, iBlockState3, 12, 0, 12, distanceAggregator);
         this.applyWorld(world, iBlockState3, 8, 3, 6, distanceAggregator);
         this.applyWorld(world, iBlockState3, 14, 3, 6, distanceAggregator);
         this.applyWorld(world, iBlockState, 4, 2, 4, distanceAggregator);
         this.applyWorld(world, iBlockState3, 4, 1, 4, distanceAggregator);
         this.applyWorld(world, iBlockState, 4, 0, 4, distanceAggregator);
         this.applyWorld(world, iBlockState, 18, 2, 4, distanceAggregator);
         this.applyWorld(world, iBlockState3, 18, 1, 4, distanceAggregator);
         this.applyWorld(world, iBlockState, 18, 0, 4, distanceAggregator);
         this.applyWorld(world, iBlockState, 4, 2, 18, distanceAggregator);
         this.applyWorld(world, iBlockState3, 4, 1, 18, distanceAggregator);
         this.applyWorld(world, iBlockState, 4, 0, 18, distanceAggregator);
         this.applyWorld(world, iBlockState, 18, 2, 18, distanceAggregator);
         this.applyWorld(world, iBlockState3, 18, 1, 18, distanceAggregator);
         this.applyWorld(world, iBlockState, 18, 0, 18, distanceAggregator);
         this.applyWorld(world, iBlockState, 9, 7, 20, distanceAggregator);
         this.applyWorld(world, iBlockState, 13, 7, 20, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 6, 0, 21, 7, 4, 21, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 15, 0, 21, 16, 4, 21, iBlockState, iBlockState, false);
         this.matchesWorld(world, distanceAggregator, 11, 2, 16);
      } else if (this.count6 == 1) {
         this.applyWorld(world, distanceAggregator, 9, 3, 18, 13, 3, 20, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 9, 0, 18, 9, 2, 18, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 13, 0, 18, 13, 2, 18, iBlockState, iBlockState, false);
         byte byteValue2 = 9;

         for (int index2 = 0; index2 < 2; index2++) {
            this.applyWorld(world, iBlockState, byteValue2, 6, 20, distanceAggregator);
            this.applyWorld(world, iBlockState3, byteValue2, 5, 20, distanceAggregator);
            this.applyWorld(world, iBlockState, byteValue2, 4, 20, distanceAggregator);
            byteValue2 = 13;
         }

         this.applyWorld(world, distanceAggregator, 7, 3, 7, 15, 3, 14, iBlockState, iBlockState, false);
         byte byteValue3 = 10;

         for (int index3 = 0; index3 < 2; index3++) {
            this.applyWorld(world, distanceAggregator, byteValue3, 0, 10, byteValue3, 6, 10, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue3, 0, 12, byteValue3, 6, 12, iBlockState, iBlockState, false);
            this.applyWorld(world, iBlockState3, byteValue3, 0, 10, distanceAggregator);
            this.applyWorld(world, iBlockState3, byteValue3, 0, 12, distanceAggregator);
            this.applyWorld(world, iBlockState3, byteValue3, 4, 10, distanceAggregator);
            this.applyWorld(world, iBlockState3, byteValue3, 4, 12, distanceAggregator);
            byteValue3 = 12;
         }

         byte byteValue4 = 8;

         for (int index4 = 0; index4 < 2; index4++) {
            this.applyWorld(world, distanceAggregator, byteValue4, 0, 7, byteValue4, 2, 7, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, byteValue4, 0, 14, byteValue4, 2, 14, iBlockState, iBlockState, false);
            byteValue4 = 14;
         }

         this.applyWorld(world, distanceAggregator, 8, 3, 8, 8, 3, 13, iBlockState2, iBlockState2, false);
         this.applyWorld(world, distanceAggregator, 14, 3, 8, 14, 3, 13, iBlockState2, iBlockState2, false);
         this.matchesWorld(world, distanceAggregator, 11, 5, 13);
      }

      return true;
   }

   public WorldCustomItemEntryAdapter() {
   }
}
