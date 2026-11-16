package cc.baritone.utils;

public final class Helper {
   public static final double value = 1.0737418E9F;
   public static final int count = 1073741824;

   private Helper() {
   }

   public static int computeCount(double offset) {
      return 1073741824 - (int)(1.0737418E9F - offset);
   }

   public static int computeCountByDouble(double offset) {
      return (int)(offset + 1.0737418E9F) - 1073741824;
   }
}
