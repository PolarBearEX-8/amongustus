package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;

public class ItemSaddle extends Item {
   public ItemSaddle() {
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
      if (entity instanceof EntityPig) {
         EntityPig entityPig = (EntityPig)entity;
         if (!entityPig.getSaddled() && !entityPig.isChild()) {
            entityPig.setSaddled(true);
            entityPig.entityWorld.playSoundAtEntity(entityPig, "mob.horse.leather", 0.5F, 1.0F);
            stack.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2) {
      this.itemInteractionForEntity(stack, (EntityPlayer)null, entity);
      return true;
   }
}
