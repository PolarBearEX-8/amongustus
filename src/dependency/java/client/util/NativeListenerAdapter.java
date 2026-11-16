package client.util;

import client.event.PostMotionProcessor;

public class NativeListenerAdapter implements PostMotionProcessor {
   public String labelText;
   public int count;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x1252ed40bcee7d45L; (int)(index % 3L) == 1; index = (index ^ 0x7c080d044db02041L) - 0x16d53a4f241c8994L) {
         String labelText2 = this.labelText;
         byteBufHandler.applyText(labelText2);
      }

      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText = byteBufHandler.getText();
      this.count = byteBufHandler.readInt();
   }

   public String getLabelText() {
      return this.labelText;
   }

   public NativeListenerAdapter() {
   }

   public NativeListenerAdapter(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }
}
