package cc.baritone.utils;

public class FileNameExtraCustom extends ExtraCustom {
   public short[] shortCodeArray;
   public short[] shortCodeArray2;
   public int count3;
   public DataRIFFDDCController dataRIFFDDCController;
   public short[] shortCodeArray3 = new short[2];

   @Override
   public void updateState() {
   }

   @Override
   public void applyIndex(int count, short shortValue) {
      this.shortCodeArray[this.shortCodeArray2[count]] = shortValue;
      this.shortCodeArray2[count] = (short)(this.shortCodeArray2[count] + this.count3);
   }

   public FileNameExtraCustom(int count, int count2, String text) {
      if (text == null) {
         throw new NullPointerException("FileName");
      } else {
         this.shortCodeArray = new short[2304];
         this.shortCodeArray2 = new short[2];
         this.count3 = count;

         for (int index = 0; index < count; index++) {
            this.shortCodeArray2[index] = (short)index;
         }

         this.dataRIFFDDCController = new DataRIFFDDCController();
         this.dataRIFFDDCController.computeCount(text, count2, (short)16, (short)this.count3);
      }
   }

   @Override
   public void updateState3() {
      this.dataRIFFDDCController.getCount();
   }

   @Override
   public void applyIndex(int count) {
      this.dataRIFFDDCController.computeCountByShortArray(this.shortCodeArray, this.shortCodeArray2[0]);

      for (int index = 0; index < this.count3; index++) {
         this.shortCodeArray2[index] = (short)index;
      }
   }

   @Override
   public void updateState2() {
   }
}
