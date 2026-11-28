package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HealthDisplay extends Module implements RenderListener {
   private static final int HEART_SYMBOL_WIDTH = mc.fontRendererObj.getStringWidth("❤");
   private final DoubleValue scale;
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final DoubleValue decimalPlaces;
   private final DoubleValue spacing;
   private final BooleanValue fontShadow;
   private final BooleanValue background;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings blurShadowSettings;
   private final ColorSetting textColor;
   private final ColorSetting heartColor;
   private final StringValue absorptionMode;
   private final ColorSetting absorptionTextColor;
   private final ColorSetting absorptionHeartColor;

   public HealthDisplay() {
       super("HealthDisplay", "Lebensanzeige", new Color(85, 235, 52), Categorys.RENDER);
      this.scale = new DoubleValue(this, "Scale", 2.0, 0.5, 4.0, 2);
      this.posX = new DoubleValue(this, "PosX", 0.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 0.0, 0.0, 100.0, 2);
      this.decimalPlaces = new DoubleValue(this, "DecimalPlaces", 1.0, 0.0, 3.0, 1);
      this.spacing = new DoubleValue(this, "Spacing", 4.0, 0.0, 10.0, 0);
      this.fontShadow = new BooleanValue(this, "FontShadow", true);
      this.background = new BooleanValue(this, "Background", true);
      ColorSetting colorSetting = new ColorSetting(this, "BackgroundColor", new Color(0, 0, 0, 150));
      BooleanValue background2 = this.background;
      this.backgroundColor = (ColorSetting)colorSetting.withVisibility(() -> background2.getValue());
      this.blurShadowSettings = new BlurShadowSettings(this);
      this.textColor = new ColorSetting(this, "TextColor", Color.green);
      this.heartColor = new ColorSetting(this, "HeartColor", Color.red);
      this.absorptionMode = new StringValue(this, "AbsorptionMode", "Combined", new String[]{"Combined", "Separate", "Hidden"});
      this.absorptionTextColor = (ColorSetting)new ColorSetting(this, "AbsorptionTextColor", Color.orange).withVisibility(() -> this.isAbsorptionTextColorVisible());
      ColorSetting colorSetting3 = new ColorSetting(this, "AbsorptionHeartColor", Color.yellow);
      SettingVisibilityCondition condition3 = () -> {
         return this.absorptionMode.getSelectedValue().equals("Separate");
      };
      this.absorptionHeartColor = (ColorSetting)colorSetting3.withVisibility(condition3);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F / (float)sr.getScaleFactor(), 1.0F / (float)sr.getScaleFactor(), 1.0F);
      String str;
      String str2;
      int n;
      if (this.absorptionMode.getSelectedValue().equals("Separate")) {
         str = MathStatisticsUtil.formatRounded((double)mc.thePlayer.getCurrentHealth(), (int)this.decimalPlaces.getValue());
         str2 = MathStatisticsUtil.formatRounded((double)mc.thePlayer.getAbsorptionAmount(), (int)this.decimalPlaces.getValue());
         n = mc.fontRendererObj.getStringWidth(str2) + (int)this.spacing.getValue(true);
      } else {
         str = MathStatisticsUtil.formatRounded(
            (double)(mc.thePlayer.getCurrentHealth() + (this.absorptionMode.getSelectedValue().equals("Combined") ? mc.thePlayer.getAbsorptionAmount() : 0.0F)), (int)this.decimalPlaces.getValue()
         );
         str2 = null;
         n = 0;
      }

      boolean flag2 = this.absorptionMode.getSelectedValue().equals("Separate") && mc.thePlayer.getAbsorptionAmount() > 0.0F;
      int n2 = mc.fontRendererObj.getStringWidth(str) + (int)this.spacing.getValue(true);
      double d = (double)(n2 + HEART_SYMBOL_WIDTH);
      double d2;
      if (flag2) {
         d2 = this.spacing.getValue(true) * 2.0 + (double)n + (double)HEART_SYMBOL_WIDTH;
      } else {
         d2 = 0.0;
      }

      int n3 = (int)(d + d2);
      GlStateManager.scale(this.scale.getValue(), this.scale.getValue(), 1.0);
      int n4 = (int)((double)mc.displayWidth / this.scale.getValue());
      int n5 = (int)((double)mc.displayHeight / this.scale.getValue());
      GlStateManager.translate(
         this.posX.getValue() / 100.0 * (double)(n4 - (n3 + (this.background.getValue() ? 7 : 0))),
         this.posY.getValue() / 100.0 * (double)(n5 - (mc.fontRendererObj.FONT_HEIGHT + (this.background.getValue() ? 6 : 0))),
         0.0
      );
      BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
      int n6 = n;
      String str3 = str2;
      String str4 = str;
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderHealthPass(n3, str4, n2, flag2, str3, n6, shaderRenderPass));
      GlStateManager.popMatrix();
      RenderUtil.resetGlColorState();
   }

   private void renderHealthPass(int n, String str, int n2, boolean flag, String str2, int n3, ShaderRenderPass shaderRenderPass) {
      byte b = 0;
      byte b2 = 0;
      if (this.background.getValue()) {
         b = 4;
         b2 = 4;
         RenderUtil.drawRect(0, 0, b + n + 3, b2 + mc.fontRendererObj.FONT_HEIGHT + 2, ColorUtil.resolvePassColor(shaderRenderPass, this.backgroundColor.getColorInt((byte)0)));
      }

      if (shaderRenderPass == ShaderRenderPass.NONE || !this.background.getValue() && shaderRenderPass != ShaderRenderPass.BLUR) {
         mc.fontRendererObj.drawStringInt(str, b, b2, this.textColor.getColorInt((byte)0), this.fontShadow.getValue());
         int n6 = b + n2;
         int n7 = b2 - 1;
         mc.fontRendererObj.drawStringInt("❤", n6, n7, this.heartColor.getColorInt((byte)0), this.fontShadow.getValue());
         if (flag) {
            int n9 = (n2 + HEART_SYMBOL_WIDTH) + (int)this.spacing.getValue(true) * 2 + b;
            mc.fontRendererObj.drawStringInt(str2, n9, b2, this.absorptionTextColor.getColorInt((byte)0), this.fontShadow.getValue());
            n6 = n9 + n3;
            n7 = b2 - 1;
            mc.fontRendererObj.drawStringInt("❤", n6, n7, this.absorptionHeartColor.getColorInt((byte)0), this.fontShadow.getValue());
         }
      }
   }

   private boolean isAbsorptionTextColorVisible() {
      return this.absorptionMode.getSelectedValue().equals("Separate");
   }

}
