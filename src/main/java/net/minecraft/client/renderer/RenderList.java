package net.minecraft.client.renderer;

import baritone.Baritone;
import java.nio.Buffer;
import java.nio.IntBuffer;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.Config;
import org.lwjgl.opengl.GL11;

public class RenderList extends ChunkRenderContainer {
   private double viewEntityX2;
   private double viewEntityY2;
   private double viewEntityZ2;
   public IntBuffer bufferLists = GLAllocation.createDirectIntBuffer(16);

   private void drawRegion(int n, int n2, IntBuffer intBuffer) {
      GlStateManager.pushMatrix();
      this.preRenderRegion(n, 0, n2);
      ((Buffer)intBuffer).flip();
      GlStateManager.callLists(intBuffer);
      ((Buffer)intBuffer).clear();
      GlStateManager.popMatrix();
   }

   @Override
   public void renderChunkLayer(EnumWorldBlockLayer enumWorldBlockLayer) {
      if (this.initialized) {
         if (!Config.isRenderRegions()) {
            for (RenderChunk renderChunk : (Iterable<RenderChunk>)(this.renderChunks)) {
               ListedRenderChunk listedRenderChunk = (ListedRenderChunk)renderChunk;
               GlStateManager.pushMatrix();
               this.preRenderChunk(renderChunk);
               GL11.glCallList(listedRenderChunk.getDisplayList(enumWorldBlockLayer, listedRenderChunk.getCompiledChunk()));
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

            for (RenderChunk renderChunk2 : (Iterable<RenderChunk>)(this.renderChunks)) {
               ListedRenderChunk listedRenderChunk2 = (ListedRenderChunk)renderChunk2;
               if (n != renderChunk2.regionX || n2 != renderChunk2.regionZ) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(n, n2, this.bufferLists);
                  }

                  n = renderChunk2.regionX;
                  n2 = renderChunk2.regionZ;
               }

               if (this.bufferLists.position() >= this.bufferLists.capacity()) {
                  IntBuffer intBuffer = GLAllocation.createDirectIntBuffer(this.bufferLists.capacity() * 2);
                  ((Buffer)this.bufferLists).flip();
                  intBuffer.put(this.bufferLists);
                  this.bufferLists = intBuffer;
               }

               this.bufferLists.put(listedRenderChunk2.getDisplayList(enumWorldBlockLayer, listedRenderChunk2.getCompiledChunk()));
            }

            if (this.bufferLists.position() > 0) {
               this.drawRegion(n, n2, this.bufferLists);
            }
         }

         if (Config.isMultiTexture()) {
            GlStateManager.bindCurrentTexture();
         }

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
}
