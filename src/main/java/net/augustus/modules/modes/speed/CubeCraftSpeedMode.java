package net.augustus.modules.modes.speed;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class CubeCraftSpeedMode extends SpeedMode {
   public CubeCraftSpeedMode(String str, Speed speed2) {
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
      if (MoveUtil.isMoving() && ClientManagers.moduleManager.killAura.target == null && !ClientManagers.moduleManager.longJump.isToggled()) {
         if (mc.thePlayer.isUsingItem()) {
            if (mc.thePlayer.isBlocking()) {
               mc.getTimer().timerSpeed = 1.23F;
            } else if (mc.getTimer().timerSpeed > 1.0F) {
               mc.getTimer().timerSpeed = 1.0F;
            }
         } else {
            mc.getTimer().timerSpeed = 1.23F;
         }
      } else if (mc.getTimer().timerSpeed > 1.0F) {
         mc.getTimer().timerSpeed = 1.0F;
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }
}
