package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class OldNCPSpeedMode extends SpeedMode {
   public OldNCPSpeedMode(String str, Speed speed2) {
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
      if (MoveUtil.isMoving()) {
         if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
            mc.thePlayer.motionX *= 1.01;
            mc.thePlayer.motionZ *= 1.01;
            mc.thePlayer.setSpeedInAir(0.022F);
         }

         mc.thePlayer.motionY -= 9.9999E-4;
         MoveUtil.strafe();
      } else {
         mc.thePlayer.motionX = 0.0;
         mc.thePlayer.motionZ = 0.0;
      }

      mc.getTimer().timerSpeed = 1.0865F;
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
   }
}
