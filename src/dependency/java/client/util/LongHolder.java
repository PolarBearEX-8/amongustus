package client.util;

import client.event.PostMotionProcessor;

public class LongHolder implements PostMotionProcessor {
   public long timestamp;
   public byte[] byteFlagArray;
   public byte[] byteFlagArray2;

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      byte[] byteFlagArray3 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray3);
      byte[] byteFlagArray4 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray4);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.timestamp = byteBufHandler.readLong();
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.byteFlagArray2 = byteBufHandler.getByteArray();
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   public LongHolder(long value, byte[] byteValueArray, byte[] byteValueArray2) {
      this.timestamp = value;
      this.byteFlagArray = byteValueArray;
      this.byteFlagArray2 = byteValueArray2;
   }

   public LongHolder() {
   }
}
