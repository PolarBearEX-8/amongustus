package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class Vanilla2FlyMode extends FlyMode {
   private boolean damageReceived = false;

   public Vanilla2FlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      mc.thePlayer.capabilities.isFlying = false;
      this.damageReceived = false;
   }

   public void onDisable() {
      mc.thePlayer.motionX = 0.0;
      mc.thePlayer.motionZ = 0.0;
      mc.thePlayer.motionY = 0.0;
      mc.thePlayer.capabilities.isFlying = false;
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
      if (mc.thePlayer.hurtTime != 0) {
         this.damageReceived = true;
      }

      if (this.damageReceived || !ClientManagers.moduleManager.fly.waitForDamage.getValue()) {
         if (MoveUtil.isMoving()) {
            MoveUtil.setSpeed((float)(0.2 * this.fly.vanillaSpeedHorizontal.getValue()));
         } else {
            MoveUtil.setSpeed(0.0F);
         }

         if (!mc.gameSettings.keyBindJump.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.thePlayer.motionY = 0.0;
         } else {
            mc.thePlayer.motionY = mc.gameSettings.keyBindSneak.isKeyDown() ? -0.2 * this.fly.vanillaSpeedVertical.getValue() : 0.2 * this.fly.vanillaSpeedVertical.getValue();
         }
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
   }
}
