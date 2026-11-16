package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.TertiaryItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovBuffer extends TertiaryItemEntryAdapter {
   public FovBuffer(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public FovBuffer() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 1, 0, true);
   }

   public static FovBuffer resolveFovBuffer(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -7, 0, 5, 14, 10, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new FovBuffer(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      int count = this.computeCount(Blocks.block24, 2);

      for (int index = 0; index <= 9; index++) {
         int count2 = Math.max(1, 7 - index);
         int count3 = Math.min(Math.max(count2 + 5, 14 - index), 13);
         this.applyWorld(world, distanceAggregator, 0, 0, index, 4, count2, index, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 1, count2 + 1, index, 3, count3 - 1, index, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (index <= 6) {
            this.applyWorld(world, Blocks.block24.processAction(count), 1, count2 + 1, index, distanceAggregator);
            this.applyWorld(world, Blocks.block24.processAction(count), 2, count2 + 1, index, distanceAggregator);
            this.applyWorld(world, Blocks.block24.processAction(count), 3, count2 + 1, index, distanceAggregator);
         }

         this.applyWorld(world, distanceAggregator, 0, count3, index, 4, count3, index, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 0, count2 + 1, index, 0, count3 - 1, index, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 4, count2 + 1, index, 4, count3 - 1, index, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         if ((index & 1) == 0) {
            this.applyWorld(world, distanceAggregator, 0, count2 + 2, index, 0, count2 + 3, index, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 4, count2 + 2, index, 4, count2 + 3, index, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         }

         for (int index2 = 0; index2 <= 4; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index2, -1, index, distanceAggregator);
         }
      }

      return true;
   }
}
