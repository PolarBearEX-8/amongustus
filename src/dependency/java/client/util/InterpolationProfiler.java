package client.util;

public class InterpolationProfiler extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int count5 = count - 1;
      int count6 = count2 - 1;
      int count7 = count3 + 2;
      int count8 = count4 + 2;
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count5, count6, count7, count8);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            int count9 = countArray[index2 + 0 + (index + 0) * count7];
            int count10 = countArray[index2 + 2 + (index + 0) * count7];
            int count11 = countArray[index2 + 0 + (index + 2) * count7];
            int count12 = countArray[index2 + 2 + (index + 2) * count7];
            int count13 = countArray[index2 + 1 + (index + 1) * count7];
            this.applyTime((long)(index2 + count), (long)(index + count2));
            if (count13 != 0 || count9 == 0 && count10 == 0 && count11 == 0 && count12 == 0) {
               if (count13 > 0 && (count9 == 0 || count10 == 0 || count11 == 0 || count12 == 0)) {
                  if (this.computeCount(5) == 0) {
                     if (count13 == 4) {
                        countArray2[index2 + index * count3] = 4;
                     } else {
                        countArray2[index2 + index * count3] = 0;
                     }
                  } else {
                     countArray2[index2 + index * count3] = count13;
                  }
               } else {
                  countArray2[index2 + index * count3] = count13;
               }
            } else {
               int count14 = 1;
               int count15 = 1;
               if (count9 != 0 && this.computeCount(count14++) == 0) {
                  count15 = count9;
               }

               if (count10 != 0 && this.computeCount(count14++) == 0) {
                  count15 = count10;
               }

               if (count11 != 0 && this.computeCount(count14++) == 0) {
                  count15 = count11;
               }

               if (count12 != 0 && this.computeCount(count14++) == 0) {
                  count15 = count12;
               }

               if (this.computeCount(3) == 0) {
                  countArray2[index2 + index * count3] = count15;
               } else if (count15 == 4) {
                  countArray2[index2 + index * count3] = 4;
               } else {
                  countArray2[index2 + index * count3] = 0;
               }
            }
         }
      }

      return countArray2;
   }

   public InterpolationProfiler(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
