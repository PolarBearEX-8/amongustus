package net.augustus.utils;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;

public class MoveUtil implements MC {
   public static final double JUMP_MOTION_Y = 0.41999998688697815;

   public static double getJumpMotion(double d) {
      return mc.thePlayer.isPotionActive(Potion.jump) ? d + (double)((float)(mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F) : d;
   }

   public static float getMovementYaw() {
      return ClientManagers.moduleManager.scaffold.isToggled() && ClientManagers.moduleManager.scaffold.moveFix.getSelectedValue().equals("None")
         ? Augustus.getInstance().getYawPitchHelper().realYaw
         : (ClientManagers.moduleManager.targetStrafe.target != null && ClientManagers.moduleManager.targetStrafe.isToggled() ? ClientManagers.moduleManager.targetStrafe.strafeYaw : Augustus.getInstance().getYawPitchHelper().realYaw);
   }

   public static boolean isMoving() {
      return mc.thePlayer.moveForward != 0.0F || mc.thePlayer.moveStrafing != 0.0F && !mc.thePlayer.isCollidedHorizontally;
   }

   public static void strafe(double d, float f) {
      if (isMoving()) {
         double d2 = d * (double)f;
         double d3 = (double)(1.0F - f);
         double d4 = mc.thePlayer.motionX * d3;
         double d5 = mc.thePlayer.motionZ * d3;
         double direction = getDirection();
         mc.thePlayer.motionX = d4 - Math.sin(direction) * d2;
         mc.thePlayer.motionZ = d5 + Math.cos(direction) * d2;
      }
   }

   public static void setSpeed(double speed) {
      setSpeed(speed, mc.thePlayer.rotationYaw, (double)mc.thePlayer.movementInput.moveStrafe, (double)mc.thePlayer.movementInput.moveForward);
   }

   public static void setMotionWithOffsetAndSprint(double d, double d2, float f, boolean flag) {
      float rawMoveStrafe = mc.thePlayer.movementInput.rawMoveStrafe;
      float rawMoveForward = mc.thePlayer.movementInput.rawMoveForward;
      float f2 = (float)d;
      if (rawMoveStrafe != 0.0F && rawMoveForward != 0.0F) {
         if (rawMoveStrafe > 0.0F) {
            f = (float)(rawMoveForward > 0.0F ? (double)f - d2 : (double)f + d2);
         } else {
            f = (float)(rawMoveForward > 0.0F ? (double)f + d2 : (double)f - d2);
         }

         rawMoveStrafe = 0.0F;
      }

      float f3 = MathHelper.sin(f * 3.1415927F / 180.0F);
      float f4 = MathHelper.cos(f * 3.1415927F / 180.0F);
      mc.thePlayer.motionX = (double)(rawMoveStrafe * f2 * f4 - rawMoveForward * f2 * f3);
      mc.thePlayer.motionZ = (double)(rawMoveForward * f2 * f4 + rawMoveStrafe * f2 * f3);
      if (flag && mc.thePlayer.isSprinting()) {
         float f5 = f * 0.017453292F;
         mc.thePlayer.motionX = mc.thePlayer.motionX - (double)(MathHelper.sin(f5) * 0.2F);
         mc.thePlayer.motionZ = mc.thePlayer.motionZ + (double)(MathHelper.cos(f5) * 0.2F);
      }
   }

   public static void strafe() {
      strafe(1.0F);
   }

   public static void setSpeed(double d, float f, double d2, double d3) {
      if (d3 != 0.0) {
         if (d2 > 0.0) {
            f += (float)(d3 > 0.0 ? -45 : 45);
         } else if (d2 < 0.0) {
            f += (float)(d3 > 0.0 ? 45 : -45);
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

   public static double getDirection() {
      return getDirection(getMovementYaw());
   }

   public static int[] getCorrectedMoveInputs() {
      int rawMoveStrafe = (int)mc.thePlayer.movementInput.rawMoveStrafe;
      int rawMoveForward = (int)mc.thePlayer.movementInput.rawMoveForward;
      float f = MathHelper.wrapAngleTo180_float(Augustus.getInstance().getYawPitchHelper().realYaw - Minecraft.getMinecraft().thePlayer.rotationYaw);
      int n = 0;
      int n2 = 0;
      if ((double)f >= 22.5 && (double)f < 67.5) {
         n2 += rawMoveStrafe;
         n += rawMoveForward;
         n2 -= rawMoveForward;
         n += rawMoveStrafe;
      } else if ((double)f >= 67.5 && (double)f < 112.5) {
         n2 -= rawMoveForward;
         n += rawMoveStrafe;
      } else if ((double)f >= 112.5 && (double)f < 157.5) {
         n2 -= rawMoveStrafe;
         n -= rawMoveForward;
         n2 -= rawMoveForward;
         n += rawMoveStrafe;
      } else if ((double)f >= 157.5 || (double)f <= -157.5) {
         n2 -= rawMoveStrafe;
         n -= rawMoveForward;
      } else if ((double)f > -157.5 && (double)f <= -112.5) {
         n2 -= rawMoveStrafe;
         n -= rawMoveForward;
         n2 += rawMoveForward;
         n -= rawMoveStrafe;
      } else if ((double)f > -112.5 && (double)f <= -67.5) {
         n2 += rawMoveForward;
         n -= rawMoveStrafe;
      } else if ((double)f > -67.5 && (double)f <= -22.5) {
         n2 += rawMoveStrafe;
         n += rawMoveForward;
         n2 += rawMoveForward;
         n -= rawMoveStrafe;
      } else {
         n2 += rawMoveStrafe;
         n += rawMoveForward;
      }

      return new int[]{MathHelper.clamp_int(n, -1, 1), MathHelper.clamp_int(n2, -1, 1)};
   }

   public static double getBaseMoveSpeed() {
      double d = (double)mc.thePlayer.capabilities.getWalkSpeed() * 2.873;
      if (mc.thePlayer.isPotionActive(Potion.moveSlowdown)) {
         d /= 1.0 + 0.2 * (double)(mc.thePlayer.getActivePotionEffect(Potion.moveSlowdown).getAmplifier() + 1);
      }

      if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
         d *= 1.0 + 0.2 * (double)(mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
      }

      return d;
   }

   public static void setMoveSpeed(double moveSpeed) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      double movementYaw = (double)getMovementYaw();
      boolean flag = thePlayer.movementInput.rawMoveForward != 0.0F || thePlayer.movementInput.rawMoveStrafe != 0.0F;
      boolean flag2 = thePlayer.movementInput.rawMoveForward > 0.0F;
      boolean flag3 = thePlayer.movementInput.rawMoveForward < 0.0F;
      boolean flag4 = thePlayer.movementInput.rawMoveStrafe > 0.0F;
      boolean flag5 = thePlayer.movementInput.rawMoveStrafe < 0.0F;
      boolean flag6 = flag5 || flag4;
      boolean flag7 = flag2 || flag3;
      if (flag) {
         if (flag2 && !flag6) {
            movementYaw += 0.0;
         } else if (flag3 && !flag6) {
            movementYaw += 180.0;
         } else if (flag2 && flag5) {
            movementYaw += 45.0;
         } else if (flag2) {
            movementYaw -= 45.0;
         } else if (!flag7 && flag5) {
            movementYaw += 90.0;
         } else if (!flag7 && flag4) {
            movementYaw -= 90.0;
         } else if (flag3 && flag5) {
            movementYaw += 135.0;
         } else if (flag3) {
            movementYaw -= 135.0;
         }

         movementYaw = Math.toRadians(movementYaw);
         thePlayer.motionX = -Math.sin(movementYaw) * moveSpeed;
         thePlayer.motionZ = Math.cos(movementYaw) * moveSpeed;
      }
   }

   public static double getDirection(float f, float f2, float f3) {
      if (f < 0.0F) {
         f3 += 180.0F;
      }

      float f4 = 1.0F;
      if (f < 0.0F) {
         f4 = -0.5F;
      } else if (f > 0.0F) {
         f4 = 0.5F;
      }

      if (f2 > 0.0F) {
         f3 -= 90.0F * f4;
      }

      if (f2 < 0.0F) {
         f3 += 90.0F * f4;
      }

      return Math.toRadians((double)f3);
   }

   public static void strafe(float f) {
      strafe(getSpeed(), f);
   }

   public static boolean isNearEdge() {
      double motionX = mc.thePlayer.motionX;
      double motionZ = mc.thePlayer.motionZ;
      double d = 0.05;

      while (motionX != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(motionX, -1.0, 0.0)).isEmpty()) {
         if (motionX < d && motionX >= -d) {
            motionX = 0.0;
         } else if (motionX > 0.0) {
            motionX -= d;
         } else {
            motionX += d;
         }
      }

      while (motionZ != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, motionZ)).isEmpty()) {
         if (motionZ < d && motionZ >= -d) {
            motionZ = 0.0;
         } else if (motionZ > 0.0) {
            motionZ -= d;
         } else {
            motionZ += d;
         }
      }

      while (motionX != 0.0 && motionZ != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(motionX, -1.0, motionZ)).isEmpty()) {
         if (motionX < d && motionX >= -d) {
            motionX = 0.0;
         } else if (motionX > 0.0) {
            motionX -= d;
         } else {
            motionX += d;
         }

         if (motionZ < d && motionZ >= -d) {
            motionZ = 0.0;
         } else if (motionZ > 0.0) {
            motionZ -= d;
         } else {
            motionZ += d;
         }
      }

