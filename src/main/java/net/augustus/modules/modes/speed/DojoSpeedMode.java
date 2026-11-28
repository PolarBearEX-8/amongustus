package net.augustus.modules.modes.speed;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class DojoSpeedMode extends SpeedMode {
   public DojoSpeedMode(String str, Speed speed2) {
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
            MoveUtil.setSpeed((float)(ClientManagers.moduleManager.speed.dojoSpeed.getValue() * MoveUtil.getSpeed()));
         } else {
            MoveUtil.setSpeed((float)MoveUtil.getSpeed());
         }
      }
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
