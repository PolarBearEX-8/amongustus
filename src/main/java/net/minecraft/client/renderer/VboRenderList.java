package net.minecraft.client.renderer;

import baritone.Baritone;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.Config;
import net.optifine.render.VboRegion;
import net.optifine.shaders.ShadersRender;
import org.lwjgl.opengl.GL11;

public class VboRenderList extends ChunkRenderContainer {
   private double viewEntityX2;
   private double viewEntityY2;
   private double viewEntityZ2;

   private void drawRegion(int n, int n2, VboRegion vboRegion) {
      GlStateManager.pushMatrix();
      this.preRenderRegion(n, 0, n2);
      vboRegion.finishDraw(this);
      GlStateManager.popMatrix();
   }

   @Override
   public void renderChunkLayer(EnumWorldBlockLayer enumWorldBlockLayer) {
      if (this.initialized) {
         if (!Config.isRenderRegions()) {
            for (RenderChunk renderChunk : (Iterable<RenderChunk>)(this.renderChunks)) {
               VertexBuffer vertexBufferByLayer = renderChunk.getVertexBufferByLayer(enumWorldBlockLayer.ordinal());
               GlStateManager.pushMatrix();
               this.preRenderChunk(renderChunk);
               renderChunk.multModelviewMatrix();
               vertexBufferByLayer.bindBuffer();
               this.setupArrayPointers();
               vertexBufferByLayer.drawArrays(7);
               if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()
                  && (Boolean)Baritone.settings().renderCachedChunks.value
                  && !Minecraft.getMinecraft().isSingleplayer()) {
                  GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               }

               GlStateManager.popMatrix();
            }
         } else {
            int n = -2147483648;
            int n2 = -2147483648;
            VboRegion vboRegion = null;

            for (RenderChunk renderChunk2 : (Iterable<RenderChunk>)(this.renderChunks)) {
               VertexBuffer vertexBufferByLayer2 = renderChunk2.getVertexBufferByLayer(enumWorldBlockLayer.ordinal());
               VboRegion vboRegion2 = vertexBufferByLayer2.getVboRegion();
               if (vboRegion2 != vboRegion || n != renderChunk2.regionX || n2 != renderChunk2.regionZ) {
                  if (vboRegion != null) {
                     this.drawRegion(n, n2, vboRegion);
                  }

                  n = renderChunk2.regionX;
                  n2 = renderChunk2.regionZ;
                  vboRegion = vboRegion2;
               }

               vertexBufferByLayer2.drawArrays(7);
            }

            if (vboRegion != null) {
               this.drawRegion(n, n2, vboRegion);
            }
         }

         OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
         GlStateManager.resetColor();
         this.renderChunks.clear();
      }
   }

   public void preRenderRegion(int n, int n2, int n3) {
      GlStateManager.translate((float)((double)n - this.viewEntityX2), (float)((double)n2 - this.viewEntityY2), (float)((double)n3 - this.viewEntityZ2));
   }

   @Override
   public void initialize(double d, double d2, double d3) {
      this.viewEntityX2 = d;
      this.viewEntityY2 = d2;
      this.viewEntityZ2 = d3;
      super.initialize(d, d2, d3);
   }

   public void setupArrayPointers() {
      if (Config.isShaderPackLoaded()) {
         ShadersRender.setupArrayPointersVbo();
      } else {
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5121, 28, 12L);
         GL11.glTexCoordPointer(2, 5126, 28, 16L);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glTexCoordPointer(2, 5122, 28, 24L);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
      }
   }
}
