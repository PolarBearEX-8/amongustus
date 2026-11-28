package net.minecraft.util;

import net.minecraft.entity.Entity;

public class MovingObjectPosition {
   private BlockPos blockPos;
   public MovingObjectPosition.MovingObjectType typeOfHit;
   public EnumFacing sideHit;
   public Vec3 hitVec;
   public Entity entityHit;

   public MovingObjectPosition(MovingObjectPosition.MovingObjectType movingObjectType, Vec3 vec, EnumFacing facing, BlockPos pos) {
      this.typeOfHit = movingObjectType;
      this.blockPos = pos;
      this.sideHit = facing;
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

   public MovingObjectPosition(MovingObjectPosition hit) {
      if (hit != null) {
         this.blockPos = hit.getBlockPos();
         this.typeOfHit = hit.typeOfHit;
         this.sideHit = hit.sideHit;
         this.hitVec = hit.hitVec;
         this.entityHit = hit.entityHit;
      }
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void setBlockPos(BlockPos pos) {
      this.blockPos = pos;
   }

   public MovingObjectPosition(Vec3 vec, EnumFacing facing) {
      this(MovingObjectPosition.MovingObjectType.BLOCK, vec, facing, BlockPos.ORIGIN);
   }

   public MovingObjectPosition(Vec3 vec, EnumFacing facing, BlockPos pos) {
      this(MovingObjectPosition.MovingObjectType.BLOCK, vec, facing, pos);
   }

   public MovingObjectPosition(Entity entity, Vec3 vec) {
      this.typeOfHit = MovingObjectPosition.MovingObjectType.ENTITY;
      this.entityHit = entity;
      this.hitVec = vec;
   }

   public MovingObjectPosition(MovingObjectPosition.MovingObjectType movingObjectType, Vec3 vec, EnumFacing facing, BlockPos pos, boolean flag) {
      this.typeOfHit = movingObjectType;
      this.blockPos = pos;
      this.sideHit = facing;
      this.hitVec = vec;
   }

   public MovingObjectPosition(Entity entity) {
      this(entity, new Vec3(entity.posX, entity.posY, entity.posZ));
   }


   public enum MovingObjectType {
      MISS,
      BLOCK,
      ENTITY;
   }
}
