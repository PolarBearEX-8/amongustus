package net.augustus.utils.rotation;

import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TargetPositionTracker {
   public double lastPosZ;
   public double offsetX;
   public double lastPosX;
   public double lastPosY;
   public int lastUpdateTick = 0;
   public double offsetY;
   public double trackedPosY;
   public double offsetZ;
   public double trackedPosZ;
   public EntityLivingBase targetEntity;
   public double trackedPosX;

   public void updateTrackedPosition(double d) {
      this.lastUpdateTick = this.targetEntity.ticksExisted;
      double d2 = 1.0;
      Vec3 interpolatedPos = this.targetEntity.getInterpolatedPos(1.0F);
      Vec3 vec = interpolatedPos.subtract(this.targetEntity.getInterpolatedPos(0.0F)).scale(d2);
      Vec3 interpolatedPos2 = this.targetEntity.getInterpolatedPos(0.0F);
      double d3 = (this.trackedPosX - interpolatedPos2.xCoord) / (interpolatedPos.xCoord - interpolatedPos2.xCoord);
      double d4 = (this.trackedPosY - interpolatedPos2.yCoord) / (interpolatedPos.yCoord - interpolatedPos2.yCoord);
      double d5 = (this.trackedPosZ - interpolatedPos2.zCoord) / (interpolatedPos.zCoord - interpolatedPos2.zCoord);
      if (!Double.isFinite(d3)) {
         d3 = 0.0;
      }

      if (!Double.isFinite(d4)) {
         d4 = 0.0;
      }

      if (!Double.isFinite(d5)) {
         d5 = 0.0;
      }

      d = Math.max(d, MathHelper.clamp_double(Math.max(Math.max(Math.max(d3, d4), d5), -0.5), -1.0, 1.0) + Math.random() * 0.1);
      this.lastPosX = this.trackedPosX;
      this.lastPosY = this.trackedPosY;
      this.lastPosZ = this.trackedPosZ;
      this.trackedPosX = MathHelper.clamp_double(interpolatedPos2.xCoord + (interpolatedPos.xCoord - interpolatedPos2.xCoord) * d, interpolatedPos.xCoord - 1.0, interpolatedPos.xCoord + 1.0);
      this.trackedPosY = MathHelper.clamp_double(interpolatedPos2.yCoord + (interpolatedPos.yCoord - interpolatedPos2.yCoord) * d, interpolatedPos.yCoord - 1.0, interpolatedPos.yCoord + 1.0);
      this.trackedPosZ = MathHelper.clamp_double(interpolatedPos2.zCoord + (interpolatedPos.zCoord - interpolatedPos2.zCoord) * d, interpolatedPos.zCoord - 1.0, interpolatedPos.zCoord + 1.0);
      this.lastPosX = interpolatedPos.xCoord;
      this.lastPosY = interpolatedPos.yCoord;
      this.lastPosZ = interpolatedPos.zCoord;
   }

   public Vec3 getLastPosition() {
      return new Vec3(this.lastPosX, this.lastPosY, this.lastPosZ);
   }

   public TargetPositionTracker(EntityLivingBase entity) {
      this.targetEntity = entity;
      this.trackedPosX = entity.posX;
      this.trackedPosY = entity.posY;
      this.trackedPosZ = entity.posZ;
   }

   public Vec3 getTrackedPosition() {
      return new Vec3(this.trackedPosX, this.trackedPosY, this.trackedPosZ);
   }
}
