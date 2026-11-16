package net.minecraft.item;

import client.setting.MovementFactorProperty;
import client.util.ClassEntry2Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenEssence extends TertiaryItemEntryAdapter {
   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 8, 3, false);
      this.resolveGoldenIngot((ClassEntry2Entry)goldenIngot, list, random, 3, 8, false);
      this.resolveGoldenIngot2((ClassEntry2Entry)goldenIngot, list, random, 3, 8, false);
   }

   public static GoldenEssence resolveGoldenEssence(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -8, -3, 0, 19, 10, 19, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenEssence(count4, random, distanceAggregator, enumFacing) : null;
   }

   public GoldenEssence() {
   }

   public GoldenEssence(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public GoldenEssence(Random random, int count, int count2) {
      super(0);
      this.enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
      switch (TertiaryItemEntryAdapterEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
         case 1:
         case 2:
            this.distanceAggregator = new DistanceAggregator(count, 64, count2, count + 19 - 1, 73, count2 + 19 - 1);
            break;
         default:
            this.distanceAggregator = new DistanceAggregator(count, 64, count2, count + 19 - 1, 73, count2 + 19 - 1);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 7, 3, 0, 11, 4, 18, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 7, 18, 4, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 5, 0, 10, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 8, 18, 7, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 5, 0, 7, 5, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 5, 11, 7, 5, 18, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 11, 5, 0, 11, 5, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 11, 5, 11, 11, 5, 18, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 7, 7, 5, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 11, 5, 7, 18, 5, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 11, 7, 5, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 11, 5, 11, 18, 5, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 2, 0, 11, 2, 5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 2, 13, 11, 2, 18, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 0, 0, 11, 1, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 0, 15, 11, 1, 18, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index = 7; index <= 11; index++) {
         for (int index2 = 0; index2 <= 2; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, index2, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, 18 - index2, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 0, 2, 7, 5, 2, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 13, 2, 7, 18, 2, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 7, 3, 1, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 15, 0, 7, 18, 1, 11, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index3 = 0; index3 <= 2; index3++) {
         for (int index4 = 7; index4 <= 11; index4++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index3, -1, index4, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), 18 - index3, -1, index4, distanceAggregator);
         }
      }

      return true;
   }
}
