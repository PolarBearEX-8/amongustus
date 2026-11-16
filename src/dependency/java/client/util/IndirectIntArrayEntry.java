package client.util;

import java.util.Random;

public class IndirectIntArrayEntry {
   public static int[][] countArray2 = new int[][]{
      {1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}
   };
   public static final double value2 = Math.sqrt(3.0);
   public int[] countArray = new int[512];
   public double value5;
   public double value3;
   public double value;
   public static final double value4 = 0.5 * (value2 - 1.0);
   public static final double value6 = (3.0 - value2) / 6.0;

   private static int computeCount(double offset) {
      return offset > 0.0 ? (int)offset : (int)offset - 1;
   }

   private static double computeValue(int[] countArray3, double offset, double offset2) {
      return (double)countArray3[0] * offset + (double)countArray3[1] * offset2;
   }

   public void applyDoubleArray(double[] offsetArray, double offset, double offset2, int count, int count2, double posX, double posY, double posZ) {
      int count3 = 0;

      for (int index = 0; index < count2; index++) {
         double offset3 = (offset2 + (double)index) * posY + this.value3;

         for (int index2 = 0; index2 < count; index2++) {
            double offset4 = (offset + (double)index2) * posX + this.value5;
            double offset5 = (offset4 + offset3) * value4;
            int count4 = computeCount(offset4 + offset5);
            int count5 = computeCount(offset3 + offset5);
            double offset6 = (double)(count4 + count5) * value6;
            double offset7 = (double)count4 - offset6;
            double offset8 = (double)count5 - offset6;
            double offset9 = offset4 - offset7;
            double offset10 = offset3 - offset8;
            byte byteValue;
            byte byteValue2;
            if (offset9 > offset10) {
               byteValue = 1;
               byteValue2 = 0;
            } else {
               byteValue = 0;
               byteValue2 = 1;
            }

            double offset11 = offset9 - (double)byteValue + value6;
            double offset12 = offset10 - (double)byteValue2 + value6;
            double offset13 = offset9 - 1.0 + 2.0 * value6;
            double offset14 = offset10 - 1.0 + 2.0 * value6;
            int count6 = count4 & 0xFF;
            int count7 = count5 & 0xFF;
            int count8 = this.countArray[count6 + this.countArray[count7]] % 12;
            int count9 = this.countArray[count6 + byteValue + this.countArray[count7 + byteValue2]] % 12;
            int count10 = this.countArray[count6 + 1 + this.countArray[count7 + 1]] % 12;
            double offset15 = 0.5 - offset9 * offset9 - offset10 * offset10;
            double offset16;
            if (offset15 < 0.0) {
               offset16 = 0.0;
            } else {
               double offset17 = offset15 * offset15;
               offset16 = offset17 * offset17 * computeValue(countArray2[count8], offset9, offset10);
            }

            double offset18 = 0.5 - offset11 * offset11 - offset12 * offset12;
            double offset19;
            if (offset18 < 0.0) {
               offset19 = 0.0;
            } else {
               double offset20 = offset18 * offset18;
               offset19 = offset20 * offset20 * computeValue(countArray2[count9], offset11, offset12);
            }

            double offset21 = 0.5 - offset13 * offset13 - offset14 * offset14;
            double offset22;
            if (offset21 < 0.0) {
               offset22 = 0.0;
            } else {
               double offset23 = offset21 * offset21;
               offset22 = offset23 * offset23 * computeValue(countArray2[count10], offset13, offset14);
            }

            int count11 = count3++;
            offsetArray[count11] += 70.0 * (offset16 + offset19 + offset22) * posZ;
         }
      }
   }

   public double computeValue(double offset, double offset2) {
      double offset3 = 0.5 * (value2 - 1.0);
      double offset4 = (offset + offset2) * offset3;
      int count = computeCount(offset + offset4);
      int count2 = computeCount(offset2 + offset4);
      double offset5 = (3.0 - value2) / 6.0;
      double offset6 = (double)(count + count2) * offset5;
      double offset7 = (double)count - offset6;
      double offset8 = (double)count2 - offset6;
      double offset9 = offset - offset7;
      double offset10 = offset2 - offset8;
      byte byteValue;
      byte byteValue2;
      if (offset9 > offset10) {
         byteValue = 1;
         byteValue2 = 0;
      } else {
         byteValue = 0;
         byteValue2 = 1;
      }

      double offset11 = offset9 - (double)byteValue + offset5;
      double offset12 = offset10 - (double)byteValue2 + offset5;
      double offset13 = offset9 - 1.0 + 2.0 * offset5;
      double offset14 = offset10 - 1.0 + 2.0 * offset5;
      int count3 = count & 0xFF;
      int count4 = count2 & 0xFF;
      int count5 = this.countArray[count3 + this.countArray[count4]] % 12;
      int count6 = this.countArray[count3 + byteValue + this.countArray[count4 + byteValue2]] % 12;
      int count7 = this.countArray[count3 + 1 + this.countArray[count4 + 1]] % 12;
      double offset15 = 0.5 - offset9 * offset9 - offset10 * offset10;
      double offset16;
      if (offset15 < 0.0) {
         offset16 = 0.0;
      } else {
         double offset17 = offset15 * offset15;
         offset16 = offset17 * offset17 * computeValue(countArray2[count5], offset9, offset10);
      }

      double offset18 = 0.5 - offset11 * offset11 - offset12 * offset12;
      double offset19;
      if (offset18 < 0.0) {
         offset19 = 0.0;
      } else {
         double offset20 = offset18 * offset18;
         offset19 = offset20 * offset20 * computeValue(countArray2[count6], offset11, offset12);
      }

      double offset21 = 0.5 - offset13 * offset13 - offset14 * offset14;
      double offset22;
      if (offset21 < 0.0) {
         offset22 = 0.0;
      } else {
         double offset23 = offset21 * offset21;
         offset22 = offset23 * offset23 * computeValue(countArray2[count7], offset13, offset14);
      }

      return 70.0 * (offset16 + offset19 + offset22);
   }

   public IndirectIntArrayEntry(Random random) {
      this.value5 = random.nextDouble() * 256.0;
      this.value3 = random.nextDouble() * 256.0;
      this.value = random.nextDouble() * 256.0;
      int count = 0;

      while (count < 256) {
         this.countArray[count] = count++;
      }

      for (int index = 0; index < 256; index++) {
         int count2 = random.nextInt(256 - index) + index;
         int count3 = this.countArray[index];
         this.countArray[index] = this.countArray[count2];
         this.countArray[count2] = count3;
         this.countArray[index + 256] = this.countArray[index];
      }
   }

   public IndirectIntArrayEntry() {
      this(new Random());
   }
}
