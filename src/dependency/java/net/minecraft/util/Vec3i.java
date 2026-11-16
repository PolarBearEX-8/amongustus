package net.minecraft.util;

import com.google.common.base.MoreObjects;

public class Vec3i implements Comparable {
   public static final Vec3i vec3i = new Vec3i(0, 0, 0);
   public final int count2;
   public final int count3;
   public final int count;
   public String labelText = "";
   public boolean enabled = true;

   public int getX() {
      return this.count2;
   }

   public double getCoordinate(Vec3i vec) {
      return this.computeValue((double)vec.getX(), (double)vec.getY(), (double)vec.getZ());
   }

   public String getText() {
      if (this.enabled) {
         this.labelText = this.count2 + "_" + this.count3 + "_" + this.count;
         this.enabled = false;
      }

      return this.labelText;
   }

   public Vec3i resolveVec3i(Vec3i vec) {
      return new Vec3i(
         this.getY() * vec.getZ() - this.getZ() * vec.getY(),
         this.getZ() * vec.getX() - this.getX() * vec.getZ(),
         this.getX() * vec.getY() - this.getY() * vec.getX()
      );
   }

   public int computeCount(Vec3i vec) {
      return this.getY() == vec.getY() ? (this.getZ() == vec.getZ() ? this.getX() - vec.getX() : this.getZ() - vec.getZ()) : this.getY() - vec.getY();
   }

   public double computeValue(double posX, double posY, double posZ) {
      double offset = (double)this.getX() - posX;
      double offset2 = (double)this.getY() - posY;
      double offset3 = (double)this.getZ() - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public int getZ() {
      return this.count;
   }

   public double computeValueByDouble(double posX, double posY, double posZ) {
      double offset = (double)this.getX() + 0.5 - posX;
      double offset2 = (double)this.getY() + 0.5 - posY;
      double offset3 = (double)this.getZ() + 0.5 - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public int getY() {
      return this.count3;
   }

   public Vec3i(int count4, int count5, int count6) {
      this.count2 = count4;
      this.count3 = count5;
      this.count = count6;
   }

   public Vec3i(double posX, double posY, double posZ) {
      this(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY), MathHelper.ceiling_double_int(posZ));
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Vec3i)) {
         return false;
      } else {
         Vec3i vec = (Vec3i)other;
         return this.getX() == vec.getX() && this.getY() == vec.getY() && this.getZ() == vec.getZ();
      }
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("x", this.getX()).add("y", this.getY()).add("z", this.getZ()).toString();
   }

   @Override
   public int hashCode() {
      return (this.getY() + this.getZ() * 31) * 31 + this.getX();
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((Vec3i)entry);
   }
}
