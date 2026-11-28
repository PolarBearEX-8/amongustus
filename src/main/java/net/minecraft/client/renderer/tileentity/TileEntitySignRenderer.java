package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class TileEntitySignRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
   private static double textRenderDistanceSq = 4096.0;
   public ModelSign model = new ModelSign();

   public static void updateTextRenderDistance() {
      Minecraft mc = Config.getMc();
      double d = (double)Config.limit3(mc.gameSettings.gammaSetting, 1.0F, 120.0F);
      double d2 = Math.max(1.5 * (double)mc.displayHeight / d, 16.0);
      textRenderDistanceSq = d2 * d2;
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntitySign)tileEntity, d, d2, d3, f, n);
   }

   private static boolean isRenderText(TileEntitySign tileEntitySign) {
      if (Shaders.isShadowPass) {
         return false;
      } else if (Config.getMc().currentScreen instanceof GuiEditSign) {
         return true;
      } else {
         if (!Config.zoomMode && tileEntitySign.lineBeingEdited < 0) {
            Entity renderViewEntity = Config.getMc().getRenderViewEntity();
            if (tileEntitySign.getDistanceSq(renderViewEntity.posX, renderViewEntity.posY, renderViewEntity.posZ) > textRenderDistanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   public void renderTileEntityAt(TileEntitySign tileEntitySign, double d, double d2, double d3, float f, int n) {
      Block blockType = tileEntitySign.getBlockType();
      GlStateManager.pushMatrix();
      float f2 = 0.6666667F;
      if (blockType == Blocks.standing_sign) {
         GlStateManager.translate((float)d + 0.5F, (float)d2 + 0.75F * f2, (float)d3 + 0.5F);
         GlStateManager.rotate(-((float)(tileEntitySign.getBlockMetadata() * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
         this.model.signStick.showModel = true;
      } else {
         int blockMetadata = tileEntitySign.getBlockMetadata();
         float f4 = 0.0F;
         if (blockMetadata == 2) {
            f4 = 180.0F;
         }

         if (blockMetadata == 4) {
            f4 = 90.0F;
         }

         if (blockMetadata == 5) {
            f4 = -90.0F;
         }

         GlStateManager.translate((float)d + 0.5F, (float)d2 + 0.75F * f2, (float)d3 + 0.5F);
         GlStateManager.rotate(-f4, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
         this.model.signStick.showModel = false;
      }

      if (n >= 0) {
         this.bindTexture(DESTROY_STAGES[n]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.scale(4.0F, 2.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         this.bindTexture(SIGN_TEXTURE);
      }

      GlStateManager.enableRescaleNormal();
      GlStateManager.pushMatrix();
      GlStateManager.scale(f2, -f2, -f2);
      this.model.renderSign();
      GlStateManager.popMatrix();
      if (isRenderText(tileEntitySign)) {
         FontRenderer fontRenderer = this.getFontRenderer();
         float f5 = 0.015625F * f2;
         GlStateManager.translate(0.0F, 0.5F * f2, 0.07F * f2);
         GlStateManager.scale(f5, -f5, f5);
         GL11.glNormal3f(0.0F, 0.0F, -1.0F * f5);
         GlStateManager.depthMask(false);
         int n2 = 0;
         if (Config.isCustomColors()) {
            n2 = CustomColors.getSignTextColor(n2);
         }

         if (n < 0) {
            for (int i = 0; i < tileEntitySign.signText.length; i++) {
               if (tileEntitySign.signText[i] != null) {
                  IChatComponent component = tileEntitySign.signText[i];
                  if (!component.isEmpty() || i == tileEntitySign.lineBeingEdited) {
                     String str = GuiUtilRenderComponents.wrapTextToString(component, 90, fontRenderer, false, true);
                     if (i == tileEntitySign.lineBeingEdited) {
                        str = "> " + str + " <";
                        fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, i * 10 - tileEntitySign.signText.length * 5, n2);
                     } else {
                        fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, i * 10 - tileEntitySign.signText.length * 5, n2);
                     }
                  }
               }
            }
         }
      }

      GlStateManager.depthMask(true);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      if (n >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }
   }
}
