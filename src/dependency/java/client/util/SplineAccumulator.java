package client.util;

import client.setting.ItemPhysicsNoneType;

public class SplineAccumulator {
   public static final int[] countArray = new int[ItemPhysicsNoneType.values().length];

   static {
      try {
         countArray[ItemPhysicsNoneType.THIRD_PERSON.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[ItemPhysicsNoneType.FIRST_PERSON.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[ItemPhysicsNoneType.HEAD.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[ItemPhysicsNoneType.GUI.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[ItemPhysicsNoneType.GROUND.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[ItemPhysicsNoneType.FIXED.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
