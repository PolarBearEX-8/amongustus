package client.util;

import net.minecraft.util.MathHelper;

public class CustomFloatArrayEntryImpl extends CustomFloatArrayEntry {
   public static CustomFloatArrayEntryImpl customFloatArrayEntryImpl = new CustomFloatArrayEntryImpl();
   public float[] factorArray5 = new float[6];
   public float[][] factorArray6 = new float[10][4];
   public int count;
   public float[] factorArray7 = new float[16];
   public float[] factorArray8 = new float[4];

   private double computeValue(float[] factorArray, float[] factorArray2) {
      return (double)factorArray[0] * (double)factorArray2[0] + (double)factorArray[1] * (double)factorArray2[1] + (double)factorArray[2] * (double)factorArray2[2];
   }

   private void applyFloatArray(float[] factorArray, float posX, float posX2, float posY, float posZ) {
      float factor = (float)Math.sqrt((double)(posX * posX + posX2 * posX2 + posY * posY));
      factorArray[0] = posX / factor;
      factorArray[1] = posX2 / factor;
      factorArray[2] = posY / factor;
      factorArray[3] = posZ / factor;
   }

   private float computeFactor(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      return this.sqrt(posX - posX4, posX2 - posY, posX3 - posZ);
   }

   public static CustomFloatArrayEntry getCustomFloatArrayEntry() {
      customFloatArrayEntryImpl.updateState();
      return customFloatArrayEntryImpl;
   }

   private float sqrt(float posX, float posY, float posZ) {
      return (float)Math.sqrt((double)(posX * posX + posY * posY + posZ * posZ));
   }

   private void applyFloatArray2(float[] factorArray) {
      float factor = MathHelper.sin(factorArray[0] * factorArray[0] + factorArray[1] * factorArray[1] + factorArray[2] * factorArray[2]);
      factorArray[0] /= factor;
      factorArray[1] /= factor;
      factorArray[2] /= factor;
      factorArray[3] /= factor;
   }

   private void applyFloatArray(float[] factorArray, float[] factorArray2, float[] factorArray3, float[] factorArray4) {
      this.applyFloatArray(this.factorArray8, factorArray2, factorArray3);
      this.applyFloatArray(factorArray, this.factorArray8, factorArray4);
      this.applyFloatArray(factorArray);
      float factor = (float)this.computeValue(factorArray2, factorArray3);
      float factor2 = (float)this.computeValue(factorArray, factorArray3);
      float factor3 = this.computeFactor(factorArray[0], factorArray[1], factorArray[2], factorArray3[0] * factor2, factorArray3[1] * factor2, factorArray3[2] * factor2);
      float factor4 = this.computeFactor(factorArray2[0], factorArray2[1], factorArray2[2], factorArray3[0] * factor, factorArray3[1] * factor, factorArray3[2] * factor);
      float factor5 = factor3 / factor4;
      float factor6 = (float)this.computeValue(factorArray, factorArray2);
      float factor7 = this.computeFactor(factorArray[0], factorArray[1], factorArray[2], factorArray2[0] * factor6, factorArray2[1] * factor6, factorArray2[2] * factor6);
      float factor8 = this.computeFactor(factorArray3[0], factorArray3[1], factorArray3[2], factorArray2[0] * factor, factorArray2[1] * factor, factorArray2[2] * factor);
      float factor9 = factor7 / factor8;
      factorArray[3] = factorArray2[3] * factor5 + factorArray3[3] * factor9;
   }

   private void applyFloatArray(float[] factorArray, float[] factorArray2, float[] factorArray3) {
      factorArray[0] = factorArray2[1] * factorArray3[2] - factorArray2[2] * factorArray3[1];
      factorArray[1] = factorArray2[2] * factorArray3[0] - factorArray2[0] * factorArray3[2];
      factorArray[2] = factorArray2[0] * factorArray3[1] - factorArray2[1] * factorArray3[0];
   }

   private void applyFloatArray(float[] factorArray) {
      float factor = MathHelper.sin(factorArray[0] * factorArray[0] + factorArray[1] * factorArray[1] + factorArray[2] * factorArray[2]);
      if (factor == 0.0F) {
         factor = 1.0F;
      }

      factorArray[0] /= factor;
      factorArray[1] /= factor;
      factorArray[2] /= factor;
   }

