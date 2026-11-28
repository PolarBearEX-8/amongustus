package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.settings.BooleanValue;
import net.minecraft.util.MathHelper;

public class UNoise3NearestToAimGoalMode extends UNoise3AimMode {
   private BooleanValue expandbb;

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double[] effectiveAngleBounds = this.getEffectiveAngleBounds(uNoise3AimContext, this.expandbb.getValue());
      if (uNoise3AimContext.vertical) {
         double d = HitboxAngleUtil.inverseLerpRange(effectiveAngleBounds, uNoise3AimContext.lastGoalAngle);
         if (!(d < 0.0) && !(d > 1.0)) {
            return uNoise3AimContext.lastGoalAngle;
         } else {
            return Math.abs(effectiveAngleBounds[0] - uNoise3AimContext.lastGoalAngle) > Math.abs(effectiveAngleBounds[1] - uNoise3AimContext.lastGoalAngle) ? effectiveAngleBounds[1] : effectiveAngleBounds[0];
         }
      } else {
         double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(effectiveAngleBounds[0], effectiveAngleBounds[1], uNoise3AimContext.lastGoalAngle);
         if (!(normalizedYawPosition < 0.0) && !(normalizedYawPosition > 1.0)) {
            return uNoise3AimContext.lastGoalAngle;
         } else {
            return HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(effectiveAngleBounds[0]), uNoise3AimContext.lastGoalAngle) > HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(effectiveAngleBounds[1]), uNoise3AimContext.lastGoalAngle) ? effectiveAngleBounds[1] : effectiveAngleBounds[0];
         }
      }
   }

   public UNoise3NearestToAimGoalMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "NearestVec to current aimgoal", UNoise3AimConstraintType.SURFACE_LIMITER, true, flag);
      this.expandbb = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_expandbb", false, this.getSettingsGroup()).withDisplayName("Expand bounding box");
   }

}
