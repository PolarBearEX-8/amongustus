package net.minecraft.util;


public class Vec3 {
   public static final Vec3 ZERO = new Vec3(0.0, 0.0, 0.0);
   public double xCoord;
   public double yCoord;
   public double zCoord;

   public Vec3 rotateYaw(float f) {
      float f2 = MathHelper.cos(f);
      float f3 = MathHelper.sin(f);
      double d = this.xCoord * (double)f2 + this.zCoord * (double)f3;
      double yCoord2 = this.yCoord;
      double d2 = this.zCoord * (double)f2 - this.xCoord * (double)f3;
      return new Vec3(d, yCoord2, d2);
   }

   public double length() {
      return Math.sqrt(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
   }

   public double lengthSquared() {
      return this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord;
   }

   public Vec3(Vec3 vec) {
      this(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public double squareDistanceTo(Vec3 vec) {
      return this.squareDistanceTo(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3 crossProduct(Vec3 vec) {
      return new Vec3(
         this.yCoord * vec.zCoord - this.zCoord * vec.yCoord,
         this.zCoord * vec.xCoord - this.xCoord * vec.zCoord,
         this.xCoord * vec.yCoord - this.yCoord * vec.xCoord
      );
   }

   public Vec3 addVector(double d, double d2, double d3) {
      return new Vec3(this.xCoord + d, this.yCoord + d2, this.zCoord + d3);
   }

   public Vec3 getIntermediateWithXValue(Vec3 vec, double d) {
      double d2 = vec.xCoord - this.xCoord;
      double d3 = vec.yCoord - this.yCoord;
      double d4 = vec.zCoord - this.zCoord;
      if (d2 * d2 < 1.0E-7F) {
         return null;
      } else {
         double d5 = (d - this.xCoord) / d2;
         return d5 >= 0.0 && d5 <= 1.0 ? new Vec3(this.xCoord + d2 * d5, this.yCoord + d3 * d5, this.zCoord + d4 * d5) : null;
      }
   }

   public double squareDistanceTo(double d, double d2, double d3) {
      double d4 = d - this.xCoord;
      double d5 = d2 - this.yCoord;
      double d6 = d3 - this.zCoord;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public Vec3 getIntermediateWithZValue(Vec3 vec, double d) {
      double d2 = vec.xCoord - this.xCoord;
      double d3 = vec.yCoord - this.yCoord;
      double d4 = vec.zCoord - this.zCoord;
      if (d4 * d4 < 1.0E-7F) {
         return null;
      } else {
         double d5 = (d - this.zCoord) / d4;
         return d5 >= 0.0 && d5 <= 1.0 ? new Vec3(this.xCoord + d2 * d5, this.yCoord + d3 * d5, this.zCoord + d4 * d5) : null;
      }
   }

   public Vec3 subtract(double d, double d2, double d3) {
      return this.addVector(-d, -d2, -d3);
   }

   public Vec3 lerp(Vec3 vec, double d) {
      return new Vec3(
         this.xCoord + (vec.xCoord - this.xCoord) * d, this.yCoord + (vec.yCoord - this.yCoord) * d, this.zCoord + (vec.zCoord - this.zCoord) * d
      );
   }

   public double horizontalLength() {
      return Math.sqrt(this.xCoord * this.xCoord + this.zCoord * this.zCoord);
   }

   @Override
   public String toString() {
      return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
   }

   public Vec3 subtractReverse(Vec3 vec) {
      return new Vec3(vec.xCoord - this.xCoord, vec.yCoord - this.yCoord, vec.zCoord - this.zCoord);
   }

   public Vec3(Vec3i vec) {
      this((double)vec.getX(), (double)vec.getY(), (double)vec.getZ());
   }

   public double getCollinearityDeviation(Vec3 vec) {
      double d = vec.xCoord / this.xCoord;
      double d2 = vec.yCoord / this.yCoord;
      double d3 = vec.zCoord / this.zCoord;
      double d4 = 0.0;
      boolean flag = Math.abs(vec.xCoord - this.xCoord) < 1.0E-4;
      boolean flag2 = Math.abs(vec.yCoord - this.yCoord) < 1.0E-4;
      boolean flag3 = Math.abs(vec.zCoord - this.zCoord) < 1.0E-4;
      if (!flag && !flag2) {
         d4 += Math.abs(d - d2);
      }

      if (!flag && !flag3) {
         d4 += Math.abs(d - d3);
      }

      return d4;
   }

   public Vec3 crossProduct(double d, double d2, double d3) {
      return new Vec3(this.yCoord * d3 - this.zCoord * d2, this.zCoord * d - this.xCoord * d3, this.xCoord * d2 - this.yCoord * d);
   }

   public Vec3 getIntermediateWithYValue(Vec3 vec, double d) {
      double d2 = vec.xCoord - this.xCoord;
      double d3 = vec.yCoord - this.yCoord;
      double d4 = vec.zCoord - this.zCoord;
      if (d3 * d3 < 1.0E-7F) {
         return null;
      } else {
         double d5 = (d - this.yCoord) / d3;
         return d5 >= 0.0 && d5 <= 1.0 ? new Vec3(this.xCoord + d2 * d5, this.yCoord + d3 * d5, this.zCoord + d4 * d5) : null;
      }
   }

   public double lengthVector() {
      return (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
   }

   public double distanceToXZ(Vec3 vec) {
      double d = vec.xCoord - this.xCoord;
      double d2 = vec.zCoord - this.zCoord;
      return (double)MathHelper.sqrt_double(d * d + d2 * d2);
   }

   public double dotProduct(Vec3 vec) {
      return this.xCoord * vec.xCoord + this.yCoord * vec.yCoord + this.zCoord * vec.zCoord;
   }

   public Vec3 rotatePitch(float f) {
      float f2 = MathHelper.cos(f);
      float f3 = MathHelper.sin(f);
      double xCoord2 = this.xCoord;
      double d = this.yCoord * (double)f2 + this.zCoord * (double)f3;
      double d2 = this.zCoord * (double)f2 - this.yCoord * (double)f3;
      return new Vec3(xCoord2, d, d2);
   }

   public Vec3 scale(double d) {
      return new Vec3(this.xCoord * d, this.yCoord * d, this.zCoord * d);
   }

   public double distanceTo(double d, double d2, double d3) {
      return (double)MathHelper.sqrt_double(this.squareDistanceTo(d, d2, d3));
   }

   public Vec3 subtract(Vec3 vec) {
      return this.subtract(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3 add(Vec3 vec) {
      return this.addVector(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3(double d, double d2, double d3) {
      if (d == -0.0) {
         d = 0.0;
      }

      if (d2 == -0.0) {
         d2 = 0.0;
      }

      if (d3 == -0.0) {
         d3 = 0.0;
      }

      this.xCoord = d;
      this.yCoord = d2;
      this.zCoord = d3;
   }

   public Vec3 normalize() {
      double d = (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
      return d < 1.0E-4 ? new Vec3(0.0, 0.0, 0.0) : new Vec3(this.xCoord / d, this.yCoord / d, this.zCoord / d);
   }

   public double distanceTo(Vec3 vec) {
      return this.distanceTo(vec.xCoord, vec.yCoord, vec.zCoord);
   }
}
