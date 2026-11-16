package client.util;

public class FloatBufferControllerAdaXYTypeSwitchMap {
   public static final int[] countArray = new int[XYType.values().length];

   static {
      try {
         countArray[XYType.Z.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[XYType.X.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[XYType.Y.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
