package client.util;

import client.event.PostMotionProcessor;

public class TertiaryListenerAdapter implements PostMotionProcessor {
   public int count;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
   }

   public int getCount() {
      return this.count;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x7ce61690ea39d4edL; (int)(index % 3L) == 0; index = (index ^ -0x2587844df8ab8985L) - 0x6a39bc238c85f36eL) {
         this.count = byteBufHandler.readInt();
      }

      this.byteFlagArray = byteBufHandler.getByteArray();
   }

   public TertiaryListenerAdapter() {
   }

   public TertiaryListenerAdapter(int count2, byte[] byteValueArray) {
      this.count = count2;
      this.byteFlagArray = byteValueArray;
   }
}
