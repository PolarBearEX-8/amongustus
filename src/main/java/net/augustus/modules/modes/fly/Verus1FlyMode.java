package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class Verus1FlyMode extends FlyMode {
   private double lastJumpY;

   public Verus1FlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.onGround) {
         this.lastJumpY = mc.thePlayer.posY;
         mc.thePlayer.jump();
      }

      if (mc.thePlayer.posY > this.lastJumpY + 0.76F) {
         if (mc.thePlayer.movementInput.moveForward > 0.0F) {
            MoveUtil.setMotionWithOffset(0.35, 45.0, MoveUtil.getMovementYaw());
         }

         mc.thePlayer.motionY = 0.42F;
         this.lastJumpY = mc.thePlayer.posY;
      }
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
