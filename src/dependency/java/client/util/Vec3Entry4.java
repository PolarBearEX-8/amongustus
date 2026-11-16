package client.util;

import net.minecraft.util.Vec3;

public class Vec3Entry4 {
   public Vec3 vec3;
   public float factor;
   public float factor2;

   public Vec3Entry4 resolveVec3Entry4(float factor3, float factor4) {
      return new Vec3Entry4(this, factor3, factor4);
   }

   public Vec3Entry4(Vec3 vec, float factor3, float factor4) {
      this.vec3 = vec;
      this.factor = factor3;
      this.factor2 = factor4;
   }

   public Vec3Entry4(float posX, float posX2, float posX3, float posY, float posZ) {
      this(new Vec3((double)posX, (double)posX2, (double)posX3), posY, posZ);
   }

   public Vec3Entry4(Vec3Entry4 vec3Entry, float factor3, float factor4) {
      this.vec3 = vec3Entry.vec3;
      this.factor = factor3;
      this.factor2 = factor4;
   }
}
