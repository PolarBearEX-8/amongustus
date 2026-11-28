package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class VulcanHopSpeedMode extends SpeedMode {
   private int airTicks = 0;

   public VulcanHopSpeedMode(String str, Speed speed2) {
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
         if (MoveUtil.isMoving()) {
            mc.thePlayer.jump();
            MoveUtil.setSpeed(MoveUtil.getBaseMoveSpeed() * 1.6);
            this.airTicks = 0;
         }
      } else {
         this.airTicks++;
         if (this.airTicks == 1) {
            MoveUtil.setSpeed(MoveUtil.getBaseMoveSpeed() * 1.16);
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
