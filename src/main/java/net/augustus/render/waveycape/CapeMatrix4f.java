package net.augustus.render.waveycape;

public final class CapeMatrix4f {
   public float m03;
   public float m23;
   public float m12;
   public float m01;
   public float m22;
   public float m30;
   public float m02;
   public float m10;
   public float m20;
   public float m11;
   public float m33;
   public float m31;
   public float m00;
   public float m21;
   public float m13;
   public float m32;

   public void multiplyWithTranslation(float f, float f2, float f3) {
      this.m03 = this.m03 + this.m00 * f + this.m01 * f2 + this.m02 * f3;
      this.m13 = this.m13 + this.m10 * f + this.m11 * f2 + this.m12 * f3;
      this.m23 = this.m23 + this.m20 * f + this.m21 * f2 + this.m22 * f3;
      this.m33 = this.m33 + this.m30 * f + this.m31 * f2 + this.m32 * f3;
   }

   public CapeMatrix4f copy() {
      return new CapeMatrix4f(this);
   }

   public CapeMatrix4f(CapeQuaternion capeQuaternion) {
      float i = capeQuaternion.getI();
      float j = capeQuaternion.getJ();
      float k = capeQuaternion.getK();
      float r = capeQuaternion.getR();
      float f = 2.0F * i * i;
      float f2 = 2.0F * j * j;
      float f3 = 2.0F * k * k;
      this.m00 = 1.0F - f2 - f3;
      this.m11 = 1.0F - f3 - f;
      this.m22 = 1.0F - f - f2;
      this.m33 = 1.0F;
      float f4 = i * j;
      float f5 = j * k;
      float f6 = k * i;
      float f7 = i * r;
      float f8 = j * r;
      float f9 = k * r;
      this.m10 = 2.0F * (f4 + f9);
      this.m01 = 2.0F * (f4 - f9);
      this.m20 = 2.0F * (f6 - f8);
      this.m02 = 2.0F * (f6 + f8);
      this.m21 = 2.0F * (f5 + f7);
      this.m12 = 2.0F * (f5 - f7);
   }

   public CapeMatrix4f() {
   }

   public void setIdentity() {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void multiply(CapeQuaternion capeQuaternion) {
      this.multiply(new CapeMatrix4f(capeQuaternion));
   }

   public static CapeMatrix4f createScaleMatrix(float f, float f2, float f3) {
      CapeMatrix4f capeMatrix4f = new CapeMatrix4f();
      capeMatrix4f.m00 = f;
      capeMatrix4f.m11 = f2;
      capeMatrix4f.m22 = f3;
      capeMatrix4f.m33 = 1.0F;
      return capeMatrix4f;
   }

   public CapeMatrix4f(CapeMatrix4f capeMatrix4f) {
      this.m00 = capeMatrix4f.m00;
      this.m01 = capeMatrix4f.m01;
      this.m02 = capeMatrix4f.m02;
      this.m03 = capeMatrix4f.m03;
      this.m10 = capeMatrix4f.m10;
      this.m11 = capeMatrix4f.m11;
      this.m12 = capeMatrix4f.m12;
      this.m13 = capeMatrix4f.m13;
      this.m20 = capeMatrix4f.m20;
      this.m21 = capeMatrix4f.m21;
      this.m22 = capeMatrix4f.m22;
      this.m23 = capeMatrix4f.m23;
      this.m30 = capeMatrix4f.m30;
      this.m31 = capeMatrix4f.m31;
      this.m32 = capeMatrix4f.m32;
      this.m33 = capeMatrix4f.m33;
   }

   public void multiply(CapeMatrix4f capeMatrix4f) {
      float f = this.m00 * capeMatrix4f.m00 + this.m01 * capeMatrix4f.m10 + this.m02 * capeMatrix4f.m20 + this.m03 * capeMatrix4f.m30;
      float f2 = this.m00 * capeMatrix4f.m01 + this.m01 * capeMatrix4f.m11 + this.m02 * capeMatrix4f.m21 + this.m03 * capeMatrix4f.m31;
      float f3 = this.m00 * capeMatrix4f.m02 + this.m01 * capeMatrix4f.m12 + this.m02 * capeMatrix4f.m22 + this.m03 * capeMatrix4f.m32;
      float f4 = this.m00 * capeMatrix4f.m03 + this.m01 * capeMatrix4f.m13 + this.m02 * capeMatrix4f.m23 + this.m03 * capeMatrix4f.m33;
      float f5 = this.m10 * capeMatrix4f.m00 + this.m11 * capeMatrix4f.m10 + this.m12 * capeMatrix4f.m20 + this.m13 * capeMatrix4f.m30;
      float f6 = this.m10 * capeMatrix4f.m01 + this.m11 * capeMatrix4f.m11 + this.m12 * capeMatrix4f.m21 + this.m13 * capeMatrix4f.m31;
      float f7 = this.m10 * capeMatrix4f.m02 + this.m11 * capeMatrix4f.m12 + this.m12 * capeMatrix4f.m22 + this.m13 * capeMatrix4f.m32;
      float f8 = this.m10 * capeMatrix4f.m03 + this.m11 * capeMatrix4f.m13 + this.m12 * capeMatrix4f.m23 + this.m13 * capeMatrix4f.m33;
      float f9 = this.m20 * capeMatrix4f.m00 + this.m21 * capeMatrix4f.m10 + this.m22 * capeMatrix4f.m20 + this.m23 * capeMatrix4f.m30;
      float f10 = this.m20 * capeMatrix4f.m01 + this.m21 * capeMatrix4f.m11 + this.m22 * capeMatrix4f.m21 + this.m23 * capeMatrix4f.m31;
      float f11 = this.m20 * capeMatrix4f.m02 + this.m21 * capeMatrix4f.m12 + this.m22 * capeMatrix4f.m22 + this.m23 * capeMatrix4f.m32;
      float f12 = this.m20 * capeMatrix4f.m03 + this.m21 * capeMatrix4f.m13 + this.m22 * capeMatrix4f.m23 + this.m23 * capeMatrix4f.m33;
      float f13 = this.m30 * capeMatrix4f.m00 + this.m31 * capeMatrix4f.m10 + this.m32 * capeMatrix4f.m20 + this.m33 * capeMatrix4f.m30;
      float f14 = this.m30 * capeMatrix4f.m01 + this.m31 * capeMatrix4f.m11 + this.m32 * capeMatrix4f.m21 + this.m33 * capeMatrix4f.m31;
      float f15 = this.m30 * capeMatrix4f.m02 + this.m31 * capeMatrix4f.m12 + this.m32 * capeMatrix4f.m22 + this.m33 * capeMatrix4f.m32;
      float f16 = this.m30 * capeMatrix4f.m03 + this.m31 * capeMatrix4f.m13 + this.m32 * capeMatrix4f.m23 + this.m33 * capeMatrix4f.m33;
      this.m00 = f;
      this.m01 = f2;
      this.m02 = f3;
      this.m03 = f4;
      this.m10 = f5;
      this.m11 = f6;
      this.m12 = f7;
      this.m13 = f8;
      this.m20 = f9;
      this.m21 = f10;
      this.m22 = f11;
      this.m23 = f12;
      this.m30 = f13;
      this.m31 = f14;
      this.m32 = f15;
      this.m33 = f16;
   }
}
