package net.minecraft.item;

import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenBlade extends ExtendedItemEntryAdapter {
   public GoldenBlade() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
   }

   public static GoldenBlade resolveGoldenBlade(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -7, 0, 5, 11, 8, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenBlade(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 10, 7, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 7, 0);
         this.applyWorld(world, random, distanceAggregator, IRONDOOREnum.OPENING, 1, 1, 7);
         int count = this.computeCount(Blocks.block125, 2);

         for (int index = 0; index < 6; index++) {
            this.applyWorld(world, Blocks.block125.processAction(count), 1, 6 - index, 1 + index, distanceAggregator);
            this.applyWorld(world, Blocks.block125.processAction(count), 2, 6 - index, 1 + index, distanceAggregator);
            this.applyWorld(world, Blocks.block125.processAction(count), 3, 6 - index, 1 + index, distanceAggregator);
            if (index < 5) {
               this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 5 - index, 1 + index, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 5 - index, 1 + index, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 5 - index, 1 + index, distanceAggregator);
            }
         }

         return true;
      }
   }

   public GoldenBlade(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
   }
}
