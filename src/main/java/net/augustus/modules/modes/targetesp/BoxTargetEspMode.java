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
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class BoxTargetEspMode extends AbstractColoredTargetEspMode {
   private final DoubleValue boxLineWidth;
   private final DoubleValue boxAdjustWidth;
   private final DoubleValue boxHeight;
   private final DoubleValue boxAdjustYPos;

   public BoxTargetEspMode(TargetESP targetESP, SettingGroup doubleValue) {
       super(targetESP, doubleValue, "Box");
      this.boxLineWidth = new DoubleValue(this.parentModule, "BoxLineWidth", 0.0, 0.0, 12.0, 0, this.modeSetting).named("LineWidth").visibleWhen(() -> this.isSelected());
      this.boxAdjustWidth = new DoubleValue(this.parentModule, "BoxAdjustWidth", -0.1, -1.0, 1.0, 2, this.modeSetting).named("AdjustWidth").visibleWhen(() -> this.isSelected());
      this.boxHeight = new DoubleValue(this.parentModule, "BoxHeight", 0.08, 0.0, 0.5, 2, this.modeSetting).named("Height").visibleWhen(() -> this.isSelected());
      this.boxAdjustYPos = new DoubleValue(this.parentModule, "BoxAdjustYPos", 0.14, -2.0, 2.0, 2, this.modeSetting).named("AdjustYPos").visibleWhen(() -> this.isSelected());
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
      float interpolatedRenderX = this.getInterpolatedRenderX(entity);
      float interpolatedRenderY = this.getInterpolatedRenderY(entity);
      float interpolatedRenderZ = this.getInterpolatedRenderZ(entity);
      int targetColor = this.getTargetColor(entity);
      BlurShadowManager.enableOneShotPass(((TargetESP)this.parentModule).blurShadowSettings.isBlurActive(), ((TargetESP)this.parentModule).blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderBoxPass(targetColor, entity, interpolatedRenderX, interpolatedRenderY, interpolatedRenderZ, shaderRenderPass));
   }

   private void renderBoxPass(int n, EntityLivingBase entity, float f, float f2, float f3, ShaderRenderPass shaderRenderPass) {
      RenderUtil.setGlColor(((TargetESP)this.parentModule).blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n)));
      RenderUtil.drawRotatedEntityBox(entity, f, f2 + entity.height + (float)this.boxAdjustYPos.getValue(), f3, (double)entity.width + this.boxAdjustWidth.getValue(), this.boxHeight.getValue());
      if (this.boxLineWidth.getValue() > 0.0) {
         GL11.glLineWidth((float)(this.boxLineWidth.getValue() / 2.0));
         RenderUtil.setGlColorWithAlpha(n, 1.0F);
         RenderUtil.drawRotatedEntityBoxOutline(entity, f, f2 + entity.height + (float)this.boxAdjustYPos.getValue(), f3, (double)entity.width + this.boxAdjustWidth.getValue(), this.boxHeight.getValue());
      }
   }

}
