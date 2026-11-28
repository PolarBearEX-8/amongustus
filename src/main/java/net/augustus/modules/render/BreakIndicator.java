package net.augustus.modules.render;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class BreakIndicator extends Module implements RenderListener {
   private final DoubleValue decimalPlaces;
   private final DoubleValue scale;
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final ColorSetting color2;
   private final BooleanValue backGround;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings blurShadowSettings;
   private final StringValue toggleAnimationMode;
   private final DoubleValue animationTime;
   private final AnimationUtil toggleAnimation;
   private float lastBlockDamage;

   public BreakIndicator() {
       super("BreakIndicator", "BlockBrechAnzeige", new Color(50, 168, 123), Categorys.RENDER);
      this.decimalPlaces = new DoubleValue(this, "DecimalPlaces", 1.0, 0.0, 4.0, 0);
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2);
      this.posX = new DoubleValue(this, "PosX", 52.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 50.0, 0.0, 100.0, 2);
      this.color2 = new ColorSetting(this, "Color", Color.white);
      this.backGround = new BooleanValue(this, "BackGround", true);
      ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
      BooleanValue backGround2 = this.backGround;
      this.backgroundColor = (ColorSetting)colorSetting.withVisibility(() -> backGround2.getValue());
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      this.toggleAnimationMode = new StringValue(this, "ToggleAnimation", "easeInOutCubic", AnimationUtil.getEasingNames(true));
      this.animationTime = new DoubleValue(this, "AnimationTime", 500.0, 0.0, 1000.0, 0).visibleWhen(() -> this.isAnimationTimeVisible());
      this.toggleAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      float f2 = mc.objectMouseOver != null && mc.playerController.isHittingBlock && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         ? mc.playerController.getCurBlockDamageMP()
         : 0.0F;
      if (f2 > 0.0F && this.lastBlockDamage == 0.0F) {
         this.toggleAnimation.start((int)this.animationTime.getValue(), AnimationUtil.parseEasingType(this.toggleAnimationMode.getSelectedValue()), 1, true);
      } else if (f2 == 0.0F && this.lastBlockDamage > 0.0F) {
         this.toggleAnimation.start((int)this.animationTime.getValue(), AnimationUtil.parseEasingType(this.toggleAnimationMode.getSelectedValue()), 0, true);
      }

      this.lastBlockDamage = f2;
      if (this.toggleAnimation.getLinearProgress() != 1.0 && !this.toggleAnimationMode.getSelectedValue().equals("None") || f2 != 0.0F) {
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F / (float)sr.getScaleFactor(), 1.0F / (float)sr.getScaleFactor(), 1.0F);
         String str = NumberFormatUtil.formatDecimal((double)(mc.playerController.getCurBlockDamageMP() * 100.0F), Math.max((int)this.decimalPlaces.getValue(), 0)) + "%";
         float f3 = (float)(mc.fontRendererObj.getStringWidth(str) + 4) / 2.0F;
         GlStateManager.translate(
            (double)f3 * this.scale.getValue()
               + this.posX.getValue() / 100.0 * ((double)mc.displayWidth - (double)(f3 * 2.0F) * this.scale.getValue()),
            6.0 * this.scale.getValue() + this.posY.getValue() / 100.0 * ((double)mc.displayHeight - 12.0 * this.scale.getValue()),
            0.0
         );
         GlStateManager.scale(this.scale.getValue(), this.scale.getValue(), 1.0);
         double easedProgress = this.toggleAnimation.getEasedProgress();
         if (this.toggleAnimation.getLinearProgress() < 1.0) {
            GlStateManager.scale(easedProgress, easedProgress, 1.0);
         }

         int n;
         if (this.backGround.getValue()) {
            n = this.backgroundColor.getColorInt((byte)0);
         } else {
            n = 0;
         }

         int n2 = n;
         int colorInt = this.color2.getColorInt((byte)0);
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
            if (this.backGround.getValue()) {
               double d = (double)(-f3);
               double d2 = (double)f3;
               BlurShadowSettings blurShadowSettings2 = this.blurShadowSettings;
               int n3 = ColorUtil.resolvePassColor(shaderRenderPass, n2);
               RenderUtil.drawRect(d, -6.0, d2, 6.0, blurShadowSettings2.resolvePassColor(shaderRenderPass, n3));
            }

            if (shaderRenderPass != ShaderRenderPass.BLUR) {
               mc.fontRendererObj.drawStringUnfiltered(str, -f3 + 2.0F, -3.5F, colorInt);
            }
         });
         GlStateManager.popMatrix();
         RenderUtil.resetGlColorState();
      }
   }

   private boolean isAnimationTimeVisible() {
      return !this.toggleAnimationMode.getSelectedValue().equals("None");
   }

}
