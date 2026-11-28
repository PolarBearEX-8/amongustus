package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.modules.movement.Speed;

public class KarhuSpeedMode extends SpeedMode {
   public KarhuSpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setJump(false);
   }

   @Override
   public void onEarlyTick() {
      if (mc.thePlayer.onGround) {
         mc.thePlayer.jump();
         mc.getTimer().timerSpeed = 1.009F;
      } else {
         if (mc.thePlayer.motionY > 0.2101) {
            mc.thePlayer.motionY *= 0.87F;
         }
      }
   }
}
