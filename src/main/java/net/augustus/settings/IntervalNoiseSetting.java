package net.augustus.settings;

import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.Module;

public class IntervalNoiseSetting extends AbstractNoiseSetting {
   public RangeSetting intervalRange;
   private Exception creationTrace;
   private DoubleValue sharpnessSetting;

   @Override
   public double sample() {
      return this.sampleWithOffset(0.0F);
   }

   @Override
   public void resetToDefault() {
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   public IntervalNoiseSetting visibleWhen(SettingVisibilityCondition condition) {
      super.withNoiseVisibility(condition);
      return this;
   }

   public IntervalNoiseSetting named(String str) {
      this.noisegroup.withDisplayName(str);
      this.setDisplayName(str);
      if (this.intervalRange != null) {
         this.intervalRange.named(str);
      }

      return this;
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   public IntervalNoiseSetting withRangeValues(double d, double d2) {
      this.intervalRange.setMinValue(d);
      this.intervalRange.setMaxValue(d2);
      return this;
   }

   public double sampleWithOffset(float f) {
      if (this.intervalRange == null) {
         this.creationTrace.printStackTrace();
         return 0.0;
      } else {
         double d = 0.0;
         double d2 = 0.0;

         for (NoiseLayerSetting noiseLayerSetting : (Iterable<NoiseLayerSetting>)(this.layers)) {
            double effectiveStrength = noiseLayerSetting.getEffectiveStrength();
            if (effectiveStrength != 0.0) {
               float f2 = noiseLayerSetting.getPhaseOffset() + f;
               d += noiseLayerSetting.sampleAt(f2) * effectiveStrength;
               d2 += effectiveStrength;
            }
         }

         if (d2 == 0.0) {
            return 0.0;
         } else {
            double d3 = d / d2 * 0.5 + 0.5;
            if (this.sharpnessSetting != null) {
               d3 = Math.pow(d3, this.sharpnessSetting.getValue(true));
            }

            double d4 = MathStatisticsUtil.lerp(this.intervalRange.getMinValue(true), this.intervalRange.getMaxValue(true), d3);
            if (this.normalized) {
               d4 /= this.intervalRange.getMaximumBound();
            }

            return d4;
         }
      }
   }

   @Override
   public void renderTooltip() {
   }

   public IntervalNoiseSetting(Module module, String str, SettingGroup doubleValue, boolean flag) {
       super(module, str, doubleValue, flag);
      this.normalized = flag;
      this.creationTrace = new Exception();
   }

   @Override
   public AbstractNoiseSetting withNoiseVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   public void enableSharpness(int n) {
      this.sharpnessSetting = new DoubleValue(this.getParentModule(), this.getName() + "_sharpness", (double)n, 0.5, 10.0, 0, this.noisegroup).named("Sharpness");
   }

   public IntervalNoiseSetting withInterval(double d, double d2, double d3, double d4, int n) {
      this.intervalRange = new RangeSetting(this.getParentModule(), this.getName() + "_interval", d3, d4, d, d2, n, this.noisegroup).named(this.getDisplayName());
      return this;
   }


}
