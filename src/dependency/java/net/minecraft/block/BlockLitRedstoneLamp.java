package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockLitRedstoneLamp extends Block {
   public final boolean enabled;

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && this.enabled && !world.matchesBlockPos7(blockPos)) {
         world.matchesBlockPos(blockPos, Blocks.block64.getDefaultState(), 2);
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.block64);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         if (this.enabled && !world.matchesBlockPos7(blockPos)) {
            world.applyBlockPos(blockPos, this, 4);
         } else if (!this.enabled && world.matchesBlockPos7(blockPos)) {
            world.matchesBlockPos(blockPos, Blocks.block49.getDefaultState(), 2);
         }
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.block64);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         if (this.enabled && !world.matchesBlockPos7(blockPos)) {
            world.matchesBlockPos(blockPos, Blocks.block64.getDefaultState(), 2);
         } else if (!this.enabled && world.matchesBlockPos7(blockPos)) {
            world.matchesBlockPos(blockPos, Blocks.block49.getDefaultState(), 2);
         }
      }
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(Blocks.block64);
   }

   public BlockLitRedstoneLamp(boolean flag) {
      super(Material.material25);
      this.enabled = flag;
      if (flag) {
         this.processAction(1.0F);
      }
   }
}
