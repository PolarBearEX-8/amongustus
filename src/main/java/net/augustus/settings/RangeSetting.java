package net.augustus.settings;

import net.augustus.font.CustomFontUtil;
import net.augustus.settings.property.DoubleProperty;
import net.augustus.utils.math.MathStatisticsUtil;
import java.io.IOException;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.minecraft.util.MathHelper;

public class RangeSetting extends Setting {
   private Runnable valueUpdatedCallback;
   private boolean maxFirst;
   private boolean wasMaxFieldFocused;
   private boolean wasMinFieldFocused;
   private final DoubleProperty f;
   public DoubleValue min;
   public DoubleValue max;
   private final DoubleProperty s;
   private int labelWidth;

   public double getMinValue(boolean flag) {
      return flag ? MathHelper.clamp_double((Double)this.f.getValue(), this.min.getMinValue(), this.min.getMaxValue()) : (Double)this.f.getValue();
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6 * 2, customFontUtil, flag);
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)customFontUtil.getStringWidth(this.min.getDisplayName() + ": ");
      }

      if (this.maxFirst) {
         this.max.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
         this.setMaxValue(this.max.getValue());
         this.min.render(n, n2, n3, n4 + this.max.getHeight(), n5, n6, customFontUtil, flag);
         this.setMinValue(this.min.getValue());
      } else {
         this.min.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
         this.setMinValue(this.min.getValue());
         this.max.render(n, n2, n3, n4 + this.min.getHeight(), n5, n6, customFontUtil, flag);
         this.setMaxValue(this.max.getValue());
      }

      if (this.getMinValue() > this.getMaxValue()) {
         if (this.min.getSlider().isDragging() || this.wasMinFieldFocused && !this.min.getTextField().isFocused() || this.min.isWasArrowAdjusting() || this.min.isScrollAdjusted()) {
            this.max.setValue(this.min.getValue());
            this.setMaxValue(this.max.getValue());
         }

         if (this.max.getSlider().isDragging() || this.wasMaxFieldFocused && !this.max.getTextField().isFocused() || this.max.isWasArrowAdjusting() || this.max.isScrollAdjusted()) {
            this.min.setValue(this.max.getValue());
            this.setMinValue(this.min.getValue());
         }
      }

      this.wasMinFieldFocused = this.min.getTextField().isFocused();
      this.wasMaxFieldFocused = this.max.getTextField().isFocused();
   }

   public RangeSetting withValueUpdatedCallback(Runnable runnable) {
      this.valueUpdatedCallback = runnable;
      return this;
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.min.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.max.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
   }

   public RangeSetting(Module module, String str, String str2, double d, double d2, double d3, double d4, int n, SettingGroup doubleValue) {
       super(module, doubleValue, str + str2);
      this.f = (DoubleProperty)new DoubleProperty(this, "F", 0.0).withOnValueChanged(obj -> this.setMinValue((double)obj));
      this.s = (DoubleProperty)new DoubleProperty(this, "S", 0.0).withOnValueChanged(obj2 -> this.setMaxValue((double)obj2));
      this.maxFirst = false;
      this.labelWidth = -1;
      this.valueUpdatedCallback = null;
      this.wasMinFieldFocused = false;
      this.wasMaxFieldFocused = false;
      DoubleProperty doubleProperty3 = this.f;
      Double d5 = d;
      doubleProperty3.setValue(d5);
      doubleProperty3 = this.s;
      Double d7 = d2;
      doubleProperty3.setValue(d7);
      doubleProperty3 = this.f;
      Double d9 = d;
      doubleProperty3.setDefaultValue(d9);
      doubleProperty3 = this.s;
      Double d10 = d2;
      doubleProperty3.setDefaultValue(d10);
      this.min = new DoubleValue(str, d, d3, d4, n).visibleWhen(() -> this.isVisible());
      this.max = new DoubleValue(str2, d2, d3, d4, n).visibleWhen(() -> this.isVisible());
   }

   public void setDecimalPlaces(int decimalPlaces) {
      this.max.setDecimalPlaces(decimalPlaces);
      this.min.setDecimalPlaces(decimalPlaces);
   }

   public RangeSetting(Module module, String str, double d, double d2, double d3, double d4, int n) {
       super(module, null, str);
      this.f = (DoubleProperty)new DoubleProperty(this, "F", 0.0).withOnValueChanged(obj -> this.setMinValue((double)obj));
      this.s = (DoubleProperty)new DoubleProperty(this, "S", 0.0).withOnValueChanged(obj2 -> this.setMaxValue((double)obj2));
      this.maxFirst = false;
      this.labelWidth = -1;
      this.valueUpdatedCallback = null;
      this.wasMinFieldFocused = false;
      this.wasMaxFieldFocused = false;
      DoubleProperty doubleProperty3 = this.f;
      Double d5 = d;
      doubleProperty3.setValue(d5);
      doubleProperty3 = this.s;
      Double d7 = d2;
      doubleProperty3.setValue(d7);
      doubleProperty3 = this.f;
      Double d9 = d;
      doubleProperty3.setDefaultValue(d9);
      doubleProperty3 = this.s;
      Double d10 = d2;
      doubleProperty3.setDefaultValue(d10);
      this.min = new DoubleValue("Min" + str, d, d3, d4, n).visibleWhen(() -> this.isVisible());
      this.max = new DoubleValue("Max" + str, d2, d3, d4, n).visibleWhen(() -> this.isVisible());
   }

   @Override
   public String[] getTranslationKeys() {
      return new String[]{this.min.getName(), this.max.getName()};
   }

   public double getMinValue() {
      return this.getMinValue(false);
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   public double getMaxValue() {
      return this.getMaxValue(false);
   }

   @Override
   public int getHeight() {
      return this.min.getHeight() + this.max.getHeight();
   }

   public RangeSetting named(String str) {
      return this.withBoundNames("Min" + str, "Max" + str);
   }

   public double getMaxValue(boolean flag) {
      return flag ? MathHelper.clamp_double((Double)this.s.getValue(), this.max.getMinValue(), this.max.getMaxValue()) : (Double)this.s.getValue();
   }

   public Runnable getValueUpdatedCallback() {
      return this.valueUpdatedCallback;
   }

   public RangeSetting withMaxFirst() {
      this.maxFirst = true;
      return this;
   }

   @Override
   public void applyTranslation(String str, String str2) {
      if (str2.equals("English_Default")) {
         this.min.setLabelDirty(true);
         this.max.setLabelDirty(true);
         String str3 = "";
         this.min.setTranslatedName(str3);
         String str4 = "";
         this.max.setTranslatedName(str4);
      } else {
         if (this.min.getName().equalsIgnoreCase(str)) {
            this.min.setLabelDirty(true);
            this.min.setTranslatedName(str2);
         } else if (this.max.getName().equalsIgnoreCase(str)) {
            this.max.setLabelDirty(true);
            this.max.setTranslatedName(str2);
         }
      }
   }

   @Override
   public void resetToDefault() {
      this.f.resetToDefault();
      this.s.resetToDefault();
      double value = (Double)this.f.getValue();
      this.setMinValue(value);
      double value2 = (Double)this.s.getValue();
      this.setMaxValue(value2);
   }

   public RangeSetting(Module module, String str, String str2, double d, double d2, double d3, double d4, int n) {
       super(module, null, str + str2);
      this.f = (DoubleProperty)new DoubleProperty(this, "F", 0.0).withOnValueChanged(obj -> this.setMinValue((double)obj));
      this.s = (DoubleProperty)new DoubleProperty(this, "S", 0.0).withOnValueChanged(obj2 -> this.setMaxValue((double)obj2));
      this.maxFirst = false;
      this.labelWidth = -1;
      this.valueUpdatedCallback = null;
      this.wasMinFieldFocused = false;
      this.wasMaxFieldFocused = false;
      DoubleProperty doubleProperty3 = this.f;
      Double d5 = d;
      doubleProperty3.setValue(d5);
      doubleProperty3 = this.s;
      Double d7 = d2;
      doubleProperty3.setValue(d7);
      doubleProperty3 = this.f;
      Double d9 = d;
      doubleProperty3.setDefaultValue(d9);
      doubleProperty3 = this.s;
      Double d10 = d2;
      doubleProperty3.setDefaultValue(d10);
      this.min = new DoubleValue(str, d, d3, d4, n).visibleWhen(() -> this.isVisible());
      this.max = new DoubleValue(str2, d2, d3, d4, n).visibleWhen(() -> this.isVisible());
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      this.min.mouseReleased(mouseX, mouseY, state);
      this.max.mouseReleased(mouseX, mouseY, state);
   }

   public int getDecimalPlaces() {
      return this.min.getDecimalPlaces();
   }

   public RangeSetting(Module module, String str, double d, double d2, double d3, double d4, int n, SettingGroup doubleValue) {
       super(module, doubleValue, str);
      this.f = (DoubleProperty)new DoubleProperty(this, "F", 0.0).withOnValueChanged(obj -> this.setMinValue((double)obj));
      this.s = (DoubleProperty)new DoubleProperty(this, "S", 0.0).withOnValueChanged(obj2 -> this.setMaxValue((double)obj2));
      this.maxFirst = false;
      this.labelWidth = -1;
      this.valueUpdatedCallback = null;
      this.wasMinFieldFocused = false;
      this.wasMaxFieldFocused = false;
      DoubleProperty doubleProperty3 = this.f;
      Double d5 = d;
      doubleProperty3.setValue(d5);
      doubleProperty3 = this.s;
      Double d7 = d2;
      doubleProperty3.setValue(d7);
      doubleProperty3 = this.f;
      Double d9 = d;
      doubleProperty3.setDefaultValue(d9);
      doubleProperty3 = this.s;
      Double d10 = d2;
      doubleProperty3.setDefaultValue(d10);
      this.min = new DoubleValue("Min" + str, d, d3, d4, n).visibleWhen(() -> this.isVisible());
      this.max = new DoubleValue("Max" + str, d2, d3, d4, n).visibleWhen(() -> this.isVisible());
   }

   public void setMinValue(double minValue) {
      DoubleProperty doubleProperty = this.f;
      Double d2 = minValue;
      doubleProperty.setValue(d2);
      this.min.setValue(minValue);
   }

   public void addPropertyChangeCallback(Runnable runnable) {
      this.f.addChangeListener(runnable);
      this.s.addChangeListener(runnable);
   }

   @Override
   public Setting withChangeListener(SettingChangeListener listener) {
      return this.onChange(listener);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.min.keyTyped(typedChar, keyCode);
      this.max.keyTyped(typedChar, keyCode);
   }

   public void setMaxValue(double maxValue) {
      DoubleProperty doubleProperty = this.s;
      Double d2 = maxValue;
      doubleProperty.setValue(d2);
      this.max.setValue(maxValue);
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.min.mouseClicked(mouseX, mouseY, mouseButton);
      this.max.mouseClicked(mouseX, mouseY, mouseButton);
      return false;
   }

   public RangeSetting visibleWhen(SettingVisibilityCondition condition) {
      this.setVisibilityCondition(condition);
      return this;
   }

   @Override
   public void randomize() {
      this.setMinValue(MathStatisticsUtil.lerp(this.min.getMinValue(), this.min.getMaxValue(), Math.random()));
      this.setMaxValue(MathStatisticsUtil.lerp((Double)this.f.getValue(), this.max.getMaxValue(), Math.random()));
   }

   public double getMinimumBound() {
      return 0.0;
   }

   public double getMaximumBound() {
      return this.min.getMaxValue();
   }

   public RangeSetting withBoundNames(String str, String str2) {
      this.min.setDisplayName(str);
      this.max.setDisplayName(str2);
      return this;
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public RangeSetting onChange(SettingChangeListener listener) {
      this.changeListener = listener;
      this.min = (DoubleValue)this.min.withChangeListener(listener);
      this.max = (DoubleValue)this.max.withChangeListener(listener);
      return this;
   }


}
