package client.util;

import client.event.PostMotionProcessor;

public class AuxiliaryByteArrayHolder implements PostMotionProcessor {
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

   public AuxiliaryByteArrayHolder() {
   }

   public AuxiliaryByteArrayHolder(byte[] byteValueArray) {
      this.byteFlagArray = byteValueArray;
   }
}
