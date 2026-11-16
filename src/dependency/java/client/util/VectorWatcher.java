package client.util;

import net.minecraft.world.World;

public class VectorWatcher {
   public EnhancedCustom enhancedCustom;
   public EnhancedCustom enhancedCustom2;
   public EnhancedCustom enhancedCustom3;
   public int[] countArray = new int[0];
   public int[] countArray2 = new int[0];

   public boolean matchesWorldByWorld(World world, float factor, int[] countArray3, boolean flag, float factor2) {
      if (this.enhancedCustom2 == null && this.enhancedCustom3 == null) {
         return this.enhancedCustom.matchesWorld(world, factor, countArray3, flag);
      } else {
         int countAsIndex = world.secondaryFloatArrayEntry.getCountAsIndex();
         if (countAsIndex != 1 && countAsIndex != -1) {
            float floatItem = world.getFloat6(factor2);
            float floatItem2 = world.getFloat7(factor2);
            float factor3 = 1.0E-4F;
            boolean flag2 = floatItem > factor3;
            boolean flag3 = floatItem2 > factor3;
            if (!flag2 && !flag3) {
               return this.enhancedCustom.matchesWorld(world, factor, countArray3, flag);
            } else {
               if (floatItem > 0.0F) {
                  floatItem2 /= floatItem;
               }

               float factor4 = 1.0F - floatItem;
               float factor5 = floatItem - floatItem2;
               if (this.countArray.length != countArray3.length) {
                  this.countArray = new int[countArray3.length];
                  this.countArray2 = new int[countArray3.length];
               }

               int count = 0;
               int[][] countMatrix = new int[][]{countArray3, this.countArray, this.countArray2};
               float[] factorArray = new float[3];
               if (factor4 > factor3 && this.enhancedCustom.matchesWorld(world, factor, countMatrix[count], flag)) {
                  factorArray[count] = factor4;
                  count++;
               }

               if (factor5 > factor3 && this.enhancedCustom2 != null && this.enhancedCustom2.matchesWorld(world, factor, countMatrix[count], flag)) {
                  factorArray[count] = factor5;
                  count++;
               }

               if (floatItem2 > factor3 && this.enhancedCustom3 != null && this.enhancedCustom3.matchesWorld(world, factor, countMatrix[count], flag)) {
                  factorArray[count] = floatItem2;
                  count++;
               }

               return count == 2
                  ? this.matchesIndexArray(countMatrix[0], factorArray[0], countMatrix[1], factorArray[1])
                  : (count == 3 ? this.matchesIndexArray(countMatrix[0], factorArray[0], countMatrix[1], factorArray[1], countMatrix[2], factorArray[2]) : true);
            }
         } else {
            return this.enhancedCustom.matchesWorld(world, factor, countArray3, flag);
         }
      }
   }

   public VectorWatcher(EnhancedCustom enhancedCustom4, EnhancedCustom enhancedCustom5, EnhancedCustom enhancedCustom6) {
      if (enhancedCustom5 != null || enhancedCustom6 != null) {
         if (enhancedCustom5 == null) {
            enhancedCustom5 = enhancedCustom4;
         }

         if (enhancedCustom6 == null) {
            enhancedCustom6 = enhancedCustom5;
         }
      }

      this.enhancedCustom = enhancedCustom4;
      this.enhancedCustom2 = enhancedCustom5;
      this.enhancedCustom3 = enhancedCustom6;
   }

   private boolean matchesIndexArray(int[] countArray3, float factor, int[] countArray4, float factor2, int[] countArray5, float factor3) {
      if (countArray4.length == countArray3.length && countArray5.length == countArray3.length) {
         for (int index = 0; index < countArray3.length; index++) {
            int count = countArray3[index];
            int count2 = count >> 16 & 0xFF;
            int count3 = count >> 8 & 0xFF;
            int count4 = count & 0xFF;
            int count5 = countArray4[index];
            int count6 = count5 >> 16 & 0xFF;
            int count7 = count5 >> 8 & 0xFF;
            int count8 = count5 & 0xFF;
            int count9 = countArray5[index];
            int count10 = count9 >> 16 & 0xFF;
            int count11 = count9 >> 8 & 0xFF;
            int count12 = count9 & 0xFF;
            int count13 = (int)((float)count2 * factor + (float)count6 * factor2 + (float)count10 * factor3);
            int count14 = (int)((float)count3 * factor + (float)count7 * factor2 + (float)count11 * factor3);
            int count15 = (int)((float)count4 * factor + (float)count8 * factor2 + (float)count12 * factor3);
            countArray3[index] = 0xFF000000 | count13 << 16 | count14 << 8 | count15;
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean matchesIndexArray(int[] countArray3, float factor, int[] countArray4, float factor2) {
      if (countArray4.length != countArray3.length) {
         return false;
      } else {
         for (int index = 0; index < countArray3.length; index++) {
            int count = countArray3[index];
            int count2 = count >> 16 & 0xFF;
            int count3 = count >> 8 & 0xFF;
            int count4 = count & 0xFF;
            int count5 = countArray4[index];
            int count6 = count5 >> 16 & 0xFF;
            int count7 = count5 >> 8 & 0xFF;
            int count8 = count5 & 0xFF;
            int count9 = (int)((float)count2 * factor + (float)count6 * factor2);
            int count10 = (int)((float)count3 * factor + (float)count7 * factor2);
            int count11 = (int)((float)count4 * factor + (float)count8 * factor2);
            countArray3[index] = 0xFF000000 | count9 << 16 | count10 << 8 | count11;
         }

         return true;
      }
   }
}
