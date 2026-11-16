package net.minecraft.client.gui.inventory;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ScrollableContainer;
import client.render.RenderManager;
import client.util.SplineBridge;
import client.util.SplineSystem;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class GuiInventory extends GuiContainerCreative {
   public float factor4;
   public float factor5;

   @Override
   public void updateState2() {
      this.list.clear();
      if (this.mc.playerController.processAction2()) {
         Minecraft mc = this.mc;
         ScrollableContainer scrollableContainer = new ScrollableContainer(this.mc.thePlayer);
         mc.displayGuiScreen(scrollableContainer);
      } else {
         super.updateState2();
      }

      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      double[] offsetArray = new double[]{(double)((float)scaledResolution.getScaledWidth() / 2.0F), (double)((float)scaledResolution.getScaledHeight() / 2.0F)};
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         Minecraft mc = this.mc;
         SplineSystem splineSystem = new SplineSystem(this, this.mc.thePlayer.getStatFileWriter());
         mc.displayGuiScreen(splineSystem);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         Minecraft mc2 = this.mc;
         SplineBridge splineBridge = new SplineBridge(this, this.mc.thePlayer.getStatFileWriter());
         mc2.displayGuiScreen(splineBridge);
      }
   }

   @Override
   public void updateState4() {
      if (this.mc.playerController.processAction2()) {
         Minecraft mc = this.mc;
         ScrollableContainer scrollableContainer = new ScrollableContainer(this.mc.thePlayer);
         mc.displayGuiScreen(scrollableContainer);
      }

      this.renderElement5();
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.fontRenderer.getIndex(StatCollector.getText("container.crafting", new Object[0]), 86, 16, 4210752);
   }

   public static void applyEntityLivingBaseByEntityLivingBase(int count, int count2, int count3, float factor, float factor2, EntityLivingBase livingEntity) {
      GlStateManager.applyState6();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)count, (float)count2, 50.0F);
      GlStateManager.translate3f3((float)(-count3), (float)count3, (float)count3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float scaleFactor = livingEntity.scaleFactor8;
      float factor3 = livingEntity.rotationYaw;
      float factor6 = livingEntity.rotationPitch;
      float scaleFactor2 = livingEntity.scaleFactor22;
      float scaleFactor3 = livingEntity.scaleFactor12;
      float scaleFactor4 = livingEntity.scaleFactor21;
      float scaleFactor5 = livingEntity.scaleFactor;
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(factor2 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      livingEntity.scaleFactor8 = (float)Math.atan((double)(factor / 40.0F)) * 20.0F;
      livingEntity.rotationYaw = (float)Math.atan((double)(factor / 40.0F)) * 40.0F;
      livingEntity.rotationPitch = livingEntity.scaleFactor = livingEntity.scaleFactor21 = -((float)Math.atan((double)(factor2 / 40.0F))) * 20.0F;
      livingEntity.scaleFactor12 = livingEntity.rotationYaw;
      livingEntity.scaleFactor22 = livingEntity.rotationYaw;
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      renderManager.setScaleFactor(180.0F);
      renderManager.setState1(false);
      renderManager.renderEntity(livingEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F);
      renderManager.setState1(true);
      livingEntity.scaleFactor8 = scaleFactor;
      livingEntity.rotationYaw = factor3;
      livingEntity.rotationPitch = factor6;
      livingEntity.scaleFactor22 = scaleFactor2;
      livingEntity.scaleFactor12 = scaleFactor3;
      livingEntity.scaleFactor21 = scaleFactor4;
      livingEntity.scaleFactor = scaleFactor5;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.applyState8();
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      this.factor4 = (float)count;
      this.factor5 = (float)count2;
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation4);
      int indexOffset = this.indexOffset1;
      int indexOffset2 = this.indexOffset2;
      this.processAction(indexOffset, indexOffset2, 0, 0, this.count9, this.count10);
      applyEntityLivingBaseByEntityLivingBase(
         indexOffset + 51, indexOffset2 + 75, 30, (float)(indexOffset + 51) - this.factor4, (float)(indexOffset2 + 75 - 50) - this.factor5, this.mc.thePlayer
      );
   }

   public GuiInventory(EntityPlayer player) {
      super(player.container);
      this.enabled = true;
   }
}
