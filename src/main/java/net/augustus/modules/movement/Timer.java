package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.SoundListener;
import net.augustus.events.EventSound;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public class Timer extends Module implements MotionListener, LoadWorldListener, SoundListener, PacketDelayListener {
   public final DoubleValue time;
   public final BooleanValue timerAfterVelocity;
   public final DoubleValue timerAfterVelocityVal;
   public final BooleanValue timerAfterFallDamage;
   public final DoubleValue timerAfterFallDamageVal;
   public final BooleanValue timerAtBlink;
   public final DoubleValue timerAtBlinkVal;
   private final TimeHelper timeHelper;
   private boolean lastHurtWasFallDamage;
   private boolean packetDelayed;

   public Timer() {
       super("Timer", "Zeitschaltuhr", new Color(180, 176, 119), Categorys.MOVEMENT);
      this.time = new DoubleValue(this, "Time", 1.0, 0.1, 10.0, 2);
      this.timerAfterVelocity = new BooleanValue(this, "TimerAfterVelocity", false);
      DoubleValue timerAfterVelocityValSetting = new DoubleValue(this, "TimerAfterVelocityVal", 1.0, 0.1, 10.0, 2);
      BooleanValue timerAfterVelocity2 = this.timerAfterVelocity;
      BooleanValue booleanValue = timerAfterVelocity2;
      this.timerAfterVelocityVal = timerAfterVelocityValSetting.visibleWhen(() -> booleanValue.getValue());
      this.timerAfterFallDamage = new BooleanValue(this, "TimerAfterFallDamage", false);
      DoubleValue timerAfterFallDamageVal2 = new DoubleValue(this, "TimerAfterFallDamageVal", 1.0, 0.1, 10.0, 2);
      timerAfterVelocity2 = this.timerAfterFallDamage;
      BooleanValue booleanValue2 = timerAfterVelocity2;
      this.timerAfterFallDamageVal = timerAfterFallDamageVal2.visibleWhen(() -> booleanValue2.getValue());
      this.timerAtBlink = new BooleanValue(this, "TimerAtBlink", false);
      DoubleValue timerAtBlinkValSetting = new DoubleValue(this, "TimerAtBlinkVal", 1.0, 0.1, 10.0, 2);
      timerAfterVelocity2 = this.timerAtBlink;
      BooleanValue booleanValue3 = timerAfterVelocity2;
      this.timerAtBlinkVal = timerAtBlinkValSetting.visibleWhen(() -> booleanValue3.getValue());
      this.timeHelper = new TimeHelper();
      this.lastHurtWasFallDamage = false;
      this.packetDelayed = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(18, -4);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(35);
      this.toggleEventSubscription(25);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      this.setSuffix(String.valueOf(mc.getTimer().timerSpeed));
      if (mc.theWorld != null) {
         if (mc.thePlayer != null) {
            if (mc.thePlayer.hurtTime > 0 && this.timerAfterVelocity.getValue() && !this.lastHurtWasFallDamage) {
               mc.getTimer().timerSpeed = (float)Math.max(0.1, this.timerAfterVelocityVal.getValue());
            } else {
               if (mc.thePlayer.hurtTime > 0 && this.timerAfterFallDamage.getValue() && this.lastHurtWasFallDamage) {
                  mc.getTimer().timerSpeed = (float)Math.max(0.1, this.timerAfterFallDamageVal.getValue());
               } else if (this.packetDelayed && this.timerAtBlink.getValue()) {
                  mc.getTimer().timerSpeed = (float)Math.max(0.1, this.timerAtBlinkVal.getValue());
                  this.packetDelayed = false;
               } else {
                  mc.getTimer().timerSpeed = (float)Math.max(0.1, this.time.getValue());
               }
            }

            return;
         }
      }

      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      if (event.getWorld() == null) {
         mc.getTimer().timerSpeed = 1.0F;
      }
   }

   @Override
   public void onSound(EventSound event) {
      double x = event.getX();
      double d = 0.01;
      if (this.isWithinTolerance(x, mc.thePlayer.posX, d)) {
         x = event.getY();
         double d3 = 0.01;
         if (this.isWithinTolerance(x, mc.thePlayer.posY, d3)) {
            x = event.getZ();
            double d5 = 0.01;
            if (this.isWithinTolerance(x, mc.thePlayer.posZ, d5)) {
               if (event.getSoundName().equals("game.player.hurt.fall.small") || event.getSoundName().equals("game.player.hurt.fall.big")) {
                  this.timeHelper.reset();
               }

               if (event.getSoundName().equals("game.player.hurt")) {
                  this.lastHurtWasFallDamage = !this.timeHelper.reached(100L);
               }
            }
         }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (event.isDelayed()) {
         this.packetDelayed = true;
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (event.isDelayed()) {
         this.packetDelayed = true;
      }
   }

   private boolean isWithinTolerance(double d, double d2, double d3) {
      return !(Math.abs(d - d2) > d3);
   }

}
