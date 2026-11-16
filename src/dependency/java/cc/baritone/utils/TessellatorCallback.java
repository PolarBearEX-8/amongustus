package cc.baritone.utils;

import cc.Settings;
import client.render.RenderManager;
import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public interface TessellatorCallback {
   Tessellator tessellator = Tessellator.getTessellator();
   WorldRenderer worldRenderer = tessellator.getWorldRenderer();
   RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
   TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
   Settings settings = SettingsTxtManager.getSettings();
   float[] factorArray = new float[]{1.0F, 1.0F, 1.0F, 255.0F};

   static void applyColor(Color color, float factor, float factor2, boolean flag) {
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      applyColor(color, factor);
      GL11.glLineWidth(factor2);
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(false);
      GlStateManager.applyState3();
      if (flag) {
         GlStateManager.enableDepth();
      }

      worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry3);
   }

   static void applyColor(Color color, float factor) {
      float[] colorComponents = color.getColorComponents(null);
      factorArray[0] = colorComponents[0];
      factorArray[1] = colorComponents[1];
      factorArray[2] = colorComponents[2];
      factorArray[3] = factor;
   }

   static void setEnabled(boolean enabled) {
      tessellator.updateState();
      if (enabled) {
         GlStateManager.disableDepth();
      }

      GlStateManager.depthMask(true);
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.applyState13();
   }

   static void applyAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox) {
      AxisAlignedBB boundingBox2 = boundingBox.expand(-renderManager.viewerPosX, -renderManager.viewerPosZ, -renderManager.viewerPosY);
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.minZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.maxX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.minY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
      worldRenderer.getWorldRenderer(boundingBox2.minX, boundingBox2.maxY, boundingBox2.maxZ)
         .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], factorArray[3])
         .updateState();
   }

   static void applyAxisAlignedBB(AxisAlignedBB boundingBox, double offset) {
      applyAxisAlignedBBByAxisAlignedBB(boundingBox.offset(offset, offset, offset));
   }

   static void applyColor(Color color, float factor, boolean flag) {
      applyColor(color, 0.4F, factor, flag);
   }

   static void applyAxisAlignedBB(AxisAlignedBB boundingBox) {
      worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry3);
      applyAxisAlignedBBByAxisAlignedBB(boundingBox);
      tessellator.updateState();
   }
}
