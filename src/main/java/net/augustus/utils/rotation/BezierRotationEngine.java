package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.modules.modes.clicking.RecordedClickPatternMode;
import net.augustus.utils.math.CachedSimplexNoise2D;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.SimplexNoise;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.PositionHistoryProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class BezierRotationEngine implements MC {
   public boolean applyRecordedMouseJitter;
   private double lastYawFactor;
   private TargetTrajectoryPredictor trajectoryPredictor;
   private RotationKinematicState kinematicState;
   private double[] overlapPitchRange;
   public float predictionInaccuracySmoothness;
   public double maxOrthogonalScale;
   public boolean skipYawOutOfReach;
   private DoubleSampleWindow angularVelocityWindow;
   public int minLookaheadTicks;
   private int reactionTick;
   public double maxPitchOffset;
   private double yawNoiseFactor;
   private int lastReactionResetTick;
   public int superCorrectionDelay;
   private double pitchNoiseFactor;
   private int lastVelocitySign;
   public DynamicAimController fallbackAimController;
   private double outOfReachYawTolerance;
   public boolean duplicateEndControlPoint;
   public double maxAimpointAngleDiff;
   public boolean adaptCurveIfNeeded;
   public double superCorrectionProbability;
   public double bestVecPitchInterpolation;
   public float pitchOffsetPhase;
   private CachedSimplexNoise2D simplexNoise;
   public boolean skipPitchOutOfReach;
   public Random random;
   private AnglePredictionSample[] lastPredictionSamples;
   public double preDecelerationFactor;
   public double minRotationOnTarget;
   public double maxRotationOnTarget;
   public double aimSpeed;
   public double minPitchOffset;
   private double forcedTargetPitch;
   private double lastPitchFactor;
   private float aimRadiusNoisePhase;
   private double storedVelocityDirection;
   private BezierRotationStepper curveStepper;
   private BezierCurve2D activeCurve;
   public int attentionWindow;
   public int maxReactionTime;
   private double clampedOverlapPitch;
   public int minReactionTime;
   private EntityLivingBase lastTarget;
   public int maxLookaheadTicks;
   public double minYawOffset;
   public double additionalInaccuracy;
   public double minOrthogonalScale;
   public float yawOffsetPhase;
   public double minPredictionInaccuracy;
   public double maxPredictionInaccuracy;
   public double maxOrthogonalMovement;
   public double postDecelerationFactor;
   public double pitchOffsetSpeed;
   public double maxLookaheadSlopeThreshold;
   public boolean additionalAccuracy;
   private int ticksSinceDirectionChange;
   private Vec2d smoothedAimOffset;
   public double maxYawOffset;
   public double yawOffsetSpeed;

   public void setPositionHistoryProvider(PositionHistoryProvider positionHistoryProvider) {
      this.trajectoryPredictor.positionHistoryProvider = positionHistoryProvider;
   }

   public Rotation computeRotation(
      EntityLivingBase entity, EntityPlayerSP player, float f, float f2, double d, double d2, Vec3 vec, Vec3 vec2
   ) {
         this.curveStepper.adaptCurveIfNeeded = this.adaptCurveIfNeeded;
         this.curveStepper.debugEnabled = true;
         this.curveStepper.aimSpeed = this.aimSpeed;
         this.curveStepper.preDecelerationFactor = this.preDecelerationFactor;
         this.curveStepper.postDecelerationFactor = this.postDecelerationFactor;
         if (this.kinematicState == null || this.kinematicState.getCreatedTick() + 1 != player.ticksExisted) {
            this.kinematicState = new RotationKinematicState(
               new Vec2d(
                  EntityBoundsUtil.getShortestSignedAngleDelta(
                        MathHelper.wrapAngleTo180_double((double)mc.thePlayer.prevReportedYaw), MathHelper.wrapAngleTo180_double((double)mc.thePlayer.lastReportedYaw)
                     )
                     / 0.15
                     / 3.0,
                  (double)(mc.thePlayer.lastReportedPitch - mc.thePlayer.prevReportedPitch) / 0.15 / 3.0
               ),
               (double)f,
               (double)f2
            );
            this.activeCurve = null;
            this.reactionTick = player.ticksExisted;
            this.lastReactionResetTick = player.ticksExisted;
            this.smoothedAimOffset = null;
            this.aimRadiusNoisePhase = (float)ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
         }

         if (this.lastTarget != entity) {
            this.activeCurve = null;
            double d3 = (double)f;
            double d4 = (double)f2;
            this.kinematicState.setAngles(d3, d4);
            this.smoothedAimOffset = null;
            this.aimRadiusNoisePhase = (float)ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
         }

         this.lastTarget = entity;
         if (player.ticksExisted - this.reactionTick > this.maxReactionTime || this.additionalAccuracy && player.ticksExisted - this.lastReactionResetTick > this.maxReactionTime) {
            this.lastReactionResetTick = this.reactionTick = player.ticksExisted - (this.minReactionTime < this.maxReactionTime ? ThreadLocalRandom.current().nextInt(this.minReactionTime, this.maxReactionTime) : this.minReactionTime);
         }

         if (this.reactionTick < player.ticksExisted && Math.random() <= this.superCorrectionProbability) {
            this.trySuperCorrection(player, entity);
         }

         this.computeOverlapPitchRange(player, entity, (double)f2);
         RotationKinematicState rotationKinematicState = this.stepKinematicState(this.kinematicState, player, entity, d2);
         this.kinematicState = rotationKinematicState;
         float[] floats = new float[2];
         if (this.applyRecordedMouseJitter && ClientManagers.moduleManager.killAura.clickMode.getAbstractSubMode() instanceof RecordedClickPatternMode && ((RecordedClickPatternMode)ClientManagers.moduleManager.killAura.clickMode.getAbstractSubMode()).getActivePattern() != null) {
            floats = ((RecordedClickPatternMode)ClientManagers.moduleManager.killAura.clickMode.getAbstractSubMode()).getActivePattern().getCurrentRotation();
         }

         return new Rotation((float)rotationKinematicState.getYaw() + floats[0], (float)rotationKinematicState.getPitch() + floats[1]);
   }

   private AnglePredictionSample[] predictTargetAngles(RotationKinematicState rotationKinematicState, EntityPlayerSP player, EntityLivingBase entity, double d, double d2, double d3) {
      int n = this.maxLookaheadTicks > this.minLookaheadTicks ? this.random.nextInt(this.maxLookaheadTicks - this.minLookaheadTicks + 1) : 0;
      this.trajectoryPredictor.inaccuracyNoiseStep = this.predictionInaccuracySmoothness;
      int n2 = player.ticksExisted + this.minLookaheadTicks + n;
      int attentionWindow2 = this.attentionWindow;
      int n3 = n2;
      int reactionTick2 = this.reactionTick;
      AnglePredictionSample[] anglePredictionSamples = this.trajectoryPredictor.predictYawBounds(player, entity, reactionTick2, n3, attentionWindow2, d, d2, d3);
      if (n > 0 && anglePredictionSamples != null && Math.abs(anglePredictionSamples[0].angularSlope + anglePredictionSamples[1].angularSlope) / 2.0 > this.maxLookaheadSlopeThreshold / (double)n) {
         n2 = player.ticksExisted + this.minLookaheadTicks;
         int n4 = n2;
         int reactionTick3 = this.reactionTick;
         anglePredictionSamples = this.trajectoryPredictor.predictYawBounds(player, entity, reactionTick3, n4, (byte)3, d, d2, d3);
      }

      return anglePredictionSamples;
   }

   public void syncCurrentRotation(float f, float f2, boolean flag) {
      if (this.kinematicState != null) {
         double d = (double)f;
         double d2 = (double)f2;
         this.kinematicState.setAngles(d, d2);
         if (flag) {
            RotationKinematicState kinematicState2 = this.kinematicState;
            Vec2d vec2d = new Vec2d(
               EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double((double)mc.thePlayer.lastReportedYaw), MathHelper.wrapAngleTo180_double((double)f)) / 0.15,
               (double)(f2 - mc.thePlayer.lastReportedPitch) / 0.15
            );
            kinematicState2.setAngularVelocity(vec2d);
         }
      }
   }

   private double computeIntervalDistance(double[] doubles, double[] doubles2) {
      return Math.min(HitboxAngleUtil.getShortestAngularDistance((doubles[0] + doubles[1]) * 0.5, (doubles2[0] + doubles2[1]) * 0.5), (HitboxAngleUtil.getShortestAngularDistance(doubles[0], doubles2[0]) + doubles[1] + doubles2[1]) * 0.5);
   }

   private void buildBezierCurve(
      RotationKinematicState rotationKinematicState, EntityPlayerSP player, EntityLivingBase entity, double d, double[] doubles, double[] doubles2, double d2, boolean flag
   ) {
      double yaw = rotationKinematicState.getYaw();
      double pitch = rotationKinematicState.getPitch();
      double d3 = 0.0;
      double d4 = 0.0;
      double d5 = 0.0;
      double d6 = 1.0;
      if (this.activeCurve != null) {
         BezierCurve2D activeCurve2 = this.activeCurve;
         Vec2d vec2d = activeCurve2.evaluate(this.activeCurve.getProgress());
         activeCurve2 = this.activeCurve;
         Vec2d vec2d3 = vec2d.subtract(activeCurve2.evaluate(this.activeCurve.getPreviousProgress()));
         d4 = vec2d3.x;
         d5 = vec2d3.y;
         yaw = MathHelper.wrapAngleTo180_double(vec2d.x);
         pitch = vec2d.y;
         d3 = Math.atan2(vec2d3.y, vec2d3.x);
         if (!Double.isFinite(d3)) {
            d3 = 0.0;
         }

         if (!this.activeCurve.stationary) {
            double d7 = 0.5;
            double shortestAngularDistance = HitboxAngleUtil.getShortestAngularDistance(this.activeCurve.targetCenterYaw, MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, d7)));
            Vec3 positionEyes = player.getPositionEyes(1.0F);
            if (flag && shortestAngularDistance < this.outOfReachYawTolerance) {
               if (RotationUtil.clampPointToBox(positionEyes, EntityBoundsUtil.getEntityBoundingBoxAt(entity, entity.getInterpolatedPos(1.0F), true)).distanceTo(positionEyes) > 3.0) {
                  if (this.skipYawOutOfReach) {
                     doubles = this.activeCurve.targetYawRange;
                  }

                  if (this.skipPitchOutOfReach) {
                     doubles2 = this.activeCurve.targetPitchRange;
                  }
               }
            }
         }
      }

      Vec2d vec2d4 = new Vec2d(yaw, pitch);
      double d9 = this.random.nextDouble() * this.maxAimpointAngleDiff * (double)(this.random.nextBoolean() ? -1 : 1);
      double d12 = Math.min(HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(doubles[0]), MathHelper.wrapAngleTo180_double(doubles[1])), Math.abs(doubles2[0] - doubles2[1]));
      float f = 0.0F;
      float aimRadiusNoisePhase2 = this.aimRadiusNoisePhase;
      double d13 = d12 * MathStatisticsUtil.lerp(0.3, 0.8, (double)Math.abs(this.simplexNoise.sample(aimRadiusNoisePhase2, f))) / 2.0;
      this.aimRadiusNoisePhase += 0.025F;
      Vec2d vec2d5 = new Vec2d(
         Math.cos(d3 + d9 / 180.0 * 3.141592653589793) * d13, Math.sin(d3 + d9 / 180.0 * 3.141592653589793) * d13
      );
      if (this.smoothedAimOffset != null) {
         double d14 = this.maxRotationOnTarget > this.minRotationOnTarget ? ThreadLocalRandom.current().nextDouble(this.minRotationOnTarget, this.maxRotationOnTarget) : this.minRotationOnTarget;
         vec2d5 = this.smoothedAimOffset.lerp(vec2d5, d14);
      }

      this.smoothedAimOffset = vec2d5;
      double d16 = HitboxAngleUtil.lerpRange(doubles, 0.5);
      double d17 = 0.5;
      Vec2d vec2d7 = vec2d5.add(new Vec2d(d16, HitboxAngleUtil.lerpRange(doubles2, d17)));
      this.angularVelocityWindow.add(Math.abs(d));
      double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(doubles[0], doubles[1], MathHelper.wrapAngleTo180_double(vec2d7.x));
      double d20 = MathStatisticsUtil.lerp(HitboxAngleUtil.inverseLerpRange(doubles2, vec2d7.y), MathHelper.clamp_double(HitboxAngleUtil.inverseLerpRange(doubles2, pitch), 0.0, 1.0), 0.2);
      if (Double.isNaN(d20)) {
      }

      if (!Double.isFinite(this.lastPitchFactor)) {
         this.lastPitchFactor = 0.0;
      }

      double d21 = MathStatisticsUtil.lerp(d20, this.lastPitchFactor, 0.5);
      this.lastYawFactor = normalizedYawPosition;
      this.lastPitchFactor = d20;
      double d22 = (double)Math.abs(SimplexNoise.noise(this.yawOffsetPhase, 3.23F));
      vec2d7.x = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, normalizedYawPosition)) + MathStatisticsUtil.lerp(this.minYawOffset, this.maxYawOffset, d22);
      vec2d7.y = HitboxAngleUtil.lerpRange(doubles2, d21) + MathStatisticsUtil.lerp(this.minPitchOffset, this.maxPitchOffset, (double)Math.abs(SimplexNoise.noise(this.pitchOffsetPhase, -5.34F)));
      vec2d7.y = MathStatisticsUtil.lerp(vec2d7.y, d2, this.bestVecPitchInterpolation);
      if (Double.isFinite(this.forcedTargetPitch)) {
         vec2d7.y = this.forcedTargetPitch;
         this.forcedTargetPitch = 0.0 / 0.0;
      }

      vec2d7.x = EntityBoundsUtil.getShortestSignedAngleDelta(vec2d4.x, MathHelper.wrapAngleTo180_double(vec2d7.x));
      vec2d7.y = vec2d7.y - vec2d4.y;
      Vec2d vec2d8 = vec2d7.perpendicular();
      if (vec2d7.length() == 0.0) {
         vec2d8 = vec2d7;
      }

      vec2d8 = vec2d8.normalize();
      if (!Double.isFinite(vec2d8.x) || !Double.isFinite(vec2d8.y)) {
         vec2d8 = new Vec2d(0.0, 0.0);
      }

      double d23 = this.random.nextDouble() * 0.2 + 0.4;
      double d24 = MathHelper.clamp_double(
         MathStatisticsUtil.lerp(this.minOrthogonalScale, this.maxOrthogonalScale, Math.random()) * vec2d7.length() * (double)(ThreadLocalRandom.current().nextBoolean() ? -1 : 1),
         -this.maxOrthogonalMovement,
         this.maxOrthogonalMovement
      );
      Vec2d vec2d11 = vec2d7.scale(d23).add(vec2d8.scale(d24));
      if (vec2d8.length() == 0.0) {
         vec2d11 = new Vec2d(0.0, 0.0);
      }

      BezierCurve2D bezierCurve2D = new BezierCurve2D();
      if (Double.isFinite(vec2d4.x)
         && Double.isFinite(vec2d4.y)
         && Double.isFinite(vec2d11.x)
         && Double.isFinite(vec2d11.y)
         && Double.isFinite(vec2d7.x)
         && !Double.isFinite(vec2d7.y)) {
      }

      bezierCurve2D.controlPoints.add(vec2d4);
      bezierCurve2D.controlPoints.add(vec2d11.add(vec2d4));
      bezierCurve2D.controlPoints.add(vec2d7.add(vec2d4));
      if (this.duplicateEndControlPoint) {
         bezierCurve2D.controlPoints.add(vec2d7.add(vec2d4));
      }

      bezierCurve2D.setProgress(1.0 / (double)(this.minLookaheadTicks + 1));
      bezierCurve2D.targetCenterYaw = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, 0.5));
      bezierCurve2D.speedScale = d6;
      bezierCurve2D.targetYawRange = doubles;
      bezierCurve2D.targetPitchRange = doubles2;
      bezierCurve2D.durationTicks = this.minLookaheadTicks;
      this.activeCurve = bezierCurve2D;
   }

   public void setNoiseCache(HashMap map) {
      this.simplexNoise.cache = map;
   }

   private void computeOverlapPitchRange(EntityPlayerSP player, EntityLivingBase entity, double d) {
      ArrayList list;
      ArrayList list2;
      if (this.trajectoryPredictor.positionHistoryProvider != null) {
         int ticksExisted = player.ticksExisted;
         list = this.trajectoryPredictor.positionHistoryProvider.getPlayerTrajectory(ticksExisted);
         int ticksExisted2 = player.ticksExisted;
         list2 = this.trajectoryPredictor.positionHistoryProvider.getTargetTrajectory(ticksExisted2);
      } else {
         double posX = player.posX;
         double posY = player.posY;
         double posZ = player.posZ;
         EventMoveInput event = new EventMoveInput(
            player.movementInput.moveStrafe,
            player.movementInput.moveForward,
            player.movementInput.jump,
            player.movementInput.sneak,
            MoveInputStage.PRE
         );
         list = PlayerUtil.predictPlayerTrajectory(5, player, posX, posY, posZ, event);
         list2 = EntityMovementPredictor.predictTrajectoryFromServerPos(entity, list.size());
      }

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

   public static boolean enforceMinimumYawSpan(Entity entity, double[] doubles) {
      double[] yawRange = HitboxAngleUtil.getYawRange(new Vec3(0.0, 0.0, 0.0), EntityBoundsUtil.getEntityBoundingBoxAt(entity, new Vec3(3.4, 0.0, 0.0), true));
      double shortestAngularDistance = HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(yawRange[0]), MathHelper.wrapAngleTo180_double(yawRange[1]));
      if (HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(doubles[0]), MathHelper.wrapAngleTo180_double(doubles[1])) < shortestAngularDistance) {
         double d2 = (doubles[0] + doubles[1]) / 2.0;
         doubles[0] = d2 - shortestAngularDistance / 2.0;
         doubles[1] = d2 + shortestAngularDistance / 2.0;
         return true;
      } else {
         return false;
      }
   }

   public BezierRotationEngine() {
       super();
      this.simplexNoise = new CachedSimplexNoise2D();
      this.trajectoryPredictor = new TargetTrajectoryPredictor();
      this.curveStepper = new BezierRotationStepper();
      this.reactionTick = 0;
      this.lastReactionResetTick = 0;
      this.lastTarget = null;
      this.lastPredictionSamples = null;
      this.storedVelocityDirection = 0.0;
      this.lastVelocitySign = 0;
      this.ticksSinceDirectionChange = 0;
      this.angularVelocityWindow = new DoubleSampleWindow(4);
      this.forcedTargetPitch = 0.0 / 0.0;
      this.smoothedAimOffset = null;
      this.yawNoiseFactor = 0.5;
      this.pitchNoiseFactor = 0.5;
      this.yawOffsetPhase = 0.0F;
      this.pitchOffsetPhase = 0.0F;
      this.minReactionTime = 0;
      this.maxReactionTime = 0;
      this.minLookaheadTicks = 2;
      this.maxLookaheadTicks = 3;
      this.attentionWindow = 3;
      this.minPredictionInaccuracy = 0.0;
      this.maxPredictionInaccuracy = 0.0;
      this.predictionInaccuracySmoothness = 0.05F;
      this.superCorrectionDelay = 0;
      this.superCorrectionProbability = 0.0;
      this.additionalInaccuracy = 0.0;
      this.minOrthogonalScale = 0.0;
      this.maxOrthogonalScale = 0.3;
      this.aimSpeed = 8.0;
      this.preDecelerationFactor = 1.0;
      this.postDecelerationFactor = 1.0;
      this.additionalAccuracy = false;
      this.maxAimpointAngleDiff = 60.0;
      this.maxLookaheadSlopeThreshold = 30.0;
      this.bestVecPitchInterpolation = 0.0;
      this.minRotationOnTarget = 1.0;
      this.maxRotationOnTarget = 1.0;
      this.minYawOffset = -10.0;
      this.maxYawOffset = 10.0;
      this.minPitchOffset = 0.0;
      this.maxPitchOffset = 0.0;
      this.yawOffsetSpeed = 0.05000000074505806;
      this.pitchOffsetSpeed = 0.0;
      this.maxOrthogonalMovement = 10.0;
      this.duplicateEndControlPoint = true;
      this.adaptCurveIfNeeded = false;
      this.applyRecordedMouseJitter = false;
      this.lastYawFactor = 0.5;
      this.lastPitchFactor = 0.5;
      this.outOfReachYawTolerance = 15.0;
      this.skipYawOutOfReach = true;
      this.skipPitchOutOfReach = true;
      this.fallbackAimController = new DynamicAimController();
      this.random = new Random();
      this.aimRadiusNoisePhase = 0.0F;
   }

   private RotationKinematicState stepKinematicState(RotationKinematicState rotationKinematicState, EntityPlayerSP player, EntityLivingBase entity, double d) {
      double additionalInaccuracy2 = this.additionalInaccuracy;
      double d2 = this.maxPredictionInaccuracy;
      AnglePredictionSample[] anglePredictionSamples = this.predictTargetAngles(rotationKinematicState, player, entity, this.minPredictionInaccuracy, d2, additionalInaccuracy2);
      if (anglePredictionSamples == null) {
         anglePredictionSamples = new AnglePredictionSample[]{new AnglePredictionSample(0.0, 0.0, rotationKinematicState.getYaw(), rotationKinematicState.getYaw(), 0), new AnglePredictionSample(0.0, 0.0, rotationKinematicState.getYaw(), rotationKinematicState.getYaw(), 0)};
         if (this.activeCurve == null || this.activeCurve.getProgress() >= 1.0) {
            this.lastPredictionSamples = anglePredictionSamples;
            this.activeCurve = new BezierCurve2D();
            this.activeCurve.stationary = true;
         }
      }

      double[] doubles = HitboxAngleUtil.normalizeYawInterval(anglePredictionSamples[0].predictedAngle, anglePredictionSamples[1].predictedAngle);
      doubles[0] -= this.additionalInaccuracy;
      doubles[1] += this.additionalInaccuracy;
      boolean flag = enforceMinimumYawSpan(entity, doubles);
      this.yawOffsetPhase = (float)((double)this.yawOffsetPhase + this.yawOffsetSpeed);
      this.pitchOffsetPhase = (float)((double)this.pitchOffsetPhase + this.pitchOffsetSpeed);
      if (this.activeCurve != null && !(this.activeCurve.getProgress() >= 1.0) && !(this.random.nextDouble() < 0.01)) {
         double d4 = 1.0 / (double)(this.minLookaheadTicks + 1);
         this.activeCurve.advanceProgress(d4);
      } else {
         this.lastPredictionSamples = anglePredictionSamples;
         double d5 = (this.lastPredictionSamples[0].angularSlope + this.lastPredictionSamples[1].angularSlope) / 2.0;
         double d6 = d5;
         if (d5 == 0.0) {
            d6 = this.random.nextBoolean() ? -1.0 : 1.0;
         }

         int n = d5 < 0.0 ? -1 : (d5 > 0.0 ? 1 : 0);
         if (this.ticksSinceDirectionChange == -1 || (n != this.lastVelocitySign || d5 == 0.0) && this.ticksSinceDirectionChange > 20) {
            this.storedVelocityDirection = d6;
            this.ticksSinceDirectionChange = 0;
         }

         this.lastVelocitySign = n;
         this.buildBezierCurve(rotationKinematicState, player, entity, d5, doubles, this.overlapPitchRange, d, flag);
      }

      this.ticksSinceDirectionChange++;
      double d7 = (this.lastPredictionSamples[0].angularSlope + this.lastPredictionSamples[1].angularSlope) / 2.0;
      BezierCurve2D activeCurve2 = this.activeCurve;
      RotationKinematicState rotationKinematicState2 = this.curveStepper.step(rotationKinematicState, activeCurve2, d7);
      rotationKinematicState2.setTargetYawBounds(doubles);
      return rotationKinematicState2;
   }

   private void truncateAtDistanceInflection(ArrayList list, ArrayList list2) {
      Vec3 vec = (Vec3)list.get(0);
      double distance = ((Vec3)list2.get(0)).distanceTo(vec);
      double d2 = 0.0;
      double d3 = 0.0;
      int n = Math.min(list.size(), list2.size());
      int n2 = 1000;

      for (int i = 1; i < n; i++) {
         if (i == 1) {
            d2 = ((Vec3)list.get(i)).distanceTo((Vec3)list2.get(i));
            if (distance == d2) {
               return;
            }

            d3 = d2;
         } else {
            double d4 = ((Vec3)list.get(i)).distanceTo((Vec3)list2.get(i));
            if (d2 > distance) {
               if (d4 < d3) {
                  n2 = i;
                  break;
               }
            } else if (d4 > d3) {
               n2 = i;
               break;
            }

            d3 = d4;
         }
      }

      while (list.size() > n2) {
         list.remove(list.size() - 1);
      }

      while (list2.size() > n2) {
         list2.remove(list2.size() - 1);
      }
   }

   private void trySuperCorrection(EntityPlayerSP player, EntityLivingBase entity) {
      int ticksExisted = player.ticksExisted;
      int reactionTick2 = this.reactionTick;
      Vec3 vec = this.trajectoryPredictor.predictPlayerPosition(player, reactionTick2, ticksExisted);
      int ticksExisted2 = player.ticksExisted;
      int reactionTick3 = this.reactionTick;
      Vec3 vec2 = this.trajectoryPredictor.predictTargetPosition(entity, reactionTick3, ticksExisted2);
      Vec3 interpolatedPos = player.getInterpolatedPos(1.0F);
      Vec3 interpolatedPos2 = entity.getInterpolatedPos(1.0F);
      Vec3 vec3 = vec2.subtract(vec);
      Vec3 vec4 = interpolatedPos2.subtract(interpolatedPos);
      double d = MathHelper.wrapAngleTo180_double(Math.atan2(vec3.zCoord, vec3.xCoord) / 3.141592653589793 * 180.0);
      double d2 = MathHelper.wrapAngleTo180_double(Math.atan2(vec4.zCoord, vec4.xCoord) / 3.141592653589793 * 180.0);
      if (vec3.subtract(vec4).horizontalLength() < 0.2 && HitboxAngleUtil.getShortestAngularDistance(d, d2) < 2.0) {
         this.reactionTick = Math.max(player.ticksExisted - this.superCorrectionDelay, 0);
      }
   }

   public void overrideTargetPitch(double d) {
      this.forcedTargetPitch = d;
      this.activeCurve = null;
   }

   private boolean isYawRangeContained(double[] doubles, double[] doubles2) {
      double d = HitboxAngleUtil.getNormalizedAngleInWrappedRange(doubles2[0], doubles2[1], doubles[0]);
      double d2 = HitboxAngleUtil.getNormalizedAngleInWrappedRange(doubles2[0], doubles2[1], doubles[1]);
      return d >= 0.0 && d <= 1.0 && d2 >= 0.0 && d2 <= 1.0;
   }


}
