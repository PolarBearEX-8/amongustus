package net.augustus.cosmetics.render;

import net.augustus.cosmetics.CosmeticMathHelper;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class CloakRenderer {
   private ModelRenderer cloakModel;

   private void initCloakModel(ModelPlayer modelPlayer) {
      this.cloakModel = new ModelRenderer(modelPlayer, 0, 0);
      this.cloakModel.setTextureOffset(22, 17);
      this.cloakModel.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, false);
   }

   public void renderCloak(AbstractClientPlayer clientPlayer, TextureManager textureManager, ModelPlayer modelPlayer, ResourceLocation location, float f) {
      if (this.cloakModel == null) {
         this.initCloakModel(modelPlayer);
      }

      GlStateManager.pushMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      textureManager.bindTexture(location);
      if (clientPlayer.isSneaking()) {
         GlStateManager.translate(0.0F, 0.2F, 0.0F);
      }

      GlStateManager.translate(0.0F, 0.0F, 0.125F);
      double d = clientPlayer.prevChasingPosX + (clientPlayer.chasingPosX - clientPlayer.prevChasingPosX) * (double)f - (clientPlayer.prevPosX + (clientPlayer.posX - clientPlayer.prevPosX) * (double)f);
      double d2 = clientPlayer.prevChasingPosY + (clientPlayer.chasingPosY - clientPlayer.prevChasingPosY) * (double)f - (clientPlayer.prevPosY + (clientPlayer.posY - clientPlayer.prevPosY) * (double)f);
      double d3 = clientPlayer.prevChasingPosZ + (clientPlayer.chasingPosZ - clientPlayer.prevChasingPosZ) * (double)f - (clientPlayer.prevPosZ + (clientPlayer.posZ - clientPlayer.prevPosZ) * (double)f);
      float f2 = clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * f;
      double d4 = (double)CosmeticMathHelper.sin(f2 * 3.1415927F / 180.0F);
      double d5 = (double)(-CosmeticMathHelper.cos(f2 * 3.1415927F / 180.0F));
      float f3 = (float)d2 * 10.0F;
      f3 = CosmeticMathHelper.clampFloat(f3, -6.0F, 32.0F);
      float f4 = (float)(d * d4 + d3 * d5) * 100.0F;
      float f5 = (float)(d * d5 - d3 * d4) * 100.0F;
      if (f4 < 0.0F) {
         f4 = 0.0F;
      }

      if (f4 >= 130.0F) {
         f4 = 130.0F + (f4 - 180.0F) * 0.2F;
      }

      float f6 = clientPlayer.prevCameraYaw + (clientPlayer.cameraYaw - clientPlayer.prevCameraYaw) * f;
      f3 += CosmeticMathHelper.sin((clientPlayer.prevDistanceWalkedModified + (clientPlayer.distanceWalkedModified - clientPlayer.prevDistanceWalkedModified) * f) * 6.0F) * 32.0F * f6;
      if (clientPlayer.isSneaking()) {
         f3 += 25.0F;
      }

      GlStateManager.rotate(6.0F + f4 / 2.0F + f3, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f5 / 2.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-f5 / 2.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      if (clientPlayer.isSneaking()) {
         GlStateManager.scale(1.0F, 1.0F, 1.11F);
         GlStateManager.translate(0.0F, -0.06F, 0.0F);
      }

      GlStateManager.scale(1.01F, 1.01F, 1.01F);
      this.cloakModel.render(0.0625F);
      GlStateManager.popMatrix();
   }




}
