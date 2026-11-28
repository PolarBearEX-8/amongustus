package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBucket extends Item {
   private Block isFull;

   private ItemStack fillBucket(ItemStack stack, EntityPlayer player, Item item) {
      if (player.capabilities.isCreativeMode) {
         return stack;
      } else if (--stack.stackSize <= 0) {
         return new ItemStack(item);
      } else {
         if (!player.inventory.addItemStackToInventory(new ItemStack(item))) {
            player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
         }

         return stack;
      }
   }

   public boolean tryPlaceContainedLiquid(World world, BlockPos pos) {
      if (this.isFull == Blocks.air) {
         return false;
      } else {
         Material material = world.getBlockState(pos).getBlock().getMaterial();
         boolean flag = !material.isSolid();
         if (!world.isAirBlock(pos) && !flag) {
            return false;
         } else {
            if (world.provider.doesWaterVaporize() && this.isFull == Blocks.flowing_water) {
               int x = pos.getX();
               int y = pos.getY();
               int z = pos.getZ();
               world.playSoundEffect(
                  (double)((float)x + 0.5F),
                  (double)((float)y + 0.5F),
                  (double)((float)z + 0.5F),
                  "random.fizz",
                  0.5F,
                  2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F
               );

               for (int i = 0; i < 8; i++) {
                  world.spawnParticle(
                     EnumParticleTypes.SMOKE_LARGE,
                     (double)x + Math.random(),
                     (double)y + Math.random(),
                     (double)z + Math.random(),
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
               }
            } else {
               if (!world.isRemote && flag && !material.isLiquid()) {
                  world.destroyBlock(pos, true);
               }

               world.setBlockState(pos, this.isFull.getDefaultState(), 3);
            }

            return true;
         }
      }
   }

   public ItemBucket(Block block) {
      this.maxStackSize = 1;
      this.isFull = block;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      boolean flag = this.isFull == Blocks.air;
      MovingObjectPosition hit = this.getMovingObjectPositionFromPlayer(world, player, flag);
      if (hit == null) {
         return stack;
      } else {
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockPos = hit.getBlockPos();
            if (!world.isBlockModifiable(player, blockPos)) {
               return stack;
            }

            if (flag) {
               if (!player.canPlayerEdit(blockPos.offset(hit.sideHit), hit.sideHit, stack)) {
                  return stack;
               }

               IBlockState blockState = world.getBlockState(blockPos);
               Material material = blockState.getBlock().getMaterial();
               if (material == Material.water && (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0) {
                  world.setBlockToAir(blockPos);
                  player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return this.fillBucket(stack, player, Items.water_bucket);
               }

               if (material == Material.lava && (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0) {
                  world.setBlockToAir(blockPos);
                  player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return this.fillBucket(stack, player, Items.lava_bucket);
               }
            } else {
               if (this.isFull == Blocks.air) {
                  return new ItemStack(Items.bucket);
               }

               BlockPos pos = blockPos.offset(hit.sideHit);
               if (!player.canPlayerEdit(pos, hit.sideHit, stack)) {
                  return stack;
               }

               if (this.tryPlaceContainedLiquid(world, pos) && !player.capabilities.isCreativeMode) {
                  player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return new ItemStack(Items.bucket);
               }
            }
         }

         return stack;
      }
   }
}
