package net.minecraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.player.EntityPlayer;

public class ItemSaddle extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, EntityLivingBase livingEntity) {
      if (livingEntity instanceof EntityPig) {
         EntityPig entityPig = (EntityPig)livingEntity;
         if (!entityPig.isActive32() && !entityPig.isActive16()) {
            entityPig.setEnabled7(true);
            entityPig.worldObj.applyEntityByEntity(entityPig, "mob.horse.leather", 0.5F, 1.0F);
            stack.animationsToGo--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean processAction(ItemStack stack, EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      this.processAction(stack, (EntityPlayer)null, livingEntity);
      return true;
   }

   public ItemSaddle() {
      this.indexOffset1 = 1;
      this.processAction(ItemGroupManager.itemGroupManager12);
   }
}
