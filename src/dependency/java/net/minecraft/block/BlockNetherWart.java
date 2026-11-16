package net.minecraft.block;

import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockNetherWart extends SecondaryBlockAdapter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 3);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      IBlockState blockState2 = blockState;
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      if (comparable < 3 && random.nextInt(10) == 0) {
         blockState2 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1);
         world.matchesBlockPos(blockPos, blockState2, 2);
      }

      super.updateTick(world, blockPos, blockState2, random);
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (!world.state) {
         int count2 = 1;
         if ((Integer)blockState.getComparable(auxiliaryImmutableSetEntry) >= 3) {
            count2 = 2 + world.random.nextInt(3);
            if (count > 0) {
               count2 += world.random.nextInt(count + 1);
            }
         }

         for (int index = 0; index < count2; index++) {
            applyWorld(world, blockPos, new ItemStack(Items.netherWart));
         }
      }
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return block == Blocks.block126;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.netherWart;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      return this.matchesBlockByBlock(world.getBlockState(blockPos.down()).getBlock());
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   public BlockNetherWart() {
      super(Material.material24, MapColor.mapColor5);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
      float factor = 0.5F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.25F, 0.5F + factor);
      this.processAction((ItemGroupManager)null);
   }
}
