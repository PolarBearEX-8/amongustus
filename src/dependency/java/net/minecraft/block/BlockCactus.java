package net.minecraft.block;

import client.ClientGlobals;
import client.module.Module;
import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCactus extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 15);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      BlockPos blockPos2 = blockPos.up();
      if (world.isAirBlock(blockPos2)) {
         int count = 1;

         while (world.getBlockState(blockPos.resolveBlockPos4(count)).getBlock() == this) {
            count++;
         }

         if (count < 3) {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            if (comparable == 15) {
               world.matchesBlockPos(blockPos2, this.getDefaultState());
               IBlockState blockState2 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, 0);
               world.matchesBlockPos(blockPos, blockState2, 4);
               this.processAction(world, blockPos2, blockState2, this);
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
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      float factor = 0.0625F;
      return new AxisAlignedBB(
         (double)((float)blockPos.getX() + factor),
         (double)blockPos.getY(),
         (double)((float)blockPos.getZ() + factor),
         (double)((float)(blockPos.getX() + 1) - factor),
         (double)(blockPos.getY() + 1),
         (double)((float)(blockPos.getZ() + 1) - factor)
      );
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      entity.matchesDamageSource(DamageSource.damageSource7, 1.0F);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld3(world, blockPos)) {
         world.matchesBlockPos3(blockPos, true);
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) ? this.matchesWorld3(world, blockPos) : false;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      float factor = 0.0625F;
      return ((Module)ClientGlobals.moduleManager.antiCactus).isToggled()
         ? new AxisAlignedBB(
            (double)count + this.coordOffset,
            (double)count2 + this.coordOffset11,
            (double)count3 + this.coordOffset2,
            (double)count + this.coordOffset9,
            (double)count2 + this.coordOffset6 - (double)(ClientGlobals.moduleManager.antiCactus.settingOnlySide.isEnabled() ? factor : 0.0F),
            (double)count3 + this.coordOffset15
         )
         : new AxisAlignedBB(
            (double)((float)count + factor),
            (double)count2,
            (double)((float)count3 + factor),
            (double)((float)(count + 1) - factor),
            (double)((float)(count2 + 1) - factor),
            (double)((float)(count3 + 1) - factor)
         );
   }

   public boolean matchesWorld3(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : (Iterable<EnumFacing>)MovementFactorProperty.HORIZONTAL) {
         if (world.getBlockState(blockPos.offset(enumFacing)).getBlock().getMaterial().canExecute2()) {
            return false;
         }
      }

      Block block = world.getBlockState(blockPos.down()).getBlock();
      return block == Blocks.blockCactus || block == Blocks.blockSand;
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

   public BlockCactus() {
      super(Material.material21);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
