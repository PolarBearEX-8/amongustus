package net.augustus.cosmetics.model;

public class GeoVector3d {
   public double y;
   public static final GeoVector3d ZERO = new GeoVector3d(0.0, 0.0, 0.0);
   public double x;
   public double z;

   public double getX() {
      return this.x;
   }

   public double getZ() {
      return this.z;
   }

   public double distanceSq(GeoVector3d geoVector3d) {
      double x2 = geoVector3d.getX();
      double z2 = geoVector3d.getZ();
      return this.distanceSq(x2, geoVector3d.getY(), z2);
   }

   public double distanceSqToCenter(double d, double d2, double d3) {
      double d4 = this.getX() + 0.5 - d;
      double d5 = this.getY() + 0.5 - d2;
      double d6 = this.getZ() + 0.5 - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public double distanceSq(double d, double d2, double d3) {
      double d4 = this.getX() - d;
      double d5 = this.getY() - d2;
      double d6 = this.getZ() - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public double getY() {
      return this.y;
   }

   public GeoVector3d crossProduct(GeoVector3d geoVector3d) {
      return new GeoVector3d(
         this.getY() * geoVector3d.getZ() - this.getZ() * geoVector3d.getY(),
         this.getZ() * geoVector3d.getX() - this.getX() * geoVector3d.getZ(),
         this.getX() * geoVector3d.getY() - this.getY() * geoVector3d.getX()
      );
   }

   @Override
   public String toString() {
      return this.getX() + "," + this.getY() + "," + this.getZ();
   }

   public GeoVector3d(double d, double d2, double d3) {
      this.x = d;
      this.y = d2;
      this.z = d3;
   }
}
