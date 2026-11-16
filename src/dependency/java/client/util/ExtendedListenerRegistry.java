package client.util;

import client.event.PostMotionProcessor;

public class ExtendedListenerRegistry implements PostMotionProcessor {
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
   }
}
