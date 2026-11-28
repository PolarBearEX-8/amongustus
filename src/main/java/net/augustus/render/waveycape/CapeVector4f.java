package net.augustus.render.waveycape;

public class CapeVector4f {
   private float y;
   private float w;
   private float z;
   private float x;

   public float getX() {
      return this.x;
   }

   public void transform(CapeMatrix4f capeMatrix4f) {
      float f = this.x;
      float f2 = this.y;
      float f3 = this.z;
      float f4 = this.w;
      this.x = capeMatrix4f.m00 * f + capeMatrix4f.m01 * f2 + capeMatrix4f.m02 * f3 + capeMatrix4f.m03 * f4;
      this.y = capeMatrix4f.m10 * f + capeMatrix4f.m11 * f2 + capeMatrix4f.m12 * f3 + capeMatrix4f.m13 * f4;
      this.z = capeMatrix4f.m20 * f + capeMatrix4f.m21 * f2 + capeMatrix4f.m22 * f3 + capeMatrix4f.m23 * f4;
      this.w = capeMatrix4f.m30 * f + capeMatrix4f.m31 * f2 + capeMatrix4f.m32 * f3 + capeMatrix4f.m33 * f4;
   }

   public float getZ() {
      return this.z;
   }

   public float getW() {
      return this.w;
   }

   public CapeVector4f(float f, float f2, float f3, float f4) {
      this.x = f;
      this.y = f2;
      this.z = f3;
      this.w = f4;
   }

   public void set(float f, float f2, float f3, float f4) {
      this.x = f;
      this.y = f2;
      this.z = f3;
      this.w = f4;
   }

   public float getY() {
      return this.y;
   }

   public CapeVector4f() {
   }
}
