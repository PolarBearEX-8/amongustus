package client.util;

import client.event.PostMotionProcessor;

public class LongPair implements PostMotionProcessor {
   public long timestamp;
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x22791195350da2abL; (int)(index % 3L) == 0; index = (index ^ -0x48865ae39d64bb1dL) - 0x3458de1cea2bf8c1L) {
         this.timestamp = byteBufHandler.readLong();
      }

      this.count = byteBufHandler.readInt();
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public LongPair(long value, int count2) {
      this.timestamp = value;
      this.count = count2;
   }

   public LongPair() {
   }
}
