package client.util;

import client.event.PostMotionProcessor;

public class AltLongEntry implements PostMotionProcessor {
   public long timestamp;
   public int count;
   public byte[] byteFlagArray;

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.timestamp = byteBufHandler.readLong();
      this.count = byteBufHandler.readInt();
   }

   public AltLongEntry() {
   }

   public AltLongEntry(byte[] byteValueArray, long value, int count2) {
      this.byteFlagArray = byteValueArray;
      this.timestamp = value;
      this.count = count2;
   }
}
