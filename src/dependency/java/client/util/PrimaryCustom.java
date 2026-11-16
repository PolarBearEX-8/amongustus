package client.util;

public class PrimaryCustom {
   public static final int[] countArray = new int[AimBuilder.values().length];

   static {
      try {
         countArray[AimBuilder.Z.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[AimBuilder.X.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[AimBuilder.Y.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
