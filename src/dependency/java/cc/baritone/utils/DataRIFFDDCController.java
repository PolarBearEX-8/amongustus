package cc.baritone.utils;

public class DataRIFFDDCController extends RIFFDDCController {
   public static final int count12 = 2;
   public RuntimeComponent9 runtimeComponent9;
   public RecordNodeBridge recordNodeBridge2;
   public long timestamp = 0L;
   public int count13 = 0;

   public int computeCountByShortArray(short[] shortValueArray, int count) {
      int count2 = count * 2;
      this.recordNodeBridge2.count2 += count2;
      return super.computeCount(shortValueArray, count2);
   }

   public short getShortCode() {
      return this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode;
   }

   public int getCount13() {
      return this.count13;
   }

   public int computeCount(String text, DataRIFFDDCController dataRIFFDDCController) {
      return this.computeCount(text, dataRIFFDDCController.getCount2(), dataRIFFDDCController.getShortCodeAsShort(), dataRIFFDDCController.getShortCode());
   }

   @Override
   public int getCount() {
      int count = 0;
      if (this.count == 1) {
         count = this.computeCount(this.timestamp, this.recordNodeBridge2, 8);
      }

      if (count == 0) {
         count = super.getCount();
      }

      return count;
   }

   public int computeCount(String text, int count, short shortValue, short shortValue2) {
      if (text != null && (shortValue == 8 || shortValue == 16) && shortValue2 >= 1 && shortValue2 <= 2) {
         this.runtimeComponent9.dataRIFFDDCControlleRecord.applyIndex(count, shortValue, shortValue2);
         int count2 = this.computeCount(text, 1);
         if (count2 == 0) {
            byte[] byteValueArray = new byte[]{87, 65, 86, 69};
            count2 = this.computeCount(byteValueArray, 4);
            if (count2 == 0) {
               this.computeCount(this.runtimeComponent9.recordNodeBridge, 8);
               this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode2, 2);
               this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode, 2);
               this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.count, 4);
               this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.count2, 4);
               this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode3, 2);
               count2 = this.computeCount(this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode4, 2);
               if (count2 == 0) {
                  this.timestamp = this.getFilePointer();
                  count2 = this.computeCount(this.recordNodeBridge2, 8);
               }
            }
         }

         return count2;
      } else {
         return 4;
      }
   }

   public short getShortCodeAsShort() {
      return this.runtimeComponent9.dataRIFFDDCControlleRecord.shortCode4;
   }

   @Override
   public long getFilePointer() {
      return super.getFilePointer();
   }

   public int getCount2() {
      return this.runtimeComponent9.dataRIFFDDCControlleRecord.count;
   }

   public DataRIFFDDCController() {
      this.recordNodeBridge2 = new RecordNodeBridge(this);
      this.runtimeComponent9 = new RuntimeComponent9(this);
      this.recordNodeBridge2.count = computeCount("data");
      this.recordNodeBridge2.count2 = 0;
      this.count13 = 0;
   }
}
