package net.minecraft.item;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.InvalidBiomeIdManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.util.BlockPos;

public class DiamondFragment extends InvalidBiomeIdManager {
   public BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager;
   public float factor;

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      return this.biomeIDIsOutOfBoundsManager;
   }

   @Override
   public BlockPos resolveBlockPos(int count, int count2, int count3, List list, Random random) {
      return list.contains(this.biomeIDIsOutOfBoundsManager)
         ? new BlockPos(count - count3 + random.nextInt(count3 * 2 + 1), 0, count2 - count3 + random.nextInt(count3 * 2 + 1))
         : null;
   }

   @Override
   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4) {
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2 = biomeIDIsOutOfBoundsManagerArray;
      if (biomeIDIsOutOfBoundsManagerArray == null || biomeIDIsOutOfBoundsManagerArray.length < count3 * count4) {
         biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[count3 * count4];
      }

      Arrays.fill(biomeIDIsOutOfBoundsManagerArray2, 0, count3 * count4, this.biomeIDIsOutOfBoundsManager);
      return biomeIDIsOutOfBoundsManagerArray2;
   }

   @Override
   public float[] resolveFloatArray(float[] factorArray, int count, int count2, int count3, int count4) {
      float[] factorArray2 = factorArray;
      if (factorArray == null || factorArray.length < count3 * count4) {
         factorArray2 = new float[count3 * count4];
      }

      Arrays.fill(factorArray2, 0, count3 * count4, this.factor);
      return factorArray2;
   }

   @Override
   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA2(BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4) {
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2 = biomeIDIsOutOfBoundsManagerArray;
      if (biomeIDIsOutOfBoundsManagerArray == null || biomeIDIsOutOfBoundsManagerArray.length < count3 * count4) {
         biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[count3 * count4];
      }

      Arrays.fill(biomeIDIsOutOfBoundsManagerArray2, 0, count3 * count4, this.biomeIDIsOutOfBoundsManager);
      return biomeIDIsOutOfBoundsManagerArray2;
   }

   @Override
   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4, boolean flag
   ) {
      return this.resolveBiomeIDIsOutOfBoundsManagerA2(biomeIDIsOutOfBoundsManagerArray, count, count2, count3, count4);
   }

   @Override
   public boolean contains(int count, int count2, int count3, List list) {
      return list.contains(this.biomeIDIsOutOfBoundsManager);
   }

   public DiamondFragment(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2, float factor2) {
      this.biomeIDIsOutOfBoundsManager = biomeIDIsOutOfBoundsManager2;
      this.factor = factor2;
   }
}
