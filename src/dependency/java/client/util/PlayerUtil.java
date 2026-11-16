package client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class PlayerUtil {
   public static double computeValue4(List list) {
      return ((Double)Collections.min(list)).doubleValue();
   }

   public static double computeValue2(List list, List list2) {
      if (list.size() != list2.size()) {
         return Double.NaN;
      } else {
         double offset = (double)computeFactor3(list);
         double offset2 = (double)computeFactor3(list2);
         return computeValue(list, list2) / (offset * offset2);
      }
   }

   public static long computeTimestampByTime(long value, long value2) {
      long value3 = 1L;
      long value4 = Math.min(value2, value - value2);

      for (long index = Math.max(value2, value - value2); index <= value; index++) {
         value3 *= index;
      }

      return value3 / computeTimestamp(value4);
   }

   public static float computeFactor3(List list) {
      float factor = computeFactorByList(list);
      float factor2 = 0.0F;

      for (float factor3 : (Iterable<Float>)(Iterable)(list)) {
         factor2 += (factor3 - factor) * (factor3 - factor);
      }

      return (float)Math.sqrt((double)factor2 / (double)list.size());
   }

   public static int computeCountByList(List list) {
      return ((Integer)Collections.min(list)).intValue();
   }

   public static double getDouble(double posX, double posY, double posZ) {
      return posX + (posY - posX) * posZ;
   }

   public static float computeFactorByList(List list) {
      return computeFactor(list) / (float)(list.size() == 0 ? 1 : list.size());
   }

   public static int computeCount(List list) {
      return ((Integer)Collections.max(list)).intValue();
   }

   public static double computeValueByList(List list) {
      double coordinate = getCoordinate(list);
      double offset = 0.0;
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         double offset2 = (double)((Integer)iterator.next()).intValue();
         offset += (offset2 - coordinate) * (offset2 - coordinate);
      }

      return Math.sqrt(offset / (double)list.size());
   }

   public static float computeFactor(List list) {
      float factor = 0.0F;

      for (float factor2 : (Iterable<Float>)(Iterable)(list)) {
         factor += factor2;
      }

      return factor;
   }

   public static PredictedHitPoint getTertiaryDoubleEntry(List list, int count) {
      int count2 = 0;
      PredictedHitPoint predictedHitPoint = null;

      for (PredictedHitPoint predictedHitPoint2 : (Iterable<PredictedHitPoint>)(Iterable)(list)) {
         if (predictedHitPoint2.booleanValueArray[count]) {
            int count3 = 0;

            for (PredictedHitPoint predictedHitPoint3 : (Iterable<PredictedHitPoint>)(Iterable)(list)) {
               if (predictedHitPoint3.booleanValueArray[count] && matchesPredictedHitPoint(predictedHitPoint2, predictedHitPoint3)) {
                  count3++;
               }
            }

            if (count3 > count2) {
               count2 = count3;
               predictedHitPoint = predictedHitPoint2;
            }

            if (count3 == 4) {
               return predictedHitPoint;
            }
         }
      }

      return predictedHitPoint;
   }

   public static double computeValue(List list, List list2) {
      if (list.size() != list2.size()) {
         return Double.NaN;
      } else {
         double offset = (double)computeFactorByList(list);
         double offset2 = (double)computeFactorByList(list2);
         double offset3 = 0.0;

         for (int index = 0; index < list.size(); index++) {
            double offset4 = (double)((Float)list.get(index)).floatValue() - offset;
            double offset5 = (double)((Float)list2.get(index)).floatValue() - offset2;
            offset3 += offset4 * offset5;
         }

         return offset3 / (double)list.size();
      }
   }

   public static double computeValue(List list) {
      double offset = computeValue5(list);
      double offset2 = 0.0;
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         double offset3 = (double)((Long)iterator.next()).longValue();
         offset2 += (offset3 - offset) * (offset3 - offset);
      }

      return Math.sqrt(offset2 / (double)list.size());
   }

   public static float getFloat(double posX, double posY, double posZ) {
      return (float)(posX + (posY - posX) * posZ);
   }

   public static long computeTimestamp(long value) {
      long value2 = 1L;

      for (int index = 2; (long)index <= value; index++) {
         value2 *= (long)index;
      }

      return value2;
   }

   public static double computeValue3(List list) {
      double offset = computeValue2(list);
      double offset2 = 0.0;

      for (double offset3 : (Iterable<Double>)(Iterable)(list)) {
         offset2 += (offset3 - offset) * (offset3 - offset);
      }

      return Math.sqrt(offset2 / (double)list.size());
   }

   public static boolean matchesArrayList(ArrayList list) {
      if (list.contains(-1)) {
         list.remove(Integer.valueOf(-1));
         return true;
      } else {
         return false;
      }
   }

   public static int computeCount2(List list) {
      int count = 0;

      for (int count2 : (Iterable<Integer>)(Iterable)(list)) {
         count += count2;
      }

      return count;
   }

   public static float computeFactor2(List list) {
      return ((Float)Collections.min(list)).floatValue();
   }

   public static double computeValue6(List list) {
      return ((Double)Collections.max(list)).doubleValue();
   }

   public static long computeTimestampByList(List list) {
      return ((Long)Collections.min(list)).longValue();
   }

   public static double computeValueByDouble(double offset, double offset2) {
      double offset3 = offset % offset2;
      if (offset3 < 0.0) {
         offset3 += offset2;
      }

      return offset3 > offset2 / 2.0 ? offset2 - offset3 : offset3;
   }

   public static double computeValue7(List list) {
      double offset = 0.0;

      for (double offset2 : (Iterable<Double>)(Iterable)(list)) {
         offset += offset2;
      }

      return offset;
   }

   public static List resolveList(PredictedHitPoint predictedHitPoint, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      ArrayList list = new ArrayList();
      double offset = 0.06;

      for (double offset2 = posX; offset2 <= posX4; offset2 += offset) {
         for (double offset3 = posX2; offset3 <= posY; offset3 += offset) {
            if (predictedHitPoint.coordOffset > 0.0) {
               list.add(new PredictedHitPoint(offset2, offset3, posX3));
            } else {
               list.add(new PredictedHitPoint(offset2, offset3, posZ));
            }
         }
      }

      if (posX2 > predictedHitPoint.coordOffset1 || posY < predictedHitPoint.coordOffset1) {
         for (double offset4 = posX; offset4 <= posX4; offset4 += offset) {
            for (double offset5 = posX3 + offset; offset5 < posZ; offset5 += offset) {
               if (posX2 > predictedHitPoint.coordOffset1) {
                  list.add(new PredictedHitPoint(offset4, posX2, offset5));
               } else {
                  list.add(new PredictedHitPoint(offset4, posY, offset5));
               }
            }
         }
      }

      for (double offset6 = posX2 + offset; offset6 <= posY - offset; offset6 += offset) {
         for (double offset7 = posX3 + offset; offset7 < posZ; offset7 += offset) {
            if (predictedHitPoint.coordOffset2 > 0.0) {
               list.add(new PredictedHitPoint(posX, offset6, offset7));
            } else {
               list.add(new PredictedHitPoint(posX4, offset6, offset7));
            }
         }
      }

      return list;
   }

   public static double getDouble3(double posX, double posY, double posZ) {
      double offset = 180.0 - posX - posY;
      double offset2 = Math.toRadians(posX);
      double offset3 = Math.toRadians(offset);
      return posZ * Math.sin(offset2) / Math.sin(offset3);
   }

   public static double getCoordinate(Vec3 vec, Vec3 vec2) {
      double offset = vec.xCoord * vec2.xCoord + vec.yCoord * vec2.yCoord + vec.zCoord * vec2.zCoord;
      double offset2 = Math.sqrt(vec.xCoord * vec.xCoord + vec.yCoord * vec.yCoord + vec.zCoord * vec.zCoord);
      double offset3 = Math.sqrt(vec2.xCoord * vec2.xCoord + vec2.yCoord * vec2.yCoord + vec2.zCoord * vec2.zCoord);
      return Math.toDegrees(Math.acos(offset / (offset2 * offset3)));
   }

   public static long getTimestamp(List list) {
      return ((Long)Collections.max(list)).longValue();
   }

   public static double computeValue(double offset, double offset2) {
      double offset3 = Math.abs(offset - offset2);
      return Math.min(offset3, 360.0 - offset3);
   }

   public static double computeValueByList(List list, List list2) {
      if (list.size() != list2.size()) {
         return Double.NaN;
      } else {
         double offset = computeValue2(list);
         double offset2 = computeValue2(list2);
         double offset3 = 0.0;

         for (int index = 0; index < list.size(); index++) {
            double offset4 = (Double)list.get(index) - offset;
            double offset5 = (Double)list2.get(index) - offset2;
            offset3 += offset4 * offset5;
         }

         return offset3 / (double)list.size();
      }
   }

   public static List getList(AxisAlignedBB boundingBox, PredictedHitPoint predictedHitPoint) {
      byte byteValue = 0;
      double maxZ = boundingBox.maxZ;
      double maxY = boundingBox.maxY;
      double maxX = boundingBox.maxX;
      double minZ = boundingBox.minZ;
      double minY = boundingBox.minY;
      double minX = boundingBox.minX;
      return resolveList(predictedHitPoint, minX, minY, minZ, maxX, maxY, maxZ);
   }

   public static long computeTimestamp(List list) {
      long value = 0L;

      for (long value2 : (Iterable<Long>)(Iterable)(list)) {
         value += value2;
      }

      return value;
   }

   public static double getCoordinate(List list) {
      return (double)computeCount2(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static double computeValue5(List list) {
      return (double)computeTimestamp(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static float getFloat2(float posX, float posY, float posZ) {
      return posX + (posY - posX) * posZ;
   }

   public static boolean matchesPredictedHitPoint(PredictedHitPoint predictedHitPoint, PredictedHitPoint predictedHitPoint2) {
      return Math.sqrt(
            Math.pow(predictedHitPoint.coordOffset2 - predictedHitPoint2.coordOffset2, 2.0)
               + Math.pow(predictedHitPoint.coordOffset1 - predictedHitPoint2.coordOffset1, 2.0)
               + Math.pow(predictedHitPoint.coordOffset - predictedHitPoint2.coordOffset, 2.0)
         )
         <= 0.06;
   }

   public static float computeFactor4(List list) {
      return ((Float)Collections.max(list)).floatValue();
   }

   public static boolean checkCondition(double posX, double posY, double posZ) {
      return !(Math.abs(posX - posY) > posZ);
   }

   public static double computeValue3(List list, List list2) {
      if (list.size() != list2.size()) {
         return Double.NaN;
      } else {
         double offset = computeValue3(list);
         double offset2 = computeValue3(list2);
         return computeValueByList(list, list2) / (offset * offset2);
      }
   }

   public static double getDouble5(double offset, int count) {
      double offset2 = Math.pow(10.0, (double)count);
      return (double)Math.round(offset * offset2) / offset2;
   }

   public static double computeValue2(List list) {
      return computeValue7(list) / (double)(list.size() == 0 ? 1 : list.size());
   }

   public static String getString(double offset, int count) {
      double offset2 = Math.pow(10.0, (double)count);
      return String.valueOf((double)Math.round(offset * offset2) / offset2);
   }

   public static double computeValue(double offset) {
      double offset2 = offset % 360.0;
      if (offset2 >= 360.0) {
         offset2 -= 360.0;
      }

      if (offset2 < 0.0) {
         offset2 += 360.0;
      }

      return offset2;
   }
}
