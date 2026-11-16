package client.util;

import client.event.PostMotionProcessor;

public class StandardListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray3;
   public byte[] byteFlagArray;
   public byte[] byteFlagArray2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray4 = this.byteFlagArray3;
      byteBufHandler.applyByteArray(byteFlagArray4);
      byte[] byteFlagArray5 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray5);
      byte[] byteFlagArray6 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray6);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray3 = byteBufHandler.getByteArray();
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.byteFlagArray2 = byteBufHandler.getByteArray();
   }

   public StandardListenerAdapter(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3) {
      this.byteFlagArray3 = byteValueArray;
      this.byteFlagArray = byteValueArray2;
      this.byteFlagArray2 = byteValueArray3;
   }

   public StandardListenerAdapter() {
   }
}
