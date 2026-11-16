package cc.baritone.utils;

public class DataRIFFDDCControlleRecord {
   public short shortCode2;
   public short shortCode;
   public int count;
   public int count2;
   public short shortCode3;
   public short shortCode4;
   public final DataRIFFDDCController dataRIFFDDCController;

   public void applyIndex(int count3, short shortValue, short shortValue2) {
      this.count = count3;
      this.shortCode = shortValue2;
      this.shortCode4 = shortValue;
      this.count2 = this.shortCode * this.count * this.shortCode4 / 8;
      this.shortCode3 = (short)(this.shortCode * this.shortCode4 / 8);
   }

   public DataRIFFDDCControlleRecord(DataRIFFDDCController dataRIFFDDCController2) {
      this.dataRIFFDDCController = dataRIFFDDCController2;
      this.shortCode2 = 0;
      this.shortCode = 0;
      this.count = 0;
      this.count2 = 0;
      this.shortCode3 = 0;
      this.shortCode4 = 0;
      this.shortCode2 = 1;
      this.applyIndex(44100, (short)16, (short)1);
   }
}
