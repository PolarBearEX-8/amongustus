package client.util;

public class VectorService extends ComparingBiomesManager {
   private boolean checkIndexByIndex(int count) {
      return BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count) != null
            && BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count).getClassAsClass() == TertiaryIBlockStateEntry.class
         ? true
         : count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager2.count
            || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count
            || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager40.count
            || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4.count
            || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15.count
            || checkIndex(count);
   }

   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count - 1, count2 - 1, count3 + 2, count4 + 2);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + 1 + (index + 1) * (count3 + 2)];
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count5);
            if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager32.count) {
               int count6 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
               int count7 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
               int count8 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
               int count9 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
               if (count6 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
                  && count7 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
                  && count8 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
                  && count9 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count) {
                  countArray2[index2 + index * count3] = count5;
               } else {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager8.count;
               }
            } else if (biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager.getClassAsClass() == TertiaryIBlockStateEntry.class) {
               int count10 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
               int count11 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
               int count12 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
               int count13 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
               if (!this.checkIndexByIndex(count10) || !this.checkIndexByIndex(count11) || !this.checkIndexByIndex(count12) || !this.checkIndexByIndex(count13)) {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager2.count;
               } else if (!checkIndex(count10) && !checkIndex(count11) && !checkIndex(count12) && !checkIndex(count13)) {
                  countArray2[index2 + index * count3] = count5;
               } else {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager11.count;
               }
            } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21.count
               || count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager13.count
               || count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager29.count) {
               this.applyIndexArray(countArray, countArray2, index2, index, count3, count5, BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager41.count);
            } else if (biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager.isEnabled2()) {
               this.applyIndexArray(countArray, countArray2, index2, index, count3, count5, BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager34.count);
            } else if (count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager5.count
               || count5 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count) {
               int count14 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
               int count15 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
               int count16 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
               int count17 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
               if (checkIndex(count14) || checkIndex(count15) || checkIndex(count16) || checkIndex(count17)) {
                  countArray2[index2 + index * count3] = count5;
               } else if (this.checkIndex3(count14) && this.checkIndex3(count15) && this.checkIndex3(count16) && this.checkIndex3(count17)) {
                  countArray2[index2 + index * count3] = count5;
               } else {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17.count;
               }
            } else if (count5 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
               && count5 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count
               && count5 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager37.count
               && count5 != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16.count) {
               int count18 = countArray[index2 + 1 + (index + 1 - 1) * (count3 + 2)];
               int count19 = countArray[index2 + 1 + 1 + (index + 1) * (count3 + 2)];
               int count20 = countArray[index2 + 1 - 1 + (index + 1) * (count3 + 2)];
               int count21 = countArray[index2 + 1 + (index + 1 + 1) * (count3 + 2)];
               if (!checkIndex(count18) && !checkIndex(count19) && !checkIndex(count20) && !checkIndex(count21)) {
                  countArray2[index2 + index * count3] = count5;
               } else {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager11.count;
               }
            } else {
               countArray2[index2 + index * count3] = count5;
            }
         }
      }

      return countArray2;
   }

   private boolean checkIndex3(int count) {
      return BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count) instanceof GGTNManager;
   }

   private void applyIndexArray(int[] countArray, int[] countArray2, int count, int count2, int count3, int count4, int count5) {
      if (checkIndex(count4)) {
         countArray2[count + count2 * count3] = count4;
      } else {
         int count6 = countArray[count + 1 + (count2 + 1 - 1) * (count3 + 2)];
         int count7 = countArray[count + 1 + 1 + (count2 + 1) * (count3 + 2)];
         int count8 = countArray[count + 1 - 1 + (count2 + 1) * (count3 + 2)];
         int count9 = countArray[count + 1 + (count2 + 1 + 1) * (count3 + 2)];
         if (!checkIndex(count6) && !checkIndex(count7) && !checkIndex(count8) && !checkIndex(count9)) {
            countArray2[count + count2 * count3] = count4;
         } else {
            countArray2[count + count2 * count3] = count5;
         }
      }
   }

   public VectorService(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
   }
}
