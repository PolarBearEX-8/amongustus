package client.util;

import client.event.PostMotionProcessor;

public class BaseListenerAdapter implements PostMotionProcessor {
   public int count2;
   public int count3;
   public String labelText3;
   public long timestamp;
   public String labelText;
   public String labelText4;
   public int count;
   public String labelText6;
   public String labelText5;
   public String labelText2;

   public String getLabelText5() {
      return this.labelText5;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText7 = this.labelText4;
      byteBufHandler.applyTextByText(labelText7);
      String labelText8 = this.labelText3;
      byteBufHandler.applyTextByText(labelText8);
      String labelText9 = this.labelText6;
      byteBufHandler.applyTextByText(labelText9);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
      String labelText10 = this.labelText;
      byteBufHandler.applyTextByText(labelText10);
      String labelText11 = this.labelText2;
      byteBufHandler.applyTextByText(labelText11);
      int count4 = this.count;
      byteBufHandler.writeInt(count4);
      int count5 = this.count2;
      byteBufHandler.writeInt(count5);
      String labelText12 = this.labelText5;
      byteBufHandler.applyTextByText(labelText12);
      int count6 = this.count3;
      byteBufHandler.writeInt(count6);
   }

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount3() {
      return this.count3;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText4 = byteBufHandler.getTextAsText();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText6 = byteBufHandler.getTextAsText();
      this.timestamp = byteBufHandler.readLong();
      this.labelText = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.count = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
      this.labelText5 = byteBufHandler.getTextAsText();
      this.count3 = byteBufHandler.readInt();
   }

   public String getLabelText4() {
      return this.labelText4;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText6() {
      return this.labelText6;
   }
}
