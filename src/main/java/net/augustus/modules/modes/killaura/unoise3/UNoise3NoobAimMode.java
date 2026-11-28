package net.augustus.modules.modes.killaura.unoise3;



import net.augustus.settings.RandomizedRangeSettings;
public class UNoise3NoobAimMode extends UNoise3AimMode {
   private RandomizedRangeSettings delay;

   public UNoise3NoobAimMode(UNoise3SettingContext uNoise3SettingContext2) {
       super(uNoise3SettingContext2, "Noob", UNoise3AimConstraintType.POINT, false, true);
      this.delay = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_delay", 0.0, 0.0, 0.0, 3.0, 0, this.getSettingsGroup()).withDisplayNamePrefix("Delay");
   }

   public RandomizedRangeSettings getDelay() {
      return this.delay;
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double delayedNoobPitch = uNoise3AimContext.delayedNoobPitch;
      if (this.isSurfaceRestrictionActive()) {
         return this.clampToUnoccludedBounds(uNoise3AimContext, delayedNoobPitch);
      } else {
         return delayedNoobPitch;
      }
   }

}
