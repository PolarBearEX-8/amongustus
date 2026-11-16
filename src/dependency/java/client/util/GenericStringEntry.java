package client.util;

import client.event.PostMotionProcessor;

public class GenericStringEntry implements PostMotionProcessor {
   public String labelText;
   public int count3;
   public int count2;
   public String labelText2;
   public int count;
   public String labelText4;
   public String labelText3;

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText4() {
      return this.labelText4;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText5 = this.labelText2;
      byteBufHandler.applyTextByText(labelText5);
      String labelText6 = this.labelText3;
      byteBufHandler.applyTextByText(labelText6);
      String labelText7 = this.labelText;
      byteBufHandler.applyTextByText(labelText7);
      int count4 = this.count;
      byteBufHandler.writeInt(count4);
      int count5 = this.count3;
      byteBufHandler.writeInt(count5);
      int count6 = this.count2;
      byteBufHandler.writeInt(count6);
      String labelText8 = this.labelText4;
      byteBufHandler.applyTextByText(labelText8);
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText2 = byteBufHandler.getTextAsText();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText = byteBufHandler.getTextAsText();
      this.count = byteBufHandler.readInt();
      this.count3 = byteBufHandler.readInt();
      this.count2 = byteBufHandler.readInt();
      this.labelText4 = byteBufHandler.getTextAsText();
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public int getCount2() {
      return this.count2;
   }

   public GenericStringEntry(String text, String text2, int count4, String text3, int count5, int count6, String text4) {
      this.labelText2 = text;
      this.labelText3 = text2;
      this.labelText = text3;
      this.count = count5;
      this.count3 = count6;
      this.count2 = count4;
      this.labelText4 = text4;
   }

   public GenericStringEntry() {
   }
}
