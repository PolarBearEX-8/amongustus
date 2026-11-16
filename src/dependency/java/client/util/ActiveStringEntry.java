package client.util;

import client.event.PostMotionProcessor;

public class ActiveStringEntry implements PostMotionProcessor {
   public String labelText2;
   public String labelText;
   public String labelText3;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      String labelText4 = this.labelText3;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText2;
      byteBufHandler.applyText(labelText5);
      String labelText6 = this.labelText;
      byteBufHandler.applyTextByText(labelText6);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getText();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public ActiveStringEntry(byte[] byteValueArray, String text, String text2, String text3) {
      this.byteFlagArray = byteValueArray;
      this.labelText3 = text;
      this.labelText2 = text2;
      this.labelText = text3;
   }
}
