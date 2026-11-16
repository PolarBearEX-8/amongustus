package client.util;

import client.setting.BlockSolidType;
import client.setting.MovementKeybindProperty;
import client.setting.RenderStateProperty;
import client.setting.SharedOptionType;

public class AdapterIntArrayEntry {
   public static final int[] countArray3;
   public static final int[] countArray4;
   public static final int[] countArray2;
   public static final int[] countArray = new int[SharedOptionType.values().length];

   static {
      try {
         countArray[SharedOptionType.GBUFFERS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[SharedOptionType.DEFERRED.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[SharedOptionType.COMPOSITE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[SharedOptionType.SHADOW.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      countArray2 = new int[BlockSolidType.values().length];

      try {
         countArray2[BlockSolidType.SOLID.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[BlockSolidType.CUTOUT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[BlockSolidType.CUTOUT_MIPPED.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[BlockSolidType.TRANSLUCENT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      countArray4 = new int[RenderStateProperty.values().length];

      try {
         countArray4[RenderStateProperty.TEXTURE_1D.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray4[RenderStateProperty.TEXTURE_2D.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray4[RenderStateProperty.TEXTURE_3D.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray4[RenderStateProperty.TEXTURE_RECTANGLE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      countArray3 = new int[MovementKeybindProperty.values().length];

      try {
         countArray3[MovementKeybindProperty.ANTIALIASING.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray3[MovementKeybindProperty.NORMAL_MAP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray3[MovementKeybindProperty.SPECULAR_MAP.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray3[MovementKeybindProperty.RENDER_RES_MUL.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         countArray3[MovementKeybindProperty.SHADOW_RES_MUL.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }

      try {
         countArray3[MovementKeybindProperty.HAND_DEPTH_MUL.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError18) {
      }

      try {
         countArray3[MovementKeybindProperty.CLOUD_SHADOW.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError19) {
      }

      try {
         countArray3[MovementKeybindProperty.OLD_HAND_LIGHT.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError20) {
      }

      try {
         countArray3[MovementKeybindProperty.OLD_LIGHTING.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError21) {
      }

      try {
         countArray3[MovementKeybindProperty.SHADER_PACK.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError22) {
      }

      try {
         countArray3[MovementKeybindProperty.TWEAK_BLOCK_DAMAGE.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError23) {
      }

      try {
         countArray3[MovementKeybindProperty.SHADOW_CLIP_FRUSTRUM.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError24) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MIN_FIL_B.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError25) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MIN_FIL_N.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError26) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MIN_FIL_S.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError27) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MAG_FIL_B.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError28) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MAG_FIL_N.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError29) {
      }

      try {
         countArray3[MovementKeybindProperty.TEX_MAG_FIL_S.ordinal()] = 18;
      } catch (NoSuchFieldError noSuchFieldError30) {
      }
   }
}
