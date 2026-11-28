package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class VerusSpeedMode extends SpeedMode {
   public VerusSpeedMode(String str, Speed speed2) {
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
      MoveUtil.setMoveSpeed(0.2919999957084656);
      if (this.speed.damageBoost.getValue() && mc.thePlayer.hurtTime != 0 && mc.thePlayer.fallDistance < 3.0F) {
         MoveUtil.setMoveSpeed((double)((float)this.speed.dmgSpeed.getValue()));
      } else {
         MoveUtil.setMoveSpeed(0.2919999957084656);
      }

      if (this.isMovingOnGround()) {
         mc.thePlayer.jump();
      } else {
         mc.thePlayer.jumpMovementFactor = 0.1F;
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }
}
