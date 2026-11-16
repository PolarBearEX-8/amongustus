package net.minecraft.item;

import client.event.PostRenderConsumer;
import client.util.DistanceAggregator;
import client.util.IBlockState;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class CustomItemEntryAdapter extends GoldenIngot {
   public static final IBlockState iBlockState4 = Blocks.block58.processAction(BlockPrismarine.count3);
   public static final IBlockState iBlockState = Blocks.block58.processAction(BlockPrismarine.count2);
   public static final IBlockState iBlockState2 = Blocks.block58.processAction(BlockPrismarine.count);
   public static final IBlockState iBlockState5 = iBlockState;
   public static final IBlockState iBlockState3 = Blocks.block62.getDefaultState();
   public static final IBlockState iBlockState6 = Blocks.blockLava2.getDefaultState();
   public static final int count2 = computeCount(2, 0, 0);
   public static final int count3 = computeCount(2, 2, 0);
   public static final int count4 = computeCount(0, 1, 0);
   public static final int count5 = computeCount(4, 1, 0);
   public EnumFacingController enumFacingController;

   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator, int count, int count6, int count7) {
      int count8 = this.computeCountByIndex(count, count7);
      int count9 = this.computeCount(count6);
      int count10 = this.computeCount(count, count7);
      if (distanceAggregator.matchesVec3i(new BlockPos(count8, count9, count10))) {
         EntityGuardian entityGuardian = new EntityGuardian(world);
         entityGuardian.setEnabled6(true);
         entityGuardian.applyFloatByFloat(entityGuardian.getFloat());
         entityGuardian.applyDoubleByDouble((double)count8 + 0.5, (double)count9, (double)count10 + 0.5, 0.0F, 0.0F);
         entityGuardian.resolvePostRenderConsumer(world.resolveGoldenOre(new BlockPos(entityGuardian)), (PostRenderConsumer)null);
         world.matchesEntity(entityGuardian);
         return true;
      } else {
         return false;
      }
   }

   public boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator, int count, int count6, int count7, int count8) {
      int count9 = this.computeCountByIndex(count, count6);
      int count10 = this.computeCount(count, count6);
      int count11 = this.computeCountByIndex(count7, count8);
      int count12 = this.computeCount(count7, count8);
      return distanceAggregator.checkIndex(Math.min(count9, count11), Math.min(count10, count12), Math.max(count9, count11), Math.max(count10, count12));
   }

   public CustomItemEntryAdapter(EnumFacing enumFacing, DistanceAggregator distanceAggregator) {
      super(1);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public CustomItemEntryAdapter() {
      super(0);
   }

   public CustomItemEntryAdapter(int count, EnumFacing enumFacing, EnumFacingController enumFacingController2, int count6, int count7, int count8) {
      super(count);
      this.enumFacing = enumFacing;
      this.enumFacingController = enumFacingController2;
      int count9 = enumFacingController2.count;
      int count10 = count9 % 5;
      int count11 = count9 / 5 % 5;
      int count12 = count9 / 25;
      if (enumFacing != EnumFacing.NORTH && enumFacing != EnumFacing.SOUTH) {
         this.distanceAggregator = new DistanceAggregator(0, 0, 0, count8 * 8 - 1, count7 * 4 - 1, count6 * 8 - 1);
      } else {
         this.distanceAggregator = new DistanceAggregator(0, 0, 0, count6 * 8 - 1, count7 * 4 - 1, count8 * 8 - 1);
      }

      switch (GoldenStaffEnumFacingSwitchMap.countArray[enumFacing.ordinal()]) {
         case 1:
            this.distanceAggregator.applyIndex(count10 * 8, count12 * 4, -(count11 + count8) * 8 + 1);
            break;
         case 2:
            this.distanceAggregator.applyIndex(count10 * 8, count12 * 4, count11 * 8);
            break;
         case 3:
            this.distanceAggregator.applyIndex(-(count11 + count8) * 8 + 1, count12 * 4, count10 * 8);
            break;
         default:
            this.distanceAggregator.applyIndex(count11 * 8, count12 * 4, count10 * 8);
      }
   }

   public static final int computeCount(int count, int count6, int count7) {
      return count6 * 25 + count7 * 5 + count;
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator, int count, int count6, int count7, int count8, int count9, int count10, boolean flag) {
      for (int index = count6; index <= count9; index++) {
         for (int index2 = count; index2 <= count8; index2++) {
            for (int index3 = count7; index3 <= count10; index3++) {
               if (!flag || this.resolveIBlockState(world, index2, index, index3, distanceAggregator).getBlock().getMaterial() != Material.coreItemEntry) {
                  if (this.computeCount(index) >= world.getIndexOffset1()) {
                     this.applyWorld(world, Blocks.air.getDefaultState(), index2, index, index3, distanceAggregator);
                  } else {
                     this.applyWorld(world, iBlockState6, index2, index, index3, distanceAggregator);
                  }
               }
            }
         }
      }
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator, int count, int count6, int count7, int count8, int count9, int count10, IBlockState blockState) {
      for (int index = count6; index <= count9; index++) {
         for (int index2 = count; index2 <= count8; index2++) {
            for (int index3 = count7; index3 <= count10; index3++) {
               if (this.resolveIBlockState(world, index2, index, index3, distanceAggregator) == iBlockState6) {
                  this.applyWorld(world, blockState, index2, index, index3, distanceAggregator);
               }
            }
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator, int count, int count6, boolean flag) {
      if (flag) {
         this.applyWorld(world, distanceAggregator, count + 0, 0, count6 + 0, count + 2, 0, count6 + 8 - 1, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 5, 0, count6 + 0, count + 8 - 1, 0, count6 + 8 - 1, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 3, 0, count6 + 0, count + 4, 0, count6 + 2, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 3, 0, count6 + 5, count + 4, 0, count6 + 8 - 1, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 3, 0, count6 + 2, count + 4, 0, count6 + 2, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, count + 3, 0, count6 + 5, count + 4, 0, count6 + 5, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, count + 2, 0, count6 + 3, count + 2, 0, count6 + 4, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, count + 5, 0, count6 + 3, count + 5, 0, count6 + 4, iBlockState, iBlockState, false);
      } else {
         this.applyWorld(world, distanceAggregator, count + 0, 0, count6 + 0, count + 8 - 1, 0, count6 + 8 - 1, iBlockState4, iBlockState4, false);
      }
   }

   public CustomItemEntryAdapter(int count) {
      super(count);
   }
}
