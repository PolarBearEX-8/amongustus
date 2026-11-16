package client.util;

public class VectorDirector extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int count5 = count >> 1;
      int count6 = count2 >> 1;
      int count7 = (count3 >> 1) + 2;
      int count8 = (count4 >> 1) + 2;
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count5, count6, count7, count8);
      int count9 = count7 - 1 << 1;
      int count10 = count8 - 1 << 1;
      int[] countArray2 = CacheManager.resolveIndexArray(count9 * count10);

      for (int index = 0; index < count8 - 1; index++) {
         int count11 = (index << 1) * count9;
         int count12 = 0;
         int count13 = countArray[0 + index * count7];

         for (int index2 = countArray[0 + (index + 1) * count7]; count12 < count7 - 1; count12++) {
            this.applyTime((long)count12 + (long)count5 << 1, (long)index + (long)count6 << 1);
            int count14 = countArray[count12 + 1 + index * count7];
            int count15 = countArray[count12 + 1 + (index + 1) * count7];
            countArray2[count11] = count13;
            countArray2[count11++ + count9] = this.computeCount(count13, index2);
            countArray2[count11] = this.computeCount(count13, count14);
            countArray2[count11++ + count9] = this.computeCount(count13, count14, index2, count15);
            count13 = count14;
            index2 = count15;
         }
      }

      int[] countArray3 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index3 = 0; index3 < count4; index3++) {
         System.arraycopy(countArray2, (index3 + (count2 & 1)) * count9 + (count & 1), countArray3, index3 * count3, count3);
      }

      return countArray3;
   }

   public int computeCount(int count, int count2) {
      return this.computeCount(2) == 0 ? count : count2;
   }

   public static ComparingBiomesManager resolveComparingBiomesManager(long value, ComparingBiomesManager comparingBiomesManager, int count) {
      Object entry = comparingBiomesManager;

      for (int index = 0; index < count; index++) {
         entry = new VectorDirector(value + (long)index, (ComparingBiomesManager)entry);
      }

      return (ComparingBiomesManager)entry;
   }

   public VectorDirector(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      super.comparingBiomesManager = comparingBiomesManager;
   }
}
