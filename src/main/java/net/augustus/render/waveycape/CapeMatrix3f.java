package net.augustus.render.waveycape;

public final class CapeMatrix3f {
   public float m20;
   public float m21;
   public float m10;
   public float m01;
   public float m00;
   public float m12;
   public float m22;
   public float m11;
   public float m02;

   public CapeMatrix3f() {
   }

   public CapeMatrix3f copy() {
      return new CapeMatrix3f(this);
   }

   public void setIdentity() {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
   }

   public void mul(CapeQuaternion capeQuaternion) {
      this.mul(new CapeMatrix3f(capeQuaternion));
   }

   public CapeMatrix3f(CapeMatrix3f capeMatrix3f) {
      this.m00 = capeMatrix3f.m00;
      this.m01 = capeMatrix3f.m01;
      this.m02 = capeMatrix3f.m02;
      this.m10 = capeMatrix3f.m10;
      this.m11 = capeMatrix3f.m11;
      this.m12 = capeMatrix3f.m12;
      this.m20 = capeMatrix3f.m20;
      this.m21 = capeMatrix3f.m21;
      this.m22 = capeMatrix3f.m22;
   }

   public CapeMatrix3f(CapeQuaternion capeQuaternion) {
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

   public void mul(float f) {
      this.m00 *= f;
      this.m01 *= f;
      this.m02 *= f;
      this.m10 *= f;
      this.m11 *= f;
      this.m12 *= f;
      this.m20 *= f;
      this.m21 *= f;
      this.m22 *= f;
   }

   public void mul(CapeMatrix3f capeMatrix3f) {
      float f = this.m00 * capeMatrix3f.m00 + this.m01 * capeMatrix3f.m10 + this.m02 * capeMatrix3f.m20;
      float f2 = this.m00 * capeMatrix3f.m01 + this.m01 * capeMatrix3f.m11 + this.m02 * capeMatrix3f.m21;
      float f3 = this.m00 * capeMatrix3f.m02 + this.m01 * capeMatrix3f.m12 + this.m02 * capeMatrix3f.m22;
      float f4 = this.m10 * capeMatrix3f.m00 + this.m11 * capeMatrix3f.m10 + this.m12 * capeMatrix3f.m20;
      float f5 = this.m10 * capeMatrix3f.m01 + this.m11 * capeMatrix3f.m11 + this.m12 * capeMatrix3f.m21;
      float f6 = this.m10 * capeMatrix3f.m02 + this.m11 * capeMatrix3f.m12 + this.m12 * capeMatrix3f.m22;
      float f7 = this.m20 * capeMatrix3f.m00 + this.m21 * capeMatrix3f.m10 + this.m22 * capeMatrix3f.m20;
      float f8 = this.m20 * capeMatrix3f.m01 + this.m21 * capeMatrix3f.m11 + this.m22 * capeMatrix3f.m21;
      float f9 = this.m20 * capeMatrix3f.m02 + this.m21 * capeMatrix3f.m12 + this.m22 * capeMatrix3f.m22;
      this.m00 = f;
      this.m01 = f2;
      this.m02 = f3;
      this.m10 = f4;
      this.m11 = f5;
      this.m12 = f6;
      this.m20 = f7;
      this.m21 = f8;
      this.m22 = f9;
   }

   public static CapeMatrix3f createScaleMatrix(float f, float f2, float f3) {
      CapeMatrix3f capeMatrix3f = new CapeMatrix3f();
      capeMatrix3f.m00 = f;
      capeMatrix3f.m11 = f2;
      capeMatrix3f.m22 = f3;
      return capeMatrix3f;
   }
}
