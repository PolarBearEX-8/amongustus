package net.minecraft.util;

import com.google.common.base.MoreObjects;

public class Vec3i implements Comparable {
   public static final Vec3i NULL_VECTOR = new Vec3i(0, 0, 0);
   private final int x;
   private final int y;
   private final int z;
   private String cachedPosKey = "";
   private boolean posKeyDirty = true;

   public double distanceSq(double d, double d2, double d3) {
      double d4 = (double)this.getX() - d;
      double d5 = (double)this.getY() - d2;
      double d6 = (double)this.getZ() - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo((Vec3i)other);
   }

   public double distanceSqToCenter(double d, double d2, double d3) {
      double d4 = (double)this.getX() + 0.5 - d;
      double d5 = (double)this.getY() + 0.5 - d2;
      double d6 = (double)this.getZ() + 0.5 - d3;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof Vec3i)) {
         return false;
      } else {
         Vec3i vec = (Vec3i)obj;
         return this.getX() == vec.getX() && this.getY() == vec.getY() && this.getZ() == vec.getZ();
      }
   }

   public Vec3i(int n, int n2, int n3) {
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   public Vec3i crossProductVec(Vec3i vec) {
      return new Vec3i(
         this.getY() * vec.getZ() - this.getZ() * vec.getY(),
         this.getZ() * vec.getX() - this.getX() * vec.getZ(),
         this.getX() * vec.getY() - this.getY() * vec.getX()
      );
   }

   public int getX() {
      return this.x;
   }

   public double distanceSq(Vec3i vec) {
      return this.distanceSq((double)vec.getX(), (double)vec.getY(), (double)vec.getZ());
   }

   public int getZ() {
      return this.z;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("x", this.getX()).add("y", this.getY()).add("z", this.getZ()).toString();
   }

   public int getY() {
      return this.y;
   }

   public String getPosKey() {
      if (this.posKeyDirty) {
         this.cachedPosKey = this.x + "_" + this.y + "_" + this.z;
         this.posKeyDirty = false;
      }

      return this.cachedPosKey;
   }

   public Vec3i(double d, double d2, double d3) {
      this(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   public int compareTo(Vec3i other) {
      return this.getY() == other.getY() ? (this.getZ() == other.getZ() ? this.getX() - other.getX() : this.getZ() - other.getZ()) : this.getY() - other.getY();
   }

   @Override
   public int hashCode() {
      return (this.getY() + this.getZ() * 31) * 31 + this.getX();
   }
}
