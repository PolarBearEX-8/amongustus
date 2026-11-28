package net.augustus.modules.modes.targethud;

import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.render.StencilUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.render.TargetHud;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ModernTargetHudMode extends AbstractTargetHudMode {
   private final ColorSetting backGroundColor;
   private final ColorSetting healthBackGroundColor;
   private final ColorSetting healthColor;
   private final ColorSetting headDamageColor;
   private final ColorSetting textColor;

   public ModernTargetHudMode(TargetHud targetHud, SettingGroup doubleValue) {
       super(targetHud, doubleValue, "Modern", false);
      String str = this.getName() + "BackGroundColor";
      Color color = new Color(0, 0, 0, 100);
      String str2 = str;
      ColorSetting colorSetting = new ColorSetting(targetHud, str2, color, doubleValue);
      colorSetting = (ColorSetting)colorSetting.withDisplayName("BackGroundColor");
      this.backGroundColor = (ColorSetting)colorSetting.withVisibility(() -> this.isSelected());
      String str4 = this.getName() + "HealthBackGroundColor";
      color = new Color(0, 0, 0);
      str2 = str4;
      this.healthBackGroundColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("HealthBackGroundColor")).withVisibility(() -> this.isSelected());
      str4 = this.getName() + "HealthColor";
      color = new Color(255, 0, 0);
      str2 = str4;
      this.healthColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("HealthColor")).withVisibility(() -> this.isSelected());
      str4 = this.getName() + "HeadDamageColor";
      color = new Color(255, 0, 0);
      str2 = str4;
      this.headDamageColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("HeadDamageColor")).withVisibility(() -> this.isSelected());
      str4 = this.getName() + "TextColor";
      color = new Color(255, 255, 255);
      str2 = str4;
      this.textColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("TextColor")).withVisibility(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new ModernTargetHudMode((TargetHud)obj, (SettingGroup)obj2);
   }

   @Override
   public int getHalfWidth() {
      return 120;
   }

   @Override
   public int getHalfHeight() {
      return 40;
   }

   @Override
   public void drawHudContent(AbstractClientPlayer clientPlayer, NetworkPlayerInfo networkPlayerInfo, ScaledResolution sr) {
      boolean isUpsideDownPlayer = this.isUpsideDownPlayer(clientPlayer);
      int halfWidth = this.getHalfWidth();
      int halfHeight = this.getHalfHeight();
      int colorInt = this.backGroundColor.getColorInt((byte)0);
      BlurShadowManager.enableOneShotPass(((TargetHud)this.parentModule).blurShadowSettings.isBlurActive(), ((TargetHud)this.parentModule).blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(true, shaderRenderPass -> {
         float f = (float)(-halfWidth);
         float f2 = (float)(-halfHeight);
         float f3 = (float)halfWidth;
         float f4 = (float)halfHeight;
         BlurShadowSettings blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int n = ColorUtil.resolvePassColor(shaderRenderPass, colorInt);
         RenderUtil.drawRoundedRect(f, f2, f3, f4, 20.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, n));
         if (shaderRenderPass == ShaderRenderPass.NONE) {
            this.renderAvatar(clientPlayer, isUpsideDownPlayer);
         }

         this.renderHealthBar(clientPlayer, shaderRenderPass);
      });
      this.renderPlayerName(clientPlayer);
   }

   private void renderAvatar(AbstractClientPlayer clientPlayer, boolean flag) {
      int hurtTime = clientPlayer.hurtTime;
      int n = 66 - hurtTime;
      int n2 = -113 + hurtTime / 2;
      int n3 = -33 + hurtTime / 2;
      StencilUtil.initStencilToWrite();
      StencilUtil.beginStencilWrite();
      RenderUtil.drawRoundedRectGl(
         (float)n2,
         (float)n3,
         (float)(n2 + n),
         (float)(n3 + n),
         15.0F,
         1,
         true,
         true,
         true,
         true,
         Color.white.getRGB()
      );
      StencilUtil.readStencilBuffer(1);
      this.drawPlayerHead(clientPlayer.getLocationSkin(), clientPlayer, flag, n2, n3, n);
      StencilUtil.uninitStencilBuffer();
      if (hurtTime > 0) {
         int colorInt = this.headDamageColor.getColorInt((byte)0);
         RenderUtil.drawRoundedRect(
            (float)n2,
            (float)n3,
            (float)(n2 + n),
            (float)(n3 + n),
            15.0F,
            1,
            ColorUtil.withAlpha(colorInt, 20 * hurtTime)
         );
      }
   }

   private void renderHealthBar(AbstractClientPlayer clientPlayer, ShaderRenderPass shaderRenderPass) {
      short s = 147;
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         this.updateHealthAnimation(clientPlayer, this.getPlayerHealth(clientPlayer));
      }

      float f = this.currentHealth / clientPlayer.getMaxHealth();
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         float f2 = this.previousHealth / clientPlayer.getMaxHealth();
         this.healthAnimation.setBounds((float)s * f2, 0.0F, (float)s * f, 0.0F);
      }

      if (shaderRenderPass != ShaderRenderPass.BLUR
         && (float)s * f > -40.0F
         && mc.getCurrentServerData() != null
         && !mc.getCurrentServerData().serverIP.contains("gommehd.net")) {
         BlurShadowSettings blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int colorInt = this.healthBackGroundColor.getColorInt((byte)0);
         RenderUtil.drawRoundedRect(-37.0F, 0.0F, 110.0F, 20.0F, 8.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt));
         float f3 = MathHelper.clamp_float(-37.0F + this.healthAnimation.updateX(), -37.0F, 110.0F);
         blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int colorInt2 = this.healthColor.getColorInt((byte)0);
         RenderUtil.drawRoundedRect(-37.0F, 0.0F, f3, 20.0F, 8.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2));
      }
   }

   private void renderPlayerName(AbstractClientPlayer clientPlayer) {
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
      CustomFontUtil rubikBold32 = FontManager.rubikBold32;
      String name2 = clientPlayer.getGameProfile().getName();
      int stringWidth = (int)rubikBold32.getStringWidth(name2);
      rubikBold32 = FontManager.rubikBold32;
      name2 = clientPlayer.getGameProfile().getName();
      double d = (double)((int)(33.0F + (float)(-stringWidth) / 2.0F));
      int colorInt = this.textColor.getColorInt((byte)0);
      rubikBold32.drawString(name2, d, -25.0, colorInt);
   }

}
