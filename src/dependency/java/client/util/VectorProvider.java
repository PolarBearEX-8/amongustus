package client.util;

public class VectorProvider extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int count5 = count - 2;
      int count6 = count2 - 2;
      int count7 = count5 >> 2;
      int count8 = count6 >> 2;
      int count9 = (count3 >> 2) + 2;
      int count10 = (count4 >> 2) + 2;
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count7, count8, count9, count10);
      int count11 = count9 - 1 << 2;
      int count12 = count10 - 1 << 2;
      int[] countArray2 = CacheManager.resolveIndexArray(count11 * count12);

      for (int index = 0; index < count10 - 1; index++) {
         int count13 = 0;
         int count14 = countArray[0 + (index + 0) * count9];

         for (int index2 = countArray[0 + (index + 1) * count9]; count13 < count9 - 1; count13++) {
            this.applyTime((long)(count13 + count7 << 2), (long)(index + count8 << 2));
            double offset = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6;
            double offset2 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6;
            this.applyTime((long)(count13 + count7 + 1 << 2), (long)(index + count8 << 2));
            double offset3 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            double offset4 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6;
            this.applyTime((long)(count13 + count7 << 2), (long)(index + count8 + 1 << 2));
            double offset5 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6;
            double offset6 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            this.applyTime((long)(count13 + count7 + 1 << 2), (long)(index + count8 + 1 << 2));
            double offset7 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            double offset8 = ((double)this.computeCount(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            int count15 = countArray[count13 + 1 + (index + 0) * count9] & 0xFF;
            int count16 = countArray[count13 + 1 + (index + 1) * count9] & 0xFF;

            for (int index3 = 0; index3 < 4; index3++) {
               int count17 = ((index << 2) + index3) * count11 + (count13 << 2);

               for (int index4 = 0; index4 < 4; index4++) {
                  double offset9 = ((double)index3 - offset2) * ((double)index3 - offset2) + ((double)index4 - offset) * ((double)index4 - offset);
                  double offset10 = ((double)index3 - offset4) * ((double)index3 - offset4) + ((double)index4 - offset3) * ((double)index4 - offset3);
                  double offset11 = ((double)index3 - offset6) * ((double)index3 - offset6) + ((double)index4 - offset5) * ((double)index4 - offset5);
                  double offset12 = ((double)index3 - offset8) * ((double)index3 - offset8) + ((double)index4 - offset7) * ((double)index4 - offset7);
                  if (offset9 < offset10 && offset9 < offset11 && offset9 < offset12) {
                     countArray2[count17++] = count14;
                  } else if (offset10 < offset9 && offset10 < offset11 && offset10 < offset12) {
                     countArray2[count17++] = count15;
                  } else if (offset11 < offset9 && offset11 < offset10 && offset11 < offset12) {
                     countArray2[count17++] = index2;
                  } else {
                     countArray2[count17++] = count16;
                  }
               }
            }

            count14 = count15;
            index2 = count16;
         }
      }

      int[] countArray3 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index5 = 0; index5 < count4; index5++) {
         System.arraycopy(countArray2, (index5 + (count6 & 3)) * count11 + (count5 & 3), countArray3, index5 * count3, count3);
      }

      return countArray3;
   }

   public VectorProvider(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      super.comparingBiomesManager = comparingBiomesManager;
   }
}
