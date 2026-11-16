package net.minecraft.item;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.CacheManager;
import client.util.ComparingBiomesManager;

public class DiamondArtifact extends ComparingBiomesManager {
   public ComparingBiomesManager comparingBiomesManager3;
   public ComparingBiomesManager comparingBiomesManager2;

   @Override
   public void applyTime(long value) {
      this.comparingBiomesManager3.applyTime(value);
      this.comparingBiomesManager2.applyTime(value);
      super.applyTime(value);
   }

   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager3.resolveIndexArray(count, count2, count3, count4);
      int[] countArray2 = this.comparingBiomesManager2.resolveIndexArray(count, count2, count3, count4);
      int[] countArray3 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count3 * count4; index++) {
         if (countArray[index] == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
            || countArray[index] == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count) {
            countArray3[index] = countArray[index];
         } else if (countArray2[index] == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager37.count) {
            if (countArray[index] == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14.count) {
               countArray3[index] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager12.count;
            } else if (countArray[index] != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager32.count
               && countArray[index] != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager8.count) {
               countArray3[index] = countArray2[index] & 0xFF;
            } else {
               countArray3[index] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager8.count;
            }
         } else {
            countArray3[index] = countArray[index];
         }
      }

      return countArray3;
   }

   public DiamondArtifact(long value, ComparingBiomesManager comparingBiomesManager, ComparingBiomesManager comparingBiomesManager4) {
      super(value);
      this.comparingBiomesManager3 = comparingBiomesManager;
      this.comparingBiomesManager2 = comparingBiomesManager4;
   }
}
