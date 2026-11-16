package client.util;

import client.setting.CombatFactorProperty;
import client.setting.MovementProperty;

public class DampenerCalculator {
   public static final int[] countArray;
   public static final int[] countArray2 = new int[CombatFactorProperty.values().length];

   static {
      try {
         countArray2[CombatFactorProperty.POPPY.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray2[CombatFactorProperty.BLUE_ORCHID.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray2[CombatFactorProperty.ALLIUM.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray2[CombatFactorProperty.HOUSTONIA.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray2[CombatFactorProperty.RED_TULIP.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[CombatFactorProperty.ORANGE_TULIP.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[CombatFactorProperty.WHITE_TULIP.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[CombatFactorProperty.PINK_TULIP.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray2[CombatFactorProperty.OXEYE_DAISY.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      countArray = new int[MovementProperty.values().length];

      try {
         countArray[MovementProperty.OAK.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[MovementProperty.SPRUCE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[MovementProperty.BIRCH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[MovementProperty.JUNGLE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[MovementProperty.ACACIA.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[MovementProperty.DARK_OAK.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }
   }
}