      return mc.thePlayer.motionX != motionX || mc.thePlayer.motionZ != motionZ;
   }

   public static double getJumpMotion() {
      return getJumpMotion(0.41999998688697815);
   }

   public static void setSpeed(float speed) {
      double direction = getDirection();
      mc.thePlayer.motionX = -(Math.sin(direction) * (double)speed);
      mc.thePlayer.motionZ = Math.cos(direction) * (double)speed;
   }

   public static double getDirection(float f) {
      return getDirection(mc.thePlayer.movementInput.rawMoveForward, mc.thePlayer.movementInput.rawMoveStrafe, f);
   }

   public static float getDirectionDegrees(boolean flag) {
      return flag ? (float)Math.toDegrees(getDirection()) : getMovementYaw();
   }

   public static double[] calculateForwardMotion(double d, float f) {
      float f2 = 0.0F;
      float moveForward = mc.thePlayer.moveForward;
      float f3 = (float)d;
      float f4 = f2 * f2 + moveForward * moveForward;
      if (f4 >= 1.0E-4F) {
         f4 = MathHelper.sqrt_float(f4);
         if (f4 < 1.0F) {
            f4 = 1.0F;
         }

         f4 = f3 / f4;
         f2 *= f4;
         moveForward *= f4;
         float f5 = MathHelper.sin(f * 3.1415927F / 180.0F);
         float f6 = MathHelper.cos(f * 3.1415927F / 180.0F);
         return new double[]{(double)(f2 * f6 - moveForward * f5), (double)(moveForward * f6 + f2 * f5)};
      } else {
         return new double[]{0.0, 0.0};
      }
   }

   public static void strafeCurrentSpeed() {
      double speed = getSpeed();
      if (isMoving()) {
         float movementYaw = getMovementYaw();
         if (mc.thePlayer.movementInput.rawMoveForward < 0.0F) {
            movementYaw += 180.0F;
         } else {
            float f = 1.0F;
            if (mc.thePlayer.movementInput.rawMoveForward < 0.0F) {
               f = -0.5F;
            } else if (mc.thePlayer.movementInput.rawMoveForward > 0.0F) {
               f = 0.5F;
            }

            if (mc.thePlayer.movementInput.rawMoveStrafe > 0.0F) {
               movementYaw -= 90.0F * f;
            }

            if (mc.thePlayer.movementInput.rawMoveStrafe < 0.0F) {
               movementYaw += 90.0F * f;
            }
         }

         double d = Math.toRadians((double)movementYaw);
         mc.thePlayer.motionX = -Math.sin(d) * speed;
         mc.thePlayer.motionZ = Math.cos(d) * speed;
      }
   }

   public static void strafe(double d) {
      strafe(d, 1.0F);
   }

   public static double[] calculateMotion(double d, float f, float f2, float f3) {
      float f4 = (float)d;
      float f5 = MathHelper.sin(f3 * 3.1415927F / 180.0F);
      float f6 = MathHelper.cos(f3 * 3.1415927F / 180.0F);
      double d2 = (double)(f * f4 * f6 - f2 * f4 * f5);
      double d3 = (double)(f2 * f4 * f6 + f * f4 * f5);
      return new double[]{d2, d3};
   }

   public static void setMotionWithOffset(double d, double d2, float f) {
      float rawMoveStrafe = mc.thePlayer.movementInput.rawMoveStrafe;
      float rawMoveForward = mc.thePlayer.movementInput.rawMoveForward;
      float f2 = (float)d;
      if (rawMoveStrafe != 0.0F && rawMoveForward != 0.0F) {
         if (rawMoveStrafe > 0.0F) {
            f = (float)(rawMoveForward > 0.0F ? (double)f - d2 : (double)f + d2);
         } else {
            f = (float)(rawMoveForward > 0.0F ? (double)f + d2 : (double)f - d2);
         }

         rawMoveStrafe = 0.0F;
      }

      float f3 = MathHelper.sin(f * 3.1415927F / 180.0F);
      float f4 = MathHelper.cos(f * 3.1415927F / 180.0F);
      mc.thePlayer.motionX = (double)(rawMoveStrafe * f2 * f4 - rawMoveForward * f2 * f3);
      mc.thePlayer.motionZ = (double)(rawMoveForward * f2 * f4 + rawMoveStrafe * f2 * f3);
   }

   public static void setEventSpeed(double d, boolean flag, EventMoveEntity event) {
      double d2 = Math.toRadians((double)getDirectionDegrees(flag));
      event.setX(-(Math.sin(d2) * d));
      event.setZ(Math.cos(d2) * d);
   }

   public static void setSpeed(float f, boolean flag) {
      double d = Math.toRadians((double)getDirectionDegrees(flag));
      mc.thePlayer.motionX = -(Math.sin(d) * (double)f);
      mc.thePlayer.motionZ = Math.cos(d) * (double)f;
   }

   public static void addEventMotion(double d, boolean flag, EventMoveEntity event) {
      float f = getDirectionDegrees(flag) * 0.017453292F;
      event.setX(event.getX() - (double)MathHelper.sin(f) * d);
      event.setZ(event.getZ() + (double)MathHelper.cos(f) * d);
   }

   public static boolean hasHorizontalMotion() {
      return mc.thePlayer.motionX != 0.0 || mc.thePlayer.motionZ != 0.0;
   }

   public static void strafeEvent(double d, float f, EventMoveEntity event) {
      if (isMoving()) {
         double d2 = d * (double)f;
         double d3 = (double)(1.0F - f);
         double d4 = event.getX() * d3;
         double d5 = event.getZ() * d3;
         double direction = getDirection();
         event.setX(d4 - Math.sin(direction) * d2);
         event.setZ(d5 + Math.cos(direction) * d2);
      }
   }

   public static void addMotion(double d, boolean flag) {
      float f = getDirectionDegrees(flag) * 0.017453292F;
      mc.thePlayer.motionX = mc.thePlayer.motionX - (double)MathHelper.sin(f) * d;
      mc.thePlayer.motionZ = mc.thePlayer.motionZ + (double)MathHelper.cos(f) * d;
   }

   public static double getSpeed() {
      return Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
   }

}
