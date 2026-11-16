package net.minecraft.item;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.StatCraftItemManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemLavaBucket extends Item {
   public Block block;

   private ItemStack resolveItemStack(ItemStack stack, EntityPlayer player, Item item) {
      if (player.invulnerableManager.enabled2) {
         return stack;
      } else if (--stack.animationsToGo <= 0) {
         return new ItemStack(item);
      } else {
         if (!player.inventoryPlayer.checkItemStack3(new ItemStack(item))) {
            player.resolveEntityItem(new ItemStack(item, 1, 0), false);
         }

         return stack;
      }
   }

   public boolean matchesWorldByWorld(World world, BlockPos blockPos) {
      if (this.block == Blocks.air) {
         return false;
      } else {
         Material material = world.getBlockState(blockPos).getBlock().getMaterial();
         boolean flag = !material.canExecute2();
         if (!world.isAirBlock(blockPos) && !flag) {
            return false;
         } else {
            if (world.secondaryFloatArrayEntry.isEnabled2() && this.block == Blocks.blockFlowingLava) {
               int coordX = blockPos.getX();
               int coordY = blockPos.getY();
               int coordZ = blockPos.getZ();
               world.applyText(
                  (double)((float)coordX + 0.5F),
                  (double)((float)coordY + 0.5F),
                  (double)((float)coordZ + 0.5F),
                  "random.fizz",
                  0.5F,
                  2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F
               );

               for (int index = 0; index < 8; index++) {
                  world.applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.SMOKE_LARGE,
                     (double)coordX + Math.random(),
                     (double)coordY + Math.random(),
                     (double)coordZ + Math.random(),
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
               }
            } else {
               if (!world.state && flag && !material.isActive2()) {
                  world.matchesBlockPos3(blockPos, true);
               }

               world.matchesBlockPos(blockPos, this.block.getDefaultState(), 3);
            }

            return true;
         }
      }
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      boolean flag = this.block == Blocks.air;
      MovingObjectPosition hitResult = this.resolveMovingObjectPositionByWorld(world, player, flag);
      if (hitResult == null) {
         return stack;
      } else {
         if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            if (!world.matchesEntityPlayer(player, blockPos)) {
               return stack;
            }

            if (flag) {
               if (!player.matchesBlockPos(blockPos.offset(hitResult.sideHit), hitResult.sideHit, stack)) {
                  return stack;
               }

               IBlockState blockState = world.getBlockState(blockPos);
               Material material = blockState.getBlock().getMaterial();
               if (material == Material.coreItemEntry1 && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0) {
                  world.matchesBlockPos8(blockPos);
                  player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
                  return this.resolveItemStack(stack, player, Items.waterBucket);
               }

               if (material == Material.material29 && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0) {
                  world.matchesBlockPos8(blockPos);
                  player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
                  return this.resolveItemStack(stack, player, Items.lavaBucket);
               }
            } else {
               if (this.block == Blocks.air) {
                  return new ItemStack(Items.bucket);
               }

               BlockPos blockPos2 = blockPos.offset(hitResult.sideHit);
               if (!player.matchesBlockPos(blockPos2, hitResult.sideHit, stack)) {
                  return stack;
               }

               if (this.matchesWorldByWorld(world, blockPos2) && !player.invulnerableManager.enabled2) {
                  player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
                  return new ItemStack(Items.bucket);
               }
            }
         }

         return stack;
      }
   }

   public ItemLavaBucket(Block block2) {
      this.indexOffset1 = 1;
      this.block = block2;
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
