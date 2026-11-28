package net.augustus.cosmetics.model;

public class GeoVector3f {
   public float z;
   public float y;
   public float x;
   public static final GeoVector3f ZERO = new GeoVector3f(0.0F, 0.0F, 0.0F);

   public GeoVector3f(float f, float f2, float f3) {
      this.x = f;
      this.y = f2;
      this.z = f3;
   }

   public float getZ() {
      return this.z;
   }

   public void setZ(float f) {
      this.z = f;
   }


   public float getX() {
      return this.x;
   }

   public void setX(float f) {
      this.x = f;
   }

   public GeoVector3f crossProduct(GeoVector3f geoVector3f) {
      return new GeoVector3f(
         this.getY() * geoVector3f.getZ() - this.getZ() * geoVector3f.getY(),
         this.getZ() * geoVector3f.getX() - this.getX() * geoVector3f.getZ(),
         this.getX() * geoVector3f.getY() - this.getY() * geoVector3f.getX()
      );
   }

   public void setY(float f) {
      this.y = f;
   }

   public void set(float f, float f2, float f3) {
      this.x = f;
      this.y = f2;
      this.z = f3;
   }

   @Override
   public String toString() {
      return this.getX() + "," + this.getY() + "," + this.getZ();
   }

   public double distanceSq(double d, double d2, double d3) {
      double d4 = (double)this.getX() - d;
      double d5 = (double)this.getY() - d2;
      double d6 = (double)this.getZ() - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public float getY() {
      return this.y;
   }

   public double distanceSqToCenter(double d, double d2, double d3) {
      double d4 = (double)this.getX() + 0.5 - d;
      double d5 = (double)this.getY() + 0.5 - d2;
      double d6 = (double)this.getZ() + 0.5 - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public double distanceSq(GeoVector3f geoVector3f) {
      double x2 = (double)geoVector3f.getX();
      double z2 = (double)geoVector3f.getZ();
      return this.distanceSq(x2, (double)geoVector3f.getY(), z2);
   }


}
