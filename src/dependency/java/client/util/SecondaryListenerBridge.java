package client.util;

import client.event.PostMotionProcessor;

public class SecondaryListenerBridge implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public byte[] byteFlagArray2;
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray3 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray3);
      byte[] byteFlagArray4 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray4);
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray2 = byteBufHandler.getByteArray();
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.count = byteBufHandler.readInt();
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public int getCount() {
      return this.count;
   }

   public SecondaryListenerBridge() {
   }

   public SecondaryListenerBridge(byte[] byteValueArray, byte[] byteValueArray2, int count2) {
      this.byteFlagArray2 = byteValueArray;
      this.byteFlagArray = byteValueArray2;
      this.count = count2;
   }
}