   public void updateState() {
      float[] factorArray = this.factorArray;
      float[] factorArray2 = this.factorArray2;
      float[] factorArray3 = this.factorArray4;
      System.arraycopy(PreEndHandManager.factorArray13, 0, factorArray, 0, 16);
      System.arraycopy(PreEndHandManager.factorArray6, 0, factorArray2, 0, 16);
      AimAccumulator.applyFloatArray(factorArray3, factorArray2, factorArray);
      this.applyFloatArray(this.factorArray3[0], factorArray3[3] - factorArray3[0], factorArray3[7] - factorArray3[4], factorArray3[11] - factorArray3[8], factorArray3[15] - factorArray3[12]);
      this.applyFloatArray(this.factorArray3[1], factorArray3[3] + factorArray3[0], factorArray3[7] + factorArray3[4], factorArray3[11] + factorArray3[8], factorArray3[15] + factorArray3[12]);
      this.applyFloatArray(this.factorArray3[2], factorArray3[3] + factorArray3[1], factorArray3[7] + factorArray3[5], factorArray3[11] + factorArray3[9], factorArray3[15] + factorArray3[13]);
      this.applyFloatArray(this.factorArray3[3], factorArray3[3] - factorArray3[1], factorArray3[7] - factorArray3[5], factorArray3[11] - factorArray3[9], factorArray3[15] - factorArray3[13]);
      this.applyFloatArray(this.factorArray3[4], factorArray3[3] - factorArray3[2], factorArray3[7] - factorArray3[6], factorArray3[11] - factorArray3[10], factorArray3[15] - factorArray3[14]);
      this.applyFloatArray(this.factorArray3[5], factorArray3[3] + factorArray3[2], factorArray3[7] + factorArray3[6], factorArray3[11] + factorArray3[10], factorArray3[15] + factorArray3[14]);
      float[] factorArray4 = PreEndHandManager.factorArray10;
      float factor = (float)this.computeValue(this.factorArray3[0], factorArray4);
      float factor2 = (float)this.computeValue(this.factorArray3[1], factorArray4);
      float factor3 = (float)this.computeValue(this.factorArray3[2], factorArray4);
      float factor4 = (float)this.computeValue(this.factorArray3[3], factorArray4);
      float factor5 = (float)this.computeValue(this.factorArray3[4], factorArray4);
      float factor6 = (float)this.computeValue(this.factorArray3[5], factorArray4);
      this.count = 0;
      if (factor >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[0]);
         if (factor > 0.0F) {
            if (factor3 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[0], this.factorArray3[2], factorArray4);
            }

            if (factor4 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[0], this.factorArray3[3], factorArray4);
            }

            if (factor5 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[0], this.factorArray3[4], factorArray4);
            }

            if (factor6 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[0], this.factorArray3[5], factorArray4);
            }
         }
      }

      if (factor2 >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[1]);
         if (factor2 > 0.0F) {
            if (factor3 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[1], this.factorArray3[2], factorArray4);
            }

            if (factor4 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[1], this.factorArray3[3], factorArray4);
            }

            if (factor5 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[1], this.factorArray3[4], factorArray4);
            }

            if (factor6 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[1], this.factorArray3[5], factorArray4);
            }
         }
      }

      if (factor3 >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[2]);
         if (factor3 > 0.0F) {
            if (factor < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[2], this.factorArray3[0], factorArray4);
            }

            if (factor2 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[2], this.factorArray3[1], factorArray4);
            }

            if (factor5 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[2], this.factorArray3[4], factorArray4);
            }

            if (factor6 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[2], this.factorArray3[5], factorArray4);
            }
         }
      }

      if (factor4 >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[3]);
         if (factor4 > 0.0F) {
            if (factor < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[3], this.factorArray3[0], factorArray4);
            }

            if (factor2 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[3], this.factorArray3[1], factorArray4);
            }

            if (factor5 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[3], this.factorArray3[4], factorArray4);
            }

            if (factor6 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[3], this.factorArray3[5], factorArray4);
            }
         }
      }

      if (factor5 >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[4]);
         if (factor5 > 0.0F) {
            if (factor < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[4], this.factorArray3[0], factorArray4);
            }

            if (factor2 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[4], this.factorArray3[1], factorArray4);
            }

            if (factor3 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[4], this.factorArray3[2], factorArray4);
            }

            if (factor4 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[4], this.factorArray3[3], factorArray4);
            }
         }
      }

      if (factor6 >= 0.0F) {
         this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[5]);
         if (factor6 > 0.0F) {
            if (factor < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[5], this.factorArray3[0], factorArray4);
            }

            if (factor2 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[5], this.factorArray3[1], factorArray4);
            }

            if (factor3 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[5], this.factorArray3[2], factorArray4);
            }

            if (factor4 < 0.0F) {
               this.applyFloatArray(this.factorArray6[this.count++], this.factorArray3[5], this.factorArray3[3], factorArray4);
            }
         }
      }
   }

   @Override
   public boolean checkDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      for (int index = 0; index < this.count; index++) {
         float[] factorArray = this.factorArray6[index];
         if (this.computeValue(factorArray, posX, posX2, posX3) <= 0.0
            && this.computeValue(factorArray, posX4, posX2, posX3) <= 0.0
            && this.computeValue(factorArray, posX, posY, posX3) <= 0.0
            && this.computeValue(factorArray, posX4, posY, posX3) <= 0.0
            && this.computeValue(factorArray, posX, posX2, posZ) <= 0.0
            && this.computeValue(factorArray, posX4, posX2, posZ) <= 0.0
            && this.computeValue(factorArray, posX, posY, posZ) <= 0.0
            && this.computeValue(factorArray, posX4, posY, posZ) <= 0.0) {
            return false;
         }
      }

      return true;
   }

   private double computeValue(float[] factorArray, double posX, double posY, double posZ) {
      return (double)factorArray[0] * posX + (double)factorArray[1] * posY + (double)factorArray[2] * posZ + (double)factorArray[3];
   }

   private void applyFloatArrayByFloatArray(float[] factorArray) {
      this.applyFloatArray(this.factorArray6[this.count++], factorArray);
   }

   private void applyFloatArray(float[] factorArray, float[] factorArray2) {
      factorArray[0] = factorArray2[0];
      factorArray[1] = factorArray2[1];
      factorArray[2] = factorArray2[2];
      factorArray[3] = factorArray2[3];
   }
}
