package client.util;

import client.event.PostMotionProcessor;

public class ListenerAdapter12 implements PostMotionProcessor {
   public long timestamp;

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.timestamp = byteBufHandler.readLong();
   }

   public ListenerAdapter12(long value) {
      this.timestamp = value;
   }

   public ListenerAdapter12() {
   }
}
