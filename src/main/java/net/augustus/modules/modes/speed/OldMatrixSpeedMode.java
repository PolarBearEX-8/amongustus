package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class OldMatrixSpeedMode extends SpeedMode {
   public OldMatrixSpeedMode(String str, Speed speed2) {
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
      if (mc.thePlayer.motionX != 0.0 || mc.thePlayer.motionZ != 0.0) {
         if (!MoveUtil.isMoving()) {
            mc.thePlayer.motionY -= 0.009998999536037445;
            if (mc.thePlayer.onGround) {
               MoveUtil.strafeCurrentSpeed();
            } else if (mc.thePlayer.movementInput.moveForward > 0.0F && mc.thePlayer.movementInput.moveStrafe != 0.0F) {
               mc.thePlayer.setSpeedInAir(0.02F);
            } else {
               mc.thePlayer.setSpeedInAir(0.0208F);
            }
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (MoveUtil.isMoving()) {
         event.setJump(true);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (MoveUtil.isMoving()) {
         MoveUtil.addMotion(0.02, false);
      } else {
         MoveUtil.addMotion(0.002, false);
      }
   }
}
