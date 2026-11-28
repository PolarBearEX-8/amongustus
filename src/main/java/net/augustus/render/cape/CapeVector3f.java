package net.augustus.render.cape;


import net.augustus.render.waveycape.CapeQuaternion;
import net.augustus.render.waveycape.CapeVector4f;
public final class CapeVector3f {
   private float x;
   public static CapeVector3f YN = new CapeVector3f(0.0F, -1.0F, 0.0F);
   public static CapeVector3f XN = new CapeVector3f(-1.0F, 0.0F, 0.0F);
   public static CapeVector3f XP = new CapeVector3f(1.0F, 0.0F, 0.0F);
   public static CapeVector3f ZERO = new CapeVector3f(0.0F, 0.0F, 0.0F);
   public static CapeVector3f YP = new CapeVector3f(0.0F, 1.0F, 0.0F);
   public static CapeVector3f ZN = new CapeVector3f(0.0F, 0.0F, -1.0F);
   private float z;
   private float y;
   public static CapeVector3f ZP = new CapeVector3f(0.0F, 0.0F, 1.0F);

   public void transform(CapeQuaternion capeQuaternion) {
      CapeQuaternion capeQuaternion2 = new CapeQuaternion(capeQuaternion);
      capeQuaternion2.mul(new CapeQuaternion(this.getX(), this.getY(), this.getZ(), 0.0F));
      CapeQuaternion capeQuaternion4 = new CapeQuaternion(capeQuaternion);
      capeQuaternion4.conj();
      capeQuaternion2.mul(capeQuaternion4);
      float i = capeQuaternion2.getI();
      float k = capeQuaternion2.getK();
      this.set(i, capeQuaternion2.getJ(), k);
   }

   public float getY() {
      return this.y;
   }

   public float getZ() {
      return this.z;
   }

   public CapeVector3f() {
   }

   public float getX() {
      return this.x;
   }

   public CapeVector3f(float f, float f2, float f3) {
      this.x = f;
      this.y = f2;
      this.z = f3;
   }

   public CapeVector3f copy() {
      return new CapeVector3f(this.x, this.y, this.z);
   }

   public void mul(float f) {
      this.x *= f;
      this.y *= f;
      this.z *= f;
   }

   public CapeQuaternion rotationDegrees(float f) {
      return new CapeQuaternion(this, f, true);
   }

   public CapeQuaternion rotation(float f) {
      return new CapeQuaternion(this, f, false);
   }

   public void set(float f, float f2, float f3) {
      this.x = f;
      this.y = f2;
      this.z = f3;
   }

   public CapeVector3f(CapeVector4f capeVector4f) {
      this(capeVector4f.getX(), capeVector4f.getY(), capeVector4f.getZ());
   }
}
