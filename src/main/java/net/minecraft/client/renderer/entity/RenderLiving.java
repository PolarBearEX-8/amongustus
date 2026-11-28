package net.minecraft.client.renderer.entity;

import net.augustus.ClientManagers;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public abstract class RenderLiving extends RendererLivingEntity {

   private double interpolateValue(double d, double d2, double d3) {
      return d + (d2 - d) * d3;
   }

   public boolean canRenderName(EntityLiving entityLiving) {
      return super.canRenderName(entityLiving)
         && !ClientManagers.moduleManager.antiStrike.shouldHideOtherNameTags()
         && entityLiving.isRenderNameTag()
         && (entityLiving.getAlwaysRenderNameTagForRender() || entityLiving.hasCustomName() && entityLiving == this.renderManager.pointedEntity);
   }

   public RenderLiving(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityLiving)entity, d, d2, d3, f, f2);
   }

   public void renderLeash(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      if (!Config.isShaderPackLoaded() || !Shaders.isShadowPass) {
         Entity leashedToEntity = entityLiving.getLeashedToEntity();
         if (leashedToEntity != null) {
            d2 -= (1.6 - (double)entityLiving.height) * 0.5;
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            double d4 = this.interpolateValue((double)leashedToEntity.prevRotationYaw, (double)leashedToEntity.rotationYaw, (double)(f2 * 0.5F)) * 0.01745329238474369;
            double d5 = this.interpolateValue((double)leashedToEntity.prevRotationPitch, (double)leashedToEntity.rotationPitch, (double)(f2 * 0.5F)) * 0.01745329238474369;
            double d6 = Math.cos(d4);
            double d7 = Math.sin(d4);
            double d8 = Math.sin(d5);
            if (leashedToEntity instanceof EntityHanging) {
               d6 = 0.0;
               d7 = 0.0;
               d8 = -1.0;
            }

            double d9 = Math.cos(d5);
            double d10 = this.interpolateValue(leashedToEntity.prevPosX, leashedToEntity.posX, (double)f2) - d6 * 0.7 - d7 * 0.5 * d9;
            double d11 = this.interpolateValue(leashedToEntity.prevPosY + (double)leashedToEntity.getEyeHeight() * 0.7, leashedToEntity.posY + (double)leashedToEntity.getEyeHeight() * 0.7, (double)f2)
               - d8 * 0.5
               - 0.25;
            double d12 = this.interpolateValue(leashedToEntity.prevPosZ, leashedToEntity.posZ, (double)f2) - d7 * 0.7 + d6 * 0.5 * d9;
            double d13 = this.interpolateValue((double)entityLiving.prevRenderYawOffset, (double)entityLiving.renderYawOffset, (double)f2) * 0.01745329238474369 + 1.5707963267948966;
            d6 = Math.cos(d13) * (double)entityLiving.width * 0.4;
            d7 = Math.sin(d13) * (double)entityLiving.width * 0.4;
            double d14 = this.interpolateValue(entityLiving.prevPosX, entityLiving.posX, (double)f2) + d6;
            double d15 = this.interpolateValue(entityLiving.prevPosY, entityLiving.posY, (double)f2);
            double d16 = this.interpolateValue(entityLiving.prevPosZ, entityLiving.posZ, (double)f2) + d7;
            d += d6;
            d3 += d7;
            double d17 = (double)((float)(d10 - d14));
            double d18 = (double)((float)(d11 - d15));
            double d19 = (double)((float)(d12 - d16));
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            if (Config.isShaderPackLoaded()) {
               Shaders.beginLeash();
            }

            double d20 = 0.025;
            worldRenderer.begin(5, DefaultVertexFormats.POSITION_COLOR);

            for (int i = 0; i <= 24; i++) {
               float f3 = 0.5F;
               float f4 = 0.4F;
               float f5 = 0.3F;
               if (i % 2 == 0) {
                  f3 *= 0.7F;
                  f4 *= 0.7F;
                  f5 *= 0.7F;
               }

               float f6 = (float)i / 24.0F;
               worldRenderer.pos(
                     d + d17 * (double)f6 + 0.0,
                     d2 + d18 * (double)(f6 * f6 + f6) * 0.5 + (double)((24.0F - (float)i) / 18.0F + 0.125F),
                     d3 + d19 * (double)f6
                  )
                  .color(f3, f4, f5, 1.0F)
                  .endVertex();
               worldRenderer.pos(
                     d + d17 * (double)f6 + 0.025,
                     d2 + d18 * (double)(f6 * f6 + f6) * 0.5 + (double)((24.0F - (float)i) / 18.0F + 0.125F) + 0.025,
                     d3 + d19 * (double)f6
                  )
                  .color(f3, f4, f5, 1.0F)
                  .endVertex();
            }

            instance.draw();
            worldRenderer.begin(5, DefaultVertexFormats.POSITION_COLOR);

            for (int j = 0; j <= 24; j++) {
               float f7 = 0.5F;
               float f8 = 0.4F;
               float f9 = 0.3F;
               if (j % 2 == 0) {
                  f7 *= 0.7F;
                  f8 *= 0.7F;
                  f9 *= 0.7F;
               }

               float f10 = (float)j / 24.0F;
               worldRenderer.pos(
                     d + d17 * (double)f10 + 0.0,
                     d2 + d18 * (double)(f10 * f10 + f10) * 0.5 + (double)((24.0F - (float)j) / 18.0F + 0.125F) + 0.025,
                     d3 + d19 * (double)f10
                  )
                  .color(f7, f8, f9, 1.0F)
                  .endVertex();
               worldRenderer.pos(
                     d + d17 * (double)f10 + 0.025,
                     d2 + d18 * (double)(f10 * f10 + f10) * 0.5 + (double)((24.0F - (float)j) / 18.0F + 0.125F),
                     d3 + d19 * (double)f10 + 0.025
                  )
                  .color(f7, f8, f9, 1.0F)
                  .endVertex();
            }

            instance.draw();
            if (Config.isShaderPackLoaded()) {
               Shaders.endLeash();
            }

            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
         }
      }
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityLiving)entity, d, d2, d3, f, f2);
   }

   @Override
   public boolean canRenderName(Entity entity) {
      return this.canRenderName((EntityLiving)entity);
   }

   public void setLightmap(EntityLiving entityLiving, float f) {
      int brightnessForRender = entityLiving.getBrightnessForRender(f);
      int n = brightnessForRender % 65536;
      int n2 = brightnessForRender / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
   }

   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      super.doRender(entityLiving, d, d2, d3, f, f2);
      this.renderLeash(entityLiving, d, d2, d3, f, f2);
   }

   @Override
   public boolean canRenderName(EntityLivingBase entity) {
      return this.canRenderName((EntityLiving)entity);
   }

   public boolean shouldRender(EntityLiving entityLiving, ICamera camera, double d, double d2, double d3) {
      if (super.shouldRender(entityLiving, camera, d, d2, d3)) {
         return true;
      } else if (entityLiving.getLeashed() && entityLiving.getLeashedToEntity() != null) {
         return camera.isBoundingBoxInFrustum(entityLiving.getLeashedToEntity().getEntityBoundingBox());
      } else {
         return false;
      }
   }

   @Override
   public boolean shouldRender(Entity entity, ICamera camera, double d, double d2, double d3) {
      return this.shouldRender((EntityLiving)entity, camera, d, d2, d3);
   }
}
