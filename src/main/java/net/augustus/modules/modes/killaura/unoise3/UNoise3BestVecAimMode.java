package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.settings.BooleanValue;
import net.minecraft.util.Vec3;

public class UNoise3BestVecAimMode extends UNoise3AimMode {
   private BooleanValue onlyifnotaimingatcorner;

   public UNoise3BestVecAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "BestVec", UNoise3AimConstraintType.POINT, true, flag);
      this.onlyifnotaimingatcorner = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), this.getSettingsGroup().getName() + "_onlyifnotaimingatcorner", false, this.getSettingsGroup()).withDisplayName("Only if not aiming at a corner");
   }

   @Override
   public double sampleWeight(UNoise3AimContext uNoise3AimContext) {
      double d = super.sampleWeight(uNoise3AimContext);
      return Double.isFinite(this.computeTargetAngle(uNoise3AimContext)) ? d : 0.0;
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      Vec3 vec = uNoise3AimContext.closestTargetPoint.subtract(uNoise3AimContext.playerEyePos);
      if (uNoise3AimContext.vertical) {
         double d = -Math.atan2(vec.yCoord, vec.horizontalLength()) / 3.141592653589793 * 180.0;
         if (this.isSurfaceRestrictionActive()) {
            d = this.clampToUnoccludedBounds(uNoise3AimContext, d);
         }

         return this.onlyifnotaimingatcorner.getValue() && d != 0.0 ? 0.0 / 0.0 : d;
      } else {
         double d2 = Math.atan2(vec.zCoord, vec.xCoord) / 3.141592653589793 * 180.0 - 90.0;
         if (this.isSurfaceRestrictionActive()) {
            d2 = this.clampToUnoccludedBounds(uNoise3AimContext, d2);
         }

         if (this.onlyifnotaimingatcorner.getValue()) {
            return MathStatisticsUtil.modularDistance(d2, 90.0) > 1.0E-4 ? d2 : 0.0 / 0.0;
         } else {
            return d2;
         }
      }
   }

}
