package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CoreCustom implements Predicate {
   public final ItemStack itemStack;

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public CoreCustom(ItemStack stack) {
      this.itemStack = stack;
   }

   public boolean matchesEntity(Entity entity) {
      if (!entity.canExecute()) {
         return false;
      } else if (!(entity instanceof EntityLivingBase)) {
         return false;
      } else {
         EntityLivingBase livingEntity = (EntityLivingBase)entity;
         return livingEntity.resolveItemStack(EntityMob.computeCount(this.itemStack)) != null
            ? false
            : (livingEntity instanceof EntityMob ? ((EntityMob)livingEntity).isEnabled22() : (livingEntity instanceof EntityArmorstand ? true : livingEntity instanceof EntityPlayer));
      }
   }
}
