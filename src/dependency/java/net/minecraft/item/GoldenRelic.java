package net.minecraft.item;

import client.util.ClassEntry2Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenRelic extends TertiaryItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 6, 1, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 6, 7, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 1, 6, 0, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 6, 1, 6, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 2, 0, 6, 6, 0, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 2, 6, 6, 6, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 6, 1, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 5, 0, 6, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 2, 0, 6, 6, 1, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 2, 5, 6, 6, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 6, 0, 4, 6, 0, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 0, 4, 5, 0, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 6, 6, 4, 6, 6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 6, 4, 5, 6, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 6, 2, 0, 6, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 2, 0, 5, 4, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 6, 2, 6, 6, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 5, 2, 6, 5, 4, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);

      for (int index = 0; index <= 6; index++) {
         for (int index2 = 0; index2 <= 6; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, index2, distanceAggregator);
         }
      }

      return true;
   }

   public GoldenRelic() {
   }

   public static GoldenRelic resolveGoldenRelic(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -2, 0, 0, 7, 9, 7, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenRelic(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 2, 0, false);
      this.resolveGoldenIngot((ClassEntry2Entry)goldenIngot, list, random, 0, 2, false);
      this.resolveGoldenIngot2((ClassEntry2Entry)goldenIngot, list, random, 0, 2, false);
   }

   public GoldenRelic(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }
}
