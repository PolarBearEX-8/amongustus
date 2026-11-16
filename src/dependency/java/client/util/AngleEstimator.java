package client.util;

import client.setting.ActiveOptionType;
import client.setting.MovementConfigProperty;

public class AngleEstimator {
   public static final int[] countArray = new int[MovementConfigProperty.values().length];
   public static final int[] countArray2;

   static {
      try {
         countArray[MovementConfigProperty.FREE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementConfigProperty.BODY.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementConfigProperty.HEAD.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementConfigProperty.ABOVE_HEAD.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementConfigProperty.RIGHT_ARM.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementConfigProperty.LEFT_ARM.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[MovementConfigProperty.RIGHT_LEG.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[MovementConfigProperty.LEFT_LEG.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      countArray2 = new int[ActiveOptionType.values().length];

      try {
         countArray2[ActiveOptionType.BOUNCE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray2[ActiveOptionType.ROTATE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray2[ActiveOptionType.FORWARD.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray2[ActiveOptionType.BACKWARD.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray2[ActiveOptionType.RESIZE.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray2[ActiveOptionType.MOTION.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }
   }
}
