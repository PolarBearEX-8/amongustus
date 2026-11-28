package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.modules.movement.Fly;

public class OldVerusFlyMode extends FlyMode {
   public boolean damageReceived = false;
   public boolean selfDamaged = false;

   public OldVerusFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.damageReceived = false;
      this.selfDamaged = false;
   }

   public void onDisable() {
      MoveUtil.setSpeed(0.0F);
   }

   public void onPreUpdate() {
      if (!this.selfDamaged && (mc.thePlayer.onGround || !ClientManagers.moduleManager.fly.onlyOnGround.getValue())) {
         PlayerUtil.damageSelfShort();
         this.selfDamaged = true;
      }

      if (mc.thePlayer.hurtTime != 0) {
         this.damageReceived = true;
      }

      if (!this.damageReceived && this.selfDamaged) {
         mc.thePlayer.motionZ = 0.0;
         mc.thePlayer.motionX = 0.0;
         mc.gameSettings.keyBindJump.setPressed(false);
      }

      if (this.damageReceived) {
         mc.getTimer().timerSpeed = 0.3F;
         if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.thePlayer.motionY = 1.5;
         } else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.thePlayer.motionY = -1.5;
         } else {
            mc.thePlayer.motionY = 0.0;
         }

         mc.thePlayer.onGround = true;
         MoveUtil.setSpeed((float)this.fly.verusSpeed.getValue());
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }
}
