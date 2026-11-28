package net.augustus.modules.latency;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import net.augustus.modules.Module;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.util.MathHelper;

public class PingDelayController {
   private final AtomicDouble decelerationStartDelay;
   private final TimeHelper accelerationApplyTimer;
   private final StringValue pingAccelerationMode;
   private final TimeHelper recalculateTimer;
   private final RandomizedRangeSettings pingrecalculatedelayMs;
   private final RandomizedRangeSettings pingAccelerationPerSecond;
   private final TimeHelper decelerationTimer;
   private final AtomicInteger cachedDelayMs;
   private final RandomizedRangeSettings pingDecelerationTime;
   private final RandomizedRangeSettings ping;
   private final RandomizedRangeSettings pingAccelerationRecalculateDelay;
   private final AtomicBoolean accelerating;
   private final AtomicBoolean decelerating;
   private final TimeHelper accelerationRecalculateTimer;
   private final AtomicDouble currentDelayMs;
   private final RandomizedRangeSettings pingAccelerationApplyDelay;

   public int getCurrentDelayMs() {
      int n = (int)this.currentDelayMs.get();
      this.cachedDelayMs.set(n);
      return n;
   }

   public void updateAsync() {
      if (this.decelerating.get()) {
         double d = (double)Math.max(this.pingDecelerationTime.getLastIntValue(), 1);
         double d2 = MathHelper.clamp_double(d - (double)this.decelerationTimer.getTime(), 0.0, d) / d;
         double d3 = this.decelerationStartDelay.get() * d2;
         this.currentDelayMs.set(d3);
      } else if (this.accelerating.get()) {
         TimeHelper timeHelper = this.accelerationRecalculateTimer;
         if (timeHelper.reached((long)this.pingAccelerationRecalculateDelay.getLastIntValue())) {
            this.accelerationRecalculateTimer.reset();
            this.pingAccelerationRecalculateDelay.nextValue();
            this.pingAccelerationPerSecond.nextValue();
         }

         timeHelper = this.accelerationApplyTimer;
         if (timeHelper.reached((long)this.pingAccelerationApplyDelay.getLastIntValue())) {
            this.accelerationApplyTimer.reset();
            this.pingAccelerationApplyDelay.nextValue();
            double d4 = Math.max(this.pingAccelerationApplyDelay.getMidpoint(), 1.0);
            double d6 = ((double)this.pingAccelerationPerSecond.getLastIntValue() / 1000.0) * d4;
            this.currentDelayMs.set(this.currentDelayMs.get() + d6);
         }

         if (this.currentDelayMs.get() >= (double)this.ping.getLastIntValue()) {
            this.accelerating.set(false);
            this.recalculateTimer.reset();
         }

         this.decelerationTimer.reset();
      } else {
         if (this.recalculateTimer.reached((long)this.pingrecalculatedelayMs.getLastIntValue())) {
            this.recalculateTimer.reset();
            this.pingrecalculatedelayMs.nextValue();
            this.ping.nextValue();
            this.currentDelayMs.set((double)this.ping.getLastIntValue());
         }

         this.decelerationTimer.reset();
      }
   }

   private boolean isAccelerationRecalculateDelayVisible() {
      return !this.pingAccelerationMode.getSelectedValue().equals("Instant");
   }

   private boolean isAccelerationApplyDelayVisible() {
      return !this.pingAccelerationMode.getSelectedValue().equals("Instant");
   }

   public void resetAccelerationState() {
      this.accelerating.set(!this.pingAccelerationMode.getSelectedValue().equals("Instant"));
   }

   public int stepDecelerationAndGetDelay() {
      if (this.decelerating.compareAndSet(false, true)) {
         this.pingDecelerationTime.nextValue();
         this.decelerationStartDelay.set((double)this.cachedDelayMs.get());
      }

      if (this.currentDelayMs.get() <= 0.0) {
         this.currentDelayMs.set(0.0);
         this.resetAccelerationState();
         return -1;
      } else {
         return this.getCurrentDelayMs();
      }
   }

   public double getConfiguredMaxDelay() {
      return this.ping.getMaxValue();
   }

   public PingDelayController(Module module, String str, SettingGroup doubleValue) {
       super();
      this.accelerationRecalculateTimer = new TimeHelper();
      this.recalculateTimer = new TimeHelper();
      this.accelerationApplyTimer = new TimeHelper();
      this.decelerationTimer = new TimeHelper();
      this.currentDelayMs = new AtomicDouble(0.0);
      this.decelerationStartDelay = new AtomicDouble(0.0);
      this.accelerating = new AtomicBoolean(false);
      this.decelerating = new AtomicBoolean(false);
      this.cachedDelayMs = new AtomicInteger(0);
      this.ping = (new RandomizedRangeSettings(module, str + "Ping", 100.0, 200.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(module, str + "PingRecalculateDelay (ms)", 400.0, 600.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      this.pingrecalculatedelayMs = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("PingRecalculateDelay (ms)");
      this.pingAccelerationMode = (StringValue)new StringValue(module, str + "PingAccelerationMode", "Instant", new String[]{"Instant", "Smooth"}, doubleValue).withDisplayName("PingAccelerationMode");
      randomizedRangeSettings = (new RandomizedRangeSettings(module, str + "PingAccelerationPerSecond", 20.0, 200.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("PingAccelerationPerSecond");
      this.pingAccelerationPerSecond = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isAccelerationRateVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, str + "PingAccelerationApplyDelay", 5.0, 10.0, 0.0, 100.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("PingAccelerationApplyDelay");
      this.pingAccelerationApplyDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isAccelerationApplyDelayVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, str + "PingAccelerationRecalculateDelay", 20.0, 200.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("PingAccelerationRecalculateDelay");
      this.pingAccelerationRecalculateDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isAccelerationRecalculateDelayVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, str + "PingDecelerationTime", 35.0, 45.0, 0.0, 100.0, 0, doubleValue)).withoutNotInARow();
      this.pingDecelerationTime = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("PingDecelerationTime");
   }

   public int resumeAndGetDelay() {
      if (this.decelerating.get()) {
         this.resetAccelerationState();
         this.decelerating.set(false);
      }

      return this.getCurrentDelayMs();
   }

   private boolean isAccelerationRateVisible() {
      return !this.pingAccelerationMode.getSelectedValue().equals("Instant");
   }

}
