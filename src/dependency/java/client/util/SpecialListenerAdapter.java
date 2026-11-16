package client.util;

import client.event.PostMotionProcessor;

public class SpecialListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray4;
   public byte[] byteFlagArray;
   public byte[] byteFlagArray3;
   public byte[] byteFlagArray2;

   public byte[] getByteFlagArray4() {
      return this.byteFlagArray4;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray5 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray5);
      byte[] byteFlagArray6 = this.byteFlagArray4;
      byteBufHandler.applyByteArray(byteFlagArray6);
      byte[] byteFlagArray7 = this.byteFlagArray3;
      byteBufHandler.applyByteArray(byteFlagArray7);
      byte[] byteFlagArray8 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray8);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray2 = byteBufHandler.getByteArray();
      this.byteFlagArray4 = byteBufHandler.getByteArray();
      this.byteFlagArray3 = byteBufHandler.getByteArray();
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   public byte[] getByteFlagArray3() {
      return this.byteFlagArray3;
   }

   public SpecialListenerAdapter() {
   }

   public SpecialListenerAdapter(byte[] byteValueArray, byte[] byteValueArray2, byte[] byteValueArray3, byte[] byteValueArray4) {
      this.byteFlagArray2 = byteValueArray;
      this.byteFlagArray4 = byteValueArray2;
      this.byteFlagArray3 = byteValueArray3;
      this.byteFlagArray = byteValueArray4;
   }
}
