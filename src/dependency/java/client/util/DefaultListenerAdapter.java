package client.util;

import client.event.PostMotionProcessor;

public class DefaultListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public double value;
   public long timestamp;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      double value2 = this.value;
      byteBufHandler.writeDouble(value2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.timestamp = byteBufHandler.readLong();
      this.value = byteBufHandler.readDouble();
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public double getValue() {
      return this.value;
   }

   public DefaultListenerAdapter() {
   }

   public DefaultListenerAdapter(byte[] byteValueArray, long value2, double offset) {
      this.byteFlagArray = byteValueArray;
      this.timestamp = value2;
      this.value = offset;
   }
}
