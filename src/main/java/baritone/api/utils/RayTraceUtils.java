package baritone.api.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public final class RayTraceUtils {
   public static MovingObjectPosition rayTraceTowards(Entity entity, Rotation rotation, double d) {
      return rayTraceTowards(entity, rotation, d, false);
   }

   public static MovingObjectPosition rayTraceTowards(Entity entity, Rotation rotation, double d, boolean flag) {
      Vec3 vec;
      if (flag) {
         vec = inferSneakingEyePosition(entity);
      } else {
         vec = entity.getPositionEyes(1.0F);
      }

      Vec3 vec2 = RotationUtils.calcLookDirectionFromRotation(rotation);
      Vec3 vec3 = vec.addVector(vec2.xCoord * d, vec2.yCoord * d, vec2.zCoord * d);
      return entity.entityWorld.rayTraceBlocks(vec.xCoord, vec.yCoord, vec.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord, false, false, true);
   }

   private RayTraceUtils() {
   }

   public static Vec3 inferSneakingEyePosition(Entity entity) {
      return new Vec3(entity.posX, entity.posY + IPlayerContext.eyeHeight(true), entity.posZ);
   }
}
