package cc.baritone.utils;

public class DataRIFFDDCControlleRecordImpl {
   public short[] shortCodeArray;
   public final DataRIFFDDCController dataRIFFDDCController;

   public DataRIFFDDCControlleRecordImpl(DataRIFFDDCController dataRIFFDDCController2) {
      this.dataRIFFDDCController = dataRIFFDDCController2;
      this.shortCodeArray = new short[2];
   }
}
