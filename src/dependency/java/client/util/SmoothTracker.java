package client.util;

import client.render.RenderManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SmoothTracker extends AltResourceLocationEntryAdapter {
   public ItemStack resolveItemStack(EntityThrownpotion entityThrownpotion) {
      return new ItemStack(this.item, 1, entityThrownpotion.getCount4());
   }

   @Override
   public ItemStack resolveItemStack(Entity entity) {
      return this.resolveItemStack((EntityThrownpotion)entity);
   }

   public SmoothTracker(RenderManager renderManager, RenderItem renderItem) {
      super(renderManager, Items.potion, renderItem);
   }
}
