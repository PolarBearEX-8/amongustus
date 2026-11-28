package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockRedstoneOre extends Block {
   private final boolean isOn;

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this == Blocks.lit_redstone_ore) {
         world.setBlockState(pos, Blocks.redstone_ore.getDefaultState());
      }
   }

   @Override
   public int tickRate(World world) {
      return 30;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      this.activate(world, pos);
      return super.onBlockActivated(world, pos, state, player, facing, f, f2, f3);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.isOn) {
         this.spawnParticles(world, pos);
      }
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, n);
      if (this.getItemDropped(state, world.rand, n) != Item.getItemFromBlock(this)) {
         this.dropXpOnBlockBreak(world, pos, 1 + world.rand.nextInt(5));
      }
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
      this.activate(world, pos);
      super.onEntityCollidedWithBlock(world, pos, entity);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.redstone;
   }

   private void activate(World world, BlockPos pos) {
      this.spawnParticles(world, pos);
      if (this == Blocks.redstone_ore) {
         world.setBlockState(pos, Blocks.lit_redstone_ore.getDefaultState());
      }
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
      this.activate(world, pos);
      super.onBlockClicked(world, pos, player);
   }

   @Override
   public ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Blocks.redstone_ore);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 4 + rand.nextInt(2);
   }

   public BlockRedstoneOre(boolean flag) {
      super(Material.rock);
      if (flag) {
         this.setTickRandomly(true);
      }

      this.isOn = flag;
   }

   @Override
   public int quantityDroppedWithBonus(int n, Random rand) {
      return this.quantityDropped(rand) + rand.nextInt(n + 1);
   }

   private void spawnParticles(World world, BlockPos pos) {
      Random rand = world.rand;
      double d = 0.0625;

      for (int i = 0; i < 6; i++) {
         double d2 = (double)((float)pos.getX() + rand.nextFloat());
         double d3 = (double)((float)pos.getY() + rand.nextFloat());
         double d4 = (double)((float)pos.getZ() + rand.nextFloat());
         if (i == 0 && !world.getBlockState(pos.up()).getBlock().isOpaqueCube()) {
            d3 = (double)pos.getY() + d + 1.0;
         }

         if (i == 1 && !world.getBlockState(pos.down()).getBlock().isOpaqueCube()) {
            d3 = (double)pos.getY() - d;
         }

         if (i == 2 && !world.getBlockState(pos.south()).getBlock().isOpaqueCube()) {
            d4 = (double)pos.getZ() + d + 1.0;
         }

         if (i == 3 && !world.getBlockState(pos.north()).getBlock().isOpaqueCube()) {
            d4 = (double)pos.getZ() - d;
         }

         if (i == 4 && !world.getBlockState(pos.east()).getBlock().isOpaqueCube()) {
            d2 = (double)pos.getX() + d + 1.0;
         }

         if (i == 5 && !world.getBlockState(pos.west()).getBlock().isOpaqueCube()) {
            d2 = (double)pos.getX() - d;
         }

         if (d2 < (double)pos.getX()
            || d2 > (double)(pos.getX() + 1)
            || d3 < 0.0
            || d3 > (double)(pos.getY() + 1)
            || d4 < (double)pos.getZ()
            || d4 > (double)(pos.getZ() + 1)) {
            world.spawnParticle(EnumParticleTypes.REDSTONE, d2, d3, d4, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }
}
