package client.util;

import client.rotation.Rotation;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RayTraceUtils implements MinecraftAccessor {
   public static MovingObjectPosition getMovingObjectPosition(float factor, Rotation rotation, double offset, double offset2) {
      return getMovingObjectPosition2(factor, rotation.yaw, rotation.pitch, offset, 4.5, offset2);
   }

   public static boolean checkEntity(Entity entity, float posX, float posY, float posZ, double offset) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(posX);
      float height = entity.getHeight();
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, entity.getBoundingBox2().minX - (double)height, entity.getBoundingBox2().maxX + (double)height);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, entity.getBoundingBox2().minY - (double)height, entity.getBoundingBox2().maxY + (double)height);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, entity.getBoundingBox2().minZ - (double)height, entity.getBoundingBox2().maxZ + (double)height);
      double offset2 = doubleItem - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset3 = doubleItem2 - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset4 = doubleItem3 - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor = (float)(MathHelper.getDouble6(offset4, offset2) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset3, (double)MathHelper.sqrt_double(offset2 * offset2 + offset4 * offset4)) * 180.0 / Math.PI));
      float floatItem = SimulatedPlayer.getFloat9(posY, factor, 180.0F);
      float floatItem2 = SimulatedPlayer.getFloat9(posZ, factor2, 180.0F);
      MovingObjectPosition movingObjectPosition = getMovingObjectPosition2(1.0F, floatItem, floatItem2, offset, 6.0, 0.0);
      return movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.ENTITY && movingObjectPosition.entityHit.getEntityId() == entity.getEntityId();
   }

   public static MovingObjectPosition resolveMovingObjectPosition(float factor) {
      MovingObjectPosition hitResult = null;
      Entity renderViewEntity = MinecraftAccessor.minecraft.getRenderViewEntity();
      if (renderViewEntity != null && MinecraftAccessor.minecraft.theWorld != null) {
         double blockReachDistance = (double)MinecraftAccessor.minecraft.playerController.getBlockReachDistance();
         hitResult = renderViewEntity.getMovingObjectPosition2(blockReachDistance, factor);
         double offset = blockReachDistance;
         Vec3 vec3 = renderViewEntity.getVec35(factor);
         boolean flag = false;
         if (MinecraftAccessor.minecraft.playerController.processAction3()) {
            blockReachDistance = 6.0;
            offset = 6.0;
         } else if (blockReachDistance > 3.0) {
            flag = true;
         }

         if (hitResult != null) {
            offset = hitResult.hitVec.getCoordinate4(vec3);
         }

         Vec3 vec32 = renderViewEntity.getVec36(factor);
         Vec3 vec33 = vec3.getVec312(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec = null;
         float factor2 = 1.0F;
         List entitiesInAABBexcluding = MinecraftAccessor.minecraft
            .theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getBoundingBox2().contract(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance).offset((double)factor2, (double)factor2, (double)factor2),
               Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(MinecraftAccessor.minecraft.entityRenderer))
            );
         double offset2 = offset;

         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity2 = (Entity)entitiesInAABBexcluding.get(index);
            float height = entity2.getHeight();
            AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
            MovingObjectPosition hitResult2 = boundingBox.calculateIntercept(vec3, vec33);
            if (boundingBox.isVecInside(vec3)) {
               if (offset2 >= 0.0) {
                  entity = entity2;
                  vec = hitResult2 == null ? vec3 : hitResult2.hitVec;
                  offset2 = 0.0;
               }
            } else if (hitResult2 != null) {
               double coordinate = vec3.getCoordinate4(hitResult2.hitVec);
               if (coordinate < offset2 || offset2 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset2 == 0.0) {
                        entity = entity2;
                        vec = hitResult2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec = hitResult2.hitVec;
                     offset2 = coordinate;
                  }
               }
            }
         }

         if (entity != null && flag && vec3.getCoordinate4(vec) > 3.0) {
            entity = null;
            hitResult = new MovingObjectPosition(MovingObjectType.MISS, vec, null, new BlockPos(vec));
         }

         if (entity != null && (offset2 < offset || hitResult == null)) {
            hitResult = new MovingObjectPosition(entity, vec);
            if (!(entity instanceof EntityLivingBase)) {
               boolean flag2 = entity instanceof EntityItemframe;
            }
         }
      }

      return hitResult;
   }

   public static MovingObjectPosition getMovingObjectPosition2(float posX, float posY, float posZ, double posX2, double posY2, double posZ2) {
      return resolveMovingObjectPosition(posX, posY, posZ, posX2, posY2, posZ2, false);
   }

   public static MovingObjectPosition resolveMovingObjectPosition(float posX, float posY, float posZ, double offset, double offset2) {
      return getMovingObjectPosition2(posX, posY, posZ, offset, 4.5, offset2);
   }

   public static MovingObjectPosition getMovingObjectPosition4() {
      MovingObjectPosition hitResult = null;
      Entity renderViewEntity = MinecraftAccessor.minecraft.getRenderViewEntity();
      if (renderViewEntity != null && MinecraftAccessor.minecraft.theWorld != null) {
         double blockReachDistance = (double)MinecraftAccessor.minecraft.playerController.getBlockReachDistance();
         hitResult = renderViewEntity.getMovingObjectPosition2(blockReachDistance, 1.0F);
         double offset = blockReachDistance;
         Vec3 vec3 = renderViewEntity.getVec35(1.0F);
         boolean flag = false;
         if (MinecraftAccessor.minecraft.playerController.processAction3()) {
            blockReachDistance = 6.0;
            offset = 6.0;
         } else if (blockReachDistance > 3.0) {
            flag = true;
         }

         if (hitResult != null) {
            offset = hitResult.hitVec.getCoordinate4(vec3);
         }

         Vec3 vec32 = renderViewEntity.getVec36(1.0F);
         Vec3 vec33 = vec3.getVec312(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec = null;
         float factor = 1.0F;
         List entitiesInAABBexcluding = MinecraftAccessor.minecraft
            .theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getBoundingBox2().contract(vec32.xCoord * blockReachDistance, vec32.yCoord * blockReachDistance, vec32.zCoord * blockReachDistance).offset((double)factor, (double)factor, (double)factor),
               Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(MinecraftAccessor.minecraft.entityRenderer))
            );
         double offset2 = offset;

         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity2 = (Entity)entitiesInAABBexcluding.get(index);
            float height = entity2.getHeight();
            AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
            if (entity2 instanceof EntityLivingBase && ((EntityLivingBase)entity2).positionVec3 != null) {
               EntityLivingBase livingEntity = (EntityLivingBase)entity2;
               float factor2 = entity2.width / 2.0F;
               boundingBox = new AxisAlignedBB(
                     livingEntity.positionVec3.xCoord - (double)factor2,
                     livingEntity.positionVec3.yCoord,
                     livingEntity.positionVec3.zCoord - (double)factor2,
                     livingEntity.positionVec3.xCoord + (double)factor2,
                     livingEntity.positionVec3.yCoord + (double)livingEntity.height,
                     livingEntity.positionVec3.zCoord + (double)factor2
                  )
                  .offset((double)height, (double)height, (double)height);
            }

            MovingObjectPosition hitResult2 = boundingBox.calculateIntercept(vec3, vec33);
            if (boundingBox.isVecInside(vec3)) {
               if (offset2 >= 0.0) {
                  entity = entity2;
                  vec = hitResult2 == null ? vec3 : hitResult2.hitVec;
                  offset2 = 0.0;
               }
            } else if (hitResult2 != null) {
               double coordinate = vec3.getCoordinate4(hitResult2.hitVec);
               if (coordinate < offset2 || offset2 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset2 == 0.0) {
                        entity = entity2;
                        vec = hitResult2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec = hitResult2.hitVec;
                     offset2 = coordinate;
                  }
               }
            }
         }

         if (entity != null && flag && vec3.getCoordinate4(vec) > 3.0) {
            entity = null;
            hitResult = new MovingObjectPosition(MovingObjectType.MISS, vec, null, new BlockPos(vec));
         }

         if (entity != null && (offset2 < offset || hitResult == null)) {
            hitResult = new MovingObjectPosition(entity, vec);
            if (!(entity instanceof EntityLivingBase)) {
               boolean flag2 = entity instanceof EntityItemframe;
            }
         }
      }

      return hitResult;
   }

   public static MovingObjectPosition resolveMovingObjectPosition(float posX, float posY, float posZ, double posX2, double posY2, double posZ2, boolean flag) {
      MovingObjectPosition hitResult = null;
      Entity renderViewEntity = MinecraftAccessor.minecraft.getRenderViewEntity();
      if (renderViewEntity != null && MinecraftAccessor.minecraft.theWorld != null) {
         double offset = Math.max(posY2, posX2);
         if (!flag) {
            hitResult = renderViewEntity.getMovingObjectPosition(offset, posX, posY, posZ);
         }

         double offset2 = offset;
         Vec3 vec3 = renderViewEntity.getVec35(posX);
         boolean flag2 = false;
         if (MinecraftAccessor.minecraft.playerController.processAction3()) {
            offset = 6.0;
            offset2 = 6.0;
         } else if (offset > 3.0) {
            flag2 = true;
         }

         if (hitResult != null) {
            offset2 = hitResult.hitVec.getCoordinate4(vec3);
         }

         double offset3 = Math.max(offset, posX2);
         Vec3 vec32 = renderViewEntity.getVec33(posX, posY, posZ);
         Vec3 vec33 = vec3.getVec312(vec32.xCoord * offset3, vec32.yCoord * offset3, vec32.zCoord * offset3);
         Entity entity = null;
         Vec3 vec = null;
         float factor = 1.0F;
         List entitiesInAABBexcluding = MinecraftAccessor.minecraft
            .theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getBoundingBox2()
                  .contract(vec32.xCoord * offset3, vec32.yCoord * offset3, vec32.zCoord * offset3)
                  .offset((double)factor, (double)factor, (double)factor),
               Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(MinecraftAccessor.minecraft.entityRenderer))
            );
         double offset4 = offset2;

         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity2 = (Entity)entitiesInAABBexcluding.get(index);
            float factor2 = (float)((double)entity2.getHeight() + posZ2);
            AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)factor2, (double)factor2, (double)factor2);
            MovingObjectPosition hitResult2 = boundingBox.calculateIntercept(vec3, vec33);
            if (boundingBox.isVecInside(vec3)) {
               if (offset4 >= 0.0) {
                  entity = entity2;
                  vec = hitResult2 == null ? vec3 : hitResult2.hitVec;
                  offset4 = 0.0;
               }
            } else if (hitResult2 != null) {
               double coordinate = vec3.getCoordinate4(hitResult2.hitVec);
               if (coordinate < offset4 || offset4 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset4 == 0.0) {
                        entity = entity2;
                        vec = hitResult2.hitVec;
                     }
                  } else {
                     entity = entity2;
                     vec = hitResult2.hitVec;
                     offset4 = coordinate;
                  }
               }
            }
         }

         if (entity != null && flag2 && vec3.getCoordinate4(vec) > posX2) {
            entity = null;
            hitResult = new MovingObjectPosition(MovingObjectType.MISS, vec, null, new BlockPos(vec));
         }

         if (entity != null && (offset4 < offset2 || hitResult == null)) {
            hitResult = new MovingObjectPosition(entity, vec);
            if (!(entity instanceof EntityLivingBase)) {
               boolean flag3 = entity instanceof EntityItemframe;
            }
         }
      }

      return hitResult;
   }

   public static MovingObjectPosition getMovingObjectPosition(float factor, Rotation rotation, double posX, double posY, double posZ) {
      return getMovingObjectPosition2(factor, rotation.yaw, rotation.pitch, posX, posY, posZ);
   }

   public static MovingObjectPosition getMovingObjectPosition6(float posX, float posY, float posZ) {
      return getMovingObjectPosition2(posX, posY, posZ, 3.0, (double)MinecraftAccessor.minecraft.playerController.getBlockReachDistance(), 0.0);
   }

   public static MovingObjectPosition getMovingObjectPosition(BlockPos blockPos, float factor, float factor2, double offset) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec34(factor2, factor);
      Vec3 vec32 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      Vec3 vec33 = vec32.getVec312(vec3.xCoord * offset, vec3.yCoord * offset, vec3.zCoord * offset);
      Block block = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock();
      AxisAlignedBB boundingBox = new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)blockPos.getX() + block.getCoordOffset9(),
            (double)blockPos.getY() + block.getCoordOffset6(),
            (double)blockPos.getZ() + block.getCoordOffset15()
         )
         .resolveAxisAlignedBB(0.025, 0.025, 0.025);
      double doubleItem = MathHelper.getDouble7(vec32.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec32.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec32.zCoord, boundingBox.minZ, boundingBox.maxZ);
      MovingObjectPosition hitResult = boundingBox.calculateIntercept(new Vec3(doubleItem, doubleItem2, doubleItem3), vec33);
      if (hitResult != null) {
         hitResult.setBlockPos(blockPos);
      }

      return hitResult;
   }
}
