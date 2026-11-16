package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class ListenerDispatcher implements PreUpdateInterceptor, PostMotionProcessor {
   public String labelText;
   public byte[] byteFlagArray;
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

   public int getCount() {
      return this.count;
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.labelText = byteBufHandler.getText();
      this.count = byteBufHandler.readInt();
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public ListenerDispatcher() {
   }

   public ListenerDispatcher(byte[] byteValueArray, String text, int count2) {
      this.byteFlagArray = byteValueArray;
      this.labelText = text;
      this.count = count2;
   }
}
