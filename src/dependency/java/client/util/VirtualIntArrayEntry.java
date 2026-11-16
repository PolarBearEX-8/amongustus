package client.util;

import client.setting.EasingFunction;

public class VirtualIntArrayEntry {
   public static final int[] countArray = new int[EasingFunction.values().length];

   static {
      try {
         countArray[EasingFunction.easeInSine.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EasingFunction.easeOutSine.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[EasingFunction.easeInOutSine.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[EasingFunction.easeInQuad.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[EasingFunction.easeOutQuad.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[EasingFunction.easeInOutQuad.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[EasingFunction.easeInCubic.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[EasingFunction.easeOutCubic.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[EasingFunction.easeInOutCubic.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[EasingFunction.easeInQuart.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[EasingFunction.easeOutQuart.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[EasingFunction.easeInOutQuart.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[EasingFunction.easeInQuint.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[EasingFunction.easeOutQuint.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[EasingFunction.easeInOutQuint.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray[EasingFunction.easeInExpo.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         countArray[EasingFunction.easeOutExpo.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }

      try {
         countArray[EasingFunction.easeInOutExpo.ordinal()] = 18;
      } catch (NoSuchFieldError noSuchFieldError18) {
      }

      try {
         countArray[EasingFunction.easeInCirc.ordinal()] = 19;
      } catch (NoSuchFieldError noSuchFieldError19) {
      }

      try {
         countArray[EasingFunction.easeOutCirc.ordinal()] = 20;
      } catch (NoSuchFieldError noSuchFieldError20) {
      }

      try {
         countArray[EasingFunction.easeInOutCirc.ordinal()] = 21;
      } catch (NoSuchFieldError noSuchFieldError21) {
      }

      try {
         countArray[EasingFunction.easeInBack.ordinal()] = 22;
      } catch (NoSuchFieldError noSuchFieldError22) {
      }

      try {
         countArray[EasingFunction.easeOutBack.ordinal()] = 23;
      } catch (NoSuchFieldError noSuchFieldError23) {
      }

      try {
         countArray[EasingFunction.easeInOutBack.ordinal()] = 24;
      } catch (NoSuchFieldError noSuchFieldError24) {
      }

      try {
         countArray[EasingFunction.easeInElastic.ordinal()] = 25;
      } catch (NoSuchFieldError noSuchFieldError25) {
      }

      try {
         countArray[EasingFunction.easeOutElastic.ordinal()] = 26;
      } catch (NoSuchFieldError noSuchFieldError26) {
      }

      try {
         countArray[EasingFunction.easeInOutElastic.ordinal()] = 27;
      } catch (NoSuchFieldError noSuchFieldError27) {
      }

      try {
         countArray[EasingFunction.easeInBounce.ordinal()] = 28;
      } catch (NoSuchFieldError noSuchFieldError28) {
      }

      try {
         countArray[EasingFunction.easeOutBounce.ordinal()] = 29;
      } catch (NoSuchFieldError noSuchFieldError29) {
      }

      try {
         countArray[EasingFunction.easeInOutBounce.ordinal()] = 30;
      } catch (NoSuchFieldError noSuchFieldError30) {
      }

      try {
         countArray[EasingFunction.Linear.ordinal()] = 31;
      } catch (NoSuchFieldError noSuchFieldError31) {
      }

      try {
         countArray[EasingFunction.None.ordinal()] = 32;
      } catch (NoSuchFieldError noSuchFieldError32) {
      }
   }
}
