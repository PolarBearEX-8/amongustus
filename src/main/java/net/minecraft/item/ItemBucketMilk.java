package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemBucketMilk extends Item {
   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
      if (!player.capabilities.isCreativeMode) {
         stack.stackSize--;
      }

      if (!world.isRemote) {
         player.clearActivePotions();
      }

      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack.stackSize <= 0 ? new ItemStack(Items.bucket) : stack;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 32;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.DRINK;
   }

   public ItemBucketMilk() {
      this.setMaxStackSize(1);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
      return stack;
   }
}
