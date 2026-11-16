package client.util;

import client.event.PostMotionProcessor;

public class ExtraLongEntry implements PostMotionProcessor {
   public long timestamp;
   public String labelText;
   public int count2;
   public int count3;
   public int count;

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
      int count5 = this.count;
      byteBufHandler.writeInt(count5);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      int count6 = this.count3;
      byteBufHandler.writeInt(count6);
      String labelText2 = this.labelText;
      byteBufHandler.applyTextByText(labelText2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count2 = byteBufHandler.readInt();
      this.count = byteBufHandler.readInt();
      this.timestamp = byteBufHandler.readLong();
      this.count3 = byteBufHandler.readInt();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public int getCount3() {
      return this.count3;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public ExtraLongEntry() {
   }

   public ExtraLongEntry(int count4, int count5, int count6, int count7, String text) {
      this.count2 = count4;
      this.count = count5;
      this.timestamp = (long)count6;
      this.count3 = count7;
      this.labelText = text;
   }
}
