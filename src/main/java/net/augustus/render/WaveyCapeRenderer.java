package net.augustus.render;

import net.augustus.render.cape.CapeMovementMode;
import net.augustus.render.cape.CapePoseStack;
import net.augustus.render.cape.CapeStyle;
import net.augustus.render.cape.CapeVector3f;
import net.augustus.render.cape.CapeWindMode;
import net.augustus.render.cape.WaveyCapeConfig;
import net.augustus.render.cape.WaveyCapeSimulationManager;
import net.augustus.render.waveycape.CapeMatrix4f;
import net.augustus.render.waveycape.CapeQuaternion;
import net.augustus.render.waveycape.CapeVector4f;
import net.augustus.render.waveycape.StickPoint;
import net.augustus.render.waveycape.StickSimulation;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class WaveyCapeRenderer {
   private final RenderPlayer playerRenderer;
   private ModelRenderer[] capeSegments;
   private static final int PART_COUNT = 16;

   private void modifyPoseStackSimulation(CapePoseStack capePoseStack, AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      StickSimulation orCreateSimulation = WaveyCapeSimulationManager.getWaveyCapeSimulationManager().getOrCreateSimulation(clientPlayer);
      capePoseStack.pushPose();
      double d = 0.125;
      double d2 = 0.0;
      capePoseStack.translate(0.0, d2, d);
      float f2 = ((StickPoint)orCreateSimulation.points.get(n)).getLerpX(f) - ((StickPoint)orCreateSimulation.points.get(0)).getLerpX(f);
      if (f2 > 0.0F) {
         f2 = 0.0F;
      }

      float f4 = (((StickPoint)orCreateSimulation.points.get(0)).getLerpY(f) - (float)n) - ((StickPoint)orCreateSimulation.points.get(n)).getLerpY(f);
      float f5 = 0.0F;
      float f6 = (float)(-Math.atan2((double)f4, (double)f2));
      f6 = Math.max(f6, 0.0F);
      if (f6 != 0.0F) {
         f6 = (float)(3.141592653589793 - (double)f6);
      }

      f6 = (float)((double)f6 * 57.2958);
      f6 *= 2.0F;
      float f7 = 0.0F;
      if (clientPlayer.isSneaking()) {
         f7 += 25.0F;
         double d4 = 0.0;
         double d5 = 0.15000000596046448;
         capePoseStack.translate(0.0, d5, d4);
      }

      float windOffset = this.getWindOffset(n, waveyCapeConfig);
      float f8 = 6.0F + f7 + windOffset;
      capePoseStack.mulPose(CapeVector3f.XP.rotationDegrees(f8));
      float f9 = f5 / 2.0F;
      capePoseStack.mulPose(CapeVector3f.ZP.rotationDegrees(f9));
      float f10 = 180.0F - f5 / 2.0F;
      capePoseStack.mulPose(CapeVector3f.YP.rotationDegrees(f10));
      double d7 = (double)(f4 / 16.0F);
      double d8 = (double)(f2 / 16.0F);
      double d9 = d7;
      capePoseStack.translate(0.0, d9, d8);
      double d11 = -0.03;
      double d12 = 0.03;
      capePoseStack.translate(0.0, d12, d11);
      d7 = (double)((float)n * 1.0F / 16.0F);
      double d14 = (double)(n * 0 / 16);
      double d15 = d7;
      capePoseStack.translate(0.0, d15, d14);
      float f11 = -f6;
      capePoseStack.mulPose(CapeVector3f.XP.rotationDegrees(f11));
      d7 = (double)((float)(-n) * 1.0F / 16.0F);
      double d17 = (double)(-n * 0 / 16);
      double d18 = d7;
      capePoseStack.translate(0.0, d18, d17);
      double d20 = 0.03;
      double d21 = -0.03;
      capePoseStack.translate(0.0, d21, d20);
   }

   private void modifyPoseVanillaGl(AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      GlStateManager.translate(0.0, 0.0, 0.125);
      double d = lerp((double)f, clientPlayer.prevChasingPosX, clientPlayer.chasingPosX) - lerp((double)f, clientPlayer.prevPosX, clientPlayer.posX);
      double d2 = lerp((double)f, clientPlayer.prevChasingPosY, clientPlayer.chasingPosY) - lerp((double)f, clientPlayer.prevPosY, clientPlayer.posY);
      double d3 = lerp((double)f, clientPlayer.prevChasingPosZ, clientPlayer.chasingPosZ) - lerp((double)f, clientPlayer.prevPosZ, clientPlayer.posZ);
      float f2 = clientPlayer.prevRenderYawOffset + clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset;
      double d4 = Math.sin((double)(f2 * 0.017453292F));
      double d5 = -Math.cos((double)(f2 * 0.017453292F));
      float f3 = (float)d2 * 10.0F;
      f3 = MathHelper.clamp_float(f3, -6.0F, 32.0F);
      float f4 = (float)(d * d4 + d3 * d5) * easeOutSine(0.0625F * (float)n) * 100.0F;
      f4 = MathHelper.clamp_float(f4, 0.0F, 150.0F * easeOutSine(0.0625F * (float)n));
      float f5 = (float)(d * d5 - d3 * d4) * 100.0F;
      f5 = MathHelper.clamp_float(f5, -20.0F, 20.0F);
      float f6 = (float)lerp((double)f, (double)clientPlayer.prevCameraYaw, (double)clientPlayer.cameraYaw);
      f3 = (float)((double)f3 + Math.sin(lerp((double)f, (double)clientPlayer.prevDistanceWalkedModified, (double)clientPlayer.distanceWalkedModified) * 6.0) * 32.0 * (double)f6);
      if (clientPlayer.isSneaking()) {
         f3 += 25.0F;
         GlStateManager.translate(0.0F, 0.15F, 0.0F);
      }

      float windOffset = this.getWindOffset(n, waveyCapeConfig);
      GlStateManager.rotate(6.0F + f4 / 2.0F + f3 + windOffset, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f5 / 2.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-f5 / 2.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
   }

   private static double lerp(double start, double end, double progress) {
      return end + start * (progress - end);
   }

   private static void addBackVertex(
      WorldRenderer worldRenderer, CapeMatrix4f capeMatrix4f, CapeMatrix4f capeMatrix4f2, float f, float f2, float f3, float f4, float f5, float f6, int n
   ) {
      if (f < f4) {
         float f7 = f;
         f = f4;
         f4 = f7;
      }

      if (f2 < f5) {
         float f8 = f2;
         f2 = f5;
         f5 = f8;
         CapeMatrix4f capeMatrix4f3 = capeMatrix4f;
         capeMatrix4f = capeMatrix4f2;
         capeMatrix4f2 = capeMatrix4f3;
      }

      float f9 = 0.015625F;
      float f10 = 0.171875F;
      float f11 = 0.03125F;
      float f12 = 0.53125F;
      float f14 = (f12 - f11) / 16.0F;
      f12 = f11 + f14 * (float)(n + 1);
      f11 += f14 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f2, f, f5, f3).tex((double)f10, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f3).tex((double)f9, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f6).tex((double)f9, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f, f2, f6).tex((double)f10, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
   }

   private static void addRightVertex(
      WorldRenderer worldRenderer,
      CapeMatrix4f capeMatrix4f,
      CapeMatrix4f capeMatrix4f2,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6,
      int n
   ) {
      if (f < f4) {
         f4 = f;
      }

      if (f2 < f5) {
         float f7 = f2;
         f2 = f5;
         f5 = f7;
      }

      float f8 = 0.171875F;
      float f9 = 0.1875F;
      float f10 = 0.03125F;
      float f11 = 0.53125F;
      float f13 = (f11 - f10) / 16.0F;
      f11 = f10 + f13 * (float)(n + 1);
      f10 += f13 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f6).tex((double)f8, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f3).tex((double)f9, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f3).tex((double)f9, (double)f10).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f6).tex((double)f8, (double)f10).normal(1.0F, 0.0F, 0.0F).endVertex();
   }

   private static void addTopVertex(
      WorldRenderer worldRenderer, CapeMatrix4f capeMatrix4f, CapeMatrix4f capeMatrix4f2, float f, float f2, float f3, float f4, float f5, float f6, int n
   ) {
      if (f < f4) {
         float f7 = f;
         f = f4;
         f4 = f7;
      }

      if (f2 < f5) {
         float f8 = f2;
         f2 = f5;
         f5 = f8;
      }

      float f9 = 0.015625F;
      float f10 = 0.171875F;
      float f11 = 0.0F;
      float f12 = 0.03125F;
      float f14 = (f12 - f11) / 16.0F;
      f12 = f11 + f14 * (float)(n + 1);
      f11 += f14 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f2, f, f5, f3).tex((double)f10, (double)f12).normal(0.0F, 1.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f3).tex((double)f9, (double)f12).normal(0.0F, 1.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f6).tex((double)f9, (double)f11).normal(0.0F, 1.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f, f2, f6).tex((double)f10, (double)f11).normal(0.0F, 1.0F, 0.0F).endVertex();
   }

   private static void addBottomVertex(
      WorldRenderer worldRenderer, CapeMatrix4f capeMatrix4f, CapeMatrix4f capeMatrix4f2, float f, float f2, float f3, float f4, float f5, float f6, int n
   ) {
      if (f < f4) {
         float f7 = f;
         f = f4;
         f4 = f7;
      }

      if (f2 < f5) {
         float f8 = f2;
         f2 = f5;
         f5 = f8;
      }

      float f9 = 0.171875F;
      float f10 = 0.328125F;
      float f11 = 0.0F;
      float f12 = 0.03125F;
      float f14 = (f12 - f11) / 16.0F;
      f12 = f11 + f14 * (float)(n + 1);
      f11 += f14 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f2, f, f5, f6).tex((double)f10, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f6).tex((double)f9, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f3).tex((double)f9, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f, f2, f3).tex((double)f10, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
   }

   private static float easeOutSine(float f) {
      return (float)Math.sin((double)f * 3.141592653589793 / 2.0);
   }

   public void renderCape(AbstractClientPlayer clientPlayer, float f, WaveyCapeConfig waveyCapeConfig) {
      WaveyCapeSimulationManager manager = WaveyCapeSimulationManager.getWaveyCapeSimulationManager();
      if (waveyCapeConfig.capeMovement == CapeMovementMode.BASIC_SIMULATION) {
         manager.updateSimulation(clientPlayer, (byte)16);
      }

      if (waveyCapeConfig.capeStyle == CapeStyle.SMOOTH) {
         this.renderSmoothCape(clientPlayer, f, waveyCapeConfig);
      } else {
         this.renderBlockyCape(clientPlayer, f, waveyCapeConfig);
      }
   }

   private void modifyPoseStack(CapePoseStack capePoseStack, AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      if (waveyCapeConfig.capeMovement == CapeMovementMode.BASIC_SIMULATION) {
         this.modifyPoseStackSimulation(capePoseStack, clientPlayer, f, n, waveyCapeConfig);
      } else {
         this.modifyPoseStackVanilla(capePoseStack, clientPlayer, f, n, waveyCapeConfig);
      }
   }

   private void initCapeSegments(ModelBase modelBase) {
      this.capeSegments = new ModelRenderer[16];

      for (int i = 0; i < 16; i++) {
         ModelRenderer modelRenderer = new ModelRenderer(modelBase, 0, i);
         modelRenderer.setTextureSize(64, 32);
         modelRenderer.setRotationPoint(0.0F, 0.0F, 0.0F);
         this.capeSegments[i] = modelRenderer.addBox(-5.0F, (float)i, -1.0F, 10, 1, 1);
      }
   }

   private void modifyPoseSimulationGl(AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      StickSimulation orCreateSimulation = WaveyCapeSimulationManager.getWaveyCapeSimulationManager().getOrCreateSimulation(clientPlayer);
      GlStateManager.translate(0.0, 0.0, 0.125);
      float f2 = ((StickPoint)orCreateSimulation.points.get(n)).getLerpX(f) - ((StickPoint)orCreateSimulation.points.get(0)).getLerpX(f);
      if (f2 > 0.0F) {
         f2 = 0.0F;
      }

      float f4 = (((StickPoint)orCreateSimulation.points.get(0)).getLerpY(f) - (float)n) - ((StickPoint)orCreateSimulation.points.get(n)).getLerpY(f);
      float f5 = 0.0F;
      if (clientPlayer.isSneaking()) {
         f5 += 25.0F;
         GlStateManager.translate(0.0F, 0.15F, 0.0F);
      }

      float windOffset = this.getWindOffset(n, waveyCapeConfig);
      float f6 = 0.0F;
      GlStateManager.rotate(6.0F + f5 + windOffset, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f6 / 2.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-f6 / 2.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, f4 / 16.0F, f2 / 16.0F);
      GlStateManager.translate(0.0, 0.03, -0.03);
      GlStateManager.translate(0.0F, (float)n * 1.0F / 16.0F, (float)(n * 0 / 16));
      GlStateManager.translate(0.0F, (float)(-n) * 1.0F / 16.0F, (float)(-n * 0 / 16));
      GlStateManager.translate(0.0, -0.03, 0.03);
   }

   private void renderSmoothCape(AbstractClientPlayer clientPlayer, float f, WaveyCapeConfig waveyCapeConfig) {
      GL11.glShadeModel(7425);
      GlStateManager.disableCull();
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);
      CapePoseStack capePoseStack = new CapePoseStack();
      capePoseStack.pushPose();
      CapeMatrix4f capeMatrix4f = null;

      for (int i = 0; i < 16; i++) {
         this.modifyPoseStack(capePoseStack, clientPlayer, f, i, waveyCapeConfig);
         if (capeMatrix4f == null) {
            capeMatrix4f = capePoseStack.last().getPose();
         }

         if (i == 0) {
            CapeMatrix4f pose = capePoseStack.last().getPose();
            float f2 = -0.06F;
            float f3 = 0.0F;
            float f4 = -0.3F;
            float f5 = 0.0F;
            float f6 = 0.0F;
            addTopVertex(worldRenderer, pose, capeMatrix4f, 0.3F, f6, f5, f4, f3, f2, i);
         }

         if (i == 15) {
            CapeMatrix4f pose2 = capePoseStack.last().getPose();
            CapeMatrix4f pose3 = capePoseStack.last().getPose();
            float f8 = (float)(i + 1) * 0.06F;
            float f9 = (float)(i + 1) * 0.06F;
            float f10 = -0.06F;
            float f12 = -0.3F;
            float f13 = 0.0F;
            addBottomVertex(worldRenderer, pose2, pose3, 0.3F, f8, f13, f12, f9, f10, i);
         }

         CapeMatrix4f pose4 = capePoseStack.last().getPose();
         float f16 = (float)(i + 1) * 0.06F;
         float f17 = (float)i * 0.06F;
         float f18 = -0.06F;
         float f19 = f17;
         float f20 = -0.3F;
         float f21 = 0.0F;
         float f22 = f16;
         addLeftVertex(worldRenderer, pose4, capeMatrix4f, -0.3F, f22, f21, f20, f19, f18, i);
         CapeMatrix4f pose5 = capePoseStack.last().getPose();
         f16 = (float)(i + 1) * 0.06F;
         float f24 = (float)i * 0.06F;
         float f25 = -0.06F;
         float f27 = 0.3F;
         float f28 = 0.0F;
         float f29 = f16;
         addRightVertex(worldRenderer, pose5, capeMatrix4f, 0.3F, f29, f28, f27, f24, f25, i);
         CapeMatrix4f pose6 = capePoseStack.last().getPose();
         f16 = (float)(i + 1) * 0.06F;
         f17 = (float)i * 0.06F;
         float f31 = -0.06F;
         float f32 = f17;
         float f33 = -0.3F;
         float f34 = -0.06F;
         float f35 = f16;
         addBackVertex(worldRenderer, pose6, capeMatrix4f, 0.3F, f35, f34, f33, f32, f31, i);
         CapeMatrix4f pose7 = capePoseStack.last().getPose();
         float f37 = (float)(i + 1) * 0.06F;
         float f38 = (float)i * 0.06F;
         float f39 = 0.0F;
         float f41 = -0.3F;
         float f42 = 0.0F;
         addFrontVertex(worldRenderer, capeMatrix4f, pose7, 0.3F, f37, f42, f41, f38, f39, i);
         capeMatrix4f = capePoseStack.last().getPose();
         capePoseStack.popPose();
      }

      Tessellator.getInstance().draw();
      GlStateManager.enableCull();
   }

   private static void addFrontVertex(
      WorldRenderer worldRenderer,
      CapeMatrix4f capeMatrix4f,
      CapeMatrix4f capeMatrix4f2,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6,
      int n
   ) {
      if (f < f4) {
         float f7 = f;
         f = f4;
         f4 = f7;
      }

      if (f2 < f5) {
         float f8 = f2;
         f2 = f5;
         f5 = f8;
         CapeMatrix4f capeMatrix4f3 = capeMatrix4f;
         capeMatrix4f = capeMatrix4f2;
         capeMatrix4f2 = capeMatrix4f3;
      }

      float f9 = 0.1875F;
      float f10 = 0.34375F;
      float f11 = 0.03125F;
      float f12 = 0.53125F;
      float f14 = (f12 - f11) / 16.0F;
      f12 = f11 + f14 * (float)(n + 1);
      f11 += f14 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f2, f, f2, f3).tex((double)f10, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f2, f3).tex((double)f9, (double)f12).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f5, f6).tex((double)f9, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f, f5, f6).tex((double)f10, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
   }

   private void modifyPoseGl(AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      if (waveyCapeConfig.capeMovement == CapeMovementMode.BASIC_SIMULATION) {
         this.modifyPoseSimulationGl(clientPlayer, f, n, waveyCapeConfig);
      } else {
         this.modifyPoseVanillaGl(clientPlayer, f, n, waveyCapeConfig);
      }
   }

   private static WorldRenderer emitVertex(WorldRenderer worldRenderer, CapeMatrix4f capeMatrix4f, float f, float f2, float f3) {
      CapeVector4f capeVector4f = new CapeVector4f(f, f2, f3, 1.0F);
      capeVector4f.transform(capeMatrix4f);
      return worldRenderer.pos((double)capeVector4f.getX(), (double)capeVector4f.getY(), (double)capeVector4f.getZ());
   }

   private static void addLeftVertex(
      WorldRenderer worldRenderer, CapeMatrix4f capeMatrix4f, CapeMatrix4f capeMatrix4f2, float f, float f2, float f3, float f4, float f5, float f6, int n
   ) {
      if (f < f4) {
         f4 = f;
      }

      if (f2 < f5) {
         float f7 = f2;
         f2 = f5;
         f5 = f7;
      }

      float f8 = 0.0F;
      float f9 = 0.015625F;
      float f10 = 0.03125F;
      float f11 = 0.53125F;
      float f13 = (f11 - f10) / 16.0F;
      f11 = f10 + f13 * (float)(n + 1);
      f10 += f13 * (float)n;
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f3).tex((double)f9, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f, f4, f2, f6).tex((double)f8, (double)f11).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f6).tex((double)f8, (double)f10).normal(1.0F, 0.0F, 0.0F).endVertex();
      emitVertex(worldRenderer, capeMatrix4f2, f4, f5, f3).tex((double)f9, (double)f10).normal(1.0F, 0.0F, 0.0F).endVertex();
   }

   public WaveyCapeRenderer(RenderPlayer renderPlayer, ModelBase modelBase) {
       super();
      this.capeSegments = new ModelRenderer[16];
      this.playerRenderer = renderPlayer;
      this.initCapeSegments(modelBase);
   }

   private void modifyPoseStackVanilla(CapePoseStack capePoseStack, AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      capePoseStack.pushPose();
      double d = 0.125;
      double d2 = 0.0;
      capePoseStack.translate(0.0, d2, d);
      double d4 = lerp((double)f, clientPlayer.prevChasingPosX, clientPlayer.chasingPosX) - lerp((double)f, clientPlayer.prevPosX, clientPlayer.posX);
      double d5 = lerp((double)f, clientPlayer.prevChasingPosY, clientPlayer.chasingPosY) - lerp((double)f, clientPlayer.prevPosY, clientPlayer.posY);
      double d6 = lerp((double)f, clientPlayer.prevChasingPosZ, clientPlayer.chasingPosZ) - lerp((double)f, clientPlayer.prevPosZ, clientPlayer.posZ);
      float f2 = clientPlayer.prevRenderYawOffset + clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset;
      double d7 = Math.sin((double)(f2 * 0.017453292F));
      double d8 = -Math.cos((double)(f2 * 0.017453292F));
      float f3 = (float)d5 * 10.0F;
      f3 = MathHelper.clamp_float(f3, -6.0F, 32.0F);
      float f4 = (float)(d4 * d7 + d6 * d8) * easeOutSine(0.0625F * (float)n) * 100.0F;
      f4 = MathHelper.clamp_float(f4, 0.0F, 150.0F * easeOutSine(0.0625F * (float)n));
      float f5 = (float)(d4 * d8 - d6 * d7) * 100.0F;
      f5 = MathHelper.clamp_float(f5, -20.0F, 20.0F);
      float f6 = (float)lerp((double)f, (double)clientPlayer.prevCameraYaw, (double)clientPlayer.cameraYaw);
      f3 = (float)((double)f3 + Math.sin(lerp((double)f, (double)clientPlayer.prevDistanceWalkedModified, (double)clientPlayer.distanceWalkedModified) * 6.0) * 32.0 * (double)f6);
      if (clientPlayer.isSneaking()) {
         f3 += 25.0F;
         double d9 = 0.0;
         double d10 = 0.15000000596046448;
         capePoseStack.translate(0.0, d10, d9);
      }

      float windOffset = this.getWindOffset(n, waveyCapeConfig);
      float f7 = 6.0F + f4 / 2.0F + f3 + windOffset;
      capePoseStack.mulPose(CapeVector3f.XP.rotationDegrees(f7));
      float f8 = f5 / 2.0F;
      capePoseStack.mulPose(CapeVector3f.ZP.rotationDegrees(f8));
      float f9 = 180.0F - f5 / 2.0F;
      capePoseStack.mulPose(CapeVector3f.YP.rotationDegrees(f9));
   }

   private float getWindOffset(int n, WaveyCapeConfig waveyCapeConfig) {
      if (waveyCapeConfig.windMode == CapeWindMode.WAVES) {
         long l = System.currentTimeMillis() / 3L % 360L;
         return (float)(Math.sin(Math.toRadians((double)(((float)(n + 1) / 16.0F) * 360.0F - (float)l))) * 3.0);
      } else {
         return 0.0F;
      }
   }

   private void modifyPoseSimulationRotatedGl(AbstractClientPlayer clientPlayer, float f, int n, WaveyCapeConfig waveyCapeConfig) {
      StickSimulation orCreateSimulation = WaveyCapeSimulationManager.getWaveyCapeSimulationManager().getOrCreateSimulation(clientPlayer);
      GlStateManager.translate(0.0, 0.0, 0.125);
      float f2 = ((StickPoint)orCreateSimulation.points.get(n)).getLerpX(f) - ((StickPoint)orCreateSimulation.points.get(0)).getLerpX(f);
      if (f2 > 0.0F) {
         f2 = 0.0F;
      }

      float f4 = (((StickPoint)orCreateSimulation.points.get(0)).getLerpY(f) - (float)n) - ((StickPoint)orCreateSimulation.points.get(n)).getLerpY(f);
      float f5 = (float)(-Math.atan2((double)f4, (double)f2));
      f5 = Math.max(f5, 0.0F);
      if (f5 != 0.0F) {
         f5 = (float)(3.141592653589793 - (double)f5);
      }

      f5 = (float)((double)f5 * 57.2958);
      f5 *= 2.0F;
      float f6 = 0.0F;
      if (clientPlayer.isSneaking()) {
         f6 += 25.0F;
         GlStateManager.translate(0.0F, 0.15F, 0.0F);
      }

      float windOffset = this.getWindOffset(n, waveyCapeConfig);
      float f7 = 0.0F;
      GlStateManager.rotate(6.0F + f6 + windOffset, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f7 / 2.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-f7 / 2.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, f4 / 16.0F, f2 / 16.0F);
      GlStateManager.translate(0.0, 0.03, -0.03);
      GlStateManager.translate(0.0F, (float)n * 1.0F / 16.0F, (float)(n * 0 / 16));
      GlStateManager.rotate(-f5, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0F, (float)(-n) * 1.0F / 16.0F, (float)(-n * 0 / 16));
      GlStateManager.translate(0.0, -0.03, 0.03);
   }

   private void renderBlockyCape(AbstractClientPlayer clientPlayer, float f, WaveyCapeConfig waveyCapeConfig) {

      for (int i = 0; i < 16; i++) {
         ModelRenderer modelRenderer = this.capeSegments[i];
         GlStateManager.pushMatrix();
         this.modifyPoseGl(clientPlayer, f, i, waveyCapeConfig);
         modelRenderer.render(0.0625F);
         GlStateManager.popMatrix();
      }
   }
}
