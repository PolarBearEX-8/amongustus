package net.augustus.utils.rotation;

import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.NoiseLayerBuilder;
import net.augustus.settings.NoiseLayerSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class RotationOvershootController {
   private BooleanValue applymisscalccorrectly;
   private RandomizedRangeSettings maxmissdegrees;
   private DoubleValue buffersize;
   private StatisticalSampleBuffer missDeltaBuffer;
   private RandomizedRangeSettings suppressiontime;
   private int suppressionTicks;
   public BooleanValue applyovershoottomousespeed;
   private IntervalNoiseSetting missstrength;

   public boolean shouldApplyToMouseSpeed() {
      return this.applyovershoottomousespeed.getValue();
   }

   public double computeOvershootMultiplier(double d) {
      this.missstrength.stepLayers();
      if (this.missDeltaBuffer == null || (double)this.missDeltaBuffer.getMaxSize() != this.buffersize.getValue()) {
         this.missDeltaBuffer = new StatisticalSampleBuffer((int)this.buffersize.getValue());
      }

      if (this.suppressionTicks > 0) {
         this.suppressionTicks--;
         return 1.0;
      } else {
         double d2 = this.missstrength.sample() + 1.0;
         double d4 = (d - (d2 * d)) * 0.15;
         this.missDeltaBuffer.add(d4);
         if ((this.applymisscalccorrectly.getValue() ? Math.abs(this.missDeltaBuffer.sum()) : this.missDeltaBuffer.sum()) >= this.maxmissdegrees.getLastValue()) {
            this.missDeltaBuffer.clear();
            this.maxmissdegrees.nextValue(true);
            this.suppressionTicks = this.suppressiontime.nextInt(true);
            if (this.suppressionTicks > 0) {
               return 1.0;
            }
         }

         return d2;
      }
   }

   public RotationOvershootController(UNoise3SettingContext uNoise3SettingContext, String str) {
       super();
      SettingGroup settingGroup = (SettingGroup)new SettingGroup(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_" + str, uNoise3SettingContext.getParentGroup()).withDisplayName("Over / Undershoot " + str);
      settingGroup.visibleWhen(uNoise3SettingContext.getVisibilityCondition());
      uNoise3SettingContext = uNoise3SettingContext.withParentGroup(settingGroup);
      this.missstrength = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), settingGroup.getName() + "_missstrength", settingGroup, false).named("Miss Strength");
      double d = 0.0;
      double d2 = 0.0;
      double d3 = 1.0;
      double d4 = -1.0;
      this.missstrength.withInterval(d4, d3, d2, d, (byte)2);
      this.missstrength.addLayer(this.missstrength.newLayerBuilder().withStepDeltaMs(16.6F).build());
      this.missstrength.addLayer(this.missstrength.newLayerBuilder().withStepDeltaMs(16.6F).build());
      this.missstrength.enableSharpness((byte)1);
      this.buffersize = new DoubleValue(uNoise3SettingContext.getModule(), settingGroup.getName() + "_buffersize", 10.0, 2.0, 30.0, 0, settingGroup).named("Buffersize");
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), settingGroup.getName() + "_maxmissdegrees", 5.0, 6.0, 0.1, 30.0, 2, settingGroup);
      this.maxmissdegrees = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Max degree by which it misses");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), settingGroup.getName() + "_suppressiontime", 0.0, 0.0, 0.0, 40.0, 0, settingGroup);
      this.suppressiontime = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Suppression time");
      this.applymisscalccorrectly = (BooleanValue)new BooleanValue(uNoise3SettingContext.getModule(), settingGroup.getName() + "_applymisscalccorrectly", false, settingGroup).withDisplayName("Apply misscalculation as intended");
      this.applyovershoottomousespeed = (BooleanValue)new BooleanValue(uNoise3SettingContext.getModule(), settingGroup.getName() + "_applyovershoottomousespeed", false, settingGroup).withDisplayName("Apply overshoot to mousespeed");
   }




}
