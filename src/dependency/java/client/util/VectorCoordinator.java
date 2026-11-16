package client.util;

public class VectorCoordinator extends ComparingBiomesManager {
   private int computeCountByIndex(int count) {
      return count >= 2 ? 2 + (count & 1) : count;
   }

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
            int count9 = this.computeCountByIndex(countArray[index2 + 0 + (index + 1) * count7]);
            int count10 = this.computeCountByIndex(countArray[index2 + 2 + (index + 1) * count7]);
            int count11 = this.computeCountByIndex(countArray[index2 + 1 + (index + 0) * count7]);
            int count12 = this.computeCountByIndex(countArray[index2 + 1 + (index + 2) * count7]);
            int count13 = this.computeCountByIndex(countArray[index2 + 1 + (index + 1) * count7]);
            if (count13 == count9 && count13 == count11 && count13 == count10 && count13 == count12) {
               countArray2[index2 + index * count3] = -1;
            } else {
               countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager37.count;
            }
         }
      }

      return countArray2;
   }

   public VectorCoordinator(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      super.comparingBiomesManager = comparingBiomesManager;
   }
}
