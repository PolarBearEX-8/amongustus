package net.augustus.utils.rotation;

import net.augustus.ClientManagers;
import net.augustus.modules.modes.killaura.unoise3.MouseSimulationDelta;
import net.augustus.modules.modes.killaura.unoise3.MouseSimulationStepCallback;
import net.augustus.modules.modes.killaura.unoise3.UNoise3MouseSimulator;
import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.MutableDoubleRef;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class RotationEngine {
   private RandomizedRangeSettings uNoise3ResyncUpdateDelay;
   private double curveDeltaYaw;
   private DoubleValue uNoise3RemoveNoiseChance;
   private final BooleanValue rotationEngineRandomizeOnlyWhileRotating;
   private IntervalNoiseSetting uNoise3SpeedAtInitialRotation;
   private float previousTargetYaw;
   private int lastAdvancedTick;
   private final RandomizedRangeSettings rotationEnginePitchAcceleration;
   private final DoubleValue rotationEngineInterval;
   private double curveStartYaw;
   private double largeRotationSpeed;
   private final Supplier targetChangedSupplier;
   private DoubleValue uNoise3LargeRotation;
   private double actualYaw;
   private final RandomizedRangeSettings rotationEngineYawSpeed;
   private double simulatedYaw;
   private final DoubleValue rotationEngineAmplitude;
   private RandomizedRangeSettings uNoise3RecalcRemoveNoise;
   private final BooleanValue rotationEngineBetterAcceleration;
   private int resyncDelayTicks;
   private RandomizedRangeSettings uNoise3resyncdelay;
   private IntervalNoiseSetting uNoise3Aimspeed;
   private boolean resyncQueued;
   private float previousTargetPitch;
   private int removeNoiseRecalcTicks;
   private IntervalNoiseSetting uNoise3DecelerationFactorPre;
   private int curveIntervalTicks;
   private boolean resyncPending;
   private final RandomizedRangeSettings rotationEnginePitchSpeed;
   private DoubleValue uNoise3ResyncChanceAtAimgoal;
   private final RandomizedRangeSettings rotationEngineYawAcceleration;
   private final StringValue rotationEngineMode;
   private boolean shouldResyncAtGoal;
   private double actualPitch;
   private IntervalNoiseSetting uNoise3DecelerationFactorPostHighspeed;
   private UNoise3MouseSimulator uNoise3MouseSimulator;
   private double curveDeltaPitch;
   private DoubleValue uNoise3highspeed;
   private IntervalNoiseSetting uNoise3DecelerationFactorPost;
   private double curveStartPitch;
   private IntervalNoiseSetting uNoise3SpeedAtLargeRotation;
   private final DoubleValue rotationEngineFrequency;
   private double simulatedPitch;
   private int resyncRecalcTicks;
   private final RandomizedRangeSettings rotationEngineRandomStrength;

   private boolean isIntervalVisible() {
      return this.rotationEngineMode.getSelectedValue().equals("Curve");
   }

   private void scheduleResync() {
      if (this.resyncDelayTicks > 0) {
         this.resyncQueued = true;
      } else {
         this.triggerResync();
      }
   }

   private boolean isFrequencyVisible() {
      return this.rotationEngineMode.getSelectedValue().equals("Curve");
   }

   public Rotation rotateTowards(float f, float f2, Rotation rotation) {
      float pitch = rotation.pitch;
      return this.rotateTowards(f, f2, rotation.yaw, pitch);
   }

   public Rotation rotateTowards(Rotation rotation, Rotation rotation2) {
      float pitch = rotation2.pitch;
      float yaw = rotation2.yaw;
      float pitch2 = rotation.pitch;
      return this.rotateTowards(rotation.yaw, pitch2, yaw, pitch);
   }

   private boolean isRandomizeOnlyWhileRotatingVisible(SettingVisibilityCondition condition) {
      return !this.rotationEngineMode.getSelectedValue().equals("Curve") && !condition.isVisible();
   }

   public Rotation computeCurvedRotation(float f, float f2, float f3, float f4, float f5, float f6, int n, float f7, float f8) {
      double d;
      f = RotationUtil.updateAngle(f3, f, 180.0F);
         f2 = RotationUtil.updateAngle(f4, f2, 180.0F);
         d = computeMaxLinearStep(f, f2, f3, f4, f5, f6);
         double d2 = (double)f - this.curveStartYaw;
         double d3 = (double)f2 - this.curveStartPitch;
if (!(this.curveIntervalTicks > 0 && haveSameSign(d2, this.curveDeltaYaw) && haveSameSign(d3, this.curveDeltaPitch))) {
this.curveIntervalTicks = n;
         this.curveStartYaw = (double)f3;
         this.curveStartPitch = (double)f4;
         this.curveDeltaYaw = (double)(f - f3);
         this.curveDeltaPitch = (double)(f2 - f4);
}

      this.curveIntervalTicks--;
      double d4 = distance2D(this.curveStartYaw, this.curveStartPitch, (double)f3, (double)f4);
      double[] doubles = sampleSinusoidalArcPoint(this.curveStartYaw, this.curveStartPitch, (double)f, (double)f2, d + d4, (double)f7, (double)f8);
      return new Rotation((float)doubles[0], (float)doubles[1]);
   }

   public RotationEngine(Module module, SettingGroup doubleValue, Supplier supplier) {
       super();
      this.previousTargetYaw = Float.NaN;
      this.previousTargetPitch = 0.0F;
      this.curveIntervalTicks = 0;
      this.rotationEngineMode = (StringValue)new StringValue(
         module, "RotationEngineMode", "Independent", new String[]{"Independent", "Linear", "Curve", "Advanced"}, doubleValue
      ).withDisplayName("Mode");
      SettingVisibilityCondition condition = () -> this.isAdvancedMode();
      RandomizedRangeSettings randomizedRangeSettings2 = ((RandomizedRangeSettings)new RandomizedRangeSettings(module, "RotationEngineYawSpeed", 15.0, 20.0, 0.0, 180.0, 1, doubleValue).withDisplayNamePrefix("YawSpeed")).withoutNotInARow();
      SettingVisibilityCondition condition2 = () -> {
         return !condition.isVisible();
      };
      this.rotationEngineYawSpeed = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition2);
      RandomizedRangeSettings randomizedRangeSettings4 = ((RandomizedRangeSettings)new RandomizedRangeSettings(module, "RotationEnginePitchSpeed", 15.0, 20.0, 0.0, 180.0, 1, doubleValue).withDisplayNamePrefix("PitchSpeed")).withoutNotInARow();
      SettingVisibilityCondition condition3 = () -> {
         return !condition.isVisible();
      };
      this.rotationEnginePitchSpeed = (RandomizedRangeSettings)randomizedRangeSettings4.withVisibility(condition3);
      RandomizedRangeSettings randomizedRangeSettings6 = ((RandomizedRangeSettings)new RandomizedRangeSettings(module, "RotationEngineYawAcceleration", 5.0, 10.0, 0.0, 180.0, 1, doubleValue).withDisplayNamePrefix("YawAcceleration")).withoutNotInARow();
      SettingVisibilityCondition condition4 = () -> {
         return !condition.isVisible();
      };
      this.rotationEngineYawAcceleration = (RandomizedRangeSettings)randomizedRangeSettings6.withVisibility(condition4);
      RandomizedRangeSettings randomizedRangeSettings8 = ((RandomizedRangeSettings)new RandomizedRangeSettings(module, "RotationEnginePitchAcceleration", 5.0, 10.0, 0.0, 180.0, 1, doubleValue).withDisplayNamePrefix("PitchAcceleration")).withoutNotInARow();
      SettingVisibilityCondition condition5 = () -> {
         return !condition.isVisible();
      };
      this.rotationEnginePitchAcceleration = (RandomizedRangeSettings)randomizedRangeSettings8.withVisibility(condition5);
      RandomizedRangeSettings randomizedRangeSettings10 = (RandomizedRangeSettings)(new RandomizedRangeSettings(module, "RotationEngineRandomStrength", 0.0, 0.5, 0.0, 4.0, 2, doubleValue)).withoutNotInARow().withDisplayNamePrefix("RandomStrength");
      SettingVisibilityCondition condition6 = () -> {
         return !this.rotationEngineMode.getSelectedValue().equals("Curve") && !condition.isVisible();
      };
      this.rotationEngineRandomStrength = (RandomizedRangeSettings)randomizedRangeSettings10.withVisibility(condition6);
      this.rotationEngineRandomizeOnlyWhileRotating = (BooleanValue)((BooleanValue)new BooleanValue(module, "RotationEngineRandomizeOnlyWhileRotating", true, doubleValue).withDisplayName("RandomizeOnlyAtRotating")).withVisibility(() -> this.isRandomizeOnlyWhileRotatingVisible(condition));
      this.rotationEngineInterval = new DoubleValue(module, "RotationEngineInterval", 7.0, 1.0, 20.0, 0, doubleValue).named("Interval").visibleWhen(() -> this.isIntervalVisible());
      this.rotationEngineAmplitude = new DoubleValue(module, "RotationEngineAmplitude", 1.0, 0.1, 5.0, 2, doubleValue).named("Amplitude").visibleWhen(() -> this.isAmplitudeVisible());
      this.rotationEngineFrequency = new DoubleValue(module, "RotationEngineFrequency", 1.0, 1.0, 8.0, 0, doubleValue).named("Frequency").visibleWhen(() -> this.isFrequencyVisible());
      BooleanValue booleanValue4 = (BooleanValue)new BooleanValue(module, "RotationEngineBetterAcceleration", false, doubleValue).withDisplayName("BetterAcceleration");
      SettingVisibilityCondition condition11 = () -> {
         return !condition.isVisible();
      };
      this.rotationEngineBetterAcceleration = (BooleanValue)booleanValue4.withVisibility(condition11);
      UNoise3SettingContext uNoise3SettingContext = new UNoise3SettingContext(module, doubleValue, condition);
      IntervalNoiseSetting intervalNoiseSetting2 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3Aimspeed", doubleValue, true).named("Aim speed");
      double d = 20.0;
      double d2 = 15.0;
      double d3 = 100.0;
      this.uNoise3Aimspeed = intervalNoiseSetting2.withInterval(1.0, d3, d2, d, (byte)1).visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting5 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3DecelerationFactorPre", doubleValue, false).named("Decelerationfactor Pre");
      double d5 = 1.0;
      double d6 = 1.0;
      double d7 = 2.0;
      this.uNoise3DecelerationFactorPre = intervalNoiseSetting5.withInterval(0.5, d7, d6, d5, (byte)2).visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting8 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3DecelerationFactorPost", doubleValue, false).named("Decelerationfactor Post");
      double d9 = 1.0;
      double d10 = 1.0;
      double d11 = 8.0;
      this.uNoise3DecelerationFactorPost = intervalNoiseSetting8.withInterval(0.5, d11, d10, d9, (byte)2).visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting11 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3DecelerationFactorPostHighspeed", doubleValue, false).named("Decelerationfactor Post at highspeed");
      double d13 = 2.0;
      double d14 = 2.0;
      double d15 = 8.0;
      this.uNoise3DecelerationFactorPostHighspeed = intervalNoiseSetting11.withInterval(0.5, d15, d14, d13, (byte)2).visibleWhen(condition);
      this.uNoise3highspeed = new DoubleValue(uNoise3SettingContext.getModule(), "UNoise3highspeed", 10.0, 5.0, 20.0, 1, doubleValue).named("How many degrees per tick is high speed?").visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting14 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3SpeedAtLargeRotation", doubleValue, true).named("Speed at large rotation");
      double d17 = 40.0;
      double d18 = 30.0;
      double d19 = 100.0;
      this.uNoise3SpeedAtLargeRotation = intervalNoiseSetting14.withInterval(1.0, d19, d18, d17, (byte)1).visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting17 = new IntervalNoiseSetting(uNoise3SettingContext.getModule(), "UNoise3SpeedAtInitialRotation", doubleValue, true).named("Speed at initial rotation");
      double d21 = 40.0;
      double d22 = 30.0;
      double d23 = 100.0;
      this.uNoise3SpeedAtInitialRotation = intervalNoiseSetting17.withInterval(1.0, d23, d22, d21, (byte)1).visibleWhen(condition);
      this.uNoise3LargeRotation = new DoubleValue(uNoise3SettingContext.getModule(), "UNoise3LargeRotation", 30.0, 20.0, 180.0, 1, doubleValue).named("How many degrees difference do count as large rotation?").visibleWhen(condition);
      this.uNoise3MouseSimulator = new UNoise3MouseSimulator(uNoise3SettingContext.withParentGroup(doubleValue));
      this.uNoise3Aimspeed.addDefaultLayer();
      this.uNoise3DecelerationFactorPre.addDefaultLayer();
      this.uNoise3DecelerationFactorPost.addDefaultLayer();
      this.uNoise3SpeedAtLargeRotation.addDefaultLayer();
      this.uNoise3SpeedAtInitialRotation.addDefaultLayer();
      this.uNoise3DecelerationFactorPostHighspeed.addDefaultLayer();
      this.uNoise3ResyncChanceAtAimgoal = new DoubleValue(uNoise3SettingContext.getModule(), "UNoise3ResyncChanceAtAimgoal", 0.0, 0.0, 100.0, 0, doubleValue).named("Resync at aimgoal").visibleWhen(condition);
      this.uNoise3ResyncUpdateDelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext.getModule(), "UNoise3ResyncUpdateDelay", 1.0, 1.0, 1.0, 5.0, 0, doubleValue).withDisplayNamePrefix("Resync recalculate delay")).withVisibility(condition);
      this.uNoise3RemoveNoiseChance = new DoubleValue(uNoise3SettingContext.getModule(), "UNoise3RemoveNoiseChance", 0.0, 0.0, 100.0, 0, doubleValue).named("Apply no noise when near target chance").visibleWhen(condition);
      this.uNoise3RecalcRemoveNoise = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext.getModule(), "UNoise3RecalcRemoveNoise", 1.0, 5.0, 1.0, 8.0, 0, doubleValue).withDisplayNamePrefix("No noise recalc delay")).withVisibility(condition);
      this.uNoise3resyncdelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(uNoise3SettingContext.getModule(), "UNoise3resyncdelay", 0.0, 0.0, 0.0, 1000.0, 0, doubleValue).withDisplayNamePrefix("Resync delay")).withoutNotInARow().withVisibility(condition);
      this.uNoise3MouseSimulator.onGoalReached = () -> this.onAimGoalReached();
      this.uNoise3MouseSimulator.onResyncTriggered = () -> this.scheduleResync();
      this.targetChangedSupplier = supplier;
   }

   public void resetAdvancedState(double d, double d2) {
      this.simulatedYaw = d;
      this.simulatedPitch = d2;
      this.actualYaw = d;
      this.actualPitch = d2;
      this.uNoise3MouseSimulator.setAcceleration(this.uNoise3SpeedAtInitialRotation.sample() * 20.0, false);
      this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
      this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
      this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
      this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue();
      this.largeRotationSpeed = this.uNoise3SpeedAtLargeRotation.sample() * 20.0;
   }

   private boolean isAdvancedMode() {
      return this.rotationEngineMode.getSelectedValue().equals("Advanced");
   }

   public Rotation computeIndependentRotation(float f, float f2, float f3, float f4, float f5, float f6) {
      f = RotationUtil.updateAngle(f3, f, f5);
      f2 = RotationUtil.updateAngle(f4, f2, f6);
      return new Rotation(f, f2);
   }

   private static double[] sampleSinusoidalArcPoint(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      double d8 = d3 - d;
      double d9 = d4 - d2;
      double d10 = Math.sqrt(d8 * d8 + d9 * d9);
      d5 = Math.min(d5, d10);
      double d11 = d5 / d10;
      double d12 = d + d11 * d8;
      double d13 = d2 + d11 * d9;
      double d14 = -d9 / d10;
      double d15 = d8 / d10;
      double d16 = d6 * Math.sin(d7 * d11 * Math.PI);
      double d17 = d12 + d16 * d14;
      double d18 = d13 + d16 * d15;
      return new double[]{d17, d18};
   }

   public static boolean haveSameSign(double d, double d2) {
      return d > 0.0 && d2 > 0.0 || d < 0.0 && d2 < 0.0;
   }

   private void onAimGoalReached() {
      if (this.shouldResyncAtGoal) {
         this.scheduleResync();
      }

      this.uNoise3MouseSimulator.setAcceleration(this.uNoise3Aimspeed.sample() * 20.0, false);
      this.uNoise3MouseSimulator.clearAccelerationCap();
      this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
      this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
      this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
      this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue();
      this.largeRotationSpeed = this.uNoise3SpeedAtLargeRotation.sample() * 20.0;
   }

   private static double computeMaxLinearStep(float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = f - f3;
      float f8 = f2 - f4;
      float f9 = Math.min(Math.abs(f7), f5);
      float f11 = f4 + Math.min(Math.abs(f8), f6) * (float)(f8 > 0.0F ? 1 : -1);
      float f12 = f3 + f9 * (float)(f7 > 0.0F ? 1 : -1);
      double d = interpolateXAtY((double)f3, (double)f4, (double)f, (double)f2, (double)f11);
      double d2 = interpolateYAtX((double)f3, (double)f4, (double)f, (double)f2, (double)f12);
      return Math.min(distance2D((double)f3, (double)f4, d, (double)f11), distance2D((double)f3, (double)f4, (double)f12, d2));
   }

   private void updatePreviousTarget(float f, float f2, boolean flag) {
      if (flag || (Boolean)this.targetChangedSupplier.get() || !Float.isFinite(this.previousTargetYaw)) {
         this.previousTargetYaw = f;
         this.previousTargetPitch = f2;
      }
   }

   private Rotation applyRandomJitter(Rotation rotation, float f, float f2) {
      Rotation rotation2 = new Rotation(rotation);
      if ((double)Math.abs(rotation2.yaw - f) > 0.16 || (double)Math.abs(rotation2.pitch - f2) > 0.16 || !this.rotationEngineRandomizeOnlyWhileRotating.getValue()) {
         rotation2.yaw = rotation2.yaw + (float)this.rotationEngineRandomStrength.nextValue(true) * (float)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
         rotation2.pitch = rotation2.pitch + (float)this.rotationEngineRandomStrength.nextValue(true) * (float)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
      }

      return rotation2;
   }

   public Rotation rotateTowards(float f, float f2, float f3, float f4) {
      f = RotationUtil.updateAngle(f3, f, 180.0F);
      f2 = RotationUtil.updateAngle(f4, f2, 180.0F);
      this.updatePreviousTarget(f, f2, false);
      Rotation rotation = this.computeTurnSpeeds(f, f2, f3, f4);
      Rotation rotation2 = null;
      String selectedValue = this.rotationEngineMode.getSelectedValue();

      switch (selectedValue) {
         case "Independent":
            float pitch = rotation.pitch;
            rotation2 = this.computeIndependentRotation(f, f2, f3, f4, rotation.yaw, pitch);
            rotation2 = this.applyRandomJitter(rotation2, f, f2);
            break;
         case "Linear":
            float pitch2 = rotation.pitch;
            rotation2 = this.computeLinearRotation(f, f2, f3, f4, rotation.yaw, pitch2);
            rotation2 = this.applyRandomJitter(rotation2, f, f2);
            break;
         case "Curve":
            rotation2 = this.computeCurvedRotation(f, f2, f3, f4, rotation.yaw, rotation.pitch, (int)this.rotationEngineInterval.getValue(true), (float)this.rotationEngineAmplitude.getValue(true), (float)this.rotationEngineFrequency.getValue(true));
            break;
         case "Advanced":
            if (this.lastAdvancedTick + 1 != Minecraft.getMinecraft().thePlayer.ticksExisted) {
               this.resetAdvancedState(MathHelper.wrapAngleTo180_double((double)f3), (double)f4);
            }

            if (EntityBoundsUtil.getShortestAngleDistance(MathHelper.wrapAngleTo180_double((double)f3), this.actualYaw) > 0.15) {
               this.actualYaw = this.simulatedYaw = MathHelper.wrapAngleTo180_double((double)f3);
            }

            if (Math.abs((double)f4 - this.actualPitch) > 0.15) {
               this.actualPitch = (double)f4;
               this.simulatedYaw = (double)f4;
            }

            if (this.resyncRecalcTicks == 0) {
               this.shouldResyncAtGoal = Math.random() <= this.uNoise3ResyncChanceAtAimgoal.getValue(true) / 100.0;
               this.resyncRecalcTicks = this.uNoise3ResyncUpdateDelay.nextInt(true);
            } else if (this.resyncRecalcTicks > 0) {
               this.resyncRecalcTicks--;
            }

            if (this.removeNoiseRecalcTicks == 0) {
               this.uNoise3MouseSimulator.disableNoiseNearTarget = Math.random() <= this.uNoise3RemoveNoiseChance.getValue(true) / 100.0;
               this.removeNoiseRecalcTicks = this.uNoise3RecalcRemoveNoise.nextInt(true);
            } else if (this.removeNoiseRecalcTicks > 0) {
               this.removeNoiseRecalcTicks--;
            }

            double d6 = MathHelper.wrapAngleTo180_double((double)f);
            MutableDoubleRef mutableDoubleRef = new MutableDoubleRef(EntityBoundsUtil.getShortestSignedAngleDelta(this.simulatedYaw, d6));
            MutableDoubleRef mutableDoubleRef2 = new MutableDoubleRef((double)f2 - this.simulatedPitch);
            double d7 = 0.0;
            if (Math.sqrt(mutableDoubleRef.getValue() * mutableDoubleRef.getValue() + mutableDoubleRef2.getValue() * mutableDoubleRef2.getValue()) > this.uNoise3LargeRotation.getValue(true)) {
               double largeRotationSpeed2 = this.largeRotationSpeed;
               this.uNoise3MouseSimulator.setAcceleration(largeRotationSpeed2, false);
            }

            UNoise3MouseSimulator uNoise3MouseSimulator3 = this.uNoise3MouseSimulator;
            double d9 = d7 / 0.15;
            float var3f = f2;
            MouseSimulationDelta mouseSimulationDelta = uNoise3MouseSimulator3.simulateTickSubsteps(mutableDoubleRef, mutableDoubleRef2, d9, (byte)3, (n2, d10, d11) -> this.onMouseSimulationSubstep(mutableDoubleRef, d6, mutableDoubleRef2, var3f, n2, d10, d11));
            this.uNoise3Aimspeed.stepLayers();
            this.uNoise3SpeedAtLargeRotation.stepLayers();
            this.uNoise3SpeedAtInitialRotation.stepLayers();
            this.uNoise3DecelerationFactorPre.stepLayers();
            this.uNoise3DecelerationFactorPost.stepLayers();
            this.uNoise3DecelerationFactorPostHighspeed.stepLayers();
            double d13 = mouseSimulationDelta.noisyDelta.x * 0.15;
            double d14 = mouseSimulationDelta.noisyDelta.y * 0.15;
            this.simulatedYaw = MathHelper.wrapAngleTo180_double(this.simulatedYaw + mouseSimulationDelta.baseDelta.x * 0.15);
            this.simulatedPitch = MathHelper.clamp_double(this.simulatedPitch + mouseSimulationDelta.baseDelta.y * 0.15, -90.0, 90.0);
            this.actualYaw = MathHelper.wrapAngleTo180_double(this.actualYaw + d13);
            this.actualPitch = MathHelper.clamp_double(this.actualPitch + d14, -90.0, 90.0);
            rotation2 = new Rotation((float)this.actualYaw, (float)this.actualPitch);
      }

      this.updatePreviousTarget(f, f2, true);
      return rotation2 == null ? new Rotation(f3, f4) : RotationUtil.applyGcdFix(rotation2.yaw, rotation2.pitch, f3, f4);
   }

   private static double distance2D(double d, double d2, double d3, double d4) {
      return Math.sqrt(Math.pow(d3 - d, 2.0) + Math.pow(d4 - d2, 2.0));
   }

   public Rotation computeLinearRotation(float f, float f2, float f3, float f4, float f5, float f6) {
      f = RotationUtil.updateAngle(f3, f, 180.0F);
      f2 = RotationUtil.updateAngle(f4, f2, 180.0F);
      double[] doubles = stepAlongLine((double)f3, (double)f4, (double)f, (double)f2, computeMaxLinearStep(f, f2, f3, f4, f5, f6));
      return new Rotation((float)doubles[0], (float)doubles[1]);
   }

   private void onMouseSimulationSubstep(MutableDoubleRef mutableDoubleRef, double d, MutableDoubleRef mutableDoubleRef2, float f, int n, double d2, double d3) {
      this.tickResyncTimer();
      this.processQueuedResync();
      if (this.resyncPending) {
         this.simulatedYaw = this.actualYaw;
         this.simulatedPitch = this.actualPitch;
         mutableDoubleRef.setValue(EntityBoundsUtil.getShortestSignedAngleDelta(this.simulatedYaw, d));
         mutableDoubleRef2.setValue((double)f - this.simulatedPitch);
         this.resyncPending = false;
      }
   }

   private static double interpolateYAtX(double d, double d2, double d3, double d4, double d5) {
      return ((d4 - d2) / (d3 - d)) * (d5 - d) + d2;
   }

   public float computeAcceleratedSpeed(RandomizedRangeSettings randomizedRangeSettings, float f, float f2, float f3, float f4, float f5, boolean flag) {
      float f6 = (float)randomizedRangeSettings.nextDouble();
      double d2 = randomizedRangeSettings.getMinValue() + (randomizedRangeSettings.getMaxValue() - randomizedRangeSettings.getMinValue()) / 2.0;
      float f7 = f - f2;
      double d3 = flag ? (double)Math.abs(f4 - f) : 0.0;
      double d4 = (double)Math.abs(f7 / Math.max(f3, 1.0F));
      double d5 = Math.abs(((double)f3 - d3) / d2);
      return MathHelper.clamp_float(Math.abs(f3) + (d4 > d5 ? f6 : -f6), 0.0F, f5);
   }

   private void processQueuedResync() {
      if (this.resyncDelayTicks == 0 && this.resyncQueued) {
         this.triggerResync();
      }
   }

   private static double[] stepAlongLine(double d, double d2, double d3, double d4, double d5) {
      double d6 = d3 - d;
      double d7 = d4 - d2;
      double d8 = Math.sqrt(d6 * d6 + d7 * d7);
      d5 = Math.min(d5, d8);
      double d9 = d5 / d8;
      double d10 = d + d9 * d6;
      double d11 = d2 + d9 * d7;
      return new double[]{d10, d11};
   }

   public RotationEngine(Module module, SettingGroup doubleValue) {
       this(module, doubleValue, () -> false);
   }

   private void triggerResync() {
      this.resyncDelayTicks = (int)((double)this.uNoise3resyncdelay.nextInt(true) / 16.6);
      this.resyncQueued = false;
      this.resyncPending = true;
   }

   private boolean isAmplitudeVisible() {
      return this.rotationEngineMode.getSelectedValue().equals("Curve");
   }

   private static double interpolateXAtY(double d, double d2, double d3, double d4, double d5) {
      double d6 = (d4 - d2) / (d3 - d);
      return (d5 - d2) / d6 + d;
   }

   private Rotation computeTurnSpeeds(float f, float f2, float f3, float f4) {
      Rotation lastTickRotation = ClientManagers.rotationController.getLastTickRotation();
      Rotation rotation = ClientManagers.rotationController.getPreviousRotation().absDelta(lastTickRotation);
      float f9 = this.computeAcceleratedSpeed(this.rotationEngineYawAcceleration, f, f3, rotation.yaw, this.previousTargetYaw, (float)this.rotationEngineYawSpeed.nextValue(), this.rotationEngineBetterAcceleration.getValue());
      float f13 = this.computeAcceleratedSpeed(this.rotationEnginePitchAcceleration, f2, f4, rotation.pitch, this.previousTargetPitch, (float)this.rotationEnginePitchSpeed.nextValue(), this.rotationEngineBetterAcceleration.getValue());
      return new Rotation(f9, f13);
   }

   private void tickResyncTimer() {
      if (this.resyncDelayTicks > 0) {
         this.resyncDelayTicks--;
      }
   }

}
