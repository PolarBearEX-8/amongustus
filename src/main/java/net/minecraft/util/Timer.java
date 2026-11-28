package net.minecraft.util;

import net.augustus.events.EventTimerUpdate;
import net.augustus.events.listeners.TimerUpdateListener;
import net.minecraft.client.Minecraft;

public class Timer {
   public float ticksPerSecond;
   private double lastHRTime;
   public int elapsedTicks;
   public float renderPartialTicks;
   public float timerSpeed = 1.0F;
   public float elapsedPartialTicks;
   private long lastSyncSysClock;
   private long lastSyncHRClock;
   private long counter;
   private double timeSyncAdjustment = 1.0;

   public void updateTimer() {
      long systemTime = Minecraft.getSystemTime();
      long l = systemTime - this.lastSyncSysClock;
      long l2 = System.nanoTime() / 1000000L;
      double d = (double)l2 / 1000.0;
      if (l <= 1000L && l >= 0L) {
         this.counter += l;
         if (this.counter > 1000L) {
            long l3 = l2 - this.lastSyncHRClock;
            double d2 = (double)this.counter / (double)l3;
            this.timeSyncAdjustment = this.timeSyncAdjustment + (d2 - this.timeSyncAdjustment) * (double)0.2F;
            this.lastSyncHRClock = l2;
            this.counter = 0L;
         }

         if (this.counter < 0L) {
            this.lastSyncHRClock = l2;
         }
      } else {
         this.lastHRTime = d;
      }

      this.lastSyncSysClock = systemTime;
      double d3 = (d - this.lastHRTime) * this.timeSyncAdjustment;
      this.lastHRTime = d;
      d3 = MathHelper.clamp_double(d3, 0.0, 1.0);
      this.elapsedPartialTicks = (float)((double)this.elapsedPartialTicks + d3 * (double)this.timerSpeed * (double)this.ticksPerSecond);
      EventTimerUpdate event = TimerUpdateListener.onTimerUpdate(this.elapsedPartialTicks, this.elapsedTicks);
      this.elapsedPartialTicks = event.getElapsedPartialTicks();
      this.elapsedTicks = event.getElapsedTicks();
      this.elapsedTicks = (int)this.elapsedPartialTicks;
      this.elapsedPartialTicks = this.elapsedPartialTicks - (float)this.elapsedTicks;
      if (this.elapsedTicks > 10) {
         this.elapsedTicks = 10;
      }

      this.renderPartialTicks = this.elapsedPartialTicks;
   }

   public void copyFrom(Timer timer) {
      this.ticksPerSecond = timer.ticksPerSecond;
      this.elapsedTicks = timer.elapsedTicks;
      this.renderPartialTicks = timer.renderPartialTicks;
      this.elapsedPartialTicks = timer.elapsedPartialTicks;
      this.lastHRTime = timer.lastHRTime;
      this.lastSyncHRClock = timer.lastSyncHRClock;
      this.lastSyncSysClock = timer.lastSyncSysClock;
      this.counter = timer.counter;
      this.timerSpeed = timer.timerSpeed;
      this.timeSyncAdjustment = timer.timeSyncAdjustment;
   }

   public Timer(float f) {
      this.ticksPerSecond = f;
      this.lastSyncSysClock = Minecraft.getSystemTime();
      this.lastSyncHRClock = System.nanoTime() / 1000000L;
   }
}
