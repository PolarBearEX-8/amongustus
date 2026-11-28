package baritone.utils;

public final class FastMath {
   private static final double BIG_ENOUGH_FLOOR = 1.0737418E9F;
   private static final int BIG_ENOUGH_INT = 1073741824;

   public static int ceil(double d) {
      return 1073741824 - (int)(1.0737418E9F - d);
   }

   private FastMath() {
   }

   public static int floor(double d) {
      return (int)(d + 1.0737418E9F) - 1073741824;
   }
}
