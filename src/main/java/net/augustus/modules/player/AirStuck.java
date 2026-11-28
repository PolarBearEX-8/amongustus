package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.EventGameLoop;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.EventKey;
import net.augustus.events.listeners.KeyListener;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.TickCounter;
import net.augustus.events.EventUpdate;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class AirStuck extends Module implements MoveEntityListener, MoveInputListener, UpdateListener, PacketListener, GameLoopListener, TickListener, KeyListener {
   public final StringValue mode;
   private final BooleanValue motionstopLegacy;
   private final BooleanValue sneak;
   private final BooleanValue delayIncomingPackets;
   private final BooleanValue onlyInAir;
   private final BooleanValue intervals;
   private final RandomizedRangeSettings interval;
   private final RandomizedRangeSettings intervalBreakDuration;
   private final BooleanValue blockKeyAndMouseInputs;
   private final TickCounter tickCounter;
   private boolean shouldMarkDead;

   public AirStuck() {
       super("AirStuck", "LuftStecken", new Color(78, 186, 112), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "NewNoRotate", new String[]{"NewNoRotate", "NoRotate", "CanRotate", "Intave", "Dead"});
      this.motionstopLegacy = new BooleanValue(this, "MotionStop (Legacy)", false);
      BooleanValue booleanValue = new BooleanValue(this, "Sneak", true);
      BooleanValue motionstopLegacy2 = this.motionstopLegacy;
      this.sneak = (BooleanValue)booleanValue.withVisibility(() -> motionstopLegacy2.getValue());
      this.delayIncomingPackets = new BooleanValue(this, "DelayIncomingPackets", true);
      booleanValue = new BooleanValue(this, "OnlyInAir", true);
      this.onlyInAir = (BooleanValue)booleanValue.withVisibility(() -> this.isOnlyInAirVisible());
      booleanValue = new BooleanValue(this, "Intervals", true);
      this.intervals = (BooleanValue)booleanValue.withVisibility(() -> this.isIntervalsVisible());
      this.interval = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "Interval", 15.0, 20.0, 1.0, 100.0, 0)).withoutNotInARow().withVisibility(() -> this.isIntervalVisible());
      this.intervalBreakDuration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "IntervalBreakDuration", 1.0, 1.0, 1.0, 10.0, 0)).withoutNotInARow().withVisibility(() -> this.isIntervalBreakDurationVisible());
      this.blockKeyAndMouseInputs = new BooleanValue(this, "BlockKeyAndMouseInputs", true);
      this.tickCounter = new TickCounter();
      this.shouldMarkDead = false;
   }

   @Override
   public void onEnable() {
      this.shouldMarkDead = true;
   }

   @Override
   public void onDisable() {
      if (this.mode.getSelectedValue().equals("Dead") && !this.shouldMarkDead) {
         mc.thePlayer.isDead = false;
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(14);
   }

   @Override
   public void onKeyboardKey(EventKey event) {
      if (this.blockKeyAndMouseInputs.getValue() && event.isPressed()) {
         event.cancel();
      }
   }

   @Override
   public void onMouseKey(EventKey event) {
      if (this.blockKeyAndMouseInputs.getValue() && event.isPressed()) {
         event.cancel();
      }
   }

   @Override
   public void onEarlyTick() {
      this.tickCounter.increment();
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.motionstopLegacy.getValue() && this.shouldFreeze()) {
         event.setX(0.0);
         event.setY(0.0);
         event.setZ(0.0);
      }
   }

   @Override
   public void onPreUpdate(EventUpdate event) {
      this.setSuffix(this.mode.getSelectedValue());
      if (this.shouldFreeze()) {
         if (this.mode.getSelectedValue().equals("Dead") && this.shouldMarkDead) {
            mc.thePlayer.isDead = true;
            this.shouldMarkDead = false;
         }

         if (this.motionstopLegacy.getValue()) {
            mc.thePlayer.motionY = 0.0;
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
         } else {
            event.cancel();
         }
      }
   }

   @Override
   public void onGameLoop(EventGameLoop event) {
      if (this.shouldFreeze() && this.delayIncomingPackets.getValue()) {
         event.setSkipRender(true);
         event.setAllowSkipRender(true);
      }
   }

   @Override
   public void onPostUpdate(EventUpdate event) {
      if (this.shouldFreeze() && this.mode.getSelectedValue().equals("NewNoRotate")) {
         event.cancel();
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.sneak.getValue() && this.shouldFreeze() && this.motionstopLegacy.getValue()) {
         event.setSneak(true);
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer && this.shouldFreeze()) {
         switch (this.mode.getSelectedValue()) {
            case "NoRotate":
               event.cancel();
               break;
            case "CanRotate":
               if (!(packet instanceof C03PacketPlayer.C05PacketPlayerLook)) {
                  event.cancel();
               }
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (mc.thePlayer == null) {
         this.setToggled(false);
      }

      if (this.mode.getSelectedValue().equals("Intave") && packet instanceof S08PacketPlayerPosLook) {
         event.cancel();
      }
   }

   private boolean shouldFreeze() {
      boolean flag = mc.thePlayer != null && (!mc.thePlayer.onGround || !this.onlyInAir.getValue());
      if (flag && this.intervals.getValue()) {
         TickCounter tickCounter2 = this.tickCounter;
         if (tickCounter2.hasReached(this.interval.getLastIntValue())) {
            tickCounter2 = this.tickCounter;
            if (!tickCounter2.hasReached(this.interval.getLastIntValue() + this.intervalBreakDuration.getLastIntValue())) {
               flag = false;
            } else {
               this.interval.nextValue();
               this.intervalBreakDuration.nextValue();
               this.tickCounter.reset();
            }
         }
      }

      return flag;
   }

   private boolean isIntervalBreakDurationVisible() {
      return this.intervals.getValue() && this.intervals.isVisible();
   }

   private boolean isIntervalVisible() {
      return this.intervals.getValue() && this.intervals.isVisible();
   }

   private boolean isIntervalsVisible() {
      return !this.mode.getSelectedValue().equals("Intave");
   }

   private boolean isOnlyInAirVisible() {
      return !this.mode.getSelectedValue().equals("Intave");
   }

}
