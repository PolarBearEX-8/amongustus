package net.minecraft.item;

import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenHammer extends ExtendedItemEntryAdapter {
   public GoldenHammer() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.EAST) {
         this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
      } else {
         this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 4, 4, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 1, 0);
         if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.EAST) {
            this.applyWorld(world, distanceAggregator, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         } else {
            this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }

   public GoldenHammer(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
   }

   public static GoldenHammer resolveGoldenHammer(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -1, 0, 5, 5, 5, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenHammer(count4, random, distanceAggregator, enumFacing) : null;
   }
}
