package net.minecraft.client.renderer.entity.layers;

import java.util.Random;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;

public class LayerArrow implements LayerRenderer {
   private final RendererLivingEntity renderer;

   public LayerArrow(RendererLivingEntity rendererLivingEntity) {
      this.renderer = rendererLivingEntity;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      int arrowCountInEntity = entity.getArrowCountInEntity();
      if (arrowCountInEntity > 0) {
         EntityArrow entityArrow = new EntityArrow(entity.entityWorld, entity.posX, entity.posY, entity.posZ);
         Random rand = new Random((long)entity.getEntityId());
         RenderHelper.disableStandardItemLighting();

         for (int i = 0; i < arrowCountInEntity; i++) {
            GlStateManager.pushMatrix();
            ModelRenderer randomModelBox = this.renderer.getMainModel().getRandomModelBox(rand);
            ModelBox modelBox = (ModelBox)randomModelBox.cubeList.get(rand.nextInt(randomModelBox.cubeList.size()));
            randomModelBox.postRender(0.0625F);
            float f8 = rand.nextFloat();
            float f9 = rand.nextFloat();
            float f10 = rand.nextFloat();
            GlStateManager.translate((modelBox.posX1 + (modelBox.posX2 - modelBox.posX1) * f8) / 16.0F, (modelBox.posY1 + (modelBox.posY2 - modelBox.posY1) * f9) / 16.0F, (modelBox.posZ1 + (modelBox.posZ2 - modelBox.posZ1) * f10) / 16.0F);
            f8 = f8 * 2.0F - 1.0F;
            f9 = f9 * 2.0F - 1.0F;
            f10 = f10 * 2.0F - 1.0F;
            f8 *= -1.0F;
            f9 *= -1.0F;
            f10 *= -1.0F;
            float f14 = MathHelper.sqrt_float(f8 * f8 + f10 * f10);
            entityArrow.prevRotationYaw = entityArrow.rotationYaw = (float)(Math.atan2((double)f8, (double)f10) * 180.0 / Math.PI);
            entityArrow.prevRotationPitch = entityArrow.rotationPitch = (float)(Math.atan2((double)f9, (double)f14) * 180.0 / Math.PI);
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            this.renderer.getRenderManager().renderEntityWithPosYaw(entityArrow, d, d2, d3, 0.0F, f3);
            GlStateManager.popMatrix();
         }

         RenderHelper.enableStandardItemLighting();
      }
   }
}
