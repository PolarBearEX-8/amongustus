package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenWeapon extends InternalItemEntryAdapter {
   public static GoldenWeapon resolveGoldenWeapon(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 9, 9, 6, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenWeapon(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }

   public GoldenWeapon(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   @Override
   public int computeCount2(int count, int count2) {
      return 1;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 9 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 1, 7, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 8, 0, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 0, 8, 5, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 6, 1, 8, 6, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 7, 2, 8, 7, 3, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      int count = this.computeCount(Blocks.block3, 3);
      int count2 = this.computeCount(Blocks.block3, 2);

      for (int index = -1; index <= 2; index++) {
         for (int index2 = 0; index2 <= 8; index2++) {
            this.applyWorld(world, Blocks.block3.processAction(count), index2, 6 + index, index, distanceAggregator);
            this.applyWorld(world, Blocks.block3.processAction(count2), index2, 6 + index, 5 - index, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 1, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 5, 8, 1, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 1, 0, 8, 1, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 1, 0, 7, 1, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 4, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 5, 0, 4, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 2, 5, 8, 4, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 2, 0, 8, 4, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 1, 0, 4, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 5, 7, 4, 5, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 2, 1, 8, 4, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 7, 4, 0, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 6, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 6, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 3, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 3, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 3, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 3, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 3, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 6, 2, 5, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 1, 4, 1, 7, 4, 1, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 4, 4, 7, 4, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 3, 4, 7, 3, 4, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 7, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(this.computeCount(Blocks.block3, 0)), 7, 1, 3, distanceAggregator);
      int count3 = this.computeCount(Blocks.block3, 3);
      this.applyWorld(world, Blocks.block3.processAction(count3), 6, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(count3), 5, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(count3), 4, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(count3), 3, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 1, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block94.getDefaultState(), 6, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 1, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block94.getDefaultState(), 4, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block46.getDefaultState(), 7, 1, 1, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 1, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 1, 2, 0, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 1, 1, 0, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));
      if (this.resolveIBlockState(world, 1, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 1, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 1, 0, -1, distanceAggregator);
      }

      for (int index3 = 0; index3 < 6; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.applyWorld(world, index4, 9, index3, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index4, -1, index3, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 2, 1, 2, 1);
      return true;
   }

   public GoldenWeapon() {
   }
}
