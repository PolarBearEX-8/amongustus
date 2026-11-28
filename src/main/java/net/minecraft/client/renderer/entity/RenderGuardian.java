package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderGuardian extends RenderLiving {
   private static final ResourceLocation GUARDIAN_TEXTURE = new ResourceLocation("textures/entity/guardian.png");
   private static final ResourceLocation GUARDIAN_ELDER_TEXTURE = new ResourceLocation("textures/entity/guardian_elder.png");
   private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");
   public int modelVersion = ((ModelGuardian)this.mainModel).getModelVersion();

   @Override
   public boolean shouldRender(Entity entity, ICamera camera, double d, double d2, double d3) {
      return this.shouldRender((EntityGuardian)entity, camera, d, d2, d3);
   }

   public ResourceLocation getEntityTexture(EntityGuardian entityGuardian) {
      return entityGuardian.isElder() ? GUARDIAN_ELDER_TEXTURE : GUARDIAN_TEXTURE;
   }

   public void doRender(EntityGuardian entityGuardian, double d, double d2, double d3, float f, float f2) {
      if (this.modelVersion != ((ModelGuardian)this.mainModel).getModelVersion()) {
         this.mainModel = new ModelGuardian();
         this.modelVersion = ((ModelGuardian)this.mainModel).getModelVersion();
      }

      super.doRender((EntityLiving)entityGuardian, d, d2, d3, f, f2);
      EntityLivingBase targetedEntity = entityGuardian.getTargetedEntity();
      if (targetedEntity != null) {
         float attackAnimationScale = entityGuardian.getAttackAnimationScale(f2);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         this.bindTexture(GUARDIAN_BEAM_TEXTURE);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         float f3 = 240.0F;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f3, f3);
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         float f4 = (float)entityGuardian.entityWorld.getTotalWorldTime() + f2;
         float f5 = f4 * 0.5F % 1.0F;
         float eyeHeight = entityGuardian.getEyeHeight();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)d, (float)d2 + eyeHeight, (float)d3);
         Vec3 vec = this.getPosition(targetedEntity, (double)targetedEntity.height * 0.5, f2).subtract(this.getPosition(entityGuardian, (double)eyeHeight, f2));
         double d4 = vec.lengthVector() + 1.0;
         vec = vec.normalize();
         float f6 = (float)Math.acos(vec.yCoord);
         float f7 = (float)Math.atan2(vec.zCoord, vec.xCoord);
         GlStateManager.rotate(((float) (Math.PI / 2) + -f7) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(f6 * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         byte b = 1;
         double d5 = (double)f4 * 0.05 * (1.0 - (double)(b & 1) * 2.5);
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         float f8 = attackAnimationScale * attackAnimationScale;
         int n = 64 + (int)(f8 * 240.0F);
         int n2 = 32 + (int)(f8 * 192.0F);
         int n3 = 128 - (int)(f8 * 64.0F);
         double d6 = (double)b * 0.2;
         double d7 = d6 * 1.41;
         double d8 = 0.0 + Math.cos(d5 + (Math.PI * 3.0 / 4.0)) * d7;
         double d9 = 0.0 + Math.sin(d5 + (Math.PI * 3.0 / 4.0)) * d7;
         double d10 = 0.0 + Math.cos(d5 + (Math.PI / 4)) * d7;
         double d11 = 0.0 + Math.sin(d5 + (Math.PI / 4)) * d7;
         double d12 = 0.0 + Math.cos(d5 + (Math.PI * 5.0 / 4.0)) * d7;
         double d13 = 0.0 + Math.sin(d5 + (Math.PI * 5.0 / 4.0)) * d7;
         double d14 = 0.0 + Math.cos(d5 + (Math.PI * 7.0 / 4.0)) * d7;
         double d15 = 0.0 + Math.sin(d5 + (Math.PI * 7.0 / 4.0)) * d7;
         double d16 = 0.0 + Math.cos(d5 + Math.PI) * d6;
         double d17 = 0.0 + Math.sin(d5 + Math.PI) * d6;
         double d18 = 0.0 + Math.cos(d5 + 0.0) * d6;
         double d19 = 0.0 + Math.sin(d5 + 0.0) * d6;
         double d20 = 0.0 + Math.cos(d5 + (Math.PI / 2)) * d6;
         double d21 = 0.0 + Math.sin(d5 + (Math.PI / 2)) * d6;
         double d22 = 0.0 + Math.cos(d5 + (Math.PI * 3.0 / 2.0)) * d6;
         double d23 = 0.0 + Math.sin(d5 + (Math.PI * 3.0 / 2.0)) * d6;
         double d24 = 0.0;
         double d25 = 0.4999;
         double d26 = (double)(-1.0F + f5);
         double d27 = d4 * (0.5 / d6) + d26;
         worldRenderer.pos(d16, d4, d17).tex(0.4999, d27).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d16, 0.0, d17).tex(0.4999, d26).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d18, 0.0, d19).tex(0.0, d26).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d18, d4, d19).tex(0.0, d27).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d20, d4, d21).tex(0.4999, d27).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d20, 0.0, d21).tex(0.4999, d26).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d22, 0.0, d23).tex(0.0, d26).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d22, d4, d23).tex(0.0, d27).color(n, n2, n3, 255).endVertex();
         double d28 = 0.0;
         if (entityGuardian.ticksExisted % 2 == 0) {
            d28 = 0.5;
         }

         worldRenderer.pos(d8, d4, d9).tex(0.5, d28 + 0.5).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d10, d4, d11).tex(1.0, d28 + 0.5).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d14, d4, d15).tex(1.0, d28).color(n, n2, n3, 255).endVertex();
         worldRenderer.pos(d12, d4, d13).tex(0.5, d28).color(n, n2, n3, 255).endVertex();
         instance.draw();
         GlStateManager.popMatrix();
      }
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityGuardian)entity);
   }

   @Override
   public boolean shouldRender(EntityLiving entityLiving, ICamera camera, double d, double d2, double d3) {
      return this.shouldRender((EntityGuardian)entityLiving, camera, d, d2, d3);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityGuardian)entity, f);
   }

   private Vec3 getPosition(EntityLivingBase entity, double d, float f) {
      double d2 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      double d3 = d + entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      double d4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
      return new Vec3(d2, d3, d4);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityGuardian)entity, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityGuardian)entity, d, d2, d3, f, f2);
   }

   public RenderGuardian(RenderManager renderManager2) {
      super(renderManager2, new ModelGuardian(), 0.5F);
   }

   public void preRenderCallback(EntityGuardian entityGuardian, float f) {
      if (entityGuardian.isElder()) {
         GlStateManager.scale(2.35F, 2.35F, 2.35F);
      }
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityGuardian)entityLiving, d, d2, d3, f, f2);
   }

   public boolean shouldRender(EntityGuardian entityGuardian, ICamera camera, double d, double d2, double d3) {
      if (super.shouldRender((EntityLiving)entityGuardian, camera, d, d2, d3)) {
         return true;
      } else {
         if (entityGuardian.hasTargetedEntity()) {
            EntityLivingBase targetedEntity = entityGuardian.getTargetedEntity();
            if (targetedEntity != null) {
               Vec3 position = this.getPosition(targetedEntity, (double)targetedEntity.height * 0.5, 1.0F);
               Vec3 position2 = this.getPosition(entityGuardian, (double)entityGuardian.getEyeHeight(), 1.0F);
               if (camera.isBoundingBoxInFrustum(AxisAlignedBB.fromBounds(position2.xCoord, position2.yCoord, position2.zCoord, position.xCoord, position.yCoord, position.zCoord))) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
