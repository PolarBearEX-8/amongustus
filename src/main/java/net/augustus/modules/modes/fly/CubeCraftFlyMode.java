package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class CubeCraftFlyMode extends FlyMode {
   private int moveTickCounter = 0;
   private double baseY = 0.0;

   public CubeCraftFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.baseY = mc.thePlayer.posY;
   }

   public void onDisable() {
      mc.thePlayer.motionX = 0.0;
      mc.thePlayer.motionZ = 0.0;
   }

   public void onPreUpdate() {
      if (MoveUtil.isMoving()) {
         if ((double)this.moveTickCounter % this.fly.cubeCraftStopDuration.getValue() == 0.0) {
            MoveUtil.setSpeed((float)this.fly.cubeCraftSpeed.getValue());
            if (mc.thePlayer.posY < this.baseY) {
               mc.thePlayer.motionY = 0.2;
            }
         } else {
            MoveUtil.setSpeed(0.0F);
         }
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
      if (mc.gameSettings.keyBindJump.isKeyDown()) {
         if ((double)this.moveTickCounter % this.fly.cubeCraftStopDuration.getValue() == 0.0) {
            event.setY(event.getY() + 2.0);
            this.baseY += 2.0;
         }
      } else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
         if ((double)this.moveTickCounter % this.fly.cubeCraftStopDuration.getValue() == 0.0) {
            event.setY(event.getY() - 2.0);
            this.baseY -= 2.0;
         }
      } else if (!MoveUtil.isMoving()) {
         event.setY(0.0);
      }

      if (!MoveUtil.isMoving()) {
         event.setX(0.0);
         event.setZ(0.0);
      }

      this.moveTickCounter++;
   }
}
