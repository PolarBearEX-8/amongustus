package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.modules.movement.Speed;
import net.minecraft.potion.Potion;

public class VulcanLowHopSpeedMode extends SpeedMode {
   private int airTicks;

   public VulcanLowHopSpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
      this.airTicks = 0;
   }

   @Override
   public void onDisable() {
      this.airTicks = 0;
   }

   @Override
   public void onPreUpdate() {
      this.airTicks++;
      if (mc.thePlayer.hurtTime <= 0) {
         if (this.canStartLowHop()) {
            mc.thePlayer.jump();
            double d;
            if (mc.thePlayer.moveForward != 0.0F && mc.thePlayer.moveStrafing == 0.0F) {
               d = 0.521;
            } else if (mc.thePlayer.moveStrafing != 0.0F) {
               d = 0.46;
            } else {
               d = this.getBaseMoveSpeed() * 1.8;
            }

            if (PlayerUtil.hasPotionEffect(Potion.moveSpeed)) {
               d *= 1.0 + 0.11 * (double)(mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
            }

            if (PlayerUtil.hasPotionEffect(Potion.moveSlowdown)) {
               d *= 0.8999999761581421;
            }

            this.setMoveSpeed(d);
            this.airTicks = 0;
         } else {
            mc.getTimer().timerSpeed = 1.0F;
            if ((double)this.getHorizontalSpeed() > 0.332) {
               this.setMoveSpeed(0.331);
            }

            if (this.airTicks == 4) {
               mc.thePlayer.motionY = -0.315;
            }

            if (this.airTicks > 3) {
               mc.thePlayer.motionY -= 4.0E-4;
            }
         }
      }
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   private void setMoveSpeed(double moveSpeed) {
      double moveStrafe = (double)mc.thePlayer.movementInput.moveStrafe;
      double moveForward = (double)mc.thePlayer.movementInput.moveForward;
      this.applyHorizontalMotion(moveSpeed, mc.thePlayer.rotationYaw, moveStrafe, moveForward);
   }

   private void applyHorizontalMotion(double d, float f, double d2, double d3) {
      if (d3 != 0.0) {
         if (d2 > 0.0) {
            f += d3 > 0.0 ? -45.0F : 45.0F;
         } else if (d2 < 0.0) {
            f += d3 > 0.0 ? 45.0F : -45.0F;
         }

         d2 = 0.0;
         if (d3 > 0.0) {
            d3 = 1.0;
         } else if (d3 < 0.0) {
            d3 = -1.0;
         }
      }

      if (d2 > 0.0) {
         d2 = 1.0;
      } else if (d2 < 0.0) {
         d2 = -1.0;
      }

      double d4 = Math.cos(Math.toRadians((double)(f + 90.0F)));
      double d5 = Math.sin(Math.toRadians((double)(f + 90.0F)));
      mc.thePlayer.motionX = d3 * d * d4 + d2 * d * d5;
      mc.thePlayer.motionZ = d3 * d * d5 - d2 * d * d4;
   }

   private float getHorizontalSpeed() {
      return (float)Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
   }

   private double getBaseMoveSpeed() {
      double d = 0.2875;
      if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
         d *= 1.0 + 0.2 * (double)(mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
      }

      return d;
   }

   private boolean canStartLowHop() {
      return this.isMoving() && mc.thePlayer.onGround;
   }
}
