package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class SentrySpeedMode extends SpeedMode {
   private int airTicks = 0;

   public SentrySpeedMode(String str, Speed speed2) {
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
         this.airTicks++;
         if (mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.4;
            MoveUtil.addMotion(0.2, false);
            this.airTicks = 0;
         }
      }
   }

   @Override
   public void onPostUpdate() {
      MoveUtil.strafe();
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (event.getY() < 0.0) {
         event.setY(event.getY() - 0.07999999821186066);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }
}
