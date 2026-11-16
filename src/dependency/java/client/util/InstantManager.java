package client.util;

import client.module.Module;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.setting.SettingGroup;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.util.MathHelper;

public class InstantManager {
   public final AtomicBoolean atomicBoolean;
   public final RangeSetting settingPingRecalculateDelayMs;
   public final ModeSetting settingPingAccelerationMode;
   public final AtomicBoolean atomicBoolean2;
   public final TimeHelper timer;
   public final RangeSetting settingPingAccelerationPerSecond;
   public final AtomicDouble atomicDouble2;
   public final RangeSetting settingPing;
   public final TimeHelper timer2;
   public final TimeHelper timer3;
   public final RangeSetting settingPingDecelerationTime;
   public final RangeSetting settingPingAccelerationRecalculateDelay;
   public final AtomicInteger atomicInteger;
   public final TimeHelper timer4;
   public final AtomicDouble atomicDouble;
   public final RangeSetting settingPingAccelerationApplyDelay;

   public double getDouble() {
      return this.settingPing.getDouble2();
   }

   public void updateState() {
      this.atomicBoolean2.set(!this.settingPingAccelerationMode.getSelected().equals("Instant"));
   }

   private boolean isActive() {
      return !this.settingPingAccelerationMode.getSelected().equals("Instant");
   }

   private boolean isActiveAsFlag() {
      return !this.settingPingAccelerationMode.getSelected().equals("Instant");
   }

   public int getInt() {
      if (this.atomicBoolean.get()) {
         this.updateState();
         this.atomicBoolean.set(false);
      }

      return this.getCount();
   }

   public void updateState2() {
      if (this.atomicBoolean.get()) {
         double offset = (double)Math.max(this.settingPingDecelerationTime.getIndexOffset2(), 1);
         double offset2 = MathHelper.getDouble7(offset - (double)this.timer3.getTime(), 0.0, offset) / offset;
         double offset3 = this.atomicDouble.get() * offset2;
         this.atomicDouble2.set(offset3);
      } else if (this.atomicBoolean2.get()) {
         TimeHelper timer5 = this.timer2;
         long indexOffset = (long)this.settingPingAccelerationRecalculateDelay.getIndexOffset2();
         if (timer5.hasReached(indexOffset)) {
            this.timer2.reset();
            this.settingPingAccelerationRecalculateDelay.recalculate();
            this.settingPingAccelerationPerSecond.recalculate();
         }

         timer5 = this.timer;
         long indexOffset2 = (long)this.settingPingAccelerationApplyDelay.getIndexOffset2();
         if (timer5.hasReached(indexOffset2)) {
            this.timer.reset();
            this.settingPingAccelerationApplyDelay.recalculate();
            double offset4 = Math.max(this.settingPingAccelerationApplyDelay.getValue(), 1.0);
            double offset5 = (double)this.settingPingAccelerationPerSecond.getIndexOffset2() / 1000.0 * offset4;
            this.atomicDouble2.set(this.atomicDouble2.get() + offset5);
         }

         if (this.atomicDouble2.get() >= (double)this.settingPing.getIndexOffset2()) {
            this.atomicBoolean2.set(false);
            this.timer4.reset();
         }

         this.timer3.reset();
      } else {
         TimeHelper timer6 = this.timer4;
         long indexOffset3 = (long)this.settingPingRecalculateDelayMs.getIndexOffset2();
         if (timer6.hasReached(indexOffset3)) {
            this.timer4.reset();
            this.settingPingRecalculateDelayMs.recalculate();
            this.settingPing.recalculate();
            this.atomicDouble2.set((double)this.settingPing.getIndexOffset2());
         }

         this.timer3.reset();
      }
   }

   private boolean isActive2() {
      return !this.settingPingAccelerationMode.getSelected().equals("Instant");
   }

   public int getInt2() {
      if (!this.atomicBoolean.get()) {
         this.settingPingDecelerationTime.recalculate();
         this.atomicDouble.set((double)this.atomicInteger.get());
      }

      this.atomicBoolean.set(true);
      if (this.atomicDouble2.get() <= 0.0) {
         this.atomicDouble2.set(0.0);
         this.updateState();
         return -1;
      } else {
         return this.getCount();
      }
   }

   private int getCount() {
      this.atomicInteger.set((int)this.atomicDouble2.get());
      return (int)this.atomicDouble2.get();
   }

   public InstantManager(Module module, String text, SettingGroup settingGroup) {
        super();
      long value = 0x3b0decbee796L;
      int count = (int)((0x3801ea48bab7L ^ value ^ 0x38e30b128e26L) >>> 32);
      
      this.timer2 = new TimeHelper();
      this.timer4 = new TimeHelper();
      this.timer = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.atomicDouble2 = new AtomicDouble(0.0);
      this.atomicDouble = new AtomicDouble(0.0);
      this.atomicBoolean2 = new AtomicBoolean(false);
      this.atomicBoolean = new AtomicBoolean(false);
      this.atomicInteger = new AtomicInteger(0);
      this.settingPing = (new RangeSetting(module, text + "Ping", 100.0, 200.0, 0.0, 2000.0, 0, settingGroup)).getRangeSetting2();
      RangeSetting rangeSetting = (new RangeSetting(module, text + "PingRecalculateDelay (ms)", 400.0, 600.0, 0.0, 2000.0, 0, settingGroup)).getRangeSetting2();
      String text2 = "PingRecalculateDelay (ms)";
      this.settingPingRecalculateDelayMs = rangeSetting.getRangeSetting(text2);
      ModeSetting setting = new ModeSetting(module, text + "PingAccelerationMode", "Instant", new String[]{"Instant", "Smooth"}, settingGroup);
      String text3 = "PingAccelerationMode";
      this.settingPingAccelerationMode = setting.getModeSetting(text3);
      rangeSetting = (new RangeSetting(module, text + "PingAccelerationPerSecond", 20.0, 200.0, 0.0, 2000.0, 0, settingGroup)).getRangeSetting2();
      String text4 = "PingAccelerationPerSecond";
      rangeSetting = rangeSetting.getRangeSetting(text4);
      BooleanSupplier booleanSupplier = this::isActive2;
      this.settingPingAccelerationPerSecond = rangeSetting.getRangeSetting(count, booleanSupplier);
      rangeSetting = (new RangeSetting(module, text + "PingAccelerationApplyDelay", 5.0, 10.0, 0.0, 100.0, 0, settingGroup)).getRangeSetting2();
      String text5 = "PingAccelerationApplyDelay";
      rangeSetting = rangeSetting.getRangeSetting(text5);
      BooleanSupplier booleanSupplier2 = this::isActive;
      this.settingPingAccelerationApplyDelay = rangeSetting.getRangeSetting(count, booleanSupplier2);
      rangeSetting = (new RangeSetting(module, text + "PingAccelerationRecalculateDelay", 20.0, 200.0, 0.0, 2000.0, 0, settingGroup)).getRangeSetting2();
      String text6 = "PingAccelerationRecalculateDelay";
      rangeSetting = rangeSetting.getRangeSetting(text6);
      BooleanSupplier booleanSupplier3 = this::isActiveAsFlag;
      this.settingPingAccelerationRecalculateDelay = rangeSetting.getRangeSetting(count, booleanSupplier3);
      rangeSetting = (new RangeSetting(module, text + "PingDecelerationTime", 35.0, 45.0, 0.0, 100.0, 0, settingGroup)).getRangeSetting2();
      String text7 = "PingDecelerationTime";
      this.settingPingDecelerationTime = rangeSetting.getRangeSetting(text7);
   }
}
