package net.minecraft.util;

import com.google.gson.JsonObject;

public class AxisAlignedBB {
   public final double minX;
   public final double minY;
   public final double minZ;
   public final double maxX;
   public final double maxY;
   public final double maxZ;

   public AxisAlignedBB expand(double d, double d2, double d3) {
      double d4 = this.minX - d;
      double d5 = this.minY - d2;
      double d6 = this.minZ - d3;
      double d7 = this.maxX + d;
      double d8 = this.maxY + d2;
      double d9 = this.maxZ + d3;
      return new AxisAlignedBB(d4, d5, d6, d7, d8, d9);
   }

   public static AxisAlignedBB fromCenter(double d, double d2, double d3, double d4) {
      return new AxisAlignedBB(d - d4 / 2.0, d2 - d4 / 2.0, d3 - d4 / 2.0, d + d4 / 2.0, d2 + d4 / 2.0, d3 + d4 / 2.0);
   }

   public AxisAlignedBB(Vec3 vec, Vec3 vec2) {
      this(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
   }

   public AxisAlignedBB addCoord(double d, double d2, double d3) {
      double minX2 = this.minX;
      double minY2 = this.minY;
      double minZ2 = this.minZ;
      double maxX2 = this.maxX;
      double maxY2 = this.maxY;
      double maxZ2 = this.maxZ;
      if (d < 0.0) {
         minX2 += d;
      } else if (d > 0.0) {
         maxX2 += d;
      }

      if (d2 < 0.0) {
         minY2 += d2;
      } else if (d2 > 0.0) {
         maxY2 += d2;
      }

      if (d3 < 0.0) {
         minZ2 += d3;
      } else if (d3 > 0.0) {
         maxZ2 += d3;
      }

      return new AxisAlignedBB(minX2, minY2, minZ2, maxX2, maxY2, maxZ2);
   }

   public double calculateXOffset(AxisAlignedBB box, double d) {
      if (box.maxY > this.minY && box.minY < this.maxY && box.maxZ > this.minZ && box.minZ < this.maxZ) {
         if (d > 0.0 && box.maxX <= this.minX) {
            double d2 = this.minX - box.maxX;
            if (d2 < d) {
               d = d2;
            }
         } else if (d < 0.0 && box.minX >= this.maxX) {
            double d3 = this.maxX - box.minX;
            if (d3 > d) {
               d = d3;
            }
         }

         return d;
      } else {
         return d;
      }
   }

   public boolean intersectsWith(AxisAlignedBB box) {
      return box.maxX > this.minX && box.minX < this.maxX && box.maxY > this.minY && box.minY < this.maxY && box.maxZ > this.minZ && box.minZ < this.maxZ;
   }

   public double calculateXOffset(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d5 > this.minY && d2 < this.maxY && d6 > this.minZ && d3 < this.maxZ) {
         if (d7 > 0.0 && d4 <= this.minX) {
            double d8 = this.minX - d4;
            if (d8 < d7) {
               d7 = d8;
            }
         } else if (d7 < 0.0 && d >= this.maxX) {
            double d9 = this.maxX - d;
            if (d9 > d7) {
               d7 = d9;
            }
         }

         return d7;
      } else {
         return d7;
      }
   }

