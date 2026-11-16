package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenCrown extends InternalItemEntryAdapter {
   public boolean enabled2;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Terrace", this.enabled2);
   }

   public GoldenCrown() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled2 = tagCompound.matchesText("Terrace");
   }

   public static GoldenCrown resolveGoldenCrown(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 5, 6, 5, enumFacing);
      return GoldenIngot.resolveGoldenIngot(list, distanceAggregator) != null ? null : new GoldenCrown(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing);
   }

   public GoldenCrown(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.enabled2 = random.nextBoolean();
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 6 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 0, 4, Blocks.block65.getDefaultState(), Blocks.block65.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 4, 0, 4, 4, 4, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 4, 1, 3, 4, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 0, 3, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 1, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 3, 4, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 0, 1, 1, 0, 3, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 1, 1, 4, 3, 3, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 1, 4, 3, 3, 4, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 0, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 2, 2, 4, distanceAggregator);
      this.applyWorld(world, Blocks.block19.getDefaultState(), 4, 2, 2, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 1, 1, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 1, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 1, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 2, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 3, 3, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 3, 2, 0, distanceAggregator);
      this.applyWorld(world, Blocks.block142.getDefaultState(), 3, 1, 0, distanceAggregator);
      if (this.resolveIBlockState(world, 2, 0, -1, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry
         && this.resolveIBlockState(world, 2, -1, -1, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
         this.applyWorld(world, Blocks.block125.processAction(this.computeCount(Blocks.block125, 3)), 2, 0, -1, distanceAggregator);
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      if (this.enabled2) {
         this.applyWorld(world, Blocks.block111.getDefaultState(), 0, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 2, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 3, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 5, 0, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 0, 5, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 1, 5, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 2, 5, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 3, 5, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 5, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 5, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 5, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 4, 5, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 0, 5, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 0, 5, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block111.getDefaultState(), 0, 5, 3, distanceAggregator);
      }

      if (this.enabled2) {
         int count = this.computeCount(Blocks.block81, 3);
         this.applyWorld(world, Blocks.block81.processAction(count), 3, 1, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block81.processAction(count), 3, 2, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block81.processAction(count), 3, 3, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block81.processAction(count), 3, 4, 3, distanceAggregator);
      }

      this.applyWorld(world, Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, this.enumFacing), 2, 3, 1, distanceAggregator);

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 5; index2++) {
            this.applyWorld(world, index2, 6, index, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block65.getDefaultState(), index2, -1, index, distanceAggregator);
         }
      }

      this.applyWorld(world, distanceAggregator, 1, 1, 2, 1);
      return true;
   }
}
