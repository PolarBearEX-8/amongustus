package net.augustus.modules.modes.killaura.unoise3;


import net.augustus.utils.rotation.HitboxAngleUtil;
public class UNoise3MidAimMode extends UNoise3AimMode {

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double d = 0.5;
      double d2 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d);
      if (this.isSurfaceRestrictionActive()) {
         return this.clampToUnoccludedBounds(uNoise3AimContext, d2);
      } else {
         return d2;
      }
   }

   public UNoise3MidAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "Mid", UNoise3AimConstraintType.POINT, false, flag);
   }

}
