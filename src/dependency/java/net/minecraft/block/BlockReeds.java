package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReeds extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 15);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if ((world.getBlockState(blockPos.down()).getBlock() == Blocks.blockReeds || this.matchesWorld(world, blockPos, blockState)) && world.isAirBlock(blockPos.up())) {
         int count = 1;

         while (world.getBlockState(blockPos.resolveBlockPos4(count)).getBlock() == this) {
            count++;
         }

         if (count < 3) {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            if (comparable == 15) {
               world.matchesBlockPos(blockPos.up(), this.getDefaultState());
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, 0), 4);
            } else {
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1), 4);
            }
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.reeds;
   }

   public final boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.matchesWorld3(world, blockPos)) {
         return true;
      } else {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos).computeCountByBlockPos(blockPos);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.matchesWorld(world, blockPos, blockState);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.reeds;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      Block block = world.getBlockState(blockPos.down()).getBlock();
      if (block == this) {
         return true;
      } else if (block != Blocks.blockGrass && block != Blocks.block115 && block != Blocks.blockSand) {
         return false;
      } else {
         for (EnumFacing enumFacing : (java.lang.Iterable<EnumFacing>) (MovementFactorProperty.HORIZONTAL)) {
            if (world.getBlockState(blockPos.offset(enumFacing).down()).getBlock().getMaterial() == Material.coreItemEntry1) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   public boolean matchesWorld3(World world, BlockPos blockPos) {
      return this.processAction7(world, blockPos);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockReeds() {
      super(Material.material24);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      float factor = 0.375F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 1.0F, 0.5F + factor);
      this.processAction(true);
   }
}
