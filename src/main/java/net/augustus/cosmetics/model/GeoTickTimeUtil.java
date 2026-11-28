package net.augustus.cosmetics.model;

public class GeoTickTimeUtil {
   public static double secondsToTicks(double d) {
      return d * 20.0;
   }

   public static double ticksToSeconds(double d) {
      return d / 20.0;
   }
}
