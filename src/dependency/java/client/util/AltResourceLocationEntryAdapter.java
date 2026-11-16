package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.setting.ItemPhysicsNoneType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AltResourceLocationEntryAdapter extends EntitySkinLocation {
   public final Item item;
   public final RenderItem renderItem;

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
      GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
      this.applyResourceLocation2(ListenerAdapter27.resourceLocation);
      this.renderItem.applyItemStack(this.resolveItemStack(entity), ItemPhysicsNoneType.GROUND);
      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      super.applyEntity(entity, posX, posY, posZ, factor, factor2);
   }

   public ItemStack resolveItemStack(Entity entity) {
      return new ItemStack(this.item, 1, 0);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return ListenerAdapter27.resourceLocation;
   }

   public AltResourceLocationEntryAdapter(RenderManager renderManager, Item item2, RenderItem renderItem2) {
      super(renderManager);
      this.item = item2;
      this.renderItem = renderItem2;
   }
}
