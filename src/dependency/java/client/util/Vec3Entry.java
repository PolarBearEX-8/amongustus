package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class Vec3Entry {
   public boolean enabled;
   public boolean enabled2;
   public Vec3 vec4;
   public Vec3 vec3;
   public Vec3 vec2;
   public float factor;

   public Vec3Entry(EntityLivingBase livingEntity) {
      this.vec3 = (Vec3)livingEntity.backtrackRecord.getObject3(0);
      this.vec4 = (Vec3)livingEntity.backtrackRecord.getObject3(1);
      this.vec2 = (Vec3)livingEntity.backtrackRecord.getObject3(2);
      this.enabled = livingEntity.onGround;
      this.enabled2 = livingEntity.isSprinting();
      this.factor = livingEntity.scaleFactor22;
   }
}
