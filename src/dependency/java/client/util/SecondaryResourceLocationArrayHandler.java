package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.DiamondShard;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SecondaryResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/sign.png");
   public static double value = 4096.0;
   public DiamondShard diamondShard = new DiamondShard();

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntitySign((TileEntitySign)tileEntity, posX, posY, posZ, factor, count);
   }

   public static void updateState() {
      Minecraft mc = BuildManager.getMc();
      double offset = (double)BuildManager.computeFactor(mc.gameSettings.scaleFactor100, 1.0F, 120.0F);
      double offset2 = Math.max(1.5 * (double)mc.displayHeight / offset, 16.0);
      value = offset2 * offset2;
   }

   public void applyTileEntitySign(TileEntitySign tileEntitySign, double posX, double posY, double posZ, float factor, int count) {
      Block block = tileEntitySign.getBlock();
      GlStateManager.pushMatrix();
      float factor2 = 0.6666667F;
      if (block == Blocks.block37) {
         GlStateManager.translate((float)posX + 0.5F, (float)posY + 0.75F * factor2, (float)posZ + 0.5F);
         GlStateManager.rotate(-((float)(tileEntitySign.getCount() * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
         this.diamondShard.boxesManager2.enabled3 = true;
      } else {
         int count2 = tileEntitySign.getCount();
         float factor3 = 0.0F;
         if (count2 == 2) {
            factor3 = 180.0F;
         }

         if (count2 == 4) {
            factor3 = 90.0F;
         }

         if (count2 == 5) {
            factor3 = -90.0F;
         }

         GlStateManager.translate((float)posX + 0.5F, (float)posY + 0.75F * factor2, (float)posZ + 0.5F);
         GlStateManager.rotate(-factor3, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
         this.diamondShard.boxesManager2.enabled3 = false;
      }

      if (count >= 0) {
         this.applyResourceLocation2(resourceLocationArray[count]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(4.0F, 2.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         this.applyResourceLocation2(resourceLocation2);
      }

      GlStateManager.disableLighting();
      GlStateManager.pushMatrix();
      GlStateManager.translate3f3(factor2, -factor2, -factor2);
      this.diamondShard.updateState();
      GlStateManager.popMatrix();
      if (matchesTileEntitySign(tileEntitySign)) {
         FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
         float factor4 = 0.015625F * factor2;
         GlStateManager.translate(0.0F, 0.5F * factor2, 0.07F * factor2);
         GlStateManager.translate3f3(factor4, -factor4, factor4);
         GL11.glNormal3f(0.0F, 0.0F, -1.0F * factor4);
         GlStateManager.depthMask(false);
         int count3 = 0;
         if (BuildManager.isActive33()) {
            count3 = LoadingManager.computeCount3(count3);
         }

         if (count < 0) {
            for (int index = 0; index < tileEntitySign.iChatComponentArray.length; index++) {
               if (tileEntitySign.iChatComponentArray[index] != null) {
                  IChatComponent iChatComponent = tileEntitySign.iChatComponentArray[index];
                  if (!iChatComponent.isActive() || index == tileEntitySign.count2) {
                     String text = FovWriter.resolveText(iChatComponent, 90, fontRendererAsFontRenderer, false, true);
                     if (index == tileEntitySign.count2) {
                        String text2 = "> " + text + " <";
                        fontRendererAsFontRenderer.getIndex(text2, -fontRendererAsFontRenderer.getIndex(text2) / 2, index * 10 - tileEntitySign.iChatComponentArray.length * 5, count3);
                     } else {
                        fontRendererAsFontRenderer.getIndex(text, -fontRendererAsFontRenderer.getIndex(text) / 2, index * 10 - tileEntitySign.iChatComponentArray.length * 5, count3);
                     }
                  }
               }
            }
         }
      }

      GlStateManager.depthMask(true);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      if (count >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }
   }

   private static boolean matchesTileEntitySign(TileEntitySign tileEntitySign) {
      if (PreEndHandManager.enabled12) {
         return false;
      } else if (BuildManager.getMc().currentScreen instanceof SplineRegistry) {
         return true;
      } else {
         if (!BuildManager.enabled10 && tileEntitySign.count2 < 0) {
            Entity renderViewEntity = BuildManager.getMc().getRenderViewEntity();
            if (tileEntitySign.computeValue(renderViewEntity.posX, renderViewEntity.posY, renderViewEntity.posZ) > value) {
               return false;
            }
         }

         return true;
      }
   }
}
