package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderSnowball extends Render {
   public final Item item;
   private final RenderItem itemRenderer;

   public ItemStack getStackToRender(Entity entity) {
      return new ItemStack(this.item, 1, 0);
   }

   public RenderSnowball(RenderManager renderManager2, Item item2, RenderItem renderItem) {
      super(renderManager2);
      this.item = item2;
      this.itemRenderer = renderItem;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      this.bindTexture(TextureMap.locationBlocksTexture);
      this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(entity, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return TextureMap.locationBlocksTexture;
   }
}
