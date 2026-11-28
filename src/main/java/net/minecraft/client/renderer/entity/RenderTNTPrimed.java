package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderTNTPrimed extends Render {
   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityTNTPrimed)entity);
   }

   public RenderTNTPrimed(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.5F;
   }

   public ResourceLocation getEntityTexture(EntityTNTPrimed entityTNTPrimed) {
      return TextureMap.locationBlocksTexture;
   }

   public void doRender(EntityTNTPrimed entityTNTPrimed, double d, double d2, double d3, float f, float f2) {
      BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2 + 0.5F, (float)d3);
      if ((float)entityTNTPrimed.fuse - f2 + 1.0F < 10.0F) {
         float f3 = 1.0F - ((float)entityTNTPrimed.fuse - f2 + 1.0F) / 10.0F;
         f3 = MathHelper.clamp_float(f3, 0.0F, 1.0F);
         f3 *= f3;
         f3 *= f3;
         float f4 = 1.0F + f3 * 0.3F;
         GlStateManager.scale(f4, f4, f4);
      }

      float f5 = (1.0F - ((float)entityTNTPrimed.fuse - f2 + 1.0F) / 100.0F) * 0.8F;
      this.bindEntityTexture(entityTNTPrimed);
      GlStateManager.translate(-0.5F, -0.5F, 0.5F);
      blockRendererDispatcher.renderBlockBrightness(Blocks.tnt.getDefaultState(), entityTNTPrimed.getBrightness(f2));
      GlStateManager.translate(0.0F, 0.0F, 1.0F);
      if (entityTNTPrimed.fuse / 5 % 2 == 0) {
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 772);
         GlStateManager.color(1.0F, 1.0F, 1.0F, f5);
         GlStateManager.doPolygonOffset(-3.0F, -3.0F);
         GlStateManager.enablePolygonOffset();
         blockRendererDispatcher.renderBlockBrightness(Blocks.tnt.getDefaultState(), 1.0F);
         GlStateManager.doPolygonOffset(0.0F, 0.0F);
         GlStateManager.disablePolygonOffset();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
      }

      GlStateManager.popMatrix();
      super.doRender(entityTNTPrimed, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityTNTPrimed)entity, d, d2, d3, f, f2);
   }
}
