package client.util;

import org.lwjgl.util.vector.Matrix4f;

public class SmoothRanker extends Matrix4f {
   public SmoothRanker() {
      this.m00 = this.m01 = this.m02 = this.m03 = this.m10 = this.m11 = this.m12 = this.m13 = this.m20 = this.m21 = this.m22 = this.m23 = this.m30 = this.m31 = this.m32 = this.m33 = 0.0F;
   }

   public SmoothRanker(float[] factorArray) {
      this.m00 = factorArray[0];
      this.m01 = factorArray[1];
      this.m02 = factorArray[2];
      this.m03 = factorArray[3];
      this.m10 = factorArray[4];
      this.m11 = factorArray[5];
      this.m12 = factorArray[6];
      this.m13 = factorArray[7];
      this.m20 = factorArray[8];
      this.m21 = factorArray[9];
      this.m22 = factorArray[10];
      this.m23 = factorArray[11];
      this.m30 = factorArray[12];
      this.m31 = factorArray[13];
      this.m32 = factorArray[14];
      this.m33 = factorArray[15];
   }
}
