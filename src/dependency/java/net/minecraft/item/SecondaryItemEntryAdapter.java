package net.minecraft.item;

import client.util.CommonIntArrayEntry;
import client.util.DistanceAggregator;
import client.util.GlobalListEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class SecondaryItemEntryAdapter extends GoldenIngot {
   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public static DistanceAggregator resolveDistanceAggregator(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing) {
      DistanceAggregator distanceAggregator = new DistanceAggregator(count, count2 - 5, count3, count, count2 + 2, count3);
      switch (CommonIntArrayEntry.countArray[enumFacing.ordinal()]) {
         case 1:
            distanceAggregator.count5 = count + 2;
            distanceAggregator.count4 = count3 - 8;
            break;
         case 2:
            distanceAggregator.count5 = count + 2;
            distanceAggregator.count = count3 + 8;
            break;
         case 3:
            distanceAggregator.count3 = count - 8;
            distanceAggregator.count = count3 + 2;
            break;
         case 4:
            distanceAggregator.count5 = count + 8;
            distanceAggregator.count = count3 + 2;
      }

      return GoldenIngot.resolveGoldenIngot(list, distanceAggregator) != null ? null : distanceAggregator;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 5, 0, 2, 7, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 0, 0, 7, 2, 2, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

         for (int index = 0; index < 5; index++) {
            this.applyWorld(
               world, distanceAggregator, 0, 5 - index - (index < 4 ? 1 : 0), 2 + index, 2, 7 - index, 2 + index, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false
            );
         }

         return true;
      }
   }

   public SecondaryItemEntryAdapter() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      int count = this.getCount();
      if (this.enumFacing != null) {
         switch (CommonIntArrayEntry.countArray[this.enumFacing.ordinal()]) {
            case 1:
               GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                  goldenIngot, list, random, this.distanceAggregator.count3, this.distanceAggregator.count6, this.distanceAggregator.count4 - 1, EnumFacing.NORTH, count
               );
               break;
            case 2:
               GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                  goldenIngot, list, random, this.distanceAggregator.count3, this.distanceAggregator.count6, this.distanceAggregator.count + 1, EnumFacing.SOUTH, count
               );
               break;
            case 3:
               GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                  goldenIngot, list, random, this.distanceAggregator.count3 - 1, this.distanceAggregator.count6, this.distanceAggregator.count4, EnumFacing.WEST, count
               );
               break;
            case 4:
               GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                  goldenIngot, list, random, this.distanceAggregator.count5 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4, EnumFacing.EAST, count
               );
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public SecondaryItemEntryAdapter(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }
}
