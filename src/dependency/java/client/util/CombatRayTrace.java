package client.util;

import client.rotation.Rotation;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class CombatRayTrace implements MinecraftAccessor {
   public static MovingObjectPosition resolveMovingObjectPosition(float factor, Vec3 vec, float factor2, float factor3) {
      MovingObjectPosition hitResult = null;
      Entity renderViewEntity = minecraft.getRenderViewEntity();
      if (renderViewEntity != null && minecraft.theWorld != null) {
         minecraft.pointedEntity = null;
         double blockReachDistance = (double)minecraft.playerController.getBlockReachDistance();
         hitResult = renderViewEntity.resolveMovingObjectPosition(blockReachDistance, factor, factor2, factor3, vec);
         double offset = blockReachDistance;
         Vec3 vec3 = vec.getVec312(0.0, (double)renderViewEntity.getHeight2(), 0.0);
         boolean flag = false;
         if (minecraft.playerController.processAction3()) {
            blockReachDistance = 6.0;
            offset = 6.0;
         } else if (blockReachDistance > 3.0) {
            flag = true;
         }

         if (hitResult != null) {
            offset = hitResult.hitVec.getCoordinate4(vec3);
         }

         Vec3 vec32 = renderViewEntity.getVec33(factor, factor2, factor3);
         Vec3 vec33 = vec3.getVec312(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec2 = null;
         float factor4 = 1.0F;
         List entitiesInAABBexcluding = minecraft.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getBoundingBox2()
                  .contract(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance)
                  .offset((double)factor4, (double)factor4, (double)factor4),
               Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(minecraft.entityRenderer))
            );
         double offset2 = offset;

         for (Entity entity2 : (Iterable<Entity>) (entitiesInAABBexcluding)) {
            float height = entity2.getHeight();
            AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
            MovingObjectPosition hitResult2 = boundingBox.calculateIntercept(vec3, vec33);
            if (boundingBox.isVecInside(vec3)) {
               if (offset2 >= 0.0) {
                  entity = entity2;
                  vec2 = hitResult2 == null ? vec3 : hitResult2.hitVec;
                  offset2 = 0.0;
               }
            } else if (hitResult2 != null) {
               double coordinate = vec3.getCoordinate4(hitResult2.hitVec);
               if (coordinate < offset2 || offset2 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset2 == 0.0) {
                        entity = entity2;
                        vec2 = hitResult2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec2 = hitResult2.hitVec;
                     offset2 = coordinate;
                  }
               }
            }
         }

         if (entity != null && flag && vec3.getCoordinate4(vec2) > 3.0) {
            entity = null;
            hitResult = new MovingObjectPosition(MovingObjectType.MISS, vec2, null, new BlockPos(vec2));
         }

         if (entity != null && (offset2 < offset || hitResult == null)) {
            hitResult = new MovingObjectPosition(entity, vec2);
            if (!(entity instanceof EntityLivingBase)) {
               boolean flag2 = entity instanceof EntityItemframe;
            }
         }
      }

      return hitResult;
   }

   public static MovingObjectPosition getMovingObjectPosition(int count, Rotation rotation, double offset) {
      MovingObjectPosition hitResult = null;
      Entity renderViewEntity = minecraft.getRenderViewEntity();
      if (renderViewEntity != null && minecraft.theWorld != null) {
         minecraft.pointedEntity = null;
         double offset2 = Math.max((double)minecraft.playerController.getBlockReachDistance(), offset);
         hitResult = renderViewEntity.getMovingObjectPosition(offset2, (float)count + 1.0F, rotation.yaw, rotation.pitch);
         double offset3 = offset2;
         double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(count - 1, minecraft.thePlayer);
         Vec3 vec = new Vec3(coordOffsetArray[0], coordOffsetArray[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[2]);
         boolean flag = false;
         if (minecraft.playerController.processAction3()) {
            offset2 = 6.0;
            offset3 = 6.0;
         } else if (offset2 > 3.0) {
            flag = true;
         }

         if (hitResult != null) {
            offset3 = hitResult.hitVec.getCoordinate4(vec);
         }

         double offset4 = Math.max(offset2, offset);
         double offset5 = Math.max(offset3, offset);
         Vec3 vec3 = renderViewEntity.getVec33(1.0F, rotation.yaw, rotation.pitch);
         Vec3 vec32 = vec.getVec312(vec3.xCoord * offset4, vec3.yCoord * offset4, vec3.zCoord * offset4);
         Entity entity = null;
         Vec3 vec2 = null;
         float factor = 1.0F;
         float factor2 = renderViewEntity.width / 2.0F;
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            coordOffsetArray[0] - (double)factor2, coordOffsetArray[1], coordOffsetArray[2] - (double)factor2, coordOffsetArray[0] + (double)factor2, coordOffsetArray[1] + (double)renderViewEntity.height, coordOffsetArray[2] + (double)factor2
         );
         List entitiesInAABBexcluding = minecraft.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               boundingBox.contract(vec3.xCoord * offset4, vec3.yCoord * offset4, vec3.zCoord * offset4).offset((double)factor, (double)factor, (double)factor),
               Predicates.and(new Predicate[]{LivingEntityFilter.predicate4})
            );
         double offset6 = offset5;

         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity2 = (Entity)entitiesInAABBexcluding.get(index);
            float height = entity2.getHeight();
            float factor3 = entity2.width / 2.0F;
            AxisAlignedBB boundingBox2 = renderViewEntity.getBoundingBox2();
            if (entity2 instanceof EntityLivingBase) {
               EntityLivingBase livingEntity = (EntityLivingBase)entity2;
               int count5 = minecraft.thePlayer.ticksExisted + count;
               byte byteValue = (byte)52;
               boolean flag2 = true;
               int count6 = count5;
               int ticksExisted = minecraft.thePlayer.ticksExisted;
               EntityLivingBase livingEntity2 = livingEntity;
               Vec3 vec4 = PositionHistory.resolveTrackedPosition(
                  PositionHistory.resolveArrayList(minecraft.thePlayer, livingEntity2, ticksExisted, count6, 136, flag2, byteValue, 10230076), 999999
               );
               boundingBox2 = new AxisAlignedBB(
                  vec4.xCoord - (double)factor3,
                  vec4.yCoord,
                  vec4.zCoord - (double)factor3,
                  vec4.xCoord + (double)factor3,
                  vec4.yCoord + (double)renderViewEntity.height,
                  vec4.zCoord + (double)factor3
               );
            }

            AxisAlignedBB boundingBox3 = boundingBox2.offset((double)height, (double)height, (double)height);
            MovingObjectPosition hitResult2 = boundingBox3.calculateIntercept(vec, vec32);
            if (boundingBox3.isVecInside(vec)) {
               if (offset6 >= 0.0) {
                  entity = entity2;
                  vec2 = hitResult2 == null ? vec : hitResult2.hitVec;
                  offset6 = 0.0;
               }
            } else if (hitResult2 != null) {
               double coordinate = vec.getCoordinate4(hitResult2.hitVec);
               if (coordinate < offset6 || offset6 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset6 == 0.0) {
                        entity = entity2;
                        vec2 = hitResult2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec2 = hitResult2.hitVec;
                     offset6 = coordinate;
                  }
               }
            }
         }

         if (entity != null && flag && vec.getCoordinate4(vec2) > offset) {
            entity = null;
            hitResult = new MovingObjectPosition(MovingObjectType.MISS, vec2, null, new BlockPos(vec2));
         }

         if (entity != null && (offset6 < offset5 || hitResult == null)) {
            hitResult = new MovingObjectPosition(entity, vec2);
            if (!(entity instanceof EntityLivingBase)) {
               boolean flag3 = entity instanceof EntityItemframe;
            }
         }
      }

      return hitResult;
   }

   public static MovingObjectPosition getMovingObjectPosition(float factor, Rotation rotation) {
      return resolveMovingObjectPosition(factor, new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ), rotation.yaw, rotation.pitch);
   }

   public static MovingObjectPosition getMovingObjectPosition(float posX, float posY, float posZ) {
      return resolveMovingObjectPosition(posX, new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ), posY, posZ);
   }

   public static MovingObjectPosition resolveMovingObjectPosition(float factor, Vec3 vec, Rotation rotation) {
      return resolveMovingObjectPosition(factor, vec, rotation.yaw, rotation.pitch);
   }
}
