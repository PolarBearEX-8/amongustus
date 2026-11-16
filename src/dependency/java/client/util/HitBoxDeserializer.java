package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenHammer;
import net.minecraft.item.GoldenIngot;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxDeserializer extends GoldenHammer {
   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.EAST) {
         this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
      } else {
         this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 4, 4, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 1, 0);
         if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.EAST) {
            this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         } else {
            this.applyWorld(world, distanceAggregator, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }
}
