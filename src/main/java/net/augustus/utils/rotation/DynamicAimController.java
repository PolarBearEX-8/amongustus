package net.augustus.utils.rotation;

import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.SimplexNoise;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class DynamicAimController implements MC {
   public static final int DEFAULT_LOOKAHEAD_TICKS = 5;
   public int maxReactionTime;
   private double[] overlapPitchRange;
   public double snapbackMinAngleError;
   private float yawNoisePhase;
   private double clampedOverlapPitch;
   private EntityLivingBase lastTarget;
   public double additionalInaccuracy;
   public int superCorrectionDelay;
   private double targetYawLerpFactor;
   public boolean additionalAccuracy;
   public double trackingSpeedPercent;
   public float yawNoiseStep;
   private boolean superCorrectionActive;
   public double[] previousYawBounds;
   public double[] currentYawBounds;
   private int updateCount;
   public double aimSpeed;
   public int attentionWindow;
   private double estimatedYawVelocity;
   private int reactionTick;
   public float speedWaveFrequency;
   public boolean overshootSnapbackEnabled;
   public double postDecelerationFactor;
   private static final int SUBSTEPS_PER_TICK = 3;
   public double snapbackMaxProjectedError;
   public double superCorrectionProbability;
   private float speedWavePhase;
   private int baseReactionTick;
   public int minReactionTime;
   public boolean applyRandomJitter;
   private double yawTrackingResidual;
   public int lastPlayerTick = 0;
   private DynamicAimState currentState;
   public static final int MOTION_HISTORY_CAPACITY = 13;
   public double preDecelerationFactor;

   public Rotation computeRotation(EntityLivingBase entity, EntityPlayerSP player, float f, float f2, double d, double d2, Vec3 vec, Vec3 vec2) {
      f = MathHelper.wrapAngleTo180_float(f);
      if (this.superCorrectionActive && this.lastTarget != entity) {
         this.reactionTick = this.baseReactionTick;
         this.superCorrectionActive = false;
      }

      boolean flag = false;
      if (this.lastPlayerTick != player.ticksExisted - 1) {
         this.reactionTick = player.ticksExisted - this.minReactionTime;
         this.baseReactionTick = this.reactionTick;
         this.superCorrectionActive = false;
         this.yawTrackingResidual = 0.0;
         flag = true;
      }

      if (player.ticksExisted - (this.additionalAccuracy ? this.baseReactionTick : this.reactionTick) > this.maxReactionTime || player.ticksExisted < this.reactionTick) {
         this.reactionTick = player.ticksExisted - this.minReactionTime;
         this.baseReactionTick = this.reactionTick;
         this.superCorrectionActive = false;
      }

      if (Math.random() < this.superCorrectionProbability) {
         Vec3 vec3 = this.predictEntityPositionNow(entity, (byte)0, this.baseReactionTick);
         int n = player.ticksExisted - this.baseReactionTick;
         Vec3 vec4 = vec3.subtract(this.predictEntityPositionNow((EntityLivingBase)player, n, (byte)0));
         Vec3 vec5 = entity.getInterpolatedPos(1.0F).subtract(player.getInterpolatedPos(1.0F));
         double d3 = Math.abs(
            EntityBoundsUtil.getShortestSignedAngleDelta(
               MathHelper.wrapAngleTo180_double(Math.atan2(vec4.zCoord, vec4.xCoord) / 3.141592653589793 * 180.0 - 90.0),
               MathHelper.wrapAngleTo180_double(Math.atan2(vec5.zCoord, vec5.xCoord) / 3.141592653589793 * 180.0 - 90.0)
            )
         );
         double distance = vec4.distanceTo(vec5);
         if (d3 < 10.0 && distance < 2.0 || d3 < 1.0) {
            this.superCorrectionActive = true;
            this.reactionTick = Math.max(player.ticksExisted - this.superCorrectionDelay, this.reactionTick);
            if (player.ticksExisted - this.baseReactionTick > this.maxReactionTime || player.ticksExisted < this.baseReactionTick) {
               this.baseReactionTick = this.reactionTick - this.minReactionTime;
            }
         }
      }

      if (this.currentState == null) {
         this.currentState = new DynamicAimState(player.getPositionEyes(1.0F), entity.getInterpolatedPos(1.0F), Vec2d.ZERO, Vec2d.ZERO, (double)f, (double)f2);
         this.yawTrackingResidual = 0.0;
      } else {
         double d5 = (double)f;
         double d6 = (double)f2;
         this.currentState.setAngles(d5, d6);
      }

      if (this.lastPlayerTick != player.ticksExisted - 1) {
         DynamicAimState currentState2 = this.currentState;
         Vec2d vec2d = new Vec2d(
            EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)mc.thePlayer.prevReportedYaw), MathHelper.wrapAngleTo180_double((double)mc.thePlayer.lastReportedYaw))
               / 3.0
               / 0.15,
            (double)((mc.thePlayer.lastReportedPitch - mc.thePlayer.prevReportedPitch) / 3.0F) / 0.15
         );
         currentState2.setAngularVelocity(vec2d);
      }

      this.updatePredictedYawBounds(player, entity, flag, false);
      this.lastPlayerTick = player.ticksExisted;
      this.computeOverlapPitchRange(player, entity, (double)f2);
      Vec3 positionEyes = player.getPositionEyes(1.0F);
      Vec3 interpolatedPos = entity.getInterpolatedPos(1.0F);
      this.yawNoisePhase = this.yawNoisePhase + this.yawNoiseStep;
      double[] currentYawBounds2 = this.currentYawBounds;
      double[] previousYawBounds2 = this.previousYawBounds;
      DynamicAimState dynamicAimState = this.stepAimState(this.currentState, player, entity, positionEyes, interpolatedPos, previousYawBounds2, currentYawBounds2);
      int ticksExisted = player.ticksExisted;
      double[] currentYawBounds3 = this.currentYawBounds;
      this.applyMicroDriftAdjustment(this.currentState, dynamicAimState, entity, vec, interpolatedPos, positionEyes, currentYawBounds3, ticksExisted);
      this.currentState = dynamicAimState;
      this.lastTarget = entity;
      this.updateCount++;
      return new Rotation(
         MathHelper.wrapAngleTo180_float((float)(this.currentState.getYaw() + (this.applyRandomJitter ? ThreadLocalRandom.current().nextDouble(-2.0, 2.0) : 0.0))),
         (float)(this.currentState.getPitch() + (this.applyRandomJitter ? ThreadLocalRandom.current().nextDouble(-2.0, 2.0) : 0.0))
      );
   }

   private void computeOverlapPitchRange(EntityPlayerSP player, EntityLivingBase entity, double d) {
      ArrayList list = PlayerUtil.predictPlayerTrajectory(5, player, player.posX, player.posY, player.posZ, new EventMoveInput(
         player.movementInput.moveStrafe, player.movementInput.moveForward, player.movementInput.jump, player.movementInput.sneak, MoveInputStage.PRE
      ));
      ArrayList list2 = EntityMovementPredictor.predictTrajectoryFromServerPos(entity, list.size());
      list.add(player.getInterpolatedPos(1.0F));
      list2.add(entity.getInterpolatedPos(1.0F));
      int n = Math.min(list.size(), list2.size());
      double[][] doublesArr = new double[n][];
      ArrayList list3 = new ArrayList();

      for (int i = 0; i < n; i++) {
         AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, (Vec3)list2.get(i), false);
         double[] pitchRange = HitboxAngleUtil.getPitchRange(((Vec3)list.get(i)).addVector(0.0, (double)player.getEyeHeight(), 0.0), entityBoundingBoxAt);
         doublesArr[i] = pitchRange;
         list3.add(pitchRange[0]);
         list3.add(pitchRange[1]);
      }

      double d5 = d;
      double d6 = d;
      double d7 = 0.0;
      int n2 = -1;
      int n3 = 0;

      for (int j = 0; j < list3.size(); j++) {
         double d8 = (Double)list3.get(j);
         int n4 = 0;

         for (double[] doubles : doublesArr) {
            if (d8 >= doubles[0] && d8 <= doubles[1]) {
               n4++;
            }
         }

         if (n4 > n2 || n4 == n2 && Math.abs(d - d8) < d7) {
            d5 = d8;
            d7 = Math.abs(d - d8);
            n2 = n4;
            n3 = j;
         }
      }

      d7 = 0.0;
      n2 = -1;

      for (int k = 0; k < list3.size(); k++) {
         if (k != n3) {
            double d9 = (Double)list3.get(k);
            if (d9 != d5) {
               int n5 = 0;

               for (double[] doubles2 : doublesArr) {
                  if (d9 >= doubles2[0] && d9 <= doubles2[1]) {
                     n5++;
                  }
               }

               if (n5 > n2 || n5 == n2 && Math.abs(d5 - d9) < d7) {
                  d6 = d9;
                  d7 = Math.abs(d5 - d9);
                  n2 = n5;
                  n3 = k;
               }
            }
         }
      }

      this.overlapPitchRange = new double[]{Math.min(d5, d6), Math.max(d5, d6)};
      this.clampedOverlapPitch = MathHelper.clamp_double(d, this.overlapPitchRange[0], this.overlapPitchRange[1]);
   }

   private DynamicAimState selectCloserYawState(DynamicAimState dynamicAimState, DynamicAimState dynamicAimState2, DynamicAimState dynamicAimState3) {
      double d = Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta(dynamicAimState.getYaw(), dynamicAimState2.getYaw()));
      return Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta(dynamicAimState.getYaw(), dynamicAimState3.getYaw())) > d ? dynamicAimState2 : dynamicAimState3;
   }

   private DynamicAimState stepAimState(
      DynamicAimState dynamicAimState, EntityPlayerSP player, EntityLivingBase entity, Vec3 vec, Vec3 vec2, double[] doubles, double[] doubles2
   ) {
      Vec2d angularVelocity = dynamicAimState.getAngularVelocity();
      double yaw = dynamicAimState.getYaw();
      double pitch = dynamicAimState.getPitch();
      double d = 1.0;
      double d3 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase, 1.1F)), 0.5) : 0.5));
      double d5 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase - this.yawNoiseStep, 1.1F)), 0.5) : 0.5));
      double d6 = EntityBoundsUtil.getShortestSignedAngleDelta(d5, d3) + this.yawTrackingResidual;
      double d7 = Math.abs(Math.sin((double)this.speedWavePhase / 3.141592653589793 * 2.0 / 0.1));
      double d8 = MathStatisticsUtil.lerp(this.trackingSpeedPercent, 100.0 + (100.0 - this.trackingSpeedPercent), d7) / 100.0;
      d6 *= d8;
      boolean isTrackingLocked = dynamicAimState.isTrackingLocked();
      double lastYawError = dynamicAimState.getLastYawError();
      double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(doubles2[0], doubles2[1], yaw);
      double d9 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, this.targetYawLerpFactor));
      double clampedOverlapPitch2 = this.clampedOverlapPitch;
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(yaw, d9);
      if (normalizedYawPosition < 0.0 || normalizedYawPosition > 1.0) {
         if (isTrackingLocked) {
            this.targetYawLerpFactor = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
         }

         isTrackingLocked = false;
         this.yawTrackingResidual = 0.0;
      }

      if (Math.signum(shortestSignedAngleDelta) != Math.signum(lastYawError)) {
         isTrackingLocked = true;
      }

      this.speedWavePhase = this.speedWavePhase + this.speedWaveFrequency;

      for (int i = 0; i < 3; i++) {
         d9 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, this.targetYawLerpFactor));
         clampedOverlapPitch2 = this.clampedOverlapPitch;
         shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(yaw, d9);
         lastYawError = shortestSignedAngleDelta;
         if (normalizedYawPosition < 0.0 || normalizedYawPosition > 1.0) {
            if (isTrackingLocked) {
               this.targetYawLerpFactor = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
            }

            isTrackingLocked = false;
            this.yawTrackingResidual = 0.0;
         }

         if (isTrackingLocked) {
            shortestSignedAngleDelta = d6 / 3.0;
         }

         double d11 = clampedOverlapPitch2 - pitch;
         if (this.overshootSnapbackEnabled) {
            if (this.checkAndResetOnDivergence(player, entity, vec, vec2, yaw, shortestSignedAngleDelta, angularVelocity.x)) {
               doubles2 = this.currentYawBounds;
               doubles = this.previousYawBounds;
               d3 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase, 1.1F)), 0.5) : 0.5));
               d5 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase - this.yawNoiseStep, 1.1F)), 0.5) : 0.5));
               d6 = EntityBoundsUtil.getShortestSignedAngleDelta(d5, d3) + this.yawTrackingResidual;
               d9 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, this.targetYawLerpFactor));
               shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(yaw, d9);
               angularVelocity.x = angularVelocity.x / (Math.random() + 4.0);
               isTrackingLocked = false;
            }
         }

         Vec2d vec2d = new Vec2d(shortestSignedAngleDelta / 0.15, d11 / 0.15);
         double d15 = Math.signum(vec2d.x) != Math.signum(angularVelocity.x) ? this.aimSpeed * this.postDecelerationFactor : this.aimSpeed;
         double d16 = Math.signum(vec2d.y) != Math.signum(angularVelocity.y) ? this.aimSpeed * this.postDecelerationFactor : this.aimSpeed;
         d15 *= ThreadLocalRandom.current().nextDouble(0.7, 1.0);
         d16 *= ThreadLocalRandom.current().nextDouble(0.7, 1.0);
         double d17 = this.aimSpeed * this.preDecelerationFactor;
         double d18 = Math.abs(this.estimatedYawVelocity / 0.15 / 3.0);
         if (Math.signum(this.estimatedYawVelocity) != Math.signum(vec2d.x)) {
            d18 = 0.01;
         }

         double length = vec2d.length();
         double d19 = 1.0;
         double d20 = 1.0;
         boolean flag = false;
         if (Math.random() < 0.9 && angularVelocity.length() < 13.333333333333334) {
            if (angularVelocity.angleDegrees(vec2d) < 70.0) {
               this.aimSpeed = Math.min(this.aimSpeed, length) / 5.0;
               flag = true;
            }
         }

         double length2 = new Vec2d(shortestSignedAngleDelta / 0.15 / d20, d11 / 0.15 / d19).length();
         Vec2d vec2d3 = new Vec2d(
            (vec2d.x - angularVelocity.x) * Math.min(length2, this.aimSpeed) / length2, (vec2d.y - angularVelocity.y) * Math.min(length, this.aimSpeed) / length / d19
         );
         if (Double.isNaN(vec2d3.x) || Double.isNaN(vec2d3.y)) {
            vec2d3 = new Vec2d(Math.random(), Math.random()).scale(0.01);
         }

         if (Math.signum(angularVelocity.x) == Math.signum(vec2d3.x)) {
            d19 = Math.abs(angularVelocity.x);
            d20 = Math.abs(vec2d3.x);
            if ((-0.5 * (d19 * d19 / (d20 * d20)) + d19 * d19 / d20) > Math.abs(vec2d.x)) {
               vec2d3.x *= -1.0;
            }
         }

         if (Math.signum(angularVelocity.y) == Math.signum(vec2d3.y)) {
            d19 = Math.abs(angularVelocity.y);
            d20 = Math.abs(vec2d3.y);
            if ((-0.5 * (d19 * d19 / (d20 * d20)) + d19 * d19 / d20) > Math.abs(vec2d.y)) {
               vec2d3.y *= -1.0;
            }
         }

         Vec2d vec2d6 = new Vec2d(angularVelocity.x, angularVelocity.y).add(vec2d3);
         angularVelocity = angularVelocity.scale(1.0 - d).add(vec2d6.scale(d));
         yaw = MathHelper.wrapAngleTo180_double(yaw + angularVelocity.x * 0.15 * d);
         pitch = MathHelper.clamp_double(pitch + angularVelocity.y * 0.15 * d, -90.0, 90.0);
      }

      DynamicAimState dynamicAimState2 = new DynamicAimState(vec, vec2, this.currentState.getAngularVelocity(), angularVelocity, yaw, pitch);
      dynamicAimState2.setInRecovery(false);
      dynamicAimState2.setTrackingLocked(isTrackingLocked);
      dynamicAimState2.setLastYawError(lastYawError);
      this.yawTrackingResidual = 0.0;
      if (isTrackingLocked) {
         this.yawTrackingResidual = d6 - EntityBoundsUtil.getShortestSignedAngleDelta(this.currentState.getYaw(), yaw);
      }

      return dynamicAimState2;
   }

   private void updatePredictedYawBounds(EntityPlayerSP player, EntityLivingBase entity, boolean flag, boolean flag2) {
      if (!flag2 && player.tickSnapshotHistory.size() >= this.maxReactionTime + this.attentionWindow + 1 && entity.positionHistory.size() >= this.maxReactionTime + this.attentionWindow + 1) {
         this.previousYawBounds = this.currentYawBounds;
         int n = player.ticksExisted - this.reactionTick;
         Vec3 vec = this.predictEntityPositionNow((EntityLivingBase)player, n, (byte)0);
         Vec3 vec2 = this.predictEntityPositionNow(entity, (byte)0, this.reactionTick);
         double[] doubles = this.regressYawEdgeTrend(player, entity, (byte)0);
         double[] doubles2 = this.regressYawEdgeTrend(player, entity, (byte)1);
         AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec2, true);
         double d = 0.0 / 0.0;
         double[] expandedYawRange = HitboxAngleUtil.getExpandedYawRange(vec, entityBoundingBoxAt, this.additionalInaccuracy, d);
         double d2 = doubles[3] + doubles[4];
         double d3 = -doubles[3] + doubles[4];
         double d4 = doubles2[3] + doubles2[4];
         double d5 = -doubles2[3] + doubles2[4];
         double d6 = this.currentYawBounds != null ? this.currentYawBounds[0] : d2;
         double d7 = this.currentYawBounds != null ? this.currentYawBounds[1] : d4;
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();
         list.add(new double[]{d2, doubles[0], doubles[1], doubles[2]});
         list.add(new double[]{d3, -doubles[0], -doubles[1], doubles[2]});
         list.add(new double[]{d6, 0.0, d2, 0.0});
         list2.add(new double[]{d4, doubles2[0], doubles2[1], doubles2[2]});
         list2.add(new double[]{d5, -doubles2[0], -doubles2[1], doubles2[2]});
         list2.add(new double[]{d7, 0.0, d4, 0.0});
         double[] doubles3 = null;
         double[] doubles4 = null;
         double d8 = 1.7976931348623157E308;
         double d9 = this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase, 1.1F)), 0.5) : 0.5;
         double d10 = this.yawNoiseStep > 0.0F ? MathStatisticsUtil.lerp(0.5, (double)Math.abs(SimplexNoise.noise(this.yawNoisePhase - this.yawNoiseStep, 1.1F)), 0.5) : 0.5;
         double d11 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(expandedYawRange, d9));
         double d12 = this.previousYawBounds == null ? this.currentState.getYaw() : MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(this.previousYawBounds, d10));
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(d12, d11);

         for (double[] doubles5 : (Iterable<double[]>)(list)) {
            for (double[] doubles6 : (Iterable<double[]>)(list2)) {
               d11 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(new double[]{doubles5[0], doubles6[0]}, d9));
               d12 = this.previousYawBounds == null ? this.currentState.getYaw() : MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(this.previousYawBounds, d10));
               double d15 = Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta(d12, d11) - shortestSignedAngleDelta);
               if (d8 > d15 || doubles4 == null) {
                  d8 = d15;
                  doubles3 = doubles5;
                  doubles4 = doubles6;
               }
            }
         }

         this.currentYawBounds = new double[]{doubles3[0], doubles4[0]};
         this.estimatedYawVelocity = (doubles3[1] + doubles4[1]) / 2.0;
         if (flag || this.previousYawBounds == null) {
            this.previousYawBounds = this.currentYawBounds;
         }
      } else {
         Vec3 positionEyes = player.getPositionEyes(1.0F);
         Vec3 interpolatedPos = entity.getInterpolatedPos(1.0F);
         this.currentYawBounds = HitboxAngleUtil.getYawRange(positionEyes, EntityBoundsUtil.getEntityBoundingBoxAt(entity, interpolatedPos, true));
         positionEyes = player.getPositionEyes(0.0F);
         interpolatedPos = entity.getInterpolatedPos(0.0F);
         AxisAlignedBB entityBoundingBoxAt3 = EntityBoundsUtil.getEntityBoundingBoxAt(entity, interpolatedPos, true);
         double d16 = 0.0 / 0.0;
         this.previousYawBounds = HitboxAngleUtil.getExpandedYawRange(positionEyes, entityBoundingBoxAt3, this.additionalInaccuracy, d16);
      }
   }

   private Vec3 getDelayedEntityPosition(EntityLivingBase entity, float f, int n, boolean flag) {
      Vec3 vec = this.predictEntityPositionNow(entity, n, this.reactionTick);
      Vec3 vec2 = new Vec3(entity.posX, entity.posY, entity.posZ);
      if (vec == null) {
         vec = vec2;
      }

      return vec;
   }

   private double[] regressYawEdgeTrend(EntityPlayerSP player, EntityLivingBase entity, int n) {
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow(this.attentionWindow);
      double d = 0.0 / 0.0;
      double d2 = 0.0;
      double d3 = 0.0;
      int n2 = player.ticksExisted - this.reactionTick;
      int n3 = 0;

      for (int i = this.attentionWindow; i > 0; i--) {
         int n4 = i + n2;
         PlayerTickSnapshot fromEnd = (PlayerTickSnapshot)player.tickSnapshotHistory.getFromEnd(n4);
         Vec3 position = fromEnd.position;
         int n5 = i + n2;
         Vec3 fromEnd2 = (Vec3)entity.positionHistory.getFromEnd(n5);
         if (this.superCorrectionActive && this.baseReactionTick < fromEnd.ticksExisted) {
            int n6 = player.ticksExisted - this.baseReactionTick;
            int n7 = fromEnd.ticksExisted - 1;
            position = this.predictEntityPositionAtTick((EntityLivingBase)player, n6, (byte)0, n7);
            int ticksExisted = fromEnd.ticksExisted;
            fromEnd2 = this.predictEntityPositionAtTick(entity, (byte)0, this.baseReactionTick, ticksExisted);
         }

         double[] yawRange = HitboxAngleUtil.getYawRange(position, EntityBoundsUtil.getEntityBoundingBoxAt(entity, fromEnd2, true));
         if (Double.isNaN(d)) {
            d = yawRange[n];
            doubleSampleWindow.add(0.0);
            d2 = d;
            n3 = ((PlayerTickSnapshot)player.tickSnapshotHistory.getFromEnd((byte)0)).ticksExisted - fromEnd.ticksExisted;
         } else {
            double d5 = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(d2), MathHelper.wrapAngleTo180_double(yawRange[n])) + d3;
            doubleSampleWindow.add(d5);
            d2 = yawRange[n];
            d3 = d5;
         }
      }

      double[] doubles = doubleSampleWindow.linearRegression();
      double d6 = doubles[0] * (double)n3 + doubles[1];
      return new double[]{doubles[0], doubles[1], (double)n3, d6, d};
   }

   public Vec3 predictEntityPositionAtTick(EntityLivingBase entity, int n, int n2, int n3) {
      if (entity == mc.thePlayer) {
         if (n == 0) {
            return entity.getInterpolatedPos(1.0F);
         } else {
            int n4 = entity.tickSnapshotHistory.size() - n - 1;
            if (n4 < entity.tickSnapshotHistory.size() && n4 >= 0) {
               ArrayList list = new ArrayList();

               for (int i = n4; i < entity.tickSnapshotHistory.size(); i++) {
                  list.add(entity.tickSnapshotHistory.get(i));
               }

               double[] doubles = PlayerUtil.simulatePlayerSnapshotsFinal((EntityPlayerSP)entity, list, n3 - ((PlayerTickSnapshot)list.get(0)).ticksExisted, false);
               return new Vec3(doubles[0], doubles[1], doubles[2]);
            } else {
               return entity.getInterpolatedPos(1.0F);
            }
         }
      } else {
         BoundedHistoryList motionSnapshotHistory = entity.motionSnapshotHistory;
         if (motionSnapshotHistory.getEntries().isEmpty()) {
            return entity.getInterpolatedPos(1.0F);
         } else {
            n = mc.thePlayer.ticksExisted - n2;
            if (n == 0) {
               return entity.getInterpolatedPos(1.0F);
            } else {
               EntityMotionSnapshot entityMotionSnapshot;
               if (!motionSnapshotHistory.isFull()) {
                  entityMotionSnapshot = (EntityMotionSnapshot)motionSnapshotHistory.getEntries().get(motionSnapshotHistory.getEntries().size() - 1);
               } else {
                  entityMotionSnapshot = (EntityMotionSnapshot)motionSnapshotHistory.get(13 - n - 1);
               }

               return EntityMovementPredictor.predictPositionFromSnapshot(entity, entityMotionSnapshot, n3 - n2);
            }
         }
      }
   }

   public DynamicAimController() {
      this.minReactionTime = 2;
      this.maxReactionTime = 4;
      this.attentionWindow = 3;
      this.superCorrectionDelay = 0;
      this.superCorrectionProbability = 0.0;
      this.additionalInaccuracy = 0.0;
      this.aimSpeed = 8.0;
      this.preDecelerationFactor = 2.0;
      this.postDecelerationFactor = 3.0;
      this.yawNoiseStep = 0.025F;
      this.additionalAccuracy = false;
      this.overshootSnapbackEnabled = true;
      this.snapbackMinAngleError = 15.0;
      this.snapbackMaxProjectedError = 30.0;
      this.speedWaveFrequency = 0.025F;
      this.clampedOverlapPitch = 0.0;
      this.targetYawLerpFactor = 0.5;
      this.reactionTick = 0;
      this.superCorrectionActive = false;
      this.baseReactionTick = 0;
      this.lastTarget = null;
      this.yawNoisePhase = 0.0F;
      this.speedWavePhase = 0.0F;
      this.yawTrackingResidual = 0.0;
      this.estimatedYawVelocity = 0.0;
      this.applyRandomJitter = true;
      this.updateCount = 0;
   }

   private void applyMicroDriftAdjustment(DynamicAimState dynamicAimState, DynamicAimState dynamicAimState2, Entity entity, Vec3 vec, Vec3 vec2, Vec3 vec3, double[] doubles, int n) {
      double d = Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta(dynamicAimState.getYaw(), dynamicAimState2.getYaw()));
      double d2 = Math.abs(dynamicAimState.getPitch() - dynamicAimState2.getPitch());
      if ((d2 > 0.075 || d > 0.075) && (d < 0.075 || d2 < 0.075 || d / d2 < 0.1 || d2 / d < 0.1)) {
         if (vec.lengthSquared() > 0.0) {
            AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec2, true);
            double[] overlapPitchRange2 = this.overlapPitchRange;
            double[] pitchRange = HitboxAngleUtil.getPitchRange(mc.thePlayer.getPositionEyes(1.0F), entityBoundingBoxAt);
            if (Math.abs(overlapPitchRange2[0] - overlapPitchRange2[1]) < 4.0 && Math.abs(pitchRange[0] - pitchRange[1]) > Math.abs(overlapPitchRange2[0] - overlapPitchRange2[1])) {
               overlapPitchRange2 = pitchRange;
            }

            double yaw = dynamicAimState2.getYaw();
            double pitch = dynamicAimState2.getPitch();
            double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(doubles[0], doubles[1], yaw);
            double d4 = HitboxAngleUtil.inverseLerpRange(overlapPitchRange2, pitch);
            if (normalizedYawPosition >= 0.0 && normalizedYawPosition <= 1.0) {
               normalizedYawPosition = MathStatisticsUtil.lerp(
                  normalizedYawPosition,
                  (double)Math.abs(SimplexNoise.noise((float)n / 20.0F, -1.0F)),
                  (double)Math.abs(SimplexNoise.noise(3.0F, (float)n / 20.0F)) * 0.15
               );
               yaw = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, normalizedYawPosition));
            }

            if (d4 >= 0.0 && d4 <= 1.0) {
               d4 = MathStatisticsUtil.lerp(
                  d4,
                  (double)Math.abs(SimplexNoise.noise((float)n / 20.0F, -5.0F)),
                  (double)Math.abs(SimplexNoise.noise(3.0F, (float)n / 10.0F)) * 0.5 + 0.1
               );
               pitch = MathHelper.clamp_double(HitboxAngleUtil.lerpRange(overlapPitchRange2, d4), -90.0, 90.0);
            }

            double d5 = (double)(Math.abs(SimplexNoise.noise((float)mc.thePlayer.ticksExisted / 20.0F, 1.0F)) + 2.0F);
            double d7 = MathHelper.clamp_double(EntityBoundsUtil.getShortestSignedAngleDelta(dynamicAimState2.getYaw(), yaw), -d5, d5);
            dynamicAimState2.setAngles(dynamicAimState2.getYaw() + d7, pitch);
         }
      }
   }

   private Vec3 clampOffsetDistance(Vec3 vec, Vec3 vec2, double d, boolean flag) {
      Vec3 vec3 = vec.subtract(vec2);
      double length = vec3.length();
      if (length < d) {
         vec3 = new Vec3(0.0, 0.0, 0.0);
      } else {
         vec3 = vec3.scale(d / length);
         if (flag) {
            ChatUtil.sendChat("Limit from: " + length + " to: " + vec3.length());
         }
      }

      return vec.subtract(vec3);
   }

   private Vec3 simulatePlayerSnapshotPosition(EntityLivingBase entity, PlayerTickSnapshot playerTickSnapshot) {
      ArrayList list = new ArrayList();
      list.add(playerTickSnapshot);
      double[] doubles = PlayerUtil.simulatePlayerSnapshotsFinal((EntityPlayerSP)entity, list, (byte)0, false);
      return new Vec3(doubles[0], doubles[1], doubles[2]);
   }

   public Vec3 predictEntityPositionNow(EntityLivingBase entity, int n, int n2) {
      return this.predictEntityPositionAtTick(entity, n, n2, mc.thePlayer.ticksExisted);
   }

   private boolean checkAndResetOnDivergence(EntityPlayerSP player, EntityLivingBase entity, Vec3 vec, Vec3 vec2, double d, double d2, double d3) {
      AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec2, true);
      double[] yawRange = HitboxAngleUtil.getYawRange(vec, entityBoundingBoxAt);
      yawRange[0] = MathHelper.wrapAngleTo180_double(yawRange[0]);
      yawRange[1] = MathHelper.wrapAngleTo180_double(yawRange[1]);
      double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(entityBoundingBoxAt, vec, d);
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(d, yawRange[0]);
      double d5 = EntityBoundsUtil.getShortestSignedAngleDelta(d, yawRange[1]);
      double d6 = shortestSignedAngleDelta;
      double d7 = Math.signum(shortestSignedAngleDelta);
      double d8 = yawRange[0];
      if (Math.abs(d5) < Math.abs(shortestSignedAngleDelta)) {
         d6 = d5;
         d7 = Math.signum(d5);
         d8 = yawRange[1];
      }

      if (!(d6 > this.snapbackMinAngleError)
         || !(HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(d + d2), d8) > this.snapbackMaxProjectedError)
         || Math.signum(d2) != Math.signum(d3)
         || Math.signum(d2) == d7
         || !(normalizedYawInBox < 0.0) && !(normalizedYawInBox > 1.0)) {
         return false;
      } else {
         this.reactionTick = mc.thePlayer.ticksExisted;
         this.baseReactionTick = mc.thePlayer.ticksExisted;
         this.updatePredictedYawBounds(player, entity, false, false);
         return true;
      }
   }




}
