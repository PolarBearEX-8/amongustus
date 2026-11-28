package net.augustus.cosmetics.model;


import net.augustus.cosmetics.CosmeticMathHelper;
public class IntVector3 implements Comparable {
   private final int z;
   public static final IntVector3 NULL_VECTOR = new IntVector3(0, 0, 0);
   private final int y;
   private final int x;

   public double distanceSqToCenter(double d, double d2, double d3) {
      double d4 = (double)this.getX() + 0.5 - d;
      double d5 = (double)this.getY() + 0.5 - d2;
      double d6 = (double)this.getZ() + 0.5 - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   @Override
   public String toString() {
      return this.getX() + "," + this.getY() + "," + this.getZ();
   }

   public double distanceSq(IntVector3 intVector3) {
      double x2 = (double)intVector3.getX();
      double z2 = (double)intVector3.getZ();
      return this.distanceSq(x2, (double)intVector3.getY(), z2);
   }

   public IntVector3(double d, double d2, double d3) {
       this(CosmeticMathHelper.floorDouble(d), CosmeticMathHelper.floorDouble(d2), CosmeticMathHelper.floorDouble(d3));
   }

   public int compareTo2(IntVector3 intVector3) {
      return this.getY() == intVector3.getY() ? (this.getZ() == intVector3.getZ() ? this.getX() - intVector3.getX() : this.getZ() - intVector3.getZ()) : this.getY() - intVector3.getY();
   }

   public int getZ() {
      return this.z;
   }

   public IntVector3 crossProduct(IntVector3 intVector3) {
      return new IntVector3(
         this.getY() * intVector3.getZ() - this.getZ() * intVector3.getY(),
         this.getZ() * intVector3.getX() - this.getX() * intVector3.getZ(),
         this.getX() * intVector3.getY() - this.getY() * intVector3.getX()
      );
   }

   public int getY() {
      return this.y;
   }

   public IntVector3(int n, int n2, int n3) {
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   public double distanceSq(double d, double d2, double d3) {
      double d4 = (double)this.getX() - d;
      double d5 = (double)this.getY() - d2;
      double d6 = (double)this.getZ() - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo2((IntVector3)other);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof IntVector3)) {
         return false;
      } else {
         IntVector3 intVector3 = (IntVector3)obj;
         return this.getX() != intVector3.getX() ? false : (this.getY() != intVector3.getY() ? false : this.getZ() == intVector3.getZ());
      }
   }

   public int getX() {
      return this.x;
   }

   @Override
   public int hashCode() {
      return (this.getY() + this.getZ() * 31) * 31 + this.getX();
   }
}
