package client.util;

public class IdleTimeEnumIdleTimeEnumSwitchMap {
   public static final int[] countArray = new int[IdleTimeEnum.values().length];

   static {
      try {
         countArray[IdleTimeEnum.HEALTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[IdleTimeEnum.HURT_TIME.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[IdleTimeEnum.IDLE_TIME.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[IdleTimeEnum.MAX_HEALTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[IdleTimeEnum.MOVE_FORWARD.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[IdleTimeEnum.MOVE_STRAFING.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[IdleTimeEnum.POS_X.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[IdleTimeEnum.POS_Y.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[IdleTimeEnum.POS_Z.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[IdleTimeEnum.REVENGE_TIME.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[IdleTimeEnum.SWING_PROGRESS.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[IdleTimeEnum.LIMB_SWING.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[IdleTimeEnum.LIMB_SWING_SPEED.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[IdleTimeEnum.AGE.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[IdleTimeEnum.HEAD_YAW.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray[IdleTimeEnum.HEAD_PITCH.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         countArray[IdleTimeEnum.SCALE.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }
   }
}
