package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EntityDamageSource extends DamageSource {
   public Entity damageSourceEntity;
   private boolean isThornsDamage = false;

   public EntityDamageSource(String str, Entity entity) {
      super(str);
      this.damageSourceEntity = entity;
   }

   @Override
   public boolean isDifficultyScaled() {
      return this.damageSourceEntity != null && this.damageSourceEntity instanceof EntityLivingBase && !(this.damageSourceEntity instanceof EntityPlayer);
   }

   @Override
   public IChatComponent getDeathMessage(EntityLivingBase entity) {
      ItemStack stack = this.damageSourceEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.damageSourceEntity).getHeldItem() : null;
      String str = "death.attack." + this.damageType;
      String str2 = str + ".item";
      return stack != null && stack.hasDisplayName() && StatCollector.canTranslate(str2)
         ? new ChatComponentTranslation(str2, new Object[]{entity.getDisplayName(), this.damageSourceEntity.getDisplayName(), stack.getChatComponent()})
         : new ChatComponentTranslation(str, new Object[]{entity.getDisplayName(), this.damageSourceEntity.getDisplayName()});
   }

   @Override
   public Entity getEntity() {
      return this.damageSourceEntity;
   }

   public EntityDamageSource setIsThornsDamage() {
      this.isThornsDamage = true;
      return this;
   }

   public boolean getIsThornsDamage() {
      return this.isThornsDamage;
   }
}
