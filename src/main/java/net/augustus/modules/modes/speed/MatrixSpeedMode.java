package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class MatrixSpeedMode extends SpeedMode {
   public MatrixSpeedMode(String str, Speed speed2) {
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
      if (MoveUtil.isMoving()) {
         if (mc.thePlayer.onGround && mc.thePlayer.motionY < 0.003) {
            mc.thePlayer.jump();
            mc.getTimer().timerSpeed = 1.0F;
         }

         if (mc.thePlayer.motionY > 0.003) {
            mc.thePlayer.motionX *= 1.0012F;
            mc.thePlayer.motionZ *= 1.0012F;
            mc.getTimer().timerSpeed = 1.05F;
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setJump(false);
   }
}
