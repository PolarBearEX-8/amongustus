package net.augustus.settings;

import java.io.IOException;
import java.util.Objects;
import net.augustus.modules.Module;
import net.augustus.clickgui.components.SliderComponent;
import net.augustus.clickgui.components.TextFieldComponent;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.font.CustomFontUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.property.DoubleProperty;
import net.augustus.font.FontManager;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.minecraft.util.MathHelper;

public class DoubleValue extends Setting {
   private final TextFieldComponent textField;
   private int decimalPlaces;
   private boolean wasArrowAdjusting;
   private long arrowPressStartTimeMs;
   private long lastArrowRepeatTimeMs;
   private boolean scrollAdjusted;
   private final SliderComponent slider;
   private double minValue;
   private int labelWidth;
   private final DoubleProperty v;
   private double lastSyncedValue;
   private boolean arrowAdjusting;
   private double maxValue;

   @Override
   public int getHeight() {
      return 27;
   }

   public DoubleValue visibleWhen(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      customFontUtil = FontManager.consolas30TextField;
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      String str = this.getDisplayName() + ": ";
      this.wasArrowAdjusting = this.arrowAdjusting;
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)customFontUtil.getStringWidth(str);
      }

      if (flag) {
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         int colorInt2 = ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0);
         customFontUtil.drawString(str, (double)n3, (double)n4, colorInt);
         n3 += this.labelWidth;
         int hoveredColor = RenderUtil.getHoveredColor(
            colorInt,
            0.2,
            n,
            n2,
            (int)((float)n3 + (float)(200 - this.textField.getTextWidth()) / 2.0F) - 4,
            n4,
            this.textField.getTextWidth() + 8,
            (int)(customFontUtil.getFontHeight() + 1.0F)
         );
         if (RenderUtil.isHovered((float)this.slider.getX(), (float)this.slider.getY(), (float)this.slider.getSliderWidth(), (float)this.slider.getSliderHeight(), n, n2)) {
            if (this.arrowAdjusting
               && (KeyboardInputUtil.isKeyDown(203) || KeyboardInputUtil.isKeyDown(205))
               && System.currentTimeMillis() - this.arrowPressStartTimeMs >= 400L
               && System.currentTimeMillis() - this.lastArrowRepeatTimeMs >= 50L) {
               double d4 = 1.0 / Math.pow(10.0, (double)Math.min(this.decimalPlaces, 3)) * (double)(KeyboardInputUtil.isKeyDown(203) ? -1 : 1);
               if (this.getValue() + d4 <= this.maxValue) {
                  if (this.getValue() + d4 >= this.minValue) {
                     this.setValue(this.getValue() + d4);
                     this.notifyValueChanged();
                     this.notifyUserChanged();
                  }
               }

               this.lastArrowRepeatTimeMs = System.currentTimeMillis();
            }

            this.scrollAdjusted = false;
            if (KeyboardInputUtil.isCtrlDown()) {
               int lastDelta = Augustus.getInstance().getMouseWheelTracker().lastDelta;
               if (lastDelta != 0) {
                  lastDelta = lastDelta > 0 ? 1 : -1;
                  double d6 = (double)Math.abs(lastDelta) / Math.pow(10.0, (double)Math.min(this.decimalPlaces, 3)) * (double)lastDelta;
                  if (this.getValue() + d6 <= this.maxValue && this.getValue() + d6 >= this.minValue) {
                     this.setValue(this.getValue() + d6);
                     this.notifyValueChanged();
                     this.notifyUserChanged();
                     this.scrollAdjusted = true;
                  }
               }
            }
         }

         if (!KeyboardInputUtil.isKeyDown(203) && !KeyboardInputUtil.isKeyDown(205)) {
            this.arrowAdjusting = false;
         }

         int colorInt3 = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
         double d11 = this.slider.drawSlider(n3, n4 - 2, (short)200, (byte)20, n, n2, this.getValue(), this.minValue, this.maxValue, this.decimalPlaces, customFontUtil, colorInt3, RenderUtil.getHoveredColor(colorInt2, 0.1294, n, n2, n3, n4 - 2, 200, 20));
         if (d11 != this.getValue()) {
            this.notifyValueChanged();
            this.notifyUserChanged();
         }

         this.setValue(d11);
         int n10 = (int)((float)n3 + (float)(200 - this.textField.getTextWidth()) / 2.0F);
         this.textField.drawTextField(n10, n4, n, n2, customFontUtil, hoveredColor);
         if (this.textField.isFocused()) {
            if (!Objects.equals(this.textField.getText(), "")) {
               if (!this.textField.getText().equals("-") && !this.textField.getText().endsWith("-") && !this.textField.getText().endsWith("E")) {
                  Double d12 = this.tryParseDouble(this.textField.getText());
                  if (d12 != null) {
                     double d13 = d12;
                     double d15 = this.roundToDecimals(d13, this.decimalPlaces);
                     if (d15 != this.getValue()) {
                        this.notifyValueChanged();
                        this.notifyUserChanged();
                     }

                     this.setValue(d15);
                  }
               } else {
                  this.setValue(1.7976931348623157E308);
               }
            }
         } else {
            double value2 = this.getValue();
            Double d17;
            if (this.textField.getText() != null && !this.textField.getText().isEmpty()) {
               d17 = this.tryParseDouble(this.textField.getText());
            } else {
               d17 = null;
            }

            Double d18 = d17;
            if (this.lastSyncedValue != value2 || d18 != null && d18 != value2) {
               int n12 = (int)value2;
               if ((double)n12 != value2) {
                  this.textField.setText(String.valueOf(value2));
               } else {
                  this.textField.setText(String.valueOf(n12));
               }
            } else if (this.textField.getText() != null && this.textField.getText().isEmpty()) {
               int lastSyncedValue2 = (int)this.lastSyncedValue;
               if ((double)lastSyncedValue2 != this.lastSyncedValue) {
                  this.textField.setText(String.valueOf(this.lastSyncedValue));
               } else {
                  this.textField.setText(String.valueOf(lastSyncedValue2));
               }
            }

            this.lastSyncedValue = this.getValue();
         }

         this.setFocused(this.textField.isFocused() || this.slider.isDragging());
         this.contentWidth = n3 + 220;
      }
   }

   public TextFieldComponent getTextField() {
      return this.textField;
   }

   public void setValue(double value) {
      DoubleProperty doubleProperty = this.v;
      Double d2 = value;
      doubleProperty.setValue(d2);
   }

   public void setMaxValue(double d) {
      this.maxValue = d;
   }

   public void setMinValue(double d) {
      this.minValue = d;
   }

   private double roundToDecimals(double d, int n) {
      if (n == 0) {
         return (double)((int)d);
      } else {
         double d2 = Math.pow(10.0, (double)n);
         return (double)Math.round(d * d2) / d2;
      }
   }

   private DoubleValue(String str, Module module, SettingGroup doubleValue, double d, double d2, double d3, int n) {
       super(module, doubleValue, str);
      this.v = new DoubleProperty(this, "V", 0.0);
      this.slider = new SliderComponent();
      this.lastArrowRepeatTimeMs = 0L;
      this.arrowPressStartTimeMs = 0L;
      this.arrowAdjusting = false;
      this.labelWidth = -1;
      this.lastSyncedValue = 0.0;
      this.wasArrowAdjusting = false;
      this.scrollAdjusted = false;
      this.setValue(d);
      DoubleProperty doubleProperty = this.v;
      Double d4 = d;
      doubleProperty.setDefaultValue(d4);
      this.minValue = d2;
      this.maxValue = d3;
      this.decimalPlaces = n;
      this.textField = new TextFieldComponent(String.valueOf(d), StringInputMode.NUMERIC);
   }

   public DoubleValue(String str, double d, double d2, double d3, int n) {
       this(str, null, null, d, d2, d3, n);
   }

   public DoubleValue named(String str) {
      this.setDisplayName(str);
      return this;
   }

   public DoubleValue(Module module, String str, double d, double d2, double d3, int n) {
       this(str, module, null, d, d2, d3, n);
   }

   @Override
   public void resetToDefault() {
      this.v.resetToDefault();
   }

   public double getMaxValue() {
      return this.maxValue;
   }

   private Double tryParseDouble(String str) {
      try {
         return Double.parseDouble(str);
      } catch (NumberFormatException e) {
         return null;
      }
   }

   public double getValue() {
      return (Double)this.v.getValue();
   }

   @Override
   public void randomize() {
      if (this.getName().contains("frequency")) {
         this.setValue(MathStatisticsUtil.lerp(this.minValue, 5.0, Math.random()));
      } else if (this.getDisplayName().contains("Rate of change")) {
         this.setValue(MathStatisticsUtil.lerp(this.minValue, 5.0, Math.random()));
      } else {
         this.setValue(MathStatisticsUtil.lerp(this.minValue, this.maxValue, Math.random()));
      }
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.slider.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
   }

   public double getMinValue() {
      return this.minValue;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      this.slider.mouseReleased(mouseX, mouseY, state);
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public void setDecimalPlaces(int n) {
      this.decimalPlaces = n;
   }

   public boolean isWasArrowAdjusting() {
      return this.wasArrowAdjusting;
   }

   public double getValue(boolean flag) {
      return flag ? MathHelper.clamp_double(this.getValue(), this.minValue, this.maxValue) : this.getValue();
   }

   public int getDecimalPlaces() {
      return this.decimalPlaces;
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   public SliderComponent getSlider() {
      return this.slider;
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.textField.mouseClicked(mouseX, mouseY, mouseButton);
      this.slider.mouseClicked(mouseX, mouseY, mouseButton);
      return false;
   }

   public DoubleValue(Module module, String str, double d, double d2, double d3, int n, SettingGroup doubleValue) {
       this(str, module, doubleValue, d, d2, d3, n);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.textField.keyTyped(typedChar, keyCode);
      this.slider.keyTyped(typedChar, keyCode);
      if (RenderUtil.isHovered(
            (float)this.slider.getX(), (float)this.slider.getY(), (float)this.slider.getSliderWidth(), (float)this.slider.getSliderHeight(), this.lastMouseX, this.lastMouseY
         )
         && (keyCode == 203 || keyCode == 205)) {
         this.arrowAdjusting = true;
         this.arrowPressStartTimeMs = System.currentTimeMillis();
         this.lastArrowRepeatTimeMs = System.currentTimeMillis();
         double d = 1.0 / Math.pow(10.0, (double)this.decimalPlaces) * (double)(keyCode == 203 ? -1 : 1);
         if (this.getValue() + d <= this.maxValue && this.getValue() + d >= this.minValue) {
            this.setValue(this.getValue() + d);
            this.notifyValueChanged();
            this.notifyUserChanged();
         }
      }
   }

   public boolean isScrollAdjusted() {
      return this.scrollAdjusted;
   }


}
