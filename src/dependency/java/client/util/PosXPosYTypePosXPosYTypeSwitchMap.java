package client.util;

public class PosXPosYTypePosXPosYTypeSwitchMap {
   public static final int[] countArray = new int[PosXPosYType.values().length];

   static {
      try {
         countArray[PosXPosYType.POS_X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[PosXPosYType.POS_Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[PosXPosYType.POS_Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[PosXPosYType.ANGLE_X.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[PosXPosYType.ANGLE_Y.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[PosXPosYType.ANGLE_Z.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[PosXPosYType.OFFSET_X.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[PosXPosYType.OFFSET_Y.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[PosXPosYType.OFFSET_Z.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[PosXPosYType.SCALE_X.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[PosXPosYType.SCALE_Y.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[PosXPosYType.SCALE_Z.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }
   }
}
