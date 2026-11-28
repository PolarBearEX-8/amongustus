package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class UNoise3EsoundAimMode extends UNoise3AimMode {
   private final EsoundAimAxisState verticalState;
   private final EsoundAimAxisState horizontalState;
   private final DoubleValue lockTolerance;

   private double computeHorizontalAngle(UNoise3AimContext uNoise3AimContext) {
      return this.updateAxisAim(uNoise3AimContext, this.horizontalState, false);
   }

   private double interpolateAxisAngle(EsoundAimAxisState esoundAimAxisState, boolean flag) {
      double d = (double)(esoundAimAxisState.interpolationStep + 1) / (double)EsoundAimAxisState.accessInterpolationDuration(esoundAimAxisState);
      double d2;
      if (flag) {
         d2 = esoundAimAxisState.startAngle + (esoundAimAxisState.targetAngle - esoundAimAxisState.startAngle) * d;
      } else {
         double d3 = (double)MathHelper.wrapAngleTo180_float((float)esoundAimAxisState.startAngle);
         double d5 = ((double)MathHelper.wrapAngleTo180_float((float)esoundAimAxisState.targetAngle)) - d3;
         if (d5 > 180.0) {
            d5 -= 360.0;
         } else if (d5 < -180.0) {
            d5 += 360.0;
         }

         d2 = d3 + d5 * d;
         d2 = (double)MathHelper.wrapAngleTo180_float((float)d2);
      }

      esoundAimAxisState.interpolationStep++;
      return d2;
   }

   @Override
   public void stepNoise() {
      super.stepNoise();
      this.horizontalState.onTick();
      this.verticalState.onTick();
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      return uNoise3AimContext.vertical ? this.computeVerticalAngle(uNoise3AimContext) : this.computeHorizontalAngle(uNoise3AimContext);
   }

   private double updateAxisAim(UNoise3AimContext uNoise3AimContext, EsoundAimAxisState esoundAimAxisState, boolean flag) {
      double[] effectiveAngleBounds = this.getEffectiveAngleBounds(uNoise3AimContext, true);
      boolean isOutsideAngleBounds = this.isOutsideAngleBounds(uNoise3AimContext, effectiveAngleBounds);
      esoundAimAxisState.ticksSinceRetarget++;
      if (isOutsideAngleBounds && esoundAimAxisState.locked || esoundAimAxisState.ticksSinceRetarget > EsoundAimAxisState.accessRetargetIntervalTicks(esoundAimAxisState)) {
         esoundAimAxisState.normalizedAimOffset = this.sampleTargetOffset(uNoise3AimContext, effectiveAngleBounds);
         esoundAimAxisState.locked = false;
         esoundAimAxisState.startAngle = uNoise3AimContext.currentAngle;
         esoundAimAxisState.angleBounds[0] = effectiveAngleBounds[0];
         esoundAimAxisState.angleBounds[1] = effectiveAngleBounds[1];
         esoundAimAxisState.interpolationStep = 0;
         esoundAimAxisState.ticksSinceRetarget = 0;
         EsoundAimAxisState.setRetargetIntervalTicks(esoundAimAxisState, DefaultRandomGenerator.instance.nextInt((short)1003, (short)1005));
         EsoundAimAxisState.setInterpolationDuration(esoundAimAxisState, DefaultRandomGenerator.instance.nextInt((byte)4, (byte)7));
      }

      if (!esoundAimAxisState.locked) {
         esoundAimAxisState.angleBounds[0] = effectiveAngleBounds[0];
         esoundAimAxisState.angleBounds[1] = effectiveAngleBounds[1];
         if (flag) {
            Vec3 vec = uNoise3AimContext.closestTargetPoint.subtract(uNoise3AimContext.playerEyePos);
            esoundAimAxisState.targetAngle = -Math.atan2(vec.yCoord, vec.horizontalLength()) / 3.141592653589793 * 180.0;
            double d = HitboxAngleUtil.inverseLerpRange(effectiveAngleBounds, esoundAimAxisState.targetAngle);
            d += esoundAimAxisState.normalizedAimOffset;
            esoundAimAxisState.targetAngle = HitboxAngleUtil.lerpRange(effectiveAngleBounds, d);
         } else {
            esoundAimAxisState.targetAngle = HitboxAngleUtil.lerpRange(effectiveAngleBounds, esoundAimAxisState.normalizedAimOffset);
         }

         if (!this.isWithinLockTolerance(uNoise3AimContext, esoundAimAxisState.normalizedAimOffset, flag) && esoundAimAxisState.ticksSinceRetarget <= EsoundAimAxisState.accessInterpolationDuration(esoundAimAxisState) + 2) {
            if (esoundAimAxisState.interpolationStep < EsoundAimAxisState.accessInterpolationDuration(esoundAimAxisState)) {
               return this.interpolateAxisAngle(esoundAimAxisState, flag);
            }

            return esoundAimAxisState.targetAngle;
         }

         esoundAimAxisState.locked = true;
         esoundAimAxisState.ticksSinceRetarget = 1000;
      }

      return uNoise3AimContext.currentAngle;
   }

   private boolean isOutsideAngleBounds(UNoise3AimContext uNoise3AimContext, double[] doubles) {
      double d = this.computeNormalizedPosition(uNoise3AimContext, doubles);
      return d < 0.0 || d > 1.0;
   }

   private boolean isWithinLockTolerance(UNoise3AimContext uNoise3AimContext, double d, boolean flag) {
      double value = this.lockTolerance.getValue();
      double d2 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d);
      double d3;
      if (flag) {
         d3 = Math.abs(uNoise3AimContext.currentAngle - d2);
      } else {
         d3 = Math.abs(HitboxAngleUtil.getShortestAngularDistance(uNoise3AimContext.currentAngle, d2));
      }

      return d3 <= value;
   }

   private double computeNormalizedPosition(UNoise3AimContext uNoise3AimContext, double[] doubles) {
      if (uNoise3AimContext.vertical) {
         return HitboxAngleUtil.inverseLerpRange(doubles, uNoise3AimContext.currentAngle);
      } else {
         return HitboxAngleUtil.getNormalizedYawPosition(doubles[0], doubles[1], uNoise3AimContext.currentAngle);
      }
   }

   private double computeVerticalAngle(UNoise3AimContext uNoise3AimContext) {
      return this.updateAxisAim(uNoise3AimContext, this.verticalState, true);
   }

   private double sampleTargetOffset(UNoise3AimContext uNoise3AimContext, double[] doubles) {
      if (uNoise3AimContext.vertical) {
         double d = 0.1;
         double d2 = -0.1;
         return DefaultRandomGenerator.instance.nextDouble(d2, d);
      } else {
         double d3 = this.computeNormalizedPosition(uNoise3AimContext, doubles);
         double d4;
         if (d3 < 0.1) {
            double d5 = 0.15;
            double d6 = 0.0;
            d4 = 0.7 + DefaultRandomGenerator.instance.nextDouble(d6, d5);
         } else if (d3 > 0.9) {
            double d7 = 0.15;
            double d8 = 0.0;
            d4 = 0.15 + DefaultRandomGenerator.instance.nextDouble(d8, d7);
         } else {
            double d9 = 0.35;
            double d10 = -0.35;
            d4 = 0.5 + DefaultRandomGenerator.instance.nextDouble(d10, d9);
         }

         return d4;
      }
   }

   public UNoise3EsoundAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "Esound", UNoise3AimConstraintType.SURFACE_LIMITER, true, flag);
      this.lockTolerance = new DoubleValue(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_lock_tolerance", 1.5, 0.5, 3.0, 2, this.getSettingsGroup()).named("Lock Tolerance");
      this.horizontalState = new EsoundAimAxisState(null);
      this.verticalState = new EsoundAimAxisState(null);
   }

}
