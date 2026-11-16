package client.util;

import client.event.PostMotionProcessor;

public class SupplementalListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public int count;
   public byte[] byteFlagArray2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      byte[] byteFlagArray3 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray3);
      byte[] byteFlagArray4 = this.byteFlagArray;
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
      this.count = byteBufHandler.readInt();
      this.byteFlagArray2 = byteBufHandler.getByteArray();
      this.byteFlagArray = byteBufHandler.getByteArray();
   }

   public int getCount() {
      return this.count;
   }

   public SupplementalListenerAdapter(int count2, byte[] byteValueArray, byte[] byteValueArray2) {
      this.count = count2;
      this.byteFlagArray2 = byteValueArray;
      this.byteFlagArray = byteValueArray2;
   }

   public SupplementalListenerAdapter() {
   }
}
