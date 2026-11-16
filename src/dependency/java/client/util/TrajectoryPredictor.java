package client.util;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TrajectoryPredictor {
   public static double computeValue2(double posX, double posY, double posZ) {
      double offset = computeValue(posZ);
      double doubleItem = getDouble4(offset, computeValue(posX));
      double doubleItem2 = getDouble4(computeValue(posX), computeValue(posY));
      double doubleItem3 = getDouble4(computeValue(posY), offset);
      double offset2 = posY - posX;
      double offset3 = doubleItem / offset2;
      if (!Double.isFinite(offset3)) {
         offset3 = 0.5;
      }

      if (doubleItem3 > doubleItem2) {
         offset3 = -offset3;
      }

      return offset3;
   }

   public static double[] getCoordOffsetArray(Vec3 vec, AxisAlignedBB boundingBox) {
      Vec3[] vecArray = new Vec3[]{
         new Vec3(boundingBox.minX, boundingBox.minY, boundingBox.minZ),
         new Vec3(boundingBox.minX, boundingBox.minY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.minY, boundingBox.minZ)
      };
      double[] offsetArray = new double[4];

      for (int index = 0; index < vecArray.length; index++) {
         Vec3 vec3 = vecArray[index].getVec311(vec);
         double offset = Math.toDegrees(Math.atan2(vec3.zCoord, vec3.xCoord));
         offsetArray[index] = offset;
      }

      double offset2 = 0.0;
      double offset3 = Double.NaN;
      double offset4 = Double.NaN;
      int count = -1;

      for (int index2 = 0; index2 < offsetArray.length; index2++) {
         for (int index3 = 0; index3 < offsetArray.length; index3++) {
            if (index2 != index3) {
               double offset5 = offsetArray[index2];
               double offset6 = offsetArray[index3];
               double offset7 = Math.abs(offset5 - offset6);
               double offset8 = Math.min(offset7, 360.0 - offset7);
               if (offset8 > offset2) {
                  offset2 = offset8;
                  offset3 = offset5;
                  offset4 = offset6;
                  count = (byte)index2 << 4 | (byte)index3;
               }
            }
         }
      }

      return new double[]{offset3 - 90.0, offset4 - 90.0, (double)count};
   }

   public static final double computeValueByDouble(double posX, double posY, double posZ) {
      return posX + (posY - posX) * posZ;
   }

   public static double getCoordinate(AxisAlignedBB boundingBox, Vec3 vec, double offset) {
      double[] coordOffsetArray = getCoordOffsetArray2(vec, boundingBox);
      return computeValue2(coordOffsetArray[0], coordOffsetArray[1], offset);
   }

   public static double[] getCoordOffsetArray2(Vec3 vec, AxisAlignedBB boundingBox) {
      double offset = Double.NaN;
      double offset2 = 0.0;
      return resolveDoubleArray(vec, boundingBox, offset2, offset);
   }

   public static double computeValue(double posX, double posY, double posZ) {
      double[] offsetArray = resolveDoubleArray(posX, posY);
      return computeValue2(offsetArray[0], offsetArray[1], posZ);
   }

   public static double getDouble4(double offset, double offset2) {
      double offset3 = Math.abs(offset - offset2);
      return Math.min(offset3, 360.0 - offset3);
   }

   public static double[] resolveDoubleArray(double[] offsetArray) {
      double offset = 1000.0;
      double offset2 = -1000.0;
      int count = 0;
      int count2 = 0;
      int count3 = 0;

      for (double offset3 : offsetArray) {
         if (offset3 < offset) {
            offset = offset3;
            count = count3;
         }

         if (offset3 > offset2) {
            offset2 = offset3;
            count2 = count3;
         }

         count3++;
      }

      return new double[]{offset, offset2, (double)count, (double)count2};
   }

   public static final double computeValue(double[] offsetArray, double offset) {
      double offset2 = offset - offsetArray[0];
      double offset3 = offsetArray[1] - offsetArray[0];
      return offset2 / offset3;
   }

   public static double computeValue(AxisAlignedBB boundingBox, Vec3 vec, double posX, double posY, double posZ) {
      double[] offsetArray = resolveDoubleArray(vec, boundingBox, posY, posZ);
      return computeValue2(offsetArray[0], offsetArray[1], posX);
   }

   public static double[] resolveDoubleArray(double offset, double offset2) {
      double offset3 = computeValue(offset);
      double offset4 = computeValue(offset2);
      if (Math.abs(offset3 - offset4) > 180.0) {
         if (offset3 < offset4) {
            offset3 += 360.0;
         } else {
            offset4 += 360.0;
         }
      }

      double offset5 = Math.min(offset3, offset4);
      double offset6 = Math.max(offset3, offset4);
      return new double[]{offset5, offset6};
   }

   public static final double getCoordinate(double[] offsetArray, double offset) {
      return offsetArray[0] + (offsetArray[1] - offsetArray[0]) * offset;
   }

   public static double[] getCoordOffsetArrayByVec3(Vec3 vec, AxisAlignedBB boundingBox) {
      Vec3[] vecArray = new Vec3[]{
         new Vec3(boundingBox.minX, boundingBox.minY, boundingBox.minZ),
         new Vec3(boundingBox.minX, boundingBox.minY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.minY, boundingBox.minZ),
         new Vec3(boundingBox.minX, boundingBox.maxY, boundingBox.minZ),
         new Vec3(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ),
         new Vec3(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ)
      };
      double[] offsetArray = new double[8];

      for (int index = 0; index < vecArray.length; index++) {
         Vec3 vec3 = vecArray[index].getVec311(vec);
         double doubleItem = MathHelper.getDouble7(
            -Math.toDegrees(Math.atan2(vec3.yCoord, Math.sqrt(vec3.xCoord * vec3.xCoord + vec3.zCoord * vec3.zCoord))), -90.0, 90.0
         );
         offsetArray[index] = doubleItem;
      }

      return resolveDoubleArray(offsetArray);
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

   public static final double getCoordinate(AxisAlignedBB boundingBox, Vec3 vec, long value, double offset) {
      double[] coordOffsetArrayByVec = getCoordOffsetArrayByVec3(vec, boundingBox);
      double offset2 = offset - coordOffsetArrayByVec[0];
      double offset3 = coordOffsetArrayByVec[1] - coordOffsetArrayByVec[0];
      return offset2 / offset3;
   }

   public static double[] resolveDoubleArray(Vec3 vec, AxisAlignedBB boundingBox, double offset, double offset2) {
      double[] coordOffsetArray = getCoordOffsetArray(vec, boundingBox);
      double offset3 = computeValue(coordOffsetArray[0]);
      double offset4 = computeValue(coordOffsetArray[1]);
      double offset5 = Math.abs(offset3 - offset4);
      if (offset5 > 180.0) {
         if (offset3 < offset4) {
            offset3 += 360.0;
         } else {
            offset4 += 360.0;
         }
      }

      double offset6 = Math.min(offset3, offset4);
      double offset7 = Math.max(offset3, offset4);
      return Double.isFinite(offset2)
         ? new double[]{(offset7 + offset6) / 2.0 - offset2, (offset7 + offset6) / 2.0 + offset2, coordOffsetArray[2]}
         : new double[]{offset6 - offset, offset7 + offset, coordOffsetArray[2]};
   }
}
