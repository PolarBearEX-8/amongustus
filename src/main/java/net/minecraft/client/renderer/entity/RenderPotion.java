package net.minecraft.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderPotion extends RenderSnowball {
   @Override
   public ItemStack getStackToRender(Entity entity) {
      return this.getStackToRender2((EntityPotion)entity);
   }

   public RenderPotion(RenderManager renderManager2, RenderItem renderItem) {
      super(renderManager2, Items.potionitem, renderItem);
   }

   public ItemStack getStackToRender2(EntityPotion entityPotion) {
      return new ItemStack(this.item, 1, entityPotion.getPotionDamage());
   }
}
