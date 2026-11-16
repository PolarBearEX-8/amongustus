package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenRod extends InternalItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 12 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 1, 3, 3, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 1, 3, 9, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 3, 0, 8, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 3, 10, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 10, 3, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 1, 1, 4, 10, 3, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 4, 0, 4, 7, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 4, 4, 4, 7, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 8, 3, 4, 8, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 4, 3, 10, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 5, 3, 5, 7, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 9, 0, 4, 9, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 0, 4, 4, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 11, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 11, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 2, 11, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 2, 11, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 1, 1, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 1, 1, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 2, 1, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 3, 1, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 3, 1, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 1, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 2, 1, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 3, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 1)), 1, 2, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 0)), 3, 2, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 3, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 3, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 6, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 7, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 6, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 7, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 6, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 7, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 6, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 7, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 3, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 3, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 3, 8, distanceAggregator);
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacing5()), 2, 4, 7, distanceAggregator);
      this.applyWorld(
         world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacingAsEnumFacing()), 1, 4, 6, distanceAggregator
      );
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacing2()), 3, 4, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing), 2, 4, 5, distanceAggregator);
      int count = this.computeCount(Blocks.block81, 4);

      for (int index = 1; index <= 9; index++) {
         this.applyWorld(world, Blocks.block81.processAction(count), 3, index, 3, distanceAggregator);
      }

      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 2, 0, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 2, 1, 0, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));
      if (this.resolveIBlockState(world, 2, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 2, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 2, 0, -1, distanceAggregator);
      }

      for (int index2 = 0; index2 < 9; index2++) {
         for (int index3 = 0; index3 < 5; index3++) {
            this.applyWorld(world, index3, 12, index2, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index3, -1, index2, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 2, 1, 2, 1);
      return true;
   }

   @Override
   public int computeCount2(int count, int count2) {
      return 2;
   }

   public GoldenRod(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public GoldenRod() {
   }

   public static GoldenRod resolveGoldenRod(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 5, 12, 9, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenRod(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }
}
