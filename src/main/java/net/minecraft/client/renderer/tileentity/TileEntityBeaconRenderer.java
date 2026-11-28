package net.minecraft.client.renderer.tileentity;

import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class TileEntityBeaconRenderer extends TileEntitySpecialRenderer {
   public static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

   public void renderTileEntityAt(TileEntityBeacon tileEntityBeacon, double d, double d2, double d3, float f, int n) {
      float f2 = tileEntityBeacon.shouldBeamRender();
      if ((double)f2 > 0.0) {
         if (Config.isShaderPackLoaded()) {
            Shaders.beginBeaconBeam();
         }

         GlStateManager.alphaFunc(516, 0.1F);
         if (f2 > 0.0F) {
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            GlStateManager.disableFog();
            List beamSegments = tileEntityBeacon.getBeamSegments();
            int n2 = 0;

            for (int i = 0; i < beamSegments.size(); i++) {
               TileEntityBeacon.BeamSegment beamSegment = (TileEntityBeacon.BeamSegment)beamSegments.get(i);
               int n3 = n2 + beamSegment.getHeight();
               this.bindTexture(beaconBeam);
               GL11.glTexParameterf(3553, 10242, 10497.0F);
               GL11.glTexParameterf(3553, 10243, 10497.0F);
               GlStateManager.disableLighting();
               GlStateManager.disableCull();
               GlStateManager.disableBlend();
               GlStateManager.depthMask(true);
               GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
               double d4 = (double)tileEntityBeacon.getWorldObj().getTotalWorldTime() + (double)f;
               double d5 = MathHelper.frac(-d4 * 0.2 - (double)MathHelper.floor_double(-d4 * 0.1));
               float f3 = beamSegment.getColors()[0];
               float f4 = beamSegment.getColors()[1];
               float f5 = beamSegment.getColors()[2];
               double d6 = d4 * 0.025 * -1.5;
               double d7 = 0.2;
               double d8 = 0.5 + Math.cos(d6 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double d9 = 0.5 + Math.sin(d6 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double d10 = 0.5 + Math.cos(d6 + (Math.PI / 4)) * 0.2;
               double d11 = 0.5 + Math.sin(d6 + (Math.PI / 4)) * 0.2;
               double d12 = 0.5 + Math.cos(d6 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double d13 = 0.5 + Math.sin(d6 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double d14 = 0.5 + Math.cos(d6 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double d15 = 0.5 + Math.sin(d6 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double d16 = 0.0;
               double d17 = 1.0;
               double d18 = -1.0 + d5;
               double d19 = (double)((float)beamSegment.getHeight() * f2) * 2.5 + d18;
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               worldRenderer.pos(d + d8, d2 + (double)n3, d3 + d9).tex(1.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d8, d2 + (double)n2, d3 + d9).tex(1.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d10, d2 + (double)n2, d3 + d11).tex(0.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d10, d2 + (double)n3, d3 + d11).tex(0.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d14, d2 + (double)n3, d3 + d15).tex(1.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d14, d2 + (double)n2, d3 + d15).tex(1.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d12, d2 + (double)n2, d3 + d13).tex(0.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d12, d2 + (double)n3, d3 + d13).tex(0.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d10, d2 + (double)n3, d3 + d11).tex(1.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d10, d2 + (double)n2, d3 + d11).tex(1.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d14, d2 + (double)n2, d3 + d15).tex(0.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d14, d2 + (double)n3, d3 + d15).tex(0.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d12, d2 + (double)n3, d3 + d13).tex(1.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d12, d2 + (double)n2, d3 + d13).tex(1.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d8, d2 + (double)n2, d3 + d9).tex(0.0, d18).color(f3, f4, f5, 1.0F).endVertex();
               worldRenderer.pos(d + d8, d2 + (double)n3, d3 + d9).tex(0.0, d19).color(f3, f4, f5, 1.0F).endVertex();
               instance.draw();
               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               GlStateManager.depthMask(false);
               d6 = 0.2;
               d7 = 0.2;
               d8 = 0.8;
               d9 = 0.2;
               d10 = 0.2;
               d11 = 0.8;
               d12 = 0.8;
               d13 = 0.8;
               d14 = 0.0;
               d15 = 1.0;
               d16 = -1.0 + d5;
               d17 = (double)((float)beamSegment.getHeight() * f2) + d16;
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               worldRenderer.pos(d + 0.2, d2 + (double)n3, d3 + 0.2).tex(1.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.2).tex(1.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.2).tex(0.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n3, d3 + 0.2).tex(0.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n3, d3 + 0.8).tex(1.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.8).tex(1.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.8).tex(0.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n3, d3 + 0.8).tex(0.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n3, d3 + 0.2).tex(1.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.2).tex(1.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.8).tex(0.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.8, d2 + (double)n3, d3 + 0.8).tex(0.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n3, d3 + 0.8).tex(1.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.8).tex(1.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.2).tex(0.0, d16).color(f3, f4, f5, 0.125F).endVertex();
               worldRenderer.pos(d + 0.2, d2 + (double)n3, d3 + 0.2).tex(0.0, d17).color(f3, f4, f5, 0.125F).endVertex();
               instance.draw();
               GlStateManager.enableLighting();
               GlStateManager.enableTexture2D();
               GlStateManager.depthMask(true);
               n2 = n3;
            }

            GlStateManager.enableFog();
         }

         if (Config.isShaderPackLoaded()) {
            Shaders.endBeaconBeam();
         }
      }
   }

   public static void renderBeaconBeam(double d, double d2, double d3, float f, long l, int n, float[] floats) {
      if (Config.isShaderPackLoaded()) {
         Shaders.beginBeaconBeam();
      }

      GlStateManager.alphaFunc(516, 0.1F);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.disableFog();
      byte b = 0;
      int n2 = b + n;
      TileEntityRendererDispatcher.instance.renderEngine.bindTexture(beaconBeam);
      GL11.glTexParameterf(3553, 10242, 10497.0F);
      GL11.glTexParameterf(3553, 10243, 10497.0F);
      GlStateManager.disableLighting();
      GlStateManager.disableCull();
      GlStateManager.disableBlend();
      GlStateManager.depthMask(true);
      GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
      double d4 = (double)l + (double)f;
      double d5 = MathHelper.frac(-d4 * 0.2 - (double)MathHelper.floor_double(-d4 * 0.1));
      float f2 = floats[0];
      float f3 = floats[1];
      float f4 = floats[2];
      double d6 = d4 * 0.025 * -1.5;
      double d7 = 0.2;
      double d8 = 0.5 + Math.cos(d6 + (Math.PI * 3.0 / 4.0)) * 0.2;
      double d9 = 0.5 + Math.sin(d6 + (Math.PI * 3.0 / 4.0)) * 0.2;
      double d10 = 0.5 + Math.cos(d6 + (Math.PI / 4)) * 0.2;
      double d11 = 0.5 + Math.sin(d6 + (Math.PI / 4)) * 0.2;
      double d12 = 0.5 + Math.cos(d6 + (Math.PI * 5.0 / 4.0)) * 0.2;
      double d13 = 0.5 + Math.sin(d6 + (Math.PI * 5.0 / 4.0)) * 0.2;
      double d14 = 0.5 + Math.cos(d6 + (Math.PI * 7.0 / 4.0)) * 0.2;
      double d15 = 0.5 + Math.sin(d6 + (Math.PI * 7.0 / 4.0)) * 0.2;
      double d16 = 0.0;
      double d17 = 1.0;
      double d18 = -1.0 + d5;
      double d19 = (double)((float)n) * 2.5 + d18;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(d + d8, d2 + (double)n2, d3 + d9).tex(1.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d8, d2 + (double)b, d3 + d9).tex(1.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d10, d2 + (double)b, d3 + d11).tex(0.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d10, d2 + (double)n2, d3 + d11).tex(0.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d14, d2 + (double)n2, d3 + d15).tex(1.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d14, d2 + (double)b, d3 + d15).tex(1.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d12, d2 + (double)b, d3 + d13).tex(0.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d12, d2 + (double)n2, d3 + d13).tex(0.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d10, d2 + (double)n2, d3 + d11).tex(1.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d10, d2 + (double)b, d3 + d11).tex(1.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d14, d2 + (double)b, d3 + d15).tex(0.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d14, d2 + (double)n2, d3 + d15).tex(0.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d12, d2 + (double)n2, d3 + d13).tex(1.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d12, d2 + (double)b, d3 + d13).tex(1.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d8, d2 + (double)b, d3 + d9).tex(0.0, d18).color(f2, f3, f4, 1.0F).endVertex();
      worldRenderer.pos(d + d8, d2 + (double)n2, d3 + d9).tex(0.0, d19).color(f2, f3, f4, 1.0F).endVertex();
      instance.draw();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.depthMask(false);
      d6 = 0.2;
      d7 = 0.2;
      d8 = 0.8;
      d9 = 0.2;
      d10 = 0.2;
      d11 = 0.8;
      d12 = 0.8;
      d13 = 0.8;
      d14 = 0.0;
      d15 = 1.0;
      d16 = -1.0 + d5;
      d17 = (double)((float)n) + d16;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.2).tex(1.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)b, d3 + 0.2).tex(1.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)b, d3 + 0.2).tex(0.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.2).tex(0.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.8).tex(1.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)b, d3 + 0.8).tex(1.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)b, d3 + 0.8).tex(0.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.8).tex(0.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.2).tex(1.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)b, d3 + 0.2).tex(1.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)b, d3 + 0.8).tex(0.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.8, d2 + (double)n2, d3 + 0.8).tex(0.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.8).tex(1.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)b, d3 + 0.8).tex(1.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)b, d3 + 0.2).tex(0.0, d16).color(f2, f3, f4, 0.125F).endVertex();
      worldRenderer.pos(d + 0.2, d2 + (double)n2, d3 + 0.2).tex(0.0, d17).color(f2, f3, f4, 0.125F).endVertex();
      instance.draw();
      GlStateManager.enableLighting();
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      GlStateManager.enableFog();
      if (Config.isShaderPackLoaded()) {
         Shaders.endBeaconBeam();
      }
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityBeacon)tileEntity, d, d2, d3, f, n);
   }

   @Override
   public boolean forceTileEntityRender() {
      return true;
   }
}
