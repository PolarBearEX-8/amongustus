package net.minecraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class ItemNameTag extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, EntityLivingBase livingEntity) {
      if (!stack.isActive()) {
         return false;
      } else if (livingEntity instanceof EntityMob) {
         EntityMob entityMob = (EntityMob)livingEntity;
         entityMob.setLabelText(stack.getString());
         entityMob.updateState5();
         stack.animationsToGo--;
         return true;
      } else {
         return super.processAction(stack, player, livingEntity);
      }
   }

   public ItemNameTag() {
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}
