package net.augustus.settings;

import net.augustus.utils.math.SimplexNoise;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.modules.Module;

public class NoiseLayerSetting extends Setting {
   private IntervalNoiseSetting frequency;
   private float stepDeltaMs;
   private float phaseOffset;
   private DoubleValue strength;
   private float phasePosition;
   private IntervalNoiseSetting rateofchange;
   private boolean useDynamicIntervals;
   private DoubleValue frequency2;
   private DoubleValue rateOfChange;
   private StringValue type;

   public float getPhaseOffset() {
      return this.phaseOffset;
   }

   public IntervalNoiseSetting getFrequency() {
      return this.frequency;
   }

   public IntervalNoiseSetting getRateofchange() {
      return this.rateofchange;
   }

   public double getEffectiveStrength() {
      if (!this.strength.isVisible()) {
         return 1.0;
      } else {
         return this.strength.getValue(true);
      }
   }

   private boolean isSinusModeSelected() {
      return this.type.getSelectedValue().equals("Sinus");
   }

   public void setNoiseType(String noiseType) {
      this.type.setSelectedValue(noiseType);
   }

   @Override
   public void resetToDefault() {
   }

   private boolean isNoiseModeSelected() {
      return this.type.getSelectedValue().equals("Noise");
   }

   public void setPhaseOffset(float f) {
      this.phaseOffset = f;
   }

   public void advanceByMillis(long l) {
      if (this.useDynamicIntervals) {
         if (this.type.getSelectedValue().equals("Noise")) {
            this.rateofchange.stepLayers();
            this.phasePosition = this.phasePosition + (float)this.rateofchange.sample() * (float)l / 4000.0F;
         } else {
            this.frequency.stepLayers();
            this.phasePosition = this.phasePosition + (float)this.frequency.sample() * (float)l / 1000.0F;
         }
      } else {
         if (this.type.getSelectedValue().equals("Noise")) {
            this.phasePosition = this.phasePosition + (float)this.rateOfChange.getValue(true) * (float)l / 4000.0F;
         } else {
            this.phasePosition = this.phasePosition + (float)this.frequency2.getValue(true) * ((float)l / 1000.0F);
            this.phasePosition %= 1.0F;
         }
      }
   }

   public void step() {
      if (this.useDynamicIntervals) {
         if (this.type.getSelectedValue().equals("Noise")) {
            this.rateofchange.stepLayers();
            this.phasePosition = this.phasePosition + (float)this.rateofchange.sample() * this.stepDeltaMs / 4000.0F;
         } else {
            this.frequency.stepLayers();
            this.phasePosition = this.phasePosition + (float)this.frequency.sample() * this.stepDeltaMs / 1000.0F;
         }
      } else {
         if (this.type.getSelectedValue().equals("Noise")) {
            this.phasePosition = this.phasePosition + (float)this.rateOfChange.getValue(true) * this.stepDeltaMs / 4000.0F;
         } else {
            this.phasePosition = this.phasePosition + (float)this.frequency2.getValue(true) * (this.stepDeltaMs / 1000.0F);
            this.phasePosition %= 1.0F;
         }
      }
   }

   public double sampleAt(float f) {
      return this.type.getSelectedValue().equals("Noise")
         ? (double)SimplexNoise.noise(this.phasePosition, f)
         : Math.sin((double)(this.phasePosition * 2.0F) * Math.PI + (double)f);
   }

   public NoiseLayerSetting(Module module, SettingGroup doubleValue, String str, SettingVisibilityCondition condition, int n, boolean flag) {
       super(module, doubleValue, str, false);
      this.phaseOffset = (float)ThreadLocalRandom.current().nextDouble();
      SettingGroup settingGroup = (SettingGroup)new SettingGroup(module, str + "_layer_" + n, doubleValue).withDisplayName("Layer " + n);
      this.type = (StringValue)new StringValue(module, str + "_type", "Noise", new String[]{"Noise", "Sinus"}, settingGroup).withDisplayName("Type");
      this.strength = new DoubleValue(module, str + "_strength", 0.0, 0.1, 100.0, 0, settingGroup).named("Strength").visibleWhen(condition);
      if (flag) {
         this.rateofchange = new IntervalNoiseSetting(module, str + "_rateofchange", settingGroup, false).named("Rate of change");
         this.frequency = new IntervalNoiseSetting(module, str + "_frequency", settingGroup, false).named("Frequency");
         double d = 1.0;
         double d2 = 1.0;
         double d3 = 25.0;
         double d4 = 0.1;
         this.frequency.withInterval(d4, d3, d2, d, (byte)2);
         double d5 = 1.0;
         double d6 = 1.0;
         double d7 = 25.0;
         double d8 = 0.1;
         this.rateofchange.withInterval(d8, d7, d6, d5, (byte)2);
         this.rateofchange.addDefaultLayer();
         this.frequency.addDefaultLayer();
         IntervalNoiseSetting rateofchange2 = this.rateofchange;
         SettingVisibilityCondition condition2 = () -> {
            return this.type.getSelectedValue().equals("Noise");
         };
         rateofchange2.visibleWhen(condition2);
         IntervalNoiseSetting frequency3 = this.frequency;
         condition2 = () -> this.isDynamicSinusModeSelected();
         frequency3.visibleWhen(condition2);
         this.useDynamicIntervals = true;
      } else {
         this.rateOfChange = new DoubleValue(module, str + "_rateOfChange", 1.0, 0.1, 25.0, 2, settingGroup).named("Rate of change").visibleWhen(() -> this.isNoiseModeSelected());
         this.frequency2 = new DoubleValue(module, str + "_frequency", 1.0, 0.1, 10.0, 2, settingGroup).named("Frequency").visibleWhen(() -> this.isSinusModeSelected());
      }
   }

   public void setFrequency(double d) {
      this.frequency2.setValue(d);
   }

   public void setStrength(double d) {
      this.strength.setValue(d);
   }

   public void setPhasePosition(float f) {
      this.phasePosition = f;
   }

   public double sample() {
      return this.sampleAt(this.phaseOffset);
   }

   public void setRateOfChange(double d) {
      this.rateOfChange.setValue(d);
   }

   @Override
   public void renderTooltip() {
   }

   public void setStepDeltaMs(float f) {
      this.stepDeltaMs = f;
   }

   private boolean isDynamicSinusModeSelected() {
      return this.type.getSelectedValue().equals("Sinus");
   }


}
