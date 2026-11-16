package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class ExtendedByteArrayEntry implements PostMotionProcessor, PreUpdateInterceptor {
   public int count;
   public byte[] byteFlagArray;

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x2ad221a313bd143dL; (int)(index % 3L) == 2; index = (index ^ -0x578120b64eca41c8L) - 0x53aeb3490083171dL) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x227e5d926954efaL; (int)(index % 3L) != 2; index = (index ^ 0x4a010d830844ffcfL) + 0x590d9c6a735fdbeL) {
         this.byteFlagArray = byteBufHandler.getByteArray();
      }

      this.count = byteBufHandler.readInt();
   }

   public ExtendedByteArrayEntry(byte[] byteValueArray, int count2) {
      this.byteFlagArray = byteValueArray;
      this.count = count2;
   }

   public ExtendedByteArrayEntry() {
   }
}
