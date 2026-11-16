package client.util;

import client.event.PostMotionProcessor;

public class TertiaryByteArrayEntry implements PostMotionProcessor {
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
   }

   public TertiaryByteArrayEntry(byte[] byteValueArray) {
      this.byteFlagArray = byteValueArray;
   }

   public TertiaryByteArrayEntry() {
   }
}
