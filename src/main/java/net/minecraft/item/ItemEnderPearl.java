package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemEnderPearl extends Item {
   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (player.capabilities.isCreativeMode) {
         return stack;
      } else {
         stack.stackSize--;
         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityEnderPearl(world, player));
         }

         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         return stack;
      }
   }

   public ItemEnderPearl() {
      this.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
