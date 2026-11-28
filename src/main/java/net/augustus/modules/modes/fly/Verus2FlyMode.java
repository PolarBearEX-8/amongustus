package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class Verus2FlyMode extends FlyMode {
   public Verus2FlyMode(String str, Fly fly2) {
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
   }

   @Override
   public void onMotion(EventMotion event) {
      double speed = MoveUtil.getSpeed();
      if (mc.gameSettings.keyBindJump.isKeyDown() && !mc.thePlayer.isCollidedVertically && mc.thePlayer.ticksExisted % 2 == 0) {
         mc.thePlayer.jump();
      }

      if (!mc.gameSettings.keyBindJump.isKeyDown() && mc.thePlayer.motionY < -0.07840000152) {
         mc.thePlayer.motionY = -0.07840000152;
      }

      if (MoveUtil.isMoving()) {
         double d = Math.toRadians((double)mc.thePlayer.rotationYaw);
         mc.thePlayer.motionX = -Math.sin(d) * speed;
         mc.thePlayer.motionZ = Math.cos(d) * speed;
         MoveUtil.addMotion(0.01, true);
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
