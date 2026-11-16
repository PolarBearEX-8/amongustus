package net.minecraft.item;

import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenSpear extends TertiaryItemEntryAdapter {
   public boolean enabled;

   public GoldenSpear() {
   }

   public static GoldenSpear resolveGoldenSpear(List list, Random random, int count, int count2, int count3, int count4, EnumFacing enumFacing) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -2, 0, 0, 7, 8, 9, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenSpear(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Mob");
   }

   public GoldenSpear(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Mob", this.enabled);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 2, 0, 6, 7, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 5, 1, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 1, 5, 2, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 3, 2, 5, 3, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 4, 3, 5, 4, 7, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 2, 0, 1, 4, 2, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 2, 0, 5, 4, 2, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 2, 1, 5, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 5, 5, 2, 5, 5, 3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 5, 3, 0, 5, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 5, 3, 6, 5, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 5, 8, 5, 5, 8, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      this.applyWorld(world, Blocks.block134.getDefaultState(), 1, 6, 3, distanceAggregator);
      this.applyWorld(world, Blocks.block134.getDefaultState(), 5, 6, 3, distanceAggregator);
      this.applyWorld(world, distanceAggregator, 0, 6, 3, 0, 6, 8, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 6, 3, 6, 6, 8, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 6, 8, 5, 7, 8, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 2, 8, 8, 4, 8, 8, Blocks.block134.getDefaultState(), Blocks.block134.getDefaultState(), false);
      if (!this.enabled) {
         BlockPos blockPos = new BlockPos(this.computeCountByIndex(3, 5), this.computeCount(5), this.computeCount(3, 5));
         if (distanceAggregator.matchesVec3i(blockPos)) {
            this.enabled = true;
            world.matchesBlockPos(blockPos, Blocks.block29.getDefaultState(), 2);
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity instanceof TileEntityMobspawner) {
               ((TileEntityMobspawner)tileEntity).getPassiveStringEntry().setLabelText("Blaze");
            }
         }
      }

      for (int index = 0; index <= 6; index++) {
         for (int index2 = 0; index2 <= 6; index2++) {
            this.applyWorldByWorld(world, Blocks.block86.getDefaultState(), index, -1, index2, distanceAggregator);
         }
      }

      return true;
   }
}
