package client.util;

import client.event.PostMotionProcessor;

public class CommonByteArrayEntry implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public byte[] byteFlagArray2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x3c1640a62314df2eL; (int)(index % 3L) != 2; index = (index ^ 0x759ddae2cbed07c4L) - 0x3d50b0e945f34395L) {
         byte[] byteFlagArray3 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray3);
      }

      byte[] byteFlagArray4 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray4);
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.byteFlagArray2 = byteBufHandler.getByteArray();
   }

   public CommonByteArrayEntry(byte[] byteValueArray, byte[] byteValueArray2) {
      this.byteFlagArray = byteValueArray;
      this.byteFlagArray2 = byteValueArray2;
   }

   public CommonByteArrayEntry() {
   }
}
