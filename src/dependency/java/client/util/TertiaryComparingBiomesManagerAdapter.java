package client.util;

import client.setting.CombatLimitProperty;

public class TertiaryComparingBiomesManagerAdapter extends ComparingBiomesManager {
   public final CombatLimitProperty combatLimitProperty;

   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      switch (AdvancedIntArrayHolder.countArray[this.combatLimitProperty.ordinal()]) {
         case 1:
         default:
            return this.resolveIndexArray2(count, count2, count3, count4);
         case 2:
            return this.resolveIndexArray3(count, count2, count3, count4);
         case 3:
            return this.resolveIndexArrayByIndex(count, count2, count3, count4);
      }
   }

   private int[] resolveIndexArrayByIndex(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count, count2, count3, count4);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + index * count3];
            if (count5 != 0 && this.computeCount(13) == 0) {
               count5 |= 1 + this.computeCount(15) << 8 & 3840;
            }

            countArray2[index2 + index * count3] = count5;
         }
      }

      return countArray2;
   }

   private int[] resolveIndexArray3(int count, int count2, int count3, int count4) {
      int count5 = count - 1;
      int count6 = count2 - 1;
      int count7 = 1 + count3 + 1;
      int count8 = 1 + count4 + 1;
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count5, count6, count7, count8);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            int count9 = countArray[index2 + 1 + (index + 1) * count7];
            if (count9 == 4) {
               int count10 = countArray[index2 + 1 + (index + 1 - 1) * count7];
               int count11 = countArray[index2 + 1 + 1 + (index + 1) * count7];
               int count12 = countArray[index2 + 1 - 1 + (index + 1) * count7];
               int count13 = countArray[index2 + 1 + (index + 1 + 1) * count7];
               boolean flag = count10 == 2 || count11 == 2 || count12 == 2 || count13 == 2;
               if (count10 == 1 || count11 == 1 || count12 == 1 || count13 == 1 || flag) {
                  count9 = 3;
               }
            }

            countArray2[index2 + index * count3] = count9;
         }
      }

      return countArray2;
   }

   private int[] resolveIndexArray2(int count, int count2, int count3, int count4) {
      int count5 = count - 1;
      int count6 = count2 - 1;
      int count7 = 1 + count3 + 1;
      int count8 = 1 + count4 + 1;
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count5, count6, count7, count8);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count9 = countArray[index2 + 1 + (index + 1) * count7];
            if (count9 == 1) {
               int count10 = countArray[index2 + 1 + (index + 1 - 1) * count7];
               int count11 = countArray[index2 + 1 + 1 + (index + 1) * count7];
               int count12 = countArray[index2 + 1 - 1 + (index + 1) * count7];
               int count13 = countArray[index2 + 1 + (index + 1 + 1) * count7];
               boolean flag = count10 == 3 || count11 == 3 || count12 == 3 || count13 == 3;
               boolean flag2 = count10 == 4 || count11 == 4 || count12 == 4 || count13 == 4;
               if (flag || flag2) {
                  count9 = 2;
               }
            }

            countArray2[index2 + index * count3] = count9;
         }
      }

      return countArray2;
   }

   public TertiaryComparingBiomesManagerAdapter(long value, ComparingBiomesManager comparingBiomesManager, CombatLimitProperty combatLimitProperty2) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
      this.combatLimitProperty = combatLimitProperty2;
   }
}
