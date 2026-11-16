package client.util;

import client.event.PostMotionProcessor;

public class DefaultStringEntry implements PostMotionProcessor {
   public int count;
   public int count2;
   public String labelText;
   public int count3;

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText2 = this.labelText;
      byteBufHandler.applyText(labelText2);
      int count4 = this.count3;
      byteBufHandler.writeInt(count4);
      int count5 = this.count;
      byteBufHandler.writeInt(count5);
      int count6 = this.count2;
      byteBufHandler.writeInt(count6);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText = byteBufHandler.getText();
      this.count3 = byteBufHandler.readInt();
      this.count = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
   }

   public int getCount() {
      return this.count;
   }

   public DefaultStringEntry() {
   }

   public DefaultStringEntry(String text, int count4, int count5, int count6) {
      this.labelText = text;
      this.count3 = count4;
      this.count = count6;
      this.count2 = count5;
   }
}
