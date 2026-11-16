package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class InternalListenerProcessor implements PostMotionProcessor, PreUpdateInterceptor {
   public byte[] byteFlagArray;
   public String labelText;
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      String labelText2 = this.labelText;
      byteBufHandler.applyText(labelText2);
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.labelText = byteBufHandler.getText();
      this.count = byteBufHandler.readInt();
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public InternalListenerProcessor() {
   }

   public InternalListenerProcessor(byte[] byteValueArray, String text, int count2) {
      this.byteFlagArray = byteValueArray;
      this.labelText = text;
      this.count = count2;
   }
}
