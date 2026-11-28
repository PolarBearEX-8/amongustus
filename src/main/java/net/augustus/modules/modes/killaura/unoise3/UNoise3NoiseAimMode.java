package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.NoiseLayerBuilder;
import net.augustus.settings.NoiseLayerSetting;
import net.augustus.settings.VarianceNoiseSetting;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.settings.SettingGroup;

public class UNoise3NoiseAimMode extends UNoise3AimMode {
   private VarianceNoiseSetting noise;

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double d = 0.5;
      double d2 = 0.5;
      double d3 = this.noise.sampleWithVariance(d2, d);
      return HitboxAngleUtil.lerpRange(this.getEffectiveAngleBounds(uNoise3AimContext, true), d3);
   }

   public UNoise3NoiseAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "Noise", UNoise3AimConstraintType.SURFACE_LIMITER, true, flag);
      SettingGroup settingsGroup2 = this.getSettingsGroup();
      this.noise = new VarianceNoiseSetting(uNoise3SettingContext2.withParentGroup(settingsGroup2), true).named("Noise");
      this.noise.addLayer(this.noise.newLayerBuilder().withDynamicIntervals(true).build());
      double d = 100.0;
      double d2 = 1.0;
      double d3 = 100.0;
      this.noise.withVariance(d3, d2, d);
   }

   @Override
   public void stepNoise() {
      this.noise.stepLayers();
   }
}
