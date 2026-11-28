package net.augustus.modules.render;

import java.awt.Color;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.font.DynamicFontUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderPassTask;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.ui.GuiIngameHook;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class HUD extends Module implements RenderListener {
   public final StringValue mode;
   public final StringValue side;
   public final StringValue casing;
   private final DoubleValue xPos;
   private final DoubleValue yPos;
   private final DoubleValue scale;
   public final DoubleValue size;
   private final DoubleValue newFontSize;
   private final ColorSetting color2;
   private final BooleanValue backGround;
   private final BooleanValue roundedBackGround;
   private final DoubleValue roundFactor;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings hud;
   private final String upperCaseClientName;
   private final String lowerCaseClientName;
   private final ResourceLocation augustusTexture;
   private final ResourceLocation augustusTexture2;
   private final ResourceLocation augustusTexture3;
   private final ResourceLocation augustusTexture4;
   private int lastSimpleFontSize;

   public HUD() {
       super("HUD", "ÜberlagerungsElemente", new Color(75, 166, 91), Categorys.RENDER);
      this.mode = new StringValue(
         this, "Mode", "Basic", new String[]{"None", "Basic", "Other", "Simple", "Ausustus", "Xave", "Augustus", "Amogus", "Logo", "Fanart"}
      );
      this.side = (StringValue)new StringValue(this, "Side", "Left", new String[]{"Left", "Right"}).withVisibility(() -> this.isSideVisible());
      StringValue setting = new StringValue(this, "Casing", "Default", new String[]{"Default", "UpperCase", "LowerCase"});
      SettingVisibilityCondition condition2 = () -> {
         return this.mode.getSelectedValue().equals("Simple");
      };
      this.casing = (StringValue)setting.withVisibility(condition2);
      this.xPos = new DoubleValue(this, "X-Pos", 0.0, 0.0, 100.0, 2).visibleWhen(() -> this.isPosXVisible());
      this.yPos = new DoubleValue(this, "Y-Pos", 0.0, 0.0, 100.0, 2).visibleWhen(() -> this.isPosYVisible());
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2).visibleWhen(() -> this.isScaleVisible());
      this.size = new DoubleValue(this, "Size", 0.6, 0.01, 2.0, 2).visibleWhen(() -> this.isImageLogoMode());
      DoubleValue newFontSizeSetting = new DoubleValue(this, "NewFontSize", 33.0, 16.0, 80.0, 0).named("Size");
      SettingVisibilityCondition condition7 = () -> {
         return this.mode.getSelectedValue().equals("Simple");
      };
      this.newFontSize = newFontSizeSetting.visibleWhen(condition7);
      this.color2 = (ColorSetting)new ColorSetting(this, "Color", Color.white).withVisibility(() -> this.isTextColorVisible());
      BooleanValue booleanValue = new BooleanValue(this, "BackGround", true);
      SettingVisibilityCondition condition9 = () -> {
         return this.mode.getSelectedValue().equals("Ausustus")
            || this.mode.getSelectedValue().equals("Other")
            || this.mode.getSelectedValue().equals("Basic")
            || this.mode.getSelectedValue().equals("Simple");
      };
      this.backGround = (BooleanValue)booleanValue.withVisibility(condition9);
      this.roundedBackGround = (BooleanValue)new BooleanValue(this, "RoundedBackGround", false).withVisibility(() -> this.isRoundedBackgroundVisible());
      this.roundFactor = new DoubleValue(this, "RoundFactor", 2.0, 1.0, 10.0, 0).visibleWhen(() -> this.isRoundFactorVisible());
      this.backgroundColor = (ColorSetting)new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100)).withVisibility(() -> this.isBackgroundColorVisible());
      BlurShadowSettings blurShadowSettings2 = new BlurShadowSettings(this, "Hud").withCustomShadowColor(Color.black);
      SettingVisibilityCondition condition13 = () -> {
         return !this.mode.getSelectedValue().equals("None");
      };
      this.hud = (BlurShadowSettings)blurShadowSettings2.withVisibility(condition13);
      this.upperCaseClientName = Augustus.getInstance().getName().toUpperCase(Locale.ROOT);
      this.lowerCaseClientName = Augustus.getInstance().getName().toLowerCase(Locale.ROOT);
      this.augustusTexture = new ResourceLocation("augustus", "pictures/augustus.png");
      this.augustusTexture2 = new ResourceLocation("augustus", "pictures/amogus.png");
      this.augustusTexture3 = new ResourceLocation("augustus", "pictures/logo.png");
      this.augustusTexture4 = new ResourceLocation("augustus", "pictures/fanart.png");
      this.lastSimpleFontSize = 0;
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
   public void onRender2DPre(float partialTicks, ScaledResolution sr) {
      if (this.isImageLogoMode() && this.hud.isBlurActive()) {
         GL11.glPushMatrix();
         GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
         GL11.glTranslated(this.xPos.getValue() / 100.0 * (double)mc.displayWidth, this.yPos.getValue() / 100.0 * (double)mc.displayHeight, 0.0);
         boolean flag = this.side.getSelectedValue().equals("Left");
         double value = this.size.getValue();
         GL11.glScaled(value, value, 1.0);
         ScaledResolution sr2 = new ScaledResolution((double)mc.displayWidth * (1.0 / value), (double)mc.displayHeight * (1.0 / value), (int)value);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int n = !this.mode.getSelectedValue().equals("Amogus") && !this.mode.getSelectedValue().equals("Logo") ? 210 : 256;
         int n2 = flag ? -20 : sr2.getScaledWidth() - n;
         mc.getTextureManager()
            .bindTexture(this.mode.getSelectedValue().equals("Augustus") ? this.augustusTexture : (this.mode.getSelectedValue().equals("Logo") ? this.augustusTexture3 : this.augustusTexture2), false);
         mc.ingameGUI.drawTexturedModalRect(n2, -3, 0, 0, n, 256);
         BlurShadowManager.enableBlurOnce();
         BlurShadowManager.disableNormalPass();
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> mc.ingameGUI.drawTexturedModalRect(n2, -3, 0, 0, n, 256));
         GL11.glScaled(value, value, 1.0);
         GL11.glPopMatrix();
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (!mc.gameSettings.showDebugInfo) {
         GL11.glPushMatrix();
         GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
         GL11.glTranslated(this.xPos.getValue() / 100.0 * (double)mc.displayWidth, this.yPos.getValue() / 100.0 * (double)mc.displayHeight, 0.0);
         boolean isImageLogoMode = this.isImageLogoMode();
         boolean flag = this.side.getSelectedValue().equals("Left");
         double d = isImageLogoMode ? this.size.getValue() : this.scale.getValue();
         if (!this.mode.getSelectedValue().equals("Simple")) {
            GL11.glScaled(d, d, 1.0);
         }

         GlStateManager.disableBlend();
         ScaledResolution sr2 = new ScaledResolution((double)mc.displayWidth * (1.0 / d), (double)mc.displayHeight * (1.0 / d), (int)d);
         FontRenderer fontRendererObj = mc.fontRendererObj;
         switch (this.mode.getSelectedValue()) {
            case "Augustus":
            case "Logo":
            case "Fanart":
            case "Amogus":
               GlStateManager.enableAlpha();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               int n = this.mode.getSelectedValue().equals("Augustus") ? 210 : 256;
               int n2 = flag ? -20 : sr2.getScaledWidth() - n;
               mc.getTextureManager()
                  .bindTexture(
                     this.mode.getSelectedValue().equals("Augustus")
                        ? this.augustusTexture
                        : (this.mode.getSelectedValue().equals("Logo") ? this.augustusTexture3 : (this.mode.getSelectedValue().equals("Amogus") ? this.augustusTexture2 : this.augustusTexture4))
                  );
               if (this.hud.isShadowActive()) {
                  BlurShadowManager.enableShadowOnce();
               }

               if (!this.hud.isBlurActive()) {
                  BlurShadowManager.enableNormalPassOnce();
               }

               BlurShadowManager.submitRenderTask(true, shaderRenderPass -> mc.ingameGUI.drawTexturedModalRect(n2, -3, 0, 0, n, 256));
               this.updateArrayListOffset((double)((float)(256.0 * d) + 1.0F), 1.0);
               break;
            case "Other":
               GL11.glPushMatrix();
               GL11.glScaled(1.5, 1.5, 1.0);
               this.updateArrayListOffset((double)((int)((double)((float)fontRendererObj.FONT_HEIGHT * 1.75F + 5.0F) * 1.5)), d);
               int stringWidth = fontRendererObj.getStringWidth("A");
               int stringWidth2 = fontRendererObj.getStringWidth("ugustus");
               int colorInt = this.color2.getColorInt((byte)0);
               int colorInt2 = this.backgroundColor.getColorInt((byte)0);
               if (this.backGround.getValue()) {
                  BlurShadowManager.enableOneShotPass(this.hud.isBlurActive(), this.hud.isShadowActive());
               }

               if (flag) {
                  if (this.backGround.getValue()) {
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderOtherLeftBackground(colorInt2, stringWidth, stringWidth2, fontRendererObj, shaderRenderPass2));
                  }

                  GL11.glPushMatrix();
                  GL11.glScaled(1.75, 1.75, 1.0);
                  fontRendererObj.drawStringWithShadowOffset("A", 1.0F, 1.0F, colorInt, true, 0.5F);
                  GL11.glPopMatrix();
                  fontRendererObj.drawStringWithShadowOffset("ugustus", (float)stringWidth * 1.75F + 2.0F, (float)(2 + fontRendererObj.FONT_HEIGHT) - 4.0F, colorInt, true, 0.5F);
               } else {
                  if (this.backGround.getValue()) {
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass3 -> this.renderOtherRightBackground(colorInt2, sr2, stringWidth, stringWidth2, fontRendererObj, shaderRenderPass3));
                  }

                  GL11.glPushMatrix();
                  GL11.glScaled(1.75, 1.75, 1.0);
                  fontRendererObj.drawStringWithShadowOffset("A", (float)sr2.getScaledWidth() / 1.5F / 1.75F - (float)stringWidth - (float)stringWidth2 / 1.75F - 0.25F, 1.0F, colorInt, true, 0.5F);
                  GL11.glPopMatrix();
                  fontRendererObj.drawStringWithShadowOffset(
                     "ugustus",
                     (float)sr2.getScaledWidth() / 1.5F - 1.75F - (float)stringWidth * 1.5F * 1.75F - (float)stringWidth2 / 1.5F + 4.0F,
                     (float)(2 + fontRendererObj.FONT_HEIGHT) - 4.0F,
                     colorInt,
                     true,
                     0.5F
                  );
               }

               GL11.glPopMatrix();
               break;
            case "Basic":
            case "Ausustus":
               GregorianCalendar instance = (GregorianCalendar)GregorianCalendar.getInstance();
               instance.setTime(new Date());
               int n3 = ClientManagers.moduleManager.protector.isToggled() && ClientManagers.moduleManager.protector.time.getValue() ? ClientManagers.moduleManager.protector.getFakeHour() : instance.get(10);
               String str = instance.get(12) <= 9 ? "0" + instance.get(12) : String.valueOf(instance.get(12));
               str = ClientManagers.moduleManager.protector.isToggled() && ClientManagers.moduleManager.protector.time.getValue() ? String.valueOf(ClientManagers.moduleManager.protector.getFakeMinute()) : str;
               String str2;
               if (instance.get(9) == 0) {
                  str2 = n3 < 9 ? " (0" + n3 + ":" + str + " AM)" : " (" + n3 + ":" + str + " AM)";
               } else {
                  str2 = n3 < 9 ? " (0" + n3 + ":" + str + " PM)" : " (" + n3 + ":" + str + " PM)";
               }

               StringBuilder sb = new StringBuilder();
               String str3;
               if (this.mode.getSelectedValue().equals("Basic")) {
                  str3 = Augustus.getInstance().getName();
               } else {
                  short s = 32427;
                  str3 = "Ausustus";
               }

               String str4 = sb.append(str3).append(" ").append(Augustus.getInstance().getVersion()).toString();
               float stringWidth3 = (float)fontRendererObj.getStringWidth(str4);
               float stringWidth4 = (float)fontRendererObj.getStringWidth(str2);
               this.updateArrayListOffset((double)(fontRendererObj.FONT_HEIGHT + 1), d);
               int rgb = new Color(182, 186, 189).getRGB();
               int colorInt3 = this.color2.getColorInt((byte)0);
               int colorInt4 = this.backgroundColor.getColorInt((byte)0);
               if (this.backGround.getValue()) {
                  BlurShadowManager.enableOneShotPass(this.hud.isBlurActive(), this.hud.isShadowActive());
               }

               boolean value = this.backGround.getValue();
               boolean value2 = this.roundedBackGround.getValue();
               float f2 = value2 && value ? (float)this.roundFactor.getValue() - 1.0F : 0.0F;
               if (flag) {
                  if (value) {
                     BlurShadowManager.submitRenderTask(
                        false,
                        shaderRenderPass4 -> {
                           int n5 = this.hud.resolvePassColor(shaderRenderPass4, ColorUtil.resolvePassColor(shaderRenderPass4, colorInt4));
                           if (value2) {
                              RenderUtil.drawRoundedRect(
                                 0.0F,
                                 0.0F,
                                 stringWidth4 + 1.0F + stringWidth3 + f2 * 2.0F,
                                 (float)(fontRendererObj.FONT_HEIGHT + 1),
                                 (float)this.roundFactor.getValue(),
                                 1,
                                 n5
                              );
                           } else {
                              RenderUtil.drawRect(0.0F, 0.0F, stringWidth4 + 1.0F + stringWidth3, (float)(fontRendererObj.FONT_HEIGHT + 1), n5);
                           }
                        }
                     );
                  }

                  fontRendererObj.drawStringWithShadowOffset(str4, 0.0F + f2, 1.0F, colorInt3, true, 0.5F);
                  fontRendererObj.drawStringWithShadowOffset(str2, 0.0F + f2 + stringWidth3, 1.0F, rgb, true, 0.5F);
               } else {
                  if (value) {
                     BlurShadowManager.submitRenderTask(
                        false,
                        shaderRenderPass5 -> {
                           int n7 = this.hud.resolvePassColor(shaderRenderPass5, ColorUtil.resolvePassColor(shaderRenderPass5, colorInt4));
                           if (value2) {
                              RenderUtil.drawRoundedRect(
                                 (float)sr2.getScaledWidth() - stringWidth4 - 3.0F - stringWidth3,
                                 0.0F,
                                 (float)sr2.getScaledWidth(),
                                 (float)(fontRendererObj.FONT_HEIGHT + 1),
                                 (float)this.roundFactor.getValue(),
                                 1,
                                 n7
                              );
                           } else {
                              RenderUtil.drawRect(
                                 (float)sr2.getScaledWidth() - stringWidth4 - 3.0F - stringWidth3,
                                 0.0F,
                                 (float)sr2.getScaledWidth(),
                                 (float)(fontRendererObj.FONT_HEIGHT + 1),
                                 n7
                              );
                           }
                        }
                     );
                  }

                  fontRendererObj.drawStringWithShadowOffset(str4, (float)sr2.getScaledWidth() - stringWidth3 - stringWidth4 - 1.0F - f2, 1.0F, colorInt3, true, 0.5F);
                  fontRendererObj.drawStringWithShadowOffset(str2, (float)sr2.getScaledWidth() - stringWidth4 - 1.0F - f2, 1.0F, rgb, true, 0.5F);
               }
               break;
            case "Xave":
               int colorInt5 = this.color2.getColorInt((byte)0);
               BlurShadowManager.enableOneShotPass(false, this.hud.isShadowActive());
               if (flag) {
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass6 -> {
                     CustomFontUtil esp60 = FontManager.esp60;
                     String upperCaseClientName2 = this.upperCaseClientName;
                     int n9 = this.hud.resolvePassColor(shaderRenderPass6, colorInt5);
                     double d7 = 2.0;
                     esp60.drawStringWithShadow(upperCaseClientName2, 2.0, d7, n9);
                  });
               } else {
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass7 -> this.renderXaveRightText(sr2, colorInt5, shaderRenderPass7));
               }

               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               this.updateArrayListOffset((double)FontManager.esp60.getFontHeight() * d + (double)FontManager.esp16.getFontHeight() / 1.25, 1.0);
               break;
            case "Simple":
               int n11 = MathHelper.clamp_int((int)this.newFontSize.getValue(), 1, 300);
               if (this.lastSimpleFontSize != n11) {
                  FontManager.rubikBoldHud.setFontSize(n11);
               }

               String str6;
               if (this.casing.getSelectedValue().equals("UpperCase")) {
                  str6 = this.upperCaseClientName;
               } else if (this.casing.getSelectedValue().equals("LowerCase")) {
                  str6 = this.lowerCaseClientName;
               } else {
                  str6 = Augustus.getInstance().getName();
               }

               this.lastSimpleFontSize = n11;
               int colorInt6 = this.color2.getColorInt((byte)0);
               BlurShadowManager.enableOneShotPass(this.hud.isBlurActive(), this.hud.isShadowActive());
               float stringWidth5 = FontManager.rubikBoldHud.getStringWidth(str6);
               float f3 = FontManager.rubikBoldHud.getStringHeight(str6) * 1.12F;
               if (flag) {
                  if (this.backGround.getValue()) {
                     int colorInt7 = this.backgroundColor.getColorInt((byte)0);
                     float f4 = f3;
                     float f5 = stringWidth5;
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass8 -> {
                        int n14 = this.hud.resolvePassColor(shaderRenderPass8, ColorUtil.resolvePassColor(shaderRenderPass8, colorInt7));
                        if (this.roundedBackGround.getValue()) {
                           RenderUtil.drawRoundedRect(0.0F, 0.0F, f5 + 6.0F, f4, (float)this.roundFactor.getValue(), 1, n14);
                        } else {
                           RenderUtil.drawRect(0.0F, 0.0F, f5 + 6.0F, f4, n14);
                        }
                     });
                  }

                  BlurShadowManager.enableOneShotPass(false, this.hud.isShadowActive() && !this.backGround.getValue());
                  String str7 = str6;
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass9 -> this.renderSimpleLeftText(str7, colorInt6, shaderRenderPass9));
               } else {
                  if (this.backGround.getValue()) {
                     int colorInt8 = this.backgroundColor.getColorInt((byte)0);
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass10 -> this.renderSimpleRightBackground(colorInt8, stringWidth5, f3, shaderRenderPass10));
                  }

                  BlurShadowManager.enableOneShotPass(false, this.hud.isShadowActive() && !this.backGround.getValue());
                  String str8 = str6;
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass11 -> {
                     DynamicFontUtil rubikBoldHud = FontManager.rubikBoldHud;
                     double d12 = (double)((float)mc.displayWidth - stringWidth5 - 3.0F);
                     int n17 = this.hud.resolvePassColor(shaderRenderPass11, colorInt6);
                     rubikBoldHud.drawString(str8, d12, 0.0, n17);
                  });
               }

               this.updateArrayListOffset((double)f3 + (double)f3 / 1.25, 1.0);
               break;
            default:
               double d18 = 1.0;
               this.updateArrayListOffset(0.0, d18);
         }

         GL11.glPopMatrix();
      } else {
         double d20 = 1.0;
         this.updateArrayListOffset(0.0, d20);
      }

      RenderUtil.resetGlColorState();
   }

   private boolean isImageLogoMode() {
      return this.mode.getSelectedValue().equals("Augustus")
         || this.mode.getSelectedValue().equals("Amogus")
         || this.mode.getSelectedValue().equals("Logo")
         || this.mode.getSelectedValue().equals("Fanart");
   }

   private void updateArrayListOffset(double d, double d2) {
      if (!this.side.getSelectedValue().equals(ClientManagers.moduleManager.arrayList.side.getSelectedValue())) {
         d = 0.0;
      }

      GuiIngameHook.hudTopOffsetY = (float)((int)((float)(d * d2)) + (d != 0.0 ? 1 : 0));
   }

   private void renderSimpleRightBackground(int n, float f, float f2, ShaderRenderPass shaderRenderPass) {
      int n3 = this.hud.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n));
      if (this.roundedBackGround.getValue()) {
         RenderUtil.drawRoundedRect(
            (float)mc.displayWidth - f - 6.0F, 0.0F, (float)mc.displayWidth, f2, (float)this.roundFactor.getValue(), 1, n3
         );
      } else {
         RenderUtil.drawRect((float)mc.displayWidth - f - 6.0F, 0.0F, (float)mc.displayWidth, f2, n3);
      }
   }

   private void renderSimpleLeftText(String str, int n, ShaderRenderPass shaderRenderPass) {
      DynamicFontUtil rubikBoldHud = FontManager.rubikBoldHud;
      double d = (double)(-1.0F * ((float)this.lastSimpleFontSize / 80.0F));
      int n2 = this.hud.resolvePassColor(shaderRenderPass, n);
      rubikBoldHud.drawString(str, 3.0, d, n2);
   }

   private void renderXaveRightText(ScaledResolution sr, int n, ShaderRenderPass shaderRenderPass) {
      CustomFontUtil esp60 = FontManager.esp60;
      String upperCaseClientName2 = this.upperCaseClientName;
      float scaledWidth = (float)sr.getScaledWidth();
      String upperCaseClientName3 = this.upperCaseClientName;
      double d = (double)(scaledWidth - FontManager.esp60.getStringWidth(upperCaseClientName3));
      int n2 = this.hud.resolvePassColor(shaderRenderPass, n);
      esp60.drawStringWithShadow(upperCaseClientName2, d, 4.0, n2);
   }

   private void renderOtherRightBackground(int n, ScaledResolution sr, int n2, int n3, FontRenderer fontRenderer, ShaderRenderPass shaderRenderPass) {
      int n5 = this.hud.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n));
      if (this.roundedBackGround.getValue()) {
         RenderUtil.drawRoundedRect(
            (float)((int)((float)sr.getScaledWidth() / 1.5F - (float)((int)((float)n2 * 1.75F + (float)n3 + 4.0F)))),
            0.0F,
            (float)((int)((float)sr.getScaledWidth() / 1.5F) + 1),
            (float)((int)((float)fontRenderer.FONT_HEIGHT * 1.75F + 3.0F)),
            (float)this.roundFactor.getValue(),
            1,
            n5
         );
      } else {
         RenderUtil.drawRect(
            (int)((float)sr.getScaledWidth() / 1.5F - (float)((int)((float)n2 * 1.75F + (float)n3 + 4.0F))),
            0,
            (int)((float)sr.getScaledWidth() / 1.5F) + 1,
            (int)((float)fontRenderer.FONT_HEIGHT * 1.75F + 3.0F),
            n5
         );
      }
   }

   private void renderOtherLeftBackground(int n, int n2, int n3, FontRenderer fontRenderer, ShaderRenderPass shaderRenderPass) {
      int n5 = this.hud.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n));
      if (this.roundedBackGround.getValue()) {
         float value = (float)this.roundFactor.getValue();
         RenderUtil.drawRoundedRect(
            0.0F,
            0.0F,
            (float)((int)((float)n2 * 1.75F + (float)n3 + 4.0F)),
            (float)((int)((float)fontRenderer.FONT_HEIGHT * 1.75F + 3.0F)),
            value,
            1,
            n5
         );
      } else {
         RenderUtil.drawRect(0, 0, (int)((float)n2 * 1.75F + (float)n3 + 4.0F), (int)((float)fontRenderer.FONT_HEIGHT * 1.75F + 3.0F), n5);
      }
   }

   private boolean isBackgroundColorVisible() {
      return this.backGround.getValue() && this.backGround.isVisible();
   }

   private boolean isRoundFactorVisible() {
      return this.backGround.getValue() && this.backGround.isVisible() && this.roundedBackGround.getValue();
   }

   private boolean isRoundedBackgroundVisible() {
      return this.backGround.getValue() && this.backGround.isVisible();
   }

   private boolean isTextColorVisible() {
      return this.mode.getSelectedValue().equals("Ausustus")
         || this.mode.getSelectedValue().equals("Other")
         || this.mode.getSelectedValue().equals("Basic")
         || this.mode.getSelectedValue().equals("Simple")
         || this.mode.getSelectedValue().equals("Xave");
   }

   private boolean isScaleVisible() {
      return !this.mode.getSelectedValue().equals("Augustus")
         && !this.isImageLogoMode()
         && !this.mode.getSelectedValue().equals("None")
         && !this.mode.getSelectedValue().equals("Simple");
   }

   private boolean isPosYVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

   private boolean isPosXVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

   private boolean isSideVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }

}
