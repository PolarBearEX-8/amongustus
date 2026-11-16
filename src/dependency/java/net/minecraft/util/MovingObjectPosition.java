package net.minecraft.util;

import client.util.MovingObjectType;
import net.minecraft.entity.Entity;

public class MovingObjectPosition {
   public BlockPos blockPos;
   public MovingObjectType typeOfHit;
   public EnumFacing sideHit;
   public Vec3 hitVec;
   public Entity entityHit;

   public void setBlockPos(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public MovingObjectPosition(Vec3 vec, EnumFacing enumFacing) {
      this(MovingObjectType.BLOCK, vec, enumFacing, BlockPos.origin);
   }

   public MovingObjectPosition(MovingObjectPosition hitResult) {
      if (hitResult != null) {
         this.blockPos = hitResult.getBlockPos();
         this.typeOfHit = hitResult.typeOfHit;
         this.sideHit = hitResult.sideHit;
         this.hitVec = hitResult.hitVec;
         this.entityHit = hitResult.entityHit;
      }
   }

   public MovingObjectPosition(Vec3 vec, EnumFacing enumFacing, BlockPos blockPos2) {
      this(MovingObjectType.BLOCK, vec, enumFacing, blockPos2);
   }

   public MovingObjectPosition(Entity entity) {
      this(entity, new Vec3(entity.posX, entity.posY, entity.posZ));
   }

   public MovingObjectPosition(Entity entity, Vec3 vec) {
      this.typeOfHit = MovingObjectType.ENTITY;
      this.entityHit = entity;
      this.hitVec = vec;
   }

   public MovingObjectPosition(MovingObjectType movingObjectType, Vec3 vec, EnumFacing enumFacing, BlockPos blockPos2) {
      this.typeOfHit = movingObjectType;
      this.blockPos = blockPos2;
      this.sideHit = enumFacing;
      this.hitVec = new Vec3(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   @Override
   public String toString() {
      return "HitResult{type="
         + this.typeOfHit
         + ", blockpos="
         + this.blockPos
         + ", f="
         + this.sideHit
         + ", pos="
         + this.hitVec
         + ", entity="
         + this.entityHit
         + '}';
   }
}
