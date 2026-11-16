package client.util;

public class ComparingBiomesManagerAdapter extends ComparingBiomesManager {
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
            int count9 = countArray[index2 + 1 + (index + 1) * count7];
            this.applyTime((long)(index2 + count), (long)(index + count2));
            if (count9 == 0) {
               countArray2[index2 + index * count3] = 0;
            } else {
               int count10 = this.computeCount(6);
               byte byteValue;
               if (count10 == 0) {
                  byteValue = 4;
               } else if (count10 <= 1) {
                  byteValue = 3;
               } else {
                  byteValue = 1;
               }

               countArray2[index2 + index * count3] = byteValue;
            }
         }
      }

      return countArray2;
   }

   public ComparingBiomesManagerAdapter(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
