package net.augustus.modules.modes.targetesp;

import net.augustus.render.RenderUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.modules.render.TargetESP;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class SigmaTargetEspMode extends AbstractColoredTargetEspMode {
   private final DoubleValue speed;
   private final DoubleValue length;
   private final DoubleValue additionalRadius;

   public SigmaTargetEspMode(TargetESP targetESP, SettingGroup doubleValue) {
       super(targetESP, doubleValue, "Sigma");
      this.speed = new DoubleValue(this.parentModule, "Speed", 1.0, 0.0, 2.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.length = new DoubleValue(this.parentModule, "Length", 1.0, 0.0, 2.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.additionalRadius = new DoubleValue(this.parentModule, "AdditionalRadius", 0.0, -1.0, 2.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void renderTargetEsp(EntityLivingBase entity) {
      BlurShadowManager.enableOneShotPass(((TargetESP)this.parentModule).blurShadowSettings.isBlurActive(), ((TargetESP)this.parentModule).blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(true, shaderRenderPass -> {
         this.renderSigmaRing(entity, shaderRenderPass);
      });
   }

   public void renderSigmaRing(EntityLivingBase entity, ShaderRenderPass shaderRenderPass) {
      float f = (float)(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
      float f2 = (float)(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY());
      float f3 = (float)(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ());
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GL11.glLineWidth(3.0F);
      GlStateManager.shadeModel(7425);
      GlStateManager.disableCull();
      double d = Math.max(0.01, (double)entity.width * 1.2 + this.additionalRadius.getValue());
      float f4 = (float)Math.sin((double)System.currentTimeMillis() / (400.0 / this.speed.getValue()));
      double d2 = MathHelper.clamp_double(this.length.getValue(), 0.0, 10.0);
      GlStateManager.translate(0.0F, f4, 0.0F);
      GL11.glBegin(5);

      for (int i = 0; i < 361; i++) {
         this.applyRingVertexColor(entity, i, shaderRenderPass);
         double d3 = (double)f + Math.cos(Math.toRadians((double)i)) * d;
         double d4 = (double)f3 - Math.sin(Math.toRadians((double)i)) * d;
         GL11.glVertex3d(d3, (double)(f2 + 1.0F), d4);
         int targetColor = this.getTargetColor(entity);
         RenderUtil.setGlColorWithAlpha(((TargetESP)this.parentModule).blurShadowSettings.resolvePassColor(shaderRenderPass, targetColor), 0.0F);
         GL11.glVertex3d(d3, (double)(f2 + 1.0F) + (double)(f4 * 0.4F) * d2, d4);
      }

      GL11.glEnd();
      GL11.glBegin(2);

      for (int j = 0; j < 361; j++) {
         this.applyRingVertexColor(entity, j, shaderRenderPass);
         GL11.glVertex3d(
            (double)f + Math.cos(Math.toRadians((double)j)) * d,
            (double)(f2 + 1.0F),
            (double)f3 - Math.sin(Math.toRadians((double)j)) * d
         );
      }

      GL11.glEnd();
      GlStateManager.enableAlpha();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.resetColor();
      GL11.glLineWidth(1.0F);
      GlStateManager.popMatrix();
   }

   private void applyRingVertexColor(EntityLivingBase entity, int n, ShaderRenderPass shaderRenderPass) {
      int targetColor = this.getTargetColor(entity);
      RenderUtil.setGlColor(((TargetESP)this.parentModule).blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, targetColor)));
   }

}
