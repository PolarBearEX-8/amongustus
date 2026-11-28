package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.VarianceNoiseSetting;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.settings.SettingGroup;

public class UNoise3FutureIntervalAimMode extends UNoise3AimMode {
   private RandomizedRangeSettings uNoise3FutureIntervalDelay;
   private VarianceNoiseSetting noise;

   public RandomizedRangeSettings getUNoise3FutureIntervalDelay() {
      return this.uNoise3FutureIntervalDelay;
   }

   public UNoise3FutureIntervalAimMode(UNoise3SettingContext uNoise3SettingContext2) {
       super(uNoise3SettingContext2, "Future Interval", UNoise3AimConstraintType.POINT, false, true);
      this.uNoise3FutureIntervalDelay = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3FutureIntervalDelay", 0.0, 0.0, 0.0, 3.0, 0, this.getSettingsGroup()).withDisplayNamePrefix("Delay");
      SettingGroup settingsGroup2 = this.getSettingsGroup();
      this.noise = new VarianceNoiseSetting(uNoise3SettingContext2.withParentGroup(settingsGroup2), "noise", true).named("Noise");
      this.noise.addDefaultLayer();
      double d = 100.0;
      double d2 = 0.0;
      double d3 = 100.0;
      this.noise.withVariance(d3, d2, d);
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double[] delayedFuturePitchBounds = uNoise3AimContext.delayedFuturePitchBounds;
      double d = 0.5;
      double d2 = 0.5;
      double d4 = HitboxAngleUtil.lerpRange(delayedFuturePitchBounds, this.noise.sampleWithVariance(d2, d));
      if (this.isSurfaceRestrictionActive()) {
         return this.clampToUnoccludedBounds(uNoise3AimContext, d4);
      } else {
         return d4;
      }
   }

   @Override
   public void stepNoise() {
      this.noise.stepLayers();
   }

}
