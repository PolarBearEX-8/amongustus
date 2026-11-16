package client.render;

import client.ClientGlobals;
import client.event.PreMotionConsumer;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.PassiveFloatEntry;
import client.util.PreEndHandManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.CustomEntityAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;

public abstract class InternalRendererLivingEntityAdapter extends RendererLivingEntity {
   public boolean matchesEntityMob(EntityMob entityMob) {
      return super.matchesEntityLivingBase(entityMob)
         && !ClientGlobals.moduleManager.antiStrike.canExecute9()
         && entityMob.isState1()
         && (entityMob.isActive13() || entityMob.processAction() && entityMob == this.renderManager.entity2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityMob((EntityMob)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityMob(EntityMob entityMob, float factor) {
      int count = entityMob.computeCount(factor);
      int count2 = count % 65536;
      int count3 = count / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity) {
      return this.matchesEntityMob((EntityMob)entity);
   }

   private double computeValue(double posX, double posY, double posZ) {
      return posX + (posY - posX) * posZ;
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      return this.matchesEntityMob((EntityMob)entity, preMotionConsumer, posX, posY, posZ);
   }

   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      super.applyEntityLivingBase(entityMob, posX, posY, posZ, factor, factor2);
      this.applyEntityMobByEntityMob(entityMob, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityMobByEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      if (!BuildManager.canExecute() || !PreEndHandManager.enabled12) {
         Entity entity = entityMob.getEntity6();
         if (entity != null) {
            double offset = posY - (1.6 - (double)entityMob.height) * 0.5;
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            double offset2 = this.computeValue((double)entity.prevRotationYaw, (double)entity.rotationYaw, (double)(factor2 * 0.5F)) * (float) (Math.PI / 180.0);
            double offset3 = this.computeValue((double)entity.prevRotationPitch, (double)entity.rotationPitch, (double)(factor2 * 0.5F)) * (float) (Math.PI / 180.0);
            double offset4 = Math.cos(offset2);
            double offset5 = Math.sin(offset2);
            double offset6 = Math.sin(offset3);
            if (entity instanceof CustomEntityAdapter) {
               offset4 = 0.0;
               offset5 = 0.0;
               offset6 = -1.0;
            }

            double offset7 = Math.cos(offset3);
            double offset8 = this.computeValue(entity.prevPosX, entity.posX, (double)factor2) - offset4 * 0.7 - offset5 * 0.5 * offset7;
            double offset9 = this.computeValue(entity.prevPosY + (double)entity.getHeight2() * 0.7, entity.posY + (double)entity.getHeight2() * 0.7, (double)factor2)
               - offset6 * 0.5
               - 0.25;
            double offset10 = this.computeValue(entity.prevPosZ, entity.posZ, (double)factor2) - offset5 * 0.7 + offset4 * 0.5 * offset7;
            double offset11 = this.computeValue((double)entityMob.factor12, (double)entityMob.scaleFactor8, (double)factor2) * (float) (Math.PI / 180.0) + (Math.PI / 2);
            double offset12 = Math.cos(offset11) * (double)entityMob.width * 0.4;
            double offset13 = Math.sin(offset11) * (double)entityMob.width * 0.4;
            double offset14 = this.computeValue(entityMob.prevPosX, entityMob.posX, (double)factor2) + offset12;
            double offset15 = this.computeValue(entityMob.prevPosY, entityMob.posY, (double)factor2);
            double offset16 = this.computeValue(entityMob.prevPosZ, entityMob.posZ, (double)factor2) + offset13;
            double offset17 = posX + offset12;
            double offset18 = posZ + offset13;
            double offset19 = (double)((float)(offset8 - offset14));
            double offset20 = (double)((float)(offset9 - offset15));
            double offset21 = (double)((float)(offset10 - offset16));
            GlStateManager.enableTexture2D();
            GlStateManager.applyState3();
            GlStateManager.disableAlpha();
            if (BuildManager.canExecute()) {
               PreEndHandManager.updateState44();
            }

            worldRenderer.updateAuxiliaryLoggerEntry(5, DefaultVertexFormats.auxiliaryLoggerEntry3);

            for (int index = 0; index <= 24; index++) {
               float factor3 = 0.5F;
               float factor4 = 0.4F;
               float factor5 = 0.3F;
               if (index % 2 == 0) {
                  factor3 *= 0.7F;
                  factor4 *= 0.7F;
                  factor5 *= 0.7F;
               }

               float factor6 = (float)index / 24.0F;
               worldRenderer.getWorldRenderer(
                     offset17 + offset19 * (double)factor6 + 0.0,
                     offset + offset20 * (double)(factor6 * factor6 + factor6) * 0.5 + (double)((24.0F - (float)index) / 18.0F + 0.125F),
                     offset18 + offset21 * (double)factor6
                  )
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(
                     offset17 + offset19 * (double)factor6 + 0.025,
                     offset + offset20 * (double)(factor6 * factor6 + factor6) * 0.5 + (double)((24.0F - (float)index) / 18.0F + 0.125F) + 0.025,
                     offset18 + offset21 * (double)factor6
                  )
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
            }

            tessellator.updateState();
            worldRenderer.updateAuxiliaryLoggerEntry(5, DefaultVertexFormats.auxiliaryLoggerEntry3);

            for (int index2 = 0; index2 <= 24; index2++) {
               float factor7 = 0.5F;
               float factor8 = 0.4F;
               float factor9 = 0.3F;
               if (index2 % 2 == 0) {
                  factor7 *= 0.7F;
                  factor8 *= 0.7F;
                  factor9 *= 0.7F;
               }

               float factor10 = (float)index2 / 24.0F;
               worldRenderer.getWorldRenderer(
                     offset17 + offset19 * (double)factor10 + 0.0,
                     offset + offset20 * (double)(factor10 * factor10 + factor10) * 0.5 + (double)((24.0F - (float)index2) / 18.0F + 0.125F) + 0.025,
                     offset18 + offset21 * (double)factor10
                  )
                  .resolveWorldRenderer(factor7, factor8, factor9, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(
                     offset17 + offset19 * (double)factor10 + 0.025,
                     offset + offset20 * (double)(factor10 * factor10 + factor10) * 0.5 + (double)((24.0F - (float)index2) / 18.0F + 0.125F),
                     offset18 + offset21 * (double)factor10 + 0.025
                  )
                  .resolveWorldRenderer(factor7, factor8, factor9, 1.0F)
                  .updateState();
            }

            tessellator.updateState();
            if (BuildManager.canExecute()) {
               PreEndHandManager.updateState15();
            }

            GlStateManager.applyState13();
            GlStateManager.disableTexture2D();
            GlStateManager.enableLighting();
         }
      }
   }

   @Override
   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return this.matchesEntityMob((EntityMob)livingEntity);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityMob((EntityMob)entity, posX, posY, posZ, factor, factor2);
   }

   public boolean matchesEntityMob(EntityMob entityMob, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      if (super.matchesEntityByEntity(entityMob, preMotionConsumer, posX, posY, posZ)) {
         return true;
      } else if (entityMob.isEnabled23() && entityMob.getEntity6() != null) {
         Entity entity = entityMob.getEntity6();
         return preMotionConsumer.checkAxisAlignedBB(entity.getBoundingBox2());
      } else {
         return false;
      }
   }

   public InternalRendererLivingEntityAdapter(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
