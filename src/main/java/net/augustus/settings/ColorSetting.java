package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.clickgui.components.ColorAlphaSliderComponent;
import net.augustus.clickgui.components.ColorBrightnessSliderComponent;
import net.augustus.clickgui.components.ColorHueSliderComponent;
import net.augustus.clickgui.components.ColorSaturationSliderComponent;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.settings.property.DoubleProperty;
import net.augustus.settings.property.IntegerProperty;
import net.augustus.utils.render.ColorUtil;
import java.awt.Color;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.Module;
import net.augustus.modules.render.CustomClientColor;
import net.minecraft.util.MathHelper;

public class ColorSetting extends Setting {
   private final ColorBrightnessSliderComponent primaryBrightnessSlider;
   private final IntegerProperty fa;
   private final BooleanProperty r;
   private final DoubleValue fSpeed;
   private final DoubleValue fDist;
   private final ColorSaturationSliderComponent fadeSaturationSlider;
   private final DoubleValue rSpeed;
   private final ColorSaturationSliderComponent primarySaturationSlider;
   private final BooleanProperty ao;
   private Color fadeColor;
   private final DoubleProperty rd;
   private int renderYOffset;
   private final DoubleValue rDist;
   private final ColorAlphaSliderComponent fadeAlphaSlider;
   private final DoubleValue aSpeed;
   private final DoubleProperty rs;
   private final BooleanValue astolfo;
   private final DoubleProperty ad;
   private final ColorHueSliderComponent fadeHueSlider;
   private final BooleanValue clientColor;
   private final DoubleProperty fs;
   private float[] fadeHsb;
   private final ColorBrightnessSliderComponent fadeBrightnessSlider;
   private final IntegerProperty a;
   private final DoubleProperty fd;
   private final BooleanProperty f;
   private final BooleanProperty cc;
   private final DoubleProperty as;
   private final BooleanValue rainbow;
   private final IntegerProperty cca;
   private final ColorAlphaSliderComponent primaryAlphaSlider;
   private final ColorHueSliderComponent primaryHueSlider;
   private int computedArgb;
   private IntegerProperty c;
   private Color primaryColor;
   private final DoubleValue clientColorAlpha;
   private float[] primaryHsb;
   private static int subPanelOffsetX = 106;
   private final DoubleValue aDist;
   private final BooleanValue fade;
   private final IntegerProperty fc;

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip((int)this.renderFont.getStringWidth(this.getDisplayName() + ": "));
   }

   public void setFadeAlpha(int fadeAlpha) {
      IntegerProperty fa2 = this.fa;
      Integer num = fadeAlpha;
      fa2.setValue(num);
   }

   public void setFadeSpeed(double fadeSpeed) {
      DoubleProperty fs2 = this.fs;
      Double d2 = fadeSpeed;
      fs2.setValue(d2);
      this.fSpeed.setValue(fadeSpeed);
   }

   public void setAstolfoEnabled(boolean astolfoEnabled) {
      BooleanProperty ao2 = this.ao;
      Boolean bool = astolfoEnabled;
      ao2.setValue(bool);
      this.astolfo.setValue(astolfoEnabled);
   }

   public ColorSetting(Module module, String str, int n) {
       this(str, module, null, new Color(n));
   }

   public void setPrimaryAlpha(int primaryAlpha) {
      IntegerProperty integerProperty = this.a;
      Integer num = primaryAlpha;
      integerProperty.setValue(num);
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      if (!(this.getParentModule() instanceof CustomClientColor)) {
         this.clientColor.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
         this.clientColorAlpha.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      }

      this.primaryHueSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.primarySaturationSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.primaryBrightnessSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.primaryAlphaSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.rSpeed.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fSpeed.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fadeHueSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fadeSaturationSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fadeBrightnessSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fadeAlphaSlider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.fDist.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.rDist.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.aSpeed.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.aDist.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
   }

   public boolean isFadeEnabled() {
      return (Boolean)this.f.getValue();
   }

   public int getAlpha() {
      return ColorUtil.getAlpha(this.computedArgb);
   }

   private int computeRainbowArgb(int n) {
      return ColorUtil.getRainbowColor(n, (int)this.getRainbowSpeed(), this.primaryHsb[1], this.primaryHsb[2], this.primaryColor.getAlpha());
   }

   public BooleanValue getRainbow() {
      return this.rainbow;
   }

   public ColorSetting withFadeDefault(boolean fadeDefault) {
      this.setFadeEnabled(fadeDefault);
      BooleanProperty booleanProperty = this.f;
      Boolean bool = fadeDefault;
      booleanProperty.setDefaultValue(bool);
      return this;
   }

   public float getRedFloat() {
      return ColorUtil.getRedFloat(this.computedArgb);
   }

   public void setRainbowEnabled(boolean rainbowEnabled) {
      BooleanProperty booleanProperty = this.r;
      Boolean bool = rainbowEnabled;
      booleanProperty.setValue(bool);
      this.rainbow.setValue(rainbowEnabled);
   }

   private void onFadeAlphaPropertyChanged(int n) {
      Color color = new Color((Integer)this.fc.getValue());
      this.setFadeColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), n));
   }

   private void onPrimaryRgbPropertyChanged(Integer num) {
      Color color = new Color(num);
      this.setPrimaryColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getPrimaryAlpha()));
   }

   public void setRainbowDistance(double rainbowDistance) {
      DoubleProperty rd2 = this.rd;
      Double d2 = rainbowDistance;
      rd2.setValue(d2);
      this.rDist.setValue(rainbowDistance);
   }

   public Color getFadeColor() {
      return this.fadeColor;
   }

   public Color getColor(int n) {
      this.updateComputedColor(n);
      return new Color(this.computedArgb);
   }

   public float getBlueFloat() {
      return ColorUtil.getBlueFloat(this.computedArgb);
   }

   public int getBlue() {
      return ColorUtil.getBlue(this.computedArgb);
   }

   public void setAstolfoDistance(double astolfoDistance) {
      DoubleProperty ad2 = this.ad;
      Double d2 = astolfoDistance;
      ad2.setValue(d2);
      this.aDist.setValue(astolfoDistance);
   }

   public void setClientColorAlpha(int n) {
      IntegerProperty cca2 = this.cca;
      Integer num = n;
      cca2.setValue(num);
      double d = (double)n;
      this.clientColorAlpha.setValue(d);
   }

   public DoubleValue getFSpeed() {
      return this.fSpeed;
   }

   public boolean isRainbowEnabled() {
      return (Boolean)this.r.getValue();
   }

   public int getRed() {
      return ColorUtil.getRed(this.computedArgb);
   }

   @Override
   public void resetToDefault() {
      this.a.resetToDefault();
      this.fa.resetToDefault();
      this.r.resetToDefault();
      this.f.resetToDefault();
      this.ao.resetToDefault();
      this.cc.resetToDefault();
      this.fc.resetToDefault();
      this.cca.resetToDefault();
      this.rs.resetToDefault();
      this.rd.resetToDefault();
      this.fs.resetToDefault();
      this.fd.resetToDefault();
      this.as.resetToDefault();
      this.ad.resetToDefault();
      this.c.resetToDefault();
   }

   public double getAstolfoSpeed() {
      return (Double)this.as.getValue();
   }

   public int getColorInt(int n) {
      this.updateComputedColor(n);
      return this.computedArgb;
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.primaryHueSlider.mouseClicked(mouseX, mouseY, mouseButton);
      this.primarySaturationSlider.mouseClicked(mouseX, mouseY, mouseButton);
      this.primaryBrightnessSlider.mouseClicked(mouseX, mouseY, mouseButton);
      this.primaryAlphaSlider.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.isRainbowEnabled()) {
         this.rSpeed.mouseClicked(mouseX, mouseY, mouseButton);
         this.rDist.mouseClicked(mouseX, mouseY, mouseButton);
      }

      if (!this.isFadeEnabled() && !this.isRainbowEnabled()) {
         if (!this.isClientColorEnabled()) {
            this.astolfo.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      if (!this.isAstolfoEnabled() && !this.isRainbowEnabled()) {
         if (!this.isClientColorEnabled()) {
            this.fade.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      if (!this.isFadeEnabled() && !this.isAstolfoEnabled()) {
         if (!this.isClientColorEnabled()) {
            this.rainbow.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      if (!this.isFadeEnabled() && !this.isAstolfoEnabled() && !this.isRainbowEnabled() && !(this.getParentModule() instanceof CustomClientColor)) {
         this.clientColor.mouseClicked(mouseX, mouseY, mouseButton);
      }

      if (this.isFadeEnabled() && !this.isRainbowEnabled()) {
         this.fSpeed.mouseClicked(mouseX, mouseY, mouseButton);
         this.fDist.mouseClicked(mouseX, mouseY, mouseButton);
         this.fadeHueSlider.mouseClicked(mouseX, mouseY, mouseButton);
         this.fadeSaturationSlider.mouseClicked(mouseX, mouseY, mouseButton);
         this.fadeBrightnessSlider.mouseClicked(mouseX, mouseY, mouseButton);
         this.fadeAlphaSlider.mouseClicked(mouseX, mouseY, mouseButton);
      }

      if (this.isAstolfoEnabled() && !this.isRainbowEnabled() && !this.isFadeEnabled()) {
         this.aSpeed.mouseClicked(mouseX, mouseY, mouseButton);
         this.aDist.mouseClicked(mouseX, mouseY, mouseButton);
      }

      if (this.isClientColorEnabled() && !(this.getParentModule() instanceof CustomClientColor)) {
         this.clientColorAlpha.mouseClicked(mouseX, mouseY, mouseButton);
      }

      this.notifyValueChanged();
      this.notifyUserChanged();
      return false;
   }

   public void updateComputedColor(int n) {
      if (this.isRainbowEnabled()) {
         this.computedArgb = this.computeRainbowArgb((int)this.getRainbowDistance() * n);
      } else if (this.isFadeEnabled()) {
         this.computedArgb = this.computeFadeArgb((int)(this.getFadeDistance() * (double)n));
      } else if (this.isAstolfoEnabled()) {
         this.computedArgb = this.computeAstolfoArgb((int)(this.getAstolfoDistance() * (double)n));
      } else {
         if (this.isClientColorEnabled()) {
            this.computedArgb = ColorUtil.withAlpha(Augustus.getInstance().getClientColor(), this.getClientColorAlphaValue());
         } else {
            this.computedArgb = this.primaryColor.getRGB();
         }
      }
   }

   public ColorSetting withRainbowDefault(boolean rainbowDefault) {
      this.setRainbowEnabled(rainbowDefault);
      BooleanProperty booleanProperty = this.r;
      Boolean bool = rainbowDefault;
      booleanProperty.setDefaultValue(bool);
      return this;
   }

   public ColorSetting withDefaultPrimaryColor(Color color) {
      this.setPrimaryColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
      IntegerProperty integerProperty = this.c;
      Integer rgb = color.getRGB();
      integerProperty.setDefaultValue(rgb);
      return this;
   }

   public ColorSaturationSliderComponent getFadeSaturationSlider() {
      return this.fadeSaturationSlider;
   }


   public BooleanValue getAstolfo() {
      return this.astolfo;
   }

   public ColorAlphaSliderComponent getFadeAlphaSlider() {
      return this.fadeAlphaSlider;
   }

   public float[] getPrimaryHsb() {
      return this.primaryHsb;
   }

   public ColorSaturationSliderComponent getPrimarySaturationSlider() {
      return this.primarySaturationSlider;
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      if (flag) {
         this.renderYOffset = 0;
         short s = 200;
         byte b = 20;
         String str = this.getDisplayName() + ": ";
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         double d = (double)n3;
         customFontUtil.drawString(str, d, (double)n4, colorInt);
         int colorInt2 = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
         this.renderYOffset += 25;
         float[] primaryHsb2 = this.primaryHsb;
         ColorHueSliderComponent primaryHueSlider2 = this.primaryHueSlider;
         int n7 = n4 - 2 + this.renderYOffset;
         double d4 = (double)this.primaryHsb[0];
         float[] primaryHsb3 = this.primaryHsb;
         int primaryAlpha = this.getPrimaryAlpha();
         float[] floats = primaryHsb3;
         double d5 = 1.0;
         primaryHsb2[0] = (float)primaryHueSlider2.drawHueSlider(n3, n7, s, b, n, n2, d4, 0.0, d5, (byte)7, customFontUtil, colorInt2, floats, primaryAlpha);
         this.renderYOffset += 25;
         primaryHsb2 = this.primaryHsb;
         ColorSaturationSliderComponent component = this.primarySaturationSlider;
         n7 = n4 - 2 + this.renderYOffset;
         double d8 = (double)this.primaryHsb[1];
         primaryHsb3 = this.primaryHsb;
         int primaryAlpha2 = this.getPrimaryAlpha();
         float[] floats2 = primaryHsb3;
         double d9 = 1.0;
         primaryHsb2[1] = (float)component.drawSaturationSlider(n3, n7, s, b, n, n2, d8, 0.0, d9, (byte)7, customFontUtil, colorInt2, floats2, primaryAlpha2);
         this.renderYOffset += 25;
         primaryHsb2 = this.primaryHsb;
         ColorBrightnessSliderComponent component2 = this.primaryBrightnessSlider;
         n7 = n4 - 2 + this.renderYOffset;
         d4 = (double)this.primaryHsb[2];
         primaryHsb3 = this.primaryHsb;
         int primaryAlpha3 = this.getPrimaryAlpha();
         float[] floats3 = primaryHsb3;
         double d12 = 1.0;
         primaryHsb2[2] = (float)component2.drawBrightnessSlider(n3, n7, s, b, n, n2, d4, 0.0, d12, (byte)7, customFontUtil, colorInt2, floats3, primaryAlpha3);
         this.renderYOffset += 25;
         ColorAlphaSliderComponent primaryAlphaSlider2 = this.primaryAlphaSlider;
         int n11 = n4 - 2 + this.renderYOffset;
         double primaryAlpha4 = (double)this.getPrimaryAlpha();
         float[] primaryHsb4 = this.primaryHsb;
         double d15 = 255.0;
         int n13 = (int)primaryAlphaSlider2.drawAlphaSlider(n3, n11, s, b, n, n2, primaryAlpha4, 0.0, d15, (byte)7, customFontUtil, colorInt2, primaryHsb4);
         int n14;
         this.setPrimaryAlpha(n13);
         this.renderYOffset += 27;
         Color color = new Color(Color.HSBtoRGB(this.primaryHsb[0], this.primaryHsb[1], this.primaryHsb[2]));
         this.primaryColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getPrimaryAlpha());
         int n16 = s + 40;
         int n17 = 25;
         n14 = n3 + n16;
         n7 = n4 + n17;
         int n18 = n7;
         int n19 = n14;
         this.rainbow.render(n, n2, n19, n18, n5, n6, customFontUtil, true);
         this.setRainbowEnabled(this.rainbow.getValue());
         n17 += 25;
         if (this.isRainbowEnabled()) {
            n14 = n3 + n16;
            n7 = n4 + n17;
            int n20 = n7;
            int n21 = n14;
            this.rSpeed.render(n, n2, n21, n20, n5, n6, customFontUtil, true);
            this.setRainbowSpeed(this.rSpeed.getValue());
            n17 += 25;
            n14 = n3 + n16;
            n7 = n4 + n17;
            int n22 = n7;
            int n23 = n14;
            this.rDist.render(n, n2, n23, n22, n5, n6, customFontUtil, true);
            this.setRainbowDistance(this.rDist.getValue());
            n17 += 25;
         }

         n14 = n3 + n16;
         n7 = n4 + n17;
         int n24 = n6 + 50;
         int n25 = n24;
         int n26 = n7;
         int n27 = n14;
         this.fade.render(n, n2, n27, n26, n5, n25, customFontUtil, true);
         this.setFadeEnabled(this.fade.getValue());
         n17 += 25;
         if (this.isFadeEnabled() && !this.isRainbowEnabled()) {
            if (!this.isClientColorEnabled()) {
               n14 = n3 + n16;
               n7 = n4 + n17;
               int n28 = n7;
               int n29 = n14;
               this.fSpeed.render(n, n2, n29, n28, n5, n6, customFontUtil, true);
               this.setFadeSpeed(this.fSpeed.getValue());
               n17 += 25;
               n14 = n3 + n16;
               n7 = n4 + n17;
               int n30 = n7;
               int n31 = n14;
               this.fDist.render(n, n2, n31, n30, n5, n6, customFontUtil, true);
               this.setFadeDistance(this.fDist.getValue());
               int n32 = 0;
               n16 += s + 100;
               d = (double)(n3 + n16);
               double d18 = (double)(n4 + n32);
               double d19 = d;
               customFontUtil.drawString("FadeColor:", d19, d18, colorInt);
               n32 += 25;
               primaryHsb2 = this.fadeHsb;
               ColorHueSliderComponent fadeHueSlider2 = this.fadeHueSlider;
               n14 = n3 + n16;
               n7 = n4 - 2 + n32;
               d4 = (double)this.fadeHsb[0];
               primaryHsb3 = this.fadeHsb;
               int fadeAlpha = this.getFadeAlpha();
               float[] floats4 = primaryHsb3;
               double d20 = 1.0;
               primaryHsb2[0] = (float)fadeHueSlider2.drawHueSlider(n14, n7, s, b, n, n2, d4, 0.0, d20, (byte)7, customFontUtil, colorInt2, floats4, fadeAlpha);
               n32 += 25;
               primaryHsb2 = this.fadeHsb;
               ColorSaturationSliderComponent fadeSaturationSlider2 = this.fadeSaturationSlider;
               n14 = n3 + n16;
               n7 = n4 - 2 + n32;
               d8 = (double)this.fadeHsb[1];
               primaryHsb3 = this.fadeHsb;
               int fadeAlpha2 = this.getFadeAlpha();
               float[] floats5 = primaryHsb3;
               double d23 = 1.0;
               primaryHsb2[1] = (float)fadeSaturationSlider2.drawSaturationSlider(n14, n7, s, b, n, n2, d8, 0.0, d23, (byte)7, customFontUtil, colorInt2, floats5, fadeAlpha2);
               n32 += 25;
               primaryHsb2 = this.fadeHsb;
               ColorBrightnessSliderComponent fadeBrightnessSlider2 = this.fadeBrightnessSlider;
               n14 = n3 + n16;
               n7 = n4 - 2 + n32;
               d4 = (double)this.fadeHsb[2];
               primaryHsb3 = this.fadeHsb;
               int fadeAlpha3 = this.getFadeAlpha();
               float[] floats6 = primaryHsb3;
               double d26 = 1.0;
               primaryHsb2[2] = (float)fadeBrightnessSlider2.drawBrightnessSlider(n14, n7, s, b, n, n2, d4, 0.0, d26, (byte)7, customFontUtil, colorInt2, floats6, fadeAlpha3);
               n32 += 25;
               ColorAlphaSliderComponent fadeAlphaSlider2 = this.fadeAlphaSlider;
               int n39 = n3 + n16;
               n14 = n4 - 2 + n32;
               primaryAlpha4 = (double)this.getFadeAlpha();
               float[] fadeHsb2 = this.fadeHsb;
               double d29 = 255.0;
               this.setFadeAlpha((int)fadeAlphaSlider2.drawAlphaSlider(n39, n14, s, b, n, n2, primaryAlpha4, 0.0, d29, (byte)7, customFontUtil, colorInt2, fadeHsb2));
               n17 = 0;
               n16 += s + 50;
               Color color2 = new Color(Color.HSBtoRGB(this.fadeHsb[0], this.fadeHsb[1], this.fadeHsb[2]));
               this.fadeColor = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), this.getFadeAlpha());
            }
         }

         if (this.isRainbowEnabled()) {
            n16 += s + 100;
            n17 = 25;
         }

         n14 = n3 + n16;
         n7 = n4 + n17;
         n24 = n6 + 50;
         int n43 = n24;
         int n44 = n7;
         int n45 = n14;
         this.astolfo.render(n, n2, n45, n44, n5, n43, customFontUtil, true);
         this.setAstolfoEnabled(this.astolfo.getValue());
         if (this.isAstolfoEnabled() && !this.isRainbowEnabled() && !this.isFadeEnabled()) {
            if (!this.isClientColorEnabled()) {
               n16 += 50 + subPanelOffsetX;
               int n46 = 25;
               n14 = n3 + n16;
               n7 = n4 + n46;
               int n47 = n7;
               int n48 = n14;
               this.aSpeed.render(n, n2, n48, n47, n5, n6, customFontUtil, true);
               this.setAstolfoSpeed(this.aSpeed.getValue());
               n46 += 25;
               n14 = n3 + n16;
               n7 = n4 + n46;
               int n49 = n7;
               int n50 = n14;
               this.aDist.render(n, n2, n50, n49, n5, n6, customFontUtil, true);
               this.setAstolfoDistance(this.aDist.getValue());
               n17 = n46 + 25;
               n16 -= 50 + subPanelOffsetX;
            }
         }

         if (!(this.getParentModule() instanceof CustomClientColor)) {
            n17 += 25;
            n14 = n3 + n16;
            n7 = n4 + n17;
            n24 = n6 + 50;
            int n51 = n24;
            int n52 = n7;
            int n53 = n14;
            this.clientColor.render(n, n2, n53, n52, n5, n51, customFontUtil, true);
            this.setClientColorEnabled(this.clientColor.getValue());
            if (this.isClientColorEnabled()) {
               n17 = 25;
               n16 += 50 + subPanelOffsetX;
            }

            if (this.isClientColorEnabled()) {
               n14 = n3 + n16;
               n7 = n4 + n17;
               n24 = n6 + 50;
               int n54 = n24;
               int n55 = n7;
               int n56 = n14;
               this.clientColorAlpha.render(n, n2, n56, n55, n5, n54, customFontUtil, true);
               this.setClientColorAlpha(MathHelper.clamp_int((int)this.clientColorAlpha.getValue(), 1, 255));
            }
         } else {
            this.setClientColorEnabled(false);
         }

         this.contentWidth = n16 + s + 20;
         RenderUtil.resetGlColorState();
      }
   }

   private ColorSetting(String str, Module module, SettingGroup doubleValue, Color color) {
       super(module, doubleValue, str);
      IntegerProperty integerProperty = new IntegerProperty(this, "A", 0);
      Consumer consumer = obj -> {
         Color color2 = new Color((Integer)this.c.getValue());
         this.setPrimaryColor(new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), (Integer)obj));
      };
      this.a = (IntegerProperty)integerProperty.withOnValueChanged(consumer);
      this.fa = (IntegerProperty)new IntegerProperty(this, "FA", 0).withOnValueChanged(obj2 -> this.onFadeAlphaPropertyChanged((int)obj2));
      this.r = (BooleanProperty)new BooleanProperty(this, "R", false).withOnValueChanged(obj3 -> this.setRainbowEnabled((boolean)obj3));
      this.f = (BooleanProperty)new BooleanProperty(this, "F", false).withOnValueChanged(obj4 -> this.setFadeEnabled((boolean)obj4));
      this.ao = (BooleanProperty)new BooleanProperty(this, "AO", false).withOnValueChanged(obj5 -> this.setAstolfoEnabled((boolean)obj5));
      this.cc = (BooleanProperty)new BooleanProperty(this, "CC", false).withOnValueChanged(obj6 -> this.setClientColorEnabled((boolean)obj6));
      IntegerProperty integerProperty3 = new IntegerProperty(this, "FC", 0);
      Supplier supplier = () -> {
         return this.toOpaqueRgb(this.fadeColor);
      };
      this.fc = (IntegerProperty)((IntegerProperty)integerProperty3.withValueSupplier(supplier)).withOnValueChanged(obj7 -> this.onFadeRgbPropertyChanged((Integer)obj7));
      this.cca = (IntegerProperty)new IntegerProperty(this, "CCA", 100).withOnValueChanged(obj8 -> this.setClientColorAlpha((int)obj8));
      this.rs = (DoubleProperty)new DoubleProperty(this, "RS", 0.0).withOnValueChanged(obj9 -> this.setRainbowSpeed((double)obj9));
      this.rd = (DoubleProperty)new DoubleProperty(this, "RD", 0.0).withOnValueChanged(obj10 -> this.setRainbowDistance((double)obj10));
      this.fs = (DoubleProperty)new DoubleProperty(this, "FS", 0.0).withOnValueChanged(obj11 -> this.setFadeSpeed((double)obj11));
      this.fd = (DoubleProperty)new DoubleProperty(this, "FD", 0.0).withOnValueChanged(obj12 -> this.setFadeDistance((double)obj12));
      this.as = (DoubleProperty)new DoubleProperty(this, "AS", 0.0).withOnValueChanged(obj13 -> this.setAstolfoSpeed((double)obj13));
      this.ad = (DoubleProperty)new DoubleProperty(this, "AD", 0.0).withOnValueChanged(obj14 -> this.setAstolfoDistance((double)obj14));
      IntegerProperty integerProperty6 = new IntegerProperty(this, "C", 0);
      Supplier supplier2 = () -> {
         return this.toOpaqueRgb(this.primaryColor);
      };
      this.c = (IntegerProperty)((IntegerProperty)integerProperty6.withValueSupplier(supplier2)).withOnValueChanged(obj15 -> this.onPrimaryRgbPropertyChanged((Integer)obj15));
      this.primaryHsb = new float[3];
      this.fadeHsb = new float[3];
      this.primaryHueSlider = new ColorHueSliderComponent();
      this.primarySaturationSlider = new ColorSaturationSliderComponent();
      this.primaryBrightnessSlider = new ColorBrightnessSliderComponent();
      this.primaryAlphaSlider = new ColorAlphaSliderComponent();
      this.rainbow = new BooleanValue("Rainbow", false);
      this.fade = new BooleanValue("Fade", false);
      this.astolfo = new BooleanValue("Astolfo", false);
      this.clientColor = new BooleanValue("ClientColor", false);
      this.rSpeed = new DoubleValue("RSpeed", 70.0, 1.0, 200.0, 0);
      this.fSpeed = new DoubleValue("FSpeed", 70.0, 1.0, 200.0, 0);
      this.aSpeed = new DoubleValue("ASpeed", 70.0, 1.0, 200.0, 0);
      this.rDist = new DoubleValue("RDist", 60.0, 0.0, 200.0, 0);
      this.fDist = new DoubleValue("FDist", 60.0, 0.0, 200.0, 0);
      this.aDist = new DoubleValue("ADist", 60.0, 0.0, 200.0, 0);
      this.clientColorAlpha = new DoubleValue("ClientColorAlpha", 100.0, 1.0, 255.0, 0);
      this.fadeHueSlider = new ColorHueSliderComponent();
      this.fadeSaturationSlider = new ColorSaturationSliderComponent();
      this.fadeBrightnessSlider = new ColorBrightnessSliderComponent();
      this.fadeAlphaSlider = new ColorAlphaSliderComponent();
      this.renderYOffset = 0;
      this.computedArgb = 0;
      this.setPrimaryColor(color);
      this.setFadeColor(color);
      this.setFadeAlpha(this.fadeColor.getAlpha());
      this.setPrimaryAlpha(this.primaryColor.getAlpha());
      IntegerProperty integerProperty8 = this.c;
      Integer rgb = this.primaryColor.getRGB();
      integerProperty8.setValue(rgb);
      integerProperty8 = this.c;
      Integer rgb2 = this.primaryColor.getRGB();
      integerProperty8.setDefaultValue(rgb2);
      integerProperty8 = this.fc;
      Integer rgb3 = this.primaryColor.getRGB();
      integerProperty8.setValue(rgb3);
      integerProperty8 = this.fc;
      Integer rgb4 = this.primaryColor.getRGB();
      integerProperty8.setDefaultValue(rgb4);
      integerProperty8 = this.a;
      Integer alpha3 = this.primaryColor.getAlpha();
      integerProperty8.setDefaultValue(alpha3);
      integerProperty8 = this.fa;
      Integer alpha4 = this.primaryColor.getAlpha();
      integerProperty8.setDefaultValue(alpha4);
      Color.RGBtoHSB(this.primaryColor.getRed(), this.primaryColor.getGreen(), this.primaryColor.getBlue(), this.primaryHsb);
      Color.RGBtoHSB(this.primaryColor.getRed(), this.primaryColor.getGreen(), this.primaryColor.getBlue(), this.fadeHsb);
   }

   public DoubleValue getASpeed() {
      return this.aSpeed;
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();
   }

   public ColorBrightnessSliderComponent getPrimaryBrightnessSlider() {
      return this.primaryBrightnessSlider;
   }

   private int computeFadeArgb(int n) {
      return ColorUtil.getAnimatedFadeColor(this.primaryColor, this.fadeColor, n, (int)this.getFadeSpeed());
   }

   public void setAstolfoSpeed(double astolfoSpeed) {
      DoubleProperty as2 = this.as;
      Double d2 = astolfoSpeed;
      as2.setValue(d2);
      this.aSpeed.setValue(astolfoSpeed);
   }

   public ColorSetting withDefaultFadeColor(Color color) {
      this.setFadeColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
      IntegerProperty fc2 = this.fc;
      Integer rgb = color.getRGB();
      fc2.setDefaultValue(rgb);
      return this;
   }

   private int computeAstolfoArgb(int n) {
      return ColorUtil.getAstolfoColor(n, (int)this.getAstolfoSpeed(), this.primaryColor.getAlpha());
   }

   public ColorSetting withDefaultFadeSpeed(int defaultFadeSpeed) {
      this.setFadeSpeed((double)defaultFadeSpeed);
      DoubleProperty fs2 = this.fs;
      Double d2 = (double)defaultFadeSpeed;
      fs2.setDefaultValue(d2);
      return this;
   }

   public ColorHueSliderComponent getPrimaryHueSlider() {
      return this.primaryHueSlider;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (!(this.getParentModule() instanceof CustomClientColor)) {
         this.clientColor.mouseReleased(mouseX, mouseY, state);
         this.clientColorAlpha.mouseReleased(mouseX, mouseY, state);
      }

      this.primaryHueSlider.mouseReleased(mouseX, mouseY, state);
      this.primarySaturationSlider.mouseReleased(mouseX, mouseY, state);
      this.primaryBrightnessSlider.mouseReleased(mouseX, mouseY, state);
      this.primaryAlphaSlider.mouseReleased(mouseX, mouseY, state);
      this.rSpeed.mouseReleased(mouseX, mouseY, state);
      this.fSpeed.mouseReleased(mouseX, mouseY, state);
      this.fadeHueSlider.mouseReleased(mouseX, mouseY, state);
      this.fadeSaturationSlider.mouseReleased(mouseX, mouseY, state);
      this.fadeBrightnessSlider.mouseReleased(mouseX, mouseY, state);
      this.fadeAlphaSlider.mouseReleased(mouseX, mouseY, state);
      this.fDist.mouseReleased(mouseX, mouseY, state);
      this.rDist.mouseReleased(mouseX, mouseY, state);
      this.aSpeed.mouseReleased(mouseX, mouseY, state);
      this.aDist.mouseReleased(mouseX, mouseY, state);
   }

   public int getGreen() {
      return ColorUtil.getGreen(this.computedArgb);
   }

   public boolean isAstolfoEnabled() {
      return (Boolean)this.ao.getValue();
   }

   public float getAlphaFloat() {
      return ColorUtil.getAlphaFloat(this.computedArgb);
   }

   public void setFadeEnabled(boolean fadeEnabled) {
      BooleanProperty booleanProperty = this.f;
      Boolean bool = fadeEnabled;
      booleanProperty.setValue(bool);
      this.fade.setValue(fadeEnabled);
   }

   public ColorSetting withDefaultClientColorAlpha(int defaultClientColorAlpha) {
      this.setClientColorAlpha(defaultClientColorAlpha);
      IntegerProperty cca2 = this.cca;
      Integer num = defaultClientColorAlpha;
      cca2.setDefaultValue(num);
      return this;
   }

   public DoubleValue getClientColorAlpha() {
      return this.clientColorAlpha;
   }

   public DoubleValue getFDist() {
      return this.fDist;
   }

   public ColorHueSliderComponent getFadeHueSlider() {
      return this.fadeHueSlider;
   }

   public ColorSetting withDefaultFadeDistance(int defaultFadeDistance) {
      this.setFadeDistance((double)defaultFadeDistance);
      DoubleProperty fd2 = this.fd;
      Double d2 = (double)defaultFadeDistance;
      fd2.setDefaultValue(d2);
      return this;
   }

   public int getPrimaryAlpha() {
      return (Integer)this.a.getValue();
   }

   public int getFadeAlpha() {
      return (Integer)this.fa.getValue();
   }

   public ColorAlphaSliderComponent getPrimaryAlphaSlider() {
      return this.primaryAlphaSlider;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (!(this.getParentModule() instanceof CustomClientColor)) {
         this.clientColor.keyTyped(typedChar, keyCode);
         this.clientColorAlpha.keyTyped(typedChar, keyCode);
      }

      this.primaryHueSlider.keyTyped(typedChar, keyCode);
      this.primarySaturationSlider.keyTyped(typedChar, keyCode);
      this.primaryBrightnessSlider.keyTyped(typedChar, keyCode);
      this.primaryAlphaSlider.keyTyped(typedChar, keyCode);
      this.rSpeed.keyTyped(typedChar, keyCode);
      this.fSpeed.keyTyped(typedChar, keyCode);
      this.fadeHueSlider.keyTyped(typedChar, keyCode);
      this.fadeSaturationSlider.keyTyped(typedChar, keyCode);
      this.fadeBrightnessSlider.keyTyped(typedChar, keyCode);
      this.fadeAlphaSlider.keyTyped(typedChar, keyCode);
      this.fDist.keyTyped(typedChar, keyCode);
      this.rDist.keyTyped(typedChar, keyCode);
      this.aSpeed.keyTyped(typedChar, keyCode);
      this.aDist.keyTyped(typedChar, keyCode);
   }

   public ColorSetting(Module module, String str, int n, SettingGroup doubleValue) {
       this(str, module, doubleValue, new Color(n));
   }

   public int getClientColorAlphaValue() {
      return (Integer)this.cca.getValue();
   }

   public double getFadeDistance() {
      return (Double)this.fd.getValue();
   }

   public DoubleValue getADist() {
      return this.aDist;
   }

   public void setRainbowSpeed(double rainbowSpeed) {
      DoubleProperty rs2 = this.rs;
      Double d2 = rainbowSpeed;
      rs2.setValue(d2);
      this.rSpeed.setValue(rainbowSpeed);
   }

   public ColorSetting(Module module, String str, Color color, SettingGroup doubleValue) {
       this(str, module, doubleValue, color);
   }

   private void onFadeRgbPropertyChanged(Integer num) {
      Color color = new Color(num);
      this.setFadeColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getFadeAlpha()));
   }

   public ColorSetting withDefaultRainbowSpeed(int defaultRainbowSpeed) {
      this.setRainbowSpeed((double)defaultRainbowSpeed);
      DoubleProperty rs2 = this.rs;
      Double d2 = (double)defaultRainbowSpeed;
      rs2.setDefaultValue(d2);
      return this;
   }

   public ColorSetting withClientColorDefault(boolean clientColorDefault) {
      this.setClientColorEnabled(clientColorDefault);
      BooleanProperty cc2 = this.cc;
      Boolean bool = clientColorDefault;
      cc2.setDefaultValue(bool);
      return this;
   }

   public void setPrimaryColor(Color color) {
      this.primaryColor = color;
      this.setPrimaryAlpha(this.primaryColor.getAlpha());
      Color.RGBtoHSB(this.primaryColor.getRed(), this.primaryColor.getGreen(), this.primaryColor.getBlue(), this.primaryHsb);
   }

   public double getFadeSpeed() {
      return (Double)this.fs.getValue();
   }

   public boolean isClientColorEnabled() {
      return (Boolean)this.cc.getValue();
   }

   @Override
   public int getHeight() {
      return 127;
   }

   public Color getPrimaryColor() {
      return this.primaryColor;
   }

   public BooleanValue getFade() {
      return this.fade;
   }

   public double getRainbowSpeed() {
      return (Double)this.rs.getValue();
   }

   public void setFadeDistance(double fadeDistance) {
      DoubleProperty fd2 = this.fd;
      Double d2 = fadeDistance;
      fd2.setValue(d2);
      this.fDist.setValue(fadeDistance);
   }

   public DoubleValue getRDist() {
      return this.rDist;
   }

   public double getAstolfoDistance() {
      return (Double)this.ad.getValue();
   }

   public int getRenderYOffset() {
      return this.renderYOffset;
   }

   public DoubleValue getRSpeed() {
      return this.rSpeed;
   }

   public double getRainbowDistance() {
      return (Double)this.rd.getValue();
   }

   public ColorBrightnessSliderComponent getFadeBrightnessSlider() {
      return this.fadeBrightnessSlider;
   }

   public ColorSetting(Module module, String str, Color color) {
       this(str, module, null, color);
   }

   public void setFadeHsb(float[] floats) {
      this.fadeHsb = floats;
   }

   public void setPrimaryHsb(float[] floats) {
      this.primaryHsb = floats;
   }

   public float[] getFadeHsb() {
      return this.fadeHsb;
   }

   public float getGreenFloat() {
      return ColorUtil.getGreenFloat(this.computedArgb);
   }

   public void setClientColorEnabled(boolean clientColorEnabled) {
      BooleanProperty cc2 = this.cc;
      Boolean bool = clientColorEnabled;
      cc2.setValue(bool);
      this.clientColor.setValue(clientColorEnabled);
   }

   private int toOpaqueRgb(Color color) {
      return new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB();
   }

   public BooleanValue getClientColor() {
      return this.clientColor;
   }

   public void setFadeColor(Color color) {
      this.fadeColor = color;
      this.setFadeAlpha(this.fadeColor.getAlpha());
      Color.RGBtoHSB(this.fadeColor.getRed(), this.fadeColor.getGreen(), this.fadeColor.getBlue(), this.fadeHsb);
   }

}
