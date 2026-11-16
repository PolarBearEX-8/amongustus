package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenAmulet extends InternalItemEntryAdapter {
   public boolean enabled2;
   public int count4;

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 6 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 3, 0, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 1, 2, 0, 3, Blocks.block115.getDefaultState(), Blocks.block115.getDefaultState(), false);
      if (this.enabled2) {
         this.applyWorld(world, distanceAggregator, 1, 4, 1, 2, 4, 3, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      } else {
         this.applyWorld(world, distanceAggregator, 1, 5, 1, 2, 5, 3, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      }

      this.applyWorld(world, Blocks.block140.getDefaultState(), 1, 4, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 2, 4, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 1, 4, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 2, 4, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 4, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 0, 4, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 3, 4, 1, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 3, 4, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block140.getDefaultState(), 3, 4, 3, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 0, 1, 0, 0, 3, 0, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 0, 3, 3, 0, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 4, 0, 3, 4, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 4, 3, 3, 4, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 1, 3, 3, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 0, 2, 3, 0, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 4, 2, 3, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 3, 2, 2, distanceAggregator);
      if (this.count4 > 0) {
         this.applyWorld(world, Blocks.block111.getDefaultState(), this.count4, 1, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block94.getDefaultState(), this.count4, 2, 3, distanceAggregator);
      }

      this.applyWorld(world, Blocks.air.getDefaultState(), 1, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.air.getDefaultState(), 1, 2, 0, distanceAggregator);
      this.applyWorld(world, distanceAggregator, random, 1, 1, 0, EnumFacing.resolveEnumFacing(this.computeCount(Blocks.block110, 1)));
      if (this.resolveIBlockState(world, 1, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 1, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 1, 0, -1, distanceAggregator);
      }

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 4; index2++) {
            this.applyWorld(world, index2, 6, index, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index2, -1, index, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 2, 1);
      return true;
   }

   public GoldenAmulet(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.enabled2 = random.nextBoolean();
      this.count4 = random.nextInt(3);
   }

   public GoldenAmulet() {
   }

   public static GoldenAmulet resolveGoldenAmulet(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count5
   ) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 4, 6, 5, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenAmulet(invalidBiomeIdManagerEntry, count5, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count4 = tagCompound.computeCount("T");
      this.enabled2 = tagCompound.matchesText("C");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("T", this.count4);
      tagCompound.applyText("C", this.enabled2);
   }
}
