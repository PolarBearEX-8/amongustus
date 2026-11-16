package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.GoldenIngot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EnumFacingEntry extends GoldenIngot {
   public EnumFacing enumFacing2;
   public boolean enabled;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.applyText("tf", this.enabled);
      tagCompound.put("D", this.enumFacing2.getIndexOffset());
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.enabled = tagCompound.matchesText("tf");
      this.enumFacing2 = EnumFacing.resolveEnumFacing(tagCompound.computeCount("D"));
   }

   public EnumFacingEntry() {
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      int count = this.getCount();
      switch (CommonIntArrayEntry.countArray[this.enumFacing2.ordinal()]) {
         case 1:
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 - 1, EnumFacing.NORTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 - 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.WEST, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count5 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.EAST, count
            );
            break;
         case 2:
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count + 1, EnumFacing.SOUTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 - 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.WEST, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count5 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.EAST, count
            );
            break;
         case 3:
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 - 1, EnumFacing.NORTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count + 1, EnumFacing.SOUTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 - 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.WEST, count
            );
            break;
         case 4:
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 - 1, EnumFacing.NORTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count3 + 1, this.distanceAggregator.count6, this.distanceAggregator.count + 1, EnumFacing.SOUTH, count
            );
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot, list, random, this.distanceAggregator.count5 + 1, this.distanceAggregator.count6, this.distanceAggregator.count4 + 1, EnumFacing.EAST, count
            );
      }

      if (this.enabled) {
         if (random.nextBoolean()) {
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot,
               list,
               random,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count6 + 3 + 1,
               this.distanceAggregator.count4 - 1,
               EnumFacing.NORTH,
               count
            );
         }

         if (random.nextBoolean()) {
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot,
               list,
               random,
               this.distanceAggregator.count3 - 1,
               this.distanceAggregator.count6 + 3 + 1,
               this.distanceAggregator.count4 + 1,
               EnumFacing.WEST,
               count
            );
         }

         if (random.nextBoolean()) {
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot,
               list,
               random,
               this.distanceAggregator.count5 + 1,
               this.distanceAggregator.count6 + 3 + 1,
               this.distanceAggregator.count4 + 1,
               EnumFacing.EAST,
               count
            );
         }

         if (random.nextBoolean()) {
            GlobalListEntry.resolveGoldenIngotByGoldenIngot(
               goldenIngot,
               list,
               random,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count6 + 3 + 1,
               this.distanceAggregator.count + 1,
               EnumFacing.SOUTH,
               count
            );
         }
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         if (this.enabled) {
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count6,
               this.distanceAggregator.count4,
               this.distanceAggregator.count5 - 1,
               this.distanceAggregator.count6 + 3 - 1,
               this.distanceAggregator.count,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3,
               this.distanceAggregator.count6,
               this.distanceAggregator.count4 + 1,
               this.distanceAggregator.count5,
               this.distanceAggregator.count6 + 3 - 1,
               this.distanceAggregator.count - 1,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count2 - 2,
               this.distanceAggregator.count4,
               this.distanceAggregator.count5 - 1,
               this.distanceAggregator.count2,
               this.distanceAggregator.count,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3,
               this.distanceAggregator.count2 - 2,
               this.distanceAggregator.count4 + 1,
               this.distanceAggregator.count5,
               this.distanceAggregator.count2,
               this.distanceAggregator.count - 1,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count6 + 3,
               this.distanceAggregator.count4 + 1,
               this.distanceAggregator.count5 - 1,
               this.distanceAggregator.count6 + 3,
               this.distanceAggregator.count - 1,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
         } else {
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3 + 1,
               this.distanceAggregator.count6,
               this.distanceAggregator.count4,
               this.distanceAggregator.count5 - 1,
               this.distanceAggregator.count2,
               this.distanceAggregator.count,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.applyWorld(
               world,
               distanceAggregator,
               this.distanceAggregator.count3,
               this.distanceAggregator.count6,
               this.distanceAggregator.count4 + 1,
               this.distanceAggregator.count5,
               this.distanceAggregator.count2,
               this.distanceAggregator.count - 1,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
         }

         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count3 + 1,
            this.distanceAggregator.count6,
            this.distanceAggregator.count4 + 1,
            this.distanceAggregator.count3 + 1,
            this.distanceAggregator.count2,
            this.distanceAggregator.count4 + 1,
            Blocks.block142.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count3 + 1,
            this.distanceAggregator.count6,
            this.distanceAggregator.count - 1,
            this.distanceAggregator.count3 + 1,
            this.distanceAggregator.count2,
            this.distanceAggregator.count - 1,
            Blocks.block142.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count5 - 1,
            this.distanceAggregator.count6,
            this.distanceAggregator.count4 + 1,
            this.distanceAggregator.count5 - 1,
            this.distanceAggregator.count2,
            this.distanceAggregator.count4 + 1,
            Blocks.block142.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );
         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count5 - 1,
            this.distanceAggregator.count6,
            this.distanceAggregator.count - 1,
            this.distanceAggregator.count5 - 1,
            this.distanceAggregator.count2,
            this.distanceAggregator.count - 1,
            Blocks.block142.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );

         for (int index = this.distanceAggregator.count3; index <= this.distanceAggregator.count5; index++) {
            for (int index2 = this.distanceAggregator.count4; index2 <= this.distanceAggregator.count; index2++) {
               if (this.resolveIBlockState(world, index, this.distanceAggregator.count6 - 1, index2, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry) {
                  this.applyWorld(world, Blocks.block142.getDefaultState(), index, this.distanceAggregator.count6 - 1, index2, distanceAggregator);
               }
            }
         }

         return true;
      }
   }

   public static DistanceAggregator resolveDistanceAggregator(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing) {
      DistanceAggregator distanceAggregator = new DistanceAggregator(count, count2, count3, count, count2 + 2, count3);
      if (random.nextInt(4) == 0) {
         distanceAggregator.count2 += 4;
      }

      switch (CommonIntArrayEntry.countArray[enumFacing.ordinal()]) {
         case 1:
            distanceAggregator.count3 = count - 1;
            distanceAggregator.count5 = count + 3;
            distanceAggregator.count4 = count3 - 4;
            break;
         case 2:
            distanceAggregator.count3 = count - 1;
            distanceAggregator.count5 = count + 3;
            distanceAggregator.count = count3 + 4;
            break;
         case 3:
            distanceAggregator.count3 = count - 4;
            distanceAggregator.count4 = count3 - 1;
            distanceAggregator.count = count3 + 3;
            break;
         case 4:
            distanceAggregator.count5 = count + 4;
            distanceAggregator.count4 = count3 - 1;
            distanceAggregator.count = count3 + 3;
      }

      return GoldenIngot.resolveGoldenIngot(list, distanceAggregator) != null ? null : distanceAggregator;
   }

   public EnumFacingEntry(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing2 = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.enabled = distanceAggregator.getCount2() > 3;
   }
}
