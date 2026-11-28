package net.augustus.render.waveycape;


import net.augustus.render.cape.CapeVector3f;
public final class CapeQuaternion {
   private float i;
   public static final CapeQuaternion ONE = new CapeQuaternion(0.0F, 0.0F, 0.0F, 1.0F);
   private float j;
   private float k;
   private float r;

   public void mul(CapeQuaternion capeQuaternion) {
      float i2 = this.getI();
      float j2 = this.getJ();
      float k2 = this.getK();
      float r2 = this.getR();
      float i3 = capeQuaternion.getI();
      float j3 = capeQuaternion.getJ();
      float k3 = capeQuaternion.getK();
      float r3 = capeQuaternion.getR();
      this.i = r2 * i3 + i2 * r3 + j2 * k3 - k2 * j3;
      this.j = r2 * j3 - i2 * k3 + j2 * r3 + k2 * i3;
      this.k = r2 * k3 + i2 * j3 - j2 * i3 + k2 * r3;
      this.r = r2 * r3 - i2 * i3 - j2 * j3 - k2 * k3;
   }

   public float getJ() {
      return this.j;
   }

   public CapeQuaternion copy() {
      return new CapeQuaternion(this);
   }

   public CapeQuaternion(CapeVector3f capeVector3f, float f, boolean flag) {
      if (flag) {
         f *= (float) (Math.PI / 180.0);
      }

      float f2 = sin(f / 2.0F);
      this.i = capeVector3f.getX() * f2;
      this.j = capeVector3f.getY() * f2;
      this.k = capeVector3f.getZ() * f2;
      this.r = cos(f / 2.0F);
   }

   private static float cos(float f) {
      return (float)Math.cos((double)f);
   }

   private static float sin(float f) {
      return (float)Math.sin((double)f);
   }

   public float getI() {
      return this.i;
   }

   public float getR() {
      return this.r;
   }

   public CapeQuaternion(CapeQuaternion capeQuaternion) {
      this.i = capeQuaternion.i;
      this.j = capeQuaternion.j;
      this.k = capeQuaternion.k;
      this.r = capeQuaternion.r;
   }

   public float getK() {
      return this.k;
   }

   public CapeQuaternion(float f, float f2, float f3, float f4) {
      this.i = f;
      this.j = f2;
      this.k = f3;
      this.r = f4;
   }

   public void set(float f, float f2, float f3, float f4) {
      this.i = f;
      this.j = f2;
      this.k = f3;
      this.r = f4;
   }

   public void conj() {
      this.i = -this.i;
      this.j = -this.j;
      this.k = -this.k;
   }
}
