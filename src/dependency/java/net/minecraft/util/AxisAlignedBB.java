package net.minecraft.util;

import com.google.gson.JsonObject;

public class AxisAlignedBB {
   public final double minX;
   public final double minY;
   public final double minZ;
   public final double maxX;
   public final double maxY;
   public final double maxZ;

   public double getCoordinate(AxisAlignedBB boundingBox, double offset) {
      double offset2 = offset;
      if (boundingBox.maxX > this.minX && boundingBox.minX < this.maxX && boundingBox.maxY > this.minY && boundingBox.minY < this.maxY) {
         if (offset > 0.0 && boundingBox.maxZ <= this.minZ) {
            double offset3 = this.minZ - boundingBox.maxZ;
            if (offset3 < offset) {
               offset2 = offset3;
            }
         } else if (offset < 0.0 && boundingBox.minZ >= this.maxZ) {
            double offset4 = this.maxZ - boundingBox.minZ;
            if (offset4 > offset) {
               offset2 = offset4;
            }
         }

         return offset2;
      } else {
         return offset;
      }
   }

   public boolean matchesVec3(Vec3 vec, Vec3 vec2) {
      return this.checkDouble(
         Math.min(vec.xCoord, vec2.xCoord),
         Math.min(vec.yCoord, vec2.yCoord),
         Math.min(vec.zCoord, vec2.zCoord),
         Math.max(vec.xCoord, vec2.xCoord),
         Math.max(vec.yCoord, vec2.yCoord),
         Math.max(vec.zCoord, vec2.zCoord)
      );
   }

   public AxisAlignedBB offset(double posX, double posY, double posZ) {
      double offset = this.minX - posX;
      double offset2 = this.minY - posY;
      double offset3 = this.minZ - posZ;
      double offset4 = this.maxX + posX;
      double offset5 = this.maxY + posY;
      double offset6 = this.maxZ + posZ;
      return new AxisAlignedBB(offset, offset2, offset3, offset4, offset5, offset6);
   }

