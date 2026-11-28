package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class TeleportHopSpeedMode extends SpeedMode {
   public TeleportHopSpeedMode(String str, Speed speed2) {
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
      if (mc.thePlayer.onGround) {
         MoveUtil.addMotion(0.2, true);
         mc.thePlayer.motionY = 0.0;
         mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ);
      } else {
         mc.thePlayer.motionY *= 1.3;
         if (mc.thePlayer.ticksExisted % 5 == 0) {
            MoveUtil.addMotion(0.02, true);
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }
}
