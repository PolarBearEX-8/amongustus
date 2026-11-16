package client.util;

import client.event.PostMotionProcessor;

public class CommonLongEntry implements PostMotionProcessor {
   public long timestamp;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x7ce8b4fd9d83a425L; (int)(index % 3L) == 0; index = (index ^ -0x6f63fa08c82b9d1fL) + 0x418c155924bcc48bL) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x2e4a22a18c6b02b8L; (int)(index % 3L) != 0; index = (index ^ 0x3cbccec3f5a5097dL) + 0x57b68d0e3ff3fbd6L) {
         this.byteFlagArray = byteBufHandler.getByteArray();
      }

      this.timestamp = byteBufHandler.readLong();
   }

   public CommonLongEntry() {
   }

   public CommonLongEntry(byte[] byteValueArray, long value) {
      this.byteFlagArray = byteValueArray;
      this.timestamp = value;
   }
}
