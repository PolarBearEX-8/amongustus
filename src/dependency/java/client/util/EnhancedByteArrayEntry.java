package client.util;

import client.event.PostMotionProcessor;

public class EnhancedByteArrayEntry implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public long timestamp;
   public String labelText;
   public byte[] byteFlagArray2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText2 = this.labelText;
      byteBufHandler.applyText(labelText2);
      byte[] byteFlagArray3 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray3);
      byte[] byteFlagArray4 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray4);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText = byteBufHandler.getText();
      this.byteFlagArray2 = byteBufHandler.getByteArray();
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.timestamp = byteBufHandler.readLong();
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   public EnhancedByteArrayEntry() {
   }

   public EnhancedByteArrayEntry(String text, byte[] byteValueArray, byte[] byteValueArray2, long value) {
      this.labelText = text;
      this.byteFlagArray2 = byteValueArray;
      this.byteFlagArray = byteValueArray2;
      this.timestamp = value;
   }
}
