package client.util;

import client.event.PostMotionProcessor;

public class InterpolationReader implements PostMotionProcessor {
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
   }

   public int getCount() {
      return this.count;
   }

   public InterpolationReader() {
   }

   public InterpolationReader(int count2) {
      this.count = count2;
   }
}