   public MovingObjectPosition calculateInterceptFast(double d, double d2, double d3, double d4, double d5, double d6) {
      double d7 = d4 - d;
      double d8 = d5 - d2;
      double d9 = d6 - d3;
      double d10 = 0.0;
      double d11 = 0.0;
      double d12 = 0.0;
      boolean flag = false;
      double d13 = 0.0;
      double d14 = 0.0;
      double d15 = 0.0;
      boolean flag2 = false;
      double d16 = 0.0;
      double d17 = 0.0;
      double d18 = 0.0;
      boolean flag3 = false;
      double d19 = 0.0;
      double d20 = 0.0;
      double d21 = 0.0;
      boolean flag4 = false;
      double d22 = 0.0;
      double d23 = 0.0;
      double d24 = 0.0;
      boolean flag5 = false;
      double d25 = 0.0;
      double d26 = 0.0;
      double d27 = 0.0;
      boolean flag6 = false;
      if (d7 * d7 >= 1.0E-7F) {
         double d28 = (this.minX - d) / d7;
         if (d28 >= 0.0 && d28 <= 1.0) {
            d10 = d + d7 * d28;
            d11 = d2 + d8 * d28;
            d12 = d3 + d9 * d28;
            if (d11 >= this.minY && d11 <= this.maxY && d12 >= this.minZ && d12 <= this.maxZ) {
               flag = true;
            }
         }
      }

      if (d7 * d7 >= 1.0E-7F) {
         double d29 = (this.maxX - d) / d7;
         if (d29 >= 0.0 && d29 <= 1.0) {
            d13 = d + d7 * d29;
            d14 = d2 + d8 * d29;
            d15 = d3 + d9 * d29;
            if (d14 >= this.minY && d14 <= this.maxY && d15 >= this.minZ && d15 <= this.maxZ) {
               flag2 = true;
            }
         }
      }

      if (d8 * d8 >= 1.0E-7F) {
         double d30 = (this.minY - d2) / d8;
         if (d30 >= 0.0 && d30 <= 1.0) {
            d16 = d + d7 * d30;
            d17 = d2 + d8 * d30;
            d18 = d3 + d9 * d30;
            if (d16 >= this.minX && d16 <= this.maxX && d18 >= this.minZ && d18 <= this.maxZ) {
               flag3 = true;
            }
         }
      }

      if (d8 * d8 >= 1.0E-7F) {
         double d31 = (this.maxY - d2) / d8;
         if (d31 >= 0.0 && d31 <= 1.0) {
            d19 = d + d7 * d31;
            d20 = d2 + d8 * d31;
            d21 = d3 + d9 * d31;
            if (d19 >= this.minX && d19 <= this.maxX && d21 >= this.minZ && d21 <= this.maxZ) {
               flag4 = true;
            }
         }
      }

      if (d9 * d9 >= 1.0E-7F) {
         double d32 = (this.minZ - d3) / d9;
         if (d32 >= 0.0 && d32 <= 1.0) {
            d22 = d + d7 * d32;
            d23 = d2 + d8 * d32;
            d24 = d3 + d9 * d32;
            if (d22 >= this.minX && d22 <= this.maxX && d23 >= this.minY && d23 <= this.maxY) {
               flag5 = true;
            }
         }
      }

      if (d9 * d9 >= 1.0E-7F) {
         double d33 = (this.maxZ - d3) / d9;
         if (d33 >= 0.0 && d33 <= 1.0) {
            d25 = d + d7 * d33;
            d26 = d2 + d8 * d33;
            d27 = d3 + d9 * d33;
            if (d25 >= this.minX && d25 <= this.maxX && d26 >= this.minY && d26 <= this.maxY) {
               flag6 = true;
            }
         }
      }

      double d34 = 0.0;
      double d35 = 0.0;
      double d36 = 0.0;
      double maxvalue = Double.MAX_VALUE;
      EnumFacing facing = null;
      if (flag) {
         double d37 = squareDistance(d, d2, d3, d10, d11, d12);
         if (d37 < maxvalue) {
            maxvalue = d37;
            d34 = d10;
            d35 = d11;
            d36 = d12;
            facing = EnumFacing.WEST;
         }
      }

      if (flag2) {
         double d38 = squareDistance(d, d2, d3, d13, d14, d15);
         if (d38 < maxvalue) {
            maxvalue = d38;
            d34 = d13;
            d35 = d14;
            d36 = d15;
            facing = EnumFacing.EAST;
         }
      }

      if (flag3) {
         double d39 = squareDistance(d, d2, d3, d16, d17, d18);
         if (d39 < maxvalue) {
            maxvalue = d39;
            d34 = d16;
            d35 = d17;
            d36 = d18;
            facing = EnumFacing.DOWN;
         }
      }

      if (flag4) {
         double d40 = squareDistance(d, d2, d3, d19, d20, d21);
         if (d40 < maxvalue) {
            maxvalue = d40;
            d34 = d19;
            d35 = d20;
            d36 = d21;
            facing = EnumFacing.UP;
         }
      }

      if (flag5) {
         double d41 = squareDistance(d, d2, d3, d22, d23, d24);
         if (d41 < maxvalue) {
            maxvalue = d41;
            d34 = d22;
            d35 = d23;
            d36 = d24;
            facing = EnumFacing.NORTH;
         }
      }

      if (flag6) {
         if (squareDistance(d, d2, d3, d25, d26, d27) < maxvalue) {
            d34 = d25;
            d35 = d26;
            d36 = d27;
            facing = EnumFacing.SOUTH;
         }
      }

      return facing == null ? null : new MovingObjectPosition(new Vec3(d34, d35, d36), facing);
   }

