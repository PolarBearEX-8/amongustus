package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.TertiaryItemEntryAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxReader extends TertiaryItemEntryAdapter {
   public HitBoxReader(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public HitBoxReader() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 5, 3, true);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 3, 0, 12, 4, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 0, 1, 12, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 11, 5, 0, 12, 12, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 11, 4, 12, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 5, 11, 10, 12, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 9, 11, 7, 12, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 0, 4, 12, 1, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 5, 0, 10, 12, 1, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 9, 0, 7, 12, 1, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 11, 2, 10, 12, 10, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 8, 0, 7, 8, 0, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);

      for (int byteValue = 1; byteValue <= 11; byteValue += 2) {
         this.applyWorld(world, distanceAggregator, byteValue, 10, 0, byteValue, 11, 0, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, byteValue, 10, 12, byteValue, 11, 12, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 0, 10, byteValue, 0, 11, byteValue, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 12, 10, byteValue, 12, 11, byteValue, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         this.applyWorld(world, Blocks.block86.getDefaultState(), byteValue, 13, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block86.getDefaultState(), byteValue, 13, 12, distanceAggregator);
         this.applyWorld(world, Blocks.block86.getDefaultState(), 0, 13, byteValue, distanceAggregator);
         this.applyWorld(world, Blocks.block86.getDefaultState(), 12, 13, byteValue, distanceAggregator);
         this.applyWorld(world, Blocks.block134.getDefaultState(), byteValue + 1, 13, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block134.getDefaultState(), byteValue + 1, 13, 12, distanceAggregator);
         this.applyWorld(world, Blocks.block134.getDefaultState(), 0, 13, byteValue + 1, distanceAggregator);
         this.applyWorld(world, Blocks.block134.getDefaultState(), 12, 13, byteValue + 1, distanceAggregator);
      }

      this.applyWorld(world, Blocks.block134.getDefaultState(), 0, 13, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block134.getDefaultState(), 0, 13, 12, distanceAggregator);
      this.applyWorld(world, Blocks.block134.getDefaultState(), 0, 13, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block134.getDefaultState(), 12, 13, 0, distanceAggregator);

      for (int byteValue2 = 3; byteValue2 <= 9; byteValue2 += 2) {
         this.applyWorld(world, distanceAggregator, 1, 7, byteValue2, 1, 8, byteValue2, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 11, 7, byteValue2, 11, 8, byteValue2, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      }

      this.applyWorld(world, distanceAggregator, 4, 2, 0, 8, 2, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 4, 12, 2, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 0, 8, 1, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 9, 8, 1, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 4, 3, 1, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 0, 4, 12, 1, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index = 4; index <= 8; index++) {
         for (int index2 = 0; index2 <= 2; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, index2, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, 12 - index2, distanceAggregator);
         }
      }

      for (int index3 = 0; index3 <= 2; index3++) {
         for (int index4 = 4; index4 <= 8; index4++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index3, -1, index4, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), 12 - index3, -1, index4, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 5, 5, 5, 7, 5, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 1, 6, 6, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block86.getDefaultState(), 6, 0, 6, distanceAggregator);
      this.applyWorld(world, Blocks.blockFlowingLava2.getDefaultState(), 6, 5, 6, distanceAggregator);
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(6, 6), this.computeCount(5), this.computeCount(6, 6));
      if (distanceAggregator.matchesVec3i(blockPos)) {
         world.applyBlock(Blocks.blockFlowingLava2, blockPos, random);
      }

      return true;
   }

   public static HitBoxReader resolveHitBoxReader(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -5, -3, 0, 13, 14, 13, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new HitBoxReader(count4, random, distanceAggregator, enumFacing) : null;
   }
}
