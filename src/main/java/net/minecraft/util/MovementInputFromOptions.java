package net.minecraft.util;

import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class MovementInputFromOptions extends MovementInput implements MC {
   private final GameSettings gameSettings;
   private boolean moveFixActive = false;
   private float yawDriftOffset = 0.0F;
   private float targetYaw;
   private float targetPitch;

   private void updateNullMovementKeys() {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      int keyCode = this.gameSettings.keyBindForward.getKeyCode();
      int keyCode2 = this.gameSettings.keyBindRight.getKeyCode();
      int keyCode3 = this.gameSettings.keyBindLeft.getKeyCode();
      int keyCode4 = this.gameSettings.keyBindBack.getKeyCode();
      boolean flag = false;
      boolean flag2 = false;

      for (int i = KeyBinding.getPressedMovementKeys().size() - 1; i >= 0; i--) {
         int keyCode5 = ((KeyBinding)KeyBinding.getPressedMovementKeys().get(i)).getKeyCode();
         if (!flag && (keyCode5 == keyCode || keyCode5 == keyCode4)) {
            if (keyCode5 == keyCode) {
               this.moveForward = 1.0F;
            } else if (this.gameSettings.keyBindBack.isKeyDown()) {
               this.moveForward = -1.0F;
            }

            flag = true;
         }

         if (!flag2 && (keyCode5 == keyCode3 || keyCode5 == keyCode2)) {
            if (keyCode5 == keyCode3) {
               this.moveStrafe = 1.0F;
            } else if (this.gameSettings.keyBindRight.isKeyDown()) {
               this.moveStrafe = -1.0F;
            }

            flag2 = true;
         }
      }
   }

   public void updatePlayerMoveState(float f, float f2) {
      this.targetYaw = f;
      this.targetPitch = f2;
      if (((Module)Augustus.getInstance().getModuleManager().snapTap).isToggled() && !KeyBinding.getPressedMovementKeys().isEmpty() && mc.currentScreen == null) {
         this.updateNullMovementKeys();
      } else {
         this.updateVanillaMovementKeys();
      }

      this.rawMoveForward = this.moveForward;
      this.rawMoveStrafe = this.moveStrafe;
      this.jump = this.gameSettings.keyBindJump.isKeyDown();
      this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
      EventMoveInput event = MoveInputListener.postMoveInput(this.moveStrafe, this.moveForward, this.jump, this.sneak, MoveInputStage.PRE);
      this.moveStrafe = event.getMoveStrafe();
      this.moveForward = event.getMoveForward();
      this.jump = event.isJump();
      this.sneak = event.isSneak();
      double sneakForwardMultiplier = event.getSneakForwardMultiplier();
      double sneakStrafeMultiplier = event.getSneakStrafeMultiplier();
      if (this.sneak) {
         this.moveStrafe = (float)((double)this.moveStrafe * sneakStrafeMultiplier);
         this.moveForward = (float)((double)this.moveForward * sneakForwardMultiplier);
      }

      float moveForward2 = this.moveForward;
      float moveStrafe2 = this.moveStrafe;
      if (!this.moveFixActive && event.getMoveFixMode() == MoveFixMode.NONE) {
         this.yawDriftOffset = 0.0F;
      } else {
         float yaw = event.getYaw();
         if (moveForward2 < 0.0F) {
            yaw += 180.0F;
         }

         float f3 = 1.0F;
         if (moveForward2 < 0.0F) {
            f3 = -0.5F;
         } else if (moveForward2 > 0.0F) {
            f3 = 0.5F;
         }

         if (moveStrafe2 > 0.0F) {
            yaw -= 90.0F * f3;
         }

         if (moveStrafe2 < 0.0F) {
            yaw += 90.0F * f3;
         }

         float f4 = MathHelper.wrapAngleTo180_float(event.getYaw() + this.yawDriftOffset - Minecraft.getMinecraft().thePlayer.rotationYaw);
         float f5 = 0.0F;
         float f6 = 0.0F;
         if ((double)f4 >= 22.5 && (double)f4 < 67.5) {
            f6 += this.moveStrafe;
            f5 += this.moveForward;
            f6 -= this.moveForward;
            f5 += this.moveStrafe;
         } else if ((double)f4 >= 67.5 && (double)f4 < 112.5) {
            f6 -= this.moveForward;
            f5 += this.moveStrafe;
         } else if ((double)f4 >= 112.5 && (double)f4 < 157.5) {
            f6 -= this.moveStrafe;
            f5 -= this.moveForward;
            f6 -= this.moveForward;
            f5 += this.moveStrafe;
         } else if ((double)f4 >= 157.5 || (double)f4 <= -157.5) {
            f6 -= this.moveStrafe;
            f5 -= this.moveForward;
         } else if ((double)f4 > -157.5 && (double)f4 <= -112.5) {
            f6 -= this.moveStrafe;
            f5 -= this.moveForward;
            f6 += this.moveForward;
            f5 -= this.moveStrafe;
         } else if ((double)f4 > -112.5 && (double)f4 <= -67.5) {
            f6 += this.moveForward;
            f5 -= this.moveStrafe;
         } else if ((double)f4 > -67.5 && (double)f4 <= -22.5) {
            f6 += this.moveStrafe;
            f5 += this.moveForward;
            f6 += this.moveForward;
            f5 -= this.moveStrafe;
         } else {
            f6 += this.moveStrafe;
            f5 += this.moveForward;
         }

         if (this.sneak) {
            this.moveForward = (float)MathHelper.clamp_double((double)f5, -sneakForwardMultiplier, sneakForwardMultiplier);
            this.moveStrafe = (float)MathHelper.clamp_double((double)f6, -sneakStrafeMultiplier, sneakStrafeMultiplier);
         } else {
            f5 = (float)MathHelper.clamp_double((double)f5, -1.0, 1.0);
            f6 = (float)MathHelper.clamp_double((double)f6, -1.0, 1.0);
            if (event.isPreventInstantReverse()) {
               float f7 = f - f5;
               if (f7 >= 2.0F) {
                  f5 = 0.0F;
               }

               if (f7 <= -2.0F) {
                  f5 = 0.0F;
               }
            }

            this.moveForward = f5;
            this.moveStrafe = f6;
         }

         if (event.getMoveFixMode() == MoveFixMode.ADVANCED) {
            double[] doubles = PlayerUtil.predictPlayerPosition(0, mc.thePlayer);
            Vec3 vec = new Vec3(doubles[0], doubles[1], doubles[2]);
            double d = vec.xCoord - mc.thePlayer.posX;
            float f8 = RotationUtil.updateAngle(yaw, (float)(Math.atan2(vec.zCoord - mc.thePlayer.posZ, d) * 180.0 / 3.141592653589793 - 90.0), 180.0F);
            this.yawDriftOffset += yaw - f8;
         } else {
            this.yawDriftOffset = 0.0F;
         }
      }

      this.moveFixActive = event.getMoveFixMode() != MoveFixMode.NONE;
   }

   public EventMoveInput applyMoveFix(EventMoveInput event) {
      float yawDriftOffset2 = this.yawDriftOffset;
      boolean moveFixActive2 = this.moveFixActive;
      float moveStrafe2 = event.getMoveStrafe();
      float moveForward2 = event.getMoveForward();
      boolean isJump = event.isJump();
      boolean isSneak = event.isSneak();
      double sneakForwardMultiplier = event.getSneakForwardMultiplier();
      double sneakStrafeMultiplier = event.getSneakStrafeMultiplier();
      if (isSneak) {
         moveStrafe2 = (float)((double)moveStrafe2 * sneakStrafeMultiplier);
         moveForward2 = (float)((double)moveForward2 * sneakForwardMultiplier);
      }

      if (moveFixActive2 || event.getMoveFixMode() != MoveFixMode.NONE) {
         float f = MathHelper.wrapAngleTo180_float(event.getYaw() + yawDriftOffset2 - Minecraft.getMinecraft().thePlayer.rotationYaw);
         float f2 = 0.0F;
         float f3 = 0.0F;
         if ((double)f >= 22.5 && (double)f < 67.5) {
            f3 += moveStrafe2;
            f2 += moveForward2;
            f3 -= moveForward2;
            f2 += moveStrafe2;
         } else if ((double)f >= 67.5 && (double)f < 112.5) {
            f3 -= moveForward2;
            f2 += moveStrafe2;
         } else if ((double)f >= 112.5 && (double)f < 157.5) {
            f3 -= moveStrafe2;
            f2 -= moveForward2;
            f3 -= moveForward2;
            f2 += moveStrafe2;
         } else if ((double)f >= 157.5 || (double)f <= -157.5) {
            f3 -= moveStrafe2;
            f2 -= moveForward2;
         } else if ((double)f > -157.5 && (double)f <= -112.5) {
            f3 -= moveStrafe2;
            f2 -= moveForward2;
            f3 += moveForward2;
            f2 -= moveStrafe2;
         } else if ((double)f > -112.5 && (double)f <= -67.5) {
            f3 += moveForward2;
            f2 -= moveStrafe2;
         } else if ((double)f > -67.5 && (double)f <= -22.5) {
            f3 += moveStrafe2;
            f2 += moveForward2;
            f3 += moveForward2;
            f2 -= moveStrafe2;
         } else {
            f3 += moveStrafe2;
            f2 += moveForward2;
         }

         if (isSneak) {
            moveForward2 = (float)MathHelper.clamp_double((double)f2, -sneakForwardMultiplier, sneakForwardMultiplier);
            moveStrafe2 = (float)MathHelper.clamp_double((double)f3, -sneakStrafeMultiplier, sneakStrafeMultiplier);
         } else {
            if (event.isPreventInstantReverse()) {
               f2 = (float)MathHelper.clamp_double((double)f2, -1.0, 1.0);
               f3 = (float)MathHelper.clamp_double((double)f3, -1.0, 1.0);
               float f4 = this.targetYaw - f2;
               if (f4 >= 2.0F) {
                  f2 = 0.0F;
               }

               if (f4 <= -2.0F) {
                  f2 = 0.0F;
               }
            }

            moveForward2 = f2;
            moveStrafe2 = f3;
         }
      }

      return new EventMoveInput(moveStrafe2, moveForward2, isJump, isSneak, MoveInputStage.PRE);
   }

   public MovementInputFromOptions(GameSettings gameSettings2) {
      this.gameSettings = gameSettings2;
   }

   private void updateVanillaMovementKeys() {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      if (this.gameSettings.keyBindForward.isKeyDown()) {
         this.moveForward++;
      }

      if (this.gameSettings.keyBindBack.isKeyDown()) {
         this.moveForward--;
      }

      if (this.gameSettings.keyBindLeft.isKeyDown()) {
         this.moveStrafe++;
      }

      if (this.gameSettings.keyBindRight.isKeyDown()) {
         this.moveStrafe--;
      }
   }
}
