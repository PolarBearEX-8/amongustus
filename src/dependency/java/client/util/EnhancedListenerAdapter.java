package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class EnhancedListenerAdapter implements PostMotionProcessor, PreUpdateInterceptor {
   public int count;
   public String labelText;
   public int count2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count3 = this.count;
      byteBufHandler.writeInt(count3);
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
      String labelText2 = this.labelText;
      byteBufHandler.applyTextByText(labelText2);
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray) {
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public EnhancedListenerAdapter(String text, int count3, int count4) {
      this.labelText = text;
      this.count = count3;
      this.count2 = count4;
   }

   public EnhancedListenerAdapter() {
   }
}
