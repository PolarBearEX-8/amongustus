package net.minecraft.item;

import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class IronShovel extends ExtendedItemEntryAdapter {
   public int count2;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Steps", this.count2);
   }

   public IronShovel() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         for (int index = 0; index < this.count2; index++) {
            this.applyWorld(world, Blocks.block42.getDefaultState(), 0, 0, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 0, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 0, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 0, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 4, 0, index, distanceAggregator);

            for (int index2 = 1; index2 <= 3; index2++) {
               this.applyWorld(world, Blocks.block42.getDefaultState(), 0, index2, index, distanceAggregator);
               this.applyWorld(world, Blocks.air.getDefaultState(), 1, index2, index, distanceAggregator);
               this.applyWorld(world, Blocks.air.getDefaultState(), 2, index2, index, distanceAggregator);
               this.applyWorld(world, Blocks.air.getDefaultState(), 3, index2, index, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 4, index2, index, distanceAggregator);
            }

            this.applyWorld(world, Blocks.block42.getDefaultState(), 0, 4, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 4, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 4, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 4, index, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), 4, 4, index, distanceAggregator);
         }

         return true;
      }
   }

   public IronShovel(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.count2 = enumFacing != EnumFacing.NORTH && enumFacing != EnumFacing.SOUTH ? distanceAggregator.getCountAsIndex() : distanceAggregator.getCount();
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeCount("Steps");
   }

   public static DistanceAggregator resolveDistanceAggregator(List list, Random random, int count, int count3, int count4, EnumFacing enumFacing) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count3, count4, -1, -1, 0, 5, 5, 4, enumFacing);
      GoldenIngot goldenIngot = GoldenIngot.resolveGoldenIngot(list, distanceAggregator);
      if (goldenIngot == null) {
         return null;
      } else {
         if (goldenIngot.getDistanceAggregator().count6 == distanceAggregator.count6) {
            for (int index = 3; index >= 1; index--) {
               DistanceAggregator distanceAggregator2 = DistanceAggregator.resolveDistanceAggregator(count, count3, count4, -1, -1, 0, 5, 5, index - 1, enumFacing);
               if (!goldenIngot.getDistanceAggregator().matchesDistanceAggregator(distanceAggregator2)) {
                  return DistanceAggregator.resolveDistanceAggregator(count, count3, count4, -1, -1, 0, 5, 5, index, enumFacing);
               }
            }
         }

         return null;
      }
   }
}
