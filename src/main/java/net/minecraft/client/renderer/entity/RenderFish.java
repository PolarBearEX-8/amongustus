package net.minecraft.client.renderer.entity;

import net.augustus.ClientManagers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderFish extends Render {
   private static final ResourceLocation FISH_PARTICLES = new ResourceLocation("textures/particle/particles.png");

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityFishHook)entity, d, d2, d3, f, f2);
   }

   public void doRender(EntityFishHook entityFishHook, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      this.bindEntityTexture(entityFishHook);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      float f3 = 0.0625F;
      float f4 = 0.125F;
      float f5 = 0.125F;
      float f6 = 0.1875F;
      float f7 = 1.0F;
      float f8 = 0.5F;
      float f9 = 0.5F;
      GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      worldRenderer.pos(-0.5, -0.5, 0.0).tex(0.0625, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(0.5, -0.5, 0.0).tex(0.125, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(0.5, 0.5, 0.0).tex(0.125, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(-0.5, 0.5, 0.0).tex(0.0625, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
      instance.draw();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      if (entityFishHook.angler != null) {
         float f10 = MathHelper.sin(MathHelper.sqrt_float(entityFishHook.angler.getSwingProgress(f2)) * 3.1415927F);
         Vec3 rodTipOffset = this.getRodTipOffset();
         rodTipOffset = rodTipOffset.rotatePitch(-(entityFishHook.angler.prevRotationPitch + (entityFishHook.angler.rotationPitch - entityFishHook.angler.prevRotationPitch) * f2) * 3.1415927F / 180.0F);
         rodTipOffset = rodTipOffset.rotateYaw(-(entityFishHook.angler.prevRotationYaw + (entityFishHook.angler.rotationYaw - entityFishHook.angler.prevRotationYaw) * f2) * 3.1415927F / 180.0F);
         rodTipOffset = rodTipOffset.rotateYaw(f10 * 0.5F);
         rodTipOffset = rodTipOffset.rotatePitch(-f10 * 0.7F);
         double d4 = entityFishHook.angler.prevPosX + (entityFishHook.angler.posX - entityFishHook.angler.prevPosX) * (double)f2 + rodTipOffset.xCoord;
         double d5 = entityFishHook.angler.prevPosY + (entityFishHook.angler.posY - entityFishHook.angler.prevPosY) * (double)f2 + rodTipOffset.yCoord;
         double d6 = entityFishHook.angler.prevPosZ + (entityFishHook.angler.posZ - entityFishHook.angler.prevPosZ) * (double)f2 + rodTipOffset.zCoord;
         double eyeHeight = (double)entityFishHook.angler.getEyeHeight();
         if (this.renderManager.options != null && this.renderManager.options.thirdPersonView > 0 || entityFishHook.angler != Minecraft.getMinecraft().thePlayer) {
            float f11 = (entityFishHook.angler.prevRenderYawOffset + (entityFishHook.angler.renderYawOffset - entityFishHook.angler.prevRenderYawOffset) * f2) * 3.1415927F / 180.0F;
            double d7 = (double)MathHelper.sin(f11);
            double d8 = (double)MathHelper.cos(f11);
            double d9 = 0.35;
            double d10 = 0.8;
            d4 = entityFishHook.angler.prevPosX + (entityFishHook.angler.posX - entityFishHook.angler.prevPosX) * (double)f2 - d8 * 0.35 - d7 * 0.8;
            d5 = entityFishHook.angler.prevPosY + eyeHeight + (entityFishHook.angler.posY - entityFishHook.angler.prevPosY) * (double)f2 - 0.45;
            d6 = entityFishHook.angler.prevPosZ + (entityFishHook.angler.posZ - entityFishHook.angler.prevPosZ) * (double)f2 - d7 * 0.35 + d8 * 0.8;
            eyeHeight = entityFishHook.angler.isSneaking() ? -0.1875 : 0.0;
         }

         double d11 = entityFishHook.prevPosX + (entityFishHook.posX - entityFishHook.prevPosX) * (double)f2;
         double d12 = entityFishHook.prevPosY + (entityFishHook.posY - entityFishHook.prevPosY) * (double)f2 + 0.25;
         double d13 = entityFishHook.prevPosZ + (entityFishHook.posZ - entityFishHook.prevPosZ) * (double)f2;
         double d14 = (double)((float)(d4 - d11));
         double d15 = (double)((float)(d5 - d12)) + eyeHeight;
         double d16 = (double)((float)(d6 - d13));
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);

         for (int i = 0; i <= 16; i++) {
            float f12 = (float)i / 16.0F;
            worldRenderer.pos(d + d14 * (double)f12, d2 + d15 * (double)(f12 * f12 + f12) * 0.5 + 0.25, d3 + d16 * (double)f12)
               .color(0, 0, 0, 255)
               .endVertex();
         }

         instance.draw();
         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
         super.doRender(entityFishHook, d, d2, d3, f, f2);
      }
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityFishHook)entity);
   }

   public ResourceLocation getEntityTexture(EntityFishHook entityFishHook) {
      return FISH_PARTICLES;
   }

   public Vec3 getRodTipOffset() {
      if (!ClientManagers.moduleManager.legacyAnimations.isLegacyRodEnabled()) {
         return new Vec3(-0.36, 0.03, 0.35);
      } else {
         double d = ((double)Minecraft.getMinecraft().gameSettings.fovSetting) / 110.0;
         return new Vec3(-d + d / 2.5 - d / 8.0 + 0.16, 0.0, 0.4);
      }
   }

   public RenderFish(RenderManager renderManager2) {
      super(renderManager2);
   }
}
