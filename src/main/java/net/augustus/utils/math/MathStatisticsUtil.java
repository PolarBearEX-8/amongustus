package net.augustus.utils.math;

import net.augustus.modules.combat.killaura.HitVecCandidate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class MathStatisticsUtil {

   public static float averageFloat(List list) {
      return sumFloat(list) / (float)(list.size() == 0 ? 1 : list.size());
   }

   public static float sumFloat(List list) {
      float f = 0.0F;

      for (float f2 : (Iterable<Float>)(list)) {
         f += f2;
      }

      return f;
   }

   public static double correlationDouble(List list, List list2) {
      if (list.size() != list2.size()) {
         return 0.0 / 0.0;
      } else {
         double d = stdDevDouble(list);
         double d2 = stdDevDouble(list2);
         return covarianceDouble(list, list2) / (d * d2);
      }
   }

   public static String formatRounded(double d, int n) {
      double d2 = Math.pow(10.0, (double)n);
      return String.valueOf((double)Math.round(d * d2) / d2);
   }

   public static double covarianceFloat(List list, List list2) {
      if (list.size() != list2.size()) {
         return 0.0 / 0.0;
      } else {
         double d = (double)averageFloat(list);
         double d2 = (double)averageFloat(list2);
         double d3 = 0.0;

         for (int i = 0; i < list.size(); i++) {
            double d4 = (double)((Float)list.get(i)).floatValue() - d;
            double d5 = (double)((Float)list2.get(i)).floatValue() - d2;
            d3 += d4 * d5;
         }

         return d3 / (double)list.size();
      }
   }

   public static List sampleBoxSurfacePoints(AxisAlignedBB box, HitVecCandidate hitVecCandidate) {
      double maxZ = box.maxZ;
      double maxY = box.maxY;
      double maxX = box.maxX;
      double minZ = box.minZ;
      double minY = box.minY;
      return sampleBoxSurfacePoints(hitVecCandidate, box.minX, minY, minZ, maxX, maxY, maxZ);
   }

   public static double modularDistance(double d, double d2) {
      double d3 = d % d2;
      if (d3 < 0.0) {
         d3 += d2;
      }

      return d3 > d2 / 2.0 ? d2 - d3 : d3;
   }

   public static ArrayList computeKernelDensityEstimate(ArrayList list, int n) {
      if (list != null && !list.isEmpty()) {
         if (n <= 1) {
            throw new IllegalArgumentException("nPoints muss größer als 1 sein.");
         } else {
            int size = list.size();
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            list.forEach(obj -> descriptiveStatistics.addValue(((Number)obj).doubleValue()));
            double mean = descriptiveStatistics.getMean();
            double standardDeviation = descriptiveStatistics.getStandardDeviation();
            double min = descriptiveStatistics.getMin();
            double max = descriptiveStatistics.getMax();
            if (standardDeviation == 0.0) {
               standardDeviation = Math.abs(mean * 1.0E-6) + 1.0E-6;
            }

            double percentile = descriptiveStatistics.getPercentile(25.0);
            double d = descriptiveStatistics.getPercentile(75.0) - percentile;
            if (d <= 1.0E-9) {
               d = standardDeviation * 1.34;
            }

            double d2 = 1.06 * Math.min(standardDeviation, d / 1.34) * Math.pow((double)size, -0.2);
            double d3 = min - 3.0 * d2;
            double d5 = ((max + 3.0 * d2) - d3) / (double)(n - 1);
            ArrayList list2 = new ArrayList(n);
            NormalDistribution normalDistribution = new NormalDistribution(0.0, 1.0);
            int size2 = list.size();

            for (int i = 0; i < n; i++) {
               double d6 = d3 + (double)i * d5;
               double d7 = 0.0;

               for (int j = 0; j < size2; j++) {
                  d7 += normalDistribution.density((d6 - (Double)list.get(j)) / d2);
               }

               double d8 = d7 / ((double)size * d2);
               list2.add(new DataPoint2D(d6, d8));
            }

            return list2;
         }
      } else {
         throw new IllegalArgumentException("Daten dürfen nicht null oder leer sein.");
      }
   }

   public static double computeExcessKurtosis(ArrayList list) {
      double d = 0.0;
      int n = 0;

      for (int i = 0; i < list.size(); i++) {
         d += ((Number)list.get(i)).doubleValue();
         n++;
      }

      if (n < 4) {
         return 0.0;
      } else {
         double d2 = d / (double)n;
         double d3 = 0.0;
         double d4 = 0.0;

         for (int j = 0; j < list.size(); j++) {
            double d5 = ((Number)list.get(j)).doubleValue() - d2;
            double d6 = d5 * d5;
            d3 += d6;
            d4 += d6 * d6;
         }

         if (d3 == 0.0) {
            return 0.0;
         } else {
            double d7 = d3 / (double)(n - 1);
            double d8 = (double)n * ((double)n + 1.0) / (((double)n - 1.0) * ((double)n - 2.0) * ((double)n - 3.0));
            double d9 = 3.0 * ((double)n - 1.0) * ((double)n - 1.0) / (((double)n - 2.0) * ((double)n - 3.0));
            return d8 * (d4 / (d7 * d7)) - d9;
         }
      }
   }

   public static long maxLong(List list) {
      return (Long)Collections.max(list);
   }

   public static double stdDevInt(List list) {
      double d = averageInt(list);
      double d2 = 0.0;
      Iterator it = list.iterator();

      while (it.hasNext()) {
         double d3 = (double)((Integer)it.next()).intValue();
         d2 += (d3 - d) * (d3 - d);
      }

      return Math.sqrt(d2 / (double)list.size());
   }

   public static double angleBetweenDegrees(Vec3 vec, Vec3 vec2) {
      return Math.toDegrees(Math.acos((vec.xCoord * vec2.xCoord + vec.yCoord * vec2.yCoord + vec.zCoord * vec2.zCoord) / (Math.sqrt(vec.xCoord * vec.xCoord + vec.yCoord * vec.yCoord + vec.zCoord * vec.zCoord) * Math.sqrt(vec2.xCoord * vec2.xCoord + vec2.yCoord * vec2.yCoord + vec2.zCoord * vec2.zCoord))));
   }

   public static List sampleBoxSurfacePoints(HitVecCandidate hitVecCandidate, double d, double d2, double d3, double d4, double d5, double d6) {
      ArrayList list = new ArrayList();
      double d7 = 0.06;

      for (double i = d; i <= d4; i += d7) {
         for (double j = d2; j <= d5; j += d7) {
            if (hitVecCandidate.z > 0.0) {
               list.add(new HitVecCandidate(i, j, d3));
            } else {
               list.add(new HitVecCandidate(i, j, d6));
            }
         }
      }

      if (d2 > hitVecCandidate.y || d5 < hitVecCandidate.y) {
         for (double k = d; k <= d4; k += d7) {
            for (double m = d3 + d7; m < d6; m += d7) {
               if (d2 > hitVecCandidate.y) {
                  list.add(new HitVecCandidate(k, d2, m));
               } else {
                  list.add(new HitVecCandidate(k, d5, m));
               }
            }
         }
      }

      for (double idx = d2 + d7; idx <= d5 - d7; idx += d7) {
         for (double index = d3 + d7; index < d6; index += d7) {
            if (hitVecCandidate.x > 0.0) {
               list.add(new HitVecCandidate(d, idx, index));
            } else {
               list.add(new HitVecCandidate(d4, idx, index));
            }
         }
      }

      return list;
   }

   public static double computeAlternateKurtosis(ArrayList list) {
      double d = 0.0;
      int n = 0;

      for (Number number : (Iterable<Number>)(list)) {
         d += number.doubleValue();
         n++;
      }

      if ((double)n < 4.0) {
         return 0.0;
      } else {
         double d2 = d / (double)n;
         double d3 = 0.0;
         double d4 = 0.0;

         for (Number number2 : (Iterable<Number>)(list)) {
            d3 += Math.pow(d2 - number2.doubleValue(), 2.0);
            d4 += Math.pow(d2 - number2.doubleValue(), 4.0);
         }

         double d5 = (double)n * ((double)n + 1.0) / (((double)n - 1.0) * ((double)n - 2.0) * ((double)n - 3.0));
         double d6 = 3.0 * Math.pow((double)n - 1.0, 2.0) / (((double)n - 2.0) * ((double)n - 3.0));
         return d5 * (d4 / Math.pow(d3 / d, 2.0)) - d6;
      }
   }

   public static float maxFloat(List list) {
      return (Float)Collections.max(list);
   }

   public static boolean arePointsAdjacent(HitVecCandidate hitVecCandidate, HitVecCandidate hitVecCandidate2) {
      return Math.sqrt(Math.pow(hitVecCandidate.x - hitVecCandidate2.x, 2.0) + Math.pow(hitVecCandidate.y - hitVecCandidate2.y, 2.0) + Math.pow(hitVecCandidate.z - hitVecCandidate2.z, 2.0)) <= 0.06;
   }

   public static int sumInt(List list) {
      int n = 0;

      for (int n2 : (Iterable<Integer>)(list)) {
         n += n2;
      }

      return n;
   }

   public static double averageLong(List list) {
      return (double)sumLong(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static boolean isWithinTolerance(double d, double d2, double d3) {
      return !(Math.abs(d - d2) > d3);
   }

   public static int minInt(List list) {
      return (Integer)Collections.min(list);
   }

   public static double roundToDecimalPlaces(double d, int n) {
      double d2 = Math.pow(10.0, (double)n);
      return (double)Math.round(d * d2) / d2;
   }

   public static double minDouble(List list) {
      return (Double)Collections.min(list);
   }

   public static long sumLong(List list) {
      long l = 0L;

      for (long l2 : (Iterable<Long>)(list)) {
         l += l2;
      }

      return l;
   }

   public static float minFloat(List list) {
      return (Float)Collections.min(list);
   }

   public static double correlationFloat(List list, List list2) {
      if (list.size() != list2.size()) {
         return 0.0 / 0.0;
      } else {
         double d = (double)stdDevFloat(list);
         double d2 = (double)stdDevFloat(list2);
         return covarianceFloat(list, list2) / (d * d2);
      }
   }

   public static double lerp(double start, double end, double progress) {
      return start + (end - start) * progress;
   }

   public static double angleDifference360(double d, double d2) {
      double d3 = Math.abs(d - d2);
      return Math.min(d3, 360.0 - d3);
   }

   public static double normalizeAngle360(double d) {
      Object obj = null;
      d %= 360.0;
      double d2;
      int n = (d2 = d - 360.0) == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
      if (obj == null) {
         if (n >= 0) {
            d -= 360.0;
         }

         if (obj != null) {
            return d;
         }

         double d3;
         n = (d3 = d - 0.0) == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
      }

      if (n < 0) {
         d += 360.0;
      }

      return d;
   }

   public static long factorial(long l) {
      long l2 = 1L;

      for (int i = 2; (long)i <= l; i++) {
         l2 *= (long)i;
      }

      return l2;
   }

   public static double averageInt(List list) {
      return (double)sumInt(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static double stdDevLong(List list) {
      double d = averageLong(list);
      double d2 = 0.0;
      Iterator it = list.iterator();

      while (it.hasNext()) {
         double d3 = (double)((Long)it.next()).longValue();
         d2 += (d3 - d) * (d3 - d);
      }

      return Math.sqrt(d2 / (double)list.size());
   }

   public static double averageDouble(List list) {
      return sumDouble(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static double maxDouble(List list) {
      return (Double)Collections.max(list);
   }

   public static long minLong(List list) {
      return (Long)Collections.min(list);
   }

   public static float stdDevFloat(List list) {
      float f = averageFloat(list);
      float f2 = 0.0F;

      for (float f3 : (Iterable<Float>)(list)) {
         f2 += (f3 - f) * (f3 - f);
      }

      return (float)Math.sqrt((double)f2 / (double)list.size());
   }

   public static double covarianceDouble(List list, List list2) {
      if (list.size() != list2.size()) {
         return 0.0 / 0.0;
      } else {
         double d = averageDouble(list);
         double d2 = averageDouble(list2);
         double d3 = 0.0;

         for (int i = 0; i < list.size(); i++) {
            double d4 = (Double)list.get(i) - d;
            double d5 = (Double)list2.get(i) - d2;
            d3 += d4 * d5;
         }

         return d3 / (double)list.size();
      }
   }

   public static double lawOfSinesSide(double d, double d2, double d3) {
      double d4 = 180.0 - d - d2;
      double d5 = Math.toRadians(d);
      double d6 = Math.toRadians(d4);
      return d3 * Math.sin(d5) / Math.sin(d6);
   }

   public static boolean removeSentinelMinusOne(ArrayList list) {
      boolean flag = list.contains(-1);
      if (null == null) {
         if (flag) {
            list.remove(Integer.valueOf(-1));
            return true;
         }

         flag = false;
      }

      return flag;
   }

   public static double stdDevDouble(List list) {
      double d = averageDouble(list);
      double d2 = 0.0;

      for (double d3 : (Iterable<Double>)(list)) {
         d2 += (d3 - d) * (d3 - d);
      }

      return Math.sqrt(d2 / (double)list.size());
   }

   public static HitVecCandidate findDensestHitVecCandidate(List list, int n) {
      int n2 = 0;
      HitVecCandidate hitVecCandidate = null;

      for (HitVecCandidate hitVecCandidate2 : (Iterable<HitVecCandidate>)(list)) {
         if (hitVecCandidate2.visibilityFlags[n]) {
            int n3 = 0;

            for (HitVecCandidate hitVecCandidate3 : (Iterable<HitVecCandidate>)(list)) {
               if (hitVecCandidate3.visibilityFlags[n] && arePointsAdjacent(hitVecCandidate2, hitVecCandidate3)) {
                  n3++;
               }
            }

            if (n3 > n2) {
               n2 = n3;
               hitVecCandidate = hitVecCandidate2;
            }

            if (n3 == 4) {
               return hitVecCandidate;
            }
         }
      }

      return hitVecCandidate;
   }

   public static float lerpFloat(double d, double d2, double d3) {
      return (float)(d + (d2 - d) * d3);
   }

   public static int maxInt(List list) {
      return (Integer)Collections.max(list);
   }

   public static long binomialCoefficient(long l, long l2) {
      long l3 = 1L;
      long l4 = Math.min(l2, l - l2);
      long l5 = Math.max(l2, l - l2);

      for (long i = l5; i <= l; i++) {
         l3 *= i;
      }

      return l3 / factorial(l4);
   }

   public static double sumDouble(List list) {
      double d = 0.0;

      for (double d2 : (Iterable<Double>)(list)) {
         d += d2;
      }

      return d;
   }

   public static float lerp(float start, float end, float progress) {
      return start + (end - start) * progress;
   }




}
