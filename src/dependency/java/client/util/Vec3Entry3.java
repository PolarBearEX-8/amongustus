package client.util;

import net.minecraft.util.Vec3;

public class Vec3Entry3 {
   public Vec3 vec3 = null;
   public long timestamp = System.currentTimeMillis();

   public Vec3 resolveVec3(double posX, double posY, double posZ) {
      if (this.vec3 == null) {
         this.vec3 = new Vec3(posX, posY, posZ);
         return this.vec3;
      } else {
         long timestamp2 = System.currentTimeMillis();
         long value = timestamp2 - this.timestamp;
         if (value == 0L) {
            return this.vec3;
         } else {
            this.timestamp = timestamp2;
            if (Math.abs(posX - this.vec3.xCoord) < 0.004 && Math.abs(posY - this.vec3.yCoord) < 0.004 && Math.abs(posZ - this.vec3.zCoord) < 0.004) {
               return this.vec3;
            } else {
               double offset = BuildManager.computeValue((double)value * 0.001, 0.0, 1.0);
               double offset2 = posX - this.vec3.xCoord;
               double offset3 = posY - this.vec3.yCoord;
               double offset4 = posZ - this.vec3.zCoord;
               double offset5 = this.vec3.xCoord + offset2 * offset;
               double offset6 = this.vec3.yCoord + offset3 * offset;
               double offset7 = this.vec3.zCoord + offset4 * offset;
               this.vec3 = new Vec3(offset5, offset6, offset7);
               return this.vec3;
            }
         }
      }
   }
}
