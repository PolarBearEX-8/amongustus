package net.augustus.utils.target;

import net.augustus.Augustus;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class TargetPriorityMetrics implements MC {

   public static double ultimateTargetScore(Entity entity, Rotation rotation, Entity entity2, double d, boolean flag) {
      if (entity instanceof EntityLivingBase) {
         float pitch = rotation.pitch;
         float yaw = rotation.yaw;
         if (RayTraceUtil.canSeeEntity(entity, 1.0F, yaw, pitch, d)) {
            double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity(entity);
            return ((double)(((EntityLivingBase)entity).hurtTime * 6)) + distanceToEntity;
         }
      }

      return entity instanceof EntityLivingBase && flag && entity2 != null && entity.getEntityId() == entity2.getEntityId() ? 1000.0 : 1000000.0;
   }

   public static double singleTargetScore(Entity entity, Entity entity2) {
      if (entity instanceof EntityLivingBase) {
         if (entity2 != null && entity.getEntityId() == entity2.getEntityId()) {
            return -1.0;
         } else {
            return (double)Math.abs(RotationUtil.getAngleDiffToPos(entity.posX, entity.posY, entity.posZ, Augustus.getInstance().getYawPitchHelper().realYaw, Augustus.getInstance().getYawPitchHelper().realPitch)[0]);
         }
      } else {
         return 1000000.0;
      }
   }

   public static double healthScore(Entity entity) {
      return entity instanceof EntityLivingBase ? (double)((EntityLivingBase)entity).getCurrentHealth() : 1000000.0;
   }

   public static double distanceScore(Entity entity) {
      return (double)mc.thePlayer.getDistanceToEntity(entity);
   }

   public static double shortAimSwitchScore(Entity entity, Rotation rotation, double d) {
      if (entity instanceof EntityLivingBase) {
         float pitch = rotation.pitch;
         float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint(entity, rotation.yaw, pitch);
         float pitch2 = rotation.pitch;
         float yaw2 = rotation.yaw;
         if (RayTraceUtil.canSeeEntity(entity, 1.0F, yaw2, pitch2, d)) {
            return ((double)(((EntityLivingBase)entity).hurtTime * 1000)) + Math.sqrt((double)(floats[0] * floats[0] + floats[1] * floats[1]));
         } else {
            return 11000.0 + Math.sqrt((double)(floats[0] * floats[0] + floats[1] * floats[1]));
         }
      } else {
         return 100000.0;
      }
   }

   public static double shortAimScore(Entity entity, Rotation rotation, double d) {
      if (entity instanceof EntityLivingBase) {
         float pitch = rotation.pitch;
         float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint(entity, rotation.yaw, pitch);
         float pitch2 = rotation.pitch;
         float yaw2 = rotation.yaw;
         return RayTraceUtil.canSeeEntity(entity, 1.0F, yaw2, pitch2, d)
            ? Math.sqrt((double)(floats[0] * floats[0] + floats[1] * floats[1]))
            : 11000.0 + Math.sqrt((double)(floats[0] * floats[0] + floats[1] * floats[1]));
      } else {
         return 1000000.0;
      }
   }

   public static double fovScore(Entity entity) {
      if (entity instanceof EntityLivingBase) {
         return (double)Math.abs(RotationUtil.getAngleDiffToPos(entity.posX, entity.posY, entity.posZ, Augustus.getInstance().getYawPitchHelper().realYaw, Augustus.getInstance().getYawPitchHelper().realPitch)[0]);
      } else {
         return 1000000.0;
      }
   }
}
