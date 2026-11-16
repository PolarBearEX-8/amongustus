package client.util;

public class ExtendedFloatArrayEntry {
   public float[] factorArray;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray;
   public int count2;
   public int count;
   public long timestamp;
   public final AuxiliaryInvalidBiomeIdManagerEntry auxiliaryInvalidBiomeIdManagerEntry;

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count3, int count4) {
      return this.biomeIDIsOutOfBoundsManagerArray[count3 & 15 | (count4 & 15) << 4];
   }

   public ExtendedFloatArrayEntry(AuxiliaryInvalidBiomeIdManagerEntry auxiliaryInvalidBiomeIdManagerEntry2, int count3, int count4) {
      this.auxiliaryInvalidBiomeIdManagerEntry = auxiliaryInvalidBiomeIdManagerEntry2;
      this.factorArray = new float[256];
      this.biomeIDIsOutOfBoundsManagerArray = new BiomeIDIsOutOfBoundsManager[256];
      this.count2 = count3;
      this.count = count4;
      AuxiliaryInvalidBiomeIdManagerEntry.resolveInvalidBiomeIdManager(auxiliaryInvalidBiomeIdManagerEntry2).resolveFloatArray(this.factorArray, count3 << 4, count4 << 4, 16, 16);
      AuxiliaryInvalidBiomeIdManagerEntry.resolveInvalidBiomeIdManager(auxiliaryInvalidBiomeIdManagerEntry2)
         .resolveBiomeIDIsOutOfBoundsManagerA(this.biomeIDIsOutOfBoundsManagerArray, count3 << 4, count4 << 4, 16, 16, false);
   }
}
