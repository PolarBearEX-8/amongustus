package net.augustus.utils.input;

import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import org.lwjgl.input.Keyboard;

public class KeyboardInputUtil implements MC {

   private static int getAxisInput(int n, int n2) {
      if (isValidKeyCode(n) && isValidKeyCode(n2)) {
         int n3 = 0;
         if (Keyboard.isKeyDown(n)) {
            n3++;
         }

         if (Keyboard.isKeyDown(n2)) {
            n3--;
         }

         return n3;
      } else {
         return 0;
      }
   }

   public static String getKeyDisplayName(int n) {
      return n >= 10000 ? "Mouse" + (n - 10000) : Keyboard.getKeyName(Math.max(n, 0));
   }

   public static boolean isValidKeyCode(int n) {
      return n > 0 && n < 256;
   }

   public static boolean isPureStrafe(float f, float f2) {
      return f == 0.0F && f2 != 0.0F;
   }

   public static boolean isPureForward(float f, float f2) {
      return f > 0.0F && f2 == 0.0F;
   }

   public static boolean isCtrlDown() {
      return isKeyDown(29) || isKeyDown(157);
   }

   public static int getForwardAxis() {
      return getAxisInput(mc.gameSettings.keyBindForward.getKeyCode(), mc.gameSettings.keyBindBack.getKeyCode());
   }

   public static boolean isCtrlKeyCombo(int n) {
      return isCtrlDown() && isKeyDown(n);
   }

   public static boolean isKeyDown(int n) {
      return isValidKeyCode(n) && Keyboard.isCreated() && Keyboard.isKeyDown(n);
   }

   public static boolean isBackwardDiagonal(float f, float f2) {
      return f < 0.0F && f2 != 0.0F;
   }

   public static boolean isPureBackward(float f, float f2) {
      return f < 0.0F && f2 == 0.0F;
   }

   public static int getStrafeAxis() {
      return getAxisInput(mc.gameSettings.keyBindLeft.getKeyCode(), mc.gameSettings.keyBindRight.getKeyCode());
   }

   public static boolean isForwardDiagonal(float f, float f2) {
      return f > 0.0F && f2 != 0.0F;
   }
}
