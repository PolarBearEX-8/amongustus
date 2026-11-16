package client.util;

import client.setting.MovementSliderProperty;
import client.setting.RenderChoiceProperty;
import client.setting.SecondaryOptionType;

public class DampenerEvaluator {
   public static final int[] countArray = new int[MovementSliderProperty.values().length];
   public static final int[] countArray2;
   public static final int[] countArray3;

   static {
      try {
         countArray[MovementSliderProperty.HORIZONTAL.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementSliderProperty.VERTICAL.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      countArray3 = new int[RenderChoiceProperty.values().length];

      try {
         countArray3[RenderChoiceProperty.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray3[RenderChoiceProperty.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray3[RenderChoiceProperty.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray3[RenderChoiceProperty.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray3[RenderChoiceProperty.UP.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray3[RenderChoiceProperty.DOWN.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      countArray2 = new int[SecondaryOptionType.values().length];

      try {
         countArray2[SecondaryOptionType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray2[SecondaryOptionType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray2[SecondaryOptionType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }
   }
}
