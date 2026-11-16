package client.util;

import cc.baritone.utils.BaritoneManager;
import client.Client;
import client.module.Module;
import client.setting.BlockSolidType;
import java.nio.Buffer;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.item.ItemArrayEntryAdapter;
import org.lwjgl.opengl.GL11;

public class DoubleEntryAdapter extends ExtraDoubleEntry {
   public double value5;
   public double value6;
   public double value4;
   public IntBuffer intBuffer = VectorMatcher.resolveIntBuffer(16);

   @Override
   public void applyBlockSolidType(BlockSolidType blockSolidType) {
      if (this.enabled) {
         if (!BuildManager.isActive55()) {
            for (RenderChunk renderChunk : (java.lang.Iterable<RenderChunk>) (this.list)) {
               ItemArrayEntryAdapter itemArrayEntryAdapter = (ItemArrayEntryAdapter)renderChunk;
               GlStateManager.pushMatrix();
               this.applyRenderChunk(renderChunk);
               GL11.glCallList(itemArrayEntryAdapter.computeCount(blockSolidType, itemArrayEntryAdapter.getIronShard()));
               if (((Module)Client.getClient().getModuleManager().baritone).isToggled()
                  && (Boolean)BaritoneManager.getSettings().renderCachedChunks.dataRef
                  && !Minecraft.getMinecraft().isFullScreen()) {
                  GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               }

               GlStateManager.popMatrix();
            }
         } else {
            int count = Integer.MIN_VALUE;
            int count2 = Integer.MIN_VALUE;

            for (RenderChunk renderChunk2 : (java.lang.Iterable<RenderChunk>) (this.list)) {
               ItemArrayEntryAdapter itemArrayEntryAdapter2 = (ItemArrayEntryAdapter)renderChunk2;
               if (count != renderChunk2.count || count2 != renderChunk2.count5) {
                  if (this.intBuffer.position() > 0) {
                     this.applyIntBuffer(count, count2, this.intBuffer);
                  }

                  count = renderChunk2.count;
                  count2 = renderChunk2.count5;
               }

               if (this.intBuffer.position() >= this.intBuffer.capacity()) {
                  IntBuffer buffer = VectorMatcher.resolveIntBuffer(this.intBuffer.capacity() * 2);
                  ((Buffer)this.intBuffer).flip();
                  buffer.put(this.intBuffer);
                  this.intBuffer = buffer;
               }

               this.intBuffer.put(itemArrayEntryAdapter2.computeCount(blockSolidType, itemArrayEntryAdapter2.getIronShard()));
            }

            if (this.intBuffer.position() > 0) {
               this.applyIntBuffer(count, count2, this.intBuffer);
            }
         }

         if (BuildManager.isActive46()) {
            GlStateManager.bindTexture();
         }

         GlStateManager.applyState();
         this.list.clear();
      }
   }

   public void applyIndex(int count, int count2, int count3) {
      GlStateManager.translate((float)((double)count - this.value5), (float)((double)count2 - this.value6), (float)((double)count3 - this.value4));
   }

   private void applyIntBuffer(int count, int count2, IntBuffer buffer) {
      GlStateManager.pushMatrix();
      this.applyIndex(count, 0, count2);
      ((Buffer)buffer).flip();
      GlStateManager.applyIntBuffer(buffer);
      ((Buffer)buffer).clear();
      GlStateManager.popMatrix();
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ) {
      this.value5 = posX;
      this.value6 = posY;
      this.value4 = posZ;
      super.applyDouble(posX, posY, posZ);
   }
}
