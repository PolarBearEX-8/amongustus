package client.util;

import java.util.Random;

public class InternalClientManagerService extends PrimaryClientManager {
   public int[] countArray = new int[512];
   public double value;
   public double value2;
   public double value3;
   public static final double[] valueArray = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
   public static final double[] valueArray2 = new double[]{1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0};
   public static final double[] valueArray3 = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};
   public static final double[] valueArray4 = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
   public static final double[] valueArray5 = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};

   public final double computeValue(int count, double offset, double offset2) {
      int count2 = count & 15;
      return valueArray4[count2] * offset + valueArray5[count2] * offset2;
   }

   public final double computeValue(int count, double posX, double posY, double posZ) {
      int count2 = count & 15;
      return valueArray[count2] * posX + valueArray2[count2] * posY + valueArray3[count2] * posZ;
   }

   public InternalClientManagerService() {
      this(new Random());
   }

   public InternalClientManagerService(Random random) {
      this.value = random.nextDouble() * 256.0;
      this.value2 = random.nextDouble() * 256.0;
      this.value3 = random.nextDouble() * 256.0;
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

   public final double computeValue(double posX, double posY, double posZ) {
      return posY + posX * (posZ - posY);
   }

   public void applyDoubleArray(
      double[] offsetArray, double posX, double posY, double posZ, int count, int count2, int count3, double posX2, double posX3, double posY2, double posZ2
   ) {
      if (count2 == 1) {
         int count4 = 0;
         double offset = 1.0 / posZ2;

         for (int index = 0; index < count; index++) {
            double offset2 = posX + (double)index * posX2 + this.value;
            int count5 = (int)offset2;
            if (offset2 < (double)count5) {
               count5--;
            }

            int count6 = count5 & 0xFF;
            double offset3 = offset2 - (double)count5;
            double offset4 = offset3 * offset3 * offset3 * (offset3 * (offset3 * 6.0 - 15.0) + 10.0);

            for (int index2 = 0; index2 < count3; index2++) {
               double offset5 = posZ + (double)index2 * posY2 + this.value3;
               int count7 = (int)offset5;
               if (offset5 < (double)count7) {
                  count7--;
               }

               int count8 = count7 & 0xFF;
               double offset6 = offset5 - (double)count7;
               double offset7 = offset6 * offset6 * offset6 * (offset6 * (offset6 * 6.0 - 15.0) + 10.0);
               int count9 = this.countArray[count6] + 0;
               int count10 = this.countArray[count9] + count8;
               int count11 = this.countArray[count6 + 1] + 0;
               int count12 = this.countArray[count11] + count8;
               double offset8 = this.computeValue(
                  offset4, this.computeValue(this.countArray[count10], offset3, offset6), this.computeValue(this.countArray[count12], offset3 - 1.0, 0.0, offset6)
               );
               double offset9 = this.computeValue(
                  offset4,
                  this.computeValue(this.countArray[count10 + 1], offset3, 0.0, offset6 - 1.0),
                  this.computeValue(this.countArray[count12 + 1], offset3 - 1.0, 0.0, offset6 - 1.0)
               );
               double offset10 = this.computeValue(offset7, offset8, offset9);
               int count13 = count4++;
               offsetArray[count13] += offset10 * offset;
            }
         }
      } else {
         int count14 = 0;
         double offset11 = 1.0 / posZ2;
         int count15 = -1;
         double offset12 = 0.0;
         double offset13 = 0.0;
         double offset14 = 0.0;
         double offset15 = 0.0;

         for (int index3 = 0; index3 < count; index3++) {
            double offset16 = posX + (double)index3 * posX2 + this.value;
            int count16 = (int)offset16;
            if (offset16 < (double)count16) {
               count16--;
            }

            int count17 = count16 & 0xFF;
            double offset17 = offset16 - (double)count16;
            double offset18 = offset17 * offset17 * offset17 * (offset17 * (offset17 * 6.0 - 15.0) + 10.0);

            for (int index4 = 0; index4 < count3; index4++) {
               double offset19 = posZ + (double)index4 * posY2 + this.value3;
               int count18 = (int)offset19;
               if (offset19 < (double)count18) {
                  count18--;
               }

               int count19 = count18 & 0xFF;
               double offset20 = offset19 - (double)count18;
               double offset21 = offset20 * offset20 * offset20 * (offset20 * (offset20 * 6.0 - 15.0) + 10.0);

               for (int index5 = 0; index5 < count2; index5++) {
                  double offset22 = posY + (double)index5 * posX3 + this.value2;
                  int count20 = (int)offset22;
                  if (offset22 < (double)count20) {
                     count20--;
                  }

                  int count21 = count20 & 0xFF;
                  double offset23 = offset22 - (double)count20;
                  double offset24 = offset23 * offset23 * offset23 * (offset23 * (offset23 * 6.0 - 15.0) + 10.0);
                  if (index5 == 0 || count21 != count15) {
                     count15 = count21;
                     int count22 = this.countArray[count17] + count21;
                     int count23 = this.countArray[count22] + count19;
                     int count24 = this.countArray[count22 + 1] + count19;
                     int count25 = this.countArray[count17 + 1] + count21;
                     int count26 = this.countArray[count25] + count19;
                     int count27 = this.countArray[count25 + 1] + count19;
                     offset12 = this.computeValue(
                        offset18,
                        this.computeValue(this.countArray[count23], offset17, offset23, offset20),
                        this.computeValue(this.countArray[count26], offset17 - 1.0, offset23, offset20)
                     );
                     offset13 = this.computeValue(
                        offset18,
                        this.computeValue(this.countArray[count24], offset17, offset23 - 1.0, offset20),
                        this.computeValue(this.countArray[count27], offset17 - 1.0, offset23 - 1.0, offset20)
                     );
                     offset14 = this.computeValue(
                        offset18,
                        this.computeValue(this.countArray[count23 + 1], offset17, offset23, offset20 - 1.0),
                        this.computeValue(this.countArray[count26 + 1], offset17 - 1.0, offset23, offset20 - 1.0)
                     );
                     offset15 = this.computeValue(
                        offset18,
                        this.computeValue(this.countArray[count24 + 1], offset17, offset23 - 1.0, offset20 - 1.0),
                        this.computeValue(this.countArray[count27 + 1], offset17 - 1.0, offset23 - 1.0, offset20 - 1.0)
                     );
                  }

                  double offset25 = this.computeValue(offset24, offset12, offset13);
                  double offset26 = this.computeValue(offset24, offset14, offset15);
                  double offset27 = this.computeValue(offset21, offset25, offset26);
                  int count28 = count14++;
                  offsetArray[count28] += offset27 * offset11;
               }
            }
         }
      }
   }
}
