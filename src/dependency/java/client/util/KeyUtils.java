package client.util;

import org.lwjgl.input.Keyboard;

public class KeyUtils implements MinecraftAccessor {
   public static boolean checkIndex(int count) {
      return isActive() && checkCondition(count);
   }

   public static boolean isActive() {
      return checkCondition(29) || checkCondition(157);
   }

   private static int computeCount(int count, int count2) {
      if (checkIndexByIndex(count) && checkIndexByIndex(count2)) {
         int count3 = 0;
         if (Keyboard.isKeyDown(count)) {
            count3++;
         }

         if (Keyboard.isKeyDown(count2)) {
            count3--;
         }

         return count3;
      } else {
         return 0;
      }
   }

   public static boolean checkIndexByIndex(int count) {
      return count > 0 && count < 256;
   }

   public static int getInt2() {
      return computeCount(MinecraftAccessor.minecraft.gameSettings.keyBindLeft.getKeyCode(), MinecraftAccessor.minecraft.gameSettings.keyBindRight.getKeyCode());
   }

   public static int getInt3() {
      return computeCount(
         MinecraftAccessor.minecraft.gameSettings.keyBindForward.getKeyCode(), MinecraftAccessor.minecraft.gameSettings.keyBindBack.getKeyCode()
      );
   }

   public static boolean checkCondition(int count) {
      return checkIndexByIndex(count) && Keyboard.isCreated() && Keyboard.isKeyDown(count);
   }
}
