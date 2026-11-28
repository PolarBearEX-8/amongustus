package net.augustus.render;

import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.GlStateSaver;
import net.augustus.utils.shader.RoundRectShader;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class RenderUtil implements MC {
   private static final GlStateSaver BATCH_GL_STATE_SAVER = GlStateSaver.create();
   private static final GlStateSaver GL_STATE_SAVER = GlStateSaver.create();

   public static void drawRect(float x, float y, float x2, float y2, float f5, float f6, float f7, float f8) {
      if (x < x2) {
         float f9 = x;
         x = x2;
         x2 = f9;
      }

      if (y < y2) {
         float f10 = y;
         y = y2;
         y2 = f10;
      }

      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GL_STATE_SAVER.setAlpha(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
      GlStateManager.color(f5, f6, f7, f8);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)x, (double)y2, 0.0).endVertex();
      worldRenderer.pos((double)x2, (double)y2, 0.0).endVertex();
      worldRenderer.pos((double)x2, (double)y, 0.0).endVertex();
      worldRenderer.pos((double)x, (double)y, 0.0).endVertex();
      instance.draw();
      GL_STATE_SAVER.restoreTexture2D();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreAlpha();
   }

   public static void drawRoundedRectOutline(
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      int n,
      int n2,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      int n3
   ) {
      if (ClientManagers.moduleManager.clientSettings.useAntialiasedRoundRects.getValue()) {
         RoundRectShader.getInstance().drawRoundedOutline(f, f2, f3 - f, f4 - f2, f5, (float)n2, flag, flag2, flag4, flag3, n3);
      } else {
         drawRoundedRectOutlineGl(f, f2, f3, f4, f5, n, n2, flag, flag2, flag3, flag4, n3);
      }
   }

   public static void drawTopRoundedRect(float f, float f2, float f3, float f4, float f5, int n, int n2) {
      drawRoundedRect(f, f2, f3, f4, f5, n, true, true, false, false, n2);
   }

   public static void drawEntityEspBox(EntityLivingBase entity, ColorSetting colorSetting, float f, int n, boolean flag) {
      if (mc.thePlayer.getDistanceToEntity(entity) > 1.0F) {
         double d = (double)(1.0F - mc.thePlayer.getDistanceToEntity(entity) / 20.0F);
         if (d < 0.3) {
            d = 0.3;
         }

         f *= (float)d;
      }

      double d2;
      double d3;
      double d4;
      if (entity instanceof EntityPlayerSP) {
         if (flag) {
            d4 = mc.thePlayer.getPrevRenderServerPos().xCoord + (mc.thePlayer.getRenderServerPos().xCoord - mc.thePlayer.getPrevRenderServerPos().xCoord) * (double)mc.getTimer().renderPartialTicks;
            d2 = mc.thePlayer.getPrevRenderServerPos().yCoord + (mc.thePlayer.getRenderServerPos().yCoord - mc.thePlayer.getPrevRenderServerPos().yCoord) * (double)mc.getTimer().renderPartialTicks;
            d3 = mc.thePlayer.getPrevRenderServerPos().zCoord + (mc.thePlayer.getRenderServerPos().zCoord - mc.thePlayer.getPrevRenderServerPos().zCoord) * (double)mc.getTimer().renderPartialTicks;
         } else {
            d4 = mc.thePlayer.getServerPos().xCoord;
            d2 = mc.thePlayer.getServerPos().yCoord;
            d3 = mc.thePlayer.getServerPos().zCoord;
         }
      } else if (flag && entity.interpolatedServerPos != null && entity.prevInterpolatedServerPos != null) {
         d4 = entity.prevInterpolatedServerPos.xCoord + (entity.interpolatedServerPos.xCoord - entity.prevInterpolatedServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
         d2 = entity.prevInterpolatedServerPos.yCoord + (entity.interpolatedServerPos.yCoord - entity.prevInterpolatedServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
         d3 = entity.prevInterpolatedServerPos.zCoord + (entity.interpolatedServerPos.zCoord - entity.prevInterpolatedServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      } else {
         d4 = entity.prevRawServerPos.xCoord + (entity.rawServerPos.xCoord - entity.prevRawServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
         d2 = entity.prevRawServerPos.yCoord + (entity.rawServerPos.yCoord - entity.prevRawServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
         d3 = entity.prevRawServerPos.zCoord + (entity.rawServerPos.zCoord - entity.prevRawServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      }

      float f2 = (float)(d4 - mc.getRenderManager().getRenderPosX());
      float f3 = (float)(d2 - mc.getRenderManager().getRenderPosY());
      float f4 = (float)(d3 - mc.getRenderManager().getRenderPosZ());
      int colorInt = colorSetting.getColorInt((byte)0);
      setGlColor(colorInt);
      float f5 = ClientManagers.moduleManager.child.isToggled() && entity instanceof EntityPlayer ? entity.height / 1.7F : entity.height;
      double d5 = (double)(entity.width - 0.2F);
      drawRotatedEntityBox(entity, f2, f3, f4, d5, (double)(f5 + 0.1F));
      if (f > 0.0F) {
         GL11.glLineWidth(f);
         setGlColorWithAlpha(colorInt, n);
         d5 = (double)(entity.width - 0.2F);
         drawRotatedEntityBoxOutline(entity, f2, f3, f4, d5, (double)(f5 + 0.1F));
      }
   }

   public static void drawLine(double x1, double y1, double x2, double y2, float lineWidth, int color) {
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(lineWidth);
      setGlColor(color);
      GL11.glBegin(1);
      GL11.glVertex2d(x1, y1);
      GL11.glVertex2d(x2, y2);
      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawRotatedEntityBox(Entity entity, float f, float f2, float f3, double d, double d2) {
      float f4 = 0.017453292F;
      d *= 1.5;
      float f5 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 45.0F;
      float f6;
      if (f5 < 0.0F) {
         f6 = 0.0F;
         f6 += 360.0F - Math.abs(f5);
      } else {
         f6 = f5;
      }

      f6 *= -1.0F;
      f6 *= f4;
      float f7 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 135.0F;
      float f8;
      if (f7 < 0.0F) {
         f8 = 0.0F;
         f8 += 360.0F - Math.abs(f7);
      } else {
         f8 = f7;
      }

      f8 *= -1.0F;
      f8 *= f4;
      float f9 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 225.0F;
      float f10;
      if (f9 < 0.0F) {
         f10 = 0.0F;
         f10 += 360.0F - Math.abs(f9);
      } else {
         f10 = f9;
      }

      f10 *= -1.0F;
      f10 *= f4;
      float f11 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 315.0F;
      float f12;
      if (f11 < 0.0F) {
         f12 = 0.0F;
         f12 += 360.0F - Math.abs(f11);
      } else {
         f12 = f11;
      }

      f12 *= -1.0F;
      f12 *= f4;
      float f13 = (float)(Math.sin((double)f6) * d + (double)f);
      float f14 = (float)(Math.cos((double)f6) * d + (double)f3);
      float f15 = (float)(Math.sin((double)f8) * d + (double)f);
      float f16 = (float)(Math.cos((double)f8) * d + (double)f3);
      float f17 = (float)(Math.sin((double)f10) * d + (double)f);
      float f18 = (float)(Math.cos((double)f10) * d + (double)f3);
      float f19 = (float)(Math.sin((double)f12) * d + (double)f);
      float f20 = (float)(Math.cos((double)f12) * d + (double)f3);
      float f21 = (float)((double)f2 + d2);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.pos((double)f13, (double)f21, (double)f14).endVertex();
      worldRenderer.pos((double)f15, (double)f21, (double)f16).endVertex();
      worldRenderer.pos((double)f15, (double)f2, (double)f16).endVertex();
      worldRenderer.pos((double)f15, (double)f2, (double)f16).endVertex();
      worldRenderer.pos((double)f15, (double)f21, (double)f16).endVertex();
      worldRenderer.pos((double)f17, (double)f21, (double)f18).endVertex();
      worldRenderer.pos((double)f17, (double)f2, (double)f18).endVertex();
      worldRenderer.pos((double)f17, (double)f2, (double)f18).endVertex();
      worldRenderer.pos((double)f17, (double)f21, (double)f18).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.pos((double)f13, (double)f21, (double)f14).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.pos((double)f15, (double)f2, (double)f16).endVertex();
      worldRenderer.pos((double)f17, (double)f2, (double)f18).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f13, (double)f21, (double)f14).endVertex();
      worldRenderer.pos((double)f15, (double)f21, (double)f16).endVertex();
      worldRenderer.pos((double)f17, (double)f21, (double)f18).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.endVertex();
      instance.draw();
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB box) {
      drawOutlinedBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
   }

   public static void drawTileEntityEspBox(TileEntity tileEntity, int n, float f, float f2, double d, boolean flag) {
      BlockPos pos = tileEntity.getPos();
      double d2 = (double)((float)pos.getX() + 0.0625F);
      double y = (double)pos.getY();
      double d3 = (double)((float)pos.getZ() + 0.0625F);
      double d4 = (double)((float)pos.getX() + 0.9375F);
      double d5 = (double)((float)pos.getY() + 0.875F);
      double d6 = (double)((float)pos.getZ() + 0.9375F);
      if (tileEntity instanceof TileEntityChest) {
         TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
         TileEntityChest tileEntityChest2 = null;
         if (tileEntityChest.adjacentChestXPos != null) {
            tileEntityChest2 = tileEntityChest.adjacentChestXPos;
         } else if (tileEntityChest.adjacentChestZPos != null) {
            tileEntityChest2 = tileEntityChest.adjacentChestZPos;
         }

         if (tileEntityChest2 != null) {
            BlockPos pos2 = tileEntityChest2.getPos();
            d2 = Math.min(d2, (double)((float)pos2.getX() + 0.0625F));
            y = Math.min(y, (double)pos2.getY());
            d3 = Math.min(d3, (double)((float)pos2.getZ() + 0.0625F));
            d4 = Math.max(d4, (double)((float)pos2.getX() + 0.9375F));
            d5 = Math.max(d5, (double)((float)pos2.getY() + 0.875F));
            d6 = Math.max(d6, (double)((float)pos2.getZ() + 0.9375F));
         }
      }

      d2 -= mc.getRenderManager().getRenderPosX();
      y -= mc.getRenderManager().getRenderPosY();
      d3 -= mc.getRenderManager().getRenderPosZ();
      d4 -= mc.getRenderManager().getRenderPosX();
      d5 -= mc.getRenderManager().getRenderPosY();
      d6 -= mc.getRenderManager().getRenderPosZ();
      setGlColor(n);
      if (flag) {
         drawFilledBox(d2 - 0.0625, y, d3 - 0.0625, d4 + 0.0625, d5 + 0.125, d6 + 0.0625);
      } else {
         drawFilledBox(d2 - d, y - d, d3 - d, d4 + d, d5 + d, d6 + d);
      }

      if (f2 > 0.0F) {
         GL11.glLineWidth(f2);
         setGlColorWithAlpha(n, f);
         if (flag) {
            drawWireframeBox(d2 - 0.0625, y, d3 - 0.0625, d4 + 0.0625, d5 + 0.125, d6 + 0.0625);
         } else {
            drawWireframeBox(d2 - d, y - d, d3 - d, d4 + d, d5 + d, d6 + d);
         }
      }
   }

   public static void drawRect(float x, float y, float x2, float y2, int n, int n2, int n3) {
      drawRect(x, y, x2, y2, (float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F, 1.0F);
   }

   public static void drawRoundedRect(float f, float f2, float f3, float f4, float f5, int n, Color color) {
      drawRoundedRect(f, f2, f3, f4, f5, n, color.getRGB());
   }

   public static void drawRect(float x, float y, float x2, float y2, int color) {
      float f5 = (float)(color >> 24 & 0xFF) / 255.0F;
      drawRect(x, y, x2, y2, (float)(color >> 16 & 0xFF) / 255.0F, (float)(color >> 8 & 0xFF) / 255.0F, (float)(color & 0xFF) / 255.0F, f5);
   }

   public static void drawTopRightRoundedRect(float f, float f2, float f3, float f4, float f5, int n, Color color) {
      drawRoundedRect(f, f2, f3, f4, f5, n, false, true, false, false, color);
   }

   public static void addFilledBoxVertices(double d, double d2, double d3, double d4, double d5, double d6) {
      addFilledBoxVertices((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6);
   }

   public static void setGlColorWithAlpha(int n, int n2) {
      GlStateManager.color((float)(n >> 16 & 0xFF) / 255.0F, (float)(n >> 8 & 0xFF) / 255.0F, (float)(n & 0xFF) / 255.0F, (float)n2 / 255.0F);
   }

   public static void drawRoundedRectOutlineGl(
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      int n,
      int n2,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      int n3
   ) {
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n3);
      GL11.glLineWidth((float)n2);
      float f6 = (float)n2 / 2.0F;
      float f7 = f + f6;
      float f8 = f2 + f6;
      float f9 = f3 - f6;
      float f10 = f4 - f6;
      GL11.glBegin(3);
      f5 = Math.min(Math.min(Math.abs(f10 - f8) / 2.0F, Math.abs(f9 - f7) / 2.0F), f5);
      n = Math.max(1, n - n % 3);
      if (flag4) {
         for (int i = 0; i <= 90; i += n) {
            GL11.glVertex2d(
               (double)(f9 - f5) + Math.sin(Math.toRadians((double)i)) * (double)f5,
               (double)(f10 - f5) + Math.cos(Math.toRadians((double)i)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f9, (double)f10);
      }

      if (flag2) {
         for (int j = 90; j <= 180; j += n) {
            GL11.glVertex2d(
               (double)(f9 - f5) + Math.sin(Math.toRadians((double)j)) * (double)f5,
               (double)(f8 + f5) + Math.cos(Math.toRadians((double)j)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f9, (double)f8);
      }

      if (flag) {
         for (int k = 180; k <= 270; k += n) {
            GL11.glVertex2d(
               (double)(f7 + f5) + Math.sin(Math.toRadians((double)k)) * (double)f5,
               (double)(f8 + f5) + Math.cos(Math.toRadians((double)k)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f7, (double)f8);
      }

      if (flag3) {
         for (int m = 270; m <= 360; m += n) {
            GL11.glVertex2d(
               (double)(f7 + f5) + Math.sin(Math.toRadians((double)m)) * (double)f5,
               (double)(f10 - f5) + Math.cos(Math.toRadians((double)m)) * (double)f5
            );
         }

         GL11.glVertex2d(
            (double)(f9 - f5) + Math.sin(Math.toRadians(0.0)) * (double)f5, (double)(f10 - f5) + Math.cos(Math.toRadians(0.0)) * (double)f5
         );
      } else {
         GL11.glVertex2d((double)f7, (double)f10);
      }

      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawResizeCornerTriangle(float f, float f2, float f3, float f4, float f5, int n, int n2) {
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n2);
      GL11.glBegin(9);
      f5 = Math.min(Math.min(Math.abs(f4 - f2) / 2.0F, Math.abs(f3 - f) / 2.0F), f5);
      n = Math.max(1, n - n % 3);

      for (int i = 0; i <= 90; i += n) {
         GL11.glVertex2d(
            (double)(f3 - f5) + Math.sin(Math.toRadians((double)i)) * (double)f5,
            (double)(f4 - f5) + Math.cos(Math.toRadians((double)i)) * (double)f5
         );
      }

      GL11.glVertex2d((double)f3, (double)f2);
      GL11.glVertex2d((double)f, (double)f4);
      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawOutlinedBoundingBox(double d, double d2, double d3, double d4, double d5, double d6) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos(d, d2, d3).endVertex();
      worldRenderer.pos(d, d2, d6).endVertex();
      worldRenderer.pos(d4, d2, d6).endVertex();
      worldRenderer.pos(d4, d2, d3).endVertex();
      worldRenderer.pos(d, d2, d3).endVertex();
      worldRenderer.pos(d, d5, d3).endVertex();
      worldRenderer.pos(d, d5, d6).endVertex();
      worldRenderer.pos(d4, d5, d6).endVertex();
      worldRenderer.pos(d4, d5, d3).endVertex();
      worldRenderer.pos(d, d5, d3).endVertex();
      instance.draw();
      worldRenderer.begin(1, DefaultVertexFormats.POSITION);
      worldRenderer.pos(d, d2, d3).endVertex();
      worldRenderer.pos(d, d5, d3).endVertex();
      worldRenderer.pos(d, d2, d6).endVertex();
      worldRenderer.pos(d, d5, d6).endVertex();
      worldRenderer.pos(d4, d2, d6).endVertex();
      worldRenderer.pos(d4, d5, d6).endVertex();
      worldRenderer.pos(d4, d2, d3).endVertex();
      worldRenderer.pos(d4, d5, d3).endVertex();
      instance.draw();
   }


   public static void drawWireframeBox(AxisAlignedBB box) {
      drawWireframeBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
   }

   public static void drawWireframeBox(double d, double d2, double d3, double d4, double d5, double d6) {
      drawWireframeBox((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6);
   }

   private static String getTexEnvModeName(int n) {
      switch (n) {
         case 260:
            return "GL_ADD";
         case 3042:
            return "GL_BLEND";
         case 7681:
            return "GL_REPLACE";
         case 8448:
            return "GL_MODULATE";
         case 8449:
            return "GL_DECAL";
         case 34160:
            return "GL_COMBINE";
         default:
            return "UNKNOWN (" + n + ")";
      }
   }

   public static void drawEntityCornerEsp(EntityLivingBase entity, int n) {
      float f = (float)(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
      float f2 = (float)(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY());
      float f3 = (float)(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ());
      GlStateManager.pushMatrix();
      GlStateManager.translate(f, f2 + (entity.isChild() ? entity.height / 3.5F : entity.height / 2.0F), f3);
      GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(-0.098, -0.098, 0.098);
      float f4 = (float)(26.6 * (double)entity.width / 2.0);
      float f5 = entity instanceof EntityPlayer
         ? (entity.isChild() ? 8.0F : 12.0F)
         : (float)(11.98 * (double)(entity.isChild() ? entity.height / 4.0F : entity.height / 2.0F));
      setGlColorWithAlpha(n, 1.0F);
      drawRawRect(f4, f5 - 1.0F, f4 - 4.0F, f5);
      drawRawRect(-f4, f5 - 1.0F, -f4 + 4.0F, f5);
      drawRawRect(-f4, f5, -f4 + 1.0F, f5 - 4.0F);
      drawRawRect(f4, f5, f4 - 1.0F, f5 - 4.0F);
      drawRawRect(f4, -f5, f4 - 4.0F, -f5 + 1.0F);
      drawRawRect(-f4, -f5, -f4 + 4.0F, -f5 + 1.0F);
      drawRawRect(-f4, -f5 + 1.0F, -f4 + 1.0F, -f5 + 4.0F);
      drawRawRect(f4, -f5 + 1.0F, f4 - 1.0F, -f5 + 4.0F);
      GlStateManager.color(0.0F, 0.0F, 0.0F);
      drawRawRect(f4, f5, f4 - 4.0F, f5 + 0.2F);
      drawRawRect(-f4, f5, -f4 + 4.0F, f5 + 0.2F);
      drawRawRect(-f4 - 0.2F, f5 + 0.2F, -f4, f5 - 4.0F);
      drawRawRect(f4 + 0.2F, f5 + 0.2F, f4, f5 - 4.0F);
      drawRawRect(f4 + 0.2F, -f5, f4 - 4.0F, -f5 - 0.2F);
      drawRawRect(-f4 - 0.2F, -f5, -f4 + 4.0F, -f5 - 0.2F);
      drawRawRect(-f4 - 0.2F, -f5, -f4, -f5 + 4.0F);
      drawRawRect(f4 + 0.2F, -f5, f4, -f5 + 4.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static void drawRoundedRect(
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      int n,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      int n2
   ) {
      if (ClientManagers.moduleManager.clientSettings.useAntialiasedRoundRects.getValue()) {
         RoundRectShader.getInstance().drawRoundedRect(f, f2, f3 - f, f4 - f2, f5, flag, flag2, flag4, flag3, n2);
      } else {
         drawRoundedRectGl(f, f2, f3, f4, f5, n, flag, flag2, flag3, flag4, n2);
      }
   }

   public static void drawRectOutline(double d, double d2, double d3, double d4, float f, int n) {
      drawRectOutline(d, d2, d3, d4, f, ColorUtil.getRedFloat(n), ColorUtil.getGreenFloat(n), ColorUtil.getBlueFloat(n), ColorUtil.getAlphaFloat(n));
   }

   public static void begin3DRender() {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.alphaFunc(516, 0.0F);
      GL11.glEnable(2848);
      GlStateManager.disableDepth();
      GlStateManager.disableTexture2D();
      if (Config.isShaderPackLoaded()) {
         Shaders.disableTexture2D();
      }

      GlStateManager.disableCull();
      GlStateManager.depthMask(false);
   }

   public static void drawRect(int x, int y, int x2, int y2, float f, float f2, float f3, float f4) {
      if (x < x2) {
         int n5 = x;
         x = x2;
         x2 = n5;
      }

      if (y < y2) {
         int n6 = y;
         y = y2;
         y2 = n6;
      }

      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GL_STATE_SAVER.setAlpha(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
      GlStateManager.color(f, f2, f3, f4);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)x, (double)y2, 0.0).endVertex();
      worldRenderer.pos((double)x2, (double)y2, 0.0).endVertex();
      worldRenderer.pos((double)x2, (double)y, 0.0).endVertex();
      worldRenderer.pos((double)x, (double)y, 0.0).endVertex();
      instance.draw();
      GL_STATE_SAVER.restoreTexture2D();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreAlpha();
   }

   public static void drawRoundedRectGl(
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      int n,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      int n2
   ) {
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n2);
      GL11.glBegin(9);
      f5 = Math.min(Math.min(Math.abs(f4 - f2) / 2.0F, Math.abs(f3 - f) / 2.0F), f5);
      n = Math.max(1, n - n % 3);
      if (flag4) {
         for (int i = 0; i <= 90; i += n) {
            GL11.glVertex2d(
               (double)(f3 - f5) + Math.sin(Math.toRadians((double)i)) * (double)f5,
               (double)(f4 - f5) + Math.cos(Math.toRadians((double)i)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f3, (double)f4);
      }

      if (flag2) {
         for (int j = 90; j <= 180; j += n) {
            GL11.glVertex2d(
               (double)(f3 - f5) + Math.sin(Math.toRadians((double)j)) * (double)f5,
               (double)(f2 + f5) + Math.cos(Math.toRadians((double)j)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f3, (double)f2);
      }

      if (flag) {
         for (int k = 180; k <= 270; k += n) {
            GL11.glVertex2d(
               (double)(f + f5) + Math.sin(Math.toRadians((double)k)) * (double)f5,
               (double)(f2 + f5) + Math.cos(Math.toRadians((double)k)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f, (double)f2);
      }

      if (flag3) {
         for (int m = 270; m <= 360; m += n) {
            GL11.glVertex2d(
               (double)(f + f5) + Math.sin(Math.toRadians((double)m)) * (double)f5,
               (double)(f4 - f5) + Math.cos(Math.toRadians((double)m)) * (double)f5
            );
         }
      } else {
         GL11.glVertex2d((double)f, (double)f4);
      }

      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawRect(float x, float y, float x2, float y2, float f5, float f6, float f7) {
      drawRect(x, y, x2, y2, f5, f6, f7, 1.0F);
   }

   public static void drawTileEntityCornerEsp(TileEntity tileEntity, int n) {
      double d = 0.0;
      double d2 = 0.0;
      if (tileEntity instanceof TileEntityChest) {
         TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
         if (tileEntityChest.adjacentChestXPos != null) {
            d += 0.5;
         } else if (tileEntityChest.adjacentChestZPos != null) {
            d2 += 0.5;
         }
      }

      float f = (float)((double)tileEntity.getPos().getX() + d - mc.getRenderManager().getRenderPosX());
      float f2 = (float)((double)tileEntity.getPos().getY() - mc.getRenderManager().getRenderPosY());
      float f3 = (float)((double)tileEntity.getPos().getZ() + d2 - mc.getRenderManager().getRenderPosZ());
      GlStateManager.pushMatrix();
      GlStateManager.translate((double)f + 0.5, (double)f2 + 0.47, (double)f3 + 0.5);
      GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(-0.098, -0.098, 0.098);
      float f4 = d == 0.0 && d2 == 0.0 ? 8.0F : 14.0F;
      float f5 = 7.5F;
      setGlColorWithAlpha(n, 1.0F);
      drawRawRect(f4, f5 - 1.0F, f4 - 4.0F, f5);
      drawRawRect(-f4, f5 - 1.0F, -f4 + 4.0F, f5);
      drawRawRect(-f4, f5, -f4 + 1.0F, f5 - 4.0F);
      drawRawRect(f4, f5, f4 - 1.0F, f5 - 4.0F);
      drawRawRect(f4, -f5, f4 - 4.0F, -f5 + 1.0F);
      drawRawRect(-f4, -f5, -f4 + 4.0F, -f5 + 1.0F);
      drawRawRect(-f4, -f5 + 1.0F, -f4 + 1.0F, -f5 + 4.0F);
      drawRawRect(f4, -f5 + 1.0F, f4 - 1.0F, -f5 + 4.0F);
      GlStateManager.color(0.0F, 0.0F, 0.0F);
      drawRawRect(f4, f5, f4 - 4.0F, f5 + 0.2F);
      drawRawRect(-f4, f5, -f4 + 4.0F, f5 + 0.2F);
      drawRawRect(-f4 - 0.2F, f5 + 0.2F, -f4, f5 - 4.0F);
      drawRawRect(f4 + 0.2F, f5 + 0.2F, f4, f5 - 4.0F);
      drawRawRect(f4 + 0.2F, -f5, f4 - 4.0F, -f5 - 0.2F);
      drawRawRect(-f4 - 0.2F, -f5, -f4 + 4.0F, -f5 - 0.2F);
      drawRawRect(-f4 - 0.2F, -f5, -f4, -f5 + 4.0F);
      drawRawRect(f4 + 0.2F, -f5, f4, -f5 + 4.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static void setGlColorWithAlpha(int n, float f) {
      GlStateManager.color((float)(n >> 16 & 0xFF) / 255.0F, (float)(n >> 8 & 0xFF) / 255.0F, (float)(n & 0xFF) / 255.0F, f);
   }

   public static void drawRect(double x, double y, double x2, double y2, int color) {
      float f = (float)(color >> 24 & 0xFF) / 255.0F;
      drawRect(x, y, x2, y2, (float)(color >> 16 & 0xFF) / 255.0F, (float)(color >> 8 & 0xFF) / 255.0F, (float)(color & 0xFF) / 255.0F, f);
   }

   public static void drawLine(int x1, int y1, int x2, int y2, float lineWidth, int color) {
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(lineWidth);
      setGlColor(color);
      GL11.glBegin(1);
      GL11.glVertex2f((float)x1, (float)y1);
      GL11.glVertex2f((float)x2, (float)y2);
      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawRect(int x, int y, int x2, int y2, int n5, int n6, int n7) {
      drawRect(x, y, x2, y2, (float)n5 / 255.0F, (float)n6 / 255.0F, (float)n7 / 255.0F, 1.0F);
   }

   public static void drawRect(int x, int y, int x2, int y2, int color) {
      float f = (float)(color >> 24 & 0xFF) / 255.0F;
      drawRect(x, y, x2, y2, (float)(color >> 16 & 0xFF) / 255.0F, (float)(color >> 8 & 0xFF) / 255.0F, (float)(color & 0xFF) / 255.0F, f);
   }

   public static void printGlStateDebug() {
      System.out.println("===== GL STATE DEBUG START =====");
      boolean flag = GL11.glIsEnabled(3042);
      System.out.println("GL_BLEND Enabled: " + flag);
      if (flag) {
         int n = GL11.glGetInteger(3041);
         int n2 = GL11.glGetInteger(3040);
         System.out.println("  Blend Src: " + getBlendFactorName(n) + " (" + n + ")");
         System.out.println("  Blend Dst: " + getBlendFactorName(n2) + " (" + n2 + ")");
      }

      boolean flag2 = GL11.glIsEnabled(3553);
      System.out.println("GL_TEXTURE_2D Enabled: " + flag2);
      if (flag2) {
         int n3 = GL11.glGetInteger(32873);
         System.out.println("  Bound Texture ID: " + n3);
         IntBuffer intBuffer = BufferUtils.createIntBuffer(16);
         GL11.glGetTexEnv(8960, 8704, intBuffer);
         int n4 = intBuffer.get(0);
         System.out.println("  TexEnv Mode: " + getTexEnvModeName(n4) + " (" + n4 + ")");
      }

      FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(2816, floatBuffer);
      System.out.printf("Current Color (RGBA): [%.2f, %.2f, %.2f, %.2f]%n", floatBuffer.get(0), floatBuffer.get(1), floatBuffer.get(2), floatBuffer.get(3));
      boolean flag3 = GL11.glIsEnabled(2896);
      System.out.println("GL_LIGHTING Enabled: " + flag3);
      boolean flag4 = GL11.glIsEnabled(2929);
      System.out.println("GL_DEPTH_TEST Enabled: " + flag4);
      if (flag4) {
         boolean flag5 = GL11.glGetBoolean(2930);
         System.out.println("  Depth Mask (Write): " + flag5);
      }

      boolean flag6 = GL11.glIsEnabled(3008);
      System.out.println("GL_ALPHA_TEST Enabled: " + flag6);
      System.out.println("===== GL STATE DEBUG END =====");
   }

   public static boolean isPointInBounds(int n, int n2, double d, double d2, double d3, double d4) {
      return (double)n >= d && (double)n2 >= d2 && (double)n < d3 && (double)n2 < d4;
   }

   public static int getHoveredColor(int n, double d, int n2, int n3, int n4, int n5, int n6, int n7) {
      return isHovered((float)n4, (float)n5, (float)n6, (float)n7, n2, n3) ? adjustColorBrightness(n, d) : n;
   }

   public static void drawBlockEspBox(BlockPos pos, int n, float f, float f2) {
      setGlColor(n);
      float f3 = (float)((double)pos.getX() - mc.getRenderManager().getRenderPosX());
      float f4 = (float)((double)pos.getY() - mc.getRenderManager().getRenderPosY());
      float f5 = (float)((double)pos.getZ() - mc.getRenderManager().getRenderPosZ());
      Block block = mc.theWorld.getBlockState(pos).getBlock();
      drawFilledBox(
         (double)f3,
         (double)f4,
         (double)f5,
         (double)f3 + block.getBlockBoundsMaxX(),
         (double)f4 + block.getBlockBoundsMaxY(),
         (double)f5 + block.getBlockBoundsMaxZ()
      );
      if (f2 > 0.0F) {
         GL11.glLineWidth(f2);
         setGlColorWithAlpha(n, f);
         drawWireframeBox(
            (double)f3,
            (double)f4,
            (double)f5,
            (double)f3 + block.getBlockBoundsMaxX(),
            (double)f4 + block.getBlockBoundsMaxY(),
            (double)f5 + block.getBlockBoundsMaxZ()
         );
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void emitQuadVertices2D(double d, double d2, double d3, double d4) {
      if (d < d3) {
         double d5 = d;
         d = d3;
         d3 = d5;
      }

      if (d2 < d4) {
         double d6 = d2;
         d2 = d4;
         d4 = d6;
      }

      GL11.glVertex2d(d, d4);
      GL11.glVertex2d(d3, d4);
      GL11.glVertex2d(d3, d2);
      GL11.glVertex2d(d, d2);
   }

   public static void drawRect(double x, double y, double x2, double y2, float f, float f2, float f3) {
      drawRect(x, y, x2, y2, f, f2, f3, 1.0F);
   }

   public static void drawRect(
      double x, double y, double x2, double y2, float f, float f2, float f3, float f4
   ) {
      if (x < x2) {
         double d5 = x;
         x = x2;
         x2 = d5;
      }

      if (y < y2) {
         double d6 = y;
         y = y2;
         y2 = d6;
      }

      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GL_STATE_SAVER.setAlpha(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
      GlStateManager.color(f, f2, f3, f4);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos(x, y2, 0.0).endVertex();
      worldRenderer.pos(x2, y2, 0.0).endVertex();
      worldRenderer.pos(x2, y, 0.0).endVertex();
      worldRenderer.pos(x, y, 0.0).endVertex();
      instance.draw();
      GL_STATE_SAVER.restoreTexture2D();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreAlpha();
   }

   public static void end3DRender() {
      GlStateManager.depthMask(true);
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      if (Config.isShaderPackLoaded()) {
         Shaders.enableTexture2D();
      }

      GlStateManager.enableDepth();
      GlStateManager.alphaFunc(516, 0.2F);
      GlStateManager.disableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glDisable(2848);
   }

   public static void resetGlColorState() {
      GlStateManager.disableAlpha();
      GlStateManager.enableLighting();
      GlStateManager.resetColor();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
   }

   public static void drawRectOutline(double d, double d2, double d3, double d4, float f, Color color) {
      drawRectOutline(
         d,
         d2,
         d3,
         d4,
         f,
         (float)color.getRed() / 255.0F,
         (float)color.getGreen() / 255.0F,
         (float)color.getBlue() / 255.0F,
         (float)color.getAlpha() / 255.0F
      );
   }

   public static void drawRect(double x, double y, double x2, double y2, int n, int n2, int n3, int n4) {
      drawRect(x, y, x2, y2, (float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F, (float)n4 / 255.0F);
   }

   public static void drawRoundedRectOutline(float f, float f2, float f3, float f4, float f5, int n, int n2, Color color) {
      drawRoundedRectOutline(f, f2, f3, f4, f5, n, n2, color.getRGB());
   }

   public static void drawRect(double x, double y, double x2, double y2, int n, int n2, int n3) {
      drawRect(x, y, x2, y2, (float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F, 1.0F);
   }

   public static void drawLine(double x1, double y1, double x2, double y2, float lineWidth, Color color) {
      drawLine(x1, y1, x2, y2, lineWidth, color.getRGB());
   }

   public static void drawRoundedRect(float f, float f2, float f3, float f4, float f5, int n, int n2) {
      drawRoundedRect(f, f2, f3, f4, f5, n, true, true, true, true, n2);
   }

   public static void renderItemIntoGui(ItemStack stack, int n, int n2, boolean flag) {
      if (stack != null) {
         GL_STATE_SAVER.setBlend(false);
         GL_STATE_SAVER.setDepth(true);
         if (flag) {
            mc.getRenderItem().renderItemIntoGUI(stack, n, n2);
            mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, stack, n, n2);
            GlStateManager.disableLighting();
         } else {
            mc.getRenderItem().renderItemIntoGUI(stack, n, n2);
         }

         GL_STATE_SAVER.restoreBlend();
         GL_STATE_SAVER.restoreDepth();
      }
   }

   public static void drawRect(int x, int y, int x2, int y2, float f, float f2, float f3) {
      drawRect(x, y, x2, y2, f, f2, f3, 1.0F);
   }

   public static void drawRoundedRect(
      float f, float f2, float f3, float f4, float f5, int n, boolean flag, boolean flag2, boolean flag3, boolean flag4, Color color
   ) {
      drawRoundedRect(f, f2, f3, f4, f5, n, flag, flag2, flag3, flag4, color.getRGB());
   }

   public static void drawTracerLine(EntityLivingBase entity, int n, String str) {
      float f = (float)(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
      float f2 = (float)(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY());
      float f3 = (float)(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ());
      setGlColor(n);
      drawTracerSegment(f, f2, f3, str);
   }

   public static void drawCornerBrackets(double d, double d2, double d3, double d4, float f, double d5, double d6, int n) {
      double d7 = Math.abs(d - d3) / 2.0 / (100.0 / d5);
      double d8 = Math.abs(d2 - d4) / 2.0 / (100.0 / d6);
      GL_STATE_SAVER.setBlend(true);
      GL_STATE_SAVER.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n);
      GL11.glLineWidth(f);
      GL11.glBegin(3);
      GL11.glVertex2d(d, d2 + d8);
      GL11.glVertex2d(d, d2);
      GL11.glVertex2d(d + d7, d2);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(d3, d2 + d8);
      GL11.glVertex2d(d3, d2);
      GL11.glVertex2d(d3 - d7, d2);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(d3, d4 - d8);
      GL11.glVertex2d(d3, d4);
      GL11.glVertex2d(d3 - d7, d4);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(d, d4 - d8);
      GL11.glVertex2d(d, d4);
      GL11.glVertex2d(d + d7, d4);
      GL11.glEnd();
      GL_STATE_SAVER.restoreBlend();
      GL_STATE_SAVER.restoreTexture2D();
   }

   public static void drawWireframeBox(float f, float f2, float f3, float f4, float f5, float f6) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).endVertex();
      worldRenderer.endVertex();
      instance.draw();
   }

   public static void drawRect(int x, int y, int x2, int y2, int n5, int n6, int n7, int n8) {
      drawRect(x, y, x2, y2, (float)n5 / 255.0F, (float)n6 / 255.0F, (float)n7 / 255.0F, (float)n8 / 255.0F);
   }

   public static void drawFilledBox(AxisAlignedBB box) {
      drawFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
   }

   public static void drawOutlinedTriangle(float f, float f2, float f3, int n) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      float f4 = f3 / 2.0F;
      float f5 = f + f4;
      float f6 = f - f4;
      float f7 = f2 + f4;
      float f8 = f2 - f4;
      worldRenderer.pos((double)f5, (double)f8, 0.0).endVertex();
      worldRenderer.pos((double)f6, (double)f8, 0.0).endVertex();
      worldRenderer.pos((double)f, (double)f7, 0.0).endVertex();
      worldRenderer.pos((double)f5, (double)f8, 0.0).endVertex();
      instance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static int adjustColorBrightness(int n, double d) {
      float[] floats = ColorUtil.toHsb(n);
      float f = (float)((double)floats[2] + d);
      if (f > 1.0F || f < 0.0F) {
         f = (float)((double)floats[2] - d);
      }

      if (f > 1.0F || f < 0.0F) {
         f = (float)((double)floats[2] + d);
      }

      if (f > 1.0F) {
         f = 1.0F;
      } else if (f < 0.0F) {
         f = 0.0F;
      }

      return Color.HSBtoRGB(floats[0], floats[1], f);
   }

   public static void setGlColorRgba(int n, int n2, int n3, int n4) {
      GlStateManager.color((float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F, (float)n4 / 255.0F);
   }

   public static void drawLine(int x1, int y1, int x2, int y2, float lineWidth, Color color) {
      drawLine(x1, y1, x2, y2, lineWidth, color.getRGB());
   }

   public static void scissorRectScaled(float f, float f2, float f3, float f4, double d) {
      int framebufferHeight = mc.getFramebuffer().framebufferHeight;
      float f5 = (float)d;
      GL11.glScissor((int)(f * f5), (int)(((float)framebufferHeight - f4) * f5), (int)((f3 - f) * f5), (int)((f4 - f2) * f5));
   }

   public static void drawRawRect(float x, float y, float x2, float y2) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)x2, (double)y, 0.0).endVertex();
      worldRenderer.pos((double)x, (double)y, 0.0).endVertex();
      worldRenderer.pos((double)x, (double)y2, 0.0).endVertex();
      worldRenderer.pos((double)x2, (double)y2, 0.0).endVertex();
      instance.draw();
   }

   public static void scissorRect(float x, float y, float x2, float y2) {
      ScaledResolution sr = new ScaledResolution(mc);
      float scaleFactor = (float)sr.getScaleFactor();
      GL11.glScissor(
         (int)(x * scaleFactor), (int)((sr.getScaledHeight_double() - (double)y2) * (double)scaleFactor), (int)((x2 - x) * scaleFactor), (int)((y2 - y) * scaleFactor)
      );
   }

   public static void drawFilledBox(double d, double d2, double d3, double d4, double d5, double d6) {
      drawFilledBox((float)d, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6);
   }

   public static void drawBatchedRect(int n, int n2, int n3, int n4, float f, float f2, float f3, float f4, boolean flag) {
      if (n < n3) {
         int n5 = n;
         n = n3;
         n3 = n5;
      }

      if (n2 < n4) {
         int n6 = n2;
         n2 = n4;
         n4 = n6;
      }

      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      if (!worldRenderer.isDrawing()) {
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
         BATCH_GL_STATE_SAVER.setBlend(true);
         BATCH_GL_STATE_SAVER.setTexture2D(false);
         BATCH_GL_STATE_SAVER.setAlpha(false);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
      }

      worldRenderer.pos((double)n, (double)n4, 0.0).color(f, f2, f3, f4).endVertex();
      worldRenderer.pos((double)n3, (double)n4, 0.0).color(f, f2, f3, f4).endVertex();
      worldRenderer.pos((double)n3, (double)n2, 0.0).color(f, f2, f3, f4).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).color(f, f2, f3, f4).endVertex();
      if (flag) {
         instance.draw();
         BATCH_GL_STATE_SAVER.restoreBlend();
         BATCH_GL_STATE_SAVER.restoreTexture2D();
         BATCH_GL_STATE_SAVER.restoreAlpha();
      }
   }

   public static void setGlColor(Color color) {
      GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
   }

   public static void drawRoundedRectOutline(float f, float f2, float f3, float f4, float f5, int n, int n2, int n3) {
      drawRoundedRectOutline(f, f2, f3, f4, f5, n, n2, true, true, true, true, n3);
   }

   private static void drawTracerSegment(float f, float f2, float f3, String str) {
      float f4 = str.equals("Feet") ? 0.0F : ClientManagers.moduleManager.tracers.cameraEyeHeight;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(1, DefaultVertexFormats.POSITION);
      worldRenderer.pos(0.0, (double)f4, 0.0).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
      worldRenderer.endVertex();
      instance.draw();
   }

   public static void drawRectOutline(double d, double d2, double d3, double d4, float f, float f2, float f3, float f4, float f5) {
      if (f5 != 0.0F) {
         GL_STATE_SAVER.setBlend(true);
         GL_STATE_SAVER.setTexture2D(false);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(f2, f3, f4, f5);
         GL11.glLineWidth(f);
         GL11.glBegin(3);
         GL11.glVertex2d(d, d2);
         GL11.glVertex2d(d3, d2);
         GL11.glVertex2d(d3, d4);
         GL11.glVertex2d(d, d4);
         GL11.glVertex2d(d, d2);
         GL11.glEnd();
         GL_STATE_SAVER.restoreBlend();
         GL_STATE_SAVER.restoreTexture2D();
      }
   }

   public static void drawRect(float x, float y, float x2, float y2, int n, int n2, int n3, int n4) {
      drawRect(x, y, x2, y2, (float)n / 255.0F, (float)n2 / 255.0F, (float)n3 / 255.0F, (float)n4 / 255.0F);
   }

   public static void addFilledBoxVertices(float f, float f2, float f3, float f4, float f5, float f6) {
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f6).endVertex();
      worldRenderer.pos((double)f4, (double)f5, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f3).endVertex();
      worldRenderer.pos((double)f4, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f2, (double)f3).endVertex();
   }

   public static void drawFilledBox(float f, float f2, float f3, float f4, float f5, float f6) {
      Tessellator instance = Tessellator.getInstance();
      instance.getWorldRenderer().begin(7, DefaultVertexFormats.POSITION);
      addFilledBoxVertices(f, f2, f3, f4, f5, f6);
      instance.draw();
   }

   public static void drawFilledTriangle(float f, float f2, float f3, int n) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setGlColor(n);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(4, DefaultVertexFormats.POSITION);
      float f4 = f3 / 2.0F;
      float f5 = f + f4;
      float f6 = f - f4;
      float f7 = f2 + f4;
      float f8 = f2 - f4;
      worldRenderer.pos((double)f5, (double)f8, 0.0).endVertex();
      worldRenderer.pos((double)f6, (double)f8, 0.0).endVertex();
      worldRenderer.pos((double)f, (double)f7, 0.0).endVertex();
      instance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawRotatedEntityBoxOutline(Entity entity, float f, float f2, float f3, double d, double d2) {
      float f4 = 0.017453292F;
      d *= 1.5;
      float f5 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 45.0F;
      float f6;
      if (f5 < 0.0F) {
         f6 = 0.0F;
         f6 += 360.0F - Math.abs(f5);
      } else {
         f6 = f5;
      }

      f6 *= -1.0F;
      f6 *= f4;
      float f7 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 135.0F;
      float f8;
      if (f7 < 0.0F) {
         f8 = 0.0F;
         f8 += 360.0F - Math.abs(f7);
      } else {
         f8 = f7;
      }

      f8 *= -1.0F;
      f8 *= f4;
      float f9 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 225.0F;
      float f10;
      if (f9 < 0.0F) {
         f10 = 0.0F;
         f10 += 360.0F - Math.abs(f9);
      } else {
         f10 = f9;
      }

      f10 *= -1.0F;
      f10 *= f4;
      float f11 = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()) + 315.0F;
      float f12;
      if (f11 < 0.0F) {
         f12 = 0.0F;
         f12 += 360.0F - Math.abs(f11);
      } else {
         f12 = f11;
      }

      f12 *= -1.0F;
      f12 *= f4;
      float f13 = (float)(Math.sin((double)f6) * d + (double)f);
      float f14 = (float)(Math.cos((double)f6) * d + (double)f3);
      float f15 = (float)(Math.sin((double)f8) * d + (double)f);
      float f16 = (float)(Math.cos((double)f8) * d + (double)f3);
      float f17 = (float)(Math.sin((double)f10) * d + (double)f);
      float f18 = (float)(Math.cos((double)f10) * d + (double)f3);
      float f19 = (float)(Math.sin((double)f12) * d + (double)f);
      float f20 = (float)(Math.cos((double)f12) * d + (double)f3);
      float f21 = (float)((double)f2 + d2);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.pos((double)f13, (double)f21, (double)f14).endVertex();
      worldRenderer.pos((double)f15, (double)f21, (double)f16).endVertex();
      worldRenderer.pos((double)f15, (double)f2, (double)f16).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f17, (double)f2, (double)f18).endVertex();
      worldRenderer.pos((double)f17, (double)f21, (double)f18).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.pos((double)f17, (double)f21, (double)f18).endVertex();
      worldRenderer.pos((double)f15, (double)f21, (double)f16).endVertex();
      worldRenderer.pos((double)f15, (double)f2, (double)f16).endVertex();
      worldRenderer.pos((double)f17, (double)f2, (double)f18).endVertex();
      worldRenderer.pos((double)f19, (double)f2, (double)f20).endVertex();
      worldRenderer.pos((double)f19, (double)f21, (double)f20).endVertex();
      worldRenderer.pos((double)f13, (double)f21, (double)f14).endVertex();
      worldRenderer.pos((double)f13, (double)f2, (double)f14).endVertex();
      worldRenderer.endVertex();
      instance.draw();
   }

   public static void renderItemIntoGuiElevated(ItemStack stack, int n, int n2, boolean flag) {
      if (stack != null) {
         mc.getRenderItem().zLevel += 50.0F;
         renderItemIntoGui(stack, n, n2, flag);
         mc.getRenderItem().zLevel -= 50.0F;
      }
   }

   private static String getBlendFactorName(int n) {
      switch (n) {
         case 0:
            return "GL_ZERO";
         case 1:
            return "GL_ONE";
         case 768:
            return "GL_SRC_COLOR";
         case 769:
            return "GL_ONE_MINUS_SRC_COLOR";
         case 770:
            return "GL_SRC_ALPHA";
         case 771:
            return "GL_ONE_MINUS_SRC_ALPHA";
         case 772:
            return "GL_DST_ALPHA";
         case 773:
            return "GL_ONE_MINUS_DST_ALPHA";
         case 774:
            return "GL_DST_COLOR";
         case 775:
            return "GL_ONE_MINUS_DST_COLOR";
         case 776:
            return "GL_SRC_ALPHA_SATURATE";
         default:
            return "UNKNOWN (" + n + ")";
      }
   }

   public static boolean isHovered(float x, float y, float width, float height, int mouseX, int mouseY) {
      return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
   }

   public static void setGlColor(int glColor) {
      GlStateManager.color(
         (float)(glColor >> 16 & 0xFF) / 255.0F, (float)(glColor >> 8 & 0xFF) / 255.0F, (float)(glColor & 0xFF) / 255.0F, (float)(glColor >> 24 & 0xFF) / 255.0F
      );
   }



}
