package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.render.RenderPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;

public class DiamondIngot implements PreMotionDispatcher {
   public final RenderPlayer renderPlayer;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyAbstractClientPlayer((AbstractClientPlayer)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public void applyAbstractClientPlayer(AbstractClientPlayer player, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (player.getText().equals("deadmau5") && player.isActive27() && !player.isActive12()) {
         this.renderPlayer.applyResourceLocation2(player.getResourceLocation2());

         for (int index = 0; index < 2; index++) {
            float factor = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * posX3 - (player.factor12 + (player.scaleFactor8 - player.factor12) * posX3);
            float factor2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * posX3;
            GlStateManager.pushMatrix();
            GlStateManager.rotate(factor, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(factor2, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.375F * (float)(index * 2 - 1), 0.0F, 0.0F);
            GlStateManager.translate(0.0F, -0.375F, 0.0F);
            GlStateManager.rotate(-factor2, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-factor, 0.0F, 1.0F, 0.0F);
            float factor3 = 1.3333334F;
            GlStateManager.translate3f3(factor3, factor3, factor3);
            this.renderPlayer.getAuxiliaryFloatEntryBridge().applyFloatByFloat(0.0625F);
            GlStateManager.popMatrix();
         }
      }
   }

   public DiamondIngot(RenderPlayer renderPlayer2) {
      this.renderPlayer = renderPlayer2;
   }
}
