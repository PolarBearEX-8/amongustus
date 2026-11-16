package client.util;

import java.util.Random;

public class AuxiliaryClientManagerService extends PrimaryClientManager {
   public IndirectIntArrayEntry[] indirectIntArrayEntryArray;
   public int count;

   public double computeValue(double offset, double offset2) {
      double offset3 = 0.0;
      double offset4 = 1.0;

      for (int index = 0; index < this.count; index++) {
         offset3 += this.indirectIntArrayEntryArray[index].computeValue(offset * offset4, offset2 * offset4) / offset4;
         offset4 /= 2.0;
      }

      return offset3;
   }

   public double[] resolveDoubleArray(double[] offsetArray, double offset, double offset2, int count2, int count3, double posX, double posX2, double posY, double posZ) {
      double[] offsetArray2 = offsetArray;
      if (offsetArray != null && offsetArray.length >= count2 * count3) {
         for (int index = 0; index < offsetArray2.length; index++) {
            offsetArray2[index] = 0.0;
         }
      } else {
         offsetArray2 = new double[count2 * count3];
      }

      double offset3 = 1.0;
      double offset4 = 1.0;

      for (int index2 = 0; index2 < this.count; index2++) {
         this.indirectIntArrayEntryArray[index2].applyDoubleArray(offsetArray2, offset, offset2, count2, count3, posX * offset4 * offset3, posX2 * offset4 * offset3, 0.55 / offset3);
         offset4 *= posY;
         offset3 *= posZ;
      }

      return offsetArray2;
   }

   public double[] resolveDoubleArray(double[] offsetArray, double offset, double offset2, int count2, int count3, double posX, double posY, double posZ) {
      return this.resolveDoubleArray(offsetArray, offset, offset2, count2, count3, posX, posY, posZ, 0.5);
   }

   public AuxiliaryClientManagerService(Random random, int count2) {
      this.count = count2;
      this.indirectIntArrayEntryArray = new IndirectIntArrayEntry[count2];

      for (int index = 0; index < count2; index++) {
         this.indirectIntArrayEntryArray[index] = new IndirectIntArrayEntry(random);
      }
   }
}
