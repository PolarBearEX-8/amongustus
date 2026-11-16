package client.util;

import client.setting.MovementProperty;
import client.setting.MovementScaleProperty;

public class PrimaryIntArrayEntry {
   public static final int[] countArray;
   public static final int[] countArray2 = new int[MovementScaleProperty.values().length];

   static {
      try {
         countArray2[MovementScaleProperty.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray2[MovementScaleProperty.Z.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray2[MovementScaleProperty.NONE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray2[MovementScaleProperty.Y.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      countArray = new int[MovementProperty.values().length];

      try {
         countArray[MovementProperty.ACACIA.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementProperty.DARK_OAK.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
