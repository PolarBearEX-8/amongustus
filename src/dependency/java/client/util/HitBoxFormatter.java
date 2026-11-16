package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.TertiaryItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxFormatter extends TertiaryItemEntryAdapter {
   public HitBoxFormatter(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public static HitBoxFormatter resolveHitBoxFormatter(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -5, -3, 0, 13, 14, 13, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new HitBoxFormatter(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 5, 3, true);
      this.resolveGoldenIngotByClassEntry2Entry((ClassEntry2Entry)goldenIngot, list, random, 5, 11, true);
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

      int count = this.computeCount(Blocks.block24, 3);

      for (int index = 0; index <= 6; index++) {
         int count2 = index + 4;

         for (int index2 = 5; index2 <= 7; index2++) {
            this.applyWorld(world, Blocks.block24.processAction(count), index2, 5 + index, count2, distanceAggregator);
         }

         if (count2 >= 5 && count2 <= 8) {
            this.applyWorld(world, distanceAggregator, 5, 5, count2, 7, index + 4, count2, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         } else if (count2 >= 9 && count2 <= 10) {
            this.applyWorld(world, distanceAggregator, 5, 8, count2, 7, index + 4, count2, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         }

         if (index >= 1) {
            this.applyWorld(world, distanceAggregator, 5, 6 + index, count2, 7, 9 + index, count2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }
      }

      for (int index3 = 5; index3 <= 7; index3++) {
         this.applyWorld(world, Blocks.block24.processAction(count), index3, 12, 11, distanceAggregator);
      }

      this.applyWorld(world, distanceAggregator, 5, 6, 7, 5, 7, 7, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 6, 7, 7, 7, 7, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 13, 12, 7, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 2, 3, 5, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 9, 3, 5, 10, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 5, 4, 2, 5, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 5, 2, 10, 5, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 5, 9, 10, 5, 10, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 10, 5, 4, 10, 5, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      int count3 = this.computeCount(Blocks.block24, 0);
      int count4 = this.computeCount(Blocks.block24, 1);
      this.applyWorld(world, Blocks.block24.processAction(count4), 4, 5, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count4), 4, 5, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count4), 4, 5, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count4), 4, 5, 10, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count3), 8, 5, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count3), 8, 5, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count3), 8, 5, 9, distanceAggregator);
      this.applyWorld(world, Blocks.block24.processAction(count3), 8, 5, 10, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 3, 4, 4, 4, 4, 8, Blocks.block126.getDefaultState(), Blocks.block126.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 4, 4, 9, 4, 8, Blocks.block126.getDefaultState(), Blocks.block126.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 5, 4, 4, 5, 8, Blocks.block78.getDefaultState(), Blocks.block78.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 8, 5, 4, 9, 5, 8, Blocks.block78.getDefaultState(), Blocks.block78.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 2, 0, 8, 2, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 2, 4, 12, 2, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 0, 8, 1, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 9, 8, 1, 12, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 4, 3, 1, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 0, 4, 12, 1, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index4 = 4; index4 <= 8; index4++) {
         for (int index5 = 0; index5 <= 2; index5++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index4, -1, index5, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index4, -1, 12 - index5, distanceAggregator);
         }
      }

      for (int index6 = 0; index6 <= 2; index6++) {
         for (int index7 = 4; index7 <= 8; index7++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index6, -1, index7, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), 12 - index6, -1, index7, distanceAggregator);
         }
      }

      return true;
   }

   public HitBoxFormatter() {
   }
}
