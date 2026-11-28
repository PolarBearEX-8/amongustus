package baritone.utils;

import baritone.api.BaritoneAPI;
import baritone.api.Settings;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public interface IRenderer {
   Tessellator tessellator = Tessellator.getInstance();
   WorldRenderer buffer = tessellator.getWorldRenderer();
   RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
   TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
   Settings settings = BaritoneAPI.getSettings();
   float[] color = new float[]{1.0F, 1.0F, 1.0F, 255.0F};

   static void startLines(Color color2, float f, boolean flag) {
      startLines(color2, 0.4F, f, flag);
   }

   static void endLines(boolean flag) {
      tessellator.draw();
      if (flag) {
         GlStateManager.enableDepth();
      }

      GlStateManager.depthMask(true);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
   }

   static void glColor(Color color2, float f) {
      float[] colorComponents = color2.getColorComponents(null);
      color[0] = colorComponents[0];
      color[1] = colorComponents[1];
      color[2] = colorComponents[2];
      color[3] = f;
   }

   static void drawAABB(AxisAlignedBB box) {
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      emitAABB(box);
      tessellator.draw();
   }

   static void emitAABB(AxisAlignedBB box, double d) {
      emitAABB(box.expand(d, d, d));
   }

   static void emitAABB(AxisAlignedBB box) {
      AxisAlignedBB box2 = box.offset(-renderManager.viewerPosX, -renderManager.viewerPosY, -renderManager.viewerPosZ);
      buffer.pos(box2.minX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.minZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.maxX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.minY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
      buffer.pos(box2.minX, box2.maxY, box2.maxZ).color(color[0], color[1], color[2], color[3]).endVertex();
   }

   static void startLines(Color color2, float f, float f2, boolean flag) {
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      glColor(color2, f);
      GL11.glLineWidth(f2);
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(false);
      GlStateManager.disableLighting();
      if (flag) {
         GlStateManager.disableDepth();
      }

      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
   }
}
