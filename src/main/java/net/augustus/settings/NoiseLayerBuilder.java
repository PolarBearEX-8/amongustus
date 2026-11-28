package net.augustus.settings;

import net.augustus.modules.Module;

public class NoiseLayerBuilder {
   private boolean useDynamicIntervals;
   private String namePrefix;
   private String noiseType = "Sinus";
   private Module parentModule;
   private double frequency;
   private double rateOfChange;
   private SettingGroup parentGroup;
   private float stepDeltaMs;
   private int layerIndex;
   private double strength = 0.0;
   private SettingVisibilityCondition strengthVisibility;

   public NoiseLayerBuilder(Module module, SettingGroup doubleValue, String str, int n, SettingVisibilityCondition condition) {
      this.rateOfChange = 1.0;
      this.frequency = 1.0;
      this.stepDeltaMs = 50.0F;
      this.useDynamicIntervals = false;
      this.parentModule = module;
      this.parentGroup = doubleValue;
      this.namePrefix = str;
      this.layerIndex = n;
      this.strengthVisibility = condition;
   }

   public NoiseLayerSetting build() {
      NoiseLayerSetting noiseLayerSetting = new NoiseLayerSetting(this.parentModule, this.parentGroup, this.namePrefix + "_layer_" + this.layerIndex, this.strengthVisibility, this.layerIndex, this.useDynamicIntervals);
      noiseLayerSetting.setNoiseType(this.noiseType);
      noiseLayerSetting.setStrength(this.strength);
      noiseLayerSetting.setStepDeltaMs(this.stepDeltaMs);
      if (this.useDynamicIntervals) {
         IntervalNoiseSetting frequency2 = noiseLayerSetting.getFrequency();
         double frequency3 = this.frequency;
         frequency2 = frequency2.withRangeValues(this.frequency, frequency3);
         frequency2.enableSharpness((byte)1);
         frequency2 = noiseLayerSetting.getRateofchange();
         double rateOfChange2 = this.rateOfChange;
         frequency2 = frequency2.withRangeValues(this.rateOfChange, rateOfChange2);
         frequency2.enableSharpness((byte)1);
      } else {
         noiseLayerSetting.setFrequency(this.frequency);
         noiseLayerSetting.setRateOfChange(this.rateOfChange);
      }

      return noiseLayerSetting;
   }

   public NoiseLayerBuilder withDynamicIntervals(boolean dynamicIntervals) {
      this.useDynamicIntervals = dynamicIntervals;
      return this;
   }

   public NoiseLayerBuilder withStrength(double d) {
      this.strength = d;
      return this;
   }

   public NoiseLayerBuilder withFrequency(double d) {
      this.frequency = d;
      return this;
   }

   public NoiseLayerBuilder withStepDeltaMs(float f) {
      this.stepDeltaMs = f;
      return this;
   }

   public NoiseLayerBuilder withRateOfChange(double d) {
      this.rateOfChange = d;
      return this;
   }





}
