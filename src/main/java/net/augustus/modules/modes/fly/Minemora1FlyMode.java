package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;

public class Minemora1FlyMode extends FlyMode {
   public Minemora1FlyMode(String str, Fly fly2) {
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
      mc.thePlayer.motionY += 0.02F;
      if (mc.thePlayer.motionY < -0.16) {
         mc.thePlayer.motionY = 0.0;
      }
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
