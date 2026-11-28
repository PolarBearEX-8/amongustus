package net.augustus.cosmetics.model;


import net.augustus.cosmetics.CosmeticMathHelper;
public class GeoMatrix4f {
   public float m22;
   public float m11;
   public float m30;
   public float m02;
   public float m23;
   public float m13;
   public float m03;
   public float m21;
   public float m31;
   public float m12;
   public float m10;
   public float m01;
   public float m20;
   public float m00;
   public float m32;
   public float m33;

   public final void setM10(float f) {
      this.m10 = f;
   }

   public final void mul(float f) {
      this.m00 *= f;
      this.m01 *= f;
      this.m02 *= f;
      this.m03 *= f;
      this.m10 *= f;
      this.m11 *= f;
      this.m12 *= f;
      this.m13 *= f;
      this.m20 *= f;
      this.m21 *= f;
      this.m22 *= f;
      this.m23 *= f;
      this.m30 *= f;
      this.m31 *= f;
      this.m32 *= f;
      this.m33 *= f;
   }

   public final void setM33(float f) {
      this.m33 = f;
   }

   public final void setM13(float f) {
      this.m13 = f;
   }

   public final void setM02(float f) {
      this.m02 = f;
   }

   public final void rotX(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = f3;
      this.m12 = -f2;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = f2;
      this.m22 = f3;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public final void rotZ(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = f3;
      this.m01 = -f2;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = f2;
      this.m11 = f3;
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

   public final void mulRotY(int n, int n2, float f, byte b) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 *= f3;
      this.m01 *= 0.0F;
      this.m02 *= f2;
      this.m03 *= 0.0F;
      this.m10 *= 0.0F;
      this.m11 *= 1.0F;
      this.m12 *= 0.0F;
      this.m13 *= 0.0F;
      this.m20 *= -f2;
      this.m21 *= 0.0F;
      this.m22 *= f3;
      this.m23 *= 0.0F;
      this.m30 *= 0.0F;
      this.m31 *= 0.0F;
      this.m32 *= 0.0F;
      this.m33 *= 1.0F;
   }

   public final void setM21(float f) {
      this.m21 = f;
   }

   public final void setIdentity() {
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

   public final float getM12() {
      return this.m12;
   }

   public final void setM00(float f) {
      this.m00 = f;
   }

   public final void setM23(float f) {
      this.m23 = f;
   }

   public final void setM11(float f) {
      this.m11 = f;
   }

   public GeoMatrix4f() {
      this.m00 = 0.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 0.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 0.0F;
   }

   public final float getM32() {
      return this.m32;
   }

   public final void setM32(float f) {
      this.m32 = f;
   }

   public final float getM13() {
      return this.m13;
   }

   public final float getM03() {
      return this.m03;
   }

   public final void mul(GeoMatrix4f geoMatrix4f) {
      float f = this.m00 * geoMatrix4f.m00 + this.m01 * geoMatrix4f.m10 + this.m02 * geoMatrix4f.m20 + this.m03 * geoMatrix4f.m30;
      float f2 = this.m00 * geoMatrix4f.m01 + this.m01 * geoMatrix4f.m11 + this.m02 * geoMatrix4f.m21 + this.m03 * geoMatrix4f.m31;
      float f3 = this.m00 * geoMatrix4f.m02 + this.m01 * geoMatrix4f.m12 + this.m02 * geoMatrix4f.m22 + this.m03 * geoMatrix4f.m32;
      float f4 = this.m00 * geoMatrix4f.m03 + this.m01 * geoMatrix4f.m13 + this.m02 * geoMatrix4f.m23 + this.m03 * geoMatrix4f.m33;
      float f5 = this.m10 * geoMatrix4f.m00 + this.m11 * geoMatrix4f.m10 + this.m12 * geoMatrix4f.m20 + this.m13 * geoMatrix4f.m30;
      float f6 = this.m10 * geoMatrix4f.m01 + this.m11 * geoMatrix4f.m11 + this.m12 * geoMatrix4f.m21 + this.m13 * geoMatrix4f.m31;
      float f7 = this.m10 * geoMatrix4f.m02 + this.m11 * geoMatrix4f.m12 + this.m12 * geoMatrix4f.m22 + this.m13 * geoMatrix4f.m32;
      float f8 = this.m10 * geoMatrix4f.m03 + this.m11 * geoMatrix4f.m13 + this.m12 * geoMatrix4f.m23 + this.m13 * geoMatrix4f.m33;
      float f9 = this.m20 * geoMatrix4f.m00 + this.m21 * geoMatrix4f.m10 + this.m22 * geoMatrix4f.m20 + this.m23 * geoMatrix4f.m30;
      float f10 = this.m20 * geoMatrix4f.m01 + this.m21 * geoMatrix4f.m11 + this.m22 * geoMatrix4f.m21 + this.m23 * geoMatrix4f.m31;
      float f11 = this.m20 * geoMatrix4f.m02 + this.m21 * geoMatrix4f.m12 + this.m22 * geoMatrix4f.m22 + this.m23 * geoMatrix4f.m32;
      float f12 = this.m20 * geoMatrix4f.m03 + this.m21 * geoMatrix4f.m13 + this.m22 * geoMatrix4f.m23 + this.m23 * geoMatrix4f.m33;
      float f13 = this.m30 * geoMatrix4f.m00 + this.m31 * geoMatrix4f.m10 + this.m32 * geoMatrix4f.m20 + this.m33 * geoMatrix4f.m30;
      float f14 = this.m30 * geoMatrix4f.m01 + this.m31 * geoMatrix4f.m11 + this.m32 * geoMatrix4f.m21 + this.m33 * geoMatrix4f.m31;
      float f15 = this.m30 * geoMatrix4f.m02 + this.m31 * geoMatrix4f.m12 + this.m32 * geoMatrix4f.m22 + this.m33 * geoMatrix4f.m32;
      float f16 = this.m30 * geoMatrix4f.m03 + this.m31 * geoMatrix4f.m13 + this.m32 * geoMatrix4f.m23 + this.m33 * geoMatrix4f.m33;
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

   public final float getM00() {
      return this.m00;
   }

   public final void mulRotX(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 *= 1.0F;
      this.m01 *= 0.0F;
      this.m02 *= 0.0F;
      this.m03 *= 0.0F;
      this.m10 *= 0.0F;
      this.m11 *= f3;
      this.m12 *= -f2;
      this.m13 *= 0.0F;
      this.m20 *= 0.0F;
      this.m21 *= f2;
      this.m22 *= f3;
      this.m23 *= 0.0F;
      this.m30 *= 0.0F;
      this.m31 *= 0.0F;
      this.m32 *= 0.0F;
      this.m33 *= 1.0F;
   }

   public final float getM11() {
      return this.m11;
   }

   public final float getM20() {
      return this.m20;
   }

   public GeoMatrix4f(GeoMatrix4f geoMatrix4f) {
      this.m00 = geoMatrix4f.m00;
      this.m01 = geoMatrix4f.m01;
      this.m02 = geoMatrix4f.m02;
      this.m03 = geoMatrix4f.m03;
      this.m10 = geoMatrix4f.m10;
      this.m11 = geoMatrix4f.m11;
      this.m12 = geoMatrix4f.m12;
      this.m13 = geoMatrix4f.m13;
      this.m20 = geoMatrix4f.m20;
      this.m21 = geoMatrix4f.m21;
      this.m22 = geoMatrix4f.m22;
      this.m23 = geoMatrix4f.m23;
      this.m30 = geoMatrix4f.m30;
      this.m31 = geoMatrix4f.m31;
      this.m32 = geoMatrix4f.m32;
      this.m33 = geoMatrix4f.m33;
   }

   public final float getM22() {
      return this.m22;
   }

   public final void setM30(float f) {
      this.m30 = f;
   }

   public final void setM22(float f) {
      this.m22 = f;
   }

   public final float getM23() {
      return this.m23;
   }

   public final float getM31() {
      return this.m31;
   }

   public final float getM30() {
      return this.m30;
   }

   public final float getM21() {
      return this.m21;
   }

   public final void setM01(float f) {
      this.m01 = f;
   }

   public final void setM20(float f) {
      this.m20 = f;
   }

   public final void mulRotZ(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 *= f3;
      this.m01 *= -f2;
      this.m02 *= 0.0F;
      this.m03 *= 0.0F;
      this.m10 *= f2;
      this.m11 *= f3;
      this.m12 *= 0.0F;
      this.m13 *= 0.0F;
      this.m20 *= 0.0F;
      this.m21 *= 0.0F;
      this.m22 *= 1.0F;
      this.m23 *= 0.0F;
      this.m30 *= 0.0F;
      this.m31 *= 0.0F;
      this.m32 *= 0.0F;
      this.m33 *= 1.0F;
   }

   public final void rotY(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = f3;
      this.m01 = 0.0F;
      this.m02 = f2;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = -f2;
      this.m21 = 0.0F;
      this.m22 = f3;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public final float getM33() {
      return this.m33;
   }

   public final float getM10() {
      return this.m10;
   }

   public final void setM12(float f) {
      this.m12 = f;
   }

   public final float getM01() {
      return this.m01;
   }

   public final void setM31(float f) {
      this.m31 = f;
   }

   public final float getM02() {
      return this.m02;
   }

   public final void setM03(float f) {
      this.m03 = f;
   }
}
