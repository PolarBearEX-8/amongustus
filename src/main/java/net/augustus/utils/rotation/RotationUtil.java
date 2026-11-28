package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.ai.AiRotationPredictor;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.modules.modes.killaura.unoise3.UNoise3RotationEngine;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.combat.CombatOptimizationUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.math.SimplexNoise;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.EntityMovementPredictor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import net.augustus.modules.latency.TimerRangeV2;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RotationUtil implements MC {
   public BezierCurve2D noiseBezierCurve;
   private double circleRandomPhase;
   public boolean bezierApplyRecordedMouseJitter;
   public double bezierPreDecelerationFactor;
   public BezierRotationEngine bezierRotationEngine;
   public double bezierMaxYawOffset;
   private Rotation previousTargetRotation;
   private double lastPitchSpeed;
   public static Vec3 debugAimPoint1;
   public static TargetPositionTracker playerPositionTracker;
   public double bezierMaxOrthogonalScale;
   public boolean bezierAdaptCurveIfNeeded;
   public int turboMinPlayerResponseTicks;
   public double bezierSuperCorrectionProbability;
   public int bezierSuperCorrectionDelay;
   public double[][] lastSubstepMouseDeltas;
   private double lastTargetMinYaw;
   public static Vec3 debugAimPoint1Prev;
   public int lastNoiseTick;
   public double noiseBezierProgressVelocity;
   public UNoise3RotationEngine uNoise3RotationEngine;
   public int turboMaxTargetResponseTicks;
   public int bezierMaxReactionTime;
   public boolean noiseTestJitterEnabled;
   public double noiseMinAimSpeed;
   private DoubleSampleWindow yawChangeWindow;
   private Vec3 patternAimOffset;
   public double bezierPostDecelerationFactor;
   public double turboMinLeadDistanceScale;
   private Rotation lastAdvancedRotation;
   private Rotation currentTargetRotation;
   public double bezierAimSpeed;
   private float lastPolarTargetPitch;
   private final TimeHelper timeHelper;
   public static Vec3 debugAimPoint2;
   private int intaveTestResetIntervalMs;
   public double bezierMaxPredictionInaccuracy;
   private int ticksSincePitchReset;
   public double noiseBezierProgress;
   public boolean bezierDuplicateEndControlPoint;
   public static Vec3 debugAimPoint3Prev;
   public float turboMaxAimSpeed;
   private double intaveHitOffsetY;
   public int turboMinTargetResponseTicks;
   public float bezierPredictionInaccuracySmoothness;
   private float lastPolarTargetYaw;
   private RotationDeltaCandidate lastSelectedCandidate;
   private Rotation previousAdvancedRotation;
   private double intaveHitOffsetX;
   public static TargetPositionTracker targetPositionTracker;
   public double bezierMinYawOffset;
   public double turboBetterAccelerationFactor;
   public float turboMinAimSpeed;
   public int turboMaxPlayerResponseTicks;
   private EntityLivingBase lastTarget;
   private double intaveHitOffsetZ;
   private double aimDeltaZ;
   public AiRotationPredictor aiRotationPredictor;
   public float turboMinAcceleration;
   public int bezierMaxLookaheadTicks;
   public double bezierMinOrthogonalScale;
   public double bezierPitchOffsetSpeed;
   public boolean bezierSkipPitchOutOfReach;
   private double[] lastCandidateDelta;
   public double bezierMinRotationOnTarget;
   public double noiseExtraAimSpeed;
   public static Vec3 debugAimPoint2Prev;
   public double bezierMinPitchOffset;
   public boolean bezierAdditionalAccuracy;
   private double aimDeltaY;
   public double noiseDynamicSpeed;
   public double bezierYawOffsetSpeed;
   public int bezierMinReactionTime;
   public float turboMaxAcceleration;
   public static Vec3 debugAimPoint4Prev;
   public double bezierMaxAimpointAngleDiff;
   private final ArrayList deltaCandidates;
   public boolean bezierSkipYawOutOfReach;
   public double bezierMinPredictionInaccuracy;
   public double bezierAdditionalInaccuracy;
   public static Vec3 debugAimPoint4;
   public double noiseIdleAimProbability;
   public int bezierMinLookaheadTicks;
   private Vec2d mouseVelocity;
   private double lastYawSpeed;
   private final int substepsPerTick;
   private double aimDeltaX;
   public double bezierMaxOrthogonalMovement;
   public double bezierBestVecPitchInterpolation;
   public int bezierAttentionWindow;
   public double bezierMaxRotationOnTarget;
   public static Vec3 debugAimPoint3;
   public double bezierMaxPitchOffset;
   private PredictiveAimSolver predictiveAimSolver;

   public static float accelerateAngle(float f, float f2, float f3, float f4, RandomizedRangeSettings randomizedRangeSettings) {
      return accelerateAngle(f, f2, f3, f4, (float)randomizedRangeSettings.nextDouble(), (float)randomizedRangeSettings.getMinValue(), (float)randomizedRangeSettings.getMaxValue(), false, 0.0F);
   }

   private RotationDeltaCandidate selectBestDeltaCandidate(
      ArrayList list,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6,
      float f7,
      float f8,
      float f9,
      float f10,
      float f11,
      float f12,
      float f13
   ) {
      ArrayList list2 = new ArrayList();

      for (RotationDeltaCandidate rotationDeltaCandidate : (Iterable<RotationDeltaCandidate>)(list)) {
         double d = Math.abs((double)rotationDeltaCandidate.getYawDelta() - this.lastCandidateDelta[0]);
         double d2 = Math.abs((double)rotationDeltaCandidate.getPitchDelta() - this.lastCandidateDelta[1]);
         if (!rotationDeltaCandidate.isDeviationBelow(f11)) {
            if (rotationDeltaCandidate.exceedsThreshold((double)f12, (double)f13)
               && Math.abs(this.lastAdvancedRotation.yaw - f + rotationDeltaCandidate.getYawDelta()) < f3
               && Math.abs(this.lastAdvancedRotation.pitch - f2 + rotationDeltaCandidate.getPitchDelta()) < f4) {
               if (rotationDeltaCandidate.computeDistanceToSegment(this.lastAdvancedRotation, this.previousAdvancedRotation, new Rotation(f, f2)) > 180.0F - f10) {
                  if (RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate, this.lastAdvancedRotation, new Rotation(f + rotationDeltaCandidate.getYawDelta(), f2 + rotationDeltaCandidate.getPitchDelta())) > (double)f5) {
                     double d6 = (double)f6;
                     if (this.isStrictlyBetween(d, d6, (double)f8)) {
                        d6 = (double)f7;
                        if (this.isStrictlyBetween(d2, d6, (double)f9)) {
                           list2.add(rotationDeltaCandidate);
                        }
                     }
                  }
               }
            }
         }
      }

      if (list2.isEmpty()) {
         for (RotationDeltaCandidate rotationDeltaCandidate2 : (Iterable<RotationDeltaCandidate>)(list)) {
            double d11 = Math.abs((double)rotationDeltaCandidate2.getYawDelta() - this.lastCandidateDelta[0]);
            double d12 = Math.abs((double)rotationDeltaCandidate2.getPitchDelta() - this.lastCandidateDelta[1]);
            if (!rotationDeltaCandidate2.isDeviationBelow(f11)) {
               if (rotationDeltaCandidate2.exceedsThreshold((double)f12, (double)f13)) {
                  if (rotationDeltaCandidate2.computeDistanceToSegment(this.lastAdvancedRotation, this.previousAdvancedRotation, new Rotation(f, f2)) > 180.0F - f10) {
                     if (RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate2, this.lastAdvancedRotation, new Rotation(f + rotationDeltaCandidate2.getYawDelta(), f2 + rotationDeltaCandidate2.getPitchDelta())) > (double)f5) {
                        double d16 = (double)f6;
                        if (this.isStrictlyBetween(d11, d16, (double)f8)) {
                           d16 = (double)f7;
                           if (this.isStrictlyBetween(d12, d16, (double)f9)) {
                              list2.add(rotationDeltaCandidate2);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (list2.isEmpty()) {
         for (RotationDeltaCandidate rotationDeltaCandidate3 : (Iterable<RotationDeltaCandidate>)(list)) {
            double d21 = Math.abs((double)rotationDeltaCandidate3.getYawDelta() - this.lastCandidateDelta[0]);
            double d22 = Math.abs((double)rotationDeltaCandidate3.getPitchDelta() - this.lastCandidateDelta[1]);
            if (!rotationDeltaCandidate3.isDeviationBelow(f11)) {
               if (rotationDeltaCandidate3.exceedsThreshold((double)f12, (double)f13)) {
                  if (RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate3, this.lastAdvancedRotation, new Rotation(f + rotationDeltaCandidate3.getYawDelta(), f2 + rotationDeltaCandidate3.getPitchDelta())) > (double)f5) {
                     double d26 = (double)f6;
                     if (this.isStrictlyBetween(d21, d26, (double)f8 * 1.4)) {
                        d26 = (double)f7;
                        if (this.isStrictlyBetween(d22, d26, (double)f9 * 1.4)) {
                           list2.add(rotationDeltaCandidate3);
                        }
                     }
                  }
               }
            }
         }

         if (!list2.isEmpty()) {
            list2.sort(Comparator.comparingDouble(obj -> this.scoreCandidateByTurnAngleFallback1(f, f2, (RotationDeltaCandidate)obj)));
            return (RotationDeltaCandidate)list2.get(0);
         }
      }

      if (list2.isEmpty()) {
         for (RotationDeltaCandidate rotationDeltaCandidate4 : (Iterable<RotationDeltaCandidate>)(list)) {
            double d31 = Math.abs((double)rotationDeltaCandidate4.getYawDelta() - this.lastCandidateDelta[0]);
            double d32 = Math.abs((double)rotationDeltaCandidate4.getPitchDelta() - this.lastCandidateDelta[1]);
            if (!rotationDeltaCandidate4.isDeviationBelow(f11)) {
               if (RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate4, this.lastAdvancedRotation, new Rotation(f + rotationDeltaCandidate4.getYawDelta(), f2 + rotationDeltaCandidate4.getPitchDelta())) > (double)f5) {
                  double d33 = (double)f6;
                  if (this.isStrictlyBetween(d31, d33, (double)f8 * 1.4)) {
                     d33 = (double)f7;
                     if (this.isStrictlyBetween(d32, d33, (double)f9 * 1.4)) {
                        list2.add(rotationDeltaCandidate4);
                     }
                  }
               }
            }
         }

         if (!list2.isEmpty()) {
            list2.sort(Comparator.comparingDouble(obj2 -> this.scoreCandidateByTurnAngleFallback(f, f2, (RotationDeltaCandidate)obj2)));
            return (RotationDeltaCandidate)list2.get(0);
         }
      }

      if (list2.isEmpty()) {
         for (RotationDeltaCandidate rotationDeltaCandidate5 : (Iterable<RotationDeltaCandidate>)(list)) {
            if (!rotationDeltaCandidate5.isDeviationBelow(f11)) {
               if (rotationDeltaCandidate5.exceedsThreshold((double)f12, (double)f13)) {
                  list2.add(rotationDeltaCandidate5);
               }
            }
         }
      }

      if (!list2.isEmpty()) {
         list2.sort(Comparator.comparingDouble(obj3 -> this.scoreCandidateByDistanceToLast(f, f2, (RotationDeltaCandidate)obj3)));
         return (RotationDeltaCandidate)list2.get(0);
      } else {
         return this.selectLeastUsedCandidate(list, f, f2);
      }
   }

   public static float updateYawWithGcd(float f, float f2, float f3) {
      float f4 = updateAngle(f, f2, f3);
      return f4 == f ? f : applyYawGcd(f4, f);
   }

   public static float interpolateAngleIfLarge(float f, float f2, float f3) {
      float f4 = wrapAngleTo180(f2 - f);
      if (!(f4 > 10.0F) && !(f4 < -10.0F)) {
         f3 = 1.0F;
      }

      return f + f3 * f4;
   }

   private Vec3 applyAimPointPattern(Entity entity, Vec3 vec, String str, float f, float f2, float f3, float f4, boolean flag, double d) {
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = new AxisAlignedBB(
         entity.getEntityBoundingBox().minX - (double)collisionBorderSize,
         entity.getEntityBoundingBox().minY - (double)collisionBorderSize,
         entity.getEntityBoundingBox().minZ - (double)collisionBorderSize,
         entity.getEntityBoundingBox().maxX + (double)collisionBorderSize,
         entity.getEntityBoundingBox().maxY + (double)collisionBorderSize,
         entity.getEntityBoundingBox().maxZ + (double)collisionBorderSize
      );
      switch (str) {
         case "Pattern1":
            double d2 = 0.4;
            double d3 = MathHelper.clamp_double(vec.xCoord - d2, box.minX, box.maxX);
            double d4 = MathHelper.clamp_double(vec.yCoord - d2, box.minY, box.maxY);
            double d5 = MathHelper.clamp_double(vec.zCoord - d2, box.minZ, box.maxZ);
            double d6 = MathHelper.clamp_double(
               vec.xCoord + d2, entity.getEntityBoundingBox().minX - (double)collisionBorderSize, entity.getEntityBoundingBox().maxX + (double)collisionBorderSize
            );
            double d7 = MathHelper.clamp_double(
               vec.yCoord + d2, entity.getEntityBoundingBox().minY - (double)collisionBorderSize, entity.getEntityBoundingBox().maxY + (double)collisionBorderSize
            );
            double d8 = MathHelper.clamp_double(
               vec.zCoord + d2, entity.getEntityBoundingBox().minZ - (double)collisionBorderSize, entity.getEntityBoundingBox().maxZ + (double)collisionBorderSize
            );
            double d9 = 0.1;
            double d10 = -0.1;
            double d11 = DefaultRandomGenerator.instance.nextDouble(d10, d9) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5 + this.patternAimOffset.xCoord;
            double d12 = 0.1;
            double d13 = -0.1;
            double d14 = DefaultRandomGenerator.instance.nextDouble(d13, d12) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5 + this.patternAimOffset.yCoord;
            double d15 = 0.1;
            double d16 = -0.1;
            double d17 = DefaultRandomGenerator.instance.nextDouble(d16, d15) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5 + this.patternAimOffset.zCoord;
            double d18 = MathHelper.clamp_double(vec.xCoord + d11, d3, d6);
            double d19 = MathHelper.clamp_double(vec.yCoord + d14, d4, d7);
            double d20 = MathHelper.clamp_double(vec.zCoord + d17, d5, d8);
            this.patternAimOffset = new Vec3(d18 - vec.xCoord, d19 - vec.yCoord, d20 - vec.zCoord);
            return new Vec3(d18, d19, d20);
         case "IntaveTest":
            long l = (long)this.intaveTestResetIntervalMs;
            if (this.timeHelper.reached(l)) {
               this.intaveTestResetIntervalMs = DefaultRandomGenerator.instance.nextInt((short)500, (short)1000);
               double d21 = box.maxX - box.minX;
               double d22 = box.maxY - box.minY;
               double d23 = box.maxZ - box.minZ;
               double d24 = 0.0;
               this.intaveHitOffsetX = DefaultRandomGenerator.instance.nextDouble(d24, d21);
               double d25 = 0.0;
               this.intaveHitOffsetY = DefaultRandomGenerator.instance.nextDouble(d25, d22);
               double d26 = 0.0;
               this.intaveHitOffsetZ = DefaultRandomGenerator.instance.nextDouble(d26, d23);
               this.timeHelper.reset();
            }

            return new Vec3(box.minX + this.intaveHitOffsetX, box.minY + this.intaveHitOffsetY, box.minZ + this.intaveHitOffsetZ);
         case "Intave14":
            float f5 = (float)(Math.atan2(this.aimDeltaZ, this.aimDeltaX) * 180.0 / Math.PI - 90.0);
            float f6 = (float)(
               -(MathHelper.atan2(this.aimDeltaY, (double)MathHelper.sqrt_double(this.aimDeltaX * this.aimDeltaX + this.aimDeltaZ * this.aimDeltaZ)) * 180.0 / Math.PI)
            );
            float f7;
            float f8;
            if (flag) {
               float f9 = 180.0F;
               float f10 = 170.0F;
               f7 = interpolateAngleIfLarge(f, f5, f3 / DefaultRandomGenerator.instance.nextFloat(f10, f9));
               float f11 = 180.0F;
               float f12 = 170.0F;
               f8 = interpolateAngleIfLarge(f2, f6, f4 / DefaultRandomGenerator.instance.nextFloat(f12, f11));
            } else {
               f7 = updateAngle(f, f5, f3);
               f8 = updateAngle(f2, f6, f4);
            }

            MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, f7, f8, d, 0.0);
            boolean flag2 = hit != null
               && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
               && hit.entityHit instanceof EntityLivingBase
               && ((EntityLivingBase)hit.entityHit).hurtTime < 5;
            Vec3 vec2 = new Vec3(vec);
            if (flag2) {
               this.patternAimOffset = new Vec3(vec);
            } else {
               double d27 = 0.4;
               double d28 = MathHelper.clamp_double(vec.xCoord - d27, box.minX, box.maxX);
               double d29 = MathHelper.clamp_double(vec.yCoord - d27, box.minY, box.maxY);
               double d30 = MathHelper.clamp_double(vec.zCoord - d27, box.minZ, box.maxZ);
               double d31 = MathHelper.clamp_double(
                  vec.xCoord + d27, entity.getEntityBoundingBox().minX - (double)collisionBorderSize, entity.getEntityBoundingBox().maxX + (double)collisionBorderSize
               );
               double d32 = MathHelper.clamp_double(
                  vec.yCoord + d27, entity.getEntityBoundingBox().minY - (double)collisionBorderSize, entity.getEntityBoundingBox().maxY + (double)collisionBorderSize
               );
               double d33 = MathHelper.clamp_double(
                  vec.zCoord + d27, entity.getEntityBoundingBox().minZ - (double)collisionBorderSize, entity.getEntityBoundingBox().maxZ + (double)collisionBorderSize
               );
               double d34 = 0.1;
               double d35 = -0.1;
               double d36 = DefaultRandomGenerator.instance.nextDouble(d35, d34) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5
                  + this.patternAimOffset.xCoord;
               double d37 = 0.1;
               double d38 = -0.1;
               double d39 = DefaultRandomGenerator.instance.nextDouble(d38, d37) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5
                  + this.patternAimOffset.yCoord;
               double d40 = 0.1;
               double d41 = -0.1;
               double d42 = DefaultRandomGenerator.instance.nextDouble(d41, d40) * (double)mc.thePlayer.getDistanceToEntity(entity) / 1.5
                  + this.patternAimOffset.zCoord;
               double d43 = MathHelper.clamp_double(vec.xCoord + d36, d28, d31);
               double d44 = MathHelper.clamp_double(vec.yCoord + d39, d29, d32);
               double d45 = MathHelper.clamp_double(vec.zCoord + d42, d30, d33);
               this.patternAimOffset = new Vec3(d43 - vec.xCoord, d44 - vec.yCoord, d45 - vec.zCoord);
               vec2 = new Vec3(d43, d44, d45);
            }

            return vec2;
         default:
            return vec;
      }
   }

   public static Rotation applyGcdFix(Rotation rotation, Rotation rotation2) {
      return applyGcdFix(rotation.yaw, rotation.pitch, rotation2.yaw, rotation2.pitch);
   }

   public static float[] getInterpolatedAngleDiffToPos(double d, double d2, double d3, float f, float f2) {
      float f3 = (float)(mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)mc.getTimer().renderPartialTicks);
      float f4 = (float)(mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)mc.getTimer().renderPartialTicks);
      float f5 = (float)(mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks);
      double d4 = d - (double)f3;
      double d5 = d2 - ((double)f4 + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - (double)f5;
      float f6 = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
      float f7 = (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
      float shortestSignedAngleDelta = (float)EntityBoundsUtil.getShortestSignedAngleDelta((double)wrapAngleTo180(f6), (double)wrapAngleTo180(f));
      float f9 = f7 - f2;
      return new float[]{shortestSignedAngleDelta, f9};
   }

   public Rotation faceCoordinatesWithSpeed(double d, double d2, double d3, float f, float f2, float f3, float f4, boolean flag) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 + 1.53 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      float f5 = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
      float f6 = (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
      float f7 = updateAngle(f, f5, f3);
      float f8 = updateAngle(f2, f6, f4);
      if (flag) {
         f7 += (float)ThreadLocalRandom.current().nextGaussian();
         f8 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return applyGcdFix(f7, f8, f, f2);
   }

   public static Rotation getRotationsToPos(double d, double d2, double d3, Rotation rotation, float f, float f2, boolean flag) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
      float f5 = updateAngle(rotation.yaw, f3, f);
      float f6 = updateAngle(rotation.pitch, f4, f2);
      if (flag) {
         double d7 = 1.5;
         double d8 = -1.5;
         f5 += DefaultRandomGenerator.instance.nextFloat(d8, d7);
         double d9 = 1.5;
         double d10 = -1.5;
         f6 += DefaultRandomGenerator.instance.nextFloat(d10, d9);
      }

      return new Rotation(f5, f6);
   }

   public static int[] computeMouseDeltas(Rotation rotation, Rotation rotation2) {
      return computeMouseDeltas(rotation.yaw, rotation.pitch, rotation2.yaw, rotation2.pitch);
   }

   public Rotation faceCoordinates(double d, double d2, double d3, float f, float f2, boolean flag) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 + 1.53 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
      float f5 = updateAngle(f, f3, 180.0F);
      float f6 = updateAngle(f2, f4, 180.0F);
      if (flag) {
         f5 += (float)ThreadLocalRandom.current().nextGaussian();
         f6 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return applyGcdFix(f5, f6, f, f2);
   }

   public static float[] getAngleDiffToEntityEyes(Entity entity, float f, float f2) {
      return getAngleDiffToPos(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, f, f2);
   }

   public static Vec3 getNearestPointInBox(EntityPlayerSP player, AxisAlignedBB box) {
      Vec3 positionEyes = player.getPositionEyes(1.0F);
      double d = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d2 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d3 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      return new Vec3(d, d2, d3);
   }

   public static float[] getAngleDiffToPos(double d, double d2, double d3, float f, float f2) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
      float f5 = MathHelper.wrapAngleTo180_float(f);
      return new float[]{(float)EntityBoundsUtil.getShortestSignedAngleDelta((double)f5, (double)f3), f4 - f2};
   }

   public static Rotation applyMouseSensitivityFix(Rotation rotation, float f, float f2, float f3, float f4, String str) {
      if (f == f3 && f2 == f4) {
         rotation.yaw = f;
         rotation.pitch = f2;
         return rotation;
      } else if (str.equals("Default")) {
         float f7 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f8 = f7 * f7 * f7 * 8.0F;
         int n = (int)((6.667 * (double)f - 6.667 * (double)f3) / (double)f8);
         int n2 = (int)((6.667 * (double)f2 - 6.667 * (double)f4) / (double)f8) * -1;
         float f9 = (float)n * f8;
         float f10 = (float)n2 * f8;
         float f11 = (float)((double)f3 + (double)f9 * 0.15);
         float f13 = MathHelper.clamp_float((float)((double)f4 - (double)f10 * 0.15), -90.0F, 90.0F);
         rotation.yaw = f11;
         rotation.pitch = f13;
         return rotation;
      } else {
         int framesPerTick = ClientManagers.moduleManager.rotationHandler.framesPerTick;
         float f14 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f15 = f14 * f14 * f14 * 8.0F;
         float f16 = f3;
         float f17 = f4;
         int n3 = (int)Math.round(
            6.6666665F
               * EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)f3), MathHelper.wrapAngleTo180_double((double)f))
               / (double)f15
         );
         int n4 = Math.round((6.6666665F * f2 - 6.6666665F * f4) / f15 * -1.0F);
         int n5 = 0;
         int n6 = 0;
         double d = 0.0;
         double d2 = 0.0;
         double d3 = (double)n3 / (double)framesPerTick;
         double d4 = (double)n4 / (double)framesPerTick;
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();

         for (int i = 0; i < framesPerTick - 1; i++) {
            d += d3;
            d2 += d4;
            int n7 = (int)Math.round(d - (double)n5);
            int n8 = (int)Math.round(d2 - (double)n6);
            list.add(n7);
            list2.add(n8);
            n5 += n7;
            n6 += n8;
         }

         Collections.shuffle(list);
         Collections.shuffle(list2);

         for (int j = 0; j < list.size(); j++) {
            float f18 = (float)((Integer)list.get(j)).intValue() * f15;
            float f19 = (float)((Integer)list2.get(j)).intValue() * f15;
            float f20 = (float)((double)f16 + (double)f18 * 0.15);
            float f22 = MathHelper.clamp_float((float)((double)f17 - (double)f19 * 0.15), -90.0F, 90.0F);
            f16 = f20;
            f17 = f22;
         }

         int n9 = (int)(
            6.6666665F
               * EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)f16), MathHelper.wrapAngleTo180_double((double)f))
               / (double)f15
         );
         int n10 = (int)((6.6666665F * f2 - 6.6666665F * f17) / f15) * -1;
         float f23 = (float)n9 * f15;
         float f24 = (float)n10 * f15;
         float f25 = (float)((double)f16 + (double)f23 * 0.15);
         float f27 = MathHelper.clamp_float((float)((double)f17 - (double)f24 * 0.15), -90.0F, 90.0F);
         rotation.yaw = f25;
         rotation.pitch = f27;
         return rotation;
      }
   }

   public static Rotation applyGcdFix(float f, float f2, Rotation rotation) {
      return applyGcdFix(f, f2, rotation.yaw, rotation.pitch);
   }

   public void setMouseVelocity(Vec2d vec2d) {
      this.mouseVelocity = vec2d.scale(1.0);
   }

   public static float wrapAngleTo180(float f) {
      f %= 360.0F;
      if (f > 180.0F) {
         f -= 360.0F;
      }

      if (f < -180.0F) {
         f += 360.0F;
      }

      return f;
   }

   public static Vec3 getNearestLookPointOnEntity(Entity entity, int n, int n2, Rotation rotation) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d = box.maxX - box.minX;
      double d2 = box.maxY - box.minY;
      double d3 = d * ((double)n2 / 100.0);
      double d4 = d2 * ((double)n / 100.0);
      box = box.expand((d3 - d) / 2.0, (d4 - d2) / 2.0, (d3 - d) / 2.0);
      double distance = positionEyes.distanceTo(box.getCenter());
      Vec3 vectorForRotation = mc.thePlayer.getVectorForRotation(rotation.pitch, rotation.yaw);
      Vec3 vec = positionEyes.addVector(vectorForRotation.xCoord * distance, vectorForRotation.yCoord * distance, vectorForRotation.zCoord * distance);
      double d6 = MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX);
      double d7 = MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY);
      double d8 = MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ);
      return new Vec3(d6, d7, d8);
   }

   public static double[] projectNormalizedAnglesBetweenBoxes(Vec3 vec, AxisAlignedBB box, AxisAlignedBB box2, double d, double d2) {
      double d3 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(HitboxAngleUtil.getYawRange(vec, box), d));
      double d4 = HitboxAngleUtil.lerpRange(HitboxAngleUtil.getPitchRange(vec, box), d2);
      double d5 = MathHelper.clamp_double(HitboxAngleUtil.getNormalizedYawInBox(box2, vec, d3), 0.0, 1.0);
      double d6 = MathHelper.clamp_double(HitboxAngleUtil.getNormalizedPitchInBox(box2, vec, d4), 0.0, 1.0);
      return new double[]{d5, d6};
   }

   public static Vec3 getNearestPointOnEntity(Entity entity) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d2 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d3 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      return new Vec3(d, d2, d3);
   }

   public static Rotation applyMouseDeltas(int[] ints, float f, float f2) {
      return applyMouseDeltas(ints[0], ints[1], f, f2);
   }

   public Rotation faceEntityEyes(Entity entity, float f, float f2, boolean flag) {
      double d = entity.posX - mc.thePlayer.posX;
      double d2 = entity.posY + (double)entity.getEyeHeight() - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d3 = entity.posZ - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d2, (double)MathHelper.sqrt_double(d * d + d3 * d3)) * 180.0 / Math.PI));
      float f5 = updateAngle(f, f3, 180.0F);
      float f6 = updateAngle(f2, f4, 180.0F);
      if (flag) {
         f5 += (float)ThreadLocalRandom.current().nextGaussian();
         f6 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return applyGcdFix(f5, f6, f, f2);
   }

   public static Vec3 getInterpolatedNearestLookPoint(Entity entity, float f, int n, int n2, Rotation rotation) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      AxisAlignedBB interpolatedEntityBox = getInterpolatedEntityBox(entity, (double)f);
      double d2 = interpolatedEntityBox.maxX - interpolatedEntityBox.minX;
      double d3 = interpolatedEntityBox.maxY - interpolatedEntityBox.minY;
      double d4 = d2 * ((double)n2 / 100.0);
      double d5 = d3 * ((double)n / 100.0);
      interpolatedEntityBox = interpolatedEntityBox.expand((d4 - d2) / 2.0, (d5 - d3) / 2.0, (d4 - d2) / 2.0);
      double distance = positionEyes.distanceTo(interpolatedEntityBox.getCenter());
      Vec3 vectorForRotation = mc.thePlayer.getVectorForRotation(rotation.pitch, rotation.yaw);
      Vec3 vec = positionEyes.addVector(vectorForRotation.xCoord * distance, vectorForRotation.yCoord * distance, vectorForRotation.zCoord * distance);
      double d7 = MathHelper.clamp_double(vec.xCoord, interpolatedEntityBox.minX, interpolatedEntityBox.maxX);
      double d8 = MathHelper.clamp_double(vec.yCoord, interpolatedEntityBox.minY, interpolatedEntityBox.maxY);
      double d9 = MathHelper.clamp_double(vec.zCoord, interpolatedEntityBox.minZ, interpolatedEntityBox.maxZ);
      return new Vec3(d7, d8, d9);
   }

   private double scoreCandidateByDistanceToLast(float f, float f2, RotationDeltaCandidate rotationDeltaCandidate) {
      return RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate, this.lastAdvancedRotation, new Rotation(f + rotationDeltaCandidate.getYawDelta(), f2 + rotationDeltaCandidate.getPitchDelta()));
   }

   public static boolean isNearCardinalYaw(float f) {
      float f2 = MathHelper.wrapAngleTo180_float(f);
      if ((double)f2 > 77.5 && (double)f2 < 102.5) {
         return true;
      } else if ((double)f2 > 167.5 || f2 < -167.0F) {
         return true;
      } else {
         return (double)f2 < -77.5 && (double)f2 > -102.5 ? true : (double)f2 > -12.5 && (double)f2 < 12.5;
      }
   }

   public static float applyYawGcd(float f, float f2) {
      return applyGcdFix(f, 0.0F, f2, 0.0F).yaw;
   }

   public Rotation computeNoise1Rotation(EntityLivingBase entity, EntityLivingBase entity2, float f, float f2, double d, double d2, Vec3 vec) {
      f = MathHelper.wrapAngleTo180_float(f);
      double d3 = (double)f;
      double d4 = (double)f2;
      if (this.lastNoiseTick != entity2.ticksExisted - 1) {
         Vec2d vec2d = new Vec2d(
            EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)mc.thePlayer.getLastReportedYaw()), (double)f) / 3.0,
            (double)((f2 - mc.thePlayer.getLastReportedPitch()) / 3.0F)
         );
         this.setMouseVelocity(vec2d);
         playerPositionTracker = new TargetPositionTracker(entity2);
         targetPositionTracker = new TargetPositionTracker(entity);
      }

      this.lastNoiseTick = mc.thePlayer.ticksExisted;
      if (playerPositionTracker == null || playerPositionTracker.targetEntity != entity2) {
         playerPositionTracker = new TargetPositionTracker(entity2);
      }

      if (targetPositionTracker == null || targetPositionTracker.targetEntity != entity) {
         targetPositionTracker = new TargetPositionTracker(entity);
      }

      TargetPositionTracker targetPositionTracker2 = playerPositionTracker;
      targetPositionTracker2.updateTrackedPosition(1.0 + Math.random());
      targetPositionTracker2 = targetPositionTracker;
      targetPositionTracker2.updateTrackedPosition(1.0 + Math.random());
      double d7 = 90.0;
      double d8 = -90.0;
      int n = ThreadLocalRandom.current().nextInt(4, 6);
      EntityPlayerSP thePlayer = mc.thePlayer;
      double posX = mc.thePlayer.posX;
      double posY = mc.thePlayer.posY;
      double posZ = mc.thePlayer.posZ;
      EventMoveInput event = new EventMoveInput(
         mc.thePlayer.movementInput.moveStrafe,
         mc.thePlayer.movementInput.moveForward,
         mc.thePlayer.movementInput.jump,
         mc.thePlayer.movementInput.sneak,
         MoveInputStage.PRE
      );
      ArrayList list = PlayerUtil.predictPlayerTrajectory(n, thePlayer, posX, posY, posZ, event);
      ArrayList list2 = EntityMovementPredictor.predictTrajectoryFromServerPos(entity, list.size());
      list.add(mc.thePlayer.getInterpolatedPos(1.0F));
      list2.add(entity.getInterpolatedPos(1.0F));
      int n2 = Math.min(list.size(), list2.size());

      for (int i = 0; i < n2; i++) {
         AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox((Vec3)list2.get(i), true);
         double[] pitchRange = HitboxAngleUtil.getPitchRange(((Vec3)list.get(i)).addVector(0.0, (double)entity2.getEyeHeight(), 0.0), box);
         d7 = Math.min(pitchRange[0], d7);
         d8 = Math.max(pitchRange[1], d8);
      }

      double d12 = 1.0;

      for (int j = 0; j < 3; j++) {
         float f3 = 1.0F;
         float f4 = ((float)entity2.ticksExisted + f3) / 20.0F;
         Vec3 vec4 = entity2.getInterpolatedPos(1.0F).addVector(0.0, (double)entity2.getEyeHeight(), 0.0);
         Vec3 vec5 = playerPositionTracker.getTrackedPosition().addVector(0.0, (double)entity2.getEyeHeight(), 0.0);
         Vec3 interpolatedPos = entity.getInterpolatedPos(f3);
         Vec3 trackedPosition = targetPositionTracker.getTrackedPosition();
         AxisAlignedBB box2 = EntityBoundsUtil.createPlayerBoundingBox(interpolatedPos, true);
         AxisAlignedBB box3 = EntityBoundsUtil.createPlayerBoundingBox(trackedPosition, true);
         double[] yawRange = HitboxAngleUtil.getYawRange(vec4, box2);
         this.yawChangeWindow.add(HitboxAngleUtil.getShortestAngularDistance(this.lastTargetMinYaw, yawRange[0]) * 3.0);
         this.lastTargetMinYaw = yawRange[0];
         double normalizedPitchInBox = HitboxAngleUtil.getNormalizedPitchInBox(box2, vec4, d2);
         double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(box2, vec4, (double)f);
         double normalizedPitchInBox2 = HitboxAngleUtil.getNormalizedPitchInBox(box2, vec4, (double)f2);
         posY = (double)f;
         posZ = (double)f2;
         double d18 = getNormalizedAnglesInBox(vec5, box3, posY, posZ)[0];
         if (this.noiseBezierProgress > 1.0) {
            this.noiseBezierCurve = null;
            this.noiseBezierProgress = 0.0;
            this.noiseBezierProgressVelocity = 0.0;
         }

         if (this.noiseBezierCurve == null
            && (
               normalizedPitchInBox2 < 0.0
                  || normalizedPitchInBox2 > 1.0
                  || normalizedYawInBox < 0.0
                  || normalizedYawInBox > 1.0
                  || d18 < 0.0
                  || d18 > 1.0
                  || Math.random() < -Math.pow(1.0 - this.noiseIdleAimProbability, 0.3333333333333333) + 1.0
            )) {
            Vec2d vec2d2 = new Vec2d(MathHelper.clamp_double(normalizedYawInBox, 0.0, 1.0), MathHelper.clamp_double(normalizedPitchInBox2, 0.0, 1.0));
            this.noiseBezierProgress = 0.3333333333333333;
            this.noiseBezierCurve = new BezierCurve2D();
            float f5 = (float)Math.random();
            this.noiseBezierCurve.controlPoints.add(vec2d2);
            this.noiseBezierCurve.controlPoints.add(new Vec2d((double)Math.abs(SimplexNoise.noise(f4, f5)), MathStatisticsUtil.lerp(normalizedPitchInBox, Math.random(), 0.5)));
            this.noiseBezierCurve
               .controlPoints
               .add(new Vec2d((double)Math.abs(SimplexNoise.noise(f4 + 0.05F, f5 + 50.0F)), MathStatisticsUtil.lerp(normalizedPitchInBox, Math.random(), 0.5)));
         }

         double d19 = MathHelper.clamp_double(normalizedYawInBox, 0.0, 1.0);
         double d20 = MathHelper.clamp_double(normalizedPitchInBox2, 0.0, 1.0);
         if (this.noiseBezierCurve != null) {
            double noiseBezierProgress2 = this.noiseBezierProgress;
            Vec2d vec2d3 = this.noiseBezierCurve.evaluate(noiseBezierProgress2);
            if (vec2d3 != null) {
               d19 = vec2d3.x;
               d20 = vec2d3.y;
            }
         }

         double d21 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(yawRange, d19));
         double d22 = HitboxAngleUtil.lerpRange(HitboxAngleUtil.getPitchRange(vec4, box2), d20);
         if (d8 >= d7) {
            d22 = MathStatisticsUtil.lerp(MathHelper.clamp_double((double)f2, d7, d8), (d7 + d8) * 0.5, 0.01 * Math.random());
         }

         if (RayTraceUtil.rayTraceDefaultReach(f3, (float)d21, (float)d22) == null) {
            return new Rotation((float)d3, (float)d4);
         }

         d22 = MathStatisticsUtil.lerp(d22, d2, ThreadLocalRandom.current().nextDouble(0.0, 0.01));
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta((double)f, d21);
         double d24 = d22 - (double)f2;
         double d25 = 4.0 + MathHelper.clamp_double(this.noiseMinAimSpeed / 100.0, 0.0, 1.0) * 4.0;
         if (Math.abs(shortestSignedAngleDelta) > 45.0) {
            d25 += (Math.abs(shortestSignedAngleDelta) - 45.0) / 135.0 * MathHelper.clamp_double(this.noiseExtraAimSpeed, 0.0, 1.0) * 5.0;
         }

         double d26 = d25
            + MathHelper.clamp_double(Math.sqrt(this.yawChangeWindow.mean()) / 20.0, 0.0, 1.0) * (MathHelper.clamp_double(this.noiseDynamicSpeed / 100.0, 0.0, 1.0) + 0.5);
         Vec2d vec2d4 = new Vec2d(
            shortestSignedAngleDelta / 0.15 + (double)SimplexNoise.noise(f4 * 2.0F, 0.0F),
            (d24 + (Math.abs(d24) > 0.6 ? 0.0 : Math.sin((Math.PI / 5) * (double)f4 * 20.0) * 0.6)) / 0.15
         );
         double length = vec2d4.length();
         double d27 = 1.0;
         double d28 = 1.0;
         boolean flag4 = false;
         if (Math.random() < 0.9 && this.mouseVelocity.length() < 13.333333333333334) {
            if (this.mouseVelocity.angleDegrees(vec2d4) < 70.0) {
               d26 = Math.min(d26, length) / 5.0;
               flag4 = true;
            }
         }

         double length2 = new Vec2d(shortestSignedAngleDelta / 0.15 / d28, d24 / 0.15 / d27).length();
         Vec2d vec2d6 = new Vec2d(
            (vec2d4.x - this.mouseVelocity.x) * Math.min(length2, d26) / length2, (vec2d4.y - this.mouseVelocity.y) * Math.min(length, d26) / length / d27
         );
         if (Double.isNaN(vec2d6.x) || Double.isNaN(vec2d6.y)) {
            vec2d6 = new Vec2d(Math.random(), Math.random()).scale(0.01);
         }

         Vec2d vec2d8 = new Vec2d(this.mouseVelocity.x, this.mouseVelocity.y);
         if (Math.signum(this.mouseVelocity.x) == Math.signum(vec2d6.x)) {
            d28 = Math.abs(this.mouseVelocity.x);
            double d30 = Math.abs(vec2d6.x);
            length2 = -0.5 * (d28 * d28 / (d30 * d30)) + d28 * d28 / d30;
            if (length2 > Math.abs(vec2d4.x)) {
               vec2d6.x *= -1.0;
            }
         }

         if (Math.signum(this.mouseVelocity.y) == Math.signum(vec2d6.y)) {
            d28 = Math.abs(this.mouseVelocity.y);
            double d31 = Math.abs(vec2d6.y);
            length2 = -0.5 * (d28 * d28 / (d31 * d31)) + d28 * d28 / d31;
            if (length2 > Math.abs(vec2d4.y)) {
               vec2d6.y *= -1.0;
               boolean flag5 = true;
            }
         }

         Vec2d vec2d9 = vec2d8.add(vec2d6);
         double d32 = 1.0 - d12;
         this.mouseVelocity = this.mouseVelocity.scale(d32).add(vec2d9.scale(d12));
         f = MathHelper.wrapAngleTo180_float(f + (float)(this.mouseVelocity.x * 0.15 * d12));
         f2 = MathHelper.clamp_float(f2 + (float)(this.mouseVelocity.y * 0.15 * d12), -90.0F, 90.0F);
         d28 = 0.2;
         this.noiseBezierProgress += d28 / 3.0;
      }

      if (this.noiseTestJitterEnabled) {
         float f6 = 2.0F;
         float f7 = -2.0F;
         f += DefaultRandomGenerator.instance.nextFloat(f7, f6);
         float f8 = 2.0F;
         float f9 = -2.0F;
         f2 += DefaultRandomGenerator.instance.nextFloat(f9, f8);
      }

      return new Rotation(f, f2);
   }

   public void updateLastRotationSpeeds(Rotation rotation, Rotation rotation2) {
      this.lastYawSpeed = (double)Math.abs(rotation.yaw - rotation2.yaw);
      this.lastPitchSpeed = (double)Math.abs(rotation.pitch - rotation2.pitch);
   }

   private static AxisAlignedBB getInterpolatedEntityBox(Entity entity, double d) {
      float collisionBorderSize = entity.getCollisionBorderSize();
      double d2 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * d;
      double d3 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * d;
      double d4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * d;
      float f = entity.width / 2.0F;
      return new AxisAlignedBB(
         d2 - (double)f, d3, d4 - (double)f, d2 + (double)f, d3 + (double)entity.height, d4 + (double)f
      ).expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
   }

   public float stepPitchAccelerated(float f, float f2, float f3, RandomizedRangeSettings randomizedRangeSettings, float f4) {
      f4 = Math.abs(f4);
      float f5 = (float)randomizedRangeSettings.nextValue();
      double d2 = randomizedRangeSettings.getMinValue() + (randomizedRangeSettings.getMaxValue() - randomizedRangeSettings.getMinValue()) / 2.0;
      float f9 = updateAngle(f2, f3, MathHelper.clamp_float(f4 + (((double)Math.abs((updateAngle(f2, f3, 180.0F) - f2) / Math.max(f4, 1.0F))) > Math.abs((double)f4 / d2) ? f5 : -f5), 0.0F, f));
      return f9 == f2 ? f2 : applyPitchGcd(f9, f2);
   }

   public Vec3 selectEntityAimPoint(Entity entity, String str, int n, int n2, int n3, int n4, Rotation rotation) {
      switch (str) {
         case "Head":
            return new Vec3(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ);
         case "BestHitVec":
            return getBestHitVec(entity, n, n2);
         case "NearestHitVec":
            return getNearestLookPointOnEntity(entity, n3, n4, rotation);
         default:
            return null;
      }
   }

   public static float updateAngle(float f, float f2, float f3) {
      float f4 = wrapAngleTo180(f2 - f);
      if (f4 >= f3) {
         f4 = f3;
      }

      if (f4 <= -f3) {
         f4 = -f3;
      }

      return f + f4;
   }

   private double scoreCandidateByTurnAngleFallback1(float f, float f2, RotationDeltaCandidate rotationDeltaCandidate) {
      Rotation lastAdvancedRotation2 = this.lastAdvancedRotation;
      Rotation rotation = this.previousAdvancedRotation;
      Rotation rotation2 = new Rotation(f, f2);
      return (double)(180.0F - rotationDeltaCandidate.computeDistanceToSegment(lastAdvancedRotation2, rotation, rotation2));
   }

   private static double rotationDistance2D(Rotation rotation, Rotation rotation2) {
      float f = rotation.yaw - rotation2.yaw;
      float f2 = rotation.pitch - rotation2.pitch;
      return Math.sqrt((double)(f2 * f2 + f * f));
   }

   private void updateTargetRotationHistory(EntityLivingBase entity, float f, float f2) {
      if (this.lastTarget != null && entity != null && entity.getEntityId() == this.lastTarget.getEntityId()) {
         this.previousTargetRotation = new Rotation(this.currentTargetRotation);
         this.currentTargetRotation = new Rotation(f, f2);
      } else {
         this.previousTargetRotation = new Rotation(f, f2);
         this.currentTargetRotation = new Rotation(f, f2);
      }
   }

   public static Vec3 getBestHitVec(Entity entity, int n, int n2) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d = box.maxX - box.minX;
      double d2 = box.maxY - box.minY;
      double d3 = d * ((double)n2 / 100.0);
      double d4 = d2 * ((double)n / 100.0);
      box = box.expand((d3 - d) / 2.0, (d4 - d2) / 2.0, (d3 - d) / 2.0);
      double d5 = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d6 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d7 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      return new Vec3(d5, d6, d7);
   }

   public static float getEntityLookAngleToPlayer(Entity entity) {
      double d = mc.thePlayer.posX - entity.posX;
      float f = (float)(MathHelper.atan2(mc.thePlayer.posZ - entity.posZ, d) * 180.0 / Math.PI - 90.0);
      return (float)HitboxAngleUtil.getShortestAngularDistance((double)MathHelper.wrapAngleTo180_float(entity.getRotationYawHead()), (double)f);
   }

   public Rotation stepVelocityTowardsAngles(double d, double d2, double d3, double d4, double d5) {
      this.lastSubstepMouseDeltas = new double[3][];
      d5 = MathHelper.clamp_double(d5, 0.0, 1.0) * 8.0;
      float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
      float f2 = f * f * f * 8.0F;
      double d6 = 1.0;

      for (int i = 0; i < 3; i++) {
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(d, d3);
         double d8 = d4 - d2;
         shortestSignedAngleDelta /= 0.15;
         d8 /= 0.15;
         Vec2d vec2d = new Vec2d(shortestSignedAngleDelta, d8);
         double length = vec2d.length();
         Vec2d vec2d2 = new Vec2d(
            (vec2d.x - this.mouseVelocity.x) * Math.min(length, d5) / length, (vec2d.y - this.mouseVelocity.y) * Math.min(length, d5) / length
         );
         if (Double.isNaN(vec2d2.x) || Double.isNaN(vec2d2.y)) {
            vec2d2 = new Vec2d(Math.random(), Math.random()).scale(0.01);
         }

         Vec2d vec2d4 = new Vec2d(this.mouseVelocity.x, this.mouseVelocity.y);
         if (Math.signum(this.mouseVelocity.x) == Math.signum(vec2d2.x)) {
            double d10 = Math.abs(this.mouseVelocity.x);
            double d11 = Math.abs(vec2d2.x);
            if ((-0.5 * (d10 * d10 / (d11 * d11)) + d10 * d10 / d11) > Math.abs(vec2d.x)) {
               vec2d2.x *= -1.0;
            }
         }

         if (Math.signum(this.mouseVelocity.y) == Math.signum(vec2d2.y)) {
            double d13 = Math.abs(this.mouseVelocity.y);
            double d14 = Math.abs(vec2d2.y);
            if ((-0.5 * (d13 * d13 / (d14 * d14)) + d13 * d13 / d14) > Math.abs(vec2d.y)) {
               vec2d2.y *= -1.0;
            }
         }

         vec2d4 = vec2d4.add(vec2d2);
         double d16 = 0.0;
         this.mouseVelocity = this.mouseVelocity.scale(d16).add(vec2d4.scale(1.0));
         double d18 = this.mouseVelocity.x * 0.15 * 1.0;
         double d19 = this.mouseVelocity.y * 0.15 * 1.0;
         this.lastSubstepMouseDeltas[i] = new double[]{this.mouseVelocity.x * 1.0 / (double)f2, this.mouseVelocity.y * 1.0 / (double)f2};
         d = MathHelper.wrapAngleTo180_double(d + d18);
         d2 = MathHelper.clamp_double(d2 + d19, -90.0, 90.0);
      }

      return new Rotation((float)d, (float)d2);
   }

   public static Vec3 getLookVector(float f, float f2) {
      float f3 = MathHelper.cos(-f2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f4 = MathHelper.sin(-f2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f5 = -MathHelper.cos(-f * (float) (Math.PI / 180.0));
      float f6 = MathHelper.sin(-f * (float) (Math.PI / 180.0));
      return new Vec3((double)(f4 * f5), (double)f6, (double)(f3 * f5));
   }

   private double scoreCandidateByTurnAngleFallback(float f, float f2, RotationDeltaCandidate rotationDeltaCandidate) {
      Rotation lastAdvancedRotation2 = this.lastAdvancedRotation;
      Rotation rotation = this.previousAdvancedRotation;
      Rotation rotation2 = new Rotation(f, f2);
      return (double)(180.0F - rotationDeltaCandidate.computeDistanceToSegment(lastAdvancedRotation2, rotation, rotation2));
   }

   public static Rotation applyMouseSensitivityFix(Rotation rotation, float f, float f2, float f3, float f4) {
      return applyMouseSensitivityFix(rotation, f, f2, f3, f4, ClientManagers.moduleManager.rotationHandler.mouseSensMode.getSelectedValue());
   }

   private float sampleRandomOffset(String str, double d, float f) {
      switch (str) {
         case "Random":
            double d2 = -d;
            return DefaultRandomGenerator.instance.nextFloat(d2, d);
         case "Gaussian":
            return (float)(ThreadLocalRandom.current().nextGaussian() * d);
         case "SecureRandom":
            double d3 = -d;
            return DefaultRandomGenerator.instance.nextSecureFloat(d3, d);
         case "Intave":
            double d4 = 2.0;
            double d5 = 1.0;
            return (float)((double)((float)((double)DefaultRandomGenerator.instance.nextSecureFloat(d5, d4) * d)) * Math.sin((double)f * Math.PI));
         default:
            return 1.0F;
      }
   }

   public static float applyPitchGcd(float f, float f2) {
      return applyGcdFix(0.0F, f, 0.0F, f2).pitch;
   }

   public BezierCurve2D createBezierCurve(Rotation rotation, Rotation rotation2, int n, double d, double d2, boolean flag) {
      return this.createBezierCurve((double)rotation.yaw, (double)rotation.pitch, (double)rotation2.yaw, (double)rotation2.pitch, n, d, d2, flag);
   }

   public static double lerpPitch(double d, double d2, double d3) {
      return MathHelper.clamp_double(d + (d2 - d) * d3, -90.0, 90.0);
   }

   public static double[] projectNormalizedAnglesBetweenOrigins(Vec3 vec, Vec3 vec2, AxisAlignedBB box, AxisAlignedBB box2, double d, double d2) {
      double d3 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(HitboxAngleUtil.getYawRange(vec, box), d));
      double d4 = HitboxAngleUtil.lerpRange(HitboxAngleUtil.getPitchRange(vec, box), d2);
      double d5 = MathHelper.clamp_double(HitboxAngleUtil.getNormalizedYawInBox(box2, vec2, d3), 0.0, 1.0);
      double d6 = MathHelper.clamp_double(HitboxAngleUtil.getNormalizedPitchInBox(box2, vec2, d4), 0.0, 1.0);
      return new double[]{d5, d6};
   }

   public static AxisAlignedBB createEntityBoxAt(EntityLivingBase entity, double d, double d2, double d3) {
      float collisionBorderSize = entity.getCollisionBorderSize();
      float f = entity.width / 2.0F + collisionBorderSize;
      return new AxisAlignedBB(
         d - (double)f, d2 - (double)collisionBorderSize, d3 - (double)f, d + (double)f, d2 + (double)entity.height + (double)collisionBorderSize, d3 + (double)f
      );
   }

   public static float accelerateAngle(float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean flag, float f8) {
      f4 = Math.abs(f4);
      double d = (double)(f6 + (f7 - f6) / 2.0F);
      float f10 = updateAngle(f, f2, 180.0F) - f;
      double d2 = (double)(flag ? Math.abs(f8) : 0.0F);
      return updateAngle(f, f2, MathHelper.clamp_float(f4 + (((double)Math.abs(f10 / Math.max(f4, 1.0F))) > Math.abs(((double)f4 - d2) / d) ? f5 : -f5), 0.0F, f3));
   }

   public void generateDeltaCandidates(float f, int n) {
      this.lastSelectedCandidate = null;
      this.deltaCandidates.clear();
      this.deltaCandidates.add(new RotationDeltaCandidate(0.0F, 0.0F, 0.0F));

      for (int i = 0; i < n; i++) {
         double d = (Math.PI * 2) * Math.random();
         double d2 = Math.sqrt(Math.random());
         float f2 = (float)(d2 * Math.cos(d)) * f;
         float f3 = (float)(d2 * Math.sin(d)) * f;
         float f4 = (float)Math.sqrt((double)(f2 * f2 + f3 * f3));
         RotationDeltaCandidate rotationDeltaCandidate = new RotationDeltaCandidate(f2, f3, f4);
         this.deltaCandidates.add(rotationDeltaCandidate);
      }
   }

   public static Rotation applyMouseDeltas(int n, int n2, Rotation rotation) {
      return applyMouseDeltas(n, n2, rotation.yaw, rotation.pitch);
   }

   public float updatePitchWithIntaveSin(float f, float f2, float f3) {
      float f4 = updateAngle(f2, f3, f);
      if (f4 != f3) {
         double d = (double)f4;
         float f5 = 2.0F;
         float f6 = 1.0F;
         f4 = (float)(d + (double)DefaultRandomGenerator.instance.nextFloat(f6, f5) * Math.sin((double)mc.thePlayer.rotationYaw * Math.PI));
      }

      return applyPitchGcd(f4, f2);
   }

   public RotationKinematicState createKinematicState(Rotation rotation) {
      return new RotationKinematicState(
         new Vec2d(
            EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)mc.thePlayer.prevReportedYaw), MathHelper.wrapAngleTo180_double((double)mc.thePlayer.lastReportedYaw)),
            (double)(mc.thePlayer.lastReportedPitch - mc.thePlayer.prevReportedPitch)
         ),
         (double)rotation.yaw,
         (double)rotation.pitch
      );
   }

   public Rotation stepKinematicAngles(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {

      for (int i = 0; i < 3; i++) {
         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(d, d3);
         double d10 = d4 - d2;
         Vec2d vec2d = new Vec2d(shortestSignedAngleDelta / 0.15, d10 / 0.15);
         double d11 = Math.signum(vec2d.x) != Math.signum(this.mouseVelocity.x) ? d5 * d7 : d5;
         double d12 = Math.signum(vec2d.y) != Math.signum(this.mouseVelocity.y) ? d5 * d7 : d5;
         d11 *= ThreadLocalRandom.current().nextDouble(0.8, 1.0);
         d12 *= ThreadLocalRandom.current().nextDouble(0.8, 1.0);
         double d13 = d5 * d6;
         double d14 = Math.abs(d8 / 0.15 / 3.0);
         if (Math.signum(d8) != Math.signum(vec2d.x)) {
            d14 = 0.0;
         }

         double d15 = Math.min(d14 + 1.4142135623730951 * Math.sqrt(d13 * Math.abs(vec2d.x)), Math.abs(vec2d.x));
         double d16 = Math.min(1.4142135623730951 * d13 * Math.sqrt(Math.abs(vec2d.y) / (2.0 * d13 - 1.0)), Math.abs(vec2d.y));
         double d17 = d15 * Math.signum(vec2d.x) - this.mouseVelocity.x;
         Vec2d vec2d2 = new Vec2d(
            MathHelper.clamp_double(
                  Math.abs(d17),
                  0.0,
                  (Math.signum(d17) != Math.signum(vec2d.x) || vec2d.x == 0.0) && Math.signum(this.mouseVelocity.x) == Math.signum(vec2d.x)
                     ? Double.MAX_VALUE
                     : d11
               )
               * Math.signum(d17),
            MathHelper.clamp_double(d16 * Math.signum(vec2d.y) - this.mouseVelocity.y, -d12, d12)
         );
         if (Double.isNaN(vec2d2.x)) {
            vec2d2.x = 0.0;
         }

         if (Double.isNaN(vec2d2.y)) {
            vec2d2.y = 0.0;
         }

         if (Math.signum(this.mouseVelocity.y) == Math.signum(vec2d.y)) {
            double d18 = Math.abs(this.mouseVelocity.y);
            double d19 = d5 * d6;
            double d20 = -0.5 * (d18 * d18 / (d19 * d19)) + d18 * d18 / d19;
            double d21 = Math.abs(vec2d.y);
            if (d20 > d21) {
               vec2d2.y = -Math.signum(vec2d.y) * Math.min(d19, (d18 * Math.sqrt(d18 * d18 - 2.0 * d21) + d18 * d18) / (2.0 * d21));
            }
         }

         this.mouseVelocity = this.mouseVelocity.add(vec2d2);
         d = MathHelper.wrapAngleTo180_double(d + this.mouseVelocity.x * 0.15);
         d2 = MathHelper.clamp_double(d2 + this.mouseVelocity.y * 0.15, -90.0, 90.0);
      }

      return new Rotation((float)d, (float)d2);
   }

   public float stepYawWithGcd(float f, float f2, float f3) {
      float f4 = updateAngle(f2, f3, f);
      return f4 == f2 ? f2 : applyYawGcd(f4, f2);
   }

   public static double[] getNormalizedAnglesInBox(Vec3 vec, AxisAlignedBB box, double d, double d2) {
      double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(box, vec, d);
      double normalizedPitchInBox = HitboxAngleUtil.getNormalizedPitchInBox(box, vec, d2);
      return new double[]{normalizedYawInBox, normalizedPitchInBox};
   }

   public static Rotation applyGcdFix(float f, float f2, float f3, float f4) {
      return applyMouseSensitivityFix(new Rotation(), f, f2, f3, f4, ClientManagers.moduleManager.rotationHandler.mouseSensMode.getSelectedValue());
   }

   public void reset() {
      this.lastPitchSpeed = 0.0;
      this.lastYawSpeed = 0.0;
      this.predictiveAimSolver.reset();
      this.lastTarget = null;
   }

   private boolean isStrictlyBetween(double d, double d2, double d3) {
      return d > d2 && d < d3;
   }

   public static double computeAngleBetweenRotations(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      if (rotation3 != null && rotation2 != null && rotation != null && !rotation2.equalsRotation(rotation3) && !rotation3.equalsRotation(rotation) && !rotation2.equalsRotation(rotation)) {
         double d = rotationDistance2D(rotation2, rotation3);
         double d2 = rotationDistance2D(rotation, rotation2);
         double d3 = rotationDistance2D(rotation, rotation3);
         if (!(d < 0.25) && !(d2 < 0.25) && !(d3 < 0.25)) {
            double d4 = 0.0;
            double d5 = 0.0;
            d4 = Math.acos((d * d + d2 * d2 - d3 * d3) / (2.0 * d * d2));
            d5 = d4 * (180.0 / Math.PI);
            return !Double.isFinite(d5) ? 0.0 : (double)((float)d5);
         } else {
            return 0.0;
         }
      } else {
         return 0.0;
      }
   }

   public static float quantizeAngleDelta(float f, float f2) {
      float f3 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
      float f4 = f3 * f3 * f3 * 8.0F;
      return (float)((int)((double)f2 / 0.15 / (double)f4)) * f4 * 0.15F + f;
   }

   public static Vec3 getNearestPointOnEntityAt(Vec3 vec, EntityLivingBase entity, double d, double d2, double d3) {
      float f = entity.width / 2.0F;
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = new AxisAlignedBB(
            d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)mc.thePlayer.height, d3 + (double)f
         )
         .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d4 = MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX);
      double d5 = MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY);
      double d6 = MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ);
      return new Vec3(d4, d5, d6);
   }

   public static Vec3 getNearestPointInBox(AxisAlignedBB box) {
      return getNearestPointInBox(mc.thePlayer, box);
   }

   public static Vec3 clampPointToBox(Vec3 vec, AxisAlignedBB box) {
      double d = MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX);
      double d2 = MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY);
      double d3 = MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ);
      return new Vec3(d, d2, d3);
   }

   public Rotation computeAdvancedRotation(
      boolean flag,
      EntityLivingBase entity,
      float f,
      float f2,
      float f3,
      float f4,
      String str,
      float f5,
      float f6,
      double d,
      boolean flag2,
      boolean flag3,
      RandomizedRangeSettings randomizedRangeSettings,
      RandomizedRangeSettings randomizedRangeSettings2,
      double d2,
      boolean flag4,
      float f7,
      float f8,
      float f9,
      float f10,
      float f11,
      float f12,
      float f13,
      float f14,
      float f15,
      float f16,
      float f17
   ) {
      if (this.lastAdvancedRotation == null) {
         double d3 = 2.0;
         f += this.sampleRandomOffset(str, d3, f2);
         double d4 = 2.0;
         f2 += this.sampleRandomOffset(str, d4, f);
         this.lastAdvancedRotation = new Rotation(f, f2);
         return new Rotation(f, f2);
      } else if (!flag && this.lastSelectedCandidate != null) {
         f += this.lastSelectedCandidate.getYawDelta();
         f2 += this.lastSelectedCandidate.getPitchDelta();
         return new Rotation(f, f2);
      } else {
         Rotation rotation = applyGcdFix(updateAngle(f3, f5, 180.0F), updateAngle(f4, f6, 180.0F), f3, f4);
         double d5 = 0.0;
         MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, rotation, d2, d5);
         boolean flag5 = hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit instanceof EntityLivingBase;
         ArrayList list = new ArrayList();
         if (flag2) {
            if (this.rollPercentageChance((int)d)) {
               if (flag5) {
                  MovingObjectPosition hit2 = RayTraceUtil.rayTraceEntity(1.0F, f3, f4, d2, 0.0);
                  if (hit2 != null
                     && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
                     && hit2.entityHit instanceof EntityLivingBase
                     && hit2.entityHit.equals(entity)) {
                     this.previousAdvancedRotation = this.lastAdvancedRotation;
                     this.lastAdvancedRotation = new Rotation(f3, f4);
                     return new Rotation(f3, f4);
                  }
               } else {
                  if (Math.abs(f - f3) < 5.0F && Math.abs(f2 - f4) < 5.0F) {
                     this.previousAdvancedRotation = this.lastAdvancedRotation;
                     this.lastAdvancedRotation = new Rotation(f3, f4);
                     return new Rotation(f3, f4);
                  }

                  MovingObjectPosition hit3 = RayTraceUtil.rayTraceEntity(1.0F, f3, f4, 200.0, 0.0);
                  if (hit3 != null
                     && hit3.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
                     && hit3.entityHit instanceof EntityLivingBase
                     && hit3.entityHit.equals(entity)) {
                     this.previousAdvancedRotation = this.lastAdvancedRotation;
                     this.lastAdvancedRotation = new Rotation(f3, f4);
                     return new Rotation(f3, f4);
                  }
               }
            }
         }

         if (flag5 && flag4 && entity.hurtTime > 3) {
            if (CombatOptimizationUtil.shouldReduceSprintKnockback(entity, applyYawGcd(updateAngle(f3, f5, 180.0F), f3))) {
               flag5 = false;
            }
         }

         if (flag5) {
            for (RotationDeltaCandidate rotationDeltaCandidate : (Iterable<RotationDeltaCandidate>)(this.deltaCandidates)) {
               rotation = applyGcdFix(f + rotationDeltaCandidate.getYawDelta(), f2 + rotationDeltaCandidate.getPitchDelta(), f3, f4);
               double d6 = 0.0;
               MovingObjectPosition hit4 = RayTraceUtil.rayTraceEntity(1.0F, rotation, d2, d6);
               if (hit4 != null && hit4.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit4.entityHit instanceof EntityLivingBase) {
                  list.add(rotationDeltaCandidate);
               }
            }
         } else {
            list.addAll(this.deltaCandidates);
         }

         this.ticksSincePitchReset++;
         RotationDeltaCandidate rotationDeltaCandidate2 = this.selectBestDeltaCandidate(list.isEmpty() ? this.deltaCandidates : list, f, f2, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17);
         RotationDeltaCandidate.incrementHitCount(rotationDeltaCandidate2);
         f += rotationDeltaCandidate2.getYawDelta();
         f2 += rotationDeltaCandidate2.getPitchDelta();
         this.lastSelectedCandidate = rotationDeltaCandidate2;
         this.previousAdvancedRotation = this.lastAdvancedRotation;
         this.lastAdvancedRotation = new Rotation(f, f2);
         this.lastCandidateDelta = new double[]{(double)rotationDeltaCandidate2.getYawDelta(), (double)rotationDeltaCandidate2.getPitchDelta()};
         if (randomizedRangeSettings2 != null && !flag5 && flag3 && this.ticksSincePitchReset > randomizedRangeSettings2.getLastIntValue()) {
            randomizedRangeSettings2.nextValue();
            this.ticksSincePitchReset = 0;
            float collisionBorderSize = entity.getCollisionBorderSize();
            AxisAlignedBB box = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            double[] pitchRange = HitboxAngleUtil.getPitchRange(mc.thePlayer.getPositionEyes(1.0F), box);
            pitchRange[0] = MathHelper.clamp_double(pitchRange[0] - randomizedRangeSettings.nextValue(), -90.0, 90.0);
            pitchRange[1] = MathHelper.clamp_double(pitchRange[1] + randomizedRangeSettings.nextValue(), -90.0, 90.0);
            f2 = (float)(Math.abs((double)f2 - pitchRange[0]) < Math.abs((double)f2 - pitchRange[1]) ? pitchRange[0] : pitchRange[1]);
         }

         return new Rotation(f, f2);
      }
   }

   public static Rotation applyMouseDeltas(int n, int n2, float f, float f2) {
      float f3 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
      float f4 = f3 * f3 * f3 * 8.0F;
      float f5 = (float)n * f4;
      float f6 = (float)n2 * f4;
      float f7 = (float)((double)f + (double)f5 * 0.15);
      float f8 = (float)((double)f2 - (double)f6 * 0.15);
      f8 = MathHelper.clamp_float(f8, -90.0F, 90.0F);
      return new Rotation(f7, f8);
   }

   public Rotation faceEntityNearestPoint(Entity entity, float f, float f2, boolean flag) {
      Vec3 nearestPointOnEntity = getNearestPointOnEntity(entity);
      double d = nearestPointOnEntity.xCoord - mc.thePlayer.posX;
      double d2 = nearestPointOnEntity.yCoord - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d3 = nearestPointOnEntity.zCoord - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d2, (double)MathHelper.sqrt_double(d * d + d3 * d3)) * 180.0 / Math.PI));
      float f5 = updateAngle(f, f3, 180.0F);
      float f6 = updateAngle(f2, f4, 180.0F);
      if (flag) {
         f5 += (float)ThreadLocalRandom.current().nextGaussian();
         f6 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return applyGcdFix(f5, f6, f, f2);
   }

   public static Rotation getInterpolatedRotationsToPos(double d, double d2, double d3, Rotation rotation, float f, float f2, boolean flag, float f3) {
      double d4 = mc.thePlayer.prevPosX + (mc.thePlayer.posX - mc.thePlayer.prevPosX) * (double)f3;
      double d5 = mc.thePlayer.prevPosY + (mc.thePlayer.posY - mc.thePlayer.prevPosY) * (double)f3;
      double d6 = mc.thePlayer.prevPosZ + (mc.thePlayer.posZ - mc.thePlayer.prevPosZ) * (double)f3;
      double d7 = d - d4;
      double d8 = d2 - (d5 + (double)mc.thePlayer.getEyeHeight());
      double d9 = d3 - d6;
      float f4 = (float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0);
      float f5 = (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI));
      float f6 = updateAngle(rotation.yaw, f4, f);
      float f7 = updateAngle(rotation.pitch, f5, f2);
      if (flag) {
         f6 += (float)ThreadLocalRandom.current().nextGaussian();
         f7 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return new Rotation(f6, f7);
   }

   private RotationDeltaCandidate selectLeastUsedCandidate(ArrayList list, float f, float f2) {
      list.sort(Comparator.comparingDouble(obj -> ((RotationDeltaCandidate)obj).computeHitScore(f, f2)));
      return (RotationDeltaCandidate)list.get(0);
   }

   public Rotation computeKillAuraRotation(
      EntityLivingBase entity,
      RandomizedRangeSettings randomizedRangeSettings,
      RandomizedRangeSettings randomizedRangeSettings2,
      RandomizedRangeSettings randomizedRangeSettings3,
      RandomizedRangeSettings randomizedRangeSettings4,
      float f,
      float f2,
      String str,
      boolean flag,
      boolean flag2,
      boolean flag3,
      float f3,
      Vec3 vec,
      boolean flag4,
      boolean flag5,
      String str2,
      String str3,
      double d,
      int n,
      int n2,
      int n3,
      int n4,
      int n5,
      String str4,
      String str5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      RandomizedRangeSettings randomizedRangeSettings5,
      RandomizedRangeSettings randomizedRangeSettings6,
      double d2,
      float f4,
      float f5,
      float f6,
      float f7,
      float f8,
      float f9,
      float f10,
      float f11,
      float f12,
      float f13,
      float f14,
      boolean flag9,
      int n6,
      double d3,
      double d4,
      double d5,
      double d6,
      double d7,
      String str6,
      boolean flag10
   ) {
      if (entity == null) {
         return new Rotation(f, f2);
      } else {
         RandomNumberGenerator instance = DefaultRandomGenerator.instance;
         float f15 = instance.nextFloat(randomizedRangeSettings3.getMinValue(), randomizedRangeSettings3.getMaxValue());
         instance = DefaultRandomGenerator.instance;
         float f16 = instance.nextFloat(randomizedRangeSettings4.getMinValue(), randomizedRangeSettings4.getMaxValue());
         Vec3 vec2;
         if (flag2 && vec != null) {
            vec2 = vec;
         } else {
            vec2 = this.selectEntityAimPoint(entity, str3, n, n2, n3, n4, new Rotation(f, f2));
         }

         Vec3 vec3 = vec2;
         Vec3 vec4 = new Vec3(
            vec3.xCoord - mc.thePlayer.posX, vec3.yCoord - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()), vec3.zCoord - mc.thePlayer.posZ
         );
         if (!str2.equals("None")) {
            vec3 = this.applyAimPointPattern(entity, vec3, str2, f, f2, f15, f16, flag, d);
         }

         this.aimDeltaX = vec3.xCoord - mc.thePlayer.posX;
         this.aimDeltaY = vec3.yCoord - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
         this.aimDeltaZ = vec3.zCoord - mc.thePlayer.posZ;
         float f17 = (float)(Math.atan2(this.aimDeltaZ, this.aimDeltaX) * 180.0 / Math.PI - 90.0);
         float f18 = (float)(
            -(MathHelper.atan2(this.aimDeltaY, (double)MathHelper.sqrt_double(this.aimDeltaX * this.aimDeltaX + this.aimDeltaZ * this.aimDeltaZ)) * 180.0 / Math.PI)
         );
         float f19 = (float)(Math.atan2(vec4.zCoord, vec4.xCoord) * 180.0 / Math.PI - 90.0);
         float f20 = (float)(
            -(MathHelper.atan2(vec4.yCoord, (double)MathHelper.sqrt_double(vec4.xCoord * vec4.xCoord + vec4.zCoord * vec4.zCoord)) * 180.0 / Math.PI)
         );
         boolean flag11 = false;
         if (str.equals("Noise")) {
            Rotation rotation3 = this.computeNoise1Rotation(entity, mc.thePlayer, f, f2, (double)f17, (double)f18, new Vec3(this.aimDeltaX, this.aimDeltaY, this.aimDeltaZ));
            f17 = rotation3.yaw;
            f18 = rotation3.pitch;
            flag11 = true;
         } else if (str.equals("Noise2")) {
            this.bezierRotationEngine.aimSpeed = this.bezierAimSpeed;
            this.bezierRotationEngine.minReactionTime = this.bezierMinReactionTime;
            this.bezierRotationEngine.maxReactionTime = this.bezierMaxReactionTime;
            this.bezierRotationEngine.attentionWindow = this.bezierAttentionWindow;
            this.bezierRotationEngine.additionalInaccuracy = this.bezierAdditionalInaccuracy;
            this.bezierRotationEngine.superCorrectionProbability = this.bezierSuperCorrectionProbability;
            this.bezierRotationEngine.superCorrectionDelay = this.bezierSuperCorrectionDelay;
            this.bezierRotationEngine.preDecelerationFactor = this.bezierPreDecelerationFactor;
            this.bezierRotationEngine.postDecelerationFactor = this.bezierPostDecelerationFactor;
            this.bezierRotationEngine.additionalAccuracy = this.bezierAdditionalAccuracy;
            this.bezierRotationEngine.minOrthogonalScale = this.bezierMinOrthogonalScale;
            this.bezierRotationEngine.maxOrthogonalScale = this.bezierMaxOrthogonalScale;
            this.bezierRotationEngine.maxAimpointAngleDiff = this.bezierMaxAimpointAngleDiff;
            this.bezierRotationEngine.skipYawOutOfReach = this.bezierSkipYawOutOfReach;
            this.bezierRotationEngine.skipPitchOutOfReach = this.bezierSkipPitchOutOfReach;
            this.bezierRotationEngine.maxOrthogonalMovement = this.bezierMaxOrthogonalMovement;
            this.bezierRotationEngine.duplicateEndControlPoint = this.bezierDuplicateEndControlPoint;
            this.bezierRotationEngine.minPredictionInaccuracy = this.bezierMinPredictionInaccuracy;
            this.bezierRotationEngine.maxPredictionInaccuracy = this.bezierMaxPredictionInaccuracy;
            this.bezierRotationEngine.predictionInaccuracySmoothness = this.bezierPredictionInaccuracySmoothness;
            this.bezierRotationEngine.bestVecPitchInterpolation = this.bezierBestVecPitchInterpolation;
            this.bezierRotationEngine.minRotationOnTarget = this.bezierMinRotationOnTarget;
            this.bezierRotationEngine.maxRotationOnTarget = this.bezierMaxRotationOnTarget;
            this.bezierRotationEngine.minLookaheadTicks = this.bezierMinLookaheadTicks;
            this.bezierRotationEngine.maxLookaheadTicks = this.bezierMaxLookaheadTicks;
            this.bezierRotationEngine.minYawOffset = this.bezierMinYawOffset;
            this.bezierRotationEngine.maxYawOffset = this.bezierMaxYawOffset;
            this.bezierRotationEngine.minPitchOffset = this.bezierMinPitchOffset;
            this.bezierRotationEngine.maxPitchOffset = this.bezierMaxPitchOffset;
            this.bezierRotationEngine.yawOffsetSpeed = this.bezierYawOffsetSpeed;
            this.bezierRotationEngine.pitchOffsetSpeed = this.bezierPitchOffsetSpeed;
            this.bezierRotationEngine.adaptCurveIfNeeded = this.bezierAdaptCurveIfNeeded;
            this.bezierRotationEngine.applyRecordedMouseJitter = this.bezierApplyRecordedMouseJitter;
            Rotation rotation4 = this.bezierRotationEngine.computeRotation(entity, mc.thePlayer, f, f2, (double)f17, (double)f18, new Vec3(this.aimDeltaX, this.aimDeltaY, this.aimDeltaZ), vec3);
            f17 = rotation4.yaw;
            f18 = rotation4.pitch;
            flag11 = true;
         } else if (str.equals("Turbo")) {
            this.predictiveAimSolver.minTargetResponseTicks = this.turboMinTargetResponseTicks;
            this.predictiveAimSolver.maxTargetResponseTicks = this.turboMaxTargetResponseTicks;
            this.predictiveAimSolver.minPlayerResponseTicks = this.turboMinPlayerResponseTicks;
            this.predictiveAimSolver.maxPlayerResponseTicks = this.turboMaxPlayerResponseTicks;
            this.predictiveAimSolver.minAcceleration = this.turboMinAcceleration;
            this.predictiveAimSolver.maxAcceleration = this.turboMaxAcceleration;
            this.predictiveAimSolver.minAimSpeed = this.turboMinAimSpeed;
            this.predictiveAimSolver.maxAimSpeed = this.turboMaxAimSpeed;
            this.predictiveAimSolver.minLeadDistanceScale = this.turboMinLeadDistanceScale;
            this.predictiveAimSolver.betterAccelerationFactor = this.turboBetterAccelerationFactor;
            Rotation rotation5 = this.predictiveAimSolver.computeRotation(entity, mc.thePlayer, f, f2, (double)f17, (double)f18, new Vec3(this.aimDeltaX, this.aimDeltaY, this.aimDeltaZ), vec3);
            f17 = rotation5.yaw;
            f18 = rotation5.pitch;
            flag11 = true;
         } else if (str.equals("Noise3")) {
            if (this.uNoise3RotationEngine != null) {
               Rotation rotation6 = this.uNoise3RotationEngine.computeRotation(entity, mc.thePlayer, f, f2, (double)f17, (double)f18, new Vec3(this.aimDeltaX, this.aimDeltaY, this.aimDeltaZ), vec3);
               f17 = rotation6.yaw;
               f18 = rotation6.pitch;
               flag11 = true;
            }
         } else if (str.equals("AI")) {
            if (this.aiRotationPredictor != null && this.aiRotationPredictor.isReady()) {
               EntityPlayerSP thePlayer5 = mc.thePlayer;
               Rotation rotation7 = this.aiRotationPredictor.predict(thePlayer5, entity);
               if (rotation7 != null) {
                  this.aiRotationPredictor.recordRotationDelta(ClientManagers.rotationController.getPreviousRotation().yaw - ClientManagers.rotationController.getLastTickRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch - ClientManagers.rotationController.getLastTickRotation().pitch);
                  float f25 = MathHelper.clamp_float(f2 + rotation7.pitch, -90.0F, 90.0F);
                  f17 = f + rotation7.yaw;
                  f18 = f25;
                  flag11 = true;
               }
            }
         } else if (str.equals("LegitTest")) {
            f17 = mc.thePlayer.rotationYaw;
            f18 = mc.thePlayer.rotationPitch;
            flag11 = true;
         }

         TimerRangeV2 timerRangeV2 = Augustus.getInstance().getModuleManager().timerRangeV2;
         if (timerRangeV2.hasPredictedTargetBox() && ClientManagers.moduleManager.killAura.timerRangeV2Fix.getValue()) {
            Vec3 nearestPointInBox = getNearestPointInBox(timerRangeV2.getPredictedTargetBox());
            Vec3 vec10 = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
            Rotation rotation8 = new Rotation(f, f2);
            float f26 = 180.0F;
            Rotation rotationsBetweenPoints = getRotationsBetweenPoints(nearestPointInBox, vec10, rotation8, 180.0F, f26);
            f17 = rotationsBetweenPoints.yaw;
            f18 = rotationsBetweenPoints.pitch;
         }

         if (flag10 && this.aimDeltaX == 0.0 && this.aimDeltaZ == 0.0) {
            f17 = f;
            if (this.aimDeltaY == 0.0) {
               f18 = f2;
            }
         }

         this.updateTargetRotationHistory(entity, updateAngle(f, f17, 180.0F), updateAngle(f2, f18, 180.0F));
         float f31;
         float f32;
         if (flag) {
            float f33 = 180.0F;
            float f34 = 170.0F;
            f31 = interpolateAngleIfLarge(f, f17, f15 / DefaultRandomGenerator.instance.nextFloat(f34, f33));
            float f35 = 180.0F;
            float f36 = 170.0F;
            f32 = interpolateAngleIfLarge(f2, f18, f16 / DefaultRandomGenerator.instance.nextFloat(f36, f35));
         } else {
            float f37 = (float)randomizedRangeSettings.nextDouble();
            float f38 = (float)randomizedRangeSettings2.nextDouble();
            double d27 = randomizedRangeSettings.getMinValue() + (randomizedRangeSettings.getMaxValue() - randomizedRangeSettings.getMinValue()) / 2.0;
            double d29 = randomizedRangeSettings2.getMinValue() + (randomizedRangeSettings2.getMaxValue() - randomizedRangeSettings2.getMinValue()) / 2.0;
            float f39 = updateAngle(f, f17, 180.0F);
            float f40 = updateAngle(f2, f18, 180.0F);
            float f41 = f39 - f;
            float f42 = f40 - f2;
            double d30 = flag9 ? (double)Math.abs(this.previousTargetRotation.yaw - this.currentTargetRotation.yaw) : 0.0;
            double d31 = Math.abs((double)f41 / Math.max(this.lastYawSpeed, 1.0));
            double d32 = Math.abs((this.lastYawSpeed - d30) / d27);
            float f43 = MathHelper.clamp_float((float)(Math.abs(this.lastYawSpeed) + (double)(d31 > d32 ? f37 : -f37)), 0.0F, f15);
            f31 = updateAngle(f, f17, flag11 ? 180.0F : f43);
            d30 = flag9 ? (double)Math.abs(this.previousTargetRotation.pitch - this.currentTargetRotation.pitch) : 0.0;
            d31 = Math.abs((double)f42 / Math.max(this.lastPitchSpeed, 1.0));
            d32 = Math.abs((this.lastPitchSpeed - d30) / d29);
            float f44 = MathHelper.clamp_float((float)(Math.abs(this.lastPitchSpeed) + (double)(d31 > d32 ? f38 : -f38)), 0.0F, f16);
            f32 = updateAngle(f2, f18, flag11 ? 180.0F : f44);
            this.lastYawSpeed = (double)Math.abs(f31 - f);
            this.lastPitchSpeed = (double)Math.abs(f32 - f2);
         }

         double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)f), MathHelper.wrapAngleTo180_double((double)f17));
         double d34 = (double)(f18 - f2);
         switch (str) {
            case "Basic":
               double d35 = (double)f3;
               f31 += this.sampleRandomOffset(str4, d35, f32);
               double d36 = (double)f3;
               f32 += this.sampleRandomOffset(str4, d36, f31);
               break;
            case "OnlyRotation":
               if (!((double)(-f15) <= shortestSignedAngleDelta) || !(shortestSignedAngleDelta <= (double)f15) || !((double)(-f16) <= d34) || !(d34 <= (double)f16)) {
                  double d37 = (double)f3;
                  f31 += this.sampleRandomOffset(str4, d37, f32);
                  double d38 = (double)f3;
                  f32 += this.sampleRandomOffset(str4, d38, f31);
               }
               break;
            case "Doubled":
               float f45 = this.sampleRandomOffset(str4, (double)f3, f32);
               float f46 = this.sampleRandomOffset(str4, (double)f3, f32);
               float f47 = this.sampleRandomOffset(str4, (double)f3, f32);
               float f48 = this.sampleRandomOffset(str4, (double)f3, f32);
               float f49 = this.sampleRandomOffset(str4, (double)f3, f31);
               float f50 = this.sampleRandomOffset(str4, (double)f3, f31);
               float f51 = this.sampleRandomOffset(str4, (double)f3, f31);
               float f52 = this.sampleRandomOffset(str4, (double)f3, f31);
               RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
               double d47 = (double)Math.max(f45, f46);
               double d48 = (double)Math.min(f45, f46);
               f31 += instance2.nextSecureFloat(d48, d47);
               RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
               double d49 = (double)Math.max(f47, f48);
               double d50 = (double)Math.min(f47, f48);
               f31 += instance3.nextSecureFloat(d50, d49);
               RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
               double d51 = (double)Math.max(f49, f50);
               double d52 = (double)Math.min(f49, f50);
               f32 += instance4.nextSecureFloat(d52, d51);
               RandomNumberGenerator instance5 = DefaultRandomGenerator.instance;
               double d53 = (double)Math.max(f51, f52);
               double d54 = (double)Math.min(f51, f52);
               f32 += instance5.nextSecureFloat(d54, d53);
               break;
            case "Hybrid":
               double d56 = (double)this.sampleRandomOffset(str4, (double)f3, f32);
               double d58 = (double)this.sampleRandomOffset(str4, (double)f3, f31);
               MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, f31, f32, d, 0.0);
               if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                  for (int i = 1; i < 10; i += 1) {
                     MovingObjectPosition hit2 = RayTraceUtil.rayTraceEntity(
                        1.0F, (float)((double)f31 + d56 / (double)i), (float)((double)f32 + d58 / (double)i), d, 0.0
                     );
                     if (hit.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY || hit2.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                        f31 += (float)(d56 / (double)i);
                        f32 += (float)(d58 / (double)i);
                        break;
                     }
                  }
               } else {
                  f31 += (float)d56;
                  f32 += (float)d58;
               }
               break;
            case "Circle":
               double d59;
               if (this.circleRandomPhase > 360.0) {
                  d59 = 0.0;
               } else {
                  double d60 = 0.7;
                  double d61 = 0.2;
                  d59 = this.circleRandomPhase + DefaultRandomGenerator.instance.nextDouble(d61, d60);
               }

               this.circleRandomPhase = d59;
               MovingObjectPosition hit3 = RayTraceUtil.rayTraceEntity(1.0F, updateAngle(f, f17, 180.0F), updateAngle(f2, f18, 180.0F), d, 0.0);
               boolean flag12 = hit3 != null && hit3.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit3.entityHit instanceof EntityLivingBase;
               double d62 = 0.0;
               int n7 = 0;
               if (flag12) {
                  for (float j = 7.0F; j >= 0.0F; j -= 0.1F) {
                     float f53 = (float)(Math.sin(this.circleRandomPhase) * (double)j);
                     float f54 = (float)(Math.cos(this.circleRandomPhase) * (double)j);
                     Rotation rotation10 = applyGcdFix(f31 + f53, f32 + f54, f, f2);
                     float yaw = rotation10.yaw;
                     float pitch = rotation10.pitch;
                     MovingObjectPosition hit4 = RayTraceUtil.rayTraceEntity(1.0F, yaw, pitch, d, 0.0);
                     if (hit4 != null && hit4.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit4.entityHit instanceof EntityLivingBase) {
                        n7 += 1;
                        if (n7 >= 2) {
                           f31 += f53;
                           f32 += f54;
                           break;
                        }
                     } else {
                        n7 = 0;
                     }
                  }
               } else {
                  d62 = 9.0;
                  double d63 = Math.sin(this.circleRandomPhase) * d62;
                  double d64 = Math.cos(this.circleRandomPhase) * d62;
                  f31 = (float)((double)f31 + d63);
                  f32 = (float)((double)f32 + d64);
               }
               break;
            case "Advanced":
               Rotation rotation11 = this.computeAdvancedRotation(
                  true,
                  entity,
                  f31,
                  f32,
                  f,
                  f2,
                  str4,
                  f17,
                  f18,
                  d2,
                  flag7,
                  flag8,
                  randomizedRangeSettings5,
                  randomizedRangeSettings6,
                  d,
                  flag6,
                  f4,
                  f5,
                  f6,
                  f7,
                  f8,
                  f9,
                  f10,
                  f11,
                  f12,
                  f13,
                  f14
               );
               if (this.aimDeltaX != 0.0 || this.aimDeltaZ != 0.0) {
                  f31 = rotation11.yaw;
               }

               if (this.aimDeltaX != 0.0 || this.aimDeltaZ != 0.0 || this.aimDeltaY != 0.0) {
                  f32 = rotation11.pitch;
               }
               break;
            case "PolarTest":
               Rotation rotation12 = this.computePolarTestRotation(entity, f, f2, f31, f32, f17, f18, d);
               f31 = rotation12.yaw;
               f32 = rotation12.pitch;
         }

         if (flag4) {
            boolean flag14 = false;
            if (flag5) {
               MovingObjectPosition hit5 = RayTraceUtil.rayTraceEntity(1.0F, updateAngle(f, f17, 180.0F), updateAngle(f2, f18, 180.0F), 6.0, 0.0);
               flag14 = hit5 != null && hit5.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit5.entityHit instanceof EntityLivingBase;
            }

            if (!flag14) {
               f32 += (float)(Math.sin(0.06981317007977318 * (double)(updateAngle(f, f17, 180.0F) - f31)) * 8.0);
            }
         }

         if (flag3 && n5 > 9) {
            double d65 = 0.5;
            double d66 = -0.5;
            f31 = f + DefaultRandomGenerator.instance.nextFloat(d66, d65);
            double d67 = 0.5;
            double d68 = -0.5;
            f32 = f2 + DefaultRandomGenerator.instance.nextFloat(d68, d67);
         }

         Rotation rotation13 = applyGcdFix(f31, f32, f, f2);
         f31 = rotation13.yaw;
         f32 = rotation13.pitch;
         if (timerRangeV2.hasPredictedTargetBox() && this.bezierRotationEngine != null && ClientManagers.moduleManager.killAura.timerRangeV2Fix.getValue()) {
            this.bezierRotationEngine.syncCurrentRotation(f31, f32, false);
            double d69 = (double)f32;
            this.bezierRotationEngine.overrideTargetPitch(d69);
         }

         Vec3 vec12 = new Vec3(this.aimDeltaX, this.aimDeltaY, this.aimDeltaZ);
         if ((!str5.equals("None")
            && (str5.equals("Always") || entity.hurtTime <= 2 || CombatOptimizationUtil.shouldReduceSprintKnockback(entity, f))
            && Math.random() <= (double)n6 / 100.0) && (vec12.length() <= d || (double)mc.thePlayer.getDistanceToEntityBox(entity) <= d)) {
            MovingObjectPosition hit6 = RayTraceUtil.rayTraceEntity(1.0F, f31, f32, d, 0.0);
            if (hit6 != null && !(hit6.entityHit instanceof EntityLivingBase)) {
               hit6 = RayTraceUtil.rayTraceEntity(1.0F, f31, f32, 6.0, 0.0);
               MovingObjectPosition hit7 = RayTraceUtil.rayTraceEntity(1.0F, f19, f20, 6.0, 0.0);
               if (hit6 != null && hit7 != null && hit6.entityHit instanceof EntityLivingBase && hit7.entityHit == hit6.entityHit) {
                  Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
                  AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox(entity.getInterpolatedPos(1.0F), true);
                  double[] yawRange = HitboxAngleUtil.getYawRange(positionEyes, box);
                  double[] pitchRange = HitboxAngleUtil.getPitchRange(positionEyes, box);
                  double length = hit6.hitVec.subtract(positionEyes).length();
                  double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(box, positionEyes, MathHelper.wrapAngleTo180_double((double)f19));
                  double normalizedPitchInBox = HitboxAngleUtil.getNormalizedPitchInBox(box, positionEyes, (double)f20);
                  double normalizedYawInBox2 = HitboxAngleUtil.getNormalizedYawInBox(box, positionEyes, MathHelper.wrapAngleTo180_double((double)f));
                  double normalizedPitchInBox2 = HitboxAngleUtil.getNormalizedPitchInBox(box, positionEyes, (double)f2);
                  Rotation rotation14 = null;
                  Rotation rotation15 = null;

                  for (int k = 0; k <= 50; k += 1) {
                     double d74 = MathStatisticsUtil.lerp(normalizedYawInBox2, normalizedYawInBox, (double)k / 50.0);
                     double d75 = MathStatisticsUtil.lerp(normalizedPitchInBox2, normalizedPitchInBox, (double)k / 50.0);
                     double d76 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(yawRange, d74));
                     double d77 = HitboxAngleUtil.lerpRange(pitchRange, d75);
                     Rotation rotation16 = applyGcdFix(updateAngle(f, (float)d76, 180.0F), (float)d77, f, f2);
                     hit6 = RayCastUtil.rayCast(1.0F, rotation16.yaw, rotation16.pitch);
                     if (hit6 != null && hit6.entityHit instanceof EntityLivingBase) {
                        AxisAlignedBB entityBoundingBox = hit6.entityHit.getEntityBoundingBox();
                        Vec3 hitVec = hit6.hitVec;
                        this.intaveHitOffsetX = hitVec.xCoord - entityBoundingBox.minX;
                        this.intaveHitOffsetY = hitVec.yCoord - entityBoundingBox.minY;
                        this.intaveHitOffsetZ = hitVec.zCoord - entityBoundingBox.minZ;
                        this.timeHelper.reset();
                        if (!(hit6.hitVec.subtract(positionEyes).length() > d3)) {
                           if (rotation14 == null) {
                              for (int m = ClientManagers.rotationController.getRotationHistory().getEntries().size() - 1; m >= 0 && rotation15 == null; m -= 1) {
                                 Rotation rotation17 = (Rotation)ClientManagers.rotationController.getRotationHistory().get(m);
                                 if (rotation14 == null && !rotation16.equalsRotation(rotation17)) {
                                    rotation14 = rotation17;
                                 } else if (rotation14 != null && !rotation14.equalsRotation(rotation17)) {
                                    rotation15 = rotation17;
                                 }
                              }

                              if (rotation14 == null) {
                                 rotation14 = ClientManagers.rotationController.getPreviousRotation();
                              }

                              if (rotation15 == null) {
                                 rotation15 = ClientManagers.rotationController.getLastTickRotation();
                              }
                           }

                           double d78 = computeAngleBetweenRotations(rotation16, rotation14, rotation15);
                           if (d78 == 0.0 || !(d78 < d4)) {
                              double d79 = EntityBoundsUtil.getShortestSignedAngleDelta(
                                 (double)MathHelper.wrapAngleTo180_float(rotation16.yaw), (double)MathHelper.wrapAngleTo180_float(f19)
                              );
                              double d80 = (double)(rotation16.pitch - f20);
                              if ((!str6.equals("Separate") || !(Math.abs(d79) < d5))
                                 && !(Math.abs(d80) < d6)
                                 && (!str6.equals("Combined") || !(Math.sqrt(d79 * d79 + d80 * d80) < d7))) {
                                 if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.devToggleI != null) {
                                 }

                                 f31 = rotation16.yaw;
                                 f32 = rotation16.pitch;
                                 if (this.bezierRotationEngine != null) {
                                    this.bezierRotationEngine.syncCurrentRotation(f31, f32, false);
                                    double d81 = (double)f32;
                                    this.bezierRotationEngine.overrideTargetPitch(d81);
                                 }
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         this.lastTarget = entity;
         return new Rotation(f31, f32);
      }
   }

   private boolean rollPercentageChance(int n) {
      return DefaultRandomGenerator.instance.nextInt((byte)0, (byte)99) < n;
   }

   public static int[] computeMouseDeltas(float f, float f2, float f3, float f4) {
      float f5 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
      float f6 = f5 * f5 * f5 * 8.0F;
      int n = (int)Math.round(
         6.6666665F * EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)f3), MathHelper.wrapAngleTo180_double((double)f)) / (double)f6
      );
      int n2 = (int)Math.round((6.667 * (double)f2 - 6.667 * (double)f4) / (double)f6) * -1;
      return new int[]{n, n2};
   }

   public static AxisAlignedBB createEntityBoxAt(EntityLivingBase entity, Vec3 vec) {
      double zCoord = vec.zCoord;
      double yCoord = vec.yCoord;
      return createEntityBoxAt(entity, vec.xCoord, yCoord, zCoord);
   }

   public static float updatePitchWithGcd(float f, float f2, float f3) {
      float f4 = updateAngle(f, f2, f3);
      return f4 == f ? f : applyPitchGcd(f4, f);
   }

   public static Rotation getRotationsBetweenPoints(Vec3 vec, Vec3 vec2, Rotation rotation, float f, float f2) {
      double d = vec.xCoord - vec2.xCoord;
      double d2 = vec.yCoord - vec2.yCoord;
      double d3 = vec.zCoord - vec2.zCoord;
      float f3 = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d2, (double)MathHelper.sqrt_double(d * d + d3 * d3)) * 180.0 / Math.PI));
      float f5 = updateAngle(rotation.yaw, f3, f);
      float f6 = updateAngle(rotation.pitch, f4, f2);
      return new Rotation(f5, f6);
   }

   public static Vec3 getInterpolatedBestHitVec(Entity entity, float f, int n, int n2) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(f);
      AxisAlignedBB interpolatedEntityBox = getInterpolatedEntityBox(entity, (double)f);
      double d2 = interpolatedEntityBox.maxX - interpolatedEntityBox.minX;
      double d3 = interpolatedEntityBox.maxY - interpolatedEntityBox.minY;
      double d4 = d2 * ((double)n2 / 100.0);
      double d5 = d3 * ((double)n / 100.0);
      interpolatedEntityBox = interpolatedEntityBox.expand((d4 - d2) / 2.0, (d5 - d3) / 2.0, (d4 - d2) / 2.0);
      double d6 = MathHelper.clamp_double(positionEyes.xCoord, interpolatedEntityBox.minX, interpolatedEntityBox.maxX);
      double d7 = MathHelper.clamp_double(positionEyes.yCoord, interpolatedEntityBox.minY, interpolatedEntityBox.maxY);
      double d8 = MathHelper.clamp_double(positionEyes.zCoord, interpolatedEntityBox.minZ, interpolatedEntityBox.maxZ);
      return new Vec3(d6, d7, d8);
   }

   public float stepYawAccelerated(float f, float f2, float f3, RandomizedRangeSettings randomizedRangeSettings, float f4) {
      f4 = Math.abs(f4);
      float f5 = (float)randomizedRangeSettings.nextValue();
      double d2 = randomizedRangeSettings.getMinValue() + (randomizedRangeSettings.getMaxValue() - randomizedRangeSettings.getMinValue()) / 2.0;
      double d3 = (double)Math.abs((updateAngle(f2, f3, 180.0F) - f2) / Math.max(f4, 1.0F));
      double d4 = Math.abs((double)f4 / d2);
      float f9 = updateAngle(f2, f3, MathHelper.clamp_float(Math.abs(f4) + (d3 > d4 ? f5 : -f5), 0.0F, f));
      return f9 == f2 ? f2 : applyYawGcd(f9, f2);
   }

   public static Vec3 getInterpolatedNearestPoint(Entity entity, float f) {
      Vec3 positionEyes = mc.thePlayer.getPositionEyes(f);
      AxisAlignedBB interpolatedEntityBox = getInterpolatedEntityBox(entity, (double)f);
      double d2 = MathHelper.clamp_double(positionEyes.xCoord, interpolatedEntityBox.minX, interpolatedEntityBox.maxX);
      double d3 = MathHelper.clamp_double(positionEyes.yCoord, interpolatedEntityBox.minY, interpolatedEntityBox.maxY);
      double d4 = MathHelper.clamp_double(positionEyes.zCoord, interpolatedEntityBox.minZ, interpolatedEntityBox.maxZ);
      return new Vec3(d2, d3, d4);
   }

   public static Vec3 getNearestPointFromEntity(Entity entity, Entity entity2) {
      Vec3 positionEyes = entity.getPositionEyes(1.0F);
      float collisionBorderSize = entity2.getCollisionBorderSize();
      AxisAlignedBB box = entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      double d = MathHelper.clamp_double(positionEyes.xCoord, box.minX, box.maxX);
      double d2 = MathHelper.clamp_double(positionEyes.yCoord, box.minY, box.maxY);
      double d3 = MathHelper.clamp_double(positionEyes.zCoord, box.minZ, box.maxZ);
      return new Vec3(d, d2, d3);
   }

   public BezierCurve2D createBezierCurve(double d, double d2, double d3, double d4, int n, double d5, double d6, boolean flag) {
      Vec2d vec2d = new Vec2d(MathHelper.wrapAngleTo180_double(d), MathHelper.clamp_double(d2, -90.0, 90.0));
      Vec2d vec2d2 = new Vec2d(EntityBoundsUtil.getShortestSignedAngleDelta(vec2d.x, MathHelper.wrapAngleTo180_double(d3)), d4 - vec2d.y);
      Vec2d vec2d3 = vec2d2.perpendicular();
      if (vec2d2.length() == 0.0) {
         vec2d3 = vec2d2;
      }

      vec2d3 = vec2d3.normalize();
      if (!Double.isFinite(vec2d3.x) || !Double.isFinite(vec2d3.y)) {
         vec2d3 = new Vec2d(0.0, 0.0);
      }

      double d7 = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
      double d9 = MathHelper.clamp_double(ThreadLocalRandom.current().nextDouble(-1.0, 1.0) * vec2d2.length() * d5, -d6, d6);
      Vec2d vec2d6 = vec2d2.scale(d7).add(vec2d3.scale(d9));
      if (vec2d3.length() == 0.0) {
         vec2d6 = new Vec2d(0.0, 0.0);
      }

      BezierCurve2D bezierCurve2D = new BezierCurve2D();
      bezierCurve2D.controlPoints.add(vec2d);
      bezierCurve2D.controlPoints.add(vec2d6.add(vec2d));
      bezierCurve2D.controlPoints.add(vec2d2.add(vec2d));
      if (flag) {
         bezierCurve2D.controlPoints.add(vec2d2.add(vec2d));
      }

      bezierCurve2D.setProgress(1.0 / (double)(n + 1));
      bezierCurve2D.durationTicks = n;
      return bezierCurve2D;
   }

   public static double lerpYaw(double d, double d2, double d3) {
      d = MathHelper.wrapAngleTo180_double(d);
      d2 = MathHelper.wrapAngleTo180_double(d2);
      return MathHelper.wrapAngleTo180_double(d + EntityBoundsUtil.getShortestSignedAngleDelta(d, d2) * d3);
   }

   public static AxisAlignedBB getExpandedEntityBox(EntityLivingBase entity) {
      float collisionBorderSize = entity.getCollisionBorderSize();
      return entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
   }

   public static float[] getAngleDiffToEntityNearestPoint(Entity entity, float f, float f2) {
      Vec3 nearestPointOnEntity = getNearestPointOnEntity(entity);
      double d = nearestPointOnEntity.xCoord - mc.thePlayer.posX;
      double d2 = nearestPointOnEntity.yCoord - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d3 = nearestPointOnEntity.zCoord - mc.thePlayer.posZ;
      float f3 = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI - 90.0);
      float f4 = (float)(-(MathHelper.atan2(d2, (double)MathHelper.sqrt_double(d * d + d3 * d3)) * 180.0 / Math.PI));
      float shortestSignedAngleDelta = (float)EntityBoundsUtil.getShortestSignedAngleDelta((double)wrapAngleTo180(f3), (double)wrapAngleTo180(f));
      float f6 = f4 - f2;
      return new float[]{d == 0.0 && d3 == 0.0 ? 0.0F : shortestSignedAngleDelta, d == 0.0 && d3 == 0.0 && d2 == 0.0 ? 0.0F : f6};
   }

   public static Vec3 getNearestPointOnEntityAt(Vec3 vec, EntityLivingBase entity, Vec3 vec2) {
      double zCoord = vec2.zCoord;
      double yCoord = vec2.yCoord;
      return getNearestPointOnEntityAt(vec, entity, vec2.xCoord, yCoord, zCoord);
   }

   public RotationKinematicState captureCurrentKinematicState() {
      double d = MathHelper.wrapAngleTo180_double((double)mc.thePlayer.lastReportedYaw);
      double lastReportedPitch = (double)mc.thePlayer.lastReportedPitch;
      return new RotationKinematicState(
         new Vec2d(
            EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)mc.thePlayer.prevReportedYaw), d) / 0.15 / 3.0,
            (lastReportedPitch - (double)mc.thePlayer.prevReportedPitch) / 0.15 / 3.0
         ),
         d,
         lastReportedPitch
      );
   }

   public RotationUtil() {
       super();
      this.lastTargetMinYaw = 0.0;
      this.mouseVelocity = new Vec2d(0.0, 0.0);
      this.substepsPerTick = 3;
      this.yawChangeWindow = new DoubleSampleWindow(9);
      this.noiseMinAimSpeed = 0.0;
      this.noiseDynamicSpeed = 0.0;
      this.noiseExtraAimSpeed = 0.0;
      this.noiseBezierProgress = 0.0;
      this.noiseIdleAimProbability = 0.142;
      this.noiseTestJitterEnabled = false;
      this.bezierMinReactionTime = 0;
      this.bezierMaxReactionTime = 0;
      this.bezierAttentionWindow = 3;
      this.bezierMinLookaheadTicks = 2;
      this.bezierMaxLookaheadTicks = 2;
      this.bezierSuperCorrectionDelay = 0;
      this.bezierAimSpeed = 8.0;
      this.bezierAdditionalInaccuracy = 0.0;
      this.bezierSuperCorrectionProbability = 0.0;
      this.bezierPreDecelerationFactor = 1.0;
      this.bezierPostDecelerationFactor = 1.0;
      this.bezierAdditionalAccuracy = false;
      this.bezierMinOrthogonalScale = 0.0;
      this.bezierMaxOrthogonalScale = 0.3;
      this.bezierMaxAimpointAngleDiff = 60.0;
      this.bezierMinPredictionInaccuracy = 0.0;
      this.bezierMaxPredictionInaccuracy = 0.0;
      this.bezierPredictionInaccuracySmoothness = 0.05F;
      this.bezierBestVecPitchInterpolation = 0.0;
      this.bezierMaxOrthogonalMovement = 10.0;
      this.bezierSkipYawOutOfReach = true;
      this.bezierSkipPitchOutOfReach = true;
      this.bezierApplyRecordedMouseJitter = false;
      this.bezierDuplicateEndControlPoint = true;
      this.turboMinTargetResponseTicks = 3;
      this.turboMaxTargetResponseTicks = 5;
      this.turboMinPlayerResponseTicks = 4;
      this.turboMaxPlayerResponseTicks = 4;
      this.turboMinAcceleration = 3.0F;
      this.turboMaxAcceleration = 6.0F;
      this.turboMinAimSpeed = 20.0F;
      this.turboMaxAimSpeed = 30.0F;
      this.turboMinLeadDistanceScale = 1.5;
      this.turboBetterAccelerationFactor = 0.0;
      this.bezierRotationEngine = new BezierRotationEngine();
      this.aiRotationPredictor = null;
      this.predictiveAimSolver = new PredictiveAimSolver();
      this.patternAimOffset = new Vec3(0.0, 0.0, 0.0);
      this.deltaCandidates = new ArrayList();
      this.previousAdvancedRotation = null;
      this.lastAdvancedRotation = null;
      this.lastCandidateDelta = new double[]{0.0, 0.0};
      this.previousTargetRotation = new Rotation();
      this.currentTargetRotation = new Rotation();
      this.lastTarget = null;
      this.lastPolarTargetYaw = 10.0F;
      this.lastPolarTargetPitch = 10.0F;
      this.lastSelectedCandidate = null;
      this.ticksSincePitchReset = 0;
      this.timeHelper = new TimeHelper();
      this.intaveTestResetIntervalMs = 2000;
   }

   public static Vec3 getScaledNearestPointInBox(Vec3 vec, AxisAlignedBB box, int n, int n2) {
      double d = box.maxX - box.minX;
      double d2 = box.maxY - box.minY;
      double d3 = d * ((double)n2 / 100.0);
      double d4 = d2 * ((double)n / 100.0);
      AxisAlignedBB box2 = box.expand((d3 - d) / 2.0, (d4 - d2) / 2.0, (d3 - d) / 2.0);
      double d5 = MathHelper.clamp_double(vec.xCoord, box2.minX, box2.maxX);
      double d6 = MathHelper.clamp_double(vec.yCoord, box2.minY, box2.maxY);
      double d7 = MathHelper.clamp_double(vec.zCoord, box2.minZ, box2.maxZ);
      return new Vec3(d5, d6, d7);
   }

   public RotationKinematicState stepAndAdvanceBezierCurve(BezierRotationStepper bezierRotationStepper, RotationKinematicState rotationKinematicState, BezierCurve2D bezierCurve2D, int n, double d) {
      RotationKinematicState rotationKinematicState2 = bezierRotationStepper.step(rotationKinematicState, bezierCurve2D, d);
      bezierCurve2D.advanceProgress(1.0 / (double)(n + 1));
      return rotationKinematicState2;
   }

   private Rotation computePolarTestRotation(
      EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, double d
   ) {
      Rotation rotation = applyGcdFix(updateAngle(f, f5, 180.0F), updateAngle(f2, f6, 180.0F), f, f2);
      double d2 = 0.0;
      MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, rotation, d, d2);
      boolean flag = hit != null && hit.entityHit instanceof EntityLivingBase;
      if (flag && entity.hurtTime > 3) {
         if (CombatOptimizationUtil.shouldReduceSprintKnockback(entity, applyYawGcd(updateAngle(f, f5, 180.0F), f))) {
            flag = false;
         }
      }

      float f8 = MathHelper.wrapAngleTo180_float(this.lastPolarTargetYaw - f5);
      float f9 = this.lastPolarTargetPitch - f6;
      if (flag && Math.abs(f8) > 10.0F) {
         flag = false;
      }

      ArrayList list = new ArrayList();
      if (flag) {
         for (RotationDeltaCandidate rotationDeltaCandidate : (Iterable<RotationDeltaCandidate>)(this.deltaCandidates)) {
            MovingObjectPosition hit2 = RayCastUtil.rayCast(1.0F, applyGcdFix(f3 + rotationDeltaCandidate.getYawDelta(), f4 + rotationDeltaCandidate.getPitchDelta(), f, f2));
            if (hit2 != null && hit2.entityHit instanceof EntityLivingBase) {
               list.add(rotationDeltaCandidate);
            }
         }
      }

      if (list.isEmpty()) {
         list = this.deltaCandidates;
      }

      HashMap map = new HashMap();
      float f10 = Math.abs(getAngleDiffToEntityNearestPoint(entity, f, f2)[0]);

      for (RotationDeltaCandidate rotationDeltaCandidate2 : (Iterable<RotationDeltaCandidate>)(list)) {
         double d3 = 0.0;
         Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
         Rotation rotation2 = new Rotation(f3 + rotationDeltaCandidate2.getYawDelta(), f4 + rotationDeltaCandidate2.getPitchDelta());
         d3 += 1.0 - Math.min(RotationDeltaCandidate.computeRotationDistance(rotationDeltaCandidate2, previousRotation, rotation2), 20.0) / 20.0;
         map.put(rotationDeltaCandidate2, d3);
      }

      List list2 = (List)map.keySet().stream().sorted(Comparator.comparingDouble(obj -> ((Number)map.get(obj)).doubleValue())).collect(Collectors.toList());
      RotationDeltaCandidate rotationDeltaCandidate3 = (RotationDeltaCandidate)list2.get(list2.size() - 1);
      this.lastPolarTargetYaw = f5;
      this.lastPolarTargetPitch = f6;
      return new Rotation(f3 + rotationDeltaCandidate3.getYawDelta(), f4 + rotationDeltaCandidate3.getPitchDelta());
   }


}
