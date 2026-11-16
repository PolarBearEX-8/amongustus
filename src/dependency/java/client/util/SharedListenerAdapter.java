package client.util;

import client.event.PostMotionProcessor;

public class SharedListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public long timestamp;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x29400d30d014fecaL; (int)(index % 3L) != 2; index = (index ^ -0x46844c498ad5b3d9L) - 0x7e622cdbf9e0969cL) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x3dd8c67395635bf7L; (int)(index % 3L) != 2; index = (index ^ -0x49bfb5a46648d9daL) - 0x5dbc4689679fb2a8L) {
         this.byteFlagArray = byteBufHandler.getByteArray();
      }

      this.timestamp = byteBufHandler.readLong();
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public SharedListenerAdapter() {
   }

   public SharedListenerAdapter(byte[] byteValueArray, long value) {
      this.byteFlagArray = byteValueArray;
      this.timestamp = value;
   }
}
