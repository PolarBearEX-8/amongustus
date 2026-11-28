package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.Setting;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.EventMoveEntity;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class Strafe extends Module implements MoveEntityListener, UpdateListener {
   public final StringValue mode;
   public final StringValue event;
   public final DoubleValue percentage;
   public final BooleanValue strafeOnGround;
   public final StringValue timingOnGround;
   public final RangeSetting hurtTimeStrafeOnGround;
   public final BooleanValue strafeOnAir;
   public final StringValue timinInAir;
   public final RangeSetting hurtTimeStrafeInAir;
   public final BooleanValue backwardsStrafe;

   public Strafe() {
       super("Strafe", "Bestrafung", new Color(50, 168, 82), Categorys.MOVEMENT);
      this.mode = new StringValue(this, "Mode", "Normal", new String[]{"Normal", "Matrix", "Intave"});
      this.event = new StringValue(this, "Event", "Pre", new String[]{"Pre", "Mid", "OtherMid", "Post"});
      this.percentage = new DoubleValue(this, "Percentage", 100.0, 0.0, 100.0, 0).visibleWhen(() -> this.isPercentageVisible());
      this.strafeOnGround = new BooleanValue(this, "StrafeOnGround", false);
      StringValue setting = new StringValue(this, "TimingOnGround", "Always", new String[]{"Always", "NotWhileKB", "OnlyWhileKB"});
      BooleanValue strafeOnGround2 = this.strafeOnGround;
      BooleanValue booleanValue = strafeOnGround2;
      this.timingOnGround = (StringValue)setting.withVisibility(() -> booleanValue.getValue());
      this.hurtTimeStrafeOnGround = new RangeSetting(this, "HurtTimeStrafeOnGround", 0.0, 10.0, 0.0, 10.0, 0).visibleWhen(() -> this.isHurtTimeOnGroundVisible());
      this.strafeOnAir = new BooleanValue(this, "StrafeOnAir", true);
      StringValue setting2 = new StringValue(this, "TiminInAir", "Always", new String[]{"Always", "NotWhileKB", "OnlyWhileKB"});
      strafeOnGround2 = this.strafeOnAir;
      BooleanValue booleanValue2 = strafeOnGround2;
      this.timinInAir = (StringValue)setting2.withVisibility(() -> booleanValue2.getValue());
      this.hurtTimeStrafeInAir = new RangeSetting(this, "HurtTimeStrafeInAir", 0.0, 10.0, 0.0, 10.0, 0).visibleWhen(() -> this.isHurtTimeInAirVisible());
      this.backwardsStrafe = new BooleanValue(this, "BackwardsStrafe", true);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onPostUpdate() {
      if (this.event.getSelectedValue().equals("Post")) {
         this.applyStrafe();
      }
   }

   @Override
   public void onPreUpdate() {
      if (this.event.getSelectedValue().equals("Pre")) {
         this.applyStrafe();
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event2) {
      if (this.canStrafe()) {
         if (!BlockUtil.isScaffoldActiveAndValid()) {
            if (this.event.getSelectedValue().equals("Mid")) {
               this.applyMidStrafe(event2, true);
            } else if (this.event.getSelectedValue().equals("OtherMid")) {
               this.applyMidStrafe(event2, false);
            }
         }
      }
   }

   private void applyMidStrafe(EventMoveEntity event2, boolean flag) {
      double x = event2.getX();
      double z = event2.getZ();
      double d = Math.sqrt(x * x + z * z);
      switch (this.mode.getSelectedValue()) {
         case "Normal":
            if (MoveUtil.isMoving()) {
               float f = (float)(this.percentage.getValue() / 100.0);
               double d2 = d * (double)f;
               double d3 = (double)(1.0F - f);
               double d4 = x * d3;
               double d5 = z * d3;
               double direction = MoveUtil.getDirection();
               x = d4 - Math.sin(direction) * d2;
               z = d5 + Math.cos(direction) * d2;
               event2.setX(x);
               event2.setZ(z);
               if (flag) {
                  mc.thePlayer.motionX = x;
                  mc.thePlayer.motionZ = z;
               }
            }
            break;
         case "Intave":
            if (MoveUtil.isMoving()
               && !mc.thePlayer.onGround
               && (mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown())
               && mc.thePlayer.hurtTime == 0
               && !mc.thePlayer.isCollidedVertically
               && !mc.thePlayer.isBurning()) {
               if (PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] == 1.0) {
                  float f2 = (float)(this.percentage.getValue() / 100.0);
                  double d6 = d * (double)f2;
                  double d7 = (double)(1.0F - f2);
                  double d8 = x * d7;
                  double d9 = z * d7;
                  double direction2 = MoveUtil.getDirection();
                  x = d8 - Math.sin(direction2) * d6;
                  z = d9 + Math.cos(direction2) * d6;
                  event2.setX(x);
                  event2.setZ(z);
                  if (flag) {
                     mc.thePlayer.motionX = x;
                     mc.thePlayer.motionZ = z;
                  }
               }
            }
            break;
         case "Matrix":
            if (MoveUtil.isMoving()) {
               double matrixStrafeDirection = getMatrixStrafeDirection();
               x = -Math.sin(matrixStrafeDirection) * d;
               z = Math.cos(matrixStrafeDirection) * d;
               event2.setX(x);
               event2.setZ(z);
               if (flag) {
                  mc.thePlayer.motionX = x;
                  mc.thePlayer.motionZ = z;
               }
            }
      }
   }

   private static double getMatrixStrafeDirection() {
      float movementYaw = MoveUtil.getMovementYaw();
      if (mc.thePlayer.moveForward < 0.0F) {
         movementYaw += 180.0F;
      } else {
         float f = 1.0F;
         if (mc.thePlayer.moveForward > 0.0F) {
            f = 0.5F;
         }

         if (mc.thePlayer.moveStrafing > 0.0F) {
            movementYaw -= 90.0F * f;
         }

         if (mc.thePlayer.moveStrafing < 0.0F) {
            movementYaw += 90.0F * f;
         }
      }

      return Math.toRadians((double)movementYaw);
   }

   private void applyStrafe() {
      if (this.canStrafe()) {
         if (!BlockUtil.isScaffoldActiveAndValid()) {
            switch (this.mode.getSelectedValue()) {
               case "Normal":
                  MoveUtil.strafe((float)(this.percentage.getValue() / 100.0));
                  break;
               case "Matrix":
                  MoveUtil.strafeCurrentSpeed();
                  break;
               case "Intave":
                  if (!mc.thePlayer.onGround) {
                     if (PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] == 1.0) {
                        MoveUtil.strafe((float)(this.percentage.getValue() / 100.0));
                     }
                  }
            }
         }
      }
   }

   private boolean canStrafe() {
      if (MoveUtil.getCorrectedMoveInputs()[0] < 0 && !this.backwardsStrafe.getValue()) {
         return false;
      } else if (mc.thePlayer.onGround) {
         if (!this.strafeOnGround.getValue()) {
            return false;
         } else if (mc.thePlayer.hurtTime != 0 && this.timingOnGround.getSelectedValue().equals("NotWhileKB")) {
            return false;
         } else if (this.timingOnGround.getSelectedValue().equals("OnlyWhileKB")) {
            if (mc.thePlayer.hurtTime != 0 && (double)mc.thePlayer.hurtTime >= this.hurtTimeStrafeOnGround.getMinValue()) {
               if (((double)mc.thePlayer.hurtTime) <= this.hurtTimeStrafeOnGround.getMaxValue()) {
                  return true;
               }
            }

            return false;
         } else if (mc.thePlayer.hurtTime != 0) {
            if ((double)mc.thePlayer.hurtTime >= this.hurtTimeStrafeOnGround.getMinValue()) {
               if (((double)mc.thePlayer.hurtTime) <= this.hurtTimeStrafeOnGround.getMaxValue()) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      } else if (!this.strafeOnAir.getValue()) {
         return false;
      } else if (mc.thePlayer.hurtTime != 0 && this.timinInAir.getSelectedValue().equals("NotWhileKB")) {
         return false;
      } else if (this.timinInAir.getSelectedValue().equals("OnlyWhileKB")) {
         if (mc.thePlayer.hurtTime != 0 && (double)mc.thePlayer.hurtTime >= this.hurtTimeStrafeInAir.getMinValue()) {
            if (((double)mc.thePlayer.hurtTime) <= this.hurtTimeStrafeInAir.getMaxValue()) {
               return true;
            }
         }

         return false;
      } else if (mc.thePlayer.hurtTime != 0) {
         if ((double)mc.thePlayer.hurtTime >= this.hurtTimeStrafeInAir.getMinValue()) {
            if (((double)mc.thePlayer.hurtTime) <= this.hurtTimeStrafeInAir.getMaxValue()) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private boolean isHurtTimeInAirVisible() {
      return this.strafeOnAir.getValue() && !this.timinInAir.getSelectedValue().equals("NotWhileKB");
   }

   private boolean isHurtTimeOnGroundVisible() {
      return this.strafeOnGround.getValue() && !this.timingOnGround.getSelectedValue().equals("NotWhileKB");
   }

   private boolean isPercentageVisible() {
      return !this.mode.getSelectedValue().equals("Matrix");
   }


}
