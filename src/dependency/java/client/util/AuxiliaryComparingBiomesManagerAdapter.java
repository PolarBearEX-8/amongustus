package client.util;

import client.setting.StandardOptionType;

public class AuxiliaryComparingBiomesManagerAdapter extends ComparingBiomesManager {
   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count - 1, count2 - 1, count3 + 2, count4 + 2);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + 1 + (index + 1) * (count3 + 2)];
            if (!this.matchesIndexArray(
                  countArray,
                  countArray2,
                  index2,
                  index,
                  count3,
                  count5,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21.count,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager29.count
               )
               && !this.matchesIndexArrayByIndexArray(
                  countArray,
                  countArray2,
                  index2,
                  index,
                  count3,
                  count5,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager5.count
               )
               && !this.matchesIndexArrayByIndexArray(
                  countArray,
                  countArray2,
                  index2,
                  index,
                  count3,
                  count5,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager30.count,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager5.count
               )
               && !this.matchesIndexArrayByIndexArray(
                  countArray,
                  countArray2,
                  index2,
                  index,
                  count3,
                  count5,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager33.count,
                  BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15.count
               )) {
               if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count) {
                  int count6 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
                  int count7 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
                  int count8 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
                  int count9 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
                  if (count6 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     && count7 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     && count8 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     && count9 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count) {
                     countArray2[index2 + index * count3] = count5;
                  } else {
                     countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager13.count;
                  }
               } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16.count) {
                  int count10 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
                  int count11 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
                  int count12 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
                  int count13 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
                  if (count10 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count
                     || count11 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count
                     || count12 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count
                     || count13 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count
                     || count10 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19.count
                     || count11 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19.count
                     || count12 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19.count
                     || count13 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19.count
                     || count10 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     || count11 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     || count12 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count
                     || count13 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count) {
                     countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count;
                  } else if (count10 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count
                     && count13 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count
                     && count11 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count
                     && count12 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count) {
                     countArray2[index2 + index * count3] = count5;
                  } else {
                     countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager2.count;
                  }
               } else {
                  countArray2[index2 + index * count3] = count5;
               }
            }
         }
      }

      return countArray2;
   }

   private boolean matchesIndexArray(int[] countArray, int[] countArray2, int count, int count2, int count3, int count4, int count5, int count6) {
      if (!checkIndex(count4, count5)) {
         return false;
      } else {
         int count7 = countArray[count + 1 + (count2 + 1 - 1) * (count3 + 2)];
         int count8 = countArray[count + 1 + 1 + (count2 + 1) * (count3 + 2)];
         int count9 = countArray[count + 1 - 1 + (count2 + 1) * (count3 + 2)];
         int count10 = countArray[count + 1 + (count2 + 1 + 1) * (count3 + 2)];
         if (this.checkIndex2(count7, count5) && this.checkIndex2(count8, count5) && this.checkIndex2(count9, count5) && this.checkIndex2(count10, count5)) {
            countArray2[count + count2 * count3] = count4;
         } else {
            countArray2[count + count2 * count3] = count6;
         }

         return true;
      }
   }

   private boolean checkIndex2(int count, int count2) {
      if (checkIndex(count, count2)) {
         return true;
      } else {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count);
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count2);
         if (biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager2 != null) {
            StandardOptionType oCEAN = biomeIDIsOutOfBoundsManager.getOCEAN();
            StandardOptionType oCEAN2 = biomeIDIsOutOfBoundsManager2.getOCEAN();
            return oCEAN == oCEAN2 || oCEAN == StandardOptionType.MEDIUM || oCEAN2 == StandardOptionType.MEDIUM;
         } else {
            return false;
         }
      }
   }

   private boolean matchesIndexArrayByIndexArray(int[] countArray, int[] countArray2, int count, int count2, int count3, int count4, int count5, int count6) {
      if (count4 != count5) {
         return false;
      } else {
         int count7 = countArray[count + 1 + (count2 + 1 - 1) * (count3 + 2)];
         int count8 = countArray[count + 1 + 1 + (count2 + 1) * (count3 + 2)];
         int count9 = countArray[count + 1 - 1 + (count2 + 1) * (count3 + 2)];
         int count10 = countArray[count + 1 + (count2 + 1 + 1) * (count3 + 2)];
         if (checkIndex(count7, count5) && checkIndex(count8, count5) && checkIndex(count9, count5) && checkIndex(count10, count5)) {
            countArray2[count + count2 * count3] = count4;
         } else {
            countArray2[count + count2 * count3] = count6;
         }

         return true;
      }
   }

   public AuxiliaryComparingBiomesManagerAdapter(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
