package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemExpBottle extends Item {
   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (!player.capabilities.isCreativeMode) {
         stack.stackSize--;
      }

      world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!world.isRemote) {
         world.spawnEntityInWorld(new EntityExpBottle(world, player));
      }

      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack;
   }

   public ItemExpBottle() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public boolean hasEffect(ItemStack stack) {
      return true;
   }
}
