package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.modules.movement.Fly;

public class VerusCombinedFlyMode extends FlyMode {
   public boolean damageReceived = false;
   public boolean selfDamaged = false;

   public VerusCombinedFlyMode(String str, Fly fly2) {
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
      if (!this.selfDamaged && mc.thePlayer.onGround && MoveUtil.hasHorizontalMotion()) {
         PlayerUtil.damageSelfShort();
         this.selfDamaged = true;
      }

      if (mc.thePlayer.hurtTime != 0) {
         this.damageReceived = true;
      }

      if (!this.damageReceived) {
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

      if (this.damageReceived) {
         if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.thePlayer.motionY = 1.5;
         } else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.thePlayer.motionY = -1.5;
         } else {
            mc.thePlayer.motionY = 0.0;
         }
      }
   }

   public void onMotion(EventMotion event) {
      if (this.damageReceived) {
         mc.getTimer().timerSpeed = 0.3F;
         MoveUtil.setSpeed((float)this.fly.verusSpeedCombined.getValue());
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
   }
}
