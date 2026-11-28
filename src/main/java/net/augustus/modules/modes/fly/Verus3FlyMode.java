package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class Verus3FlyMode extends GroundCollisionFlyMode {
   public Verus3FlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
      this.collisionFloorY = mc.thePlayer.posY - 0.5;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      if (MoveUtil.isMoving() && !mc.thePlayer.onGround) {
         MoveUtil.addMotion(0.0083, true);
      }

      MoveUtil.strafe();
   }
}
