package client.util;

import client.event.PostMotionProcessor;

public class AltByteArrayEntry implements PostMotionProcessor {
   public int count;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
      this.byteFlagArray = byteBufHandler.getByteArray();
   }

   public AltByteArrayEntry(int count2, byte[] byteValueArray) {
      this.count = count2;
      this.byteFlagArray = byteValueArray;
   }

   public AltByteArrayEntry() {
   }
}
