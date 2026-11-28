package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.interfaces.MC;

public class UNoise3RandomForceTicker implements MC {
   public int interpolationDuration;
   private RandomizedRangeSettings interpolationDurationSetting;
   public int elapsedTicks;
   private BooleanValue syncInterpolationSetting;
   public int lastRegenTick;
   private RandomizedRangeSettings regenDelaySetting;
   private int ticksUntilRegen;

   private boolean isInterpolationDurationVisible() {
      return !this.syncInterpolationSetting.getValue();
   }

   public void initSettings(UNoise3SettingContext uNoise3SettingContext, SettingGroup doubleValue, SettingVisibilityCondition condition) {
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), doubleValue.getName() + "_setDelay", 1.0, 5.0, 1.0, 20.0, 0, doubleValue);
      this.regenDelaySetting = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Regeneration delay")).withoutNotInARow().withVisibility(condition);
      this.syncInterpolationSetting = (BooleanValue)((BooleanValue)new BooleanValue(uNoise3SettingContext.getModule(), doubleValue.getName() + "_syncInterpolation", false, doubleValue).withDisplayName("Synchronize interpolation with regeneration")).withVisibility(condition);
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext.getModule(), doubleValue.getName() + "_interpolationDuration", 1.0, 5.0, 1.0, 20.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Interpolation Duration");
      this.interpolationDurationSetting = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isInterpolationDurationVisible())).withoutNotInARow().withVisibility(condition);
   }

   public void tick() {
      if (this.ticksUntilRegen == 0) {
         this.lastRegenTick = mc.thePlayer.ticksExisted;
         this.ticksUntilRegen = this.regenDelaySetting.nextInt(true);
         this.elapsedTicks = 0;
         this.interpolationDuration = this.interpolationDurationSetting.nextInt(true);
         if (this.syncInterpolationSetting.getValue()) {
            this.interpolationDuration = this.ticksUntilRegen;
         }
      }

      this.ticksUntilRegen--;
      this.elapsedTicks++;
   }





}
