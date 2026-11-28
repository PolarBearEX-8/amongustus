package net.augustus.utils.rotation;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class BoxAngleBoundsUtil {

   public static final double lerpRange(double[] doubles, double d) {
      return doubles[0] + (doubles[1] - doubles[0]) * d;
   }

   public static double getNormalizedYawPosition(AxisAlignedBB box, Vec3 vec, double d) {
      double[] sortedYawRange = getSortedYawRange(vec, box);
      double d2 = normalizeAngle360(d);
      double shortestAngularDistance = getShortestAngularDistance(d2, sortedYawRange[0]);
      double d4 = getShortestAngularDistance(sortedYawRange[0], sortedYawRange[1]);
      double d5 = getShortestAngularDistance(sortedYawRange[1], d2);
      double d7 = shortestAngularDistance / (sortedYawRange[1] - sortedYawRange[0]);
      if (d5 > d4) {
         d7 = -d7;
      }

      return d7;
   }

   public static double[] getSortedYawRange(Vec3 vec, AxisAlignedBB box) {
      double[] extremeYawAngles = getExtremeYawAngles(vec, box);
      double d = normalizeAngle360(extremeYawAngles[0]);
      double d2 = normalizeAngle360(extremeYawAngles[1]);
      if (Math.abs(d - d2) > 180.0) {
         if (d < d2) {
            d += 360.0;
         } else {
            d2 += 360.0;
         }
      }

      double d4 = Math.min(d, d2);
      double d5 = Math.max(d, d2);
      return new double[]{d4, d5, extremeYawAngles[2]};
   }

   public static double[] getExtremeYawAngles(Vec3 vec, AxisAlignedBB box) {
      Vec3[] vecs = new Vec3[4];
      vecs[0] = new Vec3(box.minX, box.minY, box.minZ);
      vecs[1] = new Vec3(box.minX, box.minY, box.maxZ);
      vecs[2] = new Vec3(box.maxX, box.minY, box.maxZ);
      vecs[3] = new Vec3(box.maxX, box.minY, box.minZ);
      double[] doubles = new double[4];

      for (int i = 0; i < vecs.length; i++) {
         Vec3 vec3 = vecs[i].subtract(vec);
         doubles[i] = Math.toDegrees(Math.atan2(vec3.zCoord, vec3.xCoord));
      }

      double d2 = 0.0;
      double d3 = 0.0 / 0.0;
      double d4 = 0.0 / 0.0;
      int n = -1;

      for (int j = 0; j < doubles.length; j++) {
         for (int k = 0; k < doubles.length; k++) {
            if (j != k) {
               double d5 = doubles[j];
               double d6 = doubles[k];
               double d7 = Math.abs(d5 - d6);
               double d8 = Math.min(d7, 360.0 - d7);
               if (d8 > d2) {
                  d2 = d8;
                  d3 = d5;
                  d4 = d6;
                  n = (byte)j << 4 | (byte)k;
               }
            }
         }
      }

      return new double[]{d3 - 90.0, d4 - 90.0, (double)n};
   }

   public static double[] findMinMaxWithIndices(double[] doubles) {
      double d = 1000.0;
      double d2 = -1000.0;
      int n = 0;
      int n2 = 0;
      int n3 = 0;

      for (double d3 : doubles) {
         if (d3 < d) {
            d = d3;
            n = n3;
         }

         if (d3 > d2) {
            d2 = d3;
            n2 = n3;
         }

         n3++;
      }

      return new double[]{d, d2, (double)n, (double)n2};
   }

   public static double[] getPitchRange(Vec3 vec, AxisAlignedBB box) {
      Vec3[] vecs = new Vec3[8];
      vecs[0] = new Vec3(box.minX, box.minY, box.minZ);
      vecs[1] = new Vec3(box.minX, box.minY, box.maxZ);
      vecs[2] = new Vec3(box.maxX, box.minY, box.maxZ);
      vecs[3] = new Vec3(box.maxX, box.minY, box.minZ);
      vecs[4] = new Vec3(box.minX, box.maxY, box.minZ);
      vecs[5] = new Vec3(box.minX, box.maxY, box.maxZ);
      vecs[6] = new Vec3(box.maxX, box.maxY, box.maxZ);
      vecs[7] = new Vec3(box.maxX, box.maxY, box.minZ);
      double[] doubles = new double[8];

      for (int i = 0; i < vecs.length; i++) {
         Vec3 vec3 = vecs[i].subtract(vec);
         doubles[i] = -Math.toDegrees(Math.atan2(vec3.yCoord, Math.sqrt(vec3.xCoord * vec3.xCoord + vec3.zCoord * vec3.zCoord)));
      }

      return findMinMaxWithIndices(doubles);
   }

   public static double normalizeAngle360(double d) {
      d %= 360.0;
      if (d >= 360.0) {
         d -= 360.0;
      }

      if (d < 0.0) {
         d += 360.0;
      }

      return d;
   }

   public static final double getNormalizedPitchPosition(AxisAlignedBB box, Vec3 vec, double d) {
      double[] pitchRange = getPitchRange(vec, box);
      return (d - pitchRange[0]) / (pitchRange[1] - pitchRange[0]);
   }

   public static double getShortestAngularDistance(double d, double d2) {
      double d3 = Math.abs(d - d2);
      return Math.min(d3, 360.0 - d3);
   }
}
