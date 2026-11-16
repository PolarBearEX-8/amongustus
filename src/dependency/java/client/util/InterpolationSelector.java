package client.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public class InterpolationSelector extends DamageSourceAdapter {
   public Entity entity2;

   @Override
   public IChatComponent resolveIChatComponent(EntityLivingBase livingEntity) {
      IChatComponent iChatComponent = this.entity2 == null ? this.entity.getChatComponent() : this.entity2.getChatComponent();
      ItemStack stack = this.entity2 instanceof EntityLivingBase ? ((EntityLivingBase)this.entity2).getItemStack() : null;
      String text = "death.attack." + this.labelText;
      String text2 = text + ".item";
      return stack != null && stack.isActive() && I18n.matchesText(text2)
         ? new BaseManagerStyleIChatComponentAdapter(text2, new Object[]{livingEntity.getChatComponent(), iChatComponent, stack.getIChatComponent()})
         : new BaseManagerStyleIChatComponentAdapter(text, new Object[]{livingEntity.getChatComponent(), iChatComponent});
   }

   public InterpolationSelector(String text, Entity entity, Entity entity3) {
      super(text, entity);
      this.entity2 = entity3;
   }

   @Override
   public Entity getEntityAsEntity() {
      return this.entity;
   }

   @Override
   public Entity getEntity() {
      return this.entity2;
   }
}
