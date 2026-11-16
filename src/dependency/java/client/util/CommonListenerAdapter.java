package client.util;

import client.event.PostMotionProcessor;

public class CommonListenerAdapter implements PostMotionProcessor {
   public String labelText3;
   public String labelText;
   public String labelText2;
   public int count2;
   public int count;

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText4 = this.labelText;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText3;
      byteBufHandler.applyTextByText(labelText5);
      String labelText6 = this.labelText2;
      byteBufHandler.applyTextByText(labelText6);
      int count3 = this.count;
      byteBufHandler.writeInt(count3);
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText = byteBufHandler.getTextAsText();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.count = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
   }

   public int getCount2() {
      return this.count2;
   }

   public CommonListenerAdapter(String text, String text2, String text3, int count3, int count4) {
      this.labelText = text;
      this.labelText3 = text2;
      this.labelText2 = text3;
      this.count = count3;
      this.count2 = count4;
   }

   public CommonListenerAdapter() {
   }
}
