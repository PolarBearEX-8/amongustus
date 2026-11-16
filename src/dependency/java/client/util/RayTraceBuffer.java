package client.util;

import client.event.PostMotionProcessor;

public class RayTraceBuffer implements PostMotionProcessor {
   public int count;

   public int getCount() {
      return this.count;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
   }

   public RayTraceBuffer(int count2) {
      this.count = count2;
   }

   public RayTraceBuffer() {
   }
}
