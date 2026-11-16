package client.util;

import client.setting.MovementKeybindProperty;

public class SplineDispatcherMovementKeybindPropeSwitchMap {
   public static final int[] countArray = new int[MovementKeybindProperty.values().length];

   static {
      try {
         countArray[MovementKeybindProperty.ANTIALIASING.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementKeybindProperty.NORMAL_MAP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementKeybindProperty.SPECULAR_MAP.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementKeybindProperty.RENDER_RES_MUL.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementKeybindProperty.SHADOW_RES_MUL.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementKeybindProperty.HAND_DEPTH_MUL.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[MovementKeybindProperty.OLD_HAND_LIGHT.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[MovementKeybindProperty.OLD_LIGHTING.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[MovementKeybindProperty.TWEAK_BLOCK_DAMAGE.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[MovementKeybindProperty.CLOUD_SHADOW.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[MovementKeybindProperty.TEX_MIN_FIL_B.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[MovementKeybindProperty.TEX_MAG_FIL_N.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[MovementKeybindProperty.TEX_MAG_FIL_S.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[MovementKeybindProperty.SHADOW_CLIP_FRUSTRUM.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }
   }
}
