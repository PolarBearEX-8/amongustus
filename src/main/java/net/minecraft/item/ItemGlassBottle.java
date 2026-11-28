package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item {
   public ItemGlassBottle() {
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hit = this.getMovingObjectPositionFromPlayer(world, player, true);
      if (hit == null) {
         return stack;
      } else {
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockPos = hit.getBlockPos();
            if (!world.isBlockModifiable(player, blockPos)) {
               return stack;
            }

            if (!player.canPlayerEdit(blockPos.offset(hit.sideHit), hit.sideHit, stack)) {
               return stack;
            }

            if (world.getBlockState(blockPos).getBlock().getMaterial() == Material.water) {
               stack.stackSize--;
               player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
               if (stack.stackSize <= 0) {
                  return new ItemStack(Items.potionitem);
               }

               if (!player.inventory.addItemStackToInventory(new ItemStack(Items.potionitem))) {
                  player.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
               }
            }
         }

         return stack;
      }
   }
}
