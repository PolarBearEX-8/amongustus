package client.util;

public class CustomFloatArrayEntry {
   public float[][] factorArray3 = new float[6][4];
   public float[] factorArray = new float[16];
   public float[] factorArray2 = new float[16];
   public float[] factorArray4 = new float[16];
   public boolean enabled = false;

   private float computeFactor(float[] factorArray5, float posX, float posY, float posZ) {
      return factorArray5[0] * posX + factorArray5[1] * posY + factorArray5[2] * posZ + factorArray5[3];
   }

   public boolean checkDoubleByDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      if (this.enabled) {
         return true;
      } else {
         float factor = (float)posX;
         float factor2 = (float)posX2;
         float factor3 = (float)posX3;
         float factor4 = (float)posX4;
         float factor5 = (float)posY;
         float factor6 = (float)posZ;

         for (int index = 0; index < 6; index++) {
            float[] factorArray5 = this.factorArray3[index];
            float factor7 = factorArray5[0];
            float factor8 = factorArray5[1];
            float factor9 = factorArray5[2];
            float factor10 = factorArray5[3];
            if (index < 4) {
               if (factor7 * factor + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
                  || factor7 * factor4 + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
                  || factor7 * factor + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
                  || factor7 * factor4 + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
                  || factor7 * factor + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
                  || factor7 * factor4 + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
                  || factor7 * factor + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F
                  || factor7 * factor4 + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F) {
                  return false;
               }
            } else if (factor7 * factor + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean checkDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      if (this.enabled) {
         return true;
      } else {
         float factor = (float)posX;
         float factor2 = (float)posX2;
         float factor3 = (float)posX3;
         float factor4 = (float)posX4;
         float factor5 = (float)posY;
         float factor6 = (float)posZ;

         for (int index = 0; index < 6; index++) {
            float[] factorArray5 = this.factorArray3[index];
            float factor7 = factorArray5[0];
            float factor8 = factorArray5[1];
            float factor9 = factorArray5[2];
            float factor10 = factorArray5[3];
            if (factor7 * factor + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor2 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor5 + factor9 * factor3 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor2 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F
               && factor7 * factor4 + factor8 * factor5 + factor9 * factor6 + factor10 <= 0.0F) {
               return false;
            }
         }

         return true;
      }
   }
}
