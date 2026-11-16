package net.minecraft.item;

import client.setting.MovementFactorProperty;
import client.util.AngleService;
import client.util.DistanceAggregator;
import client.util.HitBoxTransformerEnumFacingSwitchMap;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenTalisman extends InternalItemEntryAdapter {
   public GoldenTalisman() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
         (InvalidBiomeIdManagerEntry)goldenIngot,
         list,
         random,
         this.distanceAggregator.count3 - 1,
         this.distanceAggregator.count2 - 4,
         this.distanceAggregator.count4 + 1,
         EnumFacing.WEST,
         this.getCount()
      );
      AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
         (InvalidBiomeIdManagerEntry)goldenIngot,
         list,
         random,
         this.distanceAggregator.count5 + 1,
         this.distanceAggregator.count2 - 4,
         this.distanceAggregator.count4 + 1,
         EnumFacing.EAST,
         this.getCount()
      );
      AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
         (InvalidBiomeIdManagerEntry)goldenIngot,
         list,
         random,
         this.distanceAggregator.count3 + 1,
         this.distanceAggregator.count2 - 4,
         this.distanceAggregator.count4 - 1,
         EnumFacing.NORTH,
         this.getCount()
      );
      AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
         (InvalidBiomeIdManagerEntry)goldenIngot,
         list,
         random,
         this.distanceAggregator.count3 + 1,
         this.distanceAggregator.count2 - 4,
         this.distanceAggregator.count + 1,
         EnumFacing.SOUTH,
         this.getCount()
      );
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 3, 0);
      }

      this.applyWorld(world, distanceAggregator, 1, 0, 1, 4, 12, 4, Blocks.block65.getDefaultState(), Blocks.blockFlowingLava.getDefaultState(), false);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 12, 2, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 3, 12, 2, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 12, 3, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 3, 12, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 13, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 14, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 13, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 14, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 13, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 14, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 13, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 14, 4, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 1, 15, 1, 4, 15, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);

      for (int index = 0; index <= 5; index++) {
         for (int index2 = 0; index2 <= 5; index2++) {
            if (index2 == 0 || index2 == 5 || index == 0 || index == 5) {
               this.applyWorld(world, Blocks.block45.getDefaultState(), index2, 11, index, distanceAggregator);
               this.applyWorld(world, index2, 12, index, distanceAggregator);
            }
         }
      }

      return true;
   }

   public GoldenTalisman(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, int count2, int count3) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
      switch (HitBoxTransformerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
         case 1:
         case 2:
            this.distanceAggregator = new DistanceAggregator(count2, 64, count3, count2 + 6 - 1, 78, count3 + 6 - 1);
            break;
         default:
            this.distanceAggregator = new DistanceAggregator(count2, 64, count3, count2 + 6 - 1, 78, count3 + 6 - 1);
      }
   }
}
