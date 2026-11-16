package cc.baritone.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public final class CoreCustom {
   public static Vec3 resolveVec3(Entity entity) {
      return new Vec3(entity.posX, entity.posY + StaticListener.computeValue(true), entity.posZ);
   }

   public static MovingObjectPosition resolveMovingObjectPositionByEntity(Entity entity, DefaultCustom defaultCustom, double offset) {
      return resolveMovingObjectPosition(entity, defaultCustom, offset, false);
   }

   private CoreCustom() {
   }

   public static MovingObjectPosition resolveMovingObjectPosition(Entity entity, DefaultCustom defaultCustom, double offset, boolean flag) {
      Vec3 vec;
      if (flag) {
         vec = resolveVec3(entity);
      } else {
         vec = entity.getVec35(1.0F);
      }

      Vec3 vec2 = BreadthFirstRecorder.resolveVecByDefaultCustom(defaultCustom);
      Vec3 vec3 = vec.getVec312(vec2.xCoord * offset, vec2.yCoord * offset, vec2.zCoord * offset);
      return entity.worldObj.getMovingObjectPosition4(vec.xCoord, vec.yCoord, vec.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord, false, false, true);
   }
}
