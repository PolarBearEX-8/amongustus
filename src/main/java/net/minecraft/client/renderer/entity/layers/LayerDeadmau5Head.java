package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;

public class LayerDeadmau5Head implements LayerRenderer {
   private final RenderPlayer playerRenderer;

   public LayerDeadmau5Head(RenderPlayer renderPlayer) {
      this.playerRenderer = renderPlayer;
   }

   public void doRenderLayer(AbstractClientPlayer clientPlayer, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (clientPlayer.getName().equals("deadmau5") && clientPlayer.hasSkin() && !clientPlayer.isInvisible()) {
         this.playerRenderer.bindTexture(clientPlayer.getLocationSkin());

         for (int i = 0; i < 2; i++) {
            float f8 = clientPlayer.prevRotationYaw + (clientPlayer.rotationYaw - clientPlayer.prevRotationYaw) * f3 - (clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * f3);
            float f9 = clientPlayer.prevRotationPitch + (clientPlayer.rotationPitch - clientPlayer.prevRotationPitch) * f3;
            GlStateManager.pushMatrix();
            GlStateManager.rotate(f8, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f9, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
            GlStateManager.translate(0.0F, -0.375F, 0.0F);
            GlStateManager.rotate(-f9, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-f8, 0.0F, 1.0F, 0.0F);
            float f10 = 1.3333334F;
            GlStateManager.scale(f10, f10, f10);
            this.playerRenderer.getPlayerModel().renderDeadmau5Head(0.0625F);
            GlStateManager.popMatrix();
         }
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((AbstractClientPlayer)entity, f, f2, f3, f4, f5, f6, f7);
   }
}
