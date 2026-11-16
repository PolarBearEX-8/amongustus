package client.util;

public class AimSorterAimValidator implements AimValidator {
   public BiomeTemperatureType biomeTemperatureType;
   public int count;
   public int count2;

   @Override
   public String toString() {
      return this.biomeTemperatureType.getStringArray() == null
         ? "" + this.biomeTemperatureType
         : (
            this.biomeTemperatureType.getStringArray2() == null
               ? "" + this.biomeTemperatureType + "." + this.biomeTemperatureType.getStringArray()[this.count]
               : ""
                  + this.biomeTemperatureType
                  + "."
                  + this.biomeTemperatureType.getStringArray()[this.count]
                  + "."
                  + this.biomeTemperatureType.getStringArray2()[this.count2]
         );
   }

   @Override
   public float getFactor() {
      return this.biomeTemperatureType.computeFactor(this.count, this.count2);
   }

   public AimSorterAimValidator(BiomeTemperatureType biomeTemperatureType2) {
      this(biomeTemperatureType2, 0, 0);
   }

   public AimSorterAimValidator(BiomeTemperatureType biomeTemperatureType2, int count3, int count4) {
      this.biomeTemperatureType = biomeTemperatureType2;
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   public AimSorterAimValidator(BiomeTemperatureType biomeTemperatureType2, int count3) {
      this(biomeTemperatureType2, count3, 0);
   }
}
