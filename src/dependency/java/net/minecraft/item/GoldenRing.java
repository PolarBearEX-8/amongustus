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

public class GoldenRing extends InternalItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 7 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 0, 6, 8, 0, 10, Blocks.block115.getDefaultState(), Blocks.block115.getDefaultState(), false);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 6, 0, 6, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 2, 1, 6, 2, 1, 10, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 1, 6, 8, 1, 10, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 10, 7, 1, 10, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 1, 7, 0, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 0, 3, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 0, 0, 8, 3, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 7, 1, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 5, 7, 1, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 7, 3, 0, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 5, 7, 3, 5, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 1, 8, 4, 1, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 4, 8, 4, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 2, 8, 5, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 0, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 0, 4, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 4, 3, distanceAggregator);
      int count = this.computeCount(Blocks.block3, 3);
      int count2 = this.computeCount(Blocks.block3, 2);

      for (int index = -1; index <= 2; index++) {
         for (int index2 = 0; index2 <= 8; index2++) {
            this.applyWorld(world, Blocks.block3.processAction(count), index2, 4 + index, index, distanceAggregator);
            this.applyWorld(world, Blocks.block3.processAction(count2), index2, 4 + index, 5 - index, distanceAggregator);
         }
      }

      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 3, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 6, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 2, 1, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block94.getDefaultState(), 2, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 1, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(this.computeCount(Blocks.block3, 3)), 2, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(this.computeCount(Blocks.block3, 1)), 1, 1, 3, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 5, 0, 1, 7, 0, 3, Blocks.extraBlockAdapter5.getDefaultState(), Blocks.extraBlockAdapter5.getDefaultState(), false);
      this.applyWorld(world, Blocks.extraBlockAdapter5.getDefaultState(), 6, 1, 1, distanceAggregator);
      this.applyWorld(world, Blocks.extraBlockAdapter5.getDefaultState(), 6, 1, 2, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing), 2, 3, 1, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 2, 1, 0, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));
      if (this.resolveIBlockState(world, 2, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 2, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 2, 0, -1, distanceAggregator);
      }

      this.applyWorld(world, Blocks.air.getDefaultState(), 6, 1, 5, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 6, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacing5()), 6, 3, 4, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 6, 1, 5, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));

      for (int index3 = 0; index3 < 5; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.applyWorld(world, index4, 7, index3, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index4, -1, index3, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 4, 1, 2, 2);
      return true;
   }

   @Override
   public int computeCount2(int count, int count2) {
      return count == 0 ? 4 : super.computeCount2(count, count2);
   }

   public GoldenRing() {
   }

   public GoldenRing(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public static GoldenRing resolveGoldenRing(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 9, 7, 11, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenRing(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }
}
