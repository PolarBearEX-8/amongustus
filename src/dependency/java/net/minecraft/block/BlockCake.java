package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("bites", 0, 6);

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
      this.applyWorldByWorld(world, blockPos, world.getBlockState(blockPos), player);
   }

   @Override
   public void processAction2() {
      float factor = 0.0625F;
      float factor2 = 0.5F;
      this.setBlockBounds(factor, 0.0F, factor, 1.0F - factor, factor2, 1.0F - factor);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      return this.processAction(world, world.getBlockState(blockPos), blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      float factor = 0.0625F;
      float factor2 = (float)(1 + (Integer)iBlockAccess.getIBlockState(count, count2, count3).getComparable(auxiliaryImmutableSetEntry) * 2) / 16.0F;
      float factor3 = 0.5F;
      this.setBlockBounds(factor2, 0.0F, factor, 1.0F - factor, factor3, 1.0F - factor);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.cake;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return (7 - (Integer)world.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry)) * 2;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld4(world, blockPos)) {
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      this.applyWorldByWorld(world, blockPos, blockState, player);
      return true;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) ? this.matchesWorld4(world, blockPos) : false;
   }

   private boolean matchesWorld4(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos.down()).getBlock().getMaterial().canExecute2();
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      if (player.checkFlag(false)) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager19);
         player.getFoodLevelManager().applyIndex(2, 0.1F);
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable < 6) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1), 3);
         } else {
            world.matchesBlockPos8(blockPos);
         }
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      float factor = 0.0625F;
      float factor2 = (float)(1 + (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) * 2) / 16.0F;
      float factor3 = 0.5F;
      return new AxisAlignedBB(
         (double)((float)count + factor2),
         (double)count2,
         (double)((float)count3 + factor),
         (double)((float)(count + 1) - factor),
         (double)((float)count2 + factor3),
         (double)((float)(count3 + 1) - factor)
      );
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
   public boolean processAction16() {
      return true;
   }

   public BlockCake() {
      super(Material.material20);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
   }
}
