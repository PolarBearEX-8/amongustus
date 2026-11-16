package client.util;

public class InterpolationMonitor extends ComparingBiomesManager {
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
            int count9 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
            int count10 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
            int count11 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
            int count12 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
            int count13 = countArray[index2 + 1 + (index + 1) * count7];
            int count14 = 0;
            if (count9 == 0) {
               count14++;
            }

            if (count10 == 0) {
               count14++;
            }

            if (count11 == 0) {
               count14++;
            }

            if (count12 == 0) {
               count14++;
            }

            if (count13 == 0 && count14 > 3) {
               countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count;
            } else {
               countArray2[index2 + index * count3] = count13;
            }
         }
      }

      return countArray2;
   }

   public InterpolationMonitor(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
