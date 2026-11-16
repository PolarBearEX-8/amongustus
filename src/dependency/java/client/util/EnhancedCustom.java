package client.util;

import client.render.TexturesManager;
import net.minecraft.world.World;

public class EnhancedCustom {
   public TexturesManager texturesManager = null;
   public float[][] factorArray = new float[16][3];
   public float[][] factorArray2 = new float[16][3];

   public boolean matchesWorld(World world, float factor, int[] countArray, boolean flag) {
      if (this.texturesManager == null) {
         return false;
      } else {
         int count = this.texturesManager.getCount7();
         if (flag && count < 64) {
            return false;
         } else {
            int count2 = this.texturesManager.getCount2();
            if (count2 < 16) {
               applyText("Invalid lightmap width: " + count2);
               this.texturesManager = null;
               return false;
            } else {
               int count3 = 0;
               if (flag) {
                  count3 = count2 * 16 * 2;
               }

               float factor2 = 1.1666666F * (world.computeFactor3(1.0F) - 0.2F);
               if (world.getIndexOffset2() > 0) {
                  factor2 = 1.0F;
               }

               float factor3 = BuildManager.computeFactor(factor2) * (float)(count2 - 1);
               float factor4 = BuildManager.computeFactor(factor + 0.5F) * (float)(count2 - 1);
               float factor5 = BuildManager.computeFactor(BuildManager.getGameSettings().scaleFactor100);
               boolean flag2 = factor5 > 1.0E-4F;
               float[][] floatArray = this.texturesManager.getFloatArray();
               this.applyFloatArray(floatArray, factor3, count3, count2, this.factorArray);
               this.applyFloatArray(floatArray, factor4, count3 + 16 * count2, count2, this.factorArray2);
               float[] factorArray3 = new float[3];

               for (int index = 0; index < 16; index++) {
                  for (int index2 = 0; index2 < 16; index2++) {
                     for (int index3 = 0; index3 < 3; index3++) {
                        float factor6 = BuildManager.computeFactor(this.factorArray[index][index3] + this.factorArray2[index2][index3]);
                        if (flag2) {
                           float factor7 = 1.0F - factor6;
                           float factor8 = 1.0F - factor7 * factor7 * factor7 * factor7;
                           factor6 = factor5 * factor8 + (1.0F - factor5) * factor6;
                        }

                        factorArray3[index3] = factor6;
                     }

                     int count4 = (int)(factorArray3[0] * 255.0F);
                     int count5 = (int)(factorArray3[1] * 255.0F);
                     int count6 = (int)(factorArray3[2] * 255.0F);
                     countArray[index * 16 + index2] = 0xFF000000 | count4 << 16 | count5 << 8 | count6;
                  }
               }

               return true;
            }
         }
      }
   }

   private void applyFloatArray(float[][] factorMatrix, float factor, int count, int count2, float[][] factorMatrix2) {
      int count3 = (int)Math.floor((double)factor);
      int count4 = (int)Math.ceil((double)factor);
      if (count3 == count4) {
         for (int index = 0; index < 16; index++) {
            float[] factorArray3 = factorMatrix[count + index * count2 + count3];
            float[] factorArray4 = factorMatrix2[index];

            for (int index2 = 0; index2 < 3; index2++) {
               factorArray4[index2] = factorArray3[index2];
            }
         }
      } else {
         float factor2 = 1.0F - (factor - (float)count3);
         float factor3 = 1.0F - ((float)count4 - factor);

         for (int index3 = 0; index3 < 16; index3++) {
            float[] factorArray5 = factorMatrix[count + index3 * count2 + count3];
            float[] factorArray6 = factorMatrix[count + index3 * count2 + count4];
            float[] factorArray7 = factorMatrix2[index3];

            for (int index4 = 0; index4 < 3; index4++) {
               factorArray7[index4] = factorArray5[index4] * factor2 + factorArray6[index4] * factor3;
            }
         }
      }
   }

   public TexturesManager getTexturesManager() {
      return this.texturesManager;
   }

   private static void applyTextByText(String text) {
      BuildManager.applyText4("CustomColors: " + text);
   }

   private static void applyText(String text) {
      BuildManager.applyText3("CustomColors: " + text);
   }

   public EnhancedCustom(TexturesManager texturesManager2) {
      this.texturesManager = texturesManager2;
   }
}
