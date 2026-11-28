package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;

public class OldMineplexSpeedMode extends SpeedMode {
   private int airTicks;
   private int speedStage;

   public OldMineplexSpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
      this.speedStage = -2;
      this.airTicks = 0;
   }

   @Override
   public void onDisable() {
      this.speedStage = -2;
      this.airTicks = 0;
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      if (this.isMovingOnGround()) {
         if (this.speedStage < 0) {
            this.speedStage++;
         }

         if (mc.thePlayer.posY != (double)((int)mc.thePlayer.posY)) {
            this.speedStage = -2;
         }

         mc.thePlayer.jump();
         this.airTicks = 0;
         float movementYaw = MoveUtil.getMovementYaw();
         double d = 0.0;
         MoveUtil.setMotionWithOffsetAndSprint(-0.20000000298023224, d, movementYaw, true);
         mc.getTimer().timerSpeed = 1.0F;
      } else if (this.isMoving()) {
         if (mc.thePlayer.isCollidedHorizontally) {
            this.speedStage = -2;
            return;
         }

         this.airTicks++;
         if (mc.thePlayer.motionY < -0.4) {
            return;
         }

         if (mc.thePlayer.motionY < 0.0) {
            mc.thePlayer.motionY += 0.033;
         }

         if (mc.thePlayer.motionY > 0.0) {
            mc.thePlayer.motionY += 0.03;
         }

         if (mc.thePlayer.moveStrafing == 0.0F) {
            MoveUtil.setMotionWithOffsetAndSprint((double)(0.61F - (float)this.airTicks / 100.0F + (float)this.speedStage / 7.0F), 0.0, MoveUtil.getMovementYaw(), true);
         } else {
            this.speedStage = this.airTicks < 1 ? this.airTicks : 0;
            MoveUtil.setMotionWithOffsetAndSprint((double)(0.61F - (float)this.airTicks / 100.0F + (float)this.speedStage / 7.0F), 0.0, MoveUtil.getMovementYaw(), true);
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
