package net.minecraft.client.renderer.tileentity;

import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityEnderChestRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation ENDER_CHEST_TEXTURE = new ResourceLocation("textures/entity/chest/ender.png");
   public ModelChest modelChest = new ModelChest();

   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityEnderChest)tileEntity, d, d2, d3, f, n);
   }

   public void renderTileEntityAt(TileEntityEnderChest tileEntityEnderChest, double d, double d2, double d3, float f, int n) {
      int n2 = 0;
      boolean flag = true;
      if (tileEntityEnderChest.hasWorldObj()) {
         n2 = tileEntityEnderChest.getBlockMetadata();
         flag = false;
      }

      if (n >= 0) {
         this.bindTexture(DESTROY_STAGES[n]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.scale(4.0F, 4.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         this.bindTexture(ENDER_CHEST_TEXTURE);
      }

      GlStateManager.pushMatrix();
      GlStateManager.enableRescaleNormal();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.translate((float)d, (float)d2 + 1.0F, (float)d3 + 1.0F);
      GlStateManager.scale(1.0F, -1.0F, -1.0F);
      GlStateManager.translate(0.5F, 0.5F, 0.5F);
      short s = 0;
      if (n2 == 2) {
         s = 180;
      }

      if (n2 == 3) {
         s = 0;
      }

      if (n2 == 4) {
         s = 90;
      }

      if (n2 == 5) {
         s = -90;
      }

      GlStateManager.rotate((float)s, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(-0.5F, -0.5F, -0.5F);
      float f2 = tileEntityEnderChest.prevLidAngle + (tileEntityEnderChest.lidAngle - tileEntityEnderChest.prevLidAngle) * f;
      f2 = 1.0F - f2;
      f2 = 1.0F - f2 * f2 * f2;
      this.modelChest.chestLid.rotateAngleX = -(f2 * 3.1415927F / 2.0F);
      if (!ClientManagers.moduleManager.chams.isToggled() || !ClientManagers.moduleManager.chams.chests.getValue() || flag) {
         this.modelChest.renderAll();
      } else if (ClientManagers.moduleManager.chams.coloredChestChams.getValue()) {
         if (ClientManagers.moduleManager.chams.renderTexture.getValue() && !ClientManagers.moduleManager.chams.onlyThroughWalls.getValue()) {
            this.modelChest.renderAll();
         }

         boolean isLightingEnabled = GlStateManager.isLightingEnabled();
         boolean isDepthEnabled = GlStateManager.isDepthEnabled();
         boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
         GlStateManager.disableLighting();
         GlStateManager.disableDepth();
         RenderUtil.setGlColor(ClientManagers.moduleManager.chams.enderChestColor.getColorInt((byte)0));
         GlStateManager.disableTexture2D();
         this.modelChest.renderAll();
         GlStateManager.enableDepth();
         if (isLightingEnabled) {
            GlStateManager.enableLighting();
         }

         if (!isDepthEnabled) {
            GlStateManager.disableDepth();
         }

         if (isTexture2DEnabled) {
            GlStateManager.enableTexture2D();
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (ClientManagers.moduleManager.chams.onlyThroughWalls.getValue()) {
            this.modelChest.renderAll();
         }
      } else {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);
         this.modelChest.renderAll();
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, 1100000.0F);
      }

      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (n >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }
   }
}
