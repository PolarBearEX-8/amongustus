package net.augustus.modules.modes.anticheat;

import net.augustus.utils.rotation.RotationUtil;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public final class AntiCheatPlayerData implements MC {
   private double lastAttackRange;
   private Vec3 prevPlayerPos;
   private Vec3 targetMoveDelta;
   public double trackedPosX;
   private EntityLivingBase targetEntity;
   private int targetEntityId = -1;
   private float prevYawToTarget;
   private float serverYawToTarget;
   private float pitchToTarget;
   public double trackedPosY;
   private float yawToTarget;
   private long lastActionTime;
   private long lastAttackTime;
   public double trackedPosZ;
   private float serverPitchToTarget;
   private long lastTargetSwitchTime;
   private Vec3 lastPlayerPos = new Vec3(0.0, 0.0, 0.0);
   public boolean trackedOnGround;
   private float prevPitchToTarget;

   public float getPitchToTarget() {
      return this.pitchToTarget;
   }

   private static float computeServerPitchToEntity(Entity entity, double d, double d2, double d3) {
      Vec3 nearestPointOnEntity = RotationUtil.getNearestPointOnEntity(entity);
      double d4 = nearestPointOnEntity.yCoord + 1.6200000047683716 - (d2 + 1.6200000047683716);
      double d5 = nearestPointOnEntity.xCoord - d;
      double d6 = nearestPointOnEntity.zCoord - d3;
      return (float)(-Math.atan2(d4, Math.sqrt(d5 * d5 + d6 * d6)) * 180.0 / 3.141592653589793);
   }

   public int getTargetEntityId() {
      return this.targetEntityId;
   }

   private static float computeYawToPos(Vec3 vec, double d, double d2) {
      double d3 = vec.xCoord - d;
      return (float)Math.toDegrees(Math.atan2(vec.zCoord - d2, d3)) - 90.0F;
   }

   public boolean hasAttackedWithin(long l) {
      long l3;
      int n = (l3 = System.currentTimeMillis() - this.lastAttackTime - l) == 0L ? 0 : (l3 < 0L ? -1 : 1);
      if (null == null) {
         n = n <= 0 ? 1 : 0;
      }

      return (n != 0);
   }

   public AntiCheatPlayerData() {
      this.prevPlayerPos = new Vec3(0.0, 0.0, 0.0);
      this.targetMoveDelta = new Vec3(0.0, 0.0, 0.0);
      this.lastAttackTime = 0L;
      this.lastTargetSwitchTime = 0L;
      this.lastActionTime = 0L;
   }

   public void setLastAttackRange(double d) {
      this.lastAttackRange = d;
   }

   public float getYawToTarget() {
      return this.yawToTarget;
   }

   public EntityLivingBase getTargetEntity() {
      return this.targetEntity;
   }

   public void recordActionTime() {
      this.lastActionTime = System.currentTimeMillis();
   }

   public void resetTarget() {
      this.targetEntityId = 0;
      this.targetEntity = null;
   }

   private static float computeServerYawToEntity(Entity entity, double d, double d2) {
      Vec3 nearestPointOnEntity = RotationUtil.getNearestPointOnEntity(entity);
      double d3 = nearestPointOnEntity.xCoord - d;
      return (float)Math.toDegrees(Math.atan2(nearestPointOnEntity.zCoord - d2, d3)) - 90.0F;
   }

   public void updateTracking() {
      double xCoord = this.lastPlayerPos.xCoord;
      double yCoord = this.lastPlayerPos.yCoord;
      double zCoord = this.lastPlayerPos.zCoord;
      double xCoord2 = this.prevPlayerPos.xCoord;
      double yCoord2 = this.prevPlayerPos.yCoord;
      double zCoord2 = this.prevPlayerPos.zCoord;
      if (this.targetEntity != null) {
         Vec3 vec = new Vec3(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ);
         Vec3 vec2 = new Vec3(this.targetEntity.lastTickPosX, this.targetEntity.lastTickPosY, this.targetEntity.lastTickPosZ);
         this.yawToTarget = computeYawToPos(vec, xCoord, zCoord);
         this.pitchToTarget = computePitchToPos(vec, xCoord, yCoord, zCoord);
         this.prevYawToTarget = computeYawToPos(vec2, xCoord2, zCoord2);
         this.prevPitchToTarget = computePitchToPos(vec2, xCoord2, yCoord2, zCoord2);
         this.serverYawToTarget = computeServerYawToEntity(this.targetEntity, xCoord, zCoord);
         this.serverPitchToTarget = computeServerPitchToEntity(this.targetEntity, xCoord, yCoord, zCoord);
         this.targetMoveDelta = new Vec3(this.targetEntity.prevPosX - this.targetEntity.posX, this.targetEntity.prevPosY - this.targetEntity.posY, this.targetEntity.prevPosZ - this.targetEntity.posZ);
      }

      this.prevPlayerPos = new Vec3(this.lastPlayerPos);
      this.lastPlayerPos = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
   }

   public float getServerPitchToTarget() {
      return this.serverPitchToTarget;
   }

   private static float computePitchToPos(Vec3 vec, double d, double d2, double d3) {
      double d4 = vec.yCoord + 1.6200000047683716 - (d2 + 1.6200000047683716);
      double d5 = vec.xCoord - d;
      double d6 = vec.zCoord - d3;
      return (float)(-Math.atan2(d4, Math.sqrt(d5 * d5 + d6 * d6)) * 180.0 / 3.141592653589793);
   }

   public float getPrevYawToTarget() {
      return this.prevYawToTarget;
   }

   public boolean hasSwitchedTargetWithin(long l) {
      long l3;
      int n = (l3 = System.currentTimeMillis() - this.lastTargetSwitchTime - l) == 0L ? 0 : (l3 < 0L ? -1 : 1);
      if (null == null) {
         n = n <= 0 ? 1 : 0;
      }

      return (n != 0);
   }

   public Vec3 getTargetMoveDelta() {
      return this.targetMoveDelta;
   }

   public float getServerYawToTarget() {
      return this.serverYawToTarget;
   }

   public float getPrevPitchToTarget() {
      return this.prevPitchToTarget;
   }

   public boolean isActionRecent() {
      long l2;
      int n = (l2 = System.currentTimeMillis() - this.lastActionTime - 2000L) == 0L ? 0 : (l2 < 0L ? -1 : 1);
      if (null == null) {
         n = n < 0 ? 1 : 0;
      }

      return (n != 0);
   }

   public void onAttackEntity(int n) {
      this.targetEntityId = n;
      if (this.targetEntity != null && this.targetEntity.getEntityId() != n) {
         this.lastTargetSwitchTime = System.currentTimeMillis();
      }

      this.targetEntity = (EntityLivingBase)mc.theWorld.getEntityByID(n);
      this.lastAttackTime = System.currentTimeMillis();
   }

   public double getLastAttackRange() {
      return this.lastAttackRange;
   }
}
