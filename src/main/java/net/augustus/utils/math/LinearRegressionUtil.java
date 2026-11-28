package net.augustus.utils.math;

import java.util.ArrayList;

public class LinearRegressionUtil {
   public static double[] fitLine(ArrayList list) {
      double d = 0.0;
      double d2 = 0.0;

      for (int i = 0; i < list.size(); i++) {
         d += ((DataPoint2D)list.get(i)).x;
         d2 += ((DataPoint2D)list.get(i)).y;
      }

      d /= (double)list.size();
      d2 /= (double)list.size();
      double d3 = 0.0;
      double d4 = 0.0;

      for (int j = 0; j < list.size(); j++) {
         DataPoint2D dataPoint2D = (DataPoint2D)list.get(j);
         d3 += (dataPoint2D.x - d) * (dataPoint2D.y - d2);
         d4 += Math.pow(dataPoint2D.x - d, 2.0);
      }

      double d5 = d3 / d4;
      double d6 = d2 - d5 * d;
      return new double[]{d5, d6};
   }

   public static double computeRSquared(ArrayList list, double[] doubles) {
      double d = 0.0;

      for (int i = 0; i < list.size(); i++) {
         d += ((DataPoint2D)list.get(i)).y;
      }

      d /= (double)list.size();
      double d2 = 0.0;
      double d3 = 0.0;

      for (int j = 0; j < list.size(); j++) {
         DataPoint2D dataPoint2D = (DataPoint2D)list.get(j);
         d2 += Math.pow(dataPoint2D.x * doubles[0] + doubles[1] - d, 2.0);
         d3 += Math.pow(dataPoint2D.y - d, 2.0);
      }

      return d2 / d3;
   }
}
