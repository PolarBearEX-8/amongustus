package client.util;

import client.event.PostMotionProcessor;

public class AuxiliaryListenerProcessor implements PostMotionProcessor {
   public String labelText3;
   public byte[] byteFlagArray;
   public String labelText;
   public String labelText2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      String labelText4 = this.labelText2;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText3;
      byteBufHandler.applyTextByText(labelText5);
      String labelText6 = this.labelText;
      byteBufHandler.applyTextByText(labelText6);
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public AuxiliaryListenerProcessor() {
   }

   public AuxiliaryListenerProcessor(byte[] byteValueArray, String text, String text2, String text3) {
      this.byteFlagArray = byteValueArray;
      this.labelText2 = text;
      this.labelText3 = text2;
      this.labelText = text3;
   }
}
