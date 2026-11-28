package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.settings.BooleanValue;
import net.minecraft.util.MathHelper;

public class UNoise3NearestVecAimMode extends UNoise3AimMode {
   private RandomizedRangeSettings nearedgethreshold;
   private BooleanValue disablewhennearedge;
   private BooleanValue expandbb;

   @Override
   public double sampleWeight(UNoise3AimContext uNoise3AimContext) {
      double d = super.sampleWeight(uNoise3AimContext);
      return Double.isFinite(this.computeTargetAngle(uNoise3AimContext)) ? d : 0.0;
   }

   public UNoise3NearestVecAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "NearestVec", UNoise3AimConstraintType.SURFACE_LIMITER, true, flag);
      BooleanValue booleanValue = new BooleanValue(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_expandbb", false, this.getSettingsGroup());
      this.expandbb = (BooleanValue)booleanValue.withDisplayName("Expand bounding box");
      booleanValue = new BooleanValue(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_disablewhennearedge", false, this.getSettingsGroup());
      this.disablewhennearedge = (BooleanValue)booleanValue.withDisplayName("Disable when near edge");
      RandomizedRangeSettings randomizedRangeSettings2 = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_nearedgethreshold", 0.1, 0.2, 0.0, 0.5, 3, this.getSettingsGroup()).withDisplayNamePrefix("Near edge threshold");
      BooleanValue disablewhennearedge2 = this.disablewhennearedge;
      this.nearedgethreshold = ((RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> disablewhennearedge2.getValue())).withoutNotInARow();
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double[] effectiveAngleBounds = this.getEffectiveAngleBounds(uNoise3AimContext, this.expandbb.getValue());
      if (uNoise3AimContext.vertical) {
         double d = HitboxAngleUtil.inverseLerpRange(effectiveAngleBounds, uNoise3AimContext.currentAngle);
         if (this.disablewhennearedge.getValue()) {
            if (d < 0.0 || d > 1.0) {
               this.nearedgethreshold.nextValue();
               return Double.NaN;
            }

            if (d < this.nearedgethreshold.getLastValue() || d > 1.0 - this.nearedgethreshold.getLastValue()) {
               this.nearedgethreshold.nextValue();
               return Double.NaN;
            }
         }

         if (!(d < 0.0) && !(d > 1.0)) {
            return uNoise3AimContext.currentAngle;
         } else {
            return Math.abs(effectiveAngleBounds[0] - uNoise3AimContext.currentAngle) > Math.abs(effectiveAngleBounds[1] - uNoise3AimContext.currentAngle) ? effectiveAngleBounds[1] : effectiveAngleBounds[0];
         }
      } else {
         double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(effectiveAngleBounds[0], effectiveAngleBounds[1], uNoise3AimContext.currentAngle);
         if (this.disablewhennearedge.getValue()) {
            if (normalizedYawPosition < 0.0 || normalizedYawPosition > 1.0) {
               this.nearedgethreshold.nextValue();
               return Double.NaN;
            }

            if (normalizedYawPosition < this.nearedgethreshold.getLastValue() || normalizedYawPosition > 1.0 - this.nearedgethreshold.getLastValue()) {
               this.nearedgethreshold.nextValue();
               return Double.NaN;
            }
         }

         if (!(normalizedYawPosition < 0.0) && !(normalizedYawPosition > 1.0)) {
            return uNoise3AimContext.currentAngle;
         } else {
            return HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(effectiveAngleBounds[0]), uNoise3AimContext.currentAngle) > HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(effectiveAngleBounds[1]), uNoise3AimContext.currentAngle) ? effectiveAngleBounds[1] : effectiveAngleBounds[0];
         }
      }
   }

}
