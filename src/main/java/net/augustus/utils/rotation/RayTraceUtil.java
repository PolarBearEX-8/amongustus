package net.augustus.utils.rotation;

import net.augustus.utils.EntityCollidablePredicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RayTraceUtil implements MC {

   public static MovingObjectPosition rayTraceEntity(float f, Rotation rotation, double d, double d2) {
      return rayTrace(f, rotation.yaw, rotation.pitch, d, 4.5, d2);
   }

   public static boolean canSeeEntity(Entity entity, float f, float f2, float f3, double d) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(f);
      float collisionBorderSize = entity.getCollisionBorderSize();
      double d2 = MathHelper.clamp_double(positionEyes.xCoord, entity.getEntityBoundingBox().minX - (double)collisionBorderSize, entity.getEntityBoundingBox().maxX + (double)collisionBorderSize);
      double d3 = MathHelper.clamp_double(positionEyes.yCoord, entity.getEntityBoundingBox().minY - (double)collisionBorderSize, entity.getEntityBoundingBox().maxY + (double)collisionBorderSize);
      double d4 = MathHelper.clamp_double(positionEyes.zCoord, entity.getEntityBoundingBox().minZ - (double)collisionBorderSize, entity.getEntityBoundingBox().maxZ + (double)collisionBorderSize);
      double d5 = d2 - mc.thePlayer.posX;
      double d6 = d3 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d7 = d4 - mc.thePlayer.posZ;
      float f4 = (float)(MathHelper.atan2(d7, d5) * 180.0 / Math.PI - 90.0);
      float f5 = (float)(-(MathHelper.atan2(d6, (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7)) * 180.0 / Math.PI));
      float f6 = RotationUtil.updateAngle(f2, f4, 180.0F);
      float f7 = RotationUtil.updateAngle(f3, f5, 180.0F);
      MovingObjectPosition hit = rayTrace(1.0F, f6, f7, d, 6.0, 0.0);
      return hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit.getEntityId() == entity.getEntityId();
   }

   public static MovingObjectPosition rayTraceBlock(BlockPos pos, float f, float f2, double d) {
      Vec3 vectorForRotation = mc.thePlayer.getVectorForRotation(f2, f);
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      Vec3 vec = positionEyes.addVector(vectorForRotation.xCoord * d, vectorForRotation.yCoord * d, vectorForRotation.zCoord * d);
      Block block = mc.theWorld.getBlockState(pos).getBlock();
      AxisAlignedBB box = new AxisAlignedBB(
            (double)pos.getX(),
            (double)pos.getY(),
            (double)pos.getZ(),
            (double)pos.getX() + block.getBlockBoundsMaxX(),
            (double)pos.getY() + block.getBlockBoundsMaxY(),
            (double)pos.getZ() + block.getBlockBoundsMaxZ()
         )
         .contract(0.025, 0.025, 0.025);
      double d2 = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d3 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d4 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      MovingObjectPosition hit = box.calculateIntercept(new Vec3(d2, d3, d4), vec);
      if (hit != null) {
         hit.setBlockPos(pos);
      }

      return hit;
   }

   public static MovingObjectPosition rayTraceEntity(float f, float f2, float f3, double d, double d2) {
      return rayTrace(f, f2, f3, d, 4.5, d2);
   }

   public static MovingObjectPosition rayTraceCurrentLook(float f) {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         double blockReachDistance = (double)mc.playerController.getBlockReachDistance();
         hit = renderViewEntity.rayTrace(blockReachDistance, f);
         double d = blockReachDistance;
         Vec3 positionEyes = renderViewEntity.getPositionEyes(f);
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
            d = hit.hitVec.distanceTo(positionEyes);
         }

         Vec3 look = renderViewEntity.getLook(f);
         Vec3 vec = positionEyes.addVector(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec2 = null;
         float f2 = 1.0F;
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox()
                  .addCoord(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance)
                  .expand((double)f2, (double)f2, (double)f2),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(mc.entityRenderer))
            );
         double d2 = d;
         Object obj = null;

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            float collisionBorderSize = entity2.getCollisionBorderSize();
            AxisAlignedBB box = entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            MovingObjectPosition hit2 = box.calculateIntercept(positionEyes, vec);
            if (box.isVecInside(positionEyes)) {
               if (d2 >= 0.0) {
                  entity = entity2;
                  vec2 = hit2 == null ? positionEyes : hit2.hitVec;
                  d2 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = positionEyes.distanceTo(hit2.hitVec);
               if (distance < d2 || d2 == 0.0) {
                  if (entity2 != renderViewEntity.ridingEntity || false) {
                     entity = entity2;
                     vec2 = hit2.hitVec;
                     d2 = distance;
                  } else if (d2 == 0.0) {
                     entity = entity2;
                     vec2 = hit2.hitVec;
                  }
               }
            }
         }

         if (entity != null && flag && positionEyes.distanceTo(vec2) > 3.0) {
            entity = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec2, null, new BlockPos(vec2));
         }

         if (entity != null && (d2 < d || hit == null)) {
            hit = new MovingObjectPosition(entity, vec2);
            if (entity instanceof EntityLivingBase || entity instanceof EntityItemFrame) {
               ;
            }
         }
      }

      return hit;
   }

   public static MovingObjectPosition rayTrace(float f, float f2, float f3, double d, double d2, double d3, boolean flag) {
      return rayTraceCustom(
         f, f2, f3, d, d2, d3, flag, mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, (AxisAlignedBB)null, (EntityLivingBase)null
      );
   }

   public static MovingObjectPosition rayTraceDefaultReach(float f, float f2, float f3) {
      return rayTrace(f, f2, f3, 3.0, (double)mc.playerController.getBlockReachDistance(), 0.0);
   }

   public static MovingObjectPosition rayTraceCustom(
      float f,
      float f2,
      float f3,
      double d,
      double d2,
      double d3,
      boolean flag,
      double d4,
      double d5,
      double d6,
      AxisAlignedBB box,
      EntityLivingBase entity
   ) {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         double d7 = Math.max(d2, d);
         if (!flag) {
            hit = renderViewEntity.rayTraceCustom(d7, f, f2, f3, d4, d5, d6);
         }

         double d8 = d7;
         double d9 = f == 1.0F ? d4 : renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)f;
         double d10 = f == 1.0F
            ? d5 + (double)renderViewEntity.getEyeHeight()
            : renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)f + (double)renderViewEntity.getEyeHeight();
         double d11 = f == 1.0F ? d6 : renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)f;
         boolean flag2 = false;
         if (mc.playerController.extendedReach()) {
            d7 = 6.0;
            d8 = 6.0;
         } else {
            if (d7 > 3.0) {
               flag2 = true;
            }

            d7 = d7;
         }

         if (hit != null) {
            d8 = hit.hitVec.distanceTo(d9, d10, d11);
         }

         d7 = Math.max(d7, d);
         float f4 = f2;
         float f5 = f3;
         if (f != 1.0F && f != 2.0F) {
            f5 = renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * f;
            f4 = renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * f;
         }

         float f6 = MathHelper.cos(-f4 * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f7 = MathHelper.sin(-f4 * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f8 = -MathHelper.cos(-f5 * (float) (Math.PI / 180.0));
         float f9 = MathHelper.sin(-f5 * (float) (Math.PI / 180.0));
         double d12 = (double)(f7 * f8);
         double d13 = (double)f9;
         double d14 = (double)(f6 * f8);
         double d15 = d9 + d12 * d7;
         double d16 = d10 + d13 * d7;
         double d17 = d11 + d14 * d7;
         Entity entity2 = null;
         Vec3 vec = null;
         float f10 = 1.0F;
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox().addCoord(d12 * d7, d13 * d7, d14 * d7).expand((double)f10, (double)f10, (double)f10),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(mc.entityRenderer))
            );
         if (entity != null && !list.contains(entity)) {
            list.add(entity);
         }

         double d18 = d8;

         for (int i = 0; i < list.size(); i++) {
            Entity entity3 = (Entity)list.get(i);
            float f11 = (float)((double)entity3.getCollisionBorderSize() + d3);
            AxisAlignedBB box2;
            if (entity3 == entity) {
               box2 = box;
            } else {
               box2 = entity3.getEntityBoundingBox().expand((double)f11, (double)f11, (double)f11);
            }

            MovingObjectPosition hit2 = box2.calculateInterceptFast(d9, d10, d11, d15, d16, d17);
            if (box2.isVecInside(d9, d10, d11)) {
               if (d18 >= 0.0) {
                  entity2 = entity3;
                  vec = hit2 == null ? new Vec3(d9, d10, d11) : hit2.hitVec;
                  d18 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = hit2.hitVec.distanceTo(d9, d10, d11);
               if (distance < d18 || d18 == 0.0) {
                  if (entity3 != renderViewEntity.ridingEntity || false) {
                     entity2 = entity3;
                     vec = hit2.hitVec;
                     d18 = distance;
                  } else if (d18 == 0.0) {
                     entity2 = entity3;
                     vec = hit2.hitVec;
                  }
               }
            }
         }

         if (entity2 != null && flag2 && vec.distanceTo(d9, d10, d11) > d) {
            entity2 = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec, null, new BlockPos(vec), true);
         }

         if (entity2 != null && (d18 < d8 || hit == null)) {
            hit = new MovingObjectPosition(entity2, vec);
         }
      }

      return hit;
   }

   public static MovingObjectPosition rayTraceServerPositions() {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         double blockReachDistance = (double)mc.playerController.getBlockReachDistance();
         hit = renderViewEntity.rayTrace(blockReachDistance, 1.0F);
         double d = blockReachDistance;
         Vec3 positionEyes = renderViewEntity.getPositionEyes(1.0F);
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
            d = hit.hitVec.distanceTo(positionEyes);
         }

         Vec3 look = renderViewEntity.getLook(1.0F);
         Vec3 vec = positionEyes.addVector(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance);
         Entity entity = null;
         Vec3 vec2 = null;
         float f = 1.0F;
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox()
                  .addCoord(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance)
                  .expand((double)f, (double)f, (double)f),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(mc.entityRenderer))
            );
         double d2 = d;
         Object obj = null;

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            float collisionBorderSize = entity2.getCollisionBorderSize();
            AxisAlignedBB box = entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            if (entity2 instanceof EntityLivingBase && ((EntityLivingBase)entity2).interpolatedServerPos != null) {
               EntityLivingBase entity3 = (EntityLivingBase)entity2;
               float f2 = entity2.width / 2.0F;
               box = new AxisAlignedBB(
                     entity3.interpolatedServerPos.xCoord - (double)f2,
                     entity3.interpolatedServerPos.yCoord,
                     entity3.interpolatedServerPos.zCoord - (double)f2,
                     entity3.interpolatedServerPos.xCoord + (double)f2,
                     entity3.interpolatedServerPos.yCoord + (double)entity3.height,
                     entity3.interpolatedServerPos.zCoord + (double)f2
                  )
                  .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            }

            MovingObjectPosition hit2 = box.calculateIntercept(positionEyes, vec);
            if (box.isVecInside(positionEyes)) {
               if (d2 >= 0.0) {
                  entity = entity2;
                  vec2 = hit2 == null ? positionEyes : hit2.hitVec;
                  d2 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = positionEyes.distanceTo(hit2.hitVec);
               if (distance < d2 || d2 == 0.0) {
                  if (entity2 != renderViewEntity.ridingEntity || false) {
                     entity = entity2;
                     vec2 = hit2.hitVec;
                     d2 = distance;
                  } else if (d2 == 0.0) {
                     entity = entity2;
                     vec2 = hit2.hitVec;
                  }
               }
            }
         }

         if (entity != null && flag && positionEyes.distanceTo(vec2) > 3.0) {
            entity = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec2, null, new BlockPos(vec2));
         }

         if (entity != null && (d2 < d || hit == null)) {
            hit = new MovingObjectPosition(entity, vec2);
            if (entity instanceof EntityLivingBase || entity instanceof EntityItemFrame) {
               ;
            }
         }
      }

      return hit;
   }

   public static MovingObjectPosition rayTrace(float f, Rotation rotation, double d, double d2, double d3) {
      return rayTrace(f, rotation.yaw, rotation.pitch, d, d2, d3);
   }

   public static MovingObjectPosition rayTrace(float f, float f2, float f3, double d, double d2, double d3) {
      return rayTrace(f, f2, f3, d, d2, d3, false);
   }

   public static MovingObjectPosition rayTraceSingleBox(
      float f, float f2, double d, double d2, double d3, double d4, AxisAlignedBB box, EntityLivingBase entity
   ) {
      MovingObjectPosition hit = null;
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         double d5 = 6.0;
         d5 = Math.max(d5, d);
         double d6 = d5;
         double d7 = d3 + (double)renderViewEntity.getEyeHeight();
         boolean flag = false;
         if (mc.playerController.extendedReach()) {
            d5 = 6.0;
            d6 = 6.0;
         } else if (d5 > 3.0) {
            flag = true;
         }

         d5 = Math.max(d5, d);
         float f3 = MathHelper.cos(-f * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f4 = MathHelper.sin(-f * (float) (Math.PI / 180.0) - (float) Math.PI);
         float f5 = -MathHelper.cos(-f2 * (float) (Math.PI / 180.0));
         float f6 = MathHelper.sin(-f2 * (float) (Math.PI / 180.0));
         double d8 = (double)(f4 * f5);
         double d9 = (double)f6;
         double d10 = (double)(f3 * f5);
         double d11 = d2 + d8 * d5;
         double d12 = d7 + d9 * d5;
         double d13 = d4 + d10 * d5;
         EntityLivingBase entity2 = null;
         Vec3 vec = null;
         MovingObjectPosition hit2 = box.calculateInterceptFast(d2, d7, d4, d11, d12, d13);
         if (box.isVecInside(d2, d7, d4)) {
            if (d6 >= 0.0) {
               entity2 = entity;
               vec = hit2 == null ? new Vec3(d2, d7, d4) : hit2.hitVec;
               double d14 = 0.0;
            }
         } else if (hit2 != null) {
            if (hit2.hitVec.distanceTo(d2, d7, d4) < d6 || d6 == 0.0) {
               if (entity != renderViewEntity.ridingEntity || false) {
                  entity2 = entity;
                  vec = hit2.hitVec;
               } else if (d6 == 0.0) {
                  entity2 = entity;
                  vec = hit2.hitVec;
               }
            }
         }

         if (entity2 != null && flag && vec.distanceTo(d2, d7, d4) > d) {
            entity2 = null;
            hit = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec, null, new BlockPos(vec), true);
         }

         if (entity2 != null) {
            hit = new MovingObjectPosition(entity2, vec);
         }
      }

      return hit == null ? new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, Vec3.ZERO, EnumFacing.NORTH, null) : hit;
   }
}
