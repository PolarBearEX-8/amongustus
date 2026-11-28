package net.augustus.cosmetics;

import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.settings.ColorSetting;
import net.augustus.modules.render.ClickGui;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;

public class CosmeticSettingEntry {
   private final ColorSetting[] colorSettings;
   private final CosmeticModel model;
   private final BooleanValue enabledSetting;
   private final DoubleValue scaleSetting;
   private final DoubleValue heightSetting;
   private int[] lastColors;
   private boolean[] lastRainbowStates;
   private boolean[] lastAstolfoStates;
   private double[] lastRainbowSpeeds;
   private double[] lastAstolfoSpeeds;
   private boolean lastEnabled = false;
   private float lastScale = 1.0F;
   private float lastHeight = 0.0F;

   private CosmeticSettingEntry(CosmeticModel cosmeticModel, BooleanValue booleanValue, DoubleValue stringValue, DoubleValue stringValue2, ColorSetting[] colorSettings2) {
      this.model = cosmeticModel;
      this.enabledSetting = booleanValue;
      this.scaleSetting = stringValue;
      this.heightSetting = stringValue2;
      this.colorSettings = colorSettings2;
      this.lastColors = new int[colorSettings2.length];
      this.lastRainbowStates = new boolean[colorSettings2.length];
      this.lastAstolfoStates = new boolean[colorSettings2.length];
      this.lastRainbowSpeeds = new double[colorSettings2.length];
      this.lastAstolfoSpeeds = new double[colorSettings2.length];
   }

   public ColorSetting[] getColorSettings() {
      return this.colorSettings;
   }

   public CosmeticModel getModel() {
      return this.model;
   }

   public BooleanValue getEnabledSetting() {
      return this.enabledSetting;
   }

   public double getScale() {
      return this.scaleSetting.getValue(true);
   }

   public void setScale(double scale) {
      this.scaleSetting.setValue(scale);
   }

   public double getHeight() {
      return this.heightSetting.getValue(true);
   }

   public void setHeight(double height) {
      this.heightSetting.setValue(height);
   }

   public boolean hasChanged() {
      boolean flag = false;

      for (int i = 0; i < this.colorSettings.length; i++) {
         ColorSetting colorSetting = this.colorSettings[i];
         int colorInt = colorSetting.getColorInt((byte)0);
         boolean isRainbowEnabled = colorSetting.isRainbowEnabled();
         boolean isAstolfoEnabled = colorSetting.isAstolfoEnabled();
         double rainbowSpeed = colorSetting.getRainbowSpeed();
         double astolfoSpeed = colorSetting.getAstolfoSpeed();
         int n = this.lastColors[i];
         boolean flag2 = this.lastRainbowStates[i];
         boolean flag3 = this.lastAstolfoStates[i];
         double d = this.lastRainbowSpeeds[i];
         double d2 = this.lastAstolfoSpeeds[i];
         boolean flag4 = isRainbowEnabled && flag2 || isAstolfoEnabled && flag3;
         if (colorInt != n && !flag4 || isRainbowEnabled != flag2 || isAstolfoEnabled != flag3 || rainbowSpeed != d || astolfoSpeed != d2) {
            flag = true;
         }

         this.lastColors[i] = colorInt;
         this.lastRainbowStates[i] = isRainbowEnabled;
         this.lastAstolfoStates[i] = isAstolfoEnabled;
         this.lastRainbowSpeeds[i] = rainbowSpeed;
         this.lastAstolfoSpeeds[i] = astolfoSpeed;
      }

      boolean value = this.getEnabledSetting().getValue();
      float scale = (float)this.getScale();
      float height = (float)this.getHeight();
      if (value != this.lastEnabled || scale != this.lastScale || height != this.lastHeight) {
         flag = true;
      }

      this.lastEnabled = value;
      this.lastScale = scale;
      this.lastHeight = height;
      return flag;
   }

   public CosmeticSettingEntry(CosmeticModel cosmeticModel, BooleanValue booleanValue, DoubleValue stringValue, DoubleValue stringValue2, ColorSetting[] colorSettings2, CosmeticSettingEntrySyntheticMarker marker) {
      this(cosmeticModel, booleanValue, stringValue, stringValue2, colorSettings2);
   }
}
