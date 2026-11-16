package client.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InternalComparingBiomesManagerAdapter extends ComparingBiomesManager {
   public static final Logger logger = LogManager.getLogger();
   public ComparingBiomesManager comparingBiomesManager2;

   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count - 1, count2 - 1, count3 + 2, count4 + 2);
      int[] countArray2 = this.comparingBiomesManager2.resolveIndexArray(count - 1, count2 - 1, count3 + 2, count4 + 2);
      int[] countArray3 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + 1 + (index + 1) * (count3 + 2)];
            int count6 = countArray2[index2 + 1 + (index + 1) * (count3 + 2)];
            boolean flag = (count6 - 2) % 29 == 0;
            if (count5 > 255) {
               logger.debug("old! " + count5);
            }

            if (count5 != 0 && count6 >= 2 && (count6 - 2) % 29 == 1 && count5 < 128) {
               if (BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count5 + 128) != null) {
                  countArray3[index2 + index * count3] = count5 + 128;
               } else {
                  countArray3[index2 + index * count3] = count5;
               }
            } else if (this.computeCount(3) != 0 && !flag) {
               countArray3[index2 + index * count3] = count5;
            } else {
               int count7 = count5;
               if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager27.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager25.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager22.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager20.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager31.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager33.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager6.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager18.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count) {
                  if (this.computeCount(3) == 0) {
                     count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager25.count;
                  } else {
                     count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4.count;
                  }
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager36.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager40.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager13.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager7.count) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager24.count;
               } else if (checkIndex(count5, BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count)) {
                  count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager5.count;
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count && this.computeCount(3) == 0) {
                  if (this.computeCount(2) == 0) {
                     count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count;
                  } else {
                     count7 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4.count;
                  }
               }

               if (flag && count7 != count5) {
                  if (BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count7 + 128) != null) {
                     count7 += 128;
                  } else {
                     count7 = count5;
                  }
               }

               if (count7 == count5) {
                  countArray3[index2 + index * count3] = count5;
               } else {
                  int count8 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
                  int count9 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
                  int count10 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
                  int count11 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
                  int count12 = 0;
                  if (checkIndex(count8, count5)) {
                     count12++;
                  }

                  if (checkIndex(count9, count5)) {
                     count12++;
                  }

                  if (checkIndex(count10, count5)) {
                     count12++;
                  }

                  if (checkIndex(count11, count5)) {
                     count12++;
                  }

                  if (count12 >= 3) {
                     countArray3[index2 + index * count3] = count7;
                  } else {
                     countArray3[index2 + index * count3] = count5;
                  }
               }
            }
         }
      }

      return countArray3;
   }

   public InternalComparingBiomesManagerAdapter(long value, ComparingBiomesManager comparingBiomesManager, ComparingBiomesManager comparingBiomesManager3) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
      this.comparingBiomesManager2 = comparingBiomesManager3;
   }
}
