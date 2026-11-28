package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.config.Config;

public class RenderXPOrb extends Render {
   private static final ResourceLocation experienceOrbTextures = new ResourceLocation("textures/entity/experience_orb.png");

   public RenderXPOrb(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.15F;
      this.shadowOpaque = 0.75F;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityXPOrb)entity, d, d2, d3, f, f2);
   }

   public void doRender(EntityXPOrb entityXPOrb, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      this.bindEntityTexture(entityXPOrb);
      int textureByXP = entityXPOrb.getTextureByXP();
      float f3 = (float)(textureByXP % 4 * 16 + 0) / 64.0F;
      float f4 = (float)(textureByXP % 4 * 16 + 16) / 64.0F;
      float f5 = (float)(textureByXP / 4 * 16 + 0) / 64.0F;
      float f6 = (float)(textureByXP / 4 * 16 + 16) / 64.0F;
      float f7 = 1.0F;
      float f8 = 0.5F;
      float f9 = 0.25F;
      int brightnessForRender = entityXPOrb.getBrightnessForRender(f2);
      int n = brightnessForRender % 65536;
      int n2 = brightnessForRender / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f10 = 255.0F;
      float f11 = ((float)entityXPOrb.xpColor + f2) / 2.0F;
      if (Config.isCustomColors()) {
         f11 = CustomColors.getXpOrbTimer(f11);
      }

      n2 = (int)((MathHelper.sin(f11 + 0.0F) + 1.0F) * 0.5F * 255.0F);
      int n3 = (int)((MathHelper.sin(f11 + (float) (Math.PI * 4.0 / 3.0)) + 1.0F) * 0.1F * 255.0F);
      GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      float f12 = 0.3F;
      GlStateManager.scale(0.3F, 0.3F, 0.3F);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      int n4 = n2;
      int n5 = 255;
      int n6 = n3;
      if (Config.isCustomColors()) {
         int xpOrbColor = CustomColors.getXpOrbColor(f11);
         if (xpOrbColor >= 0) {
            n4 = xpOrbColor >> 16 & 0xFF;
            n5 = xpOrbColor >> 8 & 0xFF;
            n6 = xpOrbColor >> 0 & 0xFF;
         }
      }

      worldRenderer.pos((double)(0.0F - f8), (double)(0.0F - f9), 0.0)
         .tex((double)f3, (double)f6)
         .color(n4, n5, n6, 128)
         .normal(0.0F, 1.0F, 0.0F)
         .endVertex();
      worldRenderer.pos((double)(f7 - f8), (double)(0.0F - f9), 0.0)
         .tex((double)f4, (double)f6)
         .color(n4, n5, n6, 128)
         .normal(0.0F, 1.0F, 0.0F)
         .endVertex();
      worldRenderer.pos((double)(f7 - f8), (double)(1.0F - f9), 0.0)
         .tex((double)f4, (double)f5)
         .color(n4, n5, n6, 128)
         .normal(0.0F, 1.0F, 0.0F)
         .endVertex();
      worldRenderer.pos((double)(0.0F - f8), (double)(1.0F - f9), 0.0)
         .tex((double)f3, (double)f5)
         .color(n4, n5, n6, 128)
         .normal(0.0F, 1.0F, 0.0F)
         .endVertex();
      instance.draw();
      GlStateManager.disableBlend();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(entityXPOrb, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityXPOrb)entity);
   }

   public ResourceLocation getEntityTexture(EntityXPOrb entityXPOrb) {
      return experienceOrbTextures;
   }
}
