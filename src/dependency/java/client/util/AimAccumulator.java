package client.util;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class AimAccumulator {
   public static void applyFloatArray(float[] factorArray, float[] factorArray2) {
      factorArray[0] = factorArray2[5] * factorArray2[10] * factorArray2[15]
         - factorArray2[5] * factorArray2[11] * factorArray2[14]
         - factorArray2[9] * factorArray2[6] * factorArray2[15]
         + factorArray2[9] * factorArray2[7] * factorArray2[14]
         + factorArray2[13] * factorArray2[6] * factorArray2[11]
         - factorArray2[13] * factorArray2[7] * factorArray2[10];
      factorArray[1] = -factorArray2[1] * factorArray2[10] * factorArray2[15]
         + factorArray2[1] * factorArray2[11] * factorArray2[14]
         + factorArray2[9] * factorArray2[2] * factorArray2[15]
         - factorArray2[9] * factorArray2[3] * factorArray2[14]
         - factorArray2[13] * factorArray2[2] * factorArray2[11]
         + factorArray2[13] * factorArray2[3] * factorArray2[10];
      factorArray[2] = factorArray2[1] * factorArray2[6] * factorArray2[15]
         - factorArray2[1] * factorArray2[7] * factorArray2[14]
         - factorArray2[5] * factorArray2[2] * factorArray2[15]
         + factorArray2[5] * factorArray2[3] * factorArray2[14]
         + factorArray2[13] * factorArray2[2] * factorArray2[7]
         - factorArray2[13] * factorArray2[3] * factorArray2[6];
      factorArray[3] = -factorArray2[1] * factorArray2[6] * factorArray2[11]
         + factorArray2[1] * factorArray2[7] * factorArray2[10]
         + factorArray2[5] * factorArray2[2] * factorArray2[11]
         - factorArray2[5] * factorArray2[3] * factorArray2[10]
         - factorArray2[9] * factorArray2[2] * factorArray2[7]
         + factorArray2[9] * factorArray2[3] * factorArray2[6];
      factorArray[4] = -factorArray2[4] * factorArray2[10] * factorArray2[15]
         + factorArray2[4] * factorArray2[11] * factorArray2[14]
         + factorArray2[8] * factorArray2[6] * factorArray2[15]
         - factorArray2[8] * factorArray2[7] * factorArray2[14]
         - factorArray2[12] * factorArray2[6] * factorArray2[11]
         + factorArray2[12] * factorArray2[7] * factorArray2[10];
      factorArray[5] = factorArray2[0] * factorArray2[10] * factorArray2[15]
         - factorArray2[0] * factorArray2[11] * factorArray2[14]
         - factorArray2[8] * factorArray2[2] * factorArray2[15]
         + factorArray2[8] * factorArray2[3] * factorArray2[14]
         + factorArray2[12] * factorArray2[2] * factorArray2[11]
         - factorArray2[12] * factorArray2[3] * factorArray2[10];
      factorArray[6] = -factorArray2[0] * factorArray2[6] * factorArray2[15]
         + factorArray2[0] * factorArray2[7] * factorArray2[14]
         + factorArray2[4] * factorArray2[2] * factorArray2[15]
         - factorArray2[4] * factorArray2[3] * factorArray2[14]
         - factorArray2[12] * factorArray2[2] * factorArray2[7]
         + factorArray2[12] * factorArray2[3] * factorArray2[6];
      factorArray[7] = factorArray2[0] * factorArray2[6] * factorArray2[11]
         - factorArray2[0] * factorArray2[7] * factorArray2[10]
         - factorArray2[4] * factorArray2[2] * factorArray2[11]
         + factorArray2[4] * factorArray2[3] * factorArray2[10]
         + factorArray2[8] * factorArray2[2] * factorArray2[7]
         - factorArray2[8] * factorArray2[3] * factorArray2[6];
      factorArray[8] = factorArray2[4] * factorArray2[9] * factorArray2[15]
         - factorArray2[4] * factorArray2[11] * factorArray2[13]
         - factorArray2[8] * factorArray2[5] * factorArray2[15]
         + factorArray2[8] * factorArray2[7] * factorArray2[13]
         + factorArray2[12] * factorArray2[5] * factorArray2[11]
         - factorArray2[12] * factorArray2[7] * factorArray2[9];
      factorArray[9] = -factorArray2[0] * factorArray2[9] * factorArray2[15]
         + factorArray2[0] * factorArray2[11] * factorArray2[13]
         + factorArray2[8] * factorArray2[1] * factorArray2[15]
         - factorArray2[8] * factorArray2[3] * factorArray2[13]
         - factorArray2[12] * factorArray2[1] * factorArray2[11]
         + factorArray2[12] * factorArray2[3] * factorArray2[9];
      factorArray[10] = factorArray2[0] * factorArray2[5] * factorArray2[15]
         - factorArray2[0] * factorArray2[7] * factorArray2[13]
         - factorArray2[4] * factorArray2[1] * factorArray2[15]
         + factorArray2[4] * factorArray2[3] * factorArray2[13]
         + factorArray2[12] * factorArray2[1] * factorArray2[7]
         - factorArray2[12] * factorArray2[3] * factorArray2[5];
      factorArray[11] = -factorArray2[0] * factorArray2[5] * factorArray2[11]
         + factorArray2[0] * factorArray2[7] * factorArray2[9]
         + factorArray2[4] * factorArray2[1] * factorArray2[11]
         - factorArray2[4] * factorArray2[3] * factorArray2[9]
         - factorArray2[8] * factorArray2[1] * factorArray2[7]
         + factorArray2[8] * factorArray2[3] * factorArray2[5];
      factorArray[12] = -factorArray2[4] * factorArray2[9] * factorArray2[14]
         + factorArray2[4] * factorArray2[10] * factorArray2[13]
         + factorArray2[8] * factorArray2[5] * factorArray2[14]
         - factorArray2[8] * factorArray2[6] * factorArray2[13]
         - factorArray2[12] * factorArray2[5] * factorArray2[10]
         + factorArray2[12] * factorArray2[6] * factorArray2[9];
      factorArray[13] = factorArray2[0] * factorArray2[9] * factorArray2[14]
         - factorArray2[0] * factorArray2[10] * factorArray2[13]
         - factorArray2[8] * factorArray2[1] * factorArray2[14]
         + factorArray2[8] * factorArray2[2] * factorArray2[13]
         + factorArray2[12] * factorArray2[1] * factorArray2[10]
         - factorArray2[12] * factorArray2[2] * factorArray2[9];
      factorArray[14] = -factorArray2[0] * factorArray2[5] * factorArray2[14]
         + factorArray2[0] * factorArray2[6] * factorArray2[13]
         + factorArray2[4] * factorArray2[1] * factorArray2[14]
         - factorArray2[4] * factorArray2[2] * factorArray2[13]
         - factorArray2[12] * factorArray2[1] * factorArray2[6]
         + factorArray2[12] * factorArray2[2] * factorArray2[5];
      factorArray[15] = factorArray2[0] * factorArray2[5] * factorArray2[10]
         - factorArray2[0] * factorArray2[6] * factorArray2[9]
         - factorArray2[4] * factorArray2[1] * factorArray2[10]
         + factorArray2[4] * factorArray2[2] * factorArray2[9]
         + factorArray2[8] * factorArray2[1] * factorArray2[6]
         - factorArray2[8] * factorArray2[2] * factorArray2[5];
      float factor = factorArray2[0] * factorArray[0] + factorArray2[1] * factorArray[4] + factorArray2[2] * factorArray[8] + factorArray2[3] * factorArray[12];
      if ((double)factor != 0.0) {
         for (int index = 0; index < 16; index++) {
            factorArray[index] /= factor;
         }
      } else {
         Arrays.fill(factorArray, 0.0F);
      }
   }

   public static void applyFloatBuffer(FloatBuffer buffer, FloatBuffer buffer2, float[] factorArray, float[] factorArray2) {
      buffer2.get(factorArray2);
      applyFloatArray(factorArray, factorArray2);
      buffer.put(factorArray);
   }

   public static void applyFloatArrayByFloatArray(float[] factorArray, float[] factorArray2, float[] factorArray3) {
      factorArray[0] = factorArray2[0] * factorArray3[0] + factorArray2[4] * factorArray3[1] + factorArray2[8] * factorArray3[2] + factorArray2[12] * factorArray3[3];
      factorArray[1] = factorArray2[1] * factorArray3[0] + factorArray2[5] * factorArray3[1] + factorArray2[9] * factorArray3[2] + factorArray2[13] * factorArray3[3];
      factorArray[2] = factorArray2[2] * factorArray3[0] + factorArray2[6] * factorArray3[1] + factorArray2[10] * factorArray3[2] + factorArray2[14] * factorArray3[3];
      factorArray[3] = factorArray2[3] * factorArray3[0] + factorArray2[7] * factorArray3[1] + factorArray2[11] * factorArray3[2] + factorArray2[15] * factorArray3[3];
   }

   public static void applyFloatArray(float[] factorArray, float[] factorArray2, float[] factorArray3) {
      for (int index = 0; index < 4; index++) {
         for (int index2 = 0; index2 < 4; index2++) {
            factorArray[4 * index + index2] = factorArray2[4 * index + 0] * factorArray3[0 + index2]
               + factorArray2[4 * index + 1] * factorArray3[4 + index2]
               + factorArray2[4 * index + 2] * factorArray3[8 + index2]
               + factorArray2[4 * index + 3] * factorArray3[12 + index2];
         }
      }
   }
}
