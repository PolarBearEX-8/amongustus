package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class AdvancedListenerAdapter implements PostMotionProcessor, PreUpdateInterceptor {
   public byte[] byteFlagArray;
   public int count;
   public int count2;
   public boolean enabled;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
      int count3 = this.count;
      byteBufHandler.writeInt(count3);
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
   }

   public AdvancedListenerAdapter(byte[] byteValueArray, boolean flag, int count3, int count4) {
      this.byteFlagArray = byteValueArray;
      this.enabled = flag;
      this.count = count3;
      this.count2 = count4;
   }

   public AdvancedListenerAdapter() {
   }
}
