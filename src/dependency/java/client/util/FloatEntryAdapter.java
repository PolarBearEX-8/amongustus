package client.util;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.event.EventMove;
import client.event.MoveListener;
import client.module.Module;
import client.setting.ArrowDodgerPhase;
import client.setting.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;

public class FloatEntryAdapter extends MovementInput implements MinecraftAccessor {
   public final GameSettings gameSettings;
   public boolean enabled2 = false;
   public float factor8 = 0.0F;
   public float factor9;
   public float factor4;

   private void updateState() {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      int keyCode = this.gameSettings.keyBindForward.getKeyCode();
      int keyCode2 = this.gameSettings.keyBindRight.getKeyCode();
      int keyCode3 = this.gameSettings.keyBindLeft.getKeyCode();
      int keyCode4 = this.gameSettings.keyBindBack.getKeyCode();
      boolean flag = false;
      boolean flag2 = false;

      for (int index = KeyBinding.getPressedKeybinds().size() - 1; index >= 0; index--) {
         int keyCode5 = ((KeyBinding)KeyBinding.getPressedKeybinds().get(index)).getKeyCode();
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

   @Override
   public EventMove onMove(EventMove event) {
      float factor = this.factor8;
      boolean enabled = this.enabled2;
      float scaleFactor = event.getScaleFactor2();
      float scaleFactor2 = event.getScaleFactor7();
      boolean state = event.isState();
      boolean state2 = event.isState4();
      double coordOffset = event.getCoordOffset6();
      double coordOffset2 = event.getCoordOffset();
      if (state2) {
         scaleFactor = (float)((double)scaleFactor * coordOffset2);
         scaleFactor2 = (float)((double)scaleFactor2 * coordOffset);
      }

      if (enabled || event.getAuraRenderMode() != AuraRenderMode.NONE) {
         float factor2 = MathHelper.sqrt_float(event.getScaleFactor() + factor - Minecraft.getMinecraft().thePlayer.rotationYaw);
         float factor3 = 0.0F;
         float factor5 = 0.0F;
         float factor6;
         float factor7;
         if ((double)factor2 >= 22.5 && (double)factor2 < 67.5) {
            float factor10 = factor5 + scaleFactor;
            float factor11 = factor3 + scaleFactor2;
            factor7 = factor10 - scaleFactor2;
            factor6 = factor11 + scaleFactor;
         } else if ((double)factor2 >= 67.5 && (double)factor2 < 112.5) {
            factor7 = factor5 - scaleFactor2;
            factor6 = factor3 + scaleFactor;
         } else if ((double)factor2 >= 112.5 && (double)factor2 < 157.5) {
            float factor12 = factor5 - scaleFactor;
            float factor13 = factor3 - scaleFactor2;
            factor7 = factor12 - scaleFactor2;
            factor6 = factor13 + scaleFactor;
         } else if ((double)factor2 >= 157.5 || (double)factor2 <= -157.5) {
            factor7 = factor5 - scaleFactor;
            factor6 = factor3 - scaleFactor2;
         } else if ((double)factor2 > -157.5 && (double)factor2 <= -112.5) {
            float factor14 = factor5 - scaleFactor;
            float factor15 = factor3 - scaleFactor2;
            factor7 = factor14 + scaleFactor2;
            factor6 = factor15 - scaleFactor;
         } else if ((double)factor2 > -112.5 && (double)factor2 <= -67.5) {
            factor7 = factor5 + scaleFactor2;
            factor6 = factor3 - scaleFactor;
         } else if ((double)factor2 > -67.5 && (double)factor2 <= -22.5) {
            float factor16 = factor5 + scaleFactor;
            float factor17 = factor3 + scaleFactor2;
            factor7 = factor16 + scaleFactor2;
            factor6 = factor17 - scaleFactor;
         } else {
            factor7 = factor5 + scaleFactor;
            factor6 = factor3 + scaleFactor2;
         }

         if (state2) {
            scaleFactor2 = (float)MathHelper.getDouble7((double)factor6, -coordOffset, coordOffset);
            scaleFactor = (float)MathHelper.getDouble7((double)factor7, -coordOffset2, coordOffset2);
         } else {
            if (event.isState1()) {
               factor6 = (float)MathHelper.getDouble7((double)factor6, -1.0, 1.0);
               factor7 = (float)MathHelper.getDouble7((double)factor7, -1.0, 1.0);
               float factor18 = this.factor9 - factor6;
               if (factor18 >= 2.0F) {
                  factor6 = 0.0F;
               }

               if (factor18 <= -2.0F) {
                  factor6 = 0.0F;
               }
            }

            scaleFactor2 = factor6;
            scaleFactor = factor7;
         }
      }

      return new EventMove(scaleFactor, scaleFactor2, state, state2, ArrowDodgerPhase.PRE);
   }

   @Override
   public void applyFloat(float factor, float factor2) {
      this.factor9 = factor;
      this.factor4 = factor2;
      if (((Module)Client.getClient().getModuleManager().snapTap).isToggled() && !KeyBinding.getPressedKeybinds().isEmpty() && minecraft.currentScreen == null) {
         this.updateState();
      } else {
         this.updateState2();
      }

      this.factor2 = this.moveForward;
      this.factor = this.moveStrafe;
      this.state = this.gameSettings.keyBindJump.isKeyDown();
      this.state2 = this.gameSettings.keyBindSneak.isKeyDown();
      EventMove event = MoveListener.resolveEventMove(this.moveStrafe, this.moveForward, this.state, this.state2, ArrowDodgerPhase.PRE);
      this.moveStrafe = event.getScaleFactor2();
      this.moveForward = event.getScaleFactor7();
      this.state = event.isState();
      this.state2 = event.isState4();
      double coordOffset = event.getCoordOffset6();
      double coordOffset2 = event.getCoordOffset();
      if (this.state2) {
         this.moveStrafe = (float)((double)this.moveStrafe * coordOffset2);
         this.moveForward = (float)((double)this.moveForward * coordOffset);
      }

      float factor3 = this.moveForward;
      float factor5 = this.moveStrafe;
      if (!this.enabled2 && event.getAuraRenderMode() == AuraRenderMode.NONE) {
         this.factor8 = 0.0F;
      } else {
         float scaleFactor = event.getScaleFactor();
         if (factor3 < 0.0F) {
            scaleFactor += 180.0F;
         }

         float factor6 = 1.0F;
         if (factor3 < 0.0F) {
            factor6 = -0.5F;
         } else if (factor3 > 0.0F) {
            factor6 = 0.5F;
         }

         if (factor5 > 0.0F) {
            scaleFactor -= 90.0F * factor6;
         }

         if (factor5 < 0.0F) {
            scaleFactor += 90.0F * factor6;
         }

         float factor7 = MathHelper.sqrt_float(event.getScaleFactor() + this.factor8 - Minecraft.getMinecraft().thePlayer.rotationYaw);
         float factor10 = 0.0F;
         float factor11 = 0.0F;
         float factor12;
         float factor13;
         if ((double)factor7 >= 22.5 && (double)factor7 < 67.5) {
            float factor14 = factor11 + this.moveStrafe;
            float factor15 = factor10 + this.moveForward;
            factor13 = factor14 - this.moveForward;
            factor12 = factor15 + this.moveStrafe;
         } else if ((double)factor7 >= 67.5 && (double)factor7 < 112.5) {
            factor13 = factor11 - this.moveForward;
            factor12 = factor10 + this.moveStrafe;
         } else if ((double)factor7 >= 112.5 && (double)factor7 < 157.5) {
            float factor16 = factor11 - this.moveStrafe;
            float factor17 = factor10 - this.moveForward;
            factor13 = factor16 - this.moveForward;
            factor12 = factor17 + this.moveStrafe;
         } else if ((double)factor7 >= 157.5 || (double)factor7 <= -157.5) {
            factor13 = factor11 - this.moveStrafe;
            factor12 = factor10 - this.moveForward;
         } else if ((double)factor7 > -157.5 && (double)factor7 <= -112.5) {
            float factor18 = factor11 - this.moveStrafe;
            float factor19 = factor10 - this.moveForward;
            factor13 = factor18 + this.moveForward;
            factor12 = factor19 - this.moveStrafe;
         } else if ((double)factor7 > -112.5 && (double)factor7 <= -67.5) {
            factor13 = factor11 + this.moveForward;
            factor12 = factor10 - this.moveStrafe;
         } else if ((double)factor7 > -67.5 && (double)factor7 <= -22.5) {
            float factor20 = factor11 + this.moveStrafe;
            float factor21 = factor10 + this.moveForward;
            factor13 = factor20 + this.moveForward;
            factor12 = factor21 - this.moveStrafe;
         } else {
            factor13 = factor11 + this.moveStrafe;
            factor12 = factor10 + this.moveForward;
         }

         if (this.state2) {
            this.moveForward = (float)MathHelper.getDouble7((double)factor12, -coordOffset, coordOffset);
            this.moveStrafe = (float)MathHelper.getDouble7((double)factor13, -coordOffset2, coordOffset2);
         } else {
            if (event.isState1()) {
               factor12 = (float)MathHelper.getDouble7((double)factor12, -1.0, 1.0);
               factor13 = (float)MathHelper.getDouble7((double)factor13, -1.0, 1.0);
               float factor22 = factor - factor12;
               if (factor22 >= 2.0F) {
                  factor12 = 0.0F;
               }

               if (factor22 <= -2.0F) {
                  factor12 = 0.0F;
               }
            }

            this.moveForward = factor12;
            this.moveStrafe = factor13;
         }

         if (event.getAuraRenderMode() == AuraRenderMode.ADVANCED) {
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer);
            Vec3 vec = new Vec3(coordOffsetArray[0], coordOffsetArray[1], coordOffsetArray[2]);
            double offset = vec.xCoord - minecraft.thePlayer.posX;
            double offset2 = vec.zCoord - minecraft.thePlayer.posZ;
            float floatItem = SimulatedPlayer.getFloat9(scaleFactor, (float)(Math.atan2(offset2, offset) * 180.0 / Math.PI - 90.0), 180.0F);
            this.factor8 += scaleFactor - floatItem;
         } else {
            this.factor8 = 0.0F;
         }
      }

      this.enabled2 = event.getAuraRenderMode() != AuraRenderMode.NONE;
   }

   private void updateState2() {
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

   public FloatEntryAdapter(GameSettings gameSettings2) {
      this.gameSettings = gameSettings2;
   }
}
