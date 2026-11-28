package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemCarrotOnAStick extends Item {
   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (player.isRiding() && player.ridingEntity instanceof EntityPig) {
         EntityPig ridingEntity = (EntityPig)player.ridingEntity;
         if (ridingEntity.getAIControlledByPlayer().isControlledByPlayer() && stack.getMaxDamage() - stack.getMetadata() >= 7) {
            ridingEntity.getAIControlledByPlayer().boostSpeed();
            stack.damageItem(7, player);
            if (stack.stackSize == 0) {
               ItemStack stack2 = new ItemStack(Items.fishing_rod);
               stack2.setTagCompound(stack.getTagCompound());
               return stack2;
            }
         }
      }

      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack;
   }

   @Override
   public boolean shouldRotateAroundWhenRendering() {
      return true;
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   public ItemCarrotOnAStick() {
      this.setCreativeTab(CreativeTabs.tabTransport);
      this.setMaxStackSize(1);
      this.setMaxDamage(25);
   }
}
