package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.modules.movement.Speed;

public class OldMinemoraSpeedMode extends SpeedMode {
   private int jumpCount = 0;

   public OldMinemoraSpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
      this.jumpCount = 0;
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
      if (mc.thePlayer.isCollidedHorizontally) {
         this.jumpCount = 0;
      } else if (!mc.thePlayer.onGround) {
         mc.thePlayer.motionY -= 0.02;
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (mc.thePlayer.movementInput.moveStrafe == 0.0F && this.jumpCount > 1 && KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
         mc.thePlayer.motionX *= 1.02;
         mc.thePlayer.motionZ *= 1.02;
      }

      event.setY(event.getY() - 0.01);
      event.setX(event.getX() * 1.0123000144958496);
      event.setZ(event.getZ() * 1.0123000144958496);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (MoveUtil.isMoving()) {
         event.setJump(true);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      this.jumpCount++;
   }
}
