package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.misc.Dummy;
import net.augustus.modules.movement.Speed;

public class MotionModifierSpeedMode extends SpeedMode {
   private final MotionModifierEngine motionModifierEngine;
   public final Dummy dummy;

   public MotionModifierSpeedMode(String str, Speed speed2) {
       super(str, speed2);
      this.dummy = new Dummy();
      this.motionModifierEngine = new MotionModifierEngine(this.dummy);
      this.motionModifierEngine.initRuleSettings();
   }

   @Override
   public void onEnable() {
      this.motionModifierEngine.onEnable();
   }

   @Override
   public void onDisable() {
      this.motionModifierEngine.onDisable();
   }

   @Override
   public void onPreUpdate() {
      this.motionModifierEngine.onPreUpdate();
   }

   @Override
   public void onPostUpdate() {
      this.motionModifierEngine.onPostUpdate();
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      this.motionModifierEngine.onMoveEntity(event);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.motionModifierEngine.onMoveInput(event);
   }

   @Override
   public void onSendPacket(EventPacket event) {
      this.motionModifierEngine.onSendPacket(event);
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      this.motionModifierEngine.onOutgoingPacketDelay(event);
   }

   public MotionModifierEngine getMotionModifierEngine() {
      return this.motionModifierEngine;
   }
}
