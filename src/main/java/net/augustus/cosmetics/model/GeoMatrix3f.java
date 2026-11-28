package net.augustus.cosmetics.model;


import net.augustus.cosmetics.CosmeticMathHelper;
public class GeoMatrix3f {
   public float m00;
   public float m22;
   public float m02;
   public float m20;
   public float m10;
   public float m12;
   public float m01;
   public float m11;
   public float m21;

   public final float getM02() {
      return this.m02;
   }

   public final void setM10(float f) {
      this.m10 = f;
   }

   public final void rotZ(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = f3;
      this.m01 = -f2;
      this.m02 = 0.0F;
      this.m10 = f2;
      this.m11 = f3;
      this.m12 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
   }

   public final float getM22() {
      return this.m22;
   }

   public final float getM21() {
      return this.m21;
   }

   public GeoMatrix3f() {
      this.m00 = 0.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 0.0F;
      this.m12 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 0.0F;
   }

   public final float getM12() {
      return this.m12;
   }

   public final void setM01(float f) {
      this.m01 = f;
   }

   public final void mul(GeoMatrix3f geoMatrix3f) {
      float f = this.m00 * geoMatrix3f.m00 + this.m01 * geoMatrix3f.m10 + this.m02 * geoMatrix3f.m20;
      float f2 = this.m00 * geoMatrix3f.m01 + this.m01 * geoMatrix3f.m11 + this.m02 * geoMatrix3f.m21;
      float f3 = this.m00 * geoMatrix3f.m02 + this.m01 * geoMatrix3f.m12 + this.m02 * geoMatrix3f.m22;
      float f4 = this.m10 * geoMatrix3f.m00 + this.m11 * geoMatrix3f.m10 + this.m12 * geoMatrix3f.m20;
      float f5 = this.m10 * geoMatrix3f.m01 + this.m11 * geoMatrix3f.m11 + this.m12 * geoMatrix3f.m21;
      float f6 = this.m10 * geoMatrix3f.m02 + this.m11 * geoMatrix3f.m12 + this.m12 * geoMatrix3f.m22;
      float f7 = this.m20 * geoMatrix3f.m00 + this.m21 * geoMatrix3f.m10 + this.m22 * geoMatrix3f.m20;
      float f8 = this.m20 * geoMatrix3f.m01 + this.m21 * geoMatrix3f.m11 + this.m22 * geoMatrix3f.m21;
      float f9 = this.m20 * geoMatrix3f.m02 + this.m21 * geoMatrix3f.m12 + this.m22 * geoMatrix3f.m22;
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

   public final void setM12(float f) {
      this.m12 = f;
   }

   public final float getM00() {
      return this.m00;
   }

   public final void setM02(float f) {
      this.m02 = f;
   }

   public final void setM21(float f) {
      this.m21 = f;
   }

   public final void setIdentity() {
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

   public final void setM22(float f) {
      this.m22 = f;
   }

   public final float getM10() {
      return this.m10;
   }

   public final float getM11() {
      return this.m11;
   }

   public final void mul(float f) {
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

   public final float getM20() {
      return this.m20;
   }

   public final void rotX(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = f3;
      this.m12 = -f2;
      this.m20 = 0.0F;
      this.m21 = f2;
      this.m22 = f3;
   }

   public GeoMatrix3f(GeoMatrix3f geoMatrix3f) {
      this.m00 = geoMatrix3f.m00;
      this.m01 = geoMatrix3f.m01;
      this.m02 = geoMatrix3f.m02;
      this.m10 = geoMatrix3f.m10;
      this.m11 = geoMatrix3f.m11;
      this.m12 = geoMatrix3f.m12;
      this.m20 = geoMatrix3f.m20;
      this.m21 = geoMatrix3f.m21;
      this.m22 = geoMatrix3f.m22;
   }

   public final void setM00(float f) {
      this.m00 = f;
   }

   public final void setM11(float f) {
      this.m11 = f;
   }

   public final void rotY(float f) {
      float f2 = CosmeticMathHelper.sin(f);
      float f3 = CosmeticMathHelper.cos(f);
      this.m00 = f3;
      this.m01 = 0.0F;
      this.m02 = f2;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m20 = -f2;
      this.m21 = 0.0F;
      this.m22 = f3;
   }

   public final float getM01() {
      return this.m01;
   }

   public final void setM20(float f) {
      this.m20 = f;
   }
}
