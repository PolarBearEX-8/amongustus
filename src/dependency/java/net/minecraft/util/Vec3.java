package net.minecraft.util;

public class Vec3 {
   public static final Vec3 vec3 = new Vec3(0.0, 0.0, 0.0);
   public double xCoord;
   public double yCoord;
   public double zCoord;

   public Vec3 getVec3(double offset) {
      return new Vec3(this.xCoord * offset, this.yCoord * offset, this.zCoord * offset);
   }

   public Vec3 subtract(Vec3 vec) {
      return new Vec3(
         this.yCoord * vec.zCoord - this.zCoord * vec.yCoord,
         this.zCoord * vec.xCoord - this.xCoord * vec.zCoord,
         this.xCoord * vec.yCoord - this.yCoord * vec.xCoord
      );
   }

   public double getValueAsDouble() {
      return (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
   }

   public Vec3 resolveVec3(float factor) {
      float factor2 = MathHelper.cos(factor);
      float factor3 = MathHelper.abs(factor);
      double offset = this.xCoord * (double)factor2 + this.zCoord * (double)factor3;
      double posY = this.yCoord;
      double offset2 = this.zCoord * (double)factor2 - this.xCoord * (double)factor3;
      return new Vec3(offset, posY, offset2);
   }

   public Vec3 resolveVecByVec3(Vec3 vec, double offset) {
      double offset2 = vec.xCoord - this.xCoord;
      double offset3 = vec.yCoord - this.yCoord;
      double offset4 = vec.zCoord - this.zCoord;
      if (offset2 * offset2 < 1.0E-7F) {
         return null;
      } else {
         double offset5 = (offset - this.xCoord) / offset2;
         return offset5 >= 0.0 && offset5 <= 1.0 ? new Vec3(this.xCoord + offset2 * offset5, this.yCoord + offset3 * offset5, this.zCoord + offset4 * offset5) : null;
      }
   }

   public double dotProduct(Vec3 vec) {
      return this.getDouble4(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public double getDouble2(double posX, double posY, double posZ) {
      return (double)MathHelper.sqrt_double(this.getDouble4(posX, posY, posZ));
   }

   public Vec3 addVector(double posX, double posY, double posZ) {
      return new Vec3(this.yCoord * posZ - this.zCoord * posY, this.zCoord * posX - this.xCoord * posZ, this.xCoord * posY - this.yCoord * posX);
   }

   public Vec3 getVec36(Vec3 vec) {
      return this.getVec312(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3 resolveVec3(double posX, double posY, double posZ) {
      return this.getVec312(-posX, -posY, -posZ);
   }

   public double getValue() {
      return this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord;
   }

   public double getDouble4(double posX, double posY, double posZ) {
      double offset = posX - this.xCoord;
      double offset2 = posY - this.yCoord;
      double offset3 = posZ - this.zCoord;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public Vec3 resolveVec3(Vec3 vec, double offset) {
      double offset2 = vec.xCoord - this.xCoord;
      double offset3 = vec.yCoord - this.yCoord;
      double offset4 = vec.zCoord - this.zCoord;
      if (offset4 * offset4 < 1.0E-7F) {
         return null;
      } else {
         double offset5 = (offset - this.zCoord) / offset4;
         return offset5 >= 0.0 && offset5 <= 1.0 ? new Vec3(this.xCoord + offset2 * offset5, this.yCoord + offset3 * offset5, this.zCoord + offset4 * offset5) : null;
      }
   }

   public double computeValue(Vec3 vec) {
      double offset = vec.xCoord / this.xCoord;
      double offset2 = vec.yCoord / this.yCoord;
      double offset3 = vec.zCoord / this.zCoord;
      double offset4 = 0.0;
      boolean flag = Math.abs(vec.xCoord - this.xCoord) < 1.0E-4;
      boolean flag2 = Math.abs(vec.yCoord - this.yCoord) < 1.0E-4;
      boolean flag3 = Math.abs(vec.zCoord - this.zCoord) < 1.0E-4;
      if (!flag && !flag2) {
         offset4 += Math.abs(offset - offset2);
      }

      if (!flag && !flag3) {
         offset4 += Math.abs(offset - offset3);
      }

      return offset4;
   }

   public double sqrt() {
      return Math.sqrt(this.xCoord * this.xCoord + this.zCoord * this.zCoord);
   }

   public Vec3 getVec39(Vec3 vec, double offset) {
      return new Vec3(
         this.xCoord + (vec.xCoord - this.xCoord) * offset, this.yCoord + (vec.yCoord - this.yCoord) * offset, this.zCoord + (vec.zCoord - this.zCoord) * offset
      );
   }

   public double squareDistanceTo(Vec3 vec) {
      double offset = vec.xCoord - this.xCoord;
      double offset2 = vec.zCoord - this.zCoord;
      return (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2);
   }

   public double lengthVector() {
      return Math.sqrt(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
   }

   public Vec3 resolveVecByFloat(float factor) {
      float factor2 = MathHelper.cos(factor);
      float factor3 = MathHelper.abs(factor);
      double posX = this.xCoord;
      double offset = this.yCoord * (double)factor2 + this.zCoord * (double)factor3;
      double offset2 = this.zCoord * (double)factor2 - this.yCoord * (double)factor3;
      return new Vec3(posX, offset, offset2);
   }

   public double distanceTo(Vec3 vec) {
      return this.xCoord * vec.xCoord + this.yCoord * vec.yCoord + this.zCoord * vec.zCoord;
   }

   public Vec3 getVec311(Vec3 vec) {
      return this.resolveVec3(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3 getVec312(double posX, double posY, double posZ) {
      return new Vec3(this.xCoord + posX, this.yCoord + posY, this.zCoord + posZ);
   }

   public Vec3 normalize() {
      double offset = (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
      return offset < 1.0E-4 ? new Vec3(0.0, 0.0, 0.0) : new Vec3(this.xCoord / offset, this.yCoord / offset, this.zCoord / offset);
   }

   public double getCoordinate4(Vec3 vec) {
      return this.getDouble2(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3 resolveVec2(Vec3 vec, double offset) {
      double offset2 = vec.xCoord - this.xCoord;
      double offset3 = vec.yCoord - this.yCoord;
      double offset4 = vec.zCoord - this.zCoord;
      if (offset3 * offset3 < 1.0E-7F) {
         return null;
      } else {
         double offset5 = (offset - this.yCoord) / offset3;
         return offset5 >= 0.0 && offset5 <= 1.0 ? new Vec3(this.xCoord + offset2 * offset5, this.yCoord + offset3 * offset5, this.zCoord + offset4 * offset5) : null;
      }
   }

   public Vec3 resolveVec3(Vec3 vec) {
      return new Vec3(vec.xCoord - this.xCoord, vec.yCoord - this.yCoord, vec.zCoord - this.zCoord);
   }

   public Vec3(Vec3 vec) {
      this(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public Vec3(double posX, double posY, double posZ) {
      if (posX == -0.0) {
         posX = 0.0;
      }

      if (posY == -0.0) {
         posY = 0.0;
      }

      if (posZ == -0.0) {
         posZ = 0.0;
      }

      this.xCoord = posX;
      this.yCoord = posY;
      this.zCoord = posZ;
   }

   public Vec3(Vec3i vec) {
      this((double)vec.getX(), (double)vec.getY(), (double)vec.getZ());
   }

   @Override
   public String toString() {
      return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
   }
}
