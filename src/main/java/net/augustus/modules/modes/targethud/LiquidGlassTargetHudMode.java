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
import org.lwjgl.opengl.GL11;

public class LiquidGlassTargetHudMode extends AbstractTargetHudMode {
   private final ColorSetting glassColor;
   private final ColorSetting borderColor;
   private final ColorSetting healthGradientStart;
   private final ColorSetting healthGradientEnd;
   private final ColorSetting healthBackGroundColor;
   private final ColorSetting textColor;
   private final ColorSetting glowColor;

   public LiquidGlassTargetHudMode(TargetHud targetHud, SettingGroup doubleValue) {
       super(targetHud, doubleValue, "LiquidGlass", false);
      String str = this.getName() + "GlassColor";
      Color color = new Color(255, 255, 255, 40);
      String str2 = str;
      this.glassColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("GlassColor")).withVisibility(() -> this.isSelected());
      str = this.getName() + "BorderColor";
      color = new Color(255, 255, 255, 80);
      str2 = str;
      this.borderColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("BorderColor")).withVisibility(() -> this.isSelected());
      String str5 = this.getName() + "HealthGradientStart";
      color = new Color(0, 122, 255);
      str2 = str5;
      ColorSetting colorSetting5 = new ColorSetting(targetHud, str2, color, doubleValue);
      colorSetting5 = (ColorSetting)colorSetting5.withDisplayName("HealthGradientStart");
      this.healthGradientStart = (ColorSetting)colorSetting5.withVisibility(() -> this.isSelected());
      str5 = this.getName() + "HealthGradientEnd";
      color = new Color(52, 199, 89);
      str2 = str5;
      colorSetting5 = new ColorSetting(targetHud, str2, color, doubleValue);
      colorSetting5 = (ColorSetting)colorSetting5.withDisplayName("HealthGradientEnd");
      this.healthGradientEnd = (ColorSetting)colorSetting5.withVisibility(() -> this.isSelected());
      str5 = this.getName() + "HealthBackGroundColor";
      color = new Color(255, 255, 255, 30);
      str2 = str5;
      colorSetting5 = new ColorSetting(targetHud, str2, color, doubleValue);
      colorSetting5 = (ColorSetting)colorSetting5.withDisplayName("HealthBackGroundColor");
      this.healthBackGroundColor = (ColorSetting)colorSetting5.withVisibility(() -> this.isSelected());
      str = this.getName() + "TextColor";
      color = new Color(255, 255, 255, 230);
      str2 = str;
      this.textColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("TextColor")).withVisibility(() -> this.isSelected());
      str = this.getName() + "GlowColor";
      color = new Color(0, 122, 255, 100);
      str2 = str;
      this.glowColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("GlowColor")).withVisibility(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new LiquidGlassTargetHudMode((TargetHud)obj, (SettingGroup)obj2);
   }

   @Override
   public int getHalfWidth() {
      return 130;
   }

   @Override
   public int getHalfHeight() {
      return 50;
   }

   @Override
   public void drawHudContent(AbstractClientPlayer clientPlayer, NetworkPlayerInfo networkPlayerInfo, ScaledResolution sr) {
      boolean isUpsideDownPlayer = this.isUpsideDownPlayer(clientPlayer);
      int halfWidth = this.getHalfWidth();
      int halfHeight = this.getHalfHeight();
      int colorInt = this.glassColor.getColorInt((byte)0);
      BlurShadowManager.enableOneShotPass(true, ((TargetHud)this.parentModule).blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderGlassCard(colorInt, halfWidth, halfHeight, clientPlayer, networkPlayerInfo, isUpsideDownPlayer, shaderRenderPass));
      this.renderPlayerName(clientPlayer);
   }

   private void renderBorder(int n, int n2) {
      int colorInt = this.borderColor.getColorInt((byte)0);
      RenderUtil.drawRoundedRectOutline((float)(-n), (float)(-n2), (float)n, (float)n2, 25.0F, 1, 2, colorInt);
   }

   private void renderAvatar(AbstractClientPlayer clientPlayer, NetworkPlayerInfo networkPlayerInfo, boolean flag) {
      int hurtTime = clientPlayer.hurtTime;
      byte b = 70;
      byte b2 = -115;
      byte b3 = -35;
      if (hurtTime > 0) {
         Color color = new Color(255, 59, 48, Math.min(30 * hurtTime, 255));
         int n = b2 - 5;
         int n2 = b3 - 5;
         RenderUtil.drawRoundedRect((float)n, (float)n2, (float)(n + b + 10), (float)(n2 + b + 10), 20.0F, 1, color);
      } else {
         int colorInt = this.glowColor.getColorInt((byte)0);
         int n3 = b2 - 3;
         int n4 = b3 - 3;
         RenderUtil.drawRoundedRect((float)n3, (float)n4, (float)(n3 + b + 6), (float)(n4 + b + 6), 18.0F, 1, colorInt);
      }

      StencilUtil.initStencilToWrite();
      StencilUtil.beginStencilWrite();
      RenderUtil.drawRoundedRectGl(
         (float)b2,
         (float)b3,
         (float)(b2 + b),
         (float)(b3 + b),
         15.0F,
         1,
         true,
         true,
         true,
         true,
         Color.white.getRGB()
      );
      StencilUtil.readStencilBuffer(1);
      GlStateManager.resetColor();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawPlayerHead(networkPlayerInfo.getLocationSkin(), clientPlayer, flag, b2, b3, b);
      StencilUtil.uninitStencilBuffer();
      if (hurtTime > 0) {
         float f = 1.0F + (float)hurtTime / 20.0F * 0.1F;
         GL11.glPushMatrix();
         GL11.glTranslatef((float)b2 + (float)b / 2.0F, (float)b3 + (float)b / 2.0F, 0.0F);
         GL11.glScalef(f, f, 1.0F);
         GL11.glTranslatef(-((float)b2 + (float)b / 2.0F), -((float)b3 + (float)b / 2.0F), 0.0F);
         Color color2 = new Color(255, 59, 48, 15 * hurtTime);
         RenderUtil.drawRoundedRect((float)b2, (float)b3, (float)(b2 + b), (float)(b3 + b), 15.0F, 1, color2);
         GL11.glPopMatrix();
      }
   }

   private void renderHealthBar(AbstractClientPlayer clientPlayer, ShaderRenderPass shaderRenderPass) {
      int halfWidth = this.getHalfWidth();
      this.getHalfHeight();
      int n = (int)((double)(halfWidth * 2) * 0.55);
      byte b = 8;
      byte b2 = -25;
      byte b3 = 15;
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         this.updateHealthAnimation(clientPlayer, this.getPlayerHealth(clientPlayer));
      }

      float f = this.currentHealth / clientPlayer.getMaxHealth();
      f = MathHelper.clamp_float(f, 0.0F, 1.0F);
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         float f2 = this.previousHealth / clientPlayer.getMaxHealth();
         f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
         this.healthAnimation.setBounds((float)n * f2, 0.0F, (float)n * f, 0.0F);
      }

      if (shaderRenderPass != ShaderRenderPass.BLUR) {
         int n2 = ((TargetHud)this.parentModule).blurShadowSettings.resolvePassColor(shaderRenderPass, this.healthBackGroundColor.getColorInt((byte)0));
         RenderUtil.drawRoundedRect((float)b2, (float)b3, (float)(b2 + n), (float)(b3 + b), 4.0F, 1, n2);
         float f3 = MathHelper.clamp_float(this.healthAnimation.updateX(), 0.0F, (float)n);
         if (f3 > 1.0F) {
            this.drawGradientHealthFill(b2, b3, f3, b, shaderRenderPass);
         }
      }
   }

   private void drawGradientHealthFill(int n, int n2, float f, int n3, ShaderRenderPass shaderRenderPass) {
      BlurShadowSettings blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
      int n4 = blurShadowSettings.resolvePassColor(shaderRenderPass, this.healthGradientStart.getColorInt((byte)0));
      blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
      int n5 = blurShadowSettings.resolvePassColor(shaderRenderPass, this.healthGradientEnd.getColorInt((byte)0));
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      float f2 = (float)(n4 >> 16 & 0xFF) / 255.0F;
      float f3 = (float)(n4 >> 8 & 0xFF) / 255.0F;
      float f4 = (float)(n4 & 0xFF) / 255.0F;
      float f5 = (float)(n4 >> 24 & 0xFF) / 255.0F;
      float f6 = (float)(n5 >> 16 & 0xFF) / 255.0F;
      float f7 = (float)(n5 >> 8 & 0xFF) / 255.0F;
      float f8 = (float)(n5 & 0xFF) / 255.0F;
      float f9 = (float)(n5 >> 24 & 0xFF) / 255.0F;
      GL11.glBegin(7);
      GlStateManager.color(f2, f3, f4, f5);
      GL11.glVertex2d((double)n, (double)n2);
      GL11.glVertex2d((double)n, (double)(n2 + n3));
      GlStateManager.color(f6, f7, f8, f9);
      GL11.glVertex2d((double)((float)n + f), (double)(n2 + n3));
      GL11.glVertex2d((double)((float)n + f), (double)n2);
      GL11.glEnd();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   private void renderPlayerName(AbstractClientPlayer clientPlayer) {
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
      String name2 = clientPlayer.getGameProfile().getName();
      int stringWidth = (int)FontManager.rubikBold32.getStringWidth(name2);
      FontManager.rubikBold32.drawString(name2, (double)((byte)-25), (double)((byte)-35), this.textColor.getColorInt((byte)0));
   }

   private void renderGlassCard(int n, int n2, int n3, AbstractClientPlayer clientPlayer, NetworkPlayerInfo networkPlayerInfo, boolean flag, ShaderRenderPass shaderRenderPass) {
      int n5 = ((TargetHud)this.parentModule).blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n));
      RenderUtil.drawRoundedRect((float)(-n2), (float)(-n3), (float)n2, (float)n3, 25.0F, 1, n5);
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         this.renderBorder(n2, n3);
      }

      if (shaderRenderPass == ShaderRenderPass.NONE) {
         this.renderAvatar(clientPlayer, networkPlayerInfo, flag);
      }

      this.renderHealthBar(clientPlayer, shaderRenderPass);
   }

}
