package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class AdvancedByteArrayEntry implements PostMotionProcessor, PreUpdateInterceptor {
   public int count;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x5585a1958dc074b7L; (int)(index % 3L) != 1; index = (index ^ 0x7a48c21c159932dbL) - 0x16a7611fcf1ebb03L) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
   }

   public AdvancedByteArrayEntry() {
   }

   public AdvancedByteArrayEntry(byte[] byteValueArray, int count2) {
      this.byteFlagArray = byteValueArray;
      this.count = count2;
   }
}