   @Override
   public String toString() {
      return "box[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
   }

   private boolean isVecInYZ(Vec3 vec) {
      return vec != null && vec.yCoord >= this.minY && vec.yCoord <= this.maxY && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public MovingObjectPosition calculateIntercept(Vec3 vec, Vec3 vec2) {
      Vec3 intermediateWithXValue = vec.getIntermediateWithXValue(vec2, this.minX);
      Vec3 intermediateWithXValue2 = vec.getIntermediateWithXValue(vec2, this.maxX);
      Vec3 intermediateWithYValue = vec.getIntermediateWithYValue(vec2, this.minY);
      Vec3 intermediateWithYValue2 = vec.getIntermediateWithYValue(vec2, this.maxY);
      Vec3 intermediateWithZValue = vec.getIntermediateWithZValue(vec2, this.minZ);
      Vec3 intermediateWithZValue2 = vec.getIntermediateWithZValue(vec2, this.maxZ);
      if (!this.isVecInYZ(intermediateWithXValue)) {
         intermediateWithXValue = null;
      }

      if (!this.isVecInYZ(intermediateWithXValue2)) {
         intermediateWithXValue2 = null;
      }

      if (!this.isVecInXZ(intermediateWithYValue)) {
         intermediateWithYValue = null;
      }

      if (!this.isVecInXZ(intermediateWithYValue2)) {
         intermediateWithYValue2 = null;
      }

      if (!this.isVecInXY(intermediateWithZValue)) {
         intermediateWithZValue = null;
      }

      if (!this.isVecInXY(intermediateWithZValue2)) {
         intermediateWithZValue2 = null;
      }

      Vec3 vec3 = null;
      if (intermediateWithXValue != null) {
         vec3 = intermediateWithXValue;
      }

      if (intermediateWithXValue2 != null && (vec3 == null || vec.squareDistanceTo(intermediateWithXValue2) < vec.squareDistanceTo(vec3))) {
         vec3 = intermediateWithXValue2;
      }

      if (intermediateWithYValue != null && (vec3 == null || vec.squareDistanceTo(intermediateWithYValue) < vec.squareDistanceTo(vec3))) {
         vec3 = intermediateWithYValue;
      }

      if (intermediateWithYValue2 != null && (vec3 == null || vec.squareDistanceTo(intermediateWithYValue2) < vec.squareDistanceTo(vec3))) {
         vec3 = intermediateWithYValue2;
      }

      if (intermediateWithZValue != null && (vec3 == null || vec.squareDistanceTo(intermediateWithZValue) < vec.squareDistanceTo(vec3))) {
         vec3 = intermediateWithZValue;
      }

      if (intermediateWithZValue2 != null && (vec3 == null || vec.squareDistanceTo(intermediateWithZValue2) < vec.squareDistanceTo(vec3))) {
         vec3 = intermediateWithZValue2;
      }

      if (vec3 == null) {
         return null;
      } else {
         Object obj = null;
         if (vec3 == intermediateWithXValue) {
            obj = EnumFacing.WEST;
         } else if (vec3 == intermediateWithXValue2) {
            obj = EnumFacing.EAST;
         } else if (vec3 == intermediateWithYValue) {
            obj = EnumFacing.DOWN;
         } else if (vec3 == intermediateWithYValue2) {
            obj = EnumFacing.UP;
         } else if (vec3 == intermediateWithZValue) {
            obj = EnumFacing.NORTH;
         } else {
            obj = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec3, (EnumFacing)obj);
      }
   }

   public static AxisAlignedBB fromJson(JsonObject json) {
      return new AxisAlignedBB(
         json.get("minX").getAsDouble(),
         json.get("minY").getAsDouble(),
         json.get("minZ").getAsDouble(),
         json.get("maxX").getAsDouble(),
         json.get("maxY").getAsDouble(),
         json.get("maxZ").getAsDouble()
      );
   }

   public AxisAlignedBB(AxisAlignedBB box) {
      this.minX = box.minX;
      this.minY = box.minY;
      this.minZ = box.minZ;
      this.maxX = box.maxX;
      this.maxY = box.maxY;
      this.maxZ = box.maxZ;
   }

   public static AxisAlignedBB fromBounds(double d, double d2, double d3, double d4, double d5, double d6) {
      double d7 = Math.min(d, d4);
      double d8 = Math.min(d2, d5);
      double d9 = Math.min(d3, d6);
      double d10 = Math.max(d, d4);
      double d11 = Math.max(d2, d5);
      double d12 = Math.max(d3, d6);
      return new AxisAlignedBB(d7, d8, d9, d10, d11, d12);
   }

   public static AxisAlignedBB fromCenter(Vec3 vec, double d) {
      return fromCenter(vec.xCoord, vec.yCoord, vec.zCoord, d);
   }

   public boolean isVecInside(double d, double d2, double d3) {
      return d > this.minX && d < this.maxX && d2 > this.minY && d2 < this.maxY && d3 > this.minZ && d3 < this.maxZ;
   }

   public boolean isVecInside(Vec3 vec) {
      return this.isVecInside(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public double calculateYOffset(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d4 > this.minX && d < this.maxX && d6 > this.minZ && d3 < this.maxZ) {
         if (d7 > 0.0 && d5 <= this.minY) {
            double d8 = this.minY - d5;
            if (d8 < d7) {
               d7 = d8;
            }
         } else if (d7 < 0.0 && d2 >= this.maxY) {
            double d9 = this.maxY - d2;
            if (d9 > d7) {
               d7 = d9;
            }
         }

         return d7;
      } else {
         return d7;
      }
   }

   public AxisAlignedBB(BlockPos pos, BlockPos pos2) {
      this.minX = (double)pos.getX();
      this.minY = (double)pos.getY();
      this.minZ = (double)pos.getZ();
      this.maxX = (double)pos2.getX();
      this.maxY = (double)pos2.getY();
      this.maxZ = (double)pos2.getZ();
   }

   public AxisAlignedBB union(AxisAlignedBB box) {
      double d = Math.min(this.minX, box.minX);
      double d2 = Math.min(this.minY, box.minY);
      double d3 = Math.min(this.minZ, box.minZ);
      double d4 = Math.max(this.maxX, box.maxX);
      double d5 = Math.max(this.maxY, box.maxY);
      double d6 = Math.max(this.maxZ, box.maxZ);
      return new AxisAlignedBB(d, d2, d3, d4, d5, d6);
   }

   public boolean intersectsWith(Vec3 vec, Vec3 vec2) {
      return this.intersectsWith(
         Math.min(vec.xCoord, vec2.xCoord),
         Math.min(vec.yCoord, vec2.yCoord),
         Math.min(vec.zCoord, vec2.zCoord),
         Math.max(vec.xCoord, vec2.xCoord),
         Math.max(vec.yCoord, vec2.yCoord),
         Math.max(vec.zCoord, vec2.zCoord)
      );
   }

   public AxisAlignedBB contract(double d, double d2, double d3) {
      double d4 = this.minX + d;
      double d5 = this.minY + d2;
      double d6 = this.minZ + d3;
      double d7 = this.maxX - d;
      double d8 = this.maxY - d2;
      double d9 = this.maxZ - d3;
      return new AxisAlignedBB(d4, d5, d6, d7, d8, d9);
   }

   public MovingObjectPosition calculateInterceptFast(Vec3 vec, Vec3 vec2) {
      return this.calculateInterceptFast(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
   }

   public double getAverageEdgeLength() {
      double d = this.maxX - this.minX;
      double d2 = this.maxY - this.minY;
      double d3 = this.maxZ - this.minZ;
      return (d + d2 + d3) / 3.0;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      json.addProperty("minX", this.minX);
      json.addProperty("minY", this.minY);
      json.addProperty("minZ", this.minZ);
      json.addProperty("maxX", this.maxX);
      json.addProperty("maxY", this.maxY);
      json.addProperty("maxZ", this.maxZ);
      return json;
   }

   public double calculateZOffset(AxisAlignedBB box, double d) {
      if (box.maxX > this.minX && box.minX < this.maxX && box.maxY > this.minY && box.minY < this.maxY) {
         if (d > 0.0 && box.maxZ <= this.minZ) {
            double d2 = this.minZ - box.maxZ;
            if (d2 < d) {
               d = d2;
            }
         } else if (d < 0.0 && box.minZ >= this.maxZ) {
            double d3 = this.maxZ - box.minZ;
            if (d3 > d) {
               d = d3;
            }
         }

         return d;
      } else {
         return d;
      }
   }

   public double calculateZOffset(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      if (d4 > this.minX && d < this.maxX && d5 > this.minY && d2 < this.maxY) {
         if (d7 > 0.0 && d6 <= this.minZ) {
            double d8 = this.minZ - d6;
            if (d8 < d7) {
               d7 = d8;
            }
         } else if (d7 < 0.0 && d3 >= this.maxZ) {
            double d9 = this.maxZ - d3;
            if (d9 > d7) {
               d7 = d9;
            }
         }

         return d7;
      } else {
         return d7;
      }
   }

   public AxisAlignedBB(double d, double d2, double d3, double d4, double d5, double d6) {
      this.minX = Math.min(d, d4);
      this.minY = Math.min(d2, d5);
      this.minZ = Math.min(d3, d6);
      this.maxX = Math.max(d, d4);
      this.maxY = Math.max(d2, d5);
      this.maxZ = Math.max(d3, d6);
   }

   private boolean isVecInXZ(Vec3 vec) {
      return vec == null ? false : vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public boolean hasNaN() {
      return Double.isNaN(this.minX)
         || Double.isNaN(this.minY)
         || Double.isNaN(this.minZ)
         || Double.isNaN(this.maxX)
         || Double.isNaN(this.maxY)
         || Double.isNaN(this.maxZ);
   }

   private boolean isVecInXY(Vec3 vec) {
      return vec == null ? false : vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.yCoord >= this.minY && vec.yCoord <= this.maxY;
   }

   public double calculateYOffset(AxisAlignedBB box, double d) {
      if (box.maxX > this.minX && box.minX < this.maxX && box.maxZ > this.minZ && box.minZ < this.maxZ) {
         if (d > 0.0 && box.maxY <= this.minY) {
            double d2 = this.minY - box.maxY;
            if (d2 < d) {
               d = d2;
            }
         } else if (d < 0.0 && box.minY >= this.maxY) {
            double d3 = this.maxY - box.minY;
            if (d3 > d) {
               d = d3;
            }
         }

         return d;
      } else {
         return d;
      }
   }

   public AxisAlignedBB offset(double d, double d2, double d3) {
      return new AxisAlignedBB(this.minX + d, this.minY + d2, this.minZ + d3, this.maxX + d, this.maxY + d2, this.maxZ + d3);
   }

   public Vec3 getCenter() {
      return new Vec3(this.minX + (this.maxX - this.minX) * 0.5, this.minY + (this.maxY - this.minY) * 0.5, this.minZ + (this.maxZ - this.minZ) * 0.5);
   }

   public boolean intersectsWith(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.minX < d4 && this.maxX > d && this.minY < d5 && this.maxY > d2 && this.minZ < d6 && this.maxZ > d3;
   }

   private static double squareDistance(double d, double d2, double d3, double d4, double d5, double d6) {
      double d7 = d4 - d;
      double d8 = d5 - d2;
      double d9 = d6 - d3;
      return d7 * d7 + d8 * d8 + d9 * d9;
   }
}
