package net.augustus.utils.rotation;

import java.util.Random;
import net.minecraft.util.AxisAlignedBB;

public class NoisyBoxAimTracker {
   private static final double POSITION_NOISE_SCALE = 0.1;
   private static double velocityZ = 0.0;
   private static double aimZ = 0.0;
   private static final double MAX_AXIS_DELTA = 30.0;
   private static final Random random = new Random();
   private static double velocityY = 0.0;
   private static final double ANGLE_NOISE_SCALE = 0.1;
   private static final double CLAMP_SKIP_CHANCE = 0.1;
   private static final double MAX_COMBINED_DELTA = 60.0;
   private static double aimY = 0.0;
   private static double velocityX = 0.0;
   private static double aimX = 0.0;

   private static double clamp(double value, double min, double max) {
      return Math.max(min, Math.min(max, value));
   }

   public static double[] computeRotationDelta(double d, double d2, double d3, double d4, double d5, AxisAlignedBB box) {
      double d6 = aimX + velocityX + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      double d7 = aimY + velocityY + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      double d8 = aimZ + velocityZ + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      if (random.nextDouble() >= 0.1) {
         d6 = clamp(d6, box.minX, box.maxX);
         d7 = clamp(d7, box.minY, box.maxY);
         d8 = clamp(d8, box.minZ, box.maxZ);
      }

      double d9 = d6 - d;
      double d10 = d7 - d2;
      double d11 = d8 - d3;
      double[] doubles = computeClampedAngleDelta(d4, d5, Math.toDegrees(Math.atan2(d11, d9)) - 90.0, -Math.toDegrees(Math.atan2(d10, Math.sqrt(d9 * d9 + d11 * d11))));
      double d14 = Math.sqrt(doubles[0] * doubles[0] + doubles[1] * doubles[1]);
      if (d14 > 60.0) {
         double d15 = 60.0 / d14;
         doubles[0] *= d15;
         doubles[1] *= d15;
      }

      velocityX *= 0.9;
      velocityY *= 0.9;
      velocityZ *= 0.9;
      aimX = d6;
      aimY = d7;
      aimZ = d8;
      return doubles;
   }

   public static double[] computeClampedAngleDelta(double d, double d2, double d3, double d4) {
      double d5 = wrapAngle180(d3 - d);
      double d6 = wrapAngle180(d4 - d2);
      d5 = clamp(d5, -30.0, 30.0);
      d6 = clamp(d6, -30.0, 30.0);
      d5 += (random.nextDouble() - 0.5) * 2.0 * 0.1;
      d6 += (random.nextDouble() - 0.5) * 2.0 * 0.1;
      return new double[]{d5, d6};
   }

   private static double wrapAngle180(double d) {
      d %= 360.0;
      if (d > 180.0) {
         d -= 360.0;
      } else if (d < -180.0) {
         d += 360.0;
      }

      return d;
   }
}
