package client.util;

public class BiomeIDIsOutOfBoundsManagerArrayEntry extends ComparingBiomesManager {
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[]{
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager7,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager7,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35
   };
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray3 = new BiomeIDIsOutOfBoundsManager[]{
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager22,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16
   };
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = new BiomeIDIsOutOfBoundsManager[]{
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35
   };
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray4 = new BiomeIDIsOutOfBoundsManager[]{
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager19
   };
   public final GlobalFloatEntry globalFloatEntry;

   @Override
   public int[] resolveIndexArray(int count, int count2, int count3, int count4) {
      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count, count2, count3, count4);
      int[] countArray2 = CacheManager.resolveIndexArray(count3 * count4);

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            this.applyTime((long)(index2 + count), (long)(index + count2));
            int count5 = countArray[index2 + index * count3];
            int count6 = (count5 & 3840) >> 8;
            int count7 = count5 & -3841;
            if (this.globalFloatEntry != null && this.globalFloatEntry.count34 >= 0) {
               countArray2[index2 + index * count3] = this.globalFloatEntry.count34;
            } else if (checkIndex(count7)) {
               countArray2[index2 + index * count3] = count7;
            } else if (count7 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager32.count) {
               countArray2[index2 + index * count3] = count7;
            } else if (count7 == 1) {
               if (count6 > 0) {
                  if (this.computeCount(3) == 0) {
                     countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager30.count;
                  } else {
                     countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count;
                  }
               } else {
                  countArray2[index2 + index * count3] = this.biomeIDIsOutOfBoundsManagerArray2[this.computeCount(this.biomeIDIsOutOfBoundsManagerArray2.length)].count;
               }
            } else if (count7 == 2) {
               if (count6 > 0) {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23.count;
               } else {
                  countArray2[index2 + index * count3] = this.biomeIDIsOutOfBoundsManagerArray3[this.computeCount(this.biomeIDIsOutOfBoundsManagerArray3.length)].count;
               }
            } else if (count7 == 3) {
               if (count6 > 0) {
                  countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager33.count;
               } else {
                  countArray2[index2 + index * count3] = this.biomeIDIsOutOfBoundsManagerArray[this.computeCount(this.biomeIDIsOutOfBoundsManagerArray.length)].count;
               }
            } else if (count7 == 4) {
               countArray2[index2 + index * count3] = this.biomeIDIsOutOfBoundsManagerArray4[this.computeCount(this.biomeIDIsOutOfBoundsManagerArray4.length)].count;
            } else {
               countArray2[index2 + index * count3] = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager32.count;
            }
         }
      }

      return countArray2;
   }

   public BiomeIDIsOutOfBoundsManagerArrayEntry(long value, ComparingBiomesManager comparingBiomesManager, InfoManager infoManager, String text) {
      super(value);
      this.comparingBiomesManager = comparingBiomesManager;
      if (infoManager == InfoManager.infoManager6) {
         this.biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[]{
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4,
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21,
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16,
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
            BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15
         };
         this.globalFloatEntry = null;
      } else if (infoManager == InfoManager.infoManager3) {
         this.globalFloatEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(text).getGlobalFloatEntry();
      } else {
         this.globalFloatEntry = null;
      }
   }
}
