package net.augustus.modules.render.esp;

import net.augustus.render.esp.AbstractEspBox;
import net.augustus.utils.render.ColorUtil;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

public class EspRenderUtil {
   public static void beginTexturedQuads() {
      Tessellator.getInstance().getWorldRenderer().begin(7, DefaultVertexFormats.POSITION_TEX);
   }

   public static void addTexturedQuad(double d, double d2, float f, float f2, double d3, double d4, float f3, float f4) {
      float f5 = 1.0F / f3;
      float f6 = 1.0F / f4;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos(d, d2 + d4, 0.0).tex((double)(f * f5), (double)((f2 + (float)d4) * f6)).endVertex();
      worldRenderer.pos(d + d3, d2 + d4, 0.0).tex((double)((f + (float)d3) * f5), (double)((f2 + (float)d4) * f6)).endVertex();
      worldRenderer.pos(d + d3, d2, 0.0).tex((double)((f + (float)d3) * f5), (double)(f2 * f6)).endVertex();
      worldRenderer.pos(d, d2, 0.0).tex((double)(f * f5), (double)(f2 * f6)).endVertex();
   }

   public static void add2DCorners(double d, double d2, double d3, double d4, double d5, double d6, int n) {
      float redFloat = ColorUtil.getRedFloat(n);
      float greenFloat = ColorUtil.getGreenFloat(n);
      float blueFloat = ColorUtil.getBlueFloat(n);
      float alphaFloat = ColorUtil.getAlphaFloat(n);
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      double d7 = Math.abs(d - d3) / 2.0 / (100.0 / d5);
      double d8 = Math.abs(d2 - d4) / 2.0 / (100.0 / d6);
      worldRenderer.pos(d, d2 + d8, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d + d7, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d2 + d8, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3 - d7, d2, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d4 - d8, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d3 - d7, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d, d4 - d8, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
      worldRenderer.pos(d + d7, d4, 0.0).color(redFloat, greenFloat, blueFloat, alphaFloat).endVertex();
   }

   public static void addFilledBox(AbstractEspBox espBox) {
      float expandedHalfWidth = espBox.getExpandedHalfWidth();
      addBoxQuadVertices(
         espBox.x - expandedHalfWidth,
         espBox.y - espBox.getBottomExpand(),
         espBox.z - expandedHalfWidth,
         espBox.x + expandedHalfWidth,
         espBox.y + espBox.getExpandedHeight(),
         espBox.z + expandedHalfWidth,
         espBox.red,
         espBox.green,
         espBox.blue,
         espBox.alpha
      );
   }

   public static void beginColoredLines() {
      Tessellator.getInstance().getWorldRenderer().begin(1, DefaultVertexFormats.POSITION_COLOR);
   }

   public static void addOutlinedBox(AbstractEspBox espBox) {
      float expandedHalfWidth = espBox.getExpandedHalfWidth();
      addBoxOutlineVertices(
         espBox.x - expandedHalfWidth,
         espBox.y - espBox.getBottomExpand(),
         espBox.z - expandedHalfWidth,
         espBox.x + expandedHalfWidth,
         espBox.y + espBox.getExpandedHeight(),
         espBox.z + expandedHalfWidth,
         espBox.red,
         espBox.green,
         espBox.blue,
         espBox.alpha
      );
   }

   public static void addRotatedOutlinedBox(EntityEspBox entityEspBox) {
      float expandedHalfWidth = entityEspBox.getExpandedHalfWidth();
      float f = entityEspBox.y;
      float f2 = entityEspBox.y + entityEspBox.getExpandedHeight();
      float red = entityEspBox.red;
      float green = entityEspBox.green;
      float blue = entityEspBox.blue;
      float alpha = entityEspBox.alpha;
      float f4 = (float)Math.toRadians((double)MathHelper.wrapAngleTo180_float(entityEspBox.getYawHead()));
      float f5 = (float)Math.cos((double)f4);
      float f6 = (float)Math.sin((double)f4);
      float f7 = entityEspBox.x + (expandedHalfWidth * f5 - expandedHalfWidth * f6);
      float f8 = entityEspBox.z + expandedHalfWidth * f6 + expandedHalfWidth * f5;
      float f9 = entityEspBox.x + (expandedHalfWidth * f5 - -expandedHalfWidth * f6);
      float f10 = entityEspBox.z + expandedHalfWidth * f6 + -expandedHalfWidth * f5;
      float f11 = entityEspBox.x + (-expandedHalfWidth * f5 - -expandedHalfWidth * f6);
      float f12 = entityEspBox.z + -expandedHalfWidth * f6 + -expandedHalfWidth * f5;
      float f13 = entityEspBox.x + (-expandedHalfWidth * f5 - expandedHalfWidth * f6);
      float f14 = entityEspBox.z + -expandedHalfWidth * f6 + expandedHalfWidth * f5;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
   }

   public static void beginColoredQuads() {
      Tessellator.getInstance().getWorldRenderer().begin(7, DefaultVertexFormats.POSITION_COLOR);
   }

   private static void addBoxOutlineVertices(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
   }

   public static void drawTessellator() {
      Tessellator.getInstance().draw();
   }

   private static void addBoxQuadVertices(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).color(f7, f8, f9, f10).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).color(f7, f8, f9, f10).endVertex();
   }

   public static void addRotatedFilledBox(EntityEspBox entityEspBox) {
      float expandedHalfWidth = entityEspBox.getExpandedHalfWidth();
      float f = entityEspBox.y;
      float f2 = entityEspBox.y + entityEspBox.getExpandedHeight();
      float red = entityEspBox.red;
      float green = entityEspBox.green;
      float blue = entityEspBox.blue;
      float alpha = entityEspBox.alpha;
      float f4 = (float)Math.toRadians((double)MathHelper.wrapAngleTo180_float(entityEspBox.getYawHead()));
      float f5 = (float)Math.cos((double)f4);
      float f6 = (float)Math.sin((double)f4);
      float f7 = entityEspBox.x + (expandedHalfWidth * f5 - expandedHalfWidth * f6);
      float f8 = entityEspBox.z + expandedHalfWidth * f6 + expandedHalfWidth * f5;
      float f9 = entityEspBox.x + (expandedHalfWidth * f5 - -expandedHalfWidth * f6);
      float f10 = entityEspBox.z + expandedHalfWidth * f6 + -expandedHalfWidth * f5;
      float f11 = entityEspBox.x + (-expandedHalfWidth * f5 - -expandedHalfWidth * f6);
      float f12 = entityEspBox.z + -expandedHalfWidth * f6 + -expandedHalfWidth * f5;
      float f13 = entityEspBox.x + (-expandedHalfWidth * f5 - expandedHalfWidth * f6);
      float f14 = entityEspBox.z + -expandedHalfWidth * f6 + expandedHalfWidth * f5;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f, (double)f14).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f7, (double)f2, (double)f8).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f9, (double)f2, (double)f10).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f11, (double)f2, (double)f12).color(red, green, blue, alpha).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).color(red, green, blue, alpha).endVertex();
   }
}
