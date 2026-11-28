package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;

public class Minemora2FlyMode extends FlyMode {
   public Minemora2FlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer.onGround) {
         mc.thePlayer.jump();
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.motionY > 0.0) {
         mc.thePlayer.motionY += 0.02F;
      }

      if (mc.thePlayer.ticksExisted % 4 == 0 && mc.thePlayer.motionY < 0.0) {
         mc.thePlayer.motionY = 0.02;
         mc.getTimer().timerSpeed = 0.6F;
      } else {
         mc.getTimer().timerSpeed = 0.8F;
      }
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
