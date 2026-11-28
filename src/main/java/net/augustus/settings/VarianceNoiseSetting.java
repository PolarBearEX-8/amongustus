package net.augustus.settings;

import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.modules.Module;

public class VarianceNoiseSetting extends AbstractNoiseSetting {
   private DoubleValue varianceSetting;
   private DoubleValue strengthSetting;
   private Exception creationTrace;

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   public VarianceNoiseSetting named(String str) {
      super.withDisplayName(str);
      this.noisegroup.withDisplayName(str);
      if (this.strengthSetting != null) {
         this.strengthSetting.named(str);
      }

      return this;
   }

   public VarianceNoiseSetting(Module module, String str, SettingGroup doubleValue, boolean flag) {
       super(module, str, doubleValue, flag);
      this.creationTrace = new Exception();
   }

   @Override
   public void renderTooltip() {
   }

   public VarianceNoiseSetting(UNoise3SettingContext uNoise3SettingContext, boolean flag) {
       super(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_noise", uNoise3SettingContext.getParentGroup(), flag);
      this.creationTrace = new Exception();
   }

   public VarianceNoiseSetting withVariance(double d, double d2, double d3) {
      this.varianceSetting = new DoubleValue(this.getParentModule(), this.getName() + "_variance", d, d2, d3, 0, this.noisegroup).named("Variance");
      return this;
   }

   public VarianceNoiseSetting(UNoise3SettingContext uNoise3SettingContext, String str, boolean flag) {
       super(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_" + str, uNoise3SettingContext.getParentGroup(), flag);
      this.creationTrace = new Exception();
   }

   public VarianceNoiseSetting withStrength(double d, double d2, double d3, int n) {
      this.strengthSetting = new DoubleValue(this.getParentModule(), this.getName() + "_strength", d, d2, d3, n, this.noisegroup).named(this.getDisplayName());
      return this;
   }

   public double sampleCentered(double d, double d2) {
      return this.sample(true) * d2 + d;
   }

   @Override
   public void resetToDefault() {
   }

   public double sampleUnitRange() {
      return this.sample(true) * 0.5 + 0.5;
   }

   @Override
   public double sample() {
      float f = 0.0F;
      return this.sampleWithOffset(this.normalized, f);
   }

   public double sample(boolean flag) {
      return this.sampleWithOffset(flag, 0.0F);
   }

   public double sampleWithVariance(double d, double d2) {
      return (this.sample(true) * d2) * this.varianceSetting.getValue(true) / this.varianceSetting.getMaxValue() + d;
   }

   public double sampleWithOffset(boolean flag, float f) {
      if (this.strengthSetting == null && !flag) {
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
            d2 = 1.0;
         }

         double d3 = d / d2;
         if (!flag) {
            d3 *= this.strengthSetting.getValue(true);
         }

         return d3;
      }
   }


}