   public boolean isVecInside(Vec3 vec) {
      return this.checkDouble(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public static AxisAlignedBB resolveAxisAlignedBB(double posX, double posX2, double posY, double posZ) {
      return new AxisAlignedBB(posX - posZ / 2.0, posX2 - posZ / 2.0, posY - posZ / 2.0, posX + posZ / 2.0, posX2 + posZ / 2.0, posY + posZ / 2.0);
   }

   public AxisAlignedBB resolveAxisAlignedBB(double posX, double posY, double posZ) {
      double offset = this.minX + posX;
      double offset2 = this.minY + posY;
      double offset3 = this.minZ + posZ;
      double offset4 = this.maxX - posX;
      double offset5 = this.maxY - posY;
      double offset6 = this.maxZ - posZ;
      return new AxisAlignedBB(offset, offset2, offset3, offset4, offset5, offset6);
   }

   public boolean checkDouble(double posX, double posY, double posZ) {
      return posX > this.minX && posX < this.maxX && posY > this.minY && posY < this.maxY && posZ > this.minZ && posZ < this.maxZ;
   }

   public MovingObjectPosition calculateIntercept(Vec3 vec, Vec3 vec2) {
      Vec3 vec3 = vec.resolveVecByVec3(vec2, this.minX);
      Vec3 vec4 = vec.resolveVecByVec3(vec2, this.maxX);
      Vec3 vec5 = vec.resolveVec2(vec2, this.minY);
      Vec3 vec6 = vec.resolveVec2(vec2, this.maxY);
      Vec3 vec7 = vec.resolveVec3(vec2, this.minZ);
      Vec3 vec8 = vec.resolveVec3(vec2, this.maxZ);
      if (!this.matchesVecByVec3(vec3)) {
         vec3 = null;
      }

      if (!this.matchesVecByVec3(vec4)) {
         vec4 = null;
      }

      if (!this.matchesVec2(vec5)) {
         vec5 = null;
      }

      if (!this.matchesVec2(vec6)) {
         vec6 = null;
      }

      if (!this.matchesVec3(vec7)) {
         vec7 = null;
      }

      if (!this.matchesVec3(vec8)) {
         vec8 = null;
      }

      Vec3 vec9 = null;
      if (vec3 != null) {
         vec9 = vec3;
      }

      if (vec4 != null && (vec9 == null || vec.dotProduct(vec4) < vec.dotProduct(vec9))) {
         vec9 = vec4;
      }

      if (vec5 != null && (vec9 == null || vec.dotProduct(vec5) < vec.dotProduct(vec9))) {
         vec9 = vec5;
      }

      if (vec6 != null && (vec9 == null || vec.dotProduct(vec6) < vec.dotProduct(vec9))) {
         vec9 = vec6;
      }

      if (vec7 != null && (vec9 == null || vec.dotProduct(vec7) < vec.dotProduct(vec9))) {
         vec9 = vec7;
      }

      if (vec8 != null && (vec9 == null || vec.dotProduct(vec8) < vec.dotProduct(vec9))) {
         vec9 = vec8;
      }

      if (vec9 == null) {
         return null;
      } else {
         EnumFacing enumFacing;
         if (vec9 == vec3) {
            enumFacing = EnumFacing.WEST;
         } else if (vec9 == vec4) {
            enumFacing = EnumFacing.EAST;
         } else if (vec9 == vec5) {
            enumFacing = EnumFacing.DOWN;
         } else if (vec9 == vec6) {
            enumFacing = EnumFacing.UP;
         } else if (vec9 == vec7) {
            enumFacing = EnumFacing.NORTH;
         } else {
            enumFacing = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec9, enumFacing);
      }
   }

   public JsonObject getJsonObject() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("minX", this.minX);
      jsonObject.addProperty("minY", this.minY);
      jsonObject.addProperty("minZ", this.minZ);
      jsonObject.addProperty("maxX", this.maxX);
      jsonObject.addProperty("maxY", this.maxY);
      jsonObject.addProperty("maxZ", this.maxZ);
      return jsonObject;
   }

   public static AxisAlignedBB resolveAxisAlignedBB(JsonObject jsonObject) {
      return new AxisAlignedBB(
         jsonObject.get("minX").getAsDouble(),
         jsonObject.get("minY").getAsDouble(),
         jsonObject.get("minZ").getAsDouble(),
         jsonObject.get("maxX").getAsDouble(),
         jsonObject.get("maxY").getAsDouble(),
         jsonObject.get("maxZ").getAsDouble()
      );
   }

   public double computeValue(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      double offset = posZ;
      if (posX5 > this.minY && posX2 < this.maxY && posY > this.minZ && posX3 < this.maxZ) {
         if (posZ > 0.0 && posX4 <= this.minX) {
            double offset2 = this.minX - posX4;
            if (offset2 < posZ) {
               offset = offset2;
            }
         } else if (posZ < 0.0 && posX >= this.maxX) {
            double offset3 = this.maxX - posX;
            if (offset3 > posZ) {
               offset = offset3;
            }
         }

         return offset;
      } else {
         return posZ;
      }
   }

   public double getCoordinate2(AxisAlignedBB boundingBox, double offset) {
      double offset2 = offset;
      if (boundingBox.maxY > this.minY && boundingBox.minY < this.maxY && boundingBox.maxZ > this.minZ && boundingBox.minZ < this.maxZ) {
         if (offset > 0.0 && boundingBox.maxX <= this.minX) {
            double offset3 = this.minX - boundingBox.maxX;
            if (offset3 < offset) {
               offset2 = offset3;
            }
         } else if (offset < 0.0 && boundingBox.minX >= this.maxX) {
            double offset4 = this.maxX - boundingBox.minX;
            if (offset4 > offset) {
               offset2 = offset4;
            }
         }

         return offset2;
      } else {
         return offset;
      }
   }

   private boolean matchesVecByVec3(Vec3 vec) {
      return vec != null && vec.yCoord >= this.minY && vec.yCoord <= this.maxY && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public AxisAlignedBB getAxisAlignedBB4(AxisAlignedBB boundingBox) {
      double offset = Math.min(this.minX, boundingBox.minX);
      double offset2 = Math.min(this.minY, boundingBox.minY);
      double offset3 = Math.min(this.minZ, boundingBox.minZ);
      double offset4 = Math.max(this.maxX, boundingBox.maxX);
      double offset5 = Math.max(this.maxY, boundingBox.maxY);
      double offset6 = Math.max(this.maxZ, boundingBox.maxZ);
      return new AxisAlignedBB(offset, offset2, offset3, offset4, offset5, offset6);
   }

   public boolean checkDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.minX < posX4 && this.maxX > posX && this.minY < posY && this.maxY > posX2 && this.minZ < posZ && this.maxZ > posX3;
   }

   public double getValue() {
      double offset = this.maxX - this.minX;
      double offset2 = this.maxY - this.minY;
      double offset3 = this.maxZ - this.minZ;
      return (offset + offset2 + offset3) / 3.0;
   }

   public static AxisAlignedBB getAxisAlignedBB(Vec3 vec, double offset) {
      return resolveAxisAlignedBB(vec.xCoord, vec.yCoord, vec.zCoord, offset);
   }

   public Vec3 getVec3() {
      return new Vec3(this.minX + (this.maxX - this.minX) * 0.5, this.minY + (this.maxY - this.minY) * 0.5, this.minZ + (this.maxZ - this.minZ) * 0.5);
   }

   public boolean intersectsWith(AxisAlignedBB boundingBox) {
      return boundingBox.maxX > this.minX && boundingBox.minX < this.maxX && boundingBox.maxY > this.minY && boundingBox.minY < this.maxY && boundingBox.maxZ > this.minZ && boundingBox.minZ < this.maxZ;
   }

   public double getCoordinate3(AxisAlignedBB boundingBox, double offset) {
      double offset2 = offset;
      if (boundingBox.maxX > this.minX && boundingBox.minX < this.maxX && boundingBox.maxZ > this.minZ && boundingBox.minZ < this.maxZ) {
         if (offset > 0.0 && boundingBox.maxY <= this.minY) {
            double offset3 = this.minY - boundingBox.maxY;
            if (offset3 < offset) {
               offset2 = offset3;
            }
         } else if (offset < 0.0 && boundingBox.minY >= this.maxY) {
            double offset4 = this.maxY - boundingBox.minY;
            if (offset4 > offset) {
               offset2 = offset4;
            }
         }

         return offset2;
      } else {
         return offset;
      }
   }

   public boolean isActive() {
      return Double.isNaN(this.minX)
         || Double.isNaN(this.minY)
         || Double.isNaN(this.minZ)
         || Double.isNaN(this.maxX)
         || Double.isNaN(this.maxY)
         || Double.isNaN(this.maxZ);
   }

   public static AxisAlignedBB resolveAxisAlignedBB(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      double offset = Math.min(posX, posX4);
      double offset2 = Math.min(posX2, posY);
      double offset3 = Math.min(posX3, posZ);
      double offset4 = Math.max(posX, posX4);
      double offset5 = Math.max(posX2, posY);
      double offset6 = Math.max(posX3, posZ);
      return new AxisAlignedBB(offset, offset2, offset3, offset4, offset5, offset6);
   }

   public double computeValueByDouble(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      double offset = posZ;
      if (posX4 > this.minX && posX < this.maxX && posX5 > this.minY && posX2 < this.maxY) {
         if (posZ > 0.0 && posY <= this.minZ) {
            double offset2 = this.minZ - posY;
            if (offset2 < posZ) {
               offset = offset2;
            }
         } else if (posZ < 0.0 && posX3 >= this.maxZ) {
            double offset3 = this.maxZ - posX3;
            if (offset3 > posZ) {
               offset = offset3;
            }
         }

         return offset;
      } else {
         return posZ;
      }
   }

   public AxisAlignedBB expand(double posX, double posY, double posZ) {
      return new AxisAlignedBB(this.minX + posX, this.minY + posY, this.minZ + posZ, this.maxX + posX, this.maxY + posY, this.maxZ + posZ);
   }

   private boolean matchesVec2(Vec3 vec) {
      return vec == null ? false : vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ;
   }

   public double computeValue2(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      double offset = posZ;
      if (posX4 > this.minX && posX < this.maxX && posY > this.minZ && posX3 < this.maxZ) {
         if (posZ > 0.0 && posX5 <= this.minY) {
            double offset2 = this.minY - posX5;
            if (offset2 < posZ) {
               offset = offset2;
            }
         } else if (posZ < 0.0 && posX2 >= this.maxY) {
            double offset3 = this.maxY - posX2;
            if (offset3 > posZ) {
               offset = offset3;
            }
         }

         return offset;
      } else {
         return posZ;
      }
   }

   private boolean matchesVec3(Vec3 vec) {
      return vec == null ? false : vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.yCoord >= this.minY && vec.yCoord <= this.maxY;
   }

   public AxisAlignedBB contract(double posX, double posY, double posZ) {
      double minX2 = this.minX;
      double minY2 = this.minY;
      double minZ2 = this.minZ;
      double maxX2 = this.maxX;
      double maxY2 = this.maxY;
      double maxZ2 = this.maxZ;
      if (posX < 0.0) {
         minX2 += posX;
      } else if (posX > 0.0) {
         maxX2 += posX;
      }

      if (posY < 0.0) {
         minY2 += posY;
      } else if (posY > 0.0) {
         maxY2 += posY;
      }

      if (posZ < 0.0) {
         minZ2 += posZ;
      } else if (posZ > 0.0) {
         maxZ2 += posZ;
      }

      return new AxisAlignedBB(minX2, minY2, minZ2, maxX2, maxY2, maxZ2);
   }

   public AxisAlignedBB(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this.minX = Math.min(posX, posX4);
      this.minY = Math.min(posX2, posY);
      this.minZ = Math.min(posX3, posZ);
      this.maxX = Math.max(posX, posX4);
      this.maxY = Math.max(posX2, posY);
      this.maxZ = Math.max(posX3, posZ);
   }

   public AxisAlignedBB(BlockPos blockPos, BlockPos blockPos2) {
      this.minX = (double)blockPos.getX();
      this.minY = (double)blockPos.getY();
      this.minZ = (double)blockPos.getZ();
      this.maxX = (double)blockPos2.getX();
      this.maxY = (double)blockPos2.getY();
      this.maxZ = (double)blockPos2.getZ();
   }

   public AxisAlignedBB(AxisAlignedBB boundingBox) {
      this.minX = boundingBox.minX;
      this.minY = boundingBox.minY;
      this.minZ = boundingBox.minZ;
      this.maxX = boundingBox.maxX;
      this.maxY = boundingBox.maxY;
      this.maxZ = boundingBox.maxZ;
   }

   public AxisAlignedBB(Vec3 vec, Vec3 vec2) {
      this(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
   }

   @Override
   public String toString() {
      return "box[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
   }
}
