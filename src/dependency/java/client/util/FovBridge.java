package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.InternalItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovBridge extends InternalItemEntryAdapter {
   public FovBridge() {
   }

   public static FovBridge resolveFovBridge(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 9, 7, 12, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new FovBridge(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }

   public FovBridge(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

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
      this.applyWorld(world, distanceAggregator, 2, 0, 5, 8, 0, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 1, 7, 0, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 0, 3, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 0, 0, 8, 3, 10, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 7, 2, 0, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 5, 2, 1, 5, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 0, 6, 2, 3, 10, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 0, 10, 7, 3, 10, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 7, 3, 0, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 5, 2, 3, 5, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 1, 8, 4, 1, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 4, 3, 4, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 2, 8, 5, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 0, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 0, 4, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 4, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 4, 4, distanceAggregator);
      int count = this.computeCount(Blocks.block3, 3);
      int count2 = this.computeCount(Blocks.block3, 2);

      for (int index = -1; index <= 2; index++) {
         for (int index2 = 0; index2 <= 8; index2++) {
            this.applyWorld(world, Blocks.block3.processAction(count), index2, 4 + index, index, distanceAggregator);
            if ((index > -1 || index2 <= 1) && (index > 0 || index2 <= 3) && (index > 1 || index2 <= 4 || index2 >= 6)) {
               this.applyWorld(world, Blocks.block3.processAction(count2), index2, 4 + index, 5 - index, distanceAggregator);
            }
         }
      }

      this.applyWorld(world, distanceAggregator, 3, 4, 5, 3, 4, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 4, 2, 7, 4, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 5, 4, 4, 5, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 5, 4, 6, 5, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 6, 3, 5, 6, 10, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      int count3 = this.computeCount(Blocks.block3, 0);

      for (int index3 = 4; index3 >= 1; index3--) {
         this.applyWorld(world, Blocks.block142.getDefaultState(), index3, 2 + index3, 7 - index3, distanceAggregator);

         for (int index4 = 8 - index3; index4 <= 10; index4++) {
            this.applyWorld(world, Blocks.block3.processAction(count3), index3, 2 + index3, index4, distanceAggregator);
         }
      }

      int count4 = this.computeCount(Blocks.block3, 1);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 6, 6, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 7, 5, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block3.processAction(count4), 6, 6, 4, distanceAggregator);

      for (int index5 = 6; index5 <= 8; index5++) {
         for (int index6 = 5; index6 <= 10; index6++) {
            this.applyWorld(world, Blocks.block3.processAction(count4), index5, 12 - index5, index6, distanceAggregator);
         }
      }

      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 4, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 6, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 2, 5, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 8, 2, 8, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 8, 2, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 2, 2, 6, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 7, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 8, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 2, 2, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 4, 4, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 5, 4, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 6, 4, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 5, 5, 10, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 2, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing), 2, 3, 1, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 2, 1, 0, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));
      this.applyWorld(world, distanceAggregator, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      if (this.resolveIBlockState(world, 2, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 2, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 2, 0, -1, distanceAggregator);
      }

      for (int index7 = 0; index7 < 5; index7++) {
         for (int index8 = 0; index8 < 9; index8++) {
            this.applyWorld(world, index8, 7, index7, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index8, -1, index7, distanceAggregator);
         }
      }

      for (int index9 = 5; index9 < 11; index9++) {
         for (int index10 = 2; index10 < 9; index10++) {
            this.applyWorld(world, index10, 7, index9, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index10, -1, index9, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 4, 1, 2, 2);
      return true;
   }
}
