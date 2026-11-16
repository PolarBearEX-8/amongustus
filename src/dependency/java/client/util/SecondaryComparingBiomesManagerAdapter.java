package client.util;

public class SecondaryComparingBiomesManagerAdapter extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(count + index2), (long)(count2 + index));
            countArray[index2 + index * count3] = this.computeCount(10) == 0 ? 1 : 0;
         }
      }

      if (count > -count3 && count <= 0 && count2 > -count4 && count2 <= 0) {
         countArray[-count + -count2 * count3] = 1;
      }

      return countArray;
   }

   public SecondaryComparingBiomesManagerAdapter(long value) {
      super(value);
   }
}
