package client.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceAdapter extends DamageSource {
   public Entity entity;
   public boolean enabled9 = false;

   @Override
   public boolean isEnabled2() {
      return this.entity != null && this.entity instanceof EntityLivingBase && !(this.entity instanceof EntityPlayer);
   }

   @Override
   public Entity getEntity() {
      return this.entity;
   }

   public DamageSourceAdapter getDamageSourceAdapter() {
      this.enabled9 = true;
      return this;
   }

   public boolean isEnabled9() {
      return this.enabled9;
   }

   @Override
   public IChatComponent resolveIChatComponent(EntityLivingBase livingEntity) {
      ItemStack stack = this.entity instanceof EntityLivingBase ? ((EntityLivingBase)this.entity).getItemStack() : null;
      String text = "death.attack." + this.labelText;
      String text2 = text + ".item";
      return stack != null && stack.isActive() && I18n.matchesText(text2)
         ? new BaseManagerStyleIChatComponentAdapter(text2, new Object[]{livingEntity.getChatComponent(), this.entity.getChatComponent(), stack.getIChatComponent()})
         : new BaseManagerStyleIChatComponentAdapter(text, new Object[]{livingEntity.getChatComponent(), this.entity.getChatComponent()});
   }

   public DamageSourceAdapter(String text, Entity entity2) {
      super(text);
      this.entity = entity2;
   }
}
