package net.augustus.modules.modes.speed;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class VerusLowHopSpeedMode extends SpeedMode {
   private boolean justJumped = false;

   public VerusLowHopSpeedMode(String str, Speed speed2) {
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
         if (ClientManagers.moduleManager.step.isToggled()) {
            if (!ClientManagers.moduleManager.step.postStepTimer.reached(500L) || mc.thePlayer.isCollidedHorizontally) {
               return;
            }
         }

         if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
            this.justJumped = true;
         } else if (this.justJumped) {
            if (!mc.thePlayer.isCollidedHorizontally) {
               mc.thePlayer.motionY = -0.0784000015258789;
            }

            this.justJumped = false;
         }

         MoveUtil.setSpeed(0.34);
      } else {
         mc.thePlayer.motionX = mc.thePlayer.motionZ = 0.0;
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (MoveUtil.isMoving()) {
         event.setJump(false);
      }
   }
}
