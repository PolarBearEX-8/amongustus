package client.util;

public class BiomeTemperatureTypeBiomeTemperatureTypeSwitch {
   public static final int[] countArray = new int[BiomeTemperatureType.values().length];

   static {
      try {
         countArray[BiomeTemperatureType.BIOME.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[BiomeTemperatureType.TEMPERATURE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[BiomeTemperatureType.RAINFALL.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
