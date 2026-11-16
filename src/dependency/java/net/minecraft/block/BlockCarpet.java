package net.minecraft.block;

import client.setting.LocalOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarpet extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("color", LocalOptionType.class);

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      return !world.isAirBlock(blockPos.down());
   }

   @Override
   public void processAction2() {
      this.applyIndex(0);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIndex(0);
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, LocalOptionType.resolveLocalOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (int index = 0; index < 16; index++) {
         list.add(new ItemStack(item, 1, index));
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.matchesWorld(world, blockPos, blockState);
   }

   public void applyIndex(int count) {
      float factor = (float)1 / 16.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, factor, 1.0F);
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) && this.matchesWorld3(world, blockPos);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.UP ? true : super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesWorld3(world, blockPos)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockCarpet() {
      super(Material.material23);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, LocalOptionType.WHITE));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.applyIndex(0);
   }
}
