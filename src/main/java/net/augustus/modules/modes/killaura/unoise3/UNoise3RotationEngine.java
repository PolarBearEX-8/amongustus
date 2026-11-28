package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.RenderListener;
import net.augustus.render.RenderUtil;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MutableDoubleRef;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.rotation.DynamicAimController;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.Rotation;
import java.util.ArrayList;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class UNoise3RotationEngine implements RenderListener, MC {
   private BooleanValue uNoise3DebugPrediction;
   private IntervalNoiseSetting uNoise3SpeedAtInitialRotation;
   private EntityLivingBase lastTarget;
   private Vec3 prevPredictedTargetPos;
   private RandomizedRangeSettings uNoise3resyncdelay;
   private DoubleValue uNoise3highspeed;
   private DoubleValue uNoise3RemoveNoiseChance;
   private IntervalNoiseSetting uNoise3DecelerationFactorPostHighspeed;
   private UNoise3SettingContext uNoise3SettingContext;
   public static ArrayList ENGINES = new ArrayList();
   private IntervalNoiseSetting uNoise3SpeedAtLargeRotation;
   private double actualPitch;
   private Vec3 predictedTargetPos;
   private Vec3 prevPredictedPlayerPos;
   private RandomizedRangeSettings uNoise3ResyncUpdateDelay;
   private boolean resyncPending;
   private StringValue pointSelectionMode;
   private double baseYaw;
   private int resyncCooldownSubsteps;
   private DynamicAimController dynamicAimController;
   private DoubleValue uNoise3LargeRotation;
   private IntervalNoiseSetting uNoise3DecelerationFactorPre;
   private RandomizedRangeSettings uNoise3RecalcRemoveNoise;
   private Vec3 predictedPlayerPos;
   private Vec2d cachedAimGoal;
   private DoubleValue uNoise3ResyncChanceAtAimgoal;
   private IntervalNoiseSetting uNoise3Aimspeed;
   private int noNoiseRecalcTicks;
   private UNoise3MouseSimulator uNoise3MouseSimulator;
   private IntervalNoiseSetting uNoise3DecelerationFactorPost;
   private double basePitch;
   private UNoise3PointSelector uNoise3PointSelector;
   private double largeRotationSpeed;
   public UNoise3PredictionScheduler uNoise3PredictionScheduler;
   private boolean resyncQueued;
   private boolean resyncAtGoalEnabled;
   public boolean suppressTrailLogging;
   private int lastUpdateTick;
   private int remainingLookaheadTicks;
   private int resyncChanceRecalcTicks;
   private double actualYaw;

   private Vec2d resolveAimGoal(
      EntityLivingBase entity, EntityPlayerSP player, float f, float f2, double d, double d2, UNoise3PredictionEntry[] uNoise3PredictionEntrys, UNoise3PredictionEntry[] uNoise3PredictionEntrys2
   ) {
      if (this.cachedAimGoal != null && this.uNoise3PredictionScheduler.lookaheadEndTick >= player.ticksExisted && this.remainingLookaheadTicks > 0) {
         this.remainingLookaheadTicks--;
         return this.cachedAimGoal;
      } else {
         Vec2d vec2d;
         if (this.pointSelectionMode.getSelectedValue().equals("Prediction")) {
            double d3 = (uNoise3PredictionEntrys[0].angularVelocity + uNoise3PredictionEntrys[1].angularVelocity) / 2.0;
            double d4 = (uNoise3PredictionEntrys2[0].angularVelocity + uNoise3PredictionEntrys2[1].angularVelocity) / 2.0;
            UNoise3PointSelector uNoise3PointSelector2 = this.uNoise3PointSelector;
            UNoise3PredictionScheduler scheduler = this.uNoise3PredictionScheduler;
            vec2d = uNoise3PointSelector2.selectTargetAngles(scheduler, player, entity, uNoise3PredictionEntrys[0].predictedPlayerPos, uNoise3PredictionEntrys[0].predictedTargetPos, uNoise3PredictionEntrys2[0].predictedPlayerPos, uNoise3PredictionEntrys2[0].predictedTargetPos, (double)f, (double)f2, d, d2, this.uNoise3PredictionScheduler.uNoise3MovementFilter.predictMinDistance(player, entity, (byte)4), d3, d4);
         } else {
            vec2d = new Vec2d(d, d2);
            this.uNoise3PointSelector.resetState(d, d2);
         }

         this.cachedAimGoal = vec2d;
         this.remainingLookaheadTicks = this.uNoise3PredictionScheduler.lookaheadTicks;
         this.uNoise3MouseSimulator.clearAccelerationCap();
         return vec2d;
      }
   }

   private boolean isPointSelectionVisible() {
      return this.pointSelectionMode.getSelectedValue().equals("Prediction");
   }

   private void processQueuedResync() {
      if (this.resyncCooldownSubsteps == 0 && this.resyncQueued) {
         this.triggerResync();
      }
   }

   private void requestResync() {
      if (this.resyncCooldownSubsteps > 0) {
         this.resyncQueued = true;
      } else {
         this.triggerResync();
      }
   }

   private boolean isPredictionVisible() {
      return this.pointSelectionMode.getSelectedValue().equals("Prediction");
   }

   private void triggerResync() {
      this.resyncCooldownSubsteps = (int)((double)this.uNoise3resyncdelay.nextInt(true) / 16.6);
      this.resyncQueued = false;
      this.resyncPending = true;
   }

   public UNoise3RotationEngine(UNoise3SettingContext uNoise3SettingContext2, SettingVisibilityCondition condition) {
       super();
      this.resyncPending = false;
      this.lastUpdateTick = -1;
      this.lastTarget = null;
      this.resyncAtGoalEnabled = true;
      this.resyncChanceRecalcTicks = 0;
      this.noNoiseRecalcTicks = 0;
      this.suppressTrailLogging = false;
      this.cachedAimGoal = null;
      this.remainingLookaheadTicks = 0;
      this.uNoise3SettingContext = uNoise3SettingContext2;
      SettingGroup mouseGroup = (new SettingGroup(uNoise3SettingContext2.getModule(), "Mouse", uNoise3SettingContext2.getParentGroup())).visibleWhen(condition);
      this.pointSelectionMode = (StringValue)(new StringValue(
            uNoise3SettingContext2.getModule(), "Point selection mode", "Prediction", new String[]{"Provided", "Prediction"}, uNoise3SettingContext2.getParentGroup()
         ))
         .withVisibility(condition);
      SettingGroup pointSelectionGroup = new SettingGroup(uNoise3SettingContext2.getModule(), "Point selection", uNoise3SettingContext2.getParentGroup());
      SettingGroup settingGroup = pointSelectionGroup.visibleWhen(() -> this.isPointSelectionVisible()).visibleWhen(condition);
      pointSelectionGroup = new SettingGroup(uNoise3SettingContext2.getModule(), "Prediction", uNoise3SettingContext2.getParentGroup());
      SettingGroup settingGroup2 = pointSelectionGroup.visibleWhen(() -> this.isPredictionVisible()).visibleWhen(condition);
      this.uNoise3PredictionScheduler = new UNoise3PredictionScheduler(uNoise3SettingContext2.withParentGroup(settingGroup2));
      this.uNoise3PointSelector = new UNoise3PointSelector(uNoise3SettingContext2.withParentGroup(settingGroup), this.uNoise3PredictionScheduler);
      IntervalNoiseSetting intervalNoiseSetting2 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3Aimspeed", mouseGroup, true).named("Aim speed");
      double d = 20.0;
      double d2 = 15.0;
      double d3 = 100.0;
      this.uNoise3Aimspeed = intervalNoiseSetting2.withInterval(1.0, d3, d2, d, (byte)1);
      IntervalNoiseSetting intervalNoiseSetting4 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3DecelerationFactorPre", mouseGroup, false).named("Decelerationfactor Pre");
      double d5 = 1.0;
      double d6 = 1.0;
      double d7 = 2.0;
      this.uNoise3DecelerationFactorPre = intervalNoiseSetting4.withInterval(0.5, d7, d6, d5, (byte)2);
      IntervalNoiseSetting intervalNoiseSetting6 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3DecelerationFactorPost", mouseGroup, false).named("Decelerationfactor Post");
      double d9 = 1.0;
      double d10 = 1.0;
      double d11 = 8.0;
      this.uNoise3DecelerationFactorPost = intervalNoiseSetting6.withInterval(0.5, d11, d10, d9, (byte)2);
      IntervalNoiseSetting intervalNoiseSetting8 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3DecelerationFactorPostHighspeed", mouseGroup, false).named("Decelerationfactor Post at highspeed");
      double d13 = 2.0;
      double d14 = 2.0;
      double d15 = 8.0;
      this.uNoise3DecelerationFactorPostHighspeed = intervalNoiseSetting8.withInterval(0.5, d15, d14, d13, (byte)2);
      this.uNoise3highspeed = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3highspeed", 10.0, 5.0, 20.0, 1, mouseGroup).named("How many degrees per tick is high speed?");
      IntervalNoiseSetting intervalNoiseSetting10 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3SpeedAtLargeRotation", mouseGroup, true).named("Speed at large rotation");
      double d17 = 40.0;
      double d18 = 30.0;
      double d19 = 100.0;
      this.uNoise3SpeedAtLargeRotation = intervalNoiseSetting10.withInterval(1.0, d19, d18, d17, (byte)1);
      IntervalNoiseSetting intervalNoiseSetting12 = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3SpeedAtInitialRotation", mouseGroup, true).named("Speed at initial rotation");
      double d21 = 40.0;
      double d22 = 30.0;
      double d23 = 100.0;
      this.uNoise3SpeedAtInitialRotation = intervalNoiseSetting12.withInterval(1.0, d23, d22, d21, (byte)1);
      this.uNoise3LargeRotation = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3LargeRotation", 30.0, 1.0, 180.0, 1, mouseGroup).named("How many degrees difference do count as large rotation?");
      this.uNoise3MouseSimulator = new UNoise3MouseSimulator(uNoise3SettingContext2.withParentGroup(mouseGroup));
      this.uNoise3Aimspeed.addDefaultLayer();
      this.uNoise3DecelerationFactorPre.addDefaultLayer();
      this.uNoise3DecelerationFactorPost.addDefaultLayer();
      this.uNoise3SpeedAtLargeRotation.addDefaultLayer();
      this.uNoise3SpeedAtInitialRotation.addDefaultLayer();
      this.uNoise3DecelerationFactorPostHighspeed.addDefaultLayer();
      this.uNoise3ResyncChanceAtAimgoal = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3ResyncChanceAtAimgoal", 0.0, 0.0, 100.0, 0, mouseGroup).named("Resync at aimgoal");
      this.uNoise3ResyncUpdateDelay = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3ResyncUpdateDelay", 1.0, 1.0, 1.0, 5.0, 0, mouseGroup).withDisplayNamePrefix("Resync recalculate delay");
      this.uNoise3RemoveNoiseChance = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3RemoveNoiseChance", 0.0, 0.0, 100.0, 0, mouseGroup).named("Apply no noise when near target chance");
      this.uNoise3RecalcRemoveNoise = (RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3RecalcRemoveNoise", 1.0, 5.0, 1.0, 8.0, 0, mouseGroup).withDisplayNamePrefix("No noise recalc delay");
      this.uNoise3resyncdelay = ((RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3resyncdelay", 0.0, 0.0, 0.0, 1000.0, 0, mouseGroup).withDisplayNamePrefix("Resync delay")).withoutNotInARow();
      this.uNoise3DebugPrediction = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), "UNoise3DebugPrediction", false, settingGroup2).withDisplayName("Debug prediction");
      this.uNoise3MouseSimulator.onGoalReached = () -> {
         boolean flag = true;
         if (!this.resyncAtGoalEnabled) {
            flag = false;
         }

         if (flag) {
            this.requestResync();
         }

         this.uNoise3MouseSimulator.setAcceleration(this.uNoise3Aimspeed.sample() * 20.0, false);
         this.uNoise3MouseSimulator.clearAccelerationCap();
         this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
         this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
         this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
         this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue();
         this.largeRotationSpeed = this.uNoise3SpeedAtLargeRotation.sample() * 20.0;
         if (this.remainingLookaheadTicks > 0) {
            this.uNoise3PredictionScheduler.forceRecompute = true;
         }

         this.remainingLookaheadTicks = 0;
         this.cachedAimGoal = null;
      };
      this.uNoise3MouseSimulator.onResyncTriggered = () -> this.requestResync();
      ENGINES.add(this);
   }

   public static void syncAllEngines(double d, double d2) {

      for (UNoise3RotationEngine uNoise3RotationEngine : (Iterable<UNoise3RotationEngine>)(ENGINES)) {
         uNoise3RotationEngine.syncAngles(d, d2);
      }
   }

   public Rotation computeRotation(EntityLivingBase entity, EntityPlayerSP player, float f, float f2, double d, double d2, Vec3 vec, Vec3 vec2) {
            f = MathHelper.wrapAngleTo180_float(f);
            this.uNoise3PredictionScheduler.uNoise3MovementFilter.player = player;
            this.uNoise3PointSelector.player = player;
            boolean flag = HitboxAngleUtil.getShortestAngularDistance((double)f, this.actualYaw) > 1.0 || Math.abs(this.actualPitch - (double)f2) > 1.0;
            if (this.lastTarget != entity || this.lastUpdateTick + 1 != player.ticksExisted && (!mc.ranMultipleTicks || ClientManagers.moduleManager.rotationHandler.rotationMode.getSelectedValue().equals("Tick")) || flag) {
               this.initializeForNewTarget((double)f, (double)f2);
               double d6 = (double)f;
               double d7 = (double)f2;
               this.uNoise3PointSelector.resetState(d6, d7);
               int ticksExisted = player.ticksExisted;
               this.uNoise3PredictionScheduler.resetSnapshotTick(ticksExisted);
               double d9 = MathHelper.wrapAngleTo180_double((double)player.prevReportedYaw);
               double d10 = MathHelper.wrapAngleTo180_double((double)player.lastReportedYaw);
               double lastReportedPitch = (double)player.lastReportedPitch;
               double prevReportedPitch = (double)player.prevReportedPitch;
               this.uNoise3MouseSimulator.currentVelocity = new Vec2d(EntityBoundsUtil.getShortestSignedAngleDelta(d9, d10) / 0.15 / 3.0, (lastReportedPitch - prevReportedPitch) / 0.15 / 3.0);
               this.uNoise3MouseSimulator.resyncStepThreshold = -1;
            }

            this.lastTarget = entity;
            this.lastUpdateTick = player.ticksExisted;
            if (!Double.isFinite(this.actualYaw)) {
               this.actualYaw = (double)f;
               ChatUtil.sendChat("Fixed flaw (1)");
            }

            if (!Double.isFinite(this.actualPitch)) {
               this.actualPitch = (double)f2;
               ChatUtil.sendChat("Fixed flaw (2)");
            }

            if (!Double.isFinite(this.baseYaw)) {
               this.baseYaw = this.actualYaw;
               ChatUtil.sendChat("Fixed flaw (3)");
            }

            if (!Double.isFinite(this.basePitch)) {
               this.basePitch = this.actualPitch;
               ChatUtil.sendChat("Fixed flaw (4)");
            }

            if (this.resyncChanceRecalcTicks == 0) {
               this.resyncAtGoalEnabled = Math.random() <= this.uNoise3ResyncChanceAtAimgoal.getValue(true) / 100.0;
               this.resyncChanceRecalcTicks = this.uNoise3ResyncUpdateDelay.nextInt(true);
            } else if (this.resyncChanceRecalcTicks > 0) {
               this.resyncChanceRecalcTicks--;
            }

            if (this.noNoiseRecalcTicks == 0) {
               this.uNoise3MouseSimulator.disableNoiseNearTarget = Math.random() <= this.uNoise3RemoveNoiseChance.getValue(true) / 100.0;
               this.noNoiseRecalcTicks = this.uNoise3RecalcRemoveNoise.nextInt(true);
            } else if (this.noNoiseRecalcTicks > 0) {
               this.noNoiseRecalcTicks--;
            }

            UNoise3PredictionEntry[] uNoise3PredictionEntrys = null;
            UNoise3PredictionEntry[] uNoise3PredictionEntrys2 = null;
            Vec3 interpolatedPos = player.getInterpolatedPos(1.0F);
            Vec3 positionEyes = player.getPositionEyes(1.0F);
            Vec3 interpolatedPos2 = entity.getInterpolatedPos(1.0F);
            this.uNoise3PredictionScheduler.updatePredictions(player, entity);
            if (this.uNoise3PredictionScheduler.yawPredictions == null || !this.uNoise3PredictionScheduler.yawPredictions[0].tooClose && !this.uNoise3PredictionScheduler.yawPredictions[0].filtered) {
               uNoise3PredictionEntrys = this.uNoise3PredictionScheduler.yawPredictions;
               uNoise3PredictionEntrys2 = this.uNoise3PredictionScheduler.pitchPredictions;
            }

            if (uNoise3PredictionEntrys == null) {
               uNoise3PredictionEntrys = new UNoise3PredictionEntry[]{
                  new UNoise3PredictionEntry(interpolatedPos, interpolatedPos2, 0.0, 0.0, this.uNoise3PointSelector.lastOutputYaw, this.uNoise3PointSelector.lastOutputYaw, 1),
                  new UNoise3PredictionEntry(interpolatedPos, interpolatedPos2, 0.0, 0.0, this.uNoise3PointSelector.lastOutputYaw, this.uNoise3PointSelector.lastOutputYaw, 1)
               };
            }

            if (uNoise3PredictionEntrys2 == null) {
               uNoise3PredictionEntrys2 = new UNoise3PredictionEntry[]{
                  new UNoise3PredictionEntry(interpolatedPos, interpolatedPos2, 0.0, 0.0, this.uNoise3PointSelector.lastOutputPitch, this.uNoise3PointSelector.lastOutputPitch, 1),
                  new UNoise3PredictionEntry(interpolatedPos, interpolatedPos2, 0.0, 0.0, this.uNoise3PointSelector.lastOutputPitch, this.uNoise3PointSelector.lastOutputPitch, 1)
               };
            }

            this.prevPredictedPlayerPos = this.predictedPlayerPos;
            this.prevPredictedTargetPos = this.predictedTargetPos;
            this.predictedTargetPos = uNoise3PredictionEntrys2[0].predictedTargetPos;
            this.predictedPlayerPos = uNoise3PredictionEntrys2[0].predictedPlayerPos;
            Vec2d vec2d = this.resolveAimGoal(entity, player, f, f2, d, d2, uNoise3PredictionEntrys, uNoise3PredictionEntrys2);
            MutableDoubleRef mutableDoubleRef = new MutableDoubleRef(EntityBoundsUtil.getShortestSignedAngleDelta(this.baseYaw, MathHelper.wrapAngleTo180_double(vec2d.x)));
            MutableDoubleRef mutableDoubleRef2 = new MutableDoubleRef(vec2d.y - this.basePitch);
            double d13 = (uNoise3PredictionEntrys[0].angularVelocity + uNoise3PredictionEntrys[1].angularVelocity) / 2.0;
            if (Math.sqrt(mutableDoubleRef.getValue() * mutableDoubleRef.getValue() + mutableDoubleRef2.getValue() * mutableDoubleRef2.getValue()) > this.uNoise3LargeRotation.getValue(true)) {
               double largeRotationSpeed2 = this.largeRotationSpeed;
               this.uNoise3MouseSimulator.setAcceleration(largeRotationSpeed2, false);
            }

            if (!this.uNoise3MouseSimulator.isAccelerationCapped() && this.uNoise3PredictionScheduler.lookaheadTicks > 0 && this.remainingLookaheadTicks >= 0 && this.pointSelectionMode.getSelectedValue().equals("Prediction")) {
               double effectiveAcceleration = this.uNoise3MouseSimulator.getEffectiveAcceleration();
               double value = mutableDoubleRef.getValue();
               double d15;
               if (Math.signum(d13) != Math.signum(value) && value != 0.0) {
                  d15 = 0.0;
               } else {
                  d15 = Math.abs(d13);
               }

               MouseTrajectoryConfig mouseTrajectoryConfig = new MouseTrajectoryConfig(mutableDoubleRef.getValue() / 0.15, this.uNoise3MouseSimulator.currentVelocity.x, d15 / 0.15, effectiveAcceleration, effectiveAcceleration, effectiveAcceleration);
               MouseTrajectoryConfig mouseTrajectoryConfig2 = new MouseTrajectoryConfig(mutableDoubleRef2.getValue() / 0.15, this.uNoise3MouseSimulator.currentVelocity.y, 0.0, effectiveAcceleration, effectiveAcceleration, effectiveAcceleration);
               int n = this.remainingLookaheadTicks * 3 + 3;
               double maxAcceleration = this.uNoise3MouseSimulator.solveAccelerationForSteps(n, mouseTrajectoryConfig).maxAcceleration;
               int n2 = this.remainingLookaheadTicks * 3 + 3;
               double maxAcceleration2 = this.uNoise3MouseSimulator.solveAccelerationForSteps(n2, mouseTrajectoryConfig2).maxAcceleration;
               this.uNoise3MouseSimulator.setAcceleration(Math.max(maxAcceleration, maxAcceleration2), true);
            }

            UNoise3MouseSimulator uNoise3MouseSimulator4 = this.uNoise3MouseSimulator;
            double d18 = d13 / 0.15;
            MouseSimulationStepCallback callback = (n3, d19, d20) -> {
               this.tickResyncCooldown();
               this.processQueuedResync();
               if (!this.suppressTrailLogging) {
                  ClientManagers.moduleManager.line.rotationLineTimestamps.add((long)((double)System.currentTimeMillis() - (1.0 - (double)(n3 + 1) / 3.0) * 50.0));
                  ClientManagers.moduleManager.line.rotationLineEyePositions.add(mc.thePlayer.getPositionEyes((float)(n3 + 1) / 3.0F));
                  ClientManagers.moduleManager
                     .line
                     .rotationLineAngles
                     .add(
                        new float[]{
                           (float)MathHelper.wrapAngleTo180_double(this.actualYaw + d19 * 0.15),
                           MathHelper.clamp_float((float)(this.actualPitch + d20 * 0.15), -90.0F, 90.0F)
                        }
                     );

                  while (
                     !ClientManagers.moduleManager.line.rotationLineTimestamps.isEmpty()
                        && (double)(System.currentTimeMillis() - (Long)ClientManagers.moduleManager.line.rotationLineTimestamps.get(0)) > ClientManagers.moduleManager.line.auraLineTime.getValue()
                  ) {
                     ClientManagers.moduleManager.line.rotationLineTimestamps.remove(0);
                     ClientManagers.moduleManager.line.rotationLineAngles.remove(0);
                     ClientManagers.moduleManager.line.rotationLineEyePositions.remove(0);
                  }
               }

               if (this.resyncPending) {
                  this.baseYaw = this.actualYaw;
                  this.basePitch = this.actualPitch;
                  mutableDoubleRef.setValue(EntityBoundsUtil.getShortestSignedAngleDelta(this.baseYaw, MathHelper.wrapAngleTo180_double(vec2d.x)));
                  mutableDoubleRef2.setValue(vec2d.y - this.basePitch);
                  this.resyncPending = false;
               }
            };
            MouseSimulationDelta mouseSimulationDelta = uNoise3MouseSimulator4.simulateTickSubsteps(mutableDoubleRef, mutableDoubleRef2, d18, (byte)3, callback);
            this.uNoise3Aimspeed.stepLayers();
            this.uNoise3SpeedAtLargeRotation.stepLayers();
            this.uNoise3SpeedAtInitialRotation.stepLayers();
            this.uNoise3DecelerationFactorPre.stepLayers();
            this.uNoise3DecelerationFactorPost.stepLayers();
            this.uNoise3DecelerationFactorPostHighspeed.stepLayers();
            double d24 = mouseSimulationDelta.noisyDelta.x * 0.15;
            double d25 = mouseSimulationDelta.noisyDelta.y * 0.15;
            this.baseYaw = MathHelper.wrapAngleTo180_double(this.baseYaw + mouseSimulationDelta.baseDelta.x * 0.15);
            this.basePitch = MathHelper.clamp_double(this.basePitch + mouseSimulationDelta.baseDelta.y * 0.15, -90.0, 90.0);
            this.actualYaw = MathHelper.wrapAngleTo180_double(this.actualYaw + d24);
            this.actualPitch = MathHelper.clamp_double(this.actualPitch + d25, -90.0, 90.0);
            return new Rotation((float)this.actualYaw, (float)this.actualPitch);
   }

   public void initializeForNewTarget(double d, double d2) {
      this.baseYaw = d;
      this.basePitch = d2;
      this.actualYaw = d;
      this.actualPitch = d2;
      this.uNoise3MouseSimulator.setAcceleration(this.uNoise3SpeedAtInitialRotation.sample() * 20.0, false);
      this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
      this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
      this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
      this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue();
      this.largeRotationSpeed = this.uNoise3SpeedAtLargeRotation.sample() * 20.0;
      this.cachedAimGoal = null;
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.uNoise3DebugPrediction.getValue()) {
         Vec3 vec = null;
         Vec3 vec2 = null;
         if (this.prevPredictedTargetPos != null && this.prevPredictedPlayerPos != null && this.predictedTargetPos != null && this.lastTarget != null && this.predictedPlayerPos != null && mc.thePlayer != null) {
            vec = this.prevPredictedTargetPos.lerp(this.predictedTargetPos, (double)partialTicks);
            vec2 = this.prevPredictedPlayerPos.lerp(this.predictedPlayerPos, (double)partialTicks);
         }

         if (vec != null) {
            RenderUtil.begin3DRender();
            GL11.glPushMatrix();
            GL11.glLineWidth(3.0F);
            GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
            GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawOutlinedBoundingBox(EntityBoundsUtil.getEntityBoundingBoxAt(this.lastTarget, vec, true));
            GlStateManager.color(0.0F, 0.0F, 1.0F, 1.0F);
            RenderUtil.drawOutlinedBoundingBox(EntityBoundsUtil.getEntityBoundingBoxAt(mc.thePlayer, vec2, true));
            GL11.glPopMatrix();
            RenderUtil.end3DRender();
         }
      }
   }

   public int estimateStepsToTarget(double d, double d2, double d3, double d4) {
      return this.uNoise3MouseSimulator.simulateTrajectory(new MouseTrajectoryConfig(d, d2, d3, d4, d4, 6.0)).size();
   }

   private void tickResyncCooldown() {
      if (this.resyncCooldownSubsteps > 0) {
         this.resyncCooldownSubsteps--;
      }
   }

   public void syncAngles(double d, double d2) {
      d = MathHelper.wrapAngleTo180_double(d);
      d2 = MathHelper.clamp_double(d2, -90.0, 90.0);
      this.uNoise3PointSelector.resetState(d, d2);
      this.baseYaw = d;
      this.basePitch = d2;
      this.actualYaw = d;
      this.actualPitch = d2;
   }

}
