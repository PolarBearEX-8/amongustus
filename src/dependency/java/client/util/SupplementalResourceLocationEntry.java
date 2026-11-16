package client.util;

import client.event.PreMotionConsumer;
import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.item.DiamondCrystal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class SupplementalResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/guardian.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/guardian_elder.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/guardian_beam.png");
   public int count = ((DiamondCrystal)this.passiveFloatEntry).getCount();

   public void applyEntityGuardian(EntityGuardian entityGuardian, float factor) {
      if (entityGuardian.isActive32()) {
         GlStateManager.translate3f3(2.35F, 2.35F, 2.35F);
      }
   }

   private Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, double offset, float factor) {
      double offset2 = livingEntity.lastTickPosX + (livingEntity.posX - livingEntity.lastTickPosX) * (double)factor;
      double offset3 = offset + livingEntity.lastTickPosY + (livingEntity.posY - livingEntity.lastTickPosY) * (double)factor;
      double offset4 = livingEntity.lastTickPosZ + (livingEntity.posZ - livingEntity.lastTickPosZ) * (double)factor;
      return new Vec3(offset2, offset3, offset4);
   }

   public ResourceLocation resolveResourceLocation(EntityGuardian entityGuardian) {
      return entityGuardian.isActive32() ? resourceLocation5 : resourceLocation4;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityGuardian((EntityGuardian)livingEntity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityGuardian((EntityGuardian)livingEntity, factor);
   }

   public void applyEntityGuardian(EntityGuardian entityGuardian, double posX, double posY, double posZ, float factor, float factor2) {
      if (this.count != ((DiamondCrystal)this.passiveFloatEntry).getCount()) {
         this.passiveFloatEntry = new DiamondCrystal();
         this.count = ((DiamondCrystal)this.passiveFloatEntry).getCount();
      }

      super.applyEntityMob(entityGuardian, posX, posY, posZ, factor, factor2);
      EntityLivingBase entityLivingBase = entityGuardian.getEntityLivingBase7();
      if (entityLivingBase != null) {
         float factor3 = entityGuardian.computeFactor4(factor2);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         this.applyResourceLocation2(resourceLocation3);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.applyState3();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.depthMask(true);
         float factor4 = 240.0F;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, factor4, factor4);
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         float factor5 = (float)entityGuardian.worldObj.getTimestamp2() + factor2;
         float factor6 = factor5 * 0.5F % 1.0F;
         float height = entityGuardian.getHeight2();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)posX, (float)posY + height, (float)posZ);
         Vec3 vec = this.resolveVecByEntityLivingBase(entityLivingBase, (double)entityLivingBase.height * 0.5, factor2);
         Vec3 vec2 = this.resolveVecByEntityLivingBase(entityGuardian, (double)height, factor2);
         Vec3 vec3 = vec.getVec311(vec2);
         double offset = vec3.getValueAsDouble() + 1.0;
         Vec3 vec4 = vec3.normalize();
         float factor7 = (float)Math.acos(vec4.yCoord);
         float factor8 = (float)Math.atan2(vec4.zCoord, vec4.xCoord);
         GlStateManager.rotate(((float) (Math.PI / 2) + -factor8) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(factor7 * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         double offset2 = (double)factor5 * 0.05 * (1.0 - (double)1 * 2.5);
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         float factor9 = factor3 * factor3;
         int count2 = 64 + (int)(factor9 * 240.0F);
         int count3 = 32 + (int)(factor9 * 192.0F);
         int count4 = 128 - (int)(factor9 * 64.0F);
         double offset3 = (double)1 * 0.2;
         double offset4 = offset3 * 1.41;
         double offset5 = 0.0 + Math.cos(offset2 + (Math.PI * 3.0 / 4.0)) * offset4;
         double offset6 = 0.0 + Math.sin(offset2 + (Math.PI * 3.0 / 4.0)) * offset4;
         double offset7 = 0.0 + Math.cos(offset2 + (Math.PI / 4)) * offset4;
         double offset8 = 0.0 + Math.sin(offset2 + (Math.PI / 4)) * offset4;
         double offset9 = 0.0 + Math.cos(offset2 + (Math.PI * 5.0 / 4.0)) * offset4;
         double offset10 = 0.0 + Math.sin(offset2 + (Math.PI * 5.0 / 4.0)) * offset4;
         double offset11 = 0.0 + Math.cos(offset2 + (Math.PI * 7.0 / 4.0)) * offset4;
         double offset12 = 0.0 + Math.sin(offset2 + (Math.PI * 7.0 / 4.0)) * offset4;
         double offset13 = 0.0 + Math.cos(offset2 + Math.PI) * offset3;
         double offset14 = 0.0 + Math.sin(offset2 + Math.PI) * offset3;
         double offset15 = 0.0 + Math.cos(offset2 + 0.0) * offset3;
         double offset16 = 0.0 + Math.sin(offset2 + 0.0) * offset3;
         double offset17 = 0.0 + Math.cos(offset2 + (Math.PI / 2)) * offset3;
         double offset18 = 0.0 + Math.sin(offset2 + (Math.PI / 2)) * offset3;
         double offset19 = 0.0 + Math.cos(offset2 + (Math.PI * 3.0 / 2.0)) * offset3;
         double offset20 = 0.0 + Math.sin(offset2 + (Math.PI * 3.0 / 2.0)) * offset3;
         double offset21 = (double)(-1.0F + factor6);
         double offset22 = offset * (0.5 / offset3) + offset21;
         worldRenderer.getWorldRenderer(offset13, offset, offset14).getWorldRenderer6(0.4999, offset22).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset13, 0.0, offset14).getWorldRenderer6(0.4999, offset21).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset15, 0.0, offset16).getWorldRenderer6(0.0, offset21).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset15, offset, offset16).getWorldRenderer6(0.0, offset22).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset17, offset, offset18).getWorldRenderer6(0.4999, offset22).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset17, 0.0, offset18).getWorldRenderer6(0.4999, offset21).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset19, 0.0, offset20).getWorldRenderer6(0.0, offset21).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset19, offset, offset20).getWorldRenderer6(0.0, offset22).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         double offset23 = 0.0;
         if (entityGuardian.ticksExisted % 2 == 0) {
            offset23 = 0.5;
         }

         worldRenderer.getWorldRenderer(offset5, offset, offset6).getWorldRenderer6(0.5, offset23 + 0.5).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset7, offset, offset8).getWorldRenderer6(1.0, offset23 + 0.5).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset11, offset, offset12).getWorldRenderer6(1.0, offset23).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         worldRenderer.getWorldRenderer(offset9, offset, offset10).getWorldRenderer6(0.5, offset23).resolveWorldRenderer(count2, count3, count4, 255).updateState();
         tessellator.updateState();
         GlStateManager.popMatrix();
      }
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      return this.matchesEntityGuardian((EntityGuardian)entity, preMotionConsumer, posX, posY, posZ);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityGuardian((EntityGuardian)entityMob, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityGuardian((EntityGuardian)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityGuardian)entity);
   }

   public boolean matchesEntityGuardian(EntityGuardian entityGuardian, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      if (super.matchesEntityMob(entityGuardian, preMotionConsumer, posX, posY, posZ)) {
         return true;
      } else {
         if (entityGuardian.isActive33()) {
            EntityLivingBase entityLivingBase = entityGuardian.getEntityLivingBase7();
            if (entityLivingBase != null) {
               Vec3 vec = this.resolveVecByEntityLivingBase(entityLivingBase, (double)entityLivingBase.height * 0.5, 1.0F);
               Vec3 vec2 = this.resolveVecByEntityLivingBase(entityGuardian, (double)entityGuardian.getHeight2(), 1.0F);
               if (preMotionConsumer.checkAxisAlignedBB(
                  AxisAlignedBB.resolveAxisAlignedBB(vec2.xCoord, vec2.yCoord, vec2.zCoord, vec.xCoord, vec.yCoord, vec.zCoord)
               )) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public boolean matchesEntityMob(EntityMob entityMob, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      return this.matchesEntityGuardian((EntityGuardian)entityMob, preMotionConsumer, posX, posY, posZ);
   }

   public SupplementalResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new DiamondCrystal(), 0.5F);
   }
}
