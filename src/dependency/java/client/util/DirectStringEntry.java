package client.util;

import client.event.PostMotionProcessor;

public class DirectStringEntry implements PostMotionProcessor {
   public int count;
   public int count2;
   public String labelText;

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText2 = this.labelText;
      byteBufHandler.applyText(labelText2);
      int count3 = this.count;
      byteBufHandler.writeInt(count3);
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText = byteBufHandler.getText();
      this.count = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
   }

   public DirectStringEntry(String text, int count3, int count4) {
      this.labelText = text;
      this.count = count3;
      this.count2 = count4;
   }

   public DirectStringEntry() {
   }
}
