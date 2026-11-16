package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.TertiaryItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovRegistry extends TertiaryItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 1, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 0, 5, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 2, 0, 4, 5, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 1, 0, 4, 1, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 3, 3, 0, 4, 3, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 3, 1, 4, 4, 1, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 3, 3, 4, 4, 3, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 6, 0, 4, 6, 4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index = 0; index <= 4; index++) {
         for (int index2 = 0; index2 <= 4; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, index2, distanceAggregator);
         }
      }

      return true;
   }

   public FovRegistry(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public static FovRegistry resolveFovRegistry(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, 0, 0, 5, 7, 5, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new FovRegistry(count4, random, distanceAggregator, enumFacing) : null;
   }

   public FovRegistry() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 1, 0, true);
   }
}
