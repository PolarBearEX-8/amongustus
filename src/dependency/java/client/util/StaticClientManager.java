package client.util;

import client.event.PostMotionProcessor;

public class StaticClientManager implements PostMotionProcessor {
   public int count;
   public int count2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x41721d06d5283290L; (int)(index % 3L) != 0; index = (index ^ 0x3c47653f01266aadL) - 0x12edab996abc91e4L) {
         int count3 = this.count2;
         byteBufHandler.writeInt(count3);
      }

      int count4 = this.count;
      byteBufHandler.writeInt(count4);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x5df532c67fb07674L; (int)(index % 3L) == 1; index = (index ^ -0x417b0a3e2468bf79L) + 0x2e2be3fc9ff7569fL) {
         this.count2 = byteBufHandler.readInt();
      }

      this.count = byteBufHandler.readInt();
   }

   public int getCount2() {
      return this.count2;
   }

   public StaticClientManager(int count3) {
      this.count = count3;
   }

   public StaticClientManager() {
   }
}
