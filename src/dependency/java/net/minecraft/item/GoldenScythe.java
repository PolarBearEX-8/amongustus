package net.minecraft.item;

import client.util.ClassEntry2Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenScythe extends TertiaryItemEntryAdapter {
   public static GoldenScythe resolveGoldenScythe(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -3, 0, 0, 9, 7, 9, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenScythe(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 8, 1, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 8, 5, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 6, 0, 8, 6, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 2, 5, 0, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 2, 0, 8, 5, 0, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 3, 0, 1, 4, 0, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 3, 0, 7, 4, 0, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 4, 8, 2, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 4, 2, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 1, 4, 7, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 8, 8, 3, 8, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 6, 0, 3, 7, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 3, 6, 8, 3, 7, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 4, 0, 5, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 3, 4, 8, 5, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 3, 5, 2, 5, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 3, 5, 7, 5, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 4, 5, 1, 5, 5, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 4, 5, 7, 5, 5, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);

      for (int index = 0; index <= 5; index++) {
         for (int index2 = 0; index2 <= 8; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index2, -1, index, distanceAggregator);
         }
      }

      return true;
   }

   public GoldenScythe() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      byte byteValue = 1;
      if (this.enumFacing == EnumFacing.WEST || this.enumFacing == EnumFacing.NORTH) {
         byteValue = 5;
      }

      this.resolveGoldenIngot((ClassEntry2Entry)goldenIngot, list, random, 0, byteValue, random.nextInt(8) > 0);
      this.resolveGoldenIngot2((ClassEntry2Entry)goldenIngot, list, random, 0, byteValue, random.nextInt(8) > 0);
   }

   public GoldenScythe(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }
}
