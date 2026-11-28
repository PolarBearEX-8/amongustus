package net.augustus.modules.modes.stealer;

import net.augustus.modules.modes.killaura.unoise3.MouseSimulationDelta;
import net.augustus.modules.modes.killaura.unoise3.MouseSimulationStepCallback;
import net.augustus.modules.modes.killaura.unoise3.UNoise3MouseSimulator;
import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.NoiseLayerBuilder;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.MutableDoubleRef;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.ContainerUtil;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.player.Stealer;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class StealerMouseSimulationMode extends AbstractStealerMouseMode {
   private Vec2d actualCursorPos;
   private Vec2d baseCursorPos;
   private UNoise3MouseSimulator uNoise3MouseSimulator;
   private IntervalNoiseSetting uNoise3Aimspeed;
   private IntervalNoiseSetting uNoise3DecelerationFactorPre;
   private IntervalNoiseSetting uNoise3DecelerationFactorPost;
   private IntervalNoiseSetting uNoise3DecelerationFactorPostHighspeed;
   private DoubleValue uNoise3highspeed;
   private RandomizedRangeSettings uNoise3resyncdelay;
   private int resyncCooldownSteps;
   private boolean resyncPending;
   private boolean resyncQueued;
   private double cachedAimSpeed;
   private boolean resyncEnabled;
   private int resyncCount;
   private int stepCount;

   public StealerMouseSimulationMode(Stealer stealer, SettingGroup doubleValue) {
      super(stealer, doubleValue, "Mouse Simulation");
      this.resyncEnabled = true;
      this.resyncCount = 0;
      this.stepCount = 0;
      IntervalNoiseSetting intervalNoiseSetting2 = new IntervalNoiseSetting(stealer, "UNoise3Aimspeed", doubleValue, true).named("Aim speed");
      double d = 20.0;
      double d2 = 15.0;
      double d3 = 100.0;
      this.uNoise3Aimspeed = intervalNoiseSetting2.withInterval(1.0, d3, d2, d, (byte)1);
      IntervalNoiseSetting intervalNoiseSetting4 = new IntervalNoiseSetting(stealer, "UNoise3DecelerationFactorPre", doubleValue, false).named("Decelerationfactor Pre");
      double d5 = 1.0;
      double d6 = 1.0;
      double d7 = 2.0;
      this.uNoise3DecelerationFactorPre = intervalNoiseSetting4.withInterval(0.5, d7, d6, d5, (byte)2);
      IntervalNoiseSetting intervalNoiseSetting6 = new IntervalNoiseSetting(stealer, "UNoise3DecelerationFactorPost", doubleValue, false).named("Decelerationfactor Post");
      double d9 = 1.0;
      double d10 = 1.0;
      double d11 = 8.0;
      this.uNoise3DecelerationFactorPost = intervalNoiseSetting6.withInterval(0.5, d11, d10, d9, (byte)2);
      IntervalNoiseSetting intervalNoiseSetting8 = new IntervalNoiseSetting(stealer, "UNoise3DecelerationFactorPostHighspeed", doubleValue, false).named("Decelerationfactor Post at highspeed");
      double d13 = 2.0;
      double d14 = 2.0;
      double d15 = 8.0;
      this.uNoise3DecelerationFactorPostHighspeed = intervalNoiseSetting8.withInterval(0.5, d15, d14, d13, (byte)2);
      this.uNoise3highspeed = new DoubleValue(stealer, "UNoise3highspeed", 10.0, 5.0, 20.0, 1, doubleValue).named("How many slots per second is high speed?");
      UNoise3SettingContext uNoise3SettingContext = new UNoise3SettingContext(stealer, doubleValue, () -> this.isSelected());
      this.uNoise3MouseSimulator = new UNoise3MouseSimulator(uNoise3SettingContext);
      ArrayList layers = this.uNoise3Aimspeed.getLayers();
      layers.add(this.uNoise3Aimspeed.newLayerBuilder().withStepDeltaMs(16.6F).build());
      layers = this.uNoise3DecelerationFactorPre.getLayers();
      layers.add(this.uNoise3DecelerationFactorPre.newLayerBuilder().withStepDeltaMs(16.6F).build());
      layers = this.uNoise3DecelerationFactorPost.getLayers();
      layers.add(this.uNoise3DecelerationFactorPost.newLayerBuilder().withStepDeltaMs(16.6F).build());
      layers = this.uNoise3DecelerationFactorPostHighspeed.getLayers();
      layers.add(this.uNoise3DecelerationFactorPostHighspeed.newLayerBuilder().withStepDeltaMs(16.6F).build());
      this.uNoise3resyncdelay = ((RandomizedRangeSettings)new RandomizedRangeSettings(stealer, "UNoise3resyncdelay", 0.0, 0.0, 0.0, 1000.0, 0, doubleValue).withDisplayNamePrefix("Resync delay")).withoutNotInARow();
      this.baseCursorPos = new Vec2d(0.0, 0.0);
      this.actualCursorPos = new Vec2d(0.0, 0.0);
      IntervalNoiseSetting uNoise3Aimspeed2 = this.uNoise3Aimspeed;
      SettingVisibilityCondition condition = () -> this.isSelected();
      uNoise3Aimspeed2.visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting9 = this.uNoise3DecelerationFactorPre;
      condition = () -> this.isSelected();
      intervalNoiseSetting9.visibleWhen(condition);
      IntervalNoiseSetting intervalNoiseSetting10 = this.uNoise3DecelerationFactorPost;
      SettingVisibilityCondition condition4 = () -> this.isSelected();
      intervalNoiseSetting10.visibleWhen(condition4);
      IntervalNoiseSetting intervalNoiseSetting11 = this.uNoise3DecelerationFactorPostHighspeed;
      condition4 = () -> this.isSelected();
      intervalNoiseSetting11.visibleWhen(condition4);
      this.uNoise3highspeed.visibleWhen(() -> this.isSelected());
      this.uNoise3resyncdelay.withVisibility(() -> this.isSelected());
      this.uNoise3MouseSimulator.degreesPerStep = 1.0;
      this.uNoise3MouseSimulator.onGoalReached = () -> this.refreshSimulatorParameters();
      this.uNoise3MouseSimulator.onResyncTriggered = () -> this.requestResync();
   }

   @Override
   public Vec2d computeNextCursorPos(Container container, List list) {
      Slot slot = (Slot)list.get(0);
      double slotCenterX = ContainerUtil.getSlotCenterX(slot);
      double slotCenterY = ContainerUtil.getSlotCenterY(container, slot);
      MutableDoubleRef mutableDoubleRef = new MutableDoubleRef(slotCenterX - this.baseCursorPos.x);
      MutableDoubleRef mutableDoubleRef2 = new MutableDoubleRef(slotCenterY - this.baseCursorPos.y);
      this.processQueuedResync();
      UNoise3MouseSimulator uNoise3MouseSimulator2 = this.uNoise3MouseSimulator;
      MouseSimulationStepCallback callback = (n, d, d2) -> {
         this.tickResyncCooldown();
      };
      MouseSimulationDelta mouseSimulationDelta = uNoise3MouseSimulator2.simulateTickSubsteps(mutableDoubleRef, mutableDoubleRef2, 0.0, (byte)1, callback);
      Vec2d noisyDelta = mouseSimulationDelta.noisyDelta;
      this.actualCursorPos = this.actualCursorPos.add(noisyDelta);
      Vec2d baseDelta = mouseSimulationDelta.baseDelta;
      this.baseCursorPos = this.baseCursorPos.add(baseDelta);
      this.uNoise3Aimspeed.stepLayers();
      this.uNoise3DecelerationFactorPre.stepLayers();
      this.uNoise3DecelerationFactorPost.stepLayers();
      this.uNoise3DecelerationFactorPostHighspeed.stepLayers();
      return this.actualCursorPos;
   }

   private void performResync() {
      this.resyncCooldownSteps = (int)((double)this.uNoise3resyncdelay.nextInt(true) / 16.6);
      this.resyncQueued = false;
      this.actualCursorPos = this.baseCursorPos;
      this.resyncPending = false;
   }

   private void tickResyncCooldown() {
      if (this.resyncCooldownSteps > 0) {
         this.resyncCooldownSteps--;
      }
   }

   private void processQueuedResync() {
      if (this.resyncCooldownSteps == 0 && this.resyncQueued) {
         this.performResync();
      }
   }

   @Override
   public void resetCursorPosition() {
      ScaledResolution sr = new ScaledResolution(mc);
      this.actualCursorPos = this.baseCursorPos = new Vec2d(sr.getScaledWidth_double() / 2.0, sr.getScaledHeight_double() / 2.0);
      this.uNoise3MouseSimulator.setAcceleration(this.uNoise3Aimspeed.sample(), false);
      this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
      this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
      this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
      this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue() * 19.0 / 60.0;
      this.uNoise3MouseSimulator.currentVelocity = new Vec2d(0.0, 0.0);
   }

   private void requestResync() {
      if (this.resyncCooldownSteps > 0) {
         this.resyncQueued = true;
      } else {
         this.performResync();
      }
   }

   @Override
   public void onTargetSlotChanged() {
      this.uNoise3MouseSimulator.onGoalReached.run();
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.resetCursorPosition();
   }

   @Override
   public void onDisable() {
   }

   private void refreshSimulatorParameters() {
      this.requestResync();
      this.uNoise3MouseSimulator.setAcceleration(this.uNoise3Aimspeed.sample(), false);
      this.uNoise3MouseSimulator.clearAccelerationCap();
      this.uNoise3MouseSimulator.preDecelerationFactor = this.uNoise3DecelerationFactorPre.sample();
      this.uNoise3MouseSimulator.postDecelerationFactor = this.uNoise3DecelerationFactorPost.sample();
      this.uNoise3MouseSimulator.postHighSpeedDecelFactor = this.uNoise3DecelerationFactorPostHighspeed.sample();
      this.uNoise3MouseSimulator.highSpeedThresholdDeg = this.uNoise3highspeed.getValue() * 19.0 / 60.0;
   }

}
