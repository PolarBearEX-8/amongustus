package net.augustus.utils.rotation;

import net.augustus.utils.EntityCollidablePredicate;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.List;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RayCastUtil implements MC {

   public static MovingObjectPosition rayCastFromPosition(float f, Vec3 vec, float f2, float f3) {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         mc.pointedEntity = (Entity)null;
         double blockReachDistance = (double)mc.playerController.getBlockReachDistance();
         hit = renderViewEntity.rayTraceCustom(blockReachDistance, f, f2, f3, vec.xCoord, vec.yCoord, vec.zCoord);
         double d = blockReachDistance;
         Vec3 vec2 = vec.addVector(0.0, (double)renderViewEntity.getEyeHeight(), 0.0);
         boolean flag = false;
         if (mc.playerController.extendedReach()) {
            blockReachDistance = 6.0;
            d = 6.0;
         } else {
            if (blockReachDistance > 3.0) {
               flag = true;
            }

            blockReachDistance = blockReachDistance;
         }

         if (hit != null) {
            d = hit.hitVec.distanceTo(vec2);
         }

         Vec3 lookCustom2 = renderViewEntity.getLookCustom2(f, f2, f3);
         Vec3 vec3 = vec2.addVector(lookCustom2.xCoord * blockReachDistance, lookCustom2.yCoord * blockReachDistance, lookCustom2.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec4 = null;
         float f4 = 1.0F;
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox()
                  .addCoord(lookCustom2.xCoord * blockReachDistance, lookCustom2.yCoord * blockReachDistance, lookCustom2.zCoord * blockReachDistance)
                  .expand((double)f4, (double)f4, (double)f4),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(mc.entityRenderer))
            );
         double d2 = d;

         for (Object obj2 : list) {
            Entity entity2 = (Entity)obj2;
            float collisionBorderSize = entity2.getCollisionBorderSize();
            AxisAlignedBB box = entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            MovingObjectPosition hit2 = box.calculateIntercept(vec2, vec3);
            if (box.isVecInside(vec2)) {
               if (d2 >= 0.0) {
                  entity = entity2;
                  vec4 = hit2 == null ? vec2 : hit2.hitVec;
                  d2 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = vec2.distanceTo(hit2.hitVec);
               if (distance < d2 || d2 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (d2 == 0.0) {
                        entity = entity2;
                        vec4 = hit2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec4 = hit2.hitVec;
                     d2 = distance;
                  }
               }
            }
         }

         if (entity != null && flag && vec2.distanceTo(vec4) > 3.0) {
            entity = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec4, null, new BlockPos(vec4));
         }

         if (entity != null && (d2 < d || hit == null)) {
            hit = new MovingObjectPosition(entity, vec4);
            if (entity instanceof EntityLivingBase || entity instanceof EntityItemFrame) {
               ;
            }
         }
      }

      return hit;
   }

   public static MovingObjectPosition rayCast(float f, float f2, float f3) {
      return rayCastFromPosition(f, new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), f2, f3);
   }

   public static MovingObjectPosition rayCastPredicted(int n, Rotation rotation, double d) {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         mc.pointedEntity = (Entity)null;
         double blockReachDistance = (double)mc.playerController.getBlockReachDistance();
         blockReachDistance = Math.max(blockReachDistance, d);
         hit = renderViewEntity.rayTraceCustom(blockReachDistance, (float)n + 1.0F, rotation.yaw, rotation.pitch);
         double d2 = blockReachDistance;
         double[] doubles = PlayerUtil.predictPlayerPosition(n - 1, mc.thePlayer);
         Vec3 vec = new Vec3(doubles[0], doubles[1] + (double)mc.thePlayer.getEyeHeight(), doubles[2]);
         boolean flag = false;
         if (mc.playerController.extendedReach()) {
            blockReachDistance = 6.0;
            d2 = 6.0;
         } else {
            if (blockReachDistance > 3.0) {
               flag = true;
            }

            blockReachDistance = blockReachDistance;
         }

         if (hit != null) {
            d2 = hit.hitVec.distanceTo(vec);
         }

         blockReachDistance = Math.max(blockReachDistance, d);
         d2 = Math.max(d2, d);
         Vec3 lookCustom2 = renderViewEntity.getLookCustom2(1.0F, rotation.yaw, rotation.pitch);
         Vec3 vec2 = vec.addVector(lookCustom2.xCoord * blockReachDistance, lookCustom2.yCoord * blockReachDistance, lookCustom2.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec3 = null;
         float f = 1.0F;
         float f2 = renderViewEntity.width / 2.0F;
         AxisAlignedBB box = new AxisAlignedBB(
            doubles[0] - (double)f2, doubles[1], doubles[2] - (double)f2, doubles[0] + (double)f2, doubles[1] + (double)renderViewEntity.height, doubles[2] + (double)f2
         );
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               box.addCoord(lookCustom2.xCoord * blockReachDistance, lookCustom2.yCoord * blockReachDistance, lookCustom2.zCoord * blockReachDistance).expand((double)f, (double)f, (double)f),
               Predicates.and(new Predicate[]{EntitySelectors.NOT_SPECTATING})
            );
         double d3 = d2;
         Object obj2 = null;

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            float collisionBorderSize = entity2.getCollisionBorderSize();
            float f3 = entity2.width / 2.0F;
            AxisAlignedBB entityBoundingBox = renderViewEntity.getEntityBoundingBox();
            if (entity2 instanceof EntityLivingBase) {
               EntityLivingBase entity3 = (EntityLivingBase)entity2;
               int n2 = mc.thePlayer.ticksExisted + n;
               int ticksExisted = mc.thePlayer.ticksExisted;
               Vec3 clampedPosition = TrajectoryPredictionUtil.getClampedPosition(TrajectoryPredictionUtil.predictTargetPositions(mc.thePlayer, entity3, ticksExisted, n2, true), 999999);
               entityBoundingBox = new AxisAlignedBB(
                  clampedPosition.xCoord - (double)f3,
                  clampedPosition.yCoord,
                  clampedPosition.zCoord - (double)f3,
                  clampedPosition.xCoord + (double)f3,
                  clampedPosition.yCoord + (double)renderViewEntity.height,
                  clampedPosition.zCoord + (double)f3
               );
            }

            AxisAlignedBB box2 = entityBoundingBox.expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            MovingObjectPosition hit2 = box2.calculateIntercept(vec, vec2);
            if (box2.isVecInside(vec)) {
               if (d3 >= 0.0) {
                  entity = entity2;
                  vec3 = hit2 == null ? vec : hit2.hitVec;
                  d3 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = vec.distanceTo(hit2.hitVec);
               if (distance < d3 || d3 == 0.0) {
                  if (entity2 != renderViewEntity.ridingEntity || false) {
                     entity = entity2;
                     vec3 = hit2.hitVec;
                     d3 = distance;
                  } else if (d3 == 0.0) {
                     entity = entity2;
                     vec3 = hit2.hitVec;
                  }
               }
            }
         }

         if (entity != null && flag && vec.distanceTo(vec3) > d) {
            entity = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec3, null, new BlockPos(vec3));
         }

         if (entity != null && (d3 < d2 || hit == null)) {
            hit = new MovingObjectPosition(entity, vec3);
            if (entity instanceof EntityLivingBase || entity instanceof EntityItemFrame) {
               ;
            }
         }
      }

      return hit;
   }

   public static MovingObjectPosition rayCastFromPosition(float f, Vec3 vec, Rotation rotation) {
      return rayCastFromPosition(f, vec, rotation.yaw, rotation.pitch);
   }

   public static MovingObjectPosition rayCast(float f, Rotation rotation) {
      return rayCastFromPosition(f, new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), rotation.yaw, rotation.pitch);
   }
}
