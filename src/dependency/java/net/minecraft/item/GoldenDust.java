package net.minecraft.item;

import client.util.AuxiliaryFloatEntryBridge;
import client.util.BoxesManager;
import client.util.InternalFloatArrayEntry;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class GoldenDust {
   public BoxesManager boxesManager;

   public void applyAbstractClientPlayer(
      AbstractClientPlayer player, TextureManager textureManager, AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge, int count, ResourceLocation location, float factor
   ) {
      getIndexArray();
      if (this.boxesManager == null) {
         this.applyAuxiliaryFloatEntryBridge(auxiliaryFloatEntryBridge);
      }

      GlStateManager.pushMatrix();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      textureManager.applyResourceLocation3(location);
      if (player.isSprinting()) {
         GlStateManager.translate(0.0F, 0.2F, 0.0F);
      }

      GlStateManager.translate(0.0F, 0.0F, 0.125F);
      double offset = player.value30 + (player.value25 - player.value30) * (double)factor - (player.prevPosX + (player.posX - player.prevPosX) * (double)factor);
      double offset2 = player.value28 + (player.value27 - player.value28) * (double)factor - (player.prevPosY + (player.posY - player.prevPosY) * (double)factor);
      double offset3 = player.value29 + (player.value26 - player.value29) * (double)factor - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)factor);
      float factor2 = player.factor12 + (player.scaleFactor8 - player.factor12) * factor;
      double offset4 = (double)InternalFloatArrayEntry.computeFactorByFloat(factor2 * (float) Math.PI / 180.0F);
      double offset5 = (double)(-InternalFloatArrayEntry.computeFactor(factor2 * (float) Math.PI / 180.0F));
      float factor3 = (float)offset2 * 10.0F;
      float factor4 = InternalFloatArrayEntry.computeFactor(factor3, -6.0F, 32.0F);
      float factor5 = (float)(offset * offset4 + offset3 * offset5) * 100.0F;
      float factor6 = (float)(offset * offset5 - offset3 * offset4) * 100.0F;
      if (factor5 < 0.0F) {
         factor5 = 0.0F;
      }

      if (factor5 >= 130.0F) {
         factor5 = 130.0F + (factor5 - 180.0F) * 0.2F;
      }

      float factor7 = player.factor30 + (player.factor47 - player.factor30) * factor;
      float factor8 = factor4
         + InternalFloatArrayEntry.computeFactorByFloat((player.factor4 + (player.distanceWalkedModified - player.factor4) * factor) * 6.0F) * 32.0F * factor7;
      if (player.isSprinting()) {
         factor8 += 25.0F;
      }

      GlStateManager.rotate(6.0F + factor5 / 2.0F + factor8, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(factor6 / 2.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-factor6 / 2.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      if (player.isSprinting()) {
         GlStateManager.translate3f3(1.0F, 1.0F, 1.11F);
         GlStateManager.translate(0.0F, -0.06F, 0.0F);
      }

      GlStateManager.translate3f3(1.01F, 1.01F, 1.01F);
      this.boxesManager.applyFloat3(0.0625F);
      GlStateManager.popMatrix();
   }

   private void applyAuxiliaryFloatEntryBridge(AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge) {
      this.boxesManager = new BoxesManager(auxiliaryFloatEntryBridge, 0, 0);
      this.boxesManager.resolveBoxesManagerByIndex(22, 17);
      this.boxesManager.resolveBoxesManager(-5.0F, 0.0F, -1.0F, 10, 16, 1, false);
   }

   public static int[] getIndexArray() {
      return null;
   }
}
