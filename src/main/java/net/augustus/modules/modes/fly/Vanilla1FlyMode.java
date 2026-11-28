package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;

public class Vanilla1FlyMode extends FlyMode {
   private float savedFlySpeed;
   private boolean damageReceived = false;

   public Vanilla1FlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.savedFlySpeed = mc.thePlayer.capabilities.getFlySpeed();
      this.damageReceived = false;
   }

   public void onDisable() {
      mc.thePlayer.motionX = 0.0;
      mc.thePlayer.motionZ = 0.0;
      mc.thePlayer.capabilities.setFlySpeed(this.savedFlySpeed);
      mc.thePlayer.capabilities.isFlying = false;
   }

   public void onPreUpdate() {
      if (mc.thePlayer.hurtTime != 0) {
         this.damageReceived = true;
      }

      if (this.damageReceived || !ClientManagers.moduleManager.fly.waitForDamage.getValue()) {
         mc.thePlayer.capabilities.isFlying = true;
         mc.thePlayer.capabilities.setFlySpeed((float)this.fly.vanillaSpeed.getValue() / 4.0F);
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }
}
