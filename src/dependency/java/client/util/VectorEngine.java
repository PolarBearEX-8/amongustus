package client.util;

public class VectorEngine extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count - 1, count2 - 1, count3 + 2, count4 + 2);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + 1 + (index + 1) * (count3 + 2)];
            if (this.computeCount(57) == 0) {
               if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count) {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count + 128;
               } else {
                  countArray2[index2 + index * count3] = count5;
               }
            } else {
               countArray2[index2 + index * count3] = count5;
            }
         }
      }

      return countArray2;
   }

   public VectorEngine(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
