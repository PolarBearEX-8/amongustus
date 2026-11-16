package client.util;

import client.event.PostMotionProcessor;

public class BaseByteArrayEntry implements PostMotionProcessor {
   public byte[] byteFlagArray;

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
   }

   public BaseByteArrayEntry(byte[] byteValueArray, String text, String text2, String text3) {
      this.byteFlagArray = byteValueArray;
   }

   public BaseByteArrayEntry() {
   }
}
