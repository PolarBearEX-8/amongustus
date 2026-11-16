package client.util;

public class Helper2 {
   public static float computeFactor(boolean flag) {
      return flag ? 1.0F : 0.0F;
   }

   public static float computeFactor(long value) {
      return (float)value / 24000.0F;
   }
}
