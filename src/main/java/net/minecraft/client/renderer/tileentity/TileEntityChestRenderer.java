package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.render.GlStateSnapshot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation textureTrappedDouble = new ResourceLocation("textures/entity/chest/trapped_double.png");
   private static final ResourceLocation textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
   private static final ResourceLocation textureNormalDouble = new ResourceLocation("textures/entity/chest/normal_double.png");
   private static final ResourceLocation textureTrapped = new ResourceLocation("textures/entity/chest/trapped.png");
   private static final ResourceLocation textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
   private static final ResourceLocation textureNormal = new ResourceLocation("textures/entity/chest/normal.png");
   public ModelChest simpleChest = new ModelChest();
   public ModelChest largeChest = new ModelLargeChest();
   private boolean isChristams;
   private final GlStateSnapshot glStateSnapshot = new GlStateSnapshot();

   public void renderTileEntityAt(TileEntityChest tileEntityChest, double d, double d2, double d3, float f, int n) {
      GlStateManager.enableDepth();
      GlStateManager.depthFunc(515);
      GlStateManager.depthMask(true);
      boolean flag = false;
      int n2;
      if (!tileEntityChest.hasWorldObj()) {
         n2 = 0;
         flag = true;
      } else {
         Block blockType = tileEntityChest.getBlockType();
         n2 = tileEntityChest.getBlockMetadata();
         if (blockType instanceof BlockChest && n2 == 0) {
            ((BlockChest)blockType).checkForSurroundingChests(tileEntityChest.getWorldObj(), tileEntityChest.getPos(), tileEntityChest.getWorldObj().getBlockState(tileEntityChest.getPos()));
            n2 = tileEntityChest.getBlockMetadata();
         }

         tileEntityChest.checkForAdjacentChests();
      }

      if (tileEntityChest.adjacentChestZNeg == null && tileEntityChest.adjacentChestXNeg == null) {
         ModelChest modelChest;
         if (tileEntityChest.adjacentChestXPos == null && tileEntityChest.adjacentChestZPos == null) {
            modelChest = this.simpleChest;
            if (n >= 0) {
               this.bindTexture(DESTROY_STAGES[n]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.scale(4.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.isChristams) {
               this.bindTexture(textureChristmas);
            } else if (tileEntityChest.getChestType() == 1) {
               this.bindTexture(textureTrapped);
            } else {
               this.bindTexture(textureNormal);
            }
         } else {
            modelChest = this.largeChest;
            if (n >= 0) {
               this.bindTexture(DESTROY_STAGES[n]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.scale(8.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.isChristams) {
               this.bindTexture(textureChristmasDouble);
            } else if (tileEntityChest.getChestType() == 1) {
               this.bindTexture(textureTrappedDouble);
            } else {
               this.bindTexture(textureNormalDouble);
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         if (n < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         }

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

         if (n2 == 2 && tileEntityChest.adjacentChestXPos != null) {
            GlStateManager.translate(1.0F, 0.0F, 0.0F);
         }

         if (n2 == 5 && tileEntityChest.adjacentChestZPos != null) {
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
         }

         GlStateManager.rotate((float)s, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, -0.5F);
         float f2 = tileEntityChest.prevLidAngle + (tileEntityChest.lidAngle - tileEntityChest.prevLidAngle) * f;
         if (tileEntityChest.adjacentChestZNeg != null) {
            float f3 = tileEntityChest.adjacentChestZNeg.prevLidAngle + (tileEntityChest.adjacentChestZNeg.lidAngle - tileEntityChest.adjacentChestZNeg.prevLidAngle) * f;
            if (f3 > f2) {
               f2 = f3;
            }
         }

         if (tileEntityChest.adjacentChestXNeg != null) {
            float f4 = tileEntityChest.adjacentChestXNeg.prevLidAngle + (tileEntityChest.adjacentChestXNeg.lidAngle - tileEntityChest.adjacentChestXNeg.prevLidAngle) * f;
            if (f4 > f2) {
               f2 = f4;
            }
         }

         f2 = 1.0F - f2;
         f2 = 1.0F - f2 * f2 * f2;
         modelChest.chestLid.rotateAngleX = -(f2 * 3.1415927F / 2.0F);
         if (!ClientManagers.moduleManager.chams.isToggled() || !ClientManagers.moduleManager.chams.chests.getValue() || flag) {
            modelChest.renderAll();
         } else if (ClientManagers.moduleManager.chams.coloredChestChams.getValue()) {
            if (ClientManagers.moduleManager.chams.renderTexture.getValue() && !ClientManagers.moduleManager.chams.onlyThroughWalls.getValue()) {
               modelChest.renderAll();
            }

            boolean isLightingEnabled = GlStateManager.isLightingEnabled();
            boolean isDepthEnabled = GlStateManager.isDepthEnabled();
            boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            RenderUtil.setGlColor(ClientManagers.moduleManager.chams.chestColor.getColorInt((byte)0));
            GlStateManager.disableTexture2D();
            modelChest.renderAll();
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
               modelChest.renderAll();
            }
         } else {
            boolean flag2 = GL11.glIsEnabled(32823);
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0F, -1100000.0F);
            modelChest.renderAll();
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
            if (!flag2) {
               GL11.glDisable(32823);
            }
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

   public TileEntityChestRenderer() {
      Calendar instance = Calendar.getInstance();
      if (instance.get(2) + 1 == 12 && instance.get(5) >= 24 && instance.get(5) <= 26) {
         this.isChristams = true;
      }
   }

   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityChest)tileEntity, d, d2, d3, f, n);
   }
}
