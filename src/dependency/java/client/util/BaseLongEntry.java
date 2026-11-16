package client.util;

import client.event.PostMotionProcessor;

public class BaseLongEntry implements PostMotionProcessor {
   public long timestamp;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.timestamp = byteBufHandler.readLong();
   }
}
