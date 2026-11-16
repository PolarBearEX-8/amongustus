package net.minecraft.item;

import client.setting.LocalOptionType;
import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenArmor extends InternalItemEntryAdapter {
   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 4 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 0, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.wool.processAction(LocalOptionType.WHITE.getCount2()), 1, 3, 0, distanceAggregator);
      boolean flag = this.enumFacing == EnumFacing.EAST || this.enumFacing == EnumFacing.NORTH;
      this.applyWorld(
         world,
         Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacingAsEnumFacing()),
         flag ? 2 : 0,
         3,
         0,
         distanceAggregator
      );
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing), 1, 3, 1, distanceAggregator);
      this.applyWorld(
         world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacing2()), flag ? 0 : 2, 3, 0, distanceAggregator
      );
      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing.getEnumFacing5()), 1, 3, -1, distanceAggregator);
      return true;
   }

   public GoldenArmor(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public GoldenArmor() {
   }

   public static DistanceAggregator resolveDistanceAggregator(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing
   ) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 3, 4, 2, enumFacing);
      return GoldenIngot.resolveGoldenIngot(list, distanceAggregator) != null ? null : distanceAggregator;
   }
}
