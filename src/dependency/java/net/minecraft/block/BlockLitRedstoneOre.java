package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLitRedstoneOre extends Block {
   public final boolean enabled;

   @Override
   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
      this.applyWorld4(world, blockPos);
      super.onBlockClicked(world, blockPos, player);
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, count);
      if (this.processAction(blockState, world.random, count) != Item.processAction2(this)) {
         int count2 = 1 + world.random.nextInt(5);
         this.applyWorld(world, blockPos, count2);
      }
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this == Blocks.block) {
         world.matchesBlockPos(blockPos, Blocks.block25.getDefaultState());
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, Entity entity) {
      this.applyWorld4(world, blockPos);
      super.processAction(world, blockPos, entity);
   }

   @Override
   public int processAction(World world) {
      return 30;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.redstone;
   }

   @Override
   public int processAction(Random random) {
      return 4 + random.nextInt(2);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      this.applyWorld4(world, blockPos);
      return super.onBlockActivated(world, blockPos, blockState, player, enumFacing, posX, posY, posZ);
   }

   private void applyWorld4(World world, BlockPos blockPos) {
      this.applyWorld2(world, blockPos);
      if (this == Blocks.block25) {
         world.matchesBlockPos(blockPos, Blocks.block.getDefaultState());
      }
   }

   @Override
   public int processAction(int count, Random random) {
      return this.processAction(random) + random.nextInt(count + 1);
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      Random random = world.random;
      double offset = 0.0625;

      for (int index = 0; index < 6; index++) {
         double offset2 = (double)((float)blockPos.getX() + random.nextFloat());
         double offset3 = (double)((float)blockPos.getY() + random.nextFloat());
         double offset4 = (double)((float)blockPos.getZ() + random.nextFloat());
         if (index == 0 && !world.getBlockState(blockPos.up()).getBlock().processAction15()) {
            offset3 = (double)blockPos.getY() + offset + 1.0;
         }

         if (index == 1 && !world.getBlockState(blockPos.down()).getBlock().processAction15()) {
            offset3 = (double)blockPos.getY() - offset;
         }

         if (index == 2 && !world.getBlockState(blockPos.getBlockPos2()).getBlock().processAction15()) {
            offset4 = (double)blockPos.getZ() + offset + 1.0;
         }

         if (index == 3 && !world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock().processAction15()) {
            offset4 = (double)blockPos.getZ() - offset;
         }

         if (index == 4 && !world.getBlockState(blockPos.getBlockPos3()).getBlock().processAction15()) {
            offset2 = (double)blockPos.getX() + offset + 1.0;
         }

         if (index == 5 && !world.getBlockState(blockPos.getBlockPos()).getBlock().processAction15()) {
            offset2 = (double)blockPos.getX() - offset;
         }

         if (offset2 < (double)blockPos.getX()
            || offset2 > (double)(blockPos.getX() + 1)
            || offset3 < 0.0
            || offset3 > (double)(blockPos.getY() + 1)
            || offset4 < (double)blockPos.getZ()
            || offset4 > (double)(blockPos.getZ() + 1)) {
            world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.REDSTONE, offset2, offset3, offset4, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(Blocks.block25);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.enabled) {
         this.applyWorld2(world, blockPos);
      }
   }

   public BlockLitRedstoneOre(boolean flag) {
      super(Material.material4);
      if (flag) {
         this.processAction(true);
      }

      this.enabled = flag;
   }
}
