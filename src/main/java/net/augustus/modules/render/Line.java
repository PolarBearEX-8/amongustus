package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Line extends Module implements RenderListener, TickListener, PacketListener {
   private final SettingGroup moveLine;
   public final BooleanValue activateMoveLine;
   public final ColorSetting moveLineColor;
   public final DoubleValue lineWidth;
   public final DoubleValue lineTime;
   public final BooleanValue resetOnTeleportMoveLine;
   private final SettingGroup auraLine;
   public final BooleanValue activateAuraLine;
   public final BooleanValue displayLegitimateRotations;
   public final BooleanValue displayRotationsAtCurrentPosition;
   public final ColorSetting color2;
   public final DoubleValue auraLineWidth;
   public final DoubleValue auraLineTime;
   public final BooleanValue resetOnTeleportAuraLine;
   public BlurShadowSettings blurShadowSettings;
   private java.util.ArrayList moveLinePoints;
   public java.util.ArrayList rotationLineTimestamps;
   public java.util.ArrayList tickRotationTimestamps;
   public java.util.ArrayList rotationLineAngles;
   public java.util.ArrayList tickRotationAngles;
   public java.util.ArrayList rotationLineEyePositions;
   public java.util.ArrayList tickRotationEyePositions;
   private int lastDimensionId;
   private double[] lastExpiredMovePoint;

   public Line() {
       super("Line", "Linie", new Color(10, 20, 15), Categorys.RENDER);
      this.moveLine = new SettingGroup(this, "MoveLine", true);
      this.activateMoveLine = (BooleanValue)new BooleanValue(this, "ActivateMoveLine", true, this.moveLine).withDisplayName("Activate");
      Color color3 = new Color(77, 0, 255, 255);
      SettingGroup moveLine2 = this.moveLine;
      Color color4 = color3;
      String str2 = "Color";
      ColorSetting colorSetting = new ColorSetting(this, str2, color4, moveLine2);
      BooleanValue activateMoveLine2 = this.activateMoveLine;
      BooleanValue booleanValue2 = activateMoveLine2;
      this.moveLineColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue2.getValue());
      DoubleValue lineWidthSetting = new DoubleValue(this, "LineWidth", 2.0, 0.1, 4.0, 2, this.moveLine);
      activateMoveLine2 = this.activateMoveLine;
      BooleanValue booleanValue3 = activateMoveLine2;
      this.lineWidth = lineWidthSetting.visibleWhen(() -> booleanValue3.getValue());
      DoubleValue lineTimeSetting = new DoubleValue(this, "LineTime", 3000.0, 500.0, 20000.0, 0, this.moveLine);
      activateMoveLine2 = this.activateMoveLine;
      BooleanValue booleanValue4 = activateMoveLine2;
      this.lineTime = lineTimeSetting.visibleWhen(() -> booleanValue4.getValue());
      BooleanValue booleanValue6 = (BooleanValue)new BooleanValue(this, "ResetOnTeleportMoveLine", false, this.moveLine).withDisplayName("ResetOnTeleport");
      activateMoveLine2 = this.activateMoveLine;
      BooleanValue booleanValue7 = activateMoveLine2;
      this.resetOnTeleportMoveLine = (BooleanValue)booleanValue6.withVisibility(() -> booleanValue7.getValue());
      this.auraLine = new SettingGroup(this, "AuraLine", true);
      this.activateAuraLine = (BooleanValue)new BooleanValue(this, "ActivateAuraLine", false, this.auraLine).withDisplayName("Activate");
      BooleanValue booleanValue9 = new BooleanValue(this, "Display legitimate rotations", false, this.auraLine);
      activateMoveLine2 = this.activateAuraLine;
      BooleanValue booleanValue10 = activateMoveLine2;
      this.displayLegitimateRotations = (BooleanValue)booleanValue9.withVisibility(() -> booleanValue10.getValue());
      this.displayRotationsAtCurrentPosition = (BooleanValue)new BooleanValue(this, "Display rotations at current position", false, this.auraLine).withVisibility(() -> this.isDisplayAtCurrentPosVisible());
      color3 = new Color(77, 0, 255, 255);
      moveLine2 = this.auraLine;
      color4 = color3;
      str2 = "AuraColor";
      ColorSetting colorSetting3 = (ColorSetting)new ColorSetting(this, str2, color4, moveLine2).withDisplayName("Color");
      activateMoveLine2 = this.activateAuraLine;
      BooleanValue booleanValue12 = activateMoveLine2;
      this.color2 = (ColorSetting)colorSetting3.withVisibility(() -> booleanValue12.getValue());
      DoubleValue auraLineWidthSetting = new DoubleValue(this, "AuraLineWidth", 2.0, 0.1, 4.0, 2, this.auraLine).named("LineWidth");
      activateMoveLine2 = this.activateAuraLine;
      BooleanValue booleanValue13 = activateMoveLine2;
      this.auraLineWidth = auraLineWidthSetting.visibleWhen(() -> booleanValue13.getValue());
      DoubleValue auraLineTimeSetting = new DoubleValue(this, "AuraLineTime", 3000.0, 500.0, 20000.0, 0, this.auraLine).named("LineTime");
      activateMoveLine2 = this.activateAuraLine;
      BooleanValue booleanValue14 = activateMoveLine2;
      this.auraLineTime = auraLineTimeSetting.visibleWhen(() -> booleanValue14.getValue());
      BooleanValue booleanValue16 = (BooleanValue)new BooleanValue(this, "ResetOnTeleportAuraLine", false, this.auraLine).withDisplayName("ResetOnTeleport");
      activateMoveLine2 = this.activateAuraLine;
      BooleanValue booleanValue17 = activateMoveLine2;
      this.resetOnTeleportAuraLine = (BooleanValue)booleanValue16.withVisibility(() -> booleanValue17.getValue());
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black).disableBothByDefault();
      this.moveLinePoints = new java.util.ArrayList();
      this.rotationLineTimestamps = new java.util.ArrayList();
      this.tickRotationTimestamps = new java.util.ArrayList();
      this.rotationLineAngles = new java.util.ArrayList();
      this.tickRotationAngles = new java.util.ArrayList();
      this.rotationLineEyePositions = new java.util.ArrayList();
      this.tickRotationEyePositions = new java.util.ArrayList();
      this.lastDimensionId = -100;
   }

   @Override
   public void onEnable() {
      this.moveLinePoints = new java.util.ArrayList();
   }

   @Override
   public void onDisable() {
      this.moveLinePoints.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         S07PacketRespawn packet2 = (S07PacketRespawn)packet;
         if (packet2.getDimensionID() != this.lastDimensionId) {
            this.lastDimensionId = packet2.getDimensionID();
            this.clearAllLines();
         }
      }

      if (packet instanceof S08PacketPlayerPosLook) {
         if (this.resetOnTeleportMoveLine.getValue()) {
            this.moveLinePoints.clear();
         }

         if (this.resetOnTeleportAuraLine.getValue()) {
            this.clearAllLines();
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.activateMoveLine.getValue()) {
         RenderUtil.begin3DRender();
         GL11.glLineWidth((float)this.lineWidth.getValue());
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderMoveLinePass(partialTicks, shaderRenderPass));
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.end3DRender();
         GL11.glLineWidth(1.0F);
      }

      if (this.activateAuraLine.getValue() && this.displayLegitimateRotations.getValue()) {
         RenderUtil.begin3DRender();
         RenderUtil.setGlColor(this.moveLineColor.getColorInt((byte)0));
         GL11.glLineWidth((float)this.lineWidth.getValue());
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         if (!this.hasKillAuraTarget()) {
            this.rotationLineTimestamps.add(System.currentTimeMillis());
            this.rotationLineAngles.add(new float[]{mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch});
            this.rotationLineEyePositions.add(mc.thePlayer.getPositionEyes(partialTicks));

            while (!this.rotationLineTimestamps.isEmpty() && (double)(System.currentTimeMillis() - (Long)this.rotationLineTimestamps.get(0)) > this.auraLineTime.getValue()) {
               this.rotationLineTimestamps.remove(0);
               this.rotationLineAngles.remove(0);
               this.rotationLineEyePositions.remove(0);
            }
         }

         this.renderAuraRotationLine();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.end3DRender();
         GL11.glLineWidth(1.0F);
      }
   }

   private void renderAuraRotationLine() {
      GlStateManager.pushMatrix();
      Vec3 vec = new Vec3(mc.getRenderManager().getRenderPosX(), mc.getRenderManager().getRenderPosY(), mc.getRenderManager().getRenderPosZ());
      GlStateManager.disableAlpha();
      GL11.glBegin(3);
      GlStateManager.color(this.color2.getRedFloat(), this.color2.getGreenFloat(), this.color2.getBlueFloat(), 1.0F);
      Vec3 vec2 = vec.addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);

      for (int i = 0; i < this.rotationLineEyePositions.size(); i++) {
         float[] floats = (float[])this.rotationLineAngles.get(i);
         float[] floats2 = (float[])this.rotationLineAngles.get(Math.max(i - 1, 0));
         long l = (Long)this.rotationLineTimestamps.get(Math.max(i - 1, 0));
         long l2 = (Long)this.rotationLineTimestamps.get(i);
         double d = (double)(l2 - l);
         if (i > 0) {
            this.color2.getColorInt((byte)0);
         }

         float f = MathHelper.cos(-floats[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f2 = MathHelper.sin(-floats[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f3 = -MathHelper.cos(-floats[1] * (float) (Math.PI / 180.0));
         float f4 = MathHelper.sin(-floats[1] * (float) (Math.PI / 180.0));
         Vec3 vec3 = new Vec3(this.displayRotationsAtCurrentPosition.getValue() ? vec2 : (Vec3)this.rotationLineEyePositions.get(i));
         vec3.xCoord = vec3.xCoord + ((double)(f2 * f3) - vec.xCoord);
         vec3.yCoord = vec3.yCoord + ((double)f4 - vec.yCoord);
         vec3.zCoord = vec3.zCoord + ((double)(f * f3) - vec.zCoord);
         GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
      }

      GL11.glEnd();
      RenderUtil.setGlColorRgba(255, 0, 0, 255);
      double d2 = 0.002;

      for (int j = 0; j < this.tickRotationAngles.size(); j++) {
         float[] floats3 = (float[])this.tickRotationAngles.get(j);
         float f5 = MathHelper.cos(-floats3[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f6 = MathHelper.sin(-floats3[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f7 = -MathHelper.cos(-floats3[1] * (float) (Math.PI / 180.0));
         float f8 = MathHelper.sin(-floats3[1] * (float) (Math.PI / 180.0));
         Vec3 vec4 = new Vec3(this.displayRotationsAtCurrentPosition.getValue() ? vec2 : (Vec3)this.tickRotationEyePositions.get(j));
         vec4.xCoord = vec4.xCoord + ((double)(f6 * f7) - vec.xCoord);
         vec4.yCoord = vec4.yCoord + ((double)f8 - vec.yCoord);
         vec4.zCoord = vec4.zCoord + ((double)(f5 * f7) - vec.zCoord);
         RenderUtil.drawFilledBox(
            vec4.xCoord - 0.002,
            vec4.yCoord - 0.002,
            vec4.zCoord - 0.002,
            vec4.xCoord + 0.002,
            vec4.yCoord + 0.002,
            vec4.zCoord + 0.002
         );
      }

      GlStateManager.enableAlpha();
      RenderUtil.setGlColorRgba(255, 255, 255, 255);
      GlStateManager.popMatrix();
   }

   private boolean hasKillAuraTarget() {
      return ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null;
   }

   private boolean shouldSkipNoiseAuraSampling() {
      if (ClientManagers.moduleManager.killAura.randomizeMode.getSelectedValue().equalsIgnoreCase("noise2") || ClientManagers.moduleManager.killAura.randomizeMode.getSelectedValue().equalsIgnoreCase("noise3")) {
      }

      return false;
   }

   private boolean isMovePointExpired(double d) {
      return Math.abs(d - (double)System.currentTimeMillis()) > this.lineTime.getValue();
   }

   @Override
   public void onLateTick() {
      this.lastExpiredMovePoint = null;
      long currentTimeMs = System.currentTimeMillis();
      if (this.activateMoveLine.getValue()) {
         float f = (float)(mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX));
         float f2 = (float)(mc.thePlayer.lastTickPosY + this.lineWidth.getValue() / 100.0 + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY));
         float f3 = (float)(mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ));
         if (!this.moveLinePoints.isEmpty()) {
            double[] doubles = (double[])this.moveLinePoints.get(this.moveLinePoints.size() - 1);
            if (doubles[0] != (double)f || doubles[1] != (double)f2 || doubles[2] != (double)f3) {
               this.moveLinePoints.add(new double[]{(double)f, (double)f2, (double)f3, (double)currentTimeMs});
            }
         } else {
            this.moveLinePoints.add(new double[]{(double)f, (double)f2, (double)f3, (double)currentTimeMs});
         }

         for (int i = 0; i < this.moveLinePoints.size(); i++) {
            double[] doubles2 = (double[])this.moveLinePoints.get(i);
            if (!this.isMovePointExpired(doubles2[3])) {
               break;
            }

            this.moveLinePoints.remove(i);
            i--;
            this.lastExpiredMovePoint = doubles2;
         }
      } else if (!this.moveLinePoints.isEmpty()) {
         this.moveLinePoints.clear();
         this.lastExpiredMovePoint = null;
      }

      if (this.activateAuraLine.getValue() && this.displayLegitimateRotations.getValue()) {
         float[] floats = null;
         Vec3 vec = null;
         if (!this.shouldSkipNoiseAuraSampling()) {
            floats = new float[]{mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch};
            vec = mc.thePlayer.getPositionEyes(1.0F);
            this.tickRotationTimestamps.add(currentTimeMs);
            this.tickRotationAngles.add(floats);
            this.tickRotationEyePositions.add(vec);

            while (!this.tickRotationTimestamps.isEmpty() && (double)(currentTimeMs - (Long)this.tickRotationTimestamps.get(0)) > this.auraLineTime.getValue()) {
               this.tickRotationTimestamps.remove(0);
               this.tickRotationAngles.remove(0);
               this.tickRotationEyePositions.remove(0);
            }
         }

         if (this.hasKillAuraTarget()) {
            if (!this.shouldSkipNoiseAuraSampling()) {
               this.rotationLineTimestamps.add(currentTimeMs);
               this.rotationLineAngles.add(floats != null ? floats : new float[]{mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch});
               this.rotationLineEyePositions.add(vec != null ? vec : mc.thePlayer.getPositionEyes(1.0F));

               while (!this.rotationLineTimestamps.isEmpty() && (double)(currentTimeMs - (Long)this.rotationLineTimestamps.get(0)) > this.auraLineTime.getValue()) {
                  this.rotationLineTimestamps.remove(0);
                  this.rotationLineAngles.remove(0);
                  this.rotationLineEyePositions.remove(0);
               }
            }
         }
      }
   }

   public void renderMoveLine(ShaderRenderPass shaderRenderPass, float f) {
      if (this.moveLinePoints.size() >= 2) {
         RenderUtil.setGlColor(this.blurShadowSettings.resolvePassColor(shaderRenderPass, this.moveLineColor.getColorInt((byte)0)));
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(3, DefaultVertexFormats.POSITION);
         if (this.lastExpiredMovePoint != null) {
            double[] doubles = (double[])this.moveLinePoints.get(0);
            double[] doubles2 = new double[]{
               MathStatisticsUtil.lerp(this.lastExpiredMovePoint[0], doubles[0], (double)f),
               MathStatisticsUtil.lerp(this.lastExpiredMovePoint[1], doubles[1], (double)f),
               MathStatisticsUtil.lerp(this.lastExpiredMovePoint[2], doubles[2], (double)f)
            };
            worldRenderer.pos(
                  (double)((float)doubles2[0]) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)doubles2[1]) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)doubles2[2]) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         }

         for (int i = 0; i < this.moveLinePoints.size() - 1; i++) {
            double[] doubles3 = (double[])this.moveLinePoints.get(i);
            worldRenderer.pos(
                  (double)((float)doubles3[0]) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)doubles3[1]) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)doubles3[2]) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         }

         double[] doubles4 = (double[])this.moveLinePoints.get(this.moveLinePoints.size() - 1);
         double[] doubles5 = (double[])this.moveLinePoints.get(this.moveLinePoints.size() - 2);
         if (mc.thePlayer.posX - mc.thePlayer.lastTickPosX == 0.0 && mc.thePlayer.posY - mc.thePlayer.lastTickPosY == 0.0 && mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ == 0.0) {
            worldRenderer.pos(
                  (double)((float)doubles4[0]) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)doubles4[1]) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)doubles4[2]) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         } else {
            worldRenderer.pos(
                  (double)((float)MathStatisticsUtil.lerp(doubles5[0], doubles4[0], (double)f)) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)MathStatisticsUtil.lerp(doubles5[1], doubles4[1], (double)f)) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)MathStatisticsUtil.lerp(doubles5[2], doubles4[2], (double)f)) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         }

         instance.draw();
      }
   }

   private void clearAllLines() {
      this.tickRotationAngles.clear();
      this.tickRotationEyePositions.clear();
      this.tickRotationTimestamps.clear();
      this.rotationLineAngles.clear();
      this.rotationLineEyePositions.clear();
      this.rotationLineTimestamps.clear();
      this.lastExpiredMovePoint = null;
      this.moveLinePoints.clear();
   }

   private void renderMoveLinePass(float f, ShaderRenderPass shaderRenderPass) {
      this.renderMoveLine(shaderRenderPass, f);
   }

   private boolean isDisplayAtCurrentPosVisible() {
      return this.displayLegitimateRotations.getValue() && this.displayLegitimateRotations.isVisible();
   }

}
