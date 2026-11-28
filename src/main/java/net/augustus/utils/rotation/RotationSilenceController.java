package net.augustus.utils.rotation;

import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class RotationSilenceController {
   private BooleanValue keepticking;
   private BooleanValue synchronizesilence;
   private boolean silentActive;
   private RandomizedRangeSettings silencestrength;
   private RandomizedRangeSettings nonsilentinterval;
   private double currentDecreaseFactor;
   private RandomizedRangeSettings silentinterval;
   private RandomizedRangeSettings silencedecreasefactor;
   private BooleanValue allowforsilence;
   private int remainingIntervalTicks;
   private BooleanValue shutup;

   public void reset() {
      double d = 0.0;
      this.silencestrength.setLastValue(d);
      this.remainingIntervalTicks = this.nonsilentinterval.nextInt(true);
      if (this.remainingIntervalTicks <= 1) {
         this.remainingIntervalTicks = 2;
      }
   }

   public boolean shouldSilenceOffTarget(boolean flag) {
      return this.synchronizesilence != null && this.synchronizesilence.getValue() ? flag : this.shutup.getValue();
   }

   private boolean areSilenceSettingsVisible(boolean flag) {
      return flag || this.allowforsilence.getValue() && !this.synchronizesilence.getValue();
   }

   public boolean shouldKeepTicking(boolean flag) {
      return this.synchronizesilence != null && this.synchronizesilence.getValue() ? flag : this.keepticking.getValue();
   }

   public double computeSilenceFactor(double d) {
      if (this.allowforsilence != null && !this.allowforsilence.getValue()) {
         return 0.0;
      } else if (this.allowforsilence != null && this.synchronizesilence.getValue()) {
         return d;
      } else {
         if (this.remainingIntervalTicks > 0) {
            this.remainingIntervalTicks--;
         }

         if (this.remainingIntervalTicks == 0) {
            this.silentActive = !this.silentActive;
            boolean flag = false;
            if (this.silentActive) {
               flag = true;
            } else {
               this.remainingIntervalTicks = this.nonsilentinterval.nextInt(true);
               if (this.remainingIntervalTicks == 0) {
                  flag = true;
                  this.silentActive = true;
               }
            }

            if (flag) {
               this.remainingIntervalTicks = this.silentinterval.nextInt(true);
               this.currentDecreaseFactor = this.silencedecreasefactor.nextValue(true);
               this.silencestrength.nextValue(true);
            }
         }

         return !this.silentActive ? 0.0 : (1.0 - (double)this.remainingIntervalTicks / (double)this.silentinterval.getLastIntValue() * this.currentDecreaseFactor) * this.silencestrength.getLastValue();
      }
   }

   public RotationSilenceController(UNoise3SettingContext uNoise3SettingContext, boolean flag) {
       super();
      SettingGroup settingGroup = (SettingGroup)new SettingGroup(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_silence", uNoise3SettingContext.getParentGroup()).withDisplayName("Silence");
      if (!flag) {
         BooleanValue booleanValue = new BooleanValue(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_allowforsilence", false, settingGroup);
         this.allowforsilence = (BooleanValue)booleanValue.withDisplayName("Enable Silence");
         booleanValue = new BooleanValue(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_synchronizesilence", true, settingGroup);
         booleanValue = (BooleanValue)booleanValue.withDisplayName("Synchronize Silence");
         BooleanValue allowforsilence2 = this.allowforsilence;
         this.synchronizesilence = (BooleanValue)booleanValue.withVisibility(() -> allowforsilence2.getValue());
      }

      SettingVisibilityCondition condition2 = () -> this.areSilenceSettingsVisible(flag);
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_silentinterval", 1.0, 1.0, 1.0, 20.0, 0, settingGroup);
      this.silentinterval = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Silent Interval Length")).withVisibility(condition2);
      randomizedRangeSettings = (RandomizedRangeSettings)(new RandomizedRangeSettings(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_nonsilentinterval", 3.0, 5.0, 0.0, 40.0, 0, settingGroup))
         .withVisibility(condition2);
      this.nonsilentinterval = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Loud interval");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_silencestrength", 1.0, 1.0, 0.0, 1.0, 2, settingGroup);
      this.silencestrength = ((RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Silence Strength")).withVisibility(condition2)).withoutNotInARow();
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_silencedecreasefactor", 0.0, 0.0, 0.0, 1.0, 2, settingGroup);
      this.silencedecreasefactor = ((RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Silence Decrease Factor")).withVisibility(condition2)).withoutNotInARow();
      this.keepticking = (BooleanValue)((BooleanValue)new BooleanValue(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_keepticking", true, settingGroup).withDisplayName("Keep Ticking")).withVisibility(condition2);
      this.shutup = (BooleanValue)((BooleanValue)new BooleanValue(uNoise3SettingContext.getModule(), uNoise3SettingContext.getParentGroup().getName() + "_shutup", false, settingGroup).withDisplayName("Be silent even if not on target")).withVisibility(condition2);
   }

}
