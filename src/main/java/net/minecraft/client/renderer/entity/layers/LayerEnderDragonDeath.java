package net.minecraft.client.renderer.entity.layers;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;

public class LayerEnderDragonDeath implements LayerRenderer {
   public void doRenderLayer(EntityDragon entityDragon, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityDragon.deathTicks > 0) {
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         RenderHelper.disableStandardItemLighting();
         float f8 = ((float)entityDragon.deathTicks + f3) / 200.0F;
         float f9 = 0.0F;
         if (f8 > 0.8F) {
            f9 = (f8 - 0.8F) / 0.2F;
         }

         Random rand = new Random(432L);
         GlStateManager.disableTexture2D();
         GlStateManager.shadeModel(7425);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 1);
         GlStateManager.disableAlpha();
         GlStateManager.enableCull();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -1.0F, -2.0F);

         for (int i = 0; (float)i < (f8 + f8 * f8) / 2.0F * 60.0F; i++) {
            GlStateManager.rotate(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(rand.nextFloat() * 360.0F + f8 * 90.0F, 0.0F, 0.0F, 1.0F);
            float f10 = rand.nextFloat() * 20.0F + 5.0F + f9 * 10.0F;
            float f11 = rand.nextFloat() * 2.0F + 1.0F + f9 * 2.0F;
            worldRenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(0.0, 0.0, 0.0).color(255, 255, 255, (int)(255.0F * (1.0F - f9))).endVertex();
            worldRenderer.pos(-0.866 * (double)f11, (double)f10, (double)(-0.5F * f11)).color(255, 0, 255, 0).endVertex();
            worldRenderer.pos(0.866 * (double)f11, (double)f10, (double)(-0.5F * f11)).color(255, 0, 255, 0).endVertex();
            worldRenderer.pos(0.0, (double)f10, (double)(1.0F * f11)).color(255, 0, 255, 0).endVertex();
            worldRenderer.pos(-0.866 * (double)f11, (double)f10, (double)(-0.5F * f11)).color(255, 0, 255, 0).endVertex();
            instance.draw();
         }

         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.shadeModel(7424);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityDragon)entity, f, f2, f3, f4, f5, f6, f7);
   }
}
