package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.utils.math.Vec2d;
import java.util.Random;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.MathHelper;

public class BezierRotationStepper implements MC {
   private double previousVelocityScale;
   public boolean adaptCurveIfNeeded;
   public double aimSpeed = 8.0;
   public static final int SUBSTEPS_PER_TICK = 3;
   public boolean debugEnabled;
   public double preDecelerationFactor = 1.0;
   public double postDecelerationFactor = 1.0;
   public Random random;
   private float stepPhase;

   public BezierRotationStepper() {
      this.adaptCurveIfNeeded = false;
      this.stepPhase = 0.0F;
      this.random = new Random();
      this.debugEnabled = false;
   }

   public RotationKinematicState step(RotationKinematicState rotationKinematicState, BezierCurve2D bezierCurve2D, double d) {
      this.stepPhase += 0.05F;
      Vec2d angularVelocity = rotationKinematicState.getAngularVelocity();
      Vec2d vec2d = bezierCurve2D.evaluate(MathHelper.clamp_double(bezierCurve2D.getProgress(), 0.0, 1.0));
      Vec2d vec2d2 = bezierCurve2D.evaluate(1.0);
      double d4 = 1000.0;
      double progress = bezierCurve2D.getProgress();

      for (double i = bezierCurve2D.getProgress(); i <= 1.0; i += 0.01) {
         Vec2d vec2d3 = bezierCurve2D.evaluate(i);
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(rotationKinematicState.getYaw(), vec2d3.x);
         double d6 = rotationKinematicState.getPitch() - vec2d3.y;
         double d7 = shortestSignedAngleDelta * shortestSignedAngleDelta + d6 * d6;
         if (d4 > d7 || vec2d == null) {
            vec2d = vec2d3;
            d4 = d7;
            progress = i;
         }
      }

      if (this.adaptCurveIfNeeded) {
         bezierCurve2D.setProgress(progress + 1.0 / (double)(bezierCurve2D.durationTicks + 1));
      }

      d4 = 1.0;
      progress = rotationKinematicState.getYaw();
      double pitch = rotationKinematicState.getPitch();
      double d9 = MathHelper.wrapAngleTo180_double(vec2d.x);
      double d10 = MathHelper.clamp_double(vec2d.y, -90.0, 90.0);
      double d11 = MathHelper.wrapAngleTo180_double(vec2d2.x);
      double d12 = MathHelper.clamp_double(vec2d2.y, -90.0, 90.0);
      double d13 = Math.signum(EntityBoundsUtil.getShortestSignedAngleDelta(rotationKinematicState.getYaw(), d9));
      double d14 = Math.signum(d10 - pitch);
      boolean flag = false;
      boolean flag2 = false;

      for (int j = 0; j < 3; j++) {
         double d15 = EntityBoundsUtil.getShortestSignedAngleDelta(progress, d9);
         double d16 = d10 - pitch;
         double d17 = EntityBoundsUtil.getShortestSignedAngleDelta(progress, d11);
         double d18 = d12 - pitch;
         if (bezierCurve2D.stationary) {
            d15 = 0.0;
            d16 = 0.0;
            d17 = 0.0;
            d18 = 0.0;
         }

         Vec2d vec2d4 = new Vec2d(d15 / 0.15, d16 / 0.15);
         Vec2d vec2d5 = new Vec2d(d17 / 0.15, d18 / 0.15);
         if (Math.signum(vec2d5.x) != Math.signum(vec2d4.x)) {
            vec2d5.x = vec2d4.x;
         }

         if (Math.signum(vec2d5.y) != Math.signum(vec2d4.y)) {
            vec2d5.y = vec2d4.y;
         }

         double d19 = Math.signum(vec2d4.x) != Math.signum(angularVelocity.x) ? this.aimSpeed * this.postDecelerationFactor : this.aimSpeed;
         double d20 = Math.signum(vec2d4.y) != Math.signum(angularVelocity.y) ? this.aimSpeed * this.postDecelerationFactor : this.aimSpeed;
         d19 *= this.random.nextDouble() * 0.2 + 0.8;
         d20 *= this.random.nextDouble() * 0.2 + 0.8;
         double d21 = this.aimSpeed * this.preDecelerationFactor;
         double d22 = Math.abs(d / 0.15 / 3.0 * d4);
         if (Math.signum(d) != Math.signum(vec2d4.x)) {
            d22 = 0.0;
         }

         double d23 = Math.min(d22 + 1.4142135623730951 * Math.sqrt(d21 * Math.abs(vec2d5.x)), Math.abs(vec2d5.x));
         double d24 = Math.min(1.4142135623730951 * d21 * Math.sqrt(Math.abs(vec2d5.y) / (2.0 * d21 - 1.0)), Math.abs(vec2d5.y));
         double d25 = d23 * Math.signum(vec2d4.x) - angularVelocity.x;
         double d26 = d24 * Math.signum(vec2d4.y) - angularVelocity.y;
         Vec2d vec2d6 = new Vec2d(
            MathHelper.clamp_double(
                  Math.abs(d25),
                  0.0,
                  (Math.signum(d25) != Math.signum(vec2d4.x) || vec2d4.x == 0.0) && Math.signum(angularVelocity.x) == Math.signum(vec2d4.x)
                     ? 1.7976931348623157E308
                     : d19
               )
               * Math.signum(d25),
            MathHelper.clamp_double(
                  Math.abs(d26),
                  0.0,
                  (Math.signum(d26) != Math.signum(vec2d4.y) || vec2d4.y == 0.0) && Math.signum(angularVelocity.y) == Math.signum(vec2d4.y)
                     ? 1.7976931348623157E308
                     : d20
               )
               * Math.signum(d26)
         );
         if (vec2d6.x == Math.signum(angularVelocity.x) && vec2d6.y == Math.signum(angularVelocity.y)) {
            vec2d6.x = vec2d6.x / bezierCurve2D.speedScale;
            vec2d6.y = vec2d6.y / bezierCurve2D.speedScale;
         }

         if (Double.isNaN(vec2d6.x)) {
            vec2d6.x = 0.0;
            vec2d6.y = 0.0;
            angularVelocity.x = 0.0;
            angularVelocity.y = 0.0;
         }

         if (Double.isNaN(vec2d6.y)) {
            vec2d6.y = 0.0;
         }

         Vec2d vec2d7 = new Vec2d(angularVelocity.x, angularVelocity.y);
         vec2d7 = new Vec2d(vec2d7.x + (flag ? 0.0 : vec2d6.x), vec2d7.y + (flag2 ? 0.0 : vec2d6.y));
         angularVelocity = angularVelocity.scale(1.0 - d4).add(vec2d7.scale(d4));
         if (!flag) {
            progress = MathHelper.wrapAngleTo180_double(progress + angularVelocity.x * 0.15 * d4);
         }

         if (!flag2) {
            pitch = MathHelper.clamp_double(pitch + angularVelocity.y * 0.15 * d4, -90.0, 90.0);
         }

         double d28 = EntityBoundsUtil.getShortestSignedAngleDelta(progress, d9);
         double d29 = d10 - pitch;
         if (!flag && Math.signum(d28) != d13 && Math.signum(angularVelocity.x) == d13 && !bezierCurve2D.stationary) {
            progress = d9;
            angularVelocity.x = d22 * Math.signum(d);
            flag = true;
         }

         if (!flag2 && Math.signum(d29) != d14 && Math.signum(angularVelocity.y) == d14 && !bezierCurve2D.stationary) {
            pitch = d10;
            angularVelocity.y = 0.0;
            flag2 = true;
         }

         if (this.debugEnabled) {
         }
      }

      if (this.debugEnabled) {
      }

      return new RotationKinematicState(angularVelocity, progress, pitch);
   }
}
