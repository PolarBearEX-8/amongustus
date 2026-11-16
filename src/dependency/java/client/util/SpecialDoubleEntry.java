package client.util;

import cc.baritone.utils.BaritoneManager;
import client.Client;
import client.module.Module;
import client.setting.BlockSolidType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.item.DiamondChestplate;
import org.lwjgl.opengl.GL11;

public class SpecialDoubleEntry extends ExtraDoubleEntry {
   public double value5;
   public double value4;
   public double value6;

   private void applyCurveChecker(int count, int count2, CurveChecker curveChecker) {
      GlStateManager.pushMatrix();
      this.applyIndex(count, 0, count2);
      curveChecker.applySpecialDoubleEntry(this);
      GlStateManager.popMatrix();
   }

   @Override
   public void applyBlockSolidType(BlockSolidType blockSolidType) {
      if (this.enabled) {
         if (!BuildManager.isActive55()) {
            for (RenderChunk renderChunk : (java.lang.Iterable<RenderChunk>) (this.list)) {
               DiamondChestplate diamondChestplate = renderChunk.resolveDiamondChestplate(blockSolidType.ordinal());
               GlStateManager.pushMatrix();
               this.applyRenderChunk(renderChunk);
               renderChunk.updateState();
               diamondChestplate.updateState2();
               this.updateState();
               diamondChestplate.applyIndex(7);
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
            CurveChecker curveChecker = null;

            for (RenderChunk renderChunk2 : (java.lang.Iterable<RenderChunk>) (this.list)) {
               DiamondChestplate diamondChestplate2 = renderChunk2.resolveDiamondChestplate(blockSolidType.ordinal());
               CurveChecker curveChecker2 = diamondChestplate2.getCurveChecker();
               if (curveChecker2 != curveChecker || count != renderChunk2.count || count2 != renderChunk2.count5) {
                  if (curveChecker != null) {
                     this.applyCurveChecker(count, count2, curveChecker);
                  }

                  count = renderChunk2.count;
                  count2 = renderChunk2.count5;
                  curveChecker = curveChecker2;
               }

               diamondChestplate2.applyIndex(7);
            }

            if (curveChecker != null) {
               this.applyCurveChecker(count, count2, curveChecker);
            }
         }

         OpenGlHelper.applyIndex2(OpenGlHelper.count14, 0);
         GlStateManager.applyState();
         this.list.clear();
      }
   }

   public void updateState() {
      if (BuildManager.canExecute()) {
         RotationBridge.updateState15();
      } else {
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5121, 28, 12L);
         GL11.glTexCoordPointer(2, 5126, 28, 16L);
         OpenGlHelper.applyIndex4(OpenGlHelper.defaultTexUnit);
         GL11.glTexCoordPointer(2, 5122, 28, 24L);
         OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
      }
   }

   public void applyIndex(int count, int count2, int count3) {
      GlStateManager.translate((float)((double)count - this.value5), (float)((double)count2 - this.value4), (float)((double)count3 - this.value6));
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ) {
      this.value5 = posX;
      this.value4 = posY;
      this.value6 = posZ;
      super.applyDouble(posX, posY, posZ);
   }
}
