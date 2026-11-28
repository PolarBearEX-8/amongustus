package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
   @Override
   public boolean isItemTool(ItemStack stack) {
      return super.isItemTool(stack);
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (player.fishEntity != null) {
         stack.damageItem(player.fishEntity.handleHookRetraction(), player);
         player.swingItem();
      } else {
         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityFishHook(world, player));
         }

         player.swingItem();
         player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      }

      return stack;
   }

   @Override
   public boolean shouldRotateAroundWhenRendering() {
      return true;
   }

   public ItemFishingRod() {
      this.setMaxDamage(64);
      this.setMaxStackSize(1);
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   @Override
   public int getItemEnchantability() {
      return 1;
   }
}
