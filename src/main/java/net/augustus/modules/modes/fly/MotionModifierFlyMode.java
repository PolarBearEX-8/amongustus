package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.modules.modes.motion.MotionModifierEngine;
import net.augustus.modules.misc.Dummy;
import net.augustus.modules.movement.Fly;

public class MotionModifierFlyMode extends FlyMode {
   private final MotionModifierEngine motionModifierEngine;
   public final Dummy dummy;

   public MotionModifierFlyMode(String str, Fly fly2) {
       super(str, fly2);
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
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onPostUpdate() {
      this.motionModifierEngine.onPostUpdate();
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      this.motionModifierEngine.onMoveEntity(event);
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
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
