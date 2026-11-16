package client.render;

import client.event.PreMotionDispatcher;
import client.util.BoxesManager;
import client.util.DampenerSelector;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class RendererLivingEntityEntry implements PreMotionDispatcher {
   public final RendererLivingEntity rendererLivingEntity;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      int count = livingEntity.getCount5();
      if (count > 0) {
         EntityArrow entityArrow = new EntityArrow(livingEntity.worldObj, livingEntity.posX, livingEntity.posY, livingEntity.posZ);
         Random random = new Random((long)livingEntity.getEntityId());
         RenderHelper.disableStandardItemLighting();

         for (int index = 0; index < count; index++) {
            GlStateManager.pushMatrix();
            BoxesManager boxesManager = this.rendererLivingEntity.getPassiveFloatEntry().resolveBoxesManager(random);
            DampenerSelector dampenerSelector = (DampenerSelector)boxesManager.list.get(random.nextInt(boxesManager.list.size()));
            boxesManager.applyFloatByFloat(0.0625F);
            float factor = random.nextFloat();
            float factor2 = random.nextFloat();
            float factor3 = random.nextFloat();
            float factor4 = (dampenerSelector.factor4 + (dampenerSelector.factor6 - dampenerSelector.factor4) * factor) / 16.0F;
            float factor5 = (dampenerSelector.factor + (dampenerSelector.factor2 - dampenerSelector.factor) * factor2) / 16.0F;
            float factor6 = (dampenerSelector.factor3 + (dampenerSelector.factor5 - dampenerSelector.factor3) * factor3) / 16.0F;
            GlStateManager.translate(factor4, factor5, factor6);
            float factor7 = factor * 2.0F - 1.0F;
            float factor8 = factor2 * 2.0F - 1.0F;
            float factor9 = factor3 * 2.0F - 1.0F;
            float factor10 = factor7 * -1.0F;
            float factor11 = factor8 * -1.0F;
            float factor12 = factor9 * -1.0F;
            float factor13 = MathHelper.sin(factor10 * factor10 + factor12 * factor12);
            entityArrow.prevRotationYaw = entityArrow.rotationYaw = (float)(Math.atan2((double)factor10, (double)factor12) * 180.0 / Math.PI);
            entityArrow.prevRotationPitch = entityArrow.rotationPitch = (float)(Math.atan2((double)factor11, (double)factor13) * 180.0 / Math.PI);
            double offset = 0.0;
            double offset2 = 0.0;
            double offset3 = 0.0;
            this.rendererLivingEntity.getRenderManager().renderEntity(entityArrow, offset, offset2, offset3, 0.0F, posX3);
            GlStateManager.popMatrix();
         }

         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public RendererLivingEntityEntry(RendererLivingEntity rendererLivingEntity2) {
      this.rendererLivingEntity = rendererLivingEntity2;
   }
}
