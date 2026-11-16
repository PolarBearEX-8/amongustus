package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.ExtendedItemEntryAdapter;
import net.minecraft.item.GoldenIngot;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxEvaluator extends ExtendedItemEntryAdapter {
   public static HitBoxEvaluator resolveHitBoxEvaluator(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -1, 0, 9, 5, 11, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new HitBoxEvaluator(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 8, 4, 10, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 1, 0);
         this.applyWorld(world, distanceAggregator, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 4, 1, 1, 4, 3, 1, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 4, 1, 3, 4, 3, 3, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 4, 1, 7, 4, 3, 7, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 4, 1, 9, 4, 3, 9, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 4, 1, 4, 4, 3, 6, Blocks.block127.getDefaultState(), Blocks.block127.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 1, 5, 7, 3, 5, Blocks.block127.getDefaultState(), Blocks.block127.getDefaultState(), false);
         this.applyWorld(world, Blocks.block127.getDefaultState(), 4, 3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block127.getDefaultState(), 4, 3, 8, distanceAggregator);
         this.applyWorld(world, Blocks.ironDoor.processAction(this.computeCount(Blocks.ironDoor, 3)), 4, 1, 2, distanceAggregator);
         this.applyWorld(world, Blocks.ironDoor.processAction(this.computeCount(Blocks.ironDoor, 3) + 8), 4, 2, 2, distanceAggregator);
         this.applyWorld(world, Blocks.ironDoor.processAction(this.computeCount(Blocks.ironDoor, 3)), 4, 1, 8, distanceAggregator);
         this.applyWorld(world, Blocks.ironDoor.processAction(this.computeCount(Blocks.ironDoor, 3) + 8), 4, 2, 8, distanceAggregator);
         return true;
      }
   }

   public HitBoxEvaluator() {
   }

   public HitBoxEvaluator(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
   }
}
