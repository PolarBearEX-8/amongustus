package net.augustus.modules.modes.killaura.unoise3;



import net.augustus.settings.RandomizedRangeSettings;
public class UNoise3FutureAimMode extends UNoise3AimMode {
   private RandomizedRangeSettings uNoise3FutureDelay;

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      if (this.isSurfaceRestrictionActive()) {
         return this.clampToUnoccludedBounds(uNoise3AimContext, uNoise3AimContext.delayedFuturePitch);
      } else {
         return uNoise3AimContext.delayedFuturePitch;
      }
   }

   public RandomizedRangeSettings getUNoise3FutureDelay() {
      return this.uNoise3FutureDelay;
   }

   public UNoise3FutureAimMode(UNoise3SettingContext uNoise3SettingContext2) {
       super(uNoise3SettingContext2, "Future", UNoise3AimConstraintType.POINT, false, true);
      this.uNoise3FutureDelay = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3FutureDelay", 0.0, 0.0, 0.0, 3.0, 0, this.getSettingsGroup()).withDisplayNamePrefix("Delay");
   }

}
