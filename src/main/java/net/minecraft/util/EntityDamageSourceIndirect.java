package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityDamageSourceIndirect extends EntityDamageSource {
   private Entity indirectEntity;

   @Override
   public Entity getEntity() {
      return this.indirectEntity;
   }

   @Override
   public IChatComponent getDeathMessage(EntityLivingBase entity) {
      IChatComponent component = this.indirectEntity == null ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
      ItemStack stack = this.indirectEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.indirectEntity).getHeldItem() : null;
      String str = "death.attack." + this.damageType;
      String str2 = str + ".item";
      return stack != null && stack.hasDisplayName() && StatCollector.canTranslate(str2)
         ? new ChatComponentTranslation(str2, new Object[]{entity.getDisplayName(), component, stack.getChatComponent()})
         : new ChatComponentTranslation(str, new Object[]{entity.getDisplayName(), component});
   }

   public EntityDamageSourceIndirect(String str, Entity entity, Entity entity2) {
      super(str, entity);
      this.indirectEntity = entity2;
   }

   @Override
   public Entity getSourceOfDamage() {
      return this.damageSourceEntity;
   }
}
